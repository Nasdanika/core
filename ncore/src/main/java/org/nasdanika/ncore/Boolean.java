/**
 */
package org.nasdanika.ncore;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Boolean</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents boolean.
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.ncore.NcorePackage#getBoolean()
 * @model superTypes="org.nasdanika.ncore.ValueObject&lt;org.eclipse.emf.ecore.EBooleanObject&gt;"
 * @generated
 */
public interface Boolean extends ValueObject<java.lang.Boolean> {
	static Boolean wrap(java.lang.Boolean val) {
		Boolean ret = NcoreFactory.eINSTANCE.createBoolean();
		ret.setValue(val);
		return ret;
	}

} // Boolean
