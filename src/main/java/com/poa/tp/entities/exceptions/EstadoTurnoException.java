package com.poa.tp.entities.exceptions;

public class EstadoTurnoException extends EntidadesException {

	private static final long serialVersionUID = 1L;

	public EstadoTurnoException() {
		super();
	}

	public EstadoTurnoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EstadoTurnoException(String message, Throwable cause) {
		super(message, cause);
	}

	public EstadoTurnoException(String message) {
		super(message);
	}

	public EstadoTurnoException(Throwable cause) {
		super(cause);
	}	
}
