package com.poa.tp.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poa.tp.entities.Paciente;
import com.poa.tp.entities.Terapista;
import com.poa.tp.entities.Turno;
import com.poa.tp.entities.enums.PeriodoAtencion;
import com.poa.tp.entities.enums.TipoEstadoTurno;
import com.poa.tp.entities.exceptions.TipoEstadoTurnoException;
import com.poa.tp.services.exceptions.TurnoServiceException;


@Service
public class ClinicaService  { 
		
	public final static int QTDE_DIAS_PRESENTA_TURNOS_LIBRES=1;
	public final static int MAX_QTDE_TURNOS_AGENDADOS_PACIENTE=2;
	
	@Autowired 
	private TerapistaService terapistaService;
		
	@Autowired 
	private TurnoService turnoService;
	
	
	public List<Turno> getTurnosLibres() {
		
		String fechaDesde = TurnoDateFormat.getCurrentDate();
		String fechaHasta = TurnoDateFormat.getCurrentDatePlusDays(QTDE_DIAS_PRESENTA_TURNOS_LIBRES);
		
		List<Turno> listaTurnos = turnoService.getAll();
		List<Turno> listaTurnosLibres = new ArrayList<Turno>();			
		List<Terapista> listaTerapistas = terapistaService.getAll();
		
		for ( Terapista terapista : listaTerapistas ) {
			
			agregarTurnosLibresTerapista(	
					terapista, 
					listaTurnosLibres,
					listaTurnos, 
					fechaDesde, 
					fechaHasta); 	
		}	
		
		return listaTurnosLibres; 	
	}
	
	public List<Turno> getTurnosLibresPorTerapista(String dniTerapista) {
		
		String fechaDesde = TurnoDateFormat.getCurrentDate();
		String fechaHasta = TurnoDateFormat.getCurrentDatePlusDays(QTDE_DIAS_PRESENTA_TURNOS_LIBRES);
		
		List<Turno> listaTurnos = turnoService.getAll();
		List<Turno> listaTurnosLibres = new ArrayList<Turno>();			
	
		agregarTurnosLibresTerapista(	
					terapistaService.getOne(dniTerapista), 
					listaTurnosLibres,
					listaTurnos, 
					fechaDesde, 
					fechaHasta); 	
		
		return listaTurnosLibres; 	
	}
	
	public List<Turno> getTurnosLibresPorPeriodo( PeriodoAtencion periodoAtencion) {
		
		String fechaDesde = TurnoDateFormat.getCurrentDate();
		String fechaHasta = TurnoDateFormat.getCurrentDatePlusDays(QTDE_DIAS_PRESENTA_TURNOS_LIBRES);
		
		List<Turno> listaTurnos = turnoService.getAll();
		List<Turno> listaTurnosLibres = new ArrayList<Turno>();			
		
		agregarTurnosLibresTerapista(	
					terapistaService.getTerapistaPorPeriodo( periodoAtencion ), 
					listaTurnosLibres,
					listaTurnos, 
					fechaDesde, 
					fechaHasta); 	
		
		return listaTurnosLibres; 	
	}
	
	public void reservar(Turno entidad) {
			
		List<Turno> listaTurnos = turnoService.getAll();
		List<Turno> listaTurnosLibres = this.getTurnosLibres(); 		
		
		Turno turnoLibre = getTurnoDeLista(entidad.getFechaHoraTurno(),listaTurnosLibres );
		Turno turnoExistentes = getTurnoDeLista(entidad.getFechaHoraTurno(),listaTurnos );
		
		int qtdeTurnosPaciente = this.contarQtdeTurnosPaciente( 
												TurnoDateFormat.getCurrentDate(), 
												entidad.getPaciente() ,					
												listaTurnos
											);
									

		if ( (turnoLibre != null ) ) {
			
			if (qtdeTurnosPaciente < MAX_QTDE_TURNOS_AGENDADOS_PACIENTE ) { 
				try {
					entidad.reservar();
				} catch (TipoEstadoTurnoException e) {
					throw new TurnoServiceException( "No fue posible reservar" , e);
				}
				
				if (turnoExistentes!= null )
					turnoService.update(entidad);
				else 
					turnoService.save(entidad);
			}
			else throw new TurnoServiceException("No puede reservar mas turnos. Cantidad maxima es " +  MAX_QTDE_TURNOS_AGENDADOS_PACIENTE );
		}		
		else 
			throw new TurnoServiceException("El turno no pertenece a la lista de horarios disponibles ! Horario: " +  entidad.getFechaHoraTurno() );
	
	}
	

	public void cancelar(String date) {
		
		Turno turnoCancelar =turnoService.getOne(date); 
		
		try {
			turnoCancelar.cancelar();
		} catch (TipoEstadoTurnoException e) {
			throw new TurnoServiceException( "No fue posible cancelar" , e);
		}
		turnoService.update(turnoCancelar);
		
	}
	
	public void confirmarTurno(String date) {
		
		Turno turnoConfirmar =turnoService.getOne(date); 
		
		try {
			turnoConfirmar.confirmarAsistencia();
		} catch (TipoEstadoTurnoException e) {
			throw new TurnoServiceException( "No fue posible cancelar" , e);
		}
		turnoService.update(turnoConfirmar);
		
	}
	
