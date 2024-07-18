package Factory;

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
	
	
/*	OBS: Para estudos, por padr�o, o login e senha s�o os mesmos. 
  	Fica muito mais f�cil testar aplicativos diferentes que tenham por 
  	padr�o a mesma configura��o. Mas n�o � uma obriga��o, e sim uma boa
  	pr�tica.

*	Agora vamos criar um m�todo respons�vel por usar o driver, URL, Login 
	e Senha para realizar a conex�o.

*/	
	
	
	
}
