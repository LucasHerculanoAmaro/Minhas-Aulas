package com.euripedes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.euripedes.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
