/**
 */
package org.nasdanika.ncore;

import org.eclipse.emf.common.util.URI;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.ModelElement#getUri <em>Uri</em>}</li>
 *   <li>{@link org.nasdanika.ncore.ModelElement#getDescription <em>Description</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getModelElement()
 * @model abstract="true"
 *        annotation="urn:org.nasdanika documentation-reference='doc/model-element.md'"
 * @generated
 */
public interface ModelElement extends Marked {
	/**
	 * Returns the value of the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If element's URI is not set then its default value is derived from the container URI and containment reference. 
	 * This is a logical URI and it can be used for cross-referencing of elements in a resource-independent fashion.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Uri</em>' attribute.
	 * @see #setUri(URI)
	 * @see org.nasdanika.ncore.NcorePackage#getModelElement_Uri()
	 * @model dataType="org.nasdanika.ncore.URI"
	 * @generated
	 */
	URI getUri();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.ModelElement#getUri <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uri</em>' attribute.
	 * @see #getUri()
	 * @generated
	 */
	void setUri(URI value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Description in HTML.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.nasdanika.ncore.NcorePackage#getModelElement_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.ModelElement#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

} // ModelElement
