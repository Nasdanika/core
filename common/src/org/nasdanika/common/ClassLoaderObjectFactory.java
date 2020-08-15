package org.nasdanika.common;

import java.lang.reflect.Constructor;
import java.net.URL;

/**
 * Creates object instances with {@link ClassLoader}. Type shall resolve to fully qualified class name.
 * Classes shall have a 4-argument constructors taking {@link ObjectLoader.Factory}, config object, {@link URL}, and {@link ProgressMonitor}. 
 * @author Pavel
 *
 */
public class ClassLoaderObjectFactory implements ObjectLoader.Factory {
	
	private ClassLoader classLoader;
	private java.util.function.Function<String, String> resolver;
	private ObjectLoader.Factory chain;

	/**
	 * 
	 * @param classLoader {@link ClassLoader} for loading classes.
	 * @param resolver Resolver of type to fully qualified class name. If null, then type is used AS-IS. E.g. a map::get for a map of logical names to fully qualified class names.
	 * @param chain Object loader to fall back to if resolver is not null and returned null. 
	 */
	public ClassLoaderObjectFactory(ClassLoader classLoader, java.util.function.Function<String,String> resolver, ObjectLoader.Factory chain) {
		this.resolver = resolver;
		this.classLoader = classLoader;
		this.chain = chain;
	}

	@Override
	public Object create(ObjectLoader.Factory factory, String type, Object config, URL base, ProgressMonitor progressMonitor) throws Exception {
		String fqn = resolver == null ? type : resolver.apply(type);
		if (fqn == null) {
			if (resolver == null) {
				throw new NullPointerException("Type name is null");
			}
			if (chain == null) {
				throw new IllegalArgumentException("Type was resolved to null and there is no chain loader: " + type);
			}
			
			return chain.create(factory, type, config, base, progressMonitor);
		}
		
		Class<?> clazz = classLoader.loadClass(fqn);		
		Constructor<?> constructor = clazz.getConstructor(ObjectLoader.Factory.class, Object.class, URL.class, ProgressMonitor.class); 		
		return constructor.newInstance(factory, config, base, progressMonitor);
	}

}
