package com.poa.tp.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.poa.tp.dto.UsuarioDTO;
import com.poa.tp.entities.Usuario;
import com.poa.tp.repositories.UsuarioRepository;
import com.poa.tp.services.exceptions.ObjectAlreadyExistException;
import com.poa.tp.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService implements IService <UsuarioDTO, String>{ // Id es el DNI (independiente del almacenamietno interno)
	
	@Autowired
	private UsuarioRepository usuarioRepository;  

	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@Override
	public List<UsuarioDTO> getAll() {
	
		List<UsuarioDTO> lista = usuarioRepository.findAll()
				.stream()
				.map(entidad->new UsuarioDTO(entidad))
				.collect(Collectors.toList());
		return lista;
	}
	
	@Override
	public UsuarioDTO getOne(String dni) throws ObjectNotFoundException {
		
		Optional<UsuarioDTO> obj = usuarioRepository.findByDni(dni)
				.map(entidad->new UsuarioDTO(entidad));  
	
		return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto no encontrado! DNI: " + dni )); 
	
	}
	
	@Override
	public void save(UsuarioDTO entityDTO) throws ObjectAlreadyExistException  {
		
		Optional<Usuario> entidad = usuarioRepository.findByDni( entityDTO.getDni() );
		
		if (entidad.isPresent()){
			 throw new ObjectAlreadyExistException("Objeto ya existe! DNI: " + entityDTO.getDni() );
		}
					
		usuarioRepository.save(new Usuario (entityDTO, passwordEncoder ));
	}
	
	@Override
	public void saveAll(List<UsuarioDTO> lista) throws ObjectAlreadyExistException {
		
		for (UsuarioDTO elemento : lista) 
			this.save( elemento);		
			
	}
	

	@Override
	public void delete(String dni) throws ObjectNotFoundException {	
		
		Optional<Usuario> entidad = usuarioRepository.findByDni( dni );
		
		if (entidad.isEmpty())
			 throw new ObjectNotFoundException("Objeto a eliminar no existe! DNI: " + dni );
		
		usuarioRepository.deleteByDni(dni);
		
	}
		
	@Override
	public void update(UsuarioDTO entityDto) {
		Optional<Usuario> usuario = usuarioRepository.findByDni( entityDto.getDni() );
		
		if (usuario.isEmpty())
			 throw new ObjectNotFoundException("Objeto a Actualizar no existe! DNI: " + entityDto.getDni() );
		
		usuarioRepository.save(new Usuario (entityDto,passwordEncoder ));

	}
	
}
