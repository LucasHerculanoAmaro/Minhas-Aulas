package com.euripedes.Conectando.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.euripedes.Conectando.model.Balancete;
import com.euripedes.Conectando.model.LancamentoContabil;
import com.euripedes.Conectando.service.BalanceteService;

@RestController
@RequestMapping("/balancete")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BalanceteController {

	@Autowired
	private BalanceteService balanceteService;
	
	@GetMapping
	public ResponseEntity<List<Balancete>> listarBalancete() {
		List<Balancete> balancete = balanceteService.listarBalancete();
		return ResponseEntity.ok(balancete);
	}
	
	@PostMapping
    public ResponseEntity<Balancete> criarBalancete(
            @RequestParam Long lancamentoId, 
            @RequestBody Balancete balancete) {
        Balancete novoBalancete = balanceteService.criarBalancete(lancamentoId, balancete);
        return ResponseEntity.ok(novoBalancete);
    }
	
	@PostMapping("/atualizar")
	public ResponseEntity<Void> atualizarBalancete(@RequestBody LancamentoContabil lancamento) {
		balanceteService.atualizarBalancete(lancamento);
		return ResponseEntity.ok().build();
	}
	
}
