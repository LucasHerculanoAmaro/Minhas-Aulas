package com.euripedes.Conectando.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.euripedes.Conectando.model.Balancete;
import com.euripedes.Conectando.model.Conta;
import com.euripedes.Conectando.model.LancamentoContabil;
import com.euripedes.Conectando.repository.BalanceteRepository;
import com.euripedes.Conectando.repository.ContaRepository;
import com.euripedes.Conectando.repository.LancamentoContabilRepository;
import com.euripedesConectando.ResourceNotFoundException.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class LancamentoContabilService {

    @Autowired
    private LancamentoContabilRepository lancamentoContabilRepository;
    
    @Autowired
    private BalanceteRepository balanceteRepository;
    
    @Autowired
    private BalanceteService balanceteService;
    
    @Autowired
    private ContaRepository contaRepository;



    // Listar todos os lançamentos
    public List<LancamentoContabil> getAll() {
        return lancamentoContabilRepository.findAll();
    }

    // Buscar um lançamento por ID
    public LancamentoContabil getById(Long id) {
        return lancamentoContabilRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Lançamento não encontrado com id " + id));
    }
    
    // Criar um novo lançamento
    public LancamentoContabil createLancamento(LancamentoContabil lancamento) {
    	
    	// Salva o lançamento
        LancamentoContabil novoLancamento = lancamentoContabilRepository.save(lancamento);

        // Atualiza o balancete para a conta débito
        Balancete balanceteDebito = new Balancete();
        balanceteDebito.setConta(novoLancamento.getDebitoId());
        balanceteDebito.setLancamento(novoLancamento);  				// Associa o lançamento ao balancete
        balanceteDebito.setSaldoDevedor(novoLancamento.getValor());  	// Define o saldo devedor
        balanceteDebito.setSaldoCredor(BigDecimal.ZERO);  				// Sem saldo credor
        balanceteRepository.save(balanceteDebito);

        // Atualiza o balancete para a conta crédito
        Balancete balanceteCredito = new Balancete();
        balanceteCredito.setConta(novoLancamento.getCreditoId());
        balanceteCredito.setLancamento(novoLancamento);  				// Associa o lançamento ao balancete
        balanceteCredito.setSaldoCredor(novoLancamento.getValor());  	// Define o saldo credor
        balanceteCredito.setSaldoDevedor(BigDecimal.ZERO);  			// Sem saldo devedor
        balanceteRepository.save(balanceteCredito);

        return novoLancamento;
    	
    }
    
    // Atualizar um lançamento existente
    public LancamentoContabil updateLancamento(Long id, LancamentoContabil lancamentoAtualizado) {
        // Busca o lançamento existente pelo ID
        LancamentoContabil lancamentoExistente = lancamentoContabilRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Lançamento não encontrado"));

        // Busca as contas de crédito e débito pelo ID no banco de dados
        Conta credito = contaRepository.findById(lancamentoAtualizado.getCreditoId().getContaId())
            .orElseThrow(() -> new EntityNotFoundException("Conta de crédito não encontrada"));
        Conta debito = contaRepository.findById(lancamentoAtualizado.getDebitoId().getContaId())
            .orElseThrow(() -> new EntityNotFoundException("Conta de débito não encontrada"));

        // Atualiza os valores do lançamento
        lancamentoExistente.setCreditoId(credito);
        lancamentoExistente.setDebitoId(debito);
        lancamentoExistente.setValor(lancamentoAtualizado.getValor());
        lancamentoExistente.setData(lancamentoAtualizado.getData());
        lancamentoExistente.setHistorico(lancamentoAtualizado.getHistorico());

        // Salva o lançamento atualizado e retorna o objeto
        return lancamentoContabilRepository.save(lancamentoExistente);
    }


 

//    // Deletar um lançamento
//    @Transactional
//    public void deletarLancamento(Long id) {
//
//    	// Encontrar o lançamento a ser deletado pelo ID
//        Optional<LancamentoContabil> lancamentoOpt = lancamentoContabilRepository.findById(id);
//        if (lancamentoOpt.isPresent()) {
//            LancamentoContabil lancamento = lancamentoOpt.get();
//
//            // Atualizar o valor no balancete de débito
//            Optional<Balancete> balanceteDebitoOpt = balanceteRepository.findByConta(lancamento.getDebitoId());
//            
//            if (balanceteDebitoOpt.isPresent()) {
//                Balancete balanceteDebito = balanceteDebitoOpt.get();
//                
//                // Subtrair o valor do lançamento (porque estamos removendo o lançamento)
//                balanceteDebito.setValor(balanceteDebito.getValor().subtract(lancamento.getValor()));
//                balanceteRepository.save(balanceteDebito);
//            }
//
//            // Atualizar o valor no balancete de crédito
//            Optional<Balancete> balanceteCreditoOpt = balanceteRepository.findByConta(lancamento.getCreditoId());
//            
//            if (balanceteCreditoOpt.isPresent()) {
//                Balancete balanceteCredito = balanceteCreditoOpt.get();
//
//                // Subtrair o valor do lançamento (porque estamos removendo o lançamento)
//                balanceteCredito.setValor(balanceteCredito.getValor().subtract(lancamento.getValor()));
//                balanceteRepository.save(balanceteCredito);
//            }
//          
//
//            //balanceteService.atualizarBalancete(lancamento);    
//            //balanceteService.excluirBalancetesPorLancamentoId(id);
//            // Finalmente, deletar o lançamento
//            lancamentoContabilRepository.deleteById(id);
//            
//        } else {
//        	throw new RuntimeException("Não há Lançamentos com o ID informado.");
//        }
//    	
//        
//    }
    
}

