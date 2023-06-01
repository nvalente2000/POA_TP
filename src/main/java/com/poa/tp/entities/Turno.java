package com.poa.tp.entities;

import java.io.Serializable;
import java.util.Date;

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
	
	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente; 
	
	public Turno() {
		super();
	}

	public Turno(Long id, Date fechaHoraTurno, Integer duracion) {
		super();
		this.id = id;
		this.fechaHoraTurno = fechaHoraTurno;
		this.duracion = duracion;
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
	
}
