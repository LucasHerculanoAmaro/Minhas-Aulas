package com.euripedes.alunos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.euripedes.alunos.exception.ResourceNotFoundException;
import com.euripedes.alunos.model.Aluno;
import com.euripedes.alunos.repository.AlunoRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/alunos")
public class AlunoController {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@GetMapping("/todos")
	public List<Aluno> getAllAlunos() {
		return alunoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Aluno> getAlunoById(@PathVariable Long id) {
		Aluno aluno = alunoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));
		return ResponseEntity.ok(aluno);
	}
	
	@PostMapping("/criar")
	public Aluno createAluno(@RequestBody Aluno aluno) {
		return alunoRepository.save(aluno);
	}
	
	
	
	
}
