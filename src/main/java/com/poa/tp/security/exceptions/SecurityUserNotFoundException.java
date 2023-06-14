package com.poa.tp.security.exceptions;

public class SecurityUserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public SecurityUserNotFoundException() {
		super();
	}

	public SecurityUserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public SecurityUserNotFoundException(String message) {
		super(message);
	}

	public SecurityUserNotFoundException(Throwable cause) {
		super(cause);
	}	
}
