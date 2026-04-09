package org.nasdanika.xcore.cli;

import java.io.File;
import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XcoreStandaloneSetup;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.cli.CommandGroup;
import org.nasdanika.cli.ParentCommands;
import org.nasdanika.cli.RootCommand;
import org.nasdanika.common.Description;
import org.nasdanika.common.EModelElementSupplier;
import org.nasdanika.common.ProgressMonitor;

import com.google.inject.Injector;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
		description = "Loads Ecore models from an Xcore URI or file",
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "xcore")
@ParentCommands(RootCommand.class)
@Description(icon = "https://docs.nasdanika.org/images/xcore-logo-g.png")
public class XcoreCommand extends CommandGroup implements EModelElementSupplier<EModelElement> {
	
	protected XcoreCommand() {
		super();
	}

	protected XcoreCommand(CapabilityLoader capabilityLoader) {
		super(capabilityLoader);
	}

	@Parameters(
		index =  "0",	
		arity = "1",
		description = {  
			"Xcore resource URI or file path",
			"resolved relative to the current directory"
		})
	private String uri;
		
	@Option(
		names = {"-f", "--file"},
		description = "URI parameter is a file path")
	private boolean isFile;
	
	@Override
	public Collection<EModelElement> getEObjects(ProgressMonitor progressMonitor) {
		URI theUri = isFile ? URI.createFileURI(new File(uri).getAbsolutePath()) : URI.createURI(uri).resolve(URI.createFileURI(new File(".").getAbsolutePath()).appendSegment(""));
		
        Injector injector = new XcoreStandaloneSetup().createInjectorAndDoEMFRegistration();
        XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
        Resource resource = resourceSet.getResource(theUri, true);
        EcoreUtil.resolveAll(resource);
        
		return resource.getContents()
				.stream()
				.filter(EModelElement.class::isInstance)
				.map(EModelElement.class::cast)
				.toList(); 
		
	}
	
}
