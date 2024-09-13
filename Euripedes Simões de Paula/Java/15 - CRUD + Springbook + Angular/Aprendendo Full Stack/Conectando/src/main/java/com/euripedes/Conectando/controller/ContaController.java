package com.euripedes.Conectando.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.euripedes.Conectando.model.Conta;
import com.euripedes.Conectando.repository.ContaRepository;

@RestController
@RequestMapping("/contas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContaController {

	@Autowired
	private ContaRepository contaRepository;
	
	@PostMapping
	public Conta criarConta(@RequestBody Conta conta) {
		return contaRepository.save(conta);
	}
	
    @GetMapping("/all")
    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }
	
}
