package com.euripedes.Conectando.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.euripedes.Conectando.model.RazaoAnalitico;

@Repository
public interface RazaoAnaliticoRepository extends JpaRepository<RazaoAnalitico, Long> {

	Optional<RazaoAnalitico> findByID(RazaoAnalitico id);
	
	List<RazaoAnaliticoRepository> findByConta(String conta);
	
}
