package org.nasdanika.exec.java;

import java.net.URL;

import org.nasdanika.common.Context;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.Marker;

public class Constructor extends Operation {

	public Constructor(ObjectLoader loader, String type, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		super(loader, type, config, base, progressMonitor, marker);
	}

	@Override
	protected String generate(Context context, String comment, String body, ProgressMonitor progressMonitor) throws Exception {
		StringBuilder ret = new StringBuilder();
		// Comment
		ret.append(comment);
		appendAnnotations(context, ret);
		appendModifiers(context, ret);
		appendTypeParameters(context, ret);
		
		// Name
		ret.append(context.interpolateToString(name));
		
		appendParameters(context, ret);
		appendExceptions(context, ret);
		ret
			.append(" {")
			.append(System.lineSeparator())
			.append(body)
			.append(System.lineSeparator())
			.append("}")
			.append(System.lineSeparator());
		
		return ret.toString();
	}

}
