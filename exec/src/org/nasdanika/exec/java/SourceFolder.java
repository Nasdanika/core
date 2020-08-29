package org.nasdanika.exec.java;

import java.net.URL;
import java.util.Collection;
import java.util.Map;

import org.nasdanika.common.Consumer;
import org.nasdanika.common.Context;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.exec.resources.Container;
import org.nasdanika.exec.resources.ReconcileAction;

public class SourceFolder extends Container {
	
	private static final String JDK_LEVEL_KEY = "jdk";

	protected JdkLevel jdkLevel = JdkLevel.JDK_8;
	
	@Override
	protected Collection<String> getSupportedKeys() {
		Collection<String> supportedKeys = super.getSupportedKeys();
		supportedKeys.add(JDK_LEVEL_KEY);
		return supportedKeys;
	}
	
	@SuppressWarnings("unchecked")
	public SourceFolder(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		super(loader, config, base, progressMonitor, marker);
		Map<String,Object> configMap = (Map<String,Object>) config;
		if (configMap.containsKey(JDK_LEVEL_KEY)) {
			Object jdkObj = configMap.get(JDK_LEVEL_KEY);
			if (jdkObj instanceof String || jdkObj instanceof Number) {
				jdkLevel = JdkLevel.fromLiteral(String.valueOf(jdkObj));
			} else {
				throw new ConfigurationException("Invalid JDK type: " + jdkObj, Util.getMarker(configMap, JDK_LEVEL_KEY));
			}
		}
	}
	
	public SourceFolder(Marker marker, String name, ReconcileAction reconcileAction, JdkLevel jdkLevel) {
		super(marker, name, reconcileAction);
		this.jdkLevel = jdkLevel;
	}

	@Override
	public Consumer<BinaryEntityContainer> create(Context context) throws Exception {
		return super.create(Context.singleton(JdkLevel.class, jdkLevel).compose(context));
	}

}
