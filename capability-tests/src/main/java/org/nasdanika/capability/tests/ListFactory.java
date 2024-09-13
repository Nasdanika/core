package org.nasdanika.capability.tests;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletionStage;

import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

/**
 * Creates a list containing a double for a double requirement. 
 */
public class ListFactory extends ServiceCapabilityFactory<Double, List<Double>> {

	@Override
	public boolean isFor(Class<?> type, Object requirement) {
		return List.class.equals(type);
	}

	@Override
	public CompletionStage<Iterable<CapabilityProvider<List<Double>>>> createService(
			Class<List<Double>> serviceType,
			Double serviceRequirement,
			Loader loader,
			ProgressMonitor progressMonitor) {
		
		return wrap(Collections.singletonList(serviceRequirement));
	}

}
