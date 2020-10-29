/**
 */
package org.nasdanika.engineering;

import org.eclipse.emf.common.util.EList;
import org.nasdanika.ncore.Entity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Need</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Personas have needs which may be satisfied by organization offerings. Needs can be organized into a hierarchy and assigned weights either manually or using decision analysis techniques. Needs may be satisfied by offerings via scenarios explaining how a need is satisfied.
 * 
 * Must have, need to have, delighter - here or at the offering level?
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.Need#getScenarios <em>Scenarios</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getNeed()
 * @model
 * @generated
 */
public interface Need extends Entity, NeedCategoryElement {

	/**
	 * Returns the value of the '<em><b>Scenarios</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.Scenario}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scenarios</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getNeed_Scenarios()
	 * @model containment="true"
	 * @generated
	 */
	EList<Scenario> getScenarios();
} // Need
