package com.poa.tp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poa.tp.entities.Turno;
import com.poa.tp.repositories.TurnoRepository;
import com.poa.tp.services.exceptions.ObjectNotFoundException;

@Service
public class TurnoService {
	
	@Autowired
	private TurnoRepository turnoRepository;  // Automaticamente instanciada por Spring (inyeccion de depedencia)


	public List<Turno> buscarTodos( ) {
		
		List<Turno> lista = turnoRepository.findAll();
		
		return lista;
		
	}
	
	public Turno buscar( Integer id) {
		
		Optional<Turno> obj = turnoRepository.findById(id);  //Resolve el null. Optional es un conteiner y encapsula el null.
	
		return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto no encontrado! Id: " + id + ", Tipo: "+ Turno.class.getName())); // Retorna o objeto. Se esta null, retorna exception (orElseTrow recebe parametro funcion que instancia exception. "()" Funcion sin argumentos.
	}
	
	public void guardarTodos( List<Turno> lista) {
		
		turnoRepository.saveAll(lista);
	}

	
}
