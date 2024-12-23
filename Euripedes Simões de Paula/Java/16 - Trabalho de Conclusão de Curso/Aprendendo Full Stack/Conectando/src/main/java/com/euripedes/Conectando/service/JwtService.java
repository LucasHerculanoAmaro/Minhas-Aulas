package com.euripedes.Conectando.service;

import java.util.Collections;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtService {
	
	private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512); // "sua Senha";
	private final long EXPIRATION_TIME = 86400000L;
	
	public String generateToken(String username, String tipo) {
		return Jwts.builder()
				.setSubject(username)
				.claim("tipo", tipo)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
	}
	
	public String extractToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		
		if (header != null && header.startsWith("Bearer ")) {
			return header.substring(7);
		}
		
		return null;
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}
	
	public Authentication getAuthentication(String token) {
		
		String usuario = getUsernameFromToken(token);
		return new UsernamePasswordAuthenticationToken(
				usuario, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
		);
	}
	
	private String getUsernameFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().getSubject();
	}
	
}
