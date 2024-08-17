package com.euripedes.Conectando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.euripedes.Conectando.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
