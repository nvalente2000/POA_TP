package com.poa.tp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poa.tp.entities.Usuario;
import com.poa.tp.repositories.UsuarioRepository;
import com.poa.tp.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;  // Automaticamente instanciada por Spring (inyeccion de depedencia)


	public List<Usuario> buscarTodos( ) {
		
		List<Usuario> lista = usuarioRepository.findAll();
		
		return lista;
		
	}
	
	public Usuario buscar( Integer id) {
		
		Optional<Usuario> obj = usuarioRepository.findById(id);  //Resolve el null. Optional es un conteiner y encapsula el null.
	
		return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto no encontrado! Id: " + id + ", Tipo: "+ Usuario.class.getName())); // Retorna o objeto. Se esta null, retorna exception (orElseTrow recebe parametro funcion que instancia exception. "()" Funcion sin argumentos.
	}
	
	public void guardarTodos( List<Usuario> lista) {
		
		usuarioRepository.saveAll(lista);
	}

	
}
