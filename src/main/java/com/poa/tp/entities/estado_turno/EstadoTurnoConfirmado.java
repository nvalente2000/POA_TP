package com.poa.tp.entities.estado_turno;

import java.io.Serializable;

import com.poa.tp.entities.enums.TipoEstadoTurno;
import com.poa.tp.entities.exceptions.TipoEstadoTurnoException;

public class EstadoTurnoConfirmado implements EstadoTurno, Serializable {

	private static final long serialVersionUID = 1L;

	private static EstadoTurnoConfirmado instance = null; 
	
	private EstadoTurnoConfirmado() {}
	
	public static EstadoTurnoConfirmado getInstance() {
		if (instance == null) {
			instance = new EstadoTurnoConfirmado();
		}
		return instance;
	}
	
	@Override
	public void reservar() throws TipoEstadoTurnoException {
		throw new TipoEstadoTurnoException("Warning: Operacion indisponible para el estado Confirmado.");
	}

	@Override
	public void cancelar() throws TipoEstadoTurnoException {
		throw new TipoEstadoTurnoException("Warning: Operacion indisponible para el estado Confirmado.");
	}

	@Override
	public void confirmarAsistencia() throws TipoEstadoTurnoException {
		throw new TipoEstadoTurnoException("Warning: Operacion indisponible para el estado Confirmado.");
	}

	@Override
	public void confirmarAusencia() throws TipoEstadoTurnoException {
		throw new TipoEstadoTurnoException("Warning: Operacion indisponible para el estado Confirmado.");
	}
	
	@Override
	public TipoEstadoTurno getTipoEstado(){
		return TipoEstadoTurno.CONFIRMADA;
	}
	
}
