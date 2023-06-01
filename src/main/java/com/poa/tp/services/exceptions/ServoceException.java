package com.poa.tp.services.exceptions;

public class ServoceException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ServoceException() {
		super();
	}

	public ServoceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServoceException(String message) {
		super(message);
	}

	public ServoceException(Throwable cause) {
		super(cause);
	}
	
}
