package com.euripedes.Conectando.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.euripedes.Conectando.model.Usuario;
import com.euripedes.Conectando.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> createUsuario(Usuario usuario) {
		if (usuarioRepository.findByUsuario(usuario.getNome())) {
			
		}
	}
	
	
}
