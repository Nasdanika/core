package org.nasdanika.telemetry;

import java.util.concurrent.CompletionStage;

import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;

/**
 * Provides global {@link OpenTelemetry} which is auto-configured on demand.
 */
public class GlobalOpenTelemetryCapabilityFactory extends ServiceCapabilityFactory<Void, OpenTelemetry> {

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
		
		return wrap(GlobalOpenTelemetry.get());
	}

}
