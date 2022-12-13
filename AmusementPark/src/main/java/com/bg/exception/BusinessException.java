package com.bg.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus errorCode;

	public HttpStatus getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}

	public BusinessException() {
		super();
	}

	public BusinessException(HttpStatus errorCode) {
		super();
		this.errorCode = errorCode;
	}

	@Override
	public String toString() {
		return "Esd [errorCode=" + errorCode + "]";
	}

}
