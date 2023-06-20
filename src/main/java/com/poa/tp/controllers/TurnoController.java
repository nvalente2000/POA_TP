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

import com.poa.tp.dto.TurnoReqSaveDTO;
import com.poa.tp.dto.TurnoReqUpdateDTO;
import com.poa.tp.dto.TurnoRespDTO;
import com.poa.tp.entities.Turno;
import com.poa.tp.services.PacienteService;
import com.poa.tp.services.TerapistaService;
import com.poa.tp.services.TurnoService;


@RestController
@RequestMapping(value="/turnos")
public class TurnoController {
	
	@Autowired 
	private TurnoService turnoService;

	@Autowired 
	private TerapistaService terapistaService;
	
	@Autowired 
	private PacienteService pacienteService;
	
	
	@GetMapping(value="/")
	public ResponseEntity<?> findAll() {
		
		List<Turno> lista = turnoService.getAll();
		
		List<TurnoRespDTO> listaResponse = lista
				.stream()
				.map(entidad->new TurnoRespDTO(entidad))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaResponse); 
	}
	
	@GetMapping(value="/find/{date}")
	public ResponseEntity<?> find(@PathVariable String date) {
		
		Turno obj = turnoService.getOne(date);
		
		TurnoRespDTO usuarioResponse = new TurnoRespDTO(obj);
		
		return ResponseEntity.ok().body(usuarioResponse);
	}
	
	@PostMapping(value="/save")
	public ResponseEntity<?> save(@RequestBody TurnoReqSaveDTO entidadReqDto) {

		Turno entidad = entidadReqDto.toTurno(terapistaService, pacienteService);
		
		turnoService.save(entidad);
		
		return ResponseEntity.ok().body(entidadReqDto);		
	}

	@PostMapping(value="/saveAll")
	public ResponseEntity<?> saveAll(@RequestBody List<TurnoReqSaveDTO> listaRequest) {
		
		List<Turno> lista = listaRequest
						.stream()
						.map (entidad -> entidad.toTurno(terapistaService, pacienteService))
						.collect(Collectors.toList());
				
		turnoService.saveAll(lista);
				
		return ResponseEntity.ok().body(listaRequest);				
	}
	
	
	@DeleteMapping(value="/delete/{date}")
	public ResponseEntity<?> delete(@PathVariable String date) {
		
		turnoService.delete(date);
		
		return ResponseEntity.ok().body(date);
	}
	
	
	@PutMapping(value="/update")
	public ResponseEntity<?> update(@RequestBody TurnoReqUpdateDTO entidadReqDto) {

		Turno entidad = entidadReqDto.toTurno(turnoService);
		
		turnoService.update(entidad);
		
		return ResponseEntity.ok().body(entidadReqDto);
	}
	
}
