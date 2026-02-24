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
import org.nasdanika.telemetry.model.Span;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Exemplar</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.impl.ExemplarImpl#getFilteredAttributes <em>Filtered Attributes</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.ExemplarImpl#getTimeUnixNano <em>Time Unix Nano</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.ExemplarImpl#getAsDouble <em>As Double</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.ExemplarImpl#getAsInt <em>As Int</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.ExemplarImpl#getSpanId <em>Span Id</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.ExemplarImpl#getTraceId <em>Trace Id</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.ExemplarImpl#getSpan <em>Span</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExemplarImpl extends MinimalEObjectImpl.Container implements Exemplar {
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
	 * The default value of the '{@link #getSpanId() <em>Span Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpanId()
	 * @generated
	 * @ordered
	 */
	protected static final String SPAN_ID_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getTraceId() <em>Trace Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTraceId()
	 * @generated
	 * @ordered
	 */
	protected static final String TRACE_ID_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExemplarImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.EXEMPLAR;
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
	public EList<KeyValue> getFilteredAttributes() {
		return (EList<KeyValue>)eDynamicGet(ModelPackage.EXEMPLAR__FILTERED_ATTRIBUTES, ModelPackage.Literals.EXEMPLAR__FILTERED_ATTRIBUTES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public long getTimeUnixNano() {
		return (Long)eDynamicGet(ModelPackage.EXEMPLAR__TIME_UNIX_NANO, ModelPackage.Literals.EXEMPLAR__TIME_UNIX_NANO, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTimeUnixNano(long newTimeUnixNano) {
		eDynamicSet(ModelPackage.EXEMPLAR__TIME_UNIX_NANO, ModelPackage.Literals.EXEMPLAR__TIME_UNIX_NANO, newTimeUnixNano);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getAsDouble() {
		return (Double)eDynamicGet(ModelPackage.EXEMPLAR__AS_DOUBLE, ModelPackage.Literals.EXEMPLAR__AS_DOUBLE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAsDouble(Double newAsDouble) {
		eDynamicSet(ModelPackage.EXEMPLAR__AS_DOUBLE, ModelPackage.Literals.EXEMPLAR__AS_DOUBLE, newAsDouble);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Long getAsInt() {
		return (Long)eDynamicGet(ModelPackage.EXEMPLAR__AS_INT, ModelPackage.Literals.EXEMPLAR__AS_INT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAsInt(Long newAsInt) {
		eDynamicSet(ModelPackage.EXEMPLAR__AS_INT, ModelPackage.Literals.EXEMPLAR__AS_INT, newAsInt);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSpanId() {
		return (String)eDynamicGet(ModelPackage.EXEMPLAR__SPAN_ID, ModelPackage.Literals.EXEMPLAR__SPAN_ID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSpanId(String newSpanId) {
		eDynamicSet(ModelPackage.EXEMPLAR__SPAN_ID, ModelPackage.Literals.EXEMPLAR__SPAN_ID, newSpanId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTraceId() {
		return (String)eDynamicGet(ModelPackage.EXEMPLAR__TRACE_ID, ModelPackage.Literals.EXEMPLAR__TRACE_ID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTraceId(String newTraceId) {
		eDynamicSet(ModelPackage.EXEMPLAR__TRACE_ID, ModelPackage.Literals.EXEMPLAR__TRACE_ID, newTraceId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Span getSpan() {
		return (Span)eDynamicGet(ModelPackage.EXEMPLAR__SPAN, ModelPackage.Literals.EXEMPLAR__SPAN, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Span basicGetSpan() {
		return (Span)eDynamicGet(ModelPackage.EXEMPLAR__SPAN, ModelPackage.Literals.EXEMPLAR__SPAN, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSpan(Span newSpan) {
		eDynamicSet(ModelPackage.EXEMPLAR__SPAN, ModelPackage.Literals.EXEMPLAR__SPAN, newSpan);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.EXEMPLAR__FILTERED_ATTRIBUTES:
				return ((InternalEList<?>)getFilteredAttributes()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.EXEMPLAR__FILTERED_ATTRIBUTES:
				return getFilteredAttributes();
			case ModelPackage.EXEMPLAR__TIME_UNIX_NANO:
				return getTimeUnixNano();
			case ModelPackage.EXEMPLAR__AS_DOUBLE:
				return getAsDouble();
			case ModelPackage.EXEMPLAR__AS_INT:
				return getAsInt();
			case ModelPackage.EXEMPLAR__SPAN_ID:
				return getSpanId();
			case ModelPackage.EXEMPLAR__TRACE_ID:
				return getTraceId();
			case ModelPackage.EXEMPLAR__SPAN:
				if (resolve) return getSpan();
				return basicGetSpan();
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
			case ModelPackage.EXEMPLAR__FILTERED_ATTRIBUTES:
				getFilteredAttributes().clear();
				getFilteredAttributes().addAll((Collection<? extends KeyValue>)newValue);
				return;
			case ModelPackage.EXEMPLAR__TIME_UNIX_NANO:
				setTimeUnixNano((Long)newValue);
				return;
			case ModelPackage.EXEMPLAR__AS_DOUBLE:
				setAsDouble((Double)newValue);
				return;
			case ModelPackage.EXEMPLAR__AS_INT:
				setAsInt((Long)newValue);
				return;
			case ModelPackage.EXEMPLAR__SPAN_ID:
				setSpanId((String)newValue);
				return;
			case ModelPackage.EXEMPLAR__TRACE_ID:
				setTraceId((String)newValue);
				return;
			case ModelPackage.EXEMPLAR__SPAN:
				setSpan((Span)newValue);
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
			case ModelPackage.EXEMPLAR__FILTERED_ATTRIBUTES:
				getFilteredAttributes().clear();
				return;
			case ModelPackage.EXEMPLAR__TIME_UNIX_NANO:
				setTimeUnixNano(TIME_UNIX_NANO_EDEFAULT);
				return;
			case ModelPackage.EXEMPLAR__AS_DOUBLE:
				setAsDouble(AS_DOUBLE_EDEFAULT);
				return;
			case ModelPackage.EXEMPLAR__AS_INT:
				setAsInt(AS_INT_EDEFAULT);
				return;
			case ModelPackage.EXEMPLAR__SPAN_ID:
				setSpanId(SPAN_ID_EDEFAULT);
				return;
			case ModelPackage.EXEMPLAR__TRACE_ID:
				setTraceId(TRACE_ID_EDEFAULT);
				return;
			case ModelPackage.EXEMPLAR__SPAN:
				setSpan((Span)null);
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
			case ModelPackage.EXEMPLAR__FILTERED_ATTRIBUTES:
				return !getFilteredAttributes().isEmpty();
			case ModelPackage.EXEMPLAR__TIME_UNIX_NANO:
				return getTimeUnixNano() != TIME_UNIX_NANO_EDEFAULT;
			case ModelPackage.EXEMPLAR__AS_DOUBLE:
				return AS_DOUBLE_EDEFAULT == null ? getAsDouble() != null : !AS_DOUBLE_EDEFAULT.equals(getAsDouble());
			case ModelPackage.EXEMPLAR__AS_INT:
				return AS_INT_EDEFAULT == null ? getAsInt() != null : !AS_INT_EDEFAULT.equals(getAsInt());
			case ModelPackage.EXEMPLAR__SPAN_ID:
				return SPAN_ID_EDEFAULT == null ? getSpanId() != null : !SPAN_ID_EDEFAULT.equals(getSpanId());
			case ModelPackage.EXEMPLAR__TRACE_ID:
				return TRACE_ID_EDEFAULT == null ? getTraceId() != null : !TRACE_ID_EDEFAULT.equals(getTraceId());
			case ModelPackage.EXEMPLAR__SPAN:
				return basicGetSpan() != null;
		}
		return super.eIsSet(featureID);
	}

} //ExemplarImpl
