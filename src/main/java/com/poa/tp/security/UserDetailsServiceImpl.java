package com.poa.tp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.poa.tp.repositories.UsuarioRepository;
import com.poa.tp.security.exceptions.SecurityUserNotFoundException;
import com.poa.tp.services.exceptions.ObjectNotFoundException;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{
    
	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws SecurityUserNotFoundException {
			
		return usuarioRepository.findByDni(username)
				.map(UserDetailsImpl::new)
				.orElseThrow(() -> new ObjectNotFoundException("Usuario login no encontrado"));
	}
	
}
