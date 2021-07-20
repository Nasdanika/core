package org.nasdanika.exec;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

/**
 * Evaluates script. 
 * @author Pavel
 *
 */
public class Eval implements SupplierFactory.Provider, Marked {
	
	private static final String SCRIPT_KEY = "script";
	private static final String BINDINGS_KEY = "bindings";
	
	private static final String CONTEXT_BINDING = "context";
	private static final String PROGRESS_MONITOR_BINDING = "progressMonitor";
	
	private Marker marker;
	private SupplierFactory<InputStream> scriptFactory;
	private Map<String,Object> bindings;
	private boolean interpolateScript;
	
	@Override
	public Marker getMarker() {
		return marker;
	}

	@SuppressWarnings("unchecked")
	public Eval(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		this.marker = marker;
		if (config instanceof String) {
			scriptFactory = Util.asInputStreamSupplierFactory(config);
			interpolateScript = true;
		} else if (config instanceof Map) {
			Map<String,Object> configMap = (Map<String,Object>) config;
			Util.checkUnsupportedKeys(configMap, SCRIPT_KEY, BINDINGS_KEY);
			if (configMap.containsKey(SCRIPT_KEY)) {
				scriptFactory = Util.asInputStreamSupplierFactory(loader.load(configMap.get(SCRIPT_KEY), base, progressMonitor));
			} else {
				throw new ConfigurationException("Script is required", marker);
			}
			if (configMap.containsKey(BINDINGS_KEY)) {
				bindings = Util.getMap(configMap, BINDINGS_KEY, null);
			}
		} else {
			throw new ConfigurationException(getClass().getName() + " configuration shall be a string or a map, got " + config.getClass(), marker);
		}
	}
	
	public Eval(Marker marker, Object script, Map<String,Object> bindings) throws Exception {
		this.marker = marker;
		this.scriptFactory = Util.asInputStreamSupplierFactory(script);
		this.bindings = bindings;		
	}
	
	protected Object eval(String script, Context context, ProgressMonitor progressMonitor) throws Exception {
		Map<String,Object> bindings = new HashMap<>();
		bindings.put(CONTEXT_BINDING, context);
		bindings.put(PROGRESS_MONITOR_BINDING, progressMonitor);
		if (this.bindings != null) {
			for (Entry<String, Object> e: context.interpolate(this.bindings).entrySet()) {
				bindings.put(e.getKey(), e.getValue());
			}
		}
		return Util.eval(interpolateScript ? context.interpolateToString(script) : script, bindings);
	}
	
	/**
	 * {@link Object}, {@link InputStream}, {@link Boolean}.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> SupplierFactory<T> getFactory(Class<T> type) {
		if (type == null || type == Object.class) {
			FunctionFactory<InputStream, Object> functionFactory = context -> new Function<InputStream, Object>() {

				@Override
				public double size() {
					return 1;
				}

				@Override
				public String name() {
					return "eval";
				}

				@Override
				public Object execute(InputStream script, ProgressMonitor progressMonitor) throws Exception {
					return eval(Util.toString(context, script), context, progressMonitor);
				}
			};
			
			return (SupplierFactory<T>) scriptFactory.then(functionFactory);
		}
		
		if (type == Boolean.class) {
			FunctionFactory<Object, Boolean> booleanFactory = context -> new Function<Object,Boolean>() {

				@Override
				public double size() {
					return 1;
				}

				@Override
				public String name() {
					return "boolean";
				}

				@Override
				public Boolean execute(Object obj, ProgressMonitor progressMonitor) throws Exception {
					return Boolean.TRUE.equals(obj);
				}
			};
			
			return (SupplierFactory<T>) getFactory(Object.class).then(booleanFactory);			
		}
		
		if (type == InputStream.class) {
			FunctionFactory<Object, InputStream> booleanFactory = context -> new Function<Object,InputStream>() {

				@Override
				public double size() {
					return 1;
				}

				@Override
				public String name() {
					return "stream";
				}

				@Override
				public InputStream execute(Object obj, ProgressMonitor progressMonitor) throws Exception {
					InputStream ret = DefaultConverter.INSTANCE.convert(obj, InputStream.class);
					if (obj != null && ret == null) {
						ret = Util.toStream(context, obj.toString());
					}
					return ret;
				}
			};
			
			return (SupplierFactory<T>) getFactory(Object.class).then(booleanFactory);			
		}
		
		throw new IllegalArgumentException("Unsupported type: " + type);
	}
	
}
