package com.review.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL) // null인 경우엔 JSON 노출 안됨
public class CommonResponse {

	private String result = "FAIL";

	private String message;

	private String data;

}
