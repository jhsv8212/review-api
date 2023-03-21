package com.review.api.exception;

public class CommonException extends RuntimeException {
	public CommonException(String message) { super(message); }
	public CommonException(String message, Throwable throwable) { super(message, throwable); }
}
