/**
 */
package org.nasdanika.drawio.model;

import org.nasdanika.ncore.Marked;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.Model#getRoot <em>Root</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.drawio.model.ModelPackage#getModel()
 * @model
 * @generated
 */
public interface Model extends Marked {

	/**
	 * Returns the value of the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root</em>' containment reference.
	 * @see #setRoot(Root)
	 * @see org.nasdanika.drawio.model.ModelPackage#getModel_Root()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Root getRoot();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.Model#getRoot <em>Root</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root</em>' containment reference.
	 * @see #getRoot()
	 * @generated
	 */
	void setRoot(Root value);
} // Model
