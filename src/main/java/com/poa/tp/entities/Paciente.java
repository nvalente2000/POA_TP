package com.poa.tp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table (name = "tb_pacientes")
public class Paciente implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id 
	private Long id;
	private Boolean identidadVerificada;
	
	@JsonBackReference
	@OneToMany(mappedBy = "paciente", fetch = FetchType.EAGER)
	private List<Turno> turnos = new ArrayList<Turno>();
	
	@OneToOne (fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id")
	@MapsId
	private Usuario usuarioPaciente;
	
	public Paciente() {
		super();
	}
	
	public Paciente(Long id, Boolean identidadVerificada, Usuario usuarioPaciente) {
		super();
		this.id = id; 
		this.identidadVerificada = identidadVerificada;
		this.usuarioPaciente = usuarioPaciente;
	}

	public Paciente( Paciente paciente) {
		super();
		this.id = paciente.getId();
		this.identidadVerificada = paciente.getIdentidadVerificada();
		this.usuarioPaciente = paciente.getUsuarioPaciente();
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuarioPaciente() {
		return usuarioPaciente;
	}
	
	public void setUsuarioPaciente(Usuario usuarioPaciente) {
		this.usuarioPaciente = usuarioPaciente;
	}
	
}
