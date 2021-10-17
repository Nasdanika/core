package org.nasdanika.exec.legacy.java;

import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

public class Field extends Member {
	
	private static final String TYPE_KEY = "type";
	protected String type;
	
	@Override
	protected Collection<String> getSupportedKeys() {
		Collection<String> supportedKeys = super.getSupportedKeys();
		supportedKeys.add(TYPE_KEY);
		return supportedKeys;
	}

	@SuppressWarnings("unchecked")
	public Field(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		super(loader, config, base, progressMonitor, marker);
		this.type = Util.getString((Map<String, Object>) config, TYPE_KEY, true, marker);
	}
	
	public Field(
			Marker marker, 
			String name, 
			List<String> modifiers, 
			List<String> annotations,
			List<String> typeParameters, 
			SupplierFactory<InputStream> comment, 
			SupplierFactory<InputStream> body,
			List<String> imports,
			String type) {
		super(marker, name, modifiers, annotations, typeParameters, comment, body, imports);
		this.type = type;
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
