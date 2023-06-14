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
    				auth.requestMatchers("/usuarios/find/{dni}").hasAnyAuthority("ADMIN"); 
    				auth.requestMatchers("/usuarios/save").hasAnyAuthority("ADMIN");
    				auth.requestMatchers("/usuarios/saveAll").hasAuthority("ADMIN");
    				auth.requestMatchers("/usuarios/delete/{dni}").hasAnyAuthority("ADMIN");
    				auth.requestMatchers("/usuarios/update").hasAnyAuthority("ADMIN");
    				auth.anyRequest().authenticated();    			
    			})
    			.csrf((csrf) -> csrf.disable())
		        .headers((headers) -> headers.frameOptions((frame) -> frame.sameOrigin()))
		        .httpBasic(Customizer.withDefaults());
		
    		return http.build();
	}    	
}

