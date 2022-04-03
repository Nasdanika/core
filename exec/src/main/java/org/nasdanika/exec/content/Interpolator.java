/**
 */
package org.nasdanika.exec.content;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interpolator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Converts input stream to String using the context charset, which defaults to ``UTF-8``, interpolates, and converts back to input stream using the context charset.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.exec.content.Interpolator#isProcessIncludes <em>Process Includes</em>}</li>
 *   <li>{@link org.nasdanika.exec.content.Interpolator#getBase <em>Base</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.exec.content.ContentPackage#getInterpolator()
 * @model annotation="urn:org.nasdanika load-doc-reference='doc/content/interpolator-load-doc.md'"
 * @generated
 */
public interface Interpolator extends Filter {

	/**
	 * Returns the value of the '<em><b>Process Includes</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process Includes</em>' attribute.
	 * @see #setProcessIncludes(boolean)
	 * @see org.nasdanika.exec.content.ContentPackage#getInterpolator_ProcessIncludes()
	 * @model default="true"
	 *        annotation="urn:org.nasdanika documentation-reference='doc/content/interpolator--process-includes.md'"
	 * @generated
	 */
	boolean isProcessIncludes();

	/**
	 * Sets the value of the '{@link org.nasdanika.exec.content.Interpolator#isProcessIncludes <em>Process Includes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Process Includes</em>' attribute.
	 * @see #isProcessIncludes()
	 * @generated
	 */
	void setProcessIncludes(boolean value);

	/**
	 * Returns the value of the '<em><b>Base</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Optional base URL for resolving include and image URL's. 
	 * If the base is blank then marker location is used as the base, if marker is present and location is not null. 
	 * Otherwise, resource URL is used as the base. 
	 * Marker location is the URL of a YAML resource from which interpolator specification was loaded. 
	 * It may be different from the resource URL if a model was loaded from YAML and then saved to XML.
	 * 
	 * If the base is not blank then it is resolved relative to the marker location if it starts with ``./`` and marker location is not null or the resource base otherwise.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Base</em>' attribute.
	 * @see #setBase(String)
	 * @see org.nasdanika.exec.content.ContentPackage#getInterpolator_Base()
	 * @model default=""
	 *        annotation="urn:org.nasdanika resolve-uri='true'"
	 * @generated
	 */
	String getBase();

	/**
	 * Sets the value of the '{@link org.nasdanika.exec.content.Interpolator#getBase <em>Base</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base</em>' attribute.
	 * @see #getBase()
	 * @generated
	 */
	void setBase(String value);
} // Interpolator
