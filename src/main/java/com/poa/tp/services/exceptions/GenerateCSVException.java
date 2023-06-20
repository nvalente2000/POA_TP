package com.poa.tp.services.exceptions;

public class GenerateCSVException extends ServiceException{

	private static final long serialVersionUID = 1L;

	public GenerateCSVException() {
		super();
	}

	public GenerateCSVException(String message, Throwable cause) {
		super(message, cause);
	}

	public GenerateCSVException(String message) {
		super(message);
	}

	public GenerateCSVException(Throwable cause) {
		super(cause);
	}	
}
