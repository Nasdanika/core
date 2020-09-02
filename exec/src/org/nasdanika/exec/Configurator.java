package org.nasdanika.exec;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.nasdanika.common.Adaptable;
import org.nasdanika.common.CompoundConsumer;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ListCompoundSupplier;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.MapCompoundSupplierFactory;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.resources.BinaryEntityContainer;

/**
 * Injects configuration entries into the target's context. map with two keys - configuration and target. 
 * Target is either an object spec or a collection.
 * Configuration is a map of property names to their value supplier factories. Streams are converted to strings.
 * @author Pavel
 *
 */
public class Configurator implements Adaptable, Marked {
	
	private static final String TARGET_KEY = "target";
	private static final String PROPERTIES_KEY = "properties";
	
	protected List<Object> targets = new ArrayList<>();
	protected Map<String,Object> configuration = new HashMap<>();
	private Marker marker;
	
	@Override
	public Marker getMarker() {
		return marker;
	}

	@SuppressWarnings("unchecked")
	public Configurator(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {	
		if (config instanceof Map) {
			this.marker = marker;
			Map<String,Object> map = (Map<String, Object>) config;
			Loader.checkUnsupportedKeys(map, TARGET_KEY, PROPERTIES_KEY);
			if (!map.containsKey(TARGET_KEY)) {
				throw new ConfigurationException("Configuration must contain 'target' key", marker);				
			}
			Object targetSpec = map.get(TARGET_KEY);
			if (targetSpec instanceof Collection) {
				for (Object e: (Collection<Object>) targetSpec) {
					targets.add(loader.load(e, base, progressMonitor));					
				}
			} else {
				targets.add(loader.load(targetSpec, base, progressMonitor));
			}
			
			if (!map.containsKey(PROPERTIES_KEY)) {
				throw new ConfigurationException("Configuration must contain 'properties' key", marker);				
			}
			Object properties = map.get(PROPERTIES_KEY);
			if (!(properties instanceof Map)) {
				throw new ConfigurationException("Properties shall be a map, got " + properties.getClass(), Util.getMarker(map, PROPERTIES_KEY));								
			}
			for (Entry<String, Object> pe: ((Map<String,Object>) properties).entrySet()) {
				configuration.put(pe.getKey(), loader.load(pe.getValue(), base, progressMonitor));
			}			
		} else {
			throw new ConfigurationException("Configuration must be a map, got " + config.getClass(), marker);
		}
	}	
	
	public Configurator(Marker marker, Map<String,Object> configuration, List<Object> targets) {
		this.marker = marker;
		this.configuration.putAll(configuration);
		this.targets.addAll(targets);
	}
	
	// --- Config ---
	
	/**
	 * Creates context supplier factory from configuration for contextification.
	 * 
	 * @return Context supplier factory or null if configuration is empty.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected org.nasdanika.common.SupplierFactory<Context> createContextSupplierFactory() {
		if (configuration.isEmpty()) {
			return null;
		}
		
		MapCompoundSupplierFactory<String, Object> entriesFactory = new MapCompoundSupplierFactory<String, Object>("Entries");
		for (Entry<String, Object> ce: configuration.entrySet()) {
			Object value = ce.getValue();
			SupplierFactory<Object> sf = value instanceof SupplierFactory ? (SupplierFactory<Object>) value : context -> Supplier.from(value, "Entry "+ce.getKey());				
			entriesFactory.put(ce.getKey(), (SupplierFactory) sf.then(Util.OBJECT_TO_STRING_FACTORY));
		}
		
		FunctionFactory<Map<String, java.lang.Object>, org.nasdanika.common.Context> contextFactory = new FunctionFactory<Map<String,Object>, org.nasdanika.common.Context>() {
			
			@Override
			public Function<Map<String, java.lang.Object>, org.nasdanika.common.Context> create(org.nasdanika.common.Context context) throws Exception {
				return Function.fromBiFunction((map, progressMonitor) -> {
					return org.nasdanika.common.Context.wrap(map::get).compose(context);
				}, "Contextifier", 1);
			}
			
		};
		
		return entriesFactory.then(contextFactory);
	}
	
	// --- Consumer ---

	protected Consumer<BinaryEntityContainer> configureConsumer(Context context) throws Exception {
		SupplierFactory<Context> contextSupplierFactory = createContextSupplierFactory();
		ConsumerFactory<BinaryEntityContainer> consumerFactory = this::createConsumer;
		if (contextSupplierFactory == null) {
			return consumerFactory.create(context);
		}
		
		return consumerFactory.contextify(contextSupplierFactory).create(context);
	}

	/**
	 * Invoked for each iterator element.
	 * @param iContext Iterator element context mapped and injected with configuration entries.
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected Consumer<BinaryEntityContainer> createConsumer(Context context) throws Exception {
		if (targets.size() == 1) {
			return ((ConsumerFactory<BinaryEntityContainer>) targets.iterator().next()).create(context);
		}
		
		CompoundConsumer<BinaryEntityContainer> ret = new CompoundConsumer<>("Target collection");
		for (Object te: targets) {
			ret.add(((ConsumerFactory<BinaryEntityContainer>) te).create(context));
		}
		return ret;			
	}
		
	// --- Supplier ---
	
	protected Supplier<InputStream> configureSupplier(Context context) throws Exception {
		SupplierFactory<Context> contextSupplierFactory = createContextSupplierFactory(); 
		SupplierFactory<InputStream> supplierFactory = this::createSupplier;		
		if (contextSupplierFactory == null) {
			return supplierFactory.create(context);
		}
		
		return supplierFactory.contextify(contextSupplierFactory).create(context);
	}
	
	/**
	 * Invoked for each iterator element.
	 * @param iContext Iterator element context mapped and injected with configuration entries.
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "resource" })
	protected Supplier<InputStream> createSupplier(Context context) throws Exception {
		if (targets.size() == 1) {
			return asSupplierFactory(targets.iterator().next()).create(context);
		}
		
		ListCompoundSupplier<InputStream> ret = new ListCompoundSupplier<>("Target collection");
		for (Object te: targets) {
			ret.add(asSupplierFactory(te).create(context));
		}
		return ret.then(Util.JOIN_STREAMS);
	};		
	
	/**
	 * Wraps object into an {@link InputStream} supplier factory. Handles collection and scalar cases.
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
		
		if (obj instanceof Adaptable) {
			SupplierFactory<InputStream> adapter = ((Adaptable) obj).adaptTo(SupplierFactory.class);
			if (adapter != null) {
				return adapter;
			}
		}		
		
		// Converting to string, interpolating, streaming
		SupplierFactory<String> textFactory = context -> Supplier.from(context.interpolateToString(String.valueOf(obj)), "Scalar");
		return textFactory.then(Util.TO_STREAM);
	};		
	
	
	
	// --- Adapter ---
		
	/**
	 * Adapts to either {@link ConsumerFactory} or {@link SupplierFactory}.
	 * In the first case the consumer is expected to take {@link BinaryEntityContainer}. 
	 * In the latter case supplied results are expected to be {@link InputStream} and are joined into a single input stream.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T adaptTo(Class<T> type) {
		if (type == ConsumerFactory.class) {
			ConsumerFactory<BinaryEntityContainer> ret = this::configureConsumer; 
			return (T) ret;															
		}
		
		if (type == SupplierFactory.class) {
			SupplierFactory<InputStream> ret = this::configureSupplier;
			return (T) ret;
		}
		
		return Adaptable.super.adaptTo(type);
	}	
	
}
