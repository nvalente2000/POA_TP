package com.poa.tp.services.exceptions;

public class FromatoFechaReqInvalidoException extends ServiceException{

	private static final long serialVersionUID = 1L;

	public FromatoFechaReqInvalidoException() {
		super();
	}

	public FromatoFechaReqInvalidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public FromatoFechaReqInvalidoException(String message) {
		super(message);
	}

	public FromatoFechaReqInvalidoException(Throwable cause) {
		super(cause);
	}	
}
