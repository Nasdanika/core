package org.nasdanika.exec.java;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.exec.Loader;

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
		Loader.loadMultiString((Map<String, Object>) config, SUPER_TYPES_KEY, superTypes::add);
	}

}
