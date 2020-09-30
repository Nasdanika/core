/**
 */
package org.nasdanika.engineering;

import org.nasdanika.ncore.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Issue Relationship Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Relationship type.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.IssueRelationshipType#isBlocks <em>Blocks</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getIssueRelationshipType()
 * @model
 * @generated
 */
public interface IssueRelationshipType extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Blocks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If true, source/owner must be completed in order to start working on the target.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Blocks</em>' attribute.
	 * @see #setBlocks(boolean)
	 * @see org.nasdanika.engineering.EngineeringPackage#getIssueRelationshipType_Blocks()
	 * @model
	 * @generated
	 */
	boolean isBlocks();

	/**
	 * Sets the value of the '{@link org.nasdanika.engineering.IssueRelationshipType#isBlocks <em>Blocks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Blocks</em>' attribute.
	 * @see #isBlocks()
	 * @generated
	 */
	void setBlocks(boolean value);

} // IssueRelationshipType
