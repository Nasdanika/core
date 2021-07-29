/**
 */
package org.nasdanika.exec;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Configurator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.exec.Configurator#getTarget <em>Target</em>}</li>
 *   <li>{@link org.nasdanika.exec.Configurator#getProperties <em>Properties</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.exec.ExecPackage#getConfigurator()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/configurator.md'"
 * @generated
 */
public interface Configurator extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Component to execute with the augmented context.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target</em>' containment reference.
	 * @see #setTarget(EObject)
	 * @see org.nasdanika.exec.ExecPackage#getConfigurator_Target()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EObject getTarget();

	/**
	 * Sets the value of the '{@link org.nasdanika.exec.Configurator#getTarget <em>Target</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' containment reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(EObject value);

	/**
	 * Returns the value of the '<em><b>Properties</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.eclipse.emf.ecore.EObject},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A map injected into the instance in the ``class`` case if the instance implements java.util.function.BiConsumer or in the service or property case if they implement SupplierFactory. 
	 * If elements implement SupplierFactory then the supplier factory is used to produce value to be injected. Otherwise elements are injected AS-IS.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Properties</em>' map.
	 * @see org.nasdanika.exec.ExecPackage#getConfigurator_Properties()
	 * @model mapType="org.nasdanika.exec.Property&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EObject&gt;"
	 * @generated
	 */
	EMap<String, EObject> getProperties();

} // Configurator
