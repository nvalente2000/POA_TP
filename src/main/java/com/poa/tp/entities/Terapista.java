package com.poa.tp.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.poa.tp.entities.enums.PeriodoAtencion;
import com.poa.tp.entities.exceptions.EntidadesException;
import com.poa.tp.entities.exceptions.PeriodoAtencionException;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table (name = "tb_terapista")
public class Terapista extends RoleUsuario{
	
	private static final long serialVersionUID = 1L;

	@JsonBackReference
	@OneToMany(mappedBy = "terapista")
	private List<Turno> turnos = new ArrayList<Turno>();
		
	private Integer periodoAtencion;
	
	public Terapista() {
		super();
	}
	
	public Terapista(Long id, Usuario usuario, PeriodoAtencion periodoAtencion ) {
		super(id, usuario);
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
			throw new EntidadesException( "Periodo atencion paciente invalido invalido", e);
		}
	}

	public void setPeriodoAtencion(PeriodoAtencion periodoAtencion) {
		this.periodoAtencion = periodoAtencion.getCodigo();
	}
	
}
