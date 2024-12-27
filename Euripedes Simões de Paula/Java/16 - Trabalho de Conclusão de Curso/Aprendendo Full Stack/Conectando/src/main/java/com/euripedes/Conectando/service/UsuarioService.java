package com.euripedes.Conectando.service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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
	
//	CREATE - cadastrar
	public Optional<Usuario> createUsuario(Usuario usuario) {
		if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já cadastrado!", null);
		usuario.setSenha(
				passwordEncoder.encode(usuario.getSenha())
				);
		return Optional.of(usuarioRepository.save(usuario));
	}
	
//	CREATE - entrar
	public 
//		Optional<UsuarioLogin>
		Map<String, Object>
			loginUsuario(Optional<UsuarioLogin> usuarioLogin/*String usuario, String senha*/) {

		Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());

	    if (usuario.isPresent()) {
	        if (passwordEncoder.matches(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {
	            usuarioLogin.get().setUsuario(usuario.get().getUsuario());
	            
	            String token = jwtService.generateToken(usuarioLogin.get().getUsuario(), "USER");
	            
	            Map<String, Object> response = new HashMap<>();
	            response.put("usuarioLogin", usuarioLogin.get());
	            response.put("message", "Autenticação bem-suedida!");
	            response.put("token", token);

	        	return response;//usuarioLogin;
	        } else {
	            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha inválida.");
	        }
	    } else {
	        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário inválido.");
	    }
	}
		
//		if (usuario.isEmpty()) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
//		}
//		
//		Usuario user = usuario.get();
//		if (!passwordEncoder.matches(senha,  user.getSenha())) {
//			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha inválida.");
//		}
//		
//		return usuario;
		
//		Usuario username = usuarioRepository.findByUsuario(usuario)
//		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
		
//		if (!passwordEncoder.matches(senha, username.getSenha())) {
//			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha invalida");
//		}
		
//		return jwtService.generateToken(username.getUsuario(), username.getTipo());
//	}
	
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
	
	public String generateJwtToken(Usuario usuario) {
		return Jwts.builder()
				.setSubject(usuario.getUsuario())
				.claim("tipo", usuario.getTipo())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 86400000))
				.signWith(SignatureAlgorithm.HS512, "secreta123")
				.compact();
	}
	
	private String criptografarSenha(String senha ) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(senha);
		return senhaEncoder;
	}
	
}