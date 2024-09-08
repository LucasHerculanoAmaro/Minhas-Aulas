package com.euripedes.Conectando.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.euripedes.Conectando.model.LancamentoContabil;

public interface LancamentoContabilRepository extends JpaRepository<LancamentoContabil, Long> {

	Optional<LancamentoContabil> findById(Long id);

	List<LancamentoContabil> findByContaId(Long id);
	
	@Query(value = "SELECT SUM(lancamento_devedor) FROM lancamento", nativeQuery = true)
	BigDecimal sumDebitoById(Long Id);

	@Query(value = "SELECT SUM(lancamento_credor) FROM lancamento", nativeQuery = true)
	BigDecimal sumCreditoById(Long Id);
	

}
