/**
 */
package org.nasdanika.engineering;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Engineer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base class for Engineer and EngineeringOrganizationalUnit. Can own components and be assigned to issues. Can also contain its own issues list.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.AbstractEngineer#getIssues <em>Issues</em>}</li>
 *   <li>{@link org.nasdanika.engineering.AbstractEngineer#getObjectives <em>Objectives</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getAbstractEngineer()
 * @model
 * @generated
 */
public interface AbstractEngineer extends EObject {

	/**
	 * Returns the value of the '<em><b>Issues</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.Issue}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Issues owned by this engineer. Issues related to a single component shall be defined at the component level. If an issue doesn't have a related component or has multiple related components it can be defined at the engineer level.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Issues</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getAbstractEngineer_Issues()
	 * @model containment="true"
	 * @generated
	 */
	EList<Issue> getIssues();

	/**
	 * Returns the value of the '<em><b>Objectives</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.Objective}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Objectives</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getAbstractEngineer_Objectives()
	 * @model containment="true"
	 * @generated
	 */
	EList<Objective> getObjectives();
} // AbstractEngineer
