package com.poa.tp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poa.tp.dto.UsuarioDTO;
import com.poa.tp.services.UsuarioService;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioController {

	@Autowired 
	private UsuarioService usuarioService;

	@GetMapping(value="/")
	public ResponseEntity<?> findAll() {
		
		List<UsuarioDTO> lista = usuarioService.getAll();
		
		return ResponseEntity.ok().body(lista); 
	}
	
	@GetMapping(value="/find/{dni}")
	public ResponseEntity<?> find(@PathVariable String dni) {

		UsuarioDTO obj = usuarioService.getOne(dni);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping(value="/save")
	public ResponseEntity<?> save(@RequestBody UsuarioDTO entidad) {

		usuarioService.save(entidad);
		
		return ResponseEntity.ok().body(entidad);		
	}

	@PostMapping(value="/saveAll")
	public ResponseEntity<?> saveAll(@RequestBody List<UsuarioDTO> lista) {

		usuarioService.saveAll(lista);
		
		return ResponseEntity.ok().body(lista);		
	}
	
	@DeleteMapping(value="/delete/{dni}")
	public ResponseEntity<?> delete(@PathVariable String dni) {

		usuarioService.delete(dni);
		
		return ResponseEntity.ok().body(dni);
	}
	
	@PutMapping(value="/update")
	public ResponseEntity<?> delete(@PathVariable UsuarioDTO entidad) {

		usuarioService.update(entidad);
		
		return ResponseEntity.ok().body(entidad);
	}
	
	
}
