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
	
/*	Agora vamos iniciar a implementação  do método para buscar os Alunos pelo ID. 
 
 * 	Primeiramente vamos utilizar a classe "Response Entity", uma extenção do "Http Entity" que 
 	adiciona o Status Http ao código, e vamos indicar que esse status será baseado na entidade 
 	"Aluno".		*/	
	private ResponseEntity<Aluno>
	
//	Vamos nomear o método com um nome que o defina bem: getAlunoById. 
	 							  getAlunoById(
/*	Agora vamos adicionar ao parâmetro uma representação do "id" do tipo "Long". Mas antes dele
  	será adicionado a anotação "@PathVariable" neste método e URL, que tem a função de manipular 
  	variáveis do objeto. 		*/
	 									  @PathVariable Long id) {
		
/*		Vamos criar um objeto da classe "Aluno" que acessará o repositório com o método 
		"find By Id" e buscará o "id" solicitado via URL.		*/	
		Aluno aluno = alunoRepository.findById(id)
				
/*				Aqui vamos implementar uma condição para caso o "id" não sea encontrado no
				banco de dados		*/
				.orElseThrow(
						
/*						Vamos utilizar a Expressão Lambda para chamar o método 
 						"Resource Not Found Exception", que lançará uma mensagem caso a 
 						condição não seja atendida.		*/
						() -> new ResourceNotFoundException("Aluno não encontrado."));
		
/*		Se a exception não for executada, isso quer dizer que ele conseguiu achar algum "id" no
		repositório, então ele deverá dar algum retorno, já que este método mantém a ausência 
		do "void".
		
 *		Como retorno, vamos indicar o método "ok()", com o objeto "aluno" no parâmetro, ao 
 		"Response Entity".		*/	
		return ResponseEntity.ok(aluno);
		
/*		Agora vamos executar a nossa aplicação e testar este método. Vamos fazer o mesmo 
 		processo que no método anterior, abrir o Postman, adicionar a nova URL que representa
 		este método, e substituir o "/{id}", por um número de id existente no banco de dados.
 		
 *		Se desejar, poderá adicionar um "id" não existente, para observar a "Exception" em 
 		funcionamento no console do Eclipse e no Postman.
 		
 *		Quando solicitamos a requisição no Postman, com o método "GET" e com a URL 
 		"http://localhost:8080/api/alunos/1", é retornado uma pequena estrutura com em formato 
 		JSON que retorna os dados do banco de dados, similar ao código abaixo:
 		
			{
			    "id": 1,
			    "email": "lucash.@hotmail.com",
			    "nome": "lucas",
			    "sobrenome": "amaro"
			}

 *		Quando utilizamos um id inexistente, a mensagem apresentada no console do Eclipse é 
 		"Resolved [com.euripedes.alunos.exception.ResourceNotFoundException: Aluno não encontrado.]", 
		enquanto no Postman tivemos o status Http "404 Not Found". Ambos indicam que o objeto não 
		existe no MySQL.
 		
 *		Estamos finalizando a implementação do método READ, e com esses métodos podemos buscar 
 		dois registros: uma lista com todos os registros e outra que busca um registro por id.
 		o próximo método CRUD que será implementado é o CREATE, onde vamos conseguir cadastrar
 		um aluno utilizando URL e o postman, também veremos um antes e depois da entidade no
 		MySQL.
 */
	}
	
/*	Na aula de hoje, vamos implementar o próximo método CRUD, o Create. Nosso objetivo ao final
 	da implementação deste método é conseguir enviar uma requisição por meio da URL no Postman
 	a fim de conseguir criar no banco de dados um objeto.
 	
 * 	Para começar, vamos definir o endpoint que identificará este método. Utilizaremos a anotação 
 	"@PostMapping" para indicar que este método utiliza um mapeamento de requisição do tipo 
 	"POST", o mesmo método que será aplicado no Postman.
 	
 *	Na anotação vamos identificar este método como "/cadastrar", esse URL complementa o endereço
 	adicioando na anotação "@RequestMapping".		*/
	@PostMapping("/cadastrar")
	
