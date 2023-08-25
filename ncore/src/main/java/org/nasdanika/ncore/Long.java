/**
 */
package org.nasdanika.ncore;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Long</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents integer number.
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.ncore.NcorePackage#getLong()
 * @model superTypes="org.nasdanika.ncore.ValueObject&lt;org.eclipse.emf.ecore.ELongObject&gt;"
 * @generated
 */
public interface Long extends ValueObject<java.lang.Long> {
	static Long wrap(java.lang.Long val) {
		Long ret = NcoreFactory.eINSTANCE.createLong();
		ret.setValue(val);
		return ret;
	}	

} // Long
