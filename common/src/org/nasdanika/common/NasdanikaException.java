package org.nasdanika.common;

/**
 * This exception type is primarily for wrapping low-level exceptions.
 * @author Pavel
 *
 */
@SuppressWarnings("serial")
public class NasdanikaException extends RuntimeException {

	public NasdanikaException(String message) {
		super(message);
	}

	public NasdanikaException(Throwable cause) {
		super(cause);
	}

	public NasdanikaException(String message, Throwable cause) {
		super(message, cause);
	}

	public NasdanikaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
