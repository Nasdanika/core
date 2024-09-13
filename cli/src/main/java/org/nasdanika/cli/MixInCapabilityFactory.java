package org.nasdanika.cli;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.Adaptable;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

/**
 * Base class for MixIn factories.
 */
public abstract class MixInCapabilityFactory<T> extends ServiceCapabilityFactory<MixInRequirement, MixInRecord> {

	@Override
	public boolean isFor(Class<?> type, Object requirement) {
		return MixInRecord.class == type;
	}
	
	@Override
	protected CompletionStage<Iterable<CapabilityProvider<MixInRecord>>> createService(Class<MixInRecord> serviceType,
			MixInRequirement serviceRequirement,
			Loader loader,
			ProgressMonitor progressMonitor) {
		CompletionStage<T> mixInCS = createMixIn(serviceRequirement.commandPath(), loader, progressMonitor);
		if (mixInCS != null) {
			return wrapCompletionStage(mixInCS.thenApply(mixIn -> new MixInRecord(getName(), mixIn)));
		}
		return CompletableFuture.completedStage(Collections.emptyList());
	}
	
	protected abstract String getName();
	
	protected abstract Class<T> getMixInType();
	
	/**
	 * Matches command to parent using annotations.
	 * @param commandPath
	 * @return
	 */
	protected boolean match(List<CommandLine> commandPath) {
		if (commandPath == null || commandPath.isEmpty()) {
			return false;
		}
		
		CommandLine parent = commandPath.get(commandPath.size() - 1);
		Object userObject = parent.getCommandSpec().userObject();
		if (userObject != null) {
			MixIns mixInsAnnotation = userObject.getClass().getAnnotation(MixIns.class);
			Class<T> mixInType = getMixInType();
			if (mixInsAnnotation != null && mixInType != null) {
				for (Class<?> at: mixInsAnnotation.value()) {
					if (at.isAssignableFrom(mixInType)) {
						return true;
					}
				}
			}
			
			if (mixInType != null) {
				ParentCommands parentCommands = mixInType.getAnnotation(ParentCommands.class);
				if (parentCommands != null) {
					for (Class<?> pt: parentCommands.value()) {
						if (Adaptable.adaptTo(userObject, pt) != null) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	protected CompletionStage<T> createMixIn(
			List<CommandLine> commandPath, 
			Loader loader,
			ProgressMonitor progressMonitor) {		
		return match(commandPath) ? doCreateMixIn(commandPath, loader, progressMonitor) : null;
	}

	protected abstract CompletionStage<T> doCreateMixIn(
			List<CommandLine> commandPath, 
			Loader loader,
			ProgressMonitor progressMonitor);
	
}
