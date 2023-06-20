package com.poa.tp.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	

	@Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
       
    		http							
    			.authorizeHttpRequests( auth -> {			 
        			auth.requestMatchers(PathRequest.toH2Console()).permitAll();    

    				auth.requestMatchers("/usuarios/").permitAll();
    				auth.requestMatchers("/usuarios/find/{dni}").permitAll(); 
    				auth.requestMatchers("/usuarios/save").hasAnyAuthority("ADMIN");
    				auth.requestMatchers("/usuarios/saveAll").hasAnyAuthority("ADMIN");
    				auth.requestMatchers("/usuarios/delete/{dni}").hasAnyAuthority("ADMIN");
    				auth.requestMatchers("/usuarios/update").hasAnyAuthority("ADMIN");

    				auth.requestMatchers("/pacientes/").hasAnyAuthority("ADMIN", "PACIENTE");
    				auth.requestMatchers("/pacientes/find/{dni}").hasAnyAuthority("ADMIN", "PACIENTE");
    				auth.requestMatchers("/pacientes/save").hasAnyAuthority("ADMIN");
    				auth.requestMatchers("/pacientes/saveAll").hasAnyAuthority("ADMIN");
    				auth.requestMatchers("/pacientes/delete/{dni}").hasAnyAuthority("ADMIN");
    				auth.requestMatchers("/pacientes/update").hasAnyAuthority("ADMIN");    				
    				
    				auth.requestMatchers("/terapistas/").hasAnyAuthority("ADMIN", "TERAPISTA");
    				auth.requestMatchers("/terapistas/find/{dni}").hasAnyAuthority("ADMIN", "TERAPISTA");
    				auth.requestMatchers("/terapistas/save").hasAnyAuthority("ADMIN");
    				auth.requestMatchers("/terapistas/saveAll").hasAnyAuthority("ADMIN");
    				auth.requestMatchers("/terapistas/delete/{dni}").hasAnyAuthority("ADMIN");
    				auth.requestMatchers("/terapistas/update").hasAnyAuthority("ADMIN");    		
    				
    				auth.requestMatchers("/turnos/").hasAnyAuthority("ADMIN", "PACIENTE", "TERAPISTA");
    				auth.requestMatchers("/turnos/find/{date}").hasAnyAuthority("ADMIN", "PACIENTE", "TERAPISTA");
    				auth.requestMatchers("/turnos/save").hasAnyAuthority("ADMIN", "PACIENTE");
    				auth.requestMatchers("/turnos/saveAll").hasAnyAuthority("ADMIN", "PACIENTE");
    				auth.requestMatchers("/turnos/delete/{date}").hasAnyAuthority("ADMIN", "PACIENTE");
    				auth.requestMatchers("/turnos/update").hasAnyAuthority("ADMIN", "PACIENTE");

    				auth.requestMatchers("/clinica/findAllFreeShifts").hasAnyAuthority("ADMIN", "PACIENTE");
    				auth.requestMatchers("/clinica/findAllFreeShiftsByTerapist/{dniTerapista}").hasAnyAuthority("ADMIN", "PACIENTE");
    				auth.requestMatchers("/clinica/findAllFreeShiftsByPeriodo/{periodo}").hasAnyAuthority("ADMIN", "PACIENTE");
    				auth.requestMatchers("/clinica/reservar").hasAnyAuthority("ADMIN", "PACIENTE");
    				auth.requestMatchers("/clinica/cancelar/{date}").hasAnyAuthority("ADMIN", "PACIENTE");
    				auth.requestMatchers("/clinica/confirmarTurno/{date}").hasAnyAuthority("ADMIN", "TERAPISTA");
    				auth.requestMatchers("/clinica/findAllShiftsByTerapist/{dniTerapista}").hasAnyAuthority("ADMIN", "TERAPISTA");
    			  			
    				auth.requestMatchers("/clinica/getHistoryToCSV").hasAnyAuthority("ADMIN");
    				
    				auth.anyRequest().authenticated();    			
    			})
    			.csrf((csrf) -> csrf.disable())   			
		        .headers((headers) -> headers.frameOptions((frame) -> frame.sameOrigin()))
		        .httpBasic(Customizer.withDefaults());
		
    		return http.build();
	}    	
}

