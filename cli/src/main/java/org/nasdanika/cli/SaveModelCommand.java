package org.nasdanika.cli;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Description;
import org.nasdanika.common.EObjectSupplier;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.ParentCommand;

@Command(
		description = "Saves model to a file",
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "save")
@ParentCommands(EObjectSupplier.class)
@Description(icon = "https://img.icons8.com/dusk/20/save--v1.png")
public class SaveModelCommand extends CommandBase {
	
	@Parameters(
		index =  "0",	
		arity = "1",
		description = "Output file")
	private File output;

	@ParentCommand
	private EObjectSupplier<EObject> eObjectSupplier;
	
	@Mixin
	private ProgressMonitorMixIn progressMonitorMixIn;
	
	@Mixin
	private ResourceSetMixIn resourceSetMixIn;

	@Override
	public Integer call() throws Exception {
		ProgressMonitor progressMonitor = progressMonitorMixIn.createProgressMonitor(1);
		ResourceSet resourceSet = resourceSetMixIn.createResourceSet(progressMonitor.split("Creating a resource set", 1));
		URI resourceURI = URI.createFileURI(output.getAbsolutePath());		
		Resource resource = resourceSet.createResource(resourceURI);
		resource.getContents().addAll(eObjectSupplier.getEObjects(progressMonitor.split("Generating contents", 1)));
		resource.save(null);		
		return 0;
	}	

}
