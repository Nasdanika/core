package org.nasdanika.emf.persistence;

import java.net.URL;
import java.util.List;
import java.util.function.BiFunction;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.ListAttribute;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

/**
 * Loads a list of references using loader.
 * @author Pavel
 *
 */
public class ReferenceList<T> extends ListAttribute<T> {

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
	}
	
	@Override
	protected T createElement(ObjectLoader loader, Object element, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		return referenceFactory.create(loader, element, base, progressMonitor, marker);
	}

}
