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
						MarkedLinkedHashMap<Object, Object> entryConfig = new MarkedLinkedHashMap<>();
						entryConfig.put(keyName, entry.getKey());
						Object value = entry.getValue();
						if (valueFeature == null) {
							if (value instanceof Map) {
								entryConfig.putAll((Map<?,?>) value); // TODO - markers.
							} else {
								Marker valueMarker = null;
								if (config instanceof MarkedLinkedHashMap) {
									valueMarker = ((MarkedLinkedHashMap<?, ?>) config).getMarker(entry.getKey());
								} else {
									throw new ConfigurationException("Configuration shall be a map: " + value, valueMarker);
								}
							}
						} else {
							entryConfig.put(valueFeature, value);
						}
						entryList.add(entryConfig); // TODO - markers.						
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
