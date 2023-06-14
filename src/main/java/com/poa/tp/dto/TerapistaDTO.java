package com.poa.tp.dto;

import java.util.ArrayList;
import java.util.List;

import com.poa.tp.entities.Terapista;
import com.poa.tp.entities.Turno;
import com.poa.tp.entities.Usuario;
import com.poa.tp.entities.enums.PeriodoAtencion;
import com.poa.tp.entities.exceptions.PeriodoAtencionException;


public class TerapistaDTO {

	private PeriodoAtencion periodoAtencion;
	private List<Turno> turnos = new ArrayList<Turno>();
	private Usuario usuarioTerapia;
	
	public TerapistaDTO() {
		super();
	}

	public TerapistaDTO(PeriodoAtencion periodoAtencion, Usuario usuarioTerapia) {
		super();
		this.periodoAtencion = periodoAtencion;
		this.usuarioTerapia = usuarioTerapia;
	}
	
	public TerapistaDTO(Terapista terapissta) throws PeriodoAtencionException {
		super();
		this.periodoAtencion = PeriodoAtencion.toEnum(terapissta.getPeriodoAtencion());
		this.usuarioTerapia = terapissta.getUsuarioTerapia();
	}

	public PeriodoAtencion getPeriodoAtencion() {
		return periodoAtencion;
	}

	public void setPeriodoAtencion(PeriodoAtencion periodoAtencion) {
		this.periodoAtencion = periodoAtencion;
	}

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public Usuario getUsuarioTerapia() {
		return usuarioTerapia;
	}

	public void setUsuarioTerapia(Usuario usuarioTerapia) {
		this.usuarioTerapia = usuarioTerapia;
	}

	@Override
	public String toString() {
		return "TerapistaDTO [periodoAtencion=" + periodoAtencion + ", turnos=" + turnos + ", usuarioTerapia="
				+ usuarioTerapia + "]";
	}
	
}


