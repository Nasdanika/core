package org.nasdanika.repository;

@SuppressWarnings("serial")
public class AuthenticationException extends Exception {

	public AuthenticationException() {

	}

	public AuthenticationException(String message) {
		super(message);
	}

	public AuthenticationException(Throwable cause) {
		super(cause);
	}

	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}

}
