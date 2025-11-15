package org.nasdanika.http.tests;


import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.List;
import java.util.function.BiFunction;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.nasdanika.http.SerpapiConnector;
import org.nasdanika.http.SerpapiConnector.SearchResult;
import org.nasdanika.http.TelemetryFilter;
import org.nasdanika.telemetry.TelemetryUtil;
import org.reactivestreams.Publisher;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import io.opentelemetry.context.propagation.TextMapPropagator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.client.HttpClient;
import reactor.netty.http.server.HttpServer;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;

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
				.route(routes -> {
					routes.get("/hello", telemetryFilter.wrapStringHandler((request, response) -> "Hello World!"));
				})
				.bindNow();
		
		server.onDispose().block();
	}
	
//	protected void createRoutes(HttpServerRoutes routes, TelemetryFilter telemetryFilter) {
//		routes.get("/hello", telemetryFilter.wrapStringHandler((request, response) -> "Hello World!"));	
//		routes.get("/privet", (request, response) -> response.sendString(telemetryFilter.filter(request, Mono.fromSupplier(() -> "Hello World!"))));	
//	}
	
	@Test
	@Disabled
	public void testSse() {
		DisposableServer server =
				HttpServer.create()
					.port(8080)
					.route(routes -> routes
						.get("/index.html", (request, response) -> response.sendString(Mono.just(INDEX)))
						.get("/sse", serveSse()))
				    .bindNow();

		server.onDispose().block();
	}	
	
	private static final String INDEX = """
			<!DOCTYPE html>
			<html>
				<body>
				
				<h1>Getting Server Updates</h1>
				
				<div id="result"></div>
				
				<script>
				const x = document.getElementById("result");
				if(typeof(EventSource) !== "undefined") {
				  var source = new EventSource("sse");
				  source.onmessage = function(event) {
				    x.innerHTML += event.data + "<br>";
				  };
				} else {
				  x.innerHTML = "Sorry, no support for server-sent events.";
				}
				</script>
				
				</body>
			</html>						
			""";
	
	/**
	 * Prepares SSE response.
	 * The "Content-Type" is "text/event-stream".
	 * The flushing strategy is "flush after every element" emitted by the provided Publisher.
	 */
	private static BiFunction<HttpServerRequest, HttpServerResponse, Publisher<Void>> serveSse() {
		Flux<Long> flux = Flux.interval(Duration.ofSeconds(10));
		return (request, response) ->
		        response.sse()
		                .send(flux.map(TestHttp::toByteBuf), b -> true);
	}

	/**
	 * Transforms the Object to ByteBuf following the expected SSE format.
	 */
	private static ByteBuf toByteBuf(Object any) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			out.write("data: ".getBytes(Charset.defaultCharset()));
			out.write(any.toString().getBytes());
			out.write("\n\n".getBytes(Charset.defaultCharset()));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		return ByteBufAllocator.DEFAULT
		                       .buffer()
		                       .writeBytes(out.toByteArray());
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
	
	@Test
	public void testSearch() {
        String apiKey = System.getenv("SERPER_KEY");
        String query = "What is a kernel function in microsoft semantic kernel";
        
        SerpapiConnector serpApiConnector = new SerpapiConnector(apiKey, "learn.microsoft.com/en-us/semantic-kernel");
        Flux<SearchResult> results = serpApiConnector.search(query, 1, 0);
        List<SearchResult> resultList = results.collectList().block();
        for (SearchResult result: resultList) {
        	System.out.println("===");
        	System.out.println(result.title());
        	System.out.println();
        	System.out.println(result.markdownMainContent());
        }
	}

}
