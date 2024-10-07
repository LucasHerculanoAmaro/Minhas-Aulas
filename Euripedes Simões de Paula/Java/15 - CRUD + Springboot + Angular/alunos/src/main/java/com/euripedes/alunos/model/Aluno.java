package com.euripedes.alunos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*	INTRDUÇÃO À MODEL
 
 *	Enfim, vamos começar a tabalhar com a escrita de nosso código. 
	Esta é uma classe que já conhecemos bem, já que aprendemos a implementar 
	ela mais ou menos em Abril ou Maio, e também já criamos alguns projetos
	utilizando classes parecidas com esta.
	
 *	Esta é uma classe padrão, então pode ser que a estrutura dela não mude,
 	apenas alguns atributos e métodos sofrem uma leve alteração, mas podem
 	desempenhar a mesma função.
 	
 *	Mas aqui temos novidades: vamos ter uma pequena experiência ao utilizar 
 	as "anotações" em nosso código. Mas o que são anotações?
 	
 *	As anotações é um recurso usado para indicar marcações ao compilador.
	Essas marcações indicam que os metadados são dados que fazem refeência
	a outros dados.
	
 *	Isso significa que, ao criar um atributo, precisamos de uma anotação 
 	para referenciar o elemento que irá no atributo. Você entenderá melhor
 	logo no inicio da classe, quando utilizaremos a anotação "@Entity" e 
 	"@Table":
 	
 		"@Entity": indica ao compilador que esta classe faz referência a 
 		uma entidade, que está (ou estará) presente no banco de dados.
 		
 		"@Table": indica que esta classe faz referência a tabela "Alunos",
 		presente (ou futuramente inserida) no banco de dados.
 		
 *	Siga a dica: entenda o andamento, a lógica, a implementação do código.
	Dessa forma, você não vai ficar ficar com dúvidas sobre a anotação
	que você precisa usar, e nem vai precisar decorar uma imensa lista de
	anotações. E evite adicionar anotações que não são importantes, porque
	isso não faz bem a "saúde" da aplicação.
 	
 */

@Entity
@Table(name = "Alunos")
public class Aluno {

//	Esta anotação indica que este atributo é o identificador da entidade
	@Id
	
//	Indicamos que os valores serão gerados de forma automática
	@GeneratedValue(
			
//			Indica que os valores serão gerados de forma automática pelo banco de dados
			strategy = GenerationType.IDENTITY
			)
	private long id;
	
/*	"@Column" faz referência a coluna da tabela "Alunos". Dentro desta 
 	anotação adicionamos o nome da coluna.	
 	
 *	Siga a dica: não crie atributos e nem nomeie tabelas com o mesmo nome.
 	Em algumas Aplicações, a solução sugerida pela IDE é criar um novo
 	Atributo com o tipo diferente, mas o seu código pode não funcionar 
 	corretamente, então fique esperto para não fazer uma implementação 
 	desnecessária.
 	
 *	Veja abaixo como os atributos serão implementadas junto com as anotações,
 	e perceba que cada atributo tem uma anotação padrão, mas ainda são
 	diferentes.
 	
 */
	
	@Column(name = "nome")
	private String Nome;
	
	@Column(name = "sobrenome")
	private String Sobrenome;
	
	@Column(name = "email")
	private String email;
	
/*	Agora vamos trabalhar com os construtores.
	Clique com o botão direito do mouse, e siga para "source", depois 
	escolha a opção "Generate constructor using fields...".
	
 *	Primeiramente vamos criar um construtor sem os campos, depois vamos 
 	seguir os mesmos passos e criar um construtor utilizando os atributos
 	(com excessão do id, que será criado automáticamente).
 	
 *	Veja abaixo como a implementação deve ficar:
 	
 */
	
	public Aluno() {
		
	}

	public Aluno(String nome, String sobrenome, String email) {
		super();
		Nome = nome;
		Sobrenome = sobrenome;
		this.email = email;
	}

/*	Por que criar um construtor sem parâmetros?
 	Este construtor, sem parâmetros e referências para oos atributos é
 	conhecido como "construtor padrão" é normalmente utilizado quando
 	trabalhamos com "JPA/Hibernate" e com "SpringBoot", que é o nosso 
 	caso.
 	
 *	O JPA instância objetos a partir de registros do banco de dados, e 
 	ele precisa criar instâncias da entidade sem fornecer argumentos.
 	Por isso que é necessário criar um contrutor padrão (sem parâmetro).
 
 *	Por fim, vamos criar os métodos "Getters and Setters".
 	Clique com o botão direito do mouse, e siga para "source", depois 
	escolha a opção "Generate Getters and Setters". Selecione os 
	atributos e clique em "Generate".
	
 *	Já tivemos aulas sobre estes métodos, então evitaremos falar sobre 
 	conteúdos que já estudamos.
	
 */
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getSobrenome() {
		return Sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		Sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
/*	Finalizamos a nossa aula nesta classe. 
 	Agora vamos implementar criar a interface "AlunosRepository", no
 	pacote "repository", para criar algumas definições para o 
 	repositório da entidade no banco de dados.
 	
 */
	
}

/*	REFERÊNCIAS
 
 *	Entendendo Anotações em Java
 	https://www.devmedia.com.br/entendendo-anotacoes-em-java/26772 
 
 *	Angular 10 + Spring Boot CRUD Full Stack App - 4 - Creating JPA Entity + Repository
 	Autor: Java Guides
 	https://www.youtube.com/watch?v=PKt3Yr8mi5g&list=PLGRDMO4rOGcNzi3CpBWsCdQSzbjdWWy-f&index=4
 
 */ 
