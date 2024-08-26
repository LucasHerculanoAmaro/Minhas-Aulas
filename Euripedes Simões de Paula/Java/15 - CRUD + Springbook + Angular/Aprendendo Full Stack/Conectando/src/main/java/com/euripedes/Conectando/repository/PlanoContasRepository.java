package com.euripedes.Conectando.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.euripedes.Conectando.model.PlanoContas;

public interface PlanoContasRepository extends JpaRepository<PlanoContas, Long>{

	Optional<PlanoContas> findByPlanoContas(String planoContas);
	
	List<PlanoContas> findAllByNomeContainingIgnoreCase(String planoContas);
	
}
