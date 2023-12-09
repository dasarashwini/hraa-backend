package com.hr.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity handledINotFoundException(ResourceNotFoundException exc) {
		LocalDateTime date = LocalDateTime.now();
		ErrorDetails errorDetails = new ErrorDetails(exc.getMessage(), date);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
	}
		
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity handledAlreadyExistException(ResourceAlreadyExistsException exc) {
		LocalDateTime date = LocalDateTime.now();
		ErrorDetails errorDetails = new ErrorDetails(exc.getMessage(), date);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);	
	}
}


