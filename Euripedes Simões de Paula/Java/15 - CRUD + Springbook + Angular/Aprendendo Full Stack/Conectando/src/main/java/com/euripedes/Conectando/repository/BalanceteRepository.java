package com.euripedes.Conectando.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.euripedes.Conectando.model.Balancete;

public interface BalanceteRepository extends JpaRepository<Balancete, Long>{
		
	Optional<Balancete> findById(Balancete id);
	
}
