package org.nasdanika.xsd;

import java.io.File;
import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.xsd.ecore.XSDEcoreBuilder;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.cli.CommandGroup;
import org.nasdanika.cli.ParentCommands;
import org.nasdanika.cli.RootCommand;
import org.nasdanika.common.Description;
import org.nasdanika.common.EModelElementSupplier;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
		description = "Converts XSD to Ecore",
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "xsd-to-ecore")
@ParentCommands(RootCommand.class)
@Description(icon = "https://docs.nasdanika.org/images/xsd.svg")
public class XsdToEcoreCommand extends CommandGroup implements EModelElementSupplier<EModelElement> {
	
	protected XsdToEcoreCommand() {
		super();
	}

	protected XsdToEcoreCommand(CapabilityLoader capabilityLoader) {
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
		
		XSDEcoreBuilder xsdEcoreBuilder = new XSDEcoreBuilder();
		return 	xsdEcoreBuilder.generate(theUri)
				.stream()
				.filter(EModelElement.class::isInstance)
				.map(EModelElement.class::cast)
				.toList(); 
		
	}
	
}
