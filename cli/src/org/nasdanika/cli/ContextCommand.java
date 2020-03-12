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

import org.json.JSONObject;
import org.json.JSONTokener;
import org.nasdanika.common.Context;
import org.yaml.snakeyaml.Yaml;

import picocli.CommandLine.Option;

/**
 * Base class for commands which build {@link Context} from command line options.
 * @author Pavel
 *
 */
public abstract class ContextCommand extends CommandBase {
	
	@Option(			
			names = {"-c", "--context-entry"},
			description = {
					"Context entries. ",
					"Shadow entries in contexts and mounts."
			})
    private Map<String, String> entries = new LinkedHashMap<>();
	

	@Option(			
			names = {"-M", "--context-mount"},
			description = {
					"MappingContext resource URL relative to the current directory. "
					+ "YAML, JSON, or properties. "
					+ "In properties dots are treated as key path separators. "
					+ "Type is inferred from the content type header, if it is present, "
					+ "or extension. Mounts shadow context entries."
			})
	private Map<String,String> mounts = new LinkedHashMap<>();
		
	@Option(			
			names = {"-C", "--context"},
			description = {
					"Context resource URL relative to the current directory. "
					 + "YAML, JSON, or properties. "
					+ "In properties dots are treated as key path separators. "
					+ "Type is inferred from the content type header, if it is present, "
					+ "or extension. Contexts are composed in the order of definition, "
					+ "later context entries shadowing the former"
			})
	private List<String> contexts = new ArrayList<>();
	
	protected Context createContext() throws IOException {
		Context ret = Context.EMPTY_CONTEXT;		
		for (Entry<String, String> mountEntry: mounts.entrySet()) {
			ret = ret.mount(load(mountEntry.getValue()), mountEntry.getKey());
		}
		for (String location: contexts) {
			ret = ret.compose(load(location));
		}
		return Context.wrap(entries::get).compose(ret);
	}
	
	/**
	 * Loads context from a location resolved relative to the current directory.
	 * @param location
	 * @return
	 * @throws IOException
	 */
	protected Context load(String location) throws IOException {
		URL url = new File(".").toURI().resolve(location).toURL();
		URLConnection connection = url.openConnection();
		
		if (location.endsWith(".json")) {
			try (InputStream in = connection.getInputStream()) {
				return loadJson(in);
			}
		}
		
		if (location.endsWith(".yml") || location.endsWith(".yaml")) {
			try (InputStream in = connection.getInputStream()) {
				return loadYaml(in);
			}			
		}
		if (location.endsWith(".properties")) {
			try (InputStream in = connection.getInputStream()) {
				return loadProperties(in);
			}			
		}
		
		// Can't deduce content type from extension, attempting to use Content-Type header
		if (connection instanceof HttpURLConnection && ((HttpURLConnection) connection).getResponseCode() == HttpURLConnection.HTTP_OK) {
			String contentType = ((HttpURLConnection) connection).getHeaderField("Content-Type");
			if ("application/json".equals(contentType)) {
				try (InputStream in = connection.getInputStream()) {
					return loadJson(in);
				}				 
			} 
			
			if ("application/x-yaml".equals(contentType)) {
				try (InputStream in = connection.getInputStream()) {
					return loadYaml(in);
				}				
			} 
			
			if ("text/x-java-properties".equals(contentType)) {
				try (InputStream in = connection.getInputStream()) {
					return loadProperties(in);
				}				
			}
		}
		
		throw new IllegalArgumentException("Unknown resource type: " + url);
	}	
	
	protected Context loadJson(InputStream in) throws IOException {
		JSONObject jo = new JSONObject(new JSONTokener(in));
		return Context.wrap(jo.toMap()::get);
	}
	
	@SuppressWarnings("unchecked")
	protected Context loadYaml(InputStream in) throws IOException {
		Yaml yaml = new Yaml();
		return Context.wrap(((Map<String,String>) yaml.load(in))::get);
	}
	
	protected Context loadProperties(InputStream in) throws IOException {
		Properties props = new Properties();
		props.load(in);
		Function<String, Object> source = props::getProperty;		 
		return Context.wrap(source.compose(key -> ((String) key).replace('.', '/')));
	}
	
}
