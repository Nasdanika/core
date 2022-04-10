/**
 */
package org.nasdanika.ncore;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A list of objects.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.List#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getList()
 * @model
 * @generated
 */
public interface List extends EObject {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * List elements.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getList_Value()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika reference-type='map: Map\nlist: List\nstring: String'"
	 * @generated
	 */
	EList<EObject> getValue();
	
	default void add(boolean value) {
		org.nasdanika.ncore.Boolean valueObject = NcoreFactory.eINSTANCE.createBoolean();
		valueObject.setValue(value);
		getValue().add(valueObject);
	}
	
	default void add(int value) {
		org.nasdanika.ncore.Integer valueObject = NcoreFactory.eINSTANCE.createInteger();
		valueObject.setValue(value);
		getValue().add(valueObject);
	}
	
	default void add(java.lang.String value) {
		org.nasdanika.ncore.String valueObject = NcoreFactory.eINSTANCE.createString();
		valueObject.setValue(value);
		getValue().add(valueObject);
	}
	
	default void add(EObject value) {
		getValue().add(value);
	}
	
	default void add(java.util.Map<java.lang.String,Object> map) {
		getValue().add(Map.from(map));
	}
	
	default void add(Iterable<?> iterable) {
		getValue().add(List.from(iterable));
	}
	
	@SuppressWarnings("unchecked")
	static List from(Iterable<?> iterable) {
		List ret = NcoreFactory.eINSTANCE.createList();
		for (Object element: iterable) {
			if (element instanceof Boolean) {
				ret.add((boolean) element);
			} else if (element instanceof EObject) {
				ret.add((EObject) element);
			} else if (element instanceof Integer) {
				ret.add((int) element);
			} else if (element instanceof Iterable) {
				ret.add((Iterable<?>) element);
			} else if (element instanceof java.lang.String) {
				ret.add((java.lang.String) element);
			} else if (element instanceof java.util.Map) {
				ret.add((java.util.Map<java.lang.String,Object>) element);
			} else if (element != null) {
				throw new IllegalArgumentException("Cannot add " + element.getClass() + " to List. Value: " + element);
			}
		}
		return ret;
	}		

} // List
