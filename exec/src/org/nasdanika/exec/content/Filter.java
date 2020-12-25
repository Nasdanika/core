package org.nasdanika.exec.content;

import java.io.InputStream;
import java.net.URL;

import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

public abstract class Filter implements SupplierFactory<InputStream>, Marked {
	
	protected SupplierFactory<InputStream> source;
	private Marker marker;
	
	@Override
	public Marker getMarker() {
		return marker;
	}
	
	protected Filter(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		this.marker = marker;
		source = Util.asSupplierFactory(loader.load(config, base, progressMonitor));
	}
	
	protected Filter(Marker marker, SupplierFactory<InputStream> source) {
		this.marker = marker;
		this.source = source;
	}
	
	private FunctionFactory<InputStream,InputStream> filterFactory = ctx -> new Function<InputStream, InputStream>() {

		@Override
		public double size() {
			return 1;
		}

		@Override
		public String name() {
			return Filter.this.getClass().getName();
		}

		@Override
		public InputStream execute(InputStream input, ProgressMonitor progressMonitor) throws Exception {
			return Util.filter(ctx, input, str -> Filter.this.filter(ctx, str));
		}
		
	};
		
	@Override
	public Supplier<InputStream> create(Context context) throws Exception {
		return source.then(filterFactory).create(context);
	}
	
	protected abstract String filter(Context context, String input); 	

}


