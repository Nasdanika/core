package org.nasdanika.exec.java;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

public class Class extends Type {

	public Class(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		super(loader, config, base, progressMonitor, marker);
	}
	
	public Class(
			Marker marker, 
			String name, 
			List<String> modifiers, 
			List<String> annotations,
			List<String> typeParameters, 
			SupplierFactory<InputStream> comment, 
			SupplierFactory<InputStream> body,
			List<String> imports, 
			List<String> superTypes) {
		
		super(marker, name, modifiers, annotations, typeParameters, comment, body, imports, superTypes);
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
			for (String superTypeName: flatten(context, superTypes)) {
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
