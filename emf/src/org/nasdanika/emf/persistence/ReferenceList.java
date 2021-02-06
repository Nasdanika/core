package org.nasdanika.emf.persistence;

import java.net.URL;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
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
			EObjectLoader resolver,
			EClass referenceType,
			Object... exclusiveWith) {
		super(key, isDefault, required, defaultValue, description, exclusiveWith);
		this.resolver = resolver;
		this.referenceType = referenceType;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected T createElement(ObjectLoader loader, Object element, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
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
