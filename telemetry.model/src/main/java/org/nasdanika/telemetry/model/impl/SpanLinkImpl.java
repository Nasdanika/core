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
import org.nasdanika.telemetry.model.SpanLink;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Span Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SpanLinkImpl#getTraceId <em>Trace Id</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SpanLinkImpl#getSpanId <em>Span Id</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SpanLinkImpl#getTraceState <em>Trace State</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SpanLinkImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SpanLinkImpl#getDroppedAttributesCount <em>Dropped Attributes Count</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SpanLinkImpl#getFlags <em>Flags</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SpanLinkImpl extends MinimalEObjectImpl.Container implements SpanLink {
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
	 * The default value of the '{@link #getSpanId() <em>Span Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpanId()
	 * @generated
	 * @ordered
	 */
	protected static final String SPAN_ID_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getTraceState() <em>Trace State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTraceState()
	 * @generated
	 * @ordered
	 */
	protected static final String TRACE_STATE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getDroppedAttributesCount() <em>Dropped Attributes Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDroppedAttributesCount()
	 * @generated
	 * @ordered
	 */
	protected static final int DROPPED_ATTRIBUTES_COUNT_EDEFAULT = 0;

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
	protected SpanLinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.SPAN_LINK;
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
	public String getTraceId() {
		return (String)eDynamicGet(ModelPackage.SPAN_LINK__TRACE_ID, ModelPackage.Literals.SPAN_LINK__TRACE_ID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTraceId(String newTraceId) {
		eDynamicSet(ModelPackage.SPAN_LINK__TRACE_ID, ModelPackage.Literals.SPAN_LINK__TRACE_ID, newTraceId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSpanId() {
		return (String)eDynamicGet(ModelPackage.SPAN_LINK__SPAN_ID, ModelPackage.Literals.SPAN_LINK__SPAN_ID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSpanId(String newSpanId) {
		eDynamicSet(ModelPackage.SPAN_LINK__SPAN_ID, ModelPackage.Literals.SPAN_LINK__SPAN_ID, newSpanId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTraceState() {
		return (String)eDynamicGet(ModelPackage.SPAN_LINK__TRACE_STATE, ModelPackage.Literals.SPAN_LINK__TRACE_STATE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTraceState(String newTraceState) {
		eDynamicSet(ModelPackage.SPAN_LINK__TRACE_STATE, ModelPackage.Literals.SPAN_LINK__TRACE_STATE, newTraceState);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<KeyValue> getAttributes() {
		return (EList<KeyValue>)eDynamicGet(ModelPackage.SPAN_LINK__ATTRIBUTES, ModelPackage.Literals.SPAN_LINK__ATTRIBUTES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getDroppedAttributesCount() {
		return (Integer)eDynamicGet(ModelPackage.SPAN_LINK__DROPPED_ATTRIBUTES_COUNT, ModelPackage.Literals.SPAN_LINK__DROPPED_ATTRIBUTES_COUNT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDroppedAttributesCount(int newDroppedAttributesCount) {
		eDynamicSet(ModelPackage.SPAN_LINK__DROPPED_ATTRIBUTES_COUNT, ModelPackage.Literals.SPAN_LINK__DROPPED_ATTRIBUTES_COUNT, newDroppedAttributesCount);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getFlags() {
		return (Integer)eDynamicGet(ModelPackage.SPAN_LINK__FLAGS, ModelPackage.Literals.SPAN_LINK__FLAGS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFlags(int newFlags) {
		eDynamicSet(ModelPackage.SPAN_LINK__FLAGS, ModelPackage.Literals.SPAN_LINK__FLAGS, newFlags);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.SPAN_LINK__ATTRIBUTES:
				return ((InternalEList<?>)getAttributes()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.SPAN_LINK__TRACE_ID:
				return getTraceId();
			case ModelPackage.SPAN_LINK__SPAN_ID:
				return getSpanId();
			case ModelPackage.SPAN_LINK__TRACE_STATE:
				return getTraceState();
			case ModelPackage.SPAN_LINK__ATTRIBUTES:
				return getAttributes();
			case ModelPackage.SPAN_LINK__DROPPED_ATTRIBUTES_COUNT:
				return getDroppedAttributesCount();
			case ModelPackage.SPAN_LINK__FLAGS:
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
			case ModelPackage.SPAN_LINK__TRACE_ID:
				setTraceId((String)newValue);
				return;
			case ModelPackage.SPAN_LINK__SPAN_ID:
				setSpanId((String)newValue);
				return;
			case ModelPackage.SPAN_LINK__TRACE_STATE:
				setTraceState((String)newValue);
				return;
			case ModelPackage.SPAN_LINK__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection<? extends KeyValue>)newValue);
				return;
			case ModelPackage.SPAN_LINK__DROPPED_ATTRIBUTES_COUNT:
				setDroppedAttributesCount((Integer)newValue);
				return;
			case ModelPackage.SPAN_LINK__FLAGS:
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
			case ModelPackage.SPAN_LINK__TRACE_ID:
				setTraceId(TRACE_ID_EDEFAULT);
				return;
			case ModelPackage.SPAN_LINK__SPAN_ID:
				setSpanId(SPAN_ID_EDEFAULT);
				return;
			case ModelPackage.SPAN_LINK__TRACE_STATE:
				setTraceState(TRACE_STATE_EDEFAULT);
				return;
			case ModelPackage.SPAN_LINK__ATTRIBUTES:
				getAttributes().clear();
				return;
			case ModelPackage.SPAN_LINK__DROPPED_ATTRIBUTES_COUNT:
				setDroppedAttributesCount(DROPPED_ATTRIBUTES_COUNT_EDEFAULT);
				return;
			case ModelPackage.SPAN_LINK__FLAGS:
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
			case ModelPackage.SPAN_LINK__TRACE_ID:
				return TRACE_ID_EDEFAULT == null ? getTraceId() != null : !TRACE_ID_EDEFAULT.equals(getTraceId());
			case ModelPackage.SPAN_LINK__SPAN_ID:
				return SPAN_ID_EDEFAULT == null ? getSpanId() != null : !SPAN_ID_EDEFAULT.equals(getSpanId());
			case ModelPackage.SPAN_LINK__TRACE_STATE:
				return TRACE_STATE_EDEFAULT == null ? getTraceState() != null : !TRACE_STATE_EDEFAULT.equals(getTraceState());
			case ModelPackage.SPAN_LINK__ATTRIBUTES:
				return !getAttributes().isEmpty();
			case ModelPackage.SPAN_LINK__DROPPED_ATTRIBUTES_COUNT:
				return getDroppedAttributesCount() != DROPPED_ATTRIBUTES_COUNT_EDEFAULT;
			case ModelPackage.SPAN_LINK__FLAGS:
				return getFlags() != FLAGS_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //SpanLinkImpl
