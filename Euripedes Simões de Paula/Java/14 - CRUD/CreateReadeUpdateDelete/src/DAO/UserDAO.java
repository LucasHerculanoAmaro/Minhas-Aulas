package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Factory.DatabaseConnection;
import Model.User;

public class UserDAO {

/*	Na aula de hoje, iremos trabalhar com a cria��o do primeiro m�todo 
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
		String sql = "INSERT INTO tb_users (nome, email) VALUES (?, ?)";

		
/*		OBS: O '(?)' s�o conhecidos como placeholders, e ser�o 
 		substitu�dos pelos valores reais adicionados em uma execu��o. 
 
* 		Agora vamos utilizar a estrutura TRY/CATCH para estabelecer uma 
 		conex�o com o banco de dados e preparar a instru��o SQL que 
 		criamos acima.		
*/		
		try (
				
//				Passando a conex�o com o banco de dados
				Connection conn = DatabaseConnection.getConnection();
				
//				Passando o Script na Conex�o
				PreparedStatement stmt = conn.prepareStatement(sql)
				
/*				OBS: 'PreparedStatement' interpreta e compila a Consulta
 				SQL, otimiza e planeja o caminho de busca dos dados.
 				
* 				Tamb�m � utilizado para seguran�a, prevenindo contra 
				ataques de inje��o SQL.
 				
* 				� muito utilizando quando um Script ser� executado 
				diversas vezes, onde as execu��es s�o mais r�pidas e t�m
				menos sobrecarga no banco de dados.
				
*				Quando utilizamos instru��es SQL que aceitam par�metros, 
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
*/		
			
//			Mensagem em caso de sucesso
			System.out.println("Usu�rio criado com sucesso!");
			
			conn.close();
		}
		
//		CATCH com uma excess�o		
		catch(

//				Utilizaremos uma SQLException para caso n�o seja possivel
//				modificar o banco de dados
				SQLException e
				) {
			
//			Criamos uma RuntimeExceptions para exibir a mensagem de erro			
			throw new RuntimeException("Erro ao inserir usu�rio", e);
			}
		
/*	Na pr�xima aula, criaremos o m�todo READ.	*/
		
	}
	
//	Criando o m�todo READ
	public List<User> readUsers(){
		
//		Passando Script SQL para selecionar os dados no banco
		String sql = "SELECT * FROM tb_users";
		
//		Passando a Lista de Usu�rios como uma nova lista
		List<User> users = new ArrayList<>();
		
/*		Vamos novamente utilizar a estrutura TRY/CATCH, agora para passar
		a conex�o e utilizar o Script SQL para recuperar os dados no banco
		de dados.
*/
		
		try (
	
//				Passando a conex�o com o banco de dados
				Connection conn = DatabaseConnection.getConnection();
				
//				Passando Script na conex�o
				PreparedStatement stmt = conn.prepareStatement(sql);
				
//				Utilizando 'ResultSet' para armazenar os resultados SQL
				ResultSet rs = stmt.executeQuery()
						
/*				OBS: O 'ResultSet' � uma classe que armazena os resultados 
				de uma query SQL executada.
				
*				Esta classe funciona como um conjunto 'set', e guarda uma 
				tabela que � o resultado na consulta SQL.
*/										
				) {
	
//			Enquanto a resultSet faz a leitura
			while (rs.next()) {
	
//				Criando um novo m�todo com base na classe User
				User user = new User(0, null, null);
				
//				Lendo o ID
				user.setId(rs.getInt("id"));
				
//				Lendo o Nome
				user.setNome(rs.getString("nome"));
				
//				Lendo o Email
				user.setEmail(rs.getString("email"));
				
//				Adicionando o resultado para futuro retorno
				users.add(user);	
			}

		} catch (
				
//				Utilizaremos uma SQLException para caso n�o seja possivel
//				consultar o banco de dados
				SQLException e
				) {

//			Criamos uma RuntimeExceptions para exibir a mensagem de erro
			throw new RuntimeException("Erro ao consultar usu�rios", e);
		}
		
//		Retornando o ID, Nome e Email
		return users;
		
/*		Na pr�xima aula, vamos trabalhar com o m�todo UPDATE	*/
		
	}
	
