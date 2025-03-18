package org.nasdanika.emf;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.cli.ParentCommands;
import org.nasdanika.cli.RootCommand;
import org.nasdanika.cli.CommandGroup;
import org.nasdanika.cli.ResourceSetMixIn;
import org.nasdanika.common.BasicDiagnostic;
import org.nasdanika.common.Description;
import org.nasdanika.common.EObjectSupplier;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;

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
		Resource resource = rSet.getResource(theURI, true);
		Diagnostician diagnostician = new Diagnostician();
		Map<?, ?> diagnosticContext = createDiagnosticContext(progressMonitor);
		BasicDiagnostic diagnostic = new BasicDiagnostic(Status.SUCCESS, "Diagnostic of " + theURI);
		for (EObject e: resource.getContents()) {
			diagnostic.add(org.nasdanika.emf.EmfUtil.wrap(diagnostician.validate(e, diagnosticContext)));
		}
		
		if (diagnostic.getStatus() == Status.ERROR) {
			diagnostic.dump(System.err, 2, Status.ERROR, Status.WARNING);
			throw new NasdanikaException("Resource validation failed");
		}
		
		return resource.getContents();
	}
	
	protected Map<?,?> createDiagnosticContext(ProgressMonitor progressMonitor) {
		return Collections.emptyMap();
	}

}
