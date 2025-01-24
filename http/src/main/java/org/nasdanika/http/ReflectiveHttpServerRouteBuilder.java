package org.nasdanika.http;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.nasdanika.common.Reflector;
import org.nasdanika.common.Reflector.AnnotatedElementRecord;
import org.nasdanika.common.Util;
import org.reactivestreams.Publisher;

import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;
import reactor.netty.http.server.HttpServerRoutes;

public class ReflectiveHttpServerRouteBuilder implements HttpServerRouteBuilder {
	
	/**
	 * On a type this annotation is used to provide path prefix (namespace) for sub-factories and methods.
	 * 
	 * For methods this annotation indicates that a method is a routing method. The method much have two parameters compatible with
	 * {@link HttpServerRequest} and {@link HttpServerResponse} and return an instance of {@link Publisher}&lt;Void&gt;.
	 * 
	 * If on a method this annotation value is blank then method name is used to infer HTTP method and path by breaking the method name by camel case
	 *  and then using the first element as HTTP method name, if it matches one of method names, and the rest, converted to lower case, as path segments.
	 *  If the first element does not match any HTTP verbs then it is used as the first path segment.
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
	
	private static Route buildRoute(String prefix, AnnotatedElementRecord annotatedElementRecord) {
		StringBuilder pathBuilder = new StringBuilder(prefix); 
		
		annotatedElementRecord
			.getFactoryPath()
			.stream()
			.map(Object::getClass)
			.map(c -> c.getAnnotation(Route.class)) 
			.filter(Objects::nonNull)
			.forEach(r -> pathBuilder.append(r.value()));

		Method method = (Method) annotatedElementRecord.getAnnotatedElement();
		Route classRoute = method.getDeclaringClass().getAnnotation(Route.class);
		if (classRoute != null) {
			pathBuilder.append(classRoute.value());
		}
				
		HttpMethod[] httpMethod = { HttpMethod.GET };
		Route route = annotatedElementRecord.getAnnotation(Route.class);
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
			
		};		
	}	
	
	private record RouteRecord(
			String prefix, 
			AnnotatedElementRecord annotatedElementRecord,
			Route route) implements Comparable<RouteRecord>, HttpServerRouteBuilder {
		
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
			return (Publisher<Void>) annotatedElementRecord().invoke(request, response);
		}

		@Override
		public void buildRoutes(HttpServerRoutes routes) {
			String path = route().value();
			switch (route().method()) {
			case DELETE:
				routes.delete(path, this::handle);
				break;
			case GET:
				routes.get(path, this::handle);
				break;
			case HEAD:
				routes.head(path, this::handle);
				break;
			case OPTIONS:
				routes.options(path, this::handle);
				break;
			case POST:
				routes.post(path, this::handle);
				break;
			case PUT:
				routes.put(path, this::handle);
				break;
			default:
				throw new IllegalArgumentException("Unsupported HTTP method: " + route.method());
			}
		}
		
	}	

	@Override
	public void buildRoutes(HttpServerRoutes routes) {
		Reflector reflector = new Reflector();		
		for (Map.Entry<String, Collection<Object>> targetsEntry: targets.entrySet()) {
			for (Object target: targetsEntry.getValue()) {			
				reflector.getAnnotatedElementRecords(target, new ArrayList<>())
				.filter(aer -> aer.getAnnotation(Route.class) != null)
				.map(aer -> new RouteRecord(targetsEntry.getKey(), aer, buildRoute(targetsEntry.getKey(), aer)))
				.sorted()
				.forEach(routeRecord -> routeRecord.buildRoutes(routes));					
			}		
		}				
	}	

}
