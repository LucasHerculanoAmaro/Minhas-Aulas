package com.euripedes.Conectando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.euripedes.Conectando.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}

