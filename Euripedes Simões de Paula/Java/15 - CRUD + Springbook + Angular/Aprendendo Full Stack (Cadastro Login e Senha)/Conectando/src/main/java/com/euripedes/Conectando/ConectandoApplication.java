package com.euripedes.Conectando;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.euripedes.Conectando"})
public class ConectandoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConectandoApplication.class, args);
	}

}
