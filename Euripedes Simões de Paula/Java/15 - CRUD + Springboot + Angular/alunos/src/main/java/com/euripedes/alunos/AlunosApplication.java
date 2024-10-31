package com.euripedes.alunos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AlunosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlunosApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurer() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/**")
	                    .allowedOrigins("http://localhost:4200") // Especifique o domínio
	                    .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
	                    .allowCredentials(true); // Permite credenciais
	        }
	    };
	}
}
