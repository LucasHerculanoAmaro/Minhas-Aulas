package com.euripedes.Conectando.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.euripedes.Conectando.model.Diario;
import com.euripedes.Conectando.repository.DiarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DiarioService {

    @Autowired
    private DiarioRepository diarioRepository;
    @Autowired
    private BalanceteService balanceteService;
    @Autowired
    private HistoricoService historicoService;
    @Autowired
    private RazaoService razaoService;

    public Diario createDiario(Diario diario, String usuario) {

        // Salvar o lançamento contábil
        Diario novoDiario = diarioRepository.save(diario);

        // Atualizar o balancete
        balanceteService.atualizarBalancete(novoDiario);
        razaoService.criarRazao(novoDiario);
        historicoService.registrarHistorico(novoDiario, usuario, "Criando um novo registro!");

        return novoDiario;
    }

    public Diario updateDiario(Long id, Diario diarioAtualizado, String usuario) {
        Diario diarioExistente = diarioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Lançamento não encontrado"));

        String alteracoes = detectarAlteracoes(diarioExistente, diarioAtualizado);
        
        diarioExistente.setCredito(diarioAtualizado.getCredito());
        diarioExistente.setDebito(diarioAtualizado.getDebito());
        diarioExistente.setValor(diarioAtualizado.getValor());
        diarioExistente.setData(diarioAtualizado.getData());
        diarioExistente.setHistorico(diarioAtualizado.getHistorico());

        Diario diarioAtualizadoFinal = diarioRepository.save(diarioExistente);
        
        // Atualizar o balancete
        balanceteService.atualizarBalancete(diarioAtualizadoFinal);
        razaoService.atualizarRazao(diarioAtualizadoFinal);
        historicoService.registrarHistorico(diarioAtualizadoFinal, usuario, alteracoes);

        return diarioAtualizadoFinal;
    }
    
 // Método para detectar as alterações realizadas no lançamento
    private String detectarAlteracoes(Diario lancamentoAntigo, Diario lancamentoNovo) {
        StringBuilder alteracoes = new StringBuilder();

        if (!lancamentoAntigo.getDebito().equals(lancamentoNovo.getDebito())) {
            alteracoes.append("Conta Débito alterada. ");
        }
        if (!lancamentoAntigo.getCredito().equals(lancamentoNovo.getCredito())) {
            alteracoes.append("Conta Crédito alterada. ");
        }
        if (!lancamentoAntigo.getValor().equals(lancamentoNovo.getValor())) {
            alteracoes.append("Valor alterado. ");
        }
        if (!lancamentoAntigo.getData().equals(lancamentoNovo.getData())) {
            alteracoes.append("Data alterada. ");
        }
        if (!lancamentoAntigo.getHistorico().equals(lancamentoNovo.getHistorico())) {
            alteracoes.append("Histórico alterado. ");
        }

        return alteracoes.toString();
    }
    
    public boolean deletarTransacao(Long id) {
        Optional<Diario> optionalDiario = diarioRepository.findById(id);
        if (optionalDiario.isPresent()) {
            Diario diario = optionalDiario.get();
            
            // Atualizar o balancete com base no lançamento antes de deletá-lo
            balanceteService.atualizarBalanceteAoDeletarDiario(diario);
            
            // Atualizar o razão com base no lançamento antes de deletá-lo
            razaoService.criarRazao(diario);
            
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

    // Filtro por Conta de Crédito
    public List<Diario> buscarPorContaCredito(Long creditoId) {
        return diarioRepository.findByCreditoId(creditoId);
    }

    // Filtro por Conta de Débito
    public List<Diario> buscarPorContaDebito(Long debitoId) {
        return diarioRepository.findByDebitoId(debitoId);
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

