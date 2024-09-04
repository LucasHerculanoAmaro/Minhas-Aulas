package com.euripedes.Conectando.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.euripedes.Conectando.model.Conta;
import com.euripedes.Conectando.model.LancamentoContabil;
import com.euripedes.Conectando.model.Transacao;
import com.euripedes.Conectando.repository.ContaRepository;
import com.euripedes.Conectando.repository.LancamentoContabilRepository;
import com.euripedes.Conectando.repository.TransacaoRepository;

import jakarta.transaction.Transactional;

@Service
public class ContabilidadeService {

	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private TransacaoRepository transacaoRepository;
	
	@Autowired
	private LancamentoContabilRepository lancamentoContabilRepository;
	
	@Transactional
	public void realizarLancamento(Long contaDebitoId, Long contaCreditoId, BigDecimal valor, String descricao) {
		
		Conta contaDebito = contaRepository.findById(contaDebitoId).orElseThrow();
		Conta contaCredito = contaRepository.findById(contaCreditoId).orElseThrow();
		
		Transacao transacaoDebito = new Transacao();
		transacaoDebito.setConta(contaDebito);
		transacaoDebito.setValor(valor);
		transacaoDebito.setTipo("Debito");
		transacaoDebito.setData(LocalDate.now());
		
		Transacao transacaoCredito = new Transacao();
		transacaoCredito.setConta(contaCredito);
		transacaoCredito.setValor(valor);
		transacaoCredito.setTipo("Credito");
		transacaoCredito.setData(LocalDate.now());
		
		transacaoRepository.save(transacaoDebito);
		transacaoRepository.save(transacaoCredito);
		
		contaDebito.setSaldo(contaDebito.getSaldo().subtract(valor));
		contaCredito.setSaldo(contaCredito.getSaldo().subtract(valor));
		
		contaRepository.save(contaDebito);
		contaRepository.save(contaCredito);
		
		LancamentoContabil lancamento = new LancamentoContabil();
		lancamento.setData(LocalDate.now());
		lancamento.setDescricao(descricao);
		lancamento.setTransacaoDebito(transacaoDebito);
		lancamento.setTransacaoCredito(transacaoCredito);
		
		lancamentoContabilRepository.save(lancamento);
		
		
		
	}
	
}
