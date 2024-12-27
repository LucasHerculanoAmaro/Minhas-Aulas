package com.euripedes.Conectando.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.euripedes.Conectando.model.Usuario;
import com.euripedes.Conectando.model.UsuarioLogin;
import com.euripedes.Conectando.repository.UsuarioRepository;
import com.euripedes.Conectando.service.JwtService;
import com.euripedes.Conectando.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private JwtService jwtService;
	
//	@Autowired
//    private PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioService usuarioService /*, PasswordEncoder passwordEncoder*/) {
        this.usuarioService = usuarioService;
//        this.passwordEncoder = passwordEncoder;
    }
    
// 	Método GET
	@GetMapping("/all")
	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		return ResponseEntity.ok(usuario);
	}
	
// 	Método POST
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
		return ResponseEntity.ok(usuarioService.createUsuario(usuario).orElseThrow(
			() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao criar usuário")
		));
	}

	@PostMapping("/logar")
	public /*ResponseEntity<UsuarioLogin>*/ ResponseEntity<Map<String, Object>> logar(@RequestBody Optional<UsuarioLogin> usuario) {
		Map<String, Object> response = usuarioService.loginUsuario(usuario);
			return ResponseEntity.ok(response);
	}
//	public ResponseEntity<Map<String, String>> logar(@RequestBody Map<String, String> request) {
//	    String usuario = request.get("usuario");
//	    String senha = request.get("senha");
//
//	    Map<String, String> response = new HashMap<>();
//
//	    // Verifique o usuário no banco de dados
//	    Optional<Usuario> usuarioOptional = usuarioRepository.findByUsuario(usuario);
//
//	    if (usuarioOptional.isPresent()) {
//	        Usuario usuarioBanco = usuarioOptional.get();
//
//	        // Verifique se a senha está correta (geralmente, é feita uma comparação com a senha criptografada)
//	        if (senha.equals(usuarioBanco.getSenha())) {
//	            String token = jwtService.generateToken(usuario, usuarioBanco.getTipo());
//	            response.put("message", "Autenticação bem-sucedida!");
//	            response.put("token", token);
//	            return ResponseEntity.ok(response);
//	        }
//	    }
//
//	    response.put("message", "Login ou senha incorretos.");
//	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//	}

//	public ResponseEntity<Map<String, String>> logar(@RequestBody Map<String, Object> request, UsuarioLogin usuarioLogin) {
//	    System.out.println("Corpo recebido: " + request);
//	    String usuario = (String) request.get("usuario");
//	    String senha = (String) request.get("senha");
//
//	    System.out.println("Usuário recebido: " + usuario);
//	    System.out.println("Senha recebida: " + senha);
//
//	    Map<String, String> response = new HashMap<>();
//
//	    if ("usuario_teste".equals(usuario) && "senha123".equals(senha)) {
//			String token = jwtService.generateToken(usuarioLogin.getUsuario(), "USER");
//
//	        response.put("message", "Autenticação bem-sucedida!");
//	        response.put("token", token);
//	        return ResponseEntity.ok(response);
//	    }
//
//	    response.put("message", "Login ou senha incorretos.");
//	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//	}
//	public ResponseEntity<String> logar(@RequestBody UsuarioLogin usuarioLogin) {
//		
//		System.out.println("Usuário recebido:" + usuarioLogin.getUsuario());
//		System.out.println("Senha recebida:" + usuarioLogin.getSenha());
//		
//		Optional<UsuarioLogin> usuarioLoginOpt = Optional.of(usuarioLogin);
//		
//		Optional<Usuario> usuario = usuarioService.loginUsuario(usuarioLogin.getUsuario(), usuarioLogin.getSenha());
//		
//		if (usuario.isPresent()) {
//			String token = jwtService.generateToken(usuarioLogin.getUsuario(), "USER");
//			ResponseEntity.ok().body("Autenticação bem-sucedida");
//			return ResponseEntity.ok(token);
//		}
//		
//		return ResponseEntity
////				.ok().body("Autenticação bem-sucedida!");
//				.status(HttpStatus.UNAUTHORIZED).body("Credenciais invalidas.");
//	}

//	Método PUT
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		usuario.setNome(usuarioDetails.getNome());
		usuario.setIdade(usuarioDetails.getIdade());
		usuario.setTurma(usuarioDetails.getTurma());
		usuario.setProfissao(usuarioDetails.getProfissao());
		usuario.setUsuario(usuarioDetails.getUsuario());
		usuario.setSenha(usuarioDetails.getSenha());
		usuario.setTurma(usuarioDetails.getTurma());
		usuario.setTipo(usuarioDetails.getTipo());

		Usuario updatedUsuario = usuarioRepository.save(usuario);
		return ResponseEntity.ok(updatedUsuario);
	}

// 	Método DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
		usuarioRepository.delete(usuario);
		return ResponseEntity.noContent().build();
	}
}



