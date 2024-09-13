package com.euripedes.Conectando.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.euripedes.Conectando.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{
	
	//List<Conta> findByConta(String conta);
	
	Optional<Conta> findById(Long id);
	
	//Optional<Conta> findByCodigo(Conta codigoDebito);
	
	Optional<Conta> findByCodigo(String codigo);
	
	Conta findByNome(String nome);

	
}