//	Criando o m�todo Update
	public void updateUser(
			
//			Classe 	x 	Objeto
			User 		user
			) {
		
//		Passando o Script SQL para atualizar os dados no banco
		String sql = "UPDATE tb_users SET nome = ?, email = ?"+" WHERE id = ?";
		
		try (
//				Passando a conex�o com o banco de dados
				Connection conn = DatabaseConnection.getConnection();
				
//				Passando o Script na conex�o
				PreparedStatement stmt = conn.prepareStatement(sql)
				) {
			
//			Coluna 'NOME' 
			stmt.setString(1, user.getNome());
			
//			Coluna 'EMAIL' 
			stmt.setString(2, user.getEmail());
			
//			Coluna 'ID' 
			stmt.setInt(3, user.getId());
			
//			Executa a consulta
			stmt.executeUpdate();	
			
//			Mensagem de Sucesso
			System.out.println("Usu�rio atualizado com sucesso!");
		}
		
		catch (
				
//				Utilizando SQLException em caso de erro
				SQLException e
				) {
			
//			Exibindo o erro SQL
			throw new RuntimeException("Erro ao atualizar usu�rio", e);
		}
		
	}

//	Criando o m�todo Delete
	public void deleteUser(
			
//			Passando como par�metro o 'ID' para deletar
			int id
			) {
		
//		Passando Script SQL para deletar os dados no banco
		String sql = "DELETE FROM tb_users WHERE id = ?";
		
		try (
				
//				Passando a conex�o com o banco de dados
				Connection conn = DatabaseConnection.getConnection();
				
//				Passando o Script na conex�o
				PreparedStatement stmt = conn.prepareStatement(sql)
				) {
			
//			Coluna 'ID' na tabela 'USERS'
			stmt.setInt(1, id);
			
//			Executando o Script
			stmt.executeUpdate();
		}
		
		catch (
				
//				Criando Exception em caso de erro
				SQLException e
				) {
			
//			Exibindo o erro SQL			
			throw new RuntimeException("Erro ao deletar usu�rio", e);
		}
		
	}	

	
/*	CONCLUS�O
 
*	Agora que j� trabalhamos com a cria��o de todos os m�todos CRUD, 
	precisamos testar para saber se algum erro acontecer�, e caso tenha 
	algum erro, vamos atr�s da solu��o para aprendermos a resolv�-lo.
	
*	Antes de mais nada, uma aplica��o n�o funcionar� sem o m�todo principal
	de nossa aplica��o, ent�o, se voc� ainda n�o priou o m�todo 'main()', 
	fa�a isso, ser� necess�rio para realizarmos as nossas intera��es na 
	aplica��o.
	
*	Agora siga para o classe 'Main' para realizarmos os nossos testes.	
	
*/	
	
	
}

/*	REFER�NCIAS
 
*	Como funciona o Padr�o DAO?
	https://pt.stackoverflow.com/questions/113840/como-funciona-o-padr%C3%A3o-dao
	
*	O que � um DAO em Java?
	https://pt.wikipedia.org/wiki/Objeto_de_acesso_a_dados#:~:text=Objeto%20de%20acesso%20a%20dados%20(acr%C3%B4nimo%20do%20ingl%C3%AAs%20Data%20Access,por%20exemplo%20Java)%20e%20arquitetura

*	Qual a diferen�a entre o Statement e o PreparedStatement?
	https://pt.stackoverflow.com/questions/99620/qual-a-diferen%C3%A7a-entre-o-statement-e-o-preparedstatement

*	O que � o ResultSet?
	https://cursos.alura.com.br/forum/topico-o-que-e-o-resultset-258207

*/
