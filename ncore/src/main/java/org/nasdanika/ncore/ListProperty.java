/**
 */
package org.nasdanika.ncore;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>List Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A named/keyed list.
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.ncore.NcorePackage#getListProperty()
 * @model
 * @generated
 */
public interface ListProperty extends Property, List {
		
	@SuppressWarnings("unchecked")
	static ListProperty wrap(java.lang.String name, Iterable<?> iterable) {
		ListProperty ret = NcoreFactory.eINSTANCE.createListProperty();
		ret.setName(name);
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
				ret.add((java.util.Map<Object,Object>) element);
			} else if (element != null) {
				throw new IllegalArgumentException("Cannot add " + element.getClass() + " to ListProperty. Value: " + element);
			}
		}
		return ret;
	}			
	
} // ListProperty
