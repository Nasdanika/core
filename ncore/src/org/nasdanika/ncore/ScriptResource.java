/**
 */
package org.nasdanika.ncore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Script Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Evaluates script loaded from a resource.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.ScriptResource#getLocation <em>Location</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getScriptResource()
 * @model
 * @generated
 */
public interface ScriptResource extends Script {
	/**
	 * Returns the value of the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Script resource location. The resource location is resolved relative to the model resource.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see #setLocation(String)
	 * @see org.nasdanika.ncore.NcorePackage#getScriptResource_Location()
	 * @model required="true"
	 * @generated
	 */
	String getLocation();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.ScriptResource#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(String value);

} // ScriptResource
