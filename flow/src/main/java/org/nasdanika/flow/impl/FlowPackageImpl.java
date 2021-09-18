/**
 */
package org.nasdanika.flow.impl;

import java.util.Map;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.nasdanika.flow.Activity;
import org.nasdanika.flow.Artifact;
import org.nasdanika.flow.Call;
import org.nasdanika.flow.Choice;
import org.nasdanika.flow.End;
import org.nasdanika.flow.EntryPoint;
import org.nasdanika.flow.ExitPoint;
import org.nasdanika.flow.ExpansionInput;
import org.nasdanika.flow.ExpansionOutput;
import org.nasdanika.flow.Flow;
import org.nasdanika.flow.FlowElement;
import org.nasdanika.flow.FlowFactory;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Fork;
import org.nasdanika.flow.InputPin;
import org.nasdanika.flow.Join;
import org.nasdanika.flow.OutputPin;
import org.nasdanika.flow.Participant;
import org.nasdanika.flow.PseudoState;
import org.nasdanika.flow.Resource;
import org.nasdanika.flow.Service;
import org.nasdanika.flow.Start;
import org.nasdanika.flow.Transition;

import org.nasdanika.flow.util.FlowValidator;

import org.nasdanika.ncore.NcorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FlowPackageImpl extends EPackageImpl implements FlowPackage {
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
	private EClass packageEntryEClass = null;

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
	private EClass resourceEClass = null;

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
	private EClass flowElementEClass = null;

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
	private EClass serviceEClass = null;

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
	private EClass callEClass = null;

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
	private EClass pseudoStateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass choiceEClass = null;

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
	private EClass entryPointEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exitPointEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass expansionInputEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass expansionOutputEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass forkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inputPinEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass joinEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outputPinEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass startEClass = null;

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
	 * @see org.nasdanika.flow.FlowPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private FlowPackageImpl() {
		super(eNS_URI, FlowFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link FlowPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static FlowPackage init() {
		if (isInited) return (FlowPackage)EPackage.Registry.INSTANCE.getEPackage(FlowPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredFlowPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		FlowPackageImpl theFlowPackage = registeredFlowPackage instanceof FlowPackageImpl ? (FlowPackageImpl)registeredFlowPackage : new FlowPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		NcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theFlowPackage.createPackageContents();

		// Initialize created meta-data
		theFlowPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theFlowPackage,
			 new EValidator.Descriptor() {
				 @Override
				 public EValidator getEValidator() {
					 return FlowValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theFlowPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(FlowPackage.eNS_URI, theFlowPackage);
		return theFlowPackage;
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
	public EReference getPackage_Extends() {
		return (EReference)packageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackage_Extensions() {
		return (EReference)packageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackage_SubPackages() {
		return (EReference)packageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPackageEntry() {
		return packageEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPackageEntry_Key() {
		return (EAttribute)packageEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackageEntry_Value() {
		return (EReference)packageEntryEClass.getEStructuralFeatures().get(1);
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
	public EReference getParticipant_Extends() {
		return (EReference)participantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getParticipant_Extensions() {
		return (EReference)participantEClass.getEStructuralFeatures().get(1);
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
	public EClass getArtifact() {
		return artifactEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getArtifact_Repositories() {
		return (EReference)artifactEClass.getEStructuralFeatures().get(0);
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
	public EReference getFlowElement_Outputs() {
		return (EReference)flowElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFlowElement_Calls() {
		return (EReference)flowElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFlowElement_InputArtifacts() {
		return (EReference)flowElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFlowElement_Deliverables() {
		return (EReference)flowElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFlowElement_Participants() {
		return (EReference)flowElementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFlowElement_Overrides() {
		return (EReference)flowElementEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFlowElement_OverridenBy() {
		return (EReference)flowElementEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFlowElement_Modifiers() {
		return (EAttribute)flowElementEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFlowElement_Resources() {
		return (EReference)flowElementEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getFlowElement__GetInputs__EList() {
		return flowElementEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getFlowElement__GetInvocations__EList() {
		return flowElementEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getFlowElement__GetAllInputs__EList() {
		return flowElementEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getFlowElement__GetAllInvocations__EList() {
		return flowElementEClass.getEOperations().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getFlowElement__GetAllOutputs__EList() {
		return flowElementEClass.getEOperations().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getFlowElement__GetAllCalls__EList() {
		return flowElementEClass.getEOperations().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getFlowElement__Overrides__FlowElement() {
		return flowElementEClass.getEOperations().get(6);
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
	public EReference getActivity_Services() {
		return (EReference)activityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getService() {
		return serviceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getService_Target() {
		return (EReference)serviceEClass.getEStructuralFeatures().get(0);
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
	public EReference getTransition_Payload() {
		return (EReference)transitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTransition_Suppress() {
		return (EAttribute)transitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTransition_Target() {
		return (EAttribute)transitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getTransition__GetTarget__EList() {
		return transitionEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCall() {
		return callEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCall_Response() {
		return (EReference)callEClass.getEStructuralFeatures().get(0);
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
	public EReference getFlow_Extends() {
		return (EReference)flowEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFlow_Extensions() {
		return (EReference)flowEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFlow_Root() {
		return (EReference)flowEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFlow_AllElements() {
		return (EReference)flowEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPseudoState() {
		return pseudoStateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPseudoState_Type() {
		return (EAttribute)pseudoStateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getChoice() {
		return choiceEClass;
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
	public EClass getEntryPoint() {
		return entryPointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getExitPoint() {
		return exitPointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getExpansionInput() {
		return expansionInputEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getExpansionOutput() {
		return expansionOutputEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFork() {
		return forkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getInputPin() {
		return inputPinEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getJoin() {
		return joinEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOutputPin() {
		return outputPinEClass;
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
	public FlowFactory getFlowFactory() {
		return (FlowFactory)getEFactoryInstance();
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
		packageEClass = createEClass(PACKAGE);
		createEReference(packageEClass, PACKAGE__EXTENDS);
		createEReference(packageEClass, PACKAGE__EXTENSIONS);
		createEReference(packageEClass, PACKAGE__SUB_PACKAGES);

		packageEntryEClass = createEClass(PACKAGE_ENTRY);
		createEAttribute(packageEntryEClass, PACKAGE_ENTRY__KEY);
		createEReference(packageEntryEClass, PACKAGE_ENTRY__VALUE);

		participantEClass = createEClass(PARTICIPANT);
		createEReference(participantEClass, PARTICIPANT__EXTENDS);
		createEReference(participantEClass, PARTICIPANT__EXTENSIONS);

		resourceEClass = createEClass(RESOURCE);

		artifactEClass = createEClass(ARTIFACT);
		createEReference(artifactEClass, ARTIFACT__REPOSITORIES);

		flowElementEClass = createEClass(FLOW_ELEMENT);
		createEReference(flowElementEClass, FLOW_ELEMENT__OUTPUTS);
		createEReference(flowElementEClass, FLOW_ELEMENT__CALLS);
		createEReference(flowElementEClass, FLOW_ELEMENT__INPUT_ARTIFACTS);
		createEReference(flowElementEClass, FLOW_ELEMENT__DELIVERABLES);
		createEReference(flowElementEClass, FLOW_ELEMENT__PARTICIPANTS);
		createEReference(flowElementEClass, FLOW_ELEMENT__OVERRIDES);
		createEReference(flowElementEClass, FLOW_ELEMENT__OVERRIDEN_BY);
		createEAttribute(flowElementEClass, FLOW_ELEMENT__MODIFIERS);
		createEReference(flowElementEClass, FLOW_ELEMENT__RESOURCES);
		createEOperation(flowElementEClass, FLOW_ELEMENT___GET_INPUTS__ELIST);
		createEOperation(flowElementEClass, FLOW_ELEMENT___GET_INVOCATIONS__ELIST);
		createEOperation(flowElementEClass, FLOW_ELEMENT___GET_ALL_INPUTS__ELIST);
		createEOperation(flowElementEClass, FLOW_ELEMENT___GET_ALL_INVOCATIONS__ELIST);
		createEOperation(flowElementEClass, FLOW_ELEMENT___GET_ALL_OUTPUTS__ELIST);
		createEOperation(flowElementEClass, FLOW_ELEMENT___GET_ALL_CALLS__ELIST);
		createEOperation(flowElementEClass, FLOW_ELEMENT___OVERRIDES__FLOWELEMENT);

		activityEClass = createEClass(ACTIVITY);
		createEReference(activityEClass, ACTIVITY__SERVICES);

		serviceEClass = createEClass(SERVICE);
		createEReference(serviceEClass, SERVICE__TARGET);

		transitionEClass = createEClass(TRANSITION);
		createEReference(transitionEClass, TRANSITION__PAYLOAD);
		createEAttribute(transitionEClass, TRANSITION__SUPPRESS);
		createEAttribute(transitionEClass, TRANSITION__TARGET);
		createEOperation(transitionEClass, TRANSITION___GET_TARGET__ELIST);

		callEClass = createEClass(CALL);
		createEReference(callEClass, CALL__RESPONSE);

		flowEClass = createEClass(FLOW);
		createEReference(flowEClass, FLOW__ELEMENTS);
		createEReference(flowEClass, FLOW__EXTENDS);
		createEReference(flowEClass, FLOW__EXTENSIONS);
		createEReference(flowEClass, FLOW__ROOT);
		createEReference(flowEClass, FLOW__ALL_ELEMENTS);

		pseudoStateEClass = createEClass(PSEUDO_STATE);
		createEAttribute(pseudoStateEClass, PSEUDO_STATE__TYPE);

		choiceEClass = createEClass(CHOICE);

		endEClass = createEClass(END);

		entryPointEClass = createEClass(ENTRY_POINT);

		exitPointEClass = createEClass(EXIT_POINT);

		expansionInputEClass = createEClass(EXPANSION_INPUT);

		expansionOutputEClass = createEClass(EXPANSION_OUTPUT);

		forkEClass = createEClass(FORK);

		inputPinEClass = createEClass(INPUT_PIN);

		joinEClass = createEClass(JOIN);

		outputPinEClass = createEClass(OUTPUT_PIN);

		startEClass = createEClass(START);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		packageEClass.getESuperTypes().add(theNcorePackage.getNamedElement());
		participantEClass.getESuperTypes().add(theNcorePackage.getNamedElement());
		resourceEClass.getESuperTypes().add(theNcorePackage.getNamedElement());
		artifactEClass.getESuperTypes().add(theNcorePackage.getNamedElement());
		activityEClass.getESuperTypes().add(this.getFlowElement());
		serviceEClass.getESuperTypes().add(this.getFlowElement());
		callEClass.getESuperTypes().add(this.getTransition());
		flowEClass.getESuperTypes().add(this.getActivity());
		pseudoStateEClass.getESuperTypes().add(this.getFlowElement());
		choiceEClass.getESuperTypes().add(this.getPseudoState());
		endEClass.getESuperTypes().add(this.getPseudoState());
		entryPointEClass.getESuperTypes().add(this.getPseudoState());
		exitPointEClass.getESuperTypes().add(this.getPseudoState());
		expansionInputEClass.getESuperTypes().add(this.getPseudoState());
		expansionOutputEClass.getESuperTypes().add(this.getPseudoState());
		forkEClass.getESuperTypes().add(this.getPseudoState());
		inputPinEClass.getESuperTypes().add(this.getPseudoState());
		joinEClass.getESuperTypes().add(this.getPseudoState());
		outputPinEClass.getESuperTypes().add(this.getPseudoState());
		startEClass.getESuperTypes().add(this.getPseudoState());

		// Initialize classes, features, and operations; add parameters
		initEClass(packageEClass, org.nasdanika.flow.Package.class, "Package", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPackage_Extends(), this.getPackage(), this.getPackage_Extensions(), "extends", null, 0, -1, org.nasdanika.flow.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackage_Extensions(), this.getPackage(), this.getPackage_Extends(), "extensions", null, 0, -1, org.nasdanika.flow.Package.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getPackage_SubPackages(), this.getPackageEntry(), null, "subPackages", null, 0, -1, org.nasdanika.flow.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(packageEntryEClass, Map.Entry.class, "PackageEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPackageEntry_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackageEntry_Value(), this.getPackage(), null, "value", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(participantEClass, Participant.class, "Participant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParticipant_Extends(), this.getParticipant(), this.getParticipant_Extensions(), "extends", null, 0, -1, Participant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getParticipant_Extensions(), this.getParticipant(), this.getParticipant_Extends(), "extensions", null, 0, -1, Participant.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(resourceEClass, Resource.class, "Resource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(artifactEClass, Artifact.class, "Artifact", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getArtifact_Repositories(), this.getResource(), null, "repositories", null, 0, -1, Artifact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(flowElementEClass, FlowElement.class, "FlowElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFlowElement_Outputs(), this.getTransition(), null, "outputs", null, 0, -1, FlowElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFlowElement_Calls(), this.getCall(), null, "calls", null, 0, -1, FlowElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFlowElement_InputArtifacts(), this.getArtifact(), null, "inputArtifacts", null, 0, -1, FlowElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFlowElement_Deliverables(), this.getArtifact(), null, "deliverables", null, 0, -1, FlowElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFlowElement_Participants(), this.getParticipant(), null, "participants", null, 0, -1, FlowElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFlowElement_Overrides(), this.getFlowElement(), this.getFlowElement_OverridenBy(), "overrides", null, 0, 1, FlowElement.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getFlowElement_OverridenBy(), this.getFlowElement(), this.getFlowElement_Overrides(), "overridenBy", null, 0, -1, FlowElement.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFlowElement_Modifiers(), ecorePackage.getEString(), "modifiers", null, 0, -1, FlowElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFlowElement_Resources(), this.getResource(), null, "resources", null, 0, -1, FlowElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = initEOperation(getFlowElement__GetInputs__EList(), this.getTransition(), "getInputs", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getFlow(), "journeyPath", 1, -1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getFlowElement__GetInvocations__EList(), this.getCall(), "getInvocations", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getFlow(), "journeyPath", 1, -1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getFlowElement__GetAllInputs__EList(), this.getTransition(), "getAllInputs", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getFlow(), "journeyPath", 1, -1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getFlowElement__GetAllInvocations__EList(), this.getCall(), "getAllInvocations", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getFlow(), "journeyPath", 1, -1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getFlowElement__GetAllOutputs__EList(), this.getTransition(), "getAllOutputs", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getFlow(), "journeyPath", 1, -1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getFlowElement__GetAllCalls__EList(), this.getCall(), "getAllCalls", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getFlow(), "journeyPath", 1, -1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getFlowElement__Overrides__FlowElement(), ecorePackage.getEBoolean(), "overrides", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getFlowElement(), "journeyElement", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(activityEClass, Activity.class, "Activity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActivity_Services(), this.getService(), this.getService_Target(), "services", null, 0, -1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(serviceEClass, Service.class, "Service", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getService_Target(), this.getActivity(), this.getActivity_Services(), "target", null, 1, 1, Service.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transitionEClass, Transition.class, "Transition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTransition_Payload(), this.getArtifact(), null, "payload", null, 0, -1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransition_Suppress(), ecorePackage.getEBoolean(), "suppress", null, 0, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransition_Target(), ecorePackage.getEString(), "target", null, 1, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = initEOperation(getTransition__GetTarget__EList(), this.getFlowElement(), "getTarget", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getFlow(), "journeyPath", 1, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(callEClass, Call.class, "Call", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCall_Response(), this.getArtifact(), null, "response", null, 0, -1, Call.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(flowEClass, Flow.class, "Flow", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFlow_Elements(), this.getFlowElement(), null, "elements", null, 0, -1, Flow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFlow_Extends(), this.getFlow(), this.getFlow_Extensions(), "extends", null, 0, -1, Flow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFlow_Extensions(), this.getFlow(), this.getFlow_Extends(), "extensions", null, 0, -1, Flow.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getFlow_Root(), this.getFlow(), null, "root", null, 0, 1, Flow.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getFlow_AllElements(), this.getFlowElement(), null, "allElements", null, 0, -1, Flow.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(pseudoStateEClass, PseudoState.class, "PseudoState", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPseudoState_Type(), ecorePackage.getEString(), "type", null, 0, 1, PseudoState.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(choiceEClass, Choice.class, "Choice", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(endEClass, End.class, "End", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(entryPointEClass, EntryPoint.class, "EntryPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(exitPointEClass, ExitPoint.class, "ExitPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(expansionInputEClass, ExpansionInput.class, "ExpansionInput", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(expansionOutputEClass, ExpansionOutput.class, "ExpansionOutput", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(forkEClass, Fork.class, "Fork", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(inputPinEClass, InputPin.class, "InputPin", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(joinEClass, Join.class, "Join", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(outputPinEClass, OutputPin.class, "OutputPin", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(startEClass, Start.class, "Start", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// urn:org.nasdanika
		createUrnorgAnnotations();
		// http://www.eclipse.org/emf/2002/GenModel
		createGenModelAnnotations();
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
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
			   "documentation-reference", "doc/package-summary.md"
		   });
		addAnnotation
		  (flowElementEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/flow/journey-element.md"
		   });
		addAnnotation
		  (getFlowElement_Outputs(),
		   source,
		   new String[] {
			   "homogenous", "true",
			   "strict-containment", "true"
		   });
		addAnnotation
		  (getFlowElement_Calls(),
		   source,
		   new String[] {
			   "homogenous", "true",
			   "strict-containment", "true"
		   });
		addAnnotation
		  (activityEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/flow/activity.md"
		   });
		addAnnotation
		  (serviceEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/flow/service.md"
		   });
		addAnnotation
		  (transitionEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/flow/transition.md"
		   });
		addAnnotation
		  (getTransition_Target(),
		   source,
		   new String[] {
			   "default-feature", "true"
		   });
		addAnnotation
		  (callEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/flow/call.md"
		   });
		addAnnotation
		  (flowEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/flow/journey.md"
		   });
		addAnnotation
		  (pseudoStateEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/flow/pseudo-state.md"
		   });
		addAnnotation
		  (choiceEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/flow/choice.md"
		   });
		addAnnotation
		  (endEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/flow/end.md"
		   });
		addAnnotation
		  (entryPointEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/flow/entry-point.md"
		   });
		addAnnotation
		  (exitPointEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/flow/exit-point.md"
		   });
		addAnnotation
		  (expansionInputEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/flow/expansion-input.md"
		   });
		addAnnotation
		  (expansionOutputEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/flow/expansion-output.md"
		   });
		addAnnotation
		  (forkEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/flow/fork.md"
		   });
		addAnnotation
		  (inputPinEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/flow/input-pin.md"
		   });
		addAnnotation
		  (joinEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/flow/join.md"
		   });
		addAnnotation
		  (outputPinEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/flow/output-pin.md"
		   });
		addAnnotation
		  (startEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/flow/start.md"
		   });
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
		  (getPackage_Extends(),
		   source,
		   new String[] {
			   "documentation", "Journey can  extend another journey and inherit its elements. Inherited elements can be overriden or suppressed."
		   });
		addAnnotation
		  (getPackage_Extensions(),
		   source,
		   new String[] {
			   "documentation", "Journeys extending this journey."
		   });
		addAnnotation
		  (getParticipant_Extends(),
		   source,
		   new String[] {
			   "documentation", "Journey can  extend another journey and inherit its elements. Inherited elements can be overriden or suppressed."
		   });
		addAnnotation
		  (getParticipant_Extensions(),
		   source,
		   new String[] {
			   "documentation", "Journeys extending this journey."
		   });
		addAnnotation
		  (getFlowElement__GetInputs__EList(),
		   source,
		   new String[] {
			   "documentation", "Transitions which have this journey element as its target in a given journey path. "
		   });
		addAnnotation
		  ((getFlowElement__GetInputs__EList()).getEParameters().get(0),
		   source,
		   new String[] {
			   "documentation", "Journey nesting path to resolve transitions target elements. May be different from the containment path in the case of nested journeys of extended journeys."
		   });
		addAnnotation
		  (getFlowElement__GetInvocations__EList(),
		   source,
		   new String[] {
			   "documentation", "Calls which have this journey element as its target in a given journey path. "
		   });
		addAnnotation
		  ((getFlowElement__GetInvocations__EList()).getEParameters().get(0),
		   source,
		   new String[] {
			   "documentation", "Journey nesting path to resolve calls target elements. May be different from the containment path in the case of nested journeys of extended journeys."
		   });
		addAnnotation
		  (getFlowElement__GetAllInputs__EList(),
		   source,
		   new String[] {
			   "documentation", "Opposite to all outputs, i.e. all inbound transitions, in a given journey path."
		   });
		addAnnotation
		  ((getFlowElement__GetAllInputs__EList()).getEParameters().get(0),
		   source,
		   new String[] {
			   "documentation", "Journey nesting path to resolve transitions target elements. May be different from the containment path in the case of nested journeys of extended journeys."
		   });
		addAnnotation
		  (getFlowElement__GetAllInvocations__EList(),
		   source,
		   new String[] {
			   "documentation", "Opposite to all calls, i.e. all incoming calls, in a given journey path."
		   });
		addAnnotation
		  ((getFlowElement__GetAllInvocations__EList()).getEParameters().get(0),
		   source,
		   new String[] {
			   "documentation", "Journey nesting path to resolve calls target elements. May be different from the containment path in the case of nested journeys of extended journeys."
		   });
		addAnnotation
		  (getFlowElement__GetAllOutputs__EList(),
		   source,
		   new String[] {
			   "documentation", "Includes own outputs and outputs inherited from the base journey of the containing journey. This element outputs override inherited outputs with the same path."
		   });
		addAnnotation
		  ((getFlowElement__GetAllOutputs__EList()).getEParameters().get(0),
		   source,
		   new String[] {
			   "documentation", "Journey nesting path to resolve transitions target elements. May be different from the containment path in the case of nested journeys of extended journeys."
		   });
		addAnnotation
		  (getFlowElement__GetAllCalls__EList(),
		   source,
		   new String[] {
			   "documentation", "Includes own calls and calls inherited from the base journey of the containing journey. This element calls override inherited calls with the same path."
		   });
		addAnnotation
		  ((getFlowElement__GetAllCalls__EList()).getEParameters().get(0),
		   source,
		   new String[] {
			   "documentation", "Journey nesting path to resolve calls target elements. May be different from the containment path in the case of nested journeys of extended journeys."
		   });
		addAnnotation
		  (getFlowElement__Overrides__FlowElement(),
		   source,
		   new String[] {
			   "documentation", "Returns ``true`` if this journey element directly or transitively overrided the argument journey element."
		   });
		addAnnotation
		  (getFlowElement_Outputs(),
		   source,
		   new String[] {
			   "documentation", "Activity outbound transitions to other activities."
		   });
		addAnnotation
		  (getFlowElement_Calls(),
		   source,
		   new String[] {
			   "documentation", "Calls to other activities."
		   });
		addAnnotation
		  (getFlowElement_InputArtifacts(),
		   source,
		   new String[] {
			   "documentation", "Input artifacts required to start working on this activity."
		   });
		addAnnotation
		  (getFlowElement_Deliverables(),
		   source,
		   new String[] {
			   "documentation", "Output artifacts of the activity."
		   });
		addAnnotation
		  (getFlowElement_Participants(),
		   source,
		   new String[] {
			   "documentation", "Personas performing this journey element if it is an activity. Applies to all journey elements in order to group them into persona partitions."
		   });
		addAnnotation
		  (getFlowElement_Overrides(),
		   source,
		   new String[] {
			   "documentation", "A journey element which is overriden by this element. "
		   });
		addAnnotation
		  (getFlowElement_OverridenBy(),
		   source,
		   new String[] {
			   "documentation", "Elements of the extension journeys which override this element."
		   });
		addAnnotation
		  (getFlowElement_Modifiers(),
		   source,
		   new String[] {
			   "documentation", "A collection of boolean flags:\n\n* ``abstract`` - Specifies that this journey element is abstract. For journeys it means that they contain abstract elements and must be extended to become concrete. If a journey contains abstract elements and does not have abstract modifier, it is diagnosed as an error. If concrete journeys extend abstract journeys they must override (implement) all abstract elements.\n* ``explicit-end`` - Applies to [journeys](Journey.html). Specifies that the [end](End.html) [pseudo-state](PseudoState.html) shall not be inferred by finding journey elements with no outputs. End will either be explicitly specified or the diagram will not have an end pseudo-state.\n* ``explicit-start`` - Applies to journeys. Specifies that the [start](Start.html) pseudo-state shall not be inferred by finding journey elements with no inputs. Start will either be explicitly specified or the diagram will not have a start pseudo-state.\n* ``final`` - Specifies that this journey element cannot be overriden in journeys extending this journey. Overriding a final elemen will be diagnosed as an error. For example, in an organization some processes can be defined as journeys at higher levels of the orgnization and extended at lower levels. ``final`` modifier allows to specify what can be extended and what cannot. Specifying a top-level journey as final indicates that it cannot have extensions.\n* ``optional`` - Specifies that this journey element is optional. Optional elements have different apperance on diagrams.\n* ``override`` - Specifies that this journey element overrides an element in one of extended journeys. If this modifier is present and overrides reference is null, then it results in a diagnostic error.\n* ``partition`` - Applies to journeys and specifies that the journey shall be rendered as a partition on a digarm.\n* ``suppress`` - Specifies that this element supprresses its overriden element, i.e. removes it from the list of journey elements.\n* ``vertical`` - Specifies that journey element direction top-down instead of the default left-to-right.\n"
		   });
		addAnnotation
		  (getActivity_Services(),
		   source,
		   new String[] {
			   "documentation", "Derived opposite to Service target."
		   });
		addAnnotation
		  (getService_Target(),
		   source,
		   new String[] {
			   "documentation", "Target activity of the service."
		   });
		addAnnotation
		  (getTransition__GetTarget__EList(),
		   source,
		   new String[] {
			   "documentation", "Resolves transition\'s target journey element for a given journey."
		   });
		addAnnotation
		  ((getTransition__GetTarget__EList()).getEParameters().get(0),
		   source,
		   new String[] {
			   "documentation", "Journey nesting path to resolve target element for. May be different from the containment path in the case of nested journeys of extended journeys."
		   });
		addAnnotation
		  (getTransition_Payload(),
		   source,
		   new String[] {
			   "documentation", "Artifacts passed from source to target activity."
		   });
		addAnnotation
		  (getTransition_Suppress(),
		   source,
		   new String[] {
			   "documentation", "Can be used in journeys extending other journeys to remove the inherited transition from the list of outputs/call. Transitions to supprressed journey elements are automatically supppressed."
		   });
		addAnnotation
		  (getTransition_Target(),
		   source,
		   new String[] {
			   "documentation", "Relative path to the target [journey element](JourneyElement.html). May contain ``..`` to navigate to the parent [journey](Journey.html) - i.e. the journey which contains the journey containing the element which contains this transition. May contain ``/`` to navigate to nested journeys. Treated as URI if contains ``:``."
		   });
		addAnnotation
		  (getCall_Response(),
		   source,
		   new String[] {
			   "documentation", "Response artifacts passed back from the target to the source activity."
		   });
		addAnnotation
		  (getFlow_Elements(),
		   source,
		   new String[] {
			   "documentation", "Elements of this journey."
		   });
		addAnnotation
		  (getFlow_Extends(),
		   source,
		   new String[] {
			   "documentation", "Journey can  extend another journey and inherit its elements. Inherited elements can be overriden or suppressed."
		   });
		addAnnotation
		  (getFlow_Extensions(),
		   source,
		   new String[] {
			   "documentation", "Journeys extending this journey."
		   });
		addAnnotation
		  (getFlow_Root(),
		   source,
		   new String[] {
			   "documentation", "Root of this journey inheritance hierarchy."
		   });
		addAnnotation
		  (getFlow_AllElements(),
		   source,
		   new String[] {
			   "documentation", "Own and inherited elements."
		   });
		addAnnotation
		  (getPseudoState_Type(),
		   source,
		   new String[] {
			   "documentation", "Pseudo-state type provided by concrete sub-classes."
		   });
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore";
		addAnnotation
		  (flowElementEClass,
		   source,
		   new String[] {
			   "constraints", "final override suppress suppressAndOverride"
		   });
		addAnnotation
		  (serviceEClass,
		   source,
		   new String[] {
			   "constraints", "abstract"
		   });
		addAnnotation
		  (flowEClass,
		   source,
		   new String[] {
			   "constraints", "final abstract elements"
		   });
	}

} //FlowPackageImpl
