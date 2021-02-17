package org.nasdanika.emf.persistence;

import java.net.URL;
import java.util.List;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
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

	private EObjectLoader resolver;
	private EClass referenceType;
	private Function<ENamedElement, String> keyProvider;
	private boolean isStrictContainment;
	private ResourceSet resourceSet;

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
	public ReferenceList(Object key, 
			boolean isDefault, 
			boolean required, 
			List<T> defaultValue, 
			String description,
			ResourceSet resourceSet,
			EObjectLoader resolver,
			EClass referenceType,
			boolean isStrictContainment,
			java.util.function.Function<ENamedElement,String> keyProvider,
			Object... exclusiveWith) {
		super(key, isDefault, required, defaultValue, description, exclusiveWith);
		this.resourceSet = resourceSet;
		this.resolver = resolver;
		this.referenceType = referenceType;
		this.isStrictContainment = isStrictContainment;
		this.keyProvider = keyProvider;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected T createElement(ObjectLoader loader, Object element, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		// Strings are references if not strict containment.
		if (element instanceof String && !isStrictContainment) {
			String ref = (String) element;
			URL refURL = new URL(base, ref);
			return (T) resourceSet.getEObject(URI.createURI(refURL.toString()), true);
		}
		Object ret = referenceType == null ? loader.load(element, base, progressMonitor) : resolver.create(loader, referenceType, element, base, progressMonitor, marker, keyProvider);
		if (resolver != null && ret instanceof EObject && ((EObject) ret).eIsProxy()) {
			return (T) resolver.resolve((EObject) ret);
		}
		return (T) ret;
	}

}
