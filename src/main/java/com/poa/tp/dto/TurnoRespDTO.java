package com.poa.tp.dto;

import com.poa.tp.entities.Turno;
import com.poa.tp.entities.enums.TipoEstadoTurno;
import com.poa.tp.entities.enums.TipoPatologia;
import com.poa.tp.services.TurnoDateFormat;


public class TurnoRespDTO {

	private String fechaHoraTurno; 
	private Integer duracion;
	private TipoPatologia tipoPatologia;  
	private TipoEstadoTurno estado;	
	private String dniPaciente; 
	private String dniTerapista; 		
	
	
	public TurnoRespDTO( Turno turno) {
		super();
		this.fechaHoraTurno = TurnoDateFormat.toStrimgDateTimeFormat(turno.getFechaHoraTurno());
		this.duracion = turno.getDuracion();
		this.tipoPatologia = turno.getTipoPatologia();
		this.estado = turno.getEstado().getTipoEstado();
		this.dniPaciente = turno.getPaciente().getUsuarioPaciente().getDni();
		this.dniTerapista = turno.getTerapista().getUsuarioTerapia().getDni();
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

	public TipoPatologia getTipoPatologia() {
		return tipoPatologia;
	}

	public void setTipoPatologia(TipoPatologia tipoPatologia) {
		this.tipoPatologia = tipoPatologia;
	}

	public TipoEstadoTurno getEstado() {
		return estado;
	}

	public void setEstado(TipoEstadoTurno estado) {
		this.estado = estado;
	}

	public String getDniPaciente() {
		return dniPaciente;
	}

	public void setDniPaciente(String dniPaciente) {
		this.dniPaciente = dniPaciente;
	}

	public String getDniTerapista() {
		return dniTerapista;
	}

	public void setDniTerapista(String dniTerapista) {
		this.dniTerapista = dniTerapista;
	}
	
}
