package org.nasdanika.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;

/**
 * Object which loads its configuration to features identified by keys.
 * @author Pavel
 *
 */
public class FeatureObject implements Marked, Loadable {
	
	private Collection<? extends Marker> markers = new ArrayList<>();
	
	@Override
	public Collection<? extends Marker> getMarkers() {
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
	
	@Override
	public void load(
			ObjectLoader loader, 
			Object config, 
			URI base,
			BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> resolver, 
			Collection<? extends Marker> markers,
			ProgressMonitor progressMonitor) {
		this.markers = markers;			
		Optional<Feature<?>> constructorFeatureOptional = features.stream().filter(Feature::isConstructor).findFirst();
		if (constructorFeatureOptional.isPresent()) {
			Feature<?> constructorFeature = constructorFeatureOptional.get();
			constructorFeature.load(loader, Collections.singletonMap(constructorFeature.getKey(), config), base, resolver, markers, progressMonitor);			
		} else if (config instanceof Map) {
			Map<?,?> configMap = (Map<?,?>) config;
			Util.checkUnsupportedKeys(configMap, features.stream().map(Feature::getKey).toList());
			for (Feature<?> feature: features) {
				feature.load(loader, configMap, base, resolver, markers, progressMonitor);
			}
		} else {
			Optional<Feature<?>> defaultFeatureOptional = features.stream().filter(Feature::isDefault).findFirst();
			if (defaultFeatureOptional.isPresent()) {				
				Feature<?> defaultFeature = defaultFeatureOptional.get();
				// Singleton map with a single entry default feature key -> config.
				defaultFeature.load(loader, Collections.singletonMap(defaultFeature.getKey(), config), base, resolver, markers, progressMonitor);
			} else if (!Boolean.TRUE.equals(config)) {
				throw new ConfigurationException(getClass().getName() + " configuration shall be a map, got " + config, markers);
			}
		}
	}			

}
