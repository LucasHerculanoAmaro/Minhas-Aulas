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
 	ele consiga expertar essa classe e fazer ineções de dependência dela em outras classes. */	

	//@SuppressWarnings("removal")
	@Bean 
	
/* 	Vamos utilizar a classe SecurityFilterChain para criar uma cadeia de filtros
	que podem se comparadas com requisições http a fim de decidir qual decisão se aplica
	com a requisição. */
	public SecurityFilterChain securityFilterChain(
			
/* 			Está é uma classe do Spring Security que permite configurar a segurança de uma aplicação web
			Com esta classe podemos definir as politicas de segurnça para end-points específicos. */
			HttpSecurity http) 
	
// 					Aqui indicamos que o método pode gerar uma exceção
					throws Exception {
		
//		Aqui estamos invocando o parâmetro do método HttpSecurity
		http
		
//			A documentação recomenda desabilitar o csrf() em caso de não trabalho com  clientes/navegador 
			//.csrf().disable()
/*			O Spring Security permite que modele a sua autorização no nível de solicitação. 
			Aqui estamos declarando as nossas regras de autorização.	*/			
			.authorizeHttpRequests(
					
//				Vamos utilizar a expressão Lambda para criar a nossa regra de solicitação.
//				Abaixo criamos o argumento como expressão Lambda.
				(authorize) -> authorize
				
//				Especificando quais são as solicitações para configuração a segurança do Spring 
				.requestMatchers("/api/auth/**")
				
//				Permite que qualquer sessão do usuário seja autorizaa a executar este método
				.permitAll().anyRequest().authenticated()
        )
			
/*		Este método configura o formulário de Login padrão oferecido pelo Spring Security.
		Ele gera uma página de login que os usuários podem acessar para se autenticar. 
		
		OBS: Quando um usuário não autenticado tenta acessar uma URL protegida, o Spring Security 
		redireciona o usuário automaticamente para essa página de login.	*/
		.formLogin(
				form -> form.loginPage("/login").permitAll()
				)
		
//		Este mmétodo separa as partes de uma configuração sem precisar iniciar uma nova.
		//.and(withDefaults())
/*		Este método usa a autenticação simples de login e senha para operações de solicitação Http
		
		OBS: Essas credenciais são codificadas em Base64 e enviadas no cabeçalho Authorization. Embora seja 
		simples de implementar, o HTTP Basic não é seguro para transmissão de senhas em texto puro, a menos 
		que seja usado sobre HTTPS.		*/
		.httpBasic( 
				
/*				Passando a expressão Lambda para evitar: "The method withDefaults() is undefined for the 
				type SecurityConfig".	*/
				httpBasic -> {}
				);
		
//		Cria o Security Filter Chain com as configurações definidas.
		return http.build();	
	}

	@Bean
	
/*	Vamos implementar um método com base na interface "Password Encoder".
	Este método possibilita a transformação e o armazenamento da senha de forma segura.
	
	OBS: Password Encoder normalmente é utilizado para armazenar uma senha que precisa ser comparada com 
	uma senha forncedida pelo usuário no momento da autenticação.	*/
	public PasswordEncoder passwordEncoder() {
		
/*		Implementação de "Password Encoder" que usa a função hash do BCript para gerar senhas.
		OBS: O Hash é uma sequência de bits que tem como objetivo identificar um arquivo.	*/
		return new BCryptPasswordEncoder();
	}
	
/*	CONCLUSÃO
 	
 *	Com isso, terminamos as primeiras configurações de segurança, modificando filtros, requisições e
 	permissões http, e trabalhamos com o tratamento de senhas e criptografias com a interface
 	"Password Encoder".
 	
 	Na próxima aula, vamos trabalhar com o pacote "Model" e a classe "Users", algo não muito diferente
 	do que estudamos anteriormente.
 	
 */
	
}

/* REFERENCIAS

*	O que é Spring Security?
	https://blog.algaworks.com/spring-security/#:~:text=Importante%20n%C3%A3o%20esquecer%20da%20anota%C3%A7%C3%A3o,de%20seguran%C3%A7a%20em%20nossa%20aplica%C3%A7%C3%A3o.

*	Dúvidas sobre o @Bean
	https://cursos.alura.com.br/forum/topico-duvida-sobre-o-bean-113349#:~:text=O%20%40Bean%20serve%20para%20exportar,depend%C3%AAncia%20dela%20em%20outra%20classes.

*	O que/quem é httpSecurity?
	https://cursos.alura.com.br/forum/topico-o-que-quem-e-httpsecurity-252918

*	Para que usar throws?
	https://cursos.alura.com.br/forum/topico-para-que-usar-throws-188866#:~:text=J%C3%A1%20a%20instru%C3%A7%C3%A3o%20throws%20(com,n%C3%A3o%20tem%20try%2Fcatch).

*	Qual é o motivo para desabilitar o csrf no aplicativo web spring boot?
	https://stackoverflow.com/questions/52363487/what-is-the-reason-to-disable-csrf-in-spring-boot-web-application
	
*	Compreendendo requestMatchers() no spring-security
	https://stackoverflow.com/questions/52029258/understanding-requestmatchers-on-spring-security
	
*	Objetivo de usar permitAll() na anotação PreAuthorize no Spring Security
	https://stackoverflow.com/questions/31059528/purpose-of-using-permitall-in-preauthorize-annotation-in-spring-security
	
*	Começando com Spring Security
	https://cwi.com.br/blog/comecando-com-spring-security/#:~:text=Para%20armazenar%20senhas%20em%20um,comuns%20como%20CSRF%20ou%20XSS.
	
*	Armazenhamento de senha
	https://docs.spring.io/spring-security/reference/features/authentication/password-storage.html#authentication-password-storage
	
*	Classe BCryptPasswordEncoder
	https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/crypto/bcrypt/BCryptPasswordEncoder.html
	
*	Como funciona a Criptografia Hash em Java
	https://www.devmedia.com.br/como-funciona-a-criptografia-hash-em-java/31139#:~:text=O%20Hash%20%C3%A9%20uma%20sequ%C3%AAncia,alcan%C3%A7amos%20a%20garantia%20da%20integridade.
	
*/