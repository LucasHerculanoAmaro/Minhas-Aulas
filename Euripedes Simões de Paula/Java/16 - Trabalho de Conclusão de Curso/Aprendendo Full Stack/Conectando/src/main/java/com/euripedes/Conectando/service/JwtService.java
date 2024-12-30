package com.euripedes.Conectando.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtService {
	
	private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512); // "sua Senha";
	private final long EXPIRATION_TIME = 86400000L;
	
	public String generateToken(String username, String role) {
		return Jwts.builder()
				.setSubject(username)
				.claim("role", "ROLE_" + role.toUpperCase())//List.of(role))
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
	}
	
	public Claims getClaims(String token) {
	    return Jwts.parserBuilder()
	            .setSigningKey(SECRET_KEY)
	            .build()
	            .parseClaimsJws(token)
	            .getBody();
	}
	
	public String extractToken(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		
		if (token != null && token.startsWith("Bearer ")) {
			return token.substring(7);
		}
		
		return null;
	}

	public boolean isTokenValid(String token) {
				
		try {
//			Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
			System.out.println("Token inválido: " + e.getMessage() );
			return false;
		}
	}
	
	public Authentication getAuthentication(String token) {
		
		String usuario = getUsernameFromToken(token);
		return new UsernamePasswordAuthenticationToken(
				usuario, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
		);
	}
	
	public List<String> getRoles(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
		return claims.get("roles", List.class);
	}
	
	private String getUsernameFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().getSubject();
	}
	
}
