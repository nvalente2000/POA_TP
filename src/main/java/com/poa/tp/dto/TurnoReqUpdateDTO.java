package com.poa.tp.dto;

import com.poa.tp.entities.Turno;
import com.poa.tp.entities.enums.TipoPatologia;
import com.poa.tp.services.TurnoService;


public class TurnoReqUpdateDTO {

	private String fechaHoraTurno; 
	private TipoPatologia tipoPatologia;  		
	
	public TurnoReqUpdateDTO(
			String fechaHoraTurno, 
			TipoPatologia tipoPatologia, 
			String dniPaciente ) {
		super();
		this.fechaHoraTurno = fechaHoraTurno;
		this.tipoPatologia = tipoPatologia;
	}

	public String getFechaHoraTurno() {
		return fechaHoraTurno;
	}

	public void setFechaHoraTurno(String fechaHoraTurno) {
		this.fechaHoraTurno = fechaHoraTurno;
	}

	public TipoPatologia getTipoPatologia() {
		return tipoPatologia;
	}

	public void setTipoPatologia(TipoPatologia tipoPatologia) {
		this.tipoPatologia = tipoPatologia;
	}
	
	public Turno toTurno(TurnoService turnoService ) {
		
		Turno turnoOrig = turnoService.getOne(fechaHoraTurno);
		
		turnoOrig.setTipoPatologia(tipoPatologia);
		
		return turnoOrig; 
	}		
}
