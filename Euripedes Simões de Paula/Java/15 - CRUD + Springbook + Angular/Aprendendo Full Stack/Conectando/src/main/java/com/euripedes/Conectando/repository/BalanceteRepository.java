package com.euripedes.Conectando.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.euripedes.Conectando.model.Balancete;
import com.euripedes.Conectando.model.Conta;

public interface BalanceteRepository extends JpaRepository<Balancete, Long>{

	Optional<Balancete> findByCodigoConta(String codigoConta);
	
	Optional<Balancete> findByCodigoConta(Conta conta);

	List<Balancete> findByLancamentoId(Long lancamentoId);
	
}
