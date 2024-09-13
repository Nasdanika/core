/**
 */
package org.nasdanika.exec.util;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.nasdanika.common.Util;
import org.nasdanika.emf.DiagnosticHelper;
import org.nasdanika.exec.Block;
import org.nasdanika.exec.Call;
import org.nasdanika.exec.Configurator;
import org.nasdanika.exec.Eval;
import org.nasdanika.exec.ExecPackage;
import org.nasdanika.exec.Fail;
import org.nasdanika.exec.List;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.nasdanika.exec.ExecPackage
 * @generated
 */
public class ExecValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final ExecValidator INSTANCE = new ExecValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.nasdanika.exec";

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
	public ExecValidator() {
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
	  return ExecPackage.eINSTANCE;
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
			case ExecPackage.BLOCK:
				return validateBlock((Block)value, diagnostics, context);
			case ExecPackage.CALL:
				return validateCall((Call)value, diagnostics, context);
			case ExecPackage.PROPERTY:
				return validateProperty((Map.Entry<?, ?>)value, diagnostics, context);
			case ExecPackage.CONFIGURATOR:
				return validateConfigurator((Configurator)value, diagnostics, context);
			case ExecPackage.EVAL:
				return validateEval((Eval)value, diagnostics, context);
			case ExecPackage.FAIL:
				return validateFail((Fail)value, diagnostics, context);
			case ExecPackage.LIST:
				return validateList((List)value, diagnostics, context);
			case ExecPackage.MAP:
				return validateMap((org.nasdanika.exec.Map)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBlock(Block block, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(block, diagnostics, context);
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
		if (result || diagnostics != null) result &= validateCall_service_property_class(call, diagnostics, context);
		if (result || diagnostics != null) result &= validateCall_method_arguments(call, diagnostics, context);
		return result;
	}

	/**
	 * Validates the service_property_class constraint of '<em>Call</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateCall_service_property_class(Call call, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (diagnostics != null && Util.isBlank(call.getType()) && Util.isBlank(call.getService()) && Util.isBlank(call.getProperty())) {
			DiagnosticHelper helper = new DiagnosticHelper(diagnostics, DIAGNOSTIC_SOURCE, 0, call);
			helper.error("One of type, service, or property is required");									
			return helper.isSuccess();
		}
		return true;
	}

	/**
	 * Validates the method_arguments constraint of '<em>Call</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateCall_method_arguments(Call call, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (diagnostics != null && !call.getArguments().isEmpty() && Util.isBlank(call.getMethod())) {
			if (Util.isBlank(call.getType()) && Util.isBlank(call.getService()) && Util.isBlank(call.getProperty())) {
				DiagnosticHelper helper = new DiagnosticHelper(diagnostics, DIAGNOSTIC_SOURCE, 0, call);
				helper.error("Arguments shall be provided only when method is specified");									
				return helper.isSuccess();
			}
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProperty(Map.Entry<?, ?> property, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)property, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConfigurator(Configurator configurator, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(configurator, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEval(Eval eval, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(eval, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFail(Fail fail, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(fail, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateList(List list, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(list, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMap(org.nasdanika.exec.Map map, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(map, diagnostics, context);
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

} //ExecValidator
