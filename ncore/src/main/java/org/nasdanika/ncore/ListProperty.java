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
		
	static ListProperty wrap(java.lang.String name, Iterable<?> iterable) {
		ListProperty ret = NcoreFactory.eINSTANCE.createListProperty();
		ret.setName(name);
		for (Object element: iterable) {
			if (element instanceof EObject) {
				ret.add((EObject) element);
			} else if (element instanceof Iterable) {
				ret.add(List.wrap((Iterable<?>) element));
			} else if (element instanceof java.util.Map) {
				ret.add(Map.wrap((java.util.Map<?,?>) element));
			} else if (element instanceof java.lang.Boolean) {
				ret.add(Boolean.wrap((java.lang.Boolean) element));
			} else if (element instanceof java.lang.Integer) {
				ret.add(Integer.wrap((java.lang.Integer) element));
			} else if (element instanceof java.lang.Double) {
				ret.add(Double.wrap((java.lang.Double) element));
			} else if (element instanceof java.lang.Long) {
				ret.add(Long.wrap((java.lang.Long) element));
			} else if (element instanceof java.util.Date) {
				ret.add(Date.wrap((java.util.Date) element));
			} else if (element instanceof java.lang.String) {
				ret.add(String.wrap((java.lang.String) element));
			} else if (element != null) {
				throw new IllegalArgumentException("Cannot add " + element.getClass() + " to List. Value: " + element);
			}
		}
		return ret;
	}			
	
} // ListProperty
