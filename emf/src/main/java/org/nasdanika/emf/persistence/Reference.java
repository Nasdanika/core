package org.nasdanika.emf.persistence;

import java.net.URL;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.Attribute;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

/**
 * If config is a list loads each element creating elements using element factory and then loading them, otherwise creates a singleton in the same way as explained before.
 * @author Pavel
 *
 * @param <T>
 */
public class Reference<T> extends Attribute<T> {
	
	private ReferenceFactory<T> referenceFactory;
	
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
			EReference eReference,
			EObjectLoader resolver,
			boolean referenceSupplierFactory,
			java.util.function.Function<ENamedElement,String> keyProvider,
			Object... exclusiveWith) {
		super(key, isDefault, required, defaultValue, description, exclusiveWith);
		this.referenceFactory = new ReferenceFactory<>(eReference, resolver, referenceSupplierFactory, keyProvider);
	}
	
	@Override
	public T create(ObjectLoader loader, Object element, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		return referenceFactory.create(loader, element, base, progressMonitor, marker);
	}

}
