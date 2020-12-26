package org.nasdanika.common.persistence;

import java.net.URL;

import org.nasdanika.common.ProgressMonitor;

/**
 * Loads feature value using loader.
 * @author Pavel
 *
 */
public class Reference extends Attribute<Object> {

	public Reference(Object key, boolean isDefault, boolean required, Object defaultValue, String description, Object... exclusiveWith) {
		super(key, isDefault, required, defaultValue, description, exclusiveWith);
	}
	
	@Override
	public Object create(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker)	throws Exception {
		return loader.load(config, base, progressMonitor);
	}

}
