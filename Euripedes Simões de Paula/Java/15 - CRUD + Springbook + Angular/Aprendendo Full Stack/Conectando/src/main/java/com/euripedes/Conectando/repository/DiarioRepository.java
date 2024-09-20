package com.euripedes.Conectando.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.euripedes.Conectando.model.Diario;

public interface DiarioRepository  extends JpaRepository<Diario, Long> {

	Optional<Diario> findById(Long id);
	
}

