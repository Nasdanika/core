package org.nasdanika.emf.persistence;

import java.net.URL;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
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

	private EObjectLoader resolver;
	private EClass referenceType;
	private Function<ENamedElement, String> keyProvider;
	private boolean isStrictContainment;

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
	public Reference(Object key, 
			boolean isDefault, 
			boolean required, 
			T defaultValue, 
			String description, 
			EObjectLoader resolver,
			EClass referenceType,
			boolean isStrictContainment,
			java.util.function.Function<ENamedElement,String> keyProvider,
			Object... exclusiveWith) {
		super(key, isDefault, required, defaultValue, description, exclusiveWith);
		this.resolver = resolver;
		this.referenceType = referenceType;
		this.isStrictContainment = isStrictContainment;
		this.keyProvider = keyProvider;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T create(ObjectLoader loader, Object element, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		// Strings are references if not strict containment.
		if (element instanceof String && !isStrictContainment) {
			String ref = (String) element;
			URL refURL = new URL(base, ref);
			return (T) resolver.getResourceSet().getEObject(URI.createURI(refURL.toString()), true);
		}
		Object ret = referenceType == null ? loader.load(element, base, progressMonitor) : resolver.create(loader, referenceType, element, base, progressMonitor, marker, keyProvider);
		if (resolver != null && ret instanceof EObject && ((EObject) ret).eIsProxy()) {
			return (T) resolver.resolve((EObject) ret);
		}
		return (T) ret;
	}

}
