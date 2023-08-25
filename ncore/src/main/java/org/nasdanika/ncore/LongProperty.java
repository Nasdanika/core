/**
 */
package org.nasdanika.ncore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Long Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A named/keyed integer.
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.ncore.NcorePackage#getLongProperty()
 * @model
 * @generated
 */
public interface LongProperty extends Property, org.nasdanika.ncore.Long {
	
	static LongProperty wrap(java.lang.String name, java.lang.Long val) {
		LongProperty ret = NcoreFactory.eINSTANCE.createLongProperty();
		ret.setName(name);
		ret.setValue(val);
		return ret;
	}	
	
} // LongProperty