/*	Agora vamos implementar uma classe do tipo "Aluno", com o nome baseado no método que será 
 	implementado. 	 */
	public Aluno createAluno(
			/*	Será adicionado, como parâmetro, uma anotação "@RequestBody" que indicará que os 
			próximos parâmetros (Aluno aluno) são adicionados ao corpo da solicitação.		*/
			@RequestBody Aluno aluno) {
		
	/*	Agora que definimos o nome do método e os seus parâmetros, incluindo o parâmetros 
	 	baseados no objeto "Aluno", vamos adicionar um retorno, já que nosso método não é do 
	 	tipo "void".
	 	
	 *	No retorno vamos aplicar o método "save()" no repositório. O método "save()" tem como 
	 	parâmetro a representação do objeto "Aluno", que carrega as informações inseridas via
	 	Http.
	 	
	 *	Para resumir, inserimos os dados pelo JSON no Postman e esses dados passarão pelo 
	 	end-point da API e armazenados no parâmetro do método "createAluno()". O dados armazenado 
	 	no parâmetro serão direcionado para o parâmetro do método "save()", que será inserido 
	 	no repositório do banco de dados.		*/	
		return alunoRepository.save(aluno);
		
	/*	Agora, vamos testar a nossa aplicação para saber se o método está sendo funcional. Você
	 	precisará abrir o Postman para testar o end-point, e o MySQL para observar as mudanças 
	 	na Tabela.
	 	
	 *	No Postman você precisará seguir alguns passos antes de enviar uma requisição:
	 	*	Mude o método para "POST";
	 	*	Adiciona o endereço "http://localhost:8080/api/alunos/cadastrar";
	 	*	Nas opções abaixo, clique em "Body";
	 	*	Entre as opções do "Body", escolha "raw" e mude "Text" para "JSON";

	*	Agora você deverá adicionar as informações no formatro JSON:
			{
	    			"email": "insira o e-mail",
	    			"sobrenome": "insira o sobrenome",
	    			"nome": "insira o nome"
			}
	
	*	OBS: Não é necessário adicionar o ID, ele será criado de forma automática.
	
	*	Feito estes passos, basta clicar em "Send" e esperar que o Status seja "200 OK".
	
	*	Agora vamos no Banco de Dados e abrir a tabela "alunos". Se você observou a tabela antes 
		de enviar a requisição, perceba que não há registros com as informações acima; mas após 
		clicar em "Send" no Postman, a nossa tabela é alterada e uma nova linha é criada no MySQL.
		
	*	Se desejar, poderá utilizar os outros métodos "GET" implementados anteriormente para observar
		os dados no Postman.
		
	*	Sendo assim, terminamos a implementação do método CREATE, onde conseguimos inserir um novo 
		registro no banco de dados por meio de URL no Postman, utilizando o método Http POST, e 
		observamos a tabela sendo atualizada no Banco de Dados.
		
	*	Na próxima aula, implementaremos o outro método CRUD, o UPDATE.
	
	*/
	}
	
	
/*	Nesta aula implementaremos o penúltimo método CRUD: o UPDATE.
 
 *	Para começar, vamos utilizar a anotação "@putMapping" para indicar que a URL aplicada pertence ao
 	método Http "PUT". O end-point utilizado para identificar o método é o "/atualizar", conforme 
 	observamos abaixo:		*/
	@PutMapping("/atualizar/{id}")
	
/*	Vamos agora criar um método responsável por atualizar o objeto. Este método iniciará igual ao 
 	método  GET, onde vamos utilizar um "Response Entity" para adionar um Status Http ao método	
 	(Explicação na linha 186).		*/	
	public ResponseEntity<Aluno> updateAluno(
		
		//	Como parâmetro vamos adicionar:
			
			@PathVariable Long id, // Para manipular as variáveis do objeto (Explicação na Linha 193). 
			@RequestBody Aluno alunoDts // Para adicionar os parâmetros ao corpo da solicitação(Explicação na Linha 265)
			){
		
	/*	Agora vamos criar um objeto do tipo "Aluno" que receberá o mesmo ID do parâmetro do método 
 		"findById()", aplicado no "alunoRepository".  		*/		
		Aluno aluno = alunoRepository.findById(id)
				
	/*			Caso o ID não seja encontrado, será criado uma Exception por meio do método 
				"orElseThrow()".	*/				
				.orElseThrow(
						
	/*					Vamos utilizar o método "Resource Not Found Exception" para indicar uma 
						mensagem caso o ID não seja encontrado.		*/
						() -> new ResourceNotFoundException("Aluno não enontrado."));
		
	/*	Para caso o ID seja encontrado, a aplicação utilizará o método "Setter", implementado na 
		"Model", para pegar os dados atuais pertencentes ao ID passado do Repositório para o 
		objeto.
		
	 *	Como parâmetro do método "set", será aplicado os dados passados no parâmetro enviado
 		no corpo da requisição do método "updateAluno()", identificado com a anotação "@RequestBody",
 		com o nome "alunoDts" (aluno detalhes).
 		
	 *	Esta é uma forma de pegar os Dados atuais no repositório e substituir por novos Dados do
  		inseridos via Web pelo método Http PUT.		*/
		
	//	Pegando o Nome atual
		aluno.setNome(
		//		Inserindo o novo Nome
				alunoDts.getNome());
		
	//	Pegando o Sobrenome atual
		aluno.setSobrenome(
		//		Inserindo o novo Sobrenome
				alunoDts.getSobrenome());
		
	//	Pegando o Email atual
		aluno.setEmail(
		//		Inserindo o novo Email
				alunoDts.getEmail());
		
	/*	Agora que inserimos os novos dados, vamos criar um objeto que armazenará as novas mudanças.
 		Este objeto, com o nome "updateAluno" contém os mesmos dados inseridos no Repositório pelo 
 		método "save()". 
 		
	*	Perceba que o método "save()" tem como parâmentro o mesmo objeto que recebeu o ID no início 
 		deste método: aluno. 	*/		
		Aluno updateAluno = alunoRepository.save(aluno);
		
	//	Como retorno, indicaremos o método "ok()" ao "Response Entity".
		return ResponseEntity.ok(updateAluno);
		
	/*	Agora vamos testar este método com o MySQL e o Postman.
 		
	 *	Com o MySQL aberto, observe os dados que existem em sua tabela.
 
	 *	No Postman você precisará seguir alguns passos antes de enviar uma requisição:
		 *	Mude o método para "PUT";
		 *	Adiciona o endereço "http://localhost:8080/api/alunos/atualizar";
		 *	Nas opções abaixo, clique em "Body";
		 *	Entre as opções do "Body", escolha "raw" e mude "Text" para "JSON";
	
	*	Agora você deverá adicionar as novas informações no formatro JSON:
			{
		   			"email": "insira o novo e-mail",
		   			"sobrenome": "insira o novo sobrenome",
		   			"nome": "insira o novo nome"
			}
			
	*	Feito estes passos, basta clicar em "Send" e esperar que o Status seja "200 OK".
		Depois, vá ao MySQL e atualize a consulta da tabela e confirme se os dados foram 
		atualizados.
		
	*	Dessa forma, conseguimos concluir a implementação de mais um método CRUD, e agora 
		nossa aplicação é capaz de fornecer métodos para atualizar os registros dos objetos
		no banco de dados.
		
	*	Na próxima aula, trabalharemos com a implementação do último método CRUD, o DELETE.
		Também realizaremos testes utilizando o Postman e o MySQL.
	*/
	}
	
