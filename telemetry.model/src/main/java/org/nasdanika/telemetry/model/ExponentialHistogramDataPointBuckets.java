/**
 */
package org.nasdanika.telemetry.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exponential Histogram Data Point Buckets</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Buckets are a set of bucket counts, encoded in a contiguous array of counts.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPointBuckets#getOffset <em>Offset</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPointBuckets#getBucketCounts <em>Bucket Counts</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.telemetry.model.ModelPackage#getExponentialHistogramDataPointBuckets()
 * @model
 * @generated
 */
public interface ExponentialHistogramDataPointBuckets extends EObject {
	/**
	 * Returns the value of the '<em><b>Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Offset is the bucket index of the first entry in the bucket_counts array.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Offset</em>' attribute.
	 * @see #setOffset(int)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getExponentialHistogramDataPointBuckets_Offset()
	 * @model
	 * @generated
	 */
	int getOffset();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.ExponentialHistogramDataPointBuckets#getOffset <em>Offset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Offset</em>' attribute.
	 * @see #getOffset()
	 * @generated
	 */
	void setOffset(int value);

	/**
	 * Returns the value of the '<em><b>Bucket Counts</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Long}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bucket Counts</em>' attribute list.
	 * @see org.nasdanika.telemetry.model.ModelPackage#getExponentialHistogramDataPointBuckets_BucketCounts()
	 * @model
	 * @generated
	 */
	EList<Long> getBucketCounts();

} // ExponentialHistogramDataPointBuckets
