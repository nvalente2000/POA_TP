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

import com.poa.tp.dto.TerapistaDTO;
import com.poa.tp.services.TerapistaService;

@RestController
@RequestMapping(value="/terapistas")
public class TerapistaController {

	@Autowired 
	private TerapistaService terapistaService;
	

	@GetMapping(value="/all")
	public ResponseEntity<?> listar() {
		
		List<TerapistaDTO> lista = terapistaService.getAll();
		
		return ResponseEntity.ok().body(lista); 
	}
	
	@GetMapping(value="/dni/{dni}")
	public ResponseEntity<?> buscarPorDni(@PathVariable String dni) {

		TerapistaDTO obj = terapistaService.getOne(dni);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping(value="/save")
	public ResponseEntity<?> save(@RequestBody TerapistaDTO entidad) {

		terapistaService.save(entidad);
		
		return ResponseEntity.ok().body(entidad);		
	}

	@PostMapping(value="/saveAll")
	public ResponseEntity<?> saveAll(@RequestBody List<TerapistaDTO> lista) {

		terapistaService.saveAll(lista);
		
		return ResponseEntity.ok().body(lista);		
	}
	
}
