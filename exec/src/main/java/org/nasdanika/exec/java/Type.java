package org.nasdanika.exec.java;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

public abstract class Type extends Member {

	private static final String SUPER_TYPES_KEY = "super-types";
	protected List<String> superTypes = new ArrayList<>();
	
	@Override
	protected Collection<String> getSupportedKeys() {
		Collection<String> supportedKeys = super.getSupportedKeys();
		supportedKeys.add(SUPER_TYPES_KEY);
		return supportedKeys;
	}

	@SuppressWarnings("unchecked")
	protected Type(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		super(loader, config, base, progressMonitor, marker);
		Util.loadMultiString((Map<String, Object>) config, SUPER_TYPES_KEY, superTypes::add);
	}

	protected Type(
			Marker marker, 
			String name, 
			List<String> modifiers, 
			List<String> annotations,
			List<String> typeParameters, 
			SupplierFactory<InputStream> comment, 
			SupplierFactory<InputStream> body,
			List<String> imports,
			List<String> superTypes) {
		
		super(marker, name, modifiers, annotations, typeParameters, comment, body, imports);
		this.superTypes.addAll(superTypes);
	}

}
