package com.poa.tp.entities.estado_turno;

import com.poa.tp.entities.enums.TipoEstadoTurno;
import com.poa.tp.entities.exceptions.TipoEstadoTurnoException;

public interface EstadoTurno {

	public void reservar() throws TipoEstadoTurnoException;
	public void cancelar() throws TipoEstadoTurnoException;
	public void confirmarAsistencia() throws TipoEstadoTurnoException;
	public void confirmarAusencia() throws TipoEstadoTurnoException;
	public TipoEstadoTurno getTipoEstado();
}
