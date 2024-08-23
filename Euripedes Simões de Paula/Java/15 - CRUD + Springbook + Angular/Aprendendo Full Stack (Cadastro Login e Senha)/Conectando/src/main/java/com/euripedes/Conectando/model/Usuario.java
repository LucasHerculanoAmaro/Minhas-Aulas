package com.euripedes.Conectando.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*	Na aula de hoje trabalharemos com a classe "Usuario", onde vamos definir
	os atributos da classe, o método construtor e os métodos "Getters & Setters".
*/

/* 	A anotação "@Enmtity" é utilizada para informar que esta classe também é uma entidade.

 * 	OBS: A JPA faz ligação entre a entidade e a tabela no banco de dados, que compartilham 
	do mesmo nome.		*/
@Entity

/* A anotação "@Table" é usada para especificar o nome da tabela a ser mapeada.
 
 * OBS: Não usar esta anotação faz o HIBERNATE o usar o nome da classe como nome da tabela. */
@Table(name = "usuario")
public class Usuario {

/*	A anotação "@Id" é usada para indicar qual é o atributo que está relacionado a chave 
	primária na tabelça do banco de dados.		*/
	@Id
	
/*	Esta anotação informa que o valor do identificador único da entidade será gerados 
 	pelo provedor de persistência.		*/	
	@GeneratedValue(
			
//			Aqui modificamos a estratégia de geração da chave primária
			strategy = 
			
/*			Entre as quatro estratégias, vamos usar a "IDENTITY".		

 *			OBS: Informamos ao provedor que os valores serão gerados a partir de uma 
 			sequência.		*/
			GenerationType.IDENTITY)
	
//	Atributos	
	private Long id;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "senha")
	private String senha;
	
	@Column(name = "email")
	private String email;

	//	Construtor
	public void usuario(Long id, String login, String senha){
		
//		atributo	x	parâmetro
		this.id 	= 	id;
		this.login	=	login;
		this.senha	=	senha;
	}
/*	A partir daqui, vamos criar os métodos "getters & setters" de forma automatica
	com o seguinte passo a passo:
	
		-> 	Clique com o botão direito do mouse nesta tela.
		-> 	Escolha a opção "Source" do menu.
		-> 	Clique em "Generate Getters and Setters...".
		-> 	Escolha todas as opções relacionadas aos atributos na opção 
			"Select getters and setters to create".
		->	Clique em "Generate".
	
 *	Agora veja abaixo o resultado do que acabamos de fazer.		*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
/*	CONCLUSÃO
 
 *	Na aula de hoje, observamos como podemos utilizar anotações para indicar a chave primária,
 	gerar chaves no servidor e referenciar a tabela no banco de dados.
 	
 	Além desse conteúdo, nós trabalhamos com Atributos, método Construtor e os métodos 
 	"Getters and Setters", já estudado em aulas anteriores.
 	
 	Na próxima aula, vamos trabalhar com o conteúdo presente no pacote
 
 */
	
}

/* 	REFERÊNCIAS
 
 *	JPA: Como usar a anotação @Entity
  	https://www.devmedia.com.br/jpa-como-usar-a-anotacao-entity/38410
  	
 *	Não usar @Table
  	https://cursos.alura.com.br/forum/topico-nao-usar-table-281979
  	
 * 	JPA: Como usar a anotação @Id
  	https://www.devmedia.com.br/jpa-como-usar-a-anotacao-id/38508
  	
 *	JPA: Como usar a anotação @GeneratedValue
  	https://www.devmedia.com.br/jpa-como-usar-a-anotacao-generatedvalue/38592
  	
 * */
 