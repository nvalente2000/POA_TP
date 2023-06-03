package com.poa.tp.entities.estado_turno;

import java.io.Serializable;

import com.poa.tp.entities.enums.TipoEstado;
import com.poa.tp.entities.exceptions.EstadoTurnoException;

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
	public void reservar() throws EstadoTurnoException {
		throw new EstadoTurnoException("Warning: Operacion indisponible para el estado Ausente.");
	}

	@Override
	public void cancelar() throws EstadoTurnoException {
		throw new EstadoTurnoException("Warning: Operacion indisponible para el estado Ausente.");
	}

	@Override
	public void confirmarAsistencia() throws EstadoTurnoException {
		throw new EstadoTurnoException("Warning: Operacion indisponible para el estado Ausente.");
	}

	@Override
	public void confirmarAusencia() throws EstadoTurnoException {
		throw new EstadoTurnoException("Warning: Operacion indisponible para el estado Ausente.");
	}
	
	public TipoEstado getTipoEstado(){
		return TipoEstado.AUSENTE;
	}
	
}
