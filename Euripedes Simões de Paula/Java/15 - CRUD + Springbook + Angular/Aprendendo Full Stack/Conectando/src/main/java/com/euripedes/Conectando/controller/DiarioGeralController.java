package com.euripedes.Conectando.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.euripedes.Conectando.model.LancamentoContabil;
import com.euripedes.Conectando.repository.LancamentoContabilRepository;

@RestController
@RequestMapping("/api/diario")
public class DiarioGeralController {

	@Autowired
	private LancamentoContabilRepository lancamentoContabilRepository;
	
	@GetMapping
	public List<LancamentoContabil> listarTransacoes() {
		return lancamentoContabilRepository.findAll();
	}
	
	@PostMapping
	public LancamentoContabil criarTransacoes(@RequestBody LancamentoContabil lancamento) {
		return lancamentoContabilRepository.save(lancamento);
	}
	
}
