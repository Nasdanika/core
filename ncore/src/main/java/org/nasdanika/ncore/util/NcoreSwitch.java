/**
 */
package org.nasdanika.ncore.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;
import org.nasdanika.common.Adaptable;
import org.nasdanika.ncore.BooleanProperty;
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
import org.nasdanika.ncore.Map;
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
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.nasdanika.ncore.NcorePackage
 * @generated
 */
public class NcoreSwitch<T1> extends Switch<T1> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static NcorePackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NcoreSwitch() {
		if (modelPackage == null) {
			modelPackage = NcorePackage.eINSTANCE;
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
			case NcorePackage.ADAPTABLE: {
				Adaptable adaptable = (Adaptable)theEObject;
				T1 result = caseAdaptable(adaptable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.IMARKED: {
				Marked iMarked = (Marked)theEObject;
				T1 result = caseIMarked(iMarked);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.MARKED: {
				org.nasdanika.ncore.Marked marked = (org.nasdanika.ncore.Marked)theEObject;
				T1 result = caseMarked(marked);
				if (result == null) result = caseIMarked(marked);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.IMARKER: {
				Marker iMarker = (Marker)theEObject;
				T1 result = caseIMarker(iMarker);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.MARKER: {
				org.nasdanika.ncore.Marker marker = (org.nasdanika.ncore.Marker)theEObject;
				T1 result = caseMarker(marker);
				if (result == null) result = caseIMarker(marker);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.TEMPORAL: {
				Temporal temporal = (Temporal)theEObject;
				T1 result = caseTemporal(temporal);
				if (result == null) result = caseModelElement(temporal);
				if (result == null) result = caseMarked(temporal);
				if (result == null) result = caseAdaptable(temporal);
				if (result == null) result = caseIMarked(temporal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.PERIOD: {
				Period period = (Period)theEObject;
				T1 result = casePeriod(period);
				if (result == null) result = caseModelElement(period);
				if (result == null) result = caseMarked(period);
				if (result == null) result = caseAdaptable(period);
				if (result == null) result = caseIMarked(period);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.MODEL_ELEMENT: {
				ModelElement modelElement = (ModelElement)theEObject;
				T1 result = caseModelElement(modelElement);
				if (result == null) result = caseMarked(modelElement);
				if (result == null) result = caseAdaptable(modelElement);
				if (result == null) result = caseIMarked(modelElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.REPRESENTATION_ENTRY: {
				@SuppressWarnings("unchecked") java.util.Map.Entry<String, String> representationEntry = (java.util.Map.Entry<String, String>)theEObject;
				T1 result = caseRepresentationEntry(representationEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.NAMED_ELEMENT: {
				NamedElement namedElement = (NamedElement)theEObject;
				T1 result = caseNamedElement(namedElement);
				if (result == null) result = caseModelElement(namedElement);
				if (result == null) result = caseMarked(namedElement);
				if (result == null) result = caseAdaptable(namedElement);
				if (result == null) result = caseIMarked(namedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.REFERENCE: {
				Reference<?> reference = (Reference<?>)theEObject;
				T1 result = caseReference(reference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.VALUE_OBJECT: {
				ValueObject<?> valueObject = (ValueObject<?>)theEObject;
				T1 result = caseValueObject(valueObject);
				if (result == null) result = caseMarked(valueObject);
				if (result == null) result = caseIMarked(valueObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.STRING: {
				org.nasdanika.ncore.String string = (org.nasdanika.ncore.String)theEObject;
				T1 result = caseString(string);
				if (result == null) result = caseValueObject(string);
				if (result == null) result = caseMarked(string);
				if (result == null) result = caseIMarked(string);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.BOOLEAN: {
				org.nasdanika.ncore.Boolean boolean_ = (org.nasdanika.ncore.Boolean)theEObject;
				T1 result = caseBoolean(boolean_);
				if (result == null) result = caseValueObject(boolean_);
				if (result == null) result = caseMarked(boolean_);
				if (result == null) result = caseIMarked(boolean_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.DOUBLE: {
				org.nasdanika.ncore.Double double_ = (org.nasdanika.ncore.Double)theEObject;
				T1 result = caseDouble(double_);
				if (result == null) result = caseValueObject(double_);
				if (result == null) result = caseMarked(double_);
				if (result == null) result = caseIMarked(double_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.DATE: {
				Date date = (Date)theEObject;
				T1 result = caseDate(date);
				if (result == null) result = caseValueObject(date);
				if (result == null) result = caseMarked(date);
				if (result == null) result = caseIMarked(date);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.INTEGER: {
				org.nasdanika.ncore.Integer integer = (org.nasdanika.ncore.Integer)theEObject;
				T1 result = caseInteger(integer);
				if (result == null) result = caseValueObject(integer);
				if (result == null) result = caseMarked(integer);
				if (result == null) result = caseIMarked(integer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.LIST: {
				List list = (List)theEObject;
				T1 result = caseList(list);
				if (result == null) result = caseMarked(list);
				if (result == null) result = caseIMarked(list);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.LONG: {
				org.nasdanika.ncore.Long long_ = (org.nasdanika.ncore.Long)theEObject;
				T1 result = caseLong(long_);
				if (result == null) result = caseValueObject(long_);
				if (result == null) result = caseMarked(long_);
				if (result == null) result = caseIMarked(long_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.MAP: {
				Map map = (Map)theEObject;
				T1 result = caseMap(map);
				if (result == null) result = caseMarked(map);
				if (result == null) result = caseIMarked(map);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.PROPERTY: {
				Property property = (Property)theEObject;
				T1 result = caseProperty(property);
				if (result == null) result = caseMarked(property);
				if (result == null) result = caseIMarked(property);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.VALUE_OBJECT_PROPERTY: {
				ValueObjectProperty<?> valueObjectProperty = (ValueObjectProperty<?>)theEObject;
				T1 result = caseValueObjectProperty(valueObjectProperty);
				if (result == null) result = caseValueObject(valueObjectProperty);
				if (result == null) result = caseProperty(valueObjectProperty);
				if (result == null) result = caseMarked(valueObjectProperty);
				if (result == null) result = caseIMarked(valueObjectProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.BOOLEAN_PROPERTY: {
				BooleanProperty booleanProperty = (BooleanProperty)theEObject;
				T1 result = caseBooleanProperty(booleanProperty);
				if (result == null) result = caseBoolean(booleanProperty);
				if (result == null) result = caseValueObjectProperty(booleanProperty);
				if (result == null) result = caseValueObject(booleanProperty);
				if (result == null) result = caseProperty(booleanProperty);
				if (result == null) result = caseMarked(booleanProperty);
				if (result == null) result = caseIMarked(booleanProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.DATE_PROPERTY: {
				DateProperty dateProperty = (DateProperty)theEObject;
				T1 result = caseDateProperty(dateProperty);
				if (result == null) result = caseDate(dateProperty);
				if (result == null) result = caseValueObjectProperty(dateProperty);
				if (result == null) result = caseValueObject(dateProperty);
				if (result == null) result = caseProperty(dateProperty);
				if (result == null) result = caseMarked(dateProperty);
				if (result == null) result = caseIMarked(dateProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.DOUBLE_PROPERTY: {
				DoubleProperty doubleProperty = (DoubleProperty)theEObject;
				T1 result = caseDoubleProperty(doubleProperty);
				if (result == null) result = caseDouble(doubleProperty);
				if (result == null) result = caseValueObjectProperty(doubleProperty);
				if (result == null) result = caseValueObject(doubleProperty);
				if (result == null) result = caseProperty(doubleProperty);
				if (result == null) result = caseMarked(doubleProperty);
				if (result == null) result = caseIMarked(doubleProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.EOBJECT_PROPERTY: {
				EObjectProperty eObjectProperty = (EObjectProperty)theEObject;
				T1 result = caseEObjectProperty(eObjectProperty);
				if (result == null) result = caseProperty(eObjectProperty);
				if (result == null) result = caseMarked(eObjectProperty);
				if (result == null) result = caseIMarked(eObjectProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.INTEGER_PROPERTY: {
				IntegerProperty integerProperty = (IntegerProperty)theEObject;
				T1 result = caseIntegerProperty(integerProperty);
				if (result == null) result = caseInteger(integerProperty);
				if (result == null) result = caseValueObjectProperty(integerProperty);
				if (result == null) result = caseValueObject(integerProperty);
				if (result == null) result = caseProperty(integerProperty);
				if (result == null) result = caseMarked(integerProperty);
				if (result == null) result = caseIMarked(integerProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.LIST_PROPERTY: {
				ListProperty listProperty = (ListProperty)theEObject;
				T1 result = caseListProperty(listProperty);
				if (result == null) result = caseProperty(listProperty);
				if (result == null) result = caseList(listProperty);
				if (result == null) result = caseMarked(listProperty);
				if (result == null) result = caseIMarked(listProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.LONG_PROPERTY: {
				LongProperty longProperty = (LongProperty)theEObject;
				T1 result = caseLongProperty(longProperty);
				if (result == null) result = caseLong(longProperty);
				if (result == null) result = caseValueObjectProperty(longProperty);
				if (result == null) result = caseValueObject(longProperty);
				if (result == null) result = caseProperty(longProperty);
				if (result == null) result = caseMarked(longProperty);
				if (result == null) result = caseIMarked(longProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.MAP_PROPERTY: {
				MapProperty mapProperty = (MapProperty)theEObject;
				T1 result = caseMapProperty(mapProperty);
				if (result == null) result = caseProperty(mapProperty);
				if (result == null) result = caseMap(mapProperty);
				if (result == null) result = caseMarked(mapProperty);
				if (result == null) result = caseIMarked(mapProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.STRING_PROPERTY: {
				StringProperty stringProperty = (StringProperty)theEObject;
				T1 result = caseStringProperty(stringProperty);
				if (result == null) result = caseString(stringProperty);
				if (result == null) result = caseValueObjectProperty(stringProperty);
				if (result == null) result = caseValueObject(stringProperty);
				if (result == null) result = caseProperty(stringProperty);
				if (result == null) result = caseMarked(stringProperty);
				if (result == null) result = caseIMarked(stringProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.STRING_ENTRY: {
				@SuppressWarnings("unchecked") java.util.Map.Entry<String, String> stringEntry = (java.util.Map.Entry<String, String>)theEObject;
				T1 result = caseStringEntry(stringEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.GIT_MARKER: {
				GitMarker gitMarker = (GitMarker)theEObject;
				T1 result = caseGitMarker(gitMarker);
				if (result == null) result = caseMarker(gitMarker);
				if (result == null) result = caseIMarker(gitMarker);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.DOCUMENTED: {
				Documented documented = (Documented)theEObject;
				T1 result = caseDocumented(documented);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.DOCUMENTED_NAMED_ELEMENT: {
				DocumentedNamedElement documentedNamedElement = (DocumentedNamedElement)theEObject;
				T1 result = caseDocumentedNamedElement(documentedNamedElement);
				if (result == null) result = caseNamedElement(documentedNamedElement);
				if (result == null) result = caseDocumented(documentedNamedElement);
				if (result == null) result = caseModelElement(documentedNamedElement);
				if (result == null) result = caseMarked(documentedNamedElement);
				if (result == null) result = caseAdaptable(documentedNamedElement);
				if (result == null) result = caseIMarked(documentedNamedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.COMPOSITE: {
				Composite composite = (Composite)theEObject;
				T1 result = caseComposite(composite);
				if (result == null) result = caseDocumentedNamedElement(composite);
				if (result == null) result = caseNamedElement(composite);
				if (result == null) result = caseDocumented(composite);
				if (result == null) result = caseModelElement(composite);
				if (result == null) result = caseMarked(composite);
				if (result == null) result = caseAdaptable(composite);
				if (result == null) result = caseIMarked(composite);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.STRING_IDENTITY: {
				StringIdentity stringIdentity = (StringIdentity)theEObject;
				T1 result = caseStringIdentity(stringIdentity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.DOCUMENTED_NAMED_STRING_IDENTITY: {
				DocumentedNamedStringIdentity documentedNamedStringIdentity = (DocumentedNamedStringIdentity)theEObject;
				T1 result = caseDocumentedNamedStringIdentity(documentedNamedStringIdentity);
				if (result == null) result = caseDocumentedNamedElement(documentedNamedStringIdentity);
				if (result == null) result = caseStringIdentity(documentedNamedStringIdentity);
				if (result == null) result = caseNamedElement(documentedNamedStringIdentity);
				if (result == null) result = caseDocumented(documentedNamedStringIdentity);
				if (result == null) result = caseModelElement(documentedNamedStringIdentity);
				if (result == null) result = caseMarked(documentedNamedStringIdentity);
				if (result == null) result = caseAdaptable(documentedNamedStringIdentity);
				if (result == null) result = caseIMarked(documentedNamedStringIdentity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.TREE_ITEM: {
				TreeItem treeItem = (TreeItem)theEObject;
				T1 result = caseTreeItem(treeItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.TREE: {
				Tree tree = (Tree)theEObject;
				T1 result = caseTree(tree);
				if (result == null) result = caseTreeItem(tree);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.FILE: {
				File file = (File)theEObject;
				T1 result = caseFile(file);
				if (result == null) result = caseTreeItem(file);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.DIRECTORY: {
				Directory directory = (Directory)theEObject;
				T1 result = caseDirectory(directory);
				if (result == null) result = caseFile(directory);
				if (result == null) result = caseTree(directory);
				if (result == null) result = caseTreeItem(directory);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.THROWABLE: {
				org.nasdanika.ncore.Throwable throwable = (org.nasdanika.ncore.Throwable)theEObject;
				T1 result = caseThrowable(throwable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.STACK_TRACE_ELEMENT: {
				org.nasdanika.ncore.StackTraceElement stackTraceElement = (org.nasdanika.ncore.StackTraceElement)theEObject;
				T1 result = caseStackTraceElement(stackTraceElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.TREE_ITEM_REFERENCE: {
				TreeItemReference treeItemReference = (TreeItemReference)theEObject;
				T1 result = caseTreeItemReference(treeItemReference);
				if (result == null) result = caseTreeItem(treeItemReference);
				if (result == null) result = caseReference(treeItemReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
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
	public T1 caseIMarked(Marked object) {
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
	public T1 caseMarked(org.nasdanika.ncore.Marked object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IMarker</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IMarker</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIMarker(Marker object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Marker</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Marker</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseMarker(org.nasdanika.ncore.Marker object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Temporal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Temporal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseTemporal(Temporal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Period</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Period</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePeriod(Period object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Representation Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Representation Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseRepresentationEntry(java.util.Map.Entry<String, String> object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseReference(Reference<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseValueObject(ValueObject<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseString(org.nasdanika.ncore.String object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseList(List object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Map</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Map</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseMap(Map object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseInteger(org.nasdanika.ncore.Integer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Double</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Double</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDouble(org.nasdanika.ncore.Double object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Date</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Date</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDate(Date object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Long</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Long</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseLong(org.nasdanika.ncore.Long object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Boolean</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Boolean</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseBoolean(org.nasdanika.ncore.Boolean object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseProperty(Property object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value Object Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value Object Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseValueObjectProperty(ValueObjectProperty<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStringProperty(StringProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIntegerProperty(IntegerProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Double Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Double Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDoubleProperty(DoubleProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Long Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Long Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseLongProperty(LongProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Date Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Date Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDateProperty(DateProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Map Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Map Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseMapProperty(MapProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>List Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>List Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseListProperty(ListProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Boolean Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Boolean Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseBooleanProperty(BooleanProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseEObjectProperty(EObjectProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStringEntry(java.util.Map.Entry<String, String> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Git Marker</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Git Marker</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseGitMarker(GitMarker object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Documented</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Documented</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDocumented(Documented object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Documented Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Documented Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDocumentedNamedElement(DocumentedNamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Composite</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Composite</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseComposite(Composite object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Identity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Identity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStringIdentity(StringIdentity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Documented Named String Identity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Documented Named String Identity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDocumentedNamedStringIdentity(DocumentedNamedStringIdentity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tree Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tree Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseTreeItem(TreeItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tree</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tree</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseTree(Tree object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>File</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>File</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseFile(File object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Directory</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Directory</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDirectory(Directory object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Throwable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Throwable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseThrowable(org.nasdanika.ncore.Throwable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stack Trace Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stack Trace Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStackTraceElement(org.nasdanika.ncore.StackTraceElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tree Item Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tree Item Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseTreeItemReference(TreeItemReference object) {
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

} //NcoreSwitch
