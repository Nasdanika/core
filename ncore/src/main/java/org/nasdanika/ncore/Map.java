/**
 */
package org.nasdanika.ncore;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Map</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A set of key-value pairs.
 * <!-- end-model-doc -->
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
	 * <!-- begin-model-doc -->
	 * Map entries
	 * <!-- end-model-doc -->
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
	
	default Property put(java.lang.String key, long value) {
		LongProperty property = NcoreFactory.eINSTANCE.createLongProperty();
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
	
	default Property put(java.lang.String key, double value) {
		DoubleProperty property = NcoreFactory.eINSTANCE.createDoubleProperty();
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
	
	default Property put(java.lang.String key, java.util.Date value) {
		DateProperty property = NcoreFactory.eINSTANCE.createDateProperty();
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
		
	default Property put(java.lang.String key, java.util.Map<java.lang.String, Object> value) {
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
			MapProperty property = MapProperty.wrap(key, value);
			property.setName(key);
			getValue().add(property);
		}
		return ret;
	}
	
	default Property put(java.lang.String key, Iterable<?> value) {
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
			ListProperty property = ListProperty.wrap(key, value);;
			property.setName(key);
			getValue().add(property);
		}
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	static Map wrap(java.util.Map<java.lang.String, ?> map) {
		Map ret = NcoreFactory.eINSTANCE.createMap();
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
	
	default java.util.Map<java.lang.String, ?> toMap() {
		java.util.Map<java.lang.String, Object> ret = new LinkedHashMap<>();
		for (EObject e: getValue()) {
			if (e instanceof ListProperty) {
				ret.add(((ListProperty) e).toList());
			} else if (e instanceof MapProperty) {
				ret.add(((MapProperty) e).toMap());
			} else if (e instanceof BooleanProperty) {
				ret.add(((Boolean) e).isValue());
			} else if (e instanceof DateProperty) {
				ret.add(((DateProperty) e).getValue());
			} else if (e instanceof DoubleProperty) {
				ret.add(((DoubleProperty) e).getValue());
			} else if (e instanceof IntegerProperty) {
				ret.add(((IntegerProperty) e).getValue());
			} else if (e instanceof LongProperty) {
				ret.add(((LongProperty) e).getValue());
			} else if (e instanceof StringProperty) {
				ret.add(((StringProperty) e).getValue());
			}
		}
		return ret;
		
	}

} // Map
