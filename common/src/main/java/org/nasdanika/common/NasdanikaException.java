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
			
	protected ProgressRecorder progressRecorder;
	
	/**
	 * Creates an exception with recorder progress which can be used for troubleshooting.
	 * @param message
	 * @param progressRecorder
	 */
	public NasdanikaException(String message, ProgressRecorder progressRecorder) {
		this(message);
		this.progressRecorder = progressRecorder;
	}

	/**
	 * Creates an exception with recorder progress which can be used for troubleshooting.
	 * @param cause
	 * @param progressRecorder
	 */
	public NasdanikaException(Throwable cause, ProgressRecorder progressRecorder) {
		this(cause);
		this.progressRecorder = progressRecorder;
	}

	/**
	 * Creates an exception with recorder progress which can be used for troubleshooting.
	 * @param message
	 * @param cause
	 * @param progressRecorder
	 */
	public NasdanikaException(String message, Throwable cause, ProgressRecorder progressRecorder) {
		this(message, cause);
		this.progressRecorder = progressRecorder;
	}

	/**
	 * @return progress recorder which can be used for troubleshooting.
	 */
	public ProgressRecorder getProgressRecorder() {
		return progressRecorder;
	}

}
