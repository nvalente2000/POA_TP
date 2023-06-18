package com.poa.tp.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poa.tp.entities.Paciente;
import com.poa.tp.entities.Usuario;
import com.poa.tp.repositories.PacienteRepository;
import com.poa.tp.repositories.UsuarioRepository;
import com.poa.tp.services.exceptions.ObjectAlreadyExistException;
import com.poa.tp.services.exceptions.ObjectNotFoundException;


@Service
public class PacienteService implements IService <Paciente, String>{ // Id es el DNI (entiudad debil, obtengo del usuario)
	
	@Autowired
	private PacienteRepository pacienteRepository;  
	
	@Autowired
	private UsuarioRepository usuarioRepository;  

	@Override
	public List<Paciente> getAll() {
	
		List<Paciente> lista = pacienteRepository.findAll()
				.stream()
				.map(entidad->new Paciente(entidad))
				.collect(Collectors.toList());
		return lista;
	}
	
	
	@Override
	public Paciente getOne(String dni) throws ObjectNotFoundException {
		
		Optional<Usuario> usuario = usuarioRepository.findByDni( dni);		
		if (usuario.isEmpty() )
			throw new ObjectNotFoundException("Usuario y paciente no encontrado! DNI: " + dni);
		
		Optional<Paciente> obj = pacienteRepository.findById( usuario.get().getId() )
				.map(entidad ->new Paciente (entidad));
	
		return obj.orElseThrow( () -> new ObjectNotFoundException("Paciente no encontrado! DNI: " + dni )); 
	}
	

	@Override
	public void save(Paciente entity)  throws ObjectNotFoundException,ObjectAlreadyExistException {
		
		Optional<Usuario> usuario = usuarioRepository.findByDni( entity.getUsuarioPaciente().getDni());		
		if (usuario.isEmpty() )
			throw new ObjectNotFoundException("Usuario y paciente no encontrado! DNI: " + entity.getUsuarioPaciente().getDni() );
		
		Optional<Paciente> entidad = pacienteRepository.findById( usuario.get().getId() );
		if (entidad.isPresent()) 
			throw new ObjectAlreadyExistException("Paciente ya existe relacionado al cliente! DNI: " + entity.getUsuarioPaciente().getDni()  + ", ID Paciente: "+entidad.get().getId() );
		
		entity.setUsuarioPaciente(usuario.get());
		entity.setId(usuario.get().getId());
		usuario.get().setPaciente(entity);
		
		usuarioRepository.save(usuario.get());
		
	}
	 
	
	@Override
	public void saveAll(List<Paciente> lista) throws ObjectNotFoundException,ObjectAlreadyExistException {
		
		for (Paciente elemento : lista) {
			this.save( elemento);		
		}		
	}
	

	@Override
	public void delete(String dni) {	
		
		Optional<Usuario> usuario = usuarioRepository.findByDni( dni);		
		if (usuario.isEmpty() )
			throw new ObjectNotFoundException("Usuario y paciente a eliminar no encontrado! DNI: " + dni);
		
		Optional<Paciente> entidad = pacienteRepository.findById( usuario.get().getId() );
		if (entidad.isEmpty()) 
			throw new ObjectNotFoundException("Paciente a eliminar no existe! DNI: " + dni);
		
		usuarioRepository.deleteById(entidad.get().getId());	
	}
	
	
	@Override
	public void update(Paciente entity) {
	
		Optional<Usuario> usuario = usuarioRepository.findByDni( entity.getUsuarioPaciente().getDni());		
		if (usuario.isEmpty() )
			throw new ObjectNotFoundException("Usuario y paciente a actualizar no encontrado! DNI: " + entity.getUsuarioPaciente().getDni());

		Optional<Paciente> entidad = pacienteRepository.findById( usuario.get().getId() );
		if (entidad.isEmpty()) 
			throw new ObjectAlreadyExistException("Paciente a actualizar no existe! DNI: " + entity.getUsuarioPaciente().getDni() );
		
		entity.setId(entidad.get().getId());
		entity.setUsuarioPaciente(usuario.get());
		usuario.get().setPaciente(entity);
			
		usuarioRepository.save(usuario.get());
			
	}
	
}
