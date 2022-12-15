package com.bg.exception;

import java.util.NoSuchElementException;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	private static final org.jboss.logging.Logger LOGGER = LoggerFactory.logger(GlobalExceptionHandler.class);

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFound(Exception ex) {
		LOGGER.error(ex.getMessage(), ex);
		ResponseEntity<String> responseEntity = new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		return responseEntity;
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException elementException) {
		LOGGER.error(elementException.getMessage(), elementException);
		return new ResponseEntity<>("No Value Present In DB", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(Exception ex) {
		LOGGER.error(ex.getMessage(), ex);
		return new ResponseEntity<>("Please Once Chesk The Input Values", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<String> handleBusinessException(Exception ex) {
		LOGGER.error(ex.getMessage(), ex);
		ResponseEntity<String> responseEntity = new ResponseEntity<>
		                        (ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return responseEntity;
	}

}
