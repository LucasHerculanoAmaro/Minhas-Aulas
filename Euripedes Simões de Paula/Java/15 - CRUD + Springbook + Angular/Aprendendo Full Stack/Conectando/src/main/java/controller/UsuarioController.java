package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exception.ResourceNotFoundException;
import model.Usuario;
import repository.UsuarioRepository;
import service.UsuarioSevice;

/*	INTRODUÇÃO
 
 *	Na aula de hoje aprenderemos a implementar um controlador focado em gerenciar o registro dos usuários.
 
 */


/*	RestController: Simplifica a criação de serviços web RESTful (retorna dados em forma de JSON ou XML). 

*	OBS: RestController retorna os objetos e os dados do objeto são gravados diretamente na resposta HTTP
	como JSON e XML.		*/
@RestController

/*	RequestiMapping: usada para mapear solicitações HTTP para métodos de manipulação dentro do 
 	controlador Spring		
 	
 *	OBS: Especificamos a URL e o método HTTP que ser controlado pelo controlador.		*/
@RequestMapping("/api/usuario")

//Permite que qualquer requisição solicitada seja atendida.
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class UsuarioController {

	@Autowired
	private UsuarioSevice usuarioService;
	
	/*##############################################*/	
	@Autowired
	private UsuarioRepository repository;
	/*##############################################*/
	
//	POST: /api/usuario	
	
/*	PostMapping: Manipula solicitações HTTP POST em serviços web RESTful.
  	Mapeia a URL específica e permite o processamento de dados enviados pelo método POST.		*/	
	
////##############################################	
//	@PostMapping("/cadastrar")
//	public ResponseEntity<?> cadastrar(
//			
//			@RequestBody
//			
////			Passando o objeto da classe 'Usuario' como parâmetro no método 'cadastrar()'.
//			Usuario usuario
//			) {
//		
///*		Atribuindo a classe 'UsuarioService' o método 'cadastrar()', passando como parêmetro o objeto da
//		classe 'usuario'.		*/
//		usuarioService.cadastrar(usuario);
//		
//		return ResponseEntity.ok("Usuario cadastrado com Sucesso!");
//	}
///*##############################################*/
	
	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> getAll() {
	    return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable long id) {
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
//
//	
//	// GET
//	@GetMapping
//	public List<Usuario> getAllUsuario() {
//		return usuarioRepository.findAll();
//	}
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<Usuario> getUsuarioByID(@PathVariable Long id){
//		Usuario usuario = usuarioRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Usuario não existe com este id: " + id));
//		return ResponseEntity.ok(usuario);	
//	}
//	
//	// POST
//	@PostMapping
//	public Usuario createUsuario(@RequestBody Usuario usuario) {
//		return usuarioRepository.save(usuario);
//	}
//	
//	// PUT
//	@PutMapping("/{id}")
//	public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
//		Usuario usuario = usuarioRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado com este id: " + id));
//		
//		usuario.setLogin(usuarioDetails.getLogin());
//		usuario.setSenha(usuarioDetails.getSenha());
//		usuario.setEmail(usuarioDetails.getEmail());
//		
//		Usuario updateUsuario = usuarioRepository.save(usuario);
//		
//		return ResponseEntity.ok(updateUsuario);
//	}
//	
//	// DELETE
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Map<String, Boolean>> deleteUsuario(@PathVariable Long id) {
//		Usuario usuario = usuarioRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Usuário não existe com este id: " + id));
//		
//		usuarioRepository.delete(usuario);
//		Map<String, Boolean> response = new HashMap<>();
//		response.put("Deletado", Boolean.TRUE);
//		
//		return ResponseEntity.ok(response);
//	}
//	
/*##############################################*/
	
	
/*	CONCLUSÃO
 
 * 	Nesta aula aprendemos a criar uma classe controladora, onde utilizará o método HTTP POST para manipular 
 	dados. Criamos um método 'cadastrar()' e aplicamos a este método o objeto da classe 'Usuario' como 
 	parâmetro, e atribuímos ao retorno o objeto da classe 'UsuarioService', que implementará o método 
 	'cadastrar()' o e objeto 'usuario' como parâmetro.		
 	
 *	Agora que trabalhamos com os pacotes (model, repository, security, service e controller) de nossa 
 	aplicação, precisamos realizar alguns testes para saber se nossa aplicação está funcional. Isso envolve 
 	testar os métodos HTTP (GET, POST, PUT, DELETE). 
 	
 
 */
	
}

/*	REFERÊNCIAS
 
 *	Spring Controler vs RestController
 	https://www.baeldung.com/spring-controller-vs-restcontroller
 	
 *	Guia das principais antações do Spring Framework
 	https://www.linkedin.com/pulse/guia-das-principais-anota%C3%A7%C3%B5es-do-spring-framework-flavio-vieira-weltf/
 	
 *	Spring – Anotação @PostMapping e @GetMapping
 	https://www.geeksforgeeks.org/spring-postmapping-and-getmapping-annotation/
 
 */