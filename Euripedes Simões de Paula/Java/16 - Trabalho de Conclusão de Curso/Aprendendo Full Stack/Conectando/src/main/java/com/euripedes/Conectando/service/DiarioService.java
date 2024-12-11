package com.euripedes.Conectando.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.euripedes.Conectando.model.Diario;
import com.euripedes.Conectando.repository.DiarioRepository;
import com.euripedes.Conectando.repository.HistoricoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DiarioService {

    @Autowired
    private DiarioRepository diarioRepository;
    @Autowired
    private HistoricoService historicoService;

    public Diario createDiario(Diario diario, String usuario) {

        // Salvar o lançamento contábil
        Diario novoDiario = diarioRepository.save(diario);

        historicoService.registrarHistorico(novoDiario, usuario, "Criando um novo registro!");

        return novoDiario;
    }

    public Diario updateDiario(Long id, Diario diarioAtualizado, String usuario) {
        // Recupera o estado anterior do lançamento
        Diario diarioExistente = diarioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Lançamento não encontrado"));

        // Detecta alterações (opcional, pode ser útil para histórico)
        String alteracoes = detectarAlteracoes(diarioExistente, diarioAtualizado);

        // Atualiza os campos do lançamento com os novos valores
        diarioExistente.setValor(diarioAtualizado.getValor());
        diarioExistente.setData(diarioAtualizado.getData());
        diarioExistente.setHistorico(diarioAtualizado.getHistorico());
        diarioExistente.setTransacao(diarioAtualizado.getTransacao());

        // Salva o lançamento atualizado no banco de dados
        Diario diarioAtualizadoFinal = diarioRepository.save(diarioExistente);
        
        // Registrar o histórico de alterações
        historicoService.registrarHistorico(diarioAtualizadoFinal, usuario, alteracoes);

        return diarioAtualizadoFinal;
    }
    
 // Método para detectar as alterações realizadas no lançamento
    private String detectarAlteracoes(Diario lancamentoAntigo, Diario lancamentoNovo) {
        StringBuilder alteracoes = new StringBuilder();

        if (!lancamentoAntigo.getValor().equals(lancamentoNovo.getValor())) {
            alteracoes.append("Valor alterado.");
        }
        if (!lancamentoAntigo.getData().equals(lancamentoNovo.getData())) {
            alteracoes.append("Data alterada.");
        }
        if (!lancamentoAntigo.getHistorico().equals(lancamentoNovo.getHistorico())) {
            alteracoes.append("Histórico alterado.");
        }
        if (!lancamentoAntigo.getTransacao().equals(lancamentoNovo.getTransacao())) {
        	alteracoes.append("Transação alterado.");
        }

        return alteracoes.toString();
    }
    
    public boolean deletarTransacao(Long id) {
        Optional<Diario> optionalDiario = diarioRepository.findById(id);
        if (optionalDiario.isPresent()) {
            Diario diario = optionalDiario.get();
                        
            // Excluir o histórico
            // historicoRepository.deleteByDiarioId(id);
            
            historicoService.registrarHistorico(diario, null, "Diário deletado." );

            
            // Excluir o Diário
            diarioRepository.deleteById(id);
            
            return true;
            
        } else {
            throw new EntityNotFoundException("Diário não encontrado com o ID: " + id);
        }
    }
    
    // Filtro por ID do Diário
    public Optional<Diario> buscarPorId(Long diarioId) {
        return diarioRepository.findById(diarioId);
    }

    // Filtro por Intervalo de Datas
    public List<Diario> buscarPorIntervaloDeDatas(LocalDate startDate, LocalDate endDate) {
        return diarioRepository.findByDataBetween(startDate, endDate);
    }

    // Filtro por Intervalo de Valores (Valor da transação)
    public List<Diario> buscarPorValorIntervalo(BigDecimal valorMinimo, BigDecimal valorMaximo) {
        return diarioRepository.findByValorBetween(valorMinimo, valorMaximo);
    }

    // Filtro por Histórico
    public List<Diario> buscarPorHistorico(String palavra) {
        return diarioRepository.findByHistoricoContaining(palavra);
    }
    
}

