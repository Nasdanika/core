package org.nasdanika.common;

import java.util.concurrent.CancellationException;
import org.slf4j.Logger;

/**
 * Progress monitor reporting to a {@link Logger},
 * This monitor indents sub-tasks and worked messages. It can be thought of as a hierarchical logger.
 * @author Pavel
 *
 */
public class LoggerProgressMonitor implements ProgressMonitor {

	private Logger logger;
	private String indent;
	protected int indentIncrement = 2;
	private boolean cancelled;
	private boolean withData;
	
	/**
	 * Constructs a progress monitor for a given logger.
	 * @param out
	 * @param indent Indent in spaces for this monitor.
	 * @param indentIncrement Increment to add to the indent of this monitor when constructing a sub-monitor.
	 * @param closeStream if true the monitor closes the stream in its close() method.
	 */
	public LoggerProgressMonitor(Logger logger, int indent, int indentIncrement, boolean withData) {
		this.logger = logger;
		StringBuilder indentBuilder = new StringBuilder();
		for (int i = 0; i < indent; ++i) {
			indentBuilder.append(' ');
		}
		this.indent = indentBuilder.toString();
		this.withData = withData;
	}
	
	/**
	 * Constructs a progress monitor with indent 0 and indent increment 2.
	 */
	public LoggerProgressMonitor(Logger logger) {
		this(logger, false);
	}
	
	/**
	 * Constructs a progress monitor outputting to System.out with indent 0, indentIncrement 2 and not closing the stream.
	 */
	public LoggerProgressMonitor(Logger logger, boolean withData) {
		this(logger, 0, 2, withData);
	}
	
	@Override
	public void close() {
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public ProgressMonitor split(String taskName, double size, Object... data) {
		if (isCancelled()) {
			throw new CancellationException();
		}
		String msg = indent + "  " + taskName + " (" + size + ")";
		if (withData) {
			logger.info(msg, data);
		} else {
			logger.info(msg);
		}
		return new LoggerProgressMonitor(logger, indent.length()+indentIncrement, indentIncrement, withData) {
			
			@Override
			public boolean isCancelled() {
				return LoggerProgressMonitor.this.isCancelled();
			}
			
		};
	}
	
	/**
	 * Formats detail element for output. This implementation concatenates the indent with the detail.
	 * @param detail
	 * @param indent
	 * @return
	 */
	protected String formatDetail(Object detail, String indent) {
		return indent + detail;
	}

	@Override
	public void worked(Status status, double work, String progressMessage, Object... data) {
		String msg = indent + "  [" + status + " " + work + "] " + progressMessage;
		switch (status) {
			case ERROR:
			case FAIL: 
				if (withData) {
					logger.error(msg, data);
				} else {
					logger.error(msg);
				}
				break;
			case WARNING:
				if (withData) {
					logger.warn(msg, data);
				} else {
					logger.warn(msg);
				}
				break;			
			default:
				if (withData) {
					logger.info(msg, data);
				} else {
					logger.info(msg);
				}
				break;				
		};
		if (status == Status.CANCEL) {
			cancelled = true;
		}
	}

	@Override
	public ProgressMonitor setWorkRemaining(double size) {
		// NOP
		return this;
	}

}
