package com.euripedes.Conectando.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.euripedes.Conectando.model.PlanoContas;
import com.euripedes.Conectando.repository.PlanoContasRepository;

@RestController
@RequestMapping("/planoContas")
public class PlanoContasController {

	@Autowired
	private PlanoContasRepository planoContasRepository;
	
	@Autowired
	private PlanoContas planoContas;
	
	static {
		System.out.println("Controlador Plano Contas carregado");
	}
	
	// Método GET para obter um plano de contas
	@GetMapping("/all")
	public List<PlanoContas> getAllPlanoContas() {
		return planoContasRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PlanoContas> getPlanoConta(@RequestBody Long id) {
		PlanoContas planoContas = planoContasRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Plano de Conta não encontrado."));
		return ResponseEntity.ok(planoContas);
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<PlanoContas> createPlanoConta(@RequestBody PlanoContas planoContas){
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(planoContasRepository
							 .save(planoContas));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PlanoContas> updatePlanoConta(
		@PathVariable Long id, 
		@RequestBody PlanoContas planoContasDetails) {
				
			PlanoContas planoContas = planoContasRepository.findById(id).orElseThrow(
					() -> new RuntimeException("Plano de Contas não encontrado.")
			);
				
			planoContas.setCodigo(planoContasDetails.getCodigo());
			planoContas.setNome(planoContasDetails.getNome());
			planoContas.setDescricao(planoContasDetails.getDescricao());
				
			PlanoContas updatePlanoContas = planoContasRepository.save(planoContas);
			
			return ResponseEntity.ok(updatePlanoContas);
	}
	

	
}
