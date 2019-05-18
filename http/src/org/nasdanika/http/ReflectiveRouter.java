package org.nasdanika.http;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.nasdanika.repository.ResourceRequirement;
import org.osgi.framework.BundleContext;

public abstract class ReflectiveRouter extends Router {

	protected Object target;

	protected BundleContext bundleContext;
	protected Object resource;


	public ReflectiveRouter(BundleContext bundleContext, Object resource, Object target) {
		this.bundleContext = bundleContext;
		this.resource = resource;
		this.target = target;
	}

	@Override
	protected List<Target> getTargets(HttpServletRequest request) throws Exception {
		List<Target> ret = new ArrayList<>();
		for (Method m: target.getClass().getMethods()) {
			if (m.getAnnotation(Route.class) != null) {
				ret.add(new RouteMethodTarget(bundleContext, resource, m, target) {

					@Override
					protected Object processBodyParameter(HttpServletRequest request, Class<?> parameterType, Annotation[] parameterAnnotations) throws Exception {
						return ReflectiveRouter.this.processBodyParameter(m, request, parameterType, parameterAnnotations);
					}

					@Override
					protected <T> T convert(Object obj, Class<T> type) throws Exception {
						return ReflectiveRouter.this.convert(obj, type);
					}

					@Override
					protected Object execute(Callable<Object> command, Collection<ResourceRequirement> resourceRequirements) throws Exception {
						throw new UnsupportedOperationException("Implement locking");
//						return command.call();
					}
					
				});
			}
		}
		return ret;
	}

	/**
	 * Converts request body to parameter type. This implementation uses JSON parser for json content type 
	 * and calls convert(request.getInputStream(), parameterType) otherwise.
	 * @param context
	 * @param parameterType
	 * @return
	 * @throws Exception
	 */
	protected Object processBodyParameter(Method method, HttpServletRequest request, Class<?> parameterType, Annotation[] parameterAnnotations) throws Exception {
		// Explicit JSON conversion
		if (Util.JSON_CONTENT_TYPE.equals(request.getContentType())) {
			if (parameterType == JSONArray.class) {
				return new JSONArray(new JSONTokener(request.getReader()));
			}
			
			if (parameterType == JSONObject.class) {
				return new JSONObject(new JSONTokener(request.getReader()));
			}			
		}
		
		return convert(request.getInputStream(), parameterType);
	}

	/**
	 * Override to customize conversion, e.g. to convert from String to {@link CDOID}. 
	 * @param obj
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T> T convert(Object obj, Class<T> type) throws Exception {
		if (obj == null || type.isInstance(obj)) {
			return (T) obj;
		}
		return ReflectiveConverter.INSTANCE.convert(obj, type);
	}
	
}
