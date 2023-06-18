package com.poa.tp.dto;

import com.poa.tp.entities.Paciente;
import com.poa.tp.entities.Usuario;


public class PacienteReqDTO {

	private Boolean identidadVerificada;	
	private String dni;

	public PacienteReqDTO(Boolean identidadVerificada, String dni) {
		super();
		this.identidadVerificada = identidadVerificada;
		this.dni = dni;
	}
	
	public Boolean getIdentidadVerificada() {
		return identidadVerificada;
	}

	public void setIdentidadVerificada(Boolean identidadVerificada) {
		this.identidadVerificada = identidadVerificada;
	}
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Paciente toPaciente() {

		Usuario usuario = new Usuario ();
		usuario.setDni(this.dni);
		
		Paciente paciente = new Paciente (
				null,
				this.getIdentidadVerificada(), 
				usuario
				);
		
		return paciente; 
	}
}
