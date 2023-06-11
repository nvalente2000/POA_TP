package com.poa.tp.dto;

import java.util.List;

public class UsuarioDTO {

	private Long id;
	private String dni;
	private String email;
	private String nombre; 
	private String apellido;
	private String password;
	private List<String> roles;
	
	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(Long id, String dni, String email, String nombre, String apellido, String password, List<String> roles) {
		super();
		this.id = id;
		this.dni = dni;
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UsuarioDTO [id=" + id + ", dni=" + dni + ", email=" + email + ", nombre=" + nombre + ", apellido="
				+ apellido + ", password=" + password + ", roles=" + roles + "]";
	}
}

