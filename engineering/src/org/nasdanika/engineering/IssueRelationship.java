/**
 */
package org.nasdanika.engineering;

import org.nasdanika.ncore.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Issue Relationship</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Relationship between issues.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.IssueRelationship#getType <em>Type</em>}</li>
 *   <li>{@link org.nasdanika.engineering.IssueRelationship#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getIssueRelationship()
 * @model
 * @generated
 */
public interface IssueRelationship extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(IssueRelationshipType)
	 * @see org.nasdanika.engineering.EngineeringPackage#getIssueRelationship_Type()
	 * @model
	 * @generated
	 */
	IssueRelationshipType getType();

	/**
	 * Sets the value of the '{@link org.nasdanika.engineering.IssueRelationship#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(IssueRelationshipType value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Issue)
	 * @see org.nasdanika.engineering.EngineeringPackage#getIssueRelationship_Target()
	 * @model
	 * @generated
	 */
	Issue getTarget();

	/**
	 * Sets the value of the '{@link org.nasdanika.engineering.IssueRelationship#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Issue value);

} // IssueRelationship
