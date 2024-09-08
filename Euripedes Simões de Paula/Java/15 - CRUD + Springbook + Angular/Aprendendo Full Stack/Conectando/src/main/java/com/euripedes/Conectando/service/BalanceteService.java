package com.euripedes.Conectando.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.euripedes.Conectando.model.Conta;
import com.euripedes.Conectando.repository.BalanceteRepository;
import com.euripedes.Conectando.repository.ContaRepository;

public class BalanceteService {

	@Autowired
	private BalanceteRepository balanceteRepository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	public BigDecimal calcularTotalGeral() { 
		BigDecimal totalDevedor = balanceteRepository.sumSaldoDevedor();
		BigDecimal totalCredor = balanceteRepository.sumSaldoCredor();
		return totalDevedor.subtract(totalCredor);
	}

	public List<Conta> listarContas() {
		return contaRepository.findAll();
	}
	
}
