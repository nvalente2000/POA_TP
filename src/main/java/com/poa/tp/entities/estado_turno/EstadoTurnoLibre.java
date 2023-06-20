package com.poa.tp.entities.estado_turno;

import java.io.Serializable;

import com.poa.tp.entities.Turno;
import com.poa.tp.entities.enums.TipoEstadoTurno;
import com.poa.tp.entities.exceptions.TipoEstadoTurnoException;

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
	public void reservar(Turno turno) throws TipoEstadoTurnoException {
		turno.setTipoEstado(EstadoTurnoPendienteConfirReservado.getInstance().getTipoEstado()); 
	}

	@Override
	public void cancelar(Turno turno) throws TipoEstadoTurnoException {
		throw new TipoEstadoTurnoException("No se puede cancelar un Turno que estado libre.");
	}

	@Override
	public void confirmar(Turno turno) throws TipoEstadoTurnoException {
		throw new TipoEstadoTurnoException("No se puede confirmar un Turno que estado libre.");
	}

	@Override
	public String toString() {
		return "EstadoTurnoLibre [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	@Override
	public TipoEstadoTurno getTipoEstado(){
		return TipoEstadoTurno.LIBRE;
	}

}
