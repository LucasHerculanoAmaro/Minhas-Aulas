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

        // Cria um novo Balancete e associa ao lançamento
        Balancete novoBalancete = new Balancete();
        novoBalancete.setLancamento(lancamento);
        
        // Define os saldos de acordo com o tipo de conta (débito ou crédito)
        if (lancamento.getCodigoDebito().getContaId().equals(balancete.getConta().getContaId())) {
            novoBalancete.setSaldoDevedor(lancamento.getValor());
            novoBalancete.setSaldoCredor(BigDecimal.ZERO);
        } else if (lancamento.getCodigoCredito().getContaId().equals(balancete.getConta().getContaId())) {
            novoBalancete.setSaldoCredor(lancamento.getValor());
            novoBalancete.setSaldoDevedor(BigDecimal.ZERO);
        }


        // Salvar o Balancete
        return balanceteRepository.save(novoBalancete);
    }
    
//    public void atualizarBalancete(LancamentoContabil lancamento) {
//    	// Buscar a conta débito pelo código
//        Conta contaDebito = contaRepository.findByCodigo(lancamento.getCodigoDebito().getCodigo())
//                .orElseThrow(() -> new EntityNotFoundException("Conta débito não encontrada"));
//
//        // Atualizar saldo da conta débito
//        atualizarSaldo(contaDebito, lancamento.getValor(), true);
//
//        // Buscar a conta crédito pelo código
//        Conta contaCredito = contaRepository.findByCodigo(lancamento.getCodigoCredito().getCodigo())
//                .orElseThrow(() -> new EntityNotFoundException("Conta crédito não encontrada"));
//
//        // Atualizar saldo da conta crédito
//        atualizarSaldo(contaCredito, lancamento.getValor(), false);
//        
//    	Optional<Balancete> balanceteCreditoOpt = balanceteRepository.findByConta(lancamento.getCodigoCredito());
//        if (balanceteCreditoOpt.isPresent()) {
//            Balancete balanceteCredito = balanceteCreditoOpt.get();
//            
//            // Ajusta o valor do balancete
//            balanceteCredito.setValor(balanceteCredito.getValor().subtract(lancamento.getValor()));
//            
//            balanceteRepository.save(balanceteCredito);
//        }
//
//        // Repita o processo para outros tipos de contas ou atributos, conforme necessário
//        // Exemplo para débito
//        Optional<Balancete> balanceteDebitoOpt = balanceteRepository.findByConta(lancamento.getCodigoDebito());
//        if (balanceteDebitoOpt.isPresent()) {
//            Balancete balanceteDebito = balanceteDebitoOpt.get();
//            
//            // Ajusta o valor do balancete
//            balanceteDebito.setValor(balanceteDebito.getValor().add(lancamento.getValor()));
//            
//            balanceteRepository.save(balanceteDebito);
//        }
//        
//    }
    public void atualizarBalancete(LancamentoContabil lancamento) {
//        // Verificar se o código de débito não é nulo
//        Optional.ofNullable(lancamento.getCodigoDebito()).ifPresent(codigoDebito -> {
//            // Buscar a conta débito pelo código
//            Conta contaDebito = contaRepository.findByCodigo(codigoDebito.getCodigo())
//                    .orElseThrow(() -> new EntityNotFoundException("Conta débito não encontrada"));
//
//            // Atualizar saldo da conta débito
//            atualizarSaldo(contaDebito, lancamento.getValor(), true);
//
//            // Buscar e atualizar o balancete da conta débito
//            balanceteRepository.findByConta(codigoDebito).ifPresent(balanceteDebito -> {
//                balanceteDebito.setValor(balanceteDebito.getValor().add(lancamento.getValor()));
//                balanceteRepository.save(balanceteDebito);
//            });
//        });
//
//        // Verificar se o código de crédito não é nulo
//        Optional.ofNullable(lancamento.getCodigoCredito()).ifPresent(codigoCredito -> {
//            // Buscar a conta crédito pelo código
//            Conta contaCredito = contaRepository.findByCodigo(codigoCredito.getCodigo())
//                    .orElseThrow(() -> new EntityNotFoundException("Conta crédito não encontrada"));
//
//            // Atualizar saldo da conta crédito
//            atualizarSaldo(contaCredito, lancamento.getValor(), false);
//
//            // Buscar e atualizar o balancete da conta crédito
//            balanceteRepository.findByConta(codigoCredito).ifPresent(balanceteCredito -> {
//                balanceteCredito.setValor(balanceteCredito.getValor().subtract(lancamento.getValor()));
//                balanceteRepository.save(balanceteCredito);
//            });
//        });

    	    Conta contaDebito = lancamento.getCodigoDebito();
    	    Conta contaCredito = lancamento.getCodigoCredito();

    	    // Verifica se a conta de débito existe no balancete
    	    Balancete balanceteDebito = balanceteRepository.findByConta(contaDebito)
    	        .orElseThrow(() -> new RuntimeException("Conta de débito não encontrada no balancete"));

    	    // Verifica se a conta de crédito existe no balancete
    	    Balancete balanceteCredito = balanceteRepository.findByConta(contaCredito)
    	        .orElseThrow(() -> new RuntimeException("Conta de crédito não encontrada no balancete"));

    	    // Agora você pode trabalhar com balanceteDebito e balanceteCredito, sabendo que eles existem
    	    balanceteDebito.setSaldoDevedor(balanceteDebito.getSaldoDevedor().max(lancamento.getValor()));
    	    balanceteCredito.setSaldoCredor(balanceteCredito.getSaldoCredor().min(lancamento.getValor()));

    	    balanceteRepository.save(balanceteDebito);
    	    balanceteRepository.save(balanceteCredito);
    	

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
    	
        balanceteRepository.deleteByLancamentoId(lancamentoId);
        System.out.println("Balancetes com o lançamento ID " + lancamentoId + " foram excluídos com sucesso.");
    	
    }

    public List<Balancete> listarBalancete() {
        return balanceteRepository.findAll();
    }
    
    

}
