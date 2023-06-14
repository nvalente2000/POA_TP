package com.poa.tp.entities;

import java.io.Serializable;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.poa.tp.dto.UsuarioDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table (name = "tb_usuarios", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = {"dni"}),
			@UniqueConstraint(columnNames = {"email"})
		})
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String dni;
	private String email;
	private String nombre; 
	private String apellido;
	private String password;
	private String roles;
			
	public Usuario() {
		super();
	}

	public Usuario(String dni, String email, String nombre, String apellido, String password, String roles) {
		super();
		this.dni = dni;
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.roles = roles;
	}
	
	public Usuario(UsuarioDTO usuarioDto, PasswordEncoder passwordEncoder) {
		super();
		this.dni = usuarioDto.getDni();
		this.email = usuarioDto.getEmail();
		this.nombre = usuarioDto.getNombre();
		this.apellido = usuarioDto.getApellido();
		this.password = passwordEncoder.encode(usuarioDto.getPassword()) ;
		this.roles = String.join(",", usuarioDto.getRoles());
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

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", dni=" + dni + ", email=" + email + ", nombre=" + nombre + ", apellido="
				+ apellido + ", password=" + password + ", roles=" + roles + "]";
	}
	
}
