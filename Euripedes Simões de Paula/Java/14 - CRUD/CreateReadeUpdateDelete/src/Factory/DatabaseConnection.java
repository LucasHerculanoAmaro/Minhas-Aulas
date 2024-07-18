package Factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

/*	INTRODU��O

*	Na aulda de hoje, faremos a conex�o com o banco de dados e realizaremos 
	testes para saber se a conex�o est� est�vel.
 
* 	Algo que talvez voc� note � que n�o iniciaremos o m�todo main neste 
	pacote. Isso n�o significa que estamos saindo do padr�o de boas 
	pr�ticas. Na verdade, separar o c�digo por classes ou pacotes � uma 
	boa maneira de identificar onde est� cada configura��o.
 
* 	Por exemplo, se voc� deseja fazer uma configura��o nos atributos de um 
	objeto, talvez adicionar ou retirar atributo, voc� pode encontrar no
	pacote 'model' todos os objetos que deseja configurar. Mas nesse 
	pacote, n�o haver� classes baseadas em conex�o com banco de dados.

*	Bom, vamos ao trabalho.

*	Em primeiro lugar, precisamos fonecer ao aplicativo o Endere�o, Usuario 
	e Senha; os mesmos utilizados no banco de dados:
*/	
	
	
//	URL do seu Banco
	private static final String URL = "jdbc:mysql://localhost:3306/localhost";

//	Usu�rio do seu Banco
	private static final String USER = "root";

//	Senha do seu Banco
	private static final String PASSWORD = "root";
	
//	OBS: Para estudos, por padr�o, o login e senha s�o os mesmos.
	
	
/*	Fica muito mais f�cil testar aplicativos diferentes que tenham por 
  	padr�o a mesma configura��o. Mas n�o � uma obriga��o, e sim uma boa
  	pr�tica.

*	Agora vamos criar um m�todo respons�vel por usar o Driver, URL, Login 
	e Senha para realizar a conex�o.
*/	
	
//	Criando a conex�o com o m�todo 'getConnection()'
	public static Connection getConnection() {
		
//		Usaremos o 'try{}' para testar a conex�o
		try {
			
//			Carregando o Driver manualmente
			Class.forName("com.mysql.cj.jdbc.Driver");
			
//			O Driver dever� retornar os parametros do m�todo 'getConnection()'
			return DriverManager.getConnection(URL, USER, PASSWORD);
		}

//		O primeiro 'catch' servir� para caso a classe que cont�m o Driver n�o seja carregada
		catch (ClassNotFoundException e) {
			
//			Iniciando uma Exception para erros com o Driver JDBC
			throw new RuntimeException("N�o foi poss�vel carregar o driver JDBC", e);
		}
		
//		O segundo 'catch' servir� para tratar erros de conex�o com o Banco de Dados
		catch (SQLException e) {
			
//			Iniciando uma Exception para erros de conex�o MySQL
			throw new RuntimeException("N�o foi poss�vel conectar ao Banco de Dados", e);
		}
		
	}
	
/*	Dessa forma, terminamos mais um passo de nosso projeto.

* 	Na pr�xima aula, vamos criar o objeto ao qual iremos aplicar o m�todo 
	CRUD. N�o vamos implementar o m�todo CRUD, mas vamos apenas criar a
	classe de onde se originam os objetos e aplicar os m�todos 
	'getters & setters'.
*/
	
}
