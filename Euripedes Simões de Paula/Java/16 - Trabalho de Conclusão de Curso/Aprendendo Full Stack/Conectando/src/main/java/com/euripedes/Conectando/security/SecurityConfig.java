package com.euripedes.Conectando.security;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.euripedes.Conectando.service.JwtService;

import io.swagger.v3.oas.models.PathItem.HttpMethod;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {
	
	private final JwtAuthorizationFilter jwtAuthorizationFilter;

	private final JwtService jwtService;
	
    public SecurityConfig(JwtService jwtService, JwtAuthorizationFilter jwtAuthorizationFilter) {
        this.jwtService = jwtService;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			
        http
        	.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues() )
        	.and()
        	.csrf().disable()
        	.authorizeRequests()
            	.requestMatchers(
            			"/usuarios/logar", 
            			"/usuarios/cadastrar", 
            			"/historico/transacoes", 
            			"/diario/transacoes"
            			).permitAll()//.hasAnyRole("USER", "ADMIN")
            	.requestMatchers("/diario/datas").authenticated()
            	.requestMatchers("/admin/**").hasRole("ADMIN")
            	.anyRequest().authenticated()
            	.and()
            	.addFilter(new JwtAuthorizationFilter(authenticationManager(authenticationManager)))
            	.addFilterBefore(new JwtAuthorizationFilter(jwtService), UsernamePasswordAuthenticationFilter.class);
            
        return http.build();
	}
	
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		
//        http
//        	.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues() )
//        	.and()
//        	.csrf().disable()
//            .authorizeHttpRequests(
//            		authorizeRequests -> authorizeRequests
//            	.requestMatchers("/usuarios/logar", "/usuarios/cadastrar").permitAll()
//            	.requestMatchers(
//            			
//            			"/historico/transacoes", 
//            			"/diario/transacoes", 
//            			"/diario/datas"
//            		).permitAll()//.hasAnyRole("USER", "ADMIN")
//            	.requestMatchers("/admin/**").hasRole("ADMIN")
//            	.anyRequest().authenticated()
//            )
//            .addFilterBefore(new JwtAuthorizationFilter(jwtService), UsernamePasswordAuthenticationFilter.class);
//            
//        return http.build();
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}	
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }//	public AuthenticationManager authManager(HttpSecurity http)  throws Exception {
//		return http.getSharedObject(AuthenticationManagerBuilder.class).build();
//	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:4200")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
						.allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}
	
}
