package org.nasdanika.exec.java;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Marker;

public abstract class Operation extends Member {

	private static final String PARAMETERS_KEY = "parameters";
	private static final String EXCEPTIONS_KEY = "exceptions";

	protected List<String> parameters = new ArrayList<>();
	protected List<String> exceptions = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	protected Operation(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		super(loader, config, base, progressMonitor, marker);
		Util.loadMultiString((Map<String, Object>) config, PARAMETERS_KEY, parameters::add);
		Util.loadMultiString((Map<String, Object>) config, EXCEPTIONS_KEY, exceptions::add);
	}
	
	protected Operation(
			Marker marker, 
			String name, 
			List<String> modifiers, 
			List<String> annotations,
			List<String> typeParameters, 
			SupplierFactory<InputStream> comment, 
			SupplierFactory<InputStream> body,
			List<String> imports,
			List<String> parameters,
			List<String> exceptions) {
		
		super(marker, name, modifiers, annotations, typeParameters, comment, body, imports);
		this.parameters.addAll(parameters);
		this.exceptions.addAll(exceptions);
	}
	
	
	@Override
	protected Collection<String> getSupportedKeys() {
		Collection<String> supportedKeys = super.getSupportedKeys();
		supportedKeys.add(PARAMETERS_KEY);
		supportedKeys.add(EXCEPTIONS_KEY);
		return supportedKeys;
	}	
	
	protected void appendExceptions(Context context, StringBuilder builder) {
		if (!exceptions.isEmpty()) {
			builder.append(" throws ");
			Iterator<String> eit = flatten(context, exceptions).iterator();
			while (eit.hasNext()) {
				builder.append(eit.next());
				if (eit.hasNext()) {
					builder.append(", ");
				}
			}					
		}
	}

	protected void appendParameters(Context context, StringBuilder builder) {
		builder.append("(");
		if (!parameters.isEmpty()) {
			Iterator<String> pit = flatten(context, parameters).iterator();
			while (pit.hasNext()) {
				builder.append(pit.next());
				if (pit.hasNext()) {
					builder.append(", ");
				}
			}
		}
		builder.append(")");
	}	

}
