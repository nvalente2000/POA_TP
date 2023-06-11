package com.poa.tp.entities.enums;

import com.poa.tp.entities.exceptions.TipoPagologiaException;

public enum TipoPatologia {

	BRONQUITS_AGUDA 	(1, "Bronquitis Aguda (resfriado de pech."),
	RESFRIADO_COMUN 	(2, "Resfriado comun." ),
	INFECCION_OIDO		(3, "Infeccion de Oido."),
	INFLUENZA			(4, "Influenza (gripe)."), 
	SINUSITIS			(5, "Sinusitis (infección de los senos paranasales."),
	INFECCION_PELE		(6, "Infecciones de la piel."),
	DOLOR_GARGANTA		(7, "Dolor de garganta."),
	INFECCION_URINARIA	(8, "Infección urinaria."), 
	OTRO				(8, "Otro");
	
	private int codigo; 
	private String descripcion;
	
	private TipoPatologia(int codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public static TipoPatologia toEnum(Integer cod) throws TipoPagologiaException {
		
		if (cod == null)
			return null;
		
		for ( TipoPatologia x : TipoPatologia.values() ) {
			
			if (cod.equals(x.getCodigo())) {
				return x;
			}
		}	
		throw new TipoPagologiaException("Id invalido");
			
	}
	
}
