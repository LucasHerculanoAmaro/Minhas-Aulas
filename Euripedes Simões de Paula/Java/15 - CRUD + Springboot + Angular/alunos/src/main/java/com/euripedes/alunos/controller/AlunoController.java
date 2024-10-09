package com.euripedes.alunos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.euripedes.alunos.exception.ResourceNotFoundException;
import com.euripedes.alunos.model.Aluno;
import com.euripedes.alunos.repository.AlunoRepository;

/*	Nesta aula vamos: 
 	* 	Implementar o Método Create, Read, Update e Delete (CRUD).
 	*	Conhecer sobre o conceito e implementação de APIs.
 	*	Aprender sobre novas anotações de mapeamento.

 *	Vamos começar conhecendo as primeiras anotações de configuração para a classe:		*/
 

@RestController  /* Esta anotação foi incluída na versão 4 do Java, e surgiu para 
	melhorar os serviços RESTful com Spring.
 		
*	É importante saber que há diferença entre Aplicações Web e API REST, sendo as 
	Aplicações Web recebendo respostas por meio de visualização (HTML, JAVASCRIPT e 
	CSS), enquanto a API REST retornam dados no tipo JSON e XML.
 		
*	Então, esta anotação recupera o objeto e grava dados em respostas via HTTP
	por meio de JSON e XML.	*/

@CrossOrigin(origins = "*", allowedHeaders = "*") /* O que vai nos ajudar a entender 
	essa anotação é conhecer o que é CORS. 
 		
 *	CORS (Cross-Origin Resource Sharing) é um recurso de segurança incluído nos 
 	serviços Web que impede que outros domínios tenham acesso aos dados, e também 
 	impede que terceiros tenham acesso aos dados.
 		
 *	Essa anotação indica que um ou mais métodos dentro dessa classe possam ser 
 	chamados pelo JavaScript. Permite o acesso de cabeçalhos, origens e métodos HTTP 
 	incluídos no Request Mapping.		*/	
 	
 @RequestMapping("/api/alunos") /* Utilizamos esta anotação na classe "controller" 
 	de forma genérica definindo seu endereço, para que outros métodos herdem a sua 
	definição. 		*/
public class AlunoController {

/* 	Realizando uma injeção de dependência com @Autowired.
 
 * 	Quando você usa @Autowired, o Spring cuida de localizar um bean (um objeto 
 	gerenciado pelo container Spring) do tipo correto e o injeta na classe que precisa 
 	dele.		*/
	@Autowired
	private AlunoRepository alunoRepository;
	
	
/* 	As anotações para o método GET, POST, PUT e DELETE a seguir complementam a anotação
 	"@RequestMapping". 
 	
 *	Por exemplo: Enquanto o "RequestMapping" tem o "/api/alunos", o "GetMapping" tem 
 	definido "/{id}". Assim é possível acessar e buscar um objeto pelo id unindo os 
 	dois endpoints: /api/alunos/{id}
 	
 *	Abaixo vamos implementar o CRUD, onde vamos assimilar os métodos HTTP ao CRUD:
 	*	CREATE 	-> PostMapping
 	*	READ 	-> GetMapping
 	*	UPDATE	-> PutMapping
 	*	DELETE	-> DeleteMapping
 	
 */
	@GetMapping("/todos")
	public List<Aluno> getAllAlunos() {
		return alunoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Aluno> getAlunoById(@PathVariable Long id) {
		Aluno aluno = alunoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado."));
		return ResponseEntity.ok(aluno);
	}
	
	@PostMapping("/cadastrar")
	public Aluno createAluno(@RequestBody Aluno aluno) {
		return alunoRepository.save(aluno);
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Aluno> updateAluno(@PathVariable Long id, @RequestBody Aluno alunoDts){
		Aluno aluno = alunoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Aluno não enontrado."));
		
		aluno.setNome(alunoDts.getNome());
		aluno.setSobrenome(alunoDts.getSobrenome());
		aluno.setEmail(alunoDts.getEmail());
		
		Aluno updateAluno = alunoRepository.save(aluno);
		
		return ResponseEntity.ok(updateAluno);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteAluno(@PathVariable Long id){
		Aluno aluno = alunoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado."));
		
		alunoRepository.delete(aluno);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deletado", Boolean.TRUE);
		
		return ResponseEntity.ok(response);
	}
	
}

/*	REFERÊNCIAS
 
 *	Diferença entre @RestController e @Controller Annotation no Spring MVC e REST
	https://medium.com/@gcbrandao/diferen%C3%A7a-entre-restcontroller-e-controller-annotation-no-spring-mvc-e-rest-8533998a93eb

 *	Enabling Cross Origin Requests for a RESTful Web Service
	https://spring.io/guides/gs/rest-service-cors

 *	What is @CrossOrigin annotation in Spring boot ? Its purpose ?
	https://medium.com/@dev_RV/what-is-crossorigin-annotation-in-spring-boot-its-purpose-66125e1fc21a

 *	Diferença entre RequestMapping e GetMapping
	https://cursos.alura.com.br/forum/topico-diferenca-entre-requestmapping-e-getmapping-310841

 *	Autowired e a injeção de dependência do Spring
 	https://medium.com/@leonardogiuliani/autowired-e-a-inje%C3%A7%C3%A3o-de-depend%C3%AAncia-do-spring-d8864cc9af50

 */