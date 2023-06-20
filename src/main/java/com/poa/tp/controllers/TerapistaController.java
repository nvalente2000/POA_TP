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

import com.poa.tp.dto.TerapistaReqDTO;
import com.poa.tp.dto.TerapistaRespDTO;
import com.poa.tp.entities.Terapista;
import com.poa.tp.services.TerapistaService;

@RestController
@RequestMapping(value="/terapistas")
public class TerapistaController {

	@Autowired 
	private TerapistaService terapistaService;
	

	@GetMapping(value="/")
	public ResponseEntity<?> findAll() {
		
		List<Terapista> lista = terapistaService.getAll();
		
		List<TerapistaRespDTO> listaResponse = lista
				.stream()
				.map(entidad->new TerapistaRespDTO(entidad))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaResponse); 
	
	}
	
	@GetMapping(value="/find/{dni}")
	public ResponseEntity<?> find(@PathVariable String dni) {
		
		Terapista obj = terapistaService.getOne(dni);
		
		TerapistaRespDTO entidadResponse = new TerapistaRespDTO(obj);
		
		return ResponseEntity.ok().body(entidadResponse);

	}
	
	@PostMapping(value="/save")
	public ResponseEntity<?> save(@RequestBody TerapistaReqDTO entidadReqDto) {

		Terapista entidad = entidadReqDto.toTerapista();
		
		terapistaService.save(entidad);
		
		return ResponseEntity.ok().body(entidadReqDto);		
	}

	@PostMapping(value="/saveAll")
	public ResponseEntity<?> saveAll(@RequestBody List<TerapistaReqDTO> listaRequest) {
		
		List<Terapista> lista = listaRequest
						.stream()
						.map (entidad -> entidad.toTerapista())
						.collect(Collectors.toList());
				
		terapistaService.saveAll(lista);
				
		return ResponseEntity.ok().body(listaRequest);				
		
	}
	
	@DeleteMapping(value="/delete/{dni}")
	public ResponseEntity<?> delete(@PathVariable String dni) {

		terapistaService.delete(dni);
		
		return ResponseEntity.ok().body(dni);
	}
	
	@PutMapping(value="/update")
	public ResponseEntity<?> update(@RequestBody TerapistaReqDTO entidadReqDto) {

		Terapista entidad = entidadReqDto.toTerapista();
		
		terapistaService.update(entidad);
		
		return ResponseEntity.ok().body(entidadReqDto);
	}

	
}
