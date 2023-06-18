package com.poa.tp;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.poa.tp.entities.Paciente;
import com.poa.tp.entities.Terapista;
import com.poa.tp.entities.Turno;
import com.poa.tp.entities.Usuario;
import com.poa.tp.entities.enums.PeriodoAtencion;
import com.poa.tp.entities.enums.TipoPatologia;
import com.poa.tp.services.PacienteService;
import com.poa.tp.services.TerapistaService;
import com.poa.tp.services.TurnoService;
import com.poa.tp.services.UsuarioService;

//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@SpringBootApplication
//@ComponentScan(basePackages={"com.poa.tp.controllers"})
public class TpApplication implements CommandLineRunner{

	@Autowired
	private UsuarioService usuarioService;
/*	@Autowired 
	private PacienteService pacienteService;
	@Autowired
	private TerapistaService terapistaService;
	@Autowired
	private TurnoService turnoService;*/
	
	
	public static void main(String[] args) {
		SpringApplication.run(TpApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Creo Usario sysadmin
		Usuario usuario = new Usuario(null, "sysadmin", "sysadmin@sysadmin.com", "sysadmin", "sysadmin", "sysadmin" , Arrays.asList("ADMIN"));		
		usuarioService.save(usuario);
		/*
		// Creo paciente
		Paciente paciente = new Paciente(null, true, usuario);
		usuario.setPaciente(paciente);
		pacienteService.save(paciente);
		
		// Creo Terapista
		Terapista terapista = new Terapista(null, PeriodoAtencion.TURNO_NO_ASIGNADO , usuario);
		usuario.setTerapista(terapista);
		terapistaService.save(terapista);
		

		// Creo un turno
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm"); //Mascara de formatacion
		Turno turno = new Turno(null,sdf.parse("30/02/2023 13:30"),30, TipoPatologia.INFECCION_OIDO, paciente , terapista);
		terapista.getTurnos().add(turno);
		paciente.getTurnos().add(turno);
		
		turnoService.save(turno);*/
		
		/*
		UsuarioDTO testUDTO = new UsuarioDTO(null, "29040676", "Valente Nicolas", "123" );
		
		// Relaciono OneToMany
		p1.getTurnos().addAll(Arrays.asList(t1, t2));
		p2.getTurnos().addAll(Arrays.asList(t3));
		te1.getTurnos().addAll(Arrays.asList(t1, t2, t3));
		*/
		
	}
}
