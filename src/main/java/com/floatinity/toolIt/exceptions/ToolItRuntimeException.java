package com.floatinity.toolIt.exceptions;


/**
 * CREATED BY AKSHAY KHANDAGALE ON 01-Mar-2022
 */
public class ToolItRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	public ToolItRuntimeException(Throwable cause) {
		String msg = cause.getMessage();
		if (msg == null) {
			msg = cause.getClass().getName();
		}
		this.message = msg;
	}

	public ToolItRuntimeException(String msg) {
		this.message = msg;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
