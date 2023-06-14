package com.poa.tp.security.exceptions;

public class SecurityFilterChainException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public SecurityFilterChainException() {
		super();
	}

	public SecurityFilterChainException(String message, Throwable cause) {
		super(message, cause);
	}

	public SecurityFilterChainException(String message) {
		super(message);
	}

	public SecurityFilterChainException(Throwable cause) {
		super(cause);
	}	
}
