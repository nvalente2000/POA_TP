package com.poa.tp.entities.estado_turno;

import java.io.Serializable;

import com.poa.tp.entities.enums.TipoEstadoTurno;
import com.poa.tp.entities.exceptions.TipoEstadoTurnoException;

public class EstadoTurnoAusente implements EstadoTurno , Serializable {

	private static final long serialVersionUID = 1L;

	private static EstadoTurnoAusente instance = null; 
	
	private EstadoTurnoAusente() {}
	
	public static EstadoTurnoAusente getInstance() {
		if (instance == null) {
			instance = new EstadoTurnoAusente();
		}
		return instance;
	}
	
	@Override
	public void reservar() throws TipoEstadoTurnoException {
		throw new TipoEstadoTurnoException("Warning: Operacion indisponible para el estado Ausente.");
	}

	@Override
	public void cancelar() throws TipoEstadoTurnoException {
		throw new TipoEstadoTurnoException("Warning: Operacion indisponible para el estado Ausente.");
	}

	@Override
	public void confirmarAsistencia() throws TipoEstadoTurnoException {
		throw new TipoEstadoTurnoException("Warning: Operacion indisponible para el estado Ausente.");
	}

	@Override
	public void confirmarAusencia() throws TipoEstadoTurnoException {
		throw new TipoEstadoTurnoException("Warning: Operacion indisponible para el estado Ausente.");
	}
	
	@Override
	public TipoEstadoTurno getTipoEstado(){
		return TipoEstadoTurno.AUSENTE;
	}
	
}
