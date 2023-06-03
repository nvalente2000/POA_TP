package com.poa.tp.entities;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.poa.tp.entities.enums.TipoEstado;
import com.poa.tp.entities.enums.TipoPatologia;
import com.poa.tp.entities.estado_turno.EstadoTurno;
import com.poa.tp.entities.estado_turno.EstadoTurnoAusente;
import com.poa.tp.entities.estado_turno.EstadoTurnoConfirmado;
import com.poa.tp.entities.estado_turno.EstadoTurnoLibre;
import com.poa.tp.entities.estado_turno.EstadoTurnoReservado;
import com.poa.tp.entities.exceptions.EntidadesException;
import com.poa.tp.entities.exceptions.EstadoTurnoException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table (name = "tb_turno")
public class Turno implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Date fechaHoraTurno; 
	private Integer duracion;
	private Integer tipoPatologia; // Guardo Int, pero expongo TipoPatologia. Uso conversion interna (por seguridad de no usar directamente valores del enum autogenerados) 
	private Integer codigoEstado;
	
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente; 
	
	public Turno() {
		super();
	}

	public Turno(Long id, Date fechaHoraTurno, Integer duracion, TipoPatologia tipoPatologia, Paciente paciente) {
		super();
		this.id = id;
		this.fechaHoraTurno = fechaHoraTurno;
		this.duracion = duracion;
		this.tipoPatologia = tipoPatologia.getCodigo();
		this.codigoEstado = TipoEstado.LIBRE.getCodigo(); //Nace libre el turno
		this.paciente = paciente;
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaHoraTurno() {
		return fechaHoraTurno;
	}

	public void setFechaHoraTurno(Date fechaHoraTurno) {
		this.fechaHoraTurno = fechaHoraTurno;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public TipoPatologia getTipoPatologia() {
		return TipoPatologia.toEnum(tipoPatologia);
	}
	
	public void setTipoPatologia(TipoPatologia tipoPatologia) {
		this.tipoPatologia = tipoPatologia.getCodigo();;
	}

	public EstadoTurno getEstado() throws EntidadesException {
		
		try {
			switch ( TipoEstado.toEnum(codigoEstado) ) {
				case LIBRE: 
					return EstadoTurnoLibre.getInstance();
				case RESERVADO: 
					return EstadoTurnoReservado.getInstance();
				case CONFIRMADA: 
					return EstadoTurnoConfirmado.getInstance();
				case AUSENTE: 
					return EstadoTurnoAusente.getInstance();
				default: 
					throw new EntidadesException( "Id estado invalido");
			}
			
		} catch (EstadoTurnoException e) {
			throw new EntidadesException( "Id estado invalido", e);
		}
		
	}

}
