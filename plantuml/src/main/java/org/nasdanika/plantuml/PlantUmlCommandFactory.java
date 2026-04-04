package org.nasdanika.plantuml;

import java.util.List;
import java.util.concurrent.CompletionStage;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.emf.ResourceSetRequirement;
import org.nasdanika.cli.SubCommandCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class PlantUmlCommandFactory extends SubCommandCapabilityFactory<PlantUmlCommand> {

	@Override
	protected Class<PlantUmlCommand> getCommandType() {
		return PlantUmlCommand.class;
	}
	
	@Override
	protected CompletionStage<PlantUmlCommand> doCreateCommand(
			List<CommandLine> parentPath,
			Loader loader,
			ProgressMonitor progressMonitor) {
		
		Requirement<ResourceSetRequirement, ResourceSet> resourceSetRequirement = ServiceCapabilityFactory.createRequirement(ResourceSet.class);		
		CompletionStage<ResourceSet> resourceSetCS = loader.loadOne(resourceSetRequirement, progressMonitor);		
		return resourceSetCS.thenApply(PlantUmlCommand::new); 		
	}

}
