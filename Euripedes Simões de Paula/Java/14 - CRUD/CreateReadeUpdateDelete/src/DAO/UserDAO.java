package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import Factory.DatabaseConnection;
import Model.User;

public class UserDAO {

/*	Na aula de hoje, iremos trabalhar com a criação do primeiro método 
	CRUD, o CREATE.

*	Criamos o pacote 'DAO' para trabalhar com todos os métodos CRUD. 
	Mas o que significa 'DAO', já que vamos trabalhar com 'CRUD'?
	A palavra 'DAO' é um acrônimo em inglês para 'Data Access Object',
	e é um padrão para aplicações que trabalham com persistência de dados.
	
*	No padrão DAO, existem regras de negócio que separam o acesso ao 
	banco de dados. entra elas estão:
	
		-> Prover interface que abstrai o acesso a dados.
		-> Lêr e gravar apartir da origem dos dados.
		-> Encapsular o acessso aos dados.

*	Agora que entendemos o que é o padrão DAO, vamos aos trabalhos.
	
*	Para com o método CREATE, precisamos criar um método e adicionar a 
	ele um script SQL, e usar a estrutura TRY/CATCH para usar a conexão
	que criamos anteriormente para passar o script SQL.
*/
	
	
//	Criando o método CREATE
	public void createUser(
			
//			Classe 	X	Objeto
			User 		user
			
			) {
		
//		Passando Script SQL para inserir dados no banco
		String sql = "INSERT INTO tb_users (nome, email) VALUES (?, ?)";

		
/*		OBS: O '(?)' são conhecidos como placeholders, e serão 
 		substituídos pelos valores reais adicionados em uma execução. 
 
* 		Agora vamos utilizar a estrutura TRY/CATCH para estabelecer uma 
 		conexão com o banco de dados e preparar a instrução SQL que 
 		criamos acima.		
*/		
		try (
				
//				Passando a conexão com o banco de dados
				Connection conn = DatabaseConnection.getConnection();
				
//				Passando o Script na Conexão
				PreparedStatement stmt = conn.prepareStatement(sql)
				
/*				OBS: 'PreparedStatement' interpreta e compila a Consulta
 				SQL, otimiza e planeja o caminho de busca dos dados.
 				
* 				Também é utilizado para segurança, prevenindo contra 
				ataques de injeção SQL.
 				
* 				É muito utilizando quando um Script será executado 
				diversas vezes, onde as execuções são mais rápidas e têm
				menos sobrecarga no banco de dados.
				
*				Quando utilizamos instruções SQL que aceitam parâmetros, 
				podemos usar a mesma instrução para fornecer valores 
				diferentes a cada execução.
*/
				){
			
			
//			Aqui vamos atribuir os valores ao Placeholders
			
//			Coluna 'NOME' na tabela 'USER'
			stmt.setString(1, user.getNome());
			
//			Coluna 'EMAIL' na tabela 'USER'
			stmt.setString(2, user.getEmail());
			
/*			OBS: os números no parâmetro do método 'setString' indica a 
			posição do placeholder na consulta:
			
				'1' -> Se trata da primeira coluna do Script (NOME).
				'2' -> Se trata da segunda coluna do Script (EMAIL). 
*/			

//			Executando a consulta
			stmt.executeUpdate();
			
/*			OBS: o método 'executeUdate()' é usado para executar 
			instruções SQL que alteram o banco de dados. 
			Podem ser instruções como: INSERT, UPDATE e DELETE.
*/		
			
//			Mensagem em caso de sucesso
			System.out.println("Usuário criado com sucesso!");
			
		conn.close();
		}
		
//		CATCH com uma excessão		
		catch(

//				Utilizaremos uma SQLException para caso não seja possivel
//				modificar o banco de dados
				SQLException e
				) {
			
//			Criamos uma RuntimeExceptions para exibir a mensagem de erro			
			throw new RuntimeException("Erro ao inserir usuário", e);
			}
		
/*	Na próxima aula, criaremos o método READ.	*/
		
	}
	
//	Criando o método READ
	public List<User> readUsers(){
		
//		Passando Script SQL para selecionar os dados no banco
		String sql = "SELECT * FROM tb_users";
		
//		Passando a Lista de Usuários como uma nova lista
		List<User> users = new ArrayList<>();
		
/*		Vamos novamente utilizar a estrutura TRY/CATCH, agora para passar
		a conexão e utilizar o Script SQL para recuperar os dados no banco
		de dados.
*/
		
		try (
	
//				Passando a conexão com o banco de dados
				Connection conn = DatabaseConnection.getConnection();
				
//				Passando Script na conexão
				PreparedStatement stmt = conn.prepareStatement(sql);
				
//				Utilizando 'ResultSet' para armazenar os resultados SQL
				ResultSet rs = stmt.executeQuery()
						
/*				OBS: O 'ResultSet' é uma classe que armazena os resultados 
				de uma query SQL executada.
				
*				Esta classe funciona como um conjunto 'set', e guarda uma 
				tabela que é o resultado na consulta SQL.
*/										
				) {
	
//			Enquanto a resultSet faz a leitura
			while (rs.next()) {
	
//				Criando um novo método com base na classe User
				User user = new User();
				
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
				
//				Utilizaremos uma SQLException para caso não seja possivel
//				modificar o banco de dados
				SQLException e
				) {

//			Criamos uma RuntimeExceptions para exibir a mensagem de erro
			throw new RuntimeException("Erro ao consultar usuários", e);
		}
		
//		Retornando o ID, Nome e Email
		return users;
		
/*		Na próxima aula, vamos trabalhar com o método UPDATE	*/
		
	}
	
//	Criando o método Update
	public void updateUser(
			
//			Classe 	x 	Objeto
			User 		user
			) {
		
//		Passando o Script SQL para atualizar os dados no banco
		String sql = "UPDATE tb_users SET nome = ?, email = ?"+" WHERE id = ?";
		
		try (
//				Passando a conexão com o banco de dados
				Connection conn = DatabaseConnection.getConnection();
				
//				Passando o Script na conexão
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
			System.out.println("Usuário atualizado com sucesso!");
		}
		
		catch (
				
//				Utilizando SQLException em caso de erro
				SQLException e
				) {
			
//			Exibindo o erro SQL
			throw new RuntimeException("Erro ao atualizar usuário", e);
		}
		
	}

//	Criando o método Delete
	public void deleteUser(
			
//			Passando como parâmetro o 'ID' para deletar
			int id
			) {
		
//		Passando Script SQL para deletar os dados no banco
		String sql = "DELETE FROM tb_users WHERE id = ?";
		
		try (
				
//				Passando a conexão com o bancode dados
				Connection conn = DatabaseConnection.getConnection();
				
//				Passando o Script na conexão
				PreparedStatement stmt = conn.prepareStatement(sql)
				) {
			
//			Coluna 'ID' na tabela 'USERS'
			stmt.setInt(1, id);
			
//			Executando o Script
			stmt.executeUpdate();
		}
		
		catch (
				
//				Criando Exeption em case de erro
				SQLException e
				) {
			
//			Exibindo o erro SQL			
			throw new RuntimeException("Erro ao deletar usuário", e);
		}
		
	}	

	
/*	CONCLUSÃO
 
*	Agora que já trabalhamos com a criação de todos os métodos CRUD, 
	precisamos testar para saber se algum erro acontecerá, e caso tenha 
	algum erro, vamos atrás da solução para aprendermos a resolvê-lo.
	
*	Antes de mais nada, uma aplicação não funcionará sem o método principal
	de nossa aplicação, então, se você ainda não priou o método 'main()', 
	faça isso, será necessário para realizarmos as nossas interações na 
	aplicação.
	
*	Agora siga para o classe 'Main' para realizarmos os nossos testes.	
	
*/	
	
	
}

/*	REFERÊNCIAS
 
*	Como funciona o Padrão DAO?
	https://pt.stackoverflow.com/questions/113840/como-funciona-o-padr%C3%A3o-dao
	
*	O que é um DAO em Java?
	https://pt.wikipedia.org/wiki/Objeto_de_acesso_a_dados#:~:text=Objeto%20de%20acesso%20a%20dados%20(acr%C3%B4nimo%20do%20ingl%C3%AAs%20Data%20Access,por%20exemplo%20Java)%20e%20arquitetura

*	Qual a diferença entre o Statement e o PreparedStatement?
	https://pt.stackoverflow.com/questions/99620/qual-a-diferen%C3%A7a-entre-o-statement-e-o-preparedstatement

*	O que é o ResultSet?
	https://cursos.alura.com.br/forum/topico-o-que-e-o-resultset-258207

*/
