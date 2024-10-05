package org.nasdanika.cli;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.function.Function;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.yaml.snakeyaml.Yaml;

import picocli.CommandLine;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Spec;

/**
 * Mix-in for commands which need properties
 * @author Pavel
 *
 */
public class PropertiesMixIn {
			
	@Option(			
			names = {"-p", "--property"},
			description = {	"Properties" })
    private Map<String, String> properties = new LinkedHashMap<>();
	
	@Option(			
			names = {"-P", "--properties"},
			paramLabel = "URL",
			description = {
					"Properties resource URL relative to the current directory. "
					 + "YAML, JSON, or properties. "
					+ "Type is inferred from the content type header, if it is present, "
					+ "or extension. Properties are loaded in the order of definition, "
					+ "later properties replacing the former"
			})
	private List<String> propertySources = new ArrayList<>();
	
	@Spec(Spec.Target.MIXEE)
	CommandSpec spec;
	
	public Map<Object, Object> getProperties() {
		Map<Object, Object> result = new LinkedHashMap<>(properties);
		File currentDir = new File(".");
		for (String location: propertySources) {
			try {
				URL url = currentDir.toURI().resolve(location).toURL();
				URLConnection connection = url.openConnection();
				
				if (location.endsWith(".json")) {
					try (InputStream in = connection.getInputStream()) {
						result.putAll(loadJson(in));
					}
				} else if (location.endsWith(".yml") || location.endsWith(".yaml")) {
					try (InputStream in = connection.getInputStream()) {
						result.putAll(loadYaml(in));
					}			
				} else if (location.endsWith(".properties")) {
					try (InputStream in = connection.getInputStream()) {
						result.putAll(loadProperties(in));
					}			
				} else {				
					// Can't deduce content type from extension, attempting to use Content-Type header
					if (connection instanceof HttpURLConnection && ((HttpURLConnection) connection).getResponseCode() == HttpURLConnection.HTTP_OK) {
						String contentType = ((HttpURLConnection) connection).getHeaderField("Content-Type");
						if ("application/json".equals(contentType)) {
							try (InputStream in = connection.getInputStream()) {
								result.putAll(loadJson(in));
							}				 
						} else if ("application/x-yaml".equals(contentType)) {
							try (InputStream in = connection.getInputStream()) {
								result.putAll(loadYaml(in));
							}				
						} else if ("text/x-java-properties".equals(contentType)) {
							try (InputStream in = connection.getInputStream()) {
								result.putAll(loadProperties(in));
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
		
		return result;
	}	
	
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
	
	/**
	 * Maps properties keys and values. {@link Map} and {@link Iterable} values are processed recursively
	 * @param <K>
	 * @param keyMapper
	 * @param valueMapper
	 * @return
	 */
	public <K> Map<K, Object> getProperties(Function<Object,K> keyMapper, Function<Object, Object> valueMapper) {
		return map(getProperties(), keyMapper, valueMapper);
	}
	
	@SuppressWarnings("unchecked")
	protected <K> Map<K,Object> map(Map<Object,Object> input, Function<Object,K> keyMapper, Function<Object, Object> valueMapper) {
		if (input == null) {
			return null;
		}
		Map<K,Object> ret = new LinkedHashMap<>();
		for (Entry<Object, Object> ie: input.entrySet()) {
			Object v = ie.getValue();
			Object u;
			if (v instanceof Iterable) {
				u = map((Iterable<Object>) v, keyMapper, valueMapper);
			} else if (v instanceof Map) {
				u = map((Map<Object,Object>) v, keyMapper, valueMapper);
			} else {
				u = valueMapper.apply(v);
			}
			ret.put(keyMapper.apply(ie.getKey()) ,u);
		};
		return ret;
	}
	
	/**
	 * Creates a copy of this map using {@link ArrayList} with {@link String} values interpolated and
	 * {@link Map} and {@link Collection} values passed through interpolate methods with respective parameter types.
	 * @param input
	 * @return A deep copy of the collection with string values interpolated.
	 */
	@SuppressWarnings("unchecked")
	protected <K> List<Object> map(Iterable<?> input, Function<Object,K> keyMapper, Function<Object, Object> valueMapper) {
		if (input == null) {
			return null;
		}
		List<Object> ret = new ArrayList<>();
		input.forEach(v -> {
			Object u;
			if (v instanceof Iterable) {
				u = map((Iterable<Object>) v, keyMapper, valueMapper);
			} else if (v instanceof Map) {
				u = map((Map<Object,Object>) v, keyMapper, valueMapper);
			} else {
				u = valueMapper.apply(v);
			}
			ret.add(u);
		});
		return ret;		
	}		
	
}
