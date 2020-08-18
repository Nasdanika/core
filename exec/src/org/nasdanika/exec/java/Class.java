package org.nasdanika.exec.java;

import java.net.URL;

import org.nasdanika.common.Context;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.Marker;

public class Class extends Type {

	public Class(ObjectLoader loader, String type, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		super(loader, type, config, base, progressMonitor, marker);
	}

	@Override
	protected String generate(Context context, String comment, String body, ProgressMonitor progressMonitor) throws Exception {
		StringBuilder ret = new StringBuilder();
		// Comment
		ret.append(comment);
		appendAnnotations(context, ret);
		appendModifiers(context, ret);
		ret.append("class ").append(context.interpolateToString(name));
		appendTypeParameters(context, ret);
		
		if (!superTypes.isEmpty()) {
			int idx = 0;
			for (String st: superTypes) {
				String superTypeName = context.interpolateToString(st);
				if (superTypeName.trim().length() > 0) {
					if (idx == 0) {
						if (Object.class.getName().equals(superTypeName)) {
							++idx;
							continue; 
						}
						ret.append(" extends ");
					} else {
						if (idx == 1) {
							ret.append(" implements ");
						} else {
							ret.append(", ");
						}
					}
					ret.append(superTypeName);
					++idx;
				}
			}
		}
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
