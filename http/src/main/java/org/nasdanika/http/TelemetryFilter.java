package org.nasdanika.http;

import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.StreamSupport;

import org.nasdanika.telemetry.TelemetryUtil;
import org.reactivestreams.Publisher;

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanBuilder;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import io.opentelemetry.context.propagation.TextMapGetter;
import io.opentelemetry.context.propagation.TextMapPropagator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;

public class TelemetryFilter {
	
	protected Tracer tracer;
	protected TextMapPropagator propagator;
	protected BiConsumer<String,Long> durationConsumer;
	protected boolean resolveRemoteHostName;
	
	public TelemetryFilter(
		Tracer tracer,
		TextMapPropagator propagator,
		BiConsumer<String,Long> durationConsumer,
		boolean resolveRemoteHostName) {
		
		this.tracer = tracer;
		this.propagator = propagator;
		this.durationConsumer = durationConsumer;
		this.resolveRemoteHostName = resolveRemoteHostName;
	}
	
	protected SpanBuilder buildSpan(HttpServerRequest request) {		
		SpanBuilder builder = TelemetryUtil.buildSpan(tracer.spanBuilder(request.method().name() + " " + request.uri()));
		
		TextMapGetter<HttpServerRequest> mapper = new TextMapGetter<HttpServerRequest>() { 

		    @Override
		    public Iterable<String> keys(HttpServerRequest carrier) {
		    	HttpHeaders headers = carrier.requestHeaders();
		    	return StreamSupport
		    		.stream(headers.spliterator(), false)
		    		.map(Entry::getKey)
		    		.toList();
		    }
		    
		    @Override
		    public String get(HttpServerRequest carrier, String key) {
		    	HttpHeaders headers = carrier.requestHeaders();
		    	return StreamSupport
		    		.stream(headers.spliterator() , false)
		    		.filter(e -> Objects.equals(e.getKey() , key))
		    		.map(Entry::getValue)
		    		.findAny()
		    		.orElse(null);
		    }
		};

		Context telemetrycontext = propagator.extract(
				Context.current(),
				request,
				mapper);
		
		builder
			.setParent(telemetrycontext)
			.setAttribute("uri", request.uri())
			.setAttribute("remote.address", request.remoteAddress().getHostString())
			.setSpanKind(SpanKind.SERVER);
		
		if (resolveRemoteHostName) {
			builder.setAttribute("remote.hostname", request.remoteAddress().getHostName());
		}
		
		return builder;
	}
	
	public BiFunction<HttpServerRequest, HttpServerResponse, Publisher<Void>> wrapStringHandler(BiFunction<HttpServerRequest, HttpServerResponse, String> stringHandler) {
		return (request, response) -> {
			Span span = buildSpan(request).startSpan();
			long start = System.currentTimeMillis();
			try (Scope scope = span.makeCurrent()) {
				String result = stringHandler.apply(request, response);
				if (durationConsumer != null) {
					durationConsumer.accept(request.method().name() + " " + request.uri(), System.currentTimeMillis() - start);
				}
				span.setStatus(StatusCode.OK);
				return response.sendString(Mono.just(result));
			} catch (Exception e) {
				span.recordException(e);
				span.setStatus(StatusCode.ERROR);
				return response.status(HttpResponseStatus.INTERNAL_SERVER_ERROR).send();				
			} finally {
				span.end();
			}			
		};				
	}	
	
	public <T> Mono<T> filter(HttpServerRequest request, Mono<T> publisher) {
		Span requestSpan = buildSpan(request).startSpan();
		long start = System.currentTimeMillis();
		return publisher
			.map(result -> {
	        	if (durationConsumer != null) {
					durationConsumer.accept(request.method().name() + " " + request.uri(), System.currentTimeMillis() - start);
	        	}
	        	requestSpan.setStatus(StatusCode.OK);
				return result;
			})
			.onErrorMap(error -> {
	        	requestSpan.recordException(error);
	        	requestSpan.setStatus(StatusCode.ERROR);
				return error;
			})
			.doFinally(signal -> requestSpan.end());				
	}
		
	public <T> Flux<T> filter(HttpServerRequest request, Flux<T> publisher) {
		Span requestSpan = buildSpan(request).startSpan();
		return publisher
			.map(result -> {
	        	requestSpan.setStatus(StatusCode.OK);
				return result;
			})
			.onErrorMap(error -> {
	        	requestSpan.recordException(error);
	        	requestSpan.setStatus(StatusCode.ERROR);
				return error;
			})
			.doFinally(signal -> requestSpan.end());				
	}	

}
