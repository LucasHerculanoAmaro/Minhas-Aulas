package com.euripedes.Conectando.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.euripedes.Conectando.model.DiarioGeral;

public interface DiarioGeralRepository extends JpaRepository<DiarioGeral, Long>{

	Optional<DiarioGeral> findById(Long id);
	
	List<DiarioGeral> findByDataBetween(LocalDate inicio, LocalDate fim);
	
}
