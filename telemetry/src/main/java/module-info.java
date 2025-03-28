import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.telemetry.GlobalOpenTelemetryCapabilityFactory;

module org.nasdanika.telemetry {
	
	exports org.nasdanika.telemetry;
	
	requires transitive org.nasdanika.capability;
	requires transitive io.opentelemetry.api;
	requires io.opentelemetry.sdk;
	requires io.opentelemetry.sdk.autoconfigure;
	requires io.opentelemetry.exporter.otlp;
	requires io.opentelemetry.exporter.logging;
	requires io.opentelemetry.context;
	
	provides CapabilityFactory with GlobalOpenTelemetryCapabilityFactory; 
	
}