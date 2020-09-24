package org.nasdanika.exec;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;

import org.nasdanika.common.Adaptable;
import org.nasdanika.common.CommandFactory;
import org.nasdanika.common.CompoundCommandFactory;
import org.nasdanika.common.CompoundConsumerFactory;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.exec.content.Base64;
import org.nasdanika.exec.content.Form;
import org.nasdanika.exec.content.FreeMarker;
import org.nasdanika.exec.content.HttpCall;
import org.nasdanika.exec.content.Interpolator;
import org.nasdanika.exec.content.Json;
import org.nasdanika.exec.content.Mustache;
import org.nasdanika.exec.content.Replace;
import org.nasdanika.exec.content.Resource;
import org.nasdanika.exec.content.ScriptEvaluator;
import org.nasdanika.exec.content.Yaml;
import org.nasdanika.exec.content.ZipArchive;
import org.nasdanika.exec.java.Annotation;
import org.nasdanika.exec.java.CompilationUnit;
import org.nasdanika.exec.java.Constructor;
import org.nasdanika.exec.java.Field;
import org.nasdanika.exec.java.Interface;
import org.nasdanika.exec.java.Method;
import org.nasdanika.exec.java.SourceFolder;
import org.nasdanika.exec.java.TypeAdapter;
import org.nasdanika.exec.resources.Container;
import org.nasdanika.exec.resources.File;
import org.nasdanika.exec.resources.Git;
import org.nasdanika.exec.resources.ZipResourceCollection;

/**
 * This loader supports a pre-defined set of execution participants. 
 * @author Pavel
 *
 */
public class Loader extends ObjectLoader {
	
	private org.nasdanika.common.ObjectLoader chain;

	public Loader(ObjectLoader chain) {
		this.chain = chain;
	}
	
	public Loader() {	}	

	@Override
	public Object create(ObjectLoader loader, String type, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		
		try (ProgressMonitor subMonitor = progressMonitor.setWorkRemaining(10).split("Creating " + type, 1, marker)) {
			switch (type) {
			// General
			case "for-each":
				return new Iterator(loader, config, base, subMonitor, marker);
			case "configure":
				return new Configurator(loader, config, base, subMonitor, marker);
			case "map":
				return new Mapper(loader, config, base, subMonitor, marker);
			case "(map)":
				return new org.nasdanika.exec.Map(loader, config, base, subMonitor, marker).getMap();
			case "reference": // Referencing another spec to load
				return new Reference(loader, config, base, subMonitor, marker);
			case "group": // Both resource and content, is it needed - iterator and map shall do?
				throw new UnsupportedOperationException();
			case "block": 
				return new Block(loader, config, base, subMonitor, marker);
			case "info": 
				return new Progress(Status.INFO, loader, config, base, subMonitor, marker);
			case "warning": 
				return new Progress(Status.WARNING, loader, config, base, subMonitor, marker);
			case "error": 
				return new Progress(Status.ERROR, loader, config, base, subMonitor, marker);
			case "fail": 
				return new Fail(loader, config, base, subMonitor, marker);
			case "eval": 
				return new Eval(loader, config, base, subMonitor, marker);
			case "if": 
				return new If(loader, config, base, subMonitor, marker);
			case "switch": 
				return new Switch(loader, config, base, subMonitor, marker);
			
			// Resources
			case "container":
				return new Container(loader, config, base, subMonitor, marker);
			case "file":
				return new File(loader, config, base, subMonitor, marker);
			case "zip-resource-collection":
				return new ZipResourceCollection(loader, config, base, subMonitor, marker);
			case "git":
				return new Git(loader, config, base, subMonitor, marker);			
			
			// Content
			case "resource":
				return new Resource(loader, config, base, subMonitor, marker);
			case "interpolator":
				return new Interpolator(loader, config, base, subMonitor, marker);
			case "mustache":
				return new Mustache(loader, config, base, subMonitor, marker);
			case "free-marker":
				return new FreeMarker(loader, config, base, subMonitor, marker);
			case "replace":
				return new Replace(loader, config, base, subMonitor, marker);
			case "http":
				return new HttpCall(loader, config, base, subMonitor, marker);
			case "zip-archive":
				return new ZipArchive(loader, config, base, subMonitor, marker);
			case "script": // string value or bindings and source. Bindings are converted to String if streams.
				return new ScriptEvaluator(loader, config, base, subMonitor, marker);
			case "json": 
				return new Json(loader, config, base, subMonitor, marker);
			case "yaml": 
				return new Yaml(loader, config, base, subMonitor, marker);
			case "base64": 
				return new Base64(loader, config, base, subMonitor, marker);
			case "form": 
				return new Form(loader, config, base, subMonitor, marker);
				
			// Java
			case "source-folder": 
				return new SourceFolder(loader, config, base, subMonitor, marker);
			case "package": 
				return new org.nasdanika.exec.java.Package(loader, config, base, subMonitor, marker);
			case "compilation-unit": // Merger is external - passed by Codegen 
				return new CompilationUnit(loader, config, base, subMonitor, marker);
			case "annotation": 
				return new TypeAdapter(new Annotation(loader, config, base, subMonitor, marker));
			case "class": 
				return new TypeAdapter(new org.nasdanika.exec.java.Class(loader, config, base, subMonitor, marker));
			case "enum": 
				return new TypeAdapter(new org.nasdanika.exec.java.Enum(loader, config, base, subMonitor, marker));
			case "interface": 
				return new TypeAdapter(new Interface(loader, config, base, subMonitor, marker));
			case "method": 
				return new Method(loader, config, base, subMonitor, marker);
			case "constructor": 
				return new Constructor(loader, config, base, subMonitor, marker);
			case "field": 
				return new Field(loader, config, base, subMonitor, marker);
			
			default:
				if (chain == null) {
					throw new ConfigurationException("Unsupported type: " + type, marker);
				}
				
				return chain.create(loader, type, config, base, subMonitor, marker);
			}
		}
	}
	
