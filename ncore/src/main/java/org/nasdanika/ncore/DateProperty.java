/**
 */
package org.nasdanika.ncore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Date Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A named/keyed integer.
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.ncore.NcorePackage#getDateProperty()
 * @model
 * @generated
 */
public interface DateProperty extends Property, Date {
	
	static DateProperty wrap(java.lang.String name, java.util.Date val) {
		DateProperty ret = NcoreFactory.eINSTANCE.createDateProperty();
		ret.setName(name);
		ret.setValue(val);
		return ret;
	}	

} // DateProperty
