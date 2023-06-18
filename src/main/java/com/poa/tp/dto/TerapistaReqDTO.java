package com.poa.tp.dto;

import com.poa.tp.entities.Terapista;
import com.poa.tp.entities.Usuario;
import com.poa.tp.entities.enums.PeriodoAtencion;


public class TerapistaReqDTO {

	private PeriodoAtencion periodoAtencion;
	private String dni;
	
	public TerapistaReqDTO(PeriodoAtencion periodoAtencion, String dni) {
		super();
		this.periodoAtencion = periodoAtencion;
		this.dni = dni;
	}

	public PeriodoAtencion getPeriodoAtencion() {
		return periodoAtencion;
	}

	public void setPeriodoAtencion(PeriodoAtencion periodoAtencion) {
		this.periodoAtencion = periodoAtencion;
	}
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Terapista toTerapista() {

		Usuario usuario = new Usuario ();
		usuario.setDni(this.dni);

		Terapista terapista = new Terapista (
				null,
				this.getPeriodoAtencion(), 
				usuario
				);
		
		return terapista; 
	}

}
