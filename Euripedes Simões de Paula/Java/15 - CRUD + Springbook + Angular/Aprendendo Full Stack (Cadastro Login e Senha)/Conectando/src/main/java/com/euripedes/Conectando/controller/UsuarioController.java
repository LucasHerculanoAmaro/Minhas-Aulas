package com.euripedes.Conectando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.euripedes.Conectando.model.Usuario;
import com.euripedes.Conectando.service.UsuarioSevice;

/*	INTRODUÇÃO
 
 *	Na aula de hoje aprenderemos a implementar um controlador focado em gerenciar o registro dos usuários.
 
 */


/*	RestController: Simplifica a criação de serviços web RESTful (retorna dados em forma de JSON ou XML). 

*	OBS: RestController retorna os objetos e os dados do objeto são gravados diretamente na resposta HTTP
	como JSON e XML.		*/
@RestController

/*	RequestiMapping: usada para mapear solicitações HTTP para métodos de manipulação dentro do 
 	controlador Spring		
 	
 *	OBS: Especificamos a URL e o método HTTP que ser controlado pelo controlador.		*/
@RequestMapping("/usuarios")

//Permite que qualquer requisição solicitada seja atendida.
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class UsuarioController {

	@Autowired
	private UsuarioSevice usuarioService;
	
/*	PostMapping: Manipula solicitações HTTP POST em serviços web RESTful.
  	Mapeia a URL específica e permite o processamento de dados enviados pelo método POST.		*/	
	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastrar(
			
			@RequestBody
			
//			Passando o objeto da classe 'Usuario' como parâmetro no método 'cadastrar()'.
			Usuario usuario
			) {
		
/*		Atribuindo a classe 'UsuarioService' o método 'cadastrar()', passando como parêmetro o objeto da
		classe 'usuario'.		*/
		usuarioService.cadastrar(usuario);
		
		return ResponseEntity.ok("Usuario cadastrado com Sucesso!");
	}
		
	
	
/*	CONCLUSÃO
 
 * 	Nesta aula aprendemos a criar uma classe controladora, onde utilizará o método HTTP POST para manipular 
 	dados. Criamos um método 'cadastrar()' e aplicamos a este método o objeto da classe 'Usuario' como 
 	parâmetro, e atribuímos ao retorno o objeto da classe 'UsuarioService', que implementará o método 
 	'cadastrar()' o e objeto 'usuario' como parâmetro.		
 	
 *	Agora que trabalhamos com os pacotes (model, repository, security, service e controller) de nossa 
 	aplicação, precisamos realizar alguns testes para saber se nossa aplicação está funcional. Isso envolve 
 	testar os métodos HTTP (GET, POST, PUT, DELETE). 
 	
 
 */
	
}

/*	REFERÊNCIAS
 
 *	Spring Controler vs RestController
 	https://www.baeldung.com/spring-controller-vs-restcontroller
 	
 *	Guia das principais antações do Spring Framework
 	https://www.linkedin.com/pulse/guia-das-principais-anota%C3%A7%C3%B5es-do-spring-framework-flavio-vieira-weltf/
 	
 *	Spring – Anotação @PostMapping e @GetMapping
 	https://www.geeksforgeeks.org/spring-postmapping-and-getmapping-annotation/
 
 */
