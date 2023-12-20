/**
 */
package org.nasdanika.drawio.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tag</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.Tag#getName <em>Name</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.Tag#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.drawio.model.ModelPackage#getTag()
 * @model
 * @generated
 */
public interface Tag extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.nasdanika.drawio.model.ModelPackage#getTag_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.Tag#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Elements</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.drawio.model.ModelElement#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' reference.
	 * @see #setElements(ModelElement)
	 * @see org.nasdanika.drawio.model.ModelPackage#getTag_Elements()
	 * @see org.nasdanika.drawio.model.ModelElement#getTags
	 * @model opposite="tags"
	 * @generated
	 */
	ModelElement getElements();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.Tag#getElements <em>Elements</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Elements</em>' reference.
	 * @see #getElements()
	 * @generated
	 */
	void setElements(ModelElement value);

} // Tag
