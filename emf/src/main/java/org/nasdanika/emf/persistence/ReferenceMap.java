package org.nasdanika.emf.persistence;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.persistence.MapAttribute;
import org.nasdanika.persistence.Marker;
import org.nasdanika.persistence.ObjectLoader;

/**
 * Loads {@link EMap} of references using loader.
 * @author Pavel
 *
 */
public class ReferenceMap<K,V> extends MapAttribute<K,V> {

	private ReferenceFactory valueFactory;
	
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
			EClass eClass,
			EReference eReference,
			EObjectLoader resolver,
			boolean referenceSupplierFactory,
			BiFunction<EClass,ENamedElement,String> keyProvider,
			Object... exclusiveWith) {
		super(key, isDefault, required, defaultValue, description, exclusiveWith);
		EStructuralFeature valueFeature = eReference.getEReferenceType().getEStructuralFeature("value");
		if (valueFeature instanceof EReference) {
			this.valueFactory = new ReferenceFactory(eClass, (EReference) valueFeature, eReference.getName(), resolver, referenceSupplierFactory, keyProvider);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected V createValue(ObjectLoader loader, K key, Object value, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers) {
		if (valueFactory == null) {
			return super.createValue(loader, key, value, base, progressMonitor, markers);
		}
		
		List<?> result = valueFactory.create(loader, value, base, progressMonitor, markers);
		if (result.size() == 0) {
			return null;
		}
		
		if (result.size() == 1) {
			return (V) result.get(0);
		}
		throw new NasdanikaException("Expected result size of 0 or 1, got " + result.size() + ":" + result);		
	}
	
}
