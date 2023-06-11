package com.poa.tp;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.poa.tp.dto.UsuarioDTO;
import com.poa.tp.services.UsuarioService;

//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@SpringBootApplication
//@ComponentScan(basePackages={"com.poa.tp.controllers"})
public class TpApplication implements CommandLineRunner{

//	@Autowired 
//	private PacienteService pacienteService;
//	@Autowired
//	private TurnoService turnoService;
//	@Autowired
//	private TerapistaService terapistaService;
//	@Autowired
//	private AdministradorService administradorService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public static void main(String[] args) {
		SpringApplication.run(TpApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Usuario ADMIN INICIAL
		UsuarioDTO u = new UsuarioDTO(null, "sysadmin", "sysadmin@sysadmin.com", "sysadmin", "sysadmin", "sysadmin" , Arrays.asList("ADMIN","TERAPISTA"));
		usuarioService.save(u);

			
		/*
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm"); //Mascara de formatacion
		
		Paciente p1 = new Paciente(null, false,  u1);
		Paciente p2 = new Paciente(null, false , u2);
		Terapista te1 = new Terapista(null, u3, PeriodoAtencion.TURNO_09_A_12 );
		Administrador adm1 = new Administrador(null, u3);
		Turno t1 = new Turno(null,sdf.parse("30/02/2023 13:30"),30, TipoPatologia.INFECCION_OIDO, p1 , te1);
		Turno t2 = new Turno(null,sdf.parse("30/02/2023 14:30"),45, TipoPatologia.INFECCION_URINARIA , p1, te1);
		Turno t3 = new Turno(null,sdf.parse("30/02/2023 15:30"),40, TipoPatologia.SINUSITIS , p2, te1);
	
		UsuarioDTO testUDTO = new UsuarioDTO(null, "29040676", "Valente Nicolas", "123" );
		
		// Relaciono OneToMany
		u1.getRoles().addAll(Arrays.asList( (RoleUsuario) p1));
		u2.getRoles().addAll(Arrays.asList( (RoleUsuario) p2));
		u3.getRoles().addAll(Arrays.asList( (RoleUsuario) te1));
		p1.getTurnos().addAll(Arrays.asList(t1, t2));
		p2.getTurnos().addAll(Arrays.asList(t3));
		te1.getTurnos().addAll(Arrays.asList(t1, t2, t3));
		
		// Guardo
		usuarioService.guardarTodos(Arrays.asList(u1, u2, u3));
		pacienteService.guardarTodos(Arrays.asList(p1, p2));
		terapistaService.guardarTodos(Arrays.asList(te1));
		administradorService.guardarTodos(Arrays.asList(adm1));
		turnoService.guardarTodos(Arrays.asList(t1, t2, t3) );
		usuarioService.add(testUDTO);
		*/
		
	}
}
