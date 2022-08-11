package org.nasdanika.common;

/**
 * Wrapper exception for checked exceptions thrown in methods of {@link ExecutionParticipant}s.
 * @author Pavel
 *
 */
@SuppressWarnings("serial")
public class ExecutionException extends NasdanikaException {
	
	private ExecutionParticipant executionParticipant;

	public ExecutionException(String message) {
		super(message);
	}

	public ExecutionException(Throwable cause) {
		super(cause);
	}

	public ExecutionException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExecutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExecutionException(String message, ProgressRecorder progressRecorder) {
		super(message, progressRecorder);
	}

	public ExecutionException(Throwable cause, ProgressRecorder progressRecorder) {
		super(cause, progressRecorder);
	}

	public ExecutionException(String message, Throwable cause, ProgressRecorder progressRecorder) {
		super(message, cause, progressRecorder);
	}

	public ExecutionException(String message, ExecutionParticipant executionParticipant) {
		this(message);
		this.executionParticipant = executionParticipant;
	}

	public ExecutionException(Throwable cause, ExecutionParticipant executionParticipant) {
		this(cause);
		this.executionParticipant = executionParticipant;
	}

	public ExecutionException(String message, Throwable cause, ExecutionParticipant executionParticipant) {
		this(message, cause);
		this.executionParticipant = executionParticipant;
	}

	public ExecutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ExecutionParticipant executionParticipant) {
		this(message, cause, enableSuppression, writableStackTrace);
		this.executionParticipant = executionParticipant;
	}

	public ExecutionException(String message, ProgressRecorder progressRecorder, ExecutionParticipant executionParticipant) {
		this(message, progressRecorder);
		this.executionParticipant = executionParticipant;
	}

	public ExecutionException(Throwable cause, ProgressRecorder progressRecorder, ExecutionParticipant executionParticipant) {
		this(cause, progressRecorder);
		this.executionParticipant = executionParticipant;
	}

	public ExecutionException(String message, Throwable cause, ProgressRecorder progressRecorder, ExecutionParticipant executionParticipant) {
		this(message, cause, progressRecorder);
		this.executionParticipant = executionParticipant;
	}
	
	public ExecutionParticipant getExecutionParticipant() {
		return executionParticipant;
	}

}
