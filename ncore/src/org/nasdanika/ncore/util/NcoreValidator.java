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
import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.DiagnosticHelper;
import org.nasdanika.ncore.Array;
import org.nasdanika.ncore.Configurable;
import org.nasdanika.ncore.Context;
import org.nasdanika.ncore.Entity;
import org.nasdanika.ncore.Entry;
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
import org.nasdanika.ncore.Resource;
import org.nasdanika.ncore.RestFunction;
import org.nasdanika.ncore.RestOperation;
import org.nasdanika.ncore.SupplierEntry;
import org.nasdanika.ncore.SupplierFactoryReference;
import org.nasdanika.ncore.TypedElement;
import org.nasdanika.ncore.TypedEntry;
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
			case NcorePackage.ISUPPLIER:
				return validateISupplier((Supplier<?>)value, diagnostics, context);
			case NcorePackage.ISUPPLIER_FACTORY:
				return validateISupplierFactory((SupplierFactory<?>)value, diagnostics, context);
			case NcorePackage.SUPPLIER_FACTORY_REFERENCE:
				return validateSupplierFactoryReference((SupplierFactoryReference)value, diagnostics, context);
			case NcorePackage.IFUNCTION:
				return validateIFunction((Function<?, ?>)value, diagnostics, context);
			case NcorePackage.IFUNCTION_FACTORY:
				return validateIFunctionFactory((FunctionFactory<?, ?>)value, diagnostics, context);
			case NcorePackage.ICONSUMER:
				return validateIConsumer((Consumer<?>)value, diagnostics, context);
			case NcorePackage.ICONSUMER_FACTORY:
				return validateIConsumerFactory((ConsumerFactory<?>)value, diagnostics, context);
			case NcorePackage.MODEL_ELEMENT:
				return validateModelElement((ModelElement)value, diagnostics, context);
			case NcorePackage.NAMED_ELEMENT:
				return validateNamedElement((NamedElement)value, diagnostics, context);
			case NcorePackage.ENTITY:
				return validateEntity((Entity)value, diagnostics, context);
			case NcorePackage.CONFIGURABLE:
				return validateConfigurable((Configurable)value, diagnostics, context);
			case NcorePackage.TYPED_ELEMENT:
				return validateTypedElement((TypedElement)value, diagnostics, context);
			case NcorePackage.SUPPLIER:
				return validateSupplier((org.nasdanika.ncore.Supplier)value, diagnostics, context);
			case NcorePackage.RESOURCE:
				return validateResource((Resource)value, diagnostics, context);
			case NcorePackage.VALUE:
				return validateValue((Value)value, diagnostics, context);
			case NcorePackage.NULL:
				return validateNull((Null)value, diagnostics, context);
			case NcorePackage.OPERATION:
				return validateOperation((Operation)value, diagnostics, context);
			case NcorePackage.ARRAY:
				return validateArray((Array)value, diagnostics, context);
			case NcorePackage.CONTEXT:
				return validateContext((Context)value, diagnostics, context);
			case NcorePackage.ENTRY:
				return validateEntry((Entry<?>)value, diagnostics, context);
			case NcorePackage.TYPED_ENTRY:
				return validateTypedEntry((TypedEntry)value, diagnostics, context);
			case NcorePackage.SUPPLIER_ENTRY:
				return validateSupplierEntry((SupplierEntry)value, diagnostics, context);
			case NcorePackage.MAP:
				return validateMap((org.nasdanika.ncore.Map)value, diagnostics, context);
			case NcorePackage.PROPERTY:
				return validateProperty((Property)value, diagnostics, context);
			case NcorePackage.FUNCTION:
				return validateFunction((org.nasdanika.ncore.Function)value, diagnostics, context);
			case NcorePackage.LIST:
				return validateList((List)value, diagnostics, context);
			case NcorePackage.OBJECT:
				return validateObject((org.nasdanika.ncore.Object)value, diagnostics, context);
			case NcorePackage.HTTP_CALL:
				return validateHttpCall((HttpCall)value, diagnostics, context);
			case NcorePackage.REST_OPERATION:
				return validateRestOperation((RestOperation)value, diagnostics, context);
			case NcorePackage.REST_FUNCTION:
				return validateRestFunction((RestFunction)value, diagnostics, context);
			case NcorePackage.HTML:
				return validateHtml((Html)value, diagnostics, context);
			case NcorePackage.HTTP_METHOD:
				return validateHttpMethod((HttpMethod)value, diagnostics, context);
			case NcorePackage.EXCEPTION:
				return validateException((Exception)value, diagnostics, context);
			case NcorePackage.ICONTEXT:
				return validateIContext((org.nasdanika.common.Context)value, diagnostics, context);
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
	public boolean validateISupplier(Supplier<?> iSupplier, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)iSupplier, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateISupplierFactory(SupplierFactory<?> iSupplierFactory, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)iSupplierFactory, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSupplierFactoryReference(SupplierFactoryReference supplierFactoryReference, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(supplierFactoryReference, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(supplierFactoryReference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(supplierFactoryReference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(supplierFactoryReference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(supplierFactoryReference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(supplierFactoryReference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(supplierFactoryReference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(supplierFactoryReference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(supplierFactoryReference, diagnostics, context);
		if (result || diagnostics != null) result &= validateSupplierFactoryReference_target(supplierFactoryReference, diagnostics, context);
		return result;
	}
	
	
	private boolean validateCircularReference(SupplierFactoryReference supplierFactoryReference, Set<SupplierFactoryReference> traversed) {
		if (traversed.add(supplierFactoryReference)) {
			SupplierFactory<Object> target = supplierFactoryReference.getTarget();
			if (target instanceof SupplierFactoryReference) {
				return validateCircularReference((SupplierFactoryReference) target, traversed);
			}
			if (target instanceof EObject) {
				TreeIterator<EObject> cit = ((EObject) target).eAllContents();
				while (cit.hasNext()) {
					EObject next = cit.next();
					if (next instanceof SupplierFactoryReference) {
						if (!validateCircularReference((SupplierFactoryReference) next, traversed)) {
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
	public boolean validateSupplierFactoryReference_target(SupplierFactoryReference supplierFactoryReference, DiagnosticChain diagnostics, Map<Object, Object> context) {
		SupplierFactory<Object> refTarget = supplierFactoryReference.getTarget();
		if (diagnostics != null && refTarget != null) {
			// Validate circularity
			DiagnosticHelper helper = new DiagnosticHelper(diagnostics, DIAGNOSTIC_SOURCE, 0, supplierFactoryReference);
			Set<SupplierFactoryReference> traversed = new HashSet<>();
			if (!validateCircularReference(supplierFactoryReference, traversed)) {
				StringBuilder path = new StringBuilder();
				for (SupplierFactoryReference pe: traversed) {	
					if (path.length() > 0) {
						path.append(" => ");
					}
					 SupplierFactory<Object> target = pe.getTarget();
					String targetLabel;
					if (target instanceof ModelElement) {
						targetLabel = ((ModelElement) target).getTitle();
					} else {
						targetLabel = target.toString();
					}
					path.append(pe.getTitle() + " -> " + targetLabel);
				}
				helper.error("Loop in supplier factory references: "+path, NcorePackage.Literals.SUPPLIER_FACTORY_REFERENCE__TARGET);							
				return false; // Do not proceed if circularity test failed
			}
			
			if (((EObject) refTarget).eContainmentFeature() == null) {
				helper.error("Reference target is not contained in a resource", NcorePackage.Literals.SUPPLIER_FACTORY_REFERENCE__TARGET);											
			} else {
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
	public boolean validateIFunction(Function<?, ?> iFunction, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)iFunction, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIFunctionFactory(FunctionFactory<?, ?> iFunctionFactory, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)iFunctionFactory, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIConsumer(Consumer<?> iConsumer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)iConsumer, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIConsumerFactory(ConsumerFactory<?> iConsumerFactory, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)iConsumerFactory, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypedElement(TypedElement typedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(typedElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSupplier(org.nasdanika.ncore.Supplier supplier, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(supplier, diagnostics, context);
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
	public boolean validateValue(Value value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(value, diagnostics, context);
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
		return validate_EveryDefaultConstraint(operation, diagnostics, context);
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
	public boolean validateContext(Context context, DiagnosticChain diagnostics, Map<Object, Object> theContext) {
		return validate_EveryDefaultConstraint(context, diagnostics, theContext);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEntry(Entry<?> entry, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(entry, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypedEntry(TypedEntry typedEntry, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(typedEntry, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSupplierEntry(SupplierEntry supplierEntry, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(supplierEntry, diagnostics, context);
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
			for (Entry<?> entry: map.getEntries()) {
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
		return validate_EveryDefaultConstraint(property, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFunction(org.nasdanika.ncore.Function function, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(function, diagnostics, context);
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
	public boolean validateRestOperation(RestOperation restOperation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(restOperation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRestFunction(RestFunction restFunction, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(restFunction, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHtml(Html html, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(html, diagnostics, context);
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIContext(org.nasdanika.common.Context iContext, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateException(Exception exception, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
