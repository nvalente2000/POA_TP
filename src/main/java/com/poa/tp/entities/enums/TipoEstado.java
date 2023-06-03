package com.poa.tp.entities.enums;

import com.poa.tp.entities.exceptions.EstadoTurnoException;

public enum TipoEstado {

	LIBRE		(1, "Turno Libre"),
	RESERVADO	(2, "Turno reservado"),
	CONFIRMADA	(3, "Turno confirmado. El paciente asistio a la consulta."),
	AUSENTE		(4, "Turno Ausente. El no paciente asistio a la consulta."); 
	
	private int codigo; 
	private String descripcion;
	
	private TipoEstado(int codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public static TipoEstado toEnum(Integer cod) throws EstadoTurnoException {
		
		if (cod == null)
			return null;
		
		for ( TipoEstado x : TipoEstado.values() ) {
			
			if (cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new EstadoTurnoException("Id estado invalido");
	
	}
	
}
