/**
 */
package org.nasdanika.ncore;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Integer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents integer number.
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.ncore.NcorePackage#getInteger()
 * @model superTypes="org.nasdanika.ncore.ValueObject&lt;org.eclipse.emf.ecore.EIntegerObject&gt;"
 * @generated
 */
public interface Integer extends ValueObject<java.lang.Integer> {
	static Integer wrap(java.lang.Integer val) {
		Integer ret = NcoreFactory.eINSTANCE.createInteger();
		ret.setValue(val);
		return ret;
	}	
	
} // Integer
