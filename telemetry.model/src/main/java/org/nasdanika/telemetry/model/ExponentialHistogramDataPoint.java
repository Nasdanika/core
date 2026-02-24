/**
 */
package org.nasdanika.telemetry.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exponential Histogram Data Point</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * ExponentialHistogramDataPoint is a single data point in a timeseries that describes the time-varying values of a ExponentialHistogram of double values.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getStartTimeUnixNano <em>Start Time Unix Nano</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getTimeUnixNano <em>Time Unix Nano</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getCount <em>Count</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getSum <em>Sum</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getScale <em>Scale</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getZeroCount <em>Zero Count</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getPositive <em>Positive</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getNegative <em>Negative</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getFlags <em>Flags</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getExemplars <em>Exemplars</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getMin <em>Min</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getMax <em>Max</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getZeroThreshold <em>Zero Threshold</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.telemetry.model.ModelPackage#getExponentialHistogramDataPoint()
 * @model
 * @generated
 */
public interface ExponentialHistogramDataPoint extends EObject {
	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.telemetry.model.KeyValue}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see org.nasdanika.telemetry.model.ModelPackage#getExponentialHistogramDataPoint_Attributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<KeyValue> getAttributes();

	/**
	 * Returns the value of the '<em><b>Start Time Unix Nano</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Time Unix Nano</em>' attribute.
	 * @see #setStartTimeUnixNano(long)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getExponentialHistogramDataPoint_StartTimeUnixNano()
	 * @model
	 * @generated
	 */
	long getStartTimeUnixNano();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getStartTimeUnixNano <em>Start Time Unix Nano</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Time Unix Nano</em>' attribute.
	 * @see #getStartTimeUnixNano()
	 * @generated
	 */
	void setStartTimeUnixNano(long value);

	/**
	 * Returns the value of the '<em><b>Time Unix Nano</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Unix Nano</em>' attribute.
	 * @see #setTimeUnixNano(long)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getExponentialHistogramDataPoint_TimeUnixNano()
	 * @model
	 * @generated
	 */
	long getTimeUnixNano();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getTimeUnixNano <em>Time Unix Nano</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Unix Nano</em>' attribute.
	 * @see #getTimeUnixNano()
	 * @generated
	 */
	void setTimeUnixNano(long value);

	/**
	 * Returns the value of the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Count</em>' attribute.
	 * @see #setCount(long)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getExponentialHistogramDataPoint_Count()
	 * @model
	 * @generated
	 */
	long getCount();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getCount <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Count</em>' attribute.
	 * @see #getCount()
	 * @generated
	 */
	void setCount(long value);

	/**
	 * Returns the value of the '<em><b>Sum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sum</em>' attribute.
	 * @see #setSum(Double)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getExponentialHistogramDataPoint_Sum()
	 * @model
	 * @generated
	 */
	Double getSum();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getSum <em>Sum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sum</em>' attribute.
	 * @see #getSum()
	 * @generated
	 */
	void setSum(Double value);

	/**
	 * Returns the value of the '<em><b>Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * scale describes the resolution of the histogram. Boundaries are located at powers of the base, where base = (2^(2^-scale))
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Scale</em>' attribute.
	 * @see #setScale(int)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getExponentialHistogramDataPoint_Scale()
	 * @model
	 * @generated
	 */
	int getScale();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getScale <em>Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scale</em>' attribute.
	 * @see #getScale()
	 * @generated
	 */
	void setScale(int value);

	/**
	 * Returns the value of the '<em><b>Zero Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * zero_count is the count of values in the zero bucket.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Zero Count</em>' attribute.
	 * @see #setZeroCount(long)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getExponentialHistogramDataPoint_ZeroCount()
	 * @model
	 * @generated
	 */
	long getZeroCount();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getZeroCount <em>Zero Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Zero Count</em>' attribute.
	 * @see #getZeroCount()
	 * @generated
	 */
	void setZeroCount(long value);

	/**
	 * Returns the value of the '<em><b>Positive</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * positive carries the positive range of exponential bucket counts.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Positive</em>' containment reference.
	 * @see #setPositive(ExponentialHistogramDataPointBuckets)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getExponentialHistogramDataPoint_Positive()
	 * @model containment="true"
	 * @generated
	 */
	ExponentialHistogramDataPointBuckets getPositive();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getPositive <em>Positive</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Positive</em>' containment reference.
	 * @see #getPositive()
	 * @generated
	 */
	void setPositive(ExponentialHistogramDataPointBuckets value);

	/**
	 * Returns the value of the '<em><b>Negative</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * negative carries the negative range of exponential bucket counts.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Negative</em>' containment reference.
	 * @see #setNegative(ExponentialHistogramDataPointBuckets)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getExponentialHistogramDataPoint_Negative()
	 * @model containment="true"
	 * @generated
	 */
	ExponentialHistogramDataPointBuckets getNegative();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getNegative <em>Negative</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Negative</em>' containment reference.
	 * @see #getNegative()
	 * @generated
	 */
	void setNegative(ExponentialHistogramDataPointBuckets value);

	/**
	 * Returns the value of the '<em><b>Flags</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Flags</em>' attribute.
	 * @see #setFlags(int)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getExponentialHistogramDataPoint_Flags()
	 * @model
	 * @generated
	 */
	int getFlags();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getFlags <em>Flags</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Flags</em>' attribute.
	 * @see #getFlags()
	 * @generated
	 */
	void setFlags(int value);

	/**
	 * Returns the value of the '<em><b>Exemplars</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.telemetry.model.Exemplar}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exemplars</em>' containment reference list.
	 * @see org.nasdanika.telemetry.model.ModelPackage#getExponentialHistogramDataPoint_Exemplars()
	 * @model containment="true"
	 * @generated
	 */
	EList<Exemplar> getExemplars();

	/**
	 * Returns the value of the '<em><b>Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min</em>' attribute.
	 * @see #setMin(Double)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getExponentialHistogramDataPoint_Min()
	 * @model
	 * @generated
	 */
	Double getMin();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getMin <em>Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min</em>' attribute.
	 * @see #getMin()
	 * @generated
	 */
	void setMin(Double value);

	/**
	 * Returns the value of the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max</em>' attribute.
	 * @see #setMax(Double)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getExponentialHistogramDataPoint_Max()
	 * @model
	 * @generated
	 */
	Double getMax();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getMax <em>Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max</em>' attribute.
	 * @see #getMax()
	 * @generated
	 */
	void setMax(Double value);

	/**
	 * Returns the value of the '<em><b>Zero Threshold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Zero Threshold</em>' attribute.
	 * @see #setZeroThreshold(double)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getExponentialHistogramDataPoint_ZeroThreshold()
	 * @model
	 * @generated
	 */
	double getZeroThreshold();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint#getZeroThreshold <em>Zero Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Zero Threshold</em>' attribute.
	 * @see #getZeroThreshold()
	 * @generated
	 */
	void setZeroThreshold(double value);

} // ExponentialHistogramDataPoint
