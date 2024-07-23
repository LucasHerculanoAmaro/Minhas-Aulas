package application;

import DAO.UserDAO;
import Model.User;

public class Main {

	public static void main(String[] args) {
		
//		Criando um obeto da classe 'UserDAO'		
		UserDAO userDAO = new UserDAO();
		
/*		Agora vamos realizar os nossos testes, onde criar, consultar, editar 
		e deletar um objeto.
		
*		Logo acima foi criado um objeto da classe 'UserDAO', onde ser�
		aplicado m�todos de cria��o, leitura, atualiza��o e exclus�o no 
		objeto dessa classe.
				
* 		Bem, vamos colocar os dedos no teclado (t�, sei que n�o sou bom com 
 		humor kkkk)...
*/		
		
		
// CREATE
		
//		Criando um Obeto da Classe 'Model'
		User newUser = new User();
		
//		Adicionando um Nome
		newUser.setNome("Juliana");

//		Adicionando um Email
		newUser.setEmail("Juliana@hotmail.com");
		
//		Passando o novo usu�rio no userDAO
		userDAO.createUser(newUser);
		
	
		
	}
	
}
