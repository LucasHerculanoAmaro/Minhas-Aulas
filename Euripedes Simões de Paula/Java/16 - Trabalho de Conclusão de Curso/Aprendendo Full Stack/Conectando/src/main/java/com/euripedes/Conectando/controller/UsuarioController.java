package com.euripedes.Conectando.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.euripedes.Conectando.model.Usuario;
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
	
	@Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
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
	public ResponseEntity<String> logar(@RequestBody Usuario usuario) {
		Optional<Usuario> usuarioOpt = usuarioService.getUsuario(usuario.getUsuario());
		
		if (usuarioOpt.isPresent() && passwordEncoder.matches(usuario.getSenha(), usuarioOpt.get().getSenha())) {
			String token = jwtService.generateToken(usuario.getUsuario(), "USER");
			
			return ResponseEntity.ok(token);
		}
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais invalidas");
	}

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



