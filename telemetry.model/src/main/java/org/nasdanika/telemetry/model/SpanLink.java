/**
 */
package org.nasdanika.telemetry.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Span Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.SpanLink#getTraceId <em>Trace Id</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.SpanLink#getSpanId <em>Span Id</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.SpanLink#getTraceState <em>Trace State</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.SpanLink#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.SpanLink#getDroppedAttributesCount <em>Dropped Attributes Count</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.SpanLink#getFlags <em>Flags</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.telemetry.model.ModelPackage#getSpanLink()
 * @model
 * @generated
 */
public interface SpanLink extends EObject {
	/**
	 * Returns the value of the '<em><b>Trace Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trace Id</em>' attribute.
	 * @see #setTraceId(String)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpanLink_TraceId()
	 * @model unique="false"
	 * @generated
	 */
	String getTraceId();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.SpanLink#getTraceId <em>Trace Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trace Id</em>' attribute.
	 * @see #getTraceId()
	 * @generated
	 */
	void setTraceId(String value);

	/**
	 * Returns the value of the '<em><b>Span Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Span Id</em>' attribute.
	 * @see #setSpanId(String)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpanLink_SpanId()
	 * @model unique="false"
	 * @generated
	 */
	String getSpanId();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.SpanLink#getSpanId <em>Span Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Span Id</em>' attribute.
	 * @see #getSpanId()
	 * @generated
	 */
	void setSpanId(String value);

	/**
	 * Returns the value of the '<em><b>Trace State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trace State</em>' attribute.
	 * @see #setTraceState(String)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpanLink_TraceState()
	 * @model unique="false"
	 * @generated
	 */
	String getTraceState();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.SpanLink#getTraceState <em>Trace State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trace State</em>' attribute.
	 * @see #getTraceState()
	 * @generated
	 */
	void setTraceState(String value);

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.telemetry.model.KeyValue}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpanLink_Attributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<KeyValue> getAttributes();

	/**
	 * Returns the value of the '<em><b>Dropped Attributes Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dropped Attributes Count</em>' attribute.
	 * @see #setDroppedAttributesCount(int)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpanLink_DroppedAttributesCount()
	 * @model unique="false"
	 * @generated
	 */
	int getDroppedAttributesCount();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.SpanLink#getDroppedAttributesCount <em>Dropped Attributes Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dropped Attributes Count</em>' attribute.
	 * @see #getDroppedAttributesCount()
	 * @generated
	 */
	void setDroppedAttributesCount(int value);

	/**
	 * Returns the value of the '<em><b>Flags</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Flags</em>' attribute.
	 * @see #setFlags(int)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpanLink_Flags()
	 * @model unique="false"
	 * @generated
	 */
	int getFlags();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.SpanLink#getFlags <em>Flags</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Flags</em>' attribute.
	 * @see #getFlags()
	 * @generated
	 */
	void setFlags(int value);

} // SpanLink
