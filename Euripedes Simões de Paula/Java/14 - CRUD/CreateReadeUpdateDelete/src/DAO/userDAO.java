package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Factory.DatabaseConnection;
import Model.User;

public class userDAO {

/*	Na aula de hoe, iremos trabalhar com a criação do primeiro método 
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
		String sql = "INSERT INTO users (nome, email) VALUES (?, ?)";

		
/*		OBS: O '(?)' são conhecidos como placeholders, e serão 
 		substituídos pelos valores reais adicionados em uma execução. 
 
* 		Agora vamos utilizar a estrutura TRY/CATCH para estabelecer uma 
* 		conexão com o banco de dados e preparar a instrução SQL que 
* 		criamos acima.
		
*		
*/		
		try (
				
//				Passando a conexão com o banco de dados
				Connection conn = DatabaseConnection.getConnection();
				
//				Passando o Script na Conexão
				PreparedStatement stmt = conn.prepareStatement(sql)
				
/*				OBS: 'PreparedStatement' interpreta e compila a Consulta
 				SQL, otimiza e planeja o caminho de busca dos dados.
 				
* 				Também é utilizado para segunrança, prevenindo contra 
				ataques de ineção SQL.
 				
* 				É muito utilizando quando um Script será executado 
				diversas vezes, onde as execuções são mais rápidas e têm
				menos sobrecarga no banco de dados.
				
*				Quando utilizamos isntruções SQL que aceitam parâmetros, 
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
*/		}
		
		
		
//		CATCH com uma excessão		
		catch(

//				Utilizaremos uma SQLException para caso não seja possivel
//				modificar o banco de dados
				SQLException e
				) {
			
//			Criamos uma RuntimeExceptions para exibir a mensagem de erro			
			throw new RuntimeException("Erro ao inserir usuário", e);
			}
		
	}
	
}

/*	REFERÊNCIAS
 
*	Como funciona o Padrão DAO?
	https://pt.stackoverflow.com/questions/113840/como-funciona-o-padr%C3%A3o-dao
	
*	O que é um DAO em Java?
	https://pt.wikipedia.org/wiki/Objeto_de_acesso_a_dados#:~:text=Objeto%20de%20acesso%20a%20dados%20(acr%C3%B4nimo%20do%20ingl%C3%AAs%20Data%20Access,por%20exemplo%20Java)%20e%20arquitetura

*	Qual a diferença entre o Statement e o PreparedStatement?
	https://pt.stackoverflow.com/questions/99620/qual-a-diferen%C3%A7a-entre-o-statement-e-o-preparedstatement
**/
