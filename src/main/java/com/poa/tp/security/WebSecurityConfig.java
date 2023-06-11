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
        			
    				auth.requestMatchers("/usuarios/all").permitAll();
    				auth.requestMatchers("/usuarios/id/{id}").hasAnyAuthority("ADMIN");
    				auth.requestMatchers("/usuarios/dni/{dni}").hasAnyAuthority("ADMIN"); 
    				auth.requestMatchers("/usuarios/email/{email}").hasAnyAuthority("ADMIN");
    				auth.requestMatchers("/usuarios/save").hasAnyAuthority("ADMIN");
    				auth.requestMatchers("/usuarios/saveAll").hasAuthority("ADMIN");
    				
    				//auth.requestMatchers("/usuarios/dni/{dni}").hasAnyAuthority("PACIENTE", "TERAPISTA");
    				auth.anyRequest().authenticated();    			
    			})
    			.csrf((csrf) -> csrf.disable())
		        .headers((headers) -> headers.frameOptions((frame) -> frame.sameOrigin()))
		        .httpBasic(Customizer.withDefaults());
		
    		return http.build();
	}    	

/*  DEPRECATED
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests()
				.requestMatchers(PathRequest.toH2Console()).permitAll()
				.requestMatchers("/usuarios/all").permitAll()
				.requestMatchers("/usuarios/id/{id}").permitAll()
				.requestMatchers("/usuarios/dni/{dni}").permitAll()
				.requestMatchers("/usuarios/email/{email}").permitAll()
				//.requestMatchers("/usuarios/save").hasAuthority("ADMIN")
				.requestMatchers("/usuarios/saveAll").permitAll()
				.anyRequest().authenticated()
				.and()
				.csrf().disable()
				.httpBasic();
		return http.build();
	}	
*/

}

