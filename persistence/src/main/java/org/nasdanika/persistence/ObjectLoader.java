package org.nasdanika.persistence;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.nasdanika.common.ExecutionParticipant;
import org.nasdanika.common.ProgressMonitor;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.MarkedYAMLException;

/**
 * Loads objects from configuration, e.g. YAML or JSON.
 * Objects are loaded as follows: 
 * 
 * * If config value is a map with a single element, then its key is an object type to be instantiated. Type is passed to create() method and is implementation-specific. E.g. a factory may use aliases/logical names for a 
 * pre-defined set of supported objects/components.  
 * * If config value is a multi-value map, then the result would be a map with each value instantiated as explained here.
 * * If config value is a list then the result would be a list with each element instantiated as explained here.
 * * Otherwise the value is returned as-is. 
 * 
 * {@link ExecutionParticipant}'s factory loading methods cast instantiated objects to a specific execution participant type factory and wrap values. Lists, and maps are wrapped into compound execution participants of requested type.
 *   
 * @author Pavel
 *
 */
public interface ObjectLoader {
	
	/**
	 * Creates an object of requested type with a given config.
	 * @param ObjectLoader for creating object parts. This factory or parent/root loader for chained loaders.
	 * @param type
	 * @param config
	 * @param base Base URL for resolving references.
	 * @param progressMonitor 
	 * @param marker Optional source marker for troubleshooting.  
	 * @return
	 */
	Object create(ObjectLoader loader, String type, Object config, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers);
	
	@SuppressWarnings("unchecked")
	default Object load(Object spec, URI base, ProgressMonitor progressMonitor) {
		// map
		if (spec instanceof Map) {
			Map<String,Object> map = (Map<String,Object>) spec;
			// single
			if (map.size() == 1) {
				for (Entry<String, Object> e: map.entrySet()) {
					return create(this, e.getKey(), e.getValue(), base, progressMonitor, Util.getMarkers(map, e.getKey()));
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
	
	default Object loadYaml(String yamlString, URI base, ProgressMonitor progressMonitor) {
		Yaml yaml = MarkingYamlConstructor.createMarkingYaml(base == null ? null : base.toString());
		return load(yaml.load(yamlString), base, progressMonitor);
	}
	
	default Object loadYaml(InputStream in, URI base, ProgressMonitor progressMonitor) throws IOException {
		try (Reader reader = new InputStreamReader(in, getCharset())) {
			return loadYaml(reader, base, progressMonitor);
		}
	}
	
	default Object loadYaml(Reader reader, URI base, ProgressMonitor progressMonitor) {
		Yaml yaml = MarkingYamlConstructor.createMarkingYaml(base == null ? null : base.toString());
		return load(yaml.load(reader), base, progressMonitor);
	}
	
	default Object loadYaml(URL url, ProgressMonitor progressMonitor) throws Exception {
		progressMonitor.worked(1, "Loading YAML from " + url);
		Yaml yaml = MarkingYamlConstructor.createMarkingYaml(url.toString());
		try (Reader reader = new InputStreamReader(url.openStream(), getCharset())) {
			return load(yaml.load(reader), URI.createURI(url.toString()), progressMonitor);
		} catch (MarkedYAMLException e) {
			throw new ConfigurationException(e.getMessage(), e, new MarkerImpl(url.toString(), e.getProblemMark()));
		}
	}
	
	default Object loadYaml(File file, ProgressMonitor progressMonitor) throws MalformedURLException, Exception {
		return loadYaml(file.toURI().toURL(), progressMonitor);
	}
		
	default Object loadJsonObject(String str, URI base, ProgressMonitor progressMonitor) {
		JSONObject jsonObject = new JSONObject(new JSONTokener(str));
		return load(jsonObject.toMap(), base, progressMonitor);
	}
	
	default Object loadJsonObject(InputStream in, URI base, ProgressMonitor progressMonitor) throws IOException {
		try (Reader reader = new InputStreamReader(in, getCharset())) {	
			return loadJsonObject(reader, base, progressMonitor);
		}
	}
	
	default Object loadJsonObject(Reader reader, URI base, ProgressMonitor progressMonitor) {
		JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
		return load(jsonObject.toMap(), base, progressMonitor);
	}
	
	default Object loadJsonObject(URL url, ProgressMonitor progressMonitor) throws IOException {
		progressMonitor.worked(1, "Loading JSON object from " + url);
		return loadJsonObject(url.openStream(), URI.createURI(url.toString()), progressMonitor);
	}
		
	default Object loadJsonArray(String str, URI base, ProgressMonitor progressMonitor) {
		JSONArray jsonArray = new JSONArray(new JSONTokener(str));
		return load(jsonArray.toList(), base, progressMonitor);
	}
	
	default Object loadJsonArray(InputStream in, URI base, ProgressMonitor progressMonitor) throws IOException {
		try (Reader reader = new InputStreamReader(in, getCharset())) {
			return loadJsonArray(reader,  base, progressMonitor);
		}
	}
	
	default Object loadJsonArray(Reader reader, URI base, ProgressMonitor progressMonitor) {
		JSONArray jsonArray = new JSONArray(new JSONTokener(reader));
		return load(jsonArray.toList(), base, progressMonitor);
	}
	
	default Object loadJsonArray(URL url, ProgressMonitor progressMonitor) throws Exception {
		progressMonitor.worked(1, "Loading JSON array from " + url);
		return loadJsonArray(url.openStream(), URI.createURI(url.toString()), progressMonitor);
	}
	
	/**
	 * Override to customize charset.
	 * @return
	 */
	default Charset getCharset() {
		return StandardCharsets.UTF_8;
	}
	
	/*
	 * TODO - loading from Excel using Apache POI - https://search.maven.org/artifact/org.apache.poi/poi/5.0.0/jar
	 * Use URL fragment to address Sheets and ranges. E.g. myworkbook.xslx#Sheet1:A1:C100
	 * If no fragment - a map of sheet names to lists of maps with first row as key names and subsequent rows as data.
	 * For streams - pass sheet and range as method parameters.
	 * Figure out how to get the type, e.g. if the type is known beforehand.
	 */
	
}
