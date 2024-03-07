/**
 */
package org.nasdanika.ncore;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>String</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Text/string.
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.ncore.NcorePackage#getString()
 * @model superTypes="org.nasdanika.ncore.ValueObject&lt;org.eclipse.emf.ecore.EString&gt;"
 * @generated
 */
public interface String extends ValueObject<java.lang.String> {
	
	static String wrap(java.lang.String val) {
		String ret = NcoreFactory.eINSTANCE.createString();
		ret.setValue(val);
		return ret;
	}		
	
} // String
