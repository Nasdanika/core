/**
 */
package org.nasdanika.telemetry.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Summary Data Point Value At Quantile</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents the value at a given quantile of a distribution.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.SummaryDataPointValueAtQuantile#getQuantile <em>Quantile</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.SummaryDataPointValueAtQuantile#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.telemetry.model.ModelPackage#getSummaryDataPointValueAtQuantile()
 * @model
 * @generated
 */
public interface SummaryDataPointValueAtQuantile extends EObject {
	/**
	 * Returns the value of the '<em><b>Quantile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The quantile of a distribution. Must be in the interval [0.0, 1.0].
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Quantile</em>' attribute.
	 * @see #setQuantile(double)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSummaryDataPointValueAtQuantile_Quantile()
	 * @model
	 * @generated
	 */
	double getQuantile();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.SummaryDataPointValueAtQuantile#getQuantile <em>Quantile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quantile</em>' attribute.
	 * @see #getQuantile()
	 * @generated
	 */
	void setQuantile(double value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The value at the given quantile of a distribution.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(double)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSummaryDataPointValueAtQuantile_Value()
	 * @model
	 * @generated
	 */
	double getValue();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.SummaryDataPointValueAtQuantile#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(double value);

} // SummaryDataPointValueAtQuantile
