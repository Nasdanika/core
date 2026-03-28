package org.nasdanika.cli;

import java.util.Collection;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.EModelElementSupplier;
import org.nasdanika.common.EObjectSupplier;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine.Command;
import picocli.CommandLine.ParentCommand;

@Command(
		description = "Filters Ecore model elements",
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "ecore")
@ParentCommands(EObjectSupplier.class)
public class EcoreCommand extends CommandGroup implements EModelElementSupplier<EModelElement> {
	
	@ParentCommand
	EObjectSupplier<EObject> eObjectSupplier;

	@Override
	public Collection<EModelElement> getEObjects(ProgressMonitor progressMonitor) {
		return eObjectSupplier.getEObjects(progressMonitor).stream()
				.filter(EModelElement.class::isInstance)
				.map(EModelElement.class::cast)
				.toList(); 
	}

}
