package org.nasdanika.emf.persistence;

import java.net.URL;

import org.eclipse.emf.ecore.EClass;
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
			Object... exclusiveWith) {
		super(key, isDefault, required, defaultValue, description, exclusiveWith);
		this.resolver = resolver;
		this.referenceType = referenceType;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T create(ObjectLoader loader, Object element, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		// Strings are references. Always with the type?
		if (element instanceof String) {
			String ref = (String) element;
			URL refURL = new URL(base, ref);
			if (ref.endsWith(".json")) {
				return (T) loader.loadJsonObject(refURL, progressMonitor);
			}
			return (T) loader.loadYaml(refURL, progressMonitor);
		}
		Object ret = loader.load(element, base, progressMonitor);
		if (resolver != null && ret instanceof EObject && ((EObject) ret).eIsProxy()) {
			return (T) resolver.resolve((EObject) ret);
		}
		return (T) ret;
	}

}
