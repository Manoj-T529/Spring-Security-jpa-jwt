package com.SpringSecurityJpaJwt.handler;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.SpringSecurityJpaJwt.execptions.AlreadyExistsException;
import com.SpringSecurityJpaJwt.execptions.UserNotFoundException;

@RestControllerAdvice
public class RestApiExceptionHandler {

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<Object> handleAlreadyExistsException(AlreadyExistsException ex, WebRequest request) {
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {

		Map<String, String> map = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {

			map.put(error.getField(), error.getDefaultMessage());

		});
		return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
	}
}
