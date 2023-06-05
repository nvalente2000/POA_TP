package com.poa.tp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poa.tp.entities.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {


}
