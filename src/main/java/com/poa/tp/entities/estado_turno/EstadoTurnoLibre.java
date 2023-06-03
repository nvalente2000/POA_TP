package com.poa.tp.entities.estado_turno;

import java.io.Serializable;

import com.poa.tp.entities.enums.TipoEstado;
import com.poa.tp.entities.exceptions.EstadoTurnoException;

public class EstadoTurnoLibre implements EstadoTurno, Serializable {

	private static final long serialVersionUID = 1L;

	private static EstadoTurnoLibre instance = null; 
	
	private EstadoTurnoLibre() {}
	
	public static EstadoTurnoLibre getInstance() {
		if (instance == null) {
			instance = new EstadoTurnoLibre();
		}
		return instance;
	}
	
	@Override
	public void reservar() throws EstadoTurnoException {
		throw new EstadoTurnoException("Warning: Operacion indisponible para el estado Libre.");
	}

	@Override
	public void cancelar() throws EstadoTurnoException {
		throw new EstadoTurnoException("Warning: Operacion indisponible para el estado Libre.");
	}

	@Override
	public void confirmarAsistencia() throws EstadoTurnoException {
		throw new EstadoTurnoException("Warning: Operacion indisponible para el estado Libre.");
	}

	@Override
	public void confirmarAusencia() throws EstadoTurnoException {
		throw new EstadoTurnoException("Warning: Operacion indisponible para el estado Libre.");
	}

	@Override
	public String toString() {
		return "EstadoTurnoLibre [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	public TipoEstado getTipoEstado(){
		return TipoEstado.LIBRE;
	}


}
