package com.poa.tp.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
			
	@OneToOne (cascade=CascadeType.ALL, mappedBy="usuarioPaciente", fetch = FetchType.EAGER) 
	private Paciente paciente; 	
	
	@OneToOne (cascade=CascadeType.ALL, mappedBy="usuarioTerapia", fetch = FetchType.EAGER) 
	private Terapista terapista; 	

	
	public Usuario() {
		super();
	}
	
	public Usuario(Long id, 
			String dni, 
			String email, 
			String nombre, 
			String apellido, 
			String password, 
			String roles) {
		
		super();
		this.id = id;
		this.dni = dni;
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.roles = roles;
	}
	
	public Usuario(Long id, 
			String dni, 
			String email, 
			String nombre, 
			String apellido, 
			String password, 
			List<String> roles) {
		
		super();
		this.id = id;
		this.dni = dni;
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.roles = String.join(",", roles);;
	}

	public Usuario(Usuario usuario) {
		super();
		this.id=usuario.getId();
		this.dni = usuario.getDni();
		this.email = usuario.getEmail();
		this.nombre = usuario.getNombre();
		this.apellido = usuario.getApellido();
		this.password = usuario.getPassword();
		this.roles = usuario.getRoles();
		this.paciente = usuario.getPaciente();
		this.terapista = usuario.getTerapista();
	}
/*
	public Usuario(UsuarioResponseDTO usuarioRequestDto) {
		super();
		this.dni = usuarioRequestDto.getDni();
		this.email = usuarioRequestDto.getEmail();
		this.nombre = usuarioRequestDto.getNombre();
		this.apellido = usuarioRequestDto.getApellido();
		this.roles = String.join(",", usuarioRequestDto.getRoles());
	}	

	public Usuario(UsuarioRequestDTO usuarioRequestDto) {
		super();
		this.dni = usuarioRequestDto.getDni();
	}	
	*/
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

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Terapista getTerapista() {
		return terapista;
	}

	public void setTerapista(Terapista terapista) {
		this.terapista = terapista;
	}	
	
}
