package org.nasdanika.exec.resources;

import java.net.URL;
import java.util.Map;

import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.resources.BinaryEntityContainer;

/**
 * Base class for resources - {@link Container}, File, and Java specializations.
 * @author Pavel
 *
 */
public abstract class Resource implements ConsumerFactory<BinaryEntityContainer> {
			
	private static final String NAME_KEY = "name";
	private static final String RECONCILE_ACTION_KEY = "reconcile-action";
	static final String CONTENTS_KEY = "contents";
	static final String MERGER_KEY = "merger";

	protected ReconcileAction reconcileAction = ReconcileAction.OVERWRITE;
	protected String name;
	
	@SuppressWarnings("unchecked")
	protected Resource(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		if (config instanceof Map) {
			Map<String,Object> configMap = (Map<String,Object>) config;
			if (configMap.containsKey(RECONCILE_ACTION_KEY)) {
				Object reconcileActionObj = configMap.get(RECONCILE_ACTION_KEY);
				if (reconcileActionObj instanceof String) {
					this.reconcileAction = ReconcileAction.valueOf((String) reconcileActionObj);
				} else {
					throw new ConfigurationException(RECONCILE_ACTION_KEY + " value must be a string", Util.getMarker(configMap, RECONCILE_ACTION_KEY));
				}
			}
			
			if (!configMap.containsKey(NAME_KEY)) {
				throw new ConfigurationException(NAME_KEY + " is missing", marker);
			}
			
			Object nameObj = configMap.get(NAME_KEY);
			if (nameObj instanceof String) {
				this.name = (String) nameObj;
			} else {
				throw new ConfigurationException(NAME_KEY + " value must be a string", Util.getMarker(configMap, NAME_KEY));
			}
		} else {
			throw new ConfigurationException(getClass().getName() + " configuration shall be a map, got " + config.getClass(), marker);
		}
	}
	
	/**
	 * Produces final (physical) resource name from a a name (logical). E.g. "logical" Java package name is dot separated, but container name is slash separated.
	 * @param name
	 * @return
	 */
	protected String finalName(String name) {
		return name;
	}	

}
