/**
 */
package org.nasdanika.engineering;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scenario</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Scenario explains how a need is satisfied by offerings. A scenario references offerings and can be assigned weight - manually or computed. Scenarios can be organized into a hierarchy. Generally scenarios may have steps/flow and include other scenarios, it needs to be understood whether this level of detalization is necessary.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.Scenario#getOfferings <em>Offerings</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getScenario()
 * @model
 * @generated
 */
public interface Scenario extends EObject {

	/**
	 * Returns the value of the '<em><b>Offerings</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.Offering}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Offerings</em>' reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getScenario_Offerings()
	 * @model
	 * @generated
	 */
	EList<Offering> getOfferings();
} // Scenario
