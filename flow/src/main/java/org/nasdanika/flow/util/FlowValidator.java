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
import org.nasdanika.common.Util;
import org.nasdanika.emf.DiagnosticHelper;
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
import org.nasdanika.flow.ServiceProvider;
import org.nasdanika.flow.Start;
import org.nasdanika.flow.Transition;
import org.nasdanika.ncore.NcorePackage;

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
			case FlowPackage.SERVICE_PROVIDER:
				return validateServiceProvider((ServiceProvider<?>)value, diagnostics, context);
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
			case FlowPackage.FLOW_ELEMENT_ENTRY:
				return validateFlowElementEntry((Map.Entry<?, ?>)value, diagnostics, context);
			case FlowPackage.TRANSITION:
				return validateTransition((Transition)value, diagnostics, context);
			case FlowPackage.TRANSITION_ENTRY:
				return validateTransitionEntry((Map.Entry<?, ?>)value, diagnostics, context);
			case FlowPackage.CALL:
				return validateCall((Call)value, diagnostics, context);
			case FlowPackage.CALL_ENTRY:
				return validateCallEntry((Map.Entry<?, ?>)value, diagnostics, context);
			case FlowPackage.ACTIVITY:
				return validateActivity((Activity<?>)value, diagnostics, context);
			case FlowPackage.ACTIVITY_ENTRY:
				return validateActivityEntry((Map.Entry<?, ?>)value, diagnostics, context);
			case FlowPackage.SERVICE:
				return validateService((Service)value, diagnostics, context);
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
		if (!validate_NoCircularContainment(packageElement, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(packageElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(packageElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(packageElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(packageElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(packageElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(packageElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(packageElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(packageElement, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_final(packageElement, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_extension(packageElement, diagnostics, context);
		return result;
	}

	/**
	 * Validates the final constraint of '<em>Package Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validatePackageElement_final(PackageElement<?> packageElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (diagnostics != null) {
			DiagnosticHelper helper = new DiagnosticHelper(diagnostics, DIAGNOSTIC_SOURCE, 0, packageElement);
			if (packageElement.getModifiers().contains("final") && packageElement.getModifiers().contains("abstract")) {
				helper.error(packageElement.eClass().getName() + "  " + packageElement.getName() + " is final and abstract at the same time.");
			}
			for (PackageElement<?> ex: packageElement.getExtends()) {
				if (ex.getModifiers().contains("final")) {
					helper.error("Extending a final " + ex.eClass().getName() + " " + ex.getName() + " (" + ex.getUri() + ")");
				}
			}
			return helper.isSuccess();
		}
		return true;
	}

	/**
	 * Validates the override constraint of '<em>Package Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validatePackageElement_extension(PackageElement<?> packageElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (diagnostics != null) {
			DiagnosticHelper helper = new DiagnosticHelper(diagnostics, DIAGNOSTIC_SOURCE, 0, packageElement);
			if (packageElement.getModifiers().contains("override") && packageElement.getExtends().isEmpty()) {
				helper.error(packageElement.eClass().getName() + "  " + packageElement.getName() + " is an extension but does not extend any other element.");
			}
			return helper.isSuccess();
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePackage(org.nasdanika.flow.Package package_, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(package_, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_final(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_extension(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackage_test(package_, diagnostics, context);
		return result;
	}

	/**
	 * Validates the test constraint of '<em>Package</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validatePackage_test(org.nasdanika.flow.Package package_, DiagnosticChain diagnostics, Map<Object, Object> context) {
//		if (diagnostics != null) {
//			DiagnosticHelper helper = new DiagnosticHelper(diagnostics, DIAGNOSTIC_SOURCE, 0, package_);
//			helper.error("Package error");
//			helper.warning("URI warning", NcorePackage.Literals.MODEL_ELEMENT__URI);
//			return helper.isSuccess();
//		}
		return true;
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
	public boolean validateServiceProvider(ServiceProvider<?> serviceProvider, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(serviceProvider, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(serviceProvider, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(serviceProvider, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(serviceProvider, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(serviceProvider, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(serviceProvider, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(serviceProvider, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(serviceProvider, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(serviceProvider, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_final(serviceProvider, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_extension(serviceProvider, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateParticipant(Participant participant, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(participant, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(participant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(participant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(participant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(participant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(participant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(participant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(participant, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(participant, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_final(participant, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_extension(participant, diagnostics, context);
		return result;
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
		if (!validate_NoCircularContainment(resource, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(resource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(resource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(resource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(resource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(resource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(resource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(resource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(resource, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_final(resource, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_extension(resource, diagnostics, context);
		return result;
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
		if (!validate_NoCircularContainment(artifact, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(artifact, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(artifact, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(artifact, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(artifact, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(artifact, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(artifact, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(artifact, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(artifact, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_final(artifact, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_extension(artifact, diagnostics, context);
		return result;
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
		if (result || diagnostics != null) result &= validatePackageElement_final(flowElement, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_extension(flowElement, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFlowElementEntry(Map.Entry<?, ?> flowElementEntry, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)flowElementEntry, diagnostics, context);
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
		if (result || diagnostics != null) result &= validatePackageElement_final(activity, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_extension(activity, diagnostics, context);
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
		if (result || diagnostics != null) result &= validatePackageElement_final(service, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_extension(service, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTransition(Transition transition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(transition, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_final(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_extension(transition, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTransitionEntry(Map.Entry<?, ?> transitionEntry, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)transitionEntry, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCall(Call call, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(call, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(call, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(call, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(call, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(call, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(call, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(call, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(call, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(call, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_final(call, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_extension(call, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCallEntry(Map.Entry<?, ?> callEntry, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)callEntry, diagnostics, context);
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
		if (result || diagnostics != null) result &= validatePackageElement_final(flow, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_extension(flow, diagnostics, context);
		return result;
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
		if (result || diagnostics != null) result &= validatePackageElement_final(pseudoState, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_extension(pseudoState, diagnostics, context);
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
		if (result || diagnostics != null) result &= validatePackageElement_final(choice, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_extension(choice, diagnostics, context);
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
		if (result || diagnostics != null) result &= validatePackageElement_final(end, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_extension(end, diagnostics, context);
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
		if (result || diagnostics != null) result &= validatePackageElement_final(entryPoint, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_extension(entryPoint, diagnostics, context);
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
		if (result || diagnostics != null) result &= validatePackageElement_final(exitPoint, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_extension(exitPoint, diagnostics, context);
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
		if (result || diagnostics != null) result &= validatePackageElement_final(expansionInput, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_extension(expansionInput, diagnostics, context);
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
		if (result || diagnostics != null) result &= validatePackageElement_final(expansionOutput, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_extension(expansionOutput, diagnostics, context);
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
		if (result || diagnostics != null) result &= validatePackageElement_final(fork, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_extension(fork, diagnostics, context);
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
		if (result || diagnostics != null) result &= validatePackageElement_final(inputPin, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_extension(inputPin, diagnostics, context);
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
		if (result || diagnostics != null) result &= validatePackageElement_final(join, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_extension(join, diagnostics, context);
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
		if (result || diagnostics != null) result &= validatePackageElement_final(outputPin, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_extension(outputPin, diagnostics, context);
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
		if (result || diagnostics != null) result &= validatePackageElement_final(start, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageElement_extension(start, diagnostics, context);
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
