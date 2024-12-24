package com.euripedes.Conectando.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.euripedes.Conectando.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter{
	
	private final JwtService jwtService;
	
	public JwtAuthorizationFilter(JwtService jwtService) {
		this.jwtService = jwtService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws java.io.IOException, jakarta.servlet.ServletException {
		
		System.out.println("Authorization Header: " + request.getHeader("Authorization"));
		
		String header = request.getHeader("Authorization");
		
		
		if (header == null || !header.startsWith("Bearer ")) {
			System.out.println("Token null");
			filterChain.doFilter(request, response);
			return;
		}
		
		String token = jwtService.extractToken(request);
		System.out.println("Token Extraído: " + token);
		
		if (token != null && jwtService.isTokenValid(token)) {
			var authentication = jwtService.getAuthentication(token);
			if (authentication instanceof UsernamePasswordAuthenticationToken) {
				((UsernamePasswordAuthenticationToken) authentication)
					.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			}
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		if (token != null && jwtService.isTokenValid(token)) {
			System.out.println("Token é válido: " + token);
			var authentication = jwtService.getAuthentication(token);
			
			if (authentication != null) {
				System.out.println("Usuário authenticado: " + authentication.getName());
				System.out.println("Autoridades: " + authentication.getAuthorities());
			}
			
			if (authentication instanceof UsernamePasswordAuthenticationToken) {
				((UsernamePasswordAuthenticationToken) authentication)
					.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			}
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} else {
			System.out.println("Token inválido: " + token);
		}
		
		filterChain.doFilter(request, response);
		
	}
	
}
