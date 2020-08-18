package org.nasdanika.exec.java;

import java.net.URL;
import java.util.Iterator;

import org.nasdanika.common.Context;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.Marker;

public class Enum extends Type {

	public Enum(ObjectLoader loader, String type, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		super(loader, type, config, base, progressMonitor, marker);
	}

	@Override
	protected String generate(Context context, String comment, String body, ProgressMonitor progressMonitor) throws Exception {
		StringBuilder ret = new StringBuilder();
		ret.append(comment);
		appendAnnotations(context, ret);
		appendModifiers(context, ret);
		ret.append("enum ").append(context.interpolateToString(name));
		
		if (!superTypes.isEmpty()) {
			ret.append(" implements ");
			Iterator<String> stit = superTypes.iterator();
			while (stit.hasNext()) {
				ret.append(context.interpolate(stit.next()));
				if (stit.hasNext()) {
					ret.append(", ");
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
