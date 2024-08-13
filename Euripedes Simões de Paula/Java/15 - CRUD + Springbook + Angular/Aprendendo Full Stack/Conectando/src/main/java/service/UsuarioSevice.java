
package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import model.Usuario;
import repository.UsuarioRepository;

//	Na aula de hoje, criaremos um serviço que gerencia operações com o usuário.

/* 	Vamos iniciar a anotação "@Service" para indicar que esta classe 
	contém a regra de negócio da aplicação, e interagir com o repositório de 
	dados.		*/
@Service
public class UsuarioSevice {


//	@Autowired - É utilizada para injetar dependências automaticamente no Bean Spring.
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
/*####################################	*/
	
//	Aqui criamos o método cadastrar com base na classe 'Usuario' do pacote 'model'.	
	public Usuario cadastrar(
			
//			Iniciando um parâmetro baseado na classe 'Usuario'.
			Usuario usuario
			) {
					
//		Utilizando o método 'setSenha()', implementado na classe 'Usuário'.
		usuario.setSenha(
				
//				Utilizando a interface para pegar a senha do usuário.
				passwordEncoder

/*				Este método 'encode()' é responsável por codificar os dados, em 
 				especial senhas.

 *				OBS: O que este método faz?
 					-> 	Transformação em Hash: pega senhas ou dados de entrada e 
 						converte em um Hash seguro.
 						
 						(Hash): é uma representação de senhas que não pode ser 
 						convertida de volta para a senha original.
 					
 					->	Proteção contra ataques: A senha codificada é armazenada no
 						banco de dados em vez de armazenar os textos simples.
 						
 					->	Salt e Hashing: Para o 'BCriptPassworEncoder', o método 
 						'encode()' gera valores de 'salt', ou valores aleatórios.	*/		
				.encode(
						
//						Aqui utilizamos o método getSenha() para buscar a senha.
						usuario.getSenha())
				);
		
		
		return usuarioRepository
				
//				Insere o novo usuário (usuário que não tem id)
				.save(usuario);
	}

	
	
	

	
/*	CONCLUSÃO
 
 *	Com isso, terminamos a etapa de configuração para cadastro. 
 
 *	Na próxima aula, trabalharemos com a implementação do 'UsuarioDetailsService'.
 	Acompanhe o conteúdo na classe 'UsuarioDetailsService'.
 
 */
	
}

/*	REFERÊNCIA
 
 * 	[Dúvida] Anotações Service e Repository
 	https://cursos.alura.com.br/forum/topico-duvida-anotacoes-service-e-repository-292594
 
 *	Guia das Principais Anotações do Spring Framework
 	https://www.linkedin.com/pulse/guia-das-principais-anota%C3%A7%C3%B5es-do-spring-framework-flavio-vieira-weltf/
 
 * 
 * 
 */
