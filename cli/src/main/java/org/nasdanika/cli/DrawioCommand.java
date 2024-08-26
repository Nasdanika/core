package org.nasdanika.cli;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.Context;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.Document;
import org.xml.sax.SAXException;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
		description = "Loads Drawio document from a URI or file",
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "drawio")
@ParentCommands(RootCommand.class)
public class DrawioCommand extends CommandGroup implements Document.Supplier {
	
	@Parameters(
		index =  "0",	
		arity = "1",
		description = {  
			"Document URI or file path, resolved relative",
			"to the current directory"
		})
	private String document;
		
	@Option(
		names = {"-f", "--file"},
		description = "Document parameter is a file path")
	private boolean isFile;
	
	@Mixin
	ContextMixIn contextMixIn;
	
	@Override
	public Document getDocument(ProgressMonitor progressMonitor) {		
		File currentDir = new File(".");
		
		// TODO - URI handlers from capability. E.g. classpath
		// TODO - property source from properties, YAML, JSON, context, ...
		if (isFile) {
			File documentFile = new File(document);
			try {
				return Document.load(documentFile, getUriHandler(progressMonitor), getPropertySource(progressMonitor));
			} catch (IOException | ParserConfigurationException | SAXException e) {
				throw new NasdanikaException("Error loading document from '" + documentFile.getAbsolutePath() + "': " + e, e);
			}
		} 		
		
		URI baseURI = URI.createFileURI(currentDir.getAbsolutePath()).appendSegment("");
		URI documentURI = URI.createURI(document).resolve(baseURI);
		try {
			return Document.load(documentURI, getUriHandler(progressMonitor), getPropertySource(progressMonitor));
		} catch (IOException | ParserConfigurationException | SAXException e) {
			throw new NasdanikaException("Error loading document from '" + documentURI.toString() + "': " + e, e);
		}
	}

	protected Function<URI, InputStream> getUriHandler(ProgressMonitor progressMonitor) {
		// TODO 
		return null;
	}


	protected Function<String, String> getPropertySource(ProgressMonitor progressMonitor) {
		try {
			Context context = contextMixIn.createContext(progressMonitor);
			return context::getString;
		} catch (IOException e) {
			throw new NasdanikaException(e);
		}
	}

}
