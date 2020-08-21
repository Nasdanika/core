package org.nasdanika.exec.java;

import java.net.URL;
import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.exec.Loader;

public class Field extends Member {
	
	private static final String TYPE_KEY = "type";
	private String type;

	@SuppressWarnings("unchecked")
	public Field(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		super(loader, config, base, progressMonitor, marker);
		this.type = Loader.getString((Map<String, Object>) config, TYPE_KEY, true, marker);
	}

	@Override
	protected String generate(Context context, String comment, String body, ProgressMonitor progressMonitor) throws Exception {
		StringBuilder ret = new StringBuilder();
		// Comment
		ret.append(comment);
		appendAnnotations(context, ret);
		appendModifiers(context, ret);
		ret
			.append(context.interpolateToString(type))
			.append(" ")
			.append(context.interpolateToString(name));
		
		if (!Util.isBlank(body)) {
			ret.append(" = ").append(body);
		}
		ret.append(";");
		return ret.toString();
	}

}
