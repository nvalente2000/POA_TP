package com.poa.tp.entities.estado_turno;

import java.io.Serializable;

import com.poa.tp.entities.Turno;
import com.poa.tp.entities.enums.TipoEstadoTurno;
import com.poa.tp.entities.exceptions.TipoEstadoTurnoException;

public class EstadoTurnoPendienteConfirReservado implements EstadoTurno, Serializable {

	private static final long serialVersionUID = 1L;

	private static EstadoTurnoPendienteConfirReservado instance = null; 
	
	private EstadoTurnoPendienteConfirReservado() {}
	
	public static EstadoTurnoPendienteConfirReservado getInstance() {
		if (instance == null) {
			instance = new EstadoTurnoPendienteConfirReservado();
		}
		return instance;
	}
	
	@Override
	public void reservar(Turno turno) throws TipoEstadoTurnoException {
		throw new TipoEstadoTurnoException("No se puede reservar un Turno ya reservado.");
	}

	@Override
	public void cancelar(Turno turno) throws TipoEstadoTurnoException {
		turno.setTipoEstado(EstadoTurnoLibre.getInstance().getTipoEstado()); 		
	}

	@Override
	public void confirmar(Turno turno) throws TipoEstadoTurnoException {
		turno.setTipoEstado(EstadoTurnoConfirmado.getInstance().getTipoEstado()); 
	}

	@Override
	public TipoEstadoTurno getTipoEstado(){
		return TipoEstadoTurno.PENDIENTE_CONFIRMACION;
	}
	
}
