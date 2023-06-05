package com.poa.tp.entities.enums;

import com.poa.tp.entities.exceptions.PeriodoAtencionException;

public enum PeriodoAtencion {

	// Nota: Hacemos esto para controlar los valores numericos internos 
	//     de TipoPatologia. De esta forma no depende de valores autogeneradoes. 
	TURNO_09_A_12 	(1, "Turno Manana"),
	TURNO_14_A_18	(2, "Turno Tarde"),
	TURNO_18_A_22	(3, "Turno nochhe.");

	private int codigo; 
	private String descripcion;
	
	private PeriodoAtencion(int codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public static PeriodoAtencion toEnum(Integer cod) throws PeriodoAtencionException {
		
		if (cod == null)
			return null;
		
		for ( PeriodoAtencion x : PeriodoAtencion.values() ) {
			
			if (cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new PeriodoAtencionException("Id estado invalido");
	
	}
	
}