	public List<Turno> getTurnosPorTerapista( String dniTerapista){
		
		Terapista terapista = terapistaService.getOne(dniTerapista);
		
		List<Turno> listaTurnos = turnoService.getAll();
		List<Turno> listaTurnosTerpista = new ArrayList<Turno>();
		
		for ( Turno turno : listaTurnos ) {
			if (turno.getTerapista().getUsuarioTerapia().getDni().equals(terapista.getUsuarioTerapia().getDni()))
				listaTurnosTerpista.add(turno);
		}
		return listaTurnosTerpista; 	
	}
	
	
	private void agregarTurnosLibresTerapista(	Terapista terapista, 
												List<Turno> listaAgregarTurnosLibres,
												List<Turno> listaBusqueda,
												String fechaDesde, 
												String fechaHasta ){
		

		LocalDateTime desde= TurnoDateFormat.toDateTimeFormat(fechaDesde);
		LocalDateTime hasta = TurnoDateFormat.toDateTimeFormat(fechaHasta);

		LocalDateTime dataDesdeIt = TurnoDateFormat.toDateTimeFormat(fechaDesde);
		Long diffMin = Duration.between(desde, hasta).getSeconds() /60;
		
		LocalDateTime dateTimeProximoTurno; 
		
		while (  diffMin > 0  ) {
											
			dateTimeProximoTurno = getNextDateTimeTerapista( dataDesdeIt, terapista  );
				
			if (dateTimeProximoTurno !=null ) {
				
				Turno turnoLista = getTurnoDeLista(dateTimeProximoTurno, listaBusqueda);
				
				if (turnoLista == null){
					Turno turno = new Turno();
					turno.setFechaHoraTurno(dateTimeProximoTurno);
					turno.setTerapista(terapista);
					listaAgregarTurnosLibres.add(turno);					
				} 
				else if (turnoLista.getTipoEstado() == TipoEstadoTurno.LIBRE ) { 
						listaAgregarTurnosLibres.add(turnoLista);					
				}
			}	
			
			dataDesdeIt = dataDesdeIt.plusMinutes(Turno.DURACION_MIN_TURNO);
			diffMin = Duration.between(dataDesdeIt, hasta).getSeconds()/60 ;
		}
	}

	
	private LocalDateTime getNextDateTimeTerapista(LocalDateTime horario, Terapista terapista  ){
		
		int hhDesdeTerapista = terapista.getPeriodoAtencion().getHhDesde();
		int mmDesdeTerapista = terapista.getPeriodoAtencion().getMmDesde();
		int hhHastaTerapista = terapista.getPeriodoAtencion().getHhHasta();
		int mmHastaTerapista = terapista.getPeriodoAtencion().getMmHasta();
		
		LocalDateTime horarioValidar = LocalDateTime.of(horario.getYear(), horario.getMonth(), horario.getDayOfMonth(), horario.getHour(), horario.getMinute());
		LocalDateTime horarioInicioTurnoTerapista = LocalDateTime.of(horario.getYear(), horario.getMonth(), horario.getDayOfMonth(), hhDesdeTerapista, mmDesdeTerapista);
		LocalDateTime horarioFinTurnoTerapista = LocalDateTime.of(horario.getYear(), horario.getMonth(), horario.getDayOfMonth(), hhHastaTerapista, mmHastaTerapista);

		LocalDateTime dataDesdeIt = horarioInicioTurnoTerapista;
		Long diffConUltimoHorarioAtencion = Duration.between(dataDesdeIt, horarioFinTurnoTerapista).getSeconds() /60;				
		Long diffConProximoHorarioAtencion = Duration.between(horarioValidar, dataDesdeIt).getSeconds() /60;	
		
		while (  diffConUltimoHorarioAtencion >= Turno.DURACION_MIN_TURNO  ) {
			
			if ( diffConProximoHorarioAtencion < Turno.DURACION_MIN_TURNO &&
				 diffConProximoHorarioAtencion >= 0	) 	
				
				return dataDesdeIt; 
			
			dataDesdeIt = dataDesdeIt.plusMinutes(Turno.DURACION_MIN_TURNO);
			diffConUltimoHorarioAtencion = Duration.between(dataDesdeIt, horarioFinTurnoTerapista).getSeconds() /60;
			diffConProximoHorarioAtencion = Duration.between(horarioValidar, dataDesdeIt).getSeconds() /60;
		}
		
		return null;
		
	}
	
	
	private Turno getTurnoDeLista(	LocalDateTime fecha, List<Turno> listaTurnos ){
		
		for ( Turno turno : listaTurnos ) {
			if (turno.getFechaHoraTurno().equals(fecha))
				return turno;
		}
		return null; 	
	}
	
	private int contarQtdeTurnosPaciente (			String desde, 
													Paciente paciente, 
													List<Turno> listaTurnos ){

		LocalDateTime dateDesde = TurnoDateFormat.toDateTimeFormat(desde); 
		
		Long diff; 
		int j= 0; 
		for ( Turno turno : listaTurnos ) {
			
			diff = Duration.between(dateDesde, turno.getFechaHoraTurno()).getSeconds() /60;
			
			if ( 	diff >= 0 
					&& turno.getPaciente().getUsuarioPaciente().getDni() == paciente.getUsuarioPaciente().getDni()
					&& turno.getTipoEstado().equals(TipoEstadoTurno.PENDIENTE_CONFIRMACION) 
					) 
				j++;	
		}
		
		return j; 	
	}


}
