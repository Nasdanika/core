package org.nasdanika.exec.gen;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.BiConsumer;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.BasicDiagnostic;
import org.nasdanika.common.Context;
import org.nasdanika.common.Converter;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ExecutionException;
import org.nasdanika.common.Function;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.MapCompoundSupplierFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.exec.Call;
import org.nasdanika.exec.ExecPackage;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.persistence.Marked;

public class CallSupplierFactoryAdapter extends AdapterImpl implements SupplierFactory<InputStream> {
	
	public CallSupplierFactoryAdapter(Call call) {
		setTarget(call);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == SupplierFactory.class;
	}
	
	@Override
	public Supplier<InputStream> create(Context context) {
		MapCompoundSupplierFactory<EStructuralFeature, Object> featureMapFactory = new MapCompoundSupplierFactory<>("Feature map factory");
		Call call = (Call) getTarget();
		EList<EObject> init = call.getInit();
		if (!init.isEmpty()) {
			ListCompoundSupplierFactory<Object> initFactory = new ListCompoundSupplierFactory<>("Init"); 
			for (EObject initArg: init) {
				initFactory.add(Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(initArg, Object.class), "Cannot adapt to SupplierFactory: " + initArg));
			}
			featureMapFactory.put(ExecPackage.Literals.CALL__INIT, initFactory);
		}
		
