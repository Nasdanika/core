package org.nasdanika.http;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.nasdanika.http.Router.Target;
import org.nasdanika.repository.ResourceRequirement;
import org.osgi.framework.BundleContext;

/**
 * Web method _LegacyCommandToRemove for methods annotated with {@link RouteMethod}.
 * @author Pavel Vlasov
 *
 * @param <C>
 * @param <R>
 */
public abstract class RouteMethodTarget implements Target, Comparable<RouteMethodTarget> {
	
	private String path;
	private RequestMethod[] requestMethods;
	private Pattern pattern;
	private String produces;
	private String[] consumes;
	private String action;
	private String qualifier;
	private Lock[] locks;
	private boolean isCache;
	protected BundleContext bundleContext;
	protected Object resource;
	protected Method method;
	private Object target;
	
	public Lock[] getLocks() {
		return locks;
	}
	
	@Override
	public String getPath() {
		return path;
	}
	
	@Override
	public RequestMethod[] getRequestMethods() {
		return requestMethods;
	}

	@Override
	public Pattern getPattern() {
		return pattern;
	}
	
	public boolean isCache() {
		return isCache;
	}

	public RouteMethodTarget(BundleContext bundleContext, Object entity, Method method, Object target) throws Exception {
		this.bundleContext = bundleContext;
		this.resource = entity;
		this.method = method; 
		this.target = target;
		Route routeMethod = method.getAnnotation(Route.class);
		if (routeMethod == null) {
			throw new IllegalArgumentException("Not annotated with @RouteMethod: "+method);
		}
				
		path = routeMethod.path();						
		requestMethods = routeMethod.value();
		action = routeMethod.action();
		
		Locks locksAnnotation = method.getAnnotation(Locks.class);
		if (locksAnnotation != null) {
			locks = locksAnnotation.value();
		}
		isCache = routeMethod.cache();
		
		// Implying path and possibly request method from method name
		if (Util.isBlank(path) && Util.isBlank(routeMethod.pattern())) {
			boolean requestMethodMatched = false;
			if (requestMethods.length==0) {
				String[] smn = StringUtils.splitByCharacterTypeCamelCase(method.getName());
				RM: for (RequestMethod rm: RequestMethod.values()) {
					String[] rmna = rm.name().split("_");
					if (smn.length>rmna.length) {
						for (int idx = 0; idx < rmna.length; ++idx) {
							if (!smn[idx].equalsIgnoreCase(rmna[idx])) {
								continue RM;
							}
						}
						requestMethods = new RequestMethod[] {rm};
						StringBuilder pathBuilder = new StringBuilder();
						for (int i = rmna.length; i < smn.length; ++i) {
							if (i>rmna.length) {
								pathBuilder.append(RequestMethod.GET == rm && i == smn.length-1 ? "." : "/");
							}
							pathBuilder.append(smn[i].toLowerCase());
						}
						path = pathBuilder.toString();
						requestMethodMatched = true;
						if (Util.isBlank(action)) {
							// Mapping request methods to standard actions
							switch (rm) {
							case DELETE:
								action = "delete";
								break;
							case GET:
							case OPTIONS:
							case TRACE:
								action = "read";
								break;
							case POST:
								action = "create";
								break;
							case PUT:
							case PATCH:
								action = "update";
								break;
							default:
								// NOP
								break;						
							}
						}
						break;
					}
				}
			}
			if (!requestMethodMatched) {
				path = method.getName();
			}
		}
		
		if (Util.isBlank(path) && !Util.isBlank(routeMethod.pattern())) {
			pattern = Pattern.compile(routeMethod.pattern());
		}
		
		produces = routeMethod.produces();
		if (Util.isBlank(produces)) {
//			String shortPath = path.substring(path.lastIndexOf("/")+1);
//			String impliedProduces = AbstractRoutingServlet.MIME_TYPES_MAP.getContentType(shortPath);
//			if (!Util.isBlank(impliedProduces)) {
//				produces = impliedProduces;
//			}
		}
		consumes = routeMethod.consumes();
		qualifier = routeMethod.qualifier();
		
	}
	
	@Override
	public String getProduces() {
		return produces;
	}
	
	@Override
	public String[] getConsumes() {
		return consumes;
	}
	
	@Override
	public String getAction() {
		return action;
	}
	
	@Override
	public String getQualifier() {
		return qualifier;
	}
	
	public Method getMethod() {
		return method;
	}
		
