/**
 */
package org.nasdanika.ncore;

import java.lang.Long;


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
 * @model superTypes="org.nasdanika.ncore.Long org.nasdanika.ncore.ValueObjectProperty&lt;org.eclipse.emf.ecore.ELongObject&gt;"
 * @generated
 */
public interface LongProperty extends org.nasdanika.ncore.Long, ValueObjectProperty<Long> {
	
	static LongProperty wrap(java.lang.String name, java.lang.Long val) {
		LongProperty ret = NcoreFactory.eINSTANCE.createLongProperty();
		ret.setName(name);
		ret.setValue(val);
		return ret;
	}	
	
} // LongProperty
