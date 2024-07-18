package Factory;

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
	
	
/*	OBS: Para estudos, por padrão, o login e senha são os mesmos. 
  	Fica muito mais fácil testar aplicativos diferentes que tenham por 
  	padrão a mesma configuração. Mas não é uma obrigação, e sim uma boa
  	prática.

*	Agora vamos criar um método responsável por usar o driver, URL, Login 
	e Senha para realizar a conexão.

*/	
	
	
	
}
