package org.nasdanika.telemetry;

import java.util.concurrent.CancellationException;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;

import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;

/**
 * Progress monitor reporting to a {@link Tracer} {@link Span}s,
 * This monitor indents sub-tasks and worked messages. It can be thought of as a hierarchical span.
 * @author Pavel
 *
 */
public class TracerProgressMonitor implements ProgressMonitor {

	private Span span;
	private Tracer tracer;
	private boolean cancelled;
	private boolean withData;
	
	/**
	 * Constructs a progress monitor for a given tracer.
	 * @param out
	 * @param indent Indent in spaces for this monitor.
	 * @param indentIncrement Increment to add to the indent of this monitor when constructing a sub-monitor.
	 * @param closeStream if true the monitor closes the stream in its close() method.
	 */
	public TracerProgressMonitor(Tracer tracer, String spanName, boolean withData) {
		this.tracer = tracer;
		this.withData = withData;
		
		span = tracer
			.spanBuilder(spanName)
			.setParent(Context.current())
			.startSpan();
		
		span.makeCurrent();
	}
	
	/**
	 * Used by split()
	 * @param tracer
	 * @param spanName
	 * @param withData
	 */
	protected TracerProgressMonitor(Tracer tracer, Span parentSpan, String spanName, boolean withData) {
		this.tracer = tracer;
		this.withData = withData;
		
		span = tracer
			.spanBuilder(spanName)
			.setParent(Context.current().with(parentSpan))
			.startSpan();	
		
		span.makeCurrent();
	}
	
	/**
	 * Constructs a progress monitor with indent 0 and indent increment 2.
	 */
	public TracerProgressMonitor(Tracer tracer, String spanName) {
		this(tracer, spanName, false);
	}
	
	@Override
	public void close() {
		span.end();
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
		return new TracerProgressMonitor(tracer, span, taskName, withData) {
			
			@Override
			public boolean isCancelled() {
				return TracerProgressMonitor.this.isCancelled();
			}
			
		};
	}

	@Override
	public void worked(Status status, double work, String progressMessage, Object... data) {
		Attributes attributes = Attributes
			.builder()
			.put("status", status.name())
			.put("work", work)
			.build();
		
		span.addEvent(progressMessage, attributes);
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
