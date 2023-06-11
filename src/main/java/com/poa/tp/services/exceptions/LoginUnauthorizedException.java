package com.poa.tp.services.exceptions;

public class LoginUnauthorizedException extends ServiceException{

	private static final long serialVersionUID = 1L;

	public LoginUnauthorizedException() {
		super();
	}

	public LoginUnauthorizedException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoginUnauthorizedException(String message) {
		super(message);
	}

	public LoginUnauthorizedException(Throwable cause) {
		super(cause);
	}	
}
