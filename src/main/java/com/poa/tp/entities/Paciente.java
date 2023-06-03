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
@Table (name = "tb_paciente")
public class Paciente implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String dni; 
	private String apellido; 
	private String hashClave; 
	private Boolean identidadVerificada;
	
	@JsonBackReference
	@OneToMany(mappedBy = "paciente")
	private List<Turno> turnos = new ArrayList<Turno>();
	
	public Paciente() {
		super();
	}

	public Paciente(Long id, String dni, String apellido, String hashClave, Boolean identidadVerificada) {
		super();
		this.id = id;
		this.dni = dni;
		this.apellido = apellido;
		this.hashClave = hashClave;
		this.identidadVerificada = identidadVerificada;
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
	
	public String getHashClave() {
		return hashClave;
	}
	
	public void setHashClave(String hashClave) {
		this.hashClave = hashClave;
	}
	
	public Boolean getIdentidadVerificada() {
		return identidadVerificada;
	}
	
	public void setIdentidadVerificada(Boolean identidadVerificada) {
		this.identidadVerificada = identidadVerificada;
	}

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}
	
}
