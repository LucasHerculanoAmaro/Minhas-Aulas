package com.euripedes.Conectando.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.euripedes.Conectando.model.Balancete;
import com.euripedes.Conectando.model.LancamentoContabil;
import com.euripedes.Conectando.repository.BalanceteRepository;
import com.euripedes.Conectando.repository.LancamentoContabilRepository;
import com.euripedesConectando.ResourceNotFoundException.ResourceNotFoundException;

@Service
public class LancamentoContabilService {

    @Autowired
    private LancamentoContabilRepository lancamentoContabilRepository;
    
    @Autowired
    private BalanceteRepository balanceteRepository;
    
    @Autowired
    private BalanceteService balanceteService;

    // Criar um novo lançamento
    public LancamentoContabil criarLancamento(LancamentoContabil lancamento) {
//
//        // Salva o lançamento
//        LancamentoContabil lancamentoSalvo = lancamentoContabilRepository.save(novoLancamento);
//
//        // Criar uma nova entrada no Balancete para cada operação no Lancamento
//        adicionarBalancete(lancamentoSalvo);
//
//        return lancamentoSalvo;
    	
    	// Salva o lançamento
        LancamentoContabil novoLancamento = lancamentoContabilRepository.save(lancamento);

        // Cria o balancete para a conta débito
        Balancete balanceteDebito = new Balancete();
        balanceteDebito.setConta(lancamento.getCodigoDebito());
        balanceteDebito.setValor(lancamento.getValor());
        balanceteDebito.setLancamento(novoLancamento);  // Associa o lancamento ao balancete
        balanceteRepository.save(balanceteDebito);

        // Cria o balancete para a conta crédito
        Balancete balanceteCredito = new Balancete();
        balanceteCredito.setConta(lancamento.getCodigoCredito());
        balanceteCredito.setValor(lancamento.getValor().negate());  // Valor negativo
        balanceteCredito.setLancamento(novoLancamento);  // Associa o lancamento ao balancete
        balanceteRepository.save(balanceteCredito);

        return novoLancamento;
    	
    }

    // Listar todos os lançamentos
    public List<LancamentoContabil> listarLancamentos() {
        return lancamentoContabilRepository.findAll();
    }

    // Buscar um lançamento por ID
    public LancamentoContabil buscarLancamentoPorId(Long id) {
        return lancamentoContabilRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Lançamento não encontrado com id " + id));
    }

    public LancamentoContabil cadastrarTransacao(LancamentoContabil lancamentoContabil) {
        LancamentoContabil novoLancamento = lancamentoContabilRepository.save(lancamentoContabil);

        // Atualizar balancete com o id do novo lançamento
        Balancete balanceteDebito = new Balancete();
        balanceteDebito.setConta(lancamentoContabil.getCodigoDebito());
        balanceteDebito.setLancamentoId(novoLancamento.getId()); // Setando o ID do lançamento
        balanceteDebito.setSaldoDevedor(lancamentoContabil.getValor());
        balanceteRepository.save(balanceteDebito);

        Balancete balanceteCredito = new Balancete();
        balanceteCredito.setConta(lancamentoContabil.getCodigoCredito());
        balanceteCredito.setLancamentoId(novoLancamento.getId()); // Setando o ID do lançamento
        balanceteCredito.setSaldoCredor(lancamentoContabil.getValor());
        balanceteRepository.save(balanceteCredito);

        return novoLancamento;
    }
    
    // Atualizar um lançamento existente
    public LancamentoContabil atualizarLancamento(Long id, LancamentoContabil lancamentoAtualizado) {
        return lancamentoContabilRepository.findById(id)
            .map(lancamento -> {
                lancamento.setCodigoDebito(lancamentoAtualizado.getCodigoDebito());
                lancamento.setCodigoCredito(lancamentoAtualizado.getCodigoCredito());
                lancamento.setData(lancamentoAtualizado.getData());
                lancamento.setHistorico(lancamentoAtualizado.getHistorico());
                lancamento.setValor(lancamentoAtualizado.getValor());
                return lancamentoContabilRepository.save(lancamento);
            })
            .orElseThrow(() -> new ResourceNotFoundException("Lançamento não encontrado com id " + id));
    }

    // Deletar um lançamento
    public void deletarLancamento(Long id) {

    	// Encontrar o lançamento a ser deletado pelo ID
        Optional<LancamentoContabil> lancamentoOpt = lancamentoContabilRepository.findById(id);
        if (lancamentoOpt.isPresent()) {
            LancamentoContabil lancamento = lancamentoOpt.get();

            // Atualizar o valor no balancete de débito
            Optional<Balancete> balanceteDebitoOpt = balanceteRepository.findByConta(lancamento.getCodigoDebito());
            
            if (balanceteDebitoOpt.isPresent()) {
                Balancete balanceteDebito = balanceteDebitoOpt.get();
                
                // Subtrair o valor do lançamento (porque estamos removendo o lançamento)
                balanceteDebito.setValor(balanceteDebito.getValor().subtract(lancamento.getValor()));
                balanceteRepository.save(balanceteDebito);
            }

            // Atualizar o valor no balancete de crédito
            Optional<Balancete> balanceteCreditoOpt = balanceteRepository.findByConta(lancamento.getCodigoCredito());
            
            if (balanceteCreditoOpt.isPresent()) {
                Balancete balanceteCredito = balanceteCreditoOpt.get();

                // Subtrair o valor do lançamento (porque estamos removendo o lançamento)
                balanceteCredito.setValor(balanceteCredito.getValor().subtract(lancamento.getValor()));
                balanceteRepository.save(balanceteCredito);
            }

            balanceteService.atualizarBalancete(lancamento);
            
            // Finalmente, deletar o lançamento
            lancamentoContabilRepository.deleteById(id);
        }
        
    	
        
    }
    
}

