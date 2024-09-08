package com.euripedes.Conectando.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.euripedes.Conectando.model.Balancete;
import com.euripedes.Conectando.model.Conta;

@Repository
public interface BalanceteRepository extends JpaRepository<Conta, Long>{
		
	Optional<Balancete> findById(Balancete id);
	
	@Query(value = "SELECT SUM(saldo_devedor) FROM Balancete", nativeQuery = true)
	BigDecimal sumSaldoDevedor();
	
	@Query(value = "SELECT SUM(saldo_credor) FROM Balancete", nativeQuery = true)
	BigDecimal sumSaldoCredor();
	
}
