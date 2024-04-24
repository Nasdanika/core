package org.nasdanika.cli;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;

import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

/**
 * Base class for MixIn factories.
 */
public abstract class MixInCapabilityFactory extends ServiceCapabilityFactory<MixInRequirement, MixInRecord> {

	@Override
	public boolean isForServiceType(Class<?> type) {
		return MixInRecord.class == type;
	}
	
	@Override
	protected CompletionStage<Iterable<CapabilityProvider<MixInRecord>>> createService(Class<MixInRecord> serviceType,
			MixInRequirement serviceRequirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {
		Object mixIn = createMixIn(serviceRequirement.commandPath(), progressMonitor);
		if (mixIn != null) {
			return wrap(new MixInRecord(getName(), mixIn));
		}
		return CompletableFuture.completedStage(Collections.emptyList());
	}
	
	protected abstract String getName();
	
	protected abstract Object createMixIn(List<CommandLine> commandPath, ProgressMonitor progressMonitor);

}
