package com.review.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends RuntimeException {

	public AuthenticationException(String message) { super(message); }
	public AuthenticationException(String message, Throwable throwable) { super(message, throwable); }
}
