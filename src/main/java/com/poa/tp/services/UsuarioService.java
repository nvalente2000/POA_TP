package com.poa.tp.services;

import java.util.Arrays;
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
import com.poa.tp.services.exceptions.ServiceException;

@Service
public class UsuarioService implements IService <UsuarioDTO, Long>{
	
	@Autowired
	private UsuarioRepository usuarioRepository;  

	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@Override
	public List<UsuarioDTO> getAll() {
	
		List<UsuarioDTO> lista = usuarioRepository.findAll()
				.stream()
				.map(user->new UsuarioDTO(
						user.getId(), 
						user.getDni(), 
						user.getEmail(), 
						user.getNombre(), 
						user.getApellido(), 
						user.getPassword(), 
						Arrays.asList(user.getRoles().split(","))))
				.collect(Collectors.toList());
		return lista;
	}
	
	@Override
	public UsuarioDTO getOne(Long id) throws ServiceException {
		
		Optional<UsuarioDTO> obj = usuarioRepository.findById(id)
				.map(user->new UsuarioDTO(
					user.getId(), 
					user.getDni(), 
					user.getEmail(), 
					user.getNombre(), 
					user.getApellido(), 
					user.getPassword(), 
					Arrays.asList(user.getRoles().split(","))
				));
	
		return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto no encontrado! Id: " + id + ", Tipo: "+ Usuario.class.getName())); 
	}

	public UsuarioDTO getOneByDni(String dni) throws ServiceException {
		
		Optional<UsuarioDTO> obj = usuarioRepository.findByDni(dni)
				.map(user->new UsuarioDTO(
					user.getId(), 
					user.getDni(), 
					user.getEmail(), 
					user.getNombre(), 
					user.getApellido(), 
					user.getPassword(), 
					Arrays.asList(user.getRoles().split(","))
				));  
	
		return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto no encontrado! DNI: " + dni + ", Tipo: "+ Usuario.class.getName())); 
	}

	public UsuarioDTO getOneByEmail(String email) throws ServiceException {
		
		Optional<UsuarioDTO> obj = usuarioRepository.findByEmail(email)
				.map(user->new UsuarioDTO(
					user.getId(), 
					user.getDni(), 
					user.getEmail(), 
					user.getNombre(), 
					user.getApellido(), 
					user.getPassword(), 
					Arrays.asList(user.getRoles().split(","))
				));
	
		return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto no encontrado! DNI: " + email + ", Tipo: "+ Usuario.class.getName())); 
	}
	
	@Override
	public void save(UsuarioDTO entityDTO)  {
		
		Optional<Usuario> usuario = usuarioRepository.findByDni( entityDTO.getDni() );
		
		if (usuario.isPresent()){
			 throw new ObjectAlreadyExistException("Objeto ya existe! DNI: " + entityDTO.getDni() + ", Tipo: "+ Usuario.class.getName());
		}
		
		Usuario user = new Usuario (
							entityDTO.getId(),
							entityDTO.getDni(), 
							entityDTO.getEmail(),
							entityDTO.getNombre(),
							entityDTO.getApellido(),
							this.passwordEncoder.encode(entityDTO.getPassword()), 
							String.join(",", entityDTO.getRoles())
							);

		usuarioRepository.save(user);
	}
	
	
	public void saveAll(List<UsuarioDTO> lista) {
		
		for (UsuarioDTO elemento : lista) {
			this.save( elemento);		
		}		
	}
	

	@Override
	public void delete(Long id) {	
		
		Optional<Usuario> usuario = usuarioRepository.findById( id );
		
		if (usuario.isPresent()){
			 throw new ObjectNotFoundException("Objeto a eliminar no existe! ID: " + id + ", Tipo: "+ Usuario.class.getName());
		}
		
		usuarioRepository.deleteById(id);
		
	}
	public void deleteByDni(String dni) {	

		Optional<Usuario> usuario = usuarioRepository.findByDni( dni );
		
		if (usuario.isPresent()){
			 throw new ObjectNotFoundException("Objeto a eliminar no existe! DNI: " + dni + ", Tipo: "+ Usuario.class.getName());
		}
		usuarioRepository.deleteByDni(dni);
	}
	
	public void deleteByEmail(String email) {	

		Optional<Usuario> usuario = usuarioRepository.findByEmail( email );
		
		if (usuario.isPresent()){
			 throw new ObjectNotFoundException("Objeto a eliminar no existe! EMAIL: " + email + ", Tipo: "+ Usuario.class.getName());
		}
		usuarioRepository.deleteByEmail(email);

	}
	
	@Override
	public void update(UsuarioDTO entityDto) {
		Optional<Usuario> usuario = usuarioRepository.findById( entityDto.getId() );
		
		if (usuario.isPresent()){
			 throw new ObjectNotFoundException("Objeto a Actualizar no existe! ID: " + entityDto.getId()  + ", Tipo: "+ Usuario.class.getName());
		}
		
		Usuario user = new Usuario (
				entityDto.getId(),
				entityDto.getDni(), 
				entityDto.getEmail(),
				entityDto.getNombre(),
				entityDto.getApellido(),
				this.passwordEncoder.encode(entityDto.getPassword()), 
				String.join(",", entityDto.getRoles())
				);

		usuarioRepository.save(user);

	}
	
}
