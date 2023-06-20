package com.poa.tp.dto;

import com.poa.tp.entities.Terapista;
import com.poa.tp.entities.enums.PeriodoAtencion;


public class TerapistaRespDTO {

	private PeriodoAtencion periodoAtencion;
	private UsuarioRespDTO usuarioTerapia;

	public TerapistaRespDTO(Terapista terapista) {
		super();
		this.periodoAtencion = terapista.getPeriodoAtencion();
		this.usuarioTerapia = new UsuarioRespDTO(terapista.getUsuarioTerapia());
	}

	public PeriodoAtencion getPeriodoAtencion() {
		return periodoAtencion;
	}


	public void setPeriodoAtencion(PeriodoAtencion periodoAtencion) {
		this.periodoAtencion = periodoAtencion;
	}


	public UsuarioRespDTO getUsuarioTerapia() {
		return usuarioTerapia;
	}


	public void setUsuarioTerapia(UsuarioRespDTO usuarioTerapia) {
		this.usuarioTerapia = usuarioTerapia;
	}
	
}
