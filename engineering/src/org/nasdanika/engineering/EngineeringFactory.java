/**
 */
package org.nasdanika.engineering;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.nasdanika.engineering.EngineeringPackage
 * @generated
 */
public interface EngineeringFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EngineeringFactory eINSTANCE = org.nasdanika.engineering.impl.EngineeringFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Abstract Engineer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Abstract Engineer</em>'.
	 * @generated
	 */
	AbstractEngineer createAbstractEngineer();

	/**
	 * Returns a new object of class '<em>Engineer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Engineer</em>'.
	 * @generated
	 */
	Engineer createEngineer();

	/**
	 * Returns a new object of class '<em>Issue Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Issue Type</em>'.
	 * @generated
	 */
	IssueType createIssueType();

	/**
	 * Returns a new object of class '<em>Issue Resolution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Issue Resolution</em>'.
	 * @generated
	 */
	IssueResolution createIssueResolution();

	/**
	 * Returns a new object of class '<em>Issue Category</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Issue Category</em>'.
	 * @generated
	 */
	IssueCategory createIssueCategory();

	/**
	 * Returns a new object of class '<em>Issue Status</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Issue Status</em>'.
	 * @generated
	 */
	IssueStatus createIssueStatus();

	/**
	 * Returns a new object of class '<em>Issue Note</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Issue Note</em>'.
	 * @generated
	 */
	IssueNote createIssueNote();

	/**
	 * Returns a new object of class '<em>Issue</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Issue</em>'.
	 * @generated
	 */
	Issue createIssue();

	/**
	 * Returns a new object of class '<em>Increment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Increment</em>'.
	 * @generated
	 */
	Increment createIncrement();

	/**
	 * Returns a new object of class '<em>Organizational Unit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Organizational Unit</em>'.
	 * @generated
	 */
	EngineeringOrganizationalUnit createEngineeringOrganizationalUnit();

	/**
	 * Returns a new object of class '<em>Release</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Release</em>'.
	 * @generated
	 */
	Release createRelease();

	/**
	 * Returns a new object of class '<em>Objective</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Objective</em>'.
	 * @generated
	 */
	Objective createObjective();

	/**
	 * Returns a new object of class '<em>Key Result</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Key Result</em>'.
	 * @generated
	 */
	KeyResult createKeyResult();

	/**
	 * Returns a new object of class '<em>Product</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Product</em>'.
	 * @generated
	 */
	Product createProduct();

	/**
	 * Returns a new object of class '<em>Offering</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Offering</em>'.
	 * @generated
	 */
	Offering createOffering();

	/**
	 * Returns a new object of class '<em>Edition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Edition</em>'.
	 * @generated
	 */
	Edition createEdition();

	/**
	 * Returns a new object of class '<em>Feature</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Feature</em>'.
	 * @generated
	 */
	Feature createFeature();

	/**
	 * Returns a new object of class '<em>Component</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component</em>'.
	 * @generated
	 */
	Component createComponent();

	/**
	 * Returns a new object of class '<em>Persona</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Persona</em>'.
	 * @generated
	 */
	Persona createPersona();

	/**
	 * Returns a new object of class '<em>Need</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Need</em>'.
	 * @generated
	 */
	Need createNeed();

	/**
	 * Returns a new object of class '<em>Scenario</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Scenario</em>'.
	 * @generated
	 */
	Scenario createScenario();

	/**
	 * Returns a new object of class '<em>Portfolio</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Portfolio</em>'.
	 * @generated
	 */
	Portfolio createPortfolio();

	/**
	 * Returns a new object of class '<em>Criterion</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Criterion</em>'.
	 * @generated
	 */
	Criterion createCriterion();

	/**
	 * Returns a new object of class '<em>Comparison</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Comparison</em>'.
	 * @generated
	 */
	Comparison createComparison();

	/**
	 * Returns a new object of class '<em>Risk</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Risk</em>'.
	 * @generated
	 */
	Risk createRisk();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	EngineeringPackage getEngineeringPackage();

} //EngineeringFactory
