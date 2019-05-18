package org.nasdanika.http;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.dynamichelpers.ExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler;
import org.eclipse.core.runtime.dynamichelpers.IExtensionTracker;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.Filter;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Wraps method for further wrapping into a command.
 * @author Pavel
 *
 * @param <C>
 * @param <R>
 */
public abstract class MethodCommand {
	
	/**
	 * Resolves argument value.
	 * @author Pavel Vlasov
	 *
	 */
	protected interface ArgumentResolver {
		
		Object getValue() throws Exception;
		
		default void close() {}
		
	}
	
	private ArgumentResolver[] argumentResolvers;
	protected BundleContext bundleContext;
	protected Method method;
	protected Object resource;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Function<String, String> pathVariables;
	
	public Method getMethod() {
		return method;
	}
	
	public MethodCommand(
			BundleContext bundleContext,
			HttpServletRequest request,
			HttpServletResponse response,
			Object entity,		
			Function<String, String> pathVariables,
			Method method) throws Exception {
		this.bundleContext = bundleContext;
		this.request = request;
		this.response = response;
		this.resource = entity;
		this.pathVariables = pathVariables;
		this.method = method;
		Annotation[][] pa = method.getParameterAnnotations();
		Class<?>[] pt = method.getParameterTypes();
		argumentResolvers = new ArgumentResolver[pt.length];
		for (int i=0; i<pt.length; ++i) {
			argumentResolvers[i] = createArgumentResolver(pt[i], pa[i]);
		}
	}
	
