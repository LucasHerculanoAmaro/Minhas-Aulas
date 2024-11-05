package application;

import java.util.List;

import DAO.UserDAO;
import Model.User;

public class Main {

	public static void main(String[] args) {
		
//		Criando um objeto da classe 'UserDAO'		
		UserDAO userDAO = new UserDAO();
		
//		Criando uma Lista da classe 'User' e atribuindo o m�todo READ
		List<User> users = userDAO.readUsers();
		
/*		Agora vamos realizar os nossos testes, onde criar, consultar, editar 
		e deletar um objeto.
		
*		Logo acima foi criado um objeto da classe 'UserDAO', onde ser�
		aplicado m�todos de cria��o, leitura, atualiza��o e exclus�o no 
		objeto dessa classe.
				
* 		Bem, vamos colocar os dedos no teclado (t�, sei que n�o sou bom com 
 		humor kkkk)...
*/		

		
		
/* CREATE	*/ 
		
		
//		Criando um Objeto da Classe 'User'
		User newUser = new User(0, null, null);
		
//		Adicionando um Nome
		newUser.setNome("Bia Podre");

//		Adicionando um Email
		newUser.setEmail("Bia@podre.com");
		
//		Passando o novo usu�rio no userDAO
		userDAO.createUser(newUser); 

	
		
		
/* UPDATE 
		
//		Pagendo o dado da Lista
		User existingUser = users.get(
				
//				Posi��o do dado na lista (n�o se trata do id)
				0
				);
		
//		Passando o novo dado a Lista
		existingUser.setNome("Juliana");
		
//		Utilizando o m�todo 'UPDATE' para inserir o novo dado
		userDAO.updateUser(existingUser);
*/			
	

/* DELETE
		
//		Importando o m�todo da Classe
		userDAO.deleteUser(
				
//				Indique o ID do usu�rio
				13
				);	
 */
		
		
/* READ */
	
		
/* PRIMEIRO: 

		
//		Lendo com o m�todo 'forEach()' 
		users.forEach(
				
//		Utilizando a express�o Lambda para apresentar os dados no console
				user -> System.out.println(
						
//						Buscando 'ID'
						"Id: " + user.getId() +
						
//						Buscando 'NOME'
						"\nNome: " + user.getNome() +
						
//						Buscando 'EMAIL'						
						"\nE-mail: " + user.getEmail() +
						
//						Limitador						
						"\n-----------------"
						)
				);
*/
		
/* SEGUNDO: 

		
//		Lendo com o loop 'for/each'
		for (
				
//				Classe		Lista
				User user : users 
				
				) {
			
//			Buscando 'ID'			
			System.out.println("Id: " + user.getId());
			
//			Buscando 'NOME'
			System.out.println("Nome: " + user.getNome());
			
//			Buscando 'EMAIL'
			System.out.println("E-mail: " + user.getEmail());
			
//			Limitador
			System.out.println("-----------------");
		}
*/	
	}
	
}
