package com.poa.tp.entities.exceptions;

public class EntidadesException extends Exception{

	private static final long serialVersionUID = 1L;

	public EntidadesException() {
		super();
	}

	public EntidadesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EntidadesException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntidadesException(String message) {
		super(message);
	}

	public EntidadesException(Throwable cause) {
		super(cause);
	}
}
