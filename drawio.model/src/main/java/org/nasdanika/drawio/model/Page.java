/**
 */
package org.nasdanika.drawio.model;

import org.eclipse.emf.common.util.EList;
import org.nasdanika.ncore.Marked;
import org.nasdanika.ncore.StringIdentity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Page</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.Page#getModel <em>Model</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.Page#getName <em>Name</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.Page#getLinks <em>Links</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.drawio.model.ModelPackage#getPage()
 * @model
 * @generated
 */
public interface Page extends Marked, StringIdentity {

	/**
	 * Returns the value of the '<em><b>Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' containment reference.
	 * @see #setModel(Model)
	 * @see org.nasdanika.drawio.model.ModelPackage#getPage_Model()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Model getModel();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.Page#getModel <em>Model</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' containment reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(Model value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.nasdanika.drawio.model.ModelPackage#getPage_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.Page#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Links</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.drawio.model.ModelElement}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.drawio.model.ModelElement#getLinkedPage <em>Linked Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Model elements linking to this page
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Links</em>' reference list.
	 * @see org.nasdanika.drawio.model.ModelPackage#getPage_Links()
	 * @see org.nasdanika.drawio.model.ModelElement#getLinkedPage
	 * @model opposite="linkedPage"
	 * @generated
	 */
	EList<ModelElement> getLinks();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ModelElement getModelElementById(String modelElementId);
} // Page
