package org.nasdanika.plantuml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.cli.CommandGroup;
import org.nasdanika.cli.ParentCommands;
import org.nasdanika.cli.RootCommand;
import org.nasdanika.common.Description;

import net.sourceforge.plantuml.BlockUml;
import net.sourceforge.plantuml.SourceStringReader;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
		description = "Loads PlantUML blocks from URI or file",
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "plantuml")
@ParentCommands(RootCommand.class)
@Description(icon = "https://plantuml.com/favicon.ico")
public class PlantUmlCommand extends CommandGroup implements BlockUmlSupplier {
	
	private ResourceSet resourceSet;

	public PlantUmlCommand(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	@Parameters(
		index =  "0",	
		arity = "1",
		description = {  
			"PlantUML resource URI or file path",
			"resolved relative to the current directory"
		})
	private String uri;
		
	@Option(
		names = {"-f", "--file"},
		description = "URI parameter is a file path")
	private boolean isFile;

	@Override
	public List<BlockUml> get() {
		URI theUri = isFile ? URI.createFileURI(new File(uri).getAbsolutePath()) : URI.createURI(uri).resolve(URI.createFileURI(new File(".").getAbsolutePath()).appendSegment(""));
		try (InputStream in = resourceSet.getURIConverter().createInputStream(theUri)) {
			String source = new String(in.readAllBytes());
			SourceStringReader  sourceStringReader = new SourceStringReader(source);
			return sourceStringReader.getBlocks();
		} catch (IOException e) {
			throw new CommandLine.ExecutionException(spec.commandLine(), "Error loading PlantUML blocks from " + theUri, e);
		}
	}
	
}