	// --- Utility methods ---
	
	/**
	 * Wraps object into an {@link InputStream} supplier factory. Handles adapters, and collection and scalar cases.
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static SupplierFactory<InputStream> asSupplierFactory(Object obj) throws Exception {
		if (obj instanceof Collection) {
			ListCompoundSupplierFactory<InputStream> ret = new ListCompoundSupplierFactory<>("Supplier collection");
			for (Object e: (Collection<?>) obj) {
				ret.add(asSupplierFactory(e));
			}
			return ret.then(Util.JOIN_STREAMS_FACTORY);
		}
		
		if (obj instanceof SupplierFactory) {		
			return (SupplierFactory<InputStream>) obj;
		}
		
		if (obj instanceof SupplierFactory.Provider) {
			return ((SupplierFactory.Provider) obj).getFactory(InputStream.class);
		}
		
		if (obj instanceof Adaptable) {
			SupplierFactory<InputStream> adapter = ((Adaptable) obj).adaptTo(SupplierFactory.class);
			if (adapter != null) {
				return adapter;
			}
		}
		
		// Converting to string, interpolating, streaming
		SupplierFactory<String> textFactory = context -> new Supplier<String>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Scalar";
			}

			@Override
			public String execute(ProgressMonitor progressMonitor) throws Exception {
				return context.interpolateToString(String.valueOf(obj));
			}
		
		};
		
		return textFactory.then(Util.TO_STREAM);
	};
	
	/**
	 * Wraps object into an {@link BinaryEntityContainer} consumer factory. Handles adapters and collections.
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static ConsumerFactory<BinaryEntityContainer> asConsumerFactory(Object obj) throws Exception {
		return asConsumerFactory(obj, obj instanceof Marked ? ((Marked) obj).getMarker() : null);
	}	
		
	/**
	 * Wraps object into an {@link BinaryEntityContainer} consumer factory. Handles adapters and collections.
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static ConsumerFactory<BinaryEntityContainer> asConsumerFactory(Object obj, Marker marker) throws Exception {
		if (obj instanceof Collection) {
			CompoundConsumerFactory<BinaryEntityContainer> ret = new CompoundConsumerFactory<>("Consumer collection");
			int idx = 0;
			for (Object e: (Collection<?>) obj) {
				ret.add(asConsumerFactory(e, Util.getMarker((Collection<?>) obj, idx++)));
			}
			return ret;
		}
		
		if (obj instanceof ConsumerFactory) {		
			return (ConsumerFactory<BinaryEntityContainer>) obj;
		}		
		
		if (obj instanceof Adaptable) {
			ConsumerFactory<BinaryEntityContainer> adapter = ((Adaptable) obj).adaptTo(ConsumerFactory.class);
			if (adapter != null) {
				return adapter;
			}
		}
		
		throw new ConfigurationException(obj.getClass() + " cannot be wrapped/adapted to a consumer factory", marker);
	};		
		
	/**
	 * Wraps object into a {@link CommandFactory}. Handles adapters and collections.
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static CommandFactory asCommandFactory(Object obj) throws Exception {
		return asCommandFactory(obj, obj instanceof Marked ? ((Marked) obj).getMarker() : null);
	}	
	
	/**
	 * Wraps object into a {@link CommandFactory}. Handles adapters and collections.
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static CommandFactory asCommandFactory(Object obj, Marker marker) throws Exception {
		if (obj instanceof Collection) {
			CompoundCommandFactory ret = new CompoundCommandFactory("Command collection");
			int idx = 0;
			for (Object e: (Collection<?>) obj) {
				ret.add(asCommandFactory(e, Util.getMarker((Collection<?>) obj, idx++)));
			}
			return ret;
		}
		
		if (obj instanceof CommandFactory) {		
			return (CommandFactory) obj;
		}
		
		if (obj instanceof Adaptable) {
			CommandFactory adapter = ((Adaptable) obj).adaptTo(CommandFactory.class);
			if (adapter != null) {
				return adapter;
			}
		}
				
		throw new ConfigurationException(obj.getClass() + " cannot be wrapped/adapted to a command factory", marker);
	};
	
	/**
	 * Gets string configuration value.
	 * @param configMap
	 * @param key
	 * @param required
	 * @return
	 */
	public static String getString(Map<String, Object> configMap, String key, boolean required, Marker marker) {
		if (configMap.containsKey(key)) {
			Object val = configMap.get(key);
			if (val == null && !required) {
				return null;
			}
			if (val instanceof String) {
				return (String) val;
			} 
			
			throw new ConfigurationException(key + " value must be a string", Util.getMarker(configMap, key));
		}
		
		if (required) {
			throw new ConfigurationException(key + " is missing", marker);			
		}
		
		return null;
	}	

