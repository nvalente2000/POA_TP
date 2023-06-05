package com.poa.tp.entities.estado_turno;

import java.io.Serializable;

import com.poa.tp.entities.enums.TipoEstadoTurno;
import com.poa.tp.entities.exceptions.TipoEstadoTurnoException;

public class EstadoTurnoReservado implements EstadoTurno, Serializable {

	private static final long serialVersionUID = 1L;

	private static EstadoTurnoReservado instance = null; 
	
	private EstadoTurnoReservado() {}
	
	public static EstadoTurnoReservado getInstance() {
		if (instance == null) {
			instance = new EstadoTurnoReservado();
		}
		return instance;
	}
	
	@Override
	public void reservar() throws TipoEstadoTurnoException {
		throw new TipoEstadoTurnoException("Warning: Operacion indisponible para el estado Reservado.");
	}

	@Override
	public void cancelar() throws TipoEstadoTurnoException {
		throw new TipoEstadoTurnoException("Warning: Operacion indisponible para el estado Reservado.");
	}

	@Override
	public void confirmarAsistencia() throws TipoEstadoTurnoException {
		throw new TipoEstadoTurnoException("Warning: Operacion indisponible para el estado Reservado.");
	}

	@Override
	public void confirmarAusencia() throws TipoEstadoTurnoException {
		throw new TipoEstadoTurnoException("Warning: Operacion indisponible para el estado Reservado.");
	}

	@Override
	public TipoEstadoTurno getTipoEstado(){
		return TipoEstadoTurno.RESERVADO;
	}
	
}
