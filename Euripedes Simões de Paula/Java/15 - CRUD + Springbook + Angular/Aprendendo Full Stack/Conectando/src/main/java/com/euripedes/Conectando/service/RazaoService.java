package com.euripedes.Conectando.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.euripedes.Conectando.model.Conta;
import com.euripedes.Conectando.model.LancamentoContabil;
import com.euripedes.Conectando.repository.LancamentoContabilRepository;

@Service
public class RazaoService {

	@Autowired
	private LancamentoContabilRepository lancamentoContabilRepository;
	
	public Map<Conta, List<LancamentoContabil>> listarLancamentoPorConta() {
		List<LancamentoContabil> lancamentos = lancamentoContabilRepository.findAll();
		return lancamentos.stream()
						  .collect(Collectors.groupingBy(LancamentoContabil::getCodigoDebito));
	}
	
	public Double calcularSaldoPorConta(Conta conta) {
		List<LancamentoContabil> lancamentos = lancamentoContabilRepository.findByCodigoDebitoOrCodigoCredito(conta, conta);
		
		Double debitos = lancamentos.stream()
									.filter(l -> l.getCodigoDebito().equals(conta))
									.mapToDouble(lancamento -> lancamento.getValor().doubleValue())
									.sum();
		
		Double creditos = lancamentos.stream()
									 .filter(l -> l.getCodigoCredito().equals(conta))
									 .mapToDouble(lancamento -> lancamento.getValor().doubleValue())
									 .sum();
		return debitos - creditos;
	}
	
}
