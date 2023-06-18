package com.poa.tp.dto;

import com.poa.tp.entities.Paciente;
import com.poa.tp.entities.Terapista;
import com.poa.tp.entities.Turno;
import com.poa.tp.entities.Usuario;
import com.poa.tp.entities.enums.PeriodoAtencion;
import com.poa.tp.entities.enums.TipoPatologia;
import com.poa.tp.services.TurnoDateFormat;


public class TurnoReqDTO {

	private String fechaHoraTurno; 
	private String dniPaciente;
	private String dniTerapista;
	private TipoPatologia tipoPatologia;  		
	
	public TurnoReqDTO(
			String fechaHoraTurno, 
			TipoPatologia tipoPatologia, 
			String dniPaciente, 
			String dniTerapista) {
		super();
		this.fechaHoraTurno = fechaHoraTurno;
		this.tipoPatologia = tipoPatologia;
		this.dniPaciente = dniPaciente;
		this.dniTerapista = dniTerapista;
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


	public String getDniTerapista() {
		return dniTerapista;
	}


	public void setDniTerapista(String dniTerapista) {
		this.dniTerapista = dniTerapista;
	}
	
	public Turno toTurno() {

		Usuario usuarioPaciente = new Usuario ();
		Usuario usuarioTerapista = new Usuario ();
		Terapista terapista = new Terapista(null, PeriodoAtencion.TURNO_NO_ASIGNADO, usuarioTerapista);
		Paciente paciente = new Paciente (null, null, usuarioPaciente);
		
		usuarioPaciente.setDni(dniPaciente);
		usuarioPaciente.setPaciente(paciente);
		usuarioTerapista.setDni(dniTerapista);
		usuarioTerapista.setTerapista(terapista);
		
		Turno turno = new Turno ();
		turno.setPaciente(paciente);
		turno.setTerapista(terapista);
		turno.setFechaHoraTurno(TurnoDateFormat.toDateForma(this.getFechaHoraTurno()));
		turno.setTipoPatologia(tipoPatologia);
		paciente.getTurnos().add(turno);
		terapista.getTurnos().add(turno);
		
		return turno; 
	}
		
}
