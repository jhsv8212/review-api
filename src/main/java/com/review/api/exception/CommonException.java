package com.review.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CommonException extends RuntimeException {
	public CommonException(String message) { super(message); }
	public CommonException(String message, Throwable throwable) { super(message, throwable); }
}
