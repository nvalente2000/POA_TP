package com.poa.tp.entities.estado_turno;

import com.poa.tp.entities.Turno;
import com.poa.tp.entities.enums.TipoEstadoTurno;
import com.poa.tp.entities.exceptions.TipoEstadoTurnoException;

public interface EstadoTurno {
	public void reservar(Turno turno) throws TipoEstadoTurnoException;
	public void cancelar(Turno turno) throws TipoEstadoTurnoException;
	public void confirmar(Turno turno) throws TipoEstadoTurnoException;
	public TipoEstadoTurno getTipoEstado();
}
