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

    // Criar um novo lançamento
    public LancamentoContabil criarLancamento(LancamentoContabil novoLancamento) {
        return lancamentoContabilRepository.save(novoLancamento);
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
        balanceteDebito.setCodigoConta(lancamentoContabil.getCodigoDebito());
        balanceteDebito.setLancamentoId(novoLancamento.getId()); // Setando o ID do lançamento
        balanceteDebito.setSaldoDevedor(lancamentoContabil.getValor());
        balanceteRepository.save(balanceteDebito);

        Balancete balanceteCredito = new Balancete();
        balanceteCredito.setCodigoConta(lancamentoContabil.getCodigoCredito());
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
        LancamentoContabil lancamento = lancamentoContabilRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Lançamento não encontrado com id " + id));
        lancamentoContabilRepository.delete(lancamento);
    }
    
}

