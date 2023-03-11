package com.review.api.response;

import java.util.Collections;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor
public class CommonResponse<T> {

	private int code;

	private String message;

	@Builder.Default
	private List<T> list = Collections.emptyList();

	@Builder.Default
	private T data = null;
}
