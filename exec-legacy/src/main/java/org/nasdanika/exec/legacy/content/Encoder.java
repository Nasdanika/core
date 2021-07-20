package org.nasdanika.exec.content;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.MapCompoundSupplierFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

/**
 * Base class for encoders.  
 * @author Pavel
 *
 */
public abstract class Encoder implements SupplierFactory<InputStream>, Marked {
	
	protected SupplierFactory<Object> dataFactory;
	private Marker marker;
	
	@Override
	public Marker getMarker() {
		return marker;
	}
	
	@SuppressWarnings("unchecked")
	protected Encoder(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		this.marker = marker;
		dataFactory = load(loader, config, base, progressMonitor, marker);
	}
	
	protected Encoder(Marker marker, SupplierFactory<Object> dataFactory) {
		this.marker = marker;
		this.dataFactory = dataFactory;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected SupplierFactory load(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		Object data = loader.load(config, base, progressMonitor);
		if (data instanceof Map) {
			MapCompoundSupplierFactory<String, Object> ret = new MapCompoundSupplierFactory<>("Map" + (marker == null ? "" : " at " + marker));
			for (Entry<String, Object> me: ((Map<String,Object>) data).entrySet()) {
				ret.put(me.getKey(), load(loader, me.getValue(), base, progressMonitor, Util.getMarker((Map<String,Object>) data, me.getKey())));
			}			
			return ret;
		} else if (data instanceof Collection) {
			ListCompoundSupplierFactory<InputStream> lcsf = new ListCompoundSupplierFactory<InputStream>("Collection " + (marker == null ? "" : " at " + marker));
			int idx = 0;
			for (Object mee: (Collection<?>) data) {
				lcsf.add(Util.asInputStreamSupplierFactory(loader.load(mee, base, progressMonitor)));
			}
			return lcsf;
		} else {
			return Util.asInputStreamSupplierFactory(data);
		}
		
	}

	protected abstract FunctionFactory<Object,InputStream> getEncoderFactory();
	
	/**
	 * Recursively processes maps and lists and converts streams to strings.
	 */
	private FunctionFactory<Object,Object> streamToStringFactory = ctx -> new Function<Object,Object>() {

		@Override
		public double size() {
			return 1;
		}

		@Override
		public String name() {
			return "Stream to String";
		}

		@SuppressWarnings("unchecked")
		@Override
		public Object execute(Object data, ProgressMonitor progressMonitor) throws Exception {
			if (data == null) {
				return null;
			}
			if (data instanceof Map) {
				Map<String,Object> ret = new LinkedHashMap<>();
				for (Entry<String, Object> e: ((Map<String,Object>) data).entrySet()) {
					ret.put(e.getKey(), execute(e.getValue(), progressMonitor));
				}
				return ret;
			}
			if (data instanceof Collection) {
				Collection<Object> ret = new ArrayList<>();
				for (Object e: (Collection<?>) data) {
					ret.add(execute(e, progressMonitor));
				}
				return ret;
			}
			if (data instanceof InputStream) {
				return Util.toString(ctx, (InputStream) data);
			}
			return data; // Shall never get here?
		}
		
	};
	
			
	@Override
	public Supplier<InputStream> create(Context context) throws Exception {
		return dataFactory.then(streamToStringFactory).then(getEncoderFactory()).create(context);
	}

}


