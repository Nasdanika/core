/**
 */
package org.nasdanika.telemetry.model.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.change.ChangePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.nasdanika.telemetry.model.AggregationTemporality;
import org.nasdanika.telemetry.model.AnyValue;
import org.nasdanika.telemetry.model.Exemplar;
import org.nasdanika.telemetry.model.ExponentialHistogram;
import org.nasdanika.telemetry.model.ExponentialHistogramDataPoint;
import org.nasdanika.telemetry.model.ExponentialHistogramDataPointBuckets;
import org.nasdanika.telemetry.model.Gauge;
import org.nasdanika.telemetry.model.Histogram;
import org.nasdanika.telemetry.model.HistogramDataPoint;
import org.nasdanika.telemetry.model.InstrumentationScope;
import org.nasdanika.telemetry.model.KeyValue;
import org.nasdanika.telemetry.model.LogRecord;
import org.nasdanika.telemetry.model.LogsData;
import org.nasdanika.telemetry.model.Metric;
import org.nasdanika.telemetry.model.MetricsData;
import org.nasdanika.telemetry.model.ModelFactory;
import org.nasdanika.telemetry.model.ModelPackage;
import org.nasdanika.telemetry.model.NumberDataPoint;
import org.nasdanika.telemetry.model.Resource;
import org.nasdanika.telemetry.model.ResourceLogs;
import org.nasdanika.telemetry.model.ResourceMetrics;
import org.nasdanika.telemetry.model.ResourceSpans;
import org.nasdanika.telemetry.model.ScopeLogs;
import org.nasdanika.telemetry.model.ScopeMetrics;
import org.nasdanika.telemetry.model.ScopeSpans;
import org.nasdanika.telemetry.model.SeverityNumber;
import org.nasdanika.telemetry.model.Span;
import org.nasdanika.telemetry.model.SpanEvent;
import org.nasdanika.telemetry.model.SpanKind;
import org.nasdanika.telemetry.model.SpanLink;
import org.nasdanika.telemetry.model.SpanStatus;
import org.nasdanika.telemetry.model.StatusCode;
import org.nasdanika.telemetry.model.Sum;
import org.nasdanika.telemetry.model.Summary;
import org.nasdanika.telemetry.model.SummaryDataPoint;
import org.nasdanika.telemetry.model.SummaryDataPointValueAtQuantile;
import org.nasdanika.telemetry.model.TracesData;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelPackageImpl extends EPackageImpl implements ModelPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass instrumentationScopeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass keyValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass anyValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tracesDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceSpansEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scopeSpansEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass spanEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass spanEventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass spanLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass spanStatusEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass metricsDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceMetricsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scopeMetricsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass metricEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gaugeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sumEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass histogramEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exponentialHistogramEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass summaryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass numberDataPointEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass histogramDataPointEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exponentialHistogramDataPointEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exponentialHistogramDataPointBucketsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass summaryDataPointEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass summaryDataPointValueAtQuantileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exemplarEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass logsDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceLogsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scopeLogsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass logRecordEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum spanKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum statusCodeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum aggregationTemporalityEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum severityNumberEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.nasdanika.telemetry.model.ModelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ModelPackageImpl() {
		super(eNS_URI, ModelFactory.eINSTANCE);
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link ModelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ModelPackage init() {
		if (isInited) return (ModelPackage)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredModelPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		ModelPackageImpl theModelPackage = registeredModelPackage instanceof ModelPackageImpl ? (ModelPackageImpl)registeredModelPackage : new ModelPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		ChangePackage.eINSTANCE.eClass();
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theModelPackage.createPackageContents();

		// Initialize created meta-data
		theModelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theModelPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ModelPackage.eNS_URI, theModelPackage);
		return theModelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getResource() {
		return resourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getResource_Attributes() {
		return (EReference)resourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getResource_DroppedAttributesCount() {
		return (EAttribute)resourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getResource_SchemaUrl() {
		return (EAttribute)resourceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getInstrumentationScope() {
		return instrumentationScopeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInstrumentationScope_Name() {
		return (EAttribute)instrumentationScopeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInstrumentationScope_Version() {
		return (EAttribute)instrumentationScopeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getInstrumentationScope_Attributes() {
		return (EReference)instrumentationScopeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInstrumentationScope_DroppedAttributesCount() {
		return (EAttribute)instrumentationScopeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInstrumentationScope_SchemaUrl() {
		return (EAttribute)instrumentationScopeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getKeyValue() {
		return keyValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getKeyValue_Key() {
		return (EAttribute)keyValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getKeyValue_Value() {
		return (EReference)keyValueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAnyValue() {
		return anyValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAnyValue_StringValue() {
		return (EAttribute)anyValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAnyValue_BoolValue() {
		return (EAttribute)anyValueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAnyValue_IntValue() {
		return (EAttribute)anyValueEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAnyValue_DoubleValue() {
		return (EAttribute)anyValueEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAnyValue_ArrayValue() {
		return (EReference)anyValueEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAnyValue_KvlistValue() {
		return (EReference)anyValueEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAnyValue_BytesValue() {
		return (EAttribute)anyValueEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTracesData() {
		return tracesDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTracesData_ResourceSpans() {
		return (EReference)tracesDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getResourceSpans() {
		return resourceSpansEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getResourceSpans_Resource() {
		return (EReference)resourceSpansEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getResourceSpans_ScopeSpans() {
		return (EReference)resourceSpansEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getResourceSpans_SchemaUrl() {
		return (EAttribute)resourceSpansEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScopeSpans() {
		return scopeSpansEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScopeSpans_Scope() {
		return (EReference)scopeSpansEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScopeSpans_Spans() {
		return (EReference)scopeSpansEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScopeSpans_SchemaUrl() {
		return (EAttribute)scopeSpansEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSpan() {
		return spanEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpan_TraceId() {
		return (EAttribute)spanEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpan_SpanId() {
		return (EAttribute)spanEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpan_TraceState() {
		return (EAttribute)spanEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpan_ParentSpanId() {
		return (EAttribute)spanEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpan_Name() {
		return (EAttribute)spanEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpan_Kind() {
		return (EAttribute)spanEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpan_StartTimeUnixNano() {
		return (EAttribute)spanEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpan_EndTimeUnixNano() {
		return (EAttribute)spanEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSpan_Attributes() {
		return (EReference)spanEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpan_DroppedAttributesCount() {
		return (EAttribute)spanEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSpan_Events() {
		return (EReference)spanEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpan_DroppedEventsCount() {
		return (EAttribute)spanEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSpan_Links() {
		return (EReference)spanEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpan_DroppedLinksCount() {
		return (EAttribute)spanEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSpan_Status() {
		return (EReference)spanEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpan_Flags() {
		return (EAttribute)spanEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSpan_ChangeDescription() {
		return (EReference)spanEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSpanEvent() {
		return spanEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpanEvent_TimeUnixNano() {
		return (EAttribute)spanEventEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpanEvent_Name() {
		return (EAttribute)spanEventEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSpanEvent_Attributes() {
		return (EReference)spanEventEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpanEvent_DroppedAttributesCount() {
		return (EAttribute)spanEventEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSpanLink() {
		return spanLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpanLink_TraceId() {
		return (EAttribute)spanLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpanLink_SpanId() {
		return (EAttribute)spanLinkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpanLink_TraceState() {
		return (EAttribute)spanLinkEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSpanLink_Attributes() {
		return (EReference)spanLinkEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpanLink_DroppedAttributesCount() {
		return (EAttribute)spanLinkEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpanLink_Flags() {
		return (EAttribute)spanLinkEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSpanStatus() {
		return spanStatusEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpanStatus_Message() {
		return (EAttribute)spanStatusEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpanStatus_Code() {
		return (EAttribute)spanStatusEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMetricsData() {
		return metricsDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMetricsData_ResourceMetrics() {
		return (EReference)metricsDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getResourceMetrics() {
		return resourceMetricsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getResourceMetrics_Resource() {
		return (EReference)resourceMetricsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getResourceMetrics_ScopeMetrics() {
		return (EReference)resourceMetricsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getResourceMetrics_SchemaUrl() {
		return (EAttribute)resourceMetricsEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScopeMetrics() {
		return scopeMetricsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScopeMetrics_Scope() {
		return (EReference)scopeMetricsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScopeMetrics_Metrics() {
		return (EReference)scopeMetricsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScopeMetrics_SchemaUrl() {
		return (EAttribute)scopeMetricsEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMetric() {
		return metricEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMetric_Name() {
		return (EAttribute)metricEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMetric_Description() {
		return (EAttribute)metricEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMetric_Unit() {
		return (EAttribute)metricEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMetric_Metadata() {
		return (EReference)metricEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGauge() {
		return gaugeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getGauge_DataPoints() {
		return (EReference)gaugeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSum() {
		return sumEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSum_DataPoints() {
		return (EReference)sumEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSum_AggregationTemporality() {
		return (EAttribute)sumEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSum_IsMonotonic() {
		return (EAttribute)sumEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getHistogram() {
		return histogramEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getHistogram_DataPoints() {
		return (EReference)histogramEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHistogram_AggregationTemporality() {
		return (EAttribute)histogramEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getExponentialHistogram() {
		return exponentialHistogramEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getExponentialHistogram_DataPoints() {
		return (EReference)exponentialHistogramEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExponentialHistogram_AggregationTemporality() {
		return (EAttribute)exponentialHistogramEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSummary() {
		return summaryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSummary_DataPoints() {
		return (EReference)summaryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNumberDataPoint() {
		return numberDataPointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getNumberDataPoint_Attributes() {
		return (EReference)numberDataPointEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNumberDataPoint_StartTimeUnixNano() {
		return (EAttribute)numberDataPointEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNumberDataPoint_TimeUnixNano() {
		return (EAttribute)numberDataPointEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNumberDataPoint_AsDouble() {
		return (EAttribute)numberDataPointEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNumberDataPoint_AsInt() {
		return (EAttribute)numberDataPointEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getNumberDataPoint_Exemplars() {
		return (EReference)numberDataPointEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNumberDataPoint_Flags() {
		return (EAttribute)numberDataPointEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getHistogramDataPoint() {
		return histogramDataPointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getHistogramDataPoint_Attributes() {
		return (EReference)histogramDataPointEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHistogramDataPoint_StartTimeUnixNano() {
		return (EAttribute)histogramDataPointEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHistogramDataPoint_TimeUnixNano() {
		return (EAttribute)histogramDataPointEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHistogramDataPoint_Count() {
		return (EAttribute)histogramDataPointEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHistogramDataPoint_Sum() {
		return (EAttribute)histogramDataPointEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHistogramDataPoint_BucketCounts() {
		return (EAttribute)histogramDataPointEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHistogramDataPoint_ExplicitBounds() {
		return (EAttribute)histogramDataPointEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getHistogramDataPoint_Exemplars() {
		return (EReference)histogramDataPointEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHistogramDataPoint_Flags() {
		return (EAttribute)histogramDataPointEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHistogramDataPoint_Min() {
		return (EAttribute)histogramDataPointEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHistogramDataPoint_Max() {
		return (EAttribute)histogramDataPointEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getExponentialHistogramDataPoint() {
		return exponentialHistogramDataPointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getExponentialHistogramDataPoint_Attributes() {
		return (EReference)exponentialHistogramDataPointEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExponentialHistogramDataPoint_StartTimeUnixNano() {
		return (EAttribute)exponentialHistogramDataPointEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExponentialHistogramDataPoint_TimeUnixNano() {
		return (EAttribute)exponentialHistogramDataPointEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExponentialHistogramDataPoint_Count() {
		return (EAttribute)exponentialHistogramDataPointEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExponentialHistogramDataPoint_Sum() {
		return (EAttribute)exponentialHistogramDataPointEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExponentialHistogramDataPoint_Scale() {
		return (EAttribute)exponentialHistogramDataPointEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExponentialHistogramDataPoint_ZeroCount() {
		return (EAttribute)exponentialHistogramDataPointEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getExponentialHistogramDataPoint_Positive() {
		return (EReference)exponentialHistogramDataPointEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getExponentialHistogramDataPoint_Negative() {
		return (EReference)exponentialHistogramDataPointEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExponentialHistogramDataPoint_Flags() {
		return (EAttribute)exponentialHistogramDataPointEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getExponentialHistogramDataPoint_Exemplars() {
		return (EReference)exponentialHistogramDataPointEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExponentialHistogramDataPoint_Min() {
		return (EAttribute)exponentialHistogramDataPointEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExponentialHistogramDataPoint_Max() {
		return (EAttribute)exponentialHistogramDataPointEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExponentialHistogramDataPoint_ZeroThreshold() {
		return (EAttribute)exponentialHistogramDataPointEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getExponentialHistogramDataPointBuckets() {
		return exponentialHistogramDataPointBucketsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExponentialHistogramDataPointBuckets_Offset() {
		return (EAttribute)exponentialHistogramDataPointBucketsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExponentialHistogramDataPointBuckets_BucketCounts() {
		return (EAttribute)exponentialHistogramDataPointBucketsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSummaryDataPoint() {
		return summaryDataPointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSummaryDataPoint_Attributes() {
		return (EReference)summaryDataPointEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSummaryDataPoint_StartTimeUnixNano() {
		return (EAttribute)summaryDataPointEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSummaryDataPoint_TimeUnixNano() {
		return (EAttribute)summaryDataPointEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSummaryDataPoint_Count() {
		return (EAttribute)summaryDataPointEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSummaryDataPoint_Sum() {
		return (EAttribute)summaryDataPointEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSummaryDataPoint_QuantileValues() {
		return (EReference)summaryDataPointEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSummaryDataPoint_Flags() {
		return (EAttribute)summaryDataPointEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSummaryDataPointValueAtQuantile() {
		return summaryDataPointValueAtQuantileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSummaryDataPointValueAtQuantile_Quantile() {
		return (EAttribute)summaryDataPointValueAtQuantileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSummaryDataPointValueAtQuantile_Value() {
		return (EAttribute)summaryDataPointValueAtQuantileEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getExemplar() {
		return exemplarEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getExemplar_FilteredAttributes() {
		return (EReference)exemplarEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExemplar_TimeUnixNano() {
		return (EAttribute)exemplarEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExemplar_AsDouble() {
		return (EAttribute)exemplarEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExemplar_AsInt() {
		return (EAttribute)exemplarEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExemplar_SpanId() {
		return (EAttribute)exemplarEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExemplar_TraceId() {
		return (EAttribute)exemplarEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLogsData() {
		return logsDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLogsData_ResourceLogs() {
		return (EReference)logsDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getResourceLogs() {
		return resourceLogsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getResourceLogs_Resource() {
		return (EReference)resourceLogsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getResourceLogs_ScopeLogs() {
		return (EReference)resourceLogsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getResourceLogs_SchemaUrl() {
		return (EAttribute)resourceLogsEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScopeLogs() {
		return scopeLogsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScopeLogs_Scope() {
		return (EReference)scopeLogsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScopeLogs_LogRecords() {
		return (EReference)scopeLogsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScopeLogs_SchemaUrl() {
		return (EAttribute)scopeLogsEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLogRecord() {
		return logRecordEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLogRecord_TimeUnixNano() {
		return (EAttribute)logRecordEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLogRecord_ObservedTimeUnixNano() {
		return (EAttribute)logRecordEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLogRecord_SeverityNumber() {
		return (EAttribute)logRecordEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLogRecord_SeverityText() {
		return (EAttribute)logRecordEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLogRecord_Body() {
		return (EReference)logRecordEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLogRecord_Attributes() {
		return (EReference)logRecordEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLogRecord_DroppedAttributesCount() {
		return (EAttribute)logRecordEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLogRecord_Flags() {
		return (EAttribute)logRecordEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLogRecord_TraceId() {
		return (EAttribute)logRecordEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLogRecord_SpanId() {
		return (EAttribute)logRecordEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getSpanKind() {
		return spanKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getStatusCode() {
		return statusCodeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getAggregationTemporality() {
		return aggregationTemporalityEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getSeverityNumber() {
		return severityNumberEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ModelFactory getModelFactory() {
		return (ModelFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		resourceEClass = createEClass(RESOURCE);
		createEReference(resourceEClass, RESOURCE__ATTRIBUTES);
		createEAttribute(resourceEClass, RESOURCE__DROPPED_ATTRIBUTES_COUNT);
		createEAttribute(resourceEClass, RESOURCE__SCHEMA_URL);

		instrumentationScopeEClass = createEClass(INSTRUMENTATION_SCOPE);
		createEAttribute(instrumentationScopeEClass, INSTRUMENTATION_SCOPE__NAME);
		createEAttribute(instrumentationScopeEClass, INSTRUMENTATION_SCOPE__VERSION);
		createEReference(instrumentationScopeEClass, INSTRUMENTATION_SCOPE__ATTRIBUTES);
		createEAttribute(instrumentationScopeEClass, INSTRUMENTATION_SCOPE__DROPPED_ATTRIBUTES_COUNT);
		createEAttribute(instrumentationScopeEClass, INSTRUMENTATION_SCOPE__SCHEMA_URL);

		keyValueEClass = createEClass(KEY_VALUE);
		createEAttribute(keyValueEClass, KEY_VALUE__KEY);
		createEReference(keyValueEClass, KEY_VALUE__VALUE);

		anyValueEClass = createEClass(ANY_VALUE);
		createEAttribute(anyValueEClass, ANY_VALUE__STRING_VALUE);
		createEAttribute(anyValueEClass, ANY_VALUE__BOOL_VALUE);
		createEAttribute(anyValueEClass, ANY_VALUE__INT_VALUE);
		createEAttribute(anyValueEClass, ANY_VALUE__DOUBLE_VALUE);
		createEReference(anyValueEClass, ANY_VALUE__ARRAY_VALUE);
		createEReference(anyValueEClass, ANY_VALUE__KVLIST_VALUE);
		createEAttribute(anyValueEClass, ANY_VALUE__BYTES_VALUE);

		tracesDataEClass = createEClass(TRACES_DATA);
		createEReference(tracesDataEClass, TRACES_DATA__RESOURCE_SPANS);

		resourceSpansEClass = createEClass(RESOURCE_SPANS);
		createEReference(resourceSpansEClass, RESOURCE_SPANS__RESOURCE);
		createEReference(resourceSpansEClass, RESOURCE_SPANS__SCOPE_SPANS);
		createEAttribute(resourceSpansEClass, RESOURCE_SPANS__SCHEMA_URL);

		scopeSpansEClass = createEClass(SCOPE_SPANS);
		createEReference(scopeSpansEClass, SCOPE_SPANS__SCOPE);
		createEReference(scopeSpansEClass, SCOPE_SPANS__SPANS);
		createEAttribute(scopeSpansEClass, SCOPE_SPANS__SCHEMA_URL);

		spanEClass = createEClass(SPAN);
		createEAttribute(spanEClass, SPAN__TRACE_ID);
		createEAttribute(spanEClass, SPAN__SPAN_ID);
		createEAttribute(spanEClass, SPAN__TRACE_STATE);
		createEAttribute(spanEClass, SPAN__PARENT_SPAN_ID);
		createEAttribute(spanEClass, SPAN__NAME);
		createEAttribute(spanEClass, SPAN__KIND);
		createEAttribute(spanEClass, SPAN__START_TIME_UNIX_NANO);
		createEAttribute(spanEClass, SPAN__END_TIME_UNIX_NANO);
		createEReference(spanEClass, SPAN__ATTRIBUTES);
		createEAttribute(spanEClass, SPAN__DROPPED_ATTRIBUTES_COUNT);
		createEReference(spanEClass, SPAN__EVENTS);
		createEAttribute(spanEClass, SPAN__DROPPED_EVENTS_COUNT);
		createEReference(spanEClass, SPAN__LINKS);
		createEAttribute(spanEClass, SPAN__DROPPED_LINKS_COUNT);
		createEReference(spanEClass, SPAN__STATUS);
		createEAttribute(spanEClass, SPAN__FLAGS);
		createEReference(spanEClass, SPAN__CHANGE_DESCRIPTION);

		spanEventEClass = createEClass(SPAN_EVENT);
		createEAttribute(spanEventEClass, SPAN_EVENT__TIME_UNIX_NANO);
		createEAttribute(spanEventEClass, SPAN_EVENT__NAME);
		createEReference(spanEventEClass, SPAN_EVENT__ATTRIBUTES);
		createEAttribute(spanEventEClass, SPAN_EVENT__DROPPED_ATTRIBUTES_COUNT);

		spanLinkEClass = createEClass(SPAN_LINK);
		createEAttribute(spanLinkEClass, SPAN_LINK__TRACE_ID);
		createEAttribute(spanLinkEClass, SPAN_LINK__SPAN_ID);
		createEAttribute(spanLinkEClass, SPAN_LINK__TRACE_STATE);
		createEReference(spanLinkEClass, SPAN_LINK__ATTRIBUTES);
		createEAttribute(spanLinkEClass, SPAN_LINK__DROPPED_ATTRIBUTES_COUNT);
		createEAttribute(spanLinkEClass, SPAN_LINK__FLAGS);

		spanStatusEClass = createEClass(SPAN_STATUS);
		createEAttribute(spanStatusEClass, SPAN_STATUS__MESSAGE);
		createEAttribute(spanStatusEClass, SPAN_STATUS__CODE);

		metricsDataEClass = createEClass(METRICS_DATA);
		createEReference(metricsDataEClass, METRICS_DATA__RESOURCE_METRICS);

		resourceMetricsEClass = createEClass(RESOURCE_METRICS);
		createEReference(resourceMetricsEClass, RESOURCE_METRICS__RESOURCE);
		createEReference(resourceMetricsEClass, RESOURCE_METRICS__SCOPE_METRICS);
		createEAttribute(resourceMetricsEClass, RESOURCE_METRICS__SCHEMA_URL);

		scopeMetricsEClass = createEClass(SCOPE_METRICS);
		createEReference(scopeMetricsEClass, SCOPE_METRICS__SCOPE);
		createEReference(scopeMetricsEClass, SCOPE_METRICS__METRICS);
		createEAttribute(scopeMetricsEClass, SCOPE_METRICS__SCHEMA_URL);

		metricEClass = createEClass(METRIC);
		createEAttribute(metricEClass, METRIC__NAME);
		createEAttribute(metricEClass, METRIC__DESCRIPTION);
		createEAttribute(metricEClass, METRIC__UNIT);
		createEReference(metricEClass, METRIC__METADATA);

		gaugeEClass = createEClass(GAUGE);
		createEReference(gaugeEClass, GAUGE__DATA_POINTS);

		sumEClass = createEClass(SUM);
		createEReference(sumEClass, SUM__DATA_POINTS);
		createEAttribute(sumEClass, SUM__AGGREGATION_TEMPORALITY);
		createEAttribute(sumEClass, SUM__IS_MONOTONIC);

		histogramEClass = createEClass(HISTOGRAM);
		createEReference(histogramEClass, HISTOGRAM__DATA_POINTS);
		createEAttribute(histogramEClass, HISTOGRAM__AGGREGATION_TEMPORALITY);

		exponentialHistogramEClass = createEClass(EXPONENTIAL_HISTOGRAM);
		createEReference(exponentialHistogramEClass, EXPONENTIAL_HISTOGRAM__DATA_POINTS);
		createEAttribute(exponentialHistogramEClass, EXPONENTIAL_HISTOGRAM__AGGREGATION_TEMPORALITY);

		summaryEClass = createEClass(SUMMARY);
		createEReference(summaryEClass, SUMMARY__DATA_POINTS);

		numberDataPointEClass = createEClass(NUMBER_DATA_POINT);
		createEReference(numberDataPointEClass, NUMBER_DATA_POINT__ATTRIBUTES);
		createEAttribute(numberDataPointEClass, NUMBER_DATA_POINT__START_TIME_UNIX_NANO);
		createEAttribute(numberDataPointEClass, NUMBER_DATA_POINT__TIME_UNIX_NANO);
		createEAttribute(numberDataPointEClass, NUMBER_DATA_POINT__AS_DOUBLE);
		createEAttribute(numberDataPointEClass, NUMBER_DATA_POINT__AS_INT);
		createEReference(numberDataPointEClass, NUMBER_DATA_POINT__EXEMPLARS);
		createEAttribute(numberDataPointEClass, NUMBER_DATA_POINT__FLAGS);

		histogramDataPointEClass = createEClass(HISTOGRAM_DATA_POINT);
		createEReference(histogramDataPointEClass, HISTOGRAM_DATA_POINT__ATTRIBUTES);
		createEAttribute(histogramDataPointEClass, HISTOGRAM_DATA_POINT__START_TIME_UNIX_NANO);
		createEAttribute(histogramDataPointEClass, HISTOGRAM_DATA_POINT__TIME_UNIX_NANO);
		createEAttribute(histogramDataPointEClass, HISTOGRAM_DATA_POINT__COUNT);
		createEAttribute(histogramDataPointEClass, HISTOGRAM_DATA_POINT__SUM);
		createEAttribute(histogramDataPointEClass, HISTOGRAM_DATA_POINT__BUCKET_COUNTS);
		createEAttribute(histogramDataPointEClass, HISTOGRAM_DATA_POINT__EXPLICIT_BOUNDS);
		createEReference(histogramDataPointEClass, HISTOGRAM_DATA_POINT__EXEMPLARS);
		createEAttribute(histogramDataPointEClass, HISTOGRAM_DATA_POINT__FLAGS);
		createEAttribute(histogramDataPointEClass, HISTOGRAM_DATA_POINT__MIN);
		createEAttribute(histogramDataPointEClass, HISTOGRAM_DATA_POINT__MAX);

		exponentialHistogramDataPointEClass = createEClass(EXPONENTIAL_HISTOGRAM_DATA_POINT);
		createEReference(exponentialHistogramDataPointEClass, EXPONENTIAL_HISTOGRAM_DATA_POINT__ATTRIBUTES);
		createEAttribute(exponentialHistogramDataPointEClass, EXPONENTIAL_HISTOGRAM_DATA_POINT__START_TIME_UNIX_NANO);
		createEAttribute(exponentialHistogramDataPointEClass, EXPONENTIAL_HISTOGRAM_DATA_POINT__TIME_UNIX_NANO);
		createEAttribute(exponentialHistogramDataPointEClass, EXPONENTIAL_HISTOGRAM_DATA_POINT__COUNT);
		createEAttribute(exponentialHistogramDataPointEClass, EXPONENTIAL_HISTOGRAM_DATA_POINT__SUM);
		createEAttribute(exponentialHistogramDataPointEClass, EXPONENTIAL_HISTOGRAM_DATA_POINT__SCALE);
		createEAttribute(exponentialHistogramDataPointEClass, EXPONENTIAL_HISTOGRAM_DATA_POINT__ZERO_COUNT);
		createEReference(exponentialHistogramDataPointEClass, EXPONENTIAL_HISTOGRAM_DATA_POINT__POSITIVE);
		createEReference(exponentialHistogramDataPointEClass, EXPONENTIAL_HISTOGRAM_DATA_POINT__NEGATIVE);
		createEAttribute(exponentialHistogramDataPointEClass, EXPONENTIAL_HISTOGRAM_DATA_POINT__FLAGS);
		createEReference(exponentialHistogramDataPointEClass, EXPONENTIAL_HISTOGRAM_DATA_POINT__EXEMPLARS);
		createEAttribute(exponentialHistogramDataPointEClass, EXPONENTIAL_HISTOGRAM_DATA_POINT__MIN);
		createEAttribute(exponentialHistogramDataPointEClass, EXPONENTIAL_HISTOGRAM_DATA_POINT__MAX);
		createEAttribute(exponentialHistogramDataPointEClass, EXPONENTIAL_HISTOGRAM_DATA_POINT__ZERO_THRESHOLD);

		exponentialHistogramDataPointBucketsEClass = createEClass(EXPONENTIAL_HISTOGRAM_DATA_POINT_BUCKETS);
		createEAttribute(exponentialHistogramDataPointBucketsEClass, EXPONENTIAL_HISTOGRAM_DATA_POINT_BUCKETS__OFFSET);
		createEAttribute(exponentialHistogramDataPointBucketsEClass, EXPONENTIAL_HISTOGRAM_DATA_POINT_BUCKETS__BUCKET_COUNTS);

		summaryDataPointEClass = createEClass(SUMMARY_DATA_POINT);
		createEReference(summaryDataPointEClass, SUMMARY_DATA_POINT__ATTRIBUTES);
		createEAttribute(summaryDataPointEClass, SUMMARY_DATA_POINT__START_TIME_UNIX_NANO);
		createEAttribute(summaryDataPointEClass, SUMMARY_DATA_POINT__TIME_UNIX_NANO);
		createEAttribute(summaryDataPointEClass, SUMMARY_DATA_POINT__COUNT);
		createEAttribute(summaryDataPointEClass, SUMMARY_DATA_POINT__SUM);
		createEReference(summaryDataPointEClass, SUMMARY_DATA_POINT__QUANTILE_VALUES);
		createEAttribute(summaryDataPointEClass, SUMMARY_DATA_POINT__FLAGS);

		summaryDataPointValueAtQuantileEClass = createEClass(SUMMARY_DATA_POINT_VALUE_AT_QUANTILE);
		createEAttribute(summaryDataPointValueAtQuantileEClass, SUMMARY_DATA_POINT_VALUE_AT_QUANTILE__QUANTILE);
		createEAttribute(summaryDataPointValueAtQuantileEClass, SUMMARY_DATA_POINT_VALUE_AT_QUANTILE__VALUE);

		exemplarEClass = createEClass(EXEMPLAR);
		createEReference(exemplarEClass, EXEMPLAR__FILTERED_ATTRIBUTES);
		createEAttribute(exemplarEClass, EXEMPLAR__TIME_UNIX_NANO);
		createEAttribute(exemplarEClass, EXEMPLAR__AS_DOUBLE);
		createEAttribute(exemplarEClass, EXEMPLAR__AS_INT);
		createEAttribute(exemplarEClass, EXEMPLAR__SPAN_ID);
		createEAttribute(exemplarEClass, EXEMPLAR__TRACE_ID);

		logsDataEClass = createEClass(LOGS_DATA);
		createEReference(logsDataEClass, LOGS_DATA__RESOURCE_LOGS);

		resourceLogsEClass = createEClass(RESOURCE_LOGS);
		createEReference(resourceLogsEClass, RESOURCE_LOGS__RESOURCE);
		createEReference(resourceLogsEClass, RESOURCE_LOGS__SCOPE_LOGS);
		createEAttribute(resourceLogsEClass, RESOURCE_LOGS__SCHEMA_URL);

		scopeLogsEClass = createEClass(SCOPE_LOGS);
		createEReference(scopeLogsEClass, SCOPE_LOGS__SCOPE);
		createEReference(scopeLogsEClass, SCOPE_LOGS__LOG_RECORDS);
		createEAttribute(scopeLogsEClass, SCOPE_LOGS__SCHEMA_URL);

		logRecordEClass = createEClass(LOG_RECORD);
		createEAttribute(logRecordEClass, LOG_RECORD__TIME_UNIX_NANO);
		createEAttribute(logRecordEClass, LOG_RECORD__OBSERVED_TIME_UNIX_NANO);
		createEAttribute(logRecordEClass, LOG_RECORD__SEVERITY_NUMBER);
		createEAttribute(logRecordEClass, LOG_RECORD__SEVERITY_TEXT);
		createEReference(logRecordEClass, LOG_RECORD__BODY);
		createEReference(logRecordEClass, LOG_RECORD__ATTRIBUTES);
		createEAttribute(logRecordEClass, LOG_RECORD__DROPPED_ATTRIBUTES_COUNT);
		createEAttribute(logRecordEClass, LOG_RECORD__FLAGS);
		createEAttribute(logRecordEClass, LOG_RECORD__TRACE_ID);
		createEAttribute(logRecordEClass, LOG_RECORD__SPAN_ID);

		// Create enums
		spanKindEEnum = createEEnum(SPAN_KIND);
		statusCodeEEnum = createEEnum(STATUS_CODE);
		aggregationTemporalityEEnum = createEEnum(AGGREGATION_TEMPORALITY);
		severityNumberEEnum = createEEnum(SEVERITY_NUMBER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		ChangePackage theChangePackage = (ChangePackage)EPackage.Registry.INSTANCE.getEPackage(ChangePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		gaugeEClass.getESuperTypes().add(this.getMetric());
		sumEClass.getESuperTypes().add(this.getMetric());
		histogramEClass.getESuperTypes().add(this.getMetric());
		exponentialHistogramEClass.getESuperTypes().add(this.getMetric());
		summaryEClass.getESuperTypes().add(this.getMetric());

		// Initialize classes, features, and operations; add parameters
		initEClass(resourceEClass, Resource.class, "Resource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResource_Attributes(), this.getKeyValue(), null, "attributes", null, 0, -1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResource_DroppedAttributesCount(), ecorePackage.getEInt(), "droppedAttributesCount", null, 0, 1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResource_SchemaUrl(), ecorePackage.getEString(), "schemaUrl", null, 0, 1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(instrumentationScopeEClass, InstrumentationScope.class, "InstrumentationScope", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInstrumentationScope_Name(), ecorePackage.getEString(), "name", null, 0, 1, InstrumentationScope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInstrumentationScope_Version(), ecorePackage.getEString(), "version", null, 0, 1, InstrumentationScope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInstrumentationScope_Attributes(), this.getKeyValue(), null, "attributes", null, 0, -1, InstrumentationScope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInstrumentationScope_DroppedAttributesCount(), ecorePackage.getEInt(), "droppedAttributesCount", null, 0, 1, InstrumentationScope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInstrumentationScope_SchemaUrl(), ecorePackage.getEString(), "schemaUrl", null, 0, 1, InstrumentationScope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(keyValueEClass, KeyValue.class, "KeyValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getKeyValue_Key(), ecorePackage.getEString(), "key", null, 1, 1, KeyValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKeyValue_Value(), this.getAnyValue(), null, "value", null, 0, 1, KeyValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(anyValueEClass, AnyValue.class, "AnyValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAnyValue_StringValue(), ecorePackage.getEString(), "stringValue", null, 0, 1, AnyValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAnyValue_BoolValue(), ecorePackage.getEBooleanObject(), "boolValue", null, 0, 1, AnyValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAnyValue_IntValue(), ecorePackage.getELongObject(), "intValue", null, 0, 1, AnyValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAnyValue_DoubleValue(), ecorePackage.getEDoubleObject(), "doubleValue", null, 0, 1, AnyValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAnyValue_ArrayValue(), this.getAnyValue(), null, "arrayValue", null, 0, -1, AnyValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAnyValue_KvlistValue(), this.getKeyValue(), null, "kvlistValue", null, 0, -1, AnyValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAnyValue_BytesValue(), ecorePackage.getEByteArray(), "bytesValue", null, 0, 1, AnyValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tracesDataEClass, TracesData.class, "TracesData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTracesData_ResourceSpans(), this.getResourceSpans(), null, "resourceSpans", null, 0, -1, TracesData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceSpansEClass, ResourceSpans.class, "ResourceSpans", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResourceSpans_Resource(), this.getResource(), null, "resource", null, 0, 1, ResourceSpans.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceSpans_ScopeSpans(), this.getScopeSpans(), null, "scopeSpans", null, 0, -1, ResourceSpans.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceSpans_SchemaUrl(), ecorePackage.getEString(), "schemaUrl", null, 0, 1, ResourceSpans.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scopeSpansEClass, ScopeSpans.class, "ScopeSpans", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScopeSpans_Scope(), this.getInstrumentationScope(), null, "scope", null, 0, 1, ScopeSpans.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getScopeSpans_Spans(), this.getSpan(), null, "spans", null, 0, -1, ScopeSpans.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScopeSpans_SchemaUrl(), ecorePackage.getEString(), "schemaUrl", null, 0, 1, ScopeSpans.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(spanEClass, Span.class, "Span", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSpan_TraceId(), ecorePackage.getEString(), "traceId", null, 0, 1, Span.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpan_SpanId(), ecorePackage.getEString(), "spanId", null, 0, 1, Span.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpan_TraceState(), ecorePackage.getEString(), "traceState", null, 0, 1, Span.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpan_ParentSpanId(), ecorePackage.getEString(), "parentSpanId", null, 0, 1, Span.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpan_Name(), ecorePackage.getEString(), "name", null, 1, 1, Span.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpan_Kind(), this.getSpanKind(), "kind", null, 0, 1, Span.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpan_StartTimeUnixNano(), ecorePackage.getELong(), "startTimeUnixNano", null, 0, 1, Span.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpan_EndTimeUnixNano(), ecorePackage.getELong(), "endTimeUnixNano", null, 0, 1, Span.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpan_Attributes(), this.getKeyValue(), null, "attributes", null, 0, -1, Span.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpan_DroppedAttributesCount(), ecorePackage.getEInt(), "droppedAttributesCount", null, 0, 1, Span.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpan_Events(), this.getSpanEvent(), null, "events", null, 0, -1, Span.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpan_DroppedEventsCount(), ecorePackage.getEInt(), "droppedEventsCount", null, 0, 1, Span.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpan_Links(), this.getSpanLink(), null, "links", null, 0, -1, Span.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpan_DroppedLinksCount(), ecorePackage.getEInt(), "droppedLinksCount", null, 0, 1, Span.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpan_Status(), this.getSpanStatus(), null, "status", null, 0, 1, Span.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpan_Flags(), ecorePackage.getEInt(), "flags", null, 0, 1, Span.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpan_ChangeDescription(), theChangePackage.getChangeDescription(), null, "changeDescription", null, 0, 1, Span.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(spanEventEClass, SpanEvent.class, "SpanEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSpanEvent_TimeUnixNano(), ecorePackage.getELong(), "timeUnixNano", null, 0, 1, SpanEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpanEvent_Name(), ecorePackage.getEString(), "name", null, 1, 1, SpanEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpanEvent_Attributes(), this.getKeyValue(), null, "attributes", null, 0, -1, SpanEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpanEvent_DroppedAttributesCount(), ecorePackage.getEInt(), "droppedAttributesCount", null, 0, 1, SpanEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(spanLinkEClass, SpanLink.class, "SpanLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSpanLink_TraceId(), ecorePackage.getEString(), "traceId", null, 0, 1, SpanLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpanLink_SpanId(), ecorePackage.getEString(), "spanId", null, 0, 1, SpanLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpanLink_TraceState(), ecorePackage.getEString(), "traceState", null, 0, 1, SpanLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpanLink_Attributes(), this.getKeyValue(), null, "attributes", null, 0, -1, SpanLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpanLink_DroppedAttributesCount(), ecorePackage.getEInt(), "droppedAttributesCount", null, 0, 1, SpanLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpanLink_Flags(), ecorePackage.getEInt(), "flags", null, 0, 1, SpanLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(spanStatusEClass, SpanStatus.class, "SpanStatus", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSpanStatus_Message(), ecorePackage.getEString(), "message", null, 0, 1, SpanStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpanStatus_Code(), this.getStatusCode(), "code", null, 0, 1, SpanStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(metricsDataEClass, MetricsData.class, "MetricsData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMetricsData_ResourceMetrics(), this.getResourceMetrics(), null, "resourceMetrics", null, 0, -1, MetricsData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceMetricsEClass, ResourceMetrics.class, "ResourceMetrics", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResourceMetrics_Resource(), this.getResource(), null, "resource", null, 0, 1, ResourceMetrics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceMetrics_ScopeMetrics(), this.getScopeMetrics(), null, "scopeMetrics", null, 0, -1, ResourceMetrics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceMetrics_SchemaUrl(), ecorePackage.getEString(), "schemaUrl", null, 0, 1, ResourceMetrics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scopeMetricsEClass, ScopeMetrics.class, "ScopeMetrics", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScopeMetrics_Scope(), this.getInstrumentationScope(), null, "scope", null, 0, 1, ScopeMetrics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getScopeMetrics_Metrics(), this.getMetric(), null, "metrics", null, 0, -1, ScopeMetrics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScopeMetrics_SchemaUrl(), ecorePackage.getEString(), "schemaUrl", null, 0, 1, ScopeMetrics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(metricEClass, Metric.class, "Metric", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMetric_Name(), ecorePackage.getEString(), "name", null, 1, 1, Metric.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetric_Description(), ecorePackage.getEString(), "description", null, 0, 1, Metric.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetric_Unit(), ecorePackage.getEString(), "unit", null, 0, 1, Metric.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMetric_Metadata(), this.getKeyValue(), null, "metadata", null, 0, -1, Metric.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(gaugeEClass, Gauge.class, "Gauge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGauge_DataPoints(), this.getNumberDataPoint(), null, "dataPoints", null, 0, -1, Gauge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sumEClass, Sum.class, "Sum", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSum_DataPoints(), this.getNumberDataPoint(), null, "dataPoints", null, 0, -1, Sum.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSum_AggregationTemporality(), this.getAggregationTemporality(), "aggregationTemporality", null, 0, 1, Sum.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSum_IsMonotonic(), ecorePackage.getEBoolean(), "isMonotonic", null, 0, 1, Sum.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(histogramEClass, Histogram.class, "Histogram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getHistogram_DataPoints(), this.getHistogramDataPoint(), null, "dataPoints", null, 0, -1, Histogram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHistogram_AggregationTemporality(), this.getAggregationTemporality(), "aggregationTemporality", null, 0, 1, Histogram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(exponentialHistogramEClass, ExponentialHistogram.class, "ExponentialHistogram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExponentialHistogram_DataPoints(), this.getExponentialHistogramDataPoint(), null, "dataPoints", null, 0, -1, ExponentialHistogram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExponentialHistogram_AggregationTemporality(), this.getAggregationTemporality(), "aggregationTemporality", null, 0, 1, ExponentialHistogram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(summaryEClass, Summary.class, "Summary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSummary_DataPoints(), this.getSummaryDataPoint(), null, "dataPoints", null, 0, -1, Summary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(numberDataPointEClass, NumberDataPoint.class, "NumberDataPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNumberDataPoint_Attributes(), this.getKeyValue(), null, "attributes", null, 0, -1, NumberDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNumberDataPoint_StartTimeUnixNano(), ecorePackage.getELong(), "startTimeUnixNano", null, 0, 1, NumberDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNumberDataPoint_TimeUnixNano(), ecorePackage.getELong(), "timeUnixNano", null, 0, 1, NumberDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNumberDataPoint_AsDouble(), ecorePackage.getEDoubleObject(), "asDouble", null, 0, 1, NumberDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNumberDataPoint_AsInt(), ecorePackage.getELongObject(), "asInt", null, 0, 1, NumberDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNumberDataPoint_Exemplars(), this.getExemplar(), null, "exemplars", null, 0, -1, NumberDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNumberDataPoint_Flags(), ecorePackage.getEInt(), "flags", null, 0, 1, NumberDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(histogramDataPointEClass, HistogramDataPoint.class, "HistogramDataPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getHistogramDataPoint_Attributes(), this.getKeyValue(), null, "attributes", null, 0, -1, HistogramDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHistogramDataPoint_StartTimeUnixNano(), ecorePackage.getELong(), "startTimeUnixNano", null, 0, 1, HistogramDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHistogramDataPoint_TimeUnixNano(), ecorePackage.getELong(), "timeUnixNano", null, 0, 1, HistogramDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHistogramDataPoint_Count(), ecorePackage.getELong(), "count", null, 0, 1, HistogramDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHistogramDataPoint_Sum(), ecorePackage.getEDoubleObject(), "sum", null, 0, 1, HistogramDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHistogramDataPoint_BucketCounts(), ecorePackage.getELong(), "bucketCounts", null, 0, -1, HistogramDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHistogramDataPoint_ExplicitBounds(), ecorePackage.getEDouble(), "explicitBounds", null, 0, -1, HistogramDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHistogramDataPoint_Exemplars(), this.getExemplar(), null, "exemplars", null, 0, -1, HistogramDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHistogramDataPoint_Flags(), ecorePackage.getEInt(), "flags", null, 0, 1, HistogramDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHistogramDataPoint_Min(), ecorePackage.getEDoubleObject(), "min", null, 0, 1, HistogramDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHistogramDataPoint_Max(), ecorePackage.getEDoubleObject(), "max", null, 0, 1, HistogramDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(exponentialHistogramDataPointEClass, ExponentialHistogramDataPoint.class, "ExponentialHistogramDataPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExponentialHistogramDataPoint_Attributes(), this.getKeyValue(), null, "attributes", null, 0, -1, ExponentialHistogramDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExponentialHistogramDataPoint_StartTimeUnixNano(), ecorePackage.getELong(), "startTimeUnixNano", null, 0, 1, ExponentialHistogramDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExponentialHistogramDataPoint_TimeUnixNano(), ecorePackage.getELong(), "timeUnixNano", null, 0, 1, ExponentialHistogramDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExponentialHistogramDataPoint_Count(), ecorePackage.getELong(), "count", null, 0, 1, ExponentialHistogramDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExponentialHistogramDataPoint_Sum(), ecorePackage.getEDoubleObject(), "sum", null, 0, 1, ExponentialHistogramDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExponentialHistogramDataPoint_Scale(), ecorePackage.getEInt(), "scale", null, 0, 1, ExponentialHistogramDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExponentialHistogramDataPoint_ZeroCount(), ecorePackage.getELong(), "zeroCount", null, 0, 1, ExponentialHistogramDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExponentialHistogramDataPoint_Positive(), this.getExponentialHistogramDataPointBuckets(), null, "positive", null, 0, 1, ExponentialHistogramDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExponentialHistogramDataPoint_Negative(), this.getExponentialHistogramDataPointBuckets(), null, "negative", null, 0, 1, ExponentialHistogramDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExponentialHistogramDataPoint_Flags(), ecorePackage.getEInt(), "flags", null, 0, 1, ExponentialHistogramDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExponentialHistogramDataPoint_Exemplars(), this.getExemplar(), null, "exemplars", null, 0, -1, ExponentialHistogramDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExponentialHistogramDataPoint_Min(), ecorePackage.getEDoubleObject(), "min", null, 0, 1, ExponentialHistogramDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExponentialHistogramDataPoint_Max(), ecorePackage.getEDoubleObject(), "max", null, 0, 1, ExponentialHistogramDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExponentialHistogramDataPoint_ZeroThreshold(), ecorePackage.getEDouble(), "zeroThreshold", null, 0, 1, ExponentialHistogramDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(exponentialHistogramDataPointBucketsEClass, ExponentialHistogramDataPointBuckets.class, "ExponentialHistogramDataPointBuckets", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExponentialHistogramDataPointBuckets_Offset(), ecorePackage.getEInt(), "offset", null, 0, 1, ExponentialHistogramDataPointBuckets.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExponentialHistogramDataPointBuckets_BucketCounts(), ecorePackage.getELong(), "bucketCounts", null, 0, -1, ExponentialHistogramDataPointBuckets.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(summaryDataPointEClass, SummaryDataPoint.class, "SummaryDataPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSummaryDataPoint_Attributes(), this.getKeyValue(), null, "attributes", null, 0, -1, SummaryDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSummaryDataPoint_StartTimeUnixNano(), ecorePackage.getELong(), "startTimeUnixNano", null, 0, 1, SummaryDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSummaryDataPoint_TimeUnixNano(), ecorePackage.getELong(), "timeUnixNano", null, 0, 1, SummaryDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSummaryDataPoint_Count(), ecorePackage.getELong(), "count", null, 0, 1, SummaryDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSummaryDataPoint_Sum(), ecorePackage.getEDouble(), "sum", null, 0, 1, SummaryDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSummaryDataPoint_QuantileValues(), this.getSummaryDataPointValueAtQuantile(), null, "quantileValues", null, 0, -1, SummaryDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSummaryDataPoint_Flags(), ecorePackage.getEInt(), "flags", null, 0, 1, SummaryDataPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(summaryDataPointValueAtQuantileEClass, SummaryDataPointValueAtQuantile.class, "SummaryDataPointValueAtQuantile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSummaryDataPointValueAtQuantile_Quantile(), ecorePackage.getEDouble(), "quantile", null, 0, 1, SummaryDataPointValueAtQuantile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSummaryDataPointValueAtQuantile_Value(), ecorePackage.getEDouble(), "value", null, 0, 1, SummaryDataPointValueAtQuantile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(exemplarEClass, Exemplar.class, "Exemplar", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExemplar_FilteredAttributes(), this.getKeyValue(), null, "filteredAttributes", null, 0, -1, Exemplar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExemplar_TimeUnixNano(), ecorePackage.getELong(), "timeUnixNano", null, 0, 1, Exemplar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExemplar_AsDouble(), ecorePackage.getEDoubleObject(), "asDouble", null, 0, 1, Exemplar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExemplar_AsInt(), ecorePackage.getELongObject(), "asInt", null, 0, 1, Exemplar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExemplar_SpanId(), ecorePackage.getEString(), "spanId", null, 0, 1, Exemplar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExemplar_TraceId(), ecorePackage.getEString(), "traceId", null, 0, 1, Exemplar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(logsDataEClass, LogsData.class, "LogsData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLogsData_ResourceLogs(), this.getResourceLogs(), null, "resourceLogs", null, 0, -1, LogsData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceLogsEClass, ResourceLogs.class, "ResourceLogs", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResourceLogs_Resource(), this.getResource(), null, "resource", null, 0, 1, ResourceLogs.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceLogs_ScopeLogs(), this.getScopeLogs(), null, "scopeLogs", null, 0, -1, ResourceLogs.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceLogs_SchemaUrl(), ecorePackage.getEString(), "schemaUrl", null, 0, 1, ResourceLogs.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scopeLogsEClass, ScopeLogs.class, "ScopeLogs", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScopeLogs_Scope(), this.getInstrumentationScope(), null, "scope", null, 0, 1, ScopeLogs.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getScopeLogs_LogRecords(), this.getLogRecord(), null, "logRecords", null, 0, -1, ScopeLogs.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScopeLogs_SchemaUrl(), ecorePackage.getEString(), "schemaUrl", null, 0, 1, ScopeLogs.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(logRecordEClass, LogRecord.class, "LogRecord", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLogRecord_TimeUnixNano(), ecorePackage.getELong(), "timeUnixNano", null, 0, 1, LogRecord.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLogRecord_ObservedTimeUnixNano(), ecorePackage.getELong(), "observedTimeUnixNano", null, 0, 1, LogRecord.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLogRecord_SeverityNumber(), this.getSeverityNumber(), "severityNumber", null, 0, 1, LogRecord.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLogRecord_SeverityText(), ecorePackage.getEString(), "severityText", null, 0, 1, LogRecord.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLogRecord_Body(), this.getAnyValue(), null, "body", null, 0, 1, LogRecord.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLogRecord_Attributes(), this.getKeyValue(), null, "attributes", null, 0, -1, LogRecord.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLogRecord_DroppedAttributesCount(), ecorePackage.getEInt(), "droppedAttributesCount", null, 0, 1, LogRecord.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLogRecord_Flags(), ecorePackage.getEInt(), "flags", null, 0, 1, LogRecord.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLogRecord_TraceId(), ecorePackage.getEString(), "traceId", null, 0, 1, LogRecord.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLogRecord_SpanId(), ecorePackage.getEString(), "spanId", null, 0, 1, LogRecord.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(spanKindEEnum, SpanKind.class, "SpanKind");
		addEEnumLiteral(spanKindEEnum, SpanKind.SPAN_KIND_UNSPECIFIED);
		addEEnumLiteral(spanKindEEnum, SpanKind.SPAN_KIND_INTERNAL);
		addEEnumLiteral(spanKindEEnum, SpanKind.SPAN_KIND_SERVER);
		addEEnumLiteral(spanKindEEnum, SpanKind.SPAN_KIND_CLIENT);
		addEEnumLiteral(spanKindEEnum, SpanKind.SPAN_KIND_PRODUCER);
		addEEnumLiteral(spanKindEEnum, SpanKind.SPAN_KIND_CONSUMER);

		initEEnum(statusCodeEEnum, StatusCode.class, "StatusCode");
		addEEnumLiteral(statusCodeEEnum, StatusCode.STATUS_CODE_UNSET);
		addEEnumLiteral(statusCodeEEnum, StatusCode.STATUS_CODE_OK);
		addEEnumLiteral(statusCodeEEnum, StatusCode.STATUS_CODE_ERROR);

		initEEnum(aggregationTemporalityEEnum, AggregationTemporality.class, "AggregationTemporality");
		addEEnumLiteral(aggregationTemporalityEEnum, AggregationTemporality.AGGREGATION_TEMPORALITY_UNSPECIFIED);
		addEEnumLiteral(aggregationTemporalityEEnum, AggregationTemporality.AGGREGATION_TEMPORALITY_DELTA);
		addEEnumLiteral(aggregationTemporalityEEnum, AggregationTemporality.AGGREGATION_TEMPORALITY_CUMULATIVE);

		initEEnum(severityNumberEEnum, SeverityNumber.class, "SeverityNumber");
		addEEnumLiteral(severityNumberEEnum, SeverityNumber.SEVERITY_NUMBER_UNSPECIFIED);
		addEEnumLiteral(severityNumberEEnum, SeverityNumber.SEVERITY_NUMBER_TRACE);
		addEEnumLiteral(severityNumberEEnum, SeverityNumber.SEVERITY_NUMBER_TRACE2);
		addEEnumLiteral(severityNumberEEnum, SeverityNumber.SEVERITY_NUMBER_TRACE3);
		addEEnumLiteral(severityNumberEEnum, SeverityNumber.SEVERITY_NUMBER_TRACE4);
		addEEnumLiteral(severityNumberEEnum, SeverityNumber.SEVERITY_NUMBER_DEBUG);
		addEEnumLiteral(severityNumberEEnum, SeverityNumber.SEVERITY_NUMBER_DEBUG2);
		addEEnumLiteral(severityNumberEEnum, SeverityNumber.SEVERITY_NUMBER_DEBUG3);
		addEEnumLiteral(severityNumberEEnum, SeverityNumber.SEVERITY_NUMBER_DEBUG4);
		addEEnumLiteral(severityNumberEEnum, SeverityNumber.SEVERITY_NUMBER_INFO);
		addEEnumLiteral(severityNumberEEnum, SeverityNumber.SEVERITY_NUMBER_INFO2);
		addEEnumLiteral(severityNumberEEnum, SeverityNumber.SEVERITY_NUMBER_INFO3);
		addEEnumLiteral(severityNumberEEnum, SeverityNumber.SEVERITY_NUMBER_INFO4);
		addEEnumLiteral(severityNumberEEnum, SeverityNumber.SEVERITY_NUMBER_WARN);
		addEEnumLiteral(severityNumberEEnum, SeverityNumber.SEVERITY_NUMBER_WARN2);
		addEEnumLiteral(severityNumberEEnum, SeverityNumber.SEVERITY_NUMBER_WARN3);
		addEEnumLiteral(severityNumberEEnum, SeverityNumber.SEVERITY_NUMBER_WARN4);
		addEEnumLiteral(severityNumberEEnum, SeverityNumber.SEVERITY_NUMBER_ERROR);
		addEEnumLiteral(severityNumberEEnum, SeverityNumber.SEVERITY_NUMBER_ERROR2);
		addEEnumLiteral(severityNumberEEnum, SeverityNumber.SEVERITY_NUMBER_ERROR3);
		addEEnumLiteral(severityNumberEEnum, SeverityNumber.SEVERITY_NUMBER_ERROR4);
		addEEnumLiteral(severityNumberEEnum, SeverityNumber.SEVERITY_NUMBER_FATAL);
		addEEnumLiteral(severityNumberEEnum, SeverityNumber.SEVERITY_NUMBER_FATAL2);
		addEEnumLiteral(severityNumberEEnum, SeverityNumber.SEVERITY_NUMBER_FATAL3);
		addEEnumLiteral(severityNumberEEnum, SeverityNumber.SEVERITY_NUMBER_FATAL4);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/GenModel
		createGenModelAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/GenModel</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createGenModelAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/GenModel";
		addAnnotation
		  (this,
		   source,
		   new String[] {
			   "documentation", "An Ecore model of OpenTelemetry concepts - traces, spans, metrics, logs, and supporting types.\nBased on the OpenTelemetry specification: https://opentelemetry.io/docs/specs/otel/"
		   });
		addAnnotation
		  (resourceEClass,
		   source,
		   new String[] {
			   "documentation", "Resource represents the entity producing telemetry as Attributes.\nFor example, a process producing telemetry that is running in a container on Kubernetes has a Pod name, it is in a namespace and possibly is part of a Deployment which also has a name.\nAll three of these attributes can be included in the Resource."
		   });
		addAnnotation
		  (getResource_Attributes(),
		   source,
		   new String[] {
			   "documentation", "Set of attributes that describe the resource."
		   });
		addAnnotation
		  (getResource_DroppedAttributesCount(),
		   source,
		   new String[] {
			   "documentation", "droppedAttributesCount is the number of dropped attributes. If the value is 0, then no attributes were dropped."
		   });
		addAnnotation
		  (getResource_SchemaUrl(),
		   source,
		   new String[] {
			   "documentation", "schema_url contains the URL that describes the semantics of the attributes."
		   });
		addAnnotation
		  (instrumentationScopeEClass,
		   source,
		   new String[] {
			   "documentation", "InstrumentationScope is a message representing the instrumentation scope information such as the fully qualified name and version."
		   });
		addAnnotation
		  (getInstrumentationScope_Name(),
		   source,
		   new String[] {
			   "documentation", "An empty instrumentation scope name means the name is unknown."
		   });
		addAnnotation
		  (getInstrumentationScope_Attributes(),
		   source,
		   new String[] {
			   "documentation", "Additional attributes that describe the scope. Use of this parameter is optional."
		   });
		addAnnotation
		  (getInstrumentationScope_SchemaUrl(),
		   source,
		   new String[] {
			   "documentation", "schema_url contains the URL that describes the semantics of the attributes."
		   });
		addAnnotation
		  (keyValueEClass,
		   source,
		   new String[] {
			   "documentation", "KeyValue is a key-value pair that is used to store Span attributes, Link attributes, etc."
		   });
		addAnnotation
		  (getKeyValue_Value(),
		   source,
		   new String[] {
			   "documentation", "The value associated with the key."
		   });
		addAnnotation
		  (anyValueEClass,
		   source,
		   new String[] {
			   "documentation", "AnyValue is used to represent any type of attribute value. AnyValue may contain a primitive value such as a string or integer or it may contain an arbitrary nested object containing arrays, key-value lists and primitives."
		   });
		addAnnotation
		  (getAnyValue_ArrayValue(),
		   source,
		   new String[] {
			   "documentation", "Represents an array of any value."
		   });
		addAnnotation
		  (getAnyValue_KvlistValue(),
		   source,
		   new String[] {
			   "documentation", "Represents a list of key-value pairs."
		   });
		addAnnotation
		  (getAnyValue_BytesValue(),
		   source,
		   new String[] {
			   "documentation", "Represents raw bytes value."
		   });
		addAnnotation
		  (tracesDataEClass,
		   source,
		   new String[] {
			   "documentation", "TracesData represents the traces export payload which can be serialized to different formats, e.g., OTLP/gRPC, JSON, and proto."
		   });
		addAnnotation
		  (getTracesData_ResourceSpans(),
		   source,
		   new String[] {
			   "documentation", "An array of ResourceSpans. For data coming from a single resource this array will typically contain one element. Intermediary nodes that receive data from multiple origins typically batch the data before forwarding further."
		   });
		addAnnotation
		  (resourceSpansEClass,
		   source,
		   new String[] {
			   "documentation", "A collection of ScopeSpans from a Resource."
		   });
		addAnnotation
		  (getResourceSpans_Resource(),
		   source,
		   new String[] {
			   "documentation", "The resource for the spans in this message. If this field is not set then no resource info is known."
		   });
		addAnnotation
		  (getResourceSpans_ScopeSpans(),
		   source,
		   new String[] {
			   "documentation", "A list of ScopeSpans that originate from a resource."
		   });
		addAnnotation
		  (getResourceSpans_SchemaUrl(),
		   source,
		   new String[] {
			   "documentation", "The URL of the schema that all spans in this message are following."
		   });
		addAnnotation
		  (scopeSpansEClass,
		   source,
		   new String[] {
			   "documentation", "A collection of Spans produced by an InstrumentationScope."
		   });
		addAnnotation
		  (getScopeSpans_Scope(),
		   source,
		   new String[] {
			   "documentation", "The instrumentation scope information for the spans in this message. Semantically when InstrumentationScope isn\'t set, it is equivalent with an empty instrumentation scope name (unknown)."
		   });
		addAnnotation
		  (getScopeSpans_Spans(),
		   source,
		   new String[] {
			   "documentation", "A list of Spans that originate from an instrumentation scope."
		   });
		addAnnotation
		  (spanEClass,
		   source,
		   new String[] {
			   "documentation", "A Span represents a single operation performed by a single component of the system.\nThe next available field id is 17."
		   });
		addAnnotation
		  (getSpan_TraceId(),
		   source,
		   new String[] {
			   "documentation", "A unique identifier for a trace. All spans from the same trace share the same trace_id.\nThe ID is a 16-byte array."
		   });
		addAnnotation
		  (getSpan_SpanId(),
		   source,
		   new String[] {
			   "documentation", "A unique identifier for a span within a trace, assigned when the span is created.\nThe ID is an 8-byte array."
		   });
		addAnnotation
		  (getSpan_TraceState(),
		   source,
		   new String[] {
			   "documentation", "trace_state conveys information about request position in multiple distributed tracing graphs. It is a vendor-specific format as described by the W3C Trace Context specification."
		   });
		addAnnotation
		  (getSpan_ParentSpanId(),
		   source,
		   new String[] {
			   "documentation", "The span_id of this span\'s parent span. If this is a root span, then this field must be empty. The ID is an 8-byte array."
		   });
		addAnnotation
		  (getSpan_Name(),
		   source,
		   new String[] {
			   "documentation", "A description of the span\'s operation.\nFor example, the name can be a qualified method name or a file name and a line number where the operation is called. A best practice is to use the same display name at the same call point repeatably, so that users can filter and group spans based on the name."
		   });
		addAnnotation
		  (getSpan_Kind(),
		   source,
		   new String[] {
			   "documentation", "Distinguishes between spans generated in a particular context."
		   });
		addAnnotation
		  (getSpan_StartTimeUnixNano(),
		   source,
		   new String[] {
			   "documentation", "start_time_unix_nano is the start time of the span. On the client side, this is the time kept by the local machine where the span execution starts. On the server side, this is the time when the server\'s application handler starts running.\nValue is UNIX Epoch time in nanoseconds since 00:00:00 UTC on 1 January 1970."
		   });
		addAnnotation
		  (getSpan_EndTimeUnixNano(),
		   source,
		   new String[] {
			   "documentation", "end_time_unix_nano is the end time of the span. On the client side, this is the time kept by the local machine where the span execution ends. On the server side, this is the time when the server application handler stops running.\nValue is UNIX Epoch time in nanoseconds since 00:00:00 UTC on 1 January 1970."
		   });
		addAnnotation
		  (getSpan_Attributes(),
		   source,
		   new String[] {
			   "documentation", "attributes is a collection of key/value pairs. Note, global attributes like server name can be set using the resource API."
		   });
		addAnnotation
		  (getSpan_Events(),
		   source,
		   new String[] {
			   "documentation", "events is a collection of Event items."
		   });
		addAnnotation
		  (getSpan_Links(),
		   source,
		   new String[] {
			   "documentation", "links is a collection of Links, which are references from this span to a span in the same or different trace."
		   });
		addAnnotation
		  (getSpan_Status(),
		   source,
		   new String[] {
			   "documentation", "An optional final status for this span. Semantically when Status isn\'t set, it means span\'s status code is unset, i.e. assume STATUS_CODE_UNSET (code = 0)."
		   });
		addAnnotation
		  (getSpan_Flags(),
		   source,
		   new String[] {
			   "documentation", "Flags, a bit field. 8 least significant bits are the trace flags as defined in W3C Trace Context specification."
		   });
		addAnnotation
		  (spanEventEClass,
		   source,
		   new String[] {
			   "documentation", "Event is a time-stamped annotation of the span, consisting of user-supplied text description and key-value pairs."
		   });
		addAnnotation
		  (getSpanEvent_TimeUnixNano(),
		   source,
		   new String[] {
			   "documentation", "time_unix_nano is the time the event occurred."
		   });
		addAnnotation
		  (getSpanEvent_Name(),
		   source,
		   new String[] {
			   "documentation", "name of the event. This field is semantically required to be set to non-empty string by OpenTelemetry API when the event is created explicitly by the instrumentation libraries."
		   });
		addAnnotation
		  (getSpanEvent_Attributes(),
		   source,
		   new String[] {
			   "documentation", "attributes is a collection of attribute key/value pairs on the event."
		   });
		addAnnotation
		  (spanLinkEClass,
		   source,
		   new String[] {
			   "documentation", "A pointer from the current span to another span in the same trace or in a different trace. For example, this can be used in batching operations, where a single batch handler processes multiple requests from different traces or when the handler receives a request from a different project."
		   });
		addAnnotation
		  (getSpanLink_TraceId(),
		   source,
		   new String[] {
			   "documentation", "A unique identifier of a trace that this linked span is part of."
		   });
		addAnnotation
		  (getSpanLink_SpanId(),
		   source,
		   new String[] {
			   "documentation", "A unique identifier for the linked span."
		   });
		addAnnotation
		  (getSpanLink_Attributes(),
		   source,
		   new String[] {
			   "documentation", "attributes is a collection of attribute key/value pairs on the link."
		   });
		addAnnotation
		  (getSpanLink_Flags(),
		   source,
		   new String[] {
			   "documentation", "Flags, a bit field."
		   });
		addAnnotation
		  (spanStatusEClass,
		   source,
		   new String[] {
			   "documentation", "The Status type defines a logical error model that is suitable for different programming environments, including REST APIs and RPC APIs."
		   });
		addAnnotation
		  (getSpanStatus_Message(),
		   source,
		   new String[] {
			   "documentation", "A developer-facing human readable error message."
		   });
		addAnnotation
		  (getSpanStatus_Code(),
		   source,
		   new String[] {
			   "documentation", "The status code."
		   });
		addAnnotation
		  (metricsDataEClass,
		   source,
		   new String[] {
			   "documentation", "MetricsData represents the metrics export payload which can be serialized to different formats, e.g., OTLP/gRPC, JSON, and proto."
		   });
		addAnnotation
		  (getMetricsData_ResourceMetrics(),
		   source,
		   new String[] {
			   "documentation", "An array of ResourceMetrics."
		   });
		addAnnotation
		  (resourceMetricsEClass,
		   source,
		   new String[] {
			   "documentation", "A collection of ScopeMetrics from a Resource."
		   });
		addAnnotation
		  (scopeMetricsEClass,
		   source,
		   new String[] {
			   "documentation", "A collection of Metrics produced by an Scope."
		   });
		addAnnotation
		  (metricEClass,
		   source,
		   new String[] {
			   "documentation", "Defines a Metric which has one or more timeseries. The following is a brief summary of the Metric data model.\n\nMetric is a named entity with a description and unit."
		   });
		addAnnotation
		  (getMetric_Name(),
		   source,
		   new String[] {
			   "documentation", "name of the metric, including its DNS name prefix. It must be unique."
		   });
		addAnnotation
		  (getMetric_Description(),
		   source,
		   new String[] {
			   "documentation", "description of the metric, which can be used in documentation."
		   });
		addAnnotation
		  (getMetric_Unit(),
		   source,
		   new String[] {
			   "documentation", "unit in which the metric value is reported. Follows the format described by http://unitsofmeasure.org/ucum.html."
		   });
		addAnnotation
		  (getMetric_Metadata(),
		   source,
		   new String[] {
			   "documentation", "Additional metadata attributes that describe the metric."
		   });
		addAnnotation
		  (gaugeEClass,
		   source,
		   new String[] {
			   "documentation", "Gauge represents the type of a scalar metric that always exports the current value for every data point. It should be used for an unknown aggregation."
		   });
		addAnnotation
		  (sumEClass,
		   source,
		   new String[] {
			   "documentation", "Sum represents the type of a scalar metric that is calculated as a sum of all reported measurements over a time interval."
		   });
		addAnnotation
		  (getSum_IsMonotonic(),
		   source,
		   new String[] {
			   "documentation", "If true, the sum is monotonically increasing."
		   });
		addAnnotation
		  (histogramEClass,
		   source,
		   new String[] {
			   "documentation", "Histogram represents the type of a metric that is calculated by aggregating as a Histogram of all reported measurements over a time interval."
		   });
		addAnnotation
		  (exponentialHistogramEClass,
		   source,
		   new String[] {
			   "documentation", "ExponentialHistogram represents the type of a metric that is calculated by aggregating as a ExponentialHistogram of all reported double measurements over a time interval."
		   });
		addAnnotation
		  (summaryEClass,
		   source,
		   new String[] {
			   "documentation", "Summary metric data are used to convey quantile summaries."
		   });
		addAnnotation
		  (numberDataPointEClass,
		   source,
		   new String[] {
			   "documentation", "NumberDataPoint is a single data point in a timeseries that describes the time-varying scalar value of a metric."
		   });
		addAnnotation
		  (getNumberDataPoint_AsDouble(),
		   source,
		   new String[] {
			   "documentation", "value as a double. Either asDouble or asInt must be set."
		   });
		addAnnotation
		  (getNumberDataPoint_AsInt(),
		   source,
		   new String[] {
			   "documentation", "value as an integer. Either asDouble or asInt must be set."
		   });
		addAnnotation
		  (getNumberDataPoint_Flags(),
		   source,
		   new String[] {
			   "documentation", "Flags is an optional bit field containing hints for the backends."
		   });
		addAnnotation
		  (histogramDataPointEClass,
		   source,
		   new String[] {
			   "documentation", "HistogramDataPoint is a single data point in a timeseries that describes the time-varying values of a Histogram."
		   });
		addAnnotation
		  (getHistogramDataPoint_Count(),
		   source,
		   new String[] {
			   "documentation", "count is the number of values in the population."
		   });
		addAnnotation
		  (getHistogramDataPoint_Sum(),
		   source,
		   new String[] {
			   "documentation", "sum of the values in the population. If count is zero then this field must be zero."
		   });
		addAnnotation
		  (getHistogramDataPoint_BucketCounts(),
		   source,
		   new String[] {
			   "documentation", "bucket_counts is an optional field contains the count values of histogram for each bucket."
		   });
		addAnnotation
		  (getHistogramDataPoint_ExplicitBounds(),
		   source,
		   new String[] {
			   "documentation", "explicit_bounds specifies buckets with explicitly defined bounds for values."
		   });
		addAnnotation
		  (getHistogramDataPoint_Min(),
		   source,
		   new String[] {
			   "documentation", "min is the minimum value over (start_time, end_time]."
		   });
		addAnnotation
		  (getHistogramDataPoint_Max(),
		   source,
		   new String[] {
			   "documentation", "max is the maximum value over (start_time, end_time]."
		   });
		addAnnotation
		  (exponentialHistogramDataPointEClass,
		   source,
		   new String[] {
			   "documentation", "ExponentialHistogramDataPoint is a single data point in a timeseries that describes the time-varying values of a ExponentialHistogram of double values."
		   });
		addAnnotation
		  (getExponentialHistogramDataPoint_Scale(),
		   source,
		   new String[] {
			   "documentation", "scale describes the resolution of the histogram. Boundaries are located at powers of the base, where base = (2^(2^-scale))"
		   });
		addAnnotation
		  (getExponentialHistogramDataPoint_ZeroCount(),
		   source,
		   new String[] {
			   "documentation", "zero_count is the count of values in the zero bucket."
		   });
		addAnnotation
		  (getExponentialHistogramDataPoint_Positive(),
		   source,
		   new String[] {
			   "documentation", "positive carries the positive range of exponential bucket counts."
		   });
		addAnnotation
		  (getExponentialHistogramDataPoint_Negative(),
		   source,
		   new String[] {
			   "documentation", "negative carries the negative range of exponential bucket counts."
		   });
		addAnnotation
		  (exponentialHistogramDataPointBucketsEClass,
		   source,
		   new String[] {
			   "documentation", "Buckets are a set of bucket counts, encoded in a contiguous array of counts."
		   });
		addAnnotation
		  (getExponentialHistogramDataPointBuckets_Offset(),
		   source,
		   new String[] {
			   "documentation", "Offset is the bucket index of the first entry in the bucket_counts array."
		   });
		addAnnotation
		  (summaryDataPointEClass,
		   source,
		   new String[] {
			   "documentation", "SummaryDataPoint is a single data point in a timeseries that describes the time-varying values of a Summary metric."
		   });
		addAnnotation
		  (summaryDataPointValueAtQuantileEClass,
		   source,
		   new String[] {
			   "documentation", "Represents the value at a given quantile of a distribution."
		   });
		addAnnotation
		  (getSummaryDataPointValueAtQuantile_Quantile(),
		   source,
		   new String[] {
			   "documentation", "The quantile of a distribution. Must be in the interval [0.0, 1.0]."
		   });
		addAnnotation
		  (getSummaryDataPointValueAtQuantile_Value(),
		   source,
		   new String[] {
			   "documentation", "The value at the given quantile of a distribution."
		   });
		addAnnotation
		  (exemplarEClass,
		   source,
		   new String[] {
			   "documentation", "A representation of an exemplar, which is a sample input measurement. Exemplars also hold information about the environment when the measurement was recorded, for example the span and trace ID of the active span when the exemplar was recorded."
		   });
		addAnnotation
		  (getExemplar_FilteredAttributes(),
		   source,
		   new String[] {
			   "documentation", "The set of key/value pairs that were filtered out by the aggregator, but recorded alongside the original measurement."
		   });
		addAnnotation
		  (getExemplar_SpanId(),
		   source,
		   new String[] {
			   "documentation", "(Optional) Span ID of the exemplar trace. span_id may be missing if the measurement is not recorded inside a trace or if the trace is not sampled."
		   });
		addAnnotation
		  (getExemplar_TraceId(),
		   source,
		   new String[] {
			   "documentation", "(Optional) Trace ID of the exemplar trace."
		   });
		addAnnotation
		  (logsDataEClass,
		   source,
		   new String[] {
			   "documentation", "LogsData represents the logs export payload which can be serialized to different formats, e.g., OTLP/gRPC, JSON, and proto."
		   });
		addAnnotation
		  (getLogsData_ResourceLogs(),
		   source,
		   new String[] {
			   "documentation", "An array of ResourceLogs."
		   });
		addAnnotation
		  (resourceLogsEClass,
		   source,
		   new String[] {
			   "documentation", "A collection of ScopeLogs from a Resource."
		   });
		addAnnotation
		  (scopeLogsEClass,
		   source,
		   new String[] {
			   "documentation", "A collection of Logs produced by a Scope."
		   });
		addAnnotation
		  (logRecordEClass,
		   source,
		   new String[] {
			   "documentation", "A log record according to OpenTelemetry Log Data Model: https://opentelemetry.io/docs/specs/otel/logs/data-model/"
		   });
		addAnnotation
		  (getLogRecord_TimeUnixNano(),
		   source,
		   new String[] {
			   "documentation", "time_unix_nano is the time when the event occurred. Value is UNIX Epoch time in nanoseconds since 00:00:00 UTC on 1 January 1970."
		   });
		addAnnotation
		  (getLogRecord_ObservedTimeUnixNano(),
		   source,
		   new String[] {
			   "documentation", "Time when the event was observed by the collection system."
		   });
		addAnnotation
		  (getLogRecord_SeverityNumber(),
		   source,
		   new String[] {
			   "documentation", "Numerical value of the severity, normalized to values described in the log data model."
		   });
		addAnnotation
		  (getLogRecord_SeverityText(),
		   source,
		   new String[] {
			   "documentation", "The severity text (also known as log level). The original string representation as it is known at the source."
		   });
		addAnnotation
		  (getLogRecord_Body(),
		   source,
		   new String[] {
			   "documentation", "A value containing the body of the log record."
		   });
		addAnnotation
		  (getLogRecord_Attributes(),
		   source,
		   new String[] {
			   "documentation", "Additional attributes that describe the specific event occurrence."
		   });
		addAnnotation
		  (getLogRecord_Flags(),
		   source,
		   new String[] {
			   "documentation", "Flags, a bit field."
		   });
		addAnnotation
		  (getLogRecord_TraceId(),
		   source,
		   new String[] {
			   "documentation", "A unique identifier for a trace. All logs from the same trace share the same trace_id."
		   });
		addAnnotation
		  (getLogRecord_SpanId(),
		   source,
		   new String[] {
			   "documentation", "A unique identifier for a span within a trace. If present, this log record is associated with a specific span."
		   });
		addAnnotation
		  (spanKindEEnum,
		   source,
		   new String[] {
			   "documentation", "SpanKind is the type of span. Can be used to specify additional relationships between spans in addition to a parent/child relationship."
		   });
		addAnnotation
		  (spanKindEEnum.getELiterals().get(0),
		   source,
		   new String[] {
			   "documentation", "Unspecified. Do NOT use as default. Implementations MAY assume SpanKind to be INTERNAL when receiving UNSPECIFIED."
		   });
		addAnnotation
		  (spanKindEEnum.getELiterals().get(1),
		   source,
		   new String[] {
			   "documentation", "Indicates that the span represents an internal operation within an application, as opposed to an operations happening at the boundaries of a process."
		   });
		addAnnotation
		  (spanKindEEnum.getELiterals().get(2),
		   source,
		   new String[] {
			   "documentation", "Indicates that the span covers server-side handling of an RPC or other remote network request."
		   });
		addAnnotation
		  (spanKindEEnum.getELiterals().get(3),
		   source,
		   new String[] {
			   "documentation", "Indicates that the span describes a request to some remote service."
		   });
		addAnnotation
		  (spanKindEEnum.getELiterals().get(4),
		   source,
		   new String[] {
			   "documentation", "Indicates that the span describes a producer sending a message to a broker. Unlike CLIENT and SERVER, there is often no direct critical-path latency relationship between producer and consumer spans."
		   });
		addAnnotation
		  (spanKindEEnum.getELiterals().get(5),
		   source,
		   new String[] {
			   "documentation", "Indicates that the span describes consumer receiving a message from a broker. Like the PRODUCER kind, there is often no direct critical-path latency relationship between producer and consumer spans."
		   });
		addAnnotation
		  (statusCodeEEnum,
		   source,
		   new String[] {
			   "documentation", "For the semantics of status codes see https://github.com/open-telemetry/opentelemetry-specification/blob/main/specification/trace/api.md#set-status"
		   });
		addAnnotation
		  (statusCodeEEnum.getELiterals().get(0),
		   source,
		   new String[] {
			   "documentation", "The default status."
		   });
		addAnnotation
		  (statusCodeEEnum.getELiterals().get(1),
		   source,
		   new String[] {
			   "documentation", "The Span has been validated by an Application developer or Operator to have completed successfully."
		   });
		addAnnotation
		  (statusCodeEEnum.getELiterals().get(2),
		   source,
		   new String[] {
			   "documentation", "The Span contains an error."
		   });
		addAnnotation
		  (aggregationTemporalityEEnum,
		   source,
		   new String[] {
			   "documentation", "AggregationTemporality defines how a metric aggregator reports aggregated values. It describes how those values relate to the time interval over which they are aggregated."
		   });
		addAnnotation
		  (aggregationTemporalityEEnum.getELiterals().get(0),
		   source,
		   new String[] {
			   "documentation", "UNSPECIFIED is the default AggregationTemporality, it MUST not be used."
		   });
		addAnnotation
		  (aggregationTemporalityEEnum.getELiterals().get(1),
		   source,
		   new String[] {
			   "documentation", "DELTA is an AggregationTemporality for a metric aggregator which reports changes since last report time."
		   });
		addAnnotation
		  (aggregationTemporalityEEnum.getELiterals().get(2),
		   source,
		   new String[] {
			   "documentation", "CUMULATIVE is an AggregationTemporality for a metric aggregator which reports changes since a fixed start time."
		   });
		addAnnotation
		  (severityNumberEEnum,
		   source,
		   new String[] {
			   "documentation", "Possible values for LogRecord.SeverityNumber."
		   });
		addAnnotation
		  (severityNumberEEnum.getELiterals().get(0),
		   source,
		   new String[] {
			   "documentation", "UNSPECIFIED is the default SeverityNumber, it MUST not be used."
		   });
	}

} //ModelPackageImpl
