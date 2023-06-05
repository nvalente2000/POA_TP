package com.poa.tp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "tb_usuario")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String dni; 
	private String apellido; 
	private String clave; 
	
	@JsonBackReference
	@OneToMany(mappedBy = "usuario")
	private List<RoleUsuario> roles = new ArrayList<RoleUsuario>();
	
	public Usuario() {
		super();
	}

	public Usuario(Long id, String dni, String apellido, String hashClave) {
		super();
		this.id = id;
		this.dni = dni;
		this.apellido = apellido;
		this.clave = hashClave;
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
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getClave() {
		return clave;
	}
	
	public void setClave(String hashClave) {
		this.clave = hashClave;
	}

	public List<RoleUsuario> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleUsuario> roles) {
		this.roles = roles;
	}
	
}
