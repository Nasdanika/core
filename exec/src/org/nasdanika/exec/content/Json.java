package org.nasdanika.exec.content;

import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.Marker;

/**
 * Encodes contained content as Json.  
 * @author Pavel
 *
 */
public class Json extends Encoder {

	public Json(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		super(loader, config, base, progressMonitor, marker);
	}

	@Override
	protected FunctionFactory<Object, InputStream> getEncoderFactory() {
		return ctx -> new Function<Object, InputStream>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Encoder";
			}

			@Override
			public InputStream execute(Object data, ProgressMonitor progressMonitor) throws Exception {
				if (data == null) {
					return null;
				}
				if (data instanceof Map) {
					JSONObject jo = new JSONObject((Map<?,?>) data);
					return DefaultConverter.INSTANCE.toInputStream(jo.toString(4));
				}
				if (data instanceof Collection) {
					JSONArray ja = new JSONArray((Collection<?>) data);
					return DefaultConverter.INSTANCE.toInputStream(ja.toString(4));
				}
				throw new IllegalArgumentException("Cannot convert to JSON: " + data);
			}
			
		};
	}

}


