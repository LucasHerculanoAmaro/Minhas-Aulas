package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Usuario;

/*	Primeiramente criamos uma interface que recebe herança da extensão JPA do Spring.
 
 * 	O objetivo desta interface é interagir com o banco de dados.	*/

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

/*	Aqui estamos usando uma "Optional" para retornar uma lista de usuários. 
	
*	OBS: A "Optional" retorna valores "non-null", e o seu objetivo é verificar se há
	algum conteúdo presente no repositório.		*/
	Optional
	
//		Aqui referenciamos a classe "Usuarios" do pacote "Model".
		<Usuario> 
	
/*		Este método têm como parâmetro o atributo "login" da classe "Usuario", e será 
		responsável por consultar o login.		
		
 *		OBS: Dê nomes propícios aos métodos, quando estes disponibilizarem de escolha.	*/
		findByLogin(String login);

	
	
}

/*	CONCLUSÃO
 
 *	Nesta aula, criamos uma interface capaz de interagir com o banco de dados, onde 
 	será retornado o login.
 	
 *	Na próxima aula, vamos trabalhar com as definições de serviço, então siga para o 
 	pacote "service" para acompanhar o próximo conteúdo.	*/

