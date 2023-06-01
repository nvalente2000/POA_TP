package com.poa.tp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poa.tp.entities.Turno;
import com.poa.tp.services.TurnoService;

@RestController
@RequestMapping(value="/turnos")
public class TurnoController {

	@Autowired 
	private TurnoService turnoService;

	@GetMapping
	public List<Turno> listar() {
		
		List<Turno> lista = turnoService.buscarTodos();
		
		return lista; 
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> buscar(@PathVariable Integer id) {

		Turno obj = turnoService.buscar(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
}
