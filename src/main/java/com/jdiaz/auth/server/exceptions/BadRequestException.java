package com.jdiaz.auth.server.exceptions;

public class BadRequestException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4418574211227782914L;

	public BadRequestException(String body) {
		super(body);
	}

}
