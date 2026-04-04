package org.nasdanika.plantuml;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.nasdanika.cli.SubCommandCapabilityFactory;
import org.nasdanika.common.EModelElementSupplier;
import org.nasdanika.common.EObjectSupplier;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;

import picocli.CommandLine;

public class ClassDiagramCommandFactory extends SubCommandCapabilityFactory<ClassDiagramCommand> {

	@Override
	protected Class<ClassDiagramCommand> getCommandType() {
		return ClassDiagramCommand.class;
	}
	
	@Override
	protected CompletionStage<ClassDiagramCommand> doCreateCommand(
			List<CommandLine> parentPath,
			Loader loader,
			ProgressMonitor progressMonitor) {
		
		// Do not bind to EModelElementSuppliers and other sub-interfaces of EObjectSupplier - would be an infinite loop
		if (!parentPath.isEmpty()) {
			CommandLine lastCommand = parentPath.get(parentPath.size() - 1);
			Object userObject = lastCommand.getCommandSpec().userObject();
			if (userObject instanceof EModelElementSupplier) {
				return null;
			}
			if (userObject instanceof EObjectSupplier) {
				// Check for sub-interfaces of EObjectSupplier
				Class<?> commandClass = userObject.getClass();
				for (Class<?> ancestor: Util.lineage(commandClass)) {
					if (ancestor.isInterface() && EObjectSupplier.class.isAssignableFrom(ancestor) && ancestor != EObjectSupplier.class) {
						return null;
					}
				}; 
			}
		}
			
		return CompletableFuture.completedStage(new ClassDiagramCommand());
	}

}
