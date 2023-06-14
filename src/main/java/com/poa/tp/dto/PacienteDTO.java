package com.poa.tp.dto;

import java.util.ArrayList;
import java.util.List;

import com.poa.tp.entities.Paciente;
import com.poa.tp.entities.Turno;
import com.poa.tp.entities.Usuario;


public class PacienteDTO {

	private Boolean identidadVerificada;
	private List<Turno> turnos = new ArrayList<Turno>();	
	private Usuario usuarioPaciente;
	
	public PacienteDTO() {
		super();
	}
	
	public PacienteDTO(Boolean identidadVerificada, List<Turno> turnos, Usuario usuarioPaciente) {
		super();
		this.identidadVerificada = identidadVerificada;
		this.turnos = turnos;
		this.usuarioPaciente = usuarioPaciente;
	}

	public PacienteDTO(Paciente paciente) {
		super();
		this.identidadVerificada = paciente.getIdentidadVerificada();
		this.turnos = paciente.getTurnos();
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

	public Usuario getUsuarioPaciente() {
		return usuarioPaciente;
	}

	public void setUsuarioPaciente(Usuario usuarioPaciente) {
		this.usuarioPaciente = usuarioPaciente;
	}

	@Override
	public String toString() {
		return "PacienteDTO [identidadVerificada=" + identidadVerificada + ", turnos=" + turnos + ", usuarioPaciente="
				+ usuarioPaciente + "]";
	}
		
}


