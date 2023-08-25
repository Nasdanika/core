/**
 */
package org.nasdanika.ncore;

import java.lang.String;


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
 * @model superTypes="org.nasdanika.ncore.String org.nasdanika.ncore.ValueObjectProperty&lt;org.eclipse.emf.ecore.EString&gt;"
 * @generated
 */
public interface StringProperty extends org.nasdanika.ncore.String, ValueObjectProperty<String> {

	static StringProperty wrap(java.lang.String name, java.lang.String val) {
		StringProperty ret = NcoreFactory.eINSTANCE.createStringProperty();
		ret.setName(name);
		ret.setValue(val);
		return ret;
	}	

} // StringProperty
