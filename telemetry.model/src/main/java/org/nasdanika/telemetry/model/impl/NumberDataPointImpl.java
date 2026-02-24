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

import org.nasdanika.telemetry.model.Exemplar;
import org.nasdanika.telemetry.model.KeyValue;
import org.nasdanika.telemetry.model.ModelPackage;
import org.nasdanika.telemetry.model.NumberDataPoint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Number Data Point</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.impl.NumberDataPointImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.NumberDataPointImpl#getStartTimeUnixNano <em>Start Time Unix Nano</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.NumberDataPointImpl#getTimeUnixNano <em>Time Unix Nano</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.NumberDataPointImpl#getAsDouble <em>As Double</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.NumberDataPointImpl#getAsInt <em>As Int</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.NumberDataPointImpl#getExemplars <em>Exemplars</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.NumberDataPointImpl#getFlags <em>Flags</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NumberDataPointImpl extends MinimalEObjectImpl.Container implements NumberDataPoint {
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
	 * The default value of the '{@link #getAsDouble() <em>As Double</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAsDouble()
	 * @generated
	 * @ordered
	 */
	protected static final Double AS_DOUBLE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getAsInt() <em>As Int</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAsInt()
	 * @generated
	 * @ordered
	 */
	protected static final Long AS_INT_EDEFAULT = null;

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
	protected NumberDataPointImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.NUMBER_DATA_POINT;
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
		return (EList<KeyValue>)eDynamicGet(ModelPackage.NUMBER_DATA_POINT__ATTRIBUTES, ModelPackage.Literals.NUMBER_DATA_POINT__ATTRIBUTES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public long getStartTimeUnixNano() {
		return (Long)eDynamicGet(ModelPackage.NUMBER_DATA_POINT__START_TIME_UNIX_NANO, ModelPackage.Literals.NUMBER_DATA_POINT__START_TIME_UNIX_NANO, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStartTimeUnixNano(long newStartTimeUnixNano) {
		eDynamicSet(ModelPackage.NUMBER_DATA_POINT__START_TIME_UNIX_NANO, ModelPackage.Literals.NUMBER_DATA_POINT__START_TIME_UNIX_NANO, newStartTimeUnixNano);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public long getTimeUnixNano() {
		return (Long)eDynamicGet(ModelPackage.NUMBER_DATA_POINT__TIME_UNIX_NANO, ModelPackage.Literals.NUMBER_DATA_POINT__TIME_UNIX_NANO, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTimeUnixNano(long newTimeUnixNano) {
		eDynamicSet(ModelPackage.NUMBER_DATA_POINT__TIME_UNIX_NANO, ModelPackage.Literals.NUMBER_DATA_POINT__TIME_UNIX_NANO, newTimeUnixNano);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getAsDouble() {
		return (Double)eDynamicGet(ModelPackage.NUMBER_DATA_POINT__AS_DOUBLE, ModelPackage.Literals.NUMBER_DATA_POINT__AS_DOUBLE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAsDouble(Double newAsDouble) {
		eDynamicSet(ModelPackage.NUMBER_DATA_POINT__AS_DOUBLE, ModelPackage.Literals.NUMBER_DATA_POINT__AS_DOUBLE, newAsDouble);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Long getAsInt() {
		return (Long)eDynamicGet(ModelPackage.NUMBER_DATA_POINT__AS_INT, ModelPackage.Literals.NUMBER_DATA_POINT__AS_INT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAsInt(Long newAsInt) {
		eDynamicSet(ModelPackage.NUMBER_DATA_POINT__AS_INT, ModelPackage.Literals.NUMBER_DATA_POINT__AS_INT, newAsInt);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Exemplar> getExemplars() {
		return (EList<Exemplar>)eDynamicGet(ModelPackage.NUMBER_DATA_POINT__EXEMPLARS, ModelPackage.Literals.NUMBER_DATA_POINT__EXEMPLARS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getFlags() {
		return (Integer)eDynamicGet(ModelPackage.NUMBER_DATA_POINT__FLAGS, ModelPackage.Literals.NUMBER_DATA_POINT__FLAGS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFlags(int newFlags) {
		eDynamicSet(ModelPackage.NUMBER_DATA_POINT__FLAGS, ModelPackage.Literals.NUMBER_DATA_POINT__FLAGS, newFlags);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.NUMBER_DATA_POINT__ATTRIBUTES:
				return ((InternalEList<?>)getAttributes()).basicRemove(otherEnd, msgs);
			case ModelPackage.NUMBER_DATA_POINT__EXEMPLARS:
				return ((InternalEList<?>)getExemplars()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.NUMBER_DATA_POINT__ATTRIBUTES:
				return getAttributes();
			case ModelPackage.NUMBER_DATA_POINT__START_TIME_UNIX_NANO:
				return getStartTimeUnixNano();
			case ModelPackage.NUMBER_DATA_POINT__TIME_UNIX_NANO:
				return getTimeUnixNano();
			case ModelPackage.NUMBER_DATA_POINT__AS_DOUBLE:
				return getAsDouble();
			case ModelPackage.NUMBER_DATA_POINT__AS_INT:
				return getAsInt();
			case ModelPackage.NUMBER_DATA_POINT__EXEMPLARS:
				return getExemplars();
			case ModelPackage.NUMBER_DATA_POINT__FLAGS:
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
			case ModelPackage.NUMBER_DATA_POINT__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection<? extends KeyValue>)newValue);
				return;
			case ModelPackage.NUMBER_DATA_POINT__START_TIME_UNIX_NANO:
				setStartTimeUnixNano((Long)newValue);
				return;
			case ModelPackage.NUMBER_DATA_POINT__TIME_UNIX_NANO:
				setTimeUnixNano((Long)newValue);
				return;
			case ModelPackage.NUMBER_DATA_POINT__AS_DOUBLE:
				setAsDouble((Double)newValue);
				return;
			case ModelPackage.NUMBER_DATA_POINT__AS_INT:
				setAsInt((Long)newValue);
				return;
			case ModelPackage.NUMBER_DATA_POINT__EXEMPLARS:
				getExemplars().clear();
				getExemplars().addAll((Collection<? extends Exemplar>)newValue);
				return;
			case ModelPackage.NUMBER_DATA_POINT__FLAGS:
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
			case ModelPackage.NUMBER_DATA_POINT__ATTRIBUTES:
				getAttributes().clear();
				return;
			case ModelPackage.NUMBER_DATA_POINT__START_TIME_UNIX_NANO:
				setStartTimeUnixNano(START_TIME_UNIX_NANO_EDEFAULT);
				return;
			case ModelPackage.NUMBER_DATA_POINT__TIME_UNIX_NANO:
				setTimeUnixNano(TIME_UNIX_NANO_EDEFAULT);
				return;
			case ModelPackage.NUMBER_DATA_POINT__AS_DOUBLE:
				setAsDouble(AS_DOUBLE_EDEFAULT);
				return;
			case ModelPackage.NUMBER_DATA_POINT__AS_INT:
				setAsInt(AS_INT_EDEFAULT);
				return;
			case ModelPackage.NUMBER_DATA_POINT__EXEMPLARS:
				getExemplars().clear();
				return;
			case ModelPackage.NUMBER_DATA_POINT__FLAGS:
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
			case ModelPackage.NUMBER_DATA_POINT__ATTRIBUTES:
				return !getAttributes().isEmpty();
			case ModelPackage.NUMBER_DATA_POINT__START_TIME_UNIX_NANO:
				return getStartTimeUnixNano() != START_TIME_UNIX_NANO_EDEFAULT;
			case ModelPackage.NUMBER_DATA_POINT__TIME_UNIX_NANO:
				return getTimeUnixNano() != TIME_UNIX_NANO_EDEFAULT;
			case ModelPackage.NUMBER_DATA_POINT__AS_DOUBLE:
				return AS_DOUBLE_EDEFAULT == null ? getAsDouble() != null : !AS_DOUBLE_EDEFAULT.equals(getAsDouble());
			case ModelPackage.NUMBER_DATA_POINT__AS_INT:
				return AS_INT_EDEFAULT == null ? getAsInt() != null : !AS_INT_EDEFAULT.equals(getAsInt());
			case ModelPackage.NUMBER_DATA_POINT__EXEMPLARS:
				return !getExemplars().isEmpty();
			case ModelPackage.NUMBER_DATA_POINT__FLAGS:
				return getFlags() != FLAGS_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //NumberDataPointImpl
