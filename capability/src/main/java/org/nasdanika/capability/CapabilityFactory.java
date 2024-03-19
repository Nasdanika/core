package org.nasdanika.capability;

import java.util.ServiceLoader;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;

import org.nasdanika.common.ProgressMonitor;

/**
 * Creates providers of capabilities satisfying the requirement.
 * This is a service interface intended to be used with {@link ServiceLoader}. 
 */
public interface CapabilityFactory<R,C> {
	
	
	boolean canHandle(Object requirement);
	
	/**
	 * Asynchronously creates providers of capabilities satisfying the requirement.
	 * @param requirement Some requirement object. E.g. if the requirement is a class, then class instances would be capabilities.
	 * Such capabilities can be loaded by the {@link ServiceLoader} or by other means.  
	 * @param resolver Use to obtain dependency capabilities by requirements. E.g. service instance by service class.
	 * @return
	 */
	CompletionStage<Iterable<CapabilityProvider<C>>> create(
			R requirement, 
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor);	
	
}
