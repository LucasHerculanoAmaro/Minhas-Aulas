package model;

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
@Table(name = "users")
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
	private String login;
	private String senha;
	
//	Construtor
	public void usuario(Long id, String login, String senha){
		
//		atributo	x	parâmetro
		this.id 	= 	id;
		this.login	=	login;
		this.senha	=	senha;
	}
	
/*	A partir daqui, vamos criar os métodos "getters & setters" de forma automatica
	com o seguinte passo a passo:
	
		-> 
 * */	
	
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
 