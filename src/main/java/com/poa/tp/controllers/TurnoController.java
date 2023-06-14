package com.poa.tp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	
}