	/**
	 * Creates an argument resolver for a parameter.
	 * This implementation handles {@link ServiceParameter}, {@link ExtensionParameter}, and {@link ContextParameter} annotations. Subclasses may override
	 * this method to handle additional annotations.
	 * @param parameterType
	 * @param parameterAnnotations
	 * @return
	 */
	protected ArgumentResolver createArgumentResolver(final Class<?> parameterType, final Annotation[] parameterAnnotations) throws Exception {
		for (Annotation a: parameterAnnotations) {
			if (ResourceParameter.class.isInstance(a)) {
				return new ArgumentResolver() {

					@Override
					public Object getValue() throws Exception {
						throw new UnsupportedOperationException("TODO - implement resource resolution and then conversion.");
						//return convert(resource, parameterType);
					}
					
				};
			}
			if (RequestParameter.class.isInstance(a)) {
				return () -> request;
			}
			if (ResponseParameter.class.isInstance(a)) {
				return () -> response;
			}
		
			// Service parameter
			if (ServiceParameter.class.isInstance(a)) {
				@SuppressWarnings("unchecked")
				Class<Object> serviceType = (Class<Object>) (parameterType.isArray() ? parameterType.getComponentType() : parameterType);
				
				String filter = ((ServiceParameter) a).value();
				final ServiceTracker<Object, Object> serviceTracker;
				if (Util.isBlank(filter)) {
					serviceTracker = new ServiceTracker<Object, Object>(bundleContext, serviceType, null);				
				} else {
					Filter theFilter = bundleContext.createFilter("(&(" + Constants.OBJECTCLASS + "=" + serviceType.getName() + ")"+filter+")");
					serviceTracker = new ServiceTracker<Object, Object>(bundleContext, theFilter, null);
				}
				serviceTracker.open();
				
				return new ArgumentResolver() {
					
					@Override
					public Object getValue() throws Exception {
						if (parameterType.isArray()) {
							Object[] services = serviceTracker.getServices();
							Object ret = Array.newInstance(parameterType.getComponentType(), services==null ? 0 : services.length);
							if (services!=null) {
								System.arraycopy(services, 0, ret, 0, services.length);
							}
							return ret;
						}
						
						return serviceTracker.getService();
					}
					
					@Override
					public void close() {
						serviceTracker.close();						
					}
					
				};
				
			}
						
			// Extension parameter
			if (ExtensionParameter.class.isInstance(a)) {			
				IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
				ExtensionTracker extensionTracker = new ExtensionTracker(extensionRegistry);
		    	ExtensionParameter extensionParameter = (ExtensionParameter) a;
				IExtensionPoint extensionPoint = extensionRegistry.getExtensionPoint(extensionParameter.point());  
				
				final List<Object> extensions = new ArrayList<>();
		    	
		    	IExtensionChangeHandler extensionChangeHandler = new IExtensionChangeHandler() {

		    		@Override
		    		public void addExtension(IExtensionTracker tracker, IExtension extension) {
		    			for (IConfigurationElement ce: extension.getConfigurationElements()) {
		    				try {
			    				if (extensionParameter.configurationElement().equals(ce.getName())) {
									Object instance = ce.createExecutableExtension(extensionParameter.classAttribute());		    						
			    					synchronized (extensions) {
										extensions.add(instance);
			    					}

			    					tracker.registerObject(extension, instance, IExtensionTracker.REF_WEAK);
			    				}
		    				} catch (Exception e) {
		    					// TODO - proper logging
		    					System.err.println("Error adding extension");
		    					e.printStackTrace();
		    				}
		    			}
		    		}
		    		
		 			@Override
		    		public void removeExtension(IExtension extension, Object[] objects) {
		    			synchronized (extensions) {
			    			for (Object obj: objects) {
			    				extensions.remove(obj);
			    			}
		    			}
					}
		    		
		    	};    	
		    	
				extensionTracker.registerHandler(extensionChangeHandler, ExtensionTracker.createExtensionPointFilter(extensionPoint));
				for (IExtension ex: extensionPoint.getExtensions()) {
					extensionChangeHandler.addExtension(extensionTracker, ex);
				}
				
				return new ArgumentResolver() {
					
					@Override
					public Object getValue() throws Exception {
						synchronized (extensions) {
							if (parameterType.isArray()) {
								Object ret = Array.newInstance(parameterType.getComponentType(), extensions.size());
								int idx = 0;
								for (Object ext: extensions) {
									Array.set(ret, idx++, ext);
								}
								return ret;								
							}
							return extensions.isEmpty() ? null : extensions.get(0);
						}
					}
					
					@Override
					public void close() {
						extensionTracker.close();						
					}
				};
				
			}
			
			if (QueryParameter.class.isInstance(a)) {
				QueryParameter queryParameter = (QueryParameter) a;
				return new ArgumentResolver() {
					
					@Override
					public Object getValue() throws Exception {
						if (parameterType.isArray()) {
							Object[] values = request.getParameterValues(queryParameter.value());
							return values==null ? queryParameter.defaultValue() : values;
						}
						
						String parameterValue = request.getParameter(queryParameter.value());
						if (parameterValue!=null) {
							return parameterValue;
						}
						return queryParameter.defaultValue().length==0 ? null : queryParameter.defaultValue()[0];
					}
					
				};
			}
			
			if (HeaderParameter.class.isInstance(a)) {
				HeaderParameter headerParameter = (HeaderParameter) a;
				return new ArgumentResolver() {
					
					@Override
					public Object getValue() throws Exception {
						if (parameterType.isArray()) {
							return Collections.list(request.getHeaders(headerParameter.value()));
						}
						
						return request.getHeader(headerParameter.value());
					}
					
					@Override
					public void close() {
						// NOP						
					}
				};
			}
			if (PartParameter.class.isInstance(a)) {
				PartParameter partParameter = (PartParameter) a;
				return new ArgumentResolver() {
					
					@Override
					public Object getValue() throws Exception {
						Part part = request.getPart(partParameter.value());
						if (parameterType.isAssignableFrom(Part.class)) {
							return part;
						}
						if (part == null) {
							return part;
						}
						if (parameterType.isAssignableFrom(InputStream.class)) {
							return part.getInputStream();
						}
						
						Object ret = convert(part.getInputStream(), parameterType);
						if (ret != null) {
							return ret;
						}
						
						throw new IllegalArgumentException("Parameter type "+parameterType+" is not assignable from "+Part.class);
					}
					
					@Override
					public void close() {
						// NOP						
					}
				};
			}
			if (BodyParameter.class.isInstance(a)) {
				return new ArgumentResolver() {
					
					@Override
					public Object getValue() throws Exception {
						return processBodyParameter(parameterType, parameterAnnotations);
					}
				};
			}
			if (CookieParameter.class.isInstance(a)) {
				CookieParameter cookieParameter = (CookieParameter) a;
				return new ArgumentResolver() {
					
					@Override
					public Object getValue() throws Exception {
						List<Cookie> cookies = new ArrayList<>();
						for (Cookie cookie: request.getCookies()) {
							if (cookieParameter.value().equals(cookie.getName())) {
								cookies.add(cookie);
							}
						}
						if (parameterType.isArray()) {
							Object ret = Array.newInstance(parameterType.getComponentType(), cookies.size());
							for (int i=0; i<cookies.size(); ++i) {
								Array.set(ret, i, parameterType.getComponentType() == Cookie.class ? cookies.get(i) : cookies.get(i).getValue());
							}
							return ret;							
						}
						
						if (cookies.isEmpty()) {
							return null;
						}
						return parameterType == Cookie.class ? cookies.get(0) : cookies.get(0).getValue(); 
					}
					
				};
			}
			if (PathParameter.class.isInstance(a)) {
				PathParameter pathParameter = (PathParameter) a;
				
				return new ArgumentResolver() {
					
					@Override
					public Object getValue() throws Exception {
						return pathVariables.apply(pathParameter.value());
					}
					
				};
			}						
			
		}
						
		return null;
	}
	
