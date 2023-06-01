package com.poa.tp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poa.tp.entities.Paciente;
import com.poa.tp.services.PacienteService;

@RestController
@RequestMapping(value="/pacientes")
public class PacienteController {

	@Autowired 
	private PacienteService pacienteService;
	

	@GetMapping
	public List<Paciente> listar() {
		
		List<Paciente> lista = pacienteService.buscarTodos();
		
		return lista; 
	}
		
	@GetMapping(value="/{id}")
	public ResponseEntity<?> buscar(@PathVariable Integer id) {

		Paciente obj = pacienteService.buscar(id);
		
		return ResponseEntity.ok().body(obj);
	}
}
