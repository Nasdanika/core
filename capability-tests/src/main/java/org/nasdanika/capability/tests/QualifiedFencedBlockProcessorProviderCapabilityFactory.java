package org.nasdanika.capability.tests;

import java.util.concurrent.CompletionStage;

import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.MarkdownHelper.QualifiedFencedBlockProcessorProvider;
import org.nasdanika.common.ProgressMonitor;

public class QualifiedFencedBlockProcessorProviderCapabilityFactory extends ServiceCapabilityFactory<Void, QualifiedFencedBlockProcessorProvider> {
	
	@Override
	public boolean isFor(Class<?> type, Object requirement) {
		return QualifiedFencedBlockProcessorProvider.class == type && requirement == null;
	}

	@Override
	protected CompletionStage<Iterable<CapabilityProvider<QualifiedFencedBlockProcessorProvider>>> createService(
			Class<QualifiedFencedBlockProcessorProvider> serviceType, 
			Void serviceRequirement,
			Loader loader,
			ProgressMonitor progressMonitor) {
		
		return wrap(qualifier -> {
			if ("my-qualifier".equals(qualifier)) {
				return c -> "|>>> " + c + " <<<|";
			}
			return null;
		});
	}
	
}
