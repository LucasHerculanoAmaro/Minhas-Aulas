package com.euripedes.Conectando.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.euripedes.Conectando.model.Balancete;
import com.euripedes.Conectando.model.Conta;

public interface BalanceteRepository extends JpaRepository<Balancete, Long> {
    
	Balancete findByContaId(Conta conta);
	
	Optional<Balancete> findByConta(Conta conta);
	
	Balancete findByContaId(Long contaId);
	
}

