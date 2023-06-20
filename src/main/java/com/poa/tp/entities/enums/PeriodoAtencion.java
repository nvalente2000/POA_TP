package com.poa.tp.entities.enums;

import com.poa.tp.entities.exceptions.PeriodoAtencionException;

public enum PeriodoAtencion {

	TURNO_09_A_12 		(1, "Turno Manana",			9 , 0 , 12, 0 ), 
	TURNO_14_A_18		(2, "Turno Tarde", 			14, 0, 18, 0 ),
	TURNO_18_A_22		(3, "Turno nochhe.", 		18, 0 ,22 , 0),
	TURNO_NO_ASIGNADO	(4, "Turno no asignado.", 	0, 0 ,0 , 0); 
	
	private int codigo; 
	private String descripcion;
	private int hhDesde; 
	private int mmDesde;
	private int hhHasta;
	private int mmHasta;
	
	
	private PeriodoAtencion(int codigo, String descripcion, int hhDesde, int mmDesde, int hhHasta, int mmHasta) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.hhDesde = hhDesde;
		this.mmDesde = mmDesde;
		this.hhHasta = hhHasta;
		this.mmHasta = mmHasta;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getHhDesde() {
		return hhDesde;
	}

	public void setHhDesde(int hhDesde) {
		this.hhDesde = hhDesde;
	}

	public int getMmDesde() {
		return mmDesde;
	}

	public void setMmDesde(int mmDesde) {
		this.mmDesde = mmDesde;
	}

	public int getHhHasta() {
		return hhHasta;
	}

	public void setHhHasta(int hhHasta) {
		this.hhHasta = hhHasta;
	}

	public int getMmHasta() {
		return mmHasta;
	}

	public void setMmHasta(int mmHasta) {
		this.mmHasta = mmHasta;
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
