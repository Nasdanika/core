/**
 */
package org.nasdanika.ncore;

import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Map Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A named/keyed map.
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.ncore.NcorePackage#getMapProperty()
 * @model
 * @generated
 */
public interface MapProperty extends Property, Map {
		
	@SuppressWarnings("unchecked")
	static MapProperty from(java.util.Map<java.lang.String, Object> map) {
		MapProperty ret = NcoreFactory.eINSTANCE.createMapProperty();
		for (Entry<java.lang.String, Object> entry: map.entrySet()) {
			java.lang.String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof Boolean) {
				ret.put(key, (Boolean) value);
			} else if (value instanceof EObject) {
				ret.put(key, (EObject) value);
			} else if (value instanceof Integer) {
				ret.put(key, (Integer) value);
			} else if (value instanceof Iterable) {
				ret.put(key, (Iterable<?>) value);
			} else if (value instanceof java.lang.String) {
				ret.put(key, (java.lang.String) value);
			} else if (value instanceof java.util.Map) {
				ret.put(key, (java.util.Map<java.lang.String,Object>) value);
			} else if (value != null) {
				throw new IllegalArgumentException("Cannot put " + value.getClass() + " to MapProperty. Value: " + value);
			}
		}
		return ret;
	}	
	
} // MapProperty
