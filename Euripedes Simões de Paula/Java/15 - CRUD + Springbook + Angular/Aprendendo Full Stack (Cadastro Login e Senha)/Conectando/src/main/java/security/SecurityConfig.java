package security;



import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/* Aqui vamos criar as nossas configurações de segurança */

// Esta anotação indica que estamos habilitando os recursos de segurança de nossa aplicação
@EnableWebSecurity
public class SecurityConfig {

/*	A anotação "@Bean" é uma anotação que permite exportar uma classe para o Spring para que 
 	ele consiga expertar essa classe e fazer ineções de dependência dela em outras classes.
*/	
	@Bean 
	
/* 	Vamos utilizar a classe SecurityFilterChain para criar uma cadeia de filtros
	que podem se comparadas com requisições http a fim de decidir qual decisão se aplica
	com a requisição.
*/
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeHttpRequests(
				authorize -> authorize.antMatchers("/api/auth/**").permitAll().anyRequest().authenticated()
        )
        .formLogin().and().httpBasic();
		
		return http.build();
		
	}
	
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
}


/* REFERENCIAS

*	O que é Spring Security?
	https://blog.algaworks.com/spring-security/#:~:text=Importante%20n%C3%A3o%20esquecer%20da%20anota%C3%A7%C3%A3o,de%20seguran%C3%A7a%20em%20nossa%20aplica%C3%A7%C3%A3o.

*	Dúvidas sobre o @Bean
	https://cursos.alura.com.br/forum/topico-duvida-sobre-o-bean-113349#:~:text=O%20%40Bean%20serve%20para%20exportar,depend%C3%AAncia%20dela%20em%20outra%20classes.

*/