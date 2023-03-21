package com.review.api.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.review.api.response.CommonResponse;

@RestControllerAdvice
public class CommonExceptionHandler {

	@ExceptionHandler(value = CommonException.class)
	public CommonResponse<String> commonExceptionHandler(CommonException e) {
		CommonResponse<String> response = new CommonResponse<>();
		response.setResult("FAIL");
		response.setMessage(e.getMessage());
		return response;
	}
}
