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

import org.nasdanika.telemetry.model.AnyValue;
import org.nasdanika.telemetry.model.KeyValue;
import org.nasdanika.telemetry.model.LogRecord;
import org.nasdanika.telemetry.model.ModelPackage;
import org.nasdanika.telemetry.model.SeverityNumber;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Log Record</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.impl.LogRecordImpl#getTimeUnixNano <em>Time Unix Nano</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.LogRecordImpl#getObservedTimeUnixNano <em>Observed Time Unix Nano</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.LogRecordImpl#getSeverityNumber <em>Severity Number</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.LogRecordImpl#getSeverityText <em>Severity Text</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.LogRecordImpl#getBody <em>Body</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.LogRecordImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.LogRecordImpl#getDroppedAttributesCount <em>Dropped Attributes Count</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.LogRecordImpl#getFlags <em>Flags</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.LogRecordImpl#getTraceId <em>Trace Id</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.LogRecordImpl#getSpanId <em>Span Id</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LogRecordImpl extends MinimalEObjectImpl.Container implements LogRecord {
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
	 * The default value of the '{@link #getObservedTimeUnixNano() <em>Observed Time Unix Nano</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObservedTimeUnixNano()
	 * @generated
	 * @ordered
	 */
	protected static final long OBSERVED_TIME_UNIX_NANO_EDEFAULT = 0L;

	/**
	 * The default value of the '{@link #getSeverityNumber() <em>Severity Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeverityNumber()
	 * @generated
	 * @ordered
	 */
	protected static final SeverityNumber SEVERITY_NUMBER_EDEFAULT = SeverityNumber.SEVERITY_NUMBER_UNSPECIFIED;

	/**
	 * The default value of the '{@link #getSeverityText() <em>Severity Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeverityText()
	 * @generated
	 * @ordered
	 */
	protected static final String SEVERITY_TEXT_EDEFAULT = null;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LogRecordImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.LOG_RECORD;
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
	public long getTimeUnixNano() {
		return (Long)eDynamicGet(ModelPackage.LOG_RECORD__TIME_UNIX_NANO, ModelPackage.Literals.LOG_RECORD__TIME_UNIX_NANO, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTimeUnixNano(long newTimeUnixNano) {
		eDynamicSet(ModelPackage.LOG_RECORD__TIME_UNIX_NANO, ModelPackage.Literals.LOG_RECORD__TIME_UNIX_NANO, newTimeUnixNano);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public long getObservedTimeUnixNano() {
		return (Long)eDynamicGet(ModelPackage.LOG_RECORD__OBSERVED_TIME_UNIX_NANO, ModelPackage.Literals.LOG_RECORD__OBSERVED_TIME_UNIX_NANO, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setObservedTimeUnixNano(long newObservedTimeUnixNano) {
		eDynamicSet(ModelPackage.LOG_RECORD__OBSERVED_TIME_UNIX_NANO, ModelPackage.Literals.LOG_RECORD__OBSERVED_TIME_UNIX_NANO, newObservedTimeUnixNano);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SeverityNumber getSeverityNumber() {
		return (SeverityNumber)eDynamicGet(ModelPackage.LOG_RECORD__SEVERITY_NUMBER, ModelPackage.Literals.LOG_RECORD__SEVERITY_NUMBER, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSeverityNumber(SeverityNumber newSeverityNumber) {
		eDynamicSet(ModelPackage.LOG_RECORD__SEVERITY_NUMBER, ModelPackage.Literals.LOG_RECORD__SEVERITY_NUMBER, newSeverityNumber);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSeverityText() {
		return (String)eDynamicGet(ModelPackage.LOG_RECORD__SEVERITY_TEXT, ModelPackage.Literals.LOG_RECORD__SEVERITY_TEXT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSeverityText(String newSeverityText) {
		eDynamicSet(ModelPackage.LOG_RECORD__SEVERITY_TEXT, ModelPackage.Literals.LOG_RECORD__SEVERITY_TEXT, newSeverityText);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AnyValue getBody() {
		return (AnyValue)eDynamicGet(ModelPackage.LOG_RECORD__BODY, ModelPackage.Literals.LOG_RECORD__BODY, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBody(AnyValue newBody, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newBody, ModelPackage.LOG_RECORD__BODY, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBody(AnyValue newBody) {
		eDynamicSet(ModelPackage.LOG_RECORD__BODY, ModelPackage.Literals.LOG_RECORD__BODY, newBody);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<KeyValue> getAttributes() {
		return (EList<KeyValue>)eDynamicGet(ModelPackage.LOG_RECORD__ATTRIBUTES, ModelPackage.Literals.LOG_RECORD__ATTRIBUTES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getDroppedAttributesCount() {
		return (Integer)eDynamicGet(ModelPackage.LOG_RECORD__DROPPED_ATTRIBUTES_COUNT, ModelPackage.Literals.LOG_RECORD__DROPPED_ATTRIBUTES_COUNT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDroppedAttributesCount(int newDroppedAttributesCount) {
		eDynamicSet(ModelPackage.LOG_RECORD__DROPPED_ATTRIBUTES_COUNT, ModelPackage.Literals.LOG_RECORD__DROPPED_ATTRIBUTES_COUNT, newDroppedAttributesCount);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getFlags() {
		return (Integer)eDynamicGet(ModelPackage.LOG_RECORD__FLAGS, ModelPackage.Literals.LOG_RECORD__FLAGS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFlags(int newFlags) {
		eDynamicSet(ModelPackage.LOG_RECORD__FLAGS, ModelPackage.Literals.LOG_RECORD__FLAGS, newFlags);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTraceId() {
		return (String)eDynamicGet(ModelPackage.LOG_RECORD__TRACE_ID, ModelPackage.Literals.LOG_RECORD__TRACE_ID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTraceId(String newTraceId) {
		eDynamicSet(ModelPackage.LOG_RECORD__TRACE_ID, ModelPackage.Literals.LOG_RECORD__TRACE_ID, newTraceId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSpanId() {
		return (String)eDynamicGet(ModelPackage.LOG_RECORD__SPAN_ID, ModelPackage.Literals.LOG_RECORD__SPAN_ID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSpanId(String newSpanId) {
		eDynamicSet(ModelPackage.LOG_RECORD__SPAN_ID, ModelPackage.Literals.LOG_RECORD__SPAN_ID, newSpanId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.LOG_RECORD__BODY:
				return basicSetBody(null, msgs);
			case ModelPackage.LOG_RECORD__ATTRIBUTES:
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
			case ModelPackage.LOG_RECORD__TIME_UNIX_NANO:
				return getTimeUnixNano();
			case ModelPackage.LOG_RECORD__OBSERVED_TIME_UNIX_NANO:
				return getObservedTimeUnixNano();
			case ModelPackage.LOG_RECORD__SEVERITY_NUMBER:
				return getSeverityNumber();
			case ModelPackage.LOG_RECORD__SEVERITY_TEXT:
				return getSeverityText();
			case ModelPackage.LOG_RECORD__BODY:
				return getBody();
			case ModelPackage.LOG_RECORD__ATTRIBUTES:
				return getAttributes();
			case ModelPackage.LOG_RECORD__DROPPED_ATTRIBUTES_COUNT:
				return getDroppedAttributesCount();
			case ModelPackage.LOG_RECORD__FLAGS:
				return getFlags();
			case ModelPackage.LOG_RECORD__TRACE_ID:
				return getTraceId();
			case ModelPackage.LOG_RECORD__SPAN_ID:
				return getSpanId();
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
			case ModelPackage.LOG_RECORD__TIME_UNIX_NANO:
				setTimeUnixNano((Long)newValue);
				return;
			case ModelPackage.LOG_RECORD__OBSERVED_TIME_UNIX_NANO:
				setObservedTimeUnixNano((Long)newValue);
				return;
			case ModelPackage.LOG_RECORD__SEVERITY_NUMBER:
				setSeverityNumber((SeverityNumber)newValue);
				return;
			case ModelPackage.LOG_RECORD__SEVERITY_TEXT:
				setSeverityText((String)newValue);
				return;
			case ModelPackage.LOG_RECORD__BODY:
				setBody((AnyValue)newValue);
				return;
			case ModelPackage.LOG_RECORD__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection<? extends KeyValue>)newValue);
				return;
			case ModelPackage.LOG_RECORD__DROPPED_ATTRIBUTES_COUNT:
				setDroppedAttributesCount((Integer)newValue);
				return;
			case ModelPackage.LOG_RECORD__FLAGS:
				setFlags((Integer)newValue);
				return;
			case ModelPackage.LOG_RECORD__TRACE_ID:
				setTraceId((String)newValue);
				return;
			case ModelPackage.LOG_RECORD__SPAN_ID:
				setSpanId((String)newValue);
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
			case ModelPackage.LOG_RECORD__TIME_UNIX_NANO:
				setTimeUnixNano(TIME_UNIX_NANO_EDEFAULT);
				return;
			case ModelPackage.LOG_RECORD__OBSERVED_TIME_UNIX_NANO:
				setObservedTimeUnixNano(OBSERVED_TIME_UNIX_NANO_EDEFAULT);
				return;
			case ModelPackage.LOG_RECORD__SEVERITY_NUMBER:
				setSeverityNumber(SEVERITY_NUMBER_EDEFAULT);
				return;
			case ModelPackage.LOG_RECORD__SEVERITY_TEXT:
				setSeverityText(SEVERITY_TEXT_EDEFAULT);
				return;
			case ModelPackage.LOG_RECORD__BODY:
				setBody((AnyValue)null);
				return;
			case ModelPackage.LOG_RECORD__ATTRIBUTES:
				getAttributes().clear();
				return;
			case ModelPackage.LOG_RECORD__DROPPED_ATTRIBUTES_COUNT:
				setDroppedAttributesCount(DROPPED_ATTRIBUTES_COUNT_EDEFAULT);
				return;
			case ModelPackage.LOG_RECORD__FLAGS:
				setFlags(FLAGS_EDEFAULT);
				return;
			case ModelPackage.LOG_RECORD__TRACE_ID:
				setTraceId(TRACE_ID_EDEFAULT);
				return;
			case ModelPackage.LOG_RECORD__SPAN_ID:
				setSpanId(SPAN_ID_EDEFAULT);
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
			case ModelPackage.LOG_RECORD__TIME_UNIX_NANO:
				return getTimeUnixNano() != TIME_UNIX_NANO_EDEFAULT;
			case ModelPackage.LOG_RECORD__OBSERVED_TIME_UNIX_NANO:
				return getObservedTimeUnixNano() != OBSERVED_TIME_UNIX_NANO_EDEFAULT;
			case ModelPackage.LOG_RECORD__SEVERITY_NUMBER:
				return getSeverityNumber() != SEVERITY_NUMBER_EDEFAULT;
			case ModelPackage.LOG_RECORD__SEVERITY_TEXT:
				return SEVERITY_TEXT_EDEFAULT == null ? getSeverityText() != null : !SEVERITY_TEXT_EDEFAULT.equals(getSeverityText());
			case ModelPackage.LOG_RECORD__BODY:
				return getBody() != null;
			case ModelPackage.LOG_RECORD__ATTRIBUTES:
				return !getAttributes().isEmpty();
			case ModelPackage.LOG_RECORD__DROPPED_ATTRIBUTES_COUNT:
				return getDroppedAttributesCount() != DROPPED_ATTRIBUTES_COUNT_EDEFAULT;
			case ModelPackage.LOG_RECORD__FLAGS:
				return getFlags() != FLAGS_EDEFAULT;
			case ModelPackage.LOG_RECORD__TRACE_ID:
				return TRACE_ID_EDEFAULT == null ? getTraceId() != null : !TRACE_ID_EDEFAULT.equals(getTraceId());
			case ModelPackage.LOG_RECORD__SPAN_ID:
				return SPAN_ID_EDEFAULT == null ? getSpanId() != null : !SPAN_ID_EDEFAULT.equals(getSpanId());
		}
		return super.eIsSet(featureID);
	}

} //LogRecordImpl
