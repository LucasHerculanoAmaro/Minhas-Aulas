package Factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

/*	INTRODUÇÃO

*	Na aulda de hoje, faremos a conexão com o banco de dados e realizaremos 
	testes para saber se a conexão está estável.
 
* 	Algo que talvez você note é que não iniciaremos o método main neste 
	pacote. Isso não significa que estamos saindo do padrão de boas 
	práticas. Na verdade, separar o código por classes ou pacotes é uma 
	boa maneira de identificar onde está cada configuração.
 
* 	Por exemplo, se você deseja fazer uma configuração nos atributos de um 
	objeto, talvez adicionar ou retirar atributo, você pode encontrar no
	pacote 'model' todos os objetos que deseja configurar. Mas nesse 
	pacote, não haverá classes baseadas em conexão com banco de dados.

*	Bom, vamos ao trabalho.

*	Em primeiro lugar, precisamos fonecer ao aplicativo o Endereço, Usuario 
	e Senha; os mesmos utilizados no banco de dados:
*/	
	
	
//	URL do seu Banco
	private static final String URL = "jdbc:mysql://localhost:3306/localhost";

//	Usuário do seu Banco
	private static final String USER = "root";

//	Senha do seu Banco
	private static final String PASSWORD = "root";
	
//	OBS: Para estudos, por padrão, o login e senha são os mesmos.
	
	
/*	Fica muito mais fácil testar aplicativos diferentes que tenham por 
  	padrão a mesma configuração. Mas não é uma obrigação, e sim uma boa
  	prática.

*	Agora vamos criar um método responsável por usar o Driver, URL, Login 
	e Senha para realizar a conexão.
*/	
	
//	Criando a conexão com o método 'getConnection()'
	public static Connection getConnection() {
		
//		Usaremos o 'try{}' para testar a conexão
		try {
			
//			Carregando o Driver manualmente
			Class.forName("com.mysql.cj.jdbc.Driver");
			
//			O Driver deverá retornar os parametros do método 'getConnection()'
			return DriverManager.getConnection(URL, USER, PASSWORD);
		}

//		O primeiro 'catch' servirá para caso a classe que contém o Driver não seja carregada
		catch (ClassNotFoundException e) {
			
//			Iniciando uma Exception para erros com o Driver JDBC
			throw new RuntimeException("Não foi possível carregar o driver JDBC", e);
		}
		
//		O segundo 'catch' servirá para tratar erros de conexão com o Banco de Dados
		catch (SQLException e) {
			
//			Iniciando uma Exception para erros de conexão MySQL
			throw new RuntimeException("Não foi possível conectar ao Banco de Dados", e);
		}
		
	}
	
/*	Dessa forma, terminamos mais um passo de nosso projeto.

* 	Na próxima aula, vamos criar o objeto ao qual iremos aplicar o método 
	CRUD. Não vamos implementar o método CRUD, mas vamos apenas criar a
	classe de onde se originam os objetos e aplicar os métodos 
	'getters & setters'.
*/
	
}
