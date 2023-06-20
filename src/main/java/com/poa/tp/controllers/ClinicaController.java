package com.poa.tp.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.poa.tp.dto.TurnoListaLibresRespDTO;
import com.poa.tp.dto.TurnoReqSaveDTO;
import com.poa.tp.dto.TurnoRespDTO;
import com.poa.tp.entities.Turno;
import com.poa.tp.entities.enums.PeriodoAtencion;
import com.poa.tp.services.ClinicaService;
import com.poa.tp.services.PacienteService;
import com.poa.tp.services.TerapistaService;
import com.poa.tp.services.TurnoService;
import com.poa.tp.services.exceptions.GenerateCSVException;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value="/clinica")
public class ClinicaController {
	
	
	@Autowired 
	private ClinicaService clinicaService;
	
	@Autowired 
	private TerapistaService terapistaService;

	@Autowired 
	private PacienteService pacienteService;
	
	@Autowired 
	private TurnoService turnoService;
	
		
	@GetMapping(value="/findAllFreeShifts")
	public ResponseEntity<?> findAllFreeShifts() {
		
		List<Turno> lista = clinicaService.getTurnosLibres();
		
		List<TurnoListaLibresRespDTO> listaResponse = lista
				.stream()
				.map(entidad->new TurnoListaLibresRespDTO(entidad))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaResponse); 
	}
	
	
	@GetMapping(value="/findAllFreeShiftsByTerapist/{dniTerapista}")
	public ResponseEntity<?> findAllFreeShiftsByTerapist( @PathVariable("dniTerapista") String dniTerapista) {

		List<Turno> lista = clinicaService.getTurnosLibresPorTerapista(dniTerapista);
		
		List<TurnoListaLibresRespDTO> listaResponse = lista
				.stream()
				.map(entidad->new TurnoListaLibresRespDTO(entidad))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaResponse); 
	}
	
	
	@GetMapping(value="/findAllFreeShiftsByPeriodo/{periodo}")
	public ResponseEntity<?> findAllFreeShiftsByPeriodo( @PathVariable("periodo") PeriodoAtencion periodo) {
		
		List<Turno> lista = clinicaService.getTurnosLibresPorPeriodo(periodo);
		
		List<TurnoListaLibresRespDTO> listaResponse = lista
				.stream()
				.map(entidad->new TurnoListaLibresRespDTO(entidad))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaResponse); 
	}
	
	
	@PostMapping(value="/reservar")
	public ResponseEntity<?> reservar(@RequestBody TurnoReqSaveDTO entidadReqDto) {

		Turno entidad = entidadReqDto.toTurno(terapistaService, pacienteService);
		
		clinicaService.reservar(entidad);
		
		return ResponseEntity.ok().body(entidadReqDto);		
	}
		
	
	@PutMapping(value="/cancelar/{date}")
	public ResponseEntity<?> cancelar(@PathVariable String date) {
		
		clinicaService.cancelar(date);
		
		return ResponseEntity.ok().body(date);
	}
	
	@PutMapping(value="/confirmarTurno/{date}")
	public ResponseEntity<?> confirmarTurno(@PathVariable String date) {
		
		clinicaService.confirmarTurno(date);
		
		return ResponseEntity.ok().body(date);
	}
	
	
	@GetMapping(value="/findAllShiftsByTerapist/{dniTerapista}")
	public ResponseEntity<?> findAllShiftsByTerapist( @PathVariable("dniTerapista") String dniTerapista) {

		List<Turno> lista = clinicaService.getTurnosPorTerapista(dniTerapista);
		
		List<TurnoListaLibresRespDTO> listaResponse = lista
				.stream()
				.map(entidad->new TurnoListaLibresRespDTO(entidad))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaResponse); 
	}

	@GetMapping(value="/getHistoryToCSV")
	public void getHistoricoTurnosEnCSV(HttpServletResponse response) {
		
		// Data
		List<Turno> lista = turnoService.getAll();
		
		List<TurnoRespDTO> listaResponse = lista
				.stream()
				.map(entidad->new TurnoRespDTO(entidad))
				.collect(Collectors.toList());
		
		// Formato arquivo
		response.setContentType("text/csv");
		String csvFileName = "historyTurnos.csv";
		String headerKey = "Content-Disposition";
	    String headerValue = String.format("attachment; filename=\"%s\"",  csvFileName);
	    response.setHeader(headerKey, headerValue);

	    // Creo el archivo
		String[] csvheader = {"FechaHoraTurno", "Duracion", "TipoPatologia", "Estado", "DniPaciente", "DniTerapista" };
		String[] bameMapping = {"fechaHoraTurno", "duracion", "tipoPatologia", "estado", "dniPaciente", "dniTerapista" };

		ICsvBeanWriter csvWriter; 
		try {
			csvWriter =  new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
			csvWriter.writeHeader(csvheader);
			for (TurnoRespDTO turnoDTO : listaResponse) {
				csvWriter.write(turnoDTO, bameMapping);
		     }
			csvWriter.close();
			
		} catch (IOException e) {
			throw new GenerateCSVException("No fue posible generar el historico en CSV");
		}
	}
}
