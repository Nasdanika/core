package org.nasdanika.maven;

import java.util.Arrays;
import java.util.concurrent.CancellationException;

import org.apache.maven.plugin.logging.Log;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;

/**
 * Progress monitor reporting to {@link Log}.
 * @author Pavel
 *
 */
public class LogProgressMonitor implements ProgressMonitor {

	private Log log;
	private String indent;
	protected int indentIncrement = 2;
	private boolean cancelled;
	
	/**
	 * Constructs a progress monitor for a given log.
	 */
	public LogProgressMonitor(Log log, int indent, int indentIncrement) {
		this.log = log;
		StringBuilder indentBuilder = new StringBuilder();
		for (int i = 0; i < indent; ++i) {
			indentBuilder.append(' ');
		}
		this.indent = indentBuilder.toString();
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
		log.info(indent + "  " + taskName + " ("+size+")");
		if (data != null) {
			for (Object d: data) {
				log.info(formatDetail(d, indent + "    "));			
			}
		}
		return new LogProgressMonitor(log, indent.length() + indentIncrement, indentIncrement) {
			
			@Override
			public boolean isCancelled() {
				return LogProgressMonitor.this.isCancelled();
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
		switch (status) {
		case ERROR:
		case FAIL:
			log.error(indent + "  [" + status + " " + work + "] " + progressMessage);
			if (data.length > 0) {
				log.error(": " + Arrays.deepToString(data));
			}
			break;
		case WARNING:
			log.warn(indent + "  [" + status + " " + work + "] " + progressMessage);
			if (data.length > 0) {
				log.warn(": " + Arrays.deepToString(data));
			}
			break;
		case CANCEL:
		case INFO:
		case SUCCESS:
		default:
			log.info(indent + "  [" + status + " " + work + "] " + progressMessage);
			if (data.length > 0) {
				log.info(": " + Arrays.deepToString(data));
			}
			if (status == Status.CANCEL) {
				cancelled = true;
			}
		}
	}

	@Override
	public ProgressMonitor setWorkRemaining(double size) {
		// NOP
		return this;
	}

	@Override
	public void close() {
		// NOP		
	}

}
