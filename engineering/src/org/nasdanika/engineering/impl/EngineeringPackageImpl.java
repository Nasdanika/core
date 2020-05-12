/**
 */
package org.nasdanika.engineering.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.nasdanika.engineering.Engineer;
import org.nasdanika.engineering.EngineeredElement;
import org.nasdanika.engineering.EngineeringFactory;
import org.nasdanika.engineering.EngineeringPackage;
import org.nasdanika.engineering.Increment;
import org.nasdanika.engineering.Issue;
import org.nasdanika.engineering.IssueImportance;
import org.nasdanika.engineering.IssueStatus;
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
	private EClass engineeredElementEClass = null;

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
	private EEnum issueStatusEEnum = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum issueImportanceEEnum = null;

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
	public EClass getEngineeredElement() {
		return engineeredElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEngineeredElement_Owners() {
		return (EReference)engineeredElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEngineeredElement_Issues() {
		return (EReference)engineeredElementEClass.getEStructuralFeatures().get(1);
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
	public EClass getIssue() {
		return issueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIssue_Importance() {
		return (EAttribute)issueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIssue_Status() {
		return (EAttribute)issueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIssue_AssignedTo() {
		return (EReference)issueEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIssue_Size() {
		return (EAttribute)issueEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIssue_Benefit() {
		return (EAttribute)issueEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIssue_Children() {
		return (EReference)issueEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIssue_PlannedFor() {
		return (EReference)issueEClass.getEStructuralFeatures().get(6);
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
	public EEnum getIssueStatus() {
		return issueStatusEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getIssueImportance() {
		return issueImportanceEEnum;
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
		engineeredElementEClass = createEClass(ENGINEERED_ELEMENT);
		createEReference(engineeredElementEClass, ENGINEERED_ELEMENT__OWNERS);
		createEReference(engineeredElementEClass, ENGINEERED_ELEMENT__ISSUES);

		engineerEClass = createEClass(ENGINEER);

		issueEClass = createEClass(ISSUE);
		createEAttribute(issueEClass, ISSUE__IMPORTANCE);
		createEAttribute(issueEClass, ISSUE__STATUS);
		createEReference(issueEClass, ISSUE__ASSIGNED_TO);
		createEAttribute(issueEClass, ISSUE__SIZE);
		createEAttribute(issueEClass, ISSUE__BENEFIT);
		createEReference(issueEClass, ISSUE__CHILDREN);
		createEReference(issueEClass, ISSUE__PLANNED_FOR);

		incrementEClass = createEClass(INCREMENT);
		createEReference(incrementEClass, INCREMENT__CHILDREN);
		createEAttribute(incrementEClass, INCREMENT__START);
		createEAttribute(incrementEClass, INCREMENT__END);

		// Create enums
		issueStatusEEnum = createEEnum(ISSUE_STATUS);
		issueImportanceEEnum = createEEnum(ISSUE_IMPORTANCE);
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
		incrementEClass.getESuperTypes().add(theNcorePackage.getNamedElement());

		// Initialize classes, features, and operations; add parameters
		initEClass(engineeredElementEClass, EngineeredElement.class, "EngineeredElement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEngineeredElement_Owners(), this.getEngineer(), null, "owners", null, 0, -1, EngineeredElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEngineeredElement_Issues(), this.getIssue(), null, "issues", null, 0, -1, EngineeredElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(engineerEClass, Engineer.class, "Engineer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(issueEClass, Issue.class, "Issue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIssue_Importance(), this.getIssueImportance(), "importance", "Medium", 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIssue_Status(), this.getIssueStatus(), "status", "Open", 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIssue_AssignedTo(), this.getEngineer(), null, "assignedTo", null, 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIssue_Size(), ecorePackage.getEDouble(), "size", null, 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIssue_Benefit(), ecorePackage.getEDouble(), "benefit", null, 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIssue_Children(), this.getIssue(), null, "children", null, 0, -1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIssue_PlannedFor(), this.getIncrement(), null, "plannedFor", null, 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(incrementEClass, Increment.class, "Increment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIncrement_Children(), this.getIncrement(), null, "children", null, 0, -1, Increment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIncrement_Start(), ecorePackage.getEDate(), "start", null, 0, 1, Increment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIncrement_End(), ecorePackage.getEDate(), "end", null, 0, 1, Increment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(issueStatusEEnum, IssueStatus.class, "IssueStatus");
		addEEnumLiteral(issueStatusEEnum, IssueStatus.OPEN);
		addEEnumLiteral(issueStatusEEnum, IssueStatus.IN_PROGRESS);
		addEEnumLiteral(issueStatusEEnum, IssueStatus.DONE);
		addEEnumLiteral(issueStatusEEnum, IssueStatus.CANCELLED);

		initEEnum(issueImportanceEEnum, IssueImportance.class, "IssueImportance");
		addEEnumLiteral(issueImportanceEEnum, IssueImportance.LOW);
		addEEnumLiteral(issueImportanceEEnum, IssueImportance.MEDIUM);
		addEEnumLiteral(issueImportanceEEnum, IssueImportance.HIGH);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/GenModel
		createGenModelAnnotations();
		// urn:org.nasdanika
		createUrnorgAnnotations();
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
		  (this,
		   source,
		   new String[] {
			   "documentation", "A model of engineering - a process of building something composite by resolving issues associated with solution components by engineers in increments and making the new functionality available in releases."
		   });
		addAnnotation
		  (engineeredElementEClass,
		   source,
		   new String[] {
			   "documentation", "Base class for elements which have an owning engineer and may contain issues."
		   });
		addAnnotation
		  (getEngineeredElement_Owners(),
		   source,
		   new String[] {
			   "documentation", "Engineer responsible for this element."
		   });
		addAnnotation
		  (getEngineeredElement_Issues(),
		   source,
		   new String[] {
			   "documentation", "Issues associated with the element - problems/pain points, improvement opportunities/enhancements."
		   });
		addAnnotation
		  (engineerEClass,
		   source,
		   new String[] {
			   "documentation", "Engineers own engineered elements and are assigned issues associated with these elements."
		   });
		addAnnotation
		  (issueEClass,
		   source,
		   new String[] {
			   "documentation", "Something to possibly act on regarding the owning element - a problem/pain point, an improvement opportunity/enhancement.\n\nExample:\n\n* Containing activity - \"Initial setup of a software project\", \n* Size - 4.0 (hours) - copy an existing project and modify its sources.\n* Issue - \"Create a code generator\" (enhancement),\n    * Size - 40.0 (hours).\n    * Benefit - 3.5 (hours).\n    * Implementation - an activity providing a detailed explanation how code generator shall be implemented."
		   });
		addAnnotation
		  (getIssue_Importance(),
		   source,
		   new String[] {
			   "documentation", "Issue importance."
		   });
		addAnnotation
		  (getIssue_Status(),
		   source,
		   new String[] {
			   "documentation", "Issue status."
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
		  (issueStatusEEnum,
		   source,
		   new String[] {
			   "documentation", "Issue status."
		   });
		addAnnotation
		  (issueStatusEEnum.getELiterals().get(0),
		   source,
		   new String[] {
			   "documentation", "An issue which hasn\'t been worked on."
		   });
		addAnnotation
		  (issueStatusEEnum.getELiterals().get(1),
		   source,
		   new String[] {
			   "documentation", "Work in progress."
		   });
		addAnnotation
		  (issueStatusEEnum.getELiterals().get(2),
		   source,
		   new String[] {
			   "documentation", "Work completed."
		   });
		addAnnotation
		  (issueStatusEEnum.getELiterals().get(3),
		   source,
		   new String[] {
			   "documentation", "The issue is not going to be worked on for some reason."
		   });
		addAnnotation
		  (issueImportanceEEnum,
		   source,
		   new String[] {
			   "documentation", "Importance of the issue."
		   });
		addAnnotation
		  (issueImportanceEEnum.getELiterals().get(0),
		   source,
		   new String[] {
			   "documentation", "Low importance."
		   });
		addAnnotation
		  (issueImportanceEEnum.getELiterals().get(1),
		   source,
		   new String[] {
			   "documentation", "Medium importance."
		   });
		addAnnotation
		  (issueImportanceEEnum.getELiterals().get(2),
		   source,
		   new String[] {
			   "documentation", "High importance."
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
		  (getEngineeredElement_Issues(),
		   source,
		   new String[] {
			   "label_ru", "\u041f\u0440\u043e\u0431\u043b\u0435\u043c\u044b",
			   "Documentation_ru", "\u041f\u0440\u043e\u0431\u043b\u0435\u043c\u044b, \u0441\u0432\u044f\u0437\u0430\u043d\u043d\u044b\u0435 \u0441 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u043c - \u043f\u0440\u043e\u0431\u043b\u0435\u043c\u044b / \u0431\u043e\u043b\u0435\u0432\u044b\u0435 \u0442\u043e\u0447\u043a\u0438, \u0432\u043e\u0437\u043c\u043e\u0436\u043d\u043e\u0441\u0442\u0438 \u0443\u043b\u0443\u0447\u0448\u0435\u043d\u0438\u044f / \u0443\u043b\u0443\u0447\u0448\u0435\u043d\u0438\u044f."
		   });
	}

} //EngineeringPackageImpl
