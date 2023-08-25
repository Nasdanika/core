/**
 */
package org.nasdanika.ncore;

import java.lang.Boolean;


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
 * @model superTypes="org.nasdanika.ncore.Boolean org.nasdanika.ncore.ValueObjectProperty&lt;org.eclipse.emf.ecore.EBooleanObject&gt;"
 * @generated
 */
public interface BooleanProperty extends org.nasdanika.ncore.Boolean, ValueObjectProperty<Boolean> {
		
	static BooleanProperty wrap(java.lang.String name, java.lang.Boolean val) {
		BooleanProperty ret = NcoreFactory.eINSTANCE.createBooleanProperty();
		ret.setName(name);
		ret.setValue(val);
		return ret;
	}
	
} // BooleanProperty
