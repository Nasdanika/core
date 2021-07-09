package org.nasdanika.exec.content;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
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
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

/**
 * Encodes contained map as URL encoded form. The map may contain values and lists. Nested maps are not supported yet.  
 * @author Pavel
 *
 */
public class Form implements SupplierFactory<InputStream>, Marked {
	
	protected MapCompoundSupplierFactory<String, Object> dataFactory = new MapCompoundSupplierFactory<String, Object>("Form");
	private Marker marker;
	
	@Override
	public Marker getMarker() {
		return marker;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Form(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		this.marker = marker;
		Object data = loader.load(config, base, progressMonitor);
		if (data instanceof Map) {
			for (Entry<String, Object> me: ((Map<String,Object>) data).entrySet()) {
				if (me.getValue() instanceof Collection) {
					ListCompoundSupplierFactory<InputStream> lcsf = new ListCompoundSupplierFactory<InputStream>("Collection at " + me.getKey());
					for (Object mee: (Collection<?>) me.getValue()) {
						lcsf.add(Util.asInputStreamSupplierFactory(loader.load(mee, base, progressMonitor)));
					}
					this.dataFactory.put(me.getKey(), (SupplierFactory) lcsf);
				} else {
					this.dataFactory.put(me.getKey(), (SupplierFactory) Util.asInputStreamSupplierFactory(me.getValue()));
				}
			}
		} else {
			throw new ConfigurationException("Form configuration must be a map", marker);
		}
	}
	
	public Form(Marker marker, MapCompoundSupplierFactory<String, Object> dataFactory) {
		this.marker = marker;
		this.dataFactory = dataFactory;
	}
	
	private FunctionFactory<Map<String,Object>,InputStream> formFactory = ctx -> new Function<Map<String,Object>, InputStream>() {

		@Override
		public double size() {
			return 1;
		}

		@Override
		public String name() {
			return Form.this.getClass().getName();
		}

		@SuppressWarnings("unchecked")
		@Override
		public InputStream execute(Map<String,Object> data, ProgressMonitor progressMonitor) throws Exception {
			StringBuilder formBuilder = new StringBuilder();
			for (Entry<String, Object> e: data.entrySet()) {
				if (e.getValue() instanceof InputStream) {
					if (formBuilder.length() > 0) {
						formBuilder.append("&");
					}
					formBuilder.append(e.getKey()).append("=");
					String value = Util.toString(ctx, (InputStream) e.getValue());
					formBuilder.append(URLEncoder.encode(value, ctx.get(Charset.class, StandardCharsets.UTF_8).name()));
				} else {
					// Has to be a collection of input streams
					for (InputStream in: (Collection<InputStream>) e.getValue()) {
						if (formBuilder.length() > 0) {
							formBuilder.append("&");
						}
						formBuilder.append(e.getKey()).append("=");
						String value = Util.toString(ctx, in);
						formBuilder.append(URLEncoder.encode(value, ctx.get(Charset.class, StandardCharsets.UTF_8).name()));						
					}
				}
			}
			return Util.toStream(ctx, formBuilder.toString());
		}
		
	};
			
	@Override
	public Supplier<InputStream> create(Context context) throws Exception {
		return dataFactory.then(formFactory).create(context);
	}

}


