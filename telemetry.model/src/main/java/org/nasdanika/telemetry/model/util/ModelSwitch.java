/**
 */
package org.nasdanika.telemetry.model.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.nasdanika.telemetry.model.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.nasdanika.telemetry.model.ModelPackage
 * @generated
 */
public class ModelSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ModelPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelSwitch() {
		if (modelPackage == null) {
			modelPackage = ModelPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ModelPackage.RESOURCE: {
				Resource resource = (Resource)theEObject;
				T result = caseResource(resource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.INSTRUMENTATION_SCOPE: {
				InstrumentationScope instrumentationScope = (InstrumentationScope)theEObject;
				T result = caseInstrumentationScope(instrumentationScope);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.KEY_VALUE: {
				KeyValue keyValue = (KeyValue)theEObject;
				T result = caseKeyValue(keyValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ANY_VALUE: {
				AnyValue anyValue = (AnyValue)theEObject;
				T result = caseAnyValue(anyValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TRACES_DATA: {
				TracesData tracesData = (TracesData)theEObject;
				T result = caseTracesData(tracesData);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.RESOURCE_SPANS: {
				ResourceSpans resourceSpans = (ResourceSpans)theEObject;
				T result = caseResourceSpans(resourceSpans);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.SCOPE_SPANS: {
				ScopeSpans scopeSpans = (ScopeSpans)theEObject;
				T result = caseScopeSpans(scopeSpans);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.SPAN: {
				Span span = (Span)theEObject;
				T result = caseSpan(span);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.SPAN_EVENT: {
				SpanEvent spanEvent = (SpanEvent)theEObject;
				T result = caseSpanEvent(spanEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.SPAN_LINK: {
				SpanLink spanLink = (SpanLink)theEObject;
				T result = caseSpanLink(spanLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.SPAN_STATUS: {
				SpanStatus spanStatus = (SpanStatus)theEObject;
				T result = caseSpanStatus(spanStatus);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.METRICS_DATA: {
				MetricsData metricsData = (MetricsData)theEObject;
				T result = caseMetricsData(metricsData);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.RESOURCE_METRICS: {
				ResourceMetrics resourceMetrics = (ResourceMetrics)theEObject;
				T result = caseResourceMetrics(resourceMetrics);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.SCOPE_METRICS: {
				ScopeMetrics scopeMetrics = (ScopeMetrics)theEObject;
				T result = caseScopeMetrics(scopeMetrics);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.METRIC: {
				Metric metric = (Metric)theEObject;
				T result = caseMetric(metric);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.GAUGE: {
				Gauge gauge = (Gauge)theEObject;
				T result = caseGauge(gauge);
				if (result == null) result = caseMetric(gauge);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.SUM: {
				Sum sum = (Sum)theEObject;
				T result = caseSum(sum);
				if (result == null) result = caseMetric(sum);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.HISTOGRAM: {
				Histogram histogram = (Histogram)theEObject;
				T result = caseHistogram(histogram);
				if (result == null) result = caseMetric(histogram);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.EXPONENTIAL_HISTOGRAM: {
				ExponentialHistogram exponentialHistogram = (ExponentialHistogram)theEObject;
				T result = caseExponentialHistogram(exponentialHistogram);
				if (result == null) result = caseMetric(exponentialHistogram);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.SUMMARY: {
				Summary summary = (Summary)theEObject;
				T result = caseSummary(summary);
				if (result == null) result = caseMetric(summary);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.NUMBER_DATA_POINT: {
				NumberDataPoint numberDataPoint = (NumberDataPoint)theEObject;
				T result = caseNumberDataPoint(numberDataPoint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.HISTOGRAM_DATA_POINT: {
				HistogramDataPoint histogramDataPoint = (HistogramDataPoint)theEObject;
				T result = caseHistogramDataPoint(histogramDataPoint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.EXPONENTIAL_HISTOGRAM_DATA_POINT: {
				ExponentialHistogramDataPoint exponentialHistogramDataPoint = (ExponentialHistogramDataPoint)theEObject;
				T result = caseExponentialHistogramDataPoint(exponentialHistogramDataPoint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.EXPONENTIAL_HISTOGRAM_DATA_POINT_BUCKETS: {
				ExponentialHistogramDataPointBuckets exponentialHistogramDataPointBuckets = (ExponentialHistogramDataPointBuckets)theEObject;
				T result = caseExponentialHistogramDataPointBuckets(exponentialHistogramDataPointBuckets);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.SUMMARY_DATA_POINT: {
				SummaryDataPoint summaryDataPoint = (SummaryDataPoint)theEObject;
				T result = caseSummaryDataPoint(summaryDataPoint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.SUMMARY_DATA_POINT_VALUE_AT_QUANTILE: {
				SummaryDataPointValueAtQuantile summaryDataPointValueAtQuantile = (SummaryDataPointValueAtQuantile)theEObject;
				T result = caseSummaryDataPointValueAtQuantile(summaryDataPointValueAtQuantile);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.EXEMPLAR: {
				Exemplar exemplar = (Exemplar)theEObject;
				T result = caseExemplar(exemplar);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.LOGS_DATA: {
				LogsData logsData = (LogsData)theEObject;
				T result = caseLogsData(logsData);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.RESOURCE_LOGS: {
				ResourceLogs resourceLogs = (ResourceLogs)theEObject;
				T result = caseResourceLogs(resourceLogs);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.SCOPE_LOGS: {
				ScopeLogs scopeLogs = (ScopeLogs)theEObject;
				T result = caseScopeLogs(scopeLogs);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.LOG_RECORD: {
				LogRecord logRecord = (LogRecord)theEObject;
				T result = caseLogRecord(logRecord);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResource(Resource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Instrumentation Scope</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Instrumentation Scope</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInstrumentationScope(InstrumentationScope object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Key Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Key Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseKeyValue(KeyValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Any Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Any Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnyValue(AnyValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Traces Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Traces Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTracesData(TracesData object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Spans</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Spans</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourceSpans(ResourceSpans object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Scope Spans</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Scope Spans</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScopeSpans(ScopeSpans object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Span</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Span</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpan(Span object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Span Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Span Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpanEvent(SpanEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Span Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Span Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpanLink(SpanLink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Span Status</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Span Status</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpanStatus(SpanStatus object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Metrics Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Metrics Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMetricsData(MetricsData object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Metrics</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Metrics</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourceMetrics(ResourceMetrics object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Scope Metrics</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Scope Metrics</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScopeMetrics(ScopeMetrics object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Metric</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Metric</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMetric(Metric object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Gauge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Gauge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGauge(Gauge object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sum</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sum</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSum(Sum object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Histogram</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Histogram</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHistogram(Histogram object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exponential Histogram</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exponential Histogram</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExponentialHistogram(ExponentialHistogram object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Summary</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Summary</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSummary(Summary object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Number Data Point</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Number Data Point</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNumberDataPoint(NumberDataPoint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Histogram Data Point</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Histogram Data Point</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHistogramDataPoint(HistogramDataPoint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exponential Histogram Data Point</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exponential Histogram Data Point</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExponentialHistogramDataPoint(ExponentialHistogramDataPoint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exponential Histogram Data Point Buckets</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exponential Histogram Data Point Buckets</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExponentialHistogramDataPointBuckets(ExponentialHistogramDataPointBuckets object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Summary Data Point</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Summary Data Point</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSummaryDataPoint(SummaryDataPoint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Summary Data Point Value At Quantile</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Summary Data Point Value At Quantile</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSummaryDataPointValueAtQuantile(SummaryDataPointValueAtQuantile object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exemplar</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exemplar</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExemplar(Exemplar object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Logs Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Logs Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLogsData(LogsData object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Logs</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Logs</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourceLogs(ResourceLogs object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Scope Logs</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Scope Logs</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScopeLogs(ScopeLogs object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Log Record</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Log Record</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLogRecord(LogRecord object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //ModelSwitch
