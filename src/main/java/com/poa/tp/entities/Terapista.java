package com.poa.tp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.poa.tp.entities.enums.PeriodoAtencion;
import com.poa.tp.entities.exceptions.PeriodoAtencionException;
import com.poa.tp.services.exceptions.InvalidEntityDataException;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table (name = "tb_terapistas",
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = {"periodoAtencion"})
		})
public class Terapista implements Serializable {
			
	private static final long serialVersionUID = 1L;

	@Id 
	private Long id;
	private Integer periodoAtencion;
	
	@JsonBackReference
	@OneToMany(mappedBy = "terapista", fetch = FetchType.EAGER)
	private List<Turno> turnos = new ArrayList<Turno>();
	
	@OneToOne (fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id")
	@MapsId
	private Usuario usuarioTerapia;
	
	public Terapista() {
		super();
	}
	
	public Terapista(Long id, PeriodoAtencion periodoAtencion, Usuario usuarioTerapia) {
		super();
		this.id = id; 
		this.periodoAtencion = periodoAtencion.getCodigo();
		this.usuarioTerapia = usuarioTerapia;
	}
	
	public Terapista(Terapista terapista) throws InvalidEntityDataException {
		super();
		this.id = terapista.id; 
		this.periodoAtencion = terapista.getPeriodoAtencion().getCodigo();
		this.usuarioTerapia = terapista.getUsuarioTerapia();
	}

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}	

	public PeriodoAtencion getPeriodoAtencion() throws InvalidEntityDataException {
		try {
			return PeriodoAtencion.toEnum(periodoAtencion);
		} catch (PeriodoAtencionException e) {
			throw new InvalidEntityDataException( "Periodo de atencion invalida", e);
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

	public Usuario getUsuarioTerapia() {
		return usuarioTerapia;
	}

	public void setUsuarioTerapia(Usuario usuarioTerapia) {
		this.usuarioTerapia = usuarioTerapia;
	}

}
