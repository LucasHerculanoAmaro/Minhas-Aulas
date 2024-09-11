package com.euripedes.Conectando.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.euripedes.Conectando.model.Balancete;
import com.euripedes.Conectando.model.Conta;
import com.euripedes.Conectando.service.BalanceteService;

@RestController
@RequestMapping("/balancete")
public class BalanceteController {

	@Autowired
	private BalanceteService balanceteService;
	
//	Unidade de Teste
	static {
		System.out.println("Controlador Balancete carregado");
	}
	
	@GetMapping("/total-geral")
	public BigDecimal calcularTotalGeral() {
		return balanceteService.calcularTotalGeral();
	}
	
	@GetMapping("/contas")
	public List<Conta> listarContas() {
		return balanceteService.listarContas();
	}
	
    @GetMapping("/gerar")
    public Balancete gerarBalancete() {
        return balanceteService.gerarBalancete();
    }
	
}
