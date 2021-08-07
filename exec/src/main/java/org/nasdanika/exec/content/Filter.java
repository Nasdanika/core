/**
 */
package org.nasdanika.exec.content;

import org.eclipse.emf.ecore.EObject;

import org.nasdanika.exec.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Filter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.exec.content.Filter#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.exec.content.ContentPackage#getFilter()
 * @model
 * @generated
 */
public interface Filter extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' containment reference.
	 * @see #setSource(EObject)
	 * @see org.nasdanika.exec.content.ContentPackage#getFilter_Source()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EObject getSource();

	/**
	 * Sets the value of the '{@link org.nasdanika.exec.content.Filter#getSource <em>Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' containment reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(EObject value);

} // Filter
