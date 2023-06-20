package com.poa.tp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.poa.tp.entities.Usuario;
import com.poa.tp.services.UsuarioService;


@SpringBootApplication
public class TpApplication implements CommandLineRunner{

	@Autowired
	private UsuarioService usuarioService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(TpApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Default User: Sysadmin
		Usuario usuario = new Usuario(null, "sysadmin", "sysadmin@sysadmin.com", "sysadmin", "sysadmin", "sysadmin" , Arrays.asList("ADMIN"));		
		usuarioService.save(usuario);
				
	}
}
