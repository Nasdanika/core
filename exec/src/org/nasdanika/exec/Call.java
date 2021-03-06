package org.nasdanika.exec;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

import org.nasdanika.common.Context;
import org.nasdanika.common.Converter;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Function;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Attribute;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Feature;
import org.nasdanika.common.persistence.ListSupplierFactoryAttribute;
import org.nasdanika.common.persistence.MapSupplierFactoryAttribute;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.common.persistence.ReferenceList;
import org.nasdanika.common.persistence.ReferenceMap;
import org.nasdanika.common.persistence.StringSupplierFactoryAttribute;
import org.nasdanika.common.persistence.SupplierFactoryFeatureObject;

public class Call extends SupplierFactoryFeatureObject<Object> {
	
//	* ``class`` - fully qualified class name. The class is loaded using the classloader of the top-level loader as in the case of String configuration. Mutually exclusive with ``service`` and ``property``. One of ``class``, ``property``, or ``service`` is required.
//	* ``property`` - property name. Mutually exclusive with ``class`` and ``service``.
//	* ``service`` - fully qualified service class name. Mutually exclusive with ``class`` and ``property``. 
//	* ``init`` - an optional array of constructor arguments for the ``class``. Not applicable for ``property`` and ``service``. Arguments get converted to constructor parameter types if conversion is available. If conversion is not available, an exception is thrown.
//	* ``properties`` - a map injected into the instance in the ``class`` case if the instance implements ${javadoc/java.util.function.BiConsumer}.
//	* ``method`` - an optional method to call. In the ``class`` case the method can be static. If the method is static the class is not instantiated and if ``init`` or ``properties`` are present it results in an exception.
//	* ``arguments`` - an optional array of method arguments. Arguments get converted to method parameter types if conversion is available. If conversion is not available, an exception is thrown.

	private StringSupplierFactoryAttribute clazz;
	private StringSupplierFactoryAttribute property;
	private StringSupplierFactoryAttribute service;
	private StringSupplierFactoryAttribute method;
	private ListSupplierFactoryAttribute<Object,?> init;
	private ListSupplierFactoryAttribute<Object,?> args;
	private MapSupplierFactoryAttribute<Object,Object,Object> properties;
	private ClassLoader classLoader;
	
	public Call() {
		clazz = addFeature(new StringSupplierFactoryAttribute(new Attribute<String>("class", true, false, null, null, "property", "service"), true));
		property = addFeature(new StringSupplierFactoryAttribute(new Attribute<String>("property", false, false, null, null, "class", "service"), true));
		service = addFeature(new StringSupplierFactoryAttribute(new Attribute<String>("service", false, false, null, null, "class", "property"), true));
		method = addFeature(new StringSupplierFactoryAttribute(new Attribute<String>("method", false, false, null, null), true));
		init = addFeature(new ListSupplierFactoryAttribute<>(new ReferenceList<>("init", false, false, null, null, "property", "service"), false));
		args = addFeature(new ListSupplierFactoryAttribute<>(new ReferenceList<>("arguments", false, false, null, null), false));
		properties = addFeature(new MapSupplierFactoryAttribute<Object,Object,Object>((Feature<Map<Object, Object>>) new ReferenceMap<>("properties", false, false, null, null), false));
	}
	
	@Override
	public void load(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		super.load(loader, config, base, progressMonitor, marker);
		classLoader = loader.getClass().getClassLoader();
	}
	
	@Override
	protected Function<Map<Object, Object>, Object> createResultFunction(Context context) {			
		return new Function<Map<Object, Object>, Object>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Call";
			}

