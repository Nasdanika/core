/**
 */
package org.nasdanika.engineering;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Component is a unit of engineering. Base class for elements which have an owning engineers and may contain issues or be referenced by issues.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.AbstractComponent#getOwners <em>Owners</em>}</li>
 *   <li>{@link org.nasdanika.engineering.AbstractComponent#getIssues <em>Issues</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getAbstractComponent()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface AbstractComponent extends ComponentCategoryElement {
	/**
	 * Returns the value of the '<em><b>Owners</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.AbstractEngineer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Engineers responsible for this component.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owners</em>' reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getAbstractComponent_Owners()
	 * @model
	 * @generated
	 */
	EList<AbstractEngineer> getOwners();

	/**
	 * Returns the value of the '<em><b>Issues</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.Issue}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Issues associated with the component - problems/pain points, improvement opportunities/enhancements.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Issues</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getAbstractComponent_Issues()
	 * @model containment="true"
	 * @generated
	 */
	EList<Issue> getIssues();

} // AbstractComponent
