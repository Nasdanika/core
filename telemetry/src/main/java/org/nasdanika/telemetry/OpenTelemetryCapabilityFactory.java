package org.nasdanika.telemetry;

import java.util.concurrent.CompletionStage;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.emf.ResourceSetRequirement;
import org.nasdanika.common.Closeable;
import org.nasdanika.common.ProgressMonitor;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.autoconfigure.AutoConfiguredOpenTelemetrySdk;

/**
 * Creates and configures {@link ResourceSet} according to the {@link ResourceSetRequirement}
 */
public class OpenTelemetryCapabilityFactory extends ServiceCapabilityFactory<Void, OpenTelemetry> implements Closeable {

	@Override
	public boolean isFor(Class<?> type, Object requirement) {
		return OpenTelemetry.class == type;
	}
	
	OpenTelemetrySdk openTelemetrySdk;
	
	public OpenTelemetryCapabilityFactory() {
		boolean sdkEnabled = !"true".equals(System.getProperty("otel.sdk.disabled", "false"));		
		if (sdkEnabled) {
			openTelemetrySdk = AutoConfiguredOpenTelemetrySdk
					.initialize()
					.getOpenTelemetrySdk();
		}
	}

	@Override
	protected CompletionStage<Iterable<CapabilityProvider<OpenTelemetry>>> createService(
			Class<OpenTelemetry> serviceType, 
			Void serviceRequirement, 
			Loader loader, 
			ProgressMonitor progressMonitor) {
		
		return wrap(openTelemetrySdk == null ? OpenTelemetry.noop() : openTelemetrySdk);
	}

	@Override
	public void close(ProgressMonitor progressMonitor) {
		if (openTelemetrySdk != null) {
			openTelemetrySdk.close();
		}		
	}

}
