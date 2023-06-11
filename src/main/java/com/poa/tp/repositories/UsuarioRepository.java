package com.poa.tp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.poa.tp.entities.Usuario;

@EnableJpaRepositories
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional <Usuario> findByDni (String dni);
	Optional <Usuario> findByEmail (String email);
	Usuario deleteByDni (String dni);
	Usuario deleteByEmail (String email);	
	
}
