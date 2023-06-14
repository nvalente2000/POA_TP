package com.poa.tp.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poa.tp.dto.PacienteDTO;
import com.poa.tp.entities.Paciente;
import com.poa.tp.entities.Usuario;
import com.poa.tp.repositories.PacienteRepository;
import com.poa.tp.repositories.UsuarioRepository;
import com.poa.tp.services.exceptions.ObjectAlreadyExistException;
import com.poa.tp.services.exceptions.ObjectNotFoundException;


@Service
public class PacienteService implements IService <PacienteDTO, String>{ // Id es el DNI (entiudad debil, obtengo del usuario)
	
	@Autowired
	private PacienteRepository pacienteRepository;  
	
	@Autowired
	private UsuarioRepository usuarioRepository;  

	@Override
	public List<PacienteDTO> getAll() {
	
		List<PacienteDTO> lista = pacienteRepository.findAll()
				.stream()
				.map(entidad->new PacienteDTO(entidad))
				.collect(Collectors.toList());
		return lista;
	}
	
	
	@Override
	public PacienteDTO getOne(String dni) throws ObjectNotFoundException {
		
		Optional<Usuario> usuario = usuarioRepository.findByDni( dni);		
		if (usuario.isEmpty() )
			throw new ObjectNotFoundException("Cliente del paciente no encontrado! DNI: " + dni);
		
		Optional<PacienteDTO> obj = pacienteRepository.findById( usuario.get().getId() )
				.map(entidad ->new PacienteDTO (entidad));
	
		return obj.orElseThrow( () -> new ObjectNotFoundException("Paciente no encontrado! DNI: " + dni )); 
	}
	

	@Override
	public void save(PacienteDTO entityDTO)  throws ObjectNotFoundException,ObjectAlreadyExistException {
		
		Optional<Usuario> usuario = usuarioRepository.findByDni( entityDTO.getUsuarioPaciente().getDni());		
		if (usuario.isEmpty() )
			throw new ObjectNotFoundException("Cliente del paciente no encontrado! DNI: " + entityDTO.getUsuarioPaciente().getDni() );
		
		Optional<Paciente> entidad = pacienteRepository.findById( usuario.get().getId() );
		if (entidad.isPresent()) 
			throw new ObjectAlreadyExistException("Paciente ya existe relacionado al cliente! DNI: " + usuario.get().getDni()  + ", ID Paciente: "+entidad.get().getId() );

		pacienteRepository.save(new Paciente (entityDTO));
	}
	 
	
	@Override
	public void saveAll(List<PacienteDTO> lista) throws ObjectNotFoundException,ObjectAlreadyExistException {
		
		for (PacienteDTO elemento : lista) {
			this.save( elemento);		
		}		
	}
	

	@Override
	public void delete(String dni) {	
		
		Optional<Usuario> usuario = usuarioRepository.findByDni( dni);		
		if (usuario.isEmpty() )
			throw new ObjectNotFoundException("Cliente del paciente a eliminar no encontrado! DNI: " + dni);
		
		Optional<Paciente> entidad = pacienteRepository.findById( usuario.get().getId() );
		if (entidad.isEmpty()) 
			throw new ObjectAlreadyExistException("Paciente a eliminar no existe! DNI: " + dni + ", ID Paciente: "+entidad.get().getId());
		
		usuarioRepository.deleteById(entidad.get().getId());	
	}
	
	
	@Override
	public void update(PacienteDTO entityDto) {
	
		Optional<Usuario> usuario = usuarioRepository.findByDni( entityDto.getUsuarioPaciente().getDni());		
		if (usuario.isEmpty() )
			throw new ObjectNotFoundException("Cliente del paciente a actualizar no encontrado! DNI: " + entityDto.getUsuarioPaciente().getDni());

		Optional<Paciente> entidad = pacienteRepository.findById( usuario.get().getId() );
		if (entidad.isEmpty()) 
			throw new ObjectAlreadyExistException("Paciente a actualizar no existe! DNI: " + usuario.get().getId() + ", ID Paciente: "+entidad.get().getId());
		
		pacienteRepository.save(new Paciente (entityDto));
	}
	
}
