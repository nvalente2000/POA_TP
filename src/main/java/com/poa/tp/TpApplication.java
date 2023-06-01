package com.poa.tp;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.poa.tp.entities.Paciente;
import com.poa.tp.entities.Turno;
import com.poa.tp.services.PacienteService;
import com.poa.tp.services.TurnoService;

@SpringBootApplication
public class TpApplication implements CommandLineRunner{

	@Autowired 
	private PacienteService pacienteService;
	
	@Autowired 
	private TurnoService turnoService;

	
	public static void main(String[] args) {
		SpringApplication.run(TpApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Entidades
		Turno t1 = new Turno(null,null,30 );
		Turno t2 = new Turno(null,null,45 );
		Turno t3 = new Turno(null,null,40 );
		Paciente p1 = new Paciente(null, "29040676", "Valente Nicolas", "123", false );
		Paciente p2 = new Paciente(null, "29040675", "Valente Ezequiel", "1234", false );

		// Relaciono
		t1.setPaciente(p1);
		t2.setPaciente(p1);
		t3.setPaciente(p2);
				
		// Guardo
		pacienteService.guardarTodos(Arrays.asList(p1, p2));
		turnoService.guardarTodos(Arrays.asList(t1, t2, t3) );

		
	}
}