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
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

import org.eclipse.emf.common.util.URI;
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
	 * Creates an object of requested type with a given config. This implementation calls create(loader, type) and then load(loader, config, target, base, resolver, markers, progressMonitor)
	 * @param loader ObjectLoader for creating object parts. This loader or parent/root loader for chained loaders.
	 * @param type Object type
	 * @param config Configuration to load the object from
	 * @param base Base URI for resolving references.
	 * @param resolver Resolver of referenced objects which might not been loaded yet. The argument is a selector, which is implementation specific - it might be an object ID, URI, or a predicatie.
	 * @param markers Source markers to trace where the object was loaded from
	 * @param progressMonitor Progress monitor
	 * @return Configured object
	 */
	default  <T> T create(
			ObjectLoader loader, 
			String type, 
			Object config, 
			URI base,
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,
			Collection<? extends Marker> markers,
			ProgressMonitor progressMonitor) {
		
		T obj = create(loader, type);
		load(loader, config, obj, base, resolver, markers, progressMonitor);		
		return obj;		
	}
	
	/**
	 * Instantiates an object based on type
	 * @param loader
	 * @param type
	 * @return
	 */
	 <T> T create(ObjectLoader loader,	String type); 	
	
	/**
	 * Loads configuration into an existing object.
	 * @param loader ObjectLoader for creating object parts. This factory or parent/root loader for chained loaders.
	 * @param config Configuration to load the object from
	 * @param target An existing object to load configuration to
	 * @param base Base URI for resolving references.
	 * @param resolver Resolver of referenced objects which might not been loaded yet. The argument is a selector, which is implementation specific - it might be an object ID, URI, or a predicatie.
	 * @param markers Source markers to trace where the object was loaded from
	 * @param progressMonitor Progress monitor
	 * @return Configured object
	 */
	void load(
			ObjectLoader loader, 
			Object config, 
			Object target,			
			URI base,
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,
			Collection<? extends Marker> markers,
			ProgressMonitor progressMonitor);
	
	
	@SuppressWarnings("unchecked")
	default  <T> T load(
			Object spec, 
			URI base,
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,
			ProgressMonitor progressMonitor) {
		// map
		if (spec instanceof Map) {
			Map<String,Object> map = (Map<String,Object>) spec;
			// single
			if (map.size() == 1) {
				for (Entry<String, Object> e: map.entrySet()) {
					return create(this, e.getKey(), e.getValue(), base, resolver, Util.getMarkers(map, e.getKey()), progressMonitor);
				}
			}
			// otherwise
			Map<String,Object> ret = new LinkedHashMap<>();
			for (Entry<String, Object> e: map.entrySet()) {
				ret.put(e.getKey(), load(e.getValue(), base, resolver, progressMonitor));
			}
			return (T) ret;
		}
		
		// list
		if (spec instanceof Collection) {
			List<Object> ret = new ArrayList<>();
			for (Object e: (Collection<?>) spec) {
				ret.add(load(e, base, resolver, progressMonitor));
			}
			return (T) ret;
		}
		
		// Scalar - return AS-IS
		return (T) spec;
	}
	
	default <T> T loadYaml(
			String yamlString, 
			URI base,
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,			
			ProgressMonitor progressMonitor) {
		Yaml yaml = MarkingYamlConstructor.createMarkingYaml(base == null ? null : base.toString());
		return load(yaml.load(yamlString), base, resolver, progressMonitor);
	}
	
	default void loadYaml(
			String yamlString,
			Object target,
			URI base,
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,
			Collection<? extends Marker> markers,
			ProgressMonitor progressMonitor) {
		Yaml yaml = MarkingYamlConstructor.createMarkingYaml(base == null ? null : base.toString());
		load(
				this,
				yaml.load(yamlString), 
				target, 
				base, 
				resolver, 
				markers, 
				progressMonitor);
	}
	
	default Object loadYaml(
			InputStream in, 
			URI base, 
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,
			ProgressMonitor progressMonitor) throws IOException {
		try (Reader reader = new InputStreamReader(in, getCharset())) {
			return loadYaml(reader, base, resolver, progressMonitor);
		}
	}
	
	default void loadYaml(
			InputStream in,
			Object target,
			URI base, 
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,
			Collection<? extends Marker> markers,			
			ProgressMonitor progressMonitor) throws IOException {
		try (Reader reader = new InputStreamReader(in, getCharset())) {
			loadYaml(reader, target, base, resolver, markers, progressMonitor);
		}
	}
	
	default Object loadYaml(
			Reader reader, 
			URI base, 
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,
			ProgressMonitor progressMonitor) {
		Yaml yaml = MarkingYamlConstructor.createMarkingYaml(base == null ? null : base.toString());
		return load(yaml.load(reader), base, resolver, progressMonitor);
	}
	
	default void loadYaml(
			Reader reader,
			Object target,
			URI base, 
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,
			Collection<? extends Marker> markers,			
			ProgressMonitor progressMonitor) {
		Yaml yaml = MarkingYamlConstructor.createMarkingYaml(base == null ? null : base.toString());
		load(
				this,
				yaml.load(reader), 
				target,
				base, 
				resolver,
				markers,
				progressMonitor);
	}
	
	default Object loadYaml(
			URL url,
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,			
			ProgressMonitor progressMonitor) throws Exception {
		progressMonitor.worked(1, "Loading YAML from " + url);
		Yaml yaml = MarkingYamlConstructor.createMarkingYaml(url.toString());
		try (Reader reader = new InputStreamReader(url.openStream(), getCharset())) {
			return load(yaml.load(reader), URI.createURI(url.toString()), resolver, progressMonitor);
		} catch (MarkedYAMLException e) {
			throw new ConfigurationException(e.getMessage(), e, new MarkerImpl(url.toString(), e.getProblemMark()));
		}
	}
	
	default void loadYaml(
			URL url,
			Object target,
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,			
			ProgressMonitor progressMonitor) throws Exception {
		progressMonitor.worked(1, "Loading YAML from " + url);
		Yaml yaml = MarkingYamlConstructor.createMarkingYaml(url.toString());
		try (Reader reader = new InputStreamReader(url.openStream(), getCharset())) {
			load(
					this,
					yaml.load(reader), 
					target,
					URI.createURI(url.toString()), 
					resolver,
					Collections.<Marker>singleton(new MarkerImpl(url.toString(), (String) null)),
					progressMonitor);
		} catch (MarkedYAMLException e) {
			throw new ConfigurationException(e.getMessage(), e, new MarkerImpl(url.toString(), e.getProblemMark()));
		}
	}
	
	default Object loadYaml(
			File file, 
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,
			ProgressMonitor progressMonitor) throws MalformedURLException, Exception {
		return loadYaml(file.toURI().toURL(), resolver, progressMonitor);
	}
	
	default void loadYaml(
			File file,
			Object target,
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,
			ProgressMonitor progressMonitor) throws MalformedURLException, Exception {
		loadYaml(file.toURI().toURL(), target, resolver, progressMonitor);
	}
		
	default Object loadJsonObject(
			String str, 
			URI base, 
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,
			ProgressMonitor progressMonitor) {
		JSONObject jsonObject = new JSONObject(new JSONTokener(str));
		return load(jsonObject.toMap(), base, resolver, progressMonitor);
	}
	
	default void loadJsonObject(
			String str,
			Object target,
			URI base, 
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,
			Collection<? extends Marker> markers,			
			ProgressMonitor progressMonitor) {
		JSONObject jsonObject = new JSONObject(new JSONTokener(str));
		load(
				this,
				jsonObject.toMap(), 
				target, 
				base, 
				resolver,
				markers,
				progressMonitor);
	}
	
	default Object loadJsonObject(
			InputStream in, 
			URI base, 
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,
			ProgressMonitor progressMonitor) throws IOException {
		try (Reader reader = new InputStreamReader(in, getCharset())) {	
			return loadJsonObject(reader, base, resolver, progressMonitor);
		}
	}
	
	default void loadJsonObject(
			InputStream in,
			Object target,
			URI base, 
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,
			Collection<? extends Marker> markers,			
			ProgressMonitor progressMonitor) throws IOException {
		try (Reader reader = new InputStreamReader(in, getCharset())) {	
			loadJsonObject(reader, target, base, resolver, markers, progressMonitor);
		}
	}
	
	default Object loadJsonObject(
			Reader reader, 
			URI base, 
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,
			ProgressMonitor progressMonitor) {
		JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
		return load(jsonObject.toMap(), base, resolver, progressMonitor);
	}
	
	default void loadJsonObject(
			Reader reader,
			Object target, 
			URI base, 
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,
			Collection<? extends Marker> markers,			
			ProgressMonitor progressMonitor) {
		JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
		load(
				this,
				jsonObject.toMap(), 
				target,
				base, 
				resolver,
				markers,
				progressMonitor);
	}
	
	default Object loadJsonObject(
			URL url, 
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,
			ProgressMonitor progressMonitor) throws IOException {
		progressMonitor.worked(1, "Loading JSON object from " + url);
		return loadJsonObject(url.openStream(), URI.createURI(url.toString()), resolver, progressMonitor);
	}
	
	default void loadJsonObject(
			URL url,
			Object target,
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,
			ProgressMonitor progressMonitor) throws IOException {
		progressMonitor.worked(1, "Loading JSON object from " + url);
		loadJsonObject(
				url.openStream(), 
				target,
				URI.createURI(url.toString()), 
				resolver,
				Collections.<Marker>singleton(new MarkerImpl(url.toString(), (String) null)),				
				progressMonitor);
	}
		
	default Object loadJsonArray(
			String str, 
			URI base, 
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,
			ProgressMonitor progressMonitor) {
		JSONArray jsonArray = new JSONArray(new JSONTokener(str));
		return load(jsonArray.toList(), base, resolver, progressMonitor);
	}
	
	default Object loadJsonArray(
			InputStream in, 
			URI base,
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,			
			ProgressMonitor progressMonitor) throws IOException {
		try (Reader reader = new InputStreamReader(in, getCharset())) {
			return loadJsonArray(reader,  base, resolver, progressMonitor);
		}
	}
	
	default Object loadJsonArray(
			Reader reader, 
			URI base, 
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,
			ProgressMonitor progressMonitor) {
		JSONArray jsonArray = new JSONArray(new JSONTokener(reader));
		return load(jsonArray.toList(), base, resolver, progressMonitor);
	}
	
	default Object loadJsonArray(
			URL url, 
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,
			ProgressMonitor progressMonitor) throws IOException {
		progressMonitor.worked(1, "Loading JSON array from " + url);
		return loadJsonArray(url.openStream(), URI.createURI(url.toString()), resolver, progressMonitor);
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
	 * Figure out how to get the type, e.g. if the type is known beforehand - type provider function taking a tuple. 
	 * Maybe too complex to be handled at this level. 
	 */
	
}
