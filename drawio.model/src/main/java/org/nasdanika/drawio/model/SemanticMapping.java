/**
 */
package org.nasdanika.drawio.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Semantic Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.SemanticMapping#getDocumentURI <em>Document URI</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.SemanticMapping#getPageID <em>Page ID</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.SemanticMapping#getModelElementID <em>Model Element ID</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.SemanticMapping#isPageElement <em>Page Element</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.drawio.model.ModelPackage#getSemanticMapping()
 * @model
 * @generated
 */
public interface SemanticMapping extends EObject {
	/**
	 * Returns the value of the '<em><b>Document URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Document URI</em>' attribute.
	 * @see #setDocumentURI(String)
	 * @see org.nasdanika.drawio.model.ModelPackage#getSemanticMapping_DocumentURI()
	 * @model
	 * @generated
	 */
	String getDocumentURI();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.SemanticMapping#getDocumentURI <em>Document URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Document URI</em>' attribute.
	 * @see #getDocumentURI()
	 * @generated
	 */
	void setDocumentURI(String value);

	/**
	 * Returns the value of the '<em><b>Page ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Page ID</em>' attribute.
	 * @see #setPageID(String)
	 * @see org.nasdanika.drawio.model.ModelPackage#getSemanticMapping_PageID()
	 * @model
	 * @generated
	 */
	String getPageID();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.SemanticMapping#getPageID <em>Page ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Page ID</em>' attribute.
	 * @see #getPageID()
	 * @generated
	 */
	void setPageID(String value);

	/**
	 * Returns the value of the '<em><b>Model Element ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model Element ID</em>' attribute.
	 * @see #setModelElementID(String)
	 * @see org.nasdanika.drawio.model.ModelPackage#getSemanticMapping_ModelElementID()
	 * @model
	 * @generated
	 */
	String getModelElementID();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.SemanticMapping#getModelElementID <em>Model Element ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Element ID</em>' attribute.
	 * @see #getModelElementID()
	 * @generated
	 */
	void setModelElementID(String value);

	/**
	 * Returns the value of the '<em><b>Page Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An element may be marked as "page-element" to indicate that a particular page is about this element and shall be added to the list of element's representations
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Page Element</em>' attribute.
	 * @see #setPageElement(boolean)
	 * @see org.nasdanika.drawio.model.ModelPackage#getSemanticMapping_PageElement()
	 * @model
	 * @generated
	 */
	boolean isPageElement();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.SemanticMapping#isPageElement <em>Page Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Page Element</em>' attribute.
	 * @see #isPageElement()
	 * @generated
	 */
	void setPageElement(boolean value);

} // SemanticMapping
