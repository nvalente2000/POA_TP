package com.poa.tp.entities.estado_turno;

import com.poa.tp.entities.enums.TipoEstado;
import com.poa.tp.entities.exceptions.EstadoTurnoException;

public interface EstadoTurno {

	public void reservar() throws EstadoTurnoException;
	public void cancelar() throws EstadoTurnoException;
	public void confirmarAsistencia() throws EstadoTurnoException;
	public void confirmarAusencia() throws EstadoTurnoException;
	public TipoEstado getTipoEstado();
}
