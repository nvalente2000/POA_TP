package com.poa.tp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poa.tp.entities.Paciente;
import com.poa.tp.entities.Terapista;
import com.poa.tp.repositories.TerapistaRepository;
import com.poa.tp.services.exceptions.ObjectNotFoundException;


@Service
public class TerapistaService {
	
	@Autowired
	private TerapistaRepository terapistaRepository;  // Automaticamente instanciada por Spring (inyeccion de depedencia)

	public List<Terapista> buscarTodos( ) {
		
		List<Terapista> lista = terapistaRepository.findAll();
		
		return lista;
		
	}
	
	public Terapista buscar( Integer id) {
				
		Optional<Terapista> obj = terapistaRepository.findById(id);  //Resolve el null. Optional es un conteiner y encapsula el null.
 
		return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto no encontrado! Id: " + id + ", Tipo: "+ Paciente.class.getName())); // Retorna o objeto. Se esta null, retorna exception (orElseTrow recebe parametro funcion que instancia exception. "()" Funcion sin argumentos.

	}
	
	public void guardarTodos( List<Terapista> lista) {
		
		terapistaRepository.saveAll(lista);
	}
	
}
