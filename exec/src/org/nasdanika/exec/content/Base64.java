package org.nasdanika.exec.content;

import java.io.InputStream;
import java.net.URL;

import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.exec.Loader;

/**
 * Encodes streams provided by contained suppliers (content components) to Base64. 
 * @author Pavel
 *
 */
public class Base64 implements SupplierFactory<InputStream>, Marked {
	
	protected SupplierFactory<InputStream> source;
	private Marker marker;
	
	@Override
	public Marker getMarker() {
		return marker;
	}
	
	public Base64(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		this.marker = marker;
		source = Loader.asSupplierFactory(loader.load(config, base, progressMonitor));
	}
	
	public Base64(Marker marker, SupplierFactory<InputStream> source) {
		this.marker = marker;
		this.source = source;
	}
	
	private FunctionFactory<InputStream,InputStream> base64Factory = ctx -> new Function<InputStream, InputStream>() {

		@Override
		public double size() {
			return 1;
		}

		@Override
		public String name() {
			return Base64.this.getClass().getName();
		}

		@Override
		public InputStream execute(InputStream input, ProgressMonitor progressMonitor) throws Exception {
			byte[] inputBytes = DefaultConverter.INSTANCE.toByteArray(input);
			byte[] encodedBytes = java.util.Base64.getEncoder().encode(inputBytes);
			return DefaultConverter.INSTANCE.toInputStream(encodedBytes);
		}
		
	};
	
	@Override
	public Supplier<InputStream> create(Context context) throws Exception {
		return source.then(base64Factory).create(context);
	}

}


