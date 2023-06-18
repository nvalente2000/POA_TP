package com.poa.tp.dto;

import com.poa.tp.entities.Paciente;


public class PacienteRespDTO {

	private Boolean identidadVerificada;	
	private UsuarioRespDTO usuarioPaciente;
		
	public PacienteRespDTO(Paciente paciente) {
		super();
		this.identidadVerificada = paciente.getIdentidadVerificada();
		this.usuarioPaciente = new UsuarioRespDTO(paciente.getUsuarioPaciente());
	}
	
	public Boolean getIdentidadVerificada() {
		return identidadVerificada;
	}

	public void setIdentidadVerificada(Boolean identidadVerificada) {
		this.identidadVerificada = identidadVerificada;
	}

	public UsuarioRespDTO getUsuarioPaciente() {
		return usuarioPaciente;
	}

	public void setUsuarioPaciente(UsuarioRespDTO usuarioPaciente) {
		this.usuarioPaciente = usuarioPaciente;
	}
}
