package com.poa.tp.services.exceptions;

public class TurnoServiceException extends ServiceException{

	private static final long serialVersionUID = 1L;

	public TurnoServiceException() {
		super();
	}

	public TurnoServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public TurnoServiceException(String message) {
		super(message);
	}

	public TurnoServiceException(Throwable cause) {
		super(cause);
	}	
}
