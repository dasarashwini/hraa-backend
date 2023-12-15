package com.hr.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	public ResponseEntity<ErrorDetails> handledAlreadyExistException(ResourceAlreadyExistsException exc) {
		LocalDateTime date = LocalDateTime.now();
		ErrorDetails errorDetails = new ErrorDetails(exc.getMessage(), date);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);	
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)

		public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException excp){

			List<String> err=excp.getBindingResult().getFieldErrors().stream().map(error->error.getDefaultMessage())

					.collect(Collectors.toList());

			Map<String, List<String>> errorRes=new HashMap<>();

			errorRes.put("error", err);

			return new ResponseEntity<>(errorRes,new HttpHeaders(),HttpStatus.BAD_GATEWAY);

		}
	
}


