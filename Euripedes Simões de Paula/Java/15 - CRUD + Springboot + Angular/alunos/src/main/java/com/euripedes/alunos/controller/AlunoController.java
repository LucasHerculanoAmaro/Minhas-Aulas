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
 	*	CREATE 	-> PostMapping;
 	*	READ 	-> GetMapping;
 	*	UPDATE	-> PutMapping;
 	*	DELETE	-> DeleteMapping;
 
 *	Primeiramente, vamos implementar o método READ, onde vamos utilizar o método GET.
 	Veja que, antes de criar o método GET, utilizamos a anotação "@GetMapping" com
 	o endereço do endpoint utilizado para acessar este método via HTTP.
 	
 *	O objetivo deste método é observar os registros que existem dentro do banco de dados.
 	Mas vamos entender melhor como implementar este método, então veja abaixo como será a 
 	sua implemenção:		*/
	@GetMapping("/todos")
	
/*	Precisamos buscar os dados no banco de dados, e para isso podemos criar um método que
 	retorna uma lista. Então, vamos importar uma "List<>" que será direcionada a buscar
 	algum Objeto da entidade "Aluno".		*/
	public List<Aluno> getAllAlunos() {
		
/*		Este método tem a seguinte função: acessar a interface "alunoRepository" 
 		utilizando o método "findAll()".	*/
		return alunoRepository.findAll();
		
/*		Agora vamos testar este método para saber se ele está funcionando corretamente.
 		Para isso, vamos precisar abrir o MySQL para inserir alguns dados, e o Postman
 		para testar o endpoint que definimos para este método.
 		
 *		Também será necessário executarmos a nossa aplicação para que o banco de dados, 
 		que definimos no "application.properties", seja criado e que as requisições HTTP 
 		possam ser respondidas.
 		
 * 		Para executar, vá no pacote "com.euripedes.alunos" e abra a classe 
 		"AlunosApplication". Nesta classe, você pode clicar com o botão e escolher a 
 		opção "Run As" e depois clique em "1 Java Application".
 		
 *		Ao executar a Aplicação, uma série de códigos aparecerá no Console, quando 
 		aparecer uma mensagem similar a "Started AlunosApplication in 15.048 seconds 
 		(process running for 16.499)", você pode fazer uso de sua aplicação.

 *		Agora vá até o banco de dados e procure pelo banco com o nome 
 		"DB_SISTEMA_GERENCIAMENTO_ALUNOS". Neste banco você verá uma tabela com o nome 
 		"alunos". O nome deste banco foi configurado no "application.properties", e a 
 		tabela foi configurada na entidade "Aluno" no pacote model.

 *		Atenção: Lembre-se que configuramos no "application.properties" para o banco 
 		criar esse banco e a tabela de maneira automática caso não tenha uma tabela já 
 		criada. Se você não configurou para isso, talvez precisará criar de maneira manual, 
 		ou adicionar as devidas configurações no endereço do banco.

 * 		Procure a tabela com o script "SELECT * FROM db_sistema_gerenciamento_alunos.alunos;" 
 		e você verá que ela está vazia. Vamos inserir alguns dados para buscá-los 
		posteriormente no Postman. Execute o segunte script:

		*	INSERT INTO `db_sistema_gerenciamento_alunos`.`alunos` (`nome`, `sobrenome`, `email`) 
			VALUES ([nome], [sobrenome], [e-mail]);

 *		OBS: Em "VALUES" substitua os elementos "[nome]", "[sobrenome]" e "[e-mail]" pelo 
 		nome que deseja.

 *		Após inserir os dados no banco de dados no MySQL, vamos consultar esses dados 
 		utilizando o Postman. Nesta ferramenta encontraremos um campo para inserir uma URL, 
 		um campo para escolhermos o Método HTTP, um console, etc.

 *		Agora, para testar o método, siga os seguintes passos:
		*	Escolha o método GET;
	 	*	Insira o endpoint "http://localhost:8080/api/alunos/todos";
	 	*	Clique em "Send".

 * 		No console encontramos as seguintes informações:
	 	*	Status: informa qual é a resposta HTTP para requisição;
	 	*	E a resposta que virá no formato JSON.

 * 		A resposta para a requisição deve ser no formato JSON, parecida assim:

			[
			    {
		        	"id": 1,
		        	"email": "lucash.@hotmail.com",
		        	"nome": "lucas",
		        	"sobrenome": "amaro"
			    }
			]

*		OBS: Quando definimos a resposta para o formato XML, ela será apresentada da seguinte 
		forma:

			[{"id":1,"email":"lucash.@hotmail.com","nome":"lucas","sobrenome":"amaro"}]

 *		Agora que executamos nosso projeto, inserimos os dados no banco de dados, e conseguimos 
 		testar as requisições via Postman, podemos concluir que, se tratando do método GET para 
 		retornar todos os registros no banco de dados, nossa aplicação mostrou-se ser funcional.

 * 		Na próxima aula vamos continuar trabalhando no método GET, mas dessa vez vamos trabalhar
 		implementar a busca por ID.
 */
	}
	
/*	Nesta aula vamos trabalhar na implementação do método GET. Este método têm algumas 
 	similaridades com o método anterior, mas este método terá uma particularidade que o método 
 	GET anterior não tem: ele fará buscas pelo ID.
 	
 * 	Nosso objetivo é retornar um objeto que contém o "ID" específico, então não será necessário 
 	criar uma lista e nem importar uma "List". Dessa vez vaos utiilizar o "ResponseEntity", que 
 	já utilizamos em aulas anteriores.
 	
 *	O primeiro passo é definir o endpoint que será utilizado no Postman futuramente. Para isso, 
 	vamos definir a anotação "@GetMapping" com o endpoint "/{id}", assim vamos conseguir difinir
 	o objeto que será retornado pelo ID. Veha abaixo:		 */
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
 	
 *	Spring @GetMapping, @PostMapping, @PutMapping, @DeleteMapping and @PatchMapping
 	https://www.javaguides.net/2018/11/spring-getmapping-postmapping-putmapping-deletemapping-patchmapping.html

 */