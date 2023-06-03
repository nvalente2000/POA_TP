package com.poa.tp.entities.estado_turno;

import java.io.Serializable;

import com.poa.tp.entities.enums.TipoEstado;
import com.poa.tp.entities.exceptions.EstadoTurnoException;

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
	public void reservar() throws EstadoTurnoException {
		throw new EstadoTurnoException("Warning: Operacion indisponible para el estado Confirmado.");
	}

	@Override
	public void cancelar() throws EstadoTurnoException {
		throw new EstadoTurnoException("Warning: Operacion indisponible para el estado Confirmado.");
	}

	@Override
	public void confirmarAsistencia() throws EstadoTurnoException {
		throw new EstadoTurnoException("Warning: Operacion indisponible para el estado Confirmado.");
	}

	@Override
	public void confirmarAusencia() throws EstadoTurnoException {
		throw new EstadoTurnoException("Warning: Operacion indisponible para el estado Confirmado.");
	}
	
	public TipoEstado getTipoEstado(){
		return TipoEstado.CONFIRMADA;
	}
	
}
