package com.poa.tp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table (name = "tb_administrador")
public class Administrador extends RoleUsuario{
	
	private static final long serialVersionUID = 1L;

	public Administrador() {
		super();
	}
	
	public Administrador(Long id, Usuario usuario ) {
		super(id, usuario);
	}
	
}
