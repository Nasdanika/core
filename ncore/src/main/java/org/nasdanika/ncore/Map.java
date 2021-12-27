/**
 */
package org.nasdanika.ncore;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Map</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.Map#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getMap()
 * @model
 * @generated
 */
public interface Map extends EObject {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.ncore.Property}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getMap_Value()
	 * @model containment="true" keys="name"
	 *        annotation="urn:org.nasdanika reference-type='map: MapProperty\nlist: ListProperty\nstring: StringProperty\ninteger: IntegerProperty' value-feature='value'"
	 * @generated
	 */
	EList<Property> getValue();
	
	default Property get(java.lang.String key) {
		if (key == null) {
			return null;
		}
		for (Property property: getValue()) {
			if (key.equals(property.getName())) {
				return property;
			}
		}
		
		return null;
	}
	
	default Property put(java.lang.String key, boolean value) {
		BooleanProperty property = NcoreFactory.eINSTANCE.createBooleanProperty();
		property.setName(key);
		property.setValue(value);
		Property ret = null;
		Iterator<Property> pit = getValue().iterator();
		while (pit.hasNext()) {
			Property next = pit.next();			
			if (key.equals(next.getName())) {
				pit.remove();
				ret = next;
				break;
			}
		}
		getValue().add(property);
		return ret;
	}
	
	default Property put(java.lang.String key, int value) {
		IntegerProperty property = NcoreFactory.eINSTANCE.createIntegerProperty();
		property.setName(key);
		property.setValue(value);
		Property ret = null;
		Iterator<Property> pit = getValue().iterator();
		while (pit.hasNext()) {
			Property next = pit.next();			
			if (key.equals(next.getName())) {
				pit.remove();
				ret = next;
				break;
			}
		}
		getValue().add(property);
		return ret;
	}
	
	default Property put(java.lang.String key, java.lang.String value) {
		StringProperty property = NcoreFactory.eINSTANCE.createStringProperty();
		property.setName(key);
		property.setValue(value);
		Property ret = null;
		Iterator<Property> pit = getValue().iterator();
		while (pit.hasNext()) {
			Property next = pit.next();			
			if (key.equals(next.getName())) {
				pit.remove();
				ret = next;
				break;
			}
		}
		getValue().add(property);
		return ret;
	}
	
	default Property put(java.lang.String key, EObject value) {
		Property ret = null;
		Iterator<Property> pit = getValue().iterator();
		while (pit.hasNext()) {
			Property next = pit.next();			
			if (key.equals(next.getName())) {
				pit.remove();
				ret = next;
				break;
			}
		}
		if (value != null) {
			EObjectProperty property = NcoreFactory.eINSTANCE.createEObjectProperty();
			property.setName(key);
			property.setValue(value);
			getValue().add(property);
		}
		return ret;
	}

} // Map
