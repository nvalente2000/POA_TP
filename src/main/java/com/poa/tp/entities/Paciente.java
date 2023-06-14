package com.poa.tp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.poa.tp.dto.PacienteDTO;

import jakarta.persistence.Entity;
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
	@OneToMany(mappedBy = "paciente")
	private List<Turno> turnos = new ArrayList<Turno>();
	
	@JsonManagedReference
	@OneToOne
	@JoinColumn(name = "usuario_id")
	@MapsId
	private Usuario usuarioPaciente;
	
	public Paciente() {
		super();
	}
	
	public Paciente(Boolean identidadVerificada, Usuario usuarioPaciente) {
		super();
		this.identidadVerificada = identidadVerificada;
		this.usuarioPaciente = usuarioPaciente;
	}

	public Paciente(PacienteDTO pacienteDto) {
		super();
		this.identidadVerificada = pacienteDto.getIdentidadVerificada();
		this.usuarioPaciente = pacienteDto.getUsuarioPaciente();
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

	public String getDni() {
		return this.usuarioPaciente.getDni();
	}
	
}
