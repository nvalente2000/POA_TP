package com.poa.tp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.poa.tp.dto.TerapistaDTO;
import com.poa.tp.entities.enums.PeriodoAtencion;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table (name = "tb_terapistas")
public class Terapista implements Serializable {
			
	private static final long serialVersionUID = 1L;

	@Id 
	private Long id;
	private Integer periodoAtencion;
	
	@JsonBackReference
	@OneToMany(mappedBy = "terapista")
	private List<Turno> turnos = new ArrayList<Turno>();
	
	@JsonManagedReference
	@OneToOne
	@JoinColumn(name = "usuario_id")
	@MapsId
	private Usuario usuarioTerapia;
	
	public Terapista() {
		super();
	}
	
	public Terapista(PeriodoAtencion periodoAtencion, Usuario usuarioTerapia) {
		super();
		this.periodoAtencion = periodoAtencion.getCodigo();
		this.usuarioTerapia = usuarioTerapia;
	}
	
	public Terapista( TerapistaDTO terapistaDto) {
		super();
		this.periodoAtencion = terapistaDto.getPeriodoAtencion().getCodigo();
		this.usuarioTerapia = terapistaDto.getUsuarioTerapia();
	}
	

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}	

	public Integer getPeriodoAtencion() {
		return periodoAtencion;
	}

	public void setPeriodoAtencion(Integer periodoAtencion) {
		this.periodoAtencion = periodoAtencion;
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

	public String getDni() {
		return this.usuarioTerapia.getDni();
	}
}
