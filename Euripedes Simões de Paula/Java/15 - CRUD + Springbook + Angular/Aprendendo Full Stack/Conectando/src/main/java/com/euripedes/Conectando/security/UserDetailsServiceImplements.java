package com.euripedes.Conectando.security;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.euripedes.Conectando.model.Usuario;
import com.euripedes.Conectando.repository.UsuarioRepository;
//
//@Service
//public class UserDetailsServiceImplements implements UserDetailsService{
//	
//	private @Autowired UsuarioRepository repository;
//
//	@Override
//	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
//
//		Optional<Usuario> optional = repository.findByUsuario(usuario);
//
//		if (optional.isPresent()) {
//			return new UserDetailsImplements(optional.get());
//		} else {
//			throw new UsernameNotFoundException("Usuario não existe");
//		}
//	}
//}