package org.nasdanika.http;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.nasdanika.common.Converter;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.Reflector;
import org.nasdanika.common.Reflector.AnnotatedElementRecord;
import org.nasdanika.common.Reflector.FactoryRecord;
import org.nasdanika.common.Util;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;
import reactor.netty.http.server.HttpServerRoutes;

public class ReflectiveHttpServerRouteBuilder implements HttpServerRouteBuilder {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ReflectiveHttpServerRouteBuilder.class); 
	
	/**
	 * Transforms handler method result. Transformers are chained until they transform the 
	 * result to {@link Publisher} which is not {@link Mono} or {@link Flux}.
	 * If no such transformation is available the result is converted to {@link String}, null is converted to an empty string.
	 * In the transformation chain each transformer is invoked only once.
	 */
	public interface ResultTransformer extends Comparable<ResultTransformer> {
		
		boolean canHandle(Object result);
		
		Object transform(Object result);		
		
		/**
		 * Transformer priority to use in comparison. Higher priority transformers take precedence over lower level priority ones.
		 * @return
		 */
		default int priority() {
			return 0;		
		}
		
		@Override
		default int compareTo(ResultTransformer o) {
			if (o == this) {
				return 0;
			}
			if (o == null) {
				return -1;
			}
			return o.priority() - this.priority();
		}
								
	}
	
	public interface TypedResultTransformer<T> extends ResultTransformer {
		
		Class<T> getType();
		
		@Override
		default boolean canHandle(Object result) {
			return getType().isInstance(result);
		}
						
		@Override
		default int compareTo(ResultTransformer o) {
			int result = ResultTransformer.super.compareTo(o);
			if (result != 0) {
				return result;
			}
			
			if (o instanceof TypedResultTransformer) {
				TypedResultTransformer<?> ot = (TypedResultTransformer<?>) o;
				if (getType() == ot.getType()) {
					return result;
				}
				if (getType().isAssignableFrom(ot.getType())) {
					return 1; // less specific
				}
				if (ot.getType().isAssignableFrom(getType())) {
					return -11; // More specific
				}
				return 0;
			}
			
			// this is "larger" that the other because it considers only type, not instance.
			return 1;					
		}		
		
		/**
		 * Creates a transformer matching results by their type. Typed transformers are "larger" than other transformers 
		 * within the same priority.
		 */
		static <T> TypedResultTransformer<T> from(Class<T> type, Function<T, ?> transformer) {
			return new TypedResultTransformer<T>() {
				
				@Override
				public boolean canHandle(Object result) {
					return type.isInstance(result);
				}
				
				@Override
				public Class<T> getType() {
					return type;
				}
				
				@SuppressWarnings("unchecked")
				@Override
				public Object transform(Object result) {
					return transformer.apply((T) result);
				}
								
			};
		}		
		
	}
	
	/**
	 * On a type this annotation is used to provide path prefix (namespace) for sub-factories and methods.
	 * 
	 * For methods this annotation indicates that a method is a routing method. The method must have two parameters compatible with
	 * {@link HttpServerRequest} and {@link HttpServerResponse} and return an instance of {@link Publisher}&lt;Void&gt;.
	 * 
	 * If on a method this annotation value is blank then method name is used to infer HTTP method and path by breaking the method name by camel case
	 * and then using the first element as HTTP method name, if it matches one of method names, and the rest, converted to lower case, as path segments.
	 * If the first element does not match any HTTP verbs then it is used as the first path segment.
	 *  
	 *  Examples: 
	 *  <ul>
	 *  <li><code>getAccounts</code> or <code>accounts</code> - method <code>GET</code>, path - <code>accounts</code></li>
	 *  <li><code>getAccountDetails</code> <code>accountDetails</code> - method <code>GET</code>, path - <code>account/details</code></li>
	 *  <li><code>postTransaction</code> - method <code>POST</code>, path - <code>transaction</code></li>
	 * </ul>   
	 * 
	 * @author Pavel
	 *
	 */
	@Retention(RUNTIME)
	@Target({ METHOD, TYPE })
	@Inherited
	public @interface Route {
		
		/**
		 * Route path. For types (factories) route path is used as a prefix for method paths.   
		 * @return
		 */
		String value() default "";
		
		/**
		 * Route HTTP method. Ignored on types or if the value is blank.
		 * @return
		 */
		HttpMethod method() default HttpMethod.GET; 
		
		/**
		 * Route priority. Routes with higher priorities are registered before routes with lower priorities.
		 * Within the same priority routes are sorted by the path length in segments.
		 * @return
		 */
		int priority() default 0;
		
		/**
		 * For methods which return {@link Mono} or {@link Flux} if this attribute is <code>false</code> 
		 * <code>Mono/Flux&lt;String&gt;</code> is assumed and <code>response.sendString()</code> is used with telemetry filtering.
		 * <code>Mono/Flux&lt;byte[]&gt;</code> and <code>response.sendByteArray()</code> otherwise (if <code>true</code>).
		 * @return
		 */
		boolean binary() default false;

	}
	
	/**
	 * Field or method annotation. Field value shall be an instance of {@link HttpServerRouteBuilder}.
	 * Methods shall have zero or one parameter. No parameters methods shall return an instance of <code>HttpServerRouteBuilder</code> or null.
	 * For methods with one parameter the parameter type shall be assignable from {@link HttpServerRoutes}.   
	 * 
	 * @author Pavel
	 *
	 */
	@Retention(RUNTIME)
	@Target({ METHOD, FIELD })
	public @interface RouteBuilder {
		
		/**
		 * Route prefix.   
		 * @return
		 */
		String value() default "";
		
		/**
		 * Priority. Route builders with higher priorities are called.
		 * Within the same priority route builders are sorted by the prefix length in segments.
		 * @return
		 */
		int priority() default 0;

	}

	private TelemetryFilter telemetryFilter;	
	
	public ReflectiveHttpServerRouteBuilder() {
		this(null);
	}
	
	public ReflectiveHttpServerRouteBuilder(TelemetryFilter telemetryFilter) {
		this.telemetryFilter = telemetryFilter;
	}
	
	protected Map<String, Collection<Object>> targets = new HashMap<>();
			
	/**
	 * Adds targets without a prefix
	 * @param targets
	 * @return
	 */
	public ReflectiveHttpServerRouteBuilder addTargets(Object... targets) {
		return addTargets("", targets);
	}
	
	/**
	 * Adds targets without a prefix
	 * @param targets
	 * @return
	 */
	public ReflectiveHttpServerRouteBuilder addTargets(List<Object> targets) {
		return addTargets("", targets);
	}
	
	/**
	 * Adds targets without a prefix
	 * @param targets
	 * @return
	 */
	public ReflectiveHttpServerRouteBuilder addTargets(String prefix, Object... targets) {
		Collection<Object> noPrefixTargets = this.targets.computeIfAbsent(prefix, k -> new ArrayList<>());
		for (Object target: targets) {
			noPrefixTargets.add(target);
		}
		
		return this;
	}
	
	/**
	 * Adds targets without a prefix
	 * @param targets
	 * @return
	 */
	public ReflectiveHttpServerRouteBuilder addTargets(String prefix, List<Object> targets) {
		Collection<Object> noPrefixTargets = this.targets.computeIfAbsent(prefix, k -> new ArrayList<>());
		for (Object target: targets) {
			noPrefixTargets.add(target);
		}
		
		return this;
	}
	
	private static String getFactoryRecordPath(FactoryRecord factoryRecord) {
		StringBuilder pathBuilder = new StringBuilder();
		Route targetRoute = factoryRecord.target().getClass().getAnnotation(Route.class);
		if (targetRoute != null) {
			pathBuilder.append(targetRoute.value());
		}
		Route aeRoute = factoryRecord.annotatedElement().getAnnotation(Route.class);
		if (aeRoute != null) {
			pathBuilder.append(aeRoute.value());
		}
		return pathBuilder.toString();
	}
	
	private static Route buildRoute(String prefix, AnnotatedElementRecord annotatedElementRecord) {
		StringBuilder pathBuilder = new StringBuilder(prefix); 
		
		annotatedElementRecord
			.getFactoryPath()
			.stream()
			.map(ReflectiveHttpServerRouteBuilder::getFactoryRecordPath)
			.forEach(pathBuilder::append);
		
		Route classRoute = ((Member) annotatedElementRecord.getAnnotatedElement()).getDeclaringClass().getAnnotation(Route.class);
		if (classRoute != null) {
			pathBuilder.append(classRoute.value());
		}
		
		Route route = annotatedElementRecord.getAnnotation(Route.class);
		if (route != null) {
			Method method = (Method) annotatedElementRecord.getAnnotatedElement();					
			HttpMethod[] httpMethod = { route.method() };
			
			if (Util.isBlank(route.value())) {
				StringBuilder methodPathBuilder = new StringBuilder();
				String[] segments = StringUtils.splitByCharacterTypeCamelCase(method.getName());
				for (int i = 0; i < segments.length; ++i) {
					if (i == 0) {
						boolean matched = false;
						for (HttpMethod hm: HttpMethod.values()) {
							if (hm.name().equalsIgnoreCase(segments[i])) {
								matched = true;
								httpMethod[0] = hm;
								break;
							}
						}
						if (!matched) {
							methodPathBuilder.append(segments[i].toLowerCase());						
						}
					} else {
						if (!methodPathBuilder.isEmpty()) {
							methodPathBuilder.append("/");
						}
						methodPathBuilder.append(segments[i].toLowerCase());
					}
				}
				pathBuilder.append(methodPathBuilder);			
			} else {
				pathBuilder.append(route.value());
			}
			return new Route() {
	
				@Override
				public Class<? extends Annotation> annotationType() {
					return Route.class;
				}
	
				@Override
				public String value() {
					return pathBuilder.toString();
				}
				
				@Override
				public HttpMethod method() {
					return httpMethod[0];
				}
	
				@Override
				public int priority() {
					return route.priority();
				}

				@Override
				public boolean binary() {
					return route.binary();
				}
				
			};
		}
		
		RouteBuilder routeBuilder = annotatedElementRecord.getAnnotation(RouteBuilder.class);
		pathBuilder.append(routeBuilder.value());
		return new Route() {
			
			@Override
			public Class<? extends Annotation> annotationType() {
				return Route.class;
			}

			@Override
			public String value() {
				return pathBuilder.toString();
			}
			
			@Override
			public HttpMethod method() {
				return null;
			}

			@Override
			public int priority() {
				return routeBuilder.priority();
			}

			@Override
			public boolean binary() {
				return false;
			}
			
		};
		
	}	
	
	/**
	 * @return A modifiable unsorted list of {@link ResultTransformer}s.
	 * The list is sorted 
	 */
	protected List<ResultTransformer> createResultTransformers(Route route, HttpServerRequest request, HttpServerResponse response) {
		List<ResultTransformer> resultTransformers = new ArrayList<>();
		resultTransformers.add(TypedResultTransformer.from(Mono.class, result -> transformMono(result, route, request, response)));
		resultTransformers.add(TypedResultTransformer.from(Flux.class, result -> transformFlux(result, route, request, response)));
		resultTransformers.add(TypedResultTransformer.from(JSONObject.class, result -> transformJSONObject(result, route, request, response)));
		resultTransformers.add(TypedResultTransformer.from(JSONArray.class, result -> transformJSONArray(result, route, request, response)));
		resultTransformers.add(TypedResultTransformer.from(String.class, result -> transformString(result, route, request, response)));
		resultTransformers.add(TypedResultTransformer.from(InputStream.class, result -> transformInputStream(result, route, request, response)));
		resultTransformers.add(TypedResultTransformer.from(byte[].class, result -> transformByteArray(result, route, request, response)));
		
		return resultTransformers;
	}
		
	private static final String CONTENT_TYPE_HEADER = "Content-Type";

	private static final String APPLICATION_JSON_CONTENT_TYPE = "application/json";	
	
	protected Publisher<Void> transformMono(Mono<?> result, Route route, HttpServerRequest request, HttpServerResponse response) {
		if (route.binary()) {
			Mono<byte[]> byteArrayMono = ((Mono<?>) result).map(this::toByteArray);
			if (telemetryFilter != null) {
				byteArrayMono = telemetryFilter.filter(request, byteArrayMono);
			}
			return response.sendByteArray(byteArrayMono);
		}
		Mono<String> strMono = ((Mono<?>) result).map(obj -> {
			if (obj instanceof JSONObject || obj instanceof JSONArray) {
				response.header(CONTENT_TYPE_HEADER, APPLICATION_JSON_CONTENT_TYPE);						
			}
			return toString(obj);
		});
		if (telemetryFilter != null) {
			strMono = telemetryFilter.filter(request, strMono);
		}
		return response.sendString(strMono);		
	}
	
	protected Publisher<Void> transformFlux(Flux<?> result, Route route, HttpServerRequest request, HttpServerResponse response) {
		if (route.binary()) {
			Flux<byte[]> byteArrayFlux = ((Flux<?>) result).map(this::toByteArray);
			if (telemetryFilter != null) {
				byteArrayFlux = telemetryFilter.filter(request, byteArrayFlux);
			}
			return response.sendByteArray(byteArrayFlux);
		}
		Flux<String> strFlux = ((Flux<?>) result).map(this::toString);
		if (telemetryFilter != null) {
			strFlux = telemetryFilter.filter(request, strFlux);
		}
		return response.sendString(strFlux);
	}
	
	protected Publisher<Void> transformJSONObject(JSONObject result, Route route, HttpServerRequest request, HttpServerResponse response) {
		Mono<String> strMono = Mono.just(((JSONObject) result).toString());
		if (telemetryFilter != null) {
			strMono = telemetryFilter.filter(request, strMono);
		}
		return response.header(CONTENT_TYPE_HEADER, APPLICATION_JSON_CONTENT_TYPE).sendString(strMono);
	}
	
	protected Publisher<Void> transformJSONArray(JSONArray result, Route route, HttpServerRequest request, HttpServerResponse response) {
		Mono<String> strMono = Mono.just(((JSONArray) result).toString());
		if (telemetryFilter != null) {
			strMono = telemetryFilter.filter(request, strMono);
		}
		return response.header(CONTENT_TYPE_HEADER, APPLICATION_JSON_CONTENT_TYPE).sendString(strMono);
	}
	
	protected Publisher<Void> transformString(String result, Route route, HttpServerRequest request, HttpServerResponse response) {
		Mono<String> strMono = Mono.just((String) result);
		if (telemetryFilter != null) {
			strMono = telemetryFilter.filter(request, strMono);
		}
		return response.sendString(strMono);
	}
	
	protected byte[] transformInputStream(InputStream result, Route route, HttpServerRequest request, HttpServerResponse response) {
		try {
			return DefaultConverter.INSTANCE.toByteArray((InputStream) result);
		} catch (IOException e) {
			throw new NasdanikaException(e);
		}
	}
	
	protected Publisher<Void> transformByteArray(byte[] result, Route route, HttpServerRequest request, HttpServerResponse response) {
		Mono<byte[]> byteArrayMono = Mono.just((byte[]) result);
		if (telemetryFilter != null) {
			byteArrayMono = telemetryFilter.filter(request, byteArrayMono);
		}
		return response.sendByteArray(byteArrayMono);
	}
	
	private record RouteRecord(
			String prefix, 
			AnnotatedElementRecord annotatedElementRecord,
			Route route, 
			TelemetryFilter telemetryFilter,
			ReflectiveHttpServerRouteBuilder routeBuilder) implements Comparable<RouteRecord>, HttpServerRouteBuilder {

		@Override
		public int compareTo(RouteRecord o) {
			if (o == this) {
				return 0;
			}
			
			int thisPriority = route().priority();
			int otherPriority = o.route().priority();
			if (thisPriority == otherPriority) {			
				String thisPath = route().value();
				String otherPath = o.route().value();
				int thisSegments = thisPath.split("/").length;
				int otherSegments = otherPath.split("/").length;
				if (thisSegments == otherSegments) {
					return otherPath.length() - thisPath.length();
				}
				return otherSegments - thisSegments;
			}
			return otherPriority - thisPriority;
		}
		
		@SuppressWarnings("unchecked")
		private Publisher<Void> handle(HttpServerRequest request, HttpServerResponse response) {			
			LOGGER.info(
					"Processing {} {} by invoking {}", 
					request.method().name(), 
					request.uri(), annotatedElementRecord().getAnnotatedElement());
			
			Object result = annotatedElementRecord().invoke(request, response);
			if (result == null) {
				return response.send();
			}
						
			List<ResultTransformer> resultTransformers = routeBuilder().createResultTransformers(route, request, response);
			Collections.sort(resultTransformers);

			// Transforming result
			boolean transformed;
			do {
				transformed = false;
				Iterator<ResultTransformer> rtit = resultTransformers.iterator();
				while (rtit.hasNext()) {
					ResultTransformer rt = rtit.next();
					if (rt.canHandle(result)) {
						result = rt.transform(result);
						if (result == null) {
							return response.send();
						}
						rtit.remove();
						transformed = true;
						break;
					}
				}
			} while (transformed);
			
			if (result instanceof Publisher) {			
				return (Publisher<Void>) result;
			}
			
			if (route.binary()) {
				Mono<byte[]> byteArrayMono = Mono.just(routeBuilder.toByteArray(result));
				if (telemetryFilter() != null) {
					byteArrayMono = telemetryFilter().filter(request, byteArrayMono);
				}
				return response.sendByteArray(byteArrayMono);				
			}
			
			Mono<String> strMono = Mono.just(routeBuilder.toString(result));
			if (telemetryFilter() != null) {
				strMono = telemetryFilter().filter(request, strMono);
			}
			return response.sendString(strMono);
		}

		@Override
		public void buildRoutes(HttpServerRoutes routes) {
			String path = route().value();			
			HttpMethod routeMethod = route().method();
			if (routeMethod == null) {
				// Route builder
				if (!Util.isBlank(path)) {
					routes = new PrefixedHttpServerRoutes(path, routes);
				}
				AnnotatedElement annotatedElement = annotatedElementRecord().getAnnotatedElement();
				if (annotatedElement instanceof Method) {
					Method builderMethod = (Method) annotatedElement;
					LOGGER.info("Route builder method {} for path '{}'", builderMethod, path);
					if (builderMethod.getParameterCount() == 1) {
						annotatedElementRecord().invoke(routes);
					} else {
						((HttpServerRouteBuilder) annotatedElementRecord().get()).buildRoutes(routes);						
					}
				} else {
					((HttpServerRouteBuilder) annotatedElementRecord().get()).buildRoutes(routes);
				}				
			} else {
				switch (routeMethod) {
				case DELETE:
					routes.delete(path, this::handle);
					LOGGER.info("DELETE {} -> {}", path, annotatedElementRecord().getAnnotatedElement());
					break;
				case GET:
					routes.get(path, this::handle);
					LOGGER.info("GET {} -> {}", path, annotatedElementRecord().getAnnotatedElement());
					break;
				case HEAD:
					routes.head(path, this::handle);
					LOGGER.info("HEAD {} -> {}", path, annotatedElementRecord().getAnnotatedElement());
					break;
				case OPTIONS:
					routes.options(path, this::handle);
					LOGGER.info("OPTIONS {} -> {}", path, annotatedElementRecord().getAnnotatedElement());
					break;
				case POST:
					routes.post(path, this::handle);
					LOGGER.info("POST {} -> {}", path, annotatedElementRecord().getAnnotatedElement());
					break;
				case PUT:
					routes.put(path, this::handle);
					LOGGER.info("PUT {} -> {}", path, annotatedElementRecord().getAnnotatedElement());
					break;
				default:
					throw new IllegalArgumentException("Unsupported HTTP method: " + route.method());
				}
			}
		}
		
	}	

	@Override
	public void buildRoutes(HttpServerRoutes routes) {
		Reflector reflector = new Reflector();		
		for (Map.Entry<String, Collection<Object>> targetsEntry: targets.entrySet()) {
			for (Object target: targetsEntry.getValue()) {			
				reflector.getAnnotatedElementRecords(target, new ArrayList<>())
				.filter(aer -> aer.getAnnotation(Route.class) != null || aer.getAnnotation(RouteBuilder.class) != null)
				.map(aer -> new RouteRecord(targetsEntry.getKey(), aer, buildRoute(targetsEntry.getKey(), aer), telemetryFilter, this))
				.sorted()
				.forEach(routeRecord -> routeRecord.buildRoutes(routes));					
			}		
		}				
	}	
	
	/**
	 * @return Converter to be used by <code>toString(Object)</code> and <code>toByteArray(Object)</code>
	 */
	protected Converter getConverter() {
		return DefaultConverter.INSTANCE;
	}
	
	/**
	 * Converts method or Mono/Flex result to String to pass to response.sendString() 
	 * @param obj
	 * @return
	 */
	protected String toString(Object obj) {
		return getConverter().convert(obj, String.class);
	}

	/**
	 * Converts method or Mono/Flex result to String to pass to response.sendString() 
	 * @param obj
	 * @return
	 */
	protected byte[] toByteArray(Object obj) {
		return getConverter().convert(obj, byte[].class);		
	}

}
