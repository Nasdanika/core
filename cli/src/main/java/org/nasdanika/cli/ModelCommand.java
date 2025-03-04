package org.nasdanika.cli;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.Description;
import org.nasdanika.common.EObjectSupplier;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
		description = "Loads EObject from a URI or file",
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "model")
@ParentCommands(RootCommand.class)
@Description(icon = "https://docs.nasdanika.org/images/model.svg")
public class ModelCommand extends CommandGroup implements EObjectSupplier<EObject> {
	
	protected ModelCommand() {
		super();
	}

	protected ModelCommand(CapabilityLoader capabilityLoader) {
		super(capabilityLoader);
	}

	@Parameters(
		index =  "0",	
		arity = "1",
		description = {  
			"EObject URI or file path, resolved relative",
			"to the current directory"
		})
	private String uri;
		
	@Option(
		names = {"-f", "--file"},
		description = "URI parameter is a file path")
	private boolean isFile;
	
	@Mixin
	private ResourceSetMixIn resourceSetMixIn;

	@Override
	public Collection<EObject> getEObjects(ProgressMonitor progressMonitor) {
		ResourceSet rSet = resourceSetMixIn.createResourceSet(progressMonitor);		
		URI theURI = isFile ? URI.createFileURI(new File(uri).getAbsolutePath()) : URI.createURI(uri).resolve(URI.createFileURI(new File(".").getAbsolutePath()).appendSegment(""));
		if (theURI.hasFragment()) {
			return Collections.singleton(rSet.getEObject(theURI, true));
		}
		return rSet.getResource(theURI, true).getContents();
	}

}
