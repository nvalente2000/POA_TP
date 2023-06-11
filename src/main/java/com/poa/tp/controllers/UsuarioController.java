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

import com.poa.tp.dto.UsuarioDTO;
import com.poa.tp.services.UsuarioService;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioController {

	@Autowired 
	private UsuarioService usuarioService;

	public UsuarioController() {
	}

	@GetMapping(value="/all")
	public List<UsuarioDTO> listarTodos() {
		
		List<UsuarioDTO> lista = usuarioService.getAll();
		
		return lista; 
	}
	
	@GetMapping(value="/id/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {

		UsuarioDTO obj = usuarioService.getOne(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/dni/{dni}")
	public ResponseEntity<?> buscarPorDni(@PathVariable String dni) {

		UsuarioDTO obj = usuarioService.getOneByDni(dni);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/email/{email}")
	public ResponseEntity<?> buscarPorEmail(@PathVariable String email) {

		UsuarioDTO obj = usuarioService.getOneByEmail(email);
		
		return ResponseEntity.ok().body(obj);
	}

	
	@PostMapping(value="/save")
	public ResponseEntity<?> save(@RequestBody UsuarioDTO user) {

		usuarioService.save(user);
		
		return ResponseEntity.ok().body(user);		
	}

	@PostMapping(value="/saveAll")
	public ResponseEntity<?> saveAll(@RequestBody List<UsuarioDTO> lista) {

		usuarioService.saveAll(lista);
		
		return ResponseEntity.ok().body(lista);		
	}
	
}
