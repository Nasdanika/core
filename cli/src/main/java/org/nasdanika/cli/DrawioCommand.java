package org.nasdanika.cli;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.function.Function;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Document;
import org.xml.sax.SAXException;
import org.yaml.snakeyaml.Yaml;

import picocli.CommandLine;
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
	
	private List<URIHandler> uriHandlers;

	protected DrawioCommand(List<URIHandler> uriHandlers) {
		super();
		this.uriHandlers = uriHandlers;
	}

	protected DrawioCommand(CapabilityLoader capabilityLoader, List<URIHandler> uriHandlers) {
		super(capabilityLoader);
		this.uriHandlers = uriHandlers;
	}

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
			
	@Option(			
			names = {"-u", "--uri"},
			description = {	
					"URI mapping.",
					"Target URIs are resolved",
					"relative to the base URI"})
    private Map<String, String> uris = new LinkedHashMap<>();
	
	@Option(			
		names = {"-b", "--base-url"},
		description = {	
				"Base URI for reolving document and target",
				"URIs. Defaults to the current directory URI",
				"Resolved relative to the current directory URI"})
	private String baseUri;
	
	@Option(			
			names = {"-U", "--uris"},
			paramLabel = "URL of URI to URL mapping resource",
			description = {
					"URI map resource URL relative to the document file",
					"YAML, JSON, or properties",
					"Type is inferred from the content type header, if it is present, ",
					"or extension"
			})
	private List<String> uriMapSources = new ArrayList<>();
			
	protected Map<String,Object> loadJson(InputStream in) throws IOException {
		JSONObject jo = new JSONObject(new JSONTokener(in));
		return jo.toMap();
	}
	
	protected Map<Object,Object> loadYaml(InputStream in) throws IOException {
		Yaml yaml = new Yaml();
		return yaml.load(in);
	}
	
	protected Properties loadProperties(InputStream in) throws IOException {
		Properties props = new Properties();
		props.load(in);
		return props;
	}
	
	@Override
	public Document getDocument(
			Function<URI, InputStream> uriHandler, 
			Function<String, String> propertySource,
			ProgressMonitor progressMonitor) {
		
		File currentDir = new File(".");
		
		// TODO - property source from properties, YAML, JSON, context, ...
		if (isFile) {
			File documentFile = new File(document);
			try {
				return Document.load(documentFile, getUriHandler(
						uriHandler,
						URI.createFileURI(documentFile.toString()), progressMonitor), 
						getPropertySource(propertySource, progressMonitor));
			} catch (IOException | ParserConfigurationException | SAXException e) {
				throw new NasdanikaException("Error loading document from '" + documentFile.getAbsolutePath() + "': " + e, e);
			}
		} 		
		
		URI baseURI = URI.createFileURI(currentDir.getAbsolutePath()).appendSegment("");
		if (!Util.isBlank(this.baseUri)) {
			baseURI = URI.createURI(this.baseUri).resolve(baseURI);
		}
		URI documentURI = URI.createURI(document).resolve(baseURI);
		try {
			return Document.load(
					documentURI, 
					getUriHandler(uriHandler, documentURI, progressMonitor), 
					getPropertySource(propertySource, progressMonitor));
		} catch (IOException | ParserConfigurationException | SAXException e) {
			throw new NasdanikaException("Error loading document from '" + documentURI.toString() + "': " + e, e);
		}
	}

	protected Function<URI, InputStream> getUriHandler(
			Function<URI, InputStream> uriHandler,			
			URI baseURI, 
			ProgressMonitor progressMonitor) {
		Map<Object, Object> data = new LinkedHashMap<>(uris);
		for (String location: uriMapSources) {
			try {
				URI locationURI = URI.createURI(location).resolve(baseURI);
				URL url = new URL(locationURI.toString());
				URLConnection connection = url.openConnection();
				
				if (location.endsWith(".json")) {
					try (InputStream in = connection.getInputStream()) {
						data.putAll(loadJson(in));
					}
				} else if (location.endsWith(".yml") || location.endsWith(".yaml")) {
					try (InputStream in = connection.getInputStream()) {
						data.putAll(loadYaml(in));
					}			
				} else if (location.endsWith(".properties")) {
					try (InputStream in = connection.getInputStream()) {
						data.putAll(loadProperties(in));
					}			
				} else {				
					// Can't deduce content type from extension, attempting to use Content-Type header
					if (connection instanceof HttpURLConnection && ((HttpURLConnection) connection).getResponseCode() == HttpURLConnection.HTTP_OK) {
						String contentType = ((HttpURLConnection) connection).getHeaderField("Content-Type");
						if ("application/json".equals(contentType)) {
							try (InputStream in = connection.getInputStream()) {
								data.putAll(loadJson(in));
							}				 
						} else if ("application/x-yaml".equals(contentType)) {
							try (InputStream in = connection.getInputStream()) {
								data.putAll(loadYaml(in));
							}				
						} else if ("text/x-java-properties".equals(contentType)) {
							try (InputStream in = connection.getInputStream()) {
								data.putAll(loadProperties(in));
							}				
						} else {
							throw new CommandLine.ParameterException(spec.commandLine(), "Unsupported content type '" + contentType + "' for " + url); 													
						}
					} else {
						throw new CommandLine.ParameterException(spec.commandLine(), "Unknown resource type " + url); 						
					}
				}
			} catch (IOException e) {
				throw new CommandLine.ParameterException(spec.commandLine(), "Cannot load properties from " + location + ": " + e, e); 
			}		
		}

		if (data.isEmpty()) { 
			return null;
		}
		
		return uri -> createInputStream(uriHandler, uri, baseURI, data, null);		
	}
	
	protected InputStream createInputStream(
			Function<URI, InputStream> uriHandler,			
			final URI uri, 
			URI baseURI, 
			Map<Object, Object> mapping, 
			Map<?, ?> options) {		
		URI mappedUri = mapURI(uri, baseURI, mapping);
		
		if (uriHandler != null) {
			InputStream in = uriHandler.apply(mappedUri);
			if (in != null) {
				return in;
			}
		}
		
		if (uriHandlers != null) {
			for (URIHandler handler: uriHandlers) {
				if (handler.canHandle(mappedUri)) {
					try {
						return handler.createInputStream(mappedUri, options);
					} catch (IOException e) {
						throw new NasdanikaException("Error opening stream from " + uri + ": " + e, e);
					}
				}
			}
		}
		
		try {
			return new URL(uri.toString()).openStream();
		} catch (IOException e) {
			throw new NasdanikaException("Error opening stream from " + uri + ": " + e, e);
		}		
	}

	protected URI mapURI(final URI uri, URI baseURI, Map<Object, Object> mapping) {
		for (Entry<Object, Object> dataEntry: mapping.entrySet()) {				
			String keyStr = dataEntry.getKey().toString();
			URI keyURI = URI.createURI(keyStr);
			URI valueURI = URI.createURI(dataEntry.getValue().toString());
			if (valueURI.isRelative() && baseURI != null) {
				valueURI = valueURI.resolve(baseURI);
			}
			if (keyURI.equals(uri)) {
				return mapURI(valueURI, baseURI, mapping);
			}
			if (uri.toString().startsWith(keyStr)) {
				URI relative = uri.deresolve(keyURI, true, true, true);
				return mapURI(relative.resolve(valueURI), baseURI, mapping);
			}
		}
		return uri;
	}

	protected Function<String, String> getPropertySource(Function<String, String> propertySource, ProgressMonitor progressMonitor) {
		Map<Object, Object> properties = propertiesMixIn.getProperties();
		return key -> {
			if (propertySource != null) {
				String value = propertySource.apply(key);
				if (!Util.isBlank(value)) {
					return value;
				}
			}
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
