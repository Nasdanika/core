package org.nasdanika.http;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import org.reactivestreams.Publisher;

import reactor.netty.http.server.HttpRouteHandlerMetadata;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;
import reactor.netty.http.server.HttpServerRoutes;
import reactor.netty.http.server.WebsocketServerSpec;
import reactor.netty.http.websocket.WebsocketInbound;
import reactor.netty.http.websocket.WebsocketOutbound;

public class PrefixedHttpServerRoutes implements HttpServerRoutes {
	
	private String prefix;
	private HttpServerRoutes target;

	public PrefixedHttpServerRoutes(String prefix, HttpServerRoutes target) {
		this.prefix = prefix;
		this.target = target;
	}

	@Override
	public HttpServerRoutes delete(
			String path,
			BiFunction<? super HttpServerRequest, ? super HttpServerResponse, ? extends Publisher<Void>> handler) {
		return target.delete(prefix + path, handler);
	}

	@Override
	public HttpServerRoutes directory(String uri, Path directory) {
		return target.directory(prefix + uri, directory, null);
	}

	@Override
	public HttpServerRoutes directory(
			String uri, 
			Path directory,
			Function<HttpServerResponse, HttpServerResponse> interceptor) {
		return target.directory(prefix + uri, directory, interceptor);
		
	}
	
	@Override
	public HttpServerRoutes file(String uri, Path path) {		
		return target.file(prefix + uri, path);
	}

	@Override
	public HttpServerRoutes file(String uri, String path) {
		return target.file(prefix + uri, Paths.get(path));
	}

	/**
	 * No prefixing
	 */
	@Override
	public HttpServerRoutes file(
			Predicate<HttpServerRequest> uri, 
			Path path,
			Function<HttpServerResponse, HttpServerResponse> interceptor) {
		
		return target.file(uri, path, interceptor);
	}

	@Override
	public HttpServerRoutes get(
			String path,
			BiFunction<? super HttpServerRequest, ? super HttpServerResponse, ? extends Publisher<Void>> handler) {
		return target.get(prefix + path, handler);
	}

	@Override
	public HttpServerRoutes head(
			String path,
			BiFunction<? super HttpServerRequest, ? super HttpServerResponse, ? extends Publisher<Void>> handler) {
		return target.head(prefix + path, handler);
	}

	/**
	 * No prefixing
	 */
	@Override
	public HttpServerRoutes index(final BiFunction<? super HttpServerRequest, ? super HttpServerResponse, ? extends Publisher<Void>> handler) {
		return target.index(handler);
	}

	@Override
	public HttpServerRoutes options(
			String path,
			BiFunction<? super HttpServerRequest, ? super HttpServerResponse, ? extends Publisher<Void>> handler) {
		return target.options(prefix + path, handler);
	}

	@Override
	public HttpServerRoutes post(
			String path,
			BiFunction<? super HttpServerRequest, ? super HttpServerResponse, ? extends Publisher<Void>> handler) {
		return target.post(prefix + path, handler);
	}

	@Override
	public HttpServerRoutes put(
			String path,
			BiFunction<? super HttpServerRequest, ? super HttpServerResponse, ? extends Publisher<Void>> handler) {
		return target.put(prefix + path, handler);
	}

	/**
	 * No prefixing
	 */
	@Override
	public HttpServerRoutes removeIf(Predicate<? super HttpRouteHandlerMetadata> condition) {
		return target.removeIf(condition);
	}

	/**
	 * No prefixing
	 */
	@Override
	public HttpServerRoutes route(
			Predicate<? super HttpServerRequest> condition,
			BiFunction<? super HttpServerRequest, ? super HttpServerResponse, ? extends Publisher<Void>> handler) {
		return target.route(condition, handler);
	}

	/**
	 * No prefixing
	 */
	@Override
	public HttpServerRoutes comparator(Comparator<HttpRouteHandlerMetadata> comparator) {
		return target.comparator(comparator);
	}

	/**
	 * No prefixing
	 */
	@Override
	public HttpServerRoutes noComparator() {
		return target.noComparator();
	}

	@Override
	public HttpServerRoutes ws(
			String path,
			BiFunction<? super WebsocketInbound, ? super WebsocketOutbound, ? extends Publisher<Void>> handler) {
		return target.ws(prefix + path, handler);
	}

	@Override
	public HttpServerRoutes ws(
			String path,
			BiFunction<? super WebsocketInbound, ? super WebsocketOutbound, ? extends Publisher<Void>> handler,
			WebsocketServerSpec configurer) {
		return target.ws(prefix + path, handler, configurer);
	}

	/**
	 * No prefixing
	 */
	@Override
	public HttpServerRoutes ws(
			Predicate<? super HttpServerRequest> condition,
			BiFunction<? super WebsocketInbound, ? super WebsocketOutbound, ? extends Publisher<Void>> handler,
			WebsocketServerSpec websocketServerSpec) {
		return target.ws(condition, handler, websocketServerSpec);
	}

	/**
	 * No prefixing
	 */
	@Override
	public Publisher<Void> apply(HttpServerRequest request, HttpServerResponse response) {
		return target.apply(request, response);
	}	

}
