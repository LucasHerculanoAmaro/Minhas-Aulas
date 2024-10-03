package com.euripedes.Conectando.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.euripedes.Conectando.model.Cliente;
import com.euripedes.Conectando.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;	
	
//	Método GET
	@GetMapping("all")
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
	
//	Método PUT
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> updateCliente(
			@PathVariable Long id, 
			@RequestBody Cliente clienteDetails) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
		
		cliente.setNomeCliente(clienteDetails.getNomeCliente());
		cliente.setCnpj(clienteDetails.getCnpj());
		
		Cliente updateCliente = clienteRepository.save(cliente);
		return ResponseEntity.ok(updateCliente);
	}
	
//	Método DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
		clienteRepository.delete(cliente);
		return ResponseEntity.noContent().build();
	}
	
}