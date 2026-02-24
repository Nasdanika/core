/**
 */
package org.nasdanika.telemetry.model.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.nasdanika.telemetry.model.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelFactoryImpl extends EFactoryImpl implements ModelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ModelFactory init() {
		try {
			ModelFactory theModelFactory = (ModelFactory)EPackage.Registry.INSTANCE.getEFactory(ModelPackage.eNS_URI);
			if (theModelFactory != null) {
				return theModelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ModelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ModelPackage.RESOURCE: return createResource();
			case ModelPackage.INSTRUMENTATION_SCOPE: return createInstrumentationScope();
			case ModelPackage.KEY_VALUE: return createKeyValue();
			case ModelPackage.ANY_VALUE: return createAnyValue();
			case ModelPackage.TRACES_DATA: return createTracesData();
			case ModelPackage.RESOURCE_SPANS: return createResourceSpans();
			case ModelPackage.SCOPE_SPANS: return createScopeSpans();
			case ModelPackage.SPAN: return createSpan();
			case ModelPackage.SPAN_EVENT: return createSpanEvent();
			case ModelPackage.SPAN_LINK: return createSpanLink();
			case ModelPackage.SPAN_STATUS: return createSpanStatus();
			case ModelPackage.METRICS_DATA: return createMetricsData();
			case ModelPackage.RESOURCE_METRICS: return createResourceMetrics();
			case ModelPackage.SCOPE_METRICS: return createScopeMetrics();
			case ModelPackage.GAUGE: return createGauge();
			case ModelPackage.SUM: return createSum();
			case ModelPackage.HISTOGRAM: return createHistogram();
			case ModelPackage.EXPONENTIAL_HISTOGRAM: return createExponentialHistogram();
			case ModelPackage.SUMMARY: return createSummary();
			case ModelPackage.NUMBER_DATA_POINT: return createNumberDataPoint();
			case ModelPackage.HISTOGRAM_DATA_POINT: return createHistogramDataPoint();
			case ModelPackage.EXPONENTIAL_HISTOGRAM_DATA_POINT: return createExponentialHistogramDataPoint();
			case ModelPackage.EXPONENTIAL_HISTOGRAM_DATA_POINT_BUCKETS: return createExponentialHistogramDataPointBuckets();
			case ModelPackage.SUMMARY_DATA_POINT: return createSummaryDataPoint();
			case ModelPackage.SUMMARY_DATA_POINT_VALUE_AT_QUANTILE: return createSummaryDataPointValueAtQuantile();
			case ModelPackage.EXEMPLAR: return createExemplar();
			case ModelPackage.LOGS_DATA: return createLogsData();
			case ModelPackage.RESOURCE_LOGS: return createResourceLogs();
			case ModelPackage.SCOPE_LOGS: return createScopeLogs();
			case ModelPackage.LOG_RECORD: return createLogRecord();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ModelPackage.SPAN_KIND:
				return createSpanKindFromString(eDataType, initialValue);
			case ModelPackage.STATUS_CODE:
				return createStatusCodeFromString(eDataType, initialValue);
			case ModelPackage.AGGREGATION_TEMPORALITY:
				return createAggregationTemporalityFromString(eDataType, initialValue);
			case ModelPackage.SEVERITY_NUMBER:
				return createSeverityNumberFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ModelPackage.SPAN_KIND:
				return convertSpanKindToString(eDataType, instanceValue);
			case ModelPackage.STATUS_CODE:
				return convertStatusCodeToString(eDataType, instanceValue);
			case ModelPackage.AGGREGATION_TEMPORALITY:
				return convertAggregationTemporalityToString(eDataType, instanceValue);
			case ModelPackage.SEVERITY_NUMBER:
				return convertSeverityNumberToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Resource createResource() {
		ResourceImpl resource = new ResourceImpl();
		return resource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InstrumentationScope createInstrumentationScope() {
		InstrumentationScopeImpl instrumentationScope = new InstrumentationScopeImpl();
		return instrumentationScope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public KeyValue createKeyValue() {
		KeyValueImpl keyValue = new KeyValueImpl();
		return keyValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AnyValue createAnyValue() {
		AnyValueImpl anyValue = new AnyValueImpl();
		return anyValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TracesData createTracesData() {
		TracesDataImpl tracesData = new TracesDataImpl();
		return tracesData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceSpans createResourceSpans() {
		ResourceSpansImpl resourceSpans = new ResourceSpansImpl();
		return resourceSpans;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScopeSpans createScopeSpans() {
		ScopeSpansImpl scopeSpans = new ScopeSpansImpl();
		return scopeSpans;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Span createSpan() {
		SpanImpl span = new SpanImpl();
		return span;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SpanEvent createSpanEvent() {
		SpanEventImpl spanEvent = new SpanEventImpl();
		return spanEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SpanLink createSpanLink() {
		SpanLinkImpl spanLink = new SpanLinkImpl();
		return spanLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SpanStatus createSpanStatus() {
		SpanStatusImpl spanStatus = new SpanStatusImpl();
		return spanStatus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MetricsData createMetricsData() {
		MetricsDataImpl metricsData = new MetricsDataImpl();
		return metricsData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceMetrics createResourceMetrics() {
		ResourceMetricsImpl resourceMetrics = new ResourceMetricsImpl();
		return resourceMetrics;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScopeMetrics createScopeMetrics() {
		ScopeMetricsImpl scopeMetrics = new ScopeMetricsImpl();
		return scopeMetrics;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Gauge createGauge() {
		GaugeImpl gauge = new GaugeImpl();
		return gauge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Sum createSum() {
		SumImpl sum = new SumImpl();
		return sum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Histogram createHistogram() {
		HistogramImpl histogram = new HistogramImpl();
		return histogram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExponentialHistogram createExponentialHistogram() {
		ExponentialHistogramImpl exponentialHistogram = new ExponentialHistogramImpl();
		return exponentialHistogram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Summary createSummary() {
		SummaryImpl summary = new SummaryImpl();
		return summary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NumberDataPoint createNumberDataPoint() {
		NumberDataPointImpl numberDataPoint = new NumberDataPointImpl();
		return numberDataPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public HistogramDataPoint createHistogramDataPoint() {
		HistogramDataPointImpl histogramDataPoint = new HistogramDataPointImpl();
		return histogramDataPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExponentialHistogramDataPoint createExponentialHistogramDataPoint() {
		ExponentialHistogramDataPointImpl exponentialHistogramDataPoint = new ExponentialHistogramDataPointImpl();
		return exponentialHistogramDataPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExponentialHistogramDataPointBuckets createExponentialHistogramDataPointBuckets() {
		ExponentialHistogramDataPointBucketsImpl exponentialHistogramDataPointBuckets = new ExponentialHistogramDataPointBucketsImpl();
		return exponentialHistogramDataPointBuckets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SummaryDataPoint createSummaryDataPoint() {
		SummaryDataPointImpl summaryDataPoint = new SummaryDataPointImpl();
		return summaryDataPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SummaryDataPointValueAtQuantile createSummaryDataPointValueAtQuantile() {
		SummaryDataPointValueAtQuantileImpl summaryDataPointValueAtQuantile = new SummaryDataPointValueAtQuantileImpl();
		return summaryDataPointValueAtQuantile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Exemplar createExemplar() {
		ExemplarImpl exemplar = new ExemplarImpl();
		return exemplar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LogsData createLogsData() {
		LogsDataImpl logsData = new LogsDataImpl();
		return logsData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLogs createResourceLogs() {
		ResourceLogsImpl resourceLogs = new ResourceLogsImpl();
		return resourceLogs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScopeLogs createScopeLogs() {
		ScopeLogsImpl scopeLogs = new ScopeLogsImpl();
		return scopeLogs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LogRecord createLogRecord() {
		LogRecordImpl logRecord = new LogRecordImpl();
		return logRecord;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpanKind createSpanKindFromString(EDataType eDataType, String initialValue) {
		SpanKind result = SpanKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSpanKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatusCode createStatusCodeFromString(EDataType eDataType, String initialValue) {
		StatusCode result = StatusCode.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertStatusCodeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AggregationTemporality createAggregationTemporalityFromString(EDataType eDataType, String initialValue) {
		AggregationTemporality result = AggregationTemporality.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAggregationTemporalityToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SeverityNumber createSeverityNumberFromString(EDataType eDataType, String initialValue) {
		SeverityNumber result = SeverityNumber.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSeverityNumberToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ModelPackage getModelPackage() {
		return (ModelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ModelPackage getPackage() {
		return ModelPackage.eINSTANCE;
	}

} //ModelFactoryImpl
