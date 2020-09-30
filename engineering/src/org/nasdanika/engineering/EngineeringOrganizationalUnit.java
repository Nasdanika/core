/**
 */
package org.nasdanika.engineering;

import org.eclipse.emf.common.util.EList;
import org.nasdanika.party.OrganizationalUnit;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Organizational Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Defines engineering practice - issue types, priorities, categories, etc. Scope, if a flag is set, for engineers and issues - they can see only categories etc defined at the
 * components organized into categories. Palette for components.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.EngineeringOrganizationalUnit#getPortfolio <em>Portfolio</em>}</li>
 *   <li>{@link org.nasdanika.engineering.EngineeringOrganizationalUnit#getTargetAudiences <em>Target Audiences</em>}</li>
 *   <li>{@link org.nasdanika.engineering.EngineeringOrganizationalUnit#getIssueTypes <em>Issue Types</em>}</li>
 *   <li>{@link org.nasdanika.engineering.EngineeringOrganizationalUnit#getIssueResolutions <em>Issue Resolutions</em>}</li>
 *   <li>{@link org.nasdanika.engineering.EngineeringOrganizationalUnit#getIssueCategories <em>Issue Categories</em>}</li>
 *   <li>{@link org.nasdanika.engineering.EngineeringOrganizationalUnit#getIssueStatuses <em>Issue Statuses</em>}</li>
 *   <li>{@link org.nasdanika.engineering.EngineeringOrganizationalUnit#getIssueRelationshipTypes <em>Issue Relationship Types</em>}</li>
 *   <li>{@link org.nasdanika.engineering.EngineeringOrganizationalUnit#getIncrements <em>Increments</em>}</li>
 *   <li>{@link org.nasdanika.engineering.EngineeringOrganizationalUnit#getFeatureTypes <em>Feature Types</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getEngineeringOrganizationalUnit()
 * @model
 * @generated
 */
public interface EngineeringOrganizationalUnit extends OrganizationalUnit, AbstractEngineer {

	/**
	 * Returns the value of the '<em><b>Portfolio</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.ComponentCategoryElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Components and products owned by the organizational unit.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Portfolio</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getEngineeringOrganizationalUnit_Portfolio()
	 * @model containment="true"
	 * @generated
	 */
	EList<ComponentCategoryElement> getPortfolio();

	/**
	 * Returns the value of the '<em><b>Target Audiences</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.Persona}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Audiences</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getEngineeringOrganizationalUnit_TargetAudiences()
	 * @model containment="true"
	 * @generated
	 */
	EList<Persona> getTargetAudiences();

	/**
	 * Returns the value of the '<em><b>Issue Types</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.IssueType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Issue Types</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getEngineeringOrganizationalUnit_IssueTypes()
	 * @model containment="true"
	 * @generated
	 */
	EList<IssueType> getIssueTypes();

	/**
	 * Returns the value of the '<em><b>Issue Resolutions</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.IssueResolution}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Issue Resolutions</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getEngineeringOrganizationalUnit_IssueResolutions()
	 * @model containment="true"
	 * @generated
	 */
	EList<IssueResolution> getIssueResolutions();

	/**
	 * Returns the value of the '<em><b>Issue Categories</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.IssueCategory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Issue Categories</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getEngineeringOrganizationalUnit_IssueCategories()
	 * @model containment="true"
	 * @generated
	 */
	EList<IssueCategory> getIssueCategories();

	/**
	 * Returns the value of the '<em><b>Issue Statuses</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.IssueStatus}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Issue Statuses</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getEngineeringOrganizationalUnit_IssueStatuses()
	 * @model containment="true"
	 * @generated
	 */
	EList<IssueStatus> getIssueStatuses();

	/**
	 * Returns the value of the '<em><b>Issue Relationship Types</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.IssueRelationshipType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Issue Relationship Types</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getEngineeringOrganizationalUnit_IssueRelationshipTypes()
	 * @model containment="true"
	 * @generated
	 */
	EList<IssueRelationshipType> getIssueRelationshipTypes();

	/**
	 * Returns the value of the '<em><b>Increments</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.Increment}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Increments</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getEngineeringOrganizationalUnit_Increments()
	 * @model containment="true"
	 * @generated
	 */
	EList<Increment> getIncrements();

	/**
	 * Returns the value of the '<em><b>Feature Types</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.FeatureType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feature Types</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getEngineeringOrganizationalUnit_FeatureTypes()
	 * @model containment="true"
	 * @generated
	 */
	EList<FeatureType> getFeatureTypes();
} // EngineeringOrganizationalUnit
