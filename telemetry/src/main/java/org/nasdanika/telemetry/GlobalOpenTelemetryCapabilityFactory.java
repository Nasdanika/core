package org.nasdanika.telemetry;

import java.lang.reflect.Method;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicBoolean;

import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;

/**
 * Provides global {@link OpenTelemetry} which is auto-configured on demand.
 */
public class GlobalOpenTelemetryCapabilityFactory extends ServiceCapabilityFactory<Void, OpenTelemetry> {
	
	private static AtomicBoolean isInstalled = new AtomicBoolean();
	
	/**
	 * Gets the global {@link OpenTelemetry} with installed logback appender 
	 * @return
	 */
	public static OpenTelemetry getGlobalOpenTelemetry() {
		OpenTelemetry openTelemetry = GlobalOpenTelemetry.get();
		if (!isInstalled.getAndSet(true)) { // Installing once
			try {
				// Resorting to reflection due to a problem with automatic module name
				Class<?> openTelemetryAppenderClass = GlobalOpenTelemetryCapabilityFactory.class.getClassLoader().loadClass("io.opentelemetry.instrumentation.logback.appender.v1_0.OpenTelemetryAppender");
				Method installMethod = openTelemetryAppenderClass.getMethod("install", OpenTelemetry.class);
				installMethod.invoke(null, openTelemetry);				
			} catch (Exception e) {
				throw new NasdanikaException("Failed to install open telemetry appender: " + e, e);
			}
		}
		return openTelemetry;
	}

	@Override
	public boolean isFor(Class<?> type, Object requirement) {
		return OpenTelemetry.class == type;
	}

	@Override
	protected CompletionStage<Iterable<CapabilityProvider<OpenTelemetry>>> createService(
			Class<OpenTelemetry> serviceType, 
			Void serviceRequirement, 
			Loader loader, 
			ProgressMonitor progressMonitor) {
						
		OpenTelemetry openTelemetry = getGlobalOpenTelemetry();
		
		return wrap(openTelemetry);
	}

}
