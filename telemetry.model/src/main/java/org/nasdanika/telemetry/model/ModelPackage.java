/**
 */
package org.nasdanika.telemetry.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * An Ecore model of OpenTelemetry concepts - traces, spans, metrics, logs, and supporting types.
 * Based on the OpenTelemetry specification: https://opentelemetry.io/docs/specs/otel/
 * <!-- end-model-doc -->
 * @see org.nasdanika.telemetry.model.ModelFactory
 * @model kind="package"
 * @generated
 */
public interface ModelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "ecore://nasdanika.org/core/telemetry/model";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.nasdanika.telemetry.model";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelPackage eINSTANCE = org.nasdanika.telemetry.model.impl.ModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.ResourceImpl <em>Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.ResourceImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getResource()
	 * @generated
	 */
	int RESOURCE = 0;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__ATTRIBUTES = 0;

	/**
	 * The feature id for the '<em><b>Dropped Attributes Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__DROPPED_ATTRIBUTES_COUNT = 1;

	/**
	 * The feature id for the '<em><b>Schema Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__SCHEMA_URL = 2;

	/**
	 * The number of structural features of the '<em>Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.InstrumentationScopeImpl <em>Instrumentation Scope</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.InstrumentationScopeImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getInstrumentationScope()
	 * @generated
	 */
	int INSTRUMENTATION_SCOPE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTATION_SCOPE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTATION_SCOPE__VERSION = 1;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTATION_SCOPE__ATTRIBUTES = 2;

	/**
	 * The feature id for the '<em><b>Dropped Attributes Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTATION_SCOPE__DROPPED_ATTRIBUTES_COUNT = 3;

	/**
	 * The feature id for the '<em><b>Schema Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTATION_SCOPE__SCHEMA_URL = 4;

	/**
	 * The number of structural features of the '<em>Instrumentation Scope</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTATION_SCOPE_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Instrumentation Scope</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTATION_SCOPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.KeyValueImpl <em>Key Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.KeyValueImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getKeyValue()
	 * @generated
	 */
	int KEY_VALUE = 2;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_VALUE__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_VALUE__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Key Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_VALUE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Key Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_VALUE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.AnyValueImpl <em>Any Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.AnyValueImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getAnyValue()
	 * @generated
	 */
	int ANY_VALUE = 3;

	/**
	 * The feature id for the '<em><b>String Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANY_VALUE__STRING_VALUE = 0;

	/**
	 * The feature id for the '<em><b>Bool Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANY_VALUE__BOOL_VALUE = 1;

	/**
	 * The feature id for the '<em><b>Int Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANY_VALUE__INT_VALUE = 2;

	/**
	 * The feature id for the '<em><b>Double Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANY_VALUE__DOUBLE_VALUE = 3;

	/**
	 * The feature id for the '<em><b>Array Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANY_VALUE__ARRAY_VALUE = 4;

	/**
	 * The feature id for the '<em><b>Kvlist Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANY_VALUE__KVLIST_VALUE = 5;

	/**
	 * The feature id for the '<em><b>Bytes Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANY_VALUE__BYTES_VALUE = 6;

	/**
	 * The number of structural features of the '<em>Any Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANY_VALUE_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>Any Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANY_VALUE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.TracesDataImpl <em>Traces Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.TracesDataImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getTracesData()
	 * @generated
	 */
	int TRACES_DATA = 4;

	/**
	 * The feature id for the '<em><b>Resource Spans</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACES_DATA__RESOURCE_SPANS = 0;

	/**
	 * The number of structural features of the '<em>Traces Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACES_DATA_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Traces Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACES_DATA_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.ResourceSpansImpl <em>Resource Spans</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.ResourceSpansImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getResourceSpans()
	 * @generated
	 */
	int RESOURCE_SPANS = 5;

	/**
	 * The feature id for the '<em><b>Resource</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SPANS__RESOURCE = 0;

	/**
	 * The feature id for the '<em><b>Scope Spans</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SPANS__SCOPE_SPANS = 1;

	/**
	 * The feature id for the '<em><b>Schema Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SPANS__SCHEMA_URL = 2;

	/**
	 * The number of structural features of the '<em>Resource Spans</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SPANS_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Resource Spans</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SPANS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.ScopeSpansImpl <em>Scope Spans</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.ScopeSpansImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getScopeSpans()
	 * @generated
	 */
	int SCOPE_SPANS = 6;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_SPANS__SCOPE = 0;

	/**
	 * The feature id for the '<em><b>Spans</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_SPANS__SPANS = 1;

	/**
	 * The feature id for the '<em><b>Schema Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_SPANS__SCHEMA_URL = 2;

	/**
	 * The number of structural features of the '<em>Scope Spans</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_SPANS_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Scope Spans</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_SPANS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.SpanImpl <em>Span</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.SpanImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getSpan()
	 * @generated
	 */
	int SPAN = 7;

	/**
	 * The feature id for the '<em><b>Trace Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN__TRACE_ID = 0;

	/**
	 * The feature id for the '<em><b>Span Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN__SPAN_ID = 1;

	/**
	 * The feature id for the '<em><b>Trace State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN__TRACE_STATE = 2;

	/**
	 * The feature id for the '<em><b>Parent Span Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN__PARENT_SPAN_ID = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN__NAME = 4;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN__KIND = 5;

	/**
	 * The feature id for the '<em><b>Start Time Unix Nano</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN__START_TIME_UNIX_NANO = 6;

	/**
	 * The feature id for the '<em><b>End Time Unix Nano</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN__END_TIME_UNIX_NANO = 7;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN__ATTRIBUTES = 8;

	/**
	 * The feature id for the '<em><b>Dropped Attributes Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN__DROPPED_ATTRIBUTES_COUNT = 9;

	/**
	 * The feature id for the '<em><b>Events</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN__EVENTS = 10;

	/**
	 * The feature id for the '<em><b>Dropped Events Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN__DROPPED_EVENTS_COUNT = 11;

	/**
	 * The feature id for the '<em><b>Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN__LINKS = 12;

	/**
	 * The feature id for the '<em><b>Dropped Links Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN__DROPPED_LINKS_COUNT = 13;

	/**
	 * The feature id for the '<em><b>Status</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN__STATUS = 14;

	/**
	 * The feature id for the '<em><b>Flags</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN__FLAGS = 15;

	/**
	 * The feature id for the '<em><b>Change Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN__CHANGE_DESCRIPTION = 16;

	/**
	 * The number of structural features of the '<em>Span</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN_FEATURE_COUNT = 17;

	/**
	 * The number of operations of the '<em>Span</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.SpanEventImpl <em>Span Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.SpanEventImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getSpanEvent()
	 * @generated
	 */
	int SPAN_EVENT = 8;

	/**
	 * The feature id for the '<em><b>Time Unix Nano</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN_EVENT__TIME_UNIX_NANO = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN_EVENT__NAME = 1;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN_EVENT__ATTRIBUTES = 2;

	/**
	 * The feature id for the '<em><b>Dropped Attributes Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN_EVENT__DROPPED_ATTRIBUTES_COUNT = 3;

	/**
	 * The number of structural features of the '<em>Span Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN_EVENT_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Span Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN_EVENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.SpanLinkImpl <em>Span Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.SpanLinkImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getSpanLink()
	 * @generated
	 */
	int SPAN_LINK = 9;

	/**
	 * The feature id for the '<em><b>Trace Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN_LINK__TRACE_ID = 0;

	/**
	 * The feature id for the '<em><b>Span Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN_LINK__SPAN_ID = 1;

	/**
	 * The feature id for the '<em><b>Trace State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN_LINK__TRACE_STATE = 2;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN_LINK__ATTRIBUTES = 3;

	/**
	 * The feature id for the '<em><b>Dropped Attributes Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN_LINK__DROPPED_ATTRIBUTES_COUNT = 4;

	/**
	 * The feature id for the '<em><b>Flags</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN_LINK__FLAGS = 5;

	/**
	 * The number of structural features of the '<em>Span Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN_LINK_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Span Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN_LINK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.SpanStatusImpl <em>Span Status</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.SpanStatusImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getSpanStatus()
	 * @generated
	 */
	int SPAN_STATUS = 10;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN_STATUS__MESSAGE = 0;

	/**
	 * The feature id for the '<em><b>Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN_STATUS__CODE = 1;

	/**
	 * The number of structural features of the '<em>Span Status</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN_STATUS_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Span Status</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPAN_STATUS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.MetricsDataImpl <em>Metrics Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.MetricsDataImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getMetricsData()
	 * @generated
	 */
	int METRICS_DATA = 11;

	/**
	 * The feature id for the '<em><b>Resource Metrics</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRICS_DATA__RESOURCE_METRICS = 0;

	/**
	 * The number of structural features of the '<em>Metrics Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRICS_DATA_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Metrics Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRICS_DATA_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.ResourceMetricsImpl <em>Resource Metrics</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.ResourceMetricsImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getResourceMetrics()
	 * @generated
	 */
	int RESOURCE_METRICS = 12;

	/**
	 * The feature id for the '<em><b>Resource</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_METRICS__RESOURCE = 0;

	/**
	 * The feature id for the '<em><b>Scope Metrics</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_METRICS__SCOPE_METRICS = 1;

	/**
	 * The feature id for the '<em><b>Schema Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_METRICS__SCHEMA_URL = 2;

	/**
	 * The number of structural features of the '<em>Resource Metrics</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_METRICS_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Resource Metrics</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_METRICS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.ScopeMetricsImpl <em>Scope Metrics</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.ScopeMetricsImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getScopeMetrics()
	 * @generated
	 */
	int SCOPE_METRICS = 13;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_METRICS__SCOPE = 0;

	/**
	 * The feature id for the '<em><b>Metrics</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_METRICS__METRICS = 1;

	/**
	 * The feature id for the '<em><b>Schema Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_METRICS__SCHEMA_URL = 2;

	/**
	 * The number of structural features of the '<em>Scope Metrics</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_METRICS_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Scope Metrics</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_METRICS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.MetricImpl <em>Metric</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.MetricImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getMetric()
	 * @generated
	 */
	int METRIC = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__NAME = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__UNIT = 2;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__METADATA = 3;

	/**
	 * The number of structural features of the '<em>Metric</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Metric</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.GaugeImpl <em>Gauge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.GaugeImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getGauge()
	 * @generated
	 */
	int GAUGE = 15;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAUGE__NAME = METRIC__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAUGE__DESCRIPTION = METRIC__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAUGE__UNIT = METRIC__UNIT;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAUGE__METADATA = METRIC__METADATA;

	/**
	 * The feature id for the '<em><b>Data Points</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAUGE__DATA_POINTS = METRIC_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Gauge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAUGE_FEATURE_COUNT = METRIC_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Gauge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAUGE_OPERATION_COUNT = METRIC_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.SumImpl <em>Sum</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.SumImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getSum()
	 * @generated
	 */
	int SUM = 16;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUM__NAME = METRIC__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUM__DESCRIPTION = METRIC__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUM__UNIT = METRIC__UNIT;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUM__METADATA = METRIC__METADATA;

	/**
	 * The feature id for the '<em><b>Data Points</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUM__DATA_POINTS = METRIC_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Aggregation Temporality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUM__AGGREGATION_TEMPORALITY = METRIC_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Is Monotonic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUM__IS_MONOTONIC = METRIC_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Sum</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUM_FEATURE_COUNT = METRIC_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Sum</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUM_OPERATION_COUNT = METRIC_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.HistogramImpl <em>Histogram</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.HistogramImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getHistogram()
	 * @generated
	 */
	int HISTOGRAM = 17;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTOGRAM__NAME = METRIC__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTOGRAM__DESCRIPTION = METRIC__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTOGRAM__UNIT = METRIC__UNIT;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTOGRAM__METADATA = METRIC__METADATA;

	/**
	 * The feature id for the '<em><b>Data Points</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTOGRAM__DATA_POINTS = METRIC_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Aggregation Temporality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTOGRAM__AGGREGATION_TEMPORALITY = METRIC_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Histogram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTOGRAM_FEATURE_COUNT = METRIC_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Histogram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTOGRAM_OPERATION_COUNT = METRIC_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.ExponentialHistogramImpl <em>Exponential Histogram</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.ExponentialHistogramImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getExponentialHistogram()
	 * @generated
	 */
	int EXPONENTIAL_HISTOGRAM = 18;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM__NAME = METRIC__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM__DESCRIPTION = METRIC__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM__UNIT = METRIC__UNIT;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM__METADATA = METRIC__METADATA;

	/**
	 * The feature id for the '<em><b>Data Points</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM__DATA_POINTS = METRIC_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Aggregation Temporality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM__AGGREGATION_TEMPORALITY = METRIC_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Exponential Histogram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM_FEATURE_COUNT = METRIC_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Exponential Histogram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM_OPERATION_COUNT = METRIC_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.SummaryImpl <em>Summary</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.SummaryImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getSummary()
	 * @generated
	 */
	int SUMMARY = 19;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUMMARY__NAME = METRIC__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUMMARY__DESCRIPTION = METRIC__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUMMARY__UNIT = METRIC__UNIT;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUMMARY__METADATA = METRIC__METADATA;

	/**
	 * The feature id for the '<em><b>Data Points</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUMMARY__DATA_POINTS = METRIC_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Summary</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUMMARY_FEATURE_COUNT = METRIC_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Summary</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUMMARY_OPERATION_COUNT = METRIC_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.NumberDataPointImpl <em>Number Data Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.NumberDataPointImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getNumberDataPoint()
	 * @generated
	 */
	int NUMBER_DATA_POINT = 20;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_DATA_POINT__ATTRIBUTES = 0;

	/**
	 * The feature id for the '<em><b>Start Time Unix Nano</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_DATA_POINT__START_TIME_UNIX_NANO = 1;

	/**
	 * The feature id for the '<em><b>Time Unix Nano</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_DATA_POINT__TIME_UNIX_NANO = 2;

	/**
	 * The feature id for the '<em><b>As Double</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_DATA_POINT__AS_DOUBLE = 3;

	/**
	 * The feature id for the '<em><b>As Int</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_DATA_POINT__AS_INT = 4;

	/**
	 * The feature id for the '<em><b>Exemplars</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_DATA_POINT__EXEMPLARS = 5;

	/**
	 * The feature id for the '<em><b>Flags</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_DATA_POINT__FLAGS = 6;

	/**
	 * The number of structural features of the '<em>Number Data Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_DATA_POINT_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>Number Data Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_DATA_POINT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.HistogramDataPointImpl <em>Histogram Data Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.HistogramDataPointImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getHistogramDataPoint()
	 * @generated
	 */
	int HISTOGRAM_DATA_POINT = 21;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTOGRAM_DATA_POINT__ATTRIBUTES = 0;

	/**
	 * The feature id for the '<em><b>Start Time Unix Nano</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTOGRAM_DATA_POINT__START_TIME_UNIX_NANO = 1;

	/**
	 * The feature id for the '<em><b>Time Unix Nano</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTOGRAM_DATA_POINT__TIME_UNIX_NANO = 2;

	/**
	 * The feature id for the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTOGRAM_DATA_POINT__COUNT = 3;

	/**
	 * The feature id for the '<em><b>Sum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTOGRAM_DATA_POINT__SUM = 4;

	/**
	 * The feature id for the '<em><b>Bucket Counts</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTOGRAM_DATA_POINT__BUCKET_COUNTS = 5;

	/**
	 * The feature id for the '<em><b>Explicit Bounds</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTOGRAM_DATA_POINT__EXPLICIT_BOUNDS = 6;

	/**
	 * The feature id for the '<em><b>Exemplars</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTOGRAM_DATA_POINT__EXEMPLARS = 7;

	/**
	 * The feature id for the '<em><b>Flags</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTOGRAM_DATA_POINT__FLAGS = 8;

	/**
	 * The feature id for the '<em><b>Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTOGRAM_DATA_POINT__MIN = 9;

	/**
	 * The feature id for the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTOGRAM_DATA_POINT__MAX = 10;

	/**
	 * The number of structural features of the '<em>Histogram Data Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTOGRAM_DATA_POINT_FEATURE_COUNT = 11;

	/**
	 * The number of operations of the '<em>Histogram Data Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTOGRAM_DATA_POINT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.ExponentialHistogramDataPointImpl <em>Exponential Histogram Data Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.ExponentialHistogramDataPointImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getExponentialHistogramDataPoint()
	 * @generated
	 */
	int EXPONENTIAL_HISTOGRAM_DATA_POINT = 22;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM_DATA_POINT__ATTRIBUTES = 0;

	/**
	 * The feature id for the '<em><b>Start Time Unix Nano</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM_DATA_POINT__START_TIME_UNIX_NANO = 1;

	/**
	 * The feature id for the '<em><b>Time Unix Nano</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM_DATA_POINT__TIME_UNIX_NANO = 2;

	/**
	 * The feature id for the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM_DATA_POINT__COUNT = 3;

	/**
	 * The feature id for the '<em><b>Sum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM_DATA_POINT__SUM = 4;

	/**
	 * The feature id for the '<em><b>Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM_DATA_POINT__SCALE = 5;

	/**
	 * The feature id for the '<em><b>Zero Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM_DATA_POINT__ZERO_COUNT = 6;

	/**
	 * The feature id for the '<em><b>Positive</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM_DATA_POINT__POSITIVE = 7;

	/**
	 * The feature id for the '<em><b>Negative</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM_DATA_POINT__NEGATIVE = 8;

	/**
	 * The feature id for the '<em><b>Flags</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM_DATA_POINT__FLAGS = 9;

	/**
	 * The feature id for the '<em><b>Exemplars</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM_DATA_POINT__EXEMPLARS = 10;

	/**
	 * The feature id for the '<em><b>Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM_DATA_POINT__MIN = 11;

	/**
	 * The feature id for the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM_DATA_POINT__MAX = 12;

	/**
	 * The feature id for the '<em><b>Zero Threshold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM_DATA_POINT__ZERO_THRESHOLD = 13;

	/**
	 * The number of structural features of the '<em>Exponential Histogram Data Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM_DATA_POINT_FEATURE_COUNT = 14;

	/**
	 * The number of operations of the '<em>Exponential Histogram Data Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM_DATA_POINT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.ExponentialHistogramDataPointBucketsImpl <em>Exponential Histogram Data Point Buckets</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.ExponentialHistogramDataPointBucketsImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getExponentialHistogramDataPointBuckets()
	 * @generated
	 */
	int EXPONENTIAL_HISTOGRAM_DATA_POINT_BUCKETS = 23;

	/**
	 * The feature id for the '<em><b>Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM_DATA_POINT_BUCKETS__OFFSET = 0;

	/**
	 * The feature id for the '<em><b>Bucket Counts</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM_DATA_POINT_BUCKETS__BUCKET_COUNTS = 1;

	/**
	 * The number of structural features of the '<em>Exponential Histogram Data Point Buckets</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM_DATA_POINT_BUCKETS_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Exponential Histogram Data Point Buckets</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_HISTOGRAM_DATA_POINT_BUCKETS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.SummaryDataPointImpl <em>Summary Data Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.SummaryDataPointImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getSummaryDataPoint()
	 * @generated
	 */
	int SUMMARY_DATA_POINT = 24;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUMMARY_DATA_POINT__ATTRIBUTES = 0;

	/**
	 * The feature id for the '<em><b>Start Time Unix Nano</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUMMARY_DATA_POINT__START_TIME_UNIX_NANO = 1;

	/**
	 * The feature id for the '<em><b>Time Unix Nano</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUMMARY_DATA_POINT__TIME_UNIX_NANO = 2;

	/**
	 * The feature id for the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUMMARY_DATA_POINT__COUNT = 3;

	/**
	 * The feature id for the '<em><b>Sum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUMMARY_DATA_POINT__SUM = 4;

	/**
	 * The feature id for the '<em><b>Quantile Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUMMARY_DATA_POINT__QUANTILE_VALUES = 5;

	/**
	 * The feature id for the '<em><b>Flags</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUMMARY_DATA_POINT__FLAGS = 6;

	/**
	 * The number of structural features of the '<em>Summary Data Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUMMARY_DATA_POINT_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>Summary Data Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUMMARY_DATA_POINT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.SummaryDataPointValueAtQuantileImpl <em>Summary Data Point Value At Quantile</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.SummaryDataPointValueAtQuantileImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getSummaryDataPointValueAtQuantile()
	 * @generated
	 */
	int SUMMARY_DATA_POINT_VALUE_AT_QUANTILE = 25;

	/**
	 * The feature id for the '<em><b>Quantile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUMMARY_DATA_POINT_VALUE_AT_QUANTILE__QUANTILE = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUMMARY_DATA_POINT_VALUE_AT_QUANTILE__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Summary Data Point Value At Quantile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUMMARY_DATA_POINT_VALUE_AT_QUANTILE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Summary Data Point Value At Quantile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUMMARY_DATA_POINT_VALUE_AT_QUANTILE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.ExemplarImpl <em>Exemplar</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.ExemplarImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getExemplar()
	 * @generated
	 */
	int EXEMPLAR = 26;

	/**
	 * The feature id for the '<em><b>Filtered Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEMPLAR__FILTERED_ATTRIBUTES = 0;

	/**
	 * The feature id for the '<em><b>Time Unix Nano</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEMPLAR__TIME_UNIX_NANO = 1;

	/**
	 * The feature id for the '<em><b>As Double</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEMPLAR__AS_DOUBLE = 2;

	/**
	 * The feature id for the '<em><b>As Int</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEMPLAR__AS_INT = 3;

	/**
	 * The feature id for the '<em><b>Span Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEMPLAR__SPAN_ID = 4;

	/**
	 * The feature id for the '<em><b>Trace Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEMPLAR__TRACE_ID = 5;

	/**
	 * The number of structural features of the '<em>Exemplar</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEMPLAR_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Exemplar</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEMPLAR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.LogsDataImpl <em>Logs Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.LogsDataImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getLogsData()
	 * @generated
	 */
	int LOGS_DATA = 27;

	/**
	 * The feature id for the '<em><b>Resource Logs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGS_DATA__RESOURCE_LOGS = 0;

	/**
	 * The number of structural features of the '<em>Logs Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGS_DATA_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Logs Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGS_DATA_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.ResourceLogsImpl <em>Resource Logs</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.ResourceLogsImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getResourceLogs()
	 * @generated
	 */
	int RESOURCE_LOGS = 28;

	/**
	 * The feature id for the '<em><b>Resource</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOGS__RESOURCE = 0;

	/**
	 * The feature id for the '<em><b>Scope Logs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOGS__SCOPE_LOGS = 1;

	/**
	 * The feature id for the '<em><b>Schema Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOGS__SCHEMA_URL = 2;

	/**
	 * The number of structural features of the '<em>Resource Logs</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOGS_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Resource Logs</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOGS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.ScopeLogsImpl <em>Scope Logs</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.ScopeLogsImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getScopeLogs()
	 * @generated
	 */
	int SCOPE_LOGS = 29;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_LOGS__SCOPE = 0;

	/**
	 * The feature id for the '<em><b>Log Records</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_LOGS__LOG_RECORDS = 1;

	/**
	 * The feature id for the '<em><b>Schema Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_LOGS__SCHEMA_URL = 2;

	/**
	 * The number of structural features of the '<em>Scope Logs</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_LOGS_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Scope Logs</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_LOGS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.impl.LogRecordImpl <em>Log Record</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.impl.LogRecordImpl
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getLogRecord()
	 * @generated
	 */
	int LOG_RECORD = 30;

	/**
	 * The feature id for the '<em><b>Time Unix Nano</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOG_RECORD__TIME_UNIX_NANO = 0;

	/**
	 * The feature id for the '<em><b>Observed Time Unix Nano</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOG_RECORD__OBSERVED_TIME_UNIX_NANO = 1;

	/**
	 * The feature id for the '<em><b>Severity Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOG_RECORD__SEVERITY_NUMBER = 2;

	/**
	 * The feature id for the '<em><b>Severity Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOG_RECORD__SEVERITY_TEXT = 3;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOG_RECORD__BODY = 4;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOG_RECORD__ATTRIBUTES = 5;

	/**
	 * The feature id for the '<em><b>Dropped Attributes Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOG_RECORD__DROPPED_ATTRIBUTES_COUNT = 6;

	/**
	 * The feature id for the '<em><b>Flags</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOG_RECORD__FLAGS = 7;

	/**
	 * The feature id for the '<em><b>Trace Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOG_RECORD__TRACE_ID = 8;

	/**
	 * The feature id for the '<em><b>Span Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOG_RECORD__SPAN_ID = 9;

	/**
	 * The number of structural features of the '<em>Log Record</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOG_RECORD_FEATURE_COUNT = 10;

	/**
	 * The number of operations of the '<em>Log Record</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOG_RECORD_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.SpanKind <em>Span Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.SpanKind
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getSpanKind()
	 * @generated
	 */
	int SPAN_KIND = 31;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.StatusCode <em>Status Code</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.StatusCode
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getStatusCode()
	 * @generated
	 */
	int STATUS_CODE = 32;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.AggregationTemporality <em>Aggregation Temporality</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.AggregationTemporality
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getAggregationTemporality()
	 * @generated
	 */
	int AGGREGATION_TEMPORALITY = 33;

	/**
	 * The meta object id for the '{@link org.nasdanika.telemetry.model.SeverityNumber <em>Severity Number</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.telemetry.model.SeverityNumber
	 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getSeverityNumber()
	 * @generated
	 */
	int SEVERITY_NUMBER = 34;


	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.Resource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource</em>'.
	 * @see org.nasdanika.telemetry.model.Resource
	 * @generated
	 */
	EClass getResource();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.Resource#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see org.nasdanika.telemetry.model.Resource#getAttributes()
	 * @see #getResource()
	 * @generated
	 */
	EReference getResource_Attributes();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.Resource#getDroppedAttributesCount <em>Dropped Attributes Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dropped Attributes Count</em>'.
	 * @see org.nasdanika.telemetry.model.Resource#getDroppedAttributesCount()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_DroppedAttributesCount();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.Resource#getSchemaUrl <em>Schema Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Schema Url</em>'.
	 * @see org.nasdanika.telemetry.model.Resource#getSchemaUrl()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_SchemaUrl();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.InstrumentationScope <em>Instrumentation Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instrumentation Scope</em>'.
	 * @see org.nasdanika.telemetry.model.InstrumentationScope
	 * @generated
	 */
	EClass getInstrumentationScope();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.InstrumentationScope#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.nasdanika.telemetry.model.InstrumentationScope#getName()
	 * @see #getInstrumentationScope()
	 * @generated
	 */
	EAttribute getInstrumentationScope_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.InstrumentationScope#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.nasdanika.telemetry.model.InstrumentationScope#getVersion()
	 * @see #getInstrumentationScope()
	 * @generated
	 */
	EAttribute getInstrumentationScope_Version();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.InstrumentationScope#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see org.nasdanika.telemetry.model.InstrumentationScope#getAttributes()
	 * @see #getInstrumentationScope()
	 * @generated
	 */
	EReference getInstrumentationScope_Attributes();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.InstrumentationScope#getDroppedAttributesCount <em>Dropped Attributes Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dropped Attributes Count</em>'.
	 * @see org.nasdanika.telemetry.model.InstrumentationScope#getDroppedAttributesCount()
	 * @see #getInstrumentationScope()
	 * @generated
	 */
	EAttribute getInstrumentationScope_DroppedAttributesCount();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.InstrumentationScope#getSchemaUrl <em>Schema Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Schema Url</em>'.
	 * @see org.nasdanika.telemetry.model.InstrumentationScope#getSchemaUrl()
	 * @see #getInstrumentationScope()
	 * @generated
	 */
	EAttribute getInstrumentationScope_SchemaUrl();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.KeyValue <em>Key Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Key Value</em>'.
	 * @see org.nasdanika.telemetry.model.KeyValue
	 * @generated
	 */
	EClass getKeyValue();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.KeyValue#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see org.nasdanika.telemetry.model.KeyValue#getKey()
	 * @see #getKeyValue()
	 * @generated
	 */
	EAttribute getKeyValue_Key();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.telemetry.model.KeyValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see org.nasdanika.telemetry.model.KeyValue#getValue()
	 * @see #getKeyValue()
	 * @generated
	 */
	EReference getKeyValue_Value();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.AnyValue <em>Any Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Any Value</em>'.
	 * @see org.nasdanika.telemetry.model.AnyValue
	 * @generated
	 */
	EClass getAnyValue();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.AnyValue#getStringValue <em>String Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>String Value</em>'.
	 * @see org.nasdanika.telemetry.model.AnyValue#getStringValue()
	 * @see #getAnyValue()
	 * @generated
	 */
	EAttribute getAnyValue_StringValue();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.AnyValue#getBoolValue <em>Bool Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bool Value</em>'.
	 * @see org.nasdanika.telemetry.model.AnyValue#getBoolValue()
	 * @see #getAnyValue()
	 * @generated
	 */
	EAttribute getAnyValue_BoolValue();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.AnyValue#getIntValue <em>Int Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Int Value</em>'.
	 * @see org.nasdanika.telemetry.model.AnyValue#getIntValue()
	 * @see #getAnyValue()
	 * @generated
	 */
	EAttribute getAnyValue_IntValue();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.AnyValue#getDoubleValue <em>Double Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Double Value</em>'.
	 * @see org.nasdanika.telemetry.model.AnyValue#getDoubleValue()
	 * @see #getAnyValue()
	 * @generated
	 */
	EAttribute getAnyValue_DoubleValue();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.AnyValue#getArrayValue <em>Array Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Array Value</em>'.
	 * @see org.nasdanika.telemetry.model.AnyValue#getArrayValue()
	 * @see #getAnyValue()
	 * @generated
	 */
	EReference getAnyValue_ArrayValue();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.AnyValue#getKvlistValue <em>Kvlist Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Kvlist Value</em>'.
	 * @see org.nasdanika.telemetry.model.AnyValue#getKvlistValue()
	 * @see #getAnyValue()
	 * @generated
	 */
	EReference getAnyValue_KvlistValue();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.AnyValue#getBytesValue <em>Bytes Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bytes Value</em>'.
	 * @see org.nasdanika.telemetry.model.AnyValue#getBytesValue()
	 * @see #getAnyValue()
	 * @generated
	 */
	EAttribute getAnyValue_BytesValue();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.TracesData <em>Traces Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Traces Data</em>'.
	 * @see org.nasdanika.telemetry.model.TracesData
	 * @generated
	 */
	EClass getTracesData();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.TracesData#getResourceSpans <em>Resource Spans</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Spans</em>'.
	 * @see org.nasdanika.telemetry.model.TracesData#getResourceSpans()
	 * @see #getTracesData()
	 * @generated
	 */
	EReference getTracesData_ResourceSpans();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.ResourceSpans <em>Resource Spans</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Spans</em>'.
	 * @see org.nasdanika.telemetry.model.ResourceSpans
	 * @generated
	 */
	EClass getResourceSpans();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.telemetry.model.ResourceSpans#getResource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Resource</em>'.
	 * @see org.nasdanika.telemetry.model.ResourceSpans#getResource()
	 * @see #getResourceSpans()
	 * @generated
	 */
	EReference getResourceSpans_Resource();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.ResourceSpans#getScopeSpans <em>Scope Spans</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Scope Spans</em>'.
	 * @see org.nasdanika.telemetry.model.ResourceSpans#getScopeSpans()
	 * @see #getResourceSpans()
	 * @generated
	 */
	EReference getResourceSpans_ScopeSpans();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.ResourceSpans#getSchemaUrl <em>Schema Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Schema Url</em>'.
	 * @see org.nasdanika.telemetry.model.ResourceSpans#getSchemaUrl()
	 * @see #getResourceSpans()
	 * @generated
	 */
	EAttribute getResourceSpans_SchemaUrl();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.ScopeSpans <em>Scope Spans</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scope Spans</em>'.
	 * @see org.nasdanika.telemetry.model.ScopeSpans
	 * @generated
	 */
	EClass getScopeSpans();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.telemetry.model.ScopeSpans#getScope <em>Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Scope</em>'.
	 * @see org.nasdanika.telemetry.model.ScopeSpans#getScope()
	 * @see #getScopeSpans()
	 * @generated
	 */
	EReference getScopeSpans_Scope();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.ScopeSpans#getSpans <em>Spans</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Spans</em>'.
	 * @see org.nasdanika.telemetry.model.ScopeSpans#getSpans()
	 * @see #getScopeSpans()
	 * @generated
	 */
	EReference getScopeSpans_Spans();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.ScopeSpans#getSchemaUrl <em>Schema Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Schema Url</em>'.
	 * @see org.nasdanika.telemetry.model.ScopeSpans#getSchemaUrl()
	 * @see #getScopeSpans()
	 * @generated
	 */
	EAttribute getScopeSpans_SchemaUrl();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.Span <em>Span</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Span</em>'.
	 * @see org.nasdanika.telemetry.model.Span
	 * @generated
	 */
	EClass getSpan();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.Span#getTraceId <em>Trace Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trace Id</em>'.
	 * @see org.nasdanika.telemetry.model.Span#getTraceId()
	 * @see #getSpan()
	 * @generated
	 */
	EAttribute getSpan_TraceId();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.Span#getSpanId <em>Span Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Span Id</em>'.
	 * @see org.nasdanika.telemetry.model.Span#getSpanId()
	 * @see #getSpan()
	 * @generated
	 */
	EAttribute getSpan_SpanId();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.Span#getTraceState <em>Trace State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trace State</em>'.
	 * @see org.nasdanika.telemetry.model.Span#getTraceState()
	 * @see #getSpan()
	 * @generated
	 */
	EAttribute getSpan_TraceState();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.Span#getParentSpanId <em>Parent Span Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parent Span Id</em>'.
	 * @see org.nasdanika.telemetry.model.Span#getParentSpanId()
	 * @see #getSpan()
	 * @generated
	 */
	EAttribute getSpan_ParentSpanId();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.Span#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.nasdanika.telemetry.model.Span#getName()
	 * @see #getSpan()
	 * @generated
	 */
	EAttribute getSpan_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.Span#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.nasdanika.telemetry.model.Span#getKind()
	 * @see #getSpan()
	 * @generated
	 */
	EAttribute getSpan_Kind();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.Span#getStartTimeUnixNano <em>Start Time Unix Nano</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Time Unix Nano</em>'.
	 * @see org.nasdanika.telemetry.model.Span#getStartTimeUnixNano()
	 * @see #getSpan()
	 * @generated
	 */
	EAttribute getSpan_StartTimeUnixNano();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.Span#getEndTimeUnixNano <em>End Time Unix Nano</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>End Time Unix Nano</em>'.
	 * @see org.nasdanika.telemetry.model.Span#getEndTimeUnixNano()
	 * @see #getSpan()
	 * @generated
	 */
	EAttribute getSpan_EndTimeUnixNano();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.Span#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see org.nasdanika.telemetry.model.Span#getAttributes()
	 * @see #getSpan()
	 * @generated
	 */
	EReference getSpan_Attributes();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.Span#getDroppedAttributesCount <em>Dropped Attributes Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dropped Attributes Count</em>'.
	 * @see org.nasdanika.telemetry.model.Span#getDroppedAttributesCount()
	 * @see #getSpan()
	 * @generated
	 */
	EAttribute getSpan_DroppedAttributesCount();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.Span#getEvents <em>Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Events</em>'.
	 * @see org.nasdanika.telemetry.model.Span#getEvents()
	 * @see #getSpan()
	 * @generated
	 */
	EReference getSpan_Events();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.Span#getDroppedEventsCount <em>Dropped Events Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dropped Events Count</em>'.
	 * @see org.nasdanika.telemetry.model.Span#getDroppedEventsCount()
	 * @see #getSpan()
	 * @generated
	 */
	EAttribute getSpan_DroppedEventsCount();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.Span#getLinks <em>Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Links</em>'.
	 * @see org.nasdanika.telemetry.model.Span#getLinks()
	 * @see #getSpan()
	 * @generated
	 */
	EReference getSpan_Links();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.Span#getDroppedLinksCount <em>Dropped Links Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dropped Links Count</em>'.
	 * @see org.nasdanika.telemetry.model.Span#getDroppedLinksCount()
	 * @see #getSpan()
	 * @generated
	 */
	EAttribute getSpan_DroppedLinksCount();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.telemetry.model.Span#getStatus <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Status</em>'.
	 * @see org.nasdanika.telemetry.model.Span#getStatus()
	 * @see #getSpan()
	 * @generated
	 */
	EReference getSpan_Status();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.Span#getFlags <em>Flags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Flags</em>'.
	 * @see org.nasdanika.telemetry.model.Span#getFlags()
	 * @see #getSpan()
	 * @generated
	 */
	EAttribute getSpan_Flags();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.telemetry.model.Span#getChangeDescription <em>Change Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Change Description</em>'.
	 * @see org.nasdanika.telemetry.model.Span#getChangeDescription()
	 * @see #getSpan()
	 * @generated
	 */
	EReference getSpan_ChangeDescription();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.SpanEvent <em>Span Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Span Event</em>'.
	 * @see org.nasdanika.telemetry.model.SpanEvent
	 * @generated
	 */
	EClass getSpanEvent();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.SpanEvent#getTimeUnixNano <em>Time Unix Nano</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Unix Nano</em>'.
	 * @see org.nasdanika.telemetry.model.SpanEvent#getTimeUnixNano()
	 * @see #getSpanEvent()
	 * @generated
	 */
	EAttribute getSpanEvent_TimeUnixNano();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.SpanEvent#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.nasdanika.telemetry.model.SpanEvent#getName()
	 * @see #getSpanEvent()
	 * @generated
	 */
	EAttribute getSpanEvent_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.SpanEvent#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see org.nasdanika.telemetry.model.SpanEvent#getAttributes()
	 * @see #getSpanEvent()
	 * @generated
	 */
	EReference getSpanEvent_Attributes();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.SpanEvent#getDroppedAttributesCount <em>Dropped Attributes Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dropped Attributes Count</em>'.
	 * @see org.nasdanika.telemetry.model.SpanEvent#getDroppedAttributesCount()
	 * @see #getSpanEvent()
	 * @generated
	 */
	EAttribute getSpanEvent_DroppedAttributesCount();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.SpanLink <em>Span Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Span Link</em>'.
	 * @see org.nasdanika.telemetry.model.SpanLink
	 * @generated
	 */
	EClass getSpanLink();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.SpanLink#getTraceId <em>Trace Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trace Id</em>'.
	 * @see org.nasdanika.telemetry.model.SpanLink#getTraceId()
	 * @see #getSpanLink()
	 * @generated
	 */
	EAttribute getSpanLink_TraceId();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.SpanLink#getSpanId <em>Span Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Span Id</em>'.
	 * @see org.nasdanika.telemetry.model.SpanLink#getSpanId()
	 * @see #getSpanLink()
	 * @generated
	 */
	EAttribute getSpanLink_SpanId();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.SpanLink#getTraceState <em>Trace State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trace State</em>'.
	 * @see org.nasdanika.telemetry.model.SpanLink#getTraceState()
	 * @see #getSpanLink()
	 * @generated
	 */
	EAttribute getSpanLink_TraceState();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.SpanLink#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see org.nasdanika.telemetry.model.SpanLink#getAttributes()
	 * @see #getSpanLink()
	 * @generated
	 */
	EReference getSpanLink_Attributes();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.SpanLink#getDroppedAttributesCount <em>Dropped Attributes Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dropped Attributes Count</em>'.
	 * @see org.nasdanika.telemetry.model.SpanLink#getDroppedAttributesCount()
	 * @see #getSpanLink()
	 * @generated
	 */
	EAttribute getSpanLink_DroppedAttributesCount();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.SpanLink#getFlags <em>Flags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Flags</em>'.
	 * @see org.nasdanika.telemetry.model.SpanLink#getFlags()
	 * @see #getSpanLink()
	 * @generated
	 */
	EAttribute getSpanLink_Flags();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.SpanStatus <em>Span Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Span Status</em>'.
	 * @see org.nasdanika.telemetry.model.SpanStatus
	 * @generated
	 */
	EClass getSpanStatus();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.SpanStatus#getMessage <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message</em>'.
	 * @see org.nasdanika.telemetry.model.SpanStatus#getMessage()
	 * @see #getSpanStatus()
	 * @generated
	 */
	EAttribute getSpanStatus_Message();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.SpanStatus#getCode <em>Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Code</em>'.
	 * @see org.nasdanika.telemetry.model.SpanStatus#getCode()
	 * @see #getSpanStatus()
	 * @generated
	 */
	EAttribute getSpanStatus_Code();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.MetricsData <em>Metrics Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Metrics Data</em>'.
	 * @see org.nasdanika.telemetry.model.MetricsData
	 * @generated
	 */
	EClass getMetricsData();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.MetricsData#getResourceMetrics <em>Resource Metrics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Metrics</em>'.
	 * @see org.nasdanika.telemetry.model.MetricsData#getResourceMetrics()
	 * @see #getMetricsData()
	 * @generated
	 */
	EReference getMetricsData_ResourceMetrics();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.ResourceMetrics <em>Resource Metrics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Metrics</em>'.
	 * @see org.nasdanika.telemetry.model.ResourceMetrics
	 * @generated
	 */
	EClass getResourceMetrics();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.telemetry.model.ResourceMetrics#getResource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Resource</em>'.
	 * @see org.nasdanika.telemetry.model.ResourceMetrics#getResource()
	 * @see #getResourceMetrics()
	 * @generated
	 */
	EReference getResourceMetrics_Resource();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.ResourceMetrics#getScopeMetrics <em>Scope Metrics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Scope Metrics</em>'.
	 * @see org.nasdanika.telemetry.model.ResourceMetrics#getScopeMetrics()
	 * @see #getResourceMetrics()
	 * @generated
	 */
	EReference getResourceMetrics_ScopeMetrics();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.ResourceMetrics#getSchemaUrl <em>Schema Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Schema Url</em>'.
	 * @see org.nasdanika.telemetry.model.ResourceMetrics#getSchemaUrl()
	 * @see #getResourceMetrics()
	 * @generated
	 */
	EAttribute getResourceMetrics_SchemaUrl();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.ScopeMetrics <em>Scope Metrics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scope Metrics</em>'.
	 * @see org.nasdanika.telemetry.model.ScopeMetrics
	 * @generated
	 */
	EClass getScopeMetrics();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.telemetry.model.ScopeMetrics#getScope <em>Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Scope</em>'.
	 * @see org.nasdanika.telemetry.model.ScopeMetrics#getScope()
	 * @see #getScopeMetrics()
	 * @generated
	 */
	EReference getScopeMetrics_Scope();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.ScopeMetrics#getMetrics <em>Metrics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Metrics</em>'.
	 * @see org.nasdanika.telemetry.model.ScopeMetrics#getMetrics()
	 * @see #getScopeMetrics()
	 * @generated
	 */
	EReference getScopeMetrics_Metrics();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.ScopeMetrics#getSchemaUrl <em>Schema Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Schema Url</em>'.
	 * @see org.nasdanika.telemetry.model.ScopeMetrics#getSchemaUrl()
	 * @see #getScopeMetrics()
	 * @generated
	 */
	EAttribute getScopeMetrics_SchemaUrl();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.Metric <em>Metric</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Metric</em>'.
	 * @see org.nasdanika.telemetry.model.Metric
	 * @generated
	 */
	EClass getMetric();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.Metric#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.nasdanika.telemetry.model.Metric#getName()
	 * @see #getMetric()
	 * @generated
	 */
	EAttribute getMetric_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.Metric#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.nasdanika.telemetry.model.Metric#getDescription()
	 * @see #getMetric()
	 * @generated
	 */
	EAttribute getMetric_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.Metric#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit</em>'.
	 * @see org.nasdanika.telemetry.model.Metric#getUnit()
	 * @see #getMetric()
	 * @generated
	 */
	EAttribute getMetric_Unit();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.Metric#getMetadata <em>Metadata</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Metadata</em>'.
	 * @see org.nasdanika.telemetry.model.Metric#getMetadata()
	 * @see #getMetric()
	 * @generated
	 */
	EReference getMetric_Metadata();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.Gauge <em>Gauge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Gauge</em>'.
	 * @see org.nasdanika.telemetry.model.Gauge
	 * @generated
	 */
	EClass getGauge();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.Gauge#getDataPoints <em>Data Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Points</em>'.
	 * @see org.nasdanika.telemetry.model.Gauge#getDataPoints()
	 * @see #getGauge()
	 * @generated
	 */
	EReference getGauge_DataPoints();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.Sum <em>Sum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sum</em>'.
	 * @see org.nasdanika.telemetry.model.Sum
	 * @generated
	 */
	EClass getSum();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.Sum#getDataPoints <em>Data Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Points</em>'.
	 * @see org.nasdanika.telemetry.model.Sum#getDataPoints()
	 * @see #getSum()
	 * @generated
	 */
	EReference getSum_DataPoints();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.Sum#getAggregationTemporality <em>Aggregation Temporality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Aggregation Temporality</em>'.
	 * @see org.nasdanika.telemetry.model.Sum#getAggregationTemporality()
	 * @see #getSum()
	 * @generated
	 */
	EAttribute getSum_AggregationTemporality();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.Sum#isIsMonotonic <em>Is Monotonic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Monotonic</em>'.
	 * @see org.nasdanika.telemetry.model.Sum#isIsMonotonic()
	 * @see #getSum()
	 * @generated
	 */
	EAttribute getSum_IsMonotonic();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.Histogram <em>Histogram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Histogram</em>'.
	 * @see org.nasdanika.telemetry.model.Histogram
	 * @generated
	 */
	EClass getHistogram();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.Histogram#getDataPoints <em>Data Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Points</em>'.
	 * @see org.nasdanika.telemetry.model.Histogram#getDataPoints()
	 * @see #getHistogram()
	 * @generated
	 */
	EReference getHistogram_DataPoints();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.Histogram#getAggregationTemporality <em>Aggregation Temporality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Aggregation Temporality</em>'.
	 * @see org.nasdanika.telemetry.model.Histogram#getAggregationTemporality()
	 * @see #getHistogram()
	 * @generated
	 */
	EAttribute getHistogram_AggregationTemporality();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.ExponentialHistogram <em>Exponential Histogram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exponential Histogram</em>'.
	 * @see org.nasdanika.telemetry.model.ExponentialHistogram
	 * @generated
	 */
	EClass getExponentialHistogram();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.ExponentialHistogram#getDataPoints <em>Data Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Points</em>'.
	 * @see org.nasdanika.telemetry.model.ExponentialHistogram#getDataPoints()
	 * @see #getExponentialHistogram()
	 * @generated
	 */
	EReference getExponentialHistogram_DataPoints();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.ExponentialHistogram#getAggregationTemporality <em>Aggregation Temporality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Aggregation Temporality</em>'.
	 * @see org.nasdanika.telemetry.model.ExponentialHistogram#getAggregationTemporality()
	 * @see #getExponentialHistogram()
	 * @generated
	 */
	EAttribute getExponentialHistogram_AggregationTemporality();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.Summary <em>Summary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Summary</em>'.
	 * @see org.nasdanika.telemetry.model.Summary
	 * @generated
	 */
	EClass getSummary();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.Summary#getDataPoints <em>Data Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Points</em>'.
	 * @see org.nasdanika.telemetry.model.Summary#getDataPoints()
	 * @see #getSummary()
	 * @generated
	 */
	EReference getSummary_DataPoints();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.NumberDataPoint <em>Number Data Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Number Data Point</em>'.
	 * @see org.nasdanika.telemetry.model.NumberDataPoint
	 * @generated
	 */
	EClass getNumberDataPoint();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.NumberDataPoint#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see org.nasdanika.telemetry.model.NumberDataPoint#getAttributes()
	 * @see #getNumberDataPoint()
	 * @generated
	 */
	EReference getNumberDataPoint_Attributes();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.NumberDataPoint#getStartTimeUnixNano <em>Start Time Unix Nano</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Time Unix Nano</em>'.
	 * @see org.nasdanika.telemetry.model.NumberDataPoint#getStartTimeUnixNano()
	 * @see #getNumberDataPoint()
	 * @generated
	 */
	EAttribute getNumberDataPoint_StartTimeUnixNano();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.NumberDataPoint#getTimeUnixNano <em>Time Unix Nano</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Unix Nano</em>'.
	 * @see org.nasdanika.telemetry.model.NumberDataPoint#getTimeUnixNano()
	 * @see #getNumberDataPoint()
	 * @generated
	 */
	EAttribute getNumberDataPoint_TimeUnixNano();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.NumberDataPoint#getAsDouble <em>As Double</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>As Double</em>'.
	 * @see org.nasdanika.telemetry.model.NumberDataPoint#getAsDouble()
	 * @see #getNumberDataPoint()
	 * @generated
	 */
	EAttribute getNumberDataPoint_AsDouble();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.NumberDataPoint#getAsInt <em>As Int</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>As Int</em>'.
	 * @see org.nasdanika.telemetry.model.NumberDataPoint#getAsInt()
	 * @see #getNumberDataPoint()
	 * @generated
	 */
	EAttribute getNumberDataPoint_AsInt();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.NumberDataPoint#getExemplars <em>Exemplars</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exemplars</em>'.
	 * @see org.nasdanika.telemetry.model.NumberDataPoint#getExemplars()
	 * @see #getNumberDataPoint()
	 * @generated
	 */
	EReference getNumberDataPoint_Exemplars();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.NumberDataPoint#getFlags <em>Flags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Flags</em>'.
	 * @see org.nasdanika.telemetry.model.NumberDataPoint#getFlags()
	 * @see #getNumberDataPoint()
	 * @generated
	 */
	EAttribute getNumberDataPoint_Flags();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.HistogramDataPoint <em>Histogram Data Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Histogram Data Point</em>'.
	 * @see org.nasdanika.telemetry.model.HistogramDataPoint
	 * @generated
	 */
	EClass getHistogramDataPoint();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.HistogramDataPoint#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see org.nasdanika.telemetry.model.HistogramDataPoint#getAttributes()
	 * @see #getHistogramDataPoint()
	 * @generated
	 */
	EReference getHistogramDataPoint_Attributes();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.HistogramDataPoint#getStartTimeUnixNano <em>Start Time Unix Nano</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Time Unix Nano</em>'.
	 * @see org.nasdanika.telemetry.model.HistogramDataPoint#getStartTimeUnixNano()
	 * @see #getHistogramDataPoint()
	 * @generated
	 */
	EAttribute getHistogramDataPoint_StartTimeUnixNano();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.HistogramDataPoint#getTimeUnixNano <em>Time Unix Nano</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Unix Nano</em>'.
	 * @see org.nasdanika.telemetry.model.HistogramDataPoint#getTimeUnixNano()
	 * @see #getHistogramDataPoint()
	 * @generated
	 */
	EAttribute getHistogramDataPoint_TimeUnixNano();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.HistogramDataPoint#getCount <em>Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Count</em>'.
	 * @see org.nasdanika.telemetry.model.HistogramDataPoint#getCount()
	 * @see #getHistogramDataPoint()
	 * @generated
	 */
	EAttribute getHistogramDataPoint_Count();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.HistogramDataPoint#getSum <em>Sum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sum</em>'.
	 * @see org.nasdanika.telemetry.model.HistogramDataPoint#getSum()
	 * @see #getHistogramDataPoint()
	 * @generated
	 */
	EAttribute getHistogramDataPoint_Sum();

	/**
	 * Returns the meta object for the attribute list '{@link org.nasdanika.telemetry.model.HistogramDataPoint#getBucketCounts <em>Bucket Counts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Bucket Counts</em>'.
	 * @see org.nasdanika.telemetry.model.HistogramDataPoint#getBucketCounts()
	 * @see #getHistogramDataPoint()
	 * @generated
	 */
	EAttribute getHistogramDataPoint_BucketCounts();

	/**
	 * Returns the meta object for the attribute list '{@link org.nasdanika.telemetry.model.HistogramDataPoint#getExplicitBounds <em>Explicit Bounds</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Explicit Bounds</em>'.
	 * @see org.nasdanika.telemetry.model.HistogramDataPoint#getExplicitBounds()
	 * @see #getHistogramDataPoint()
	 * @generated
	 */
	EAttribute getHistogramDataPoint_ExplicitBounds();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.HistogramDataPoint#getExemplars <em>Exemplars</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exemplars</em>'.
	 * @see org.nasdanika.telemetry.model.HistogramDataPoint#getExemplars()
	 * @see #getHistogramDataPoint()
	 * @generated
	 */
	EReference getHistogramDataPoint_Exemplars();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.HistogramDataPoint#getFlags <em>Flags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Flags</em>'.
	 * @see org.nasdanika.telemetry.model.HistogramDataPoint#getFlags()
	 * @see #getHistogramDataPoint()
	 * @generated
	 */
	EAttribute getHistogramDataPoint_Flags();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.HistogramDataPoint#getMin <em>Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min</em>'.
	 * @see org.nasdanika.telemetry.model.HistogramDataPoint#getMin()
	 * @see #getHistogramDataPoint()
	 * @generated
	 */
	EAttribute getHistogramDataPoint_Min();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.HistogramDataPoint#getMax <em>Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max</em>'.
	 * @see org.nasdanika.telemetry.model.HistogramDataPoint#getMax()
	 * @see #getHistogramDataPoint()
	 * @generated
	 */
	EAttribute getHistogramDataPoint_Max();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint <em>Exponential Histogram Data Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exponential Histogram Data Point</em>'.
	 * @see org.nasdanika.telemetry.model.ExponentialHistogramDataPoint
	 * @generated
	 */
	EClass getExponentialHistogramDataPoint();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getAttributes()
	 * @see #getExponentialHistogramDataPoint()
	 * @generated
	 */
	EReference getExponentialHistogramDataPoint_Attributes();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getStartTimeUnixNano <em>Start Time Unix Nano</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Time Unix Nano</em>'.
	 * @see org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getStartTimeUnixNano()
	 * @see #getExponentialHistogramDataPoint()
	 * @generated
	 */
	EAttribute getExponentialHistogramDataPoint_StartTimeUnixNano();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getTimeUnixNano <em>Time Unix Nano</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Unix Nano</em>'.
	 * @see org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getTimeUnixNano()
	 * @see #getExponentialHistogramDataPoint()
	 * @generated
	 */
	EAttribute getExponentialHistogramDataPoint_TimeUnixNano();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getCount <em>Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Count</em>'.
	 * @see org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getCount()
	 * @see #getExponentialHistogramDataPoint()
	 * @generated
	 */
	EAttribute getExponentialHistogramDataPoint_Count();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getSum <em>Sum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sum</em>'.
	 * @see org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getSum()
	 * @see #getExponentialHistogramDataPoint()
	 * @generated
	 */
	EAttribute getExponentialHistogramDataPoint_Sum();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getScale <em>Scale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scale</em>'.
	 * @see org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getScale()
	 * @see #getExponentialHistogramDataPoint()
	 * @generated
	 */
	EAttribute getExponentialHistogramDataPoint_Scale();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getZeroCount <em>Zero Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Zero Count</em>'.
	 * @see org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getZeroCount()
	 * @see #getExponentialHistogramDataPoint()
	 * @generated
	 */
	EAttribute getExponentialHistogramDataPoint_ZeroCount();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getPositive <em>Positive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Positive</em>'.
	 * @see org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getPositive()
	 * @see #getExponentialHistogramDataPoint()
	 * @generated
	 */
	EReference getExponentialHistogramDataPoint_Positive();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getNegative <em>Negative</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Negative</em>'.
	 * @see org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getNegative()
	 * @see #getExponentialHistogramDataPoint()
	 * @generated
	 */
	EReference getExponentialHistogramDataPoint_Negative();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getFlags <em>Flags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Flags</em>'.
	 * @see org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getFlags()
	 * @see #getExponentialHistogramDataPoint()
	 * @generated
	 */
	EAttribute getExponentialHistogramDataPoint_Flags();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getExemplars <em>Exemplars</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exemplars</em>'.
	 * @see org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getExemplars()
	 * @see #getExponentialHistogramDataPoint()
	 * @generated
	 */
	EReference getExponentialHistogramDataPoint_Exemplars();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getMin <em>Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min</em>'.
	 * @see org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getMin()
	 * @see #getExponentialHistogramDataPoint()
	 * @generated
	 */
	EAttribute getExponentialHistogramDataPoint_Min();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getMax <em>Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max</em>'.
	 * @see org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getMax()
	 * @see #getExponentialHistogramDataPoint()
	 * @generated
	 */
	EAttribute getExponentialHistogramDataPoint_Max();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getZeroThreshold <em>Zero Threshold</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Zero Threshold</em>'.
	 * @see org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getZeroThreshold()
	 * @see #getExponentialHistogramDataPoint()
	 * @generated
	 */
	EAttribute getExponentialHistogramDataPoint_ZeroThreshold();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPointBuckets <em>Exponential Histogram Data Point Buckets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exponential Histogram Data Point Buckets</em>'.
	 * @see org.nasdanika.telemetry.model.ExponentialHistogramDataPointBuckets
	 * @generated
	 */
	EClass getExponentialHistogramDataPointBuckets();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPointBuckets#getOffset <em>Offset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Offset</em>'.
	 * @see org.nasdanika.telemetry.model.ExponentialHistogramDataPointBuckets#getOffset()
	 * @see #getExponentialHistogramDataPointBuckets()
	 * @generated
	 */
	EAttribute getExponentialHistogramDataPointBuckets_Offset();

	/**
	 * Returns the meta object for the attribute list '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPointBuckets#getBucketCounts <em>Bucket Counts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Bucket Counts</em>'.
	 * @see org.nasdanika.telemetry.model.ExponentialHistogramDataPointBuckets#getBucketCounts()
	 * @see #getExponentialHistogramDataPointBuckets()
	 * @generated
	 */
	EAttribute getExponentialHistogramDataPointBuckets_BucketCounts();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.SummaryDataPoint <em>Summary Data Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Summary Data Point</em>'.
	 * @see org.nasdanika.telemetry.model.SummaryDataPoint
	 * @generated
	 */
	EClass getSummaryDataPoint();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.SummaryDataPoint#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see org.nasdanika.telemetry.model.SummaryDataPoint#getAttributes()
	 * @see #getSummaryDataPoint()
	 * @generated
	 */
	EReference getSummaryDataPoint_Attributes();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.SummaryDataPoint#getStartTimeUnixNano <em>Start Time Unix Nano</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Time Unix Nano</em>'.
	 * @see org.nasdanika.telemetry.model.SummaryDataPoint#getStartTimeUnixNano()
	 * @see #getSummaryDataPoint()
	 * @generated
	 */
	EAttribute getSummaryDataPoint_StartTimeUnixNano();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.SummaryDataPoint#getTimeUnixNano <em>Time Unix Nano</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Unix Nano</em>'.
	 * @see org.nasdanika.telemetry.model.SummaryDataPoint#getTimeUnixNano()
	 * @see #getSummaryDataPoint()
	 * @generated
	 */
	EAttribute getSummaryDataPoint_TimeUnixNano();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.SummaryDataPoint#getCount <em>Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Count</em>'.
	 * @see org.nasdanika.telemetry.model.SummaryDataPoint#getCount()
	 * @see #getSummaryDataPoint()
	 * @generated
	 */
	EAttribute getSummaryDataPoint_Count();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.SummaryDataPoint#getSum <em>Sum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sum</em>'.
	 * @see org.nasdanika.telemetry.model.SummaryDataPoint#getSum()
	 * @see #getSummaryDataPoint()
	 * @generated
	 */
	EAttribute getSummaryDataPoint_Sum();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.SummaryDataPoint#getQuantileValues <em>Quantile Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Quantile Values</em>'.
	 * @see org.nasdanika.telemetry.model.SummaryDataPoint#getQuantileValues()
	 * @see #getSummaryDataPoint()
	 * @generated
	 */
	EReference getSummaryDataPoint_QuantileValues();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.SummaryDataPoint#getFlags <em>Flags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Flags</em>'.
	 * @see org.nasdanika.telemetry.model.SummaryDataPoint#getFlags()
	 * @see #getSummaryDataPoint()
	 * @generated
	 */
	EAttribute getSummaryDataPoint_Flags();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.SummaryDataPointValueAtQuantile <em>Summary Data Point Value At Quantile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Summary Data Point Value At Quantile</em>'.
	 * @see org.nasdanika.telemetry.model.SummaryDataPointValueAtQuantile
	 * @generated
	 */
	EClass getSummaryDataPointValueAtQuantile();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.SummaryDataPointValueAtQuantile#getQuantile <em>Quantile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Quantile</em>'.
	 * @see org.nasdanika.telemetry.model.SummaryDataPointValueAtQuantile#getQuantile()
	 * @see #getSummaryDataPointValueAtQuantile()
	 * @generated
	 */
	EAttribute getSummaryDataPointValueAtQuantile_Quantile();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.SummaryDataPointValueAtQuantile#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.nasdanika.telemetry.model.SummaryDataPointValueAtQuantile#getValue()
	 * @see #getSummaryDataPointValueAtQuantile()
	 * @generated
	 */
	EAttribute getSummaryDataPointValueAtQuantile_Value();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.Exemplar <em>Exemplar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exemplar</em>'.
	 * @see org.nasdanika.telemetry.model.Exemplar
	 * @generated
	 */
	EClass getExemplar();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.Exemplar#getFilteredAttributes <em>Filtered Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Filtered Attributes</em>'.
	 * @see org.nasdanika.telemetry.model.Exemplar#getFilteredAttributes()
	 * @see #getExemplar()
	 * @generated
	 */
	EReference getExemplar_FilteredAttributes();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.Exemplar#getTimeUnixNano <em>Time Unix Nano</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Unix Nano</em>'.
	 * @see org.nasdanika.telemetry.model.Exemplar#getTimeUnixNano()
	 * @see #getExemplar()
	 * @generated
	 */
	EAttribute getExemplar_TimeUnixNano();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.Exemplar#getAsDouble <em>As Double</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>As Double</em>'.
	 * @see org.nasdanika.telemetry.model.Exemplar#getAsDouble()
	 * @see #getExemplar()
	 * @generated
	 */
	EAttribute getExemplar_AsDouble();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.Exemplar#getAsInt <em>As Int</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>As Int</em>'.
	 * @see org.nasdanika.telemetry.model.Exemplar#getAsInt()
	 * @see #getExemplar()
	 * @generated
	 */
	EAttribute getExemplar_AsInt();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.Exemplar#getSpanId <em>Span Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Span Id</em>'.
	 * @see org.nasdanika.telemetry.model.Exemplar#getSpanId()
	 * @see #getExemplar()
	 * @generated
	 */
	EAttribute getExemplar_SpanId();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.Exemplar#getTraceId <em>Trace Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trace Id</em>'.
	 * @see org.nasdanika.telemetry.model.Exemplar#getTraceId()
	 * @see #getExemplar()
	 * @generated
	 */
	EAttribute getExemplar_TraceId();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.LogsData <em>Logs Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Logs Data</em>'.
	 * @see org.nasdanika.telemetry.model.LogsData
	 * @generated
	 */
	EClass getLogsData();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.LogsData#getResourceLogs <em>Resource Logs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Logs</em>'.
	 * @see org.nasdanika.telemetry.model.LogsData#getResourceLogs()
	 * @see #getLogsData()
	 * @generated
	 */
	EReference getLogsData_ResourceLogs();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.ResourceLogs <em>Resource Logs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Logs</em>'.
	 * @see org.nasdanika.telemetry.model.ResourceLogs
	 * @generated
	 */
	EClass getResourceLogs();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.telemetry.model.ResourceLogs#getResource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Resource</em>'.
	 * @see org.nasdanika.telemetry.model.ResourceLogs#getResource()
	 * @see #getResourceLogs()
	 * @generated
	 */
	EReference getResourceLogs_Resource();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.ResourceLogs#getScopeLogs <em>Scope Logs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Scope Logs</em>'.
	 * @see org.nasdanika.telemetry.model.ResourceLogs#getScopeLogs()
	 * @see #getResourceLogs()
	 * @generated
	 */
	EReference getResourceLogs_ScopeLogs();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.ResourceLogs#getSchemaUrl <em>Schema Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Schema Url</em>'.
	 * @see org.nasdanika.telemetry.model.ResourceLogs#getSchemaUrl()
	 * @see #getResourceLogs()
	 * @generated
	 */
	EAttribute getResourceLogs_SchemaUrl();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.ScopeLogs <em>Scope Logs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scope Logs</em>'.
	 * @see org.nasdanika.telemetry.model.ScopeLogs
	 * @generated
	 */
	EClass getScopeLogs();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.telemetry.model.ScopeLogs#getScope <em>Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Scope</em>'.
	 * @see org.nasdanika.telemetry.model.ScopeLogs#getScope()
	 * @see #getScopeLogs()
	 * @generated
	 */
	EReference getScopeLogs_Scope();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.ScopeLogs#getLogRecords <em>Log Records</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Log Records</em>'.
	 * @see org.nasdanika.telemetry.model.ScopeLogs#getLogRecords()
	 * @see #getScopeLogs()
	 * @generated
	 */
	EReference getScopeLogs_LogRecords();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.ScopeLogs#getSchemaUrl <em>Schema Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Schema Url</em>'.
	 * @see org.nasdanika.telemetry.model.ScopeLogs#getSchemaUrl()
	 * @see #getScopeLogs()
	 * @generated
	 */
	EAttribute getScopeLogs_SchemaUrl();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.telemetry.model.LogRecord <em>Log Record</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Log Record</em>'.
	 * @see org.nasdanika.telemetry.model.LogRecord
	 * @generated
	 */
	EClass getLogRecord();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.LogRecord#getTimeUnixNano <em>Time Unix Nano</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Unix Nano</em>'.
	 * @see org.nasdanika.telemetry.model.LogRecord#getTimeUnixNano()
	 * @see #getLogRecord()
	 * @generated
	 */
	EAttribute getLogRecord_TimeUnixNano();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.LogRecord#getObservedTimeUnixNano <em>Observed Time Unix Nano</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Observed Time Unix Nano</em>'.
	 * @see org.nasdanika.telemetry.model.LogRecord#getObservedTimeUnixNano()
	 * @see #getLogRecord()
	 * @generated
	 */
	EAttribute getLogRecord_ObservedTimeUnixNano();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.LogRecord#getSeverityNumber <em>Severity Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Severity Number</em>'.
	 * @see org.nasdanika.telemetry.model.LogRecord#getSeverityNumber()
	 * @see #getLogRecord()
	 * @generated
	 */
	EAttribute getLogRecord_SeverityNumber();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.LogRecord#getSeverityText <em>Severity Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Severity Text</em>'.
	 * @see org.nasdanika.telemetry.model.LogRecord#getSeverityText()
	 * @see #getLogRecord()
	 * @generated
	 */
	EAttribute getLogRecord_SeverityText();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.telemetry.model.LogRecord#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see org.nasdanika.telemetry.model.LogRecord#getBody()
	 * @see #getLogRecord()
	 * @generated
	 */
	EReference getLogRecord_Body();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.telemetry.model.LogRecord#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see org.nasdanika.telemetry.model.LogRecord#getAttributes()
	 * @see #getLogRecord()
	 * @generated
	 */
	EReference getLogRecord_Attributes();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.LogRecord#getDroppedAttributesCount <em>Dropped Attributes Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dropped Attributes Count</em>'.
	 * @see org.nasdanika.telemetry.model.LogRecord#getDroppedAttributesCount()
	 * @see #getLogRecord()
	 * @generated
	 */
	EAttribute getLogRecord_DroppedAttributesCount();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.LogRecord#getFlags <em>Flags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Flags</em>'.
	 * @see org.nasdanika.telemetry.model.LogRecord#getFlags()
	 * @see #getLogRecord()
	 * @generated
	 */
	EAttribute getLogRecord_Flags();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.LogRecord#getTraceId <em>Trace Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trace Id</em>'.
	 * @see org.nasdanika.telemetry.model.LogRecord#getTraceId()
	 * @see #getLogRecord()
	 * @generated
	 */
	EAttribute getLogRecord_TraceId();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.telemetry.model.LogRecord#getSpanId <em>Span Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Span Id</em>'.
	 * @see org.nasdanika.telemetry.model.LogRecord#getSpanId()
	 * @see #getLogRecord()
	 * @generated
	 */
	EAttribute getLogRecord_SpanId();

	/**
	 * Returns the meta object for enum '{@link org.nasdanika.telemetry.model.SpanKind <em>Span Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Span Kind</em>'.
	 * @see org.nasdanika.telemetry.model.SpanKind
	 * @generated
	 */
	EEnum getSpanKind();

	/**
	 * Returns the meta object for enum '{@link org.nasdanika.telemetry.model.StatusCode <em>Status Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Status Code</em>'.
	 * @see org.nasdanika.telemetry.model.StatusCode
	 * @generated
	 */
	EEnum getStatusCode();

	/**
	 * Returns the meta object for enum '{@link org.nasdanika.telemetry.model.AggregationTemporality <em>Aggregation Temporality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Aggregation Temporality</em>'.
	 * @see org.nasdanika.telemetry.model.AggregationTemporality
	 * @generated
	 */
	EEnum getAggregationTemporality();

	/**
	 * Returns the meta object for enum '{@link org.nasdanika.telemetry.model.SeverityNumber <em>Severity Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Severity Number</em>'.
	 * @see org.nasdanika.telemetry.model.SeverityNumber
	 * @generated
	 */
	EEnum getSeverityNumber();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ModelFactory getModelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.ResourceImpl <em>Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.ResourceImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getResource()
		 * @generated
		 */
		EClass RESOURCE = eINSTANCE.getResource();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE__ATTRIBUTES = eINSTANCE.getResource_Attributes();

		/**
		 * The meta object literal for the '<em><b>Dropped Attributes Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE__DROPPED_ATTRIBUTES_COUNT = eINSTANCE.getResource_DroppedAttributesCount();

		/**
		 * The meta object literal for the '<em><b>Schema Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE__SCHEMA_URL = eINSTANCE.getResource_SchemaUrl();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.InstrumentationScopeImpl <em>Instrumentation Scope</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.InstrumentationScopeImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getInstrumentationScope()
		 * @generated
		 */
		EClass INSTRUMENTATION_SCOPE = eINSTANCE.getInstrumentationScope();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSTRUMENTATION_SCOPE__NAME = eINSTANCE.getInstrumentationScope_Name();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSTRUMENTATION_SCOPE__VERSION = eINSTANCE.getInstrumentationScope_Version();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTRUMENTATION_SCOPE__ATTRIBUTES = eINSTANCE.getInstrumentationScope_Attributes();

		/**
		 * The meta object literal for the '<em><b>Dropped Attributes Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSTRUMENTATION_SCOPE__DROPPED_ATTRIBUTES_COUNT = eINSTANCE.getInstrumentationScope_DroppedAttributesCount();

		/**
		 * The meta object literal for the '<em><b>Schema Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSTRUMENTATION_SCOPE__SCHEMA_URL = eINSTANCE.getInstrumentationScope_SchemaUrl();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.KeyValueImpl <em>Key Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.KeyValueImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getKeyValue()
		 * @generated
		 */
		EClass KEY_VALUE = eINSTANCE.getKeyValue();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute KEY_VALUE__KEY = eINSTANCE.getKeyValue_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference KEY_VALUE__VALUE = eINSTANCE.getKeyValue_Value();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.AnyValueImpl <em>Any Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.AnyValueImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getAnyValue()
		 * @generated
		 */
		EClass ANY_VALUE = eINSTANCE.getAnyValue();

		/**
		 * The meta object literal for the '<em><b>String Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANY_VALUE__STRING_VALUE = eINSTANCE.getAnyValue_StringValue();

		/**
		 * The meta object literal for the '<em><b>Bool Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANY_VALUE__BOOL_VALUE = eINSTANCE.getAnyValue_BoolValue();

		/**
		 * The meta object literal for the '<em><b>Int Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANY_VALUE__INT_VALUE = eINSTANCE.getAnyValue_IntValue();

		/**
		 * The meta object literal for the '<em><b>Double Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANY_VALUE__DOUBLE_VALUE = eINSTANCE.getAnyValue_DoubleValue();

		/**
		 * The meta object literal for the '<em><b>Array Value</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANY_VALUE__ARRAY_VALUE = eINSTANCE.getAnyValue_ArrayValue();

		/**
		 * The meta object literal for the '<em><b>Kvlist Value</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANY_VALUE__KVLIST_VALUE = eINSTANCE.getAnyValue_KvlistValue();

		/**
		 * The meta object literal for the '<em><b>Bytes Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANY_VALUE__BYTES_VALUE = eINSTANCE.getAnyValue_BytesValue();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.TracesDataImpl <em>Traces Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.TracesDataImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getTracesData()
		 * @generated
		 */
		EClass TRACES_DATA = eINSTANCE.getTracesData();

		/**
		 * The meta object literal for the '<em><b>Resource Spans</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACES_DATA__RESOURCE_SPANS = eINSTANCE.getTracesData_ResourceSpans();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.ResourceSpansImpl <em>Resource Spans</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.ResourceSpansImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getResourceSpans()
		 * @generated
		 */
		EClass RESOURCE_SPANS = eINSTANCE.getResourceSpans();

		/**
		 * The meta object literal for the '<em><b>Resource</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_SPANS__RESOURCE = eINSTANCE.getResourceSpans_Resource();

		/**
		 * The meta object literal for the '<em><b>Scope Spans</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_SPANS__SCOPE_SPANS = eINSTANCE.getResourceSpans_ScopeSpans();

		/**
		 * The meta object literal for the '<em><b>Schema Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_SPANS__SCHEMA_URL = eINSTANCE.getResourceSpans_SchemaUrl();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.ScopeSpansImpl <em>Scope Spans</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.ScopeSpansImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getScopeSpans()
		 * @generated
		 */
		EClass SCOPE_SPANS = eINSTANCE.getScopeSpans();

		/**
		 * The meta object literal for the '<em><b>Scope</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCOPE_SPANS__SCOPE = eINSTANCE.getScopeSpans_Scope();

		/**
		 * The meta object literal for the '<em><b>Spans</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCOPE_SPANS__SPANS = eINSTANCE.getScopeSpans_Spans();

		/**
		 * The meta object literal for the '<em><b>Schema Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCOPE_SPANS__SCHEMA_URL = eINSTANCE.getScopeSpans_SchemaUrl();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.SpanImpl <em>Span</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.SpanImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getSpan()
		 * @generated
		 */
		EClass SPAN = eINSTANCE.getSpan();

		/**
		 * The meta object literal for the '<em><b>Trace Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPAN__TRACE_ID = eINSTANCE.getSpan_TraceId();

		/**
		 * The meta object literal for the '<em><b>Span Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPAN__SPAN_ID = eINSTANCE.getSpan_SpanId();

		/**
		 * The meta object literal for the '<em><b>Trace State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPAN__TRACE_STATE = eINSTANCE.getSpan_TraceState();

		/**
		 * The meta object literal for the '<em><b>Parent Span Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPAN__PARENT_SPAN_ID = eINSTANCE.getSpan_ParentSpanId();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPAN__NAME = eINSTANCE.getSpan_Name();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPAN__KIND = eINSTANCE.getSpan_Kind();

		/**
		 * The meta object literal for the '<em><b>Start Time Unix Nano</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPAN__START_TIME_UNIX_NANO = eINSTANCE.getSpan_StartTimeUnixNano();

		/**
		 * The meta object literal for the '<em><b>End Time Unix Nano</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPAN__END_TIME_UNIX_NANO = eINSTANCE.getSpan_EndTimeUnixNano();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPAN__ATTRIBUTES = eINSTANCE.getSpan_Attributes();

		/**
		 * The meta object literal for the '<em><b>Dropped Attributes Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPAN__DROPPED_ATTRIBUTES_COUNT = eINSTANCE.getSpan_DroppedAttributesCount();

		/**
		 * The meta object literal for the '<em><b>Events</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPAN__EVENTS = eINSTANCE.getSpan_Events();

		/**
		 * The meta object literal for the '<em><b>Dropped Events Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPAN__DROPPED_EVENTS_COUNT = eINSTANCE.getSpan_DroppedEventsCount();

		/**
		 * The meta object literal for the '<em><b>Links</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPAN__LINKS = eINSTANCE.getSpan_Links();

		/**
		 * The meta object literal for the '<em><b>Dropped Links Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPAN__DROPPED_LINKS_COUNT = eINSTANCE.getSpan_DroppedLinksCount();

		/**
		 * The meta object literal for the '<em><b>Status</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPAN__STATUS = eINSTANCE.getSpan_Status();

		/**
		 * The meta object literal for the '<em><b>Flags</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPAN__FLAGS = eINSTANCE.getSpan_Flags();

		/**
		 * The meta object literal for the '<em><b>Change Description</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPAN__CHANGE_DESCRIPTION = eINSTANCE.getSpan_ChangeDescription();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.SpanEventImpl <em>Span Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.SpanEventImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getSpanEvent()
		 * @generated
		 */
		EClass SPAN_EVENT = eINSTANCE.getSpanEvent();

		/**
		 * The meta object literal for the '<em><b>Time Unix Nano</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPAN_EVENT__TIME_UNIX_NANO = eINSTANCE.getSpanEvent_TimeUnixNano();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPAN_EVENT__NAME = eINSTANCE.getSpanEvent_Name();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPAN_EVENT__ATTRIBUTES = eINSTANCE.getSpanEvent_Attributes();

		/**
		 * The meta object literal for the '<em><b>Dropped Attributes Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPAN_EVENT__DROPPED_ATTRIBUTES_COUNT = eINSTANCE.getSpanEvent_DroppedAttributesCount();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.SpanLinkImpl <em>Span Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.SpanLinkImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getSpanLink()
		 * @generated
		 */
		EClass SPAN_LINK = eINSTANCE.getSpanLink();

		/**
		 * The meta object literal for the '<em><b>Trace Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPAN_LINK__TRACE_ID = eINSTANCE.getSpanLink_TraceId();

		/**
		 * The meta object literal for the '<em><b>Span Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPAN_LINK__SPAN_ID = eINSTANCE.getSpanLink_SpanId();

		/**
		 * The meta object literal for the '<em><b>Trace State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPAN_LINK__TRACE_STATE = eINSTANCE.getSpanLink_TraceState();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPAN_LINK__ATTRIBUTES = eINSTANCE.getSpanLink_Attributes();

		/**
		 * The meta object literal for the '<em><b>Dropped Attributes Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPAN_LINK__DROPPED_ATTRIBUTES_COUNT = eINSTANCE.getSpanLink_DroppedAttributesCount();

		/**
		 * The meta object literal for the '<em><b>Flags</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPAN_LINK__FLAGS = eINSTANCE.getSpanLink_Flags();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.SpanStatusImpl <em>Span Status</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.SpanStatusImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getSpanStatus()
		 * @generated
		 */
		EClass SPAN_STATUS = eINSTANCE.getSpanStatus();

		/**
		 * The meta object literal for the '<em><b>Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPAN_STATUS__MESSAGE = eINSTANCE.getSpanStatus_Message();

		/**
		 * The meta object literal for the '<em><b>Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPAN_STATUS__CODE = eINSTANCE.getSpanStatus_Code();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.MetricsDataImpl <em>Metrics Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.MetricsDataImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getMetricsData()
		 * @generated
		 */
		EClass METRICS_DATA = eINSTANCE.getMetricsData();

		/**
		 * The meta object literal for the '<em><b>Resource Metrics</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METRICS_DATA__RESOURCE_METRICS = eINSTANCE.getMetricsData_ResourceMetrics();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.ResourceMetricsImpl <em>Resource Metrics</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.ResourceMetricsImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getResourceMetrics()
		 * @generated
		 */
		EClass RESOURCE_METRICS = eINSTANCE.getResourceMetrics();

		/**
		 * The meta object literal for the '<em><b>Resource</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_METRICS__RESOURCE = eINSTANCE.getResourceMetrics_Resource();

		/**
		 * The meta object literal for the '<em><b>Scope Metrics</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_METRICS__SCOPE_METRICS = eINSTANCE.getResourceMetrics_ScopeMetrics();

		/**
		 * The meta object literal for the '<em><b>Schema Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_METRICS__SCHEMA_URL = eINSTANCE.getResourceMetrics_SchemaUrl();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.ScopeMetricsImpl <em>Scope Metrics</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.ScopeMetricsImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getScopeMetrics()
		 * @generated
		 */
		EClass SCOPE_METRICS = eINSTANCE.getScopeMetrics();

		/**
		 * The meta object literal for the '<em><b>Scope</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCOPE_METRICS__SCOPE = eINSTANCE.getScopeMetrics_Scope();

		/**
		 * The meta object literal for the '<em><b>Metrics</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCOPE_METRICS__METRICS = eINSTANCE.getScopeMetrics_Metrics();

		/**
		 * The meta object literal for the '<em><b>Schema Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCOPE_METRICS__SCHEMA_URL = eINSTANCE.getScopeMetrics_SchemaUrl();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.MetricImpl <em>Metric</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.MetricImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getMetric()
		 * @generated
		 */
		EClass METRIC = eINSTANCE.getMetric();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METRIC__NAME = eINSTANCE.getMetric_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METRIC__DESCRIPTION = eINSTANCE.getMetric_Description();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METRIC__UNIT = eINSTANCE.getMetric_Unit();

		/**
		 * The meta object literal for the '<em><b>Metadata</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METRIC__METADATA = eINSTANCE.getMetric_Metadata();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.GaugeImpl <em>Gauge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.GaugeImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getGauge()
		 * @generated
		 */
		EClass GAUGE = eINSTANCE.getGauge();

		/**
		 * The meta object literal for the '<em><b>Data Points</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GAUGE__DATA_POINTS = eINSTANCE.getGauge_DataPoints();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.SumImpl <em>Sum</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.SumImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getSum()
		 * @generated
		 */
		EClass SUM = eINSTANCE.getSum();

		/**
		 * The meta object literal for the '<em><b>Data Points</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUM__DATA_POINTS = eINSTANCE.getSum_DataPoints();

		/**
		 * The meta object literal for the '<em><b>Aggregation Temporality</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUM__AGGREGATION_TEMPORALITY = eINSTANCE.getSum_AggregationTemporality();

		/**
		 * The meta object literal for the '<em><b>Is Monotonic</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUM__IS_MONOTONIC = eINSTANCE.getSum_IsMonotonic();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.HistogramImpl <em>Histogram</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.HistogramImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getHistogram()
		 * @generated
		 */
		EClass HISTOGRAM = eINSTANCE.getHistogram();

		/**
		 * The meta object literal for the '<em><b>Data Points</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HISTOGRAM__DATA_POINTS = eINSTANCE.getHistogram_DataPoints();

		/**
		 * The meta object literal for the '<em><b>Aggregation Temporality</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HISTOGRAM__AGGREGATION_TEMPORALITY = eINSTANCE.getHistogram_AggregationTemporality();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.ExponentialHistogramImpl <em>Exponential Histogram</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.ExponentialHistogramImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getExponentialHistogram()
		 * @generated
		 */
		EClass EXPONENTIAL_HISTOGRAM = eINSTANCE.getExponentialHistogram();

		/**
		 * The meta object literal for the '<em><b>Data Points</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPONENTIAL_HISTOGRAM__DATA_POINTS = eINSTANCE.getExponentialHistogram_DataPoints();

		/**
		 * The meta object literal for the '<em><b>Aggregation Temporality</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPONENTIAL_HISTOGRAM__AGGREGATION_TEMPORALITY = eINSTANCE.getExponentialHistogram_AggregationTemporality();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.SummaryImpl <em>Summary</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.SummaryImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getSummary()
		 * @generated
		 */
		EClass SUMMARY = eINSTANCE.getSummary();

		/**
		 * The meta object literal for the '<em><b>Data Points</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUMMARY__DATA_POINTS = eINSTANCE.getSummary_DataPoints();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.NumberDataPointImpl <em>Number Data Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.NumberDataPointImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getNumberDataPoint()
		 * @generated
		 */
		EClass NUMBER_DATA_POINT = eINSTANCE.getNumberDataPoint();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NUMBER_DATA_POINT__ATTRIBUTES = eINSTANCE.getNumberDataPoint_Attributes();

		/**
		 * The meta object literal for the '<em><b>Start Time Unix Nano</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NUMBER_DATA_POINT__START_TIME_UNIX_NANO = eINSTANCE.getNumberDataPoint_StartTimeUnixNano();

		/**
		 * The meta object literal for the '<em><b>Time Unix Nano</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NUMBER_DATA_POINT__TIME_UNIX_NANO = eINSTANCE.getNumberDataPoint_TimeUnixNano();

		/**
		 * The meta object literal for the '<em><b>As Double</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NUMBER_DATA_POINT__AS_DOUBLE = eINSTANCE.getNumberDataPoint_AsDouble();

		/**
		 * The meta object literal for the '<em><b>As Int</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NUMBER_DATA_POINT__AS_INT = eINSTANCE.getNumberDataPoint_AsInt();

		/**
		 * The meta object literal for the '<em><b>Exemplars</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NUMBER_DATA_POINT__EXEMPLARS = eINSTANCE.getNumberDataPoint_Exemplars();

		/**
		 * The meta object literal for the '<em><b>Flags</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NUMBER_DATA_POINT__FLAGS = eINSTANCE.getNumberDataPoint_Flags();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.HistogramDataPointImpl <em>Histogram Data Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.HistogramDataPointImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getHistogramDataPoint()
		 * @generated
		 */
		EClass HISTOGRAM_DATA_POINT = eINSTANCE.getHistogramDataPoint();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HISTOGRAM_DATA_POINT__ATTRIBUTES = eINSTANCE.getHistogramDataPoint_Attributes();

		/**
		 * The meta object literal for the '<em><b>Start Time Unix Nano</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HISTOGRAM_DATA_POINT__START_TIME_UNIX_NANO = eINSTANCE.getHistogramDataPoint_StartTimeUnixNano();

		/**
		 * The meta object literal for the '<em><b>Time Unix Nano</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HISTOGRAM_DATA_POINT__TIME_UNIX_NANO = eINSTANCE.getHistogramDataPoint_TimeUnixNano();

		/**
		 * The meta object literal for the '<em><b>Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HISTOGRAM_DATA_POINT__COUNT = eINSTANCE.getHistogramDataPoint_Count();

		/**
		 * The meta object literal for the '<em><b>Sum</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HISTOGRAM_DATA_POINT__SUM = eINSTANCE.getHistogramDataPoint_Sum();

		/**
		 * The meta object literal for the '<em><b>Bucket Counts</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HISTOGRAM_DATA_POINT__BUCKET_COUNTS = eINSTANCE.getHistogramDataPoint_BucketCounts();

		/**
		 * The meta object literal for the '<em><b>Explicit Bounds</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HISTOGRAM_DATA_POINT__EXPLICIT_BOUNDS = eINSTANCE.getHistogramDataPoint_ExplicitBounds();

		/**
		 * The meta object literal for the '<em><b>Exemplars</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HISTOGRAM_DATA_POINT__EXEMPLARS = eINSTANCE.getHistogramDataPoint_Exemplars();

		/**
		 * The meta object literal for the '<em><b>Flags</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HISTOGRAM_DATA_POINT__FLAGS = eINSTANCE.getHistogramDataPoint_Flags();

		/**
		 * The meta object literal for the '<em><b>Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HISTOGRAM_DATA_POINT__MIN = eINSTANCE.getHistogramDataPoint_Min();

		/**
		 * The meta object literal for the '<em><b>Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HISTOGRAM_DATA_POINT__MAX = eINSTANCE.getHistogramDataPoint_Max();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.ExponentialHistogramDataPointImpl <em>Exponential Histogram Data Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.ExponentialHistogramDataPointImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getExponentialHistogramDataPoint()
		 * @generated
		 */
		EClass EXPONENTIAL_HISTOGRAM_DATA_POINT = eINSTANCE.getExponentialHistogramDataPoint();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPONENTIAL_HISTOGRAM_DATA_POINT__ATTRIBUTES = eINSTANCE.getExponentialHistogramDataPoint_Attributes();

		/**
		 * The meta object literal for the '<em><b>Start Time Unix Nano</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPONENTIAL_HISTOGRAM_DATA_POINT__START_TIME_UNIX_NANO = eINSTANCE.getExponentialHistogramDataPoint_StartTimeUnixNano();

		/**
		 * The meta object literal for the '<em><b>Time Unix Nano</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPONENTIAL_HISTOGRAM_DATA_POINT__TIME_UNIX_NANO = eINSTANCE.getExponentialHistogramDataPoint_TimeUnixNano();

		/**
		 * The meta object literal for the '<em><b>Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPONENTIAL_HISTOGRAM_DATA_POINT__COUNT = eINSTANCE.getExponentialHistogramDataPoint_Count();

		/**
		 * The meta object literal for the '<em><b>Sum</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPONENTIAL_HISTOGRAM_DATA_POINT__SUM = eINSTANCE.getExponentialHistogramDataPoint_Sum();

		/**
		 * The meta object literal for the '<em><b>Scale</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPONENTIAL_HISTOGRAM_DATA_POINT__SCALE = eINSTANCE.getExponentialHistogramDataPoint_Scale();

		/**
		 * The meta object literal for the '<em><b>Zero Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPONENTIAL_HISTOGRAM_DATA_POINT__ZERO_COUNT = eINSTANCE.getExponentialHistogramDataPoint_ZeroCount();

		/**
		 * The meta object literal for the '<em><b>Positive</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPONENTIAL_HISTOGRAM_DATA_POINT__POSITIVE = eINSTANCE.getExponentialHistogramDataPoint_Positive();

		/**
		 * The meta object literal for the '<em><b>Negative</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPONENTIAL_HISTOGRAM_DATA_POINT__NEGATIVE = eINSTANCE.getExponentialHistogramDataPoint_Negative();

		/**
		 * The meta object literal for the '<em><b>Flags</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPONENTIAL_HISTOGRAM_DATA_POINT__FLAGS = eINSTANCE.getExponentialHistogramDataPoint_Flags();

		/**
		 * The meta object literal for the '<em><b>Exemplars</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPONENTIAL_HISTOGRAM_DATA_POINT__EXEMPLARS = eINSTANCE.getExponentialHistogramDataPoint_Exemplars();

		/**
		 * The meta object literal for the '<em><b>Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPONENTIAL_HISTOGRAM_DATA_POINT__MIN = eINSTANCE.getExponentialHistogramDataPoint_Min();

		/**
		 * The meta object literal for the '<em><b>Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPONENTIAL_HISTOGRAM_DATA_POINT__MAX = eINSTANCE.getExponentialHistogramDataPoint_Max();

		/**
		 * The meta object literal for the '<em><b>Zero Threshold</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPONENTIAL_HISTOGRAM_DATA_POINT__ZERO_THRESHOLD = eINSTANCE.getExponentialHistogramDataPoint_ZeroThreshold();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.ExponentialHistogramDataPointBucketsImpl <em>Exponential Histogram Data Point Buckets</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.ExponentialHistogramDataPointBucketsImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getExponentialHistogramDataPointBuckets()
		 * @generated
		 */
		EClass EXPONENTIAL_HISTOGRAM_DATA_POINT_BUCKETS = eINSTANCE.getExponentialHistogramDataPointBuckets();

		/**
		 * The meta object literal for the '<em><b>Offset</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPONENTIAL_HISTOGRAM_DATA_POINT_BUCKETS__OFFSET = eINSTANCE.getExponentialHistogramDataPointBuckets_Offset();

		/**
		 * The meta object literal for the '<em><b>Bucket Counts</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPONENTIAL_HISTOGRAM_DATA_POINT_BUCKETS__BUCKET_COUNTS = eINSTANCE.getExponentialHistogramDataPointBuckets_BucketCounts();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.SummaryDataPointImpl <em>Summary Data Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.SummaryDataPointImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getSummaryDataPoint()
		 * @generated
		 */
		EClass SUMMARY_DATA_POINT = eINSTANCE.getSummaryDataPoint();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUMMARY_DATA_POINT__ATTRIBUTES = eINSTANCE.getSummaryDataPoint_Attributes();

		/**
		 * The meta object literal for the '<em><b>Start Time Unix Nano</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUMMARY_DATA_POINT__START_TIME_UNIX_NANO = eINSTANCE.getSummaryDataPoint_StartTimeUnixNano();

		/**
		 * The meta object literal for the '<em><b>Time Unix Nano</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUMMARY_DATA_POINT__TIME_UNIX_NANO = eINSTANCE.getSummaryDataPoint_TimeUnixNano();

		/**
		 * The meta object literal for the '<em><b>Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUMMARY_DATA_POINT__COUNT = eINSTANCE.getSummaryDataPoint_Count();

		/**
		 * The meta object literal for the '<em><b>Sum</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUMMARY_DATA_POINT__SUM = eINSTANCE.getSummaryDataPoint_Sum();

		/**
		 * The meta object literal for the '<em><b>Quantile Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUMMARY_DATA_POINT__QUANTILE_VALUES = eINSTANCE.getSummaryDataPoint_QuantileValues();

		/**
		 * The meta object literal for the '<em><b>Flags</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUMMARY_DATA_POINT__FLAGS = eINSTANCE.getSummaryDataPoint_Flags();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.SummaryDataPointValueAtQuantileImpl <em>Summary Data Point Value At Quantile</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.SummaryDataPointValueAtQuantileImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getSummaryDataPointValueAtQuantile()
		 * @generated
		 */
		EClass SUMMARY_DATA_POINT_VALUE_AT_QUANTILE = eINSTANCE.getSummaryDataPointValueAtQuantile();

		/**
		 * The meta object literal for the '<em><b>Quantile</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUMMARY_DATA_POINT_VALUE_AT_QUANTILE__QUANTILE = eINSTANCE.getSummaryDataPointValueAtQuantile_Quantile();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUMMARY_DATA_POINT_VALUE_AT_QUANTILE__VALUE = eINSTANCE.getSummaryDataPointValueAtQuantile_Value();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.ExemplarImpl <em>Exemplar</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.ExemplarImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getExemplar()
		 * @generated
		 */
		EClass EXEMPLAR = eINSTANCE.getExemplar();

		/**
		 * The meta object literal for the '<em><b>Filtered Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXEMPLAR__FILTERED_ATTRIBUTES = eINSTANCE.getExemplar_FilteredAttributes();

		/**
		 * The meta object literal for the '<em><b>Time Unix Nano</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEMPLAR__TIME_UNIX_NANO = eINSTANCE.getExemplar_TimeUnixNano();

		/**
		 * The meta object literal for the '<em><b>As Double</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEMPLAR__AS_DOUBLE = eINSTANCE.getExemplar_AsDouble();

		/**
		 * The meta object literal for the '<em><b>As Int</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEMPLAR__AS_INT = eINSTANCE.getExemplar_AsInt();

		/**
		 * The meta object literal for the '<em><b>Span Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEMPLAR__SPAN_ID = eINSTANCE.getExemplar_SpanId();

		/**
		 * The meta object literal for the '<em><b>Trace Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEMPLAR__TRACE_ID = eINSTANCE.getExemplar_TraceId();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.LogsDataImpl <em>Logs Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.LogsDataImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getLogsData()
		 * @generated
		 */
		EClass LOGS_DATA = eINSTANCE.getLogsData();

		/**
		 * The meta object literal for the '<em><b>Resource Logs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOGS_DATA__RESOURCE_LOGS = eINSTANCE.getLogsData_ResourceLogs();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.ResourceLogsImpl <em>Resource Logs</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.ResourceLogsImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getResourceLogs()
		 * @generated
		 */
		EClass RESOURCE_LOGS = eINSTANCE.getResourceLogs();

		/**
		 * The meta object literal for the '<em><b>Resource</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_LOGS__RESOURCE = eINSTANCE.getResourceLogs_Resource();

		/**
		 * The meta object literal for the '<em><b>Scope Logs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_LOGS__SCOPE_LOGS = eINSTANCE.getResourceLogs_ScopeLogs();

		/**
		 * The meta object literal for the '<em><b>Schema Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_LOGS__SCHEMA_URL = eINSTANCE.getResourceLogs_SchemaUrl();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.ScopeLogsImpl <em>Scope Logs</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.ScopeLogsImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getScopeLogs()
		 * @generated
		 */
		EClass SCOPE_LOGS = eINSTANCE.getScopeLogs();

		/**
		 * The meta object literal for the '<em><b>Scope</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCOPE_LOGS__SCOPE = eINSTANCE.getScopeLogs_Scope();

		/**
		 * The meta object literal for the '<em><b>Log Records</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCOPE_LOGS__LOG_RECORDS = eINSTANCE.getScopeLogs_LogRecords();

		/**
		 * The meta object literal for the '<em><b>Schema Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCOPE_LOGS__SCHEMA_URL = eINSTANCE.getScopeLogs_SchemaUrl();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.impl.LogRecordImpl <em>Log Record</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.impl.LogRecordImpl
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getLogRecord()
		 * @generated
		 */
		EClass LOG_RECORD = eINSTANCE.getLogRecord();

		/**
		 * The meta object literal for the '<em><b>Time Unix Nano</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOG_RECORD__TIME_UNIX_NANO = eINSTANCE.getLogRecord_TimeUnixNano();

		/**
		 * The meta object literal for the '<em><b>Observed Time Unix Nano</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOG_RECORD__OBSERVED_TIME_UNIX_NANO = eINSTANCE.getLogRecord_ObservedTimeUnixNano();

		/**
		 * The meta object literal for the '<em><b>Severity Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOG_RECORD__SEVERITY_NUMBER = eINSTANCE.getLogRecord_SeverityNumber();

		/**
		 * The meta object literal for the '<em><b>Severity Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOG_RECORD__SEVERITY_TEXT = eINSTANCE.getLogRecord_SeverityText();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOG_RECORD__BODY = eINSTANCE.getLogRecord_Body();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOG_RECORD__ATTRIBUTES = eINSTANCE.getLogRecord_Attributes();

		/**
		 * The meta object literal for the '<em><b>Dropped Attributes Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOG_RECORD__DROPPED_ATTRIBUTES_COUNT = eINSTANCE.getLogRecord_DroppedAttributesCount();

		/**
		 * The meta object literal for the '<em><b>Flags</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOG_RECORD__FLAGS = eINSTANCE.getLogRecord_Flags();

		/**
		 * The meta object literal for the '<em><b>Trace Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOG_RECORD__TRACE_ID = eINSTANCE.getLogRecord_TraceId();

		/**
		 * The meta object literal for the '<em><b>Span Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOG_RECORD__SPAN_ID = eINSTANCE.getLogRecord_SpanId();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.SpanKind <em>Span Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.SpanKind
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getSpanKind()
		 * @generated
		 */
		EEnum SPAN_KIND = eINSTANCE.getSpanKind();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.StatusCode <em>Status Code</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.StatusCode
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getStatusCode()
		 * @generated
		 */
		EEnum STATUS_CODE = eINSTANCE.getStatusCode();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.AggregationTemporality <em>Aggregation Temporality</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.AggregationTemporality
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getAggregationTemporality()
		 * @generated
		 */
		EEnum AGGREGATION_TEMPORALITY = eINSTANCE.getAggregationTemporality();

		/**
		 * The meta object literal for the '{@link org.nasdanika.telemetry.model.SeverityNumber <em>Severity Number</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.telemetry.model.SeverityNumber
		 * @see org.nasdanika.telemetry.model.impl.ModelPackageImpl#getSeverityNumber()
		 * @generated
		 */
		EEnum SEVERITY_NUMBER = eINSTANCE.getSeverityNumber();

	}

} //ModelPackage
