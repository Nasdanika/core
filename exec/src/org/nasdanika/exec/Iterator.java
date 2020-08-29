package org.nasdanika.exec;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.nasdanika.common.Adaptable;
import org.nasdanika.common.CompoundConsumer;
import org.nasdanika.common.CompoundConsumerFactory;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.ListCompoundSupplier;
import org.nasdanika.common.ListCompoundSupplierFactory;
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
 * TODO 
 * @author Pavel
 *
 */
public class Iterator implements Adaptable, Marked {

	/**
	 * key - context path for iteration, value - object to execute for each iteration (target).
	 */
	protected Map<String,Object> iterators = new LinkedHashMap<>();
	private Marker marker;
	
	@Override
	public Marker getMarker() {
		return marker;
	}

	/**
	 * Iterator config is a map of iterator values to objects to iterate over. I.e. one iterator (for-each) may contain multiple iteration "clauses". 
	 * @param factory
	 * @param config
	 * @param base
	 * @param progressMonitor
	 * @param marker 
	 */
	@SuppressWarnings("unchecked")
	public Iterator(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {		
		if (config instanceof Map) {
			this.marker = marker;
			for (Entry<String, Object> e: ((Map<String,Object>) config).entrySet()) {
				iterators.put(e.getKey(), loader.load(e.getValue(), base, progressMonitor));
			}
		} else {
			throw new ConfigurationException("Iterator specification must be a map.", marker);
		}
	}
	
	public Iterator(Marker marker, Map<String,Object> iterators) {
		this.marker = marker;
		this.iterators.putAll(iterators);
	}
	
	public Iterator(String iterator, Object target) {
		iterators.put(iterator, target);
	}	
	
	public Iterator(Map<String,Object> iterators) {
		this.iterators.putAll(iterators);
	}	
	
	/**
	 * @param context
	 * @return
	 */
	protected Collection<Context> iterate(Context context, String iterator) throws Exception {
		Object value = context.get(iterator);
		if (value == null) {
			// Sub-context - prefix.
			return Collections.singleton((context.map(Util.hierarchicalMapper(iterator))));
		}
		
		return iterate(context, value);
	}
	
	/**
	 * Recursively iterates
	 * @param context
	 * @param iterator
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Collection<Context> iterate(Context context, Object value) throws Exception {	
		if (Boolean.TRUE.equals(value)) {
			return Collections.singleton(context);			
		}
		if (Boolean.FALSE.equals(value)) {
			return Collections.emptyList();
		}
		
		if (value instanceof Map) {
			// Interpolate the map and wrap into context
			Context ctx = Context.wrap(context.interpolate((Map<String,Object>) value)::get).compose(new Context() {

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
			return Collections.singleton(ctx);
		}
		if (value instanceof Collection) {
			Collection<Context> ret = new ArrayList<>();
			for (Object e: (Collection<?>) value) {
				ret.addAll(iterate(context, e));
			}
			return ret;
		}
		
		return Collections.singleton(context.compose(Context.singleton("data", value instanceof String ? context.interpolate((String) value) : value))); 		
	}
	
	/**
	 * Adapts to either {@link ConsumerFactory} or {@link SupplierFactory}.
	 * In the first case the consumer is expected to take {@link BinaryEntityContainer}. 
	 * In the latter case supplied results are expected to be {@link InputStream} and are joined into a single input stream.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T adaptTo(Class<T> type) {
		if (type == ConsumerFactory.class) {
			if (iterators.size() == 1) {
				for (Entry<String, Object> ie: iterators.entrySet()) {
					ConsumerFactory<BinaryEntityContainer> ret = context -> createConsumer(context, ie.getKey(), ie.getValue()); 
					return (T) ret;															
				}
			}
			CompoundConsumerFactory<BinaryEntityContainer> ret = new CompoundConsumerFactory<BinaryEntityContainer>("Iterator");
			for (Entry<String, Object> ie: iterators.entrySet()) {
				ConsumerFactory<BinaryEntityContainer> cf = context -> createConsumer(context, ie.getKey(), ie.getValue());
				ret.add(cf);
			}
			return (T) ret;									
		}
		
		if (type == SupplierFactory.class) {
			if (iterators.size() == 1) {
				for (Entry<String, Object> ie: iterators.entrySet()) {
					SupplierFactory<InputStream> ret = context -> createSupplier(context, ie.getKey(), ie.getValue()); 
					return (T) ret;															
				}
			}
			
			ListCompoundSupplierFactory<InputStream> ret = new ListCompoundSupplierFactory<>("Iterator");
			for (Entry<String, Object> ie: iterators.entrySet()) {
				SupplierFactory<InputStream> sf = context -> createSupplier(context, ie.getKey(), ie.getValue());
				ret.add(sf);
			}
			return (T) ret.then(Util.JOIN_STREAMS_FACTORY);
		}
		
		return Adaptable.super.adaptTo(type);
	}	
	
	// --- Consumer ---

	/**
	 * Consumer factory method
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public Consumer<BinaryEntityContainer> createConsumer(Context context, String iterator, Object target) throws Exception {		
		Collection<Context> iContexts = iterate(context, iterator);
		if (iContexts.size() == 1) {
			return createConsumerElement(iContexts.iterator().next(), target);
		}
		CompoundConsumer<BinaryEntityContainer> ret = new CompoundConsumer<>("Iterator");
		for (Context iContext: iContexts) {
			ret.add(createConsumerElement(iContext, target));
		}
		return ret;
	}

	/**
	 * Invoked for each iterator element.
	 * @param iContext Iterator element context mapped and injected with configuration entries.
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected Consumer<BinaryEntityContainer> createConsumerElement(Context iContext, Object target) throws Exception {
		if (target instanceof Collection) {
			CompoundConsumer<BinaryEntityContainer> ret = new CompoundConsumer<>("Target collection");
			for (ConsumerFactory<BinaryEntityContainer> te: (Collection<ConsumerFactory<BinaryEntityContainer>>) target) {
				ret.add(te.create(iContext));
			}
			return ret;			
		}
		
		return ((ConsumerFactory<BinaryEntityContainer>) target).create(iContext);
	}
		
	// --- Supplier ---
	
	public Supplier<InputStream> createSupplier(Context context, String iterator, Object target) throws Exception {
		Collection<Context> iContexts = iterate(context, iterator);
		if (iContexts.size() == 1) {
			return createSupplierElement(iContexts.iterator().next(), target);
		}
		
		@SuppressWarnings("resource")
		ListCompoundSupplier<InputStream> ret = new ListCompoundSupplier<>("Iterator");
		for (Context iContext: iContexts) {
			ret.add(createSupplierElement(iContext, target));
		}
		return ret.then(Util.JOIN_STREAMS);
	}
	
	/**
	 * Invoked for each iterator element.
	 * @param iContext Iterator element context mapped and injected with configuration entries.
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected Supplier<InputStream> createSupplierElement(Context iContext, Object target) throws Exception {
		if (target instanceof Collection) {
			@SuppressWarnings("resource")
			ListCompoundSupplier<InputStream> ret = new ListCompoundSupplier<>("Target collection");
			for (Object te: (Collection<?>) target) {
				ret.add(createSupplierElement(iContext, te));
			}
			return ret.then(Util.JOIN_STREAMS);
		}
		
		if (target instanceof SupplierFactory) {		
			return ((SupplierFactory<InputStream>) target).create(iContext);
		}
		
		// Converting to string, interpolating, streaming
		Supplier<String> textSupplier = Supplier.from(iContext.interpolateToString(String.valueOf(target)), "Scalar");
		return textSupplier.then(Util.TO_STREAM.create(iContext));
	};		
	
}
