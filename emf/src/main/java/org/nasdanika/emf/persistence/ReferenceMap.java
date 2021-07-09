package org.nasdanika.emf.persistence;

import java.net.URL;
import java.util.Map;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.MapAttribute;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

/**
 * Loads {@link EMap} of references using loader.
 * @author Pavel
 *
 */
public class ReferenceMap<K,V> extends MapAttribute<K,V> {

	private ReferenceFactory<V> valueFactory;
	
	/**
	 * @param key
	 * @param isDefault
	 * @param required
	 * @param defaultValue
	 * @param description
	 * @param resolver
	 * @param referenceType Reference type if the reference is homogenous, i.e. its type is known beforehand.
	 * @param exclusiveWith
	 */
	public ReferenceMap(
			Object key, 
			boolean isDefault, 
			boolean required, 
			Map<K,V> defaultValue, 
			String description, 
			EReference eReference,
			EObjectLoader resolver,
			boolean referenceSupplierFactory,
			java.util.function.Function<ENamedElement,String> keyProvider,
			Object... exclusiveWith) {
		super(key, isDefault, required, defaultValue, description, exclusiveWith);
		EStructuralFeature valueFeature = eReference.getEReferenceType().getEStructuralFeature("value");
		if (valueFeature instanceof EReference) {
			this.valueFactory = new ReferenceFactory<>((EReference) valueFeature, resolver, referenceSupplierFactory, keyProvider);
		}
	}
	
	@Override
	protected V createValue(ObjectLoader loader, K key, Object value, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		return valueFactory == null ? super.createValue(loader, key, value, base, progressMonitor, marker) : valueFactory.create(loader, value, base, progressMonitor, marker);
	}
	
}
