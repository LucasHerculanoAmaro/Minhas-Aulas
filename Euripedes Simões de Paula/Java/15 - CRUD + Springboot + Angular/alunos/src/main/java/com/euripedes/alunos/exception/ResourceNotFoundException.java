package com.euripedes.alunos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*	Na aula de hoje, vamos configurar a classe "ResourceNotFoundException".
 	esta classe indica que os recursos de uma solicitação não foi encontrado,
 	então vamos criar algumas deifinições para nos ajudar a lidar com essa 
 	classe em nossa aplicação.
 	
 *	Primeiramente precisamos entender que o Spring fornece respostas HTTP. 
 	Por exemplo, temos a resposta 200 do HTTP, que significa "OK".
 	
 *	Se quisermos definir o método de um controlador, podemos utilizar a 
 	anotação "@ResponseStatus" e ao valor da resposta HTTP podemos difinir 
 	um dos métodos (que em nossa classe é o "Not Found").	 
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException 

//	Estamos herdando algumas proporiedades da classe "RuntimeException"
	extends RuntimeException{

/*	Agora precisamos entender o conceito de serialização.
 	É um processo de converter um objeto em um fluxo de bytes, para que 
 	possam ser armazenados ou transmitidos.
 	
 *	Desserialização é o processo inverso, onde pegamos um fluxo de dados 
 	e transformamos em um objeto.
 	
 *	Mas qual é o papel do "serialVersionUID"? É um identificador de 
 	versão da classe serializada, usado para verificar se a versão da 
 	classe serializada é compatível com a versão da classe, que está
 	sendo desserializada.
 	
 *	É uma garantia de que as classes sejam compatíveis durante uma mudança.
 	Caso uma classe seja impacompatível, durante a Serialização e 
 	Desserialização, o erro "InvalidClassException" será lançado pelo Java.
 	
 *	Veja abaixo como será implementado o "serialVersionUID":	*/
	private static final long serialVersionUID = 1L;
	
/*	Abaixo definimos o construtro da classe public ResourceNotFoundException.
 	Dessa forma, podemos fornecer uma mensagem personalizada quando a 
 	exception for utilizada.	*/
	public ResourceNotFoundException(String message) {
		super(message);
	}
	
/*	Criadas as implementações para exception nesta classe, vamos começar a 
 	trabalhar com a implementação da camada de controle. Siga para o pacote
 	"controller", e na classe "AlunoController" implentaremos as configurações 
 	CRUD. 
 */
	
}

/*	REFERÊNCIAS
 
 *	Class ResourceNotFoundException
 	https://docs.spring.io/spring-data/rest/docs/current/api/org/springframework/data/rest/webmvc/ResourceNotFoundException.html#%3Cinit%3E()
 
 *	Using Spring @ResponseStatus to Set HTTP Status Code
 	https://www.baeldung.com/spring-response-status
 
 *	Para que serve o serialVersionUID = 1L?
	https://cursos.alura.com.br/forum/topico-para-que-serve-o-serialversionuid-1l-146107
 
 *	Entendendo SerialVersionUID em Java
 	https://www.devmedia.com.br/entendendo-serialversionuid-em-java/29017
 	
 *	Angular 10 + Spring Boot CRUD Full Stack App - 5 - Creating List Employee REST API
 	Autor: Java Guides.
 	https://www.youtube.com/watch?v=RA37EdWywjg&list=PLGRDMO4rOGcNzi3CpBWsCdQSzbjdWWy-f&index=5
 
 */
