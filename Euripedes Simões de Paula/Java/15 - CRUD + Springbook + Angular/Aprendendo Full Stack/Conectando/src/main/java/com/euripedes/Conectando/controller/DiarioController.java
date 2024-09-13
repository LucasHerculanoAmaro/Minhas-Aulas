package com.euripedes.Conectando.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.euripedes.Conectando.model.LancamentoContabil;
import com.euripedes.Conectando.service.DiarioService;

@RestController
@RequestMapping("/diario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DiarioController {

	@Autowired
	private DiarioService diarioService;
	
	@GetMapping("/lancamentos")
	public ResponseEntity<List<LancamentoContabil>> listarLancamento() {
		List<LancamentoContabil> lancamentos = diarioService.listarLancamento();
		return ResponseEntity.ok(lancamentos);
	}
	
}
