package com.poa.tp.dto;

import java.util.Arrays;
import java.util.List;

import com.poa.tp.entities.Usuario;


public class UsuarioRespDTO {

	private String dni;
	private String email;
	private String nombre; 
	private String apellido;
	private List<String> roles;			
	
	public UsuarioRespDTO( Usuario usuario) {
		super();
		this.dni = usuario.getDni();
		this.email = usuario.getDni();
		this.nombre = usuario.getNombre();
		this.apellido = usuario.getApellido();
		this.roles = Arrays.asList(usuario.getRoles().split(","));
	}
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
}
