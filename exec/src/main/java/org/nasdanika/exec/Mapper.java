package org.nasdanika.exec;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.nasdanika.common.Adaptable;
import org.nasdanika.common.Command;
import org.nasdanika.common.CommandFactory;
import org.nasdanika.common.CompoundCommand;
import org.nasdanika.common.CompoundConsumer;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.ListCompoundSupplier;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.common.resources.BinaryEntityContainer;

/**
 * Uses map interpolation to create a context for target(s). 
 * Map value can be a map or a string. In the latter case the string is resolved to a URL relative to the base and YAML map is loaded from that URL.
 * @author Pavel
 *
 */
public class Mapper implements Adaptable, Marked {
	
	private static final String TARGET_KEY = "target";
	private static final String MAP_KEY = "map";
	
	protected List<Object> targets = new ArrayList<>();
	protected List<Marker> targetMarkers = new ArrayList<>();
	protected Map<String,Object> map; 
	private Marker marker;
	
	@Override
	public Marker getMarker() {
		return marker;
	}

	@SuppressWarnings("unchecked")
	public Mapper(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		if (config instanceof Map) {
			this.marker = marker;
			Map<String,Object> map = (Map<String, Object>) config;
			Util.checkUnsupportedKeys(map, TARGET_KEY, MAP_KEY);
			if (!map.containsKey(TARGET_KEY)) {
				throw new ConfigurationException("Configuration must contain 'target' key", marker);				
			}
			Object targetSpec = map.get(TARGET_KEY);			
			if (targetSpec instanceof Collection) {
				int idx = 0;
				for (Object e: (Collection<Object>) targetSpec) {
					targets.add(loader.load(e, base, progressMonitor));					
					targetMarkers.add(Util.getMarker((Collection<?>) targetSpec, idx++));
				}
			} else {
				targets.add(loader.load(targetSpec, base, progressMonitor));
				targetMarkers.add(Util.getMarker(map, TARGET_KEY));
			}
			
			if (!map.containsKey(MAP_KEY)) {
				throw new ConfigurationException("Configuration must contain 'map' key", marker);				
			}
			loadMap(loader, map.get(MAP_KEY), base, progressMonitor);
		} else {
			throw new ConfigurationException("Configuration must be a map, got " + config.getClass(), marker);
		}
	}	
	
	public Mapper(Marker marker, Map<String,Object> map, List<Object> targets, List<Marker> targetMarkers) {
		this.marker = marker;
		this.map.putAll(map);
		this.targets.addAll(targets);
		this.targetMarkers.addAll(targetMarkers);
	}
	
	@SuppressWarnings("unchecked")
	private void loadMap(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor) throws Exception {
		if (config instanceof Map) {
			map = (Map<String,Object>) config;
		} else if (config instanceof String) {
			URL mapURL = new URL(base, (String) config);
			loadMap(loader, loader.loadYaml(mapURL, progressMonitor), mapURL, progressMonitor);
		} else {
			throw new IllegalArgumentException("Expected map or string, got " + config.getClass());
		}
	}
	
	// --- Mapping ---
	
	protected org.nasdanika.common.Supplier<Context> createContextSupplier(Context context) {
		return new Supplier<Context>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Mapping";
			}

			@Override
			public Context execute(ProgressMonitor progressMonitor) throws Exception {
				Map<String, Object> iMap = context.interpolate(map);
				return Context.wrap(iMap::get).compose(new Context() {

					@Override
					public Object get(String key) {
						// Not inheriting properties
						return null;
					}

					@Override
					public <S> S get(Class<S> type) {
						return context.get(type);
					}
					
				});  
			}
			
		};
		
	}
	
	// --- Command ---

	protected Command configureCommand(Context context) throws Exception {
		SupplierFactory<Context> contextSupplierFactory = this::createContextSupplier;
		CommandFactory commandFactory = this::createCommand;
		return commandFactory.contextify(contextSupplierFactory).create(context);
	}

	/**
	 * Invoked for each iterator element.
	 * @param iContext Iterator element context mapped and injected with configuration entries.
	 * @return
	 * @throws Exception
	 */
	protected Command createCommand(Context context) throws Exception {
		if (targets.size() == 1) {
			return Util.asCommandFactory(targets.iterator().next(), targetMarkers.iterator().next()).create(context);
		}
		
		CompoundCommand ret = new CompoundCommand("Target collection", null);
		int idx = 0;
		for (Object te: targets) {
			ret.add(Util.asCommandFactory(te, targetMarkers.get(idx++)).create(context));
		}
		return ret;			
	}
	
	// --- Consumer ---

	protected Consumer<BinaryEntityContainer> configureConsumer(Context context) throws Exception {
		SupplierFactory<Context> contextSupplierFactory = this::createContextSupplier;
		ConsumerFactory<BinaryEntityContainer> consumerFactory = this::createConsumer;
		return consumerFactory.contextify(contextSupplierFactory).create(context);
	}

	/**
	 * Invoked for each iterator element.
	 * @param iContext Iterator element context mapped and injected with configuration entries.
	 * @return
	 * @throws Exception
	 */
	protected Consumer<BinaryEntityContainer> createConsumer(Context context) throws Exception {
		if (targets.size() == 1) {
			return Util.<BinaryEntityContainer>asConsumerFactory(targets.iterator().next(), targetMarkers.iterator().next()).create(context);
		}
		
		CompoundConsumer<BinaryEntityContainer> ret = new CompoundConsumer<>("Target collection");
		int idx = 0;
		for (Object te: targets) {
			ret.add(Util.<BinaryEntityContainer>asConsumerFactory(te, targetMarkers.get(idx++)).create(context));
		}
		return ret;			
	}
		
	// --- Supplier ---
	
	protected Supplier<InputStream> configureSupplier(Context context) throws Exception {
		SupplierFactory<Context> contextSupplierFactory = this::createContextSupplier; 
		SupplierFactory<InputStream> supplierFactory = this::createSupplier;		
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
			return Util.asInputStreamSupplierFactory(targets.iterator().next()).create(context);
		}
		
		ListCompoundSupplier<InputStream> ret = new ListCompoundSupplier<>("Target collection");
		for (Object te: targets) {
			ret.add(Util.asInputStreamSupplierFactory(te).create(context));
		}
		return ret.then(Util.JOIN_STREAMS);
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
		if (type == CommandFactory.class) {
			CommandFactory ret = this::configureCommand; 
			return (T) ret;															
		}
		
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
