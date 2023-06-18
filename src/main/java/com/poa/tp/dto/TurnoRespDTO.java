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
	private PacienteRespDTO paciente; 
	private TerapistaRespDTO terapista; 		
	
	
	public TurnoRespDTO( Turno turno) {
		super();
		this.fechaHoraTurno = TurnoDateFormat.toStromgFormat(turno.getFechaHoraTurno());
		this.duracion = turno.getDuracion();
		this.tipoPatologia = turno.getTipoPatologia();
		this.estado = turno.getEstado().getTipoEstado();
		this.paciente = new PacienteRespDTO(turno.getPaciente());
		this.terapista = new TerapistaRespDTO(turno.getTerapista());
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

	public PacienteRespDTO getPaciente() {
		return paciente;
	}

	public void setPaciente(PacienteRespDTO paciente) {
		this.paciente = paciente;
	}

	public TerapistaRespDTO getTerapista() {
		return terapista;
	}

	public void setTerapista(TerapistaRespDTO terapista) {
		this.terapista = terapista;
	}
	
}
