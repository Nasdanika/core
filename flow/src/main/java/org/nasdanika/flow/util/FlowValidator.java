/**
 */
package org.nasdanika.flow.util;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

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
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Fork;
import org.nasdanika.flow.InputPin;
import org.nasdanika.flow.Join;
import org.nasdanika.flow.OutputPin;
import org.nasdanika.flow.PackageElement;
import org.nasdanika.flow.Participant;
import org.nasdanika.flow.PseudoState;
import org.nasdanika.flow.Resource;
import org.nasdanika.flow.Service;
import org.nasdanika.flow.Start;
import org.nasdanika.flow.Transition;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.nasdanika.flow.FlowPackage
 * @generated
 */
public class FlowValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final FlowValidator INSTANCE = new FlowValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.nasdanika.flow";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlowValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return FlowPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case FlowPackage.PACKAGE_ELEMENT:
				return validatePackageElement((PackageElement<?>)value, diagnostics, context);
			case FlowPackage.PACKAGE:
				return validatePackage((org.nasdanika.flow.Package)value, diagnostics, context);
			case FlowPackage.PACKAGE_ENTRY:
				return validatePackageEntry((Map.Entry<?, ?>)value, diagnostics, context);
			case FlowPackage.PARTICIPANT:
				return validateParticipant((Participant)value, diagnostics, context);
			case FlowPackage.PARTICIPANT_ENTRY:
				return validateParticipantEntry((Map.Entry<?, ?>)value, diagnostics, context);
			case FlowPackage.RESOURCE:
				return validateResource((Resource)value, diagnostics, context);
			case FlowPackage.RESOURCE_ENTRY:
				return validateResourceEntry((Map.Entry<?, ?>)value, diagnostics, context);
			case FlowPackage.ARTIFACT:
				return validateArtifact((Artifact)value, diagnostics, context);
			case FlowPackage.ARTIFACT_ENTRY:
				return validateArtifactEntry((Map.Entry<?, ?>)value, diagnostics, context);
			case FlowPackage.FLOW_ELEMENT:
				return validateFlowElement((FlowElement<?>)value, diagnostics, context);
			case FlowPackage.ACTIVITY:
				return validateActivity((Activity<?>)value, diagnostics, context);
			case FlowPackage.ACTIVITY_ENTRY:
				return validateActivityEntry((Map.Entry<?, ?>)value, diagnostics, context);
			case FlowPackage.SERVICE:
				return validateService((Service)value, diagnostics, context);
			case FlowPackage.TRANSITION:
				return validateTransition((Transition)value, diagnostics, context);
			case FlowPackage.CALL:
				return validateCall((Call)value, diagnostics, context);
			case FlowPackage.FLOW:
				return validateFlow((Flow)value, diagnostics, context);
			case FlowPackage.PSEUDO_STATE:
				return validatePseudoState((PseudoState)value, diagnostics, context);
			case FlowPackage.CHOICE:
				return validateChoice((Choice)value, diagnostics, context);
			case FlowPackage.END:
				return validateEnd((End)value, diagnostics, context);
			case FlowPackage.ENTRY_POINT:
				return validateEntryPoint((EntryPoint)value, diagnostics, context);
			case FlowPackage.EXIT_POINT:
				return validateExitPoint((ExitPoint)value, diagnostics, context);
			case FlowPackage.EXPANSION_INPUT:
				return validateExpansionInput((ExpansionInput)value, diagnostics, context);
			case FlowPackage.EXPANSION_OUTPUT:
				return validateExpansionOutput((ExpansionOutput)value, diagnostics, context);
			case FlowPackage.FORK:
				return validateFork((Fork)value, diagnostics, context);
			case FlowPackage.INPUT_PIN:
				return validateInputPin((InputPin)value, diagnostics, context);
			case FlowPackage.JOIN:
				return validateJoin((Join)value, diagnostics, context);
			case FlowPackage.OUTPUT_PIN:
				return validateOutputPin((OutputPin)value, diagnostics, context);
			case FlowPackage.START:
				return validateStart((Start)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePackageElement(PackageElement<?> packageElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(packageElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePackage(org.nasdanika.flow.Package package_, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(package_, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePackageEntry(Map.Entry<?, ?> packageEntry, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)packageEntry, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateParticipant(Participant participant, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(participant, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateParticipantEntry(Map.Entry<?, ?> participantEntry, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)participantEntry, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateResource(Resource resource, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(resource, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateResourceEntry(Map.Entry<?, ?> resourceEntry, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)resourceEntry, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateArtifact(Artifact artifact, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(artifact, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateArtifactEntry(Map.Entry<?, ?> artifactEntry, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)artifactEntry, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFlowElement(FlowElement<?> flowElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(flowElement, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(flowElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(flowElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(flowElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(flowElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(flowElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(flowElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(flowElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(flowElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_final(flowElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_override(flowElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppress(flowElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppressAndOverride(flowElement, diagnostics, context);
		return result;
	}

	/**
	 * Validates the final constraint of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFlowElement_final(FlowElement<?> flowElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "final", getObjectLabel(flowElement, context) },
						 new Object[] { flowElement },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the override constraint of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFlowElement_override(FlowElement<?> flowElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "override", getObjectLabel(flowElement, context) },
						 new Object[] { flowElement },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the suppress constraint of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFlowElement_suppress(FlowElement<?> flowElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "suppress", getObjectLabel(flowElement, context) },
						 new Object[] { flowElement },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the suppressAndOverride constraint of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFlowElement_suppressAndOverride(FlowElement<?> flowElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "suppressAndOverride", getObjectLabel(flowElement, context) },
						 new Object[] { flowElement },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateActivity(Activity<?> activity, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(activity, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(activity, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(activity, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(activity, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(activity, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(activity, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(activity, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(activity, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(activity, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_final(activity, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_override(activity, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppress(activity, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppressAndOverride(activity, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateActivityEntry(Map.Entry<?, ?> activityEntry, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)activityEntry, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateService(Service service, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(service, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(service, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(service, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(service, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(service, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(service, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(service, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(service, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(service, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_final(service, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_override(service, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppress(service, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppressAndOverride(service, diagnostics, context);
		if (result || diagnostics != null) result &= validateService_abstract(service, diagnostics, context);
		return result;
	}

	/**
	 * Validates the abstract constraint of '<em>Service</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateService_abstract(Service service, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "abstract", getObjectLabel(service, context) },
						 new Object[] { service },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTransition(Transition transition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(transition, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCall(Call call, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(call, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFlow(Flow flow, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(flow, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(flow, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(flow, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(flow, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(flow, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(flow, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(flow, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(flow, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(flow, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlow_final(flow, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_override(flow, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppress(flow, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppressAndOverride(flow, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlow_abstract(flow, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlow_elements(flow, diagnostics, context);
		return result;
	}

	/**
	 * Validates the final constraint of '<em>Flow</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFlow_final(Flow flow, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO override the constraint, if desired
		// -> uncomment the scaffolding
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "final", getObjectLabel(flow, context) },
						 new Object[] { flow },
						 context));
			}
			return false;
		}
		return validateFlowElement_final(flow, diagnostics, context);
	}

	/**
	 * Validates the abstract constraint of '<em>Flow</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFlow_abstract(Flow flow, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "abstract", getObjectLabel(flow, context) },
						 new Object[] { flow },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the elements constraint of '<em>Flow</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFlow_elements(Flow flow, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "elements", getObjectLabel(flow, context) },
						 new Object[] { flow },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePseudoState(PseudoState pseudoState, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(pseudoState, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(pseudoState, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(pseudoState, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(pseudoState, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(pseudoState, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(pseudoState, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(pseudoState, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(pseudoState, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(pseudoState, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_final(pseudoState, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_override(pseudoState, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppress(pseudoState, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppressAndOverride(pseudoState, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateChoice(Choice choice, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(choice, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(choice, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(choice, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(choice, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(choice, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(choice, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(choice, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(choice, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(choice, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_final(choice, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_override(choice, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppress(choice, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppressAndOverride(choice, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEnd(End end, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(end, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(end, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(end, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(end, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(end, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(end, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(end, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(end, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(end, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_final(end, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_override(end, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppress(end, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppressAndOverride(end, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEntryPoint(EntryPoint entryPoint, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(entryPoint, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(entryPoint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(entryPoint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(entryPoint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(entryPoint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(entryPoint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(entryPoint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(entryPoint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(entryPoint, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_final(entryPoint, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_override(entryPoint, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppress(entryPoint, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppressAndOverride(entryPoint, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExitPoint(ExitPoint exitPoint, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(exitPoint, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(exitPoint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(exitPoint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(exitPoint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(exitPoint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(exitPoint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(exitPoint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(exitPoint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(exitPoint, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_final(exitPoint, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_override(exitPoint, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppress(exitPoint, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppressAndOverride(exitPoint, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExpansionInput(ExpansionInput expansionInput, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(expansionInput, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(expansionInput, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(expansionInput, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(expansionInput, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(expansionInput, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(expansionInput, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(expansionInput, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(expansionInput, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(expansionInput, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_final(expansionInput, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_override(expansionInput, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppress(expansionInput, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppressAndOverride(expansionInput, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExpansionOutput(ExpansionOutput expansionOutput, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(expansionOutput, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(expansionOutput, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(expansionOutput, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(expansionOutput, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(expansionOutput, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(expansionOutput, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(expansionOutput, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(expansionOutput, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(expansionOutput, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_final(expansionOutput, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_override(expansionOutput, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppress(expansionOutput, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppressAndOverride(expansionOutput, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFork(Fork fork, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(fork, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(fork, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(fork, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(fork, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(fork, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(fork, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(fork, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(fork, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(fork, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_final(fork, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_override(fork, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppress(fork, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppressAndOverride(fork, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInputPin(InputPin inputPin, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(inputPin, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(inputPin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(inputPin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(inputPin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(inputPin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(inputPin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(inputPin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(inputPin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(inputPin, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_final(inputPin, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_override(inputPin, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppress(inputPin, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppressAndOverride(inputPin, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateJoin(Join join, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(join, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(join, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(join, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(join, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(join, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(join, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(join, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(join, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(join, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_final(join, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_override(join, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppress(join, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppressAndOverride(join, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOutputPin(OutputPin outputPin, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(outputPin, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(outputPin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(outputPin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(outputPin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(outputPin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(outputPin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(outputPin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(outputPin, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(outputPin, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_final(outputPin, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_override(outputPin, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppress(outputPin, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppressAndOverride(outputPin, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStart(Start start, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(start, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(start, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(start, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(start, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(start, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(start, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(start, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(start, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(start, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_final(start, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_override(start, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppress(start, diagnostics, context);
		if (result || diagnostics != null) result &= validateFlowElement_suppressAndOverride(start, diagnostics, context);
		return result;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //FlowValidator
