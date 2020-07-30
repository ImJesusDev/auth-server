package com.jdiaz.auth.server.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<CustomErrorResponse> handleBadRequestException(BadRequestException e) {
		String message = e.getMessage();
		CustomErrorResponse error = new CustomErrorResponse("BAD_REQUEST_ERROR", message);
		error.setTimestamp(LocalDateTime.now());
		error.setStatus((HttpStatus.BAD_REQUEST.value()));
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}

}
