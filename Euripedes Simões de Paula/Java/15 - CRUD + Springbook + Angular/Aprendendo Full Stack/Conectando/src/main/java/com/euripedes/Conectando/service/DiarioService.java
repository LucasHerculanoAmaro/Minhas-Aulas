package com.euripedes.Conectando.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.euripedes.Conectando.model.Conta;
import com.euripedes.Conectando.model.DiarioGeral;
import com.euripedes.Conectando.model.LancamentoContabil;
import com.euripedes.Conectando.repository.ContaRepository;
import com.euripedes.Conectando.repository.DiarioGeralRepository;
import com.euripedes.Conectando.repository.LancamentoContabilRepository;

@Service
public class DiarioService {

	@Autowired
	private DiarioGeralRepository diarioRepository;
	
	public List<DiarioGeral> obterTransacoesPorDatas(LocalDate inicio, LocalDate fim) {
		return diarioRepository.findByDataBetween(inicio, fim);
	}
	
	public LancamentoContabil registrarTransacao(LancamentoContabil lancamento) {
		
		Conta contaDebito = ContaRepository.findById(lancamento.getContaDebito().getId())
				.orElseThrow(() -> new RuntimeException("Conta de Débito não encontrada"));
		Conta contaCredito = ContaRepository.findById(lancamento.getContaCredito().getId())
				.oeElseThrow(() -> new RuntimeException("Conta de Crédito não encontrada"));
		
		lancamento.setContaDebito(contaDebito);
		lancamento.setContaCredito(contaCredito);
		lancamento.setData(LocalDate.now());
		
		return LancamentoContabilRepository.save(lancamento);
	}
	
}
