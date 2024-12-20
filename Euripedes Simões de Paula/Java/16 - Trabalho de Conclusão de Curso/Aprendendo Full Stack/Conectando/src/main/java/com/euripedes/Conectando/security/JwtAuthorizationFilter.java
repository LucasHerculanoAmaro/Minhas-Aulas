package com.euripedes.Conectando.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.euripedes.Conectando.service.JwtService;

import jakarta.servlet.FilterChain;
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
		
		String token = jwtService.extractToken(request);
		
		if (token != null && jwtService.isTokenValid(token)) {
			
			var authentication = jwtService.getAuthentication(token);
			
			if (authentication instanceof UsernamePasswordAuthenticationToken) {
				
				((UsernamePasswordAuthenticationToken) authentication)
					.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			}
		
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
		
	}
	
}
