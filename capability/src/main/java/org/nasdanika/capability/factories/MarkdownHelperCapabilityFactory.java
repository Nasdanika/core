package org.nasdanika.capability.factories;

import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.MarkdownHelper;
import org.nasdanika.common.ProgressMonitor;

/**
 * Provides {@link MarkdownHelper} for a given resource base {@link URI}
 */
public class MarkdownHelperCapabilityFactory extends ServiceCapabilityFactory<URI, MarkdownHelper> {
	
	@Override
	public boolean isFor(Class<?> type, Object requirement) {
		return MarkdownHelper.class == type;
	}

	@Override
	protected CompletionStage<Iterable<CapabilityProvider<MarkdownHelper>>> createService(
			Class<MarkdownHelper> serviceType, 
			URI serviceRequirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {
		
		MarkdownHelper markdownHelper = new MarkdownHelper() {
			
			@Override
			protected URI getResourceBase() {
				return serviceRequirement;
			}
			
		};
		
		return wrap(markdownHelper);
	}
	
}
