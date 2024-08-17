package com.euripedes.Conectando.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.euripedes.Conectando.model.Cliente;
import com.euripedes.Conectando.model.Usuario;
import com.euripedes.Conectando.repository.ClienteRepository;
import com.euripedes.Conectando.repository.UsuarioRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
//	Unidade de Teste
	static {
		System.out.println("Controlador Cliente carregado");
	}

	@GetMapping("/test")
	public String testEndpoint() {
		return "API Cliente funcionando!";
	}	
	
//	Método GET
	@GetMapping
	public List<Cliente> getAllClientes() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
		return ResponseEntity.ok(cliente);
	}
	
//	Método POST
	@PostMapping("/cadastrar")
	public Cliente createCliente(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	
	
	
}
