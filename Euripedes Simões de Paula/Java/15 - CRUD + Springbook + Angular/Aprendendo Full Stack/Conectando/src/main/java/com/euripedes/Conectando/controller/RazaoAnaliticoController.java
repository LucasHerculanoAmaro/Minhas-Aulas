package com.euripedes.Conectando.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.euripedes.Conectando.model.LancamentoContabil;
import com.euripedes.Conectando.service.RazaoAnaliticoService;

public class RazaoAnaliticoController {

	@Autowired
	private RazaoAnaliticoService razaoAnaliticoService;
	
	@GetMapping("/conta/{Id}")
	public List<LancamentoContabil> listarTransacoesPorConta(@PathVariable Long id){
		return razaoAnaliticoService.listarTransacoesPorConta(id);
	}
	
	@GetMapping("/conta/{id}/saldo")
	public BigDecimal calcularSaldoPorConta(@PathVariable Long id) {
		return razaoAnaliticoService.calcularSaldoPorConta(id);
	}
	
}
