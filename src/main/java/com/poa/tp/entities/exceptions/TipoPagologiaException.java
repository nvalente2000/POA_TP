package com.poa.tp.entities.exceptions;

public class TipoPagologiaException extends EntidadesException {

	private static final long serialVersionUID = 1L;

	public TipoPagologiaException() {
		super();
	}

	public TipoPagologiaException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TipoPagologiaException(String message, Throwable cause) {
		super(message, cause);
	}

	public TipoPagologiaException(String message) {
		super(message);
	}

	public TipoPagologiaException(Throwable cause) {
		super(cause);
	}	
}
