package org.nasdanika.exec.legacy.content;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;

public class Mustache extends Filter {

	public Mustache(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		super(loader, config, base, progressMonitor, marker);
	}

	public Mustache(Marker marker, SupplierFactory<InputStream> source) {
		super(marker, source);
	}

	@Override
	protected String filter(Context context, String input) {		
		/*
		 * Context -> Map adapter. Breaks the map contract, but works with Mustache.
		 * Shall it break, a customized ObjectHandler would be required which would support Context
		 * in addition to Map.
		 */
		@SuppressWarnings("serial")
		Map<String, Object> map = new HashMap<String, Object>() {
			
			@Override
			public boolean containsKey(Object key) {
				return context.get((String) key) != null;
			}
			
			@Override
			public Object get(Object key) {
				return context.get((String) key);
			}
			
		};

		Writer writer = new StringWriter();
		MustacheFactory mf = new DefaultMustacheFactory();

		com.github.mustachejava.Mustache mustache = mf.compile(new StringReader(input), "Mustache Evaluator");
		mustache.execute(writer, map);
		try {
			writer.close();
		} catch (IOException e) {
			throw new NasdanikaException("Unexpected here...", e);
		}
		return writer.toString();
	}
	
	

}
