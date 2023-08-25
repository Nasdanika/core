/**
 */
package org.nasdanika.ncore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>String Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A named/keyed string.
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.ncore.NcorePackage#getStringProperty()
 * @model
 * @generated
 */
public interface StringProperty extends Property, org.nasdanika.ncore.String {

	static StringProperty wrap(java.lang.String name, java.lang.String val) {
		StringProperty ret = NcoreFactory.eINSTANCE.createStringProperty();
		ret.setName(name);
		ret.setValue(val);
		return ret;
	}	

} // StringProperty
