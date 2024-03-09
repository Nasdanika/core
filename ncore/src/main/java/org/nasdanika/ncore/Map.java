/**
 */
package org.nasdanika.ncore;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.persistence.MarkedLinkedHashMap;

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
public interface Map extends Marked {
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
		return put(key, value, null);
	}
	
	private Property put(java.lang.String key, long value, Iterable<? extends org.nasdanika.persistence.Marker> markers) {
		LongProperty property = NcoreFactory.eINSTANCE.createLongProperty();
		property.mark(markers);
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
		return put(key, value, null);
	}
	
	private Property put(java.lang.String key, double value, Iterable<? extends org.nasdanika.persistence.Marker> markers) {
		DoubleProperty property = NcoreFactory.eINSTANCE.createDoubleProperty();
		property.mark(markers);
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
		return put(key, value, null);
	}
	
	private Property put(java.lang.String key, java.util.Date value, Iterable<? extends org.nasdanika.persistence.Marker> markers) {
		DateProperty property = NcoreFactory.eINSTANCE.createDateProperty();
		property.mark(markers);
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
		return put(key, value, null);
	}
	
	private Property put(java.lang.String key, boolean value, Iterable<? extends org.nasdanika.persistence.Marker> markers) {
		BooleanProperty property = NcoreFactory.eINSTANCE.createBooleanProperty();
		property.mark(markers);
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
		return put(key, value, null);
	}
	
	private Property put(java.lang.String key, int value, Iterable<? extends org.nasdanika.persistence.Marker> markers) {
		IntegerProperty property = NcoreFactory.eINSTANCE.createIntegerProperty();
		property.mark(markers);
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
		return put(key, value, null);
	}
	
	private Property put(java.lang.String key, java.lang.String value, Iterable<? extends org.nasdanika.persistence.Marker> markers) {
		StringProperty property = NcoreFactory.eINSTANCE.createStringProperty();
		property.mark(markers);
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
		return put(key, value, null);
	}
	
	private Property put(java.lang.String key, EObject value, Iterable<? extends org.nasdanika.persistence.Marker> markers) {
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
			property.mark(markers);
			property.setName(key);
			property.setValue(value);			
			getValue().add(property);
		}
		return ret;
	}
		
	default Property put(java.lang.String key, java.util.Map<java.lang.String, Object> value) {
		return put(key, value, null);
	}
	
	private Property put(java.lang.String key, java.util.Map<java.lang.String, Object> value, Iterable<? extends org.nasdanika.persistence.Marker> markers) {
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
			property.mark(markers);
			property.setName(key);
			getValue().add(property);
		}
		return ret;
	}
	
	default Property put(java.lang.String key, Iterable<?> value) {
		return put(key, value, null);
	}
	
	private Property put(java.lang.String key, Iterable<?> value, Iterable<? extends org.nasdanika.persistence.Marker> markers) {
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
			property.mark(markers);
			property.setName(key);
			getValue().add(property);
		}
		return ret;
	}
	
	default Property put(java.lang.String key, Object value) {
		return put(key, value, null);
	}
	
	@SuppressWarnings("unchecked")
	private Property put(java.lang.String key, Object value, Iterable<? extends org.nasdanika.persistence.Marker> entryMarkers) {
		if (value instanceof java.lang.Boolean) {
			return put(key, ((java.lang.Boolean) value).booleanValue(), entryMarkers);
		} 
		if (value instanceof EObject) {
			return put(key, (EObject) value, entryMarkers);
		} 
		if (value instanceof java.lang.Integer) {
			return put(key, ((java.lang.Integer) value).intValue(), entryMarkers);
		} 
		if (value instanceof java.lang.Long) {
			return put(key, ((java.lang.Long) value).longValue(), entryMarkers);
		} 
		if (value instanceof java.lang.Double) {
			return put(key, ((java.lang.Double) value).doubleValue(), entryMarkers);
		} 
		if (value instanceof java.util.Date) {
			return put(key, (java.util.Date) value, entryMarkers);
		} 
		if (value instanceof Iterable) {
			return put(key, (Iterable<?>) value, entryMarkers);
		} 
		if (value instanceof java.lang.String) {
			return put(key, (java.lang.String) value, entryMarkers);
		} 
		if (value instanceof java.util.Map) {
			return put(key, (java.util.Map<java.lang.String,Object>) value, entryMarkers);
		} 
		
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
			throw new IllegalArgumentException("Cannot put " + value.getClass() + " to Map. Value: " + value);
		}
		return ret;
	}
	
	static Map wrap(java.util.Map<?, ?> map) {
		Map ret = NcoreFactory.eINSTANCE.createMap();
		ret.mark(map);
		for (Entry<?, ?> entry: map.entrySet()) {
			Object key = entry.getKey();
			java.lang.String keyString = key == null || key instanceof String ? (java.lang.String) key : DefaultConverter.INSTANCE.toString(key);
			Iterable<? extends org.nasdanika.persistence.Marker> entryMarkers = null;
			if (map instanceof MarkedLinkedHashMap) {
				entryMarkers = ((MarkedLinkedHashMap<?, ?>) map).getEntryMarkers(key);
			}
			ret.put(keyString, entry.getValue(), entryMarkers);
		}
		return ret;
	}	
	
	default java.util.Map<java.lang.String, ?> toMap() {
		java.util.Map<java.lang.String, Object> ret = new LinkedHashMap<>();
		for (EObject e: getValue()) {
			if (e instanceof Property) {
				java.lang.String name = ((Property) e).getName();
				if (e instanceof ListProperty) {
					ret.put(name, ((ListProperty) e).toList());
				} else if (e instanceof MapProperty) {
					ret.put(name, ((MapProperty) e).toMap());
				} else if (e instanceof ValueObjectProperty) {
					ret.put(name, ((ValueObjectProperty<?>) e).getValue());
				} else {
					throw new UnsupportedOperationException("Unsupported property type: " + e); 
				}
			}
		}
		return ret;
		
	}

} // Map
