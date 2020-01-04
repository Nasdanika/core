/**
 */
package org.nasdanika.ncore;

import org.nasdanika.common.SupplierFactory;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entry</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Command factory with an name.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.Entry#isEnabled <em>Enabled</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getEntry()
 * @model interface="true" abstract="true" superTypes="org.nasdanika.ncore.NamedElement org.nasdanika.ncore.ISupplierFactory&lt;T&gt;"
 * @generated
 */
public interface Entry<T> extends NamedElement, SupplierFactory<T> {

	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If this attribute is set to ``false`` the entry is "commented out" - not included into its container result.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #setEnabled(boolean)
	 * @see org.nasdanika.ncore.NcorePackage#getEntry_Enabled()
	 * @model default="true"
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.Entry#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);
} // Entry
