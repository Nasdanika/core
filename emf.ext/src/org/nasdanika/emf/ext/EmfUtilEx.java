package org.nasdanika.emf.ext;

import java.net.URL;
import java.util.Objects;
import java.util.function.BiConsumer;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Converter;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EmfUtil;
import org.osgi.framework.Bundle;

public class EmfUtilEx {
	
	public static final String BUNDLE_PROTOCOL = "bundle://";
	
	/**
	 * Resolves reference relative to the resource. <code>bundle://</code> protocol can be used to reference resources in OSGi bundles similarly to <code>plugin://</code> protocol.  
	 * @param resource Used to resolve relative references if not null.
	 * @param reference Reference.
	 * @return
	 * @throws Exception
	 */
	public static URL resolveReference(Resource resource, String reference) throws Exception {
		if (reference.startsWith(BUNDLE_PROTOCOL)) {
			String bp = reference.substring(BUNDLE_PROTOCOL.length());
			int slashIdx = bp.indexOf("/");
			Bundle bundle = Platform.getBundle(bp.substring(0, slashIdx));
			return bundle.getEntry(bp.substring(slashIdx));
		}
		
		if (resource != null) {
			URI resUri = resource.getURI();
			URI refUri = URI.createURI(reference);
			URI resolvedUri = refUri.resolve(resUri);
			return new URL(resolvedUri.toString());			
		}
		
		return new URL(reference);
	}
	
	/**
	 * Loads element documentation from a documentation resource specified in documentation-reference nasdanika (urn:org.nasdanika) annotation resolved relative to the 
	 * model element resource. 
	 * @param modelElement
	 * @return
	 */
	public static String getDocumentation(EModelElement modelElement) {
		EAnnotation nasdanikaAnnotation = EmfUtil.getNasdanikaAnnotation(modelElement);
		if (nasdanikaAnnotation != null) {
			String docRef = nasdanikaAnnotation.getDetails().get("documentation-reference");
			if (!Util.isBlank(docRef)) {
				try {
					return DefaultConverter.INSTANCE.toString(resolveReference(modelElement.eResource(), docRef).openStream());
				} catch (Exception e) {
					e.printStackTrace();
					return "Error loading documentation: " + e;
				}
			}
		}
		
		return null;
	}
	
	// --- Methods for working with named adapter factories ---
	
	/**
	 * Adapts target to type using specified named factory. Named factory is addressed as <code>&lt;bundle symbolic name&gt;/&lt;factory id&gt;</code>, e.g. <code>com.myorg.mybundle/my-factory</code>.
	 * @param <A>
	 * @param target
	 * @param type
	 * @param factory
	 * @return Adapter for requested type created by the specified named factory. May return null.
	 * @throws IllegalArgumentException if factory is not found or is not for requested type.
	 * @throws NasdanikaException wrapping {@link CoreException} if factory could not be instantiated.
	 */
	@SuppressWarnings("unchecked")
	public static <A> A adaptTo(EObject target, Class<A> type, String factory) {
		if (target == null) {
			return null;
		}
		for (IConfigurationElement ce: Platform.getExtensionRegistry().getConfigurationElementsFor(Activator.ADAPTER_FACTORIES_EXTENSION_POINT_ID)) {
			if ("named-factory".equals(ce.getName())) {
				try {					
					AdapterFactory f = (AdapterFactory) ce.createExecutableExtension("class");
					if (f.isFactoryForType(type)) {
						for (IConfigurationElement parameter: ce.getChildren("parameter")) {
							((BiConsumer<String, String>) f).accept(parameter.getAttribute("name"), parameter.getAttribute("value"));
						}						
						return (A) f.adapt(target, type);
					}
					throw new IllegalArgumentException("Adapter factory " + factory + "(" + ce.getAttribute("class") + ")  is not a factory for " + type);
				} catch (CoreException e) {
					throw new NasdanikaException("Could not instantiate adapter factory " + ce.getAttribute("class"), e);
				}
			}
		}
		throw new IllegalArgumentException("Named factory not found: " + factory);		
	}

	
	/**
	 * Adapts to a {@link SupplierFactory} of specific type by adapting to {@link SupplierFactory.Provider} first and obtaining a typed factory from it.
	 * If it doesn't work then adapts to SupplierFactory and then's it with a {@link FunctionFactory} which converts the result to requested type.
	 * @param <T>
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> SupplierFactory<T> adaptToSupplierFactory(EObject target, Class<T> type, String factoryId) {
		SupplierFactory.Provider provider = adaptTo(target, SupplierFactory.Provider.class, factoryId);
		if (provider != null) {
			SupplierFactory<T> factory = provider.getFactory(type);
			if (factory != null) {
				return factory;
			}
		}
		SupplierFactory<Object> factory = adaptTo(target, SupplierFactory.class, factoryId);
		if (factory == null) { 
			return null;
		}
		return factory.then(new FunctionFactory<Object,T>() {

			@Override
			public Function<Object, T> create(Context context) throws Exception {				
				return new Function<Object, T>() {

					@Override
					public double size() {
						return 1;
					}

					@Override
					public String name() {
						return "Converter to "+type;
					}

					@Override
					public T execute(Object result, ProgressMonitor progressMonitor) throws Exception {
						if (result == null || type.isInstance(result)) {
							return (T) result;
						}
						Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
						T ret = converter.convert(result, type);
						if (ret == null) {
							throw new IllegalArgumentException("Cannot convert " + result.getClass() + " " + result + " to " + type);
						}
						return ret;
					}
				};
			}
			
		});		
	}
	
	/**
	 * Adapts target to {@link SupplierFactory} and throws {@link NullPointerException} if result is null.
	 * @param <T>
	 * @param target
	 * @param type
	 * @return
	 */
	public static <T> SupplierFactory<T> adaptToSupplierFactoryNonNull(EObject target, Class<T> type, String factoryId) {
		return Objects.requireNonNull(adaptToSupplierFactory(target, type, factoryId), "Cannot adapt " + target + " to SupplierFactory<" + type + "> with named factory " + factoryId);
	}
	
