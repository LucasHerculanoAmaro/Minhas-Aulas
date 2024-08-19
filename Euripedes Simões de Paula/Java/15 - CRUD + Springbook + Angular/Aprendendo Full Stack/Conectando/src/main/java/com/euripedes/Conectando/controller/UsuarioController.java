package com.euripedes.Conectando.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.euripedes.Conectando.model.Usuario;
import com.euripedes.Conectando.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
    private UsuarioRepository usuarioRepository;
	
//	Unidade de Teste
	static {
		System.out.println("Controlador Usuário carregado");
	}

    @GetMapping("/test")
    public String testEndpoint() {
        return "Hello, Spring!";
    }
    
//  Método GET
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return ResponseEntity.ok(usuario);
    }

//  Método POST
    @PostMapping("/cadastrar")
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

//  Método PUT
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(usuarioDetails.getNome());
        usuario.setIdade(usuarioDetails.getIdade());
        usuario.setTurma(usuarioDetails.getTurma());
        usuario.setProfissao(usuarioDetails.getProfissao());

        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.ok(updatedUsuario);
    }

//  Método DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuarioRepository.delete(usuario);
        return ResponseEntity.noContent().build();
    }
}
