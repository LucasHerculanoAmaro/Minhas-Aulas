package com.euripedes.Conectando.service;

import java.net.http.HttpHeaders;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.euripedes.Conectando.model.Usuario;
import com.euripedes.Conectando.model.UsuarioLogin;
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
	
//	CREATE - CADASTRAR
	public Optional<Usuario> createUsuario(Usuario usuario) {
		
		if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já cadastrado!", null);
			usuario.setSenha(
					passwordEncoder.encode(usuario.getSenha())
					);
			return Optional.of(usuarioRepository.save(usuario));
	}
	
//	POST - LOGIN
	public Optional<UsuarioLogin> loginUsuario(Optional<UsuarioLogin> usuarioLogin) {

		Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());
		
	    if (usuario.isPresent()) {
	        if (passwordEncoder.matches(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {
	        	
	        	String token = jwtService.generateToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getTipo());//"USER");
	            
	            usuarioLogin.get().setToken(token);//.setUsuario(usuario.get().getUsuario());

	            System.out.println("Token JWT gerado: " + token + " com tipo: ");	            

	        	return usuarioLogin;
	        } else {
	            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha inválida.");
	        }
	       
	    } else {
	        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário inválido.");
	    }
	}
	
//	UPDATE
	public ResponseEntity<Usuario> updateUsuario(Usuario usuario) {
		if (usuarioRepository.findById(usuario.getId()).isPresent()) {
			Optional<Usuario> buscandoUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
			if (buscandoUsuario.isPresent()) {
				if(buscandoUsuario.get().getId() != usuario.getId())
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já cadastrado!", null);
			}
			usuario.setSenha(criptografarSenha(usuario.getSenha()));
//			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
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
	
	private String criptografarSenha(String senha ) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(senha);
		return senhaEncoder;
	}
	
}