/**
 */
package org.nasdanika.flow;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.Service#getTarget <em>Target</em>}</li>
 *   <li>{@link org.nasdanika.flow.Service#getTargetKey <em>Target Key</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getService()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/flow/service.md'"
 * @generated
 */
public interface Service extends FlowElement<Service> {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Activity#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Target activity of the service.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see org.nasdanika.flow.FlowPackage#getService_Target()
	 * @see org.nasdanika.flow.Activity#getServices
	 * @model opposite="services" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	Activity<?> getTarget();

	/**
	 * Returns the value of the '<em><b>Target Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Key of service target activity relative to the containing package.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target Key</em>' attribute.
	 * @see #setTargetKey(String)
	 * @see org.nasdanika.flow.FlowPackage#getService_TargetKey()
	 * @model required="true"
	 *        annotation="urn:org.nasdanika feature-key='target'"
	 * @generated
	 */
	String getTargetKey();

	/**
	 * Sets the value of the '{@link org.nasdanika.flow.Service#getTargetKey <em>Target Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Key</em>' attribute.
	 * @see #getTargetKey()
	 * @generated
	 */
	void setTargetKey(String value);

} // Service
