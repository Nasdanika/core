/**
 */
package org.nasdanika.telemetry.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Summary</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Summary metric data are used to convey quantile summaries.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.Summary#getDataPoints <em>Data Points</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.telemetry.model.ModelPackage#getSummary()
 * @model
 * @generated
 */
public interface Summary extends Metric {
	/**
	 * Returns the value of the '<em><b>Data Points</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.telemetry.model.SummaryDataPoint}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Points</em>' containment reference list.
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSummary_DataPoints()
	 * @model containment="true"
	 * @generated
	 */
	EList<SummaryDataPoint> getDataPoints();

} // Summary
