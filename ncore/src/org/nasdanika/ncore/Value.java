/**
 */
package org.nasdanika.ncore;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Value computes its result from text. If factory is empty then computation result is the value text interpolated if ``interpolate`` is set to true.
 * Otherwise an adapter created by the factory provides value result.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.Value#getValue <em>Value</em>}</li>
 *   <li>{@link org.nasdanika.ncore.Value#isInterpolate <em>Interpolate</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getValue()
 * @model
 * @generated
 */
public interface Value extends Supplier {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Textual representation of the value. If interpolate is ``true`` then the value is interpolated in the context.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.nasdanika.ncore.NcorePackage#getValue_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.Value#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Interpolate</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If ``true`` (default) the value is interpolated.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Interpolate</em>' attribute.
	 * @see #setInterpolate(boolean)
	 * @see org.nasdanika.ncore.NcorePackage#getValue_Interpolate()
	 * @model default="true"
	 * @generated
	 */
	boolean isInterpolate();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.Value#isInterpolate <em>Interpolate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interpolate</em>' attribute.
	 * @see #isInterpolate()
	 * @generated
	 */
	void setInterpolate(boolean value);

} // Value