	/**
	 * Loads values from a key which can be either a string (single value) or a list of strings (multi-value)
	 * @param configMap
	 * @param key
	 * @param consumer
	 */
	public static void loadMultiString(Map<String,Object> configMap, String key, Consumer<String> consumer) {
		if (configMap.containsKey(key)) {
			Object val = configMap.get(key);
			if (val instanceof String) {
				consumer.accept((String) val);
			} else if (val instanceof Collection) {
				int idx = 0;
				for (Object ve: (Collection<?>) val) {
					if (ve instanceof String) {
						consumer.accept((String) ve);
					} else {
						throw new ConfigurationException(key + " element must be a string", Util.getMarker((Collection<?>) val, idx));							
					}
					++idx;
				}
			} else {
				throw new ConfigurationException(key + " value must be a string or list", Util.getMarker(configMap, key));
			}
		}		
	}
	
	/**
	 * A convenience method to check for presence of unsupported configuration keys
	 * @param config Configuration map.
	 * @param supportedKeys Supported keys.
	 * @param marker Map location.
	 */
	public static Map<String,Object> checkUnsupportedKeys(Map<String,Object> config, String... supportedKeys) throws ConfigurationException {
		return checkUnsupportedKeys(config, Arrays.asList(supportedKeys));
	}
	
	/**
	 * A convenience method to check for presence of unsupported configuration keys
	 * @param config Configuration map.
	 * @param supportedKeys Supported keys.
	 * @param marker Map location.
	 */
	public static Map<String,Object> checkUnsupportedKeys(Map<String,Object> config, Collection<String> supportedKeys) throws ConfigurationException {
		if (config == null) {
			return config;
		}
		Collection<String> unsupportedKeys = new ArrayList<String>(config.keySet());
		unsupportedKeys.removeAll(supportedKeys);
		if (unsupportedKeys.isEmpty()) {
			return config;
		}		
		
		if (unsupportedKeys.size() == 1) {
			String unsupportedKey = unsupportedKeys.iterator().next();
			throw new ConfigurationException("Unsupported configuration key: " + unsupportedKey, Util.getMarker(config, unsupportedKey));
		}
		
		StringBuilder keyList = new StringBuilder();
		for (String uk: unsupportedKeys) {
			if (keyList.length() > 0) {
				keyList.append(", ");
			}
			keyList.append(uk);
		}
		throw new ConfigurationException("Unsupported configuration keys: " + keyList, config instanceof Marked ? ((Marked) config).getMarker() : null);
	}
		
}
