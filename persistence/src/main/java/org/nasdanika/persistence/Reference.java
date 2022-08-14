package org.nasdanika.persistence;

import java.util.List;

import org.eclipse.emf.common.util.URI;
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
	public Object create(ObjectLoader loader, Object config, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers) {
		return loader.load(config, base, progressMonitor);
	}

}
