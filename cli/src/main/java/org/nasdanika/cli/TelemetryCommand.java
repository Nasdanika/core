package org.nasdanika.cli;

import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.telemetry.TelemetryUtil;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanBuilder;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import picocli.CommandLine.Model.CommandSpec;

/**
 * Wraps command execution into a {@link Span}.
 */
public abstract class TelemetryCommand extends CommandBase {

	protected OpenTelemetry openTelemetry;
	protected Tracer tracer; 

	protected TelemetryCommand(OpenTelemetry openTelemetry, CapabilityLoader capabilityLoader) {
		super(capabilityLoader);
		this.openTelemetry = openTelemetry;
		tracer = openTelemetry.getTracer(getInstrumentationScopeName(), getInstrumentationScopeVersion());
	}
	
	protected String getCommandPath() {
		String ret = null;
		for (CommandSpec pathElement = spec; pathElement != null; pathElement = pathElement.parent()) {
			if (ret == null) {
				ret = pathElement.name();
			} else {
				ret = pathElement.name() + " " + ret;
			}
		}
		return ret;
	}
	
	protected String getInstrumentationScopeName() {
		return getClass().getName();
	}
	
	protected String getInstrumentationScopeVersion() {
		String[] version = spec.version();
		if (version == null) {
			return null;
		}
		return String.join(System.lineSeparator(), version);
	}
	
	protected String getSpanName() {
		return getCommandPath();
	}
	
	protected SpanBuilder buildSpan(SpanBuilder builder) {
		return TelemetryUtil.buildSpan(builder);
	}

	@Override
	public final Integer call() throws Exception {
		if (openTelemetry == null) {
			return execute(Span.current());
		}
		
		Span commandSpan = buildSpan(tracer.spanBuilder(getSpanName())).startSpan();
		try (Scope scope = commandSpan.makeCurrent()) {
			Integer result = execute(commandSpan);
	        commandSpan.setStatus(StatusCode.OK);
	        return result;
		} catch (Exception e) {
			commandSpan.recordException(e);
	        commandSpan.setStatus(StatusCode.ERROR);
			throw e;
		} finally {			
			commandSpan.end();
		}				
	}
	
	protected abstract Integer execute(Span commandSpan) throws Exception;

}
