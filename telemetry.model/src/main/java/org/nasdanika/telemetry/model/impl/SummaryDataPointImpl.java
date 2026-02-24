/**
 */
package org.nasdanika.telemetry.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.telemetry.model.KeyValue;
import org.nasdanika.telemetry.model.ModelPackage;
import org.nasdanika.telemetry.model.SummaryDataPoint;
import org.nasdanika.telemetry.model.SummaryDataPointValueAtQuantile;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Summary Data Point</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SummaryDataPointImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SummaryDataPointImpl#getStartTimeUnixNano <em>Start Time Unix Nano</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SummaryDataPointImpl#getTimeUnixNano <em>Time Unix Nano</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SummaryDataPointImpl#getCount <em>Count</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SummaryDataPointImpl#getSum <em>Sum</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SummaryDataPointImpl#getQuantileValues <em>Quantile Values</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SummaryDataPointImpl#getFlags <em>Flags</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SummaryDataPointImpl extends MinimalEObjectImpl.Container implements SummaryDataPoint {
	/**
	 * The default value of the '{@link #getStartTimeUnixNano() <em>Start Time Unix Nano</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartTimeUnixNano()
	 * @generated
	 * @ordered
	 */
	protected static final long START_TIME_UNIX_NANO_EDEFAULT = 0L;

	/**
	 * The default value of the '{@link #getTimeUnixNano() <em>Time Unix Nano</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeUnixNano()
	 * @generated
	 * @ordered
	 */
	protected static final long TIME_UNIX_NANO_EDEFAULT = 0L;

	/**
	 * The default value of the '{@link #getCount() <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCount()
	 * @generated
	 * @ordered
	 */
	protected static final long COUNT_EDEFAULT = 0L;

	/**
	 * The default value of the '{@link #getSum() <em>Sum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSum()
	 * @generated
	 * @ordered
	 */
	protected static final double SUM_EDEFAULT = 0.0;

	/**
	 * The default value of the '{@link #getFlags() <em>Flags</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFlags()
	 * @generated
	 * @ordered
	 */
	protected static final int FLAGS_EDEFAULT = 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SummaryDataPointImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.SUMMARY_DATA_POINT;
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
	@SuppressWarnings("unchecked")
	@Override
	public EList<KeyValue> getAttributes() {
		return (EList<KeyValue>)eDynamicGet(ModelPackage.SUMMARY_DATA_POINT__ATTRIBUTES, ModelPackage.Literals.SUMMARY_DATA_POINT__ATTRIBUTES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public long getStartTimeUnixNano() {
		return (Long)eDynamicGet(ModelPackage.SUMMARY_DATA_POINT__START_TIME_UNIX_NANO, ModelPackage.Literals.SUMMARY_DATA_POINT__START_TIME_UNIX_NANO, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStartTimeUnixNano(long newStartTimeUnixNano) {
		eDynamicSet(ModelPackage.SUMMARY_DATA_POINT__START_TIME_UNIX_NANO, ModelPackage.Literals.SUMMARY_DATA_POINT__START_TIME_UNIX_NANO, newStartTimeUnixNano);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public long getTimeUnixNano() {
		return (Long)eDynamicGet(ModelPackage.SUMMARY_DATA_POINT__TIME_UNIX_NANO, ModelPackage.Literals.SUMMARY_DATA_POINT__TIME_UNIX_NANO, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTimeUnixNano(long newTimeUnixNano) {
		eDynamicSet(ModelPackage.SUMMARY_DATA_POINT__TIME_UNIX_NANO, ModelPackage.Literals.SUMMARY_DATA_POINT__TIME_UNIX_NANO, newTimeUnixNano);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public long getCount() {
		return (Long)eDynamicGet(ModelPackage.SUMMARY_DATA_POINT__COUNT, ModelPackage.Literals.SUMMARY_DATA_POINT__COUNT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCount(long newCount) {
		eDynamicSet(ModelPackage.SUMMARY_DATA_POINT__COUNT, ModelPackage.Literals.SUMMARY_DATA_POINT__COUNT, newCount);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getSum() {
		return (Double)eDynamicGet(ModelPackage.SUMMARY_DATA_POINT__SUM, ModelPackage.Literals.SUMMARY_DATA_POINT__SUM, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSum(double newSum) {
		eDynamicSet(ModelPackage.SUMMARY_DATA_POINT__SUM, ModelPackage.Literals.SUMMARY_DATA_POINT__SUM, newSum);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<SummaryDataPointValueAtQuantile> getQuantileValues() {
		return (EList<SummaryDataPointValueAtQuantile>)eDynamicGet(ModelPackage.SUMMARY_DATA_POINT__QUANTILE_VALUES, ModelPackage.Literals.SUMMARY_DATA_POINT__QUANTILE_VALUES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getFlags() {
		return (Integer)eDynamicGet(ModelPackage.SUMMARY_DATA_POINT__FLAGS, ModelPackage.Literals.SUMMARY_DATA_POINT__FLAGS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFlags(int newFlags) {
		eDynamicSet(ModelPackage.SUMMARY_DATA_POINT__FLAGS, ModelPackage.Literals.SUMMARY_DATA_POINT__FLAGS, newFlags);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.SUMMARY_DATA_POINT__ATTRIBUTES:
				return ((InternalEList<?>)getAttributes()).basicRemove(otherEnd, msgs);
			case ModelPackage.SUMMARY_DATA_POINT__QUANTILE_VALUES:
				return ((InternalEList<?>)getQuantileValues()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.SUMMARY_DATA_POINT__ATTRIBUTES:
				return getAttributes();
			case ModelPackage.SUMMARY_DATA_POINT__START_TIME_UNIX_NANO:
				return getStartTimeUnixNano();
			case ModelPackage.SUMMARY_DATA_POINT__TIME_UNIX_NANO:
				return getTimeUnixNano();
			case ModelPackage.SUMMARY_DATA_POINT__COUNT:
				return getCount();
			case ModelPackage.SUMMARY_DATA_POINT__SUM:
				return getSum();
			case ModelPackage.SUMMARY_DATA_POINT__QUANTILE_VALUES:
				return getQuantileValues();
			case ModelPackage.SUMMARY_DATA_POINT__FLAGS:
				return getFlags();
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
			case ModelPackage.SUMMARY_DATA_POINT__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection<? extends KeyValue>)newValue);
				return;
			case ModelPackage.SUMMARY_DATA_POINT__START_TIME_UNIX_NANO:
				setStartTimeUnixNano((Long)newValue);
				return;
			case ModelPackage.SUMMARY_DATA_POINT__TIME_UNIX_NANO:
				setTimeUnixNano((Long)newValue);
				return;
			case ModelPackage.SUMMARY_DATA_POINT__COUNT:
				setCount((Long)newValue);
				return;
			case ModelPackage.SUMMARY_DATA_POINT__SUM:
				setSum((Double)newValue);
				return;
			case ModelPackage.SUMMARY_DATA_POINT__QUANTILE_VALUES:
				getQuantileValues().clear();
				getQuantileValues().addAll((Collection<? extends SummaryDataPointValueAtQuantile>)newValue);
				return;
			case ModelPackage.SUMMARY_DATA_POINT__FLAGS:
				setFlags((Integer)newValue);
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
			case ModelPackage.SUMMARY_DATA_POINT__ATTRIBUTES:
				getAttributes().clear();
				return;
			case ModelPackage.SUMMARY_DATA_POINT__START_TIME_UNIX_NANO:
				setStartTimeUnixNano(START_TIME_UNIX_NANO_EDEFAULT);
				return;
			case ModelPackage.SUMMARY_DATA_POINT__TIME_UNIX_NANO:
				setTimeUnixNano(TIME_UNIX_NANO_EDEFAULT);
				return;
			case ModelPackage.SUMMARY_DATA_POINT__COUNT:
				setCount(COUNT_EDEFAULT);
				return;
			case ModelPackage.SUMMARY_DATA_POINT__SUM:
				setSum(SUM_EDEFAULT);
				return;
			case ModelPackage.SUMMARY_DATA_POINT__QUANTILE_VALUES:
				getQuantileValues().clear();
				return;
			case ModelPackage.SUMMARY_DATA_POINT__FLAGS:
				setFlags(FLAGS_EDEFAULT);
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
			case ModelPackage.SUMMARY_DATA_POINT__ATTRIBUTES:
				return !getAttributes().isEmpty();
			case ModelPackage.SUMMARY_DATA_POINT__START_TIME_UNIX_NANO:
				return getStartTimeUnixNano() != START_TIME_UNIX_NANO_EDEFAULT;
			case ModelPackage.SUMMARY_DATA_POINT__TIME_UNIX_NANO:
				return getTimeUnixNano() != TIME_UNIX_NANO_EDEFAULT;
			case ModelPackage.SUMMARY_DATA_POINT__COUNT:
				return getCount() != COUNT_EDEFAULT;
			case ModelPackage.SUMMARY_DATA_POINT__SUM:
				return getSum() != SUM_EDEFAULT;
			case ModelPackage.SUMMARY_DATA_POINT__QUANTILE_VALUES:
				return !getQuantileValues().isEmpty();
			case ModelPackage.SUMMARY_DATA_POINT__FLAGS:
				return getFlags() != FLAGS_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //SummaryDataPointImpl
