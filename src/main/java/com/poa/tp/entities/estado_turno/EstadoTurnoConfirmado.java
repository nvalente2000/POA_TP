package com.poa.tp.entities.estado_turno;

import java.io.Serializable;

import com.poa.tp.entities.Turno;
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
	public void reservar(Turno turno) throws TipoEstadoTurnoException {
		throw new TipoEstadoTurnoException("No se puede reservar un Turno que estado Confirmado."); 
	}

	@Override
	public void cancelar(Turno turno) throws TipoEstadoTurnoException {
		throw new TipoEstadoTurnoException("No se puede cancelar un Turno que estado Confirmado.");
	}

	@Override
	public void confirmar(Turno turno) throws TipoEstadoTurnoException {
		throw new TipoEstadoTurnoException("No se puede confirmar un Turno que estado Confirmado.");
	}
	
	@Override
	public TipoEstadoTurno getTipoEstado(){
		return TipoEstadoTurno.CONFIRMADA;
	}
	
}
