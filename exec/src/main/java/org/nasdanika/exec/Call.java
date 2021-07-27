/**
 */
package org.nasdanika.exec;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.exec.Call#getType <em>Type</em>}</li>
 *   <li>{@link org.nasdanika.exec.Call#getProperty <em>Property</em>}</li>
 *   <li>{@link org.nasdanika.exec.Call#getService <em>Service</em>}</li>
 *   <li>{@link org.nasdanika.exec.Call#getMethod <em>Method</em>}</li>
 *   <li>{@link org.nasdanika.exec.Call#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.nasdanika.exec.Call#getInit <em>Init</em>}</li>
 *   <li>{@link org.nasdanika.exec.Call#getArguments <em>Arguments</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.exec.ExecPackage#getCall()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/call.md'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='service_property_class method_arguments'"
 * @generated
 */
public interface Call extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Fully qualified class name. Mutually exclusive with ``service`` and ``property``. One of ``class``, ``property``, or ``service`` is required.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.nasdanika.exec.ExecPackage#getCall_Type()
	 * @model annotation="urn:org.nasdanika load-key='class' exclusive-with='service property' default-feature='true'"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.nasdanika.exec.Call#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Context property name. Mutually exclusive with ``class`` and ``service``. One of ``class``, ``property``, or ``service`` is required.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Property</em>' attribute.
	 * @see #setProperty(String)
	 * @see org.nasdanika.exec.ExecPackage#getCall_Property()
	 * @model annotation="urn:org.nasdanika exclusive-with='class service'"
	 * @generated
	 */
	String getProperty();

	/**
	 * Sets the value of the '{@link org.nasdanika.exec.Call#getProperty <em>Property</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property</em>' attribute.
	 * @see #getProperty()
	 * @generated
	 */
	void setProperty(String value);

	/**
	 * Returns the value of the '<em><b>Service</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Fully qualified context service class name. Mutually exclusive with ``class`` and ``property``. One of ``class``, ``property``, or ``service`` is required.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Service</em>' attribute.
	 * @see #setService(String)
	 * @see org.nasdanika.exec.ExecPackage#getCall_Service()
	 * @model annotation="urn:org.nasdanika exclusive-with='class property'"
	 * @generated
	 */
	String getService();

	/**
	 * Sets the value of the '{@link org.nasdanika.exec.Call#getService <em>Service</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service</em>' attribute.
	 * @see #getService()
	 * @generated
	 */
	void setService(String value);

	/**
	 * Returns the value of the '<em><b>Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An optional method to call. In the ``class`` case the method can be static. If the method is static the class is not instantiated and if ``init`` or ``properties`` are present it results in an exception.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Method</em>' attribute.
	 * @see #setMethod(String)
	 * @see org.nasdanika.exec.ExecPackage#getCall_Method()
	 * @model
	 * @generated
	 */
	String getMethod();

	/**
	 * Sets the value of the '{@link org.nasdanika.exec.Call#getMethod <em>Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method</em>' attribute.
	 * @see #getMethod()
	 * @generated
	 */
	void setMethod(String value);

	/**
	 * Returns the value of the '<em><b>Properties</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.eclipse.emf.ecore.EObject},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A map injected into the instance in the ``class`` case if the instance implements ${javadoc/java.util.function.BiConsumer} or in the service or property case if they implement SupplierFactory. If elements implement SupplierFactory then the supplier factory is used to produce value to be injected. Otherwise elements are injected AS-IS.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Properties</em>' map.
	 * @see org.nasdanika.exec.ExecPackage#getCall_Properties()
	 * @model mapType="org.nasdanika.exec.Property&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EObject&gt;"
	 * @generated
	 */
	EMap<String, EObject> getProperties();

	/**
	 * Returns the value of the '<em><b>Init</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An optional array of constructor arguments for the ``class``. Not applicable for ``property`` and ``service``. If elements implement SupplierFactory then the factory is used to produce argument values. Then arguments get converted to constructor parameter types if conversion is available. If conversion is not available, an exception is thrown.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Init</em>' containment reference list.
	 * @see org.nasdanika.exec.ExecPackage#getCall_Init()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika exclusive-with='service property'"
	 * @generated
	 */
	EList<EObject> getInit();

	/**
	 * Returns the value of the '<em><b>Arguments</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An optional array of method arguments. If elements implement SupplierFactory, then the factory is used to produce argument value. Then arguments get converted to method parameter types if conversion is available. If conversion is not available, an exception is thrown.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Arguments</em>' containment reference list.
	 * @see org.nasdanika.exec.ExecPackage#getCall_Arguments()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getArguments();

} // Call