		EList<EObject> args = call.getArguments();
		if (!args.isEmpty()) {
			ListCompoundSupplierFactory<Object> argFactory = new ListCompoundSupplierFactory<>("Arguments"); 
			for (EObject arg: args) {
				argFactory.add(Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(arg, Object.class), "Cannot adapt to SupplierFactory: " + arg));
			}
			featureMapFactory.put(ExecPackage.Literals.CALL__ARGUMENTS, argFactory);
		}
		
		EMap<String, EObject> properties = call.getProperties();
		if (!properties.isEmpty()) {
			MapCompoundSupplierFactory<String,Object> propertiesFactory = new MapCompoundSupplierFactory<>("Properties");
			for (Entry<String, EObject> pe: properties.entrySet()) {
				propertiesFactory.put(pe.getKey(), Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(pe.getValue(), Object.class), "Cannot adapt to SupplierFactory: " + pe.getValue()));
			}
			featureMapFactory.put(ExecPackage.Literals.CALL__PROPERTIES, propertiesFactory);
		}
		
		return featureMapFactory.then(this::createResultFunction).create(context);
	}
	
	protected Function<Map<EStructuralFeature,Object>, InputStream> createResultFunction(Context context) {
		Call call = (Call) getTarget();
		
		return new Function<Map<EStructuralFeature,Object>, InputStream>() {
			
			private Class<?> clazz;
			private Constructor<?> constructor;
			private Object instance;		
			private Method method;

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				String description = call.getDescription();
				return Util.isBlank(description) ? "Call" : "Call " + description;
			}
			
			@Override
			public Diagnostic diagnose(ProgressMonitor progressMonitor) {
				String property = call.getProperty();
				if (!Util.isBlank(property)) {
					instance = context.get(property);
					if (instance == null) {
						return new BasicDiagnostic(Status.ERROR, "Call property not found: " + property, call);
					}
					if (!call.getInit().isEmpty()) {
						return new BasicDiagnostic(Status.ERROR, "Cannot specify constructor arguments for property", call);								
					}
					if (!call.getProperties().isEmpty()) {
						return new BasicDiagnostic(Status.ERROR, "Cannot specify properties for property", call);								
					}
				} else {
					ClassLoader classLoader = context.get(ClassLoader.class, getClass().getClassLoader());
					String service = call.getService();
					if (!Util.isBlank(service)) {
						try {
							instance = context.get(classLoader.loadClass(service));
							if (instance == null) {
								return new BasicDiagnostic(Status.ERROR, "Call service not found: " + service, call);
							}
							if (!call.getInit().isEmpty()) {
								return new BasicDiagnostic(Status.ERROR, "Cannot specify constructor arguments for service", call);								
							}
							if (!call.getProperties().isEmpty()) {
								return new BasicDiagnostic(Status.ERROR, "Cannot specify properties for service", call);								
							}
						} catch (ClassNotFoundException e) {
							return new BasicDiagnostic(Status.ERROR, "Service class not found: " + service, call);
						}						
					} else {
						String type = call.getType();
						if (Util.isBlank(type)) {
							return new BasicDiagnostic(Status.ERROR, "Call must specify one of 'class', 'property', or 'service'", call);							
						}
						try {
							clazz = classLoader.loadClass(type);
							if (!BiConsumer.class.isAssignableFrom(clazz) && !call.getProperties().isEmpty()) {
								return new BasicDiagnostic(Status.ERROR, "In order to inject properties " + clazz.getName() + " must implement " + BiConsumer.class, call);															
							}
							if ((SupplierFactory.class.isAssignableFrom(clazz) || Supplier.class.isAssignableFrom(clazz))
									&& call.getInit().isEmpty()
									&& call.getProperties().isEmpty()
									&& Util.isBlank(call.getMethod())) {
								// Special case - creating an instance here so can be diagnosed
								try {
									Constructor<?> constr = clazz.getDeclaredConstructor();
									Object obj = constr.newInstance();
									instance = SupplierFactory.class.isAssignableFrom(clazz) ? ((SupplierFactory<?>) obj).create(context) : (Supplier<?>) obj;
									clazz = null;
									return ((Supplier<?>) instance).diagnose(progressMonitor);
								} catch (Exception e) {
									return new BasicDiagnostic(Status.ERROR, "Cannot instantiate " + clazz, call, e);															
									
								}								
							}
						} catch (ClassNotFoundException e) {
							return new BasicDiagnostic(Status.ERROR, "Class not found: " + type, call);
						}												
					}
				}
				String methodName = call.getMethod();
				if (Util.isBlank(methodName)) {
					if (!call.getArguments().isEmpty()) {
						return new BasicDiagnostic(Status.ERROR, "Cannot specify arguments without method", call);								
					}
					for (Constructor<?> constructor: clazz.getConstructors()) {
						if (constructor.getParameterCount() == call.getInit().size()) {
							this.constructor = constructor;
							break;
						}
					}		
					if (constructor == null) {
						return new BasicDiagnostic(Status.ERROR, "There is no constructor for class '" + call.getType() + "' which takes " + call.getInit().size() + " arguments", call);
					}
				} else {
					if (clazz == null) {
						for (Method method: instance.getClass().getMethods()) {
							if (method.getName().equals(methodName) && method.getParameterCount() == call.getArguments().size() && !Modifier.isStatic(method.getModifiers())) {
								this.method = method;
								break;
							}
						}		
						if (method == null) {
							return new BasicDiagnostic(Status.ERROR, "Method '" + methodName +"' with " + call.getArguments().size() + " arguments not found in " + instance, call);																						
						}
					} else {
						for (Method method: clazz.getMethods()) {
							if (method.getName().equals(methodName) && method.getParameterCount() == call.getArguments().size()) {
								if (Modifier.isStatic(method.getModifiers())) {
									if (!call.getInit().isEmpty()) {
										return new BasicDiagnostic(Status.ERROR, "Cannot specify constructor arguments for a static method", call);								
									}								
									if (!call.getProperties().isEmpty()) {
										return new BasicDiagnostic(Status.ERROR, "Cannot specify properties for a static method", call);								
									}
								} else if (clazz != null) {
									for (Constructor<?> constructor: clazz.getConstructors()) {
										if (constructor.getParameterCount() == call.getInit().size()) {
											this.constructor = constructor;
											break;
										}
									}		
									if (constructor == null) {
										return new BasicDiagnostic(Status.ERROR, "There is no constructor for class '" + call.getType() + "' which takes " + call.getInit().size() + " arguments", call);
									}
								}
								this.method = method;
								break;
							}
						}		
						if (method == null) {
							return new BasicDiagnostic(Status.ERROR, "Method '" + methodName +"' with " + call.getArguments().size() + " arguments not found in " + clazz, call);																						
						}						
					}
				}
				return Function.super.diagnose(progressMonitor);
			}

			@SuppressWarnings("unchecked")
			@Override
			public InputStream execute(Map<EStructuralFeature,Object> data, ProgressMonitor progressMonitor) {
				List<Object> initList = (List<Object>) data.get(ExecPackage.Literals.CALL__INIT);
				
				Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
				Object obj;
				if (clazz == null) {
					obj = instance;
				} else if (constructor == null) {
					obj = null;
				} else {
					Object[] constructorArguments = coerce(context, initList, constructor.getParameterTypes(), progressMonitor);
					try {
						obj = constructor.newInstance(constructorArguments);
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException	| InvocationTargetException e) {
						throw new ExecutionException(e, this);
					}
					Map<String,Object> properties = (Map<String,Object>) data.get(ExecPackage.Literals.CALL__PROPERTIES);					
					if (properties != null) {
						for (Entry<String, Object> pe: properties.entrySet()) {
							((BiConsumer<String,Object>) obj).accept(pe.getKey(), pe.getValue());
						}
					}
				}

				Object result;
				if (method == null) {
					result = obj;
				} else {
					List<Object> argList = (List<Object>) data.get(ExecPackage.Literals.CALL__ARGUMENTS);
					Object[] methodArguments = coerce(context, argList, method.getParameterTypes(), progressMonitor);
					try {
						result = method.invoke(obj, methodArguments);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						throw new ExecutionException(e, this);
					}					
				}

				if (result instanceof SupplierFactory) {
					result = ((SupplierFactory<?>) result).create(context); 
				} 
				
				if (result instanceof Supplier) {
					try {
						result = Util.call((Supplier<?>) result, progressMonitor, null);
					} catch (Exception e) {
						throw new ExecutionException(e, this);
					}
				}
				
				if (result == null) {
					return null;
				}

				InputStream ret = converter.convert(result, InputStream.class);
				if (ret == null) {
					throw new ConfigurationException("Cannot convert '" + obj + "' to InputStream", EObjectAdaptable.adaptTo((EObject) getTarget(), Marked.class));
				}
				return ret;
			}
		};
	}
	
	/**
	 * Converts arguments to parameter types where possible.
	 * @param arguments
	 * @param parameterTypes
	 * @return
	 */
	private Object[] coerce(Context context, List<Object> arguments, Class<?>[] parameterTypes, ProgressMonitor progressMonitor) {
		if (arguments == null) {
			return null;
		}
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
	 */
	private Object coerce(Context context, Object argument, Class<?> parameterType, ProgressMonitor progressMonitor) {
		if (argument == null || parameterType.isInstance(argument)) {
			return argument;
		}
		if (parameterType == String.class && argument instanceof InputStream) {
			try {
				return Util.toString(context, (InputStream ) argument);
			} catch (IOException e) {
				throw new ExecutionException(e);
			}
		}
		Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
		Object ret = converter.convert(argument, parameterType);
		if (ret == null) {
			throw new ConfigurationException("Cannot convert " + argument + " to " + parameterType, EObjectAdaptable.adaptTo((EObject) getTarget(), Marked.class));
		}
		return ret;
	}	

}
