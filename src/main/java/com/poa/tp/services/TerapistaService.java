package com.poa.tp.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poa.tp.dto.TerapistaDTO;
import com.poa.tp.entities.Terapista;
import com.poa.tp.entities.Usuario;
import com.poa.tp.entities.exceptions.PeriodoAtencionException;
import com.poa.tp.repositories.TerapistaRepository;
import com.poa.tp.repositories.UsuarioRepository;
import com.poa.tp.services.exceptions.InvalidEntityDataException;
import com.poa.tp.services.exceptions.ObjectAlreadyExistException;
import com.poa.tp.services.exceptions.ObjectNotFoundException;


@Service
public class TerapistaService implements IService <TerapistaDTO, String> { // Id es el DNI (entiudad debil, obtengo del usuario)
	
	@Autowired
	private TerapistaRepository terapistaRepository;  
	
	@Autowired
	private UsuarioRepository usuarioRepository;  

	@Override
	public List<TerapistaDTO> getAll() {
	
		List<TerapistaDTO> lista = terapistaRepository.findAll()
				.stream()
				.map(entidad->{
					try {
						return new TerapistaDTO(entidad);
					} catch (PeriodoAtencionException e) {
						throw new InvalidEntityDataException("Periodo de atencion Invalido obtener alguno de los Terapistas");
					}
				})
				.collect(Collectors.toList());
		return lista;
	}
	
	@Override
	public TerapistaDTO getOne(String dni) throws ObjectNotFoundException {
		
		Optional<Usuario> usuario = usuarioRepository.findByDni( dni);		
		if (usuario.isEmpty() )
			throw new ObjectNotFoundException("Cliente del terapista no encontrado! DNI: " + dni);
		
		Optional<TerapistaDTO> obj = terapistaRepository.findById( usuario.get().getId() )
				.map(entidad ->{
					try {
						return new TerapistaDTO (entidad);
					} catch (PeriodoAtencionException e) {
						throw new InvalidEntityDataException("Periodo de atencion Invalido obtener un Terapista");
					}
				});
	
		return obj.orElseThrow( () -> new ObjectNotFoundException("Terapista no encontrado! DNI: " + dni )); 
	}

	@Override
	public void save(TerapistaDTO entityDTO)  throws ObjectNotFoundException,ObjectAlreadyExistException {
		
		Optional<Usuario> usuario = usuarioRepository.findByDni( entityDTO.getUsuarioTerapia().getDni());		
		if (usuario.isEmpty() )
			throw new ObjectNotFoundException("Cliente del paciente no encontrado! DNI: " + entityDTO.getUsuarioTerapia().getDni() );
		
		Optional<Terapista> entidad = terapistaRepository.findById( usuario.get().getId() );
		if (entidad.isPresent()) 
			throw new ObjectAlreadyExistException("Terapista ya existe relacionado al cliente! DNI: " + usuario.get().getDni()  + ", ID Paciente: "+entidad.get().getId() );

		terapistaRepository.save(new Terapista (entityDTO));
	}
	
	@Override
	public void saveAll(List<TerapistaDTO> lista) throws ObjectNotFoundException,ObjectAlreadyExistException {
		
		for (TerapistaDTO elemento : lista) 
			this.save( elemento);		
	}
	

	@Override
	public void delete(String dni) {	
		
		Optional<Usuario> usuario = usuarioRepository.findByDni( dni);		
		if (usuario.isEmpty() )
			throw new ObjectNotFoundException("Cliente del paciente a eliminar no encontrado! DNI: " + dni);
		
		Optional<Terapista> entidad = terapistaRepository.findById( usuario.get().getId() );
		if (entidad.isEmpty()) 
			throw new ObjectAlreadyExistException("Terapista a eliminar no existe! DNI: " + dni + ", ID Terapista: "+entidad.get().getId());
		
		usuarioRepository.deleteById(entidad.get().getId());
		
	}
	
	
	@Override
	public void update(TerapistaDTO entityDto) {
	
		Optional<Usuario> usuario = usuarioRepository.findByDni( entityDto.getUsuarioTerapia().getDni());		
		if (usuario.isEmpty() )
			throw new ObjectNotFoundException("Cliente del paciente a actualizar no encontrado! DNI: " + entityDto.getUsuarioTerapia().getDni());
		
		Optional<Terapista> entidad = terapistaRepository.findById( usuario.get().getId() );
		if (entidad.isEmpty()) 
			throw new ObjectAlreadyExistException("Paciente a actualizar no existe! DNI: " + usuario.get().getId() + ", ID Paciente: "+entidad.get().getId());
				
		terapistaRepository.save(new Terapista (entityDto));
	}
	

}
