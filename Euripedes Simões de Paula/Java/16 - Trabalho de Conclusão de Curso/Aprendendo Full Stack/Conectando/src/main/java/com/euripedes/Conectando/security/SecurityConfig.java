package com.euripedes.Conectando.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import com.euripedes.Conectando.service.JwtService;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {
	
	private final JwtAuthorizationFilter jwtAuthorizationFilter;
	private final JwtService jwtService;
	
    public SecurityConfig(JwtService jwtService, JwtAuthorizationFilter jwtAuthorizationFilter) {
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
		this.jwtService = jwtService;
    }
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			
        http
        	.csrf().disable()
        	.cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
            	.requestMatchers("usuarios/logar","/diario/**").permitAll()
//            	.requestMatchers("/diario/**").hasRole("ADMIN")
//            	.requestMatchers("/usuarios/cadastrar").hasRole("USER")
            	.anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
	}
	
	@Bean
	public AuthenticationManager authManager(/*HttpSecurity http*/AuthenticationConfiguration configuration)  throws Exception {
		return configuration.getAuthenticationManager(); //http.getSharedObject(AuthenticationManagerBuilder.class).build();
	}
		
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200")); // Adicione aqui as origens permitidas
	    configuration.setAllowedMethods(Collections.singletonList("*")); // Permitir todos os métodos (GET, POST, etc.)
	    configuration.setAllowedHeaders(Collections.singletonList("*")); // Permitir todos os cabeçalhos
	    configuration.setAllowCredentials(true); // Permitir cookies/sessões compartilhadas

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}
	
}
