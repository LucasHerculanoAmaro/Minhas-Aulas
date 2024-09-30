package com.euripedes.Conectando.service;

import java.nio.charset.StandardCharsets;
//import java.nio.charset.StandardCharsets;
import java.util.Optional;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.euripedes.Conectando.model.Usuario;
import com.euripedes.Conectando.model.UsuarioLogin;
import com.euripedes.Conectando.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	CREATE - cadastrar
	public Optional<Usuario> createUsuario(Usuario usuario) {
		if (usuarioRepository.findByUsuario(usuario.getNome()).isPresent()) 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já cadastrado!");
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		return Optional.of(usuarioRepository.save(usuario));
	}
	
//	CREATE - entrar
	public Optional<UsuarioLogin> loginUsuario(Optional<UsuarioLogin> usuarioLogin) {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());
		if (usuario.isPresent()) {
			if (compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {
				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setFoto(usuario.get().getFoto());
				usuarioLogin.get().setToken(generatorBasicToken( usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));
				usuarioLogin.get().setSenha(usuario.get().getSenha());
				usuarioLogin.get().setTipo(usuario.get().getTipo());
				return usuarioLogin;
			}
		}
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário ou Senha inválidos!", null);
	}
	
//	UPDATE
	public ResponseEntity<Usuario> updateUsuario(Usuario usuario) {
		if (usuarioRepository.findById(usuario.getId()).isPresent()) {
			Optional<Usuario> buscandoUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
			if (buscandoUsuario.isPresent()) {
				if(buscandoUsuario.get().getId() != usuario.getId())
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já cadastrado!", null);
			}
			//usuario.setSenha(criptografarSenha(usuario.getSenha()));
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			return ResponseEntity.status(200).body(usuarioRepository.save(usuario));
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não cadastrado!", null);
	}
	
//	Método para comparar senha
	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
		return passwordEncoder.matches(senhaDigitada, senhaBanco);
	}
	
	public String generatorBasicToken(String usuario, String senha) {
	    String auth = usuario + ":" + senha;
	    byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
	    return "Basic " + new String(encodedAuth);
	}
	
}