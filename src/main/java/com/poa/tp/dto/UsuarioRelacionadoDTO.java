package com.poa.tp.dto;

import com.poa.tp.entities.Usuario;

public class UsuarioRelacionadoDTO {

	private String dni;
			
	public UsuarioRelacionadoDTO(String dni) {
		super();
		this.dni = dni;
	}

	public UsuarioRelacionadoDTO(Usuario usuarioPaciente) {
		this.setDni(usuarioPaciente.getDni()); 
	}	

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}	
	
	public Usuario toUsuario() {

		Usuario usuario = new Usuario ();
		usuario.setDni(this.dni);
		
		return usuario; 
	}
	
}
