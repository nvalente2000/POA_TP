package com.poa.tp.entities.estado_turno;

import java.io.Serializable;

import com.poa.tp.entities.enums.TipoEstado;
import com.poa.tp.entities.exceptions.EstadoTurnoException;

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
	public void reservar() throws EstadoTurnoException {
		throw new EstadoTurnoException("Warning: Operacion indisponible para el estado Reservado.");
	}

	@Override
	public void cancelar() throws EstadoTurnoException {
		throw new EstadoTurnoException("Warning: Operacion indisponible para el estado Reservado.");
	}

	@Override
	public void confirmarAsistencia() throws EstadoTurnoException {
		throw new EstadoTurnoException("Warning: Operacion indisponible para el estado Reservado.");
	}

	@Override
	public void confirmarAusencia() throws EstadoTurnoException {
		throw new EstadoTurnoException("Warning: Operacion indisponible para el estado Reservado.");
	}

	public TipoEstado getTipoEstado(){
		return TipoEstado.RESERVADO;
	}
	
}
