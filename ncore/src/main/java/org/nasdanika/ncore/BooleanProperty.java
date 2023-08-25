/**
 */
package org.nasdanika.ncore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Boolean Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A named/keyed boolean.
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.ncore.NcorePackage#getBooleanProperty()
 * @model
 * @generated
 */
public interface BooleanProperty extends Property, org.nasdanika.ncore.Boolean {
		
	static BooleanProperty wrap(java.lang.String name, java.lang.Boolean val) {
		BooleanProperty ret = NcoreFactory.eINSTANCE.createBooleanProperty();
		ret.setName(name);
		ret.setValue(val);
		return ret;
	}
	
} // BooleanProperty