	@Override
	public int compareTo(RouteMethodTarget o) {
		Route rm1 = getMethod().getAnnotation(Route.class);
		Route rm2 = o.getMethod().getAnnotation(Route.class);
		
		// The route with higher priority takes precedence.
		int cmp = rm2.priority() - rm1.priority();
		if (cmp!=0) {
			return cmp;
		}
		
		// If priorities are equal, then route defined in a sub-class takes precedence.
		Class<?> dc1 = getMethod().getDeclaringClass();
		Class<?> dc2 = o.getMethod().getDeclaringClass();
		if (dc1!=dc2) {
			return dc1.isAssignableFrom(dc2) ? 1 : -1;
		}
		
		// If the routes have the same inheritance distance then a route with path takes precedence over a route with pattern.
		if (!Util.isBlank(rm1.path())) {
			if (Util.isBlank(rm2.path())) {
				return -1; 
			}
		}
		
		if (!Util.isBlank(rm2.path())) {
			if (Util.isBlank(rm1.path())) {
				return 1; 
			}
		}
		
		// A route with the longest path/pattern takes precedence over the other if both use path or pattern.				
		String p1 = Util.isBlank(getPath()) ? rm1.pattern() : getPath();
		String p2 = Util.isBlank(o.getPath()) ? rm2.pattern() : o.getPath();
		cmp = p2.length() - p1.length();
		if (cmp!=0) {
			return cmp;
		}
		
		return hashCode() - o.hashCode();
	}
	

	@Override
	public String toString() {
		return "RouteMethodCommand [path=" + path + ", requestMethods=" + Arrays.toString(requestMethods) + ", pattern="
				+ pattern + ", produces=" + produces + ", consumes=" + Arrays.toString(consumes) + ", action=" + action
				+ ", qualifier=" + qualifier + ", method=" + method + "]";
	}
	
	/**
	 * Returns a list of resource requirements from {@link org.nasdanika.http.Lock} annotations for executing route methods. 
	 * @param context
	 * @param target
	 * @param arguments
	 * @return
	 */
	protected Collection<ResourceRequirement> getResourceRequirements(HttpServletRequest request) throws Exception {
		Collection<ResourceRequirement> ret = new ArrayList<>();
		Lock[] locks =  getLocks();
		if (locks == null) {
			return ret;
		}
		
		for (Lock lock: locks) {
			switch (lock.value()) {
			case READ:
				ret.add(new ResourceRequirement() {

					@Override
					public String getResource() {
						return lock.resource();
					}

					@Override
					public Mode getMode() {
						return Mode.read;
					}
					
				});
				break;
			case WRITE:
				ret.add(new ResourceRequirement() {

					@Override
					public String getResource() {
						return lock.resource();
					}

					@Override
					public Mode getMode() {
						return Mode.write;
					}
					
				});
				break;
			case IMPLY_FROM_HTTP_METHOD:
				switch (RequestMethod.valueOf(request.getMethod())) {
				case DELETE:
				case PATCH:
				case POST:
				case PUT:
					ret.add(new ResourceRequirement() {

						@Override
						public String getResource() {
							return lock.resource();
						}

						@Override
						public Mode getMode() {
							return Mode.write;
						}
						
					});
					break;
				default:
					ret.add(new ResourceRequirement() {

						@Override
						public String getResource() {
							return lock.resource();
						}

						@Override
						public Mode getMode() {
							return Mode.read;
						}
						
					});
					break;
				}
				break;
			default:
				break;
			}
		}
		return ret;
	}
	
	@Override
	public Result process(HttpServletRequest request, HttpServletResponse response,	Function<String, String> pathVariables) throws Exception {
		if (!isCache) {
			response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate"); // Dynamic.
			response.setHeader("Pragma", "no-cache");
		}
		
		MethodCommand methodCommand = new MethodCommand(bundleContext, request, response, resource, pathVariables, method) {

			@Override
			protected Object processBodyParameter(Class<?> parameterType, Annotation[] parameterAnnotations) throws Exception {
				return RouteMethodTarget.this.processBodyParameter(request, parameterType, parameterAnnotations);
			}

			@Override
			protected <T> T convert(Object obj, Class<T> type) throws Exception {
				return RouteMethodTarget.this.convert(obj, type);
			}
			
		};
		
		return Result.wrap(execute(() -> methodCommand.execute(target), getResourceRequirements(request)));
	}
	
	/**
	 * Obtains (locks) required resources and executes the _LegacyCommandToRemove. 
	 * @param _LegacyCommandToRemove
	 * @param resourceRequirements
	 * @return
	 */
	protected abstract Object execute(Callable<Object> command, Collection<ResourceRequirement> resourceRequirements) throws Exception;

	protected abstract Object processBodyParameter(HttpServletRequest request, Class<?> parameterType, Annotation[] parameterAnnotations) throws Exception;
	
	protected abstract <T> T convert(Object obj, Class<T> type) throws Exception;
		
}
