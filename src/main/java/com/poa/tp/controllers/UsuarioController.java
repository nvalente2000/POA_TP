package com.poa.tp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poa.tp.entities.Usuario;
import com.poa.tp.services.UsuarioService;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioController {

	@Autowired 
	private UsuarioService usuarioService;

	@GetMapping
	public List<Usuario> listar() {
		
		List<Usuario> lista = usuarioService.buscarTodos();
		
		return lista; 
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> buscar(@PathVariable Integer id) {

		Usuario obj = usuarioService.buscar(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
}
