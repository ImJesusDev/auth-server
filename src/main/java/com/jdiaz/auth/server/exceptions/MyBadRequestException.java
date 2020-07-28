package com.jdiaz.auth.server.exceptions;

public class MyBadRequestException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4418574211227782914L;

	public MyBadRequestException(String body) {
		super(body);
	}

}
