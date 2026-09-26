/**
 */
package org.nasdanika.telemetry.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Log Record</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.LogRecord#getTimeUnixNano <em>Time Unix Nano</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.LogRecord#getObservedTimeUnixNano <em>Observed Time Unix Nano</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.LogRecord#getSeverityNumber <em>Severity Number</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.LogRecord#getSeverityText <em>Severity Text</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.LogRecord#getBody <em>Body</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.LogRecord#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.LogRecord#getDroppedAttributesCount <em>Dropped Attributes Count</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.LogRecord#getFlags <em>Flags</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.LogRecord#getTraceId <em>Trace Id</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.LogRecord#getSpanId <em>Span Id</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.telemetry.model.ModelPackage#getLogRecord()
 * @model
 * @generated
 */
public interface LogRecord extends EObject {
	/**
	 * Returns the value of the '<em><b>Time Unix Nano</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Unix Nano</em>' attribute.
	 * @see #setTimeUnixNano(long)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getLogRecord_TimeUnixNano()
	 * @model unique="false"
	 * @generated
	 */
	long getTimeUnixNano();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.LogRecord#getTimeUnixNano <em>Time Unix Nano</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Unix Nano</em>' attribute.
	 * @see #getTimeUnixNano()
	 * @generated
	 */
	void setTimeUnixNano(long value);

	/**
	 * Returns the value of the '<em><b>Observed Time Unix Nano</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Observed Time Unix Nano</em>' attribute.
	 * @see #setObservedTimeUnixNano(long)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getLogRecord_ObservedTimeUnixNano()
	 * @model unique="false"
	 * @generated
	 */
	long getObservedTimeUnixNano();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.LogRecord#getObservedTimeUnixNano <em>Observed Time Unix Nano</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Observed Time Unix Nano</em>' attribute.
	 * @see #getObservedTimeUnixNano()
	 * @generated
	 */
	void setObservedTimeUnixNano(long value);

	/**
	 * Returns the value of the '<em><b>Severity Number</b></em>' attribute.
	 * The literals are from the enumeration {@link org.nasdanika.telemetry.model.SeverityNumber}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Severity Number</em>' attribute.
	 * @see org.nasdanika.telemetry.model.SeverityNumber
	 * @see #setSeverityNumber(SeverityNumber)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getLogRecord_SeverityNumber()
	 * @model unique="false"
	 * @generated
	 */
	SeverityNumber getSeverityNumber();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.LogRecord#getSeverityNumber <em>Severity Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Severity Number</em>' attribute.
	 * @see org.nasdanika.telemetry.model.SeverityNumber
	 * @see #getSeverityNumber()
	 * @generated
	 */
	void setSeverityNumber(SeverityNumber value);

	/**
	 * Returns the value of the '<em><b>Severity Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Severity Text</em>' attribute.
	 * @see #setSeverityText(String)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getLogRecord_SeverityText()
	 * @model unique="false"
	 * @generated
	 */
	String getSeverityText();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.LogRecord#getSeverityText <em>Severity Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Severity Text</em>' attribute.
	 * @see #getSeverityText()
	 * @generated
	 */
	void setSeverityText(String value);

	/**
	 * Returns the value of the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body</em>' containment reference.
	 * @see #setBody(AnyValue)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getLogRecord_Body()
	 * @model containment="true"
	 * @generated
	 */
	AnyValue getBody();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.LogRecord#getBody <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body</em>' containment reference.
	 * @see #getBody()
	 * @generated
	 */
	void setBody(AnyValue value);

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.telemetry.model.KeyValue}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see org.nasdanika.telemetry.model.ModelPackage#getLogRecord_Attributes()
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
	 * @see org.nasdanika.telemetry.model.ModelPackage#getLogRecord_DroppedAttributesCount()
	 * @model unique="false"
	 * @generated
	 */
	int getDroppedAttributesCount();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.LogRecord#getDroppedAttributesCount <em>Dropped Attributes Count</em>}' attribute.
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
	 * @see org.nasdanika.telemetry.model.ModelPackage#getLogRecord_Flags()
	 * @model unique="false"
	 * @generated
	 */
	int getFlags();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.LogRecord#getFlags <em>Flags</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Flags</em>' attribute.
	 * @see #getFlags()
	 * @generated
	 */
	void setFlags(int value);

	/**
	 * Returns the value of the '<em><b>Trace Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trace Id</em>' attribute.
	 * @see #setTraceId(String)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getLogRecord_TraceId()
	 * @model unique="false"
	 * @generated
	 */
	String getTraceId();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.LogRecord#getTraceId <em>Trace Id</em>}' attribute.
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
	 * @see org.nasdanika.telemetry.model.ModelPackage#getLogRecord_SpanId()
	 * @model unique="false"
	 * @generated
	 */
	String getSpanId();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.LogRecord#getSpanId <em>Span Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Span Id</em>' attribute.
	 * @see #getSpanId()
	 * @generated
	 */
	void setSpanId(String value);

} // LogRecord
