package com.poa.tp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poa.tp.entities.Administrador;
import com.poa.tp.entities.Paciente;
import com.poa.tp.repositories.AdministradorRepository;
import com.poa.tp.services.exceptions.ObjectNotFoundException;


@Service
public class AdministradorService {
	
	@Autowired
	private AdministradorRepository administradorRepository;  // Automaticamente instanciada por Spring (inyeccion de depedencia)

	public List<Administrador> buscarTodos( ) {
		
		List<Administrador> lista = administradorRepository.findAll();
		
		return lista;
		
	}
	
	public Administrador buscar( Integer id) {
				
		Optional<Administrador> obj = administradorRepository.findById(id);  //Resolve el null. Optional es un conteiner y encapsula el null.
 
		return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto no encontrado! Id: " + id + ", Tipo: "+ Paciente.class.getName())); // Retorna o objeto. Se esta null, retorna exception (orElseTrow recebe parametro funcion que instancia exception. "()" Funcion sin argumentos.

	}
	
	public void guardarTodos( List<Administrador> lista) {
		
		administradorRepository.saveAll(lista);
	}
	
}
