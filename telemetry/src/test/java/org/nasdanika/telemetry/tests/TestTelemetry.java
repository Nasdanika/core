package org.nasdanika.telemetry.tests;

import org.junit.jupiter.api.Test;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.ServiceCapabilityFactory.Requirement;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.logs.Logger;
import io.opentelemetry.api.logs.Severity;
import io.opentelemetry.api.metrics.Meter;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;

public class TestTelemetry {
	
	@Test
	public void testTelemetry() throws InterruptedException {
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		try {
			Requirement<Object, OpenTelemetry> requirement = ServiceCapabilityFactory.createRequirement(OpenTelemetry.class);
			OpenTelemetry openTelemetry = capabilityLoader.loadOne(requirement, progressMonitor);
			
			Meter meter = openTelemetry.getMeter(getClass().getModule().getName());
			meter
				.counterBuilder("my-counter")
				.setDescription("My test counter")
				.setUnit("my-unit")
				.build()
				.add(25);
			
			Tracer tracer = openTelemetry.getTracer(getClass().getModule().getName());
			Span span = tracer
				.spanBuilder("something important")
				.setAttribute("importance", 33)
				.setAttribute("service_name", "telemetry-test")
				.startSpan();
			
			try {
				Thread.sleep(500);
			} finally {
				span.end();
			}
			
			Logger logger = openTelemetry.getLogsBridge().get("my-logger");
			
			logger			
				.logRecordBuilder()
				.setSeverity(Severity.ERROR)
				.setBody("My log message")
				.setAttribute("my-attribute", 88)
				.emit();
			
			
			Thread.sleep(5000);
		} finally {				
			capabilityLoader.close(progressMonitor);
		}
	}

}
