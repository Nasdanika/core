package org.nasdanika.exec.java;

import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Marker;

public class Method extends Operation {
		
	private static final String RETURN_TYPE_KEY = "return-type";
	protected String returnType;

	@SuppressWarnings("unchecked")
	public Method(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		super(loader, config, base, progressMonitor, marker);
		returnType = Util.getString((Map<String, Object>) config, RETURN_TYPE_KEY, false, marker);
	}
	
	public Method(
			Marker marker, 
			String name, 
			List<String> modifiers, 
			List<String> annotations,
			List<String> typeParameters, 
			SupplierFactory<InputStream> comment, 
			SupplierFactory<InputStream> body,
			List<String> imports, 
			List<String> parameters, 
			List<String> exceptions,
			String returnType) {
		
		super(marker, name, modifiers, annotations, typeParameters, comment, body, imports, parameters, exceptions);
		this.returnType = returnType;
	}
	
	
	@Override
	protected Collection<String> getSupportedKeys() {
		Collection<String> supportedKeys = super.getSupportedKeys();
		supportedKeys.add(RETURN_TYPE_KEY);
		return supportedKeys;
	}

	@Override
	protected String generate(Context context, String comment, String body, ProgressMonitor progressMonitor) throws Exception {
		StringBuilder ret = new StringBuilder();
		// Comment
		ret.append(comment);
		appendAnnotations(context, ret);
		appendModifiers(context, ret);
		appendTypeParameters(context, ret);
		
		// Return type
		if (Util.isBlank(returnType)) {
			ret.append("void").append(" ");
		} else {
			ret.append(context.interpolateToString(returnType)).append(" ");
		}
		
		// Name
		ret.append(context.interpolateToString(name));
		
		appendParameters(context, ret);
		appendExceptions(context, ret);
		
		if (Util.isBlank(body)) { // Empty body - abstract or interface method. 
			ret.append(";").append(System.lineSeparator());
		} else {
			ret
				.append(" {")
				.append(System.lineSeparator())
				.append(body)
				.append(System.lineSeparator())
				.append("}")
				.append(System.lineSeparator());
		}
		return ret.toString();
	}

}
