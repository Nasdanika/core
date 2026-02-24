/**
 */
package org.nasdanika.telemetry.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.nasdanika.telemetry.model.ModelPackage
 * @generated
 */
public interface ModelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelFactory eINSTANCE = org.nasdanika.telemetry.model.impl.ModelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Resource</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource</em>'.
	 * @generated
	 */
	Resource createResource();

	/**
	 * Returns a new object of class '<em>Instrumentation Scope</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Instrumentation Scope</em>'.
	 * @generated
	 */
	InstrumentationScope createInstrumentationScope();

	/**
	 * Returns a new object of class '<em>Key Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Key Value</em>'.
	 * @generated
	 */
	KeyValue createKeyValue();

	/**
	 * Returns a new object of class '<em>Any Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Any Value</em>'.
	 * @generated
	 */
	AnyValue createAnyValue();

	/**
	 * Returns a new object of class '<em>Traces Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Traces Data</em>'.
	 * @generated
	 */
	TracesData createTracesData();

	/**
	 * Returns a new object of class '<em>Resource Spans</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Spans</em>'.
	 * @generated
	 */
	ResourceSpans createResourceSpans();

	/**
	 * Returns a new object of class '<em>Scope Spans</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Scope Spans</em>'.
	 * @generated
	 */
	ScopeSpans createScopeSpans();

	/**
	 * Returns a new object of class '<em>Span</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Span</em>'.
	 * @generated
	 */
	Span createSpan();

	/**
	 * Returns a new object of class '<em>Span Event</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Span Event</em>'.
	 * @generated
	 */
	SpanEvent createSpanEvent();

	/**
	 * Returns a new object of class '<em>Span Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Span Link</em>'.
	 * @generated
	 */
	SpanLink createSpanLink();

	/**
	 * Returns a new object of class '<em>Span Status</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Span Status</em>'.
	 * @generated
	 */
	SpanStatus createSpanStatus();

	/**
	 * Returns a new object of class '<em>Metrics Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Metrics Data</em>'.
	 * @generated
	 */
	MetricsData createMetricsData();

	/**
	 * Returns a new object of class '<em>Resource Metrics</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Metrics</em>'.
	 * @generated
	 */
	ResourceMetrics createResourceMetrics();

	/**
	 * Returns a new object of class '<em>Scope Metrics</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Scope Metrics</em>'.
	 * @generated
	 */
	ScopeMetrics createScopeMetrics();

	/**
	 * Returns a new object of class '<em>Gauge</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Gauge</em>'.
	 * @generated
	 */
	Gauge createGauge();

	/**
	 * Returns a new object of class '<em>Sum</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sum</em>'.
	 * @generated
	 */
	Sum createSum();

	/**
	 * Returns a new object of class '<em>Histogram</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Histogram</em>'.
	 * @generated
	 */
	Histogram createHistogram();

	/**
	 * Returns a new object of class '<em>Exponential Histogram</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Exponential Histogram</em>'.
	 * @generated
	 */
	ExponentialHistogram createExponentialHistogram();

	/**
	 * Returns a new object of class '<em>Summary</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Summary</em>'.
	 * @generated
	 */
	Summary createSummary();

	/**
	 * Returns a new object of class '<em>Number Data Point</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Number Data Point</em>'.
	 * @generated
	 */
	NumberDataPoint createNumberDataPoint();

	/**
	 * Returns a new object of class '<em>Histogram Data Point</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Histogram Data Point</em>'.
	 * @generated
	 */
	HistogramDataPoint createHistogramDataPoint();

	/**
	 * Returns a new object of class '<em>Exponential Histogram Data Point</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Exponential Histogram Data Point</em>'.
	 * @generated
	 */
	ExponentialHistogramDataPoint createExponentialHistogramDataPoint();

	/**
	 * Returns a new object of class '<em>Exponential Histogram Data Point Buckets</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Exponential Histogram Data Point Buckets</em>'.
	 * @generated
	 */
	ExponentialHistogramDataPointBuckets createExponentialHistogramDataPointBuckets();

	/**
	 * Returns a new object of class '<em>Summary Data Point</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Summary Data Point</em>'.
	 * @generated
	 */
	SummaryDataPoint createSummaryDataPoint();

	/**
	 * Returns a new object of class '<em>Summary Data Point Value At Quantile</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Summary Data Point Value At Quantile</em>'.
	 * @generated
	 */
	SummaryDataPointValueAtQuantile createSummaryDataPointValueAtQuantile();

	/**
	 * Returns a new object of class '<em>Exemplar</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Exemplar</em>'.
	 * @generated
	 */
	Exemplar createExemplar();

	/**
	 * Returns a new object of class '<em>Logs Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Logs Data</em>'.
	 * @generated
	 */
	LogsData createLogsData();

	/**
	 * Returns a new object of class '<em>Resource Logs</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Logs</em>'.
	 * @generated
	 */
	ResourceLogs createResourceLogs();

	/**
	 * Returns a new object of class '<em>Scope Logs</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Scope Logs</em>'.
	 * @generated
	 */
	ScopeLogs createScopeLogs();

	/**
	 * Returns a new object of class '<em>Log Record</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Log Record</em>'.
	 * @generated
	 */
	LogRecord createLogRecord();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ModelPackage getModelPackage();

} //ModelFactory
