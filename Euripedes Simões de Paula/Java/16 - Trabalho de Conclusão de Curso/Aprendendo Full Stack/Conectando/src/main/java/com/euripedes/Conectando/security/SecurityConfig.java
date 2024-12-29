package com.euripedes.Conectando.security;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
	
	private final JwtService jwtService;
	
    public SecurityConfig(JwtService jwtService) {
        this.jwtService = jwtService;
    }
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			
        http
        	.cors(cors -> cors.configurationSource(corsConfigurationSource()))
        	.csrf().disable()
            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
            	.requestMatchers("usuarios/logar").permitAll()
            	.requestMatchers("/usuarios/cadastrar").hasRole("ADMIN")
            	.requestMatchers("/diario/**").hasRole("USER")
            	.anyRequest().authenticated()
            )
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(new JwtAuthorizationFilter(jwtService), UsernamePasswordAuthenticationFilter.class);
            
        return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}	
	
	@Bean
	public AuthenticationManager authManager(HttpSecurity http)  throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).build();
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
