package com.euripedes.Conectando.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
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
    private final UserDetailsServiceImplements userDetailsService;

    public JwtAuthorizationFilter(JwtService jwtService, @Lazy UserDetailsServiceImplements userDetailsService) {
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
            throws ServletException, IOException {
        
        String token = getTokenFromRequest(request);
        
        if (token != null && jwtService.isTokenValid(token)) {
            String usuario = jwtService.getUsernameFromToken(token);
            
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    "usuário autenicado", null, new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
        } else {
            logger.debug("JWT ausente ou inválido: " + token);
        }
        
        filterChain.doFilter(request, response);
        
    }
    
    private String getTokenFromRequest(HttpServletRequest request) {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {            
        	return header.substring(7);
        } else {
            System.out.printf("\nHeader Authorization está nulo ou mal formatado\n");
            return null;
        }

    }

}
