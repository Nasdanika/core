package org.nasdanika.cli;

import java.lang.module.ModuleDescriptor.Version;
import java.net.InetAddress;
import java.util.Optional;

import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    protected Logger logger = LoggerFactory.getLogger(getClass());

	protected TelemetryCommand(OpenTelemetry openTelemetry, CapabilityLoader capabilityLoader) {
		super(capabilityLoader);
		this.openTelemetry = openTelemetry;
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
	
	protected String getVersion() {
		String[] version = spec.version();
		if (version == null) {
			return null;
		}
		return String.join(System.lineSeparator(), version);
	}
	
	protected String getSpanName() {
		return getCommandPath();
	}
	
	protected void buildSpan(SpanBuilder builder) {
		builder.setAttribute("command_path", getCommandPath());
		String username = System.getProperty("user.name");
		if (!Util.isBlank(username)) {
			builder.setAttribute("user.name", username);
		}
		String vmName = System.getProperty("java.vm.name");
		if (!Util.isBlank(vmName)) {
			builder.setAttribute("java.vm.name", vmName);
		}
		String version = getVersion();
		if (!Util.isBlank(version)) {
			builder.setAttribute("version", version);
		}
		try {
			InetAddress localHost = InetAddress.getLocalHost();
			String hostName = localHost.getHostName();
			if (!Util.isBlank(hostName)) {
				builder.setAttribute("host.name", hostName);				
			}
			String hostAddress = localHost.getHostAddress();
			if (!Util.isBlank(hostAddress)) {
				builder.setAttribute("host.address", hostAddress);				
			}			
		} catch (Exception e) {
			logger.warn("Unable to obtain host name: " + e, e); 
		}
	}

	@Override
	public final Integer call() throws Exception {
		if (openTelemetry == null) {
			return execute();
		}
		
		Tracer tracer = openTelemetry.getTracer(getInstrumentationScopeName());
		SpanBuilder builder = tracer.spanBuilder(getSpanName());
		buildSpan(builder);
		
		Span commandSpan = builder.startSpan();
		try (Scope scope = commandSpan.makeCurrent()) {
			Integer result = execute();
	        commandSpan.setStatus(StatusCode.OK);
	        return result;
		} catch (Exception e) {
	        logger.error("Error: " + e , e);
	        commandSpan.setStatus(StatusCode.ERROR);
			throw e;
		} finally {			
			commandSpan.end();
		}				
	}
	
	protected abstract Integer execute() throws Exception;

}
