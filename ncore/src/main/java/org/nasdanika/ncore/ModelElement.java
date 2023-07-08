/**
 */
package org.nasdanika.ncore;

import java.lang.String;
import java.util.Iterator;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Adaptable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base class for many Nasdanika model classes.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.ModelElement#getUris <em>Uris</em>}</li>
 *   <li>{@link org.nasdanika.ncore.ModelElement#getDescription <em>Description</em>}</li>
 *   <li>{@link org.nasdanika.ncore.ModelElement#getUuid <em>Uuid</em>}</li>
 *   <li>{@link org.nasdanika.ncore.ModelElement#getLabelPrototype <em>Label Prototype</em>}</li>
 *   <li>{@link org.nasdanika.ncore.ModelElement#getRepresentations <em>Representations</em>}</li>
 *   <li>{@link org.nasdanika.ncore.ModelElement#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.nasdanika.ncore.ModelElement#getAliases <em>Aliases</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getModelElement()
 * @model abstract="true" superTypes="org.nasdanika.ncore.Marked org.nasdanika.ncore.Adaptable"
 * @generated
 */
public interface ModelElement extends Marked, Adaptable {
	/**
	 * Returns the value of the '<em><b>Uris</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Element URI's - there might be more than one. See ``NcoreUtil.getUris()`` for more details.
	 * URI's can be used for cross-referencing of elements in a resource-independent fashion.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Uris</em>' attribute list.
	 * @see org.nasdanika.ncore.NcorePackage#getModelElement_Uris()
	 * @model
	 * @generated
	 */
	EList<String> getUris();

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Description in HTML.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.nasdanika.ncore.NcorePackage#getModelElement_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.ModelElement#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Optional unique identifier for this model element. For root objects UUID is used to compute URI, if the URI is not set.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Uuid</em>' attribute.
	 * @see #setUuid(String)
	 * @see org.nasdanika.ncore.NcorePackage#getModelElement_Uuid()
	 * @model
	 * @generated
	 */
	String getUuid();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.ModelElement#getUuid <em>Uuid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uuid</em>' attribute.
	 * @see #getUuid()
	 * @generated
	 */
	void setUuid(String value);

	/**
	 * Returns the value of the '<em><b>Label Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If this reference is not set then EObjectLabelBuilder creates a new Label of its subclass (Link or Action) using AppFactory in newLabel() method. 
	 * If this reference is set and is and instanceof Label then a copy of the label is created and returned.
	 * Otherwise the reference value it is adapted to LabelProvider which is used to create a label. 
	 * This allows to merge labels and chain label generation. 
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Label Prototype</em>' containment reference.
	 * @see #setLabelPrototype(EObject)
	 * @see org.nasdanika.ncore.NcorePackage#getModelElement_LabelPrototype()
	 * @model containment="true"
	 * @generated
	 */
	EObject getLabelPrototype();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.ModelElement#getLabelPrototype <em>Label Prototype</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label Prototype</em>' containment reference.
	 * @see #getLabelPrototype()
	 * @generated
	 */
	void setLabelPrototype(EObject value);

	/**
	 * Returns the value of the '<em><b>Representations</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Mapping of representation keys to URI's of representation resources. URI's are resolved relative to the model element resource URI.
	 * During object loading resources are loaded and linked to the object. Additional processing depends on the resource type.
	 * In case of Drawio diagrams, diagram elements are semantically mapped to model elements and representation resource root elements are added as children to the object.
	 * See [Nasdanika Core Drawio](../drawio/index.html) and [Nasdanika Core EMF](../emf/index.html) documentation for more information.
	 * 
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Representations</em>' map.
	 * @see org.nasdanika.ncore.NcorePackage#getModelElement_Representations()
	 * @model mapType="org.nasdanika.ncore.RepresentationEntry&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 *        annotation="urn:org.nasdanika homogeneous='true' load-doc-reference='doc/model-element--representations.md'"
	 * @generated
	 */
	EMap<String, String> getRepresentations();

	/**
	 * Returns the value of the '<em><b>Annotations</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.ncore.Property}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Annotations are used to store custom data similar to Java and Ecore annotations. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Annotations</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getModelElement_Annotations()
	 * @model containment="true" keys="name"
	 *        annotation="urn:org.nasdanika reference-type='map: MapProperty\nlist: ListProperty\nstring: StringProperty\ninteger: IntegerProperty' value-feature='value'"
	 * @generated
	 */
	EList<Property> getAnnotations();
	
	/**
	 * Returns the value of the '<em><b>Aliases</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.ncore.ModelElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Two model elements are aliases if they have overlapping URI's.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Aliases</em>' reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getModelElement_Aliases()
	 * @model changeable="false" derived="true"
	 *        annotation="urn:org.nasdanika opposite='aliases'"
	 * @generated
	 */
	EList<ModelElement> getAliases();

	// Annotation convenience methods
	
	default Property getAnnotation(java.lang.String key) {
		if (key == null) {
			return null;
		}
		for (Property property: getAnnotations()) {
			if (key.equals(property.getName())) {
				return property;
			}
		}
		
		return null;
	}
	
	default Property setAnnotation(java.lang.String key, boolean value) {
		BooleanProperty property = NcoreFactory.eINSTANCE.createBooleanProperty();
		property.setName(key);
		property.setValue(value);
		Property ret = null;
		EList<Property> annotations = getAnnotations();
		Iterator<Property> pit = annotations.iterator();
		while (pit.hasNext()) {
			Property next = pit.next();			
			if (key.equals(next.getName())) {
				pit.remove();
				ret = next;
				break;
			}
		}
		annotations.add(property);
		return ret;
	}
	
	default Property setAnnotation(java.lang.String key, int value) {
		IntegerProperty property = NcoreFactory.eINSTANCE.createIntegerProperty();
		property.setName(key);
		property.setValue(value);
		Property ret = null;
		EList<Property> annotations = getAnnotations();
		Iterator<Property> pit = annotations.iterator();
		while (pit.hasNext()) {
			Property next = pit.next();			
			if (key.equals(next.getName())) {
				pit.remove();
				ret = next;
				break;
			}
		}
		annotations.add(property);
		return ret;
	}
	
	default Property setAnnotation(java.lang.String key, java.lang.String value) {
		StringProperty property = NcoreFactory.eINSTANCE.createStringProperty();
		property.setName(key);
		property.setValue(value);
		Property ret = null;
		EList<Property> annotations = getAnnotations();
		Iterator<Property> pit = annotations.iterator();
		while (pit.hasNext()) {
			Property next = pit.next();			
			if (key.equals(next.getName())) {
				pit.remove();
				ret = next;
				break;
			}
		}
		annotations.add(property);
		return ret;
	}
	
	default Property setAnnotation(java.lang.String key, EObject value) {
		Property ret = null;
		EList<Property> annotations = getAnnotations();
		Iterator<Property> pit = annotations.iterator();
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
			annotations.add(property);
		}
		return ret;
	}
		
	default Property setAnnotation(java.lang.String key, java.util.Map<java.lang.String, ?> value) {
		Property ret = null;
		EList<Property> annotations = getAnnotations();
		Iterator<Property> pit = annotations.iterator();
		while (pit.hasNext()) {
			Property next = pit.next();			
			if (key.equals(next.getName())) {
				pit.remove();
				ret = next;
				break;
			}
		}
		if (value != null) {
			MapProperty property = MapProperty.from(value);
			property.setName(key);
			annotations.add(property);
		}
		return ret;
	}
	
	default Property setAnnotation(java.lang.String key, Iterable<?> value) {
		Property ret = null;
		EList<Property> annotations = getAnnotations();
		Iterator<Property> pit = annotations.iterator();
		while (pit.hasNext()) {
			Property next = pit.next();			
			if (key.equals(next.getName())) {
				pit.remove();
				ret = next;
				break;
			}
		}
		if (value != null) {
			ListProperty property = ListProperty.from(value);;
			property.setName(key);
			annotations.add(property);
		}
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	default void setAnnotations(java.util.Map<java.lang.String, ?> map) {
		for (Entry<java.lang.String, ?> entry: map.entrySet()) {
			java.lang.String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof Boolean) {
				setAnnotation(key, (Boolean) value);
			} else if (value instanceof EObject) {
				setAnnotation(key, (EObject) value);
			} else if (value instanceof Integer) {
				setAnnotation(key, (Integer) value);
			} else if (value instanceof Iterable) {
				setAnnotation(key, (Iterable<?>) value);
			} else if (value instanceof java.lang.String) {
				setAnnotation(key, (java.lang.String) value);
			} else if (value instanceof java.util.Map) {
				setAnnotation(key, (java.util.Map<java.lang.String,Object>) value);
			} else if (value != null) {
				throw new IllegalArgumentException("Cannot set annotation of type " + value.getClass() + " and value: " + value);
			}
		}
	}	

} // ModelElement
