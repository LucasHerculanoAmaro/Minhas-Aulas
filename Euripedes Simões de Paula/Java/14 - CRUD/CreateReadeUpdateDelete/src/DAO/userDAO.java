package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Factory.DatabaseConnection;
import Model.User;

public class userDAO {

/*	Na aula de hoe, iremos trabalhar com a cria��o do primeiro m�todo 
	CRUD, o CREATE.

*	Criamos o pacote 'DAO' para trabalhar com todos os m�todos CRUD. 
	Mas o que significa 'DAO', j� que vamos trabalhar com 'CRUD'?
	A palavra 'DAO' � um acr�nimo em ingl�s para 'Data Access Object',
	e � um padr�o para aplica��es que trabalham com persist�ncia de dados.
	
*	No padr�o DAO, existem regras de neg�cio que separam o acesso ao 
	banco de dados. entra elas est�o:
	
		-> Prover interface que abstrai o acesso a dados.
		-> L�r e gravar apartir da origem dos dados.
		-> Encapsular o acessso aos dados.

*	Agora que entendemos o que � o padr�o DAO, vamos aos trabalhos.
	
*	Para com o m�todo CREATE, precisamos criar um m�todo e adicionar a 
	ele um script SQL, e usar a estrutura TRY/CATCH para usar a conex�o
	que criamos anteriormente para passar o script SQL.
*/
	
	
//	Criando o m�todo CREATE
	public void createUser(
			
//			Classe 	X	Objeto
			User 		user
			
			) {
		
//		Passando Script SQL para inserir dados no banco
		String sql = "INSERT INTO users (nome, email) VALUES (?, ?)";

		
/*		OBS: O '(?)' s�o conhecidos como placeholders, e ser�o 
 		substitu�dos pelos valores reais adicionados em uma execu��o. 
 
* 		Agora vamos utilizar a estrutura TRY/CATCH para estabelecer uma 
* 		conex�o com o banco de dados e preparar a instru��o SQL que 
* 		criamos acima.
		
*		
*/		
		try (
				
//				Passando a conex�o com o banco de dados
				Connection conn = DatabaseConnection.getConnection();
				
//				Passando o Script na Conex�o
				PreparedStatement stmt = conn.prepareStatement(sql)
				
/*				OBS: 'PreparedStatement' interpreta e compila a Consulta
 				SQL, otimiza e planeja o caminho de busca dos dados.
 				
* 				Tamb�m � utilizado para segunran�a, prevenindo contra 
				ataques de ine��o SQL.
 				
* 				� muito utilizando quando um Script ser� executado 
				diversas vezes, onde as execu��es s�o mais r�pidas e t�m
				menos sobrecarga no banco de dados.
				
*				Quando utilizamos isntru��es SQL que aceitam par�metros, 
				podemos usar a mesma instru��o para fornecer valores 
				diferentes a cada execu��o.
*/
				){
			
			
//			Aqui vamos atribuir os valores ao Placeholders
			
//			Coluna 'NOME' na tabela 'USER'
			stmt.setString(1, user.getNome());
			
//			Coluna 'EMAIL' na tabela 'USER'
			stmt.setString(2, user.getEmail());
			
/*			OBS: os n�meros no par�metro do m�todo 'setString' indica a 
			posi��o do placeholder na consulta:
			
				'1' -> Se trata da primeira coluna do Script (NOME).
				'2' -> Se trata da segunda coluna do Script (EMAIL). 
*/			

//			Executando a consulta
			stmt.executeUpdate();
			
/*			OBS: o m�todo 'executeUdate()' � usado para executar 
			instru��es SQL que alteram o banco de dados. 
			Podem ser instru��es como: INSERT, UPDATE e DELETE.
*/		}
		
		
		
//		CATCH com uma excess�o		
		catch(

//				Utilizaremos uma SQLException para caso n�o seja possivel
//				modificar o banco de dados
				SQLException e
				) {
			
//			Criamos uma RuntimeExceptions para exibir a mensagem de erro			
			throw new RuntimeException("Erro ao inserir usu�rio", e);
			}
		
	}
	
}

/*	REFER�NCIAS
 
*	Como funciona o Padr�o DAO?
	https://pt.stackoverflow.com/questions/113840/como-funciona-o-padr%C3%A3o-dao
	
*	O que � um DAO em Java?
	https://pt.wikipedia.org/wiki/Objeto_de_acesso_a_dados#:~:text=Objeto%20de%20acesso%20a%20dados%20(acr%C3%B4nimo%20do%20ingl%C3%AAs%20Data%20Access,por%20exemplo%20Java)%20e%20arquitetura

*	Qual a diferen�a entre o Statement e o PreparedStatement?
	https://pt.stackoverflow.com/questions/99620/qual-a-diferen%C3%A7a-entre-o-statement-e-o-preparedstatement
**/
