/**
 */
package org.nasdanika.ncore.util;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.nasdanika.common.Util;
import org.nasdanika.emf.DiagnosticHelper;
import org.nasdanika.ncore.Array;
import org.nasdanika.ncore.Configurable;
import org.nasdanika.ncore.Entity;
import org.nasdanika.ncore.Entry;
import org.nasdanika.ncore.AbstractEntry;
import org.nasdanika.ncore.Function;
import org.nasdanika.ncore.Html;
import org.nasdanika.ncore.HttpCall;
import org.nasdanika.ncore.HttpMethod;
import org.nasdanika.ncore.List;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.Null;
import org.nasdanika.ncore.Operation;
import org.nasdanika.ncore.Property;
import org.nasdanika.ncore.Reference;
import org.nasdanika.ncore.Resource;
import org.nasdanika.ncore.Script;
import org.nasdanika.ncore.ScriptResource;
import org.nasdanika.ncore.ScriptText;
import org.nasdanika.ncore.Service;
import org.nasdanika.ncore.Supplier;
import org.nasdanika.ncore.Value;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.nasdanika.ncore.NcorePackage
 * @generated
 */
public class NcoreValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final NcoreValidator INSTANCE = new NcoreValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.nasdanika.ncore";

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
	public NcoreValidator() {
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
	  return NcorePackage.eINSTANCE;
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
			case NcorePackage.MODEL_ELEMENT:
				return validateModelElement((ModelElement)value, diagnostics, context);
			case NcorePackage.NAMED_ELEMENT:
				return validateNamedElement((NamedElement)value, diagnostics, context);
			case NcorePackage.ENTITY:
				return validateEntity((Entity)value, diagnostics, context);
			case NcorePackage.CONFIGURABLE:
				return validateConfigurable((Configurable)value, diagnostics, context);
			case NcorePackage.SERVICE:
				return validateService((Service)value, diagnostics, context);
			case NcorePackage.SUPPLIER:
				return validateSupplier((Supplier)value, diagnostics, context);
			case NcorePackage.RESOURCE:
				return validateResource((Resource)value, diagnostics, context);
			case NcorePackage.REFERENCE:
				return validateReference((Reference)value, diagnostics, context);
			case NcorePackage.VALUE:
				return validateValue((Value)value, diagnostics, context);
			case NcorePackage.NULL:
				return validateNull((Null)value, diagnostics, context);
			case NcorePackage.OPERATION:
				return validateOperation((Operation)value, diagnostics, context);
			case NcorePackage.ARRAY:
				return validateArray((Array)value, diagnostics, context);
			case NcorePackage.ABSTRACT_ENTRY:
				return validateAbstractEntry((AbstractEntry)value, diagnostics, context);
			case NcorePackage.MAP:
				return validateMap((org.nasdanika.ncore.Map)value, diagnostics, context);
			case NcorePackage.PROPERTY:
				return validateProperty((Property)value, diagnostics, context);
			case NcorePackage.FUNCTION:
				return validateFunction((Function)value, diagnostics, context);
			case NcorePackage.LIST:
				return validateList((List)value, diagnostics, context);
			case NcorePackage.OBJECT:
				return validateObject((org.nasdanika.ncore.Object)value, diagnostics, context);
			case NcorePackage.HTTP_CALL:
				return validateHttpCall((HttpCall)value, diagnostics, context);
			case NcorePackage.HTML:
				return validateHtml((Html)value, diagnostics, context);
			case NcorePackage.SCRIPT:
				return validateScript((Script)value, diagnostics, context);
			case NcorePackage.SCRIPT_TEXT:
				return validateScriptText((ScriptText)value, diagnostics, context);
			case NcorePackage.SCRIPT_RESOURCE:
				return validateScriptResource((ScriptResource)value, diagnostics, context);
			case NcorePackage.ENTRY:
				return validateEntry((Entry)value, diagnostics, context);
			case NcorePackage.HTTP_METHOD:
				return validateHttpMethod((HttpMethod)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateModelElement(ModelElement modelElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(modelElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNamedElement(NamedElement namedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(namedElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEntity(Entity entity, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(entity, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConfigurable(Configurable configurable, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(configurable, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateService(Service service, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(service, diagnostics, context);
	}

	private boolean validateCircularReference(Reference reference, Set<Reference> traversed) {
		if (traversed.add(reference)) {
			EObject target = reference.getTarget();
			if (target instanceof Reference) {
				return validateCircularReference((Reference) target, traversed);
			}
			if (target instanceof EObject) {
				TreeIterator<EObject> cit = ((EObject) target).eAllContents();
				while (cit.hasNext()) {
					EObject next = cit.next();
					if (next instanceof Reference) {
						if (!validateCircularReference((Reference) next, traversed)) {
							return false;
						}
					}
				}
			}
			return true;
		} 
		return false;
	}

	/**
	 * Validates the target constraint of '<em>Supplier Factory Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateReference_target(Reference reference, DiagnosticChain diagnostics, Map<Object, Object> context) {
		EObject refTarget = reference.getTarget();
		if (diagnostics != null && refTarget != null) {
			// Validate circularity
			DiagnosticHelper helper = new DiagnosticHelper(diagnostics, DIAGNOSTIC_SOURCE, 0, reference);
			Set<Reference> traversed = new HashSet<>();
			if (!validateCircularReference(reference, traversed)) {
				StringBuilder path = new StringBuilder();
				for (Reference pe: traversed) {	
					if (path.length() > 0) {
						path.append(" => ");
					}
					 EObject target = pe.getTarget();
					String targetLabel;
					if (target instanceof ModelElement) {
						targetLabel = ((ModelElement) target).getTitle();
					} else {
						targetLabel = target.toString();
					}
					path.append(pe.getTitle() + " -> " + targetLabel);
				}
				helper.error("Loop in supplier factory references: "+path, NcorePackage.Literals.REFERENCE__TARGET);							
				return false; // Do not proceed if circularity test failed
			}
			
			if (!((EObject) refTarget).eIsProxy()) {
				// Maybe unnecessary?
				Diagnostician diagnostician = new Diagnostician() {
					
					public Map<Object,Object> createDefaultContext() {
						return context;
					};
					
				};
				
				Diagnostic validationResult = diagnostician.validate((EObject) refTarget);				
				diagnostics.add(validationResult);
				return validationResult.getSeverity() != Diagnostic.ERROR;
			}
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSupplier(Supplier supplier, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(supplier, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(supplier, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(supplier, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(supplier, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(supplier, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(supplier, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(supplier, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(supplier, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(supplier, diagnostics, context);
		if (result || diagnostics != null) result &= validateSupplier_factory(supplier, diagnostics, context);
		return result;
	}

	/**
	 * Validates the factory constraint of '<em>Supplier</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateSupplier_factory(Supplier supplier, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (diagnostics != null && Util.isBlank(supplier.getFactory()) && !(supplier instanceof Value)) {			
			DiagnosticHelper helper = new DiagnosticHelper(diagnostics, DIAGNOSTIC_SOURCE, 0, supplier);
			helper.error("Supplier factory is a required attribute", NcorePackage.Literals.SUPPLIER__FACTORY);
			return helper.isSuccess();
		}
		return true;
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
	public boolean validateReference(Reference reference, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(reference, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(reference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(reference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(reference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(reference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(reference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(reference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(reference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(reference, diagnostics, context);
		if (result || diagnostics != null) result &= validateReference_target(reference, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateValue(Value value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(value, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(value, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(value, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(value, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(value, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(value, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(value, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(value, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(value, diagnostics, context);
		if (result || diagnostics != null) result &= validateSupplier_factory(value, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNull(Null null_, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(null_, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOperation(Operation operation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(operation, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(operation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(operation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(operation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(operation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(operation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(operation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(operation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(operation, diagnostics, context);
		if (result || diagnostics != null) result &= validateSupplier_factory(operation, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateArray(Array array, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(array, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAbstractEntry(AbstractEntry abstractEntry, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(abstractEntry, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMap(org.nasdanika.ncore.Map map, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(map, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(map, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(map, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(map, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(map, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(map, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(map, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(map, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(map, diagnostics, context);
		if (result || diagnostics != null) result &= validateMap_entries(map, diagnostics, context);
		return result;
	}

	/**
	 * Validates the entries constraint of '<em>Map</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateMap_entries(org.nasdanika.ncore.Map map, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (diagnostics != null) {			
			Set<String> names = new HashSet<>();
			boolean ret = true;
			for (AbstractEntry entry: map.getEntries()) {
				if (entry.isEnabled()) {
					DiagnosticHelper helper = new DiagnosticHelper(diagnostics, DIAGNOSTIC_SOURCE, 0, entry);
					if (!names.add(entry.getName())) {
						helper.error("Duplicate entry: "+entry.getName(), NcorePackage.Literals.NAMED_ELEMENT__NAME);
						ret = false;
					}
				}
			}
			return ret;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProperty(Property property, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(property, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(property, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(property, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(property, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(property, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(property, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(property, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(property, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(property, diagnostics, context);
		if (result || diagnostics != null) result &= validateSupplier_factory(property, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFunction(Function function, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(function, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(function, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(function, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(function, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(function, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(function, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(function, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(function, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(function, diagnostics, context);
		if (result || diagnostics != null) result &= validateSupplier_factory(function, diagnostics, context);
		return result;
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
	public boolean validateObject(org.nasdanika.ncore.Object object, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(object, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(object, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(object, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(object, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(object, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(object, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(object, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(object, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(object, diagnostics, context);
		if (result || diagnostics != null) result &= validateMap_entries(object, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHttpCall(HttpCall httpCall, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(httpCall, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHtml(Html html, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(html, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(html, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(html, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(html, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(html, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(html, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(html, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(html, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(html, diagnostics, context);
		if (result || diagnostics != null) result &= validateSupplier_factory(html, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScript(Script script, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(script, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(script, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(script, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(script, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(script, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(script, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(script, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(script, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(script, diagnostics, context);
		if (result || diagnostics != null) result &= validateScript_bindings(script, diagnostics, context);
		return result;
	}

	/**
	 * Validates the bindings constraint of '<em>Script</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateScript_bindings(Script script, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (diagnostics != null) {			
			DiagnosticHelper helper = new DiagnosticHelper(diagnostics, DIAGNOSTIC_SOURCE, 0, script);
			Set<String> bNames = new HashSet<>();
			for (AbstractEntry b: script.getBindings()) {
				if ("context".equals(b.getName())) {
					helper.error("'context' is a reserved binding", NcorePackage.Literals.SCRIPT__BINDINGS);					
				}
				if (!bNames.add(b.getName())) {
					helper.error("Duplicate binding: " + b.getName(), NcorePackage.Literals.SCRIPT__BINDINGS);					
				}
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
	public boolean validateScriptText(ScriptText scriptText, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(scriptText, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(scriptText, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(scriptText, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(scriptText, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(scriptText, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(scriptText, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(scriptText, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(scriptText, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(scriptText, diagnostics, context);
		if (result || diagnostics != null) result &= validateScript_bindings(scriptText, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScriptResource(ScriptResource scriptResource, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(scriptResource, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(scriptResource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(scriptResource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(scriptResource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(scriptResource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(scriptResource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(scriptResource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(scriptResource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(scriptResource, diagnostics, context);
		if (result || diagnostics != null) result &= validateScript_bindings(scriptResource, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEntry(Entry entry, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(entry, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHttpMethod(HttpMethod httpMethod, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
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

} //NcoreValidator
