package com.poa.tp.services.exceptions;

public class InvalidEntityDataException extends ServiceException{

	private static final long serialVersionUID = 1L;

	public InvalidEntityDataException() {
		super();
	}

	public InvalidEntityDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidEntityDataException(String message) {
		super(message);
	}

	public InvalidEntityDataException(Throwable cause) {
		super(cause);
	}	
}
