package application;

import java.util.List;

import DAO.UserDAO;
import Model.User;

public class Main {

	public static void main(String[] args) {
		
//		Criando um objeto da classe 'UserDAO'		
		UserDAO userDAO = new UserDAO();
		
//		Criando uma Lista da classe 'User' e atribuindo o método READ
		List<User> users = userDAO.readUsers();
		
/*		Agora vamos realizar os nossos testes, onde criar, consultar, editar 
		e deletar um objeto.
		
*		Logo acima foi criado um objeto da classe 'UserDAO', onde será
		aplicado métodos de criação, leitura, atualização e exclusão no 
		objeto dessa classe.
				
* 		Bem, vamos colocar os dedos no teclado (tá, sei que não sou bom com 
 		humor kkkk)...
*/		

		
		
/* CREATE */
		
		
//		Criando um Obeto da Classe 'Model'
		User newUser = new User();
		
//		Adicionando um Nome
		newUser.setNome("Juliana");

//		Adicionando um Email
		newUser.setEmail("juliana.93@hotmail.com");
		
//		Passando o novo usuário no userDAO
		userDAO.createUser(newUser);

	
		
		
/* UPDATE */
		
//		Pagendo o dado da Lista
		User existingUser = users.get(
				
//				Posição do dado na lista (não se trata do id)
				0
				);
		
	
		
//		Passando o novo dado a Lista
		existingUser.setNome("Lucas Herculano Amaro");
		
//		Utilizando o método 'UPDATE' para inserir o novo dado
		userDAO.updateUser(existingUser);
			
	

/* DELETE */
		
//		Importando o método da Classe
		userDAO.deleteUser(
				
//				Indique o ID do usuário
				4
				);	

		
		
/* READ */
	
		
/* PRIMEIRO: */

		
//		Lendo com o método 'forEach()' 
		users.forEach(
				
//		Utilizando a expressão Lambda para apresentar os dados no console
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

		
/* SEGUNDO: */

		
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
	
	}
	
}
