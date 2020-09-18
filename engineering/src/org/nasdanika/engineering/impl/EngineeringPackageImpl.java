/**
 */
package org.nasdanika.engineering.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.nasdanika.engineering.AbstractComponent;
import org.nasdanika.engineering.AbstractEngineer;
import org.nasdanika.engineering.Comparison;
import org.nasdanika.engineering.Component;
import org.nasdanika.engineering.Criterion;
import org.nasdanika.engineering.Edition;
import org.nasdanika.engineering.Engineer;
import org.nasdanika.engineering.EngineeringFactory;
import org.nasdanika.engineering.EngineeringOrganizationalUnit;
import org.nasdanika.engineering.EngineeringPackage;
import org.nasdanika.engineering.Feature;
import org.nasdanika.engineering.Increment;
import org.nasdanika.engineering.Issue;
import org.nasdanika.engineering.IssueCategory;
import org.nasdanika.engineering.IssueNote;
import org.nasdanika.engineering.IssueResolution;
import org.nasdanika.engineering.IssueStatus;
import org.nasdanika.engineering.IssueType;
import org.nasdanika.engineering.KeyResult;
import org.nasdanika.engineering.Need;
import org.nasdanika.engineering.Objective;
import org.nasdanika.engineering.Offering;
import org.nasdanika.engineering.Persona;
import org.nasdanika.engineering.Portfolio;
import org.nasdanika.engineering.Product;
import org.nasdanika.engineering.Release;
import org.nasdanika.engineering.Risk;
import org.nasdanika.engineering.Scenario;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.party.PartyPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EngineeringPackageImpl extends EPackageImpl implements EngineeringPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractEngineerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass engineerEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass issueTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass issueResolutionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass issueCategoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass issueStatusEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass issueNoteEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass issueEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass incrementEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass engineeringOrganizationalUnitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass releaseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass objectiveEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass keyResultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass productEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass offeringEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass editionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass featureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass personaEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass needEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scenarioEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portfolioEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass criterionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass comparisonEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass riskEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.nasdanika.engineering.EngineeringPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private EngineeringPackageImpl() {
		super(eNS_URI, EngineeringFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link EngineeringPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static EngineeringPackage init() {
		if (isInited) return (EngineeringPackage)EPackage.Registry.INSTANCE.getEPackage(EngineeringPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredEngineeringPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		EngineeringPackageImpl theEngineeringPackage = registeredEngineeringPackage instanceof EngineeringPackageImpl ? (EngineeringPackageImpl)registeredEngineeringPackage : new EngineeringPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		NcorePackage.eINSTANCE.eClass();
		PartyPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theEngineeringPackage.createPackageContents();

		// Initialize created meta-data
		theEngineeringPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theEngineeringPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(EngineeringPackage.eNS_URI, theEngineeringPackage);
		return theEngineeringPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAbstractComponent() {
		return abstractComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAbstractComponent_Owners() {
		return (EReference)abstractComponentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAbstractComponent_Issues() {
		return (EReference)abstractComponentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAbstractEngineer() {
		return abstractEngineerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEngineer() {
		return engineerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIssueType() {
		return issueTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIssueResolution() {
		return issueResolutionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIssueCategory() {
		return issueCategoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIssue() {
		return issueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIssue_AssignedTo() {
		return (EReference)issueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIssue_Size() {
		return (EAttribute)issueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIssue_Benefit() {
		return (EAttribute)issueEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIssue_Children() {
		return (EReference)issueEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIssue_PlannedFor() {
		return (EReference)issueEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIncrement() {
		return incrementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIncrement_Children() {
		return (EReference)incrementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIncrement_Start() {
		return (EAttribute)incrementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIncrement_End() {
		return (EAttribute)incrementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEngineeringOrganizationalUnit() {
		return engineeringOrganizationalUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRelease() {
		return releaseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getObjective() {
		return objectiveEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getKeyResult() {
		return keyResultEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProduct() {
		return productEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOffering() {
		return offeringEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEdition() {
		return editionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFeature() {
		return featureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getComponent() {
		return componentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPersona() {
		return personaEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNeed() {
		return needEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScenario() {
		return scenarioEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPortfolio() {
		return portfolioEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCriterion() {
		return criterionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getComparison() {
		return comparisonEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRisk() {
		return riskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIssueStatus() {
		return issueStatusEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIssueNote() {
		return issueNoteEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EngineeringFactory getEngineeringFactory() {
		return (EngineeringFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		abstractComponentEClass = createEClass(ABSTRACT_COMPONENT);
		createEReference(abstractComponentEClass, ABSTRACT_COMPONENT__OWNERS);
		createEReference(abstractComponentEClass, ABSTRACT_COMPONENT__ISSUES);

		abstractEngineerEClass = createEClass(ABSTRACT_ENGINEER);

		engineerEClass = createEClass(ENGINEER);

		issueTypeEClass = createEClass(ISSUE_TYPE);

		issueResolutionEClass = createEClass(ISSUE_RESOLUTION);

		issueCategoryEClass = createEClass(ISSUE_CATEGORY);

		issueStatusEClass = createEClass(ISSUE_STATUS);

		issueNoteEClass = createEClass(ISSUE_NOTE);

		issueEClass = createEClass(ISSUE);
		createEReference(issueEClass, ISSUE__ASSIGNED_TO);
		createEAttribute(issueEClass, ISSUE__SIZE);
		createEAttribute(issueEClass, ISSUE__BENEFIT);
		createEReference(issueEClass, ISSUE__CHILDREN);
		createEReference(issueEClass, ISSUE__PLANNED_FOR);

		incrementEClass = createEClass(INCREMENT);
		createEReference(incrementEClass, INCREMENT__CHILDREN);
		createEAttribute(incrementEClass, INCREMENT__START);
		createEAttribute(incrementEClass, INCREMENT__END);

		engineeringOrganizationalUnitEClass = createEClass(ENGINEERING_ORGANIZATIONAL_UNIT);

		releaseEClass = createEClass(RELEASE);

		objectiveEClass = createEClass(OBJECTIVE);

		keyResultEClass = createEClass(KEY_RESULT);

		productEClass = createEClass(PRODUCT);

		offeringEClass = createEClass(OFFERING);

		editionEClass = createEClass(EDITION);

		featureEClass = createEClass(FEATURE);

		componentEClass = createEClass(COMPONENT);

		personaEClass = createEClass(PERSONA);

		needEClass = createEClass(NEED);

		scenarioEClass = createEClass(SCENARIO);

		portfolioEClass = createEClass(PORTFOLIO);

		criterionEClass = createEClass(CRITERION);

		comparisonEClass = createEClass(COMPARISON);

		riskEClass = createEClass(RISK);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		PartyPackage thePartyPackage = (PartyPackage)EPackage.Registry.INSTANCE.getEPackage(PartyPackage.eNS_URI);
		NcorePackage theNcorePackage = (NcorePackage)EPackage.Registry.INSTANCE.getEPackage(NcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		engineerEClass.getESuperTypes().add(thePartyPackage.getRole());
		issueEClass.getESuperTypes().add(theNcorePackage.getEntity());
		incrementEClass.getESuperTypes().add(theNcorePackage.getNamedElement());
		engineeringOrganizationalUnitEClass.getESuperTypes().add(thePartyPackage.getOrganizationalUnit());

		// Initialize classes, features, and operations; add parameters
		initEClass(abstractComponentEClass, AbstractComponent.class, "AbstractComponent", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractComponent_Owners(), this.getEngineer(), null, "owners", null, 0, -1, AbstractComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractComponent_Issues(), this.getIssue(), null, "issues", null, 0, -1, AbstractComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractEngineerEClass, AbstractEngineer.class, "AbstractEngineer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(engineerEClass, Engineer.class, "Engineer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(issueTypeEClass, IssueType.class, "IssueType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(issueResolutionEClass, IssueResolution.class, "IssueResolution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(issueCategoryEClass, IssueCategory.class, "IssueCategory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(issueStatusEClass, IssueStatus.class, "IssueStatus", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(issueNoteEClass, IssueNote.class, "IssueNote", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(issueEClass, Issue.class, "Issue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIssue_AssignedTo(), this.getAbstractEngineer(), null, "assignedTo", null, 0, -1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIssue_Size(), ecorePackage.getEDouble(), "size", null, 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIssue_Benefit(), ecorePackage.getEDouble(), "benefit", null, 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIssue_Children(), this.getIssue(), null, "children", null, 0, -1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIssue_PlannedFor(), this.getIncrement(), null, "plannedFor", null, 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(incrementEClass, Increment.class, "Increment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIncrement_Children(), this.getIncrement(), null, "children", null, 0, -1, Increment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIncrement_Start(), ecorePackage.getEDate(), "start", null, 0, 1, Increment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIncrement_End(), ecorePackage.getEDate(), "end", null, 0, 1, Increment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(engineeringOrganizationalUnitEClass, EngineeringOrganizationalUnit.class, "EngineeringOrganizationalUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(releaseEClass, Release.class, "Release", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(objectiveEClass, Objective.class, "Objective", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(keyResultEClass, KeyResult.class, "KeyResult", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(productEClass, Product.class, "Product", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(offeringEClass, Offering.class, "Offering", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(editionEClass, Edition.class, "Edition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(featureEClass, Feature.class, "Feature", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(componentEClass, Component.class, "Component", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(personaEClass, Persona.class, "Persona", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(needEClass, Need.class, "Need", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(scenarioEClass, Scenario.class, "Scenario", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(portfolioEClass, Portfolio.class, "Portfolio", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(criterionEClass, Criterion.class, "Criterion", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(comparisonEClass, Comparison.class, "Comparison", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(riskEClass, Risk.class, "Risk", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// urn:org.nasdanika
		createUrnorgAnnotations();
		// http://www.eclipse.org/emf/2002/GenModel
		createGenModelAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/GenModel</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createGenModelAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/GenModel";
		addAnnotation
		  (abstractComponentEClass,
		   source,
		   new String[] {
			   "documentation", "Component is a unit of engineering. Base class for elements which have an owning engineer and may contain issues or be referenced by issues."
		   });
		addAnnotation
		  (getAbstractComponent_Owners(),
		   source,
		   new String[] {
			   "documentation", "Engineer responsible for this element."
		   });
		addAnnotation
		  (getAbstractComponent_Issues(),
		   source,
		   new String[] {
			   "documentation", "Issues associated with the element - problems/pain points, improvement opportunities/enhancements."
		   });
		addAnnotation
		  (abstractEngineerEClass,
		   source,
		   new String[] {
			   "documentation", "Base class for Engineer and EngineeringOrganizationalUnit. Can own engineered elements and be assigned to issues. Can also contain its own issues list."
		   });
		addAnnotation
		  (engineerEClass,
		   source,
		   new String[] {
			   "documentation", "Engineers own engineered elements and are assigned issues associated with these elements."
		   });
		addAnnotation
		  (issueTypeEClass,
		   source,
		   new String[] {
			   "documentation", "Issue type. E.g. feature or bug. Or epic/story/task. May define child type(s). E.g. epics may contain only stories, but not tasks. Defined at engineering org unit level and referenced by issues."
		   });
		addAnnotation
		  (issueResolutionEClass,
		   source,
		   new String[] {
			   "documentation", "Issue resolution. E.g. fixed, cancelled, wont fix."
		   });
		addAnnotation
		  (issueCategoryEClass,
		   source,
		   new String[] {
			   "documentation", "Issue category. E.g. documentation, code, automated build. An issue may have multiple categories. Categories can be hierarchical. Category may have an owning engineer. E.g. an automated build category would be owned by a build engineer."
		   });
		addAnnotation
		  (issueStatusEClass,
		   source,
		   new String[] {
			   "documentation", "Issue status. E.g. open, in progress, closed. May define transitions. If transitions are empty then can transition to any status. Defined at engineering org unit level. Visualization - statuses and transitions diagram."
		   });
		addAnnotation
		  (issueNoteEClass,
		   source,
		   new String[] {
			   "documentation", "Issue note - progress report or comment. Can contain time and expense information. Reporter, resources."
		   });
		addAnnotation
		  (issueEClass,
		   source,
		   new String[] {
			   "documentation", "Something to possibly act on regarding the owning element - a problem/pain point, an improvement opportunity/enhancement.\n\nExample:\n\n* Containing activity - \"Initial setup of a software project\", \n* Size - 4.0 (hours) - copy an existing project and modify its sources.\n* Issue - \"Create a code generator\" (enhancement),\n    * Size - 40.0 (hours).\n    * Benefit - 3.5 (hours).\n    * Implementation - an activity providing a detailed explanation how code generator shall be implemented."
		   });
		addAnnotation
		  (getIssue_AssignedTo(),
		   source,
		   new String[] {
			   "documentation", "Engineer this issue is assigned to. If this attribute is null the issue is assumed to be assigned to the owner of the containing element."
		   });
		addAnnotation
		  (getIssue_Size(),
		   source,
		   new String[] {
			   "documentation", "An estimation of effort required to complete this issue in some units used consistently throughout the model - points, person hours, dollars."
		   });
		addAnnotation
		  (getIssue_Benefit(),
		   source,
		   new String[] {
			   "documentation", "An estimation of reduction of the containing activity effort caused by completion of this issue. It can be used for cost/benefit analysis in order to prioritize issues."
		   });
		addAnnotation
		  (getIssue_Children(),
		   source,
		   new String[] {
			   "documentation", "Issues may be organized into a hierarchy."
		   });
		addAnnotation
		  (incrementEClass,
		   source,
		   new String[] {
			   "documentation", "A time period in which engineers are working on issues."
		   });
		addAnnotation
		  (getIncrement_Children(),
		   source,
		   new String[] {
			   "documentation", "Increments can be organized into a hierarchy. E.g. Year - Quarters - Months."
		   });
		addAnnotation
		  (engineeringOrganizationalUnitEClass,
		   source,
		   new String[] {
			   "documentation", "Defines engineering practice - issue types, priorities, categories, etc. Scope, if a flag is set, for engineers and issues - they can see only categories etc defined at the"
		   });
		addAnnotation
		  (releaseEClass,
		   source,
		   new String[] {
			   "documentation", "Release/version of an engineered element. Date, may include other releases of other engineered elements or depend on other releases - in either case a given release cannot be released before the included/dependency release."
		   });
		addAnnotation
		  (objectiveEClass,
		   source,
		   new String[] {
			   "documentation", "Strategic objective owned by an engineer or engineering org unit. Hierarchical, may reference a non-containing parent (only for top level?). References an increment. Parent shall be for the same or larger increment. Inherits increment from the parent if not set. Issue references objectives. Weight in the parent objective. Effective weight. Completion status - manually entered number? Effective completion - manually entered number plus computed from associated issues and key results. Status - open/closed? Or auto-closes when increment closes?"
		   });
		addAnnotation
		  (keyResultEClass,
		   source,
		   new String[] {
			   "documentation", "Owned by an objective. Weight in the objective."
		   });
		addAnnotation
		  (productEClass,
		   source,
		   new String[] {
			   "documentation", "Product is an offering and an engineered element. It has features which satisfy personas neeeds. It may have editions, which are collections of features offered at different prices and or to different personas"
		   });
		addAnnotation
		  (offeringEClass,
		   source,
		   new String[] {
			   "documentation", "Offering is something which satisfied persona needs - product, edition, feature. Offerings can be nested - perhaps shall be handled at the concrete sub-types. Offerings may reference personas as their intended target audiences. Also offering benefit may be computed from needs and scenarios weights."
		   });
		addAnnotation
		  (editionEClass,
		   source,
		   new String[] {
			   "documentation", "A subset of product features offered to different personas at different prices to maximize value for a particular persona. For example, a beginner user may not get benefit from advanced product features and therefore does not need a professional edition. Editions may have bases, e.g. professional edition is based on community edition."
		   });
		addAnnotation
		  (featureEClass,
		   source,
		   new String[] {
			   "documentation", "Unit of product functionality which satisfies persona needs. Maybe included in multiple editions. Scheduled for a release. May depend on other features and on product components releases or issues. I.e. an issue is owned by an engineered element, but it contributes to a feature. Benefit - explicit value and computed from needs and scenarios etc."
		   });
		addAnnotation
		  (componentEClass,
		   source,
		   new String[] {
			   "documentation", "Component is a generic engineered element, part of a product. Components can be nested. They can also depend on other components. More precisely, component releases may depend on other releases."
		   });
		addAnnotation
		  (personaEClass,
		   source,
		   new String[] {
			   "documentation", "Description from internet, e.g. Wikipedia. Abstraction of parties who benefit from organization offerings. Not necessarily pay for them or use them. Weight - manual or computed with decision analysis. Manual may be sales volume, budget contribution for internal customers, population, percentage in profit generation. E.g. a grant size is dependent on how many personas adopt a product. References parties are persona representatives. Such parties may be involved in need identification and prioritization. Defined at EngineeringOrgUnit level - may be internal personas and internal products. May have base personas and personas can be organized into a hierarchy. Base personas define common needs, what everybody needs. Base personas may be abstract - no representatives and no own weight."
		   });
		addAnnotation
		  (needEClass,
		   source,
		   new String[] {
			   "documentation", "Personas have needs which may be satisfied by organization offerings. Needs can be organized into a hierarchy and assigned weights either manually or using decision analysis techniques. Needs may be satisfied by offerings via scenarios explaining how a need is satisfied."
		   });
		addAnnotation
		  (scenarioEClass,
		   source,
		   new String[] {
			   "documentation", "Scenario explains how a need is satisfied by offerings. A scenario references offerings and can be assigned weight - manually or computed. Scenarios can be organized into a hierarchy. Generally scenarios may have steps/flow and include other scenarios, it needs to be understood whether this level of detalization is necessary."
		   });
		addAnnotation
		  (portfolioEClass,
		   source,
		   new String[] {
			   "documentation", "A group of products"
		   });
		addAnnotation
		  (criterionEClass,
		   source,
		   new String[] {
			   "documentation", "A criterion for MCDA methodologies. Can be nested"
		   });
		addAnnotation
		  (comparisonEClass,
		   source,
		   new String[] {
			   "documentation", "AHP comparison of two criteria. Owned by the left criterion and references the right criterion."
		   });
		addAnnotation
		  (riskEClass,
		   source,
		   new String[] {
			   "documentation", "Probability of an adverse event which will affect effort, cost, or totally prevent issue implementation. Attributes such as probability, affect on effort and cost, show-stopper flag."
		   });
	}

	/**
	 * Initializes the annotations for <b>urn:org.nasdanika</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createUrnorgAnnotations() {
		String source = "urn:org.nasdanika";
		addAnnotation
		  (this,
		   source,
		   new String[] {
			   "documentation-reference", "doc/engineering.md"
		   });
		addAnnotation
		  (getAbstractComponent_Issues(),
		   source,
		   new String[] {
			   "label_ru", "\u041f\u0440\u043e\u0431\u043b\u0435\u043c\u044b",
			   "Documentation_ru", "\u041f\u0440\u043e\u0431\u043b\u0435\u043c\u044b, \u0441\u0432\u044f\u0437\u0430\u043d\u043d\u044b\u0435 \u0441 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u043c - \u043f\u0440\u043e\u0431\u043b\u0435\u043c\u044b / \u0431\u043e\u043b\u0435\u0432\u044b\u0435 \u0442\u043e\u0447\u043a\u0438, \u0432\u043e\u0437\u043c\u043e\u0436\u043d\u043e\u0441\u0442\u0438 \u0443\u043b\u0443\u0447\u0448\u0435\u043d\u0438\u044f / \u0443\u043b\u0443\u0447\u0448\u0435\u043d\u0438\u044f."
		   });
	}

} //EngineeringPackageImpl
