package com.euripedes.Conectando.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.euripedes.Conectando.model.RazaoAnalitico;

public interface RazaoAnaliticoRepository extends JpaRepository<RazaoAnalitico, Long> {

	Optional<RazaoAnalitico> findByID(RazaoAnalitico id);
	
}