	/**
	 * Adapts to a {@link FunctionFactory} of specific parameter and result types by adapting to {@link FunctionFactory.Provider} first and obtaining a typed factory from it.
	 * If it doesn't work then adapts to FunctionFactory and then's it with a {@link FunctionFactory} which converts the result to requested type. The argument is not converted.
	 * @param <T>
	 * @param <R>
	 * @param parameterType
	 * @param resultType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T,R> FunctionFactory<T,R> adaptToFunctionFactory(EObject target, Class<T> parameterType, Class<R> resultType, String factoryId) {
		FunctionFactory.Provider provider = adaptTo(target, FunctionFactory.Provider.class, factoryId);
		if (provider != null) {
			FunctionFactory<T,R> factory = provider.getFactory(parameterType, resultType);
			if (factory != null) {
				return factory;
			}
		}
		FunctionFactory<Object,Object> factory = adaptTo(target, FunctionFactory.class, factoryId);
		if (factory == null) { 
			return null;
		}
		return (FunctionFactory<T,R>) factory.then(new FunctionFactory<Object,R>() {

			@Override
			public Function<Object,R> create(Context context) throws Exception {				
				return new Function<Object,R>() {

					@Override
					public double size() {
						return 1;
					}

					@Override
					public String name() {
						return "Converter to "+resultType;
					}

					@Override
					public R execute(Object result, ProgressMonitor progressMonitor) throws Exception {
						if (result == null || resultType.isInstance(result)) {
							return (R) result;
						}
						Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
						R ret = converter.convert(result, resultType);
						if (ret == null) {
							throw new IllegalArgumentException("Cannot convert " + result.getClass() + " " + result + " to " + resultType);
						}
						return ret;
					}
				};
			}
			
		});		
	}
	
	/**
	 * Adapts target to FunctionFactory and throws {@link NullPointerException} if result is null.
	 * @param <T>
	 * @param target
	 * @param type
	 * @return
	 */
	public static <T,R> FunctionFactory<T,R> adaptToFunctionFactoryNonNull(EObject target, Class<T> parameterType, Class<R> resultType, String factoryId) {
		return Objects.requireNonNull(adaptToFunctionFactory(target, parameterType, resultType, factoryId), "Cannot adapt " + target + " to FunctionFactory<" + parameterType + ", " + resultType + "> with named factory" + factoryId);
	}
	
	/**
	 * Adapts to a {@link ConsumerFactory} of specific type by adapting to {@link ConsumerFactory.Provider} first and obtaining a typed factory from it.
	 * If it doesn't work then adapts to ConsumerFactory.
	 * @param <T>
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> ConsumerFactory<T> adaptToConsumerFactory(EObject target, Class<T> type, String factoryId) {
		ConsumerFactory.Provider provider = adaptTo(target, ConsumerFactory.Provider.class, factoryId);
		if (provider != null) {
			ConsumerFactory<T> factory = provider.getFactory(type);
			if (factory != null) {
				return factory;
			}
		}
		return (ConsumerFactory<T>) adaptTo(target, SupplierFactory.class, factoryId);
	}
		
	/**
	 * Adapts target to ConsumerFactory and throws {@link NullPointerException} if result is null.
	 * @param <T>
	 * @param target
	 * @param type
	 * @return
	 */
	public static <T> ConsumerFactory<T> adaptToConsumerFactoryNonNull(EObject target, Class<T> type, String factoryId) {
		return Objects.requireNonNull(adaptToConsumerFactory(target, type, factoryId), "Cannot adapt " + target + " to ConsumerFactory<" + type + "> with named factory " + factoryId);
	}

}
