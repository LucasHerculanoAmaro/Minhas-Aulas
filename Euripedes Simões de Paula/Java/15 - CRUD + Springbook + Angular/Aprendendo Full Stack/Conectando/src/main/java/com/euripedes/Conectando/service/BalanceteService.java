package com.euripedes.Conectando.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.euripedes.Conectando.model.Balancete;
import com.euripedes.Conectando.model.Conta;
import com.euripedes.Conectando.model.LancamentoContabil;
import com.euripedes.Conectando.repository.BalanceteRepository;
import com.euripedes.Conectando.repository.ContaRepository;
import com.euripedes.Conectando.repository.LancamentoContabilRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class BalanceteService {

    @Autowired
    private BalanceteRepository balanceteRepository;
    
    @Autowired
    private ContaRepository contaRepository;
    
    @Autowired
    private LancamentoContabilRepository lancamentoRepository;
    
    public void atualizarBalancete(LancamentoContabil lancamento) {
        // Encontrar as tuplas de Balancete associadas ao lançamento
        Balancete balanceteCredito = balanceteRepository.findAllByConta_ContaId(lancamento.getCreditoId());
        Balancete balanceteDebito = balanceteRepository.findAllByConta_ContaId(lancamento.getDebitoId());

        // Atualizar os saldos conforme necessário
        balanceteCredito.setSaldo(balanceteCredito.getSaldo() + lancamento.getValor());
        balanceteDebito.setSaldo(balanceteDebito.getSaldo() - lancamento.getValor());

        // Salvar as atualizações no banco
        balanceteRepository.save(balanceteCredito);
        balanceteRepository.save(balanceteDebito);
    }

    

}
