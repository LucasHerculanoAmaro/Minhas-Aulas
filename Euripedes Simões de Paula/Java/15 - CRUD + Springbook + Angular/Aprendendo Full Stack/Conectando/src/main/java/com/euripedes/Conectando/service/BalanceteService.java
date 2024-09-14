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

import jakarta.persistence.EntityNotFoundException;

@Service
public class BalanceteService {

    @Autowired
    private BalanceteRepository balanceteRepository;
 
    public void atualizarBalancete(LancamentoContabil lancamento) {
        // Atualizar saldo da conta débito
        atualizarSaldo(lancamento.getCodigoDebito().getCodigo(), lancamento.getValor(), true);

        // Atualizar saldo da conta crédito
        atualizarSaldo(lancamento.getCodigoCredito().getCodigo(), lancamento.getValor(), false);
    }

    private void atualizarSaldo(Conta conta, BigDecimal valor, boolean isDebito) {
        // Busca o balancete pela conta
        Optional<Balancete> balanceteOpt = balanceteRepository.findByCodigoConta(conta);
        
        Balancete balancete;
        
        if (balanceteOpt.isPresent()) {
            balancete = balanceteOpt.get();
        } else {
            // Se não existir, cria um novo
            balancete = new Balancete();
            balancete.setCodigoConta(conta);
        }
        
        if (isDebito) {
            // Atualizar saldo devedor
            balancete.setSaldoDevedor(balancete.getSaldoDevedor().add(valor));
        } else {
            // Atualizar saldo credor
            balancete.setSaldoCredor(balancete.getSaldoCredor().add(valor));
        }

        // Salvar ou atualizar o balancete
        balanceteRepository.save(balancete);
    }
    
    public void atualizarBalanceteRemocao(LancamentoContabil lancamento) {
    	// Ajusta o saldo da conta de débito no balancete
        Balancete balanceteDebito = balanceteRepository.findById(lancamento.getId())
            .orElseThrow(() -> new EntityNotFoundException("Conta de débito não encontrada no balancete"));
        balanceteDebito.setSaldoDevedor(balanceteDebito.getSaldoDevedor().add(lancamento.getValor())); // Reverte o valor
        balanceteRepository.save(balanceteDebito);

        // Ajusta o saldo da conta de crédito no balancete
        Balancete balanceteCredito = balanceteRepository.findById(lancamento.getId())
            .orElseThrow(() -> new EntityNotFoundException("Conta de crédito não encontrada no balancete"));
        balanceteCredito.setSaldoCredor(balanceteCredito.getSaldoCredor().add(lancamento.getValor())); // Reverte o valor
        balanceteRepository.save(balanceteCredito);
    }
    
    public void excluirBalancetesPorLancamentoId(Long lancamentoId) {
        List<Balancete> balancetes = balanceteRepository.findByLancamentoId(lancamentoId);
        if (!balancetes.isEmpty()) {
            balanceteRepository.deleteAll(balancetes);
        }
    }

    public List<Balancete> listarBalancete() {
        return balanceteRepository.findAll();
    }
    
    

}
