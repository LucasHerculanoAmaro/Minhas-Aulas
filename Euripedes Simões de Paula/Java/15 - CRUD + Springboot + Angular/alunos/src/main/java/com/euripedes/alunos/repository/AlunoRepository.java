package com.euripedes.alunos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.euripedes.alunos.model.Aluno;

/*	Nesta interface implementaremos o "AlunoRepository", onde 
 	trataremos do repositório de nossa aplicação.
 	
 *	Por que criar uma interface e não uma classe?
 	Porque a interface define a ação de uma classe. Criamos a classe 
 	"Aluno" que serve como modelo de nosso objeto, mas precisamos 
 	definir o comportamento dessa classe com relação ao banco de dados.
 
 * 	Para começar, vamos utilizar a anotação "@Repository" para indicar 
 	ao compilador que esta interface é uma definição de Repositório.
 	
 *	Perceba que utilizamos a palavra "extends" para herdar algumas 
 	funcionalidades e implementações JPA relacionada a dados.
 	
 *	Como parâmetro da declaração JPA, indicamos a classe "Aluno" e um
 	valor base, sendo ele "Long".
 */

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{

}

/*	Na próxima aula, vamos implementar configurações para exception
 	e para controller. Então, siga até o pacote "exception" para 
 	continuar o conteúdo.
 */

/*	REFERÊNCIAS
 
 * 	Tópicos de Poo e Java
 	https://dcm.ffclrp.usp.br/~evandro/ibm1030/topicos/topicos2.html#:~:text=Uma%20interface%20%C3%A9%20uma%20maneira,feita%20atrav%C3%A9s%20de%20seus%20tipos.
 
 */
