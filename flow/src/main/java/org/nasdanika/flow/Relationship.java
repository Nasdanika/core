/**
 */
package org.nasdanika.flow;

import org.nasdanika.diagram.Connection;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relationship</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.Relationship#getTargetKey <em>Target Key</em>}</li>
 *   <li>{@link org.nasdanika.flow.Relationship#getTarget <em>Target</em>}</li>
 *   <li>{@link org.nasdanika.flow.Relationship#getStyle <em>Style</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getRelationship()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/flow/relationship.md'"
 * @generated
 */
public interface Relationship extends PackageElement<Relationship> {
	/**
	 * Returns the value of the '<em><b>Target Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Key of relationship target relative to the containing artifact.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target Key</em>' attribute.
	 * @see #setTargetKey(String)
	 * @see org.nasdanika.flow.FlowPackage#getRelationship_TargetKey()
	 * @model required="true"
	 *        annotation="urn:org.nasdanika load-key='target' default-feature='true'"
	 * @generated
	 */
	String getTargetKey();

	/**
	 * Sets the value of the '{@link org.nasdanika.flow.Relationship#getTargetKey <em>Target Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Key</em>' attribute.
	 * @see #getTargetKey()
	 * @generated
	 */
	void setTargetKey(String value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Artifact#getInboundRelationships <em>Inbound Relationships</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Relationship target.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see org.nasdanika.flow.FlowPackage#getRelationship_Target()
	 * @see org.nasdanika.flow.Artifact#getInboundRelationships
	 * @model opposite="inboundRelationships" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	Artifact getTarget();

	/**
	 * Returns the value of the '<em><b>Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Connection style for component diagrams.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Style</em>' containment reference.
	 * @see #setStyle(Connection)
	 * @see org.nasdanika.flow.FlowPackage#getRelationship_Style()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	Connection getStyle();

	/**
	 * Sets the value of the '{@link org.nasdanika.flow.Relationship#getStyle <em>Style</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Style</em>' containment reference.
	 * @see #getStyle()
	 * @generated
	 */
	void setStyle(Connection value);

} // Relationship
