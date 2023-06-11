package com.poa.tp.entities.enums;

import com.poa.tp.entities.exceptions.TipoEstadoTurnoException;

public enum TipoEstadoTurno {

	LIBRE		(1, "Turno Libre"),
	RESERVADO	(2, "Turno reservado"),
	CONFIRMADA	(3, "Turno confirmado. El paciente asistio a la consulta."),
	AUSENTE		(4, "Turno Ausente. El no paciente asistio a la consulta."); 
	
	private int codigo; 
	private String descripcion;
	
	private TipoEstadoTurno(int codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public static TipoEstadoTurno toEnum(Integer cod) throws TipoEstadoTurnoException {
		
		if (cod == null)
			return null;
		
		for ( TipoEstadoTurno x : TipoEstadoTurno.values() ) {
			
			if (cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new TipoEstadoTurnoException("Id estado invalido");
	
	}
	
}
