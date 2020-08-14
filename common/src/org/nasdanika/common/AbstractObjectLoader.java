package org.nasdanika.common;

import java.io.InputStream;
import java.io.Reader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.yaml.snakeyaml.Yaml;

/**
 * Loads objects from configuration, e.g. YAML or JSON.
 * Objects are loaded as follows: 
 * a) If config value is a map with a single element, then it's key is an object type to be instantiated. Type is passed to create() method and is implementation-specific. E.g. a subclass may use aliases/logical names for a 
 * pre-defined set of supported objects/components.  
 * The class must have a two argument constructor taking {@link ObjectLoader} and {@link Object}.
 * This loader is passed to the constructor as its first argument, and the map value is passed to the second argument. Use, say, an empty list or map for the second argument if not configuration is required. 
 * The value passed to the constructor is not processed by the loader - the loaded object may delegate to the loader for loading it constituents.
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
public abstract class AbstractObjectLoader {
	
	/**
	 * Creates an object of requested type with a given config.
	 * @param type
	 * @param config
	 * @return
	 * @throws Exception
	 */
	protected abstract Object create(String type, Object config) throws Exception;
	
	public Object load(Object spec, ProgressMonitor progressMonitor) {
		if (spec == null) {
			return null;
		}
		
		// map
		// single
		// otherwise
		
		// list
		
		// object - else
		
		throw new UnsupportedOperationException();
	}
	
	public Object loadYaml(String yamlString, ProgressMonitor progressMonitor) {
		Yaml yaml = new Yaml();
		return load(yaml.load(yamlString), progressMonitor);
	}
	
	public Object loadYaml(InputStream in, ProgressMonitor progressMonitor) {
		Yaml yaml = new Yaml();
		return load(yaml.load(in), progressMonitor);
	}
	
	public Object loadYaml(Reader reader, ProgressMonitor progressMonitor) {
		Yaml yaml = new Yaml();
		return load(yaml.load(reader), progressMonitor);
	}
		
	public Object loadJsonObject(String str, ProgressMonitor progressMonitor) {
		JSONObject jsonObject = new JSONObject(new JSONTokener(str));
		return load(jsonObject.toMap(), progressMonitor);
	}
	
	public Object loadJsonObject(InputStream in, ProgressMonitor progressMonitor) {
		JSONObject jsonObject = new JSONObject(new JSONTokener(in));
		return load(jsonObject.toMap(), progressMonitor);
	}
	
	public Object loadJsonObject(Reader reader, ProgressMonitor progressMonitor) {
		JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
		return load(jsonObject.toMap(), progressMonitor);
	}
		
	public Object loadJsonArray(String str, ProgressMonitor progressMonitor) {
		JSONArray jsonArray = new JSONArray(new JSONTokener(str));
		return load(jsonArray.toList(), progressMonitor);
	}
	
	public Object loadJsonArray(InputStream in, ProgressMonitor progressMonitor) {
		JSONArray jsonArray = new JSONArray(new JSONTokener(in));
		return load(jsonArray.toList(), progressMonitor);
	}
	
	public Object loadJsonArray(Reader reader, ProgressMonitor progressMonitor) {
		JSONArray jsonArray = new JSONArray(new JSONTokener(reader));
		return load(jsonArray.toList(), progressMonitor);
	}
	
	// --- Supplier factory ---
	
	public SupplierFactory<?> loadSupplierFactory(Object spec, ProgressMonitor progressMonitor) {
		throw new UnsupportedOperationException();
	}
	
	public SupplierFactory<?> loadYamlSupplierFactory(String yamlString, ProgressMonitor progressMonitor) {
		Yaml yaml = new Yaml();
		return loadSupplierFactory(yaml.load(yamlString), progressMonitor);
	}
	
	public SupplierFactory<?> loadYamlSupplierFactory(InputStream in, ProgressMonitor progressMonitor) {
		Yaml yaml = new Yaml();
		return loadSupplierFactory(yaml.load(in), progressMonitor);
	}
	
	public SupplierFactory<?> loadYamlSupplierFactory(Reader reader, ProgressMonitor progressMonitor) {
		Yaml yaml = new Yaml();
		return loadSupplierFactory(yaml.load(reader), progressMonitor);
	}
		
	public SupplierFactory<?> loadJsonObjectSupplierFactory(String str, ProgressMonitor progressMonitor) {
		JSONObject jsonObject = new JSONObject(new JSONTokener(str));
		return loadSupplierFactory(jsonObject.toMap(), progressMonitor);
	}
	
	public SupplierFactory<?> loadJsonObjectSupplierFactory(InputStream in, ProgressMonitor progressMonitor) {
		JSONObject jsonObject = new JSONObject(new JSONTokener(in));
		return loadSupplierFactory(jsonObject.toMap(), progressMonitor);
	}
	
	public SupplierFactory<?> loadJsonObjectSupplierFactory(Reader reader, ProgressMonitor progressMonitor) {
		JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
		return loadSupplierFactory(jsonObject.toMap(), progressMonitor);
	}
		
	public SupplierFactory<?> loadJsonArraySupplierFactory(String str, ProgressMonitor progressMonitor) {
		JSONArray jsonArray = new JSONArray(new JSONTokener(str));
		return loadSupplierFactory(jsonArray.toList(), progressMonitor);
	}
	
	public SupplierFactory<?> loadJsonArraySupplierFactory(InputStream in, ProgressMonitor progressMonitor) {
		JSONArray jsonArray = new JSONArray(new JSONTokener(in));
		return loadSupplierFactory(jsonArray.toList(), progressMonitor);
	}
	
	public SupplierFactory<?> loadJsonArraySupplierFactory(Reader reader, ProgressMonitor progressMonitor) {
		JSONArray jsonArray = new JSONArray(new JSONTokener(reader));
		return loadSupplierFactory(jsonArray.toList(), progressMonitor);
	}

	// --- Function factory ---
	
	public FunctionFactory<?,?> loadFunctionFactory(Object spec, ProgressMonitor progressMonitor) {
		throw new UnsupportedOperationException();
	}
	
	public FunctionFactory<?,?> loadYamlFunctionFactory(String yamlString, ProgressMonitor progressMonitor) {
		Yaml yaml = new Yaml();
		return loadFunctionFactory(yaml.load(yamlString), progressMonitor);
	}
	
	public FunctionFactory<?,?> loadYamlFunctionFactory(InputStream in, ProgressMonitor progressMonitor) {
		Yaml yaml = new Yaml();
		return loadFunctionFactory(yaml.load(in), progressMonitor);
	}
	
	public FunctionFactory<?,?> loadYamlFunctionFactory(Reader reader, ProgressMonitor progressMonitor) {
		Yaml yaml = new Yaml();
		return loadFunctionFactory(yaml.load(reader), progressMonitor);
	}
		
	public FunctionFactory<?,?> loadJsonObjectFunctionFactory(String str, ProgressMonitor progressMonitor) {
		JSONObject jsonObject = new JSONObject(new JSONTokener(str));
		return loadFunctionFactory(jsonObject.toMap(), progressMonitor);
	}
	
	public FunctionFactory<?,?> loadJsonObjectFunctionFactory(InputStream in, ProgressMonitor progressMonitor) {
		JSONObject jsonObject = new JSONObject(new JSONTokener(in));
		return loadFunctionFactory(jsonObject.toMap(), progressMonitor);
	}
	
	public FunctionFactory<?,?> loadJsonObjectFunctionFactory(Reader reader, ProgressMonitor progressMonitor) {
		JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
		return loadFunctionFactory(jsonObject.toMap(), progressMonitor);
	}
		
	public FunctionFactory<?,?> loadJsonArrayFunctionFactory(String str, ProgressMonitor progressMonitor) {
		JSONArray jsonArray = new JSONArray(new JSONTokener(str));
		return loadFunctionFactory(jsonArray.toList(), progressMonitor);
	}
	
	public FunctionFactory<?,?> loadJsonArrayFunctionFactory(InputStream in, ProgressMonitor progressMonitor) {
		JSONArray jsonArray = new JSONArray(new JSONTokener(in));
		return loadFunctionFactory(jsonArray.toList(), progressMonitor);
	}
	
	public FunctionFactory<?,?> loadJsonArrayFunctionFactory(Reader reader, ProgressMonitor progressMonitor) {
		JSONArray jsonArray = new JSONArray(new JSONTokener(reader));
		return loadFunctionFactory(jsonArray.toList(), progressMonitor);
	}
	
	// --- Consumer factory ---
	
	public ConsumerFactory<?> loadConsumerFactory(Object spec, ProgressMonitor progressMonitor) {
		throw new UnsupportedOperationException();
	}
	
	public ConsumerFactory<?> loadYamlConsumerFactory(String yamlString, ProgressMonitor progressMonitor) {
		Yaml yaml = new Yaml();
		return loadConsumerFactory(yaml.load(yamlString), progressMonitor);
	}
	
	public ConsumerFactory<?> loadYamlConsumerFactory(InputStream in, ProgressMonitor progressMonitor) {
		Yaml yaml = new Yaml();
		return loadConsumerFactory(yaml.load(in), progressMonitor);
	}
	
	public ConsumerFactory<?> loadYamlConsumerFactory(Reader reader, ProgressMonitor progressMonitor) {
		Yaml yaml = new Yaml();
		return loadConsumerFactory(yaml.load(reader), progressMonitor);
	}
		
	public ConsumerFactory<?> loadJsonObjectConsumerFactory(String str, ProgressMonitor progressMonitor) {
		JSONObject jsonObject = new JSONObject(new JSONTokener(str));
		return loadConsumerFactory(jsonObject.toMap(), progressMonitor);
	}
	
	public ConsumerFactory<?> loadJsonObjectConsumerFactory(InputStream in, ProgressMonitor progressMonitor) {
		JSONObject jsonObject = new JSONObject(new JSONTokener(in));
		return loadConsumerFactory(jsonObject.toMap(), progressMonitor);
	}
	
	public ConsumerFactory<?> loadJsonObjectConsumerFactory(Reader reader, ProgressMonitor progressMonitor) {
		JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
		return loadConsumerFactory(jsonObject.toMap(), progressMonitor);
	}
		
	public ConsumerFactory<?> loadJsonArrayConsumerFactory(String str, ProgressMonitor progressMonitor) {
		JSONArray jsonArray = new JSONArray(new JSONTokener(str));
		return loadConsumerFactory(jsonArray.toList(), progressMonitor);
	}
	
	public ConsumerFactory<?> loadJsonArrayConsumerFactory(InputStream in, ProgressMonitor progressMonitor) {
		JSONArray jsonArray = new JSONArray(new JSONTokener(in));
		return loadConsumerFactory(jsonArray.toList(), progressMonitor);
	}
	
	public ConsumerFactory<?> loadJsonArrayConsumerFactory(Reader reader, ProgressMonitor progressMonitor) {
		JSONArray jsonArray = new JSONArray(new JSONTokener(reader));
		return loadConsumerFactory(jsonArray.toList(), progressMonitor);
	}
	
	// --- Command factory --- 
	
	public CommandFactory loadCommandFactory(Object spec, ProgressMonitor progressMonitor) {
		throw new UnsupportedOperationException();
	}
	
	public CommandFactory loadYamlCommandFactory(String yamlString, ProgressMonitor progressMonitor) {
		Yaml yaml = new Yaml();
		return loadCommandFactory(yaml.load(yamlString), progressMonitor);
	}
	
	public CommandFactory loadYamlCommandFactory(InputStream in, ProgressMonitor progressMonitor) {
		Yaml yaml = new Yaml();
		return loadCommandFactory(yaml.load(in), progressMonitor);
	}
	
	public CommandFactory loadYamlCommandFactory(Reader reader, ProgressMonitor progressMonitor) {
		Yaml yaml = new Yaml();
		return loadCommandFactory(yaml.load(reader), progressMonitor);
	}
		
	public CommandFactory loadJsonObjectCommandFactory(String str, ProgressMonitor progressMonitor) {
		JSONObject jsonObject = new JSONObject(new JSONTokener(str));
		return loadCommandFactory(jsonObject.toMap(), progressMonitor);
	}
	
	public CommandFactory loadJsonObjectCommandFactory(InputStream in, ProgressMonitor progressMonitor) {
		JSONObject jsonObject = new JSONObject(new JSONTokener(in));
		return loadCommandFactory(jsonObject.toMap(), progressMonitor);
	}
	
	public CommandFactory loadJsonObjectCommandFactory(Reader reader, ProgressMonitor progressMonitor) {
		JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
		return loadCommandFactory(jsonObject.toMap(), progressMonitor);
	}
		
	public CommandFactory loadJsonArrayCommandFactory(String str, ProgressMonitor progressMonitor) {
		JSONArray jsonArray = new JSONArray(new JSONTokener(str));
		return loadCommandFactory(jsonArray.toList(), progressMonitor);
	}
	
	public CommandFactory loadJsonArrayCommandFactory(InputStream in, ProgressMonitor progressMonitor) {
		JSONArray jsonArray = new JSONArray(new JSONTokener(in));
		return loadCommandFactory(jsonArray.toList(), progressMonitor);
	}
	
	public CommandFactory loadJsonArrayCommandFactory(Reader reader, ProgressMonitor progressMonitor) {
		JSONArray jsonArray = new JSONArray(new JSONTokener(reader));
		return loadCommandFactory(jsonArray.toList(), progressMonitor);
	}
	
}
