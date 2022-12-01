/**
 */
package org.nasdanika.flow.util;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.nasdanika.common.Adaptable;
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
import org.nasdanika.ncore.Marked;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NamedElement;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.nasdanika.flow.FlowPackage
 * @generated
 */
public class FlowSwitch<T1> extends Switch<T1> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static FlowPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlowSwitch() {
		if (modelPackage == null) {
			modelPackage = FlowPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T1 doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case FlowPackage.PACKAGE_ELEMENT: {
				PackageElement<?> packageElement = (PackageElement<?>)theEObject;
				T1 result = casePackageElement(packageElement);
				if (result == null) result = caseNamedElement(packageElement);
				if (result == null) result = caseModelElement(packageElement);
				if (result == null) result = caseMarked(packageElement);
				if (result == null) result = caseAdaptable(packageElement);
				if (result == null) result = caseIMarked(packageElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.PACKAGE: {
				org.nasdanika.flow.Package package_ = (org.nasdanika.flow.Package)theEObject;
				T1 result = casePackage(package_);
				if (result == null) result = casePackageElement(package_);
				if (result == null) result = caseNamedElement(package_);
				if (result == null) result = caseModelElement(package_);
				if (result == null) result = caseMarked(package_);
				if (result == null) result = caseAdaptable(package_);
				if (result == null) result = caseIMarked(package_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.PACKAGE_ENTRY: {
				@SuppressWarnings("unchecked") Map.Entry<String, org.nasdanika.flow.Package> packageEntry = (Map.Entry<String, org.nasdanika.flow.Package>)theEObject;
				T1 result = casePackageEntry(packageEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.SERVICE_PROVIDER: {
				ServiceProvider<?> serviceProvider = (ServiceProvider<?>)theEObject;
				T1 result = caseServiceProvider(serviceProvider);
				if (result == null) result = casePackageElement(serviceProvider);
				if (result == null) result = caseNamedElement(serviceProvider);
				if (result == null) result = caseModelElement(serviceProvider);
				if (result == null) result = caseMarked(serviceProvider);
				if (result == null) result = caseAdaptable(serviceProvider);
				if (result == null) result = caseIMarked(serviceProvider);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.PARTICIPANT: {
				Participant participant = (Participant)theEObject;
				T1 result = caseParticipant(participant);
				if (result == null) result = caseServiceProvider(participant);
				if (result == null) result = casePackageElement(participant);
				if (result == null) result = caseNamedElement(participant);
				if (result == null) result = caseModelElement(participant);
				if (result == null) result = caseMarked(participant);
				if (result == null) result = caseAdaptable(participant);
				if (result == null) result = caseIMarked(participant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.PARTICIPANT_ENTRY: {
				@SuppressWarnings("unchecked") Map.Entry<String, Participant> participantEntry = (Map.Entry<String, Participant>)theEObject;
				T1 result = caseParticipantEntry(participantEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.RESOURCE: {
				Resource resource = (Resource)theEObject;
				T1 result = caseResource(resource);
				if (result == null) result = caseServiceProvider(resource);
				if (result == null) result = casePackageElement(resource);
				if (result == null) result = caseNamedElement(resource);
				if (result == null) result = caseModelElement(resource);
				if (result == null) result = caseMarked(resource);
				if (result == null) result = caseAdaptable(resource);
				if (result == null) result = caseIMarked(resource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.RESOURCE_ENTRY: {
				@SuppressWarnings("unchecked") Map.Entry<String, Resource> resourceEntry = (Map.Entry<String, Resource>)theEObject;
				T1 result = caseResourceEntry(resourceEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.PARTICIPANT_RESPONSIBILITY: {
				ParticipantResponsibility<?> participantResponsibility = (ParticipantResponsibility<?>)theEObject;
				T1 result = caseParticipantResponsibility(participantResponsibility);
				if (result == null) result = casePackageElement(participantResponsibility);
				if (result == null) result = caseNamedElement(participantResponsibility);
				if (result == null) result = caseModelElement(participantResponsibility);
				if (result == null) result = caseMarked(participantResponsibility);
				if (result == null) result = caseAdaptable(participantResponsibility);
				if (result == null) result = caseIMarked(participantResponsibility);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.ARTIFACT: {
				Artifact artifact = (Artifact)theEObject;
				T1 result = caseArtifact(artifact);
				if (result == null) result = caseParticipantResponsibility(artifact);
				if (result == null) result = caseServiceProvider(artifact);
				if (result == null) result = casePackageElement(artifact);
				if (result == null) result = caseNamedElement(artifact);
				if (result == null) result = caseModelElement(artifact);
				if (result == null) result = caseMarked(artifact);
				if (result == null) result = caseAdaptable(artifact);
				if (result == null) result = caseIMarked(artifact);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.ARTIFACT_ENTRY: {
				@SuppressWarnings("unchecked") Map.Entry<String, Artifact> artifactEntry = (Map.Entry<String, Artifact>)theEObject;
				T1 result = caseArtifactEntry(artifactEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.ARTIFACT_PARTICIPANT_RESPONSIBILITY: {
				ArtifactParticipantResponsibility artifactParticipantResponsibility = (ArtifactParticipantResponsibility)theEObject;
				T1 result = caseArtifactParticipantResponsibility(artifactParticipantResponsibility);
				if (result == null) result = caseParticipantResponsibility(artifactParticipantResponsibility);
				if (result == null) result = casePackageElement(artifactParticipantResponsibility);
				if (result == null) result = caseNamedElement(artifactParticipantResponsibility);
				if (result == null) result = caseModelElement(artifactParticipantResponsibility);
				if (result == null) result = caseMarked(artifactParticipantResponsibility);
				if (result == null) result = caseAdaptable(artifactParticipantResponsibility);
				if (result == null) result = caseIMarked(artifactParticipantResponsibility);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.RELATIONSHIP: {
				Relationship relationship = (Relationship)theEObject;
				T1 result = caseRelationship(relationship);
				if (result == null) result = casePackageElement(relationship);
				if (result == null) result = caseNamedElement(relationship);
				if (result == null) result = caseModelElement(relationship);
				if (result == null) result = caseMarked(relationship);
				if (result == null) result = caseAdaptable(relationship);
				if (result == null) result = caseIMarked(relationship);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.RELATIONSHIP_ENTRY: {
				@SuppressWarnings("unchecked") Map.Entry<String, Relationship> relationshipEntry = (Map.Entry<String, Relationship>)theEObject;
				T1 result = caseRelationshipEntry(relationshipEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.FLOW_ELEMENT: {
				FlowElement<?> flowElement = (FlowElement<?>)theEObject;
				T1 result = caseFlowElement(flowElement);
				if (result == null) result = caseParticipantResponsibility(flowElement);
				if (result == null) result = casePackageElement(flowElement);
				if (result == null) result = caseNamedElement(flowElement);
				if (result == null) result = caseModelElement(flowElement);
				if (result == null) result = caseMarked(flowElement);
				if (result == null) result = caseAdaptable(flowElement);
				if (result == null) result = caseIMarked(flowElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.FLOW_ELEMENT_ENTRY: {
				@SuppressWarnings("unchecked") Map.Entry<String, FlowElement<?>> flowElementEntry = (Map.Entry<String, FlowElement<?>>)theEObject;
				T1 result = caseFlowElementEntry(flowElementEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.TRANSITION: {
				Transition transition = (Transition)theEObject;
				T1 result = caseTransition(transition);
				if (result == null) result = casePackageElement(transition);
				if (result == null) result = caseNamedElement(transition);
				if (result == null) result = caseModelElement(transition);
				if (result == null) result = caseMarked(transition);
				if (result == null) result = caseAdaptable(transition);
				if (result == null) result = caseIMarked(transition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.TRANSITION_ENTRY: {
				@SuppressWarnings("unchecked") Map.Entry<String, Transition> transitionEntry = (Map.Entry<String, Transition>)theEObject;
				T1 result = caseTransitionEntry(transitionEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.CALL: {
				Call call = (Call)theEObject;
				T1 result = caseCall(call);
				if (result == null) result = caseTransition(call);
				if (result == null) result = casePackageElement(call);
				if (result == null) result = caseNamedElement(call);
				if (result == null) result = caseModelElement(call);
				if (result == null) result = caseMarked(call);
				if (result == null) result = caseAdaptable(call);
				if (result == null) result = caseIMarked(call);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.CALL_ENTRY: {
				@SuppressWarnings("unchecked") Map.Entry<String, Call> callEntry = (Map.Entry<String, Call>)theEObject;
				T1 result = caseCallEntry(callEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.ACTIVITY: {
				Activity<?> activity = (Activity<?>)theEObject;
				T1 result = caseActivity(activity);
				if (result == null) result = caseFlowElement(activity);
				if (result == null) result = caseParticipantResponsibility(activity);
				if (result == null) result = casePackageElement(activity);
				if (result == null) result = caseNamedElement(activity);
				if (result == null) result = caseModelElement(activity);
				if (result == null) result = caseMarked(activity);
				if (result == null) result = caseAdaptable(activity);
				if (result == null) result = caseIMarked(activity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.ACTIVITY_ENTRY: {
				@SuppressWarnings("unchecked") Map.Entry<String, Activity<?>> activityEntry = (Map.Entry<String, Activity<?>>)theEObject;
				T1 result = caseActivityEntry(activityEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.SERVICE: {
				Service service = (Service)theEObject;
				T1 result = caseService(service);
				if (result == null) result = caseFlowElement(service);
				if (result == null) result = caseParticipantResponsibility(service);
				if (result == null) result = casePackageElement(service);
				if (result == null) result = caseNamedElement(service);
				if (result == null) result = caseModelElement(service);
				if (result == null) result = caseMarked(service);
				if (result == null) result = caseAdaptable(service);
				if (result == null) result = caseIMarked(service);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.FLOW: {
				Flow flow = (Flow)theEObject;
				T1 result = caseFlow(flow);
				if (result == null) result = caseActivity(flow);
				if (result == null) result = caseFlowElement(flow);
				if (result == null) result = caseParticipantResponsibility(flow);
				if (result == null) result = casePackageElement(flow);
				if (result == null) result = caseNamedElement(flow);
				if (result == null) result = caseModelElement(flow);
				if (result == null) result = caseMarked(flow);
				if (result == null) result = caseAdaptable(flow);
				if (result == null) result = caseIMarked(flow);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.PSEUDO_STATE: {
				PseudoState pseudoState = (PseudoState)theEObject;
				T1 result = casePseudoState(pseudoState);
				if (result == null) result = caseFlowElement(pseudoState);
				if (result == null) result = caseParticipantResponsibility(pseudoState);
				if (result == null) result = casePackageElement(pseudoState);
				if (result == null) result = caseNamedElement(pseudoState);
				if (result == null) result = caseModelElement(pseudoState);
				if (result == null) result = caseMarked(pseudoState);
				if (result == null) result = caseAdaptable(pseudoState);
				if (result == null) result = caseIMarked(pseudoState);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.CHOICE: {
				Choice choice = (Choice)theEObject;
				T1 result = caseChoice(choice);
				if (result == null) result = casePseudoState(choice);
				if (result == null) result = caseFlowElement(choice);
				if (result == null) result = caseParticipantResponsibility(choice);
				if (result == null) result = casePackageElement(choice);
				if (result == null) result = caseNamedElement(choice);
				if (result == null) result = caseModelElement(choice);
				if (result == null) result = caseMarked(choice);
				if (result == null) result = caseAdaptable(choice);
				if (result == null) result = caseIMarked(choice);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.END: {
				End end = (End)theEObject;
				T1 result = caseEnd(end);
				if (result == null) result = casePseudoState(end);
				if (result == null) result = caseFlowElement(end);
				if (result == null) result = caseParticipantResponsibility(end);
				if (result == null) result = casePackageElement(end);
				if (result == null) result = caseNamedElement(end);
				if (result == null) result = caseModelElement(end);
				if (result == null) result = caseMarked(end);
				if (result == null) result = caseAdaptable(end);
				if (result == null) result = caseIMarked(end);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.ENTRY_POINT: {
				EntryPoint entryPoint = (EntryPoint)theEObject;
				T1 result = caseEntryPoint(entryPoint);
				if (result == null) result = casePseudoState(entryPoint);
				if (result == null) result = caseFlowElement(entryPoint);
				if (result == null) result = caseParticipantResponsibility(entryPoint);
				if (result == null) result = casePackageElement(entryPoint);
				if (result == null) result = caseNamedElement(entryPoint);
				if (result == null) result = caseModelElement(entryPoint);
				if (result == null) result = caseMarked(entryPoint);
				if (result == null) result = caseAdaptable(entryPoint);
				if (result == null) result = caseIMarked(entryPoint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.EXIT_POINT: {
				ExitPoint exitPoint = (ExitPoint)theEObject;
				T1 result = caseExitPoint(exitPoint);
				if (result == null) result = casePseudoState(exitPoint);
				if (result == null) result = caseFlowElement(exitPoint);
				if (result == null) result = caseParticipantResponsibility(exitPoint);
				if (result == null) result = casePackageElement(exitPoint);
				if (result == null) result = caseNamedElement(exitPoint);
				if (result == null) result = caseModelElement(exitPoint);
				if (result == null) result = caseMarked(exitPoint);
				if (result == null) result = caseAdaptable(exitPoint);
				if (result == null) result = caseIMarked(exitPoint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.EXPANSION_INPUT: {
				ExpansionInput expansionInput = (ExpansionInput)theEObject;
				T1 result = caseExpansionInput(expansionInput);
				if (result == null) result = casePseudoState(expansionInput);
				if (result == null) result = caseFlowElement(expansionInput);
				if (result == null) result = caseParticipantResponsibility(expansionInput);
				if (result == null) result = casePackageElement(expansionInput);
				if (result == null) result = caseNamedElement(expansionInput);
				if (result == null) result = caseModelElement(expansionInput);
				if (result == null) result = caseMarked(expansionInput);
				if (result == null) result = caseAdaptable(expansionInput);
				if (result == null) result = caseIMarked(expansionInput);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.EXPANSION_OUTPUT: {
				ExpansionOutput expansionOutput = (ExpansionOutput)theEObject;
				T1 result = caseExpansionOutput(expansionOutput);
				if (result == null) result = casePseudoState(expansionOutput);
				if (result == null) result = caseFlowElement(expansionOutput);
				if (result == null) result = caseParticipantResponsibility(expansionOutput);
				if (result == null) result = casePackageElement(expansionOutput);
				if (result == null) result = caseNamedElement(expansionOutput);
				if (result == null) result = caseModelElement(expansionOutput);
				if (result == null) result = caseMarked(expansionOutput);
				if (result == null) result = caseAdaptable(expansionOutput);
				if (result == null) result = caseIMarked(expansionOutput);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.FORK: {
				Fork fork = (Fork)theEObject;
				T1 result = caseFork(fork);
				if (result == null) result = casePseudoState(fork);
				if (result == null) result = caseFlowElement(fork);
				if (result == null) result = caseParticipantResponsibility(fork);
				if (result == null) result = casePackageElement(fork);
				if (result == null) result = caseNamedElement(fork);
				if (result == null) result = caseModelElement(fork);
				if (result == null) result = caseMarked(fork);
				if (result == null) result = caseAdaptable(fork);
				if (result == null) result = caseIMarked(fork);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.INPUT_PIN: {
				InputPin inputPin = (InputPin)theEObject;
				T1 result = caseInputPin(inputPin);
				if (result == null) result = casePseudoState(inputPin);
				if (result == null) result = caseFlowElement(inputPin);
				if (result == null) result = caseParticipantResponsibility(inputPin);
				if (result == null) result = casePackageElement(inputPin);
				if (result == null) result = caseNamedElement(inputPin);
				if (result == null) result = caseModelElement(inputPin);
				if (result == null) result = caseMarked(inputPin);
				if (result == null) result = caseAdaptable(inputPin);
				if (result == null) result = caseIMarked(inputPin);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.JOIN: {
				Join join = (Join)theEObject;
				T1 result = caseJoin(join);
				if (result == null) result = casePseudoState(join);
				if (result == null) result = caseFlowElement(join);
				if (result == null) result = caseParticipantResponsibility(join);
				if (result == null) result = casePackageElement(join);
				if (result == null) result = caseNamedElement(join);
				if (result == null) result = caseModelElement(join);
				if (result == null) result = caseMarked(join);
				if (result == null) result = caseAdaptable(join);
				if (result == null) result = caseIMarked(join);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.OUTPUT_PIN: {
				OutputPin outputPin = (OutputPin)theEObject;
				T1 result = caseOutputPin(outputPin);
				if (result == null) result = casePseudoState(outputPin);
				if (result == null) result = caseFlowElement(outputPin);
				if (result == null) result = caseParticipantResponsibility(outputPin);
				if (result == null) result = casePackageElement(outputPin);
				if (result == null) result = caseNamedElement(outputPin);
				if (result == null) result = caseModelElement(outputPin);
				if (result == null) result = caseMarked(outputPin);
				if (result == null) result = caseAdaptable(outputPin);
				if (result == null) result = caseIMarked(outputPin);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FlowPackage.START: {
				Start start = (Start)theEObject;
				T1 result = caseStart(start);
				if (result == null) result = casePseudoState(start);
				if (result == null) result = caseFlowElement(start);
				if (result == null) result = caseParticipantResponsibility(start);
				if (result == null) result = casePackageElement(start);
				if (result == null) result = caseNamedElement(start);
				if (result == null) result = caseModelElement(start);
				if (result == null) result = caseMarked(start);
				if (result == null) result = caseAdaptable(start);
				if (result == null) result = caseIMarked(start);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Package Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Package Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T extends PackageElement<T>> T1 casePackageElement(PackageElement<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePackage(org.nasdanika.flow.Package object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Package Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Package Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePackageEntry(Map.Entry<String, org.nasdanika.flow.Package> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Service Provider</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Service Provider</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T extends PackageElement<T>> T1 caseServiceProvider(ServiceProvider<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Participant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Participant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseParticipant(Participant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Participant Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Participant Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseParticipantEntry(Map.Entry<String, Participant> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseResource(Resource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseResourceEntry(Map.Entry<String, Resource> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Participant Responsibility</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Participant Responsibility</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T extends PackageElement<T>> T1 caseParticipantResponsibility(ParticipantResponsibility<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Artifact</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Artifact</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseArtifact(Artifact object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Artifact Participant Responsibility</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Artifact Participant Responsibility</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseArtifactParticipantResponsibility(ArtifactParticipantResponsibility object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relationship</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relationship</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseRelationship(Relationship object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relationship Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relationship Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseRelationshipEntry(Map.Entry<String, Relationship> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Artifact Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Artifact Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseArtifactEntry(Map.Entry<String, Artifact> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T extends FlowElement<T>> T1 caseFlowElement(FlowElement<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseFlowElementEntry(Map.Entry<String, FlowElement<?>> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T extends Activity<T>> T1 caseActivity(Activity<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activity Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activity Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseActivityEntry(Map.Entry<String, Activity<?>> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Service</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseService(Service object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Transition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Transition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseTransition(Transition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Transition Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Transition Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseTransitionEntry(Map.Entry<String, Transition> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Call</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Call</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseCall(Call object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Call Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Call Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseCallEntry(Map.Entry<String, Call> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Flow</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Flow</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseFlow(Flow object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pseudo State</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pseudo State</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePseudoState(PseudoState object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Choice</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Choice</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseChoice(Choice object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>End</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>End</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseEnd(End object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entry Point</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entry Point</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseEntryPoint(EntryPoint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exit Point</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exit Point</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseExitPoint(ExitPoint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Expansion Input</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Expansion Input</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseExpansionInput(ExpansionInput object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Expansion Output</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Expansion Output</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseExpansionOutput(ExpansionOutput object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fork</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fork</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseFork(Fork object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Input Pin</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Input Pin</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseInputPin(InputPin object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Join</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Join</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseJoin(Join object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Output Pin</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Output Pin</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOutputPin(OutputPin object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Start</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Start</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStart(Start object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IMarked</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IMarked</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIMarked(org.nasdanika.persistence.Marked object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Marked</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Marked</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseMarked(Marked object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Adaptable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Adaptable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseAdaptable(Adaptable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseModelElement(ModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseNamedElement(NamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T1 defaultCase(EObject object) {
		return null;
	}

} //FlowSwitch
