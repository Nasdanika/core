/**
 */
package org.nasdanika.engineering;

import org.nasdanika.ncore.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Issue Resolution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Issue resolution. E.g. fixed, cancelled, wont fix.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.IssueResolution#isCompleted <em>Completed</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getIssueResolution()
 * @model
 * @generated
 */
public interface IssueResolution extends ModelElement {

	/**
	 * Returns the value of the '<em><b>Completed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If true, the issue is successfully completed and blocked issues can be worked on.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Completed</em>' attribute.
	 * @see #setCompleted(boolean)
	 * @see org.nasdanika.engineering.EngineeringPackage#getIssueResolution_Completed()
	 * @model
	 * @generated
	 */
	boolean isCompleted();

	/**
	 * Sets the value of the '{@link org.nasdanika.engineering.IssueResolution#isCompleted <em>Completed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Completed</em>' attribute.
	 * @see #isCompleted()
	 * @generated
	 */
	void setCompleted(boolean value);
} // IssueResolution
