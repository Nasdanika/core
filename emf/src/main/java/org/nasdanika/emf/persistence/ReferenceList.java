package org.nasdanika.emf.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.ListAttribute;
import org.nasdanika.common.persistence.MarkedArrayList;
import org.nasdanika.common.persistence.MarkedLinkedHashMap;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

/**
 * Loads a list of references using loader.
 * @author Pavel
 *
 */
public class ReferenceList<T> extends ListAttribute<T> {

	private ReferenceFactory referenceFactory;
	private List<String> keys = new ArrayList<>();
	private BiFunction<EClass, ENamedElement, String> keyProvider;
	
	/**
	 * 
	 * @param key
	 * @param isDefault
	 * @param required
	 * @param defaultValue
	 * @param description
	 * @param resolver
	 * @param referenceType Reference type if the reference is homogenous, i.e. its type is known beforehand.
	 * @param exclusiveWith
	 */
	public ReferenceList(
			Object key, 
			boolean isDefault, 
			boolean required, 
			List<T> defaultValue, 
			String description, 
			EClass eClass,
			EReference eReference,
			EObjectLoader resolver,
			boolean referenceSupplierFactory,
			BiFunction<EClass,ENamedElement,String> keyProvider,
			Object... exclusiveWith) {
		super(key, isDefault, required, defaultValue, description, exclusiveWith);
		this.referenceFactory = new ReferenceFactory(eClass, eReference, null, resolver, referenceSupplierFactory, keyProvider);
		this.keyProvider = keyProvider;
		for (EAttribute rKey: eReference.getEKeys()) {
			keys.add(keyProvider.apply(eClass, rKey));
		}
	}
	
	@Override
	public List<T> create(ObjectLoader loader, Object config, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers)	throws Exception {
		if (config instanceof Map) {
			if (!keys.isEmpty()) {
				String valueFeature = EObjectLoader.getValueFeature(referenceFactory.getEReference());
				if (keys.size() == 1) {
					// Converting entry set to a list of maps
					String keyName = keys.get(0);
					MarkedArrayList<Map<?,?>> entryList = new MarkedArrayList<>();
					entryList.mark(markers);
					for (Entry<?, ?> entry: ((Map<?,?>) config).entrySet()) {
						Object value = entry.getValue();
						List<? extends Marker> valueMarkers = null;
						if (config instanceof MarkedLinkedHashMap) {
							valueMarkers = ((MarkedLinkedHashMap<?, ?>) config).getEntryMarkers(entry.getKey());
						}
						
						if (referenceFactory.isHomogenous()) {
							// Config shall be a map with potentially multiple entries, type is already known
							MarkedLinkedHashMap<Object, Object> entryConfig = new MarkedLinkedHashMap<>();
							entryConfig.mark(valueMarkers);
							entryConfig.put(keyName, entry.getKey());
							entryConfig.markEntry(keyName, valueMarkers);
							if (valueFeature == null) {
								if (value instanceof Map) {
									entryConfig.putAll((Map<?,?>) value);
								} else {
									EStructuralFeature defaultFeature = referenceFactory.effectiveDefaultFeature(value);
									if (defaultFeature == null) {
										throw new ConfigurationException("Configuration shall be a map: " + value, valueMarkers);
									}
									entryConfig.put(keyProvider.apply(null, defaultFeature), value);
								}
							} else {
								entryConfig.put(valueFeature, value);
							}
							entryList.add(entryConfig);
						} else {
							// Config (value) must be a singleton map with type as a key. We need to inject things into the value.
							if (value instanceof Map && ((Map<?,?>) value).size() == 1) {
								for (Entry<?, ?> valueEntry: ((Map<?,?>) value).entrySet()) {
									MarkedLinkedHashMap<Object, Object> entryConfig = new MarkedLinkedHashMap<>();
									entryConfig.mark(valueMarkers);
									entryConfig.put(keyName, entry.getKey());
									entryConfig.markEntry(keyName, valueMarkers);
									if (valueFeature == null) {
										Object valueEntryValue = valueEntry.getValue();
										if (valueEntryValue instanceof Map) {
											entryConfig.putAll((Map<?,?>) valueEntryValue);
										} else {
											// TODO - Default feature											
											throw new ConfigurationException("Configuration shall be a map: " + valueEntry.getValue(), valueMarkers); // TODO - marker 
										}
									} else {
										entryConfig.put(valueFeature, valueEntry.getValue());
									}
									
									MarkedLinkedHashMap<Object, Object> singleton = new MarkedLinkedHashMap<>();
									singleton.put(valueEntry.getKey(), entryConfig);
									singleton.mark(valueMarkers);
									singleton.markEntry(valueEntry.getKey(), valueMarkers);
									entryList.add(singleton); 
								}
							} else {
								throw new ConfigurationException("Configuration shall be a singleton map: " + value, valueMarkers);								
							}
						}
						entryList.getElementMarkers().add(valueMarkers);
					}
					return super.create(loader, entryList, base, progressMonitor, markers);			
				} 
				
				throw new UnsupportedOperationException("Multiple e-keys are not supported yet");
			}
		}
		return super.create(loader, config, base, progressMonitor, markers);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected List<T> createElements(ObjectLoader loader, Object element, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers) throws Exception {
		return (List<T>) referenceFactory.create(loader, element, base, progressMonitor, markers);
	}

}
