package org.nasdanika.emf.persistence;

import java.util.List;
import java.util.function.BiFunction;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.Attribute;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

/**
 * Single value reference.
 * @author Pavel
 *
 * @param <T>
 */
public class Reference<T> extends Attribute<T> {
	
	private ReferenceFactory referenceFactory;
	
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
	public Reference(
			Object key, 
			boolean isDefault, 
			boolean required, 
			T defaultValue, 
			String description, 
			EClass eClass,
			EReference eReference,
			EObjectLoader resolver,
			boolean referenceSupplierFactory,
			BiFunction<EClass,ENamedElement,String> keyProvider,
			Object... exclusiveWith) {
		super(key, isDefault, required, defaultValue, description, exclusiveWith);
		this.referenceFactory = new ReferenceFactory(eClass, eReference, null, resolver, referenceSupplierFactory, keyProvider);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T create(ObjectLoader loader, Object element, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers) {
		List<?> result = referenceFactory.create(loader, element, base, progressMonitor, markers);
		if (result == null || result.isEmpty()) {
			return null;
		}
		if (result.size() == 1) {
			return (T) result.get(0);
		}
		throw new NasdanikaException("Expected result size of 0 or 1, got " + result.size() + ":" + result);
	}

}
