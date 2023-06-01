package com.poa.tp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poa.tp.entities.Paciente;
import com.poa.tp.repositories.PacienteRepository;
import com.poa.tp.services.exceptions.*;


@Service
public class PacienteService {
	
	@Autowired
	private PacienteRepository pacienteRepository;  // Automaticamente instanciada por Spring (inyeccion de depedencia)

	public List<Paciente> buscarTodos( ) {
		
		List<Paciente> lista = pacienteRepository.findAll();
		
		return lista;
		
	}
	
	public Paciente buscar( Integer id) {
		
		Optional<Paciente> obj = pacienteRepository.findById(id);  //Resolve el null. Optional es un conteiner y encapsula el null.
 
		return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto no encontrado! Id: " + id + ", Tipo: "+ Paciente.class.getName())); // Retorna o objeto. Se esta null, retorna exception (orElseTrow recebe parametro funcion que instancia exception. "()" Funcion sin argumentos.

	}
	
	public void guardarTodos( List<Paciente> lista) {
		
		pacienteRepository.saveAll(lista);
	}
	
}
