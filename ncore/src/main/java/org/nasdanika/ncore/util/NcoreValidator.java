/**
 */
package org.nasdanika.ncore.util;

import java.time.Duration;
import java.time.Instant;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;
import org.nasdanika.common.Adaptable;
import org.nasdanika.ncore.BooleanProperty;
import org.nasdanika.ncore.Catalog;
import org.nasdanika.ncore.Composite;
import org.nasdanika.ncore.Date;
import org.nasdanika.ncore.DateProperty;
import org.nasdanika.ncore.Directory;
import org.nasdanika.ncore.Documented;
import org.nasdanika.ncore.DocumentedNamedElement;
import org.nasdanika.ncore.DocumentedNamedStringIdentity;
import org.nasdanika.ncore.DoubleProperty;
import org.nasdanika.ncore.EObjectProperty;
import org.nasdanika.ncore.File;
import org.nasdanika.ncore.GitMarker;
import org.nasdanika.ncore.IntegerProperty;
import org.nasdanika.ncore.List;
import org.nasdanika.ncore.ListProperty;
import org.nasdanika.ncore.LongProperty;
import org.nasdanika.ncore.MapProperty;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.Period;
import org.nasdanika.ncore.Property;
import org.nasdanika.ncore.Reference;
import org.nasdanika.ncore.StringIdentity;
import org.nasdanika.ncore.StringProperty;
import org.nasdanika.ncore.Temporal;
import org.nasdanika.ncore.Tree;
import org.nasdanika.ncore.TreeItem;
import org.nasdanika.ncore.TreeItemReference;
import org.nasdanika.ncore.ValueObject;
import org.nasdanika.ncore.ValueObjectProperty;
import org.nasdanika.persistence.Marked;
import org.nasdanika.persistence.Marker;

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
			case NcorePackage.ADAPTABLE:
				return validateAdaptable((Adaptable)value, diagnostics, context);
			case NcorePackage.IMARKED:
				return validateIMarked((Marked)value, diagnostics, context);
			case NcorePackage.MARKED:
				return validateMarked((org.nasdanika.ncore.Marked)value, diagnostics, context);
			case NcorePackage.IMARKER:
				return validateIMarker((Marker)value, diagnostics, context);
			case NcorePackage.MARKER:
				return validateMarker((org.nasdanika.ncore.Marker)value, diagnostics, context);
			case NcorePackage.TEMPORAL:
				return validateTemporal((Temporal)value, diagnostics, context);
			case NcorePackage.PERIOD:
				return validatePeriod((Period)value, diagnostics, context);
			case NcorePackage.MODEL_ELEMENT:
				return validateModelElement((ModelElement)value, diagnostics, context);
			case NcorePackage.REPRESENTATION_ENTRY:
				return validateRepresentationEntry((Map.Entry<?, ?>)value, diagnostics, context);
			case NcorePackage.NAMED_ELEMENT:
				return validateNamedElement((NamedElement)value, diagnostics, context);
			case NcorePackage.REFERENCE:
				return validateReference((Reference<?>)value, diagnostics, context);
			case NcorePackage.VALUE_OBJECT:
				return validateValueObject((ValueObject<?>)value, diagnostics, context);
			case NcorePackage.STRING:
				return validateString((org.nasdanika.ncore.String)value, diagnostics, context);
			case NcorePackage.BOOLEAN:
				return validateBoolean((org.nasdanika.ncore.Boolean)value, diagnostics, context);
			case NcorePackage.DOUBLE:
				return validateDouble((org.nasdanika.ncore.Double)value, diagnostics, context);
			case NcorePackage.DATE:
				return validateDate((Date)value, diagnostics, context);
			case NcorePackage.INTEGER:
				return validateInteger((org.nasdanika.ncore.Integer)value, diagnostics, context);
			case NcorePackage.LIST:
				return validateList((List)value, diagnostics, context);
			case NcorePackage.LONG:
				return validateLong((org.nasdanika.ncore.Long)value, diagnostics, context);
			case NcorePackage.MAP:
				return validateMap((org.nasdanika.ncore.Map)value, diagnostics, context);
			case NcorePackage.PROPERTY:
				return validateProperty((Property)value, diagnostics, context);
			case NcorePackage.VALUE_OBJECT_PROPERTY:
				return validateValueObjectProperty((ValueObjectProperty<?>)value, diagnostics, context);
			case NcorePackage.BOOLEAN_PROPERTY:
				return validateBooleanProperty((BooleanProperty)value, diagnostics, context);
			case NcorePackage.DATE_PROPERTY:
				return validateDateProperty((DateProperty)value, diagnostics, context);
			case NcorePackage.DOUBLE_PROPERTY:
				return validateDoubleProperty((DoubleProperty)value, diagnostics, context);
			case NcorePackage.EOBJECT_PROPERTY:
				return validateEObjectProperty((EObjectProperty)value, diagnostics, context);
			case NcorePackage.INTEGER_PROPERTY:
				return validateIntegerProperty((IntegerProperty)value, diagnostics, context);
			case NcorePackage.LIST_PROPERTY:
				return validateListProperty((ListProperty)value, diagnostics, context);
			case NcorePackage.LONG_PROPERTY:
				return validateLongProperty((LongProperty)value, diagnostics, context);
			case NcorePackage.MAP_PROPERTY:
				return validateMapProperty((MapProperty)value, diagnostics, context);
			case NcorePackage.STRING_PROPERTY:
				return validateStringProperty((StringProperty)value, diagnostics, context);
			case NcorePackage.STRING_ENTRY:
				return validateStringEntry((Map.Entry<?, ?>)value, diagnostics, context);
			case NcorePackage.GIT_MARKER:
				return validateGitMarker((GitMarker)value, diagnostics, context);
			case NcorePackage.DOCUMENTED:
				return validateDocumented((Documented)value, diagnostics, context);
			case NcorePackage.DOCUMENTED_NAMED_ELEMENT:
				return validateDocumentedNamedElement((DocumentedNamedElement)value, diagnostics, context);
			case NcorePackage.COMPOSITE:
				return validateComposite((Composite)value, diagnostics, context);
			case NcorePackage.STRING_IDENTITY:
				return validateStringIdentity((StringIdentity)value, diagnostics, context);
			case NcorePackage.DOCUMENTED_NAMED_STRING_IDENTITY:
				return validateDocumentedNamedStringIdentity((DocumentedNamedStringIdentity)value, diagnostics, context);
			case NcorePackage.CATALOG:
				return validateCatalog((Catalog)value, diagnostics, context);
			case NcorePackage.TREE_ITEM:
				return validateTreeItem((TreeItem)value, diagnostics, context);
			case NcorePackage.TREE:
				return validateTree((Tree)value, diagnostics, context);
			case NcorePackage.FILE:
				return validateFile((File)value, diagnostics, context);
			case NcorePackage.DIRECTORY:
				return validateDirectory((Directory)value, diagnostics, context);
			case NcorePackage.THROWABLE:
				return validateThrowable((org.nasdanika.ncore.Throwable)value, diagnostics, context);
			case NcorePackage.STACK_TRACE_ELEMENT:
				return validateStackTraceElement((org.nasdanika.ncore.StackTraceElement)value, diagnostics, context);
			case NcorePackage.TREE_ITEM_REFERENCE:
				return validateTreeItemReference((TreeItemReference)value, diagnostics, context);
			case NcorePackage.INSTANT:
				return validateInstant((Instant)value, diagnostics, context);
			case NcorePackage.DURATION:
				return validateDuration((Duration)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAdaptable(Adaptable adaptable, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)adaptable, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIMarked(Marked iMarked, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)iMarked, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMarked(org.nasdanika.ncore.Marked marked, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(marked, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIMarker(Marker iMarker, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)iMarker, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMarker(org.nasdanika.ncore.Marker marker, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(marker, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTemporal(Temporal temporal, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(temporal, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(temporal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(temporal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(temporal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(temporal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(temporal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(temporal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(temporal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(temporal, diagnostics, context);
		if (result || diagnostics != null) result &= validateTemporal_bounds(temporal, diagnostics, context);
		if (result || diagnostics != null) result &= validateTemporal_offset(temporal, diagnostics, context);
		return result;
	}

	/**
	 * Validates the bounds constraint of '<em>Temporal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTemporal_bounds(Temporal temporal, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "bounds", getObjectLabel(temporal, context) },
						 new Object[] { temporal },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the offset constraint of '<em>Temporal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTemporal_offset(Temporal temporal, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "offset", getObjectLabel(temporal, context) },
						 new Object[] { temporal },
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
	public boolean validatePeriod(Period period, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(period, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(period, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(period, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(period, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(period, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(period, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(period, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(period, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(period, diagnostics, context);
		if (result || diagnostics != null) result &= validatePeriod_start_end(period, diagnostics, context);
		return result;
	}

	/**
	 * Validates the start_end constraint of '<em>Period</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePeriod_start_end(Period period, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "start_end", getObjectLabel(period, context) },
						 new Object[] { period },
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
	public boolean validateModelElement(ModelElement modelElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(modelElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRepresentationEntry(Map.Entry<?, ?> representationEntry, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)representationEntry, diagnostics, context);
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
	public boolean validateReference(Reference<?> reference, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(reference, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateValueObject(ValueObject<?> valueObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(valueObject, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateString(org.nasdanika.ncore.String string, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(string, diagnostics, context);
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
	public boolean validateMap(org.nasdanika.ncore.Map map, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(map, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInteger(org.nasdanika.ncore.Integer integer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(integer, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDouble(org.nasdanika.ncore.Double double_, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(double_, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDate(Date date, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(date, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLong(org.nasdanika.ncore.Long long_, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(long_, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBoolean(org.nasdanika.ncore.Boolean boolean_, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(boolean_, diagnostics, context);
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
	public boolean validateValueObjectProperty(ValueObjectProperty<?> valueObjectProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(valueObjectProperty, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStringProperty(StringProperty stringProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(stringProperty, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIntegerProperty(IntegerProperty integerProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(integerProperty, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDoubleProperty(DoubleProperty doubleProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(doubleProperty, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLongProperty(LongProperty longProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(longProperty, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDateProperty(DateProperty dateProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(dateProperty, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMapProperty(MapProperty mapProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(mapProperty, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateListProperty(ListProperty listProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(listProperty, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBooleanProperty(BooleanProperty booleanProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(booleanProperty, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEObjectProperty(EObjectProperty eObjectProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(eObjectProperty, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStringEntry(Map.Entry<?, ?> stringEntry, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)stringEntry, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGitMarker(GitMarker gitMarker, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(gitMarker, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDocumented(Documented documented, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(documented, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDocumentedNamedElement(DocumentedNamedElement documentedNamedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(documentedNamedElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateComposite(Composite composite, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(composite, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStringIdentity(StringIdentity stringIdentity, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(stringIdentity, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDocumentedNamedStringIdentity(DocumentedNamedStringIdentity documentedNamedStringIdentity, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(documentedNamedStringIdentity, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCatalog(Catalog catalog, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(catalog, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTreeItem(TreeItem treeItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(treeItem, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTree(Tree tree, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tree, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFile(File file, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(file, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDirectory(Directory directory, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(directory, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateThrowable(org.nasdanika.ncore.Throwable throwable, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(throwable, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStackTraceElement(org.nasdanika.ncore.StackTraceElement stackTraceElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(stackTraceElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTreeItemReference(TreeItemReference treeItemReference, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(treeItemReference, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInstant(Instant instant, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDuration(Duration duration, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
