/**
 */
package org.nasdanika.rigel.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.nasdanika.rigel.Activity;
import org.nasdanika.rigel.FlowElement;
import org.nasdanika.rigel.IPackage;
import org.nasdanika.rigel.ActivityReference;
import org.nasdanika.rigel.Actor;
import org.nasdanika.rigel.Artifact;
import org.nasdanika.rigel.Association;
import org.nasdanika.rigel.Capability;
import org.nasdanika.rigel.End;
import org.nasdanika.rigel.Engineer;
import org.nasdanika.rigel.EngineeredElement;
import org.nasdanika.rigel.Flow;
import org.nasdanika.rigel.Issue;
import org.nasdanika.rigel.IssueImportance;
import org.nasdanika.rigel.IssueStatus;
import org.nasdanika.rigel.Milestone;
import org.nasdanika.rigel.ModelElement;
import org.nasdanika.rigel.PackageElement;
import org.nasdanika.rigel.Participant;
import org.nasdanika.rigel.Partition;
import org.nasdanika.rigel.Requirement;
import org.nasdanika.rigel.Resource;
import org.nasdanika.rigel.RigelFactory;
import org.nasdanika.rigel.RigelPackage;
import org.nasdanika.rigel.Source;
import org.nasdanika.rigel.Start;
import org.nasdanika.rigel.Target;
import org.nasdanika.rigel.Transition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RigelPackageImpl extends EPackageImpl implements RigelPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageElementEClass = null;

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
	private EClass iPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass participantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass flowEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass flowElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass startEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass targetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass endEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass partitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activityReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass artifactEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass associationEClass = null;

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
	private EClass milestoneEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass capabilityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass requirementEClass = null;

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
	 * @see org.nasdanika.rigel.RigelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private RigelPackageImpl() {
		super(eNS_URI, RigelFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link RigelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static RigelPackage init() {
		if (isInited) return (RigelPackage)EPackage.Registry.INSTANCE.getEPackage(RigelPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredRigelPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		RigelPackageImpl theRigelPackage = registeredRigelPackage instanceof RigelPackageImpl ? (RigelPackageImpl)registeredRigelPackage : new RigelPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theRigelPackage.createPackageContents();

		// Initialize created meta-data
		theRigelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theRigelPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(RigelPackage.eNS_URI, theRigelPackage);
		return theRigelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getModelElement() {
		return modelElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getModelElement_Name() {
		return (EAttribute)modelElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getModelElement_Url() {
		return (EAttribute)modelElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getModelElement_Description() {
		return (EAttribute)modelElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getModelElement_Configuration() {
		return (EAttribute)modelElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPackageElement() {
		return packageElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackageElement_Associations() {
		return (EReference)packageElementEClass.getEStructuralFeatures().get(0);
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
	public EClass getIPackage() {
		return iPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIPackage_Elements() {
		return (EReference)iPackageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPackage() {
		return packageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getParticipant() {
		return participantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getParticipant_Flows() {
		return (EReference)participantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getActor() {
		return actorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFlow() {
		return flowEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFlow_Elements() {
		return (EReference)flowEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFlow_Paricipants() {
		return (EReference)flowEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFlow_TotalSize() {
		return (EAttribute)flowEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFlow_TotalProgress() {
		return (EAttribute)flowEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFlowElement() {
		return flowElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSource() {
		return sourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSource_OutboundTransitions() {
		return (EReference)sourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSource_Outputs() {
		return (EReference)sourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStart() {
		return startEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTarget() {
		return targetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTarget_InboundTransitions() {
		return (EReference)targetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTarget_Inputs() {
		return (EReference)targetEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEnd() {
		return endEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPartition() {
		return partitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getActivity() {
		return activityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getActivity_Size() {
		return (EAttribute)activityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getActivity_Progress() {
		return (EAttribute)activityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getActivityReference() {
		return activityReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getActivityReference_Activity() {
		return (EReference)activityReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getArtifact() {
		return artifactEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getArtifact_Consumers() {
		return (EReference)artifactEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getArtifact_Producers() {
		return (EReference)artifactEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getArtifact_Children() {
		return (EReference)artifactEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getResource() {
		return resourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getResource_Children() {
		return (EReference)resourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getResource_Artifacts() {
		return (EReference)resourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTransition() {
		return transitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTransition_Target() {
		return (EReference)transitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTransition_Inputs() {
		return (EReference)transitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTransition_Results() {
		return (EReference)transitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAssociation() {
		return associationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAssociation_Target() {
		return (EReference)associationEClass.getEStructuralFeatures().get(0);
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
	public EReference getEngineer_Owns() {
		return (EReference)engineerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEngineer_Assignments() {
		return (EReference)engineerEClass.getEStructuralFeatures().get(1);
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
	public EReference getIssue_Implementation() {
		return (EReference)issueEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMilestone() {
		return milestoneEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMilestone_Size() {
		return (EAttribute)milestoneEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMilestone_Progress() {
		return (EAttribute)milestoneEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMilestone_TargetDate() {
		return (EAttribute)milestoneEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMilestone_Missed() {
		return (EAttribute)milestoneEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCapability() {
		return capabilityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCapability_RequiredBy() {
		return (EReference)capabilityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRequirement() {
		return requirementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRequirement_RequiredCapabilities() {
		return (EReference)requirementEClass.getEStructuralFeatures().get(0);
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
	public RigelFactory getRigelFactory() {
		return (RigelFactory)getEFactoryInstance();
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
		modelElementEClass = createEClass(MODEL_ELEMENT);
		createEAttribute(modelElementEClass, MODEL_ELEMENT__NAME);
		createEAttribute(modelElementEClass, MODEL_ELEMENT__URL);
		createEAttribute(modelElementEClass, MODEL_ELEMENT__DESCRIPTION);
		createEAttribute(modelElementEClass, MODEL_ELEMENT__CONFIGURATION);

		packageElementEClass = createEClass(PACKAGE_ELEMENT);
		createEReference(packageElementEClass, PACKAGE_ELEMENT__ASSOCIATIONS);

		engineeredElementEClass = createEClass(ENGINEERED_ELEMENT);
		createEReference(engineeredElementEClass, ENGINEERED_ELEMENT__OWNERS);
		createEReference(engineeredElementEClass, ENGINEERED_ELEMENT__ISSUES);

		iPackageEClass = createEClass(IPACKAGE);
		createEReference(iPackageEClass, IPACKAGE__ELEMENTS);

		packageEClass = createEClass(PACKAGE);

		participantEClass = createEClass(PARTICIPANT);
		createEReference(participantEClass, PARTICIPANT__FLOWS);

		actorEClass = createEClass(ACTOR);

		capabilityEClass = createEClass(CAPABILITY);
		createEReference(capabilityEClass, CAPABILITY__REQUIRED_BY);

		requirementEClass = createEClass(REQUIREMENT);
		createEReference(requirementEClass, REQUIREMENT__REQUIRED_CAPABILITIES);

		flowEClass = createEClass(FLOW);
		createEReference(flowEClass, FLOW__ELEMENTS);
		createEReference(flowEClass, FLOW__PARICIPANTS);
		createEAttribute(flowEClass, FLOW__TOTAL_SIZE);
		createEAttribute(flowEClass, FLOW__TOTAL_PROGRESS);

		flowElementEClass = createEClass(FLOW_ELEMENT);

		sourceEClass = createEClass(SOURCE);
		createEReference(sourceEClass, SOURCE__OUTBOUND_TRANSITIONS);
		createEReference(sourceEClass, SOURCE__OUTPUTS);

		startEClass = createEClass(START);

		targetEClass = createEClass(TARGET);
		createEReference(targetEClass, TARGET__INBOUND_TRANSITIONS);
		createEReference(targetEClass, TARGET__INPUTS);

		endEClass = createEClass(END);

		partitionEClass = createEClass(PARTITION);

		activityEClass = createEClass(ACTIVITY);
		createEAttribute(activityEClass, ACTIVITY__SIZE);
		createEAttribute(activityEClass, ACTIVITY__PROGRESS);

		milestoneEClass = createEClass(MILESTONE);
		createEAttribute(milestoneEClass, MILESTONE__SIZE);
		createEAttribute(milestoneEClass, MILESTONE__PROGRESS);
		createEAttribute(milestoneEClass, MILESTONE__TARGET_DATE);
		createEAttribute(milestoneEClass, MILESTONE__MISSED);

		activityReferenceEClass = createEClass(ACTIVITY_REFERENCE);
		createEReference(activityReferenceEClass, ACTIVITY_REFERENCE__ACTIVITY);

		artifactEClass = createEClass(ARTIFACT);
		createEReference(artifactEClass, ARTIFACT__CONSUMERS);
		createEReference(artifactEClass, ARTIFACT__PRODUCERS);
		createEReference(artifactEClass, ARTIFACT__CHILDREN);

		resourceEClass = createEClass(RESOURCE);
		createEReference(resourceEClass, RESOURCE__CHILDREN);
		createEReference(resourceEClass, RESOURCE__ARTIFACTS);

		transitionEClass = createEClass(TRANSITION);
		createEReference(transitionEClass, TRANSITION__TARGET);
		createEReference(transitionEClass, TRANSITION__INPUTS);
		createEReference(transitionEClass, TRANSITION__RESULTS);

		associationEClass = createEClass(ASSOCIATION);
		createEReference(associationEClass, ASSOCIATION__TARGET);

		engineerEClass = createEClass(ENGINEER);
		createEReference(engineerEClass, ENGINEER__OWNS);
		createEReference(engineerEClass, ENGINEER__ASSIGNMENTS);

		issueEClass = createEClass(ISSUE);
		createEAttribute(issueEClass, ISSUE__IMPORTANCE);
		createEAttribute(issueEClass, ISSUE__STATUS);
		createEReference(issueEClass, ISSUE__ASSIGNED_TO);
		createEAttribute(issueEClass, ISSUE__SIZE);
		createEAttribute(issueEClass, ISSUE__BENEFIT);
		createEReference(issueEClass, ISSUE__CHILDREN);
		createEReference(issueEClass, ISSUE__IMPLEMENTATION);

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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		packageElementEClass.getESuperTypes().add(this.getModelElement());
		packageEClass.getESuperTypes().add(this.getPackageElement());
		packageEClass.getESuperTypes().add(this.getEngineeredElement());
		packageEClass.getESuperTypes().add(this.getIPackage());
		actorEClass.getESuperTypes().add(this.getPackageElement());
		actorEClass.getESuperTypes().add(this.getEngineeredElement());
		actorEClass.getESuperTypes().add(this.getParticipant());
		flowEClass.getESuperTypes().add(this.getPackageElement());
		flowEClass.getESuperTypes().add(this.getEngineeredElement());
		flowEClass.getESuperTypes().add(this.getRequirement());
		flowElementEClass.getESuperTypes().add(this.getModelElement());
		sourceEClass.getESuperTypes().add(this.getFlowElement());
		startEClass.getESuperTypes().add(this.getPackageElement());
		startEClass.getESuperTypes().add(this.getSource());
		targetEClass.getESuperTypes().add(this.getFlowElement());
		endEClass.getESuperTypes().add(this.getPackageElement());
		endEClass.getESuperTypes().add(this.getTarget());
		partitionEClass.getESuperTypes().add(this.getFlow());
		partitionEClass.getESuperTypes().add(this.getFlowElement());
		activityEClass.getESuperTypes().add(this.getFlow());
		activityEClass.getESuperTypes().add(this.getSource());
		activityEClass.getESuperTypes().add(this.getTarget());
		milestoneEClass.getESuperTypes().add(this.getPackageElement());
		milestoneEClass.getESuperTypes().add(this.getSource());
		milestoneEClass.getESuperTypes().add(this.getTarget());
		activityReferenceEClass.getESuperTypes().add(this.getPackageElement());
		activityReferenceEClass.getESuperTypes().add(this.getSource());
		activityReferenceEClass.getESuperTypes().add(this.getTarget());
		artifactEClass.getESuperTypes().add(this.getPackageElement());
		artifactEClass.getESuperTypes().add(this.getEngineeredElement());
		resourceEClass.getESuperTypes().add(this.getPackageElement());
		resourceEClass.getESuperTypes().add(this.getEngineeredElement());
		resourceEClass.getESuperTypes().add(this.getCapability());
		transitionEClass.getESuperTypes().add(this.getModelElement());
		associationEClass.getESuperTypes().add(this.getModelElement());
		engineerEClass.getESuperTypes().add(this.getPackageElement());
		issueEClass.getESuperTypes().add(this.getModelElement());
		issueEClass.getESuperTypes().add(this.getRequirement());

		// Initialize classes, features, and operations; add parameters
		initEClass(modelElementEClass, ModelElement.class, "ModelElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModelElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelElement_Url(), ecorePackage.getEString(), "url", null, 0, 1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelElement_Description(), ecorePackage.getEString(), "description", null, 0, 1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelElement_Configuration(), ecorePackage.getEString(), "configuration", null, 0, 1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(packageElementEClass, PackageElement.class, "PackageElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPackageElement_Associations(), this.getAssociation(), null, "associations", null, 0, -1, PackageElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(engineeredElementEClass, EngineeredElement.class, "EngineeredElement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEngineeredElement_Owners(), this.getEngineer(), this.getEngineer_Owns(), "owners", null, 0, -1, EngineeredElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEngineeredElement_Issues(), this.getIssue(), null, "issues", null, 0, -1, EngineeredElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iPackageEClass, IPackage.class, "IPackage", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIPackage_Elements(), this.getPackageElement(), null, "elements", null, 0, -1, IPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(packageEClass, org.nasdanika.rigel.Package.class, "Package", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(participantEClass, Participant.class, "Participant", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParticipant_Flows(), this.getFlow(), this.getFlow_Paricipants(), "flows", null, 0, -1, Participant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(actorEClass, Actor.class, "Actor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(capabilityEClass, Capability.class, "Capability", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCapability_RequiredBy(), this.getRequirement(), this.getRequirement_RequiredCapabilities(), "requiredBy", null, 0, -1, Capability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(requirementEClass, Requirement.class, "Requirement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRequirement_RequiredCapabilities(), this.getCapability(), this.getCapability_RequiredBy(), "requiredCapabilities", null, 0, -1, Requirement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(flowEClass, Flow.class, "Flow", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFlow_Elements(), this.getFlowElement(), null, "elements", null, 0, -1, Flow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFlow_Paricipants(), this.getParticipant(), this.getParticipant_Flows(), "paricipants", null, 0, -1, Flow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFlow_TotalSize(), ecorePackage.getEDouble(), "totalSize", null, 0, 1, Flow.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFlow_TotalProgress(), ecorePackage.getEInt(), "totalProgress", null, 0, 1, Flow.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(flowElementEClass, FlowElement.class, "FlowElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(sourceEClass, Source.class, "Source", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSource_OutboundTransitions(), this.getTransition(), null, "outboundTransitions", null, 0, -1, Source.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSource_Outputs(), this.getArtifact(), this.getArtifact_Producers(), "outputs", null, 0, -1, Source.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(startEClass, Start.class, "Start", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(targetEClass, Target.class, "Target", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTarget_InboundTransitions(), this.getTransition(), this.getTransition_Target(), "inboundTransitions", null, 0, -1, Target.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTarget_Inputs(), this.getArtifact(), this.getArtifact_Consumers(), "inputs", null, 0, -1, Target.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(endEClass, End.class, "End", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(partitionEClass, Partition.class, "Partition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(activityEClass, Activity.class, "Activity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getActivity_Size(), ecorePackage.getEDouble(), "size", null, 0, 1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivity_Progress(), ecorePackage.getEInt(), "progress", null, 0, 1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(milestoneEClass, Milestone.class, "Milestone", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMilestone_Size(), ecorePackage.getEDouble(), "size", null, 0, 1, Milestone.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMilestone_Progress(), ecorePackage.getEInt(), "progress", null, 0, 1, Milestone.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMilestone_TargetDate(), ecorePackage.getEDate(), "targetDate", null, 0, 1, Milestone.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMilestone_Missed(), ecorePackage.getEBoolean(), "missed", null, 0, 1, Milestone.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(activityReferenceEClass, ActivityReference.class, "ActivityReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActivityReference_Activity(), this.getActivity(), null, "activity", null, 0, 1, ActivityReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(artifactEClass, Artifact.class, "Artifact", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getArtifact_Consumers(), this.getTarget(), this.getTarget_Inputs(), "consumers", null, 0, -1, Artifact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArtifact_Producers(), this.getSource(), this.getSource_Outputs(), "producers", null, 0, -1, Artifact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArtifact_Children(), this.getArtifact(), null, "children", null, 0, -1, Artifact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceEClass, Resource.class, "Resource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResource_Children(), this.getResource(), null, "children", null, 0, -1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResource_Artifacts(), this.getArtifact(), null, "artifacts", null, 0, -1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transitionEClass, Transition.class, "Transition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTransition_Target(), this.getTarget(), this.getTarget_InboundTransitions(), "target", null, 1, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransition_Inputs(), this.getArtifact(), null, "inputs", null, 0, -1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransition_Results(), this.getArtifact(), null, "results", null, 0, -1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(associationEClass, Association.class, "Association", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAssociation_Target(), this.getPackageElement(), null, "target", null, 1, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(engineerEClass, Engineer.class, "Engineer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEngineer_Owns(), this.getEngineeredElement(), this.getEngineeredElement_Owners(), "owns", null, 0, -1, Engineer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEngineer_Assignments(), this.getIssue(), this.getIssue_AssignedTo(), "assignments", null, 0, -1, Engineer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(issueEClass, Issue.class, "Issue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIssue_Importance(), this.getIssueImportance(), "importance", "Medium", 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIssue_Status(), this.getIssueStatus(), "status", "Open", 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIssue_AssignedTo(), this.getEngineer(), this.getEngineer_Assignments(), "assignedTo", null, 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIssue_Size(), ecorePackage.getEDouble(), "size", null, 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIssue_Benefit(), ecorePackage.getEDouble(), "benefit", null, 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIssue_Children(), this.getIssue(), null, "children", null, 0, -1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIssue_Implementation(), this.getActivity(), null, "implementation", null, 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
			   "documentation", "Workflow model."
		   });
		addAnnotation
		  (modelElementEClass,
		   source,
		   new String[] {
			   "documentation", "Base class for other model elements."
		   });
		addAnnotation
		  (getModelElement_Name(),
		   source,
		   new String[] {
			   "documentation", "Element name. "
		   });
		addAnnotation
		  (getModelElement_Url(),
		   source,
		   new String[] {
			   "documentation", "Optional element URL. Resolved relative to the containing element URL. "
		   });
		addAnnotation
		  (getModelElement_Description(),
		   source,
		   new String[] {
			   "documentation", "Element description in [markdown](https://en.wikipedia.org/wiki/Markdown)."
		   });
		addAnnotation
		  (getModelElement_Configuration(),
		   source,
		   new String[] {
			   "documentation", "Element configuration in [YAML](https://en.wikipedia.org/wiki/YAML). Configuration is element-specific and it can be used for, say, analysis or runtime configuration. "
		   });
		addAnnotation
		  (packageElementEClass,
		   source,
		   new String[] {
			   "documentation", "Model element which can be contained by a package."
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
		  (iPackageEClass,
		   source,
		   new String[] {
			   "documentation", "Abstract container of package elements."
		   });
		addAnnotation
		  (getIPackage_Elements(),
		   source,
		   new String[] {
			   "documentation", "Container elements."
		   });
		addAnnotation
		  (packageEClass,
		   source,
		   new String[] {
			   "documentation", "Container of package elements."
		   });
		addAnnotation
		  (getParticipant_Flows(),
		   source,
		   new String[] {
			   "documentation", "Activities in which this actor participates."
		   });
		addAnnotation
		  (actorEClass,
		   source,
		   new String[] {
			   "documentation", "Actors perform activities using resources consuming and producing artifacts."
		   });
		addAnnotation
		  (capabilityEClass,
		   source,
		   new String[] {
			   "documentation", "Capability is something required by an issue."
		   });
		addAnnotation
		  (getCapability_RequiredBy(),
		   source,
		   new String[] {
			   "documentation", "Activities using/leveraging this resource."
		   });
		addAnnotation
		  (requirementEClass,
		   source,
		   new String[] {
			   "documentation", "Requirement of a zero or more capabilities."
		   });
		addAnnotation
		  (getRequirement_RequiredCapabilities(),
		   source,
		   new String[] {
			   "documentation", "Capabilities needed to satisfy the requirement."
		   });
		addAnnotation
		  (flowEClass,
		   source,
		   new String[] {
			   "documentation", "Flow is an abstract container of flow elements."
		   });
		addAnnotation
		  (getFlow_Elements(),
		   source,
		   new String[] {
			   "documentation", "Flows are composed from flow elements."
		   });
		addAnnotation
		  (getFlow_Paricipants(),
		   source,
		   new String[] {
			   "documentation", "One or more actors participate in completion of a flow."
		   });
		addAnnotation
		  (getFlow_TotalSize(),
		   source,
		   new String[] {
			   "documentation", "The sum of total sizes of this flow children plus the size of self if instance of activity."
		   });
		addAnnotation
		  (getFlow_TotalProgress(),
		   source,
		   new String[] {
			   "documentation", "Calculated total flow progress in percent. "
		   });
		addAnnotation
		  (flowElementEClass,
		   source,
		   new String[] {
			   "documentation", "Element of a (composite) activity."
		   });
		addAnnotation
		  (sourceEClass,
		   source,
		   new String[] {
			   "documentation", "Source of transitions."
		   });
		addAnnotation
		  (getSource_OutboundTransitions(),
		   source,
		   new String[] {
			   "documentation", "Outbound transitions."
		   });
		addAnnotation
		  (getSource_Outputs(),
		   source,
		   new String[] {
			   "documentation", "Artifacts output/produced by this source."
		   });
		addAnnotation
		  (startEClass,
		   source,
		   new String[] {
			   "documentation", "Start pseudo-activity."
		   });
		addAnnotation
		  (targetEClass,
		   source,
		   new String[] {
			   "documentation", "Target of transitions."
		   });
		addAnnotation
		  (getTarget_InboundTransitions(),
		   source,
		   new String[] {
			   "documentation", "Inbound transitions."
		   });
		addAnnotation
		  (getTarget_Inputs(),
		   source,
		   new String[] {
			   "documentation", "Input artifacts consumed by this target."
		   });
		addAnnotation
		  (endEClass,
		   source,
		   new String[] {
			   "documentation", "End pseudo-activity."
		   });
		addAnnotation
		  (partitionEClass,
		   source,
		   new String[] {
			   "documentation", "Partition is a grouping of flow elements associating participants and resources with the contained elements. "
		   });
		addAnnotation
		  (activityEClass,
		   source,
		   new String[] {
			   "documentation", "By performing an activity participants produce outputs from inputs using resources."
		   });
		addAnnotation
		  (getActivity_Size(),
		   source,
		   new String[] {
			   "documentation", "Activity size in some unit used by all activities. For example - hours, points, dollars."
		   });
		addAnnotation
		  (getActivity_Progress(),
		   source,
		   new String[] {
			   "documentation", "Activity progress in percent. Value between 0 and 100. Progress can be used in \"instance\" models which are used to model and track execution of an actual effort as opposed to \"template\" models which explain steps to complete an effort."
		   });
		addAnnotation
		  (milestoneEClass,
		   source,
		   new String[] {
			   "documentation", "A milestone shows an important achievement in a flow. \nThe milestones represent a sequence of events that incrementally build up until flow completion."
		   });
		addAnnotation
		  (getMilestone_Size(),
		   source,
		   new String[] {
			   "documentation", "The sum of size of all activities which need to be completed to reach this milestone from the start of the previous milestone(s)."
		   });
		addAnnotation
		  (getMilestone_Progress(),
		   source,
		   new String[] {
			   "documentation", "Calculated total progress toward achiveing the milestone in percent. "
		   });
		addAnnotation
		  (getMilestone_Missed(),
		   source,
		   new String[] {
			   "documentation", "True if target date is set, the current date is after the target date and progress is less than 100%"
		   });
		addAnnotation
		  (activityReferenceEClass,
		   source,
		   new String[] {
			   "documentation", "References a shared activity defined elsewhere."
		   });
		addAnnotation
		  (getActivityReference_Activity(),
		   source,
		   new String[] {
			   "documentation", "By performing an activity participants produce outputs from inputs using resources."
		   });
		addAnnotation
		  (artifactEClass,
		   source,
		   new String[] {
			   "documentation", "Artifact is something output/produced by a source and consumed by a target as input. For example - source code, user guide, binary code. Artifacts are passed between activities over transitions."
		   });
		addAnnotation
		  (getArtifact_Consumers(),
		   source,
		   new String[] {
			   "documentation", "Targets consuming this artifact as their input."
		   });
		addAnnotation
		  (getArtifact_Producers(),
		   source,
		   new String[] {
			   "documentation", "Sources emitting this artifact as their output."
		   });
		addAnnotation
		  (getArtifact_Children(),
		   source,
		   new String[] {
			   "documentation", "Artifacts can be nested. E.g. a zip archive contains directories which contain files."
		   });
		addAnnotation
		  (resourceEClass,
		   source,
		   new String[] {
			   "documentation", "Something leveraged by an actor to perform an activity. For example - a tool."
		   });
		addAnnotation
		  (getResource_Children(),
		   source,
		   new String[] {
			   "documentation", "Resource can be nested. E.g. a virtual machine may host a web server."
		   });
		addAnnotation
		  (getResource_Artifacts(),
		   source,
		   new String[] {
			   "documentation", "Resources may host artifacts."
		   });
		addAnnotation
		  (transitionEClass,
		   source,
		   new String[] {
			   "documentation", "Transition from its containing source element to a target element. May pass artifacts in both directions. E.g. a transition to \"Commit code\" may take \"Source code\" as input and return \"Commit ID\" as a result."
		   });
		addAnnotation
		  (getTransition_Target(),
		   source,
		   new String[] {
			   "documentation", "Transition target."
		   });
		addAnnotation
		  (getTransition_Inputs(),
		   source,
		   new String[] {
			   "documentation", "Artifacts passed by the source to the transition and delivered to the target."
		   });
		addAnnotation
		  (getTransition_Results(),
		   source,
		   new String[] {
			   "documentation", "Artifacts returned by the target to be delivered to the source."
		   });
		addAnnotation
		  (associationEClass,
		   source,
		   new String[] {
			   "documentation", "A generic relationship between model elements."
		   });
		addAnnotation
		  (engineerEClass,
		   source,
		   new String[] {
			   "documentation", "Engineers own engineered elements and are assigned issues associated with these elements."
		   });
		addAnnotation
		  (getEngineer_Owns(),
		   source,
		   new String[] {
			   "documentation", "Elements which this engineer owns. Ownership is propagated down to child elements recursively, unless these elements have an explicitly assigned owner. Also issues associated with owned elements and not having an engineer explicitly assigned to them are implicitly assigned to the element owner."
		   });
		addAnnotation
		  (getEngineer_Assignments(),
		   source,
		   new String[] {
			   "documentation", "Engineer\'s assigned issues."
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
		  (getIssue_Implementation(),
		   source,
		   new String[] {
			   "documentation", "Activity providing details about how to implement this issue.\n\n"
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
			   "label_ru", "Nasdanika.\u0420\u0438\u0433\u0435\u043b\u044c",
			   "Documentation_ru", "Nasdanika Rigel -  \u0438\u043d\u0441\u0442\u0440\u0443\u043c\u0435\u043d\u0442 \u043c\u043e\u0434\u0435\u043b\u0438\u0440\u043e\u0432\u0430\u043d\u0438\u044f, \u043e\u043f\u0438\u0441\u0430\u043d\u0438\u044f \u0438 \u043a\u043e\u043d\u0442\u0440\u0430\u043b\u044f \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u044f \u0440\u0430\u0431\u043e\u0447\u0438\u0445 \u043f\u043e\u0442\u043e\u043a\u043e\u0432 \u0438 \u043f\u0440\u043e\u0446\u0435\u0441\u0441\u043e\u0432. "
		   });
		addAnnotation
		  (modelElementEClass,
		   source,
		   new String[] {
			   "label_ru", "\u042d\u043b\u0435\u043c\u0435\u043d\u0442 \u043c\u043e\u0434\u0435\u043b\u0438",
			   "Documentation_ru", "\u0411\u0430\u0437\u043e\u0432\u044b\u0439 \u043a\u043b\u0430\u0441\u0441 \u0434\u043b\u044f \u0434\u0440\u0443\u0433\u0438\u0445 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u0432 \u043c\u043e\u0434\u0435\u043b\u0438"
		   });
		addAnnotation
		  (getModelElement_Name(),
		   source,
		   new String[] {
			   "label_ru", "\u041d\u0430\u0438\u043c\u0435\u043d\u043e\u0432\u0430\u043d\u0438\u0435",
			   "Documentation_ru", "\u041d\u0430\u0438\u043c\u0435\u043d\u043e\u0432\u0430\u043d\u0438\u0435 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430 \u043c\u043e\u0434\u0435\u043b\u0438"
		   });
		addAnnotation
		  (getModelElement_Description(),
		   source,
		   new String[] {
			   "label_ru", "\u041e\u043f\u0438\u0441\u0430\u043d\u0438\u0435",
			   "Documentation_ru", "\u041e\u043f\u0438\u0441\u0430\u043d\u0438\u0435 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430 \u043c\u043e\u0434\u0435\u043b\u0438 \u0441 \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0435\u043c \u0441\u0438\u043d\u0442\u0430\u043a\u0441\u0438\u0441\u0430 [markdown](https://en.wikipedia.org/wiki/Markdown)."
		   });
		addAnnotation
		  (getModelElement_Configuration(),
		   source,
		   new String[] {
			   "label_ru", "\u041a\u043e\u043d\u0444\u0438\u0433\u0443\u0440\u0430\u0446\u0438\u044f",
			   "Documentation_ru", "*\u041a\u043e\u043d\u0444\u0438\u0433\u0443\u0440\u0430\u0446\u0438\u044f* \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430 \u0432 [YAML] (https://en.wikipedia.org/wiki/YAML). \u041a\u043e\u043d\u0444\u0438\u0433\u0443\u0440\u0430\u0446\u0438\u044f \u0437\u0430\u0432\u0438\u0441\u0438\u0442 \u043e\u0442 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430 \u0438 \u043c\u043e\u0436\u0435\u0442 \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c\u0441\u044f, \u043d\u0430\u043f\u0440\u0438\u043c\u0435\u0440, \u0434\u043b\u044f \u0430\u043d\u0430\u043b\u0438\u0437\u0430 \u0438\u043b\u0438 \u043a\u043e\u043d\u0444\u0438\u0433\u0443\u0440\u0430\u0446\u0438\u0438 \u0432\u043e \u0432\u0440\u0435\u043c\u044f \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u044f."
		   });
		addAnnotation
		  (packageElementEClass,
		   source,
		   new String[] {
			   "label_ru", "\u0423\u043f\u0430\u043a\u043e\u0432\u0430\u043d\u043d\u044b\u0439 \u044d\u043b\u0435\u043c\u0435\u043d\u0442",
			   "Documentation_ru", "\u042d\u043b\u0435\u043c\u0435\u043d\u0442 \u043c\u043e\u0434\u0435\u043b\u0438, \u043a\u043e\u0442\u0440\u044b\u0439 \u043c\u043e\u0436\u0435\u0442 \u0431\u044b\u0442\u044c \u0432\u043a\u043b\u044e\u0447\u0435\u043d \u0432 \u043f\u0430\u043a\u0435\u0442"
		   });
		addAnnotation
		  (engineeredElementEClass,
		   source,
		   new String[] {
			   "label_ru", "?\u0423\u043f\u0440\u0430\u0432\u043b\u044f\u0435\u043c\u044b\u0439 \u044d\u043b\u0435\u043c\u0435\u043d\u0442",
			   "Documentation_ru", "\u0411\u0430\u0437\u043e\u0432\u044b\u0439 \u043a\u043b\u0430\u0441\u0441 \u0434\u043b\u044f \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430 \u043c\u043e\u0434\u0435\u043b\u0438, \u0432\u043b\u0430\u0434\u0435\u043b\u044c\u0446\u0435\u043c \u043a\u043e\u0442\u043e\u0440\u043e\u0433\u043e \u044f\u0432\u043b\u044f\u0435\u0442\u0441\u044f \u0438\u043d\u0436\u0435\u043d\u0435\u0440. \u041c\u043e\u0436\u0435\u0442 \u0441\u043e\u0434\u0435\u0440\u0436\u0430\u0442\u044c \u043f\u0440\u043e\u0431\u043b\u0435\u043c\u043c\u044b/\u0437\u0430\u0434\u0430\u0447\u0438."
		   });
		addAnnotation
		  (getEngineeredElement_Owners(),
		   source,
		   new String[] {
			   "label_ru", "\u0418\u043d\u0436\u0435\u043d\u0435\u0440",
			   "Documentation_ru", "\u0418\u043d\u0436\u0435\u043d\u0435\u0440, \u0432\u043b\u0430\u0434\u0435\u043b\u0435\u0446 \u044d\u0442\u043e\u0433\u043e \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430."
		   });
		addAnnotation
		  (getEngineeredElement_Issues(),
		   source,
		   new String[] {
			   "label_ru", "\u041f\u0440\u043e\u0431\u043b\u0435\u043c\u044b",
			   "Documentation_ru", "\u041f\u0440\u043e\u0431\u043b\u0435\u043c\u044b, \u0441\u0432\u044f\u0437\u0430\u043d\u043d\u044b\u0435 \u0441 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u043c - \u043f\u0440\u043e\u0431\u043b\u0435\u043c\u044b / \u0431\u043e\u043b\u0435\u0432\u044b\u0435 \u0442\u043e\u0447\u043a\u0438, \u0432\u043e\u0437\u043c\u043e\u0436\u043d\u043e\u0441\u0442\u0438 \u0443\u043b\u0443\u0447\u0448\u0435\u043d\u0438\u044f / \u0443\u043b\u0443\u0447\u0448\u0435\u043d\u0438\u044f."
		   });
		addAnnotation
		  (getIPackage_Elements(),
		   source,
		   new String[] {
			   "label_ru", "\u042d\u043b\u0435\u043c\u0435\u043d\u0442\u044b \u043f\u0430\u043a\u0435\u0442\u0430",
			   "documentation_ru", "\u042d\u043b\u0435\u043c\u0435\u043d\u0442\u044b \u043f\u0430\u043a\u0435\u0442\u0430"
		   });
		addAnnotation
		  (packageEClass,
		   source,
		   new String[] {
			   "label_ru", "\u041f\u0430\u043a\u0435\u0442",
			   "documentation_ru", "\u041f\u0430\u043a\u0435\u0442 \u0441\u043e\u0434\u0435\u0440\u0436\u0438\u0442 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u044b \u043c\u043e\u0434\u0435\u043b\u0438."
		   });
		addAnnotation
		  (getParticipant_Flows(),
		   source,
		   new String[] {
			   "label_ru", "\u0414\u0435\u0439\u0441\u0442\u0432\u0438\u044f",
			   "documentation_ru", "\u0414\u0435\u0439\u0441\u0442\u0432\u0438\u044f \u0432 \u043a\u043e\u0442\u043e\u0440\u044b\u0445 \u0443\u0447\u0430\u0441\u0442\u0432\u0443\u0435\u0442 \u0438\u0441\u043f\u043e\u043b\u043d\u0438\u0442\u0435\u043b\u044c"
		   });
		addAnnotation
		  (actorEClass,
		   source,
		   new String[] {
			   "label_ru", "\u0418\u0441\u043f\u043e\u043b\u043d\u0438\u0442\u0435\u043b\u044c",
			   "documentation_ru", "\u041b\u0438\u0446\u043e, \u043e\u0441\u0443\u0449\u0435\u0441\u0442\u0432\u043b\u044f\u044e\u0449\u0435\u0435 \u0434\u0435\u0439\u0441\u0442\u0432\u0438\u044f, \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u044f \u0440\u0435\u0441\u0443\u0440\u0441\u044b \u0438 \u043f\u0440\u043e\u0438\u0437\u0432\u043e\u0434\u044f \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b"
		   });
		addAnnotation
		  (getCapability_RequiredBy(),
		   source,
		   new String[] {
			   "label_ru", "\u041f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u0435\u043b\u0438",
			   "documentation_ru", "\u0420\u0430\u0431\u043e\u0442\u044b \u0438 \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u0438 \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u044e\u0449\u0438\u0435 \u0434\u0430\u043d\u043d\u044b\u0439 \u0440\u0435\u0441\u0443\u0440\u0441."
		   });
		addAnnotation
		  (getFlow_Elements(),
		   source,
		   new String[] {
			   "label_ru", "\u041e\u043f\u0435\u0440\u0430\u0446\u0438\u044f",
			   "documentation_ru", "\u0420\u0430\u0431\u043e\u0442\u0430 \u043c\u043e\u0436\u0435\u0442 \u0431\u044b\u0442\u044c \u0440\u0430\u0437\u0434\u0435\u043b\u0435\u043d\u0430 \u043d\u0430 \u0441\u043e\u0441\u0442\u0430\u0432\u043b\u044f\u044e\u0449\u0438\u0435 \u0435\u0451 \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u0438."
		   });
		addAnnotation
		  (getFlow_Paricipants(),
		   source,
		   new String[] {
			   "label_ru", "\u0423\u0447\u0430\u0441\u0442\u043d\u0438\u043a\u0438",
			   "documentation_ru", "\u041e\u0434\u0438\u043d \u0438\u043b\u0438 \u0431\u043e\u043b\u0435\u0435 \u0438\u0441\u043f\u043e\u043b\u043d\u0438\u0442\u0435\u043b\u0435\u0439, \u0443\u0447\u0430\u0441\u0442\u0432\u0443\u044e\u0449\u0438\u0445 \u0432 \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u0438 \u0440\u0430\u0431\u043e\u0442\u044b."
		   });
		addAnnotation
		  (getFlow_TotalSize(),
		   source,
		   new String[] {
			   "label_ru", "\u0421\u0443\u043c\u043c\u0430\u0440\u043d\u044b\u0439 \u0440\u0430\u0437\u043c\u0435\u0440",
			   "documentation_ru", "\u0421\u0443\u043c\u043c\u0430 \u0440\u0430\u0437\u043c\u0435\u0440\u043e\u0432 \u0432\u0441\u0435\u0445 \u0434\u043e\u0447\u0435\u0440\u043d\u0438\u0445 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u0432 \u0440\u0430\u0431\u043e\u0442\u044b"
		   });
		addAnnotation
		  (getFlow_TotalProgress(),
		   source,
		   new String[] {
			   "label_ru", "\u0421\u0443\u043c\u043c\u0430\u0440\u043d\u044b\u0439 \u043f\u0440\u043e\u0433\u0440\u0435\u0441\u0441",
			   "documentation_ru", "\u0421\u0443\u043c\u043c\u0430\u0440\u043d\u0430\u044f \u043e\u0446\u0435\u043d\u043a\u0430 \u043f\u0440\u043e\u0433\u0440\u0435\u0441\u0441\u0430 \u0432\u0441\u0435\u0439 \u0440\u0430\u0431\u043e\u0442\u044b (\u0441 \u0443\u0447\u0435\u0442\u043e\u043c \u0440\u0430\u0437\u043c\u0435\u0440\u0430 \u0438 \u0432\u0430\u0436\u043d\u043e\u0441\u0442\u0438 \u0434\u043e\u0447\u0435\u0440\u043d\u0438\u0445 \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u0439)"
		   });
		addAnnotation
		  (flowElementEClass,
		   source,
		   new String[] {
			   "label_ru", "\u041e\u043f\u0435\u0440\u0430\u0446\u0438\u044f",
			   "documentation_ru", "\u041e\u043f\u0435\u0440\u0430\u0446\u0438\u044f - \u044d\u0442\u043e \u044d\u043b\u0435\u043c\u0435\u043d\u0442 \u0441\u043b\u043e\u0436\u043d\u043e\u0433\u043e (\u0441\u043e\u0441\u0442\u0430\u0432\u043d\u043e\u0433\u043e) \u0434\u0435\u0439\u0441\u0442\u0432\u0438\u044f"
		   });
		addAnnotation
		  (sourceEClass,
		   source,
		   new String[] {
			   "label_ru", "\u0418\u0441\u0442\u043e\u0447\u043d\u0438\u043a",
			   "documentation_ru", "\u0418\u0441\u0442\u043e\u0447\u043d\u0438\u043a (\u0447\u0435\u0433\u043e?) \u0422\u043e\u0447\u043a\u0430 \u0432\u0445\u043e\u0434\u0430?"
		   });
		addAnnotation
		  (getSource_OutboundTransitions(),
		   source,
		   new String[] {
			   "label_ru", "\u0418\u0441\u0445\u043e\u0434\u044f\u0449\u0438\u0435",
			   "documentation_ru", "\u0418\u0441\u0445\u043e\u0434\u044f\u0449\u0438\u0435 (\u0447\u0442\u043e?) \u0422\u043e\u0447\u043a\u0438 \u0432\u044b\u0445\u043e\u0434\u0430?"
		   });
		addAnnotation
		  (getSource_Outputs(),
		   source,
		   new String[] {
			   "label_ru", "\u0410\u0440\u0442\u0435\u0444\u0430\u043a\u0442",
			   "documentation_ru", "\u0410\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b \u043f\u0440\u043e\u0438\u0437\u0432\u043e\u0434\u0438\u043c\u044b\u0435 \u044d\u0442\u0438\u043c \u0438\u0441\u0442\u043e\u0447\u043d\u0438\u043a\u043e\u043c"
		   });
		addAnnotation
		  (startEClass,
		   source,
		   new String[] {
			   "label_ru", "\u0421\u0442\u0430\u0440\u0442",
			   "documentation_ru", "\u0421\u0442\u0430\u0440\u0442 \u043f\u0441\u0435\u0432\u0434\u043e- \u0430\u043a\u0442\u0438\u0432\u043d\u043e\u0441\u0442\u0438"
		   });
		addAnnotation
		  (targetEClass,
		   source,
		   new String[] {
			   "label_ru", "\u0426\u0435\u043b\u044c",
			   "documentation_ru", "\u0426\u0435\u043b\u044c \u043f\u0435\u0440\u0435\u0434\u0430\u0447\u0438 \u043f\u043e\u0442\u043e\u043a\u0430 \u0434\u0430\u043d\u043d\u044b\u0445 \u0438  \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u043e\u0432"
		   });
		addAnnotation
		  (getTarget_InboundTransitions(),
		   source,
		   new String[] {
			   "label_ru", "\u0412\u0445\u043e\u0434 \u0432 \u0440\u0430\u0431\u043e\u0442\u0443/\u043e\u043f\u0435\u0440\u0430\u0446\u0438\u044e",
			   "documentation_ru", "???Inbound transitions."
		   });
		addAnnotation
		  (getTarget_Inputs(),
		   source,
		   new String[] {
			   "label_ru", "\u0412\u0445\u043e\u0434\u044f\u0449\u0438\u0435 \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b",
			   "documentation_ru", "\u0412\u0445\u043e\u0434\u044f\u0449\u0438\u0435 \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b \u043d\u0435\u043e\u0431\u0445\u043e\u0434\u0438\u043c\u044b\u0435 \u0434\u043b\u044f \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u044f \u0446\u0435\u043b\u0435\u0432\u043e\u0433\u043e \u044d\u0442\u0430\u043f\u0430 \u0440\u0430\u0431\u043e\u0442\u044b"
		   });
		addAnnotation
		  (endEClass,
		   source,
		   new String[] {
			   "label_ru", "\u041a\u043e\u043d\u0435\u0446 \u0440\u0430\u0431\u043e\u0442\u044b",
			   "documentation_ru", "\u042d\u043b\u0435\u043c\u0435\u043d\u0442 \u0437\u0430\u0432\u0435\u0440\u0448\u0430\u044e\u0449\u0438\u0439 \u043f\u0441\u0435\u0432\u0434\u043e-\u0430\u043a\u0442\u0438\u0432\u043d\u043e\u0441\u0442\u044c"
		   });
		addAnnotation
		  (activityEClass,
		   source,
		   new String[] {
			   "label_ru", "\u0420\u0430\u0431\u043e\u0442\u0430",
			   "documentation_ru", "\u0420\u0430\u0431\u043e\u0442\u0430 - \u044d\u0442\u043e \u0442\u043e, \u0447\u0442\u043e \u043d\u0435\u043e\u0431\u0445\u043e\u0434\u0438\u043c\u043e \u0434\u043b\u044f \u043f\u043e\u043b\u0443\u0447\u0435\u043d\u0438\u044f \u0436\u0435\u043b\u0430\u0435\u043c\u043e\u0433\u043e \u0440\u0435\u0437\u0443\u043b\u044c\u0442\u0430\u0442\u0430. \n\n\u0412\u044b\u043f\u043e\u043b\u043d\u044f\u044f \u0434\u0435\u0439\u0441\u0442\u0432\u0438\u0435 \u0438\u0441\u043f\u043e\u043b\u043d\u0438\u0442\u0435\u043b\u044c \u043f\u043e\u043b\u0443\u0447\u0430\u0435\u0442 \u0432\u0445\u043e\u0434\u044f\u0449\u0438\u0435 \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b, \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0435\u0442 \u0440\u0435\u0441\u0443\u0440\u0441\u044b \u0438 \u0441\u043e\u0437\u0434\u0430\u0451\u0442 \u0438\u0441\u0445\u043e\u0434\u044f\u0449\u0438\u0435 \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b, \u044f\u0432\u043b\u044f\u044e\u0449\u0438\u0435\u0441\u044f \u043f\u0440\u043e\u043c\u0435\u0436\u0443\u0442\u043e\u0447\u043d\u044b\u043c\u0438 \u0438\u043b\u0438 \u043a\u043e\u043d\u0435\u0447\u043d\u044b\u043c\u0438 \u0440\u0435\u0437\u0443\u043b\u044c\u0442\u0430\u0442\u0430\u043c\u0438 \u043f\u0440\u043e\u0446\u0435\u0441\u0441\u0430.\n\n\u0412\u0430\u0440\u0438\u0430\u043d\u0442\u044b \u043f\u0435\u0440\u0435\u0432\u043e\u0434\u0430:\n\n\n* \u0414\u0435\u0439\u0441\u0442\u0432\u0438\u0435 / \u042d\u043b\u0435\u043c\u0435\u043d\u0442\u0430\u0440\u043d\u043e\u0435 \u0434\u0435\u0439\u0441\u0442\u0432\u0438\u0435\n* \u041f\u0440\u043e\u0446\u0435\u0441\u0441/\u041e\u043f\u0435\u0440\u0430\u0446\u0438\u044f\n* \u0420\u0430\u0431\u043e\u0442\u0430 / \u041e\u043f\u0435\u0440\u0430\u0446\u0438\u044f "
		   });
		addAnnotation
		  (getActivity_Size(),
		   source,
		   new String[] {
			   "label_ru", "\u0420\u0430\u0437\u043c\u0435\u0440",
			   "documentation_ru", "\u041e\u0446\u0435\u043d\u043a\u0430 \u0440\u0430\u0431\u043e\u0442\u044b / \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u0438 \u0432 \u043a\u0430\u043a\u0438\u0445-\u043b\u0438\u0431\u043e \u0435\u0434\u0438\u043d\u0438\u0446\u0430\u0445: \u0442\u0440\u0443\u0434\u043e-\u0447\u0430\u0441\u0430\u0445, \u043f\u0440\u0438\u0441\u0435\u0441\u0442\u0430\u0445, \u0434\u0435\u043d\u044c\u0433\u0430\u0445."
		   });
		addAnnotation
		  (getActivity_Progress(),
		   source,
		   new String[] {
			   "label_ru", "\u041f\u0440\u043e\u0433\u0440\u0435\u0441\u0441",
			   "documentation_ru", "\u041e\u0446\u0435\u043d\u043a\u0430 \u0441\u0442\u0435\u043f\u0435\u043d\u0438 \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u044f \u0440\u0430\u0431\u043e\u0442\u044b/ \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u0438 \u0432 \u043f\u0440\u043e\u0446\u0435\u043d\u0442\u0430\u0445. \u0417\u043d\u0430\u0447\u0435\u043d\u0438\u0435 \u043c\u0435\u0436\u0434\u0443 0 \u0438 100. \n\n\n\u041f\u0440\u043e\u0433\u0440\u0435\u0441\u0441 \u043c\u043e\u0436\u0435\u0442 \u0431\u044b\u0442\u044c \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d \u0434\u043b\u044f \u043e\u0446\u0435\u043d\u043a\u0438 \u043f\u0440\u0430\u043a\u0442\u0438\u0447\u0435\u0441\u043a\u043e\u0433\u043e \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u044f \u0440\u0430\u0431\u043e\u0447\u0435\u0433\u043e \u043f\u043e\u0442\u043e\u043a\u0430. \n\n\u0414\u043b\u044f \u044d\u0442\u043e\u0433\u043e \u043d\u0430 \u043e\u0441\u043d\u043e\u0432\u0430\u043d\u0438\u0438 \u0441\u043c\u043e\u0434\u0435\u043b\u0438\u0440\u043e\u0432\u0430\u043d\u043d\u043e\u0433\u043e \u0448\u0430\u0431\u043b\u043e\u043d\u0430, \u043e\u043f\u0438\u0441\u044b\u0432\u0430\u044e\u0449\u0435\u0433\u043e \u044d\u0442\u0430\u043f\u044b \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u044f \u0440\u0430\u0431\u043e\u0442\u044b \u0441\u043e\u0437\u0434\u0430\u0435\u0442\u0441\u044f \"\u0414\u0435\u0439\u0441\u0442\u0432\u0443\u044e\u0449\u0438\u0439 \u044d\u043a\u0437\u0435\u043c\u043f\u043b\u044f\u0440\" \u0440\u0430\u0431\u043e\u0447\u0435\u0433\u043e \u043f\u043e\u0442\u043e\u043a\u0430, \u0432 \u043a\u043e\u0442\u043e\u0440\u043e\u043c \u043e\u0446\u0435\u043d\u0438\u0432\u0430\u044e\u0442\u0441\u044f \u0444\u0430\u043a\u0442\u0438\u0447\u0435\u0441\u043a\u0438\u0435 \u0437\u0430\u0442\u0440\u0430\u0442\u044b \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u043e\u0432, \u043c\u0430\u0442\u0435\u0440\u0438\u0430\u043b\u043e\u0432, \u0440\u0435\u0441\u0443\u0440\u0441\u043e\u0432 \u0438 \u043f\u0440\u043e\u0433\u0440\u0435\u0441\u0441 \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u044f. \n\n"
		   });
		addAnnotation
		  (getMilestone_Size(),
		   source,
		   new String[] {
			   "label_ru", "\u0421\u0443\u043c\u043c\u0430\u0440\u043d\u044b\u0439 \u0440\u0430\u0437\u043c\u0435\u0440",
			   "documentation_ru", "\u0421\u0443\u043c\u043c\u0430 \u0440\u0430\u0437\u043c\u0435\u0440\u043e\u0432 \u0432\u0441\u0435\u0445 \u0434\u043e\u0447\u0435\u0440\u043d\u0438\u0445 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u0432 \u0440\u0430\u0431\u043e\u0442\u044b"
		   });
		addAnnotation
		  (getMilestone_Progress(),
		   source,
		   new String[] {
			   "label_ru", "\u0421\u0443\u043c\u043c\u0430\u0440\u043d\u044b\u0439 \u043f\u0440\u043e\u0433\u0440\u0435\u0441\u0441",
			   "documentation_ru", "\u0421\u0443\u043c\u043c\u0430\u0440\u043d\u0430\u044f \u043e\u0446\u0435\u043d\u043a\u0430 \u043f\u0440\u043e\u0433\u0440\u0435\u0441\u0441\u0430 \u0432\u0441\u0435\u0439 \u0440\u0430\u0431\u043e\u0442\u044b (\u0441 \u0443\u0447\u0435\u0442\u043e\u043c \u0440\u0430\u0437\u043c\u0435\u0440\u0430 \u0438 \u0432\u0430\u0436\u043d\u043e\u0441\u0442\u0438 \u0434\u043e\u0447\u0435\u0440\u043d\u0438\u0445 \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u0439)"
		   });
		addAnnotation
		  (getMilestone_Missed(),
		   source,
		   new String[] {
			   "label_ru", "\u0421\u0443\u043c\u043c\u0430\u0440\u043d\u044b\u0439 \u0440\u0430\u0437\u043c\u0435\u0440",
			   "documentation_ru", "\u0421\u0443\u043c\u043c\u0430 \u0440\u0430\u0437\u043c\u0435\u0440\u043e\u0432 \u0432\u0441\u0435\u0445 \u0434\u043e\u0447\u0435\u0440\u043d\u0438\u0445 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u0432 \u0440\u0430\u0431\u043e\u0442\u044b"
		   });
		addAnnotation
		  (activityReferenceEClass,
		   source,
		   new String[] {
			   "label_ru", "\u0421\u0441\u044b\u043b\u043a\u0430 \u043d\u0430 \u0440\u0430\u0431\u043e\u0442\u0443",
			   "documentation_ru", "???\u0421\u0441\u044b\u043b\u043a\u0430 \u043d\u0430 \u043e\u0431\u0449\u0438\u0439 \u0432\u0438\u0434 \u0434\u0435\u044f\u0442\u0435\u043b\u044c\u043d\u043e\u0441\u0442\u0438, \u043e\u043f\u0440\u0435\u0434\u0435\u043b\u0435\u043d\u043d\u044b\u0439 \u0432 \u0434\u0440\u0443\u0433\u043e\u043c \u043c\u0435\u0441\u0442\u0435."
		   });
		addAnnotation
		  (getActivityReference_Activity(),
		   source,
		   new String[] {
			   "label_ru", "\u0420\u0430\u0431\u043e\u0442\u0430",
			   "documentation_ru", "\u0412\u044b\u043f\u043e\u043b\u043d\u044f\u044f \u0440\u0430\u0431\u043e\u0442\u0443 \u0438\u0441\u043f\u043e\u043b\u043d\u0438\u0442\u0435\u043b\u0438 \u043f\u0440\u043e\u0438\u0437\u0432\u043e\u0434\u044f\u0442 \u0438\u0441\u0445\u043e\u0434\u044f\u0449\u0438\u0435 (\u0446\u0435\u043b\u0435\u0432\u044b\u0435) \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b, \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u044f \u0432\u0445\u043e\u0434\u044f\u0449\u0438\u0435 \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b \u0438 \u0440\u0435\u0441\u0443\u0440\u0441\u044b."
		   });
		addAnnotation
		  (artifactEClass,
		   source,
		   new String[] {
			   "label_ru", "\u0410\u0440\u0442\u0435\u0444\u0430\u043a\u0442",
			   "documentation_ru", "\u0410\u0440\u0442\u0435\u0444\u0430\u043a\u0442 - \u044d\u0442\u043e \u0447\u0442\u043e-\u0442\u043e, \u0447\u0442\u043e \u0432\u044b\u0432\u043e\u0434\u0438\u0442\u0441\u044f / \u0433\u0435\u043d\u0435\u0440\u0438\u0440\u0443\u0435\u0442\u0441\u044f \u0438\u0441\u0442\u043e\u0447\u043d\u0438\u043a\u043e\u043c \u0438 \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0435\u0442\u0441\u044f \u0446\u0435\u043b\u044c\u044e \u0432 \u043a\u0430\u0447\u0435\u0441\u0442\u0432\u0435 \u0432\u0445\u043e\u0434\u043d\u044b\u0445 \u0434\u0430\u043d\u043d\u044b\u0445. \n\n\u041d\u0430\u043f\u0440\u0438\u043c\u0435\u0440 - \u0438\u0441\u0445\u043e\u0434\u043d\u044b\u0439 \u043a\u043e\u0434, \u0440\u0443\u043a\u043e\u0432\u043e\u0434\u0441\u0442\u0432\u043e \u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u0435\u043b\u044f, \u0434\u0432\u043e\u0438\u0447\u043d\u044b\u0439 \u043a\u043e\u0434. \n\n*\u0410\u0440\u0442\u0435\u0444\u0430\u043a\u0442 - \u044d\u0442\u043e \u043f\u0440\u043e\u043c\u0435\u0436\u0443\u0442\u043e\u0447\u043d\u044b\u0439 \u0440\u0435\u0437\u0443\u043b\u044c\u0442\u0430\u0442, \u043a\u043e\u0442\u043e\u0440\u044b\u0439 \u043f\u0435\u0440\u0435\u0434\u0430\u0435\u0442\u0441\u044f \u043e\u0442 \u043f\u0440\u0435\u0434\u0448\u0435\u0441\u0442\u0432\u0443\u044e\u0449\u0435\u0439 \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u0438 (\u0438\u0441\u0442\u043e\u0447\u043d\u0438\u043a\u0430) \u043a \u043f\u043e\u0441\u043b\u0435\u0434\u0443\u044e\u0449\u0435\u0439 \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u0438 (\u0446\u0435\u043b\u0438).*\n\n\u0410\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b \u043f\u0435\u0440\u0435\u0434\u0430\u044e\u0442\u0441\u044f \u043c\u0435\u0436\u0434\u0443 \u0434\u0435\u0439\u0441\u0442\u0432\u0438\u044f\u043c\u0438 \u0447\u0435\u0440\u0435\u0437 \u043f\u0435\u0440\u0435\u0445\u043e\u0434\u044b."
		   });
		addAnnotation
		  (getArtifact_Consumers(),
		   source,
		   new String[] {
			   "label_ru", "\u041f\u043e\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043b\u0438",
			   "documentation_ru", "\u0420\u0430\u0431\u043e\u0442\u044b \u0438\u043b\u0438 \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u0438 \u0434\u043b\u044f \u043a\u043e\u0442\u043e\u0440\u044b\u0445 \u0434\u0430\u043d\u043d\u044b\u0439 \u0430\u0440\u0438\u0442\u0435\u0444\u0430\u043a\u0442 \u044f\u0432\u043b\u044f\u0435\u0442\u0441\u044f \u0432\u0445\u043e\u0434\u044f\u0449\u0438\u043c."
		   });
		addAnnotation
		  (getArtifact_Producers(),
		   source,
		   new String[] {
			   "label_ru", "\u041f\u0440\u043e\u0438\u0437\u0432\u043e\u0434\u0438\u0442\u0435\u043b\u0438",
			   "documentation_ru", "\u0420\u0430\u0431\u043e\u0442\u0430 \u0438\u043b\u0438 \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u044f \u0440\u0435\u0437\u0443\u043b\u044c\u0442\u0430\u0442\u043e\u043c \u043a\u043e\u0442\u043e\u0440\u043e\u0439 \u044f\u0432\u043b\u044f\u0435\u0442\u0441\u044f \u0434\u0430\u043d\u043d\u044b\u0439 \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442"
		   });
		addAnnotation
		  (getArtifact_Children(),
		   source,
		   new String[] {
			   "label_ru", "\u0412\u043b\u043e\u0436\u0435\u043d\u043d\u044b\u0435 \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b",
			   "documentation_ru", "\u0410\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b \u043c\u043e\u0433\u0443\u0442 \u0431\u044b\u0442\u044c \u0432\u043b\u043e\u0436\u0435\u043d\u043d\u044b\u043c\u0438 \u0434\u0440\u0443\u0433 \u0432 \u0434\u0440\u0443\u0433\u0430. \u041d\u0430\u043f\u0440\u0438\u043c\u0435\u0440: zip, \u0441\u043e\u0434\u0435\u0440\u0436\u0430\u0449\u0438\u0439 \u043f\u0430\u043f\u043a\u0438, \u0441\u043e\u0434\u0435\u0440\u0436\u0430\u0449\u0438\u0435 \u0444\u0430\u0439\u043b\u044b."
		   });
		addAnnotation
		  (resourceEClass,
		   source,
		   new String[] {
			   "label_ru", "\u0420\u0435\u0441\u0443\u0440\u0441",
			   "documentation_ru", "\u0420\u0435\u0441\u0443\u0440\u0441 - \u043e\u0431\u044a\u0435\u043a\u0442, \u043f\u043e\u0437\u0432\u043e\u043b\u044f\u044e\u0449\u0438\u0439 \u0438\u0441\u043f\u043e\u043b\u043d\u0438\u0442\u0435\u043b\u044e \u0432\u044b\u043f\u043e\u043b\u043d\u044f\u0442\u044c \u0440\u0430\u0431\u043e\u0442\u0443. \u041d\u0430\u043f\u0440\u0438\u043c\u0435\u0440, \u043a\u0430\u043a\u043e\u0439 \u043b\u0438\u0431\u043e \u0438\u043d\u0441\u0442\u0440\u0443\u043c\u0435\u043d\u0442"
		   });
		addAnnotation
		  (getResource_Children(),
		   source,
		   new String[] {
			   "label_ru", "\u0412\u043b\u043e\u0436\u0435\u043d\u043d\u044b\u0435 \u0440\u0435\u0441\u0443\u0440\u0441\u044b",
			   "documentation_ru", "\u0420\u0435\u0441\u0443\u0440\u0441\u044b \u043c\u043e\u0433\u0443\u0442 \u0431\u044b\u0442\u044c \u0432\u043b\u043e\u0436\u0435\u043d\u043d\u044b\u043c\u0438, \u043c\u043d\u043e\u0433\u043e\u043a\u043e\u043c\u043f\u043e\u043d\u0435\u043d\u0442\u043d\u044b\u043c\u0438. \u041d\u0430\u043f\u0440\u0438\u043c\u0435\u0440, \u0432\u0438\u0440\u0442\u0443\u0430\u043b\u044c\u043d\u0430\u044f \u043c\u0430\u0448\u0438\u043d\u0430 \u043c\u043e\u0436\u0435\u0442 \u0432\u043a\u043b\u044e\u0447\u0430\u0442\u044c \u0432 \u0441\u0435\u0431\u044f \u0432\u0435\u0431-\u0441\u0435\u0440\u0432\u0435\u0440."
		   });
		addAnnotation
		  (getResource_Artifacts(),
		   source,
		   new String[] {
			   "label_ru", "\u0410\u0440\u0442\u0435\u0444\u0430\u043a\u0442",
			   "documentation_ru", "\u0420\u0435\u0441\u0443\u0440\u0441\u044b \u043c\u043e\u0433\u0443\u0442 \u0432\u043a\u043b\u044e\u0447\u0430\u0442\u044c \u0432 \u0441\u0435\u0431\u044f \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b"
		   });
		addAnnotation
		  (transitionEClass,
		   source,
		   new String[] {
			   "label_ru", "\u041f\u0435\u0440\u0435\u0445\u043e\u0434 (\u043f\u0435\u0440\u0435\u0434\u0430\u0447\u0430)",
			   "documentation_ru", "\u041f\u0435\u0440\u0435\u0445\u043e\u0434 (\u043f\u0435\u0440\u0435\u0434\u0430\u0447\u0430) \u043f\u0440\u043e\u043c\u0435\u0436\u0443\u0442\u043e\u0447\u043d\u044b\u0445 \u0440\u0435\u0437\u0443\u043b\u044c\u0442\u0430\u0442\u043e\u0432 \u0440\u0430\u0431\u043e\u0442/\u043e\u043f\u0435\u0440\u0430\u0446\u0438\u0439 \u043e\u0442 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430-\u0438\u0441\u0442\u043e\u0447\u043d\u0438\u043a\u0430 \u043a \u0446\u0435\u043b\u0435\u0432\u043e\u043c\u0443 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0443. \u0410\u0440\u0442\u0438\u0444\u0430\u043a\u0442\u044b \u043c\u043e\u0433\u0443\u0442 \u043f\u0435\u0440\u0435\u0434\u0430\u0432\u0430\u0442\u044c\u0441\u044f \u0432 \u043e\u0431\u043e\u0438\u0445 \u043d\u0430\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u044f\u0445. \u041d\u0430\u043f\u0440\u0438\u043c\u0435\u0440, \u043f\u0435\u0440\u0435\u0445\u043e\u0434 \u043a \u00ab\u0424\u0438\u043a\u0441\u0438\u0440\u0443\u044e\u0449\u0435\u043c\u0443 \u043a\u043e\u0434\u0443\u00bb \u043c\u043e\u0436\u0435\u0442 \u043f\u0440\u0438\u043d\u0438\u043c\u0430\u0442\u044c \u00ab\u0418\u0441\u0445\u043e\u0434\u043d\u044b\u0439 \u043a\u043e\u0434\u00bb \u0432 \u043a\u0430\u0447\u0435\u0441\u0442\u0432\u0435 \u0432\u0432\u043e\u0434\u0430 \u0438 \u0432\u043e\u0437\u0432\u0440\u0430\u0449\u0430\u0442\u044c \u00ab\u0424\u0438\u043a\u0441\u0438\u0440\u043e\u0432\u0430\u043d\u043d\u044b\u0439 \u043a\u043e\u0434\u00bb \u0432 \u0440\u0435\u0437\u0443\u043b\u044c\u0442\u0430\u0442\u0435.\n "
		   });
		addAnnotation
		  (getTransition_Target(),
		   source,
		   new String[] {
			   "label_ru", "\u0426\u0435\u043b\u0435\u0432\u043e\u0439 \u044d\u043b\u0435\u043c\u0435\u043d\u0442",
			   "documentation_ru", "\u0420\u0430\u0431\u043e\u0442\u0430/\u043e\u043f\u0435\u0440\u0430\u0446\u0438\u044f, \u044f\u0432\u043b\u044f\u044e\u0449\u0430\u044f\u0441\u044f \u0446\u0435\u043b\u044c\u044e \u043f\u0435\u0440\u0435\u0434\u0430\u0447\u0438 \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u0430, \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u0435 \u043a\u043e\u0442\u043e\u0440\u043e\u0439 \u043d\u0435\u0432\u043e\u0437\u043c\u043e\u0436\u043d\u043e \u0434\u043e \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u044f \u0440\u0430\u0431\u043e\u0442\u044b-\u0438\u0441\u0442\u043e\u0447\u043d\u0438\u043a\u0430 \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u0430."
		   });
		addAnnotation
		  (getTransition_Inputs(),
		   source,
		   new String[] {
			   "label_ru", "\u0412\u0445\u043e\u0434\u044f\u0449\u0438\u0435 \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b",
			   "documentation_ru", "\u0410\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b \u043f\u0440\u0435\u0434\u0430\u0432\u0430\u0435\u043c\u044b\u0435 \u043e\u0442 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430-\u0438\u0441\u0442\u043e\u0447\u043d\u0438\u043a\u0430 \u043a \u0446\u0435\u043b\u0435\u0432\u043e\u043c\u0443 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0443 \u0447\u0435\u0440\u0435\u0437 \u043f\u0435\u0440\u0435\u0445\u043e\u0434."
		   });
		addAnnotation
		  (getTransition_Results(),
		   source,
		   new String[] {
			   "label_ru", "\u0420\u0435\u0437\u0443\u043b\u044c\u0442\u0430\u0442 \u043f\u0435\u0440\u0435\u0434\u0430\u0447\u0438",
			   "documentation_ru", "\u0410\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b \u0432\u043e\u0437\u0432\u0440\u0430\u0449\u0430\u0435\u043c\u044b\u0435 \u0446\u0435\u043b\u0435\u0432\u044b\u043c \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u043c \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0443 \u0438\u0441\u0442\u043e\u0447\u043d\u0438\u043a\u0443."
		   });
		addAnnotation
		  (associationEClass,
		   source,
		   new String[] {
			   "label_ru", "\u0410\u0441\u0441\u043e\u0446\u0438\u0430\u0446\u0438\u044f",
			   "documentation_ru", "\u041e\u0442\u043d\u043e\u0448\u0435\u043d\u0438\u044f \u043d\u0430\u0441\u043b\u0435\u0434\u043e\u0432\u0430\u043d\u0438\u044f \u043c\u0435\u0436\u0434\u0443 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430\u043c\u0438 \u043c\u043e\u0434\u0435\u043b\u0438"
		   });
		addAnnotation
		  (getAssociation_Target(),
		   source,
		   new String[] {
			   "label_ru", "\u0426\u0435\u043b\u044c"
		   });
		addAnnotation
		  (engineerEClass,
		   source,
		   new String[] {
			   "label_ru", "\u0418\u043d\u0436\u0435\u043d\u0435\u0440",
			   "documentation_ru", "\u0421\u043f\u0435\u0446\u0438\u0430\u043b\u0438\u0441\u0442, \u0432\u043b\u0430\u0434\u0435\u044e\u0449\u0438\u0439 \u043a\u0430\u043a\u0438\u043c-\u043b\u0438\u0431\u043e \u0440\u0435\u0441\u0443\u0440\u0441\u043e\u043c, \u043f\u0440\u043e\u0446\u0435\u0441\u0441\u043e\u043c \u0438\u043b\u0438 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u043c \u043f\u0440\u043e\u0446\u0435\u0441\u0441\u0430 \u0438 \u043f\u043e\u043b\u0443\u0447\u0430\u044e\u0449\u0438\u0439 \u0437\u0430\u0434\u0430\u0447\u0438, \u0441\u0432\u044f\u0437\u0430\u043d\u043d\u044b\u0435 \u0441 \u044d\u0442\u0438\u043c \u043f\u0440\u043e\u0446\u0435\u0441\u0441\u043e\u043c \u0438\u043b\u0438 \u0440\u0435\u0441\u0443\u0440\u043e\u043c."
		   });
		addAnnotation
		  (getEngineer_Owns(),
		   source,
		   new String[] {
			   "label_ru", "\u0425\u043e\u0437\u044f\u0439\u0441\u0442\u0432\u043e",
			   "documentation_ru", "\u0421\u043e\u0432\u043e\u043a\u0443\u043f\u043d\u043e\u0441\u0442\u044c \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u0432, \u043a\u043e\u0442\u043e\u0440\u044b\u043c\u0438 \u0432\u043b\u0430\u0434\u0435\u0435\u0442 \u0438\u043d\u0436\u0435\u043d\u0435\u0440. \n\u0412\u043b\u0430\u0434\u0435\u043d\u0438\u0435 \u0440\u0430\u0441\u043f\u0440\u043e\u0441\u0442\u0440\u0430\u043d\u044f\u0435\u0442\u0441\u044f \u0434\u043e \u0434\u043e\u0447\u0435\u0440\u043d\u0438\u0445 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u0432 \u0440\u0435\u043a\u0443\u0440\u0441\u0438\u0432\u043d\u043e, \u0435\u0441\u043b\u0438 \u0442\u043e\u043b\u044c\u043a\u043e \u044d\u0442\u0438 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u044b \u043d\u0435 \u0438\u043c\u0435\u044e\u0442 \u044f\u0432\u043d\u043e \u043d\u0430\u0437\u043d\u0430\u0447\u0435\u043d\u043d\u043e\u0433\u043e \u0432\u043b\u0430\u0434\u0435\u043b\u044c\u0446\u0430. \u0422\u0430\u043a\u0436\u0435 \u043f\u0440\u043e\u0431\u043b\u0435\u043c\u044b, \u0441\u0432\u044f\u0437\u0430\u043d\u043d\u044b\u0435 \u0441 \u0441\u043e\u0431\u0441\u0442\u0432\u0435\u043d\u043d\u044b\u043c\u0438 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430\u043c\u0438 \u0438 \u043e\u0442\u0441\u0443\u0442\u0441\u0442\u0432\u0438\u0435\u043c \u044f\u0432\u043d\u043e \u043d\u0430\u0437\u043d\u0430\u0447\u0435\u043d\u043d\u043e\u0433\u043e \u0438\u043c \u0438\u043d\u0436\u0435\u043d\u0435\u0440\u0430, \u043d\u0435\u044f\u0432\u043d\u043e \u043d\u0430\u0437\u043d\u0430\u0447\u0430\u044e\u0442\u0441\u044f \u0432\u043b\u0430\u0434\u0435\u043b\u044c\u0446\u0443 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430."
		   });
		addAnnotation
		  (getEngineer_Assignments(),
		   source,
		   new String[] {
			   "label_ru", "\u0417\u0430\u0434\u0430\u0447\u0430",
			   "documentation_ru", "\u0417\u0430\u0434\u0430\u0447\u0438, \u043f\u0435\u0440\u0435\u0434\u0430\u043d\u043d\u044b\u0435 \u0438\u043d\u0436\u0435\u043d\u0435\u0440\u0443 \u043d\u0430 \u0438\u0441\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u0435"
		   });
		addAnnotation
		  (issueEClass,
		   source,
		   new String[] {
			   "label_ru", "\u0417\u0430\u0434\u0430\u0447\u0430",
			   "documentation_ru", "\u0427\u0442\u043e-\u0442\u043e, \u0442\u0440\u0435\u0431\u0443\u044e\u0449\u0435\u0435 \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u044f \u043a\u0430\u043a\u043e\u0439-\u043b\u0438\u0431\u043e \u0440\u0430\u0431\u043e\u0442\u044b \u0432 \u043e\u0442\u043d\u043e\u0448\u0435\u043d\u0438\u0438 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430-\u0432\u043b\u0430\u0434\u0435\u043b\u044c\u0446\u0430 - \u043e\u0448\u0438\u0431\u043a\u0430, \u043f\u0440\u043e\u0431\u043b\u0435\u043c\u0430, \u0438\u043d\u0446\u0435\u0434\u0435\u043d\u0442, \u0432\u043e\u0437\u043c\u043e\u0436\u043d\u043e\u0441\u0442\u044c \u0443\u0441\u043e\u0432\u0435\u0440\u0448\u0435\u043d\u0441\u0442\u0432\u043e\u0432\u0430\u043d\u0438\u044f."
		   });
	}

} //RigelPackageImpl
