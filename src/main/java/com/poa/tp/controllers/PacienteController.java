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

import com.poa.tp.dto.PacienteReqDTO;
import com.poa.tp.dto.PacienteRespDTO;
import com.poa.tp.entities.Paciente;
import com.poa.tp.services.PacienteService;

@RestController
@RequestMapping(value="/pacientes")
public class PacienteController {

	@Autowired 
	private PacienteService pacienteService;
	
	@GetMapping(value="/")
	public ResponseEntity<?> findAll() {
		
		List<Paciente> lista = pacienteService.getAll();
		
		List<PacienteRespDTO> listaResponse = lista
				.stream()
				.map(entidad->new PacienteRespDTO(entidad))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaResponse); 
	
	}
	
	@GetMapping(value="/find/{dni}")
	public ResponseEntity<?> find(@PathVariable String dni) {
		
		Paciente obj = pacienteService.getOne(dni);
		
		PacienteRespDTO usuarioResponse = new PacienteRespDTO(obj);
		
		return ResponseEntity.ok().body(usuarioResponse);

	}
	
	@PostMapping(value="/save")
	public ResponseEntity<?> save(@RequestBody PacienteReqDTO entidadRequestDTO) {
		
		Paciente entidad = entidadRequestDTO.toPaciente();
		
		pacienteService.save(entidad);
		
		return ResponseEntity.ok().body(entidadRequestDTO);	
	}

	@PostMapping(value="/saveAll")
	public ResponseEntity<?> saveAll(@RequestBody List<PacienteReqDTO> listaRequest) {

		List<Paciente> lista = listaRequest
					.stream()
					.map (entidad -> entidad.toPaciente())
					.collect(Collectors.toList());
			
		pacienteService.saveAll(lista);
			
		return ResponseEntity.ok().body(listaRequest);	
		
	}
	
	@DeleteMapping(value="/delete/{dni}")
	public ResponseEntity<?> delete(@PathVariable String dni) {

		pacienteService.delete(dni);
		
		return ResponseEntity.ok().body(dni);
	}
	
	@PutMapping(value="/update")
	public ResponseEntity<?> update(@RequestBody PacienteReqDTO entidadRequestDTO) {

		Paciente entidad = entidadRequestDTO.toPaciente();
		
		pacienteService.update(entidad);
		
		return ResponseEntity.ok().body(entidadRequestDTO);
	}
	
}
