package com.poa.tp.services;


import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poa.tp.entities.Paciente;
import com.poa.tp.entities.Terapista;
import com.poa.tp.entities.Turno;
import com.poa.tp.entities.Usuario;
import com.poa.tp.repositories.PacienteRepository;
import com.poa.tp.repositories.TerapistaRepository;
import com.poa.tp.repositories.TurnoRepository;
import com.poa.tp.repositories.UsuarioRepository;
import com.poa.tp.services.exceptions.ObjectAlreadyExistException;
import com.poa.tp.services.exceptions.ObjectNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class TurnoService implements IService <Turno, String> { 
		
	@Autowired
	private TurnoRepository turnoRepository;  
	
	@Autowired
	private TerapistaRepository terapistaRepository;  
	
	@Autowired
	private UsuarioRepository usuarioRepository;  

	@Autowired
	private PacienteRepository pacienteRepository;  


	@Override
	public List<Turno> getAll() {
	
		List<Turno> lista = turnoRepository.findAll()
				.stream()
				.map(entidad-> new Turno(entidad))
				.collect(Collectors.toList());
		return lista;
	}
	
	@Override
	public Turno getOne(String fechaHoraTurno) throws ObjectNotFoundException {

		Optional<Turno> turno = turnoRepository.findByFechaHoraTurno(TurnoDateFormat.toDateTimeFormat(fechaHoraTurno));		
	
		return turno.orElseThrow( () -> new ObjectNotFoundException("Turno no encontrado! Fecha Hora: " + fechaHoraTurno)); 
	}

	@Override
	public void save(Turno turno)  throws ObjectNotFoundException,ObjectAlreadyExistException {
				
		Optional<Turno> entidad = turnoRepository.findByFechaHoraTurno( turno.getFechaHoraTurno() );
		if (entidad.isPresent()) 
			throw new ObjectAlreadyExistException("Turno ya existe! Fecha Hora: " +  turno.getFechaHoraTurno() );
		
		Optional<Usuario> usuarioPaciente = usuarioRepository.findByDni( turno.getPaciente().getUsuarioPaciente().getDni());		
		if (usuarioPaciente.isEmpty() )
			throw new ObjectNotFoundException("Usuario del Paciente no encontrado! DNI: " + turno.getPaciente().getUsuarioPaciente().getDni() );
		
		Optional<Usuario> usuarioTerapista = usuarioRepository.findByDni( turno.getTerapista().getUsuarioTerapia().getDni());		
		if (usuarioTerapista.isEmpty() )
			throw new ObjectNotFoundException("Usuario del Terapista no encontrado! DNI: " + turno.getTerapista().getUsuarioTerapia().getDni() );

		Optional<Paciente> paciente = pacienteRepository.findById( usuarioPaciente.get().getId() );
		if (paciente.isEmpty()) 
			throw new ObjectAlreadyExistException("Paciente no existe ! DNI: " +  usuarioPaciente.get().getDni()  );

		Optional<Terapista> terapista = terapistaRepository.findById( usuarioTerapista.get().getId() );
		if (terapista.isEmpty()) 
			throw new ObjectAlreadyExistException("Terapista no existe ! DNI: " +  usuarioTerapista.get().getDni() );
		
		turnoRepository.save(turno);
	}
	
	@Override
	public void saveAll(List<Turno> lista) throws ObjectNotFoundException,ObjectAlreadyExistException {
		
		for (Turno elemento : lista) 
			this.save( elemento);		
	}
			
		
	@Override
	@Transactional
	public void delete(String fechaHoraTurno) throws ObjectNotFoundException {	
		
		Optional<Turno> entidad = turnoRepository.findByFechaHoraTurno(TurnoDateFormat.toDateTimeFormat(fechaHoraTurno));
		if (entidad.isEmpty()) 
			throw new ObjectNotFoundException("Turno no encontrado! Fecha Hora: " + fechaHoraTurno );
			
		LocalDateTime date = TurnoDateFormat.toDateTimeFormat(fechaHoraTurno) ;
		turnoRepository.deleteByFechaHoraTurno(date );
		
	}
	
	
	@Override
	public void update(Turno turno)  throws ObjectNotFoundException {
		
		Optional<Turno> turnoOld = turnoRepository.findByFechaHoraTurno( turno.getFechaHoraTurno() );
		if (turnoOld.isEmpty()) 
			throw new ObjectNotFoundException("Turno no existe! Fecha Hora: " +  turno.getFechaHoraTurno() );
		
		Optional<Usuario> usuarioPaciente = usuarioRepository.findByDni( turno.getPaciente().getUsuarioPaciente().getDni());		
		if (usuarioPaciente.isEmpty() )
			throw new ObjectNotFoundException("Usuario del Paciente no encontrado! DNI: " + turno.getPaciente().getUsuarioPaciente().getDni() );
		
		Optional<Usuario> usuarioTerapista = usuarioRepository.findByDni( turno.getTerapista().getUsuarioTerapia().getDni());		
		if (usuarioTerapista.isEmpty() )
			throw new ObjectNotFoundException("Usuario del Terapista no encontrado! DNI: " + turno.getTerapista().getUsuarioTerapia().getDni() );

		Optional<Paciente> paciente = pacienteRepository.findById( usuarioPaciente.get().getId() );
		if (paciente.isEmpty()) 
			throw new ObjectAlreadyExistException("Paciente no existe ! DNI: " +  usuarioPaciente.get().getDni()  );

		Optional<Terapista> terapista = terapistaRepository.findById( usuarioTerapista.get().getId() );
		if (terapista.isEmpty()) 
			throw new ObjectAlreadyExistException("Terapista no existe ! DNI: " +  usuarioTerapista.get().getDni() );

		turno.setId(turnoOld.get().getId());
		
		turnoRepository.save(turno);

	}


	
	
	/*
	// Ejemplos de Data hora
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH::mm");
	DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");  // mismo sin horas
	DateTimeFormatter fmt3 = DateTimeFormatter.ISO_DATE_TIME; 

	LocalDate d01 = LocalDate.now();
	LocalDateTime d02 = LocalDateTime.now();
	LocalDate d03 = LocalDate.parse("2022-07-20");
	LocalDateTime d04 = LocalDateTime.parse("2022-07-20T01:30:26");
	LocalDateTime d05 = LocalDateTime.parse("2022-07-20T01:30:26");
	LocalDateTime d06 = LocalDateTime.parse("2022-07-20 01:30:26", fmt);
	LocalDateTime d10 = LocalDateTime.of(2022, 7, 20, 2, 30); /// yyyy dd MM  HH mm

	String dataEnString1 = d06.format(fmt);  /// Formata en lo que define de fomrato.
	String dataEnString2 = fmt.format(d06); // Es equivalente al anterior. 
	String dataEnString3 = d06.toString();   // Lo deja en el formatoo ISO 

	int dia = d06.getDayOfMonth();
	int mes = d06.getMonthValue();
	int ano = d06.getYear();
	int hora = d06.getHour();
	int min = d06.getMinute();

	/// Data hora son objetos inmutables. Tenemos que crear otro objeto. 
	LocalDateTime pastWeekDate = d04.minusDays(7); // Menos 7 ddias
	LocalDateTime nexttWeekDate = d04.plusDays(7); // Menos 7 ddias
	LocalDateTime pastHoursWeekDate = d04.minusMinutes(30); // Menos 7 ddias
	LocalDateTime nextHourstWeekDate = d04.plusMinutes(30); // Menos 7 ddias
			
	Duration t1 = Duration.between(pastHoursWeekDate, nextHourstWeekDate); 
	Long duracion = t1.toHours();

	*/
}
