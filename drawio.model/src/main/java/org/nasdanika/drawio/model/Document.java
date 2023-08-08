/**
 */
package org.nasdanika.drawio.model;

import org.eclipse.emf.common.util.EList;
import org.nasdanika.ncore.Marked;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.Document#getPages <em>Pages</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.Document#getUri <em>Uri</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.drawio.model.ModelPackage#getDocument()
 * @model
 * @generated
 */
public interface Document extends Marked {

	/**
	 * Returns the value of the '<em><b>Pages</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.drawio.model.Page}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pages</em>' containment reference list.
	 * @see org.nasdanika.drawio.model.ModelPackage#getDocument_Pages()
	 * @model containment="true"
	 * @generated
	 */
	EList<Page> getPages();

	/**
	 * Returns the value of the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uri</em>' attribute.
	 * @see #setUri(String)
	 * @see org.nasdanika.drawio.model.ModelPackage#getDocument_Uri()
	 * @model
	 * @generated
	 */
	String getUri();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.Document#getUri <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uri</em>' attribute.
	 * @see #getUri()
	 * @generated
	 */
	void setUri(String value);
} // Document
