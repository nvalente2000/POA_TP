package com.poa.tp.entities;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.poa.tp.entities.enums.TipoEstadoTurno;
import com.poa.tp.entities.enums.TipoPatologia;
import com.poa.tp.entities.estado_turno.EstadoTurno;
import com.poa.tp.entities.estado_turno.EstadoTurnoAusente;
import com.poa.tp.entities.estado_turno.EstadoTurnoConfirmado;
import com.poa.tp.entities.estado_turno.EstadoTurnoLibre;
import com.poa.tp.entities.estado_turno.EstadoTurnoReservado;
import com.poa.tp.entities.exceptions.EntidadesException;
import com.poa.tp.entities.exceptions.TipoEstadoTurnoException;
import com.poa.tp.entities.exceptions.TipoPagologiaException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table (name = "tb_turnos")
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
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "terapista_id")
	private Terapista terapista; 


	public Turno() {
		super();
	}

	public Turno(Long id, Date fechaHoraTurno, Integer duracion, TipoPatologia tipoPatologia, Paciente paciente, Terapista terapista) {
		super();
		this.id = id;
		this.fechaHoraTurno = fechaHoraTurno;
		this.duracion = duracion;
		this.tipoPatologia = tipoPatologia.getCodigo();
		this.codigoEstado = TipoEstadoTurno.LIBRE.getCodigo(); //Inica libre 
		this.paciente = paciente;
		this.terapista = terapista;
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

	public Terapista getTerapista() {
		return terapista;
	}

	public void setTerapista(Terapista terapista) {
		this.terapista = terapista;
	}
	
	public TipoPatologia getTipoPatologia() throws EntidadesException  {
		try {
			return TipoPatologia.toEnum(tipoPatologia);
		} catch (TipoPagologiaException e) {
			throw new EntidadesException( "Tipo patologia invalida", e);
		}
	}
	
	public void setTipoPatologia(TipoPatologia tipoPatologia) {
		this.tipoPatologia = tipoPatologia.getCodigo();
	}
	
	public EstadoTurno getEstado() throws EntidadesException {
		
		try {
			switch ( TipoEstadoTurno.toEnum(codigoEstado) ) {
				case LIBRE: 
					return EstadoTurnoLibre.getInstance();
				case RESERVADO: 
					return EstadoTurnoReservado.getInstance();
				case CONFIRMADA: 
					return EstadoTurnoConfirmado.getInstance();
				case AUSENTE: 
					return EstadoTurnoAusente.getInstance();
				default: 
					throw new EntidadesException( "Etado turno invalido");
			}
			
		} catch (TipoEstadoTurnoException e) {
			throw new EntidadesException( "Etado turno invalido", e);
		}
		
	}

}
