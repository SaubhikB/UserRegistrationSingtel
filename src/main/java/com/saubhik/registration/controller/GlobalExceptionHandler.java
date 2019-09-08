package com.saubhik.registration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.saubhik.registration.util.RegistrationException;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(RegistrationException.class)
	public ResponseEntity<?> handleRegistrationException(RegistrationException ex) {

		logger.info("Incorrect User input..." + ex.getErrorMsg());
		return new ResponseEntity<>(ex.getErrorMsg(),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception ex) {
		logger.error("Exception: " + ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
