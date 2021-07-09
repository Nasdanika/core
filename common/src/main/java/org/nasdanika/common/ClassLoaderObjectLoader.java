package org.nasdanika.common;

import java.lang.reflect.Constructor;
import java.net.URL;

import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

/**
 * Creates object instances with {@link ClassLoader}. Type shall resolve to fully qualified class name.
 * Classes shall have a 5-argument constructors taking {@link ObjectLoader}, config object, {@link URL}, {@link ProgressMonitor}, and {@link Marker}. 
 * @author Pavel
 *
 */
public class ClassLoaderObjectLoader implements ObjectLoader {
	
	private ClassLoader classLoader;
	private java.util.function.Function<String, String> resolver;
	private ObjectLoader chain;

	/**
	 * 
	 * @param classLoader {@link ClassLoader} for loading classes.
	 * @param resolver Resolver of type to fully qualified class name. If null, then type is used AS-IS. E.g. a map::get for a map of logical names to fully qualified class names.
	 * @param chain Object loader to fall back to if resolver is not null and returned null. 
	 */
	public ClassLoaderObjectLoader(ClassLoader classLoader, java.util.function.Function<String,String> resolver, ObjectLoader chain) {
		this.resolver = resolver;
		this.classLoader = classLoader;
		this.chain = chain;
	}

	@Override
	public Object create(ObjectLoader loader, String type, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		String fqn = resolver == null ? type : resolver.apply(type);
		if (fqn == null) {
			if (resolver == null) {
				throw new NullPointerException("Type name is null");
			}
			if (chain == null) {
				throw new IllegalArgumentException("Type was resolved to null and there is no chain loader: " + type);
			}
			
			return chain.create(loader, type, config, base, progressMonitor, marker);
		}
		
		Class<?> clazz = classLoader.loadClass(fqn);		
		Constructor<?> constructor = clazz.getConstructor(ObjectLoader.class, Object.class, URL.class, ProgressMonitor.class, Marker.class); 		
		return constructor.newInstance(loader, config, base, progressMonitor, marker);
	}

}
