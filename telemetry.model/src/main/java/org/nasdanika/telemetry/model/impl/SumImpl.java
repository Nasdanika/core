/**
 */
package org.nasdanika.telemetry.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.telemetry.model.AggregationTemporality;
import org.nasdanika.telemetry.model.ModelPackage;
import org.nasdanika.telemetry.model.NumberDataPoint;
import org.nasdanika.telemetry.model.Sum;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sum</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SumImpl#getDataPoints <em>Data Points</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SumImpl#getAggregationTemporality <em>Aggregation Temporality</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SumImpl#isIsMonotonic <em>Is Monotonic</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SumImpl extends MetricImpl implements Sum {
	/**
	 * The default value of the '{@link #getAggregationTemporality() <em>Aggregation Temporality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAggregationTemporality()
	 * @generated
	 * @ordered
	 */
	protected static final AggregationTemporality AGGREGATION_TEMPORALITY_EDEFAULT = AggregationTemporality.AGGREGATION_TEMPORALITY_UNSPECIFIED;

	/**
	 * The default value of the '{@link #isIsMonotonic() <em>Is Monotonic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsMonotonic()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_MONOTONIC_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SumImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.SUM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<NumberDataPoint> getDataPoints() {
		return (EList<NumberDataPoint>)eDynamicGet(ModelPackage.SUM__DATA_POINTS, ModelPackage.Literals.SUM__DATA_POINTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AggregationTemporality getAggregationTemporality() {
		return (AggregationTemporality)eDynamicGet(ModelPackage.SUM__AGGREGATION_TEMPORALITY, ModelPackage.Literals.SUM__AGGREGATION_TEMPORALITY, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAggregationTemporality(AggregationTemporality newAggregationTemporality) {
		eDynamicSet(ModelPackage.SUM__AGGREGATION_TEMPORALITY, ModelPackage.Literals.SUM__AGGREGATION_TEMPORALITY, newAggregationTemporality);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsMonotonic() {
		return (Boolean)eDynamicGet(ModelPackage.SUM__IS_MONOTONIC, ModelPackage.Literals.SUM__IS_MONOTONIC, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsMonotonic(boolean newIsMonotonic) {
		eDynamicSet(ModelPackage.SUM__IS_MONOTONIC, ModelPackage.Literals.SUM__IS_MONOTONIC, newIsMonotonic);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.SUM__DATA_POINTS:
				return ((InternalEList<?>)getDataPoints()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.SUM__DATA_POINTS:
				return getDataPoints();
			case ModelPackage.SUM__AGGREGATION_TEMPORALITY:
				return getAggregationTemporality();
			case ModelPackage.SUM__IS_MONOTONIC:
				return isIsMonotonic();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.SUM__DATA_POINTS:
				getDataPoints().clear();
				getDataPoints().addAll((Collection<? extends NumberDataPoint>)newValue);
				return;
			case ModelPackage.SUM__AGGREGATION_TEMPORALITY:
				setAggregationTemporality((AggregationTemporality)newValue);
				return;
			case ModelPackage.SUM__IS_MONOTONIC:
				setIsMonotonic((Boolean)newValue);
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
			case ModelPackage.SUM__DATA_POINTS:
				getDataPoints().clear();
				return;
			case ModelPackage.SUM__AGGREGATION_TEMPORALITY:
				setAggregationTemporality(AGGREGATION_TEMPORALITY_EDEFAULT);
				return;
			case ModelPackage.SUM__IS_MONOTONIC:
				setIsMonotonic(IS_MONOTONIC_EDEFAULT);
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
			case ModelPackage.SUM__DATA_POINTS:
				return !getDataPoints().isEmpty();
			case ModelPackage.SUM__AGGREGATION_TEMPORALITY:
				return getAggregationTemporality() != AGGREGATION_TEMPORALITY_EDEFAULT;
			case ModelPackage.SUM__IS_MONOTONIC:
				return isIsMonotonic() != IS_MONOTONIC_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //SumImpl
