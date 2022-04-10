/**
 */
package org.nasdanika.ncore;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>String</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Text/string.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.String#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getString()
 * @model
 * @generated
 */
public interface String extends EObject {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * String value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(java.lang.String)
	 * @see org.nasdanika.ncore.NcorePackage#getString_Value()
	 * @model annotation="urn:org.nasdanika default-feature='true'"
	 * @generated
	 */
	java.lang.String getValue();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.String#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(java.lang.String value);

} // String
