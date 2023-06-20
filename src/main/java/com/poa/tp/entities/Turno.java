package com.poa.tp.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.poa.tp.entities.enums.TipoEstadoTurno;
import com.poa.tp.entities.enums.TipoPatologia;
import com.poa.tp.entities.estado_turno.EstadoTurno;
import com.poa.tp.entities.estado_turno.EstadoTurnoConfirmado;
import com.poa.tp.entities.estado_turno.EstadoTurnoLibre;
import com.poa.tp.entities.estado_turno.EstadoTurnoPendienteConfirReservado;
import com.poa.tp.entities.exceptions.TipoEstadoTurnoException;
import com.poa.tp.entities.exceptions.TipoPagologiaException;
import com.poa.tp.services.exceptions.InvalidEntityDataException;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table (name = "tb_turnos", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = {"fechaHoraTurno"})
		})
public class Turno implements Serializable {

	public final static int DURACION_MIN_TURNO=30;
	
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime fechaHoraTurno; 
	@Value ("${com.turnos.duracion}")
	private Integer duracion;
	private Integer tipoPatologia; // Guardo Int, pero expongo TipoPatologia. Uso conversion interna (por seguridad de no usar directamente valores del enum autogenerados) 
	private Integer codigoEstado;
	
	@JsonManagedReference
	@ManyToOne (fetch=FetchType.EAGER)
	@JoinColumn(name = "paciente_id")
	private Paciente paciente; 
	
	@JsonManagedReference
	@ManyToOne (fetch=FetchType.EAGER)
	@JoinColumn(name = "terapista_id")
	private Terapista terapista; 


	public Turno() {
		super();
		this.duracion = DURACION_MIN_TURNO;
		this.codigoEstado = TipoEstadoTurno.LIBRE.getCodigo(); //Inica libre 
		this.tipoPatologia = TipoPatologia.OTRO.getCodigo();
	}

	public Turno(
			Long id, 
			LocalDateTime fechaHoraTurno, 
			TipoPatologia tipoPatologia, 
			Paciente paciente, 
			Terapista terapista) {
		super();
		this.id = id;
		this.fechaHoraTurno = fechaHoraTurno;
		this.duracion = DURACION_MIN_TURNO;
		this.tipoPatologia = tipoPatologia.getCodigo();
		this.codigoEstado = TipoEstadoTurno.LIBRE.getCodigo(); //Inica libre 
		this.paciente = paciente;
		this.terapista = terapista;
	}
	
	public Turno( Turno turno)  {
		super();
		this.id = turno.getId();
		this.fechaHoraTurno = turno.getFechaHoraTurno();
		this.duracion = turno.getDuracion();
		this.tipoPatologia = turno.getTipoPatologia().getCodigo();
		this.codigoEstado = turno.getEstado().getTipoEstado().getCodigo(); 
		this.paciente = turno.getPaciente();
		this.terapista = turno.getTerapista();
	}	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFechaHoraTurno() {
		return fechaHoraTurno;
	}

	public void setFechaHoraTurno(LocalDateTime fechaHoraTurno) {
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
	
	public TipoPatologia getTipoPatologia() throws InvalidEntityDataException  {
		try {
			return TipoPatologia.toEnum(tipoPatologia);
		} catch (TipoPagologiaException e) {
			throw new InvalidEntityDataException( "Tipo patologia invalida", e);
		}
	}
	
	
	public void setTipoPatologia(TipoPatologia tipoPatologia) {
		this.tipoPatologia = tipoPatologia.getCodigo();
	}
	
	
	public TipoEstadoTurno getTipoEstado() throws InvalidEntityDataException {
		try {
			return TipoEstadoTurno.toEnum(codigoEstado);
			
		} catch (TipoEstadoTurnoException e) {
			throw new InvalidEntityDataException( "Etado turno invalido");
		}
	}
	
	public void setTipoEstado(TipoEstadoTurno tipoEstadoTurno) throws InvalidEntityDataException {
		
		this.codigoEstado = tipoEstadoTurno.getCodigo();
	
	}
	public EstadoTurno getEstado() throws InvalidEntityDataException {
		
		try {
			switch ( TipoEstadoTurno.toEnum(codigoEstado) ) {
				case LIBRE: 
					return EstadoTurnoLibre.getInstance();
				case PENDIENTE_CONFIRMACION: 
					return EstadoTurnoPendienteConfirReservado.getInstance();
				case CONFIRMADA: 
					return EstadoTurnoConfirmado.getInstance();
				default: 
					throw new InvalidEntityDataException( "Etado turno invalido");
			}
			
		} catch (TipoEstadoTurnoException e) {
			throw new InvalidEntityDataException( "Etado turno invalido", e);
		}
		
	}
	
	public void reservar() throws TipoEstadoTurnoException{
		this.getEstado().reservar(this);
	}
	
	public void cancelar() throws TipoEstadoTurnoException{
		this.getEstado().cancelar(this);
	}
	
	public void confirmarAsistencia() throws TipoEstadoTurnoException{
		this.getEstado().confirmar(this);
	}

}
