package com.poa.tp.entities;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.poa.tp.entities.enums.TipoPatologia;

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
	
	@JsonBackReference
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
}
