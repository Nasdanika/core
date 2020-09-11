package org.nasdanika.exec.content;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.exec.Loader;

public class Replace implements SupplierFactory<InputStream>, Marked {
	
	private static final String SOURCE_KEY = "source";
	private static final String PATTERNS_KEY = "patterns";	
	
	protected SupplierFactory<InputStream> source;
	private Marker marker;
	private Map<String, String> patterns;
	
	@Override
	public Marker getMarker() {
		return marker;
	}
	
	@SuppressWarnings("unchecked")
	public Replace(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		this.marker = marker;
		if (config instanceof Map) {
			Map<String,Object> configMap = (Map<String,Object>) config;
			Loader.checkUnsupportedKeys(configMap, SOURCE_KEY, PATTERNS_KEY);
			source = Loader.asSupplierFactory(loader.load(configMap.get(SOURCE_KEY), base, progressMonitor), marker);
			patterns = Util.getMap(configMap, PATTERNS_KEY, null);
		} else {
			throw new ConfigurationException(getClass().getName() + " configuration shall be a map, got " + config.getClass(), marker);
		}
		
	}
	
	protected Replace(Marker marker, SupplierFactory<InputStream> source) {
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
			return Replace.this.getClass().getName();
		}

		@Override
		public InputStream execute(InputStream input, ProgressMonitor progressMonitor) throws Exception {
			return Util.filter(ctx, input, str -> Replace.this.replace(ctx, str));
		}
		
	};
	
	
	@Override
	public Supplier<InputStream> create(Context context) throws Exception {
		return source.then(filterFactory).create(context);
	}
	
	protected String replace(Context context, String input) {
		if (patterns == null || input == null) {
			return input;
		}
		
		for (Entry<String, String> pe: patterns.entrySet()) {
			Pattern pattern = Pattern.compile(pe.getKey());
			Matcher matcher = pattern.matcher(input);
			StringBuilder output = new StringBuilder();
			int i = 0;
			while (matcher.find()) {
				String replacement = context.interpolateToString(pe.getValue());
			    if (replacement != null) {
				    output.append(input.substring(i, matcher.start())).append(replacement);			    
				    i = matcher.end();
			    }
			}
			output.append(input.substring(i, input.length()));
			input = output.toString();			
		}
		
		return input;		
	}; 	

}


