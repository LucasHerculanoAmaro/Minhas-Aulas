package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.UsuarioSevice;


/*	RestController: Simplifica a criação de serviços web RESTful (retorna dados em forma de JSON ou XML). 

*	OBS: RestController retorna os objetos e os dados do objeto são gravados diretamente na resposta HTTP
	como JSON e XML.		*/
@RestController

/*	RequestiMapping: usada para mapear solicitações HTTP para métodos de manipulação dentro do 
 	controlador Spring		
 	
 *	OBS: Especificamos a URL e o método HTTP que ser´controlado pelo controlador.	 	*/
@RequestMapping("/api/auth")

public class AuthController {

	@Autowired
	private UsuarioSevice usuarioService;
	
/*	PostMapping: Manipula solicitações HTTP POST em serviços web RESTful.			*/	
	//@PostMapping("/cadastrar")
	
}

/*	REFERÊNCIAS
 
 *	Spring Controler vs RestController
 	https://www.baeldung.com/spring-controller-vs-restcontroller
 	
 *	Guia das principais antações do Spring Framework
 	https://www.linkedin.com/pulse/guia-das-principais-anota%C3%A7%C3%B5es-do-spring-framework-flavio-vieira-weltf/
 	
 *	Spring – Anotação @PostMapping e @GetMapping
 	https://www.geeksforgeeks.org/spring-postmapping-and-getmapping-annotation/
 
 * */
