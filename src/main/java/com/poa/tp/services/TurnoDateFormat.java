package com.poa.tp.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.poa.tp.services.exceptions.FromatoFechaReqInvalidoException;

public class TurnoDateFormat {
	
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm"); //Mascara de formatacion
	
	
	public static Date toDateForma(String date) {
		try {
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			throw new FromatoFechaReqInvalidoException("Formato fecha request invalido" + date + ". Formato esperado: dd-MM-yyyy hh:mm", e);
		} 		
	}

	public static String toStromgFormat(Date date) {
		return simpleDateFormat.format(date); 
	}
}
