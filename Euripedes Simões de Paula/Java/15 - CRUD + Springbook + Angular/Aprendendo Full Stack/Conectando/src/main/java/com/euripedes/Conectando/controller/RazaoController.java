package com.euripedes.Conectando.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.euripedes.Conectando.model.Conta;
import com.euripedes.Conectando.model.LancamentoContabil;
import com.euripedes.Conectando.service.RazaoService;

@RestController
@RequestMapping("/razao")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RazaoController {

	@Autowired
	private RazaoService razaoService;
	
	@GetMapping("/lancamentos")
	public ResponseEntity<Map<Conta, List<LancamentoContabil>>> listarLancamentosPorConta() {
		Map<Conta, List<LancamentoContabil>> lancamentosPorConta = razaoService.listarLancamentoPorConta();
		return ResponseEntity.ok(lancamentosPorConta);
	}
	
	@GetMapping("/saldo/{idConta}")
	public ResponseEntity<Double> calcularSaldoPorConta(@PathVariable Long idConta) {
		Conta conta = new Conta();
		conta.setContaId(idConta);
		Double saldo = razaoService.calcularSaldoPorConta(conta);
		return ResponseEntity.ok(saldo);
	}
	
}
