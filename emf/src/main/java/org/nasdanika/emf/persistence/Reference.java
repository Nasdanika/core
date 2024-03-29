package org.nasdanika.emf.persistence;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.persistence.Attribute;
import org.nasdanika.persistence.Marker;
import org.nasdanika.persistence.ObjectLoader;

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
	 * @param referenceType Reference type if the reference is homogeneous, i.e. its type is known beforehand.
	 * @param exclusiveWith
	 */
	public Reference(
			Object key, 
			boolean isDefault, 
			boolean isConstructor, 
			boolean required, 
			T defaultValue, 
			String description, 
			EClass eClass,
			EReference eReference,
			EObjectLoader resolver,
			boolean referenceSupplierFactory,
			BiFunction<EClass,ENamedElement,String> keyProvider,
			Object... exclusiveWith) {
		super(key, isDefault, isConstructor, required, defaultValue, description, exclusiveWith);
		this.referenceFactory = new ReferenceFactory(eClass, eReference, null, resolver, referenceSupplierFactory, keyProvider);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T create(
			ObjectLoader loader, 
			Object element, 
			URI base,
			BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> resolver, 
			Collection<? extends Marker> markers,
			ProgressMonitor progressMonitor) {
		List<?> result = referenceFactory.create(loader, element, base, resolver, markers, progressMonitor);
		if (result == null || result.isEmpty()) {
			return null;
		}
		if (result.size() == 1) {
			return (T) result.get(0);
		}
		throw new NasdanikaException("Expected result size of 0 or 1, got " + result.size() + ":" + result);
	}

}
