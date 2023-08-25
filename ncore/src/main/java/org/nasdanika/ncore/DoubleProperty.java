/**
 */
package org.nasdanika.ncore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Double Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A named/keyed integer.
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.ncore.NcorePackage#getDoubleProperty()
 * @model
 * @generated
 */
public interface DoubleProperty extends Property, org.nasdanika.ncore.Double {

	static DoubleProperty wrap(java.lang.String name, java.lang.Double val) {
		DoubleProperty ret = NcoreFactory.eINSTANCE.createDoubleProperty();
		ret.setName(name);
		ret.setValue(val);
		return ret;
	}
	
} // DoubleProperty
