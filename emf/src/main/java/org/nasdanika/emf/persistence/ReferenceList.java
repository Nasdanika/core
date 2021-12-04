package org.nasdanika.emf.persistence;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.ListAttribute;
import org.nasdanika.common.persistence.Marked;
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

	private ReferenceFactory<T> referenceFactory;
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
		this.referenceFactory = new ReferenceFactory<>(eClass, eReference, null, resolver, referenceSupplierFactory, keyProvider);
		this.keyProvider = keyProvider;
		for (EAttribute rKey: eReference.getEKeys()) {
			keys.add(keyProvider.apply(eClass, rKey));
		}
	}
	
	@Override
	public List<T> create(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker)	throws Exception {
		if (config instanceof Map) {
			if (!keys.isEmpty()) {
				String valueFeature = EObjectLoader.getValueFeature(referenceFactory.getEReference());
				if (keys.size() == 1) {
					// Converting entry set to a list of maps
					String keyName = keys.get(0);
					MarkedArrayList<Map<?,?>> entryList = new MarkedArrayList<>();
					if (config instanceof Marked) {
						entryList.setMarker(((Marked) config).getMarker());
					}
					for (Entry<?, ?> entry: ((Map<?,?>) config).entrySet()) {
						Object value = entry.getValue();
						Marker valueMarker = null;
						if (config instanceof MarkedLinkedHashMap) {
							valueMarker = ((MarkedLinkedHashMap<?, ?>) config).getMarker(entry.getKey());
						}
						
						if (referenceFactory.isHomogenous()) {
							// Config shall be a map with potentially multiple entries, type is already known
							MarkedLinkedHashMap<Object, Object> entryConfig = new MarkedLinkedHashMap<>();
							entryConfig.put(keyName, entry.getKey());
							if (valueFeature == null) {
								if (value instanceof Map) {
									entryConfig.putAll((Map<?,?>) value);
									if (value instanceof MarkedLinkedHashMap) {
										MarkedLinkedHashMap<?,?> markedValue = (MarkedLinkedHashMap<?,?>) value;
										entryConfig.setMarker(markedValue.getMarker());
										for (Object key: markedValue.keySet()) {
											entryConfig.mark(keyName, markedValue.getMarker(key));
										}
									}
								} else {
									EStructuralFeature defaultFeature = referenceFactory.effectiveDefaultFeature(value);
									if (defaultFeature == null) {
										throw new ConfigurationException("Configuration shall be a map: " + value, valueMarker);
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
									entryConfig.put(keyName, entry.getKey());
									if (valueFeature == null) {
										if (valueEntry.getValue() instanceof Map) {
											entryConfig.putAll((Map<?,?>) valueEntry.getValue());
											if (valueEntry.getValue() instanceof MarkedLinkedHashMap) {
												MarkedLinkedHashMap<?,?> markedValueEntry = (MarkedLinkedHashMap<?,?>) valueEntry.getValue();
												entryConfig.setMarker(markedValueEntry.getMarker());
												for (Object key: markedValueEntry.keySet()) {
													entryConfig.mark(keyName, markedValueEntry.getMarker(key));
												}
											}
										} else {
											// TODO - Default feature
											throw new ConfigurationException("Configuration shall be a map: " + valueEntry.getValue(), (Marker) null); // TODO - marker 
										}
									} else {
										entryConfig.put(valueFeature, valueEntry.getValue());
									}
									
									MarkedLinkedHashMap<Object, Object> singleton = new MarkedLinkedHashMap<>();
									singleton.put(valueEntry.getKey(), entryConfig);
									entryList.add(singleton); 
								}
							} else {
								throw new ConfigurationException("Configuration shall be a singleton map: " + value, valueMarker);								
							}
						}
						entryList.getMarkers().add(valueMarker);
					}
					return super.create(loader, entryList, base, progressMonitor, marker);			
				} 
				
				throw new UnsupportedOperationException("Multiple e-keys are not supported yet");
			}
		}
		return super.create(loader, config, base, progressMonitor, marker);
	}
	
	@Override
	protected T createElement(ObjectLoader loader, Object element, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		return referenceFactory.create(loader, element, base, progressMonitor, marker);
	}

}
