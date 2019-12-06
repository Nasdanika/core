/**
 */
package org.nasdanika.ncore.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.ncore.Array;
import org.nasdanika.ncore.ContactMethod;
import org.nasdanika.ncore.Context;
import org.nasdanika.ncore.EMail;
import org.nasdanika.ncore.Entity;
import org.nasdanika.ncore.Entry;
import org.nasdanika.ncore.Function;
import org.nasdanika.ncore.HttpCall;
import org.nasdanika.ncore.List;
import org.nasdanika.ncore.Map;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.Null;
import org.nasdanika.ncore.Operation;
import org.nasdanika.ncore.Party;
import org.nasdanika.ncore.Phone;
import org.nasdanika.ncore.PostalAddress;
import org.nasdanika.ncore.Property;
import org.nasdanika.ncore.RestFunction;
import org.nasdanika.ncore.RestOperation;
import org.nasdanika.ncore.SupplierEntry;
import org.nasdanika.ncore.SupplierFactoryReference;
import org.nasdanika.ncore.TypedElement;
import org.nasdanika.ncore.TypedEntry;
import org.nasdanika.ncore.Value;
import org.nasdanika.ncore.WebAddress;

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
			case NcorePackage.MODEL_ELEMENT: {
				ModelElement modelElement = (ModelElement)theEObject;
				T1 result = caseModelElement(modelElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.NAMED_ELEMENT: {
				NamedElement namedElement = (NamedElement)theEObject;
				T1 result = caseNamedElement(namedElement);
				if (result == null) result = caseModelElement(namedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.ENTITY: {
				Entity entity = (Entity)theEObject;
				T1 result = caseEntity(entity);
				if (result == null) result = caseModelElement(entity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.PARTY: {
				Party party = (Party)theEObject;
				T1 result = caseParty(party);
				if (result == null) result = caseNamedElement(party);
				if (result == null) result = caseEntity(party);
				if (result == null) result = caseModelElement(party);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.CONTACT_METHOD: {
				ContactMethod contactMethod = (ContactMethod)theEObject;
				T1 result = caseContactMethod(contactMethod);
				if (result == null) result = caseNamedElement(contactMethod);
				if (result == null) result = caseModelElement(contactMethod);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.EMAIL: {
				EMail eMail = (EMail)theEObject;
				T1 result = caseEMail(eMail);
				if (result == null) result = caseContactMethod(eMail);
				if (result == null) result = caseNamedElement(eMail);
				if (result == null) result = caseModelElement(eMail);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.PHONE: {
				Phone phone = (Phone)theEObject;
				T1 result = casePhone(phone);
				if (result == null) result = caseContactMethod(phone);
				if (result == null) result = caseNamedElement(phone);
				if (result == null) result = caseModelElement(phone);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.POSTAL_ADDRESS: {
				PostalAddress postalAddress = (PostalAddress)theEObject;
				T1 result = casePostalAddress(postalAddress);
				if (result == null) result = caseContactMethod(postalAddress);
				if (result == null) result = caseNamedElement(postalAddress);
				if (result == null) result = caseModelElement(postalAddress);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.WEB_ADDRESS: {
				WebAddress webAddress = (WebAddress)theEObject;
				T1 result = caseWebAddress(webAddress);
				if (result == null) result = caseContactMethod(webAddress);
				if (result == null) result = caseNamedElement(webAddress);
				if (result == null) result = caseModelElement(webAddress);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.ISUPPLIER: {
				Supplier<?> iSupplier = (Supplier<?>)theEObject;
				T1 result = caseISupplier(iSupplier);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.ISUPPLIER_FACTORY: {
				SupplierFactory<?> iSupplierFactory = (SupplierFactory<?>)theEObject;
				T1 result = caseISupplierFactory(iSupplierFactory);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.SUPPLIER_FACTORY_REFERENCE: {
				SupplierFactoryReference supplierFactoryReference = (SupplierFactoryReference)theEObject;
				T1 result = caseSupplierFactoryReference(supplierFactoryReference);
				if (result == null) result = caseModelElement(supplierFactoryReference);
				if (result == null) result = caseISupplierFactory(supplierFactoryReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.IFUNCTION: {
				org.nasdanika.common.Function<?, ?> iFunction = (org.nasdanika.common.Function<?, ?>)theEObject;
				T1 result = caseIFunction(iFunction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.IFUNCTION_FACTORY: {
				FunctionFactory<?, ?> iFunctionFactory = (FunctionFactory<?, ?>)theEObject;
				T1 result = caseIFunctionFactory(iFunctionFactory);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.ICONSUMER: {
				Consumer<?> iConsumer = (Consumer<?>)theEObject;
				T1 result = caseIConsumer(iConsumer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.ICONSUMER_FACTORY: {
				ConsumerFactory<?> iConsumerFactory = (ConsumerFactory<?>)theEObject;
				T1 result = caseIConsumerFactory(iConsumerFactory);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.TYPED_ELEMENT: {
				TypedElement typedElement = (TypedElement)theEObject;
				T1 result = caseTypedElement(typedElement);
				if (result == null) result = caseModelElement(typedElement);
				if (result == null) result = caseISupplierFactory(typedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.SUPPLIER: {
				org.nasdanika.ncore.Supplier supplier = (org.nasdanika.ncore.Supplier)theEObject;
				T1 result = caseSupplier(supplier);
				if (result == null) result = caseTypedElement(supplier);
				if (result == null) result = caseModelElement(supplier);
				if (result == null) result = caseISupplierFactory(supplier);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.VALUE: {
				Value value = (Value)theEObject;
				T1 result = caseValue(value);
				if (result == null) result = caseSupplier(value);
				if (result == null) result = caseTypedElement(value);
				if (result == null) result = caseModelElement(value);
				if (result == null) result = caseISupplierFactory(value);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.NULL: {
				Null null_ = (Null)theEObject;
				T1 result = caseNull(null_);
				if (result == null) result = caseTypedElement(null_);
				if (result == null) result = caseModelElement(null_);
				if (result == null) result = caseISupplierFactory(null_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.OPERATION: {
				Operation operation = (Operation)theEObject;
				T1 result = caseOperation(operation);
				if (result == null) result = caseSupplier(operation);
				if (result == null) result = caseTypedElement(operation);
				if (result == null) result = caseModelElement(operation);
				if (result == null) result = caseISupplierFactory(operation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.ARRAY: {
				Array array = (Array)theEObject;
				T1 result = caseArray(array);
				if (result == null) result = caseModelElement(array);
				if (result == null) result = caseISupplierFactory(array);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.CONTEXT: {
				Context context = (Context)theEObject;
				T1 result = caseContext(context);
				if (result == null) result = caseModelElement(context);
				if (result == null) result = caseISupplierFactory(context);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.ENTRY: {
				Entry<?> entry = (Entry<?>)theEObject;
				T1 result = caseEntry(entry);
				if (result == null) result = caseNamedElement(entry);
				if (result == null) result = caseISupplierFactory(entry);
				if (result == null) result = caseModelElement(entry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.TYPED_ENTRY: {
				TypedEntry typedEntry = (TypedEntry)theEObject;
				T1 result = caseTypedEntry(typedEntry);
				if (result == null) result = caseTypedElement(typedEntry);
				if (result == null) result = caseEntry(typedEntry);
				if (result == null) result = caseISupplierFactory(typedEntry);
				if (result == null) result = caseNamedElement(typedEntry);
				if (result == null) result = caseModelElement(typedEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.SUPPLIER_ENTRY: {
				SupplierEntry supplierEntry = (SupplierEntry)theEObject;
				T1 result = caseSupplierEntry(supplierEntry);
				if (result == null) result = caseSupplier(supplierEntry);
				if (result == null) result = caseEntry(supplierEntry);
				if (result == null) result = caseTypedElement(supplierEntry);
				if (result == null) result = caseNamedElement(supplierEntry);
				if (result == null) result = caseModelElement(supplierEntry);
				if (result == null) result = caseISupplierFactory(supplierEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.MAP: {
				Map map = (Map)theEObject;
				T1 result = caseMap(map);
				if (result == null) result = caseModelElement(map);
				if (result == null) result = caseISupplierFactory(map);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.PROPERTY: {
				Property property = (Property)theEObject;
				T1 result = caseProperty(property);
				if (result == null) result = caseValue(property);
				if (result == null) result = caseEntry(property);
				if (result == null) result = caseSupplier(property);
				if (result == null) result = caseNamedElement(property);
				if (result == null) result = caseTypedElement(property);
				if (result == null) result = caseModelElement(property);
				if (result == null) result = caseISupplierFactory(property);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.FUNCTION: {
				Function function = (Function)theEObject;
				T1 result = caseFunction(function);
				if (result == null) result = caseOperation(function);
				if (result == null) result = caseEntry(function);
				if (result == null) result = caseSupplier(function);
				if (result == null) result = caseNamedElement(function);
				if (result == null) result = caseTypedElement(function);
				if (result == null) result = caseModelElement(function);
				if (result == null) result = caseISupplierFactory(function);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.LIST: {
				List list = (List)theEObject;
				T1 result = caseList(list);
				if (result == null) result = caseArray(list);
				if (result == null) result = caseEntry(list);
				if (result == null) result = caseISupplierFactory(list);
				if (result == null) result = caseNamedElement(list);
				if (result == null) result = caseModelElement(list);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.OBJECT: {
				org.nasdanika.ncore.Object object = (org.nasdanika.ncore.Object)theEObject;
				T1 result = caseObject(object);
				if (result == null) result = caseMap(object);
				if (result == null) result = caseEntry(object);
				if (result == null) result = caseISupplierFactory(object);
				if (result == null) result = caseNamedElement(object);
				if (result == null) result = caseModelElement(object);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.HTTP_CALL: {
				HttpCall httpCall = (HttpCall)theEObject;
				T1 result = caseHttpCall(httpCall);
				if (result == null) result = caseModelElement(httpCall);
				if (result == null) result = caseISupplierFactory(httpCall);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.REST_OPERATION: {
				RestOperation restOperation = (RestOperation)theEObject;
				T1 result = caseRestOperation(restOperation);
				if (result == null) result = caseHttpCall(restOperation);
				if (result == null) result = caseModelElement(restOperation);
				if (result == null) result = caseISupplierFactory(restOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case NcorePackage.REST_FUNCTION: {
				RestFunction restFunction = (RestFunction)theEObject;
				T1 result = caseRestFunction(restFunction);
				if (result == null) result = caseRestOperation(restFunction);
				if (result == null) result = caseEntry(restFunction);
				if (result == null) result = caseHttpCall(restFunction);
				if (result == null) result = caseNamedElement(restFunction);
				if (result == null) result = caseModelElement(restFunction);
				if (result == null) result = caseISupplierFactory(restFunction);
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
	 * Returns the result of interpreting the object as an instance of '<em>Entity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseEntity(Entity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Party</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Party</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseParty(Party object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Contact Method</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Contact Method</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseContactMethod(ContactMethod object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EMail</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EMail</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseEMail(EMail object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Phone</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Phone</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePhone(Phone object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Postal Address</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Postal Address</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePostalAddress(PostalAddress object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Web Address</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Web Address</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseWebAddress(WebAddress object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ISupplier</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ISupplier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseISupplier(Supplier<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ISupplier Factory</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ISupplier Factory</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseISupplierFactory(SupplierFactory<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Supplier Factory Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Supplier Factory Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseSupplierFactoryReference(SupplierFactoryReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IFunction</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IFunction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T, R> T1 caseIFunction(org.nasdanika.common.Function<T, R> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IFunction Factory</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IFunction Factory</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T, R> T1 caseIFunctionFactory(FunctionFactory<T, R> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IConsumer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IConsumer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseIConsumer(Consumer<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IConsumer Factory</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IConsumer Factory</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseIConsumerFactory(ConsumerFactory<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Typed Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Typed Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseTypedElement(TypedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Supplier</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Supplier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseSupplier(org.nasdanika.ncore.Supplier object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseValue(Value object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Null</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Null</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseNull(Null object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOperation(Operation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Array</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Array</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseArray(Array object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Context</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Context</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseContext(Context object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseEntry(Entry<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Typed Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Typed Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseTypedEntry(TypedEntry object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Supplier Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Supplier Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseSupplierEntry(SupplierEntry object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseFunction(Function object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseObject(org.nasdanika.ncore.Object object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Http Call</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Http Call</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseHttpCall(HttpCall object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rest Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rest Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseRestOperation(RestOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rest Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rest Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseRestFunction(RestFunction object) {
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
