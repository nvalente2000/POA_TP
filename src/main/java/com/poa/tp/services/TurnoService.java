package com.poa.tp.services;

import java.util.Date;
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

		Optional<Turno> turno = turnoRepository.findByFechaHoraTurno(TurnoDateFormat.toDateForma(fechaHoraTurno));		
	
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

		turno.setPaciente(paciente.get());
		turno.setTerapista(terapista.get());
		paciente.get().getTurnos().add(turno);
		terapista.get().getTurnos().add(turno);
		
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
		
		Optional<Turno> entidad = turnoRepository.findByFechaHoraTurno(TurnoDateFormat.toDateForma(fechaHoraTurno));
		if (entidad.isEmpty()) 
			throw new ObjectNotFoundException("Turno no encontrado! Fecha Hora: " + fechaHoraTurno );
			
		Date date = TurnoDateFormat.toDateForma(fechaHoraTurno) ;
		turnoRepository.deleteByFechaHoraTurno(date );
		
	}
	
	
	@Override
	public void update(Turno turno)  throws ObjectNotFoundException {
		
		Optional<Turno> turnoOld = turnoRepository.findByFechaHoraTurno( turno.getFechaHoraTurno() );
		if (turnoOld.isEmpty()) 
			throw new ObjectAlreadyExistException("Turno no existe! Fecha Hora: " +  turno.getFechaHoraTurno() );
		
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
		turno.setPaciente(paciente.get());
		turno.setTerapista(terapista.get());
		paciente.get().getTurnos().add(turno);
		terapista.get().getTurnos().add(turno);
		
		turnoRepository.save(turno);

	}

	
}
