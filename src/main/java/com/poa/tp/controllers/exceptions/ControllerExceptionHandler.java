package com.poa.tp.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.poa.tp.services.exceptions.LoginUnauthorizedException;
import com.poa.tp.services.exceptions.ObjectAlreadyExistException;
import com.poa.tp.services.exceptions.ObjectNotFoundException;
import com.poa.tp.services.exceptions.ServiceException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler (ObjectNotFoundException.class)
	public ResponseEntity<StandardErrorResponse> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardErrorResponse err = new StandardErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler (LoginUnauthorizedException.class)
	public ResponseEntity<StandardErrorResponse> loginUnauthorized(LoginUnauthorizedException e, HttpServletRequest request){
		
		StandardErrorResponse err = new StandardErrorResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);		
	}


	@ExceptionHandler (ObjectAlreadyExistException.class)
	public ResponseEntity<StandardErrorResponse> objectAlreadyExist(ObjectAlreadyExistException e, HttpServletRequest request){
		
		StandardErrorResponse err = new StandardErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		
	}
	
	@ExceptionHandler (ServiceException.class)
	public ResponseEntity<StandardErrorResponse> serviceError(ServiceException e, HttpServletRequest request){
		
		StandardErrorResponse err = new StandardErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
		
	}
	

	
}
