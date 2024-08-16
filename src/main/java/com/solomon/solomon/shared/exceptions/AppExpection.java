package com.solomon.solomon.shared.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppExpection extends RuntimeException {
	private int statusCode;

	public AppExpection(String message, int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

}
