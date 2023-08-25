/**
 */
package org.nasdanika.ncore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Integer Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A named/keyed integer.
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.ncore.NcorePackage#getIntegerProperty()
 * @model
 * @generated
 */
public interface IntegerProperty extends Property, org.nasdanika.ncore.Integer {

	static IntegerProperty wrap(java.lang.String name, java.lang.Integer val) {
		IntegerProperty ret = NcoreFactory.eINSTANCE.createIntegerProperty();
		ret.setName(name);
		ret.setValue(val);
		return ret;
	}	
	
} // IntegerProperty
