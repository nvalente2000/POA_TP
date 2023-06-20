package com.poa.tp.dto;

import com.poa.tp.entities.Paciente;
import com.poa.tp.entities.Terapista;
import com.poa.tp.entities.Turno;
import com.poa.tp.entities.enums.TipoPatologia;
import com.poa.tp.services.PacienteService;
import com.poa.tp.services.TerapistaService;
import com.poa.tp.services.TurnoDateFormat;


public class TurnoReqSaveDTO {

	private String fechaHoraTurno; 
	private String dniPaciente;
	private TipoPatologia tipoPatologia;  		
	
	public TurnoReqSaveDTO(
			String fechaHoraTurno, 
			TipoPatologia tipoPatologia, 
			String dniPaciente ) {
		super();
		this.fechaHoraTurno = fechaHoraTurno;
		this.tipoPatologia = tipoPatologia;
		this.dniPaciente = dniPaciente;
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

	public String getDniPaciente() {
		return dniPaciente;
	}

	public void setDniPaciente(String dniPaciente) {
		this.dniPaciente = dniPaciente;
	}	
	
	public Turno toTurno(TerapistaService terapistaService , PacienteService pacienteService) {

		Terapista terapista = terapistaService.getTerapistaPorHodario(TurnoDateFormat.toDateTimeFormat (this.fechaHoraTurno));
		Paciente paciente = pacienteService.getOne(dniPaciente); 
				
		Turno turno = new Turno ();
		turno.setPaciente(paciente);
		turno.setTerapista(terapista);
		turno.setFechaHoraTurno(TurnoDateFormat.toDateTimeFormat(this.getFechaHoraTurno()));
		turno.setTipoPatologia(tipoPatologia);
		paciente.getTurnos().add(turno);
		terapista.getTurnos().add(turno);
		
		return turno; 
	}
		
}
