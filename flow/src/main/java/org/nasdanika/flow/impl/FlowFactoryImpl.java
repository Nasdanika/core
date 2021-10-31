/**
 */
package org.nasdanika.flow.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.nasdanika.diagram.Diagram;
import org.nasdanika.flow.Activity;
import org.nasdanika.flow.Artifact;
import org.nasdanika.flow.ArtifactParticipantResponsibility;
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
import org.nasdanika.flow.PackageElement;
import org.nasdanika.flow.Participant;
import org.nasdanika.flow.ParticipantResponsibility;
import org.nasdanika.flow.PseudoState;
import org.nasdanika.flow.Relationship;
import org.nasdanika.flow.Resource;
import org.nasdanika.flow.Service;
import org.nasdanika.flow.ServiceProvider;
import org.nasdanika.flow.Start;
import org.nasdanika.flow.Transition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FlowFactoryImpl extends EFactoryImpl implements FlowFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static FlowFactory init() {
		try {
			FlowFactory theFlowFactory = (FlowFactory)EPackage.Registry.INSTANCE.getEFactory(FlowPackage.eNS_URI);
			if (theFlowFactory != null) {
				return theFlowFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new FlowFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlowFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case FlowPackage.REPRESENTATION_ENTRY: return (EObject)createRepresentationEntry();
			case FlowPackage.PACKAGE: return createPackage();
			case FlowPackage.PACKAGE_ENTRY: return (EObject)createPackageEntry();
			case FlowPackage.SERVICE_PROVIDER: return createServiceProvider();
			case FlowPackage.PARTICIPANT: return createParticipant();
			case FlowPackage.PARTICIPANT_ENTRY: return (EObject)createParticipantEntry();
			case FlowPackage.RESOURCE: return createResource();
			case FlowPackage.RESOURCE_ENTRY: return (EObject)createResourceEntry();
			case FlowPackage.PARTICIPANT_RESPONSIBILITY: return createParticipantResponsibility();
			case FlowPackage.ARTIFACT: return createArtifact();
			case FlowPackage.ARTIFACT_ENTRY: return (EObject)createArtifactEntry();
			case FlowPackage.ARTIFACT_PARTICIPANT_RESPONSIBILITY: return createArtifactParticipantResponsibility();
			case FlowPackage.RELATIONSHIP: return createRelationship();
			case FlowPackage.RELATIONSHIP_ENTRY: return (EObject)createRelationshipEntry();
			case FlowPackage.FLOW_ELEMENT: return createFlowElement();
			case FlowPackage.FLOW_ELEMENT_ENTRY: return (EObject)createFlowElementEntry();
			case FlowPackage.TRANSITION: return createTransition();
			case FlowPackage.TRANSITION_ENTRY: return (EObject)createTransitionEntry();
			case FlowPackage.CALL: return createCall();
			case FlowPackage.CALL_ENTRY: return (EObject)createCallEntry();
			case FlowPackage.ACTIVITY: return createActivity();
			case FlowPackage.ACTIVITY_ENTRY: return (EObject)createActivityEntry();
			case FlowPackage.SERVICE: return createService();
			case FlowPackage.FLOW: return createFlow();
			case FlowPackage.PSEUDO_STATE: return createPseudoState();
			case FlowPackage.CHOICE: return createChoice();
			case FlowPackage.END: return createEnd();
			case FlowPackage.ENTRY_POINT: return createEntryPoint();
			case FlowPackage.EXIT_POINT: return createExitPoint();
			case FlowPackage.EXPANSION_INPUT: return createExpansionInput();
			case FlowPackage.EXPANSION_OUTPUT: return createExpansionOutput();
			case FlowPackage.FORK: return createFork();
			case FlowPackage.INPUT_PIN: return createInputPin();
			case FlowPackage.JOIN: return createJoin();
			case FlowPackage.OUTPUT_PIN: return createOutputPin();
			case FlowPackage.START: return createStart();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, Diagram> createRepresentationEntry() {
		RepresentationEntryImpl representationEntry = new RepresentationEntryImpl();
		return representationEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.nasdanika.flow.Package createPackage() {
		PackageImpl package_ = new PackageImpl();
		return package_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, org.nasdanika.flow.Package> createPackageEntry() {
		PackageEntryImpl packageEntry = new PackageEntryImpl();
		return packageEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <T extends PackageElement<T>> ServiceProvider<T> createServiceProvider() {
		ServiceProviderImpl<T> serviceProvider = new ServiceProviderImpl<T>();
		return serviceProvider;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Participant createParticipant() {
		ParticipantImpl participant = new ParticipantImpl();
		return participant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, Participant> createParticipantEntry() {
		ParticipantEntryImpl participantEntry = new ParticipantEntryImpl();
		return participantEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Resource createResource() {
		ResourceImpl resource = new ResourceImpl();
		return resource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, Resource> createResourceEntry() {
		ResourceEntryImpl resourceEntry = new ResourceEntryImpl();
		return resourceEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <T extends PackageElement<T>> ParticipantResponsibility<T> createParticipantResponsibility() {
		ParticipantResponsibilityImpl<T> participantResponsibility = new ParticipantResponsibilityImpl<T>();
		return participantResponsibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Artifact createArtifact() {
		ArtifactImpl artifact = new ArtifactImpl();
		return artifact;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ArtifactParticipantResponsibility createArtifactParticipantResponsibility() {
		ArtifactParticipantResponsibilityImpl artifactParticipantResponsibility = new ArtifactParticipantResponsibilityImpl();
		return artifactParticipantResponsibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Relationship createRelationship() {
		RelationshipImpl relationship = new RelationshipImpl();
		return relationship;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, Relationship> createRelationshipEntry() {
		RelationshipEntryImpl relationshipEntry = new RelationshipEntryImpl();
		return relationshipEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, Artifact> createArtifactEntry() {
		ArtifactEntryImpl artifactEntry = new ArtifactEntryImpl();
		return artifactEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <T extends FlowElement<T>> FlowElement<T> createFlowElement() {
		FlowElementImpl<T> flowElement = new FlowElementImpl<T>();
		return flowElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, FlowElement<?>> createFlowElementEntry() {
		FlowElementEntryImpl flowElementEntry = new FlowElementEntryImpl();
		return flowElementEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <T extends Activity<T>> Activity<T> createActivity() {
		ActivityImpl<T> activity = new ActivityImpl<T>();
		return activity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, Activity<?>> createActivityEntry() {
		ActivityEntryImpl activityEntry = new ActivityEntryImpl();
		return activityEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Service createService() {
		ServiceImpl service = new ServiceImpl();
		return service;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Transition createTransition() {
		TransitionImpl transition = new TransitionImpl();
		return transition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, Transition> createTransitionEntry() {
		TransitionEntryImpl transitionEntry = new TransitionEntryImpl();
		return transitionEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Call createCall() {
		CallImpl call = new CallImpl();
		return call;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, Call> createCallEntry() {
		CallEntryImpl callEntry = new CallEntryImpl();
		return callEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Flow createFlow() {
		FlowImpl flow = new FlowImpl();
		return flow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PseudoState createPseudoState() {
		PseudoStateImpl pseudoState = new PseudoStateImpl();
		return pseudoState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Choice createChoice() {
		ChoiceImpl choice = new ChoiceImpl();
		return choice;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public End createEnd() {
		EndImpl end = new EndImpl();
		return end;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EntryPoint createEntryPoint() {
		EntryPointImpl entryPoint = new EntryPointImpl();
		return entryPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExitPoint createExitPoint() {
		ExitPointImpl exitPoint = new ExitPointImpl();
		return exitPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExpansionInput createExpansionInput() {
		ExpansionInputImpl expansionInput = new ExpansionInputImpl();
		return expansionInput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExpansionOutput createExpansionOutput() {
		ExpansionOutputImpl expansionOutput = new ExpansionOutputImpl();
		return expansionOutput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Fork createFork() {
		ForkImpl fork = new ForkImpl();
		return fork;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InputPin createInputPin() {
		InputPinImpl inputPin = new InputPinImpl();
		return inputPin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Join createJoin() {
		JoinImpl join = new JoinImpl();
		return join;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OutputPin createOutputPin() {
		OutputPinImpl outputPin = new OutputPinImpl();
		return outputPin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Start createStart() {
		StartImpl start = new StartImpl();
		return start;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FlowPackage getFlowPackage() {
		return (FlowPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static FlowPackage getPackage() {
		return FlowPackage.eINSTANCE;
	}

} //FlowFactoryImpl
