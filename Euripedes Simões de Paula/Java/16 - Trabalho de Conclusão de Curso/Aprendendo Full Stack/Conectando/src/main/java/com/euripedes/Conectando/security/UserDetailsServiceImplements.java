package com.euripedes.Conectando.security;


import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.euripedes.Conectando.model.Usuario;
import com.euripedes.Conectando.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImplements implements UserDetailsService{
	
	@Autowired
	private  UsuarioRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {

		/*Optional<Usuario>*/
		Usuario user = repository.findByUsuario(usuario)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado." + usuario));

		return new org.springframework.security.core.userdetails.User(
				user.getUsuario(),
				user.getSenha(),
				new ArrayList<>()
				);
		
//		if (optional.isPresent()) {
//			return new UserDetailsImplements(optional.get());
//		} else {
//			throw new UsernameNotFoundException("Usuario não existe");
//		}
	}
}