	// Abstract - delegate to the Reflective router
	/**
	 * Converts request body to parameter type. This implementation uses JSON parser for json content type 
	 * and calls convert(request.getInputStream(), parameterType) otherwise.
	 * @param context
	 * @param parameterType
	 * @return
	 * @throws Exception
	 */
	protected abstract Object processBodyParameter(Class<?> parameterType, Annotation[] parameterAnnotations) throws Exception;
	
//	private static final String ID_KEY = "$id";	
	
//	/**
//	 * Converts request to parameter type. This implementation instantiates parameter type and then injects request parameters with {@link CoreUtil}.injectProperty method.
//	 * Multi-value parameters are currently not supported.
//	 * @param context
//	 * @param parameterType
//	 * @return
//	 * @throws Exception
//	 */
//	protected Object processModelParameter(Class<?> parameterType, Annotation[] parameterAnnotations) throws Exception {				
//		if (EObject.class.isAssignableFrom(parameterType)) {
//			CDOView view = entity.cdoView();
//			String idParameter = request.getParameter(ID_KEY);
//			if (Util.isBlank(idParameter)) {
//				CDOPackageRegistry packageRegistry = view.getSession().getPackageRegistry();										
//				EClassifier eClassifier = resolveModelEClassifier(packageRegistry, parameterType);
//				if (eClassifier instanceof EClass) {
//					EObject model = EcoreUtil.create((EClass) eClassifier);
//					inject(context, context.getRequest().getParameterMap(), model);
//					return model;
//				}						
//			} else {
//				CDOObject model = ((CDOViewContext<?,?>) context).getView().getObject(CDOIDCodec.INSTANCE.decode(context, idParameter));
//				inject(context, context.getRequest().getParameterMap(), model);
//				return model;						
//			}					
//		}
//		return super.processModelParameter(context, parameterType);
//	}
			
//	/** Finds EClass by Class in the global registry.
//	 * @param modelClass
//	 * @return
//	 */
//	private EClassifier resolveModelEClassifier(Registry registry, Class<?> modelClass) {
//		Map<Class<?>, EClassifier> typeMap = modelTypeMap.get(registry);
//		if (typeMap == null) {
//			typeMap = new ConcurrentHashMap<>();
//			modelTypeMap.put(registry, typeMap);
//		}
//		EClassifier ret = typeMap.get(modelClass);
//		if (ret==null) {
//			//Registry registry = EPackage.Registry.INSTANCE;
//			for (String nsURI: registry.keySet()) {			
//				EPackage ePackage = registry.getEPackage(nsURI);
//				for (EClassifier c: ePackage.getEClassifiers()) {
//					if (c.getInstanceClass() == modelClass) {
//						ret = c;
//						typeMap.put(modelClass, ret);
//						return ret;
//					}
//				}
//			}
//		}
//		return ret;
//	}	
	
//	@SuppressWarnings("unchecked")
//	protected void inject(Context context, Map<String, String[]> parameterMap, EObject model) throws Exception {
//		for (EStructuralFeature feature: model.eClass().getEAllStructuralFeatures()) {
//			String[] values = parameterMap.get(feature.getName());
//			if (values==null) {
//				Map<String, String[]> subMap = new HashMap<>();
//				String dotPrefix = feature.getName()+".";
//				int maxSize = -1;
//				for (Entry<String, String[]> e: parameterMap.entrySet()) {
//					if (e.getKey().startsWith(dotPrefix)) {
//						String[] value = e.getValue();
//						if (value.length > maxSize) {
//							maxSize = value.length;
//						}
//						subMap.put(e.getKey().substring(dotPrefix.length()), value);
//					}					
//				}
//				
//				if (!subMap.isEmpty() && feature instanceof EAttribute) {
//					throw new IllegalArgumentException("Hierarhical parameter names are not supported for attributes: "+feature.getName()+" "+subMap);
//				}				
//				
//				for (int i=0; i<maxSize; ++i) {
//					Map<String, String[]> entryMap = new HashMap<>();
//					for (Entry<String, String[]> e: subMap.entrySet()) {
//						if (e.getKey().startsWith(dotPrefix)) {
//							String[] value = e.getValue();
//							if (i < value.length) {
//								entryMap.put(e.getKey(), new String[] {value[i]});
//							}
//						}					
//					}
//					EObject subModel = EcoreUtil.create(((EReference) feature).getEReferenceType());
//					inject(context, entryMap, subModel);
//					if (feature.isMany()) {
//						((Collection<EObject>) model.eGet(feature)).add(subModel);
//					} else {
//						if (i == 0) {
//							model.eSet(feature, subModel);
//						} else {
//							throw new IllegalArgumentException("Cannot inject multi-value into a single-value reference: "+feature.getName()+" "+i+" "+entryMap);
//						}
//					}
//				}
//			} else {
//				if (feature.isMany()) {
//					Collection<Object> featureValue = (Collection<Object>) model.eGet(feature);
//					featureValue.clear();					
//					for (int i=0; i<values.length; ++i) {
//						if (feature instanceof EReference) {
//							// Got to be a CDOID
//							CDOObject value = ((CDOViewContext<?,?>) context).getView().getObject(CDOIDCodec.INSTANCE.decode(context, values[i]));
//							featureValue.add(value);
//						} else {
//							featureValue.add(fromString(context, (EAttribute) feature, values[i]));
//						}
//					}
//				} else {
//					if (values.length>1) {
//						throw new IllegalArgumentException("Multiple values for "+feature.getName()+": "+Arrays.toString(values));
//					} else if (values.length == 1) {
//						if (feature instanceof EReference) {
//							// Got to be a CDOID
//							CDOObject value = ((CDOViewContext<?,?>) context).getView().getObject(CDOIDCodec.INSTANCE.decode(context, values[0]));
//							model.eSet(feature, value);
//						} else {
//							model.eSet(feature, fromString(context, (EAttribute) feature, values[0]));
//						}
//					}
//				}
//			}			
//		}
//	}	
//	/**
//	 * Converts String value to attribute type. This implementation delegates to context.convert();
//	 * Override to customize.
//	 * @param attribute
//	 * @param value
//	 * @return
//	 */
//	protected Object fromString(Context context, EAttribute attribute, String value) throws Exception {
//		if (value==null) {
//			return null;
//		}
//		Class<?> instanceClass = attribute.getEType().getInstanceClass();
//		if (instanceClass!=null) {
//			if (instanceClass.isInstance(value)) {
//				return value;
//			}
//			Object convertedValue = context.convert(value, instanceClass);
//			if (convertedValue == null) {
//				throw new IllegalArgumentException("Cannot convert "+value+"("+value.getClass().getName()+") to "+instanceClass.getName());
//			}
//			return convertedValue;
//		}
//		return value;
//	}	
//	
	

	public void close() {
		for (ArgumentResolver ar: argumentResolvers) {
			if (ar!=null) {
				ar.close();
			}
		}				
	}	

	/**
	 * Resolves arguments and invokes the method.
	 */
	public Object execute(Object target) throws Exception {
		if (target!=null && !method.getDeclaringClass().isInstance(target)) {
			throw new IllegalArgumentException("Invalid target: "+method.getDeclaringClass().getName() +" is not assignable from "+target.getClass().getName());
		}
		Class<?>[] pt = method.getParameterTypes();
		Object[] args = new Object[pt.length];
		for (int i=0; i<args.length; ++i) {
			args[i] = convert(getArgument(i), pt[i]);
		}
		return method.invoke(target, args);
	}
	
	protected Object getArgument(int parameterIndex) throws Exception {
		if (argumentResolvers[parameterIndex]==null) {
			throw new IllegalStateException("No argument resolver for parameter "+parameterIndex+" for method "+method);
		}
		return argumentResolvers[parameterIndex].getValue();		
	}
	
	// TODO - make abstract, convert in reflective router
	
	protected abstract <T> T convert(Object obj, Class<T> type) throws Exception;
	
	@Override
	public String toString() {
		return "MethodCommand [method=" + method + "]";
	}
	
}
