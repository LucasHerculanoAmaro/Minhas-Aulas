
package service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
	
	private String criptPassword(String password) {
		BCryptPasswordEncoder encoder = new  BCryptPasswordEncoder();
		String passwordEncoder = encoder.encode(password);
		return passwordEncoder;
	}
	
	@Autowired
	private Optional<Usuario> cadastrarUsuario(Usuario usuario) {
		if (usuarioRepository.findByUsuario(
				usuario.getUsername()).isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, "Usuário á existe!", null);
		} 
		usuario.setPassword(criptPassword(usuario.getPassword()));
		return Optional.of(usuarioRepository.save(usuario));
	}
	
	

	
}

/*	REFERÊNCIA
 
 * 	[Dúvida] Anotações Service e Repository
 	https://cursos.alura.com.br/forum/topico-duvida-anotacoes-service-e-repository-292594
 
 *	Guia das Principais Anotações do Spring Framework
 	https://www.linkedin.com/pulse/guia-das-principais-anota%C3%A7%C3%B5es-do-spring-framework-flavio-vieira-weltf/
 
 * 
 * 
 */
