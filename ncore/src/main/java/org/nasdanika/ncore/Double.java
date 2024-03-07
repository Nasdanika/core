/**
 */
package org.nasdanika.ncore;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Double</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents integer number.
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.ncore.NcorePackage#getDouble()
 * @model superTypes="org.nasdanika.ncore.ValueObject&lt;org.eclipse.emf.ecore.EDoubleObject&gt;"
 * @generated
 */
public interface Double extends ValueObject<java.lang.Double> {
	
	static Double wrap(java.lang.Double val) {
		Double ret = NcoreFactory.eINSTANCE.createDouble();
		ret.setValue(val);
		return ret;
	}	
	
} // Double
