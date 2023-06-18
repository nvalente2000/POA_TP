package com.poa.tp.controllers;

import java.util.List;
import java.util.stream.Collectors;

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

import com.poa.tp.dto.UsuarioReqDTO;
import com.poa.tp.dto.UsuarioRespDTO;
import com.poa.tp.entities.Usuario;
import com.poa.tp.services.UsuarioService;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioController {
	
	@Autowired 
	private UsuarioService usuarioService;

	@GetMapping(value="/")
	public ResponseEntity<?> findAll() {
		
		List<Usuario> lista = usuarioService.getAll();
		
		List<UsuarioRespDTO> listaResponse = lista
				.stream()
				.map(entidad->new UsuarioRespDTO(entidad))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaResponse); 
	}
	
	@GetMapping(value="/find/{dni}")
	public ResponseEntity<?> find(@PathVariable String dni) {

		Usuario obj = usuarioService.getOne(dni);
		
		UsuarioRespDTO usuarioResponse = new UsuarioRespDTO(obj);
		
		return ResponseEntity.ok().body(usuarioResponse);
	}
	
	@PostMapping(value="/save")
	public ResponseEntity<?> save(@RequestBody UsuarioReqDTO entidadRequestDTO) {

		Usuario entidad = entidadRequestDTO.toUsuario();
		
		usuarioService.save(entidad);
		
		return ResponseEntity.ok().body(entidad);		
	}

	@PostMapping(value="/saveAll")
	public ResponseEntity<?> saveAll(@RequestBody List<UsuarioReqDTO> listaRequest) {

		List<Usuario> lista = listaRequest
				.stream()
				.map (entidad -> entidad.toUsuario())
				.collect(Collectors.toList());
		
		usuarioService.saveAll(lista);
		
		return ResponseEntity.ok().body(lista);		
	}
	
	@DeleteMapping(value="/delete/{dni}")
	public ResponseEntity<?> delete(@PathVariable String dni) {

		usuarioService.delete(dni);
		
		return ResponseEntity.ok().body(dni);
	}
	
	@PutMapping(value="/update")
	public ResponseEntity<?> update(@RequestBody UsuarioReqDTO entidadRequestDto) {

		Usuario entidad = entidadRequestDto.toUsuario();
		
		usuarioService.update(entidad);
		
		return ResponseEntity.ok().body(entidad);
	}
	
}
