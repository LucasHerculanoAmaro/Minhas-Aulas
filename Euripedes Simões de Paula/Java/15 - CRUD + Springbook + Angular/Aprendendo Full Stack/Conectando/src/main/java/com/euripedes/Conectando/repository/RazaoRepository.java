package com.euripedes.Conectando.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.euripedes.Conectando.model.Razao;

public interface RazaoRepository extends JpaRepository<Razao, Long>{

	Optional<Razao> findByContaId(Long contaId);
	
}
