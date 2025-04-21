package org.nasdanika.telemetry.tests;

import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.ServiceCapabilityFactory.Requirement;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.metrics.DoubleGauge;
import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.Meter;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import io.opentelemetry.context.propagation.TextMapPropagator;
import io.opentelemetry.context.propagation.TextMapSetter;
import reactor.core.publisher.Mono;

public class TestTelemetry {
	@Test
	public void testReactorTelemetry() throws InterruptedException {
		OpenTelemetry openTelemetry = GlobalOpenTelemetry.get();
		Tracer tracer = openTelemetry.getTracer("reactor.tracer");
		Span span = tracer.spanBuilder("reactor.span").startSpan();
		
		Mono<String> mono = Mono.deferContextual(contextView -> {
			return Mono.just("Hello world!");
		}).doOnEach(signal -> {
			Context ctx = signal.getContextView().getOrDefault(Context.class, Context.current());
			System.out.println(signal);
			Span signalSpan = Span.fromContext(ctx);
			Attributes attributes = Attributes.builder()
					.put("thread", Thread.currentThread().getName())
					.put("signal", signal.toString())
					.build();
			
			signalSpan.recordException(
					new RuntimeException(), 
					attributes);
		}).doFinally(signal -> {
			span.end();
		}).contextWrite(reactor.util.context.Context.of(Context.class, Context.current().with(span)));
		
		System.out.println(mono.block());
	}
	
	@Test
	public void testTelemetry() throws InterruptedException {
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		try {
			Requirement<Object, OpenTelemetry> requirement = ServiceCapabilityFactory.createRequirement(OpenTelemetry.class);
			OpenTelemetry openTelemetry = capabilityLoader.loadOne(requirement, progressMonitor);
			
			TextMapPropagator propagator = openTelemetry.getPropagators().getTextMapPropagator();
			
			Meter meter = openTelemetry.getMeter(getClass().getModule().getName());
			
			LongCounter counter = meter
				.counterBuilder("my-counter")
				.setDescription("My test counter")
				.setUnit("my-unit")
				.build();			
			
			counter.add(25);
			
			DoubleGauge gauge = meter
				.gaugeBuilder("my-gauge")
				.setDescription("My test gauge")
				.setUnit("my-unit")
				.build();			
			
			gauge.set(25);
			
			DoubleGauge histogram = meter
				.gaugeBuilder("my-histogram")
				.setDescription("My test histogram")
				.setUnit("my-unit")
				.build();			
			
			histogram.set(25);
			
			LongCounter upDownCounter = meter
				.counterBuilder("my-up-downcounter")
				.setDescription("My test up/down counter")
				.setUnit("my-unit")
				.build();			
			
			upDownCounter.add(25);
			
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
		        
		        TextMapSetter<PrintStream> setter = (out, k, v) -> out.println("Propagaging " + k + ": " + v);
				propagator.inject(context, System.out, setter);
		        		        
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
