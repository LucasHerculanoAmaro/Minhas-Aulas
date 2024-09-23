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
	
//	Filtro para ID
	List<Razao> findByDiarioId(Long diarioId);
	
//	Filtro para Data
    List<Razao> findByData(LocalDate data); 

//  Filtro para um intervalo de Datas 
    List<Razao> findByDataBetween(LocalDate startDate, LocalDate endDate);
    
    List<Razao> findByConta_Id(Long contaId);
    
//  Filtro para Histórico
    List<Razao> findByHistoricoContaining(String palavra);
    
    List<Razao> findByDebitoGreaterThan(BigDecimal valor); // Filtro de débitos

    List<Razao> findByCreditoGreaterThan(BigDecimal valor); // Filtro de créditos
    
    List<Razao> findByDebitoBetween(BigDecimal valorMinimo, BigDecimal valorMaximo); // Intervalo de débito

    List<Razao> findByCreditoBetween(BigDecimal valorMinimo, BigDecimal valorMaximo); // Intervalo de crédito
    
    @Query("SELECT r FROM Razao r WHERE r.credito BETWEEN :valorMinimo AND :valorMaximo OR r.debito BETWEEN :valorMinimo AND :valorMaximo")
    List<Razao> findByCreditoOrDebitoBetween(
    		@Param("valorMinimo") BigDecimal valorMinimo,
    		@Param("valorMaximo") BigDecimal valorMaximo);
    
}
