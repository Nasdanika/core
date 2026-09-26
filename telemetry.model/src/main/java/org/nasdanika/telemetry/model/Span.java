/**
 */
package org.nasdanika.telemetry.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Span</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.Span#getTraceId <em>Trace Id</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.Span#getSpanId <em>Span Id</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.Span#getTraceState <em>Trace State</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.Span#getParentSpanId <em>Parent Span Id</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.Span#getName <em>Name</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.Span#getKind <em>Kind</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.Span#getStartTimeUnixNano <em>Start Time Unix Nano</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.Span#getEndTimeUnixNano <em>End Time Unix Nano</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.Span#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.Span#getDroppedAttributesCount <em>Dropped Attributes Count</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.Span#getEvents <em>Events</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.Span#getDroppedEventsCount <em>Dropped Events Count</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.Span#getLinks <em>Links</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.Span#getDroppedLinksCount <em>Dropped Links Count</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.Span#getStatus <em>Status</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.Span#getFlags <em>Flags</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.Span#getChangeDescription <em>Change Description</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.Span#getLogRecords <em>Log Records</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan()
 * @model
 * @generated
 */
public interface Span extends EObject {
	/**
	 * Returns the value of the '<em><b>Trace Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trace Id</em>' attribute.
	 * @see #setTraceId(String)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_TraceId()
	 * @model unique="false"
	 * @generated
	 */
	String getTraceId();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.Span#getTraceId <em>Trace Id</em>}' attribute.
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
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_SpanId()
	 * @model unique="false"
	 * @generated
	 */
	String getSpanId();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.Span#getSpanId <em>Span Id</em>}' attribute.
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
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_TraceState()
	 * @model unique="false"
	 * @generated
	 */
	String getTraceState();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.Span#getTraceState <em>Trace State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trace State</em>' attribute.
	 * @see #getTraceState()
	 * @generated
	 */
	void setTraceState(String value);

	/**
	 * Returns the value of the '<em><b>Parent Span Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Span Id</em>' attribute.
	 * @see #setParentSpanId(String)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_ParentSpanId()
	 * @model unique="false"
	 * @generated
	 */
	String getParentSpanId();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.Span#getParentSpanId <em>Parent Span Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Span Id</em>' attribute.
	 * @see #getParentSpanId()
	 * @generated
	 */
	void setParentSpanId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_Name()
	 * @model unique="false" required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.Span#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link org.nasdanika.telemetry.model.SpanKind}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see org.nasdanika.telemetry.model.SpanKind
	 * @see #setKind(SpanKind)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_Kind()
	 * @model unique="false"
	 * @generated
	 */
	SpanKind getKind();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.Span#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see org.nasdanika.telemetry.model.SpanKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(SpanKind value);

	/**
	 * Returns the value of the '<em><b>Start Time Unix Nano</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Time Unix Nano</em>' attribute.
	 * @see #setStartTimeUnixNano(long)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_StartTimeUnixNano()
	 * @model unique="false"
	 * @generated
	 */
	long getStartTimeUnixNano();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.Span#getStartTimeUnixNano <em>Start Time Unix Nano</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Time Unix Nano</em>' attribute.
	 * @see #getStartTimeUnixNano()
	 * @generated
	 */
	void setStartTimeUnixNano(long value);

	/**
	 * Returns the value of the '<em><b>End Time Unix Nano</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End Time Unix Nano</em>' attribute.
	 * @see #setEndTimeUnixNano(long)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_EndTimeUnixNano()
	 * @model unique="false"
	 * @generated
	 */
	long getEndTimeUnixNano();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.Span#getEndTimeUnixNano <em>End Time Unix Nano</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End Time Unix Nano</em>' attribute.
	 * @see #getEndTimeUnixNano()
	 * @generated
	 */
	void setEndTimeUnixNano(long value);

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.telemetry.model.KeyValue}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_Attributes()
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
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_DroppedAttributesCount()
	 * @model unique="false"
	 * @generated
	 */
	int getDroppedAttributesCount();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.Span#getDroppedAttributesCount <em>Dropped Attributes Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dropped Attributes Count</em>' attribute.
	 * @see #getDroppedAttributesCount()
	 * @generated
	 */
	void setDroppedAttributesCount(int value);

	/**
	 * Returns the value of the '<em><b>Events</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.telemetry.model.SpanEvent}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Events</em>' containment reference list.
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_Events()
	 * @model containment="true"
	 * @generated
	 */
	EList<SpanEvent> getEvents();

	/**
	 * Returns the value of the '<em><b>Dropped Events Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dropped Events Count</em>' attribute.
	 * @see #setDroppedEventsCount(int)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_DroppedEventsCount()
	 * @model unique="false"
	 * @generated
	 */
	int getDroppedEventsCount();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.Span#getDroppedEventsCount <em>Dropped Events Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dropped Events Count</em>' attribute.
	 * @see #getDroppedEventsCount()
	 * @generated
	 */
	void setDroppedEventsCount(int value);

	/**
	 * Returns the value of the '<em><b>Links</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.telemetry.model.SpanLink}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Links</em>' containment reference list.
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_Links()
	 * @model containment="true"
	 * @generated
	 */
	EList<SpanLink> getLinks();

	/**
	 * Returns the value of the '<em><b>Dropped Links Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dropped Links Count</em>' attribute.
	 * @see #setDroppedLinksCount(int)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_DroppedLinksCount()
	 * @model unique="false"
	 * @generated
	 */
	int getDroppedLinksCount();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.Span#getDroppedLinksCount <em>Dropped Links Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dropped Links Count</em>' attribute.
	 * @see #getDroppedLinksCount()
	 * @generated
	 */
	void setDroppedLinksCount(int value);

	/**
	 * Returns the value of the '<em><b>Status</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' containment reference.
	 * @see #setStatus(SpanStatus)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_Status()
	 * @model containment="true"
	 * @generated
	 */
	SpanStatus getStatus();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.Span#getStatus <em>Status</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' containment reference.
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(SpanStatus value);

	/**
	 * Returns the value of the '<em><b>Flags</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Flags</em>' attribute.
	 * @see #setFlags(int)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_Flags()
	 * @model unique="false"
	 * @generated
	 */
	int getFlags();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.Span#getFlags <em>Flags</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Flags</em>' attribute.
	 * @see #getFlags()
	 * @generated
	 */
	void setFlags(int value);

	/**
	 * Returns the value of the '<em><b>Change Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Change Description</em>' containment reference.
	 * @see #setChangeDescription(Object)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_ChangeDescription()
	 * @model type="org.eclipse.emf.ecore.EJavaObject" containment="true"
	 * @generated
	 */
	Object getChangeDescription();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.Span#getChangeDescription <em>Change Description</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Change Description</em>' containment reference.
	 * @see #getChangeDescription()
	 * @generated
	 */
	void setChangeDescription(Object value);

	/**
	 * Returns the value of the '<em><b>Log Records</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.telemetry.model.LogRecord}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Log Records</em>' reference list.
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_LogRecords()
	 * @model
	 * @generated
	 */
	EList<LogRecord> getLogRecords();

} // Span
