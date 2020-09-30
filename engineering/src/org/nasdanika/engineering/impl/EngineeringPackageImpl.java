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
import org.nasdanika.engineering.ComponentCategory;
import org.nasdanika.engineering.ComponentCategoryElement;
import org.nasdanika.engineering.ComponentReference;
import org.nasdanika.engineering.Criterion;
import org.nasdanika.engineering.Edition;
import org.nasdanika.engineering.Engineer;
import org.nasdanika.engineering.EngineeringFactory;
import org.nasdanika.engineering.EngineeringOrganization;
import org.nasdanika.engineering.EngineeringOrganizationalUnit;
import org.nasdanika.engineering.EngineeringPackage;
import org.nasdanika.engineering.Feature;
import org.nasdanika.engineering.FeatureCategory;
import org.nasdanika.engineering.FeatureCategoryElement;
import org.nasdanika.engineering.FeatureType;
import org.nasdanika.engineering.Increment;
import org.nasdanika.engineering.Issue;
import org.nasdanika.engineering.IssueCategory;
import org.nasdanika.engineering.IssueNote;
import org.nasdanika.engineering.IssueRelationship;
import org.nasdanika.engineering.IssueRelationshipType;
import org.nasdanika.engineering.IssueResolution;
import org.nasdanika.engineering.IssueStatus;
import org.nasdanika.engineering.IssueType;
import org.nasdanika.engineering.KeyResult;
import org.nasdanika.engineering.Need;
import org.nasdanika.engineering.NeedCategory;
import org.nasdanika.engineering.NeedCategoryElement;
import org.nasdanika.engineering.Objective;
import org.nasdanika.engineering.Offering;
import org.nasdanika.engineering.Persona;
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
	private EClass componentCategoryElementEClass = null;

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
	private EClass componentCategoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentReferenceEClass = null;

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
	private EClass issueRelationshipTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass issueRelationshipEClass = null;

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
	private EClass engineeringOrganizationEClass = null;

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
	private EClass featureTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass featureCategoryElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass featureCategoryEClass = null;

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
	private EClass needCategoryElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass needCategoryEClass = null;

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
	public EClass getComponentCategoryElement() {
		return componentCategoryElementEClass;
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
	public EClass getComponentCategory() {
		return componentCategoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getComponentCategory_Elements() {
		return (EReference)componentCategoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getComponentReference() {
		return componentReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getComponentReference_Target() {
		return (EReference)componentReferenceEClass.getEStructuralFeatures().get(0);
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
	public EReference getAbstractEngineer_Issues() {
		return (EReference)abstractEngineerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAbstractEngineer_Objectives() {
		return (EReference)abstractEngineerEClass.getEStructuralFeatures().get(1);
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
	public EReference getIssueType_Children() {
		return (EReference)issueTypeEClass.getEStructuralFeatures().get(0);
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
	public EAttribute getIssueResolution_Completed() {
		return (EAttribute)issueResolutionEClass.getEStructuralFeatures().get(0);
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
	public EReference getIssueCategory_Children() {
		return (EReference)issueCategoryEClass.getEStructuralFeatures().get(0);
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
	public EAttribute getIssue_Cost() {
		return (EAttribute)issueEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIssue_Benefit() {
		return (EAttribute)issueEClass.getEStructuralFeatures().get(3);
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
	public EReference getIssue_Type() {
		return (EReference)issueEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIssue_Status() {
		return (EReference)issueEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIssue_Resolution() {
		return (EReference)issueEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIssue_Categories() {
		return (EReference)issueEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIssue_Notes() {
		return (EReference)issueEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIssue_Releases() {
		return (EReference)issueEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIssue_Relationships() {
		return (EReference)issueEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIssue_Requires() {
		return (EReference)issueEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIssue_Actionable() {
		return (EAttribute)issueEClass.getEStructuralFeatures().get(14);
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
	public EReference getEngineeringOrganizationalUnit_Portfolio() {
		return (EReference)engineeringOrganizationalUnitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEngineeringOrganizationalUnit_TargetAudiences() {
		return (EReference)engineeringOrganizationalUnitEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEngineeringOrganizationalUnit_IssueTypes() {
		return (EReference)engineeringOrganizationalUnitEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEngineeringOrganizationalUnit_IssueResolutions() {
		return (EReference)engineeringOrganizationalUnitEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEngineeringOrganizationalUnit_IssueCategories() {
		return (EReference)engineeringOrganizationalUnitEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEngineeringOrganizationalUnit_IssueStatuses() {
		return (EReference)engineeringOrganizationalUnitEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEngineeringOrganizationalUnit_IssueRelationshipTypes() {
		return (EReference)engineeringOrganizationalUnitEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEngineeringOrganizationalUnit_Increments() {
		return (EReference)engineeringOrganizationalUnitEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEngineeringOrganizationalUnit_FeatureTypes() {
		return (EReference)engineeringOrganizationalUnitEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEngineeringOrganization() {
		return engineeringOrganizationEClass;
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
	public EReference getRelease_Requires() {
		return (EReference)releaseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRelease_Includes() {
		return (EReference)releaseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRelease_Date() {
		return (EAttribute)releaseEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRelease_PlannedFor() {
		return (EReference)releaseEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRelease_Released() {
		return (EAttribute)releaseEClass.getEStructuralFeatures().get(4);
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
	public EReference getObjective_Increment() {
		return (EReference)objectiveEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getObjective_Children() {
		return (EReference)objectiveEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getObjective_KeyResults() {
		return (EReference)objectiveEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getObjective_Parent() {
		return (EReference)objectiveEClass.getEStructuralFeatures().get(3);
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
	public EReference getProduct_Editions() {
		return (EReference)productEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProduct_Features() {
		return (EReference)productEClass.getEStructuralFeatures().get(1);
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
	public EReference getOffering_TargetAudiences() {
		return (EReference)offeringEClass.getEStructuralFeatures().get(0);
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
	public EReference getEdition_Bases() {
		return (EReference)editionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEdition_Features() {
		return (EReference)editionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFeatureType() {
		return featureTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFeatureCategoryElement() {
		return featureCategoryElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFeatureCategory() {
		return featureCategoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFeatureCategory_Elements() {
		return (EReference)featureCategoryEClass.getEStructuralFeatures().get(0);
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
	public EReference getFeature_PlannedFor() {
		return (EReference)featureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFeature_Type() {
		return (EReference)featureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFeature_Requires() {
		return (EReference)featureEClass.getEStructuralFeatures().get(2);
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
	public EReference getComponent_Components() {
		return (EReference)componentEClass.getEStructuralFeatures().get(0);
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
	public EReference getPersona_Needs() {
		return (EReference)personaEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPersona_Extends() {
		return (EReference)personaEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNeedCategoryElement() {
		return needCategoryElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNeedCategory() {
		return needCategoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getNeedCategory_Elements() {
		return (EReference)needCategoryEClass.getEStructuralFeatures().get(0);
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
	public EReference getNeed_Scenarios() {
		return (EReference)needEClass.getEStructuralFeatures().get(0);
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
	public EReference getScenario_Offerings() {
		return (EReference)scenarioEClass.getEStructuralFeatures().get(0);
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
	public EReference getIssueStatus_Transitions() {
		return (EReference)issueStatusEClass.getEStructuralFeatures().get(0);
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
	public EAttribute getIssueNote_Description() {
		return (EAttribute)issueNoteEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIssueRelationshipType() {
		return issueRelationshipTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIssueRelationshipType_Blocks() {
		return (EAttribute)issueRelationshipTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIssueRelationship() {
		return issueRelationshipEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIssueRelationship_Type() {
		return (EReference)issueRelationshipEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIssueRelationship_Target() {
		return (EReference)issueRelationshipEClass.getEStructuralFeatures().get(1);
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
		componentCategoryElementEClass = createEClass(COMPONENT_CATEGORY_ELEMENT);

		abstractComponentEClass = createEClass(ABSTRACT_COMPONENT);
		createEReference(abstractComponentEClass, ABSTRACT_COMPONENT__OWNERS);
		createEReference(abstractComponentEClass, ABSTRACT_COMPONENT__ISSUES);

		componentCategoryEClass = createEClass(COMPONENT_CATEGORY);
		createEReference(componentCategoryEClass, COMPONENT_CATEGORY__ELEMENTS);

		componentReferenceEClass = createEClass(COMPONENT_REFERENCE);
		createEReference(componentReferenceEClass, COMPONENT_REFERENCE__TARGET);

		abstractEngineerEClass = createEClass(ABSTRACT_ENGINEER);
		createEReference(abstractEngineerEClass, ABSTRACT_ENGINEER__ISSUES);
		createEReference(abstractEngineerEClass, ABSTRACT_ENGINEER__OBJECTIVES);

		engineeringOrganizationalUnitEClass = createEClass(ENGINEERING_ORGANIZATIONAL_UNIT);
		createEReference(engineeringOrganizationalUnitEClass, ENGINEERING_ORGANIZATIONAL_UNIT__PORTFOLIO);
		createEReference(engineeringOrganizationalUnitEClass, ENGINEERING_ORGANIZATIONAL_UNIT__TARGET_AUDIENCES);
		createEReference(engineeringOrganizationalUnitEClass, ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_TYPES);
		createEReference(engineeringOrganizationalUnitEClass, ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RESOLUTIONS);
		createEReference(engineeringOrganizationalUnitEClass, ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_CATEGORIES);
		createEReference(engineeringOrganizationalUnitEClass, ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_STATUSES);
		createEReference(engineeringOrganizationalUnitEClass, ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RELATIONSHIP_TYPES);
		createEReference(engineeringOrganizationalUnitEClass, ENGINEERING_ORGANIZATIONAL_UNIT__INCREMENTS);
		createEReference(engineeringOrganizationalUnitEClass, ENGINEERING_ORGANIZATIONAL_UNIT__FEATURE_TYPES);

		engineeringOrganizationEClass = createEClass(ENGINEERING_ORGANIZATION);

		engineerEClass = createEClass(ENGINEER);

		issueTypeEClass = createEClass(ISSUE_TYPE);
		createEReference(issueTypeEClass, ISSUE_TYPE__CHILDREN);

		issueResolutionEClass = createEClass(ISSUE_RESOLUTION);
		createEAttribute(issueResolutionEClass, ISSUE_RESOLUTION__COMPLETED);

		issueCategoryEClass = createEClass(ISSUE_CATEGORY);
		createEReference(issueCategoryEClass, ISSUE_CATEGORY__CHILDREN);

		issueStatusEClass = createEClass(ISSUE_STATUS);
		createEReference(issueStatusEClass, ISSUE_STATUS__TRANSITIONS);

		issueNoteEClass = createEClass(ISSUE_NOTE);
		createEAttribute(issueNoteEClass, ISSUE_NOTE__DESCRIPTION);

		issueRelationshipTypeEClass = createEClass(ISSUE_RELATIONSHIP_TYPE);
		createEAttribute(issueRelationshipTypeEClass, ISSUE_RELATIONSHIP_TYPE__BLOCKS);

		issueRelationshipEClass = createEClass(ISSUE_RELATIONSHIP);
		createEReference(issueRelationshipEClass, ISSUE_RELATIONSHIP__TYPE);
		createEReference(issueRelationshipEClass, ISSUE_RELATIONSHIP__TARGET);

		issueEClass = createEClass(ISSUE);
		createEReference(issueEClass, ISSUE__ASSIGNED_TO);
		createEAttribute(issueEClass, ISSUE__SIZE);
		createEAttribute(issueEClass, ISSUE__COST);
		createEAttribute(issueEClass, ISSUE__BENEFIT);
		createEReference(issueEClass, ISSUE__PLANNED_FOR);
		createEReference(issueEClass, ISSUE__CHILDREN);
		createEReference(issueEClass, ISSUE__TYPE);
		createEReference(issueEClass, ISSUE__STATUS);
		createEReference(issueEClass, ISSUE__RESOLUTION);
		createEReference(issueEClass, ISSUE__CATEGORIES);
		createEReference(issueEClass, ISSUE__NOTES);
		createEReference(issueEClass, ISSUE__RELEASES);
		createEReference(issueEClass, ISSUE__RELATIONSHIPS);
		createEReference(issueEClass, ISSUE__REQUIRES);
		createEAttribute(issueEClass, ISSUE__ACTIONABLE);

		incrementEClass = createEClass(INCREMENT);
		createEReference(incrementEClass, INCREMENT__CHILDREN);
		createEAttribute(incrementEClass, INCREMENT__START);
		createEAttribute(incrementEClass, INCREMENT__END);

		releaseEClass = createEClass(RELEASE);
		createEReference(releaseEClass, RELEASE__REQUIRES);
		createEReference(releaseEClass, RELEASE__INCLUDES);
		createEAttribute(releaseEClass, RELEASE__DATE);
		createEReference(releaseEClass, RELEASE__PLANNED_FOR);
		createEAttribute(releaseEClass, RELEASE__RELEASED);

		objectiveEClass = createEClass(OBJECTIVE);
		createEReference(objectiveEClass, OBJECTIVE__INCREMENT);
		createEReference(objectiveEClass, OBJECTIVE__CHILDREN);
		createEReference(objectiveEClass, OBJECTIVE__KEY_RESULTS);
		createEReference(objectiveEClass, OBJECTIVE__PARENT);

		keyResultEClass = createEClass(KEY_RESULT);

		componentEClass = createEClass(COMPONENT);
		createEReference(componentEClass, COMPONENT__COMPONENTS);

		offeringEClass = createEClass(OFFERING);
		createEReference(offeringEClass, OFFERING__TARGET_AUDIENCES);

		productEClass = createEClass(PRODUCT);
		createEReference(productEClass, PRODUCT__EDITIONS);
		createEReference(productEClass, PRODUCT__FEATURES);

		editionEClass = createEClass(EDITION);
		createEReference(editionEClass, EDITION__BASES);
		createEReference(editionEClass, EDITION__FEATURES);

		featureTypeEClass = createEClass(FEATURE_TYPE);

		featureCategoryElementEClass = createEClass(FEATURE_CATEGORY_ELEMENT);

		featureCategoryEClass = createEClass(FEATURE_CATEGORY);
		createEReference(featureCategoryEClass, FEATURE_CATEGORY__ELEMENTS);

		featureEClass = createEClass(FEATURE);
		createEReference(featureEClass, FEATURE__PLANNED_FOR);
		createEReference(featureEClass, FEATURE__TYPE);
		createEReference(featureEClass, FEATURE__REQUIRES);

		personaEClass = createEClass(PERSONA);
		createEReference(personaEClass, PERSONA__NEEDS);
		createEReference(personaEClass, PERSONA__EXTENDS);

		needCategoryElementEClass = createEClass(NEED_CATEGORY_ELEMENT);

		needCategoryEClass = createEClass(NEED_CATEGORY);
		createEReference(needCategoryEClass, NEED_CATEGORY__ELEMENTS);

		needEClass = createEClass(NEED);
		createEReference(needEClass, NEED__SCENARIOS);

		scenarioEClass = createEClass(SCENARIO);
		createEReference(scenarioEClass, SCENARIO__OFFERINGS);

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
		NcorePackage theNcorePackage = (NcorePackage)EPackage.Registry.INSTANCE.getEPackage(NcorePackage.eNS_URI);
		PartyPackage thePartyPackage = (PartyPackage)EPackage.Registry.INSTANCE.getEPackage(PartyPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		abstractComponentEClass.getESuperTypes().add(this.getComponentCategoryElement());
		componentCategoryEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		componentCategoryEClass.getESuperTypes().add(this.getComponentCategoryElement());
		componentReferenceEClass.getESuperTypes().add(this.getComponentCategoryElement());
		engineeringOrganizationalUnitEClass.getESuperTypes().add(thePartyPackage.getOrganizationalUnit());
		engineeringOrganizationalUnitEClass.getESuperTypes().add(this.getAbstractEngineer());
		engineeringOrganizationEClass.getESuperTypes().add(thePartyPackage.getOrganization());
		engineeringOrganizationEClass.getESuperTypes().add(this.getEngineeringOrganizationalUnit());
		engineerEClass.getESuperTypes().add(thePartyPackage.getRole());
		engineerEClass.getESuperTypes().add(this.getAbstractEngineer());
		issueTypeEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		issueResolutionEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		issueCategoryEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		issueStatusEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		issueRelationshipTypeEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		issueRelationshipEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		issueEClass.getESuperTypes().add(theNcorePackage.getEntity());
		incrementEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		releaseEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		objectiveEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		keyResultEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		componentEClass.getESuperTypes().add(this.getAbstractComponent());
		productEClass.getESuperTypes().add(this.getComponent());
		productEClass.getESuperTypes().add(this.getOffering());
		editionEClass.getESuperTypes().add(this.getOffering());
		featureTypeEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		featureCategoryEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		featureCategoryEClass.getESuperTypes().add(this.getFeatureCategoryElement());
		featureEClass.getESuperTypes().add(this.getFeatureCategoryElement());
		personaEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		needCategoryEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		needCategoryEClass.getESuperTypes().add(this.getNeedCategoryElement());
		needEClass.getESuperTypes().add(this.getNeedCategoryElement());
		scenarioEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		criterionEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		riskEClass.getESuperTypes().add(theNcorePackage.getModelElement());

		// Initialize classes, features, and operations; add parameters
		initEClass(componentCategoryElementEClass, ComponentCategoryElement.class, "ComponentCategoryElement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(abstractComponentEClass, AbstractComponent.class, "AbstractComponent", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractComponent_Owners(), this.getAbstractEngineer(), null, "owners", null, 0, -1, AbstractComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractComponent_Issues(), this.getIssue(), null, "issues", null, 0, -1, AbstractComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(componentCategoryEClass, ComponentCategory.class, "ComponentCategory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponentCategory_Elements(), this.getComponentCategoryElement(), null, "elements", null, 0, -1, ComponentCategory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(componentReferenceEClass, ComponentReference.class, "ComponentReference", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponentReference_Target(), this.getAbstractComponent(), null, "target", null, 0, -1, ComponentReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractEngineerEClass, AbstractEngineer.class, "AbstractEngineer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractEngineer_Issues(), this.getIssue(), null, "issues", null, 0, -1, AbstractEngineer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractEngineer_Objectives(), this.getObjective(), null, "objectives", null, 0, -1, AbstractEngineer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(engineeringOrganizationalUnitEClass, EngineeringOrganizationalUnit.class, "EngineeringOrganizationalUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEngineeringOrganizationalUnit_Portfolio(), this.getComponentCategoryElement(), null, "portfolio", null, 0, -1, EngineeringOrganizationalUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEngineeringOrganizationalUnit_TargetAudiences(), this.getPersona(), null, "targetAudiences", null, 0, -1, EngineeringOrganizationalUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEngineeringOrganizationalUnit_IssueTypes(), this.getIssueType(), null, "issueTypes", null, 0, -1, EngineeringOrganizationalUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEngineeringOrganizationalUnit_IssueResolutions(), this.getIssueResolution(), null, "issueResolutions", null, 0, -1, EngineeringOrganizationalUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEngineeringOrganizationalUnit_IssueCategories(), this.getIssueCategory(), null, "issueCategories", null, 0, -1, EngineeringOrganizationalUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEngineeringOrganizationalUnit_IssueStatuses(), this.getIssueStatus(), null, "issueStatuses", null, 0, -1, EngineeringOrganizationalUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEngineeringOrganizationalUnit_IssueRelationshipTypes(), this.getIssueRelationshipType(), null, "issueRelationshipTypes", null, 0, -1, EngineeringOrganizationalUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEngineeringOrganizationalUnit_Increments(), this.getIncrement(), null, "increments", null, 0, -1, EngineeringOrganizationalUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEngineeringOrganizationalUnit_FeatureTypes(), this.getFeatureType(), null, "featureTypes", null, 0, -1, EngineeringOrganizationalUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(engineeringOrganizationEClass, EngineeringOrganization.class, "EngineeringOrganization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(engineerEClass, Engineer.class, "Engineer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(issueTypeEClass, IssueType.class, "IssueType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIssueType_Children(), this.getIssueType(), null, "children", null, 0, -1, IssueType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(issueResolutionEClass, IssueResolution.class, "IssueResolution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIssueResolution_Completed(), ecorePackage.getEBoolean(), "completed", null, 0, 1, IssueResolution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(issueCategoryEClass, IssueCategory.class, "IssueCategory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIssueCategory_Children(), this.getIssueCategory(), null, "children", null, 0, -1, IssueCategory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(issueStatusEClass, IssueStatus.class, "IssueStatus", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIssueStatus_Transitions(), this.getIssueStatus(), null, "transitions", null, 0, -1, IssueStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(issueNoteEClass, IssueNote.class, "IssueNote", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIssueNote_Description(), ecorePackage.getEString(), "description", null, 0, 1, IssueNote.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(issueRelationshipTypeEClass, IssueRelationshipType.class, "IssueRelationshipType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIssueRelationshipType_Blocks(), ecorePackage.getEBoolean(), "blocks", null, 0, 1, IssueRelationshipType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(issueRelationshipEClass, IssueRelationship.class, "IssueRelationship", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIssueRelationship_Type(), this.getIssueRelationshipType(), null, "type", null, 0, 1, IssueRelationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIssueRelationship_Target(), this.getIssue(), null, "target", null, 0, 1, IssueRelationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(issueEClass, Issue.class, "Issue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIssue_AssignedTo(), this.getAbstractEngineer(), null, "assignedTo", null, 0, -1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIssue_Size(), ecorePackage.getEDouble(), "size", null, 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIssue_Cost(), ecorePackage.getEDouble(), "cost", null, 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIssue_Benefit(), ecorePackage.getEDouble(), "benefit", null, 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIssue_PlannedFor(), this.getIncrement(), null, "plannedFor", null, 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIssue_Children(), this.getIssue(), null, "children", null, 0, -1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIssue_Type(), this.getIssueType(), null, "type", null, 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIssue_Status(), this.getIssueStatus(), null, "status", null, 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIssue_Resolution(), this.getIssueResolution(), null, "resolution", null, 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIssue_Categories(), this.getIssueCategory(), null, "categories", null, 0, -1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIssue_Notes(), this.getIssueNote(), null, "notes", null, 0, -1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIssue_Releases(), this.getRelease(), null, "releases", null, 0, -1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIssue_Relationships(), this.getIssueRelationship(), null, "relationships", null, 0, -1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIssue_Requires(), this.getRelease(), null, "requires", null, 0, -1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIssue_Actionable(), ecorePackage.getEBoolean(), "actionable", null, 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(incrementEClass, Increment.class, "Increment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIncrement_Children(), this.getIncrement(), null, "children", null, 0, -1, Increment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIncrement_Start(), ecorePackage.getEDate(), "start", null, 0, 1, Increment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIncrement_End(), ecorePackage.getEDate(), "end", null, 0, 1, Increment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(releaseEClass, Release.class, "Release", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRelease_Requires(), this.getRelease(), null, "requires", null, 0, -1, Release.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRelease_Includes(), this.getRelease(), null, "includes", null, 0, -1, Release.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelease_Date(), ecorePackage.getEDate(), "date", null, 0, 1, Release.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRelease_PlannedFor(), this.getIncrement(), null, "plannedFor", null, 0, 1, Release.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelease_Released(), ecorePackage.getEBoolean(), "released", null, 0, 1, Release.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(objectiveEClass, Objective.class, "Objective", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getObjective_Increment(), this.getIncrement(), null, "increment", null, 0, 1, Objective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getObjective_Children(), this.getObjective(), null, "children", null, 0, -1, Objective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getObjective_KeyResults(), this.getKeyResult(), null, "keyResults", null, 0, -1, Objective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getObjective_Parent(), this.getObjective(), null, "parent", null, 0, 1, Objective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(keyResultEClass, KeyResult.class, "KeyResult", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(componentEClass, Component.class, "Component", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponent_Components(), this.getComponentCategoryElement(), null, "components", null, 0, -1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(offeringEClass, Offering.class, "Offering", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOffering_TargetAudiences(), this.getPersona(), null, "targetAudiences", null, 0, -1, Offering.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(productEClass, Product.class, "Product", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProduct_Editions(), this.getEdition(), null, "editions", null, 0, -1, Product.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProduct_Features(), this.getFeatureCategoryElement(), null, "features", null, 0, 1, Product.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(editionEClass, Edition.class, "Edition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEdition_Bases(), this.getEdition(), null, "bases", null, 0, -1, Edition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEdition_Features(), this.getFeature(), null, "features", null, 0, -1, Edition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(featureTypeEClass, FeatureType.class, "FeatureType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(featureCategoryElementEClass, FeatureCategoryElement.class, "FeatureCategoryElement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(featureCategoryEClass, FeatureCategory.class, "FeatureCategory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFeatureCategory_Elements(), this.getFeatureCategoryElement(), null, "elements", null, 0, -1, FeatureCategory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(featureEClass, Feature.class, "Feature", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFeature_PlannedFor(), this.getRelease(), null, "plannedFor", null, 0, 1, Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFeature_Type(), this.getFeatureType(), null, "type", null, 0, 1, Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFeature_Requires(), this.getFeature(), null, "requires", null, 0, -1, Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(personaEClass, Persona.class, "Persona", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPersona_Needs(), this.getNeedCategoryElement(), null, "needs", null, 0, -1, Persona.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPersona_Extends(), this.getPersona(), null, "extends", null, 0, -1, Persona.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(needCategoryElementEClass, NeedCategoryElement.class, "NeedCategoryElement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(needCategoryEClass, NeedCategory.class, "NeedCategory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNeedCategory_Elements(), this.getNeedCategoryElement(), null, "elements", null, 0, -1, NeedCategory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(needEClass, Need.class, "Need", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNeed_Scenarios(), this.getScenario(), null, "scenarios", null, 0, -1, Need.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scenarioEClass, Scenario.class, "Scenario", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScenario_Offerings(), this.getOffering(), null, "offerings", null, 0, -1, Scenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		  (componentCategoryElementEClass,
		   source,
		   new String[] {
			   "documentation", "Base class for model elements which can be contained by the component category - components and sub-categories."
		   });
		addAnnotation
		  (abstractComponentEClass,
		   source,
		   new String[] {
			   "documentation", "Component is a unit of engineering. Base class for elements which have an owning engineers and may contain issues or be referenced by issues."
		   });
		addAnnotation
		  (getAbstractComponent_Owners(),
		   source,
		   new String[] {
			   "documentation", "Engineers responsible for this component."
		   });
		addAnnotation
		  (getAbstractComponent_Issues(),
		   source,
		   new String[] {
			   "documentation", "Issues associated with the component - problems/pain points, improvement opportunities/enhancements."
		   });
		addAnnotation
		  (componentCategoryEClass,
		   source,
		   new String[] {
			   "documentation", "Grouping of components."
		   });
		addAnnotation
		  (getComponentCategory_Elements(),
		   source,
		   new String[] {
			   "documentation", "Category elements."
		   });
		addAnnotation
		  (componentReferenceEClass,
		   source,
		   new String[] {
			   "documentation", "Reference to a component. Can be used to federate multiple model resources into a single logical model."
		   });
		addAnnotation
		  (getComponentReference_Target(),
		   source,
		   new String[] {
			   "documentation", "Reference target."
		   });
		addAnnotation
		  (abstractEngineerEClass,
		   source,
		   new String[] {
			   "documentation", "Base class for Engineer and EngineeringOrganizationalUnit. Can own components and be assigned to issues. Can also contain its own issues list."
		   });
		addAnnotation
		  (getAbstractEngineer_Issues(),
		   source,
		   new String[] {
			   "documentation", "Issues owned by this engineer. Issues related to a single component shall be defined at the component level. If an issue doesn\'t have a related component or has multiple related components it can be defined at the engineer level."
		   });
		addAnnotation
		  (engineeringOrganizationalUnitEClass,
		   source,
		   new String[] {
			   "documentation", "Defines engineering practice - issue types, priorities, categories, etc. Scope, if a flag is set, for engineers and issues - they can see only categories etc defined at the\ncomponents organized into categories. Palette for components."
		   });
		addAnnotation
		  (getEngineeringOrganizationalUnit_Portfolio(),
		   source,
		   new String[] {
			   "documentation", "Components and products owned by the organizational unit."
		   });
		addAnnotation
		  (engineeringOrganizationEClass,
		   source,
		   new String[] {
			   "documentation", "Engineering organization."
		   });
		addAnnotation
		  (engineerEClass,
		   source,
		   new String[] {
			   "documentation", "Engineer is a role which can own components, be assigned to issues and has its own issues list."
		   });
		addAnnotation
		  (issueTypeEClass,
		   source,
		   new String[] {
			   "documentation", "Issue type. E.g. feature or bug. Or epic/story/task. May define child type(s). E.g. epics may contain only stories, but not tasks. Defined at engineering org unit level and referenced by issues."
		   });
		addAnnotation
		  (getIssueType_Children(),
		   source,
		   new String[] {
			   "documentation", "Issue type(s) which can be children of this issue type. E.g. epics may contain stories but not tasks."
		   });
		addAnnotation
		  (issueResolutionEClass,
		   source,
		   new String[] {
			   "documentation", "Issue resolution. E.g. fixed, cancelled, wont fix."
		   });
		addAnnotation
		  (getIssueResolution_Completed(),
		   source,
		   new String[] {
			   "documentation", "If true, the issue is successfully completed and blocked issues can be worked on."
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
		  (getIssueStatus_Transitions(),
		   source,
		   new String[] {
			   "documentation", "Statuses to which this status can transition. If empty then can transition to any status."
		   });
		addAnnotation
		  (issueNoteEClass,
		   source,
		   new String[] {
			   "documentation", "Issue note - progress report or comment. Can contain time and expense information. Reporter, resources."
		   });
		addAnnotation
		  (getIssueNote_Description(),
		   source,
		   new String[] {
			   "documentation", "Note description in markdown."
		   });
		addAnnotation
		  (issueRelationshipTypeEClass,
		   source,
		   new String[] {
			   "documentation", "Relationship type."
		   });
		addAnnotation
		  (getIssueRelationshipType_Blocks(),
		   source,
		   new String[] {
			   "documentation", "If true, source/owner must be completed in order to start working on the target."
		   });
		addAnnotation
		  (issueRelationshipEClass,
		   source,
		   new String[] {
			   "documentation", "Relationship between issues."
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
			   "documentation", "An estimation of effort required to complete this issue in some units used consistently throughout the model - e.g. points or person hours."
		   });
		addAnnotation
		  (getIssue_Cost(),
		   source,
		   new String[] {
			   "documentation", "An estimation of monetary expenditure required to complete this issue in some units used consistently throughout the model - e.g. dollars. "
		   });
		addAnnotation
		  (getIssue_Benefit(),
		   source,
		   new String[] {
			   "documentation", "Explicitly assigned issue benefit value. Used for computing effective benefit which in turn is used for prioritization."
		   });
		addAnnotation
		  (getIssue_PlannedFor(),
		   source,
		   new String[] {
			   "documentation", "Interment in which issue is scheduled to be worked on."
		   });
		addAnnotation
		  (getIssue_Children(),
		   source,
		   new String[] {
			   "documentation", "Issues may be organized into a hierarchy, a [Work Breakdown Structure](https://en.wikipedia.org/wiki/Work_breakdown_structure). The parent issue is implicitly blocked by its children, i.e. it can be worked on and closed only after all of its children are closed."
		   });
		addAnnotation
		  (getIssue_Categories(),
		   source,
		   new String[] {
			   "documentation", "An issue can be assigned zero or more categories."
		   });
		addAnnotation
		  (getIssue_Notes(),
		   source,
		   new String[] {
			   "documentation", "Issue notes can be used for tracking work progress including time and expenditure reporting."
		   });
		addAnnotation
		  (getIssue_Releases(),
		   source,
		   new String[] {
			   "documentation", "Modifications performed as part of the issue work may appear in zero or more component releases."
		   });
		addAnnotation
		  (getIssue_Requires(),
		   source,
		   new String[] {
			   "documentation", "Releases required to start working on the issue. "
		   });
		addAnnotation
		  (getIssue_Actionable(),
		   source,
		   new String[] {
			   "documentation", "Computed attribute. True if the issue doesn\'t have blocking relationships with open issues and doesn\'t have unreleased releases in its requires list."
		   });
		addAnnotation
		  (incrementEClass,
		   source,
		   new String[] {
			   "documentation", "A time period in which engineers are working on issues. "
		   });
		addAnnotation
		  (getIncrement_Children(),
		   source,
		   new String[] {
			   "documentation", "Increments can be organized into a hierarchy. E.g. Year - Quarter - Month - Week."
		   });
		addAnnotation
		  (releaseEClass,
		   source,
		   new String[] {
			   "documentation", "Release/version of component. Date, may include other releases of other components or depend/require other releases - in either case a given release cannot be released before the included/dependency release."
		   });
		addAnnotation
		  (getRelease_Requires(),
		   source,
		   new String[] {
			   "documentation", "Releases required by this release. For example product B release 1.2.0 may require product or component A release 1.5.6"
		   });
		addAnnotation
		  (getRelease_Includes(),
		   source,
		   new String[] {
			   "documentation", "Release included in this release. For example product release 2020-12 may include component release 1.2.0."
		   });
		addAnnotation
		  (getRelease_Date(),
		   source,
		   new String[] {
			   "documentation", "Release can be planned for a date, an increment, or both. In the latter case the date shall be within the increment."
		   });
		addAnnotation
		  (getRelease_PlannedFor(),
		   source,
		   new String[] {
			   "documentation", "Interment in which release is scheduled to be published."
		   });
		addAnnotation
		  (objectiveEClass,
		   source,
		   new String[] {
			   "documentation", "Strategic objective owned by an engineer or engineering org unit. Hierarchical, may reference a non-containing parent (only for top level?). References an increment. Parent shall be for the same or larger increment. Inherits increment from the parent if not set. Issue references objectives. Weight in the parent objective. Effective weight. Completion status - manually entered number? Effective completion - manually entered number plus computed from associated issues and key results. Status - open/closed? Or auto-closes when increment closes?"
		   });
		addAnnotation
		  (getObjective_Increment(),
		   source,
		   new String[] {
			   "documentation", "Interment in which release is scheduled to be published."
		   });
		addAnnotation
		  (keyResultEClass,
		   source,
		   new String[] {
			   "documentation", "Owned by an objective. Weight in the objective."
		   });
		addAnnotation
		  (componentEClass,
		   source,
		   new String[] {
			   "documentation", "Component is a concrete generic component, can be a part of a product. Components can be nested. They can also depend on other components. More precisely, component releases may depend on other releases."
		   });
		addAnnotation
		  (offeringEClass,
		   source,
		   new String[] {
			   "documentation", "Offering is something which satisfies persona needs - product, edition, feature. Offerings may reference personas as their intended target audiences. Also offering benefit may be computed from needs and scenarios weights."
		   });
		addAnnotation
		  (productEClass,
		   source,
		   new String[] {
			   "documentation", "Product is an offering and a component. It has features which satisfy personas neeeds. It may have editions, which are collections of features offered at different prices and or to different personas"
		   });
		addAnnotation
		  (editionEClass,
		   source,
		   new String[] {
			   "documentation", "A subset of product features offered to different personas at different prices to maximize value for a particular persona. For example, a beginner user may not get benefit from advanced product features and therefore does not need a professional edition. Editions may have bases, e.g. professional edition is based on community edition."
		   });
		addAnnotation
		  (featureTypeEClass,
		   source,
		   new String[] {
			   "documentation", "Feature type. E.g. Basic, Performance, Delighter"
		   });
		addAnnotation
		  (featureCategoryElementEClass,
		   source,
		   new String[] {
			   "documentation", "Base class for model elements which can be contained by feature category - features and sub-categories."
		   });
		addAnnotation
		  (featureCategoryEClass,
		   source,
		   new String[] {
			   "documentation", "Grouping of features."
		   });
		addAnnotation
		  (getFeatureCategory_Elements(),
		   source,
		   new String[] {
			   "documentation", "Category elements."
		   });
		addAnnotation
		  (featureEClass,
		   source,
		   new String[] {
			   "documentation", "Unit of product functionality which satisfies persona needs. Maybe included in multiple editions. Scheduled for a release. May depend on other features and on product components releases or issues. I.e. an issue is owned by an engineered element, but it contributes to a feature. Benefit - explicit value and computed from needs and scenarios etc."
		   });
		addAnnotation
		  (getFeature_Requires(),
		   source,
		   new String[] {
			   "documentation", "One feature may require another feature to build on/extend."
		   });
		addAnnotation
		  (personaEClass,
		   source,
		   new String[] {
			   "documentation", "Description from internet, e.g. Wikipedia. Abstraction of parties who benefit from organization offerings. Not necessarily pay for them or use them. Weight - manual or computed with decision analysis. Manual may be sales volume, budget contribution for internal customers, population, percentage in profit generation. E.g. a grant size is dependent on how many personas adopt a product. References parties are persona representatives. Such parties may be involved in need identification and prioritization. Defined at EngineeringOrgUnit level - may be internal personas and internal products. May have base personas and personas can be organized into a hierarchy. Base personas define common needs, what everybody needs. Base personas may be abstract - no representatives and no own weight.\nReference roles in addition to representatives - internal clients.\n\n\nPersona benefits from engineering organization outputs (offerings). Not necessarily buys or uses.\nPersona is an engineered element - owner, issues, ... representatives\n\nResources reference and palette - markdown docs, rigel flows, ... - party level. Embedded and references. Folders\n\n\nRigel activities defined in roles (actor). Prof extensions report which flows role participates in.\n\nNeeds, scenarios, offerings\n\nCustomer value and strategic value/alignment - objectives.\n"
		   });
		addAnnotation
		  (getPersona_Extends(),
		   source,
		   new String[] {
			   "documentation", "Description from internet, e.g. Wikipedia. Abstraction of parties who benefit from organization offerings. Not necessarily pay for them or use them. Weight - manual or computed with decision analysis. Manual may be sales volume, budget contribution for internal customers, population, percentage in profit generation. E.g. a grant size is dependent on how many personas adopt a product. References parties are persona representatives. Such parties may be involved in need identification and prioritization. Defined at EngineeringOrgUnit level - may be internal personas and internal products. May have base personas and personas can be organized into a hierarchy. Base personas define common needs, what everybody needs. Base personas may be abstract - no representatives and no own weight.\nReference roles in addition to representatives - internal clients.\n\n\nPersona benefits from engineering organization outputs (offerings). Not necessarily buys or uses.\nPersona is an engineered element - owner, issues, ... representatives\n\nResources reference and palette - markdown docs, rigel flows, ... - party level. Embedded and references. Folders\n\n\nRigel activities defined in roles (actor). Prof extensions report which flows role participates in.\n\nNeeds, scenarios, offerings\n\nCustomer value and strategic value/alignment - objectives.\n"
		   });
		addAnnotation
		  (needCategoryElementEClass,
		   source,
		   new String[] {
			   "documentation", "Base class for model elements which can be contained by need category - needs and sub-categories."
		   });
		addAnnotation
		  (needCategoryEClass,
		   source,
		   new String[] {
			   "documentation", "Grouping of persona needs."
		   });
		addAnnotation
		  (getNeedCategory_Elements(),
		   source,
		   new String[] {
			   "documentation", "Category elements."
		   });
		addAnnotation
		  (needEClass,
		   source,
		   new String[] {
			   "documentation", "Personas have needs which may be satisfied by organization offerings. Needs can be organized into a hierarchy and assigned weights either manually or using decision analysis techniques. Needs may be satisfied by offerings via scenarios explaining how a need is satisfied.\n\nMust have, need to have, delighter - here or at the offering level?"
		   });
		addAnnotation
		  (scenarioEClass,
		   source,
		   new String[] {
			   "documentation", "Scenario explains how a need is satisfied by offerings. A scenario references offerings and can be assigned weight - manually or computed. Scenarios can be organized into a hierarchy. Generally scenarios may have steps/flow and include other scenarios, it needs to be understood whether this level of detalization is necessary."
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
	}

} //EngineeringPackageImpl
