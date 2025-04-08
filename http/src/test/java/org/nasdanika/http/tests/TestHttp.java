package org.nasdanika.http.tests;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.nasdanika.http.TelemetryFilter;
import org.nasdanika.telemetry.TelemetryUtil;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import io.opentelemetry.context.propagation.TextMapPropagator;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.client.HttpClient;
import reactor.netty.http.server.HttpServer;
import reactor.netty.http.server.HttpServerRoutes;

public class TestHttp {
	
	@Test
	@Disabled
	public void testServerWithTelemetry() {
		OpenTelemetry openTelemetry = GlobalOpenTelemetry.get();
		Tracer tracer = openTelemetry.getTracer(TestHttp.class.getName() + ".testServerWithTelemetry");
		TelemetryFilter telemetryFilter = new TelemetryFilter(
				tracer, 
				openTelemetry.getPropagators().getTextMapPropagator(), 
				(k, v) -> System.out.println(k + ": " + v), 
				true);
		DisposableServer server = HttpServer
				.create()
				.port(8080)
				.route(routes -> createRoutes(routes, telemetryFilter))
				.bindNow();
		
		server.onDispose().block();
	}
	
	protected void createRoutes(HttpServerRoutes routes, TelemetryFilter telemetryFilter) {
		routes.get("/hello", telemetryFilter.wrapStringHandler((request, response) -> "Hello World!"));	
		routes.get("/privet", (request, response) -> response.sendString(telemetryFilter.filter(request, Mono.fromSupplier(() -> "Hello World!"))));	
		
	}
	
	@Test
	@Disabled
	public void testClientWithTelemetry() {
		OpenTelemetry openTelemetry = GlobalOpenTelemetry.get();
		Tracer tracer = openTelemetry.getTracer(TestHttp.class.getName() + ".testClientWithTelemetry");
		Span span = TelemetryUtil.buildSpan(tracer.spanBuilder("Hello"))
			.setSpanKind(SpanKind.CLIENT)
			.startSpan();
		
		TextMapPropagator propagator = openTelemetry.getPropagators().getTextMapPropagator();

		try (Scope scope = span.makeCurrent()) {			
			HttpClient client = HttpClient.create()
					.headers(headerBuilder -> propagator.inject(Context.current(), headerBuilder, (c, k, v) -> c.set(k,v)))
					.followRedirect(true);
			
			String response = client.get()
				.uri("http://localhost:8080/privet")
				.responseContent()
				.aggregate()
				.asString()
				.block();
			
			System.out.println(response);
		} finally {
			span.end();
		}
	}
	
	@Test
	@Disabled
	public void testAsyncClientWithTelemetry() {
		OpenTelemetry openTelemetry = GlobalOpenTelemetry.get();
		Tracer tracer = openTelemetry.getTracer(TestHttp.class.getName() + ".testAsyncClientWithTelemetry");
		Span span = TelemetryUtil.buildSpan(tracer.spanBuilder("Hello"))
			.setSpanKind(SpanKind.CLIENT)
			.startSpan();
		
		TextMapPropagator propagator = openTelemetry.getPropagators().getTextMapPropagator();

		try (Scope scope = span.makeCurrent()) {			
			HttpClient client = HttpClient.create()
					.headers(headerBuilder -> {
						propagator.inject(Context.current(), headerBuilder, (c, k, v) -> c.set(k,v));	
					})
					.followRedirect(true);
			
			String response = client.get()
				.uri("http://localhost:8080/privet")			
				.responseContent()
				.aggregate()
				.asString()
				.map(result -> {
					span.setStatus(StatusCode.OK);
					return result;
				})
				.onErrorMap(error -> {
					span.recordException(error);
					span.setStatus(StatusCode.ERROR);
					return error;
				})
				.doFinally(signal -> span.end())
				.block();
			
			System.out.println(response);
		}
	}

}
