package com.poa.tp;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.poa.tp.entities.Paciente;
import com.poa.tp.entities.Turno;
import com.poa.tp.entities.enums.TipoPatologia;
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

		// Entidades & Relacion ManyToOne
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm"); //Mascara de formatacion
		
		Paciente p1 = new Paciente(null, "29040676", "Valente Nicolas", "123", false );
		Paciente p2 = new Paciente(null, "29040675", "Valente Ezequiel", "1234", false );
		Turno t1 = new Turno(null,sdf.parse("30/02/2023 13:30"),30, TipoPatologia.INFECCION_OIDO, p1 );
		Turno t2 = new Turno(null,sdf.parse("30/02/2023 14:30"),45, TipoPatologia.INFECCION_URINARIA , p1);
		Turno t3 = new Turno(null,sdf.parse("30/02/2023 15:30"),40, TipoPatologia.SINUSITIS , p2);
				
		// Relaciono OneToMany
		p1.getTurnos().addAll(Arrays.asList(t1, t2));
		p2.getTurnos().addAll(Arrays.asList(t3));
				
		// Guardo
		pacienteService.guardarTodos(Arrays.asList(p1, p2));
		turnoService.guardarTodos(Arrays.asList(t1, t2, t3) );

		
	}
}
