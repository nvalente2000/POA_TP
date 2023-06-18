package com.poa.tp.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poa.tp.entities.Terapista;
import com.poa.tp.entities.Usuario;
import com.poa.tp.repositories.TerapistaRepository;
import com.poa.tp.repositories.UsuarioRepository;
import com.poa.tp.services.exceptions.ObjectAlreadyExistException;
import com.poa.tp.services.exceptions.ObjectNotFoundException;


@Service
public class TerapistaService implements IService <Terapista, String> { // Id es el DNI (entiudad debil, obtengo del usuario)
	
	@Autowired
	private TerapistaRepository terapistaRepository;  
	
	@Autowired
	private UsuarioRepository usuarioRepository;  

	@Override
	public List<Terapista> getAll() {
	
		List<Terapista> lista = terapistaRepository.findAll()
				.stream()
				.map(entidad-> new Terapista(entidad))
				.collect(Collectors.toList());
		return lista;
	}
	
	@Override
	public Terapista getOne(String dni) throws ObjectNotFoundException {
		
		Optional<Usuario> usuario = usuarioRepository.findByDni( dni);		
		if (usuario.isEmpty() )
			throw new ObjectNotFoundException("Usuario y terapista no encontrado! DNI: " + dni);
		
		Optional<Terapista> obj = terapistaRepository.findById( usuario.get().getId() )
				.map(entidad -> new Terapista (entidad));
	
		return obj.orElseThrow( () -> new ObjectNotFoundException("Terapista no encontrado! DNI: " + dni )); 
	}

	@Override
	public void save(Terapista entity)  throws ObjectNotFoundException,ObjectAlreadyExistException {
		
		Optional<Usuario> usuario = usuarioRepository.findByDni( entity.getUsuarioTerapia().getDni());		
		if (usuario.isEmpty() )
			throw new ObjectNotFoundException("Usuario y paciente no encontrado! DNI: " + entity.getUsuarioTerapia().getDni() );
		
		Optional<Terapista> entidad = terapistaRepository.findById( usuario.get().getId() );
		if (entidad.isPresent()) 
			throw new ObjectAlreadyExistException("Terapista ya existe relacionado al cliente! DNI: " +  entity.getUsuarioTerapia().getDni()  + ", ID Terapista: "+entidad.get().getId()  );
				
		entity.setUsuarioTerapia(usuario.get());
		entity.setId(usuario.get().getId());
		usuario.get().setTerapista(entity);

		usuarioRepository.save(usuario.get());

	}
	
	@Override
	public void saveAll(List<Terapista> lista) throws ObjectNotFoundException,ObjectAlreadyExistException {
		
		for (Terapista elemento : lista) 
			this.save( elemento);		
	}
	

	@Override
	public void delete(String dni) {	
		
		Optional<Usuario> usuario = usuarioRepository.findByDni( dni);		
		if (usuario.isEmpty() )
			throw new ObjectNotFoundException("Usuario y paciente a eliminar no encontrado! DNI: " + dni);
		
		Optional<Terapista> entidad = terapistaRepository.findById( usuario.get().getId() );
		if (entidad.isEmpty()) 
			throw new ObjectNotFoundException("Terapista a eliminar no existe! DNI: " + dni );
		
		usuarioRepository.deleteById(entidad.get().getId());
		
	}
	
	
	@Override
	public void update(Terapista entity) {
	
		Optional<Usuario> usuario = usuarioRepository.findByDni( entity.getUsuarioTerapia().getDni());		
		if (usuario.isEmpty() )
			throw new ObjectNotFoundException("Usuario y paciente a actualizar no encontrado! DNI: " + entity.getUsuarioTerapia().getDni());
		
		Optional<Terapista> entidad = terapistaRepository.findById( usuario.get().getId() );
		if (entidad.isEmpty()) 
			throw new ObjectAlreadyExistException("Paciente a actualizar no existe! DNI: " + entity.getUsuarioTerapia().getDni() );
				
		entity.setId(entidad.get().getId());
		entity.setUsuarioTerapia(usuario.get());
		usuario.get().setTerapista(entity);
		
		usuarioRepository.save(usuario.get());

	}
	

}
