package com.poa.tp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poa.tp.dto.PacienteDTO;
import com.poa.tp.services.PacienteService;

@RestController
@RequestMapping(value="/pacientes")
public class PacienteController {

	@Autowired 
	private PacienteService pacienteService;
	

	@GetMapping(value="/all")
	public ResponseEntity<?> listar() {
		
		List<PacienteDTO> lista = pacienteService.getAll();
		
		return ResponseEntity.ok().body(lista); 
	}
	
	@GetMapping(value="/dni/{dni}")
	public ResponseEntity<?> buscarPorDni(@PathVariable String dni) {

		PacienteDTO obj = pacienteService.getOne(dni);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping(value="/save")
	public ResponseEntity<?> save(@RequestBody PacienteDTO entidad) {

		pacienteService.save(entidad);
		
		return ResponseEntity.ok().body(entidad);		
	}

	@PostMapping(value="/saveAll")
	public ResponseEntity<?> saveAll(@RequestBody List<PacienteDTO> lista) {

		pacienteService.saveAll(lista);
		
		return ResponseEntity.ok().body(lista);		
	}
	
	
	
	
	
}
