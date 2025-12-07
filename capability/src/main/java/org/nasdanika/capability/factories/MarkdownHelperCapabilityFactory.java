package org.nasdanika.capability.factories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.CompletionStage;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.MarkdownHelper;
import org.nasdanika.common.MarkdownHelper.QualifiedFencedBlockProcessorProvider;
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
			Loader loader,
			ProgressMonitor progressMonitor) {
		
		Requirement<Object, QualifiedFencedBlockProcessorProvider> qualifiedFencedBlockProcessorProviderRequirement = ServiceCapabilityFactory.createRequirement(MarkdownHelper.QualifiedFencedBlockProcessorProvider.class);
		CompletionStage<Iterable<CapabilityProvider<QualifiedFencedBlockProcessorProvider>>> qualifiedFencedBlockProcessorProvidersCS = loader.load(qualifiedFencedBlockProcessorProviderRequirement, progressMonitor);
		
		return wrapCompletionStage(qualifiedFencedBlockProcessorProvidersCS.thenApply(qfbppcps -> createMarkdownHelper(qfbppcps, serviceRequirement)));
	}
		
	protected MarkdownHelper createMarkdownHelper( 
			Iterable<CapabilityProvider<QualifiedFencedBlockProcessorProvider>> qualifiedFencedBlockProcessorProviderCPs, 
			URI serviceRequirement) {
		
		Collection<QualifiedFencedBlockProcessorProvider> qualifiedFencedBlockProcessorProviders = Collections.synchronizedCollection(new ArrayList<>());
		for (CapabilityProvider<QualifiedFencedBlockProcessorProvider> cp: qualifiedFencedBlockProcessorProviderCPs) {
			cp.getPublisher().filter(Objects::nonNull).subscribe(qualifiedFencedBlockProcessorProviders::add);
		}
				
		return new MarkdownHelper() {
			
			@Override
			protected URI getResourceBase() {
				return serviceRequirement;
			}
			
			@Override
			protected Collection<QualifiedFencedBlockProcessorProvider> getQualifiedFencedBlockProcessorProviders() {
				return qualifiedFencedBlockProcessorProviders;
			}
			
		};
	}
	
	
}
