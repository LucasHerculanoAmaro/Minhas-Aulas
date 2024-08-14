package com.euripedes.Conectando;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication( exclude = {SecurityAutoConfiguration.class})
public class ConectandoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConectandoApplication.class, args);
	}
	
}