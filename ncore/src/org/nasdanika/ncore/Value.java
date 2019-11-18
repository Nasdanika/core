/**
 */
package org.nasdanika.ncore;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Value computes its result from text. 
 * 
 * Value implementation can be defined as follows:
 * 
 * * Fully qualified class name, e.g. ``java.lang.Integer``. An instance of the implementation class is constructed using a contructor which takes ``org.nasdanika.common.Context`` and ``java.lang.String``, just ``java.lang.String``.
 * * Method reference using ``::`` as a separator between the fully qualified class name and the method name. This definition can be used if the type is a functional interface with a single method. If the method is not static then an instance of the implementation class is constructed using a contructor which takes ``org.nasdanika.common.Context`` and ``java.lang.String``, or just ``java.lang.String``.
 * * Provider reference using ``->`` as a separator between the fully qualified class name and the provider method. If the method is static then it shall take Context and String or just String. Otherwise an instance of the implementation class is constructed using a contructor which takes ``org.nasdanika.common.Context`` and ``java.lang.String``, or just ``java.lang.String``.
 * 
 * When implementation is specified, value is equivalent to an operation with a single String argument.
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
	 * Textual representation of the value. If interpolate is ``true`` then the value is interpolated in the context. If type and implementation are empty value is returned as is. 
	 * If type is specified and the result is not of that type, then the result is converted to the type using the context converter service.
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
