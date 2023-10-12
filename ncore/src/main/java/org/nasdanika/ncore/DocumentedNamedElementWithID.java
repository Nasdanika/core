/**
 */
package org.nasdanika.ncore;

import java.lang.String;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Documented Named Element With ID</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.DocumentedNamedElementWithID#getId <em>Id</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getDocumentedNamedElementWithID()
 * @model
 * @generated
 */
public interface DocumentedNamedElementWithID extends DocumentedNamedElement {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Element ID used as eKey in containment references
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.nasdanika.ncore.NcorePackage#getDocumentedNamedElementWithID_Id()
	 * @model required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.DocumentedNamedElementWithID#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // DocumentedNamedElementWithID
