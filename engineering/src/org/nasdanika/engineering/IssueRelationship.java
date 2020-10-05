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
 * Relationship between issues. Contained by the target (dependent) issue, references the source issue. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.IssueRelationship#getType <em>Type</em>}</li>
 *   <li>{@link org.nasdanika.engineering.IssueRelationship#getSource <em>Source</em>}</li>
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
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(Issue)
	 * @see org.nasdanika.engineering.EngineeringPackage#getIssueRelationship_Source()
	 * @model
	 * @generated
	 */
	Issue getSource();

	/**
	 * Sets the value of the '{@link org.nasdanika.engineering.IssueRelationship#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Issue value);

} // IssueRelationship
