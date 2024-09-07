package com.euripedes.Conectando.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.euripedes.Conectando.repository.BalanceteRepository;

public class BalanceteService {

	@Autowired
	private BalanceteRepository balanceteRepository;
	
	public BigDecimal calcularTotalGeral() { 
		BigDecimal totalDevedor = balanceteRepository.sumSaldoDevedor();
		BigDecimal totalCredor = balanceteRepository.sumSaldoCredor();
		return totalDevedor.subtract(totalCredor);
	}
//	
//	public List<Conta> listarContas() {
//		return balanceteRepository.findAllContas();
//	}
	
}
