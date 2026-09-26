/**
 */
package org.nasdanika.telemetry.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Span Status</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.SpanStatus#getMessage <em>Message</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.SpanStatus#getCode <em>Code</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.telemetry.model.ModelPackage#getSpanStatus()
 * @model
 * @generated
 */
public interface SpanStatus extends EObject {
	/**
	 * Returns the value of the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message</em>' attribute.
	 * @see #setMessage(String)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpanStatus_Message()
	 * @model unique="false"
	 * @generated
	 */
	String getMessage();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.SpanStatus#getMessage <em>Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message</em>' attribute.
	 * @see #getMessage()
	 * @generated
	 */
	void setMessage(String value);

	/**
	 * Returns the value of the '<em><b>Code</b></em>' attribute.
	 * The literals are from the enumeration {@link org.nasdanika.telemetry.model.StatusCode}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Code</em>' attribute.
	 * @see org.nasdanika.telemetry.model.StatusCode
	 * @see #setCode(StatusCode)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpanStatus_Code()
	 * @model unique="false"
	 * @generated
	 */
	StatusCode getCode();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.SpanStatus#getCode <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Code</em>' attribute.
	 * @see org.nasdanika.telemetry.model.StatusCode
	 * @see #getCode()
	 * @generated
	 */
	void setCode(StatusCode value);

} // SpanStatus
