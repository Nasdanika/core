/**
 */
package org.nasdanika.engineering;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.party.PartyPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.nasdanika.engineering.EngineeringFactory
 * @model kind="package"
 *        annotation="urn:org.nasdanika documentation-reference='doc/engineering.md'"
 * @generated
 */
public interface EngineeringPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "engineering";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "urn:org.nasdanika.engineering";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.nasdanika.engineering";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EngineeringPackage eINSTANCE = org.nasdanika.engineering.impl.EngineeringPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.ComponentCategoryElement <em>Component Category Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.ComponentCategoryElement
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getComponentCategoryElement()
	 * @generated
	 */
	int COMPONENT_CATEGORY_ELEMENT = 0;

	/**
	 * The number of structural features of the '<em>Component Category Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_CATEGORY_ELEMENT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Component Category Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_CATEGORY_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.AbstractComponent <em>Abstract Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.AbstractComponent
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getAbstractComponent()
	 * @generated
	 */
	int ABSTRACT_COMPONENT = 1;

	/**
	 * The feature id for the '<em><b>Owners</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_COMPONENT__OWNERS = COMPONENT_CATEGORY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Issues</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_COMPONENT__ISSUES = COMPONENT_CATEGORY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Abstract Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_COMPONENT_FEATURE_COUNT = COMPONENT_CATEGORY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Abstract Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_COMPONENT_OPERATION_COUNT = COMPONENT_CATEGORY_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.ComponentCategoryImpl <em>Component Category</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.ComponentCategoryImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getComponentCategory()
	 * @generated
	 */
	int COMPONENT_CATEGORY = 2;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_CATEGORY__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_CATEGORY__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_CATEGORY__ELEMENTS = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Component Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_CATEGORY_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Component Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_CATEGORY_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.ComponentReference <em>Component Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.ComponentReference
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getComponentReference()
	 * @generated
	 */
	int COMPONENT_REFERENCE = 3;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_REFERENCE__TARGET = COMPONENT_CATEGORY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Component Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_REFERENCE_FEATURE_COUNT = COMPONENT_CATEGORY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Component Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_REFERENCE_OPERATION_COUNT = COMPONENT_CATEGORY_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.AbstractEngineerImpl <em>Abstract Engineer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.AbstractEngineerImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getAbstractEngineer()
	 * @generated
	 */
	int ABSTRACT_ENGINEER = 4;

	/**
	 * The feature id for the '<em><b>Issues</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ENGINEER__ISSUES = 0;

	/**
	 * The feature id for the '<em><b>Objectives</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ENGINEER__OBJECTIVES = 1;

	/**
	 * The number of structural features of the '<em>Abstract Engineer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ENGINEER_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Abstract Engineer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ENGINEER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.EngineerImpl <em>Engineer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.EngineerImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getEngineer()
	 * @generated
	 */
	int ENGINEER = 7;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.IssueTypeImpl <em>Issue Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.IssueTypeImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIssueType()
	 * @generated
	 */
	int ISSUE_TYPE = 8;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.IssueResolutionImpl <em>Issue Resolution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.IssueResolutionImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIssueResolution()
	 * @generated
	 */
	int ISSUE_RESOLUTION = 9;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.IssueCategoryImpl <em>Issue Category</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.IssueCategoryImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIssueCategory()
	 * @generated
	 */
	int ISSUE_CATEGORY = 10;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.IssueImpl <em>Issue</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.IssueImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIssue()
	 * @generated
	 */
	int ISSUE = 15;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.IncrementImpl <em>Increment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.IncrementImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIncrement()
	 * @generated
	 */
	int INCREMENT = 16;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.EngineeringOrganizationalUnitImpl <em>Organizational Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.EngineeringOrganizationalUnitImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getEngineeringOrganizationalUnit()
	 * @generated
	 */
	int ENGINEERING_ORGANIZATIONAL_UNIT = 5;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.ReleaseImpl <em>Release</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.ReleaseImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getRelease()
	 * @generated
	 */
	int RELEASE = 17;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.ObjectiveImpl <em>Objective</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.ObjectiveImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getObjective()
	 * @generated
	 */
	int OBJECTIVE = 18;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.KeyResultImpl <em>Key Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.KeyResultImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getKeyResult()
	 * @generated
	 */
	int KEY_RESULT = 19;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.ProductImpl <em>Product</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.ProductImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getProduct()
	 * @generated
	 */
	int PRODUCT = 22;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.OfferingImpl <em>Offering</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.OfferingImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getOffering()
	 * @generated
	 */
	int OFFERING = 21;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.EditionImpl <em>Edition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.EditionImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getEdition()
	 * @generated
	 */
	int EDITION = 23;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.FeatureImpl <em>Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.FeatureImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getFeature()
	 * @generated
	 */
	int FEATURE = 27;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.ComponentImpl <em>Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.ComponentImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getComponent()
	 * @generated
	 */
	int COMPONENT = 20;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.PersonaImpl <em>Persona</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.PersonaImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getPersona()
	 * @generated
	 */
	int PERSONA = 28;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.NeedImpl <em>Need</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.NeedImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getNeed()
	 * @generated
	 */
	int NEED = 31;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.ScenarioImpl <em>Scenario</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.ScenarioImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getScenario()
	 * @generated
	 */
	int SCENARIO = 32;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.CriterionImpl <em>Criterion</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.CriterionImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getCriterion()
	 * @generated
	 */
	int CRITERION = 33;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.ComparisonImpl <em>Comparison</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.ComparisonImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getComparison()
	 * @generated
	 */
	int COMPARISON = 34;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.RiskImpl <em>Risk</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.RiskImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getRisk()
	 * @generated
	 */
	int RISK = 35;


	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.AbstractComponent <em>Abstract Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Component</em>'.
	 * @see org.nasdanika.engineering.AbstractComponent
	 * @generated
	 */
	EClass getAbstractComponent();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.engineering.AbstractComponent#getOwners <em>Owners</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Owners</em>'.
	 * @see org.nasdanika.engineering.AbstractComponent#getOwners()
	 * @see #getAbstractComponent()
	 * @generated
	 */
	EReference getAbstractComponent_Owners();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.AbstractComponent#getIssues <em>Issues</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Issues</em>'.
	 * @see org.nasdanika.engineering.AbstractComponent#getIssues()
	 * @see #getAbstractComponent()
	 * @generated
	 */
	EReference getAbstractComponent_Issues();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.ComponentCategory <em>Component Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Category</em>'.
	 * @see org.nasdanika.engineering.ComponentCategory
	 * @generated
	 */
	EClass getComponentCategory();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.ComponentCategory#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see org.nasdanika.engineering.ComponentCategory#getElements()
	 * @see #getComponentCategory()
	 * @generated
	 */
	EReference getComponentCategory_Elements();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.ComponentReference <em>Component Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Reference</em>'.
	 * @see org.nasdanika.engineering.ComponentReference
	 * @generated
	 */
	EClass getComponentReference();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.engineering.ComponentReference#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Target</em>'.
	 * @see org.nasdanika.engineering.ComponentReference#getTarget()
	 * @see #getComponentReference()
	 * @generated
	 */
	EReference getComponentReference_Target();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.AbstractEngineer <em>Abstract Engineer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Engineer</em>'.
	 * @see org.nasdanika.engineering.AbstractEngineer
	 * @generated
	 */
	EClass getAbstractEngineer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.AbstractEngineer#getIssues <em>Issues</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Issues</em>'.
	 * @see org.nasdanika.engineering.AbstractEngineer#getIssues()
	 * @see #getAbstractEngineer()
	 * @generated
	 */
	EReference getAbstractEngineer_Issues();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.AbstractEngineer#getObjectives <em>Objectives</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Objectives</em>'.
	 * @see org.nasdanika.engineering.AbstractEngineer#getObjectives()
	 * @see #getAbstractEngineer()
	 * @generated
	 */
	EReference getAbstractEngineer_Objectives();

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.IssueStatusImpl <em>Issue Status</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.IssueStatusImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIssueStatus()
	 * @generated
	 */
	int ISSUE_STATUS = 11;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.IssueNoteImpl <em>Issue Note</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.IssueNoteImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIssueNote()
	 * @generated
	 */
	int ISSUE_NOTE = 12;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATIONAL_UNIT__TITLE = PartyPackage.ORGANIZATIONAL_UNIT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATIONAL_UNIT__DESCRIPTION = PartyPackage.ORGANIZATIONAL_UNIT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATIONAL_UNIT__ID = PartyPackage.ORGANIZATIONAL_UNIT__ID;

	/**
	 * The feature id for the '<em><b>Contact Methods</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATIONAL_UNIT__CONTACT_METHODS = PartyPackage.ORGANIZATIONAL_UNIT__CONTACT_METHODS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATIONAL_UNIT__RESOURCES = PartyPackage.ORGANIZATIONAL_UNIT__RESOURCES;

	/**
	 * The feature id for the '<em><b>Organizational Units</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATIONAL_UNIT__ORGANIZATIONAL_UNITS = PartyPackage.ORGANIZATIONAL_UNIT__ORGANIZATIONAL_UNITS;

	/**
	 * The feature id for the '<em><b>Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATIONAL_UNIT__ROLES = PartyPackage.ORGANIZATIONAL_UNIT__ROLES;

	/**
	 * The feature id for the '<em><b>Issues</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATIONAL_UNIT__ISSUES = PartyPackage.ORGANIZATIONAL_UNIT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Objectives</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATIONAL_UNIT__OBJECTIVES = PartyPackage.ORGANIZATIONAL_UNIT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Portfolio</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATIONAL_UNIT__PORTFOLIO = PartyPackage.ORGANIZATIONAL_UNIT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Target Audiences</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATIONAL_UNIT__TARGET_AUDIENCES = PartyPackage.ORGANIZATIONAL_UNIT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Issue Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_TYPES = PartyPackage.ORGANIZATIONAL_UNIT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Issue Resolutions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RESOLUTIONS = PartyPackage.ORGANIZATIONAL_UNIT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Issue Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_CATEGORIES = PartyPackage.ORGANIZATIONAL_UNIT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Issue Statuses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_STATUSES = PartyPackage.ORGANIZATIONAL_UNIT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Issue Relationship Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RELATIONSHIP_TYPES = PartyPackage.ORGANIZATIONAL_UNIT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Increments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATIONAL_UNIT__INCREMENTS = PartyPackage.ORGANIZATIONAL_UNIT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Feature Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATIONAL_UNIT__FEATURE_TYPES = PartyPackage.ORGANIZATIONAL_UNIT_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Organizational Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATIONAL_UNIT_FEATURE_COUNT = PartyPackage.ORGANIZATIONAL_UNIT_FEATURE_COUNT + 11;

	/**
	 * The number of operations of the '<em>Organizational Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATIONAL_UNIT_OPERATION_COUNT = PartyPackage.ORGANIZATIONAL_UNIT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.EngineeringOrganizationImpl <em>Organization</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.EngineeringOrganizationImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getEngineeringOrganization()
	 * @generated
	 */
	int ENGINEERING_ORGANIZATION = 6;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATION__TITLE = PartyPackage.ORGANIZATION__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATION__DESCRIPTION = PartyPackage.ORGANIZATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATION__ID = PartyPackage.ORGANIZATION__ID;

	/**
	 * The feature id for the '<em><b>Contact Methods</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATION__CONTACT_METHODS = PartyPackage.ORGANIZATION__CONTACT_METHODS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATION__RESOURCES = PartyPackage.ORGANIZATION__RESOURCES;

	/**
	 * The feature id for the '<em><b>Organizational Units</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATION__ORGANIZATIONAL_UNITS = PartyPackage.ORGANIZATION__ORGANIZATIONAL_UNITS;

	/**
	 * The feature id for the '<em><b>Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATION__ROLES = PartyPackage.ORGANIZATION__ROLES;

	/**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATION__MEMBERS = PartyPackage.ORGANIZATION__MEMBERS;

	/**
	 * The feature id for the '<em><b>Directory</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATION__DIRECTORY = PartyPackage.ORGANIZATION__DIRECTORY;

	/**
	 * The feature id for the '<em><b>Issues</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATION__ISSUES = PartyPackage.ORGANIZATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Objectives</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATION__OBJECTIVES = PartyPackage.ORGANIZATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Portfolio</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATION__PORTFOLIO = PartyPackage.ORGANIZATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Target Audiences</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATION__TARGET_AUDIENCES = PartyPackage.ORGANIZATION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Issue Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATION__ISSUE_TYPES = PartyPackage.ORGANIZATION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Issue Resolutions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATION__ISSUE_RESOLUTIONS = PartyPackage.ORGANIZATION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Issue Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATION__ISSUE_CATEGORIES = PartyPackage.ORGANIZATION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Issue Statuses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATION__ISSUE_STATUSES = PartyPackage.ORGANIZATION_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Issue Relationship Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATION__ISSUE_RELATIONSHIP_TYPES = PartyPackage.ORGANIZATION_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Increments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATION__INCREMENTS = PartyPackage.ORGANIZATION_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Feature Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATION__FEATURE_TYPES = PartyPackage.ORGANIZATION_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Organization</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATION_FEATURE_COUNT = PartyPackage.ORGANIZATION_FEATURE_COUNT + 11;

	/**
	 * The number of operations of the '<em>Organization</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATION_OPERATION_COUNT = PartyPackage.ORGANIZATION_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEER__TITLE = PartyPackage.ROLE__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEER__DESCRIPTION = PartyPackage.ROLE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEER__ID = PartyPackage.ROLE__ID;

	/**
	 * The feature id for the '<em><b>Members</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEER__MEMBERS = PartyPackage.ROLE__MEMBERS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEER__EXTENDS = PartyPackage.ROLE__EXTENDS;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEER__ABSTRACT = PartyPackage.ROLE__ABSTRACT;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEER__RESOURCES = PartyPackage.ROLE__RESOURCES;

	/**
	 * The feature id for the '<em><b>Issues</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEER__ISSUES = PartyPackage.ROLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Objectives</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEER__OBJECTIVES = PartyPackage.ROLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Engineer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEER_FEATURE_COUNT = PartyPackage.ROLE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Engineer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEER_OPERATION_COUNT = PartyPackage.ROLE_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_TYPE__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_TYPE__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Children</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_TYPE__CHILDREN = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Issue Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_TYPE_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Issue Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_TYPE_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_RESOLUTION__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_RESOLUTION__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Completed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_RESOLUTION__COMPLETED = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Issue Resolution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_RESOLUTION_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Issue Resolution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_RESOLUTION_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_CATEGORY__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_CATEGORY__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_CATEGORY__CHILDREN = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Issue Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_CATEGORY_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Issue Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_CATEGORY_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_STATUS__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_STATUS__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_STATUS__TRANSITIONS = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Issue Status</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_STATUS_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Issue Status</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_STATUS_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_NOTE__DESCRIPTION = 0;

	/**
	 * The number of structural features of the '<em>Issue Note</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_NOTE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Issue Note</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_NOTE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.IssueRelationshipTypeImpl <em>Issue Relationship Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.IssueRelationshipTypeImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIssueRelationshipType()
	 * @generated
	 */
	int ISSUE_RELATIONSHIP_TYPE = 13;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_RELATIONSHIP_TYPE__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_RELATIONSHIP_TYPE__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Blocks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_RELATIONSHIP_TYPE__BLOCKS = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Issue Relationship Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_RELATIONSHIP_TYPE_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Issue Relationship Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_RELATIONSHIP_TYPE_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.IssueRelationshipImpl <em>Issue Relationship</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.IssueRelationshipImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIssueRelationship()
	 * @generated
	 */
	int ISSUE_RELATIONSHIP = 14;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_RELATIONSHIP__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_RELATIONSHIP__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_RELATIONSHIP__TYPE = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_RELATIONSHIP__TARGET = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Issue Relationship</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_RELATIONSHIP_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Issue Relationship</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_RELATIONSHIP_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE__TITLE = NcorePackage.ENTITY__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE__DESCRIPTION = NcorePackage.ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE__ID = NcorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Assigned To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE__ASSIGNED_TO = NcorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE__SIZE = NcorePackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE__COST = NcorePackage.ENTITY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Benefit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE__BENEFIT = NcorePackage.ENTITY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Planned For</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE__PLANNED_FOR = NcorePackage.ENTITY_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE__CHILDREN = NcorePackage.ENTITY_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE__TYPE = NcorePackage.ENTITY_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE__STATUS = NcorePackage.ENTITY_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Resolution</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE__RESOLUTION = NcorePackage.ENTITY_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Categories</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE__CATEGORIES = NcorePackage.ENTITY_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE__NOTES = NcorePackage.ENTITY_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Releases</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE__RELEASES = NcorePackage.ENTITY_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Relationships</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE__RELATIONSHIPS = NcorePackage.ENTITY_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Requires</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE__REQUIRES = NcorePackage.ENTITY_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Actionable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE__ACTIONABLE = NcorePackage.ENTITY_FEATURE_COUNT + 14;

	/**
	 * The number of structural features of the '<em>Issue</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_FEATURE_COUNT = NcorePackage.ENTITY_FEATURE_COUNT + 15;

	/**
	 * The number of operations of the '<em>Issue</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_OPERATION_COUNT = NcorePackage.ENTITY_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCREMENT__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCREMENT__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCREMENT__CHILDREN = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCREMENT__START = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCREMENT__END = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Increment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCREMENT_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Increment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCREMENT_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Requires</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE__REQUIRES = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Includes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE__INCLUDES = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE__DATE = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Planned For</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE__PLANNED_FOR = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Released</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE__RELEASED = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Release</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Release</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTIVE__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTIVE__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Increment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTIVE__INCREMENT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTIVE__CHILDREN = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Key Results</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTIVE__KEY_RESULTS = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTIVE__PARENT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Objective</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTIVE_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Objective</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTIVE_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_RESULT__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_RESULT__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The number of structural features of the '<em>Key Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_RESULT_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Key Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_RESULT_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owners</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__OWNERS = ABSTRACT_COMPONENT__OWNERS;

	/**
	 * The feature id for the '<em><b>Issues</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__ISSUES = ABSTRACT_COMPONENT__ISSUES;

	/**
	 * The feature id for the '<em><b>Components</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__COMPONENTS = ABSTRACT_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.FeatureCategoryElement <em>Feature Category Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.FeatureCategoryElement
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getFeatureCategoryElement()
	 * @generated
	 */
	int FEATURE_CATEGORY_ELEMENT = 25;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.FeatureCategoryImpl <em>Feature Category</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.FeatureCategoryImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getFeatureCategory()
	 * @generated
	 */
	int FEATURE_CATEGORY = 26;

	/**
	 * The number of structural features of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_FEATURE_COUNT = ABSTRACT_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_OPERATION_COUNT = ABSTRACT_COMPONENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target Audiences</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERING__TARGET_AUDIENCES = 0;

	/**
	 * The number of structural features of the '<em>Offering</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERING_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Offering</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERING_OPERATION_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Owners</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__OWNERS = COMPONENT__OWNERS;

	/**
	 * The feature id for the '<em><b>Issues</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__ISSUES = COMPONENT__ISSUES;

	/**
	 * The feature id for the '<em><b>Components</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__COMPONENTS = COMPONENT__COMPONENTS;

	/**
	 * The feature id for the '<em><b>Target Audiences</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__TARGET_AUDIENCES = COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Editions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__EDITIONS = COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__FEATURES = COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Product</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_FEATURE_COUNT = COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Product</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_OPERATION_COUNT = COMPONENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target Audiences</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITION__TARGET_AUDIENCES = OFFERING__TARGET_AUDIENCES;

	/**
	 * The feature id for the '<em><b>Bases</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITION__BASES = OFFERING_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITION__FEATURES = OFFERING_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Edition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITION_FEATURE_COUNT = OFFERING_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Edition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITION_OPERATION_COUNT = OFFERING_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.FeatureTypeImpl <em>Feature Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.FeatureTypeImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getFeatureType()
	 * @generated
	 */
	int FEATURE_TYPE = 24;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_TYPE__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_TYPE__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The number of structural features of the '<em>Feature Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_TYPE_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Feature Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_TYPE_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Feature Category Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_CATEGORY_ELEMENT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Feature Category Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_CATEGORY_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_CATEGORY__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_CATEGORY__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_CATEGORY__ELEMENTS = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Feature Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_CATEGORY_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Feature Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_CATEGORY_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Planned For</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__PLANNED_FOR = FEATURE_CATEGORY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__TYPE = FEATURE_CATEGORY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Requires</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__REQUIRES = FEATURE_CATEGORY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FEATURE_COUNT = FEATURE_CATEGORY_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_OPERATION_COUNT = FEATURE_CATEGORY_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONA__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONA__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Needs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONA__NEEDS = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONA__EXTENDS = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Persona</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONA_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Persona</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONA_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.NeedCategoryElement <em>Need Category Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.NeedCategoryElement
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getNeedCategoryElement()
	 * @generated
	 */
	int NEED_CATEGORY_ELEMENT = 29;

	/**
	 * The number of structural features of the '<em>Need Category Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEED_CATEGORY_ELEMENT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Need Category Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEED_CATEGORY_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.NeedCategoryImpl <em>Need Category</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.NeedCategoryImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getNeedCategory()
	 * @generated
	 */
	int NEED_CATEGORY = 30;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEED_CATEGORY__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEED_CATEGORY__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEED_CATEGORY__ELEMENTS = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Need Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEED_CATEGORY_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Need Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEED_CATEGORY_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Scenarios</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEED__SCENARIOS = NEED_CATEGORY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Need</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEED_FEATURE_COUNT = NEED_CATEGORY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Need</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEED_OPERATION_COUNT = NEED_CATEGORY_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Offerings</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO__OFFERINGS = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Scenario</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Scenario</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRITERION__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRITERION__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The number of structural features of the '<em>Criterion</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRITERION_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Criterion</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRITERION_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Comparison</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Comparison</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON_OPERATION_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RISK__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RISK__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The number of structural features of the '<em>Risk</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RISK_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Risk</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RISK_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.ComponentCategoryElement <em>Component Category Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Category Element</em>'.
	 * @see org.nasdanika.engineering.ComponentCategoryElement
	 * @generated
	 */
	EClass getComponentCategoryElement();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.Engineer <em>Engineer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Engineer</em>'.
	 * @see org.nasdanika.engineering.Engineer
	 * @generated
	 */
	EClass getEngineer();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.IssueType <em>Issue Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Issue Type</em>'.
	 * @see org.nasdanika.engineering.IssueType
	 * @generated
	 */
	EClass getIssueType();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.engineering.IssueType#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Children</em>'.
	 * @see org.nasdanika.engineering.IssueType#getChildren()
	 * @see #getIssueType()
	 * @generated
	 */
	EReference getIssueType_Children();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.IssueResolution <em>Issue Resolution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Issue Resolution</em>'.
	 * @see org.nasdanika.engineering.IssueResolution
	 * @generated
	 */
	EClass getIssueResolution();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.engineering.IssueResolution#isCompleted <em>Completed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Completed</em>'.
	 * @see org.nasdanika.engineering.IssueResolution#isCompleted()
	 * @see #getIssueResolution()
	 * @generated
	 */
	EAttribute getIssueResolution_Completed();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.IssueCategory <em>Issue Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Issue Category</em>'.
	 * @see org.nasdanika.engineering.IssueCategory
	 * @generated
	 */
	EClass getIssueCategory();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.IssueCategory#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see org.nasdanika.engineering.IssueCategory#getChildren()
	 * @see #getIssueCategory()
	 * @generated
	 */
	EReference getIssueCategory_Children();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.Issue <em>Issue</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Issue</em>'.
	 * @see org.nasdanika.engineering.Issue
	 * @generated
	 */
	EClass getIssue();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.engineering.Issue#getAssignedTo <em>Assigned To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Assigned To</em>'.
	 * @see org.nasdanika.engineering.Issue#getAssignedTo()
	 * @see #getIssue()
	 * @generated
	 */
	EReference getIssue_AssignedTo();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.engineering.Issue#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size</em>'.
	 * @see org.nasdanika.engineering.Issue#getSize()
	 * @see #getIssue()
	 * @generated
	 */
	EAttribute getIssue_Size();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.engineering.Issue#getCost <em>Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cost</em>'.
	 * @see org.nasdanika.engineering.Issue#getCost()
	 * @see #getIssue()
	 * @generated
	 */
	EAttribute getIssue_Cost();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.engineering.Issue#getBenefit <em>Benefit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Benefit</em>'.
	 * @see org.nasdanika.engineering.Issue#getBenefit()
	 * @see #getIssue()
	 * @generated
	 */
	EAttribute getIssue_Benefit();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.Issue#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see org.nasdanika.engineering.Issue#getChildren()
	 * @see #getIssue()
	 * @generated
	 */
	EReference getIssue_Children();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.engineering.Issue#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.nasdanika.engineering.Issue#getType()
	 * @see #getIssue()
	 * @generated
	 */
	EReference getIssue_Type();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.engineering.Issue#getStatus <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Status</em>'.
	 * @see org.nasdanika.engineering.Issue#getStatus()
	 * @see #getIssue()
	 * @generated
	 */
	EReference getIssue_Status();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.engineering.Issue#getResolution <em>Resolution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Resolution</em>'.
	 * @see org.nasdanika.engineering.Issue#getResolution()
	 * @see #getIssue()
	 * @generated
	 */
	EReference getIssue_Resolution();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.engineering.Issue#getCategories <em>Categories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Categories</em>'.
	 * @see org.nasdanika.engineering.Issue#getCategories()
	 * @see #getIssue()
	 * @generated
	 */
	EReference getIssue_Categories();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.Issue#getNotes <em>Notes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Notes</em>'.
	 * @see org.nasdanika.engineering.Issue#getNotes()
	 * @see #getIssue()
	 * @generated
	 */
	EReference getIssue_Notes();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.engineering.Issue#getReleases <em>Releases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Releases</em>'.
	 * @see org.nasdanika.engineering.Issue#getReleases()
	 * @see #getIssue()
	 * @generated
	 */
	EReference getIssue_Releases();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.Issue#getRelationships <em>Relationships</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Relationships</em>'.
	 * @see org.nasdanika.engineering.Issue#getRelationships()
	 * @see #getIssue()
	 * @generated
	 */
	EReference getIssue_Relationships();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.engineering.Issue#getRequires <em>Requires</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Requires</em>'.
	 * @see org.nasdanika.engineering.Issue#getRequires()
	 * @see #getIssue()
	 * @generated
	 */
	EReference getIssue_Requires();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.engineering.Issue#isActionable <em>Actionable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actionable</em>'.
	 * @see org.nasdanika.engineering.Issue#isActionable()
	 * @see #getIssue()
	 * @generated
	 */
	EAttribute getIssue_Actionable();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.engineering.Issue#getPlannedFor <em>Planned For</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Planned For</em>'.
	 * @see org.nasdanika.engineering.Issue#getPlannedFor()
	 * @see #getIssue()
	 * @generated
	 */
	EReference getIssue_PlannedFor();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.Increment <em>Increment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Increment</em>'.
	 * @see org.nasdanika.engineering.Increment
	 * @generated
	 */
	EClass getIncrement();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.Increment#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see org.nasdanika.engineering.Increment#getChildren()
	 * @see #getIncrement()
	 * @generated
	 */
	EReference getIncrement_Children();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.engineering.Increment#getStart <em>Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start</em>'.
	 * @see org.nasdanika.engineering.Increment#getStart()
	 * @see #getIncrement()
	 * @generated
	 */
	EAttribute getIncrement_Start();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.engineering.Increment#getEnd <em>End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>End</em>'.
	 * @see org.nasdanika.engineering.Increment#getEnd()
	 * @see #getIncrement()
	 * @generated
	 */
	EAttribute getIncrement_End();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.EngineeringOrganizationalUnit <em>Organizational Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Organizational Unit</em>'.
	 * @see org.nasdanika.engineering.EngineeringOrganizationalUnit
	 * @generated
	 */
	EClass getEngineeringOrganizationalUnit();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.EngineeringOrganizationalUnit#getPortfolio <em>Portfolio</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Portfolio</em>'.
	 * @see org.nasdanika.engineering.EngineeringOrganizationalUnit#getPortfolio()
	 * @see #getEngineeringOrganizationalUnit()
	 * @generated
	 */
	EReference getEngineeringOrganizationalUnit_Portfolio();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.EngineeringOrganizationalUnit#getTargetAudiences <em>Target Audiences</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Target Audiences</em>'.
	 * @see org.nasdanika.engineering.EngineeringOrganizationalUnit#getTargetAudiences()
	 * @see #getEngineeringOrganizationalUnit()
	 * @generated
	 */
	EReference getEngineeringOrganizationalUnit_TargetAudiences();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.EngineeringOrganizationalUnit#getIssueTypes <em>Issue Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Issue Types</em>'.
	 * @see org.nasdanika.engineering.EngineeringOrganizationalUnit#getIssueTypes()
	 * @see #getEngineeringOrganizationalUnit()
	 * @generated
	 */
	EReference getEngineeringOrganizationalUnit_IssueTypes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.EngineeringOrganizationalUnit#getIssueResolutions <em>Issue Resolutions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Issue Resolutions</em>'.
	 * @see org.nasdanika.engineering.EngineeringOrganizationalUnit#getIssueResolutions()
	 * @see #getEngineeringOrganizationalUnit()
	 * @generated
	 */
	EReference getEngineeringOrganizationalUnit_IssueResolutions();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.EngineeringOrganizationalUnit#getIssueCategories <em>Issue Categories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Issue Categories</em>'.
	 * @see org.nasdanika.engineering.EngineeringOrganizationalUnit#getIssueCategories()
	 * @see #getEngineeringOrganizationalUnit()
	 * @generated
	 */
	EReference getEngineeringOrganizationalUnit_IssueCategories();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.EngineeringOrganizationalUnit#getIssueStatuses <em>Issue Statuses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Issue Statuses</em>'.
	 * @see org.nasdanika.engineering.EngineeringOrganizationalUnit#getIssueStatuses()
	 * @see #getEngineeringOrganizationalUnit()
	 * @generated
	 */
	EReference getEngineeringOrganizationalUnit_IssueStatuses();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.EngineeringOrganizationalUnit#getIssueRelationshipTypes <em>Issue Relationship Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Issue Relationship Types</em>'.
	 * @see org.nasdanika.engineering.EngineeringOrganizationalUnit#getIssueRelationshipTypes()
	 * @see #getEngineeringOrganizationalUnit()
	 * @generated
	 */
	EReference getEngineeringOrganizationalUnit_IssueRelationshipTypes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.EngineeringOrganizationalUnit#getIncrements <em>Increments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Increments</em>'.
	 * @see org.nasdanika.engineering.EngineeringOrganizationalUnit#getIncrements()
	 * @see #getEngineeringOrganizationalUnit()
	 * @generated
	 */
	EReference getEngineeringOrganizationalUnit_Increments();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.EngineeringOrganizationalUnit#getFeatureTypes <em>Feature Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Feature Types</em>'.
	 * @see org.nasdanika.engineering.EngineeringOrganizationalUnit#getFeatureTypes()
	 * @see #getEngineeringOrganizationalUnit()
	 * @generated
	 */
	EReference getEngineeringOrganizationalUnit_FeatureTypes();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.EngineeringOrganization <em>Organization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Organization</em>'.
	 * @see org.nasdanika.engineering.EngineeringOrganization
	 * @generated
	 */
	EClass getEngineeringOrganization();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.Release <em>Release</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Release</em>'.
	 * @see org.nasdanika.engineering.Release
	 * @generated
	 */
	EClass getRelease();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.engineering.Release#getRequires <em>Requires</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Requires</em>'.
	 * @see org.nasdanika.engineering.Release#getRequires()
	 * @see #getRelease()
	 * @generated
	 */
	EReference getRelease_Requires();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.engineering.Release#getIncludes <em>Includes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Includes</em>'.
	 * @see org.nasdanika.engineering.Release#getIncludes()
	 * @see #getRelease()
	 * @generated
	 */
	EReference getRelease_Includes();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.engineering.Release#getDate <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date</em>'.
	 * @see org.nasdanika.engineering.Release#getDate()
	 * @see #getRelease()
	 * @generated
	 */
	EAttribute getRelease_Date();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.engineering.Release#getPlannedFor <em>Planned For</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Planned For</em>'.
	 * @see org.nasdanika.engineering.Release#getPlannedFor()
	 * @see #getRelease()
	 * @generated
	 */
	EReference getRelease_PlannedFor();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.engineering.Release#isReleased <em>Released</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Released</em>'.
	 * @see org.nasdanika.engineering.Release#isReleased()
	 * @see #getRelease()
	 * @generated
	 */
	EAttribute getRelease_Released();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.Objective <em>Objective</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Objective</em>'.
	 * @see org.nasdanika.engineering.Objective
	 * @generated
	 */
	EClass getObjective();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.engineering.Objective#getIncrement <em>Increment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Increment</em>'.
	 * @see org.nasdanika.engineering.Objective#getIncrement()
	 * @see #getObjective()
	 * @generated
	 */
	EReference getObjective_Increment();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.Objective#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see org.nasdanika.engineering.Objective#getChildren()
	 * @see #getObjective()
	 * @generated
	 */
	EReference getObjective_Children();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.Objective#getKeyResults <em>Key Results</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Key Results</em>'.
	 * @see org.nasdanika.engineering.Objective#getKeyResults()
	 * @see #getObjective()
	 * @generated
	 */
	EReference getObjective_KeyResults();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.engineering.Objective#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent</em>'.
	 * @see org.nasdanika.engineering.Objective#getParent()
	 * @see #getObjective()
	 * @generated
	 */
	EReference getObjective_Parent();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.KeyResult <em>Key Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Key Result</em>'.
	 * @see org.nasdanika.engineering.KeyResult
	 * @generated
	 */
	EClass getKeyResult();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.Product <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product</em>'.
	 * @see org.nasdanika.engineering.Product
	 * @generated
	 */
	EClass getProduct();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.Product#getEditions <em>Editions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Editions</em>'.
	 * @see org.nasdanika.engineering.Product#getEditions()
	 * @see #getProduct()
	 * @generated
	 */
	EReference getProduct_Editions();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.engineering.Product#getFeatures <em>Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Features</em>'.
	 * @see org.nasdanika.engineering.Product#getFeatures()
	 * @see #getProduct()
	 * @generated
	 */
	EReference getProduct_Features();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.Offering <em>Offering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Offering</em>'.
	 * @see org.nasdanika.engineering.Offering
	 * @generated
	 */
	EClass getOffering();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.engineering.Offering#getTargetAudiences <em>Target Audiences</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Target Audiences</em>'.
	 * @see org.nasdanika.engineering.Offering#getTargetAudiences()
	 * @see #getOffering()
	 * @generated
	 */
	EReference getOffering_TargetAudiences();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.Edition <em>Edition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Edition</em>'.
	 * @see org.nasdanika.engineering.Edition
	 * @generated
	 */
	EClass getEdition();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.engineering.Edition#getBases <em>Bases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Bases</em>'.
	 * @see org.nasdanika.engineering.Edition#getBases()
	 * @see #getEdition()
	 * @generated
	 */
	EReference getEdition_Bases();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.engineering.Edition#getFeatures <em>Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Features</em>'.
	 * @see org.nasdanika.engineering.Edition#getFeatures()
	 * @see #getEdition()
	 * @generated
	 */
	EReference getEdition_Features();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.FeatureType <em>Feature Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Type</em>'.
	 * @see org.nasdanika.engineering.FeatureType
	 * @generated
	 */
	EClass getFeatureType();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.FeatureCategoryElement <em>Feature Category Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Category Element</em>'.
	 * @see org.nasdanika.engineering.FeatureCategoryElement
	 * @generated
	 */
	EClass getFeatureCategoryElement();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.FeatureCategory <em>Feature Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Category</em>'.
	 * @see org.nasdanika.engineering.FeatureCategory
	 * @generated
	 */
	EClass getFeatureCategory();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.FeatureCategory#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see org.nasdanika.engineering.FeatureCategory#getElements()
	 * @see #getFeatureCategory()
	 * @generated
	 */
	EReference getFeatureCategory_Elements();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.Feature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature</em>'.
	 * @see org.nasdanika.engineering.Feature
	 * @generated
	 */
	EClass getFeature();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.engineering.Feature#getPlannedFor <em>Planned For</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Planned For</em>'.
	 * @see org.nasdanika.engineering.Feature#getPlannedFor()
	 * @see #getFeature()
	 * @generated
	 */
	EReference getFeature_PlannedFor();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.engineering.Feature#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.nasdanika.engineering.Feature#getType()
	 * @see #getFeature()
	 * @generated
	 */
	EReference getFeature_Type();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.engineering.Feature#getRequires <em>Requires</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Requires</em>'.
	 * @see org.nasdanika.engineering.Feature#getRequires()
	 * @see #getFeature()
	 * @generated
	 */
	EReference getFeature_Requires();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.Component <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component</em>'.
	 * @see org.nasdanika.engineering.Component
	 * @generated
	 */
	EClass getComponent();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.Component#getComponents <em>Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Components</em>'.
	 * @see org.nasdanika.engineering.Component#getComponents()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Components();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.Persona <em>Persona</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Persona</em>'.
	 * @see org.nasdanika.engineering.Persona
	 * @generated
	 */
	EClass getPersona();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.Persona#getNeeds <em>Needs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Needs</em>'.
	 * @see org.nasdanika.engineering.Persona#getNeeds()
	 * @see #getPersona()
	 * @generated
	 */
	EReference getPersona_Needs();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.engineering.Persona#getExtends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Extends</em>'.
	 * @see org.nasdanika.engineering.Persona#getExtends()
	 * @see #getPersona()
	 * @generated
	 */
	EReference getPersona_Extends();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.NeedCategoryElement <em>Need Category Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Need Category Element</em>'.
	 * @see org.nasdanika.engineering.NeedCategoryElement
	 * @generated
	 */
	EClass getNeedCategoryElement();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.NeedCategory <em>Need Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Need Category</em>'.
	 * @see org.nasdanika.engineering.NeedCategory
	 * @generated
	 */
	EClass getNeedCategory();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.NeedCategory#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see org.nasdanika.engineering.NeedCategory#getElements()
	 * @see #getNeedCategory()
	 * @generated
	 */
	EReference getNeedCategory_Elements();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.Need <em>Need</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Need</em>'.
	 * @see org.nasdanika.engineering.Need
	 * @generated
	 */
	EClass getNeed();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.engineering.Need#getScenarios <em>Scenarios</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Scenarios</em>'.
	 * @see org.nasdanika.engineering.Need#getScenarios()
	 * @see #getNeed()
	 * @generated
	 */
	EReference getNeed_Scenarios();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.Scenario <em>Scenario</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scenario</em>'.
	 * @see org.nasdanika.engineering.Scenario
	 * @generated
	 */
	EClass getScenario();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.engineering.Scenario#getOfferings <em>Offerings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Offerings</em>'.
	 * @see org.nasdanika.engineering.Scenario#getOfferings()
	 * @see #getScenario()
	 * @generated
	 */
	EReference getScenario_Offerings();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.Criterion <em>Criterion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Criterion</em>'.
	 * @see org.nasdanika.engineering.Criterion
	 * @generated
	 */
	EClass getCriterion();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.Comparison <em>Comparison</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comparison</em>'.
	 * @see org.nasdanika.engineering.Comparison
	 * @generated
	 */
	EClass getComparison();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.Risk <em>Risk</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Risk</em>'.
	 * @see org.nasdanika.engineering.Risk
	 * @generated
	 */
	EClass getRisk();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.IssueStatus <em>Issue Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Issue Status</em>'.
	 * @see org.nasdanika.engineering.IssueStatus
	 * @generated
	 */
	EClass getIssueStatus();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.engineering.IssueStatus#getTransitions <em>Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Transitions</em>'.
	 * @see org.nasdanika.engineering.IssueStatus#getTransitions()
	 * @see #getIssueStatus()
	 * @generated
	 */
	EReference getIssueStatus_Transitions();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.IssueNote <em>Issue Note</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Issue Note</em>'.
	 * @see org.nasdanika.engineering.IssueNote
	 * @generated
	 */
	EClass getIssueNote();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.engineering.IssueNote#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.nasdanika.engineering.IssueNote#getDescription()
	 * @see #getIssueNote()
	 * @generated
	 */
	EAttribute getIssueNote_Description();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.IssueRelationshipType <em>Issue Relationship Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Issue Relationship Type</em>'.
	 * @see org.nasdanika.engineering.IssueRelationshipType
	 * @generated
	 */
	EClass getIssueRelationshipType();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.engineering.IssueRelationshipType#isBlocks <em>Blocks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Blocks</em>'.
	 * @see org.nasdanika.engineering.IssueRelationshipType#isBlocks()
	 * @see #getIssueRelationshipType()
	 * @generated
	 */
	EAttribute getIssueRelationshipType_Blocks();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.IssueRelationship <em>Issue Relationship</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Issue Relationship</em>'.
	 * @see org.nasdanika.engineering.IssueRelationship
	 * @generated
	 */
	EClass getIssueRelationship();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.engineering.IssueRelationship#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.nasdanika.engineering.IssueRelationship#getType()
	 * @see #getIssueRelationship()
	 * @generated
	 */
	EReference getIssueRelationship_Type();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.engineering.IssueRelationship#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see org.nasdanika.engineering.IssueRelationship#getTarget()
	 * @see #getIssueRelationship()
	 * @generated
	 */
	EReference getIssueRelationship_Target();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EngineeringFactory getEngineeringFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.ComponentCategoryElement <em>Component Category Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.ComponentCategoryElement
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getComponentCategoryElement()
		 * @generated
		 */
		EClass COMPONENT_CATEGORY_ELEMENT = eINSTANCE.getComponentCategoryElement();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.AbstractComponent <em>Abstract Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.AbstractComponent
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getAbstractComponent()
		 * @generated
		 */
		EClass ABSTRACT_COMPONENT = eINSTANCE.getAbstractComponent();
		/**
		 * The meta object literal for the '<em><b>Owners</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_COMPONENT__OWNERS = eINSTANCE.getAbstractComponent_Owners();
		/**
		 * The meta object literal for the '<em><b>Issues</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_COMPONENT__ISSUES = eINSTANCE.getAbstractComponent_Issues();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.ComponentCategoryImpl <em>Component Category</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.ComponentCategoryImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getComponentCategory()
		 * @generated
		 */
		EClass COMPONENT_CATEGORY = eINSTANCE.getComponentCategory();
		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_CATEGORY__ELEMENTS = eINSTANCE.getComponentCategory_Elements();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.ComponentReference <em>Component Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.ComponentReference
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getComponentReference()
		 * @generated
		 */
		EClass COMPONENT_REFERENCE = eINSTANCE.getComponentReference();
		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_REFERENCE__TARGET = eINSTANCE.getComponentReference_Target();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.AbstractEngineerImpl <em>Abstract Engineer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.AbstractEngineerImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getAbstractEngineer()
		 * @generated
		 */
		EClass ABSTRACT_ENGINEER = eINSTANCE.getAbstractEngineer();
		/**
		 * The meta object literal for the '<em><b>Issues</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_ENGINEER__ISSUES = eINSTANCE.getAbstractEngineer_Issues();
		/**
		 * The meta object literal for the '<em><b>Objectives</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_ENGINEER__OBJECTIVES = eINSTANCE.getAbstractEngineer_Objectives();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.EngineerImpl <em>Engineer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.EngineerImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getEngineer()
		 * @generated
		 */
		EClass ENGINEER = eINSTANCE.getEngineer();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.IssueTypeImpl <em>Issue Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.IssueTypeImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIssueType()
		 * @generated
		 */
		EClass ISSUE_TYPE = eINSTANCE.getIssueType();
		/**
		 * The meta object literal for the '<em><b>Children</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ISSUE_TYPE__CHILDREN = eINSTANCE.getIssueType_Children();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.IssueResolutionImpl <em>Issue Resolution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.IssueResolutionImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIssueResolution()
		 * @generated
		 */
		EClass ISSUE_RESOLUTION = eINSTANCE.getIssueResolution();
		/**
		 * The meta object literal for the '<em><b>Completed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ISSUE_RESOLUTION__COMPLETED = eINSTANCE.getIssueResolution_Completed();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.IssueCategoryImpl <em>Issue Category</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.IssueCategoryImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIssueCategory()
		 * @generated
		 */
		EClass ISSUE_CATEGORY = eINSTANCE.getIssueCategory();
		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ISSUE_CATEGORY__CHILDREN = eINSTANCE.getIssueCategory_Children();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.IssueImpl <em>Issue</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.IssueImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIssue()
		 * @generated
		 */
		EClass ISSUE = eINSTANCE.getIssue();
		/**
		 * The meta object literal for the '<em><b>Assigned To</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ISSUE__ASSIGNED_TO = eINSTANCE.getIssue_AssignedTo();
		/**
		 * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ISSUE__SIZE = eINSTANCE.getIssue_Size();
		/**
		 * The meta object literal for the '<em><b>Cost</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ISSUE__COST = eINSTANCE.getIssue_Cost();
		/**
		 * The meta object literal for the '<em><b>Benefit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ISSUE__BENEFIT = eINSTANCE.getIssue_Benefit();
		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ISSUE__CHILDREN = eINSTANCE.getIssue_Children();
		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ISSUE__TYPE = eINSTANCE.getIssue_Type();
		/**
		 * The meta object literal for the '<em><b>Status</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ISSUE__STATUS = eINSTANCE.getIssue_Status();
		/**
		 * The meta object literal for the '<em><b>Resolution</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ISSUE__RESOLUTION = eINSTANCE.getIssue_Resolution();
		/**
		 * The meta object literal for the '<em><b>Categories</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ISSUE__CATEGORIES = eINSTANCE.getIssue_Categories();
		/**
		 * The meta object literal for the '<em><b>Notes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ISSUE__NOTES = eINSTANCE.getIssue_Notes();
		/**
		 * The meta object literal for the '<em><b>Releases</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ISSUE__RELEASES = eINSTANCE.getIssue_Releases();
		/**
		 * The meta object literal for the '<em><b>Relationships</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ISSUE__RELATIONSHIPS = eINSTANCE.getIssue_Relationships();
		/**
		 * The meta object literal for the '<em><b>Requires</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ISSUE__REQUIRES = eINSTANCE.getIssue_Requires();
		/**
		 * The meta object literal for the '<em><b>Actionable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ISSUE__ACTIONABLE = eINSTANCE.getIssue_Actionable();
		/**
		 * The meta object literal for the '<em><b>Planned For</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ISSUE__PLANNED_FOR = eINSTANCE.getIssue_PlannedFor();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.IncrementImpl <em>Increment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.IncrementImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIncrement()
		 * @generated
		 */
		EClass INCREMENT = eINSTANCE.getIncrement();
		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INCREMENT__CHILDREN = eINSTANCE.getIncrement_Children();
		/**
		 * The meta object literal for the '<em><b>Start</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INCREMENT__START = eINSTANCE.getIncrement_Start();
		/**
		 * The meta object literal for the '<em><b>End</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INCREMENT__END = eINSTANCE.getIncrement_End();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.EngineeringOrganizationalUnitImpl <em>Organizational Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.EngineeringOrganizationalUnitImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getEngineeringOrganizationalUnit()
		 * @generated
		 */
		EClass ENGINEERING_ORGANIZATIONAL_UNIT = eINSTANCE.getEngineeringOrganizationalUnit();
		/**
		 * The meta object literal for the '<em><b>Portfolio</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENGINEERING_ORGANIZATIONAL_UNIT__PORTFOLIO = eINSTANCE.getEngineeringOrganizationalUnit_Portfolio();
		/**
		 * The meta object literal for the '<em><b>Target Audiences</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENGINEERING_ORGANIZATIONAL_UNIT__TARGET_AUDIENCES = eINSTANCE.getEngineeringOrganizationalUnit_TargetAudiences();
		/**
		 * The meta object literal for the '<em><b>Issue Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_TYPES = eINSTANCE.getEngineeringOrganizationalUnit_IssueTypes();
		/**
		 * The meta object literal for the '<em><b>Issue Resolutions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RESOLUTIONS = eINSTANCE.getEngineeringOrganizationalUnit_IssueResolutions();
		/**
		 * The meta object literal for the '<em><b>Issue Categories</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_CATEGORIES = eINSTANCE.getEngineeringOrganizationalUnit_IssueCategories();
		/**
		 * The meta object literal for the '<em><b>Issue Statuses</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_STATUSES = eINSTANCE.getEngineeringOrganizationalUnit_IssueStatuses();
		/**
		 * The meta object literal for the '<em><b>Issue Relationship Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RELATIONSHIP_TYPES = eINSTANCE.getEngineeringOrganizationalUnit_IssueRelationshipTypes();
		/**
		 * The meta object literal for the '<em><b>Increments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENGINEERING_ORGANIZATIONAL_UNIT__INCREMENTS = eINSTANCE.getEngineeringOrganizationalUnit_Increments();
		/**
		 * The meta object literal for the '<em><b>Feature Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENGINEERING_ORGANIZATIONAL_UNIT__FEATURE_TYPES = eINSTANCE.getEngineeringOrganizationalUnit_FeatureTypes();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.EngineeringOrganizationImpl <em>Organization</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.EngineeringOrganizationImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getEngineeringOrganization()
		 * @generated
		 */
		EClass ENGINEERING_ORGANIZATION = eINSTANCE.getEngineeringOrganization();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.ReleaseImpl <em>Release</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.ReleaseImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getRelease()
		 * @generated
		 */
		EClass RELEASE = eINSTANCE.getRelease();
		/**
		 * The meta object literal for the '<em><b>Requires</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELEASE__REQUIRES = eINSTANCE.getRelease_Requires();
		/**
		 * The meta object literal for the '<em><b>Includes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELEASE__INCLUDES = eINSTANCE.getRelease_Includes();
		/**
		 * The meta object literal for the '<em><b>Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELEASE__DATE = eINSTANCE.getRelease_Date();
		/**
		 * The meta object literal for the '<em><b>Planned For</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELEASE__PLANNED_FOR = eINSTANCE.getRelease_PlannedFor();
		/**
		 * The meta object literal for the '<em><b>Released</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELEASE__RELEASED = eINSTANCE.getRelease_Released();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.ObjectiveImpl <em>Objective</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.ObjectiveImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getObjective()
		 * @generated
		 */
		EClass OBJECTIVE = eINSTANCE.getObjective();
		/**
		 * The meta object literal for the '<em><b>Increment</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OBJECTIVE__INCREMENT = eINSTANCE.getObjective_Increment();
		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OBJECTIVE__CHILDREN = eINSTANCE.getObjective_Children();
		/**
		 * The meta object literal for the '<em><b>Key Results</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OBJECTIVE__KEY_RESULTS = eINSTANCE.getObjective_KeyResults();
		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OBJECTIVE__PARENT = eINSTANCE.getObjective_Parent();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.KeyResultImpl <em>Key Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.KeyResultImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getKeyResult()
		 * @generated
		 */
		EClass KEY_RESULT = eINSTANCE.getKeyResult();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.ProductImpl <em>Product</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.ProductImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getProduct()
		 * @generated
		 */
		EClass PRODUCT = eINSTANCE.getProduct();
		/**
		 * The meta object literal for the '<em><b>Editions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT__EDITIONS = eINSTANCE.getProduct_Editions();
		/**
		 * The meta object literal for the '<em><b>Features</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT__FEATURES = eINSTANCE.getProduct_Features();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.OfferingImpl <em>Offering</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.OfferingImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getOffering()
		 * @generated
		 */
		EClass OFFERING = eINSTANCE.getOffering();
		/**
		 * The meta object literal for the '<em><b>Target Audiences</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OFFERING__TARGET_AUDIENCES = eINSTANCE.getOffering_TargetAudiences();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.EditionImpl <em>Edition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.EditionImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getEdition()
		 * @generated
		 */
		EClass EDITION = eINSTANCE.getEdition();
		/**
		 * The meta object literal for the '<em><b>Bases</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDITION__BASES = eINSTANCE.getEdition_Bases();
		/**
		 * The meta object literal for the '<em><b>Features</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDITION__FEATURES = eINSTANCE.getEdition_Features();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.FeatureTypeImpl <em>Feature Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.FeatureTypeImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getFeatureType()
		 * @generated
		 */
		EClass FEATURE_TYPE = eINSTANCE.getFeatureType();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.FeatureCategoryElement <em>Feature Category Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.FeatureCategoryElement
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getFeatureCategoryElement()
		 * @generated
		 */
		EClass FEATURE_CATEGORY_ELEMENT = eINSTANCE.getFeatureCategoryElement();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.FeatureCategoryImpl <em>Feature Category</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.FeatureCategoryImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getFeatureCategory()
		 * @generated
		 */
		EClass FEATURE_CATEGORY = eINSTANCE.getFeatureCategory();
		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE_CATEGORY__ELEMENTS = eINSTANCE.getFeatureCategory_Elements();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.FeatureImpl <em>Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.FeatureImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getFeature()
		 * @generated
		 */
		EClass FEATURE = eINSTANCE.getFeature();
		/**
		 * The meta object literal for the '<em><b>Planned For</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE__PLANNED_FOR = eINSTANCE.getFeature_PlannedFor();
		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE__TYPE = eINSTANCE.getFeature_Type();
		/**
		 * The meta object literal for the '<em><b>Requires</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE__REQUIRES = eINSTANCE.getFeature_Requires();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.ComponentImpl <em>Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.ComponentImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getComponent()
		 * @generated
		 */
		EClass COMPONENT = eINSTANCE.getComponent();
		/**
		 * The meta object literal for the '<em><b>Components</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__COMPONENTS = eINSTANCE.getComponent_Components();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.PersonaImpl <em>Persona</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.PersonaImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getPersona()
		 * @generated
		 */
		EClass PERSONA = eINSTANCE.getPersona();
		/**
		 * The meta object literal for the '<em><b>Needs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSONA__NEEDS = eINSTANCE.getPersona_Needs();
		/**
		 * The meta object literal for the '<em><b>Extends</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSONA__EXTENDS = eINSTANCE.getPersona_Extends();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.NeedCategoryElement <em>Need Category Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.NeedCategoryElement
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getNeedCategoryElement()
		 * @generated
		 */
		EClass NEED_CATEGORY_ELEMENT = eINSTANCE.getNeedCategoryElement();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.NeedCategoryImpl <em>Need Category</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.NeedCategoryImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getNeedCategory()
		 * @generated
		 */
		EClass NEED_CATEGORY = eINSTANCE.getNeedCategory();
		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NEED_CATEGORY__ELEMENTS = eINSTANCE.getNeedCategory_Elements();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.NeedImpl <em>Need</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.NeedImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getNeed()
		 * @generated
		 */
		EClass NEED = eINSTANCE.getNeed();
		/**
		 * The meta object literal for the '<em><b>Scenarios</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NEED__SCENARIOS = eINSTANCE.getNeed_Scenarios();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.ScenarioImpl <em>Scenario</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.ScenarioImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getScenario()
		 * @generated
		 */
		EClass SCENARIO = eINSTANCE.getScenario();
		/**
		 * The meta object literal for the '<em><b>Offerings</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO__OFFERINGS = eINSTANCE.getScenario_Offerings();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.CriterionImpl <em>Criterion</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.CriterionImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getCriterion()
		 * @generated
		 */
		EClass CRITERION = eINSTANCE.getCriterion();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.ComparisonImpl <em>Comparison</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.ComparisonImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getComparison()
		 * @generated
		 */
		EClass COMPARISON = eINSTANCE.getComparison();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.RiskImpl <em>Risk</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.RiskImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getRisk()
		 * @generated
		 */
		EClass RISK = eINSTANCE.getRisk();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.IssueStatusImpl <em>Issue Status</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.IssueStatusImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIssueStatus()
		 * @generated
		 */
		EClass ISSUE_STATUS = eINSTANCE.getIssueStatus();
		/**
		 * The meta object literal for the '<em><b>Transitions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ISSUE_STATUS__TRANSITIONS = eINSTANCE.getIssueStatus_Transitions();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.IssueNoteImpl <em>Issue Note</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.IssueNoteImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIssueNote()
		 * @generated
		 */
		EClass ISSUE_NOTE = eINSTANCE.getIssueNote();
		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ISSUE_NOTE__DESCRIPTION = eINSTANCE.getIssueNote_Description();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.IssueRelationshipTypeImpl <em>Issue Relationship Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.IssueRelationshipTypeImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIssueRelationshipType()
		 * @generated
		 */
		EClass ISSUE_RELATIONSHIP_TYPE = eINSTANCE.getIssueRelationshipType();
		/**
		 * The meta object literal for the '<em><b>Blocks</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ISSUE_RELATIONSHIP_TYPE__BLOCKS = eINSTANCE.getIssueRelationshipType_Blocks();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.IssueRelationshipImpl <em>Issue Relationship</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.IssueRelationshipImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIssueRelationship()
		 * @generated
		 */
		EClass ISSUE_RELATIONSHIP = eINSTANCE.getIssueRelationship();
		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ISSUE_RELATIONSHIP__TYPE = eINSTANCE.getIssueRelationship_Type();
		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ISSUE_RELATIONSHIP__TARGET = eINSTANCE.getIssueRelationship_Target();

	}

} //EngineeringPackage
