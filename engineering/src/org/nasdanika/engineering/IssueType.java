/**
 */
package org.nasdanika.engineering;

import org.eclipse.emf.common.util.EList;
import org.nasdanika.ncore.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Issue Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Issue type. E.g. feature or bug. Or epic/story/task. May define child type(s). E.g. epics may contain only stories, but not tasks. Defined at engineering org unit level and referenced by issues.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.IssueType#getChildren <em>Children</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getIssueType()
 * @model
 * @generated
 */
public interface IssueType extends ModelElement {

	/**
	 * Returns the value of the '<em><b>Children</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.IssueType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Issue type(s) which can be children of this issue type. E.g. epics may contain stories but not tasks.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Children</em>' reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getIssueType_Children()
	 * @model
	 * @generated
	 */
	EList<IssueType> getChildren();
} // IssueType
