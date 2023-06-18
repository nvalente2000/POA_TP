package com.poa.tp.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.poa.tp.entities.Usuario;
import com.poa.tp.repositories.UsuarioRepository;
import com.poa.tp.services.exceptions.ObjectAlreadyExistException;
import com.poa.tp.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService implements IService <Usuario, String>{ // Id es el DNI (independiente del almacenamietno interno)
	
	@Autowired
	private UsuarioRepository usuarioRepository;  

	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@Override
	public List<Usuario> getAll() {
	
		List<Usuario> lista = usuarioRepository.findAll()
				.stream()
				.map(entidad->new Usuario(entidad))
				.collect(Collectors.toList());
		return lista;
	}
	
	@Override
	public Usuario getOne(String dni) throws ObjectNotFoundException {
		
		Optional<Usuario> obj = usuarioRepository.findByDni(dni)
				.map(entidad->new Usuario(entidad));  
	
		return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto no encontrado! DNI: " + dni )); 
	
	}
	
	@Override
	public void save(Usuario entity) throws ObjectAlreadyExistException  {
		
		Optional<Usuario> entidad = usuarioRepository.findByDni( entity.getDni() );
		
		if (entidad.isPresent()){
			 throw new ObjectAlreadyExistException("Objeto ya existe! DNI: " + entity.getDni() );
		}
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		usuarioRepository.save(entity);
	}
	
	@Override
	public void saveAll(List<Usuario> lista) throws ObjectAlreadyExistException {
		
		for (Usuario elemento : lista) 
			this.save( elemento);				
	}

	@Override
	public void delete(String dni) throws ObjectNotFoundException {	
		
		Optional<Usuario> entidad = usuarioRepository.findByDni( dni );
		
		if (entidad.isEmpty())
			 throw new ObjectNotFoundException("Objeto a eliminar no existe! DNI: " + dni );
		
		usuarioRepository.deleteById(entidad.get().getId());
	}
		
	@Override
	public void update(Usuario entity) {
		Optional<Usuario> usuario = usuarioRepository.findByDni( entity.getDni() );
		
		if (usuario.isEmpty())
			 throw new ObjectNotFoundException("Objeto a Actualizar no existe! DNI: " + entity.getDni() );
	
		Usuario user = new Usuario (usuario.get());
		user.setApellido(entity.getApellido());
		user.setEmail(entity.getEmail());
		user.setNombre(entity.getNombre());
		user.setRoles(entity.getRoles());
		user.setPassword(passwordEncoder.encode(entity.getPassword()));
		entity.setId(usuario.get().getId());
		usuarioRepository.save(user);

	}
	
}
