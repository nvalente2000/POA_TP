package com.poa.tp.entities.exceptions;

public class PeriodoAtencionException extends EntidadesException {

	private static final long serialVersionUID = 1L;

	public PeriodoAtencionException() {
		super();
	}

	public PeriodoAtencionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PeriodoAtencionException(String message, Throwable cause) {
		super(message, cause);
	}

	public PeriodoAtencionException(String message) {
		super(message);
	}

	public PeriodoAtencionException(Throwable cause) {
		super(cause);
	}	
}
