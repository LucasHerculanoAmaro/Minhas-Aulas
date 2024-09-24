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
    private RazaoService razaoService;

    public Diario createDiario(Diario diario) {

        // Salvar o lançamento contábil
        Diario novoDiario = diarioRepository.save(diario);

        // Atualizar o balancete
        balanceteService.atualizarBalancete(novoDiario);
        razaoService.atualizarRazao(novoDiario);

        return novoDiario;
    }

    public Diario updateDiario(Long id, Diario diarioAtualizado) {
        Diario diarioExistente = diarioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Lançamento não encontrado"));

        diarioExistente.setCredito(diarioAtualizado.getCredito());
        diarioExistente.setDebito(diarioAtualizado.getDebito());
        diarioExistente.setValor(diarioAtualizado.getValor());
        diarioExistente.setData(diarioAtualizado.getData());
        diarioExistente.setHistorico(diarioAtualizado.getHistorico());

        Diario diarioAtualizadoFinal = diarioRepository.save(diarioExistente);

        // Atualizar o balancete
        balanceteService.atualizarBalancete(diarioAtualizadoFinal);
        razaoService.atualizarRazao(diarioAtualizadoFinal);

        return diarioAtualizadoFinal;
    }
    
    public boolean deletarTransacao(Long id) {
        Optional<Diario> optionalDiario = diarioRepository.findById(id);
        if (optionalDiario.isPresent()) {
            Diario diario = optionalDiario.get();
            
            // Atualizar o balancete com base no lançamento antes de deletá-lo
            balanceteService.atualizarBalanceteAoDeletarDiario(diario);
            
            // Atualizar o razão com base no lançamento antes de deletá-lo
            razaoService.atualizarRazao(diario);
            
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

