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
	static MapProperty wrap(java.lang.String name, java.util.Map<java.lang.String, ?> map) {
		MapProperty ret = NcoreFactory.eINSTANCE.createMapProperty();
		ret.setName(name);
		for (Entry<java.lang.String, ?> entry: map.entrySet()) {
			java.lang.String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof java.lang.Boolean) {
				ret.put(key, (java.lang.Boolean) value);
			} else if (value instanceof EObject) {
				ret.put(key, (EObject) value);
			} else if (value instanceof java.lang.Integer) {
				ret.put(key, (java.lang.Integer) value);
			} else if (value instanceof java.lang.Long) {
				ret.put(key, (java.lang.Long) value);
			} else if (value instanceof java.lang.Double) {
				ret.put(key, (java.lang.Double) value);
			} else if (value instanceof java.util.Date) {
				ret.put(key, (java.util.Date) value);
			} else if (value instanceof Iterable) {
				ret.put(key, (Iterable<?>) value);
			} else if (value instanceof java.lang.String) {
				ret.put(key, (java.lang.String) value);
			} else if (value instanceof java.util.Map) {
				ret.put(key, (java.util.Map<java.lang.String,Object>) value);
			} else if (value != null) {
				throw new IllegalArgumentException("Cannot put " + value.getClass() + " to Map. Value: " + value);
			}
		}
		return ret;
	}	
	
} // MapProperty
