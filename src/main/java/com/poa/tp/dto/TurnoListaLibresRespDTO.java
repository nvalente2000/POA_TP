package com.poa.tp.dto;

import com.poa.tp.entities.Turno;
import com.poa.tp.entities.enums.TipoEstadoTurno;
import com.poa.tp.services.TurnoDateFormat;


public class TurnoListaLibresRespDTO {

	private String fechaHoraTurno; 
	private Integer duracion;  
	private TipoEstadoTurno estado;	 
	private String terapistaDni; 		
	
	public TurnoListaLibresRespDTO( Turno turno) {
		super();
		this.fechaHoraTurno = TurnoDateFormat.toStrimgDateTimeFormat(turno.getFechaHoraTurno());
		this.duracion = turno.getDuracion();
		this.estado = turno.getEstado().getTipoEstado();
		this.terapistaDni = turno.getTerapista().getUsuarioTerapia().getDni();
	}

	public String getFechaHoraTurno() {
		return fechaHoraTurno;
	}

	public void setFechaHoraTurno(String fechaHoraTurno) {
		this.fechaHoraTurno = fechaHoraTurno;
	}

	public Integer getDuracion() {
		return duracion;
	}


	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public TipoEstadoTurno getEstado() {
		return estado;
	}

	public void setEstado(TipoEstadoTurno estado) {
		this.estado = estado;
	}

	public String getTerapistaDni() {
		return terapistaDni;
	}

	public void setTerapistaDni(String terapistaDni) {
		this.terapistaDni = terapistaDni;
	}
	
}
