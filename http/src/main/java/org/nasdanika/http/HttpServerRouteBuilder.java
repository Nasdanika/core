package org.nasdanika.http;

import java.util.Collection;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.nasdanika.common.PropertySource;
import org.nasdanika.common.Util;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.reactivestreams.Publisher;
import org.yaml.snakeyaml.Yaml;

import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;
import reactor.netty.http.server.HttpServerRoutes;

/**
 * Service interface for building routes.
 */
public interface HttpServerRouteBuilder {
	
	public static final String PATH_KEY = "path";
	public static final String METHOD_KEY = "method";

	void buildRoutes(HttpServerRoutes routes); 
		
	@SuppressWarnings("unchecked")
	static <P> void buildRoutes(
			Collection<ProcessorInfo<P>> processorInfos,
			Function<ProcessorInfo<P>, Route> routeProvider,
			HttpServerRoutes routes) {
				
		for (ProcessorInfo<P> pInfo: processorInfos) {
			Route route = routeProvider.apply(pInfo); 
			if (route != null) {
				String path = route.path();
				
				if (Util.isBlank(path)) {
					throw new IllegalArgumentException("Blank path for: " + pInfo.getElement());					
				}
				P processor = pInfo.getProcessor();
				if (processor instanceof BiFunction) {
					BiFunction<? super HttpServerRequest, ? super HttpServerResponse, ? extends Publisher<Void>> handler = (BiFunction<? super HttpServerRequest, ? super HttpServerResponse, ? extends Publisher<Void>>) processor;
					switch (route.method()) {
					case DELETE:
						routes.delete(path, handler);
						break;
					case GET:
						routes.get(path, handler);
						break;
					case HEAD:
						routes.head(path, handler);
						break;
					case OPTIONS:
						routes.options(path, handler);
						break;
					case POST:
						routes.post(path, handler);
						break;
					case PUT:
						routes.put(path, handler);
						break;
					default:
						throw new IllegalArgumentException("Unsupported HTTP method: " + route.method());
					}
				} else {
					throw new IllegalArgumentException("Route processor must implement BiFunction: " + processor);
				}
			}
		}
	}

	/**
	 * @param <P>
	 * @param processorInfos
	 * @param routeProperty Shall be a string or a map. If a string, parsed as YAML. If the parse result is a string
	 * then it is treated as a GET path. If a map, it shall contain method and path keys.
	 * method key value shall be a string name of HTTP method in capitals.
	 * @param routes
	 */
	@SuppressWarnings("unchecked")
	static <P> void buildRoutes(
			Collection<ProcessorInfo<P>> processorInfos,
			String routeProperty,
			HttpServerRoutes routes) {
		
		Function<ProcessorInfo<P>, Route> routeProvider = pInfo -> {
			if (pInfo.getElement() instanceof PropertySource) {			
				Object routeVal = ((PropertySource<Object, Object>) pInfo.getElement()).getProperty(routeProperty);
				if (routeVal == null) {
					return null;
				}
				if (routeVal instanceof Map) {
					Map<?, ?> routeMap = (Map<?,?>) routeVal;
					return new Route(
							HttpMethod.valueOf((String) routeMap.get(METHOD_KEY)),
							(String) routeMap.get(PATH_KEY));
				}
				if (routeVal instanceof String) {					
					Yaml yaml = new Yaml();
					String routeValStr = (String) routeVal;
					if (Util.isBlank(routeValStr)) {
						return null;
					}
					Object routeValParsed = yaml.load(routeValStr);
					if (routeValParsed instanceof String) {
						return new Route(HttpMethod.GET, (String) routeValParsed);
					}
					if (routeValParsed instanceof Map) {
						Map<?, ?> routeMap = (Map<?,?>) routeValParsed;
						return new Route(
								HttpMethod.valueOf((String) routeMap.get(METHOD_KEY)),
								(String) routeMap.get(PATH_KEY));						
					}
					throw new IllegalArgumentException("Unsupported route value type: " + routeValParsed.getClass());
				}
				throw new IllegalArgumentException("Unsupported route value type: " + routeVal.getClass());
			} 
			return null;
		};
		
		buildRoutes(processorInfos, routeProvider, routes);
	}
	
}
