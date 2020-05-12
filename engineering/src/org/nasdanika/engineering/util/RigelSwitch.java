/**
 */
package org.nasdanika.rigel.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

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
import org.nasdanika.rigel.Milestone;
import org.nasdanika.rigel.ModelElement;
import org.nasdanika.rigel.PackageElement;
import org.nasdanika.rigel.Participant;
import org.nasdanika.rigel.Partition;
import org.nasdanika.rigel.Requirement;
import org.nasdanika.rigel.Resource;
import org.nasdanika.rigel.RigelPackage;
import org.nasdanika.rigel.Source;
import org.nasdanika.rigel.Start;
import org.nasdanika.rigel.Target;
import org.nasdanika.rigel.Transition;

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
 * @see org.nasdanika.rigel.RigelPackage
 * @generated
 */
public class RigelSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static RigelPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RigelSwitch() {
		if (modelPackage == null) {
			modelPackage = RigelPackage.eINSTANCE;
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
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case RigelPackage.MODEL_ELEMENT: {
				ModelElement modelElement = (ModelElement)theEObject;
				T result = caseModelElement(modelElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RigelPackage.PACKAGE_ELEMENT: {
				PackageElement packageElement = (PackageElement)theEObject;
				T result = casePackageElement(packageElement);
				if (result == null) result = caseModelElement(packageElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RigelPackage.ENGINEERED_ELEMENT: {
				EngineeredElement engineeredElement = (EngineeredElement)theEObject;
				T result = caseEngineeredElement(engineeredElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RigelPackage.IPACKAGE: {
				IPackage iPackage = (IPackage)theEObject;
				T result = caseIPackage(iPackage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RigelPackage.PACKAGE: {
				org.nasdanika.rigel.Package package_ = (org.nasdanika.rigel.Package)theEObject;
				T result = casePackage(package_);
				if (result == null) result = casePackageElement(package_);
				if (result == null) result = caseEngineeredElement(package_);
				if (result == null) result = caseIPackage(package_);
				if (result == null) result = caseModelElement(package_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RigelPackage.PARTICIPANT: {
				Participant participant = (Participant)theEObject;
				T result = caseParticipant(participant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RigelPackage.ACTOR: {
				Actor actor = (Actor)theEObject;
				T result = caseActor(actor);
				if (result == null) result = casePackageElement(actor);
				if (result == null) result = caseEngineeredElement(actor);
				if (result == null) result = caseParticipant(actor);
				if (result == null) result = caseModelElement(actor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RigelPackage.CAPABILITY: {
				Capability capability = (Capability)theEObject;
				T result = caseCapability(capability);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RigelPackage.REQUIREMENT: {
				Requirement requirement = (Requirement)theEObject;
				T result = caseRequirement(requirement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RigelPackage.FLOW: {
				Flow flow = (Flow)theEObject;
				T result = caseFlow(flow);
				if (result == null) result = casePackageElement(flow);
				if (result == null) result = caseEngineeredElement(flow);
				if (result == null) result = caseRequirement(flow);
				if (result == null) result = caseModelElement(flow);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RigelPackage.FLOW_ELEMENT: {
				FlowElement flowElement = (FlowElement)theEObject;
				T result = caseFlowElement(flowElement);
				if (result == null) result = caseModelElement(flowElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RigelPackage.SOURCE: {
				Source source = (Source)theEObject;
				T result = caseSource(source);
				if (result == null) result = caseFlowElement(source);
				if (result == null) result = caseModelElement(source);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RigelPackage.START: {
				Start start = (Start)theEObject;
				T result = caseStart(start);
				if (result == null) result = casePackageElement(start);
				if (result == null) result = caseSource(start);
				if (result == null) result = caseFlowElement(start);
				if (result == null) result = caseModelElement(start);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RigelPackage.TARGET: {
				Target target = (Target)theEObject;
				T result = caseTarget(target);
				if (result == null) result = caseFlowElement(target);
				if (result == null) result = caseModelElement(target);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RigelPackage.END: {
				End end = (End)theEObject;
				T result = caseEnd(end);
				if (result == null) result = casePackageElement(end);
				if (result == null) result = caseTarget(end);
				if (result == null) result = caseFlowElement(end);
				if (result == null) result = caseModelElement(end);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RigelPackage.PARTITION: {
				Partition partition = (Partition)theEObject;
				T result = casePartition(partition);
				if (result == null) result = caseFlow(partition);
				if (result == null) result = caseFlowElement(partition);
				if (result == null) result = casePackageElement(partition);
				if (result == null) result = caseEngineeredElement(partition);
				if (result == null) result = caseRequirement(partition);
				if (result == null) result = caseModelElement(partition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RigelPackage.ACTIVITY: {
				Activity activity = (Activity)theEObject;
				T result = caseActivity(activity);
				if (result == null) result = caseFlow(activity);
				if (result == null) result = caseSource(activity);
				if (result == null) result = caseTarget(activity);
				if (result == null) result = casePackageElement(activity);
				if (result == null) result = caseEngineeredElement(activity);
				if (result == null) result = caseRequirement(activity);
				if (result == null) result = caseFlowElement(activity);
				if (result == null) result = caseModelElement(activity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RigelPackage.MILESTONE: {
				Milestone milestone = (Milestone)theEObject;
				T result = caseMilestone(milestone);
				if (result == null) result = casePackageElement(milestone);
				if (result == null) result = caseSource(milestone);
				if (result == null) result = caseTarget(milestone);
				if (result == null) result = caseFlowElement(milestone);
				if (result == null) result = caseModelElement(milestone);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RigelPackage.ACTIVITY_REFERENCE: {
				ActivityReference activityReference = (ActivityReference)theEObject;
				T result = caseActivityReference(activityReference);
				if (result == null) result = casePackageElement(activityReference);
				if (result == null) result = caseSource(activityReference);
				if (result == null) result = caseTarget(activityReference);
				if (result == null) result = caseFlowElement(activityReference);
				if (result == null) result = caseModelElement(activityReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RigelPackage.ARTIFACT: {
				Artifact artifact = (Artifact)theEObject;
				T result = caseArtifact(artifact);
				if (result == null) result = casePackageElement(artifact);
				if (result == null) result = caseEngineeredElement(artifact);
				if (result == null) result = caseModelElement(artifact);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RigelPackage.RESOURCE: {
				Resource resource = (Resource)theEObject;
				T result = caseResource(resource);
				if (result == null) result = casePackageElement(resource);
				if (result == null) result = caseEngineeredElement(resource);
				if (result == null) result = caseCapability(resource);
				if (result == null) result = caseModelElement(resource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RigelPackage.TRANSITION: {
				Transition transition = (Transition)theEObject;
				T result = caseTransition(transition);
				if (result == null) result = caseModelElement(transition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RigelPackage.ASSOCIATION: {
				Association association = (Association)theEObject;
				T result = caseAssociation(association);
				if (result == null) result = caseModelElement(association);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RigelPackage.ENGINEER: {
				Engineer engineer = (Engineer)theEObject;
				T result = caseEngineer(engineer);
				if (result == null) result = casePackageElement(engineer);
				if (result == null) result = caseModelElement(engineer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RigelPackage.ISSUE: {
				Issue issue = (Issue)theEObject;
				T result = caseIssue(issue);
				if (result == null) result = caseModelElement(issue);
				if (result == null) result = caseRequirement(issue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
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
	public T caseModelElement(ModelElement object) {
		return null;
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
	public T casePackageElement(PackageElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Engineered Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Engineered Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEngineeredElement(EngineeredElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IPackage</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IPackage</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIPackage(IPackage object) {
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
	public T casePackage(org.nasdanika.rigel.Package object) {
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
	public T caseParticipant(Participant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Actor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Actor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActor(Actor object) {
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
	public T caseFlow(Flow object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Flow Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Flow Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFlowElement(FlowElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSource(Source object) {
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
	public T caseStart(Start object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Target</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Target</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTarget(Target object) {
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
	public T caseEnd(End object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Partition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Partition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePartition(Partition object) {
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
	public T caseActivity(Activity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activity Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activity Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivityReference(ActivityReference object) {
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
	public T caseArtifact(Artifact object) {
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
	public T caseResource(Resource object) {
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
	public T caseTransition(Transition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Association</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Association</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAssociation(Association object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Engineer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Engineer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEngineer(Engineer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Issue</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Issue</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIssue(Issue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Milestone</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Milestone</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMilestone(Milestone object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Capability</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Capability</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCapability(Capability object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Requirement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Requirement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRequirement(Requirement object) {
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
	public T defaultCase(EObject object) {
		return null;
	}

} //RigelSwitch
