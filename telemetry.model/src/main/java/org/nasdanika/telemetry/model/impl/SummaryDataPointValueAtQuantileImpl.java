/**
 */
package org.nasdanika.telemetry.model.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.nasdanika.telemetry.model.ModelPackage;
import org.nasdanika.telemetry.model.SummaryDataPointValueAtQuantile;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Summary Data Point Value At Quantile</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SummaryDataPointValueAtQuantileImpl#getQuantile <em>Quantile</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SummaryDataPointValueAtQuantileImpl#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SummaryDataPointValueAtQuantileImpl extends MinimalEObjectImpl.Container implements SummaryDataPointValueAtQuantile {
	/**
	 * The default value of the '{@link #getQuantile() <em>Quantile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuantile()
	 * @generated
	 * @ordered
	 */
	protected static final double QUANTILE_EDEFAULT = 0.0;

	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final double VALUE_EDEFAULT = 0.0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SummaryDataPointValueAtQuantileImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.SUMMARY_DATA_POINT_VALUE_AT_QUANTILE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getQuantile() {
		return (Double)eDynamicGet(ModelPackage.SUMMARY_DATA_POINT_VALUE_AT_QUANTILE__QUANTILE, ModelPackage.Literals.SUMMARY_DATA_POINT_VALUE_AT_QUANTILE__QUANTILE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setQuantile(double newQuantile) {
		eDynamicSet(ModelPackage.SUMMARY_DATA_POINT_VALUE_AT_QUANTILE__QUANTILE, ModelPackage.Literals.SUMMARY_DATA_POINT_VALUE_AT_QUANTILE__QUANTILE, newQuantile);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getValue() {
		return (Double)eDynamicGet(ModelPackage.SUMMARY_DATA_POINT_VALUE_AT_QUANTILE__VALUE, ModelPackage.Literals.SUMMARY_DATA_POINT_VALUE_AT_QUANTILE__VALUE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setValue(double newValue) {
		eDynamicSet(ModelPackage.SUMMARY_DATA_POINT_VALUE_AT_QUANTILE__VALUE, ModelPackage.Literals.SUMMARY_DATA_POINT_VALUE_AT_QUANTILE__VALUE, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.SUMMARY_DATA_POINT_VALUE_AT_QUANTILE__QUANTILE:
				return getQuantile();
			case ModelPackage.SUMMARY_DATA_POINT_VALUE_AT_QUANTILE__VALUE:
				return getValue();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.SUMMARY_DATA_POINT_VALUE_AT_QUANTILE__QUANTILE:
				setQuantile((Double)newValue);
				return;
			case ModelPackage.SUMMARY_DATA_POINT_VALUE_AT_QUANTILE__VALUE:
				setValue((Double)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ModelPackage.SUMMARY_DATA_POINT_VALUE_AT_QUANTILE__QUANTILE:
				setQuantile(QUANTILE_EDEFAULT);
				return;
			case ModelPackage.SUMMARY_DATA_POINT_VALUE_AT_QUANTILE__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ModelPackage.SUMMARY_DATA_POINT_VALUE_AT_QUANTILE__QUANTILE:
				return getQuantile() != QUANTILE_EDEFAULT;
			case ModelPackage.SUMMARY_DATA_POINT_VALUE_AT_QUANTILE__VALUE:
				return getValue() != VALUE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //SummaryDataPointValueAtQuantileImpl
