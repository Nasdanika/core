/**
 */
package org.nasdanika.exec.content;

import org.nasdanika.ncore.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.exec.content.Resource#getLocation <em>Location</em>}</li>
 *   <li>{@link org.nasdanika.exec.content.Resource#isInterpolate <em>Interpolate</em>}</li>
 *   <li>{@link org.nasdanika.exec.content.Resource#getErrorMessage <em>Error Message</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.exec.content.ContentPackage#getResource()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/content/resource.md'"
 * @generated
 */
public interface Resource extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resource location resolved relative to the model resource location.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see #setLocation(String)
	 * @see org.nasdanika.exec.content.ContentPackage#getResource_Location()
	 * @model required="true"
	 *        annotation="urn:org.nasdanika default-feature='true' resolve-uri='true'"
	 * @generated
	 */
	String getLocation();

	/**
	 * Sets the value of the '{@link org.nasdanika.exec.content.Resource#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(String value);

	/**
	 * Returns the value of the '<em><b>Interpolate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If true, location is interpolated. Default is false.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Interpolate</em>' attribute.
	 * @see #setInterpolate(boolean)
	 * @see org.nasdanika.exec.content.ContentPackage#getResource_Interpolate()
	 * @model
	 * @generated
	 */
	boolean isInterpolate();

	/**
	 * Sets the value of the '{@link org.nasdanika.exec.content.Resource#isInterpolate <em>Interpolate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interpolate</em>' attribute.
	 * @see #isInterpolate()
	 * @generated
	 */
	void setInterpolate(boolean value);

	/**
	 * Returns the value of the '<em><b>Error Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If set, the message content is used as resource content if there is an error loading the resource content. The message is interpolated with uri and exception tokens.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Error Message</em>' attribute.
	 * @see #setErrorMessage(String)
	 * @see org.nasdanika.exec.content.ContentPackage#getResource_ErrorMessage()
	 * @model
	 * @generated
	 */
	String getErrorMessage();

	/**
	 * Sets the value of the '{@link org.nasdanika.exec.content.Resource#getErrorMessage <em>Error Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Error Message</em>' attribute.
	 * @see #getErrorMessage()
	 * @generated
	 */
	void setErrorMessage(String value);

} // Resource
