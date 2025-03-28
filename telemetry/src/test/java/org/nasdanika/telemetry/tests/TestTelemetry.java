package org.nasdanika.telemetry.tests;

import org.junit.jupiter.api.Test;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.ServiceCapabilityFactory.Requirement;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.metrics.Meter;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;

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
			
	        Tracer tracer = openTelemetry.getTracer("test-telemetry");        
	        Span parentSpan = tracer
	        	.spanBuilder("parent")
	        	.startSpan();
	        
	        try (Scope parentScope = parentSpan.makeCurrent()) {
				Thread.sleep(200);
		        Logger logger = LoggerFactory.getLogger(TestTelemetry.class);
		        logger
		        	.atInfo()
		        	.setMessage("My test telemetry message")
		        	.addKeyValue("someKey", "someValue")
		        	.log();
		        
		        Context context = Context.current();
		        		        
		        Span childSpan = tracer
			        	.spanBuilder("child")
			        	.setParent(context)
			        	.startSpan();
		        
		        try (Scope childScope = childSpan.makeCurrent()) {
					Thread.sleep(300);
		        } finally {
		        	childSpan.end();
		        }
		        		        
				Thread.sleep(100);		        
	        } finally {
	        	parentSpan.end();
	        }
		} finally {				
			capabilityLoader.close(progressMonitor);
		}
	}

}
