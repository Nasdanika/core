package org.nasdanika.exec.java;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.exec.Loader;

public abstract class Operation extends Member {

	private static final String PARAMETERS_KEY = "parameters";
	private static final String EXCEPTIONS_KEY = "exceptions";

	protected List<String> parameters = new ArrayList<>();
	protected List<String> exceptions = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	protected Operation(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		super(loader, config, base, progressMonitor, marker);
		Loader.loadMultiString((Map<String, Object>) config, PARAMETERS_KEY, parameters::add);
		Loader.loadMultiString((Map<String, Object>) config, EXCEPTIONS_KEY, exceptions::add);
	}
	
	
	protected void appendExceptions(Context context, StringBuilder builder) {
		if (!exceptions.isEmpty()) {
			builder.append(" throws ");
			Iterator<String> eit = exceptions.iterator();
			while (eit.hasNext()) {
				builder.append(context.interpolate(eit.next()));
				if (eit.hasNext()) {
					builder.append(", ");
				}
			}					
		}
	}

	protected void appendParameters(Context context, StringBuilder builder) {
		builder.append("(");
		if (!parameters.isEmpty()) {
			Iterator<String> pit = parameters.iterator();
			while (pit.hasNext()) {
				builder.append(context.interpolate(pit.next()));
				if (pit.hasNext()) {
					builder.append(", ");
				}
			}
		}
		builder.append(")");
	}	

}
