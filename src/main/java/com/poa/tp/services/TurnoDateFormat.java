package com.poa.tp.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.poa.tp.services.exceptions.FromatoFechaReqInvalidoException;

public class TurnoDateFormat {
	
	private static DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");		
	
	public static LocalDateTime toDateTimeFormat(String date) {
		try {
			return LocalDateTime.parse(date, fmt1);			
			
		} catch (DateTimeParseException e) {
			throw new FromatoFechaReqInvalidoException("Formato fecha request invalido: " + date + ". Formato esperado: dd-MM-yyyy hh:mm", e);
		} 		
	}
	
	public static String toStrimgDateTimeFormat(LocalDateTime date) {
		return date.format(fmt1);
	}	
	
	public static String getCurrentDate() {
		return LocalDateTime.now().format(fmt1);
	}	
	
	public static String getCurrentDatePlusDays(int days) {
		return LocalDateTime.now().plusDays(days).format(fmt1);
	}	
}

