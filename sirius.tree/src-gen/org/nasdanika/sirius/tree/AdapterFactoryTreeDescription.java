/**
 */
package org.nasdanika.sirius.tree;

import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Adapter Factory Tree Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.sirius.tree.AdapterFactoryTreeDescription#getEditorId <em>Editor Id</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.sirius.tree.TreePackage#getAdapterFactoryTreeDescription()
 * @model
 * @generated
 */
public interface AdapterFactoryTreeDescription extends RepresentationDescription {

	/**
	 * Returns the value of the '<em><b>Editor Id</b></em>' attribute.
	 * The default value is <code>"org.nasdanika.emf.presentation.AdapterFactoryTreeEditor.ID"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Editor Id</em>' attribute.
	 * @see #setEditorId(String)
	 * @see org.nasdanika.sirius.tree.TreePackage#getAdapterFactoryTreeDescription_EditorId()
	 * @model default="org.nasdanika.emf.presentation.AdapterFactoryTreeEditor.ID"
	 * @generated
	 */
	String getEditorId();

	/**
	 * Sets the value of the '{@link org.nasdanika.sirius.tree.AdapterFactoryTreeDescription#getEditorId <em>Editor Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Editor Id</em>' attribute.
	 * @see #getEditorId()
	 * @generated
	 */
	void setEditorId(String value);
} // AdapterFactoryTreeDescription
