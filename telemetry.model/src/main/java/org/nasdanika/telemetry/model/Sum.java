/**
 */
package org.nasdanika.telemetry.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sum</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Sum represents the type of a scalar metric that is calculated as a sum of all reported measurements over a time interval.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.Sum#getDataPoints <em>Data Points</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.Sum#getAggregationTemporality <em>Aggregation Temporality</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.Sum#isIsMonotonic <em>Is Monotonic</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.telemetry.model.ModelPackage#getSum()
 * @model
 * @generated
 */
public interface Sum extends Metric {
	/**
	 * Returns the value of the '<em><b>Data Points</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.telemetry.model.NumberDataPoint}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Points</em>' containment reference list.
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSum_DataPoints()
	 * @model containment="true"
	 * @generated
	 */
	EList<NumberDataPoint> getDataPoints();

	/**
	 * Returns the value of the '<em><b>Aggregation Temporality</b></em>' attribute.
	 * The literals are from the enumeration {@link org.nasdanika.telemetry.model.AggregationTemporality}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aggregation Temporality</em>' attribute.
	 * @see org.nasdanika.telemetry.model.AggregationTemporality
	 * @see #setAggregationTemporality(AggregationTemporality)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSum_AggregationTemporality()
	 * @model
	 * @generated
	 */
	AggregationTemporality getAggregationTemporality();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.Sum#getAggregationTemporality <em>Aggregation Temporality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Aggregation Temporality</em>' attribute.
	 * @see org.nasdanika.telemetry.model.AggregationTemporality
	 * @see #getAggregationTemporality()
	 * @generated
	 */
	void setAggregationTemporality(AggregationTemporality value);

	/**
	 * Returns the value of the '<em><b>Is Monotonic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If true, the sum is monotonically increasing.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Monotonic</em>' attribute.
	 * @see #setIsMonotonic(boolean)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSum_IsMonotonic()
	 * @model
	 * @generated
	 */
	boolean isIsMonotonic();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.Sum#isIsMonotonic <em>Is Monotonic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Monotonic</em>' attribute.
	 * @see #isIsMonotonic()
	 * @generated
	 */
	void setIsMonotonic(boolean value);

} // Sum
