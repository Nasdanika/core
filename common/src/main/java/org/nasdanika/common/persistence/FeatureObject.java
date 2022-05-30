package org.nasdanika.common.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;

/**
 * Object which loads its configuration to features identified by keys.
 * @author Pavel
 *
 */
public class FeatureObject implements Marked, Loadable {
	
	private List<? extends Marker> markers = new ArrayList<>();
	
	@Override
	public List<? extends Marker> getMarkers() {
		return markers;
	}
	
	protected List<Feature<?>> features = new ArrayList<>();

	/**
	 * Adds feature to the feature list.
	 * @param <F>
	 * @param feature
	 * @return
	 */
	protected <F extends Feature<?>> F addFeature(F feature) {
		if (feature.isDefault() && features.stream().filter(Feature::isDefault).findFirst().isPresent()) {
			throw new IllegalArgumentException("Duplicate default feature: " + feature.getKey());
		}
		if (features.stream().filter(o -> feature.getKey().equals(o.getKey())).findFirst().isPresent()) {
			throw new IllegalArgumentException("Duplicate feature: " + feature.getKey());
		}
		features.add(feature);
		return feature;
	}

	/**
	 * Loads object
	 * @param loader
	 * @param config
	 * @param base
	 * @param progressMonitor
	 * @param marker
	 * @return this object.
	 * @throws Exception
	 */
	@Override
	public void load(ObjectLoader loader, Object config, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers) throws Exception {
		this.markers = markers;			
		if (config instanceof Map) {
			Map<?,?> configMap = (Map<?,?>) config;
			Util.checkUnsupportedKeys(configMap, features.stream().map(Feature::getKey).collect(Collectors.toList()));
			for (Feature<?> feature: features) {
				feature.load(loader, configMap, base, progressMonitor, markers);
			}
		} else {
			Optional<Feature<?>> defaultFeatureOptional = features.stream().filter(Feature::isDefault).findFirst();
			if (defaultFeatureOptional.isPresent()) {				
				Feature<?> defaultFeature = defaultFeatureOptional.get();
				// Singleton map with a single entry default feature key -> config.
				defaultFeature.load(loader, Collections.singletonMap(defaultFeature.getKey(), config), base, progressMonitor, markers);
			} else if (!Boolean.TRUE.equals(config)) {
				throw new ConfigurationException(getClass().getName() + " configuration shall be a map, got " + config, markers);
			}
		}
	}			

}
