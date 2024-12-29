package com.euripedes.Conectando.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.euripedes.Conectando.service.JwtService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthorizationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
    	
        String header = request.getHeader("Authorization");
        
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = jwtService.extractToken(request);//header.substring(7); // Remove "Bearer " do início
        
        try {
        	
        	if (token != null && jwtService.isTokenValid(token)) {
        		Claims claims = jwtService.getClaims(token);
        		String username = claims.getSubject();
        		String role = claims.get("role", String.class);
        		
//        		List<String> roles = jwtService.getRoles(token);
//        		List<SimpleGrantedAuthority> authorities = roles.stream()
//        				.map(SimpleGrantedAuthority::new)
//        				.toList();
//	            Authentication authentication = jwtService.getAuthentication(token);
        		if (username != null && role != null) {
        			List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
		            UsernamePasswordAuthenticationToken auth =
		            		new UsernamePasswordAuthenticationToken(username, null, authorities);
		            SecurityContextHolder.getContext().setAuthentication(auth);
        		}
        	}
        	
        } catch (Exception e) {
        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        chain.doFilter(request, response);
    }
}
