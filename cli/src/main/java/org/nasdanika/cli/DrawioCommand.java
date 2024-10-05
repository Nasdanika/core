package org.nasdanika.cli;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.function.Function;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.DefaultConverter;
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
@Description(icon = "data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAzMiAzMiI+PHJlY3QgeT0iMiIgeD0iMiIgd2lkdGg9IjI4IiByeD0iMS4xMiIgaGVpZ2h0PSIyOCIgZmlsbD0iI2YwODcwNSIvPjxwYXRoIGZpbGwtcnVsZT0iZXZlbm9kZCIgZmlsbD0iI2RmNmMwYyIgZD0ibTE2Ljg2MSA5LjE2OCAzLjAyLTMuMTg3IDEwLjExOSAxMC4xMTN2MTIuNzg2YTEuMTE5IDEuMTE5IDAgMCAxIC0xLjEyIDEuMTJoLTE3LjU2NGwtNS4zODUtNS40MDd6Ii8+PHBhdGggZmlsbD0iI2ZmZiIgZD0ibTI1LjI0IDE3Ljk2aC0zLjI5NGwtMy4wNzEtNS4zMmguMmExLjExOSAxLjExOSAwIDAgMCAxLjEyLTEuMTJ2LTQuNzZhMS4xMTkgMS4xMTkgMCAwIDAgLTEuMTItMS4xMmgtNi4xNTVhMS4xMTkgMS4xMTkgMCAwIDAgLTEuMTIgMS4xMnY0Ljc2YTEuMTE5IDEuMTE5IDAgMCAwIDEuMTIgMS4xMmguMjA1bC0zLjA3MSA1LjMyaC0zLjI5NGExLjExOSAxLjExOSAwIDAgMCAtMS4xMiAxLjEydjQuNzZhMS4xMTkgMS4xMTkgMCAwIDAgMS4xMiAxLjEyaDYuMTZhMS4xMTkgMS4xMTkgMCAwIDAgMS4xMi0xLjEydi00Ljc2YTEuMTE5IDEuMTE5IDAgMCAwIC0xLjEyLTEuMTJoLS45MjdsMy4wNzItNS4zMmgxLjg3bDMuMDcxIDUuMzJoLS45MjZhMS4xMTkgMS4xMTkgMCAwIDAgLTEuMTIgMS4xMnY0Ljc2YTEuMTE5IDEuMTE5IDAgMCAwIDEuMTIgMS4xMmg2LjE2YTEuMTE5IDEuMTE5IDAgMCAwIDEuMTItMS4xMnYtNC43NmExLjExOSAxLjExOSAwIDAgMCAtMS4xMi0xLjEyeiIvPjwvc3ZnPg==")
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
	PropertiesMixIn propertiesMixIn;
	
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
		Map<Object, Object> properties = propertiesMixIn.getProperties();
		return key -> {
			if (properties.containsKey(key)) {
				Object value = properties.get(key);
				if (value instanceof String) {
					return (String) value;
				}
				return DefaultConverter.INSTANCE.convert(value, String.class);
			}
			return null;
		};
	}

}
