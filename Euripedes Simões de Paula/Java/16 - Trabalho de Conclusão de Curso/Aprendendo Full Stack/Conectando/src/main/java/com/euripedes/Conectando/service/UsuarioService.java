package com.euripedes.Conectando.service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.euripedes.Conectando.model.Usuario;
import com.euripedes.Conectando.repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
//	CREATE - cadastrar
	public Optional<Usuario> createUsuario(Usuario usuario) {
		if (usuarioRepository.findByUsuario(usuario.getNome()).isPresent()) 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já cadastrado!");
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		return Optional.of(usuarioRepository.save(usuario));
	}
	
//	CREATE - entrar
	public String loginUsuario(String username, String password) {

		Usuario usuario = usuarioRepository.findByUsuario(username).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
		
		if (!passwordEncoder.matches(password, usuario.getSenha())) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha invalida");
		}
		
		return jwtService.generateToken(usuario.getUsuario(), usuario.getTipo());
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
	
	public Optional<Usuario> getUsuario(String usuario) {
		return usuarioRepository.findByUsuario(usuario);
	}
	
public String generatorBasicToken(String usuario, String senha) {
	    String auth = usuario + ":" + senha;
	    byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
	    return "Basic " + new String(encodedAuth);
	}
	
	public String generateJwtToken(Usuario usuario) {
		return Jwts.builder()
				.setSubject(usuario.getUsuario())
				.claim("tipo", usuario.getTipo())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 86400000))
				.signWith(SignatureAlgorithm.HS512, "secreta123")
				.compact();
	}
	
}