/*	Nesta aula, implementaremos o último métoo CRUD: o DELETE.
 
 *	Para começar, vamos utilizar a anotação "@DeleteMapping" para indicar qual é o endpoint
  	que identificará o este método. Como parâmetro da anotação, vamos utilizar o nome 
  	"/deletar{id}".		*/	
	@DeleteMapping("/deletar{id}")
	
/*	Agora vamos implementar um "Response Entity" com uma implementação conhecida como 
 	"mapa de chaves e valores booleanos".
 	
 * 	Quando utilizamos a implementação "Response Entity" com "Map<String, Boolean>", estamos
	criando um mapa que associa chaves do Tipo "String" aos valores do tipo "Booleano".
	Esta implementação pode ser útil ao indicar sucesso ou falha de uma operação, onde uma 
	chave "deleted" é identificada com "Boolean.TRUE".		*/
	public ResponseEntity<Map<String, Boolean>> deleteAluno(@PathVariable Long id){
		
	/*	Vamos criar uma implementação com o objeto "Aluno", similar a implementação que 
	 	realizamos no método "getAlunoById()" e "updateAluno()".
	 	
	 * 	Vamos evitar explicar o que já explicamos, mas se surgir alguma dúvida, consulte
	 	a linha 198 em diante para relembrar alguns detalhes.	*/
		Aluno aluno = alunoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado."));
		
	/*	Vamos chamar o método "delete()" que será aplicado ao Repositório; este método 
 		recebe o objeto "aluno" que carrega um ID da requisição para dentro do Repositório,
 		a fim de deletar o objeto que tenha o ID solicitado.	*/
		alunoRepository.delete(aluno);
		
	/*	Agora vamos implementar um objeto de mapa, com o nome "response", que utilizará o
	 	"HashMap". O HashMap é uma estrutura de dados que armazena pares de chave-valor
	 	(String-Boolean).		*/
		Map<String, Boolean> response = new HashMap<>();
		
//		Vamos utilizar o método "put()" para adicionar um par chave-valor no "HashMap".
		response.put(
				
//				Indicando a chave, que é "Deletado" (uma String), e o valor que é Boolean.TRUE.
				"Deletado", Boolean.TRUE);
		
//		Como retorno, vamos indicar o método "ok()", com o objeto "response", ao "Response Entity".
		return ResponseEntity.ok(response);
		
	/*	Dessa forma, estamos finalizando o último método CRUD: o DELETE.		*/	
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

 *	Class ResponseEntity<T>
	https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html

 *	Spring @PathVariable Annotation
	https://www.baeldung.com/spring-pathvariable
	
 *	Angular + Spring Boot CRUD Full Stack App - 13 - Creating REST API to Save Employee Object
	https://www.youtube.com/watch?v=qH7_1W8MKfs&list=PLGRDMO4rOGcNzi3CpBWsCdQSzbjdWWy-f&index=16
	
 *	Angular + Spring Boot CRUD Full Stack App - 18 - Creating Update Employee REST API
	https://www.youtube.com/watch?v=XG3cgNvVx9M&list=PLGRDMO4rOGcNzi3CpBWsCdQSzbjdWWy-f&index=18
 */