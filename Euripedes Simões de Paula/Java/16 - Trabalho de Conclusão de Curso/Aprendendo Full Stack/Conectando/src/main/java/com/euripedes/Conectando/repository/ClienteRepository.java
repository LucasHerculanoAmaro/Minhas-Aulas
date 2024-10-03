package com.euripedes.Conectando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.euripedes.Conectando.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
