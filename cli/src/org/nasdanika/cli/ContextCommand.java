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
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
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
			paramLabel = "URL",
			description = {
					"Context resource URL relative to the current directory. "
					 + "YAML, JSON, or properties. "
					+ "In properties dots are treated as key path separators. "
					+ "Type is inferred from the content type header, if it is present, "
					+ "or extension. Contexts are composed in the order of definition, "
					+ "later context entries shadowing the former"
			})
	private List<String> contexts = new ArrayList<>();
		
	@Option(			
			names = {"-B", "--context-builders"},
			paramLabel = "URL",
			description = {
					"Context builders configuration resource URL relative to the current directory.  "
					+ "See online documentation at https://www.nasdanika.org/builds/develop/doc/reference/cli/context-builders.html for details."
			})
	private List<String> contextBuilders = new ArrayList<>();
		
	protected Context createContext(ProgressMonitor progressMonitor) throws Exception {
		Context ret = Context.EMPTY_CONTEXT;		
		for (Entry<String, String> mountEntry: mounts.entrySet()) {
			ret = ret.mount(load(mountEntry.getValue()), mountEntry.getKey());
		}
		for (String location: contexts) {
			ret = ret.compose(load(location));
		}
		
		ret = Context.wrap(entries::get).compose(ret);
		for (String cb: contextBuilders) {
			URL url = new File(".").toURI().resolve(cb).toURL();
			URLConnection connection = url.openConnection();
			try (InputStream in = connection.getInputStream()) {
				Yaml yaml = new Yaml();
				@SuppressWarnings("unchecked")
				Map<String, Object> config = (Map<String,Object>) yaml.load(in);
				ret = buildContext(config, ret, progressMonitor);
			}			
		}
		return ret;
	}
		
	/**
	 * Builds context. Processes mounts first and then passes the result to the context builder if id is present.
	 * @param config
	 * @param ret
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Context buildContext(Map<String, Object> config, Context context, ProgressMonitor progressMonitor) throws Exception {
		Map<String, Map<String, Object>> cbMounts = (Map<String, Map<String, Object>>) config.get("mounts");
		double size = (config.get("id") == null ? 0 : 1) + (cbMounts == null ? 0 : cbMounts.size());
		progressMonitor.setWorkRemaining(size);
		if (cbMounts != null) {			
			for (Entry<String, Map<String, Object>> me: cbMounts.entrySet()) {
				context = context.mount(buildContext(me.getValue(), context, progressMonitor.split("Building context for mount "+me.getKey(), 1)), me.getKey());
			}
		}
		String id = (String) config.get("id");
		if (id != null) {
			ContextBuilder contextBuilder = createContextBuilder(id);
			context = contextBuilder.build(config.get("config"), context, progressMonitor.split("Building context for id: " +id, 1));
		}
		
		return context;
	}
	
	/**
	 * Collects subcommands from extensions 
	 * @param command
	 * @param id
	 */
	@SuppressWarnings("unchecked")
	private ContextBuilder createContextBuilder(String id) throws Exception {
		for (IConfigurationElement ce: Platform.getExtensionRegistry().getConfigurationElementsFor(Application.CLI_EXTENSION_POINT_ID)) {
			String ceId = ce.getAttribute("id");
			if ("context-builder".equals(ce.getName())
					&& !Util.isBlank(ceId)
					&& Application.equal(id, ce.getContributor().getName()+"/"+ceId)) {
				ContextBuilder contextBuilder = (ContextBuilder) ce.createExecutableExtension("class");
				for (IConfigurationElement parameter: ce.getChildren("parameter")) {
					((BiConsumer<String, String>) contextBuilder).accept(parameter.getAttribute("name"), parameter.getAttribute("value"));
				}
				return contextBuilder;
			}
		}
		throw new IllegalArgumentException("Context builder not found: " + id);
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
		return Context.wrap(((Map<String,Object>) yaml.load(in))::get);
	}
	
	protected Context loadProperties(InputStream in) throws IOException {
		Properties props = new Properties();
		props.load(in);
		Function<String, Object> source = props::getProperty;		 
		return Context.wrap(source.compose(key -> ((String) key).replace('.', '/')));
	}
	
}
