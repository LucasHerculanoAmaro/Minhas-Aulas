package com.euripedes.Conectando.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.euripedes.Conectando.model.Razao;

public interface RazaoRepository extends JpaRepository<Razao, Long>{

	Optional<Razao> findByContaId(Long contaId);
	
	List<Razao> findByDiarioId(Long diarioId);
	
    List<Razao> findByData(LocalDate data); 

    List<Razao> findByDataBetween(LocalDate startDate, LocalDate endDate);
    
    List<Razao> findByConta_Id(Long contaId);
    
    List<Razao> findByHistoricoContaining(String palavra);
    
    List<Razao> findByDebitoGreaterThan(BigDecimal valor); 

    List<Razao> findByCreditoGreaterThan(BigDecimal valor); 
    
    List<Razao> findByDebitoBetween(BigDecimal valorMinimo, BigDecimal valorMaximo); 

    List<Razao> findByCreditoBetween(BigDecimal valorMinimo, BigDecimal valorMaximo); 
    
    @Query("SELECT r FROM Razao r WHERE r.credito BETWEEN :valorMinimo AND :valorMaximo OR r.debito BETWEEN :valorMinimo AND :valorMaximo")
    List<Razao> findByCreditoOrDebitoBetween(
    		@Param("valorMinimo") BigDecimal valorMinimo,
    		@Param("valorMaximo") BigDecimal valorMaximo);
    
	Optional<Razao> findByContaIdAndDiarioId(Long contaId, Long diarioId);

}
