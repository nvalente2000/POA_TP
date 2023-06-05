package com.poa.tp.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table (name = "tb_paciente")
public class Paciente extends RoleUsuario{
	
	private static final long serialVersionUID = 1L;

	private Boolean identidadVerificada;
	
	@JsonBackReference
	@OneToMany(mappedBy = "paciente")
	private List<Turno> turnos = new ArrayList<Turno>();
		
	public Paciente() {
		super();
	}
	
	public Paciente(Long id,boolean identidadVerificada, Usuario usuario ) {
		super(id, usuario);
		this.identidadVerificada = identidadVerificada;
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
