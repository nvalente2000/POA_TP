package com.poa.tp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.poa.tp.entities.enums.PeriodoAtencion;
import com.poa.tp.entities.exceptions.EntidadesException;
import com.poa.tp.entities.exceptions.PeriodoAtencionException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table (name = "terapista")
public class Terapista implements Serializable {
			
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Integer periodoAtencion;
	
	@JsonBackReference
	@OneToMany(mappedBy = "terapista")
	private List<Turno> turnos = new ArrayList<Turno>();
		
	
	public Terapista() {
		super();
	}
	
	public Terapista(Long id, PeriodoAtencion periodoAtencion ) {
		super();
		this.id = id;
		this.periodoAtencion = periodoAtencion.getCodigo();
	}

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}
	
	public PeriodoAtencion getPeriodoAtencion() throws EntidadesException  {
		try {
			return PeriodoAtencion.toEnum(this.periodoAtencion);
		} catch (PeriodoAtencionException e) {
			throw new EntidadesException( "Periodo atencion paciente invalido", e);
		}
	}

	public void setPeriodoAtencion(PeriodoAtencion periodoAtencion) {
		this.periodoAtencion = periodoAtencion.getCodigo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
