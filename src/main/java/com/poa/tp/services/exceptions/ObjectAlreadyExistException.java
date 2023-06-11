package com.poa.tp.services.exceptions;

public class ObjectAlreadyExistException extends ServiceException{

	private static final long serialVersionUID = 1L;

	public ObjectAlreadyExistException() {
		super();
	}

	public ObjectAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectAlreadyExistException(String message) {
		super(message);
	}

	public ObjectAlreadyExistException(Throwable cause) {
		super(cause);
	}	
}
