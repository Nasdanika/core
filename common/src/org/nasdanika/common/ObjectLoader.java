package org.nasdanika.common;

import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.MarkingYamlConstructor;
import org.yaml.snakeyaml.Yaml;

/**
 * Loads objects from configuration, e.g. YAML or JSON.
 * Objects are loaded as follows: 
 * a) If config value is a map with a single element, then it's key is an object type to be instantiated. Type is passed to create() method and is implementation-specific. E.g. a factory may use aliases/logical names for a 
 * pre-defined set of supported objects/components.  
 * b) If config value is a multi-value map, then the result would be a map with each value instantiated as explained here.
 * c) If config value is a list then the result would be a list with each element instantiated as explained here.
 * d) Otherwise the value is returned as-is.
 * 
 * 
 * {@link ExecutionParticipant}'s factory loading methods cast instantiated objects to a specific execution participant type factory and wrap values. Lists, and maps are wrapped into compound execution participants of requested type.
 *   
 * @author Pavel
 *
 */
public abstract class ObjectLoader {
	
	/**
	 * Creates an object of requested type with a given config.
	 * @param ObjectLoader for creating object parts. This factory or parent/root loader for chained loaders.
	 * @param type
	 * @param config
	 * @param base Base URL for resolving references.
	 * @param progressMonitor 
	 * @param marker Optional source marker for troubleshooting.  
	 * @return
	 * @throws Exception
	 */
	public abstract Object create(ObjectLoader factory, String type, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception;
	
	@SuppressWarnings("unchecked")
	public Object load(Object spec, URL base, ProgressMonitor progressMonitor) throws Exception {
		// map
		if (spec instanceof Map) {
			Map<String,Object> map = (Map<String,Object>) spec;
			// single
			if (map.size() == 1) {
				for (Entry<String, Object> e: map.entrySet()) {
					return create(this, e.getKey(), e.getValue(), base, progressMonitor, Util.getMarker(map, e.getKey()));
				}
			}
			// otherwise
			Map<String,Object> ret = new LinkedHashMap<>();
			for (Entry<String, Object> e: map.entrySet()) {
				ret.put(e.getKey(), load(e.getValue(), base, progressMonitor));
			}
			return ret;
		}
		
		// list
		if (spec instanceof Collection) {
			List<Object> ret = new ArrayList<>();
			for (Object e: (Collection<?>) spec) {
				ret.add(load(e, base, progressMonitor));
			}
			return ret;
		}
		
		// Scalar - return AS-IS
		return spec;
	}
	
	public Object loadYaml(String yamlString, URL base, ProgressMonitor progressMonitor) throws Exception {
		Yaml yaml = MarkingYamlConstructor.createMarkingYaml(base == null ? null : base.toString());
		return load(yaml.load(yamlString), base, progressMonitor);
	}
	
	public Object loadYaml(InputStream in, URL base, ProgressMonitor progressMonitor) throws Exception {
		Yaml yaml = MarkingYamlConstructor.createMarkingYaml(base == null ? null : base.toString());
		return load(yaml.load(in), base, progressMonitor);
	}
	
	public Object loadYaml(Reader reader, URL base, ProgressMonitor progressMonitor) throws Exception {
		Yaml yaml = MarkingYamlConstructor.createMarkingYaml(base == null ? null : base.toString());
		return load(yaml.load(reader), base, progressMonitor);
	}
	
	public Object loadYaml(URL url, ProgressMonitor progressMonitor) throws Exception {
		progressMonitor.worked(1, "Loading YAML from " + url);
		Yaml yaml = MarkingYamlConstructor.createMarkingYaml(url.toString());
		return load(yaml.load(url.openStream()), url, progressMonitor);
	}
		
	public Object loadJsonObject(String str, URL base, ProgressMonitor progressMonitor) throws Exception {
		JSONObject jsonObject = new JSONObject(new JSONTokener(str));
		return load(jsonObject.toMap(), base, progressMonitor);
	}
	
	public Object loadJsonObject(InputStream in, URL base, ProgressMonitor progressMonitor) throws Exception {
		JSONObject jsonObject = new JSONObject(new JSONTokener(in));
		return load(jsonObject.toMap(), base, progressMonitor);
	}
	
	public Object loadJsonObject(Reader reader, URL base, ProgressMonitor progressMonitor) throws Exception {
		JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
		return load(jsonObject.toMap(), base, progressMonitor);
	}
	
	public Object loadJsonObject(URL url, ProgressMonitor progressMonitor) throws Exception {
		progressMonitor.worked(1, "Loading JSON object from " + url);
		JSONObject jsonObject = new JSONObject(new JSONTokener(url.openStream()));
		return load(jsonObject.toMap(), url, progressMonitor);
	}
		
	public Object loadJsonArray(String str, URL base, ProgressMonitor progressMonitor) throws Exception {
		JSONArray jsonArray = new JSONArray(new JSONTokener(str));
		return load(jsonArray.toList(), base, progressMonitor);
	}
	
	public Object loadJsonArray(InputStream in, URL base, ProgressMonitor progressMonitor) throws Exception {
		JSONArray jsonArray = new JSONArray(new JSONTokener(in));
		return load(jsonArray.toList(), base, progressMonitor);
	}
	
	public Object loadJsonArray(Reader reader, URL base, ProgressMonitor progressMonitor) throws Exception {
		JSONArray jsonArray = new JSONArray(new JSONTokener(reader));
		return load(jsonArray.toList(), base, progressMonitor);
	}
	
	public Object loadJsonArray(URL url, ProgressMonitor progressMonitor) throws Exception {
		progressMonitor.worked(1, "Loading JSON array from " + url);
		JSONArray jsonArray = new JSONArray(new JSONTokener(url.openStream()));
		return load(jsonArray.toList(), url, progressMonitor);
	}
	
}
