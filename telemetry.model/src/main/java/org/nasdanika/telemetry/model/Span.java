/**
 */
package org.nasdanika.telemetry.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.ChangeDescription;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Span</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Span represents a single operation performed by a single component of the system.
 * The next available field id is 17.
 * <!-- end-model-doc -->
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
	 * <!-- begin-model-doc -->
	 * A unique identifier for a trace. All spans from the same trace share the same trace_id.
	 * The ID is a 16-byte array.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Trace Id</em>' attribute.
	 * @see #setTraceId(String)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_TraceId()
	 * @model
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
	 * <!-- begin-model-doc -->
	 * A unique identifier for a span within a trace, assigned when the span is created.
	 * The ID is an 8-byte array.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Span Id</em>' attribute.
	 * @see #setSpanId(String)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_SpanId()
	 * @model
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
	 * <!-- begin-model-doc -->
	 * trace_state conveys information about request position in multiple distributed tracing graphs. It is a vendor-specific format as described by the W3C Trace Context specification.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Trace State</em>' attribute.
	 * @see #setTraceState(String)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_TraceState()
	 * @model
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
	 * <!-- begin-model-doc -->
	 * The span_id of this span's parent span. If this is a root span, then this field must be empty. The ID is an 8-byte array.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Parent Span Id</em>' attribute.
	 * @see #setParentSpanId(String)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_ParentSpanId()
	 * @model
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
	 * <!-- begin-model-doc -->
	 * A description of the span's operation.
	 * For example, the name can be a qualified method name or a file name and a line number where the operation is called. A best practice is to use the same display name at the same call point repeatably, so that users can filter and group spans based on the name.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_Name()
	 * @model required="true"
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
	 * <!-- begin-model-doc -->
	 * Distinguishes between spans generated in a particular context.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see org.nasdanika.telemetry.model.SpanKind
	 * @see #setKind(SpanKind)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_Kind()
	 * @model
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
	 * <!-- begin-model-doc -->
	 * start_time_unix_nano is the start time of the span. On the client side, this is the time kept by the local machine where the span execution starts. On the server side, this is the time when the server's application handler starts running.
	 * Value is UNIX Epoch time in nanoseconds since 00:00:00 UTC on 1 January 1970.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Start Time Unix Nano</em>' attribute.
	 * @see #setStartTimeUnixNano(long)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_StartTimeUnixNano()
	 * @model
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
	 * <!-- begin-model-doc -->
	 * end_time_unix_nano is the end time of the span. On the client side, this is the time kept by the local machine where the span execution ends. On the server side, this is the time when the server application handler stops running.
	 * Value is UNIX Epoch time in nanoseconds since 00:00:00 UTC on 1 January 1970.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>End Time Unix Nano</em>' attribute.
	 * @see #setEndTimeUnixNano(long)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_EndTimeUnixNano()
	 * @model
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
	 * <!-- begin-model-doc -->
	 * attributes is a collection of key/value pairs. Note, global attributes like server name can be set using the resource API.
	 * <!-- end-model-doc -->
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
	 * @model
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
	 * <!-- begin-model-doc -->
	 * events is a collection of Event items.
	 * <!-- end-model-doc -->
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
	 * @model
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
	 * <!-- begin-model-doc -->
	 * links is a collection of Links, which are references from this span to a span in the same or different trace.
	 * <!-- end-model-doc -->
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
	 * @model
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
	 * <!-- begin-model-doc -->
	 * An optional final status for this span. Semantically when Status isn't set, it means span's status code is unset, i.e. assume STATUS_CODE_UNSET (code = 0).
	 * <!-- end-model-doc -->
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
	 * <!-- begin-model-doc -->
	 * Flags, a bit field. 8 least significant bits are the trace flags as defined in W3C Trace Context specification.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Flags</em>' attribute.
	 * @see #setFlags(int)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_Flags()
	 * @model
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
	 * @see #setChangeDescription(ChangeDescription)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpan_ChangeDescription()
	 * @model containment="true"
	 * @generated
	 */
	ChangeDescription getChangeDescription();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.Span#getChangeDescription <em>Change Description</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Change Description</em>' containment reference.
	 * @see #getChangeDescription()
	 * @generated
	 */
	void setChangeDescription(ChangeDescription value);

} // Span
