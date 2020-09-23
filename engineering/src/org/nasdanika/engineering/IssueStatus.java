/**
 */
package org.nasdanika.engineering;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Issue Status</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Issue status. E.g. open, in progress, closed. May define transitions. If transitions are empty then can transition to any status. Defined at engineering org unit level. Visualization - statuses and transitions diagram.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.IssueStatus#getTransitions <em>Transitions</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getIssueStatus()
 * @model
 * @generated
 */
public interface IssueStatus extends EObject {

	/**
	 * Returns the value of the '<em><b>Transitions</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.IssueStatus}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Statuses to which this status can transition. If empty then can transition to any status.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Transitions</em>' reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getIssueStatus_Transitions()
	 * @model
	 * @generated
	 */
	EList<IssueStatus> getTransitions();
} // IssueStatus
