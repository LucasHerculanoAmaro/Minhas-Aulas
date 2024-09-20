package com.euripedes.Conectando.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.euripedes.Conectando.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

	Optional<Conta> findById(Conta credito);
}