			@SuppressWarnings("unchecked")
			@Override
			public Object execute(Map<Object, Object> data, ProgressMonitor progressMonitor) throws Exception {
				// Validations
				if (init.isLoaded() && !clazz.isLoaded()) {
					throw new ConfigurationException("Init is only applicable with 'class'", init.getMarker());
				}
				if (args.isLoaded() && !method.isLoaded()) {
					throw new ConfigurationException("Arguments are only applicable with 'method'", init.getMarker());					
				}
				if (properties.isLoaded() && !clazz.isLoaded()) {
					throw new ConfigurationException("Properties can only be injected into new instances of 'class'", properties.getMarker());					
				}
				
				try {
					List<Object> arguments = args.isLoaded() ? (List<Object>) args.get(data) : Collections.emptyList();		

					// Static method
					if (clazz.isLoaded() && method.isLoaded()) {
						Class<?> theClass = classLoader.loadClass((String) clazz.get(data));						
						String methodName = (String) method.get(data);						
						for (Method method: theClass.getMethods()) {
							if (method.getName().equals(methodName) 
									&& Modifier.isStatic(method.getModifiers())
									&& method.getParameterCount() == arguments.size()) {
	
								if (properties.isLoaded()) {
									throw new ConfigurationException("Properties can not be specified with static methods", properties.getMarker());					
								}								
								return method.invoke(null, coerce(context, arguments, method.getParameterTypes(), progressMonitor));
							}
						}
					}
					
					// Instance method					
					Class<?> theClass;					
					Object instance = null;
					if (clazz.isLoaded()) {
						theClass = classLoader.loadClass((String) clazz.get(data));						
						List<Object> constructorArguments = init.isLoaded() ? (List<Object>) init.get(data) : Collections.emptyList();		
						for (Constructor<?> constructor: theClass.getConstructors()) {
							if (constructor.getParameterCount() == constructorArguments.size()) {								
								instance = constructor.newInstance(coerce(context, constructorArguments, constructor.getParameterTypes(), progressMonitor));
								if (properties.isLoaded()) {
									if (instance instanceof BiConsumer) {
										for (Entry<?, ?> pe: ((Map<?,?>) properties.get(data)).entrySet()) {
											((BiConsumer<Object, Object>) instance).accept(pe.getKey(), pe.getValue());
										}
									} else {
										throw new ConfigurationException("Class must implement BiConsumer for properties to be injected" , clazz.getMarker());
									}
								}
								
								break;
							}
						}				
						if (instance == null) {
							throw new ConfigurationException("Constructor with " + constructorArguments.size() + " parameters was not found in " + theClass, getMarker());
						}
					} else if (property.isLoaded()) {
						String propertyName = (String) property.get(data);
						instance = context.get(propertyName);
						if (instance == null) {
							throw new ConfigurationException("No such property: " + propertyName, property.getMarker());
						}
						theClass = instance.getClass();						
					} else if (service.isLoaded()) {
						String serviceType = (String) service.get(data);
						theClass = classLoader.loadClass(serviceType);
						instance = context.get(theClass);
						if (instance == null) {
							throw new ConfigurationException("No such service: " + serviceType, service.getMarker());
						}
					} else {
						throw new ConfigurationException("One of 'class', 'property', or 'service' configuration keys is required", getMarker());
					}

					if (!method.isLoaded()) {
						return instance;
					}
					
					String methodName = (String) method.get(data);
					for (Method method: theClass.getMethods()) {
						if (method.getName().equals(methodName) 
								&& !Modifier.isStatic(method.getModifiers())
								&& method.getParameterCount() == arguments.size()) {
							return method.invoke(instance, coerce(context, arguments, method.getParameterTypes(), progressMonitor));
						}
					}					
					throw new ConfigurationException("Method " + methodName + " with " + arguments.size() + " parameters was not found in " + theClass, method.getMarker());					
				} catch (ConfigurationException e) {
					throw e;
				} catch (Exception e) {
					Marker marker = getMarker();
					throw marker == null ? e : new ConfigurationException("Exception in call: " + e,  e, marker);
				}				
				
			}
		};
	}
	
	/**
	 * Converts arguments to parameter types where possible.
	 * @param arguments
	 * @param parameterTypes
	 * @return
	 * @throws Exception
	 */
	private Object[] coerce(Context context, List<Object> arguments, Class<?>[] parameterTypes, ProgressMonitor progressMonitor) throws Exception {
		Object[] ret = arguments.toArray();
		for (int i = 0; i < ret.length; ++i) {
			ret[i] = coerce(context, ret[i], parameterTypes[i], progressMonitor);
		}
		return ret;
	}
	
	/**
	 * Converts argument to parameter type where possible.
	 * @param argument
	 * @param parameterType
	 * @return
	 * @throws Exception
	 */
	private Object coerce(Context context, Object argument, Class<?> parameterType, ProgressMonitor progressMonitor) throws Exception {
		if (argument == null || parameterType.isInstance(argument)) {
			return argument;
		}
		if (parameterType == String.class && argument instanceof InputStream) {
			return Util.toString(context, (InputStream ) argument);
		}
		Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
		Object ret = converter.convert(argument, parameterType);
		if (ret == null) {
			throw new ConfigurationException("Cannot convert " + argument + " to " + parameterType, getMarker());
		}
		return ret;
	}

}
