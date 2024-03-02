/**
 */
package org.nasdanika.ncore;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Date</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents integer number.
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.ncore.NcorePackage#getDate()
 * @model superTypes="org.nasdanika.ncore.ValueObject&lt;org.eclipse.emf.ecore.EDate&gt;"
 * @generated
 */
public interface Date extends ValueObject<java.util.Date> {
	static Date wrap(java.util.Date val) {
		Date ret = NcoreFactory.eINSTANCE.createDate();
		ret.setValue(val);
		return ret;
	}

} // Date
