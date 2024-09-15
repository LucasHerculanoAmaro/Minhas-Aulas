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
    
    public Balancete criarBalancete(Long lancamentoId, Balancete balancete) {
        // Encontrar o lançamento pelo ID
        LancamentoContabil lancamento = lancamentoRepository.findById(lancamentoId)
                .orElseThrow(() -> new RuntimeException("Lançamento não encontrado"));

        // Criar um novo Balancete
        Balancete novoBalancete = new Balancete();
        novoBalancete.setLancamento(lancamento);
        // Definir outros atributos do Balancete a partir do DTO
        // ...

        // Salvar o Balancete
        return balanceteRepository.save(balancete);
    }
    
    public void atualizarBalancete(LancamentoContabil lancamento) {
//    	// Buscar a conta débito pelo código
        Conta contaDebito = contaRepository.findByCodigo(lancamento.getCodigoDebito().getCodigo())
                .orElseThrow(() -> new EntityNotFoundException("Conta débito não encontrada"));

        // Atualizar saldo da conta débito
        atualizarSaldo(contaDebito, lancamento.getValor(), true);

        // Buscar a conta crédito pelo código
        Conta contaCredito = contaRepository.findByCodigo(lancamento.getCodigoCredito().getCodigo())
                .orElseThrow(() -> new EntityNotFoundException("Conta crédito não encontrada"));

        // Atualizar saldo da conta crédito
        atualizarSaldo(contaCredito, lancamento.getValor(), false);
        
    	Optional<Balancete> balanceteCreditoOpt = balanceteRepository.findByConta(lancamento.getCodigoCredito());
        if (balanceteCreditoOpt.isPresent()) {
            Balancete balanceteCredito = balanceteCreditoOpt.get();
            
            // Ajusta o valor do balancete
            balanceteCredito.setValor(balanceteCredito.getValor().subtract(lancamento.getValor()));
            
            balanceteRepository.save(balanceteCredito);
        }

        // Repita o processo para outros tipos de contas ou atributos, conforme necessário
        // Exemplo para débito
        Optional<Balancete> balanceteDebitoOpt = balanceteRepository.findByConta(lancamento.getCodigoDebito());
        if (balanceteDebitoOpt.isPresent()) {
            Balancete balanceteDebito = balanceteDebitoOpt.get();
            
            // Ajusta o valor do balancete
            balanceteDebito.setValor(balanceteDebito.getValor().add(lancamento.getValor()));
            
            balanceteRepository.save(balanceteDebito);
        }
        
    }

    private void atualizarSaldo(Conta conta, BigDecimal valor, boolean isDebito) {
        // Busca o balancete pela conta
        Optional<Balancete> balanceteOpt = balanceteRepository.findByConta(conta);
        
        Balancete balancete;
        
        if (balanceteOpt.isPresent()) {
            balancete = balanceteOpt.get();
        } else {
            // Se não existir, cria um novo
            balancete = new Balancete();
            balancete.setConta(conta);  // Atualize aqui
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
    	// Obter o balancete para a conta de débito
        Optional<Balancete> balanceteDebitoOpt = balanceteRepository.findByConta(lancamento.getCodigoDebito());
        if (balanceteDebitoOpt.isPresent()) {
            Balancete balanceteDebito = balanceteDebitoOpt.get();
            
         // Se o valor for nulo, inicializa com BigDecimal.ZERO
            if (balanceteDebito.getValor() == null) {
            	balanceteDebito.setValor(BigDecimal.ZERO);
            }
            
            balanceteDebito.setValor(balanceteDebito.getValor().add(lancamento.getValor()));
        }

        // Obter o balancete para a conta de crédito
        Optional<Balancete> balanceteCreditoOpt = balanceteRepository.findByConta(lancamento.getCodigoCredito());
        if (balanceteCreditoOpt.isPresent()) {
            Balancete balanceteCredito = balanceteCreditoOpt.get();

            // Se o valor for nulo, inicializa com BigDecimal.ZERO
            if (balanceteCredito.getValor() == null) {
                balanceteCredito.setValor(BigDecimal.ZERO);
            }

            // Adiciona o valor do lançamento ao valor do balancete
            balanceteCredito.setValor(balanceteCredito.getValor().add(lancamento.getValor()));

            balanceteRepository.save(balanceteCredito);
        }
    }
    
    @Transactional
    public void excluirBalancetesPorLancamentoId(Long lancamentoId) {
        
    	balanceteRepository.deleteByLancamento_id(lancamentoId);
        System.out.println("Balancetes com o lançamento ID " + lancamentoId + " foram excluídos com sucesso.");
    	
    }

    public List<Balancete> listarBalancete() {
        return balanceteRepository.findAll();
    }
    
    

}
