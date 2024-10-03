package com.euripedes.Conectando.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.euripedes.Conectando.model.Diario;

public interface DiarioRepository  extends JpaRepository<Diario, Long> {

	Optional<Diario> findById(Long id);
	
	List<Diario> findByDataBetween(LocalDate startDate, LocalDate endDate);
	
	List<Diario> findByCreditoId(Long creditoId);
	
	List<Diario> findByDebitoId(Long dabitoId);
	
	List<Diario> findByValorBetween(BigDecimal valorMinimo, BigDecimal valorMaximo);
	
	List<Diario> findByHistoricoContaining(String palavra);
	
}