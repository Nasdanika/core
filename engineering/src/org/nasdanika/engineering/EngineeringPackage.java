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
	 * The meta object id for the '{@link org.nasdanika.engineering.AbstractComponent <em>Abstract Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.AbstractComponent
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getAbstractComponent()
	 * @generated
	 */
	int ABSTRACT_COMPONENT = 0;

	/**
	 * The feature id for the '<em><b>Owners</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_COMPONENT__OWNERS = 0;

	/**
	 * The feature id for the '<em><b>Issues</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_COMPONENT__ISSUES = 1;

	/**
	 * The number of structural features of the '<em>Abstract Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_COMPONENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Abstract Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_COMPONENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.AbstractEngineerImpl <em>Abstract Engineer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.AbstractEngineerImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getAbstractEngineer()
	 * @generated
	 */
	int ABSTRACT_ENGINEER = 1;

	/**
	 * The number of structural features of the '<em>Abstract Engineer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ENGINEER_FEATURE_COUNT = 0;

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
	int ENGINEER = 2;

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
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEER__NAME = PartyPackage.ROLE__NAME;

	/**
	 * The feature id for the '<em><b>Members</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEER__MEMBERS = PartyPackage.ROLE__MEMBERS;

	/**
	 * The number of structural features of the '<em>Engineer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEER_FEATURE_COUNT = PartyPackage.ROLE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Engineer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEER_OPERATION_COUNT = PartyPackage.ROLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.IssueTypeImpl <em>Issue Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.IssueTypeImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIssueType()
	 * @generated
	 */
	int ISSUE_TYPE = 3;

	/**
	 * The number of structural features of the '<em>Issue Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_TYPE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Issue Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.IssueResolutionImpl <em>Issue Resolution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.IssueResolutionImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIssueResolution()
	 * @generated
	 */
	int ISSUE_RESOLUTION = 4;

	/**
	 * The number of structural features of the '<em>Issue Resolution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_RESOLUTION_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Issue Resolution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_RESOLUTION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.IssueCategoryImpl <em>Issue Category</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.IssueCategoryImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIssueCategory()
	 * @generated
	 */
	int ISSUE_CATEGORY = 5;

	/**
	 * The number of structural features of the '<em>Issue Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_CATEGORY_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Issue Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_CATEGORY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.IssueImpl <em>Issue</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.IssueImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIssue()
	 * @generated
	 */
	int ISSUE = 8;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.IncrementImpl <em>Increment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.IncrementImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIncrement()
	 * @generated
	 */
	int INCREMENT = 9;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.EngineeringOrganizationalUnitImpl <em>Organizational Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.EngineeringOrganizationalUnitImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getEngineeringOrganizationalUnit()
	 * @generated
	 */
	int ENGINEERING_ORGANIZATIONAL_UNIT = 10;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.ReleaseImpl <em>Release</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.ReleaseImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getRelease()
	 * @generated
	 */
	int RELEASE = 11;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.ObjectiveImpl <em>Objective</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.ObjectiveImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getObjective()
	 * @generated
	 */
	int OBJECTIVE = 12;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.KeyResultImpl <em>Key Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.KeyResultImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getKeyResult()
	 * @generated
	 */
	int KEY_RESULT = 13;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.ProductImpl <em>Product</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.ProductImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getProduct()
	 * @generated
	 */
	int PRODUCT = 14;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.OfferingImpl <em>Offering</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.OfferingImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getOffering()
	 * @generated
	 */
	int OFFERING = 15;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.EditionImpl <em>Edition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.EditionImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getEdition()
	 * @generated
	 */
	int EDITION = 16;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.FeatureImpl <em>Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.FeatureImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getFeature()
	 * @generated
	 */
	int FEATURE = 17;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.ComponentImpl <em>Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.ComponentImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getComponent()
	 * @generated
	 */
	int COMPONENT = 18;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.PersonaImpl <em>Persona</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.PersonaImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getPersona()
	 * @generated
	 */
	int PERSONA = 19;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.NeedImpl <em>Need</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.NeedImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getNeed()
	 * @generated
	 */
	int NEED = 20;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.ScenarioImpl <em>Scenario</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.ScenarioImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getScenario()
	 * @generated
	 */
	int SCENARIO = 21;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.PortfolioImpl <em>Portfolio</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.PortfolioImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getPortfolio()
	 * @generated
	 */
	int PORTFOLIO = 22;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.CriterionImpl <em>Criterion</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.CriterionImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getCriterion()
	 * @generated
	 */
	int CRITERION = 23;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.ComparisonImpl <em>Comparison</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.ComparisonImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getComparison()
	 * @generated
	 */
	int COMPARISON = 24;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.RiskImpl <em>Risk</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.RiskImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getRisk()
	 * @generated
	 */
	int RISK = 25;


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
	 * Returns the meta object for class '{@link org.nasdanika.engineering.AbstractEngineer <em>Abstract Engineer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Engineer</em>'.
	 * @see org.nasdanika.engineering.AbstractEngineer
	 * @generated
	 */
	EClass getAbstractEngineer();

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.IssueStatusImpl <em>Issue Status</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.IssueStatusImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIssueStatus()
	 * @generated
	 */
	int ISSUE_STATUS = 6;

	/**
	 * The number of structural features of the '<em>Issue Status</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_STATUS_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Issue Status</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_STATUS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.impl.IssueNoteImpl <em>Issue Note</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.impl.IssueNoteImpl
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIssueNote()
	 * @generated
	 */
	int ISSUE_NOTE = 7;

	/**
	 * The number of structural features of the '<em>Issue Note</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_NOTE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Issue Note</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_NOTE_OPERATION_COUNT = 0;

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
	 * The feature id for the '<em><b>Benefit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE__BENEFIT = NcorePackage.ENTITY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE__CHILDREN = NcorePackage.ENTITY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Planned For</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE__PLANNED_FOR = NcorePackage.ENTITY_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Issue</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISSUE_FEATURE_COUNT = NcorePackage.ENTITY_FEATURE_COUNT + 5;

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
	int INCREMENT__TITLE = NcorePackage.NAMED_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCREMENT__DESCRIPTION = NcorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCREMENT__NAME = NcorePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCREMENT__CHILDREN = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCREMENT__START = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCREMENT__END = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Increment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCREMENT_FEATURE_COUNT = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Increment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCREMENT_OPERATION_COUNT = NcorePackage.NAMED_ELEMENT_OPERATION_COUNT + 0;

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
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATIONAL_UNIT__NAME = PartyPackage.ORGANIZATIONAL_UNIT__NAME;

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
	 * The number of structural features of the '<em>Organizational Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATIONAL_UNIT_FEATURE_COUNT = PartyPackage.ORGANIZATIONAL_UNIT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Organizational Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERING_ORGANIZATIONAL_UNIT_OPERATION_COUNT = PartyPackage.ORGANIZATIONAL_UNIT_OPERATION_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Release</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Release</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE_OPERATION_COUNT = 0;

	/**
	 * The number of structural features of the '<em>Objective</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTIVE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Objective</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTIVE_OPERATION_COUNT = 0;

	/**
	 * The number of structural features of the '<em>Key Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_RESULT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Key Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_RESULT_OPERATION_COUNT = 0;

	/**
	 * The number of structural features of the '<em>Product</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Product</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_OPERATION_COUNT = 0;

	/**
	 * The number of structural features of the '<em>Offering</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERING_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Offering</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERING_OPERATION_COUNT = 0;

	/**
	 * The number of structural features of the '<em>Edition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITION_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Edition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITION_OPERATION_COUNT = 0;

	/**
	 * The number of structural features of the '<em>Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_OPERATION_COUNT = 0;

	/**
	 * The number of structural features of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_OPERATION_COUNT = 0;

	/**
	 * The number of structural features of the '<em>Persona</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONA_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Persona</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONA_OPERATION_COUNT = 0;

	/**
	 * The number of structural features of the '<em>Need</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEED_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Need</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEED_OPERATION_COUNT = 0;

	/**
	 * The number of structural features of the '<em>Scenario</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Scenario</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_OPERATION_COUNT = 0;

	/**
	 * The number of structural features of the '<em>Portfolio</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTFOLIO_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Portfolio</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTFOLIO_OPERATION_COUNT = 0;

	/**
	 * The number of structural features of the '<em>Criterion</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRITERION_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Criterion</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRITERION_OPERATION_COUNT = 0;

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
	 * The number of structural features of the '<em>Risk</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RISK_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Risk</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RISK_OPERATION_COUNT = 0;

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
	 * Returns the meta object for class '{@link org.nasdanika.engineering.IssueResolution <em>Issue Resolution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Issue Resolution</em>'.
	 * @see org.nasdanika.engineering.IssueResolution
	 * @generated
	 */
	EClass getIssueResolution();

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
	 * Returns the meta object for class '{@link org.nasdanika.engineering.Release <em>Release</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Release</em>'.
	 * @see org.nasdanika.engineering.Release
	 * @generated
	 */
	EClass getRelease();

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
	 * Returns the meta object for class '{@link org.nasdanika.engineering.Offering <em>Offering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Offering</em>'.
	 * @see org.nasdanika.engineering.Offering
	 * @generated
	 */
	EClass getOffering();

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
	 * Returns the meta object for class '{@link org.nasdanika.engineering.Feature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature</em>'.
	 * @see org.nasdanika.engineering.Feature
	 * @generated
	 */
	EClass getFeature();

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
	 * Returns the meta object for class '{@link org.nasdanika.engineering.Persona <em>Persona</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Persona</em>'.
	 * @see org.nasdanika.engineering.Persona
	 * @generated
	 */
	EClass getPersona();

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
	 * Returns the meta object for class '{@link org.nasdanika.engineering.Scenario <em>Scenario</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scenario</em>'.
	 * @see org.nasdanika.engineering.Scenario
	 * @generated
	 */
	EClass getScenario();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.Portfolio <em>Portfolio</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Portfolio</em>'.
	 * @see org.nasdanika.engineering.Portfolio
	 * @generated
	 */
	EClass getPortfolio();

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
	 * Returns the meta object for class '{@link org.nasdanika.engineering.IssueNote <em>Issue Note</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Issue Note</em>'.
	 * @see org.nasdanika.engineering.IssueNote
	 * @generated
	 */
	EClass getIssueNote();

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
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.AbstractEngineerImpl <em>Abstract Engineer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.AbstractEngineerImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getAbstractEngineer()
		 * @generated
		 */
		EClass ABSTRACT_ENGINEER = eINSTANCE.getAbstractEngineer();
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
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.IssueResolutionImpl <em>Issue Resolution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.IssueResolutionImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIssueResolution()
		 * @generated
		 */
		EClass ISSUE_RESOLUTION = eINSTANCE.getIssueResolution();
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
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.ReleaseImpl <em>Release</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.ReleaseImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getRelease()
		 * @generated
		 */
		EClass RELEASE = eINSTANCE.getRelease();
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
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.OfferingImpl <em>Offering</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.OfferingImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getOffering()
		 * @generated
		 */
		EClass OFFERING = eINSTANCE.getOffering();
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
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.FeatureImpl <em>Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.FeatureImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getFeature()
		 * @generated
		 */
		EClass FEATURE = eINSTANCE.getFeature();
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
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.PersonaImpl <em>Persona</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.PersonaImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getPersona()
		 * @generated
		 */
		EClass PERSONA = eINSTANCE.getPersona();
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
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.ScenarioImpl <em>Scenario</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.ScenarioImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getScenario()
		 * @generated
		 */
		EClass SCENARIO = eINSTANCE.getScenario();
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.PortfolioImpl <em>Portfolio</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.PortfolioImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getPortfolio()
		 * @generated
		 */
		EClass PORTFOLIO = eINSTANCE.getPortfolio();
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
		 * The meta object literal for the '{@link org.nasdanika.engineering.impl.IssueNoteImpl <em>Issue Note</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.impl.IssueNoteImpl
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getIssueNote()
		 * @generated
		 */
		EClass ISSUE_NOTE = eINSTANCE.getIssueNote();

	}

} //EngineeringPackage
