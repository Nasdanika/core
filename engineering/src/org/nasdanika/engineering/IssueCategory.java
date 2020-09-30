/**
 */
package org.nasdanika.engineering;

import org.eclipse.emf.common.util.EList;
import org.nasdanika.ncore.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Issue Category</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Issue category. E.g. documentation, code, automated build. An issue may have multiple categories. Categories can be hierarchical. Category may have an owning engineer. E.g. an automated build category would be owned by a build engineer.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.IssueCategory#getChildren <em>Children</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getIssueCategory()
 * @model
 * @generated
 */
public interface IssueCategory extends ModelElement {

	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.IssueCategory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getIssueCategory_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<IssueCategory> getChildren();
} // IssueCategory
