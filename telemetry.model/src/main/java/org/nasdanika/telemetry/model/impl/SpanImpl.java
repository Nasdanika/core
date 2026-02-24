/**
 */
package org.nasdanika.telemetry.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.telemetry.model.KeyValue;
import org.nasdanika.telemetry.model.ModelPackage;
import org.nasdanika.telemetry.model.Span;
import org.nasdanika.telemetry.model.SpanEvent;
import org.nasdanika.telemetry.model.SpanKind;
import org.nasdanika.telemetry.model.SpanLink;
import org.nasdanika.telemetry.model.SpanStatus;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Span</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SpanImpl#getTraceId <em>Trace Id</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SpanImpl#getSpanId <em>Span Id</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SpanImpl#getTraceState <em>Trace State</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SpanImpl#getParentSpanId <em>Parent Span Id</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SpanImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SpanImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SpanImpl#getStartTimeUnixNano <em>Start Time Unix Nano</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SpanImpl#getEndTimeUnixNano <em>End Time Unix Nano</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SpanImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SpanImpl#getDroppedAttributesCount <em>Dropped Attributes Count</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SpanImpl#getEvents <em>Events</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SpanImpl#getDroppedEventsCount <em>Dropped Events Count</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SpanImpl#getLinks <em>Links</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SpanImpl#getDroppedLinksCount <em>Dropped Links Count</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SpanImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SpanImpl#getFlags <em>Flags</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.SpanImpl#getChangeDescription <em>Change Description</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SpanImpl extends MinimalEObjectImpl.Container implements Span {
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
	 * The default value of the '{@link #getParentSpanId() <em>Parent Span Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentSpanId()
	 * @generated
	 * @ordered
	 */
	protected static final String PARENT_SPAN_ID_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final SpanKind KIND_EDEFAULT = SpanKind.SPAN_KIND_UNSPECIFIED;

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
	 * The default value of the '{@link #getEndTimeUnixNano() <em>End Time Unix Nano</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndTimeUnixNano()
	 * @generated
	 * @ordered
	 */
	protected static final long END_TIME_UNIX_NANO_EDEFAULT = 0L;

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
	 * The default value of the '{@link #getDroppedEventsCount() <em>Dropped Events Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDroppedEventsCount()
	 * @generated
	 * @ordered
	 */
	protected static final int DROPPED_EVENTS_COUNT_EDEFAULT = 0;

	/**
	 * The default value of the '{@link #getDroppedLinksCount() <em>Dropped Links Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDroppedLinksCount()
	 * @generated
	 * @ordered
	 */
	protected static final int DROPPED_LINKS_COUNT_EDEFAULT = 0;

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
	protected SpanImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.SPAN;
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
		return (String)eDynamicGet(ModelPackage.SPAN__TRACE_ID, ModelPackage.Literals.SPAN__TRACE_ID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTraceId(String newTraceId) {
		eDynamicSet(ModelPackage.SPAN__TRACE_ID, ModelPackage.Literals.SPAN__TRACE_ID, newTraceId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSpanId() {
		return (String)eDynamicGet(ModelPackage.SPAN__SPAN_ID, ModelPackage.Literals.SPAN__SPAN_ID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSpanId(String newSpanId) {
		eDynamicSet(ModelPackage.SPAN__SPAN_ID, ModelPackage.Literals.SPAN__SPAN_ID, newSpanId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTraceState() {
		return (String)eDynamicGet(ModelPackage.SPAN__TRACE_STATE, ModelPackage.Literals.SPAN__TRACE_STATE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTraceState(String newTraceState) {
		eDynamicSet(ModelPackage.SPAN__TRACE_STATE, ModelPackage.Literals.SPAN__TRACE_STATE, newTraceState);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getParentSpanId() {
		return (String)eDynamicGet(ModelPackage.SPAN__PARENT_SPAN_ID, ModelPackage.Literals.SPAN__PARENT_SPAN_ID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setParentSpanId(String newParentSpanId) {
		eDynamicSet(ModelPackage.SPAN__PARENT_SPAN_ID, ModelPackage.Literals.SPAN__PARENT_SPAN_ID, newParentSpanId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return (String)eDynamicGet(ModelPackage.SPAN__NAME, ModelPackage.Literals.SPAN__NAME, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		eDynamicSet(ModelPackage.SPAN__NAME, ModelPackage.Literals.SPAN__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SpanKind getKind() {
		return (SpanKind)eDynamicGet(ModelPackage.SPAN__KIND, ModelPackage.Literals.SPAN__KIND, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setKind(SpanKind newKind) {
		eDynamicSet(ModelPackage.SPAN__KIND, ModelPackage.Literals.SPAN__KIND, newKind);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public long getStartTimeUnixNano() {
		return (Long)eDynamicGet(ModelPackage.SPAN__START_TIME_UNIX_NANO, ModelPackage.Literals.SPAN__START_TIME_UNIX_NANO, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStartTimeUnixNano(long newStartTimeUnixNano) {
		eDynamicSet(ModelPackage.SPAN__START_TIME_UNIX_NANO, ModelPackage.Literals.SPAN__START_TIME_UNIX_NANO, newStartTimeUnixNano);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public long getEndTimeUnixNano() {
		return (Long)eDynamicGet(ModelPackage.SPAN__END_TIME_UNIX_NANO, ModelPackage.Literals.SPAN__END_TIME_UNIX_NANO, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEndTimeUnixNano(long newEndTimeUnixNano) {
		eDynamicSet(ModelPackage.SPAN__END_TIME_UNIX_NANO, ModelPackage.Literals.SPAN__END_TIME_UNIX_NANO, newEndTimeUnixNano);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<KeyValue> getAttributes() {
		return (EList<KeyValue>)eDynamicGet(ModelPackage.SPAN__ATTRIBUTES, ModelPackage.Literals.SPAN__ATTRIBUTES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getDroppedAttributesCount() {
		return (Integer)eDynamicGet(ModelPackage.SPAN__DROPPED_ATTRIBUTES_COUNT, ModelPackage.Literals.SPAN__DROPPED_ATTRIBUTES_COUNT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDroppedAttributesCount(int newDroppedAttributesCount) {
		eDynamicSet(ModelPackage.SPAN__DROPPED_ATTRIBUTES_COUNT, ModelPackage.Literals.SPAN__DROPPED_ATTRIBUTES_COUNT, newDroppedAttributesCount);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<SpanEvent> getEvents() {
		return (EList<SpanEvent>)eDynamicGet(ModelPackage.SPAN__EVENTS, ModelPackage.Literals.SPAN__EVENTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getDroppedEventsCount() {
		return (Integer)eDynamicGet(ModelPackage.SPAN__DROPPED_EVENTS_COUNT, ModelPackage.Literals.SPAN__DROPPED_EVENTS_COUNT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDroppedEventsCount(int newDroppedEventsCount) {
		eDynamicSet(ModelPackage.SPAN__DROPPED_EVENTS_COUNT, ModelPackage.Literals.SPAN__DROPPED_EVENTS_COUNT, newDroppedEventsCount);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<SpanLink> getLinks() {
		return (EList<SpanLink>)eDynamicGet(ModelPackage.SPAN__LINKS, ModelPackage.Literals.SPAN__LINKS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getDroppedLinksCount() {
		return (Integer)eDynamicGet(ModelPackage.SPAN__DROPPED_LINKS_COUNT, ModelPackage.Literals.SPAN__DROPPED_LINKS_COUNT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDroppedLinksCount(int newDroppedLinksCount) {
		eDynamicSet(ModelPackage.SPAN__DROPPED_LINKS_COUNT, ModelPackage.Literals.SPAN__DROPPED_LINKS_COUNT, newDroppedLinksCount);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SpanStatus getStatus() {
		return (SpanStatus)eDynamicGet(ModelPackage.SPAN__STATUS, ModelPackage.Literals.SPAN__STATUS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStatus(SpanStatus newStatus, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newStatus, ModelPackage.SPAN__STATUS, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStatus(SpanStatus newStatus) {
		eDynamicSet(ModelPackage.SPAN__STATUS, ModelPackage.Literals.SPAN__STATUS, newStatus);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getFlags() {
		return (Integer)eDynamicGet(ModelPackage.SPAN__FLAGS, ModelPackage.Literals.SPAN__FLAGS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFlags(int newFlags) {
		eDynamicSet(ModelPackage.SPAN__FLAGS, ModelPackage.Literals.SPAN__FLAGS, newFlags);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ChangeDescription getChangeDescription() {
		return (ChangeDescription)eDynamicGet(ModelPackage.SPAN__CHANGE_DESCRIPTION, ModelPackage.Literals.SPAN__CHANGE_DESCRIPTION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetChangeDescription(ChangeDescription newChangeDescription, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newChangeDescription, ModelPackage.SPAN__CHANGE_DESCRIPTION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setChangeDescription(ChangeDescription newChangeDescription) {
		eDynamicSet(ModelPackage.SPAN__CHANGE_DESCRIPTION, ModelPackage.Literals.SPAN__CHANGE_DESCRIPTION, newChangeDescription);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.SPAN__ATTRIBUTES:
				return ((InternalEList<?>)getAttributes()).basicRemove(otherEnd, msgs);
			case ModelPackage.SPAN__EVENTS:
				return ((InternalEList<?>)getEvents()).basicRemove(otherEnd, msgs);
			case ModelPackage.SPAN__LINKS:
				return ((InternalEList<?>)getLinks()).basicRemove(otherEnd, msgs);
			case ModelPackage.SPAN__STATUS:
				return basicSetStatus(null, msgs);
			case ModelPackage.SPAN__CHANGE_DESCRIPTION:
				return basicSetChangeDescription(null, msgs);
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
			case ModelPackage.SPAN__TRACE_ID:
				return getTraceId();
			case ModelPackage.SPAN__SPAN_ID:
				return getSpanId();
			case ModelPackage.SPAN__TRACE_STATE:
				return getTraceState();
			case ModelPackage.SPAN__PARENT_SPAN_ID:
				return getParentSpanId();
			case ModelPackage.SPAN__NAME:
				return getName();
			case ModelPackage.SPAN__KIND:
				return getKind();
			case ModelPackage.SPAN__START_TIME_UNIX_NANO:
				return getStartTimeUnixNano();
			case ModelPackage.SPAN__END_TIME_UNIX_NANO:
				return getEndTimeUnixNano();
			case ModelPackage.SPAN__ATTRIBUTES:
				return getAttributes();
			case ModelPackage.SPAN__DROPPED_ATTRIBUTES_COUNT:
				return getDroppedAttributesCount();
			case ModelPackage.SPAN__EVENTS:
				return getEvents();
			case ModelPackage.SPAN__DROPPED_EVENTS_COUNT:
				return getDroppedEventsCount();
			case ModelPackage.SPAN__LINKS:
				return getLinks();
			case ModelPackage.SPAN__DROPPED_LINKS_COUNT:
				return getDroppedLinksCount();
			case ModelPackage.SPAN__STATUS:
				return getStatus();
			case ModelPackage.SPAN__FLAGS:
				return getFlags();
			case ModelPackage.SPAN__CHANGE_DESCRIPTION:
				return getChangeDescription();
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
			case ModelPackage.SPAN__TRACE_ID:
				setTraceId((String)newValue);
				return;
			case ModelPackage.SPAN__SPAN_ID:
				setSpanId((String)newValue);
				return;
			case ModelPackage.SPAN__TRACE_STATE:
				setTraceState((String)newValue);
				return;
			case ModelPackage.SPAN__PARENT_SPAN_ID:
				setParentSpanId((String)newValue);
				return;
			case ModelPackage.SPAN__NAME:
				setName((String)newValue);
				return;
			case ModelPackage.SPAN__KIND:
				setKind((SpanKind)newValue);
				return;
			case ModelPackage.SPAN__START_TIME_UNIX_NANO:
				setStartTimeUnixNano((Long)newValue);
				return;
			case ModelPackage.SPAN__END_TIME_UNIX_NANO:
				setEndTimeUnixNano((Long)newValue);
				return;
			case ModelPackage.SPAN__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection<? extends KeyValue>)newValue);
				return;
			case ModelPackage.SPAN__DROPPED_ATTRIBUTES_COUNT:
				setDroppedAttributesCount((Integer)newValue);
				return;
			case ModelPackage.SPAN__EVENTS:
				getEvents().clear();
				getEvents().addAll((Collection<? extends SpanEvent>)newValue);
				return;
			case ModelPackage.SPAN__DROPPED_EVENTS_COUNT:
				setDroppedEventsCount((Integer)newValue);
				return;
			case ModelPackage.SPAN__LINKS:
				getLinks().clear();
				getLinks().addAll((Collection<? extends SpanLink>)newValue);
				return;
			case ModelPackage.SPAN__DROPPED_LINKS_COUNT:
				setDroppedLinksCount((Integer)newValue);
				return;
			case ModelPackage.SPAN__STATUS:
				setStatus((SpanStatus)newValue);
				return;
			case ModelPackage.SPAN__FLAGS:
				setFlags((Integer)newValue);
				return;
			case ModelPackage.SPAN__CHANGE_DESCRIPTION:
				setChangeDescription((ChangeDescription)newValue);
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
			case ModelPackage.SPAN__TRACE_ID:
				setTraceId(TRACE_ID_EDEFAULT);
				return;
			case ModelPackage.SPAN__SPAN_ID:
				setSpanId(SPAN_ID_EDEFAULT);
				return;
			case ModelPackage.SPAN__TRACE_STATE:
				setTraceState(TRACE_STATE_EDEFAULT);
				return;
			case ModelPackage.SPAN__PARENT_SPAN_ID:
				setParentSpanId(PARENT_SPAN_ID_EDEFAULT);
				return;
			case ModelPackage.SPAN__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModelPackage.SPAN__KIND:
				setKind(KIND_EDEFAULT);
				return;
			case ModelPackage.SPAN__START_TIME_UNIX_NANO:
				setStartTimeUnixNano(START_TIME_UNIX_NANO_EDEFAULT);
				return;
			case ModelPackage.SPAN__END_TIME_UNIX_NANO:
				setEndTimeUnixNano(END_TIME_UNIX_NANO_EDEFAULT);
				return;
			case ModelPackage.SPAN__ATTRIBUTES:
				getAttributes().clear();
				return;
			case ModelPackage.SPAN__DROPPED_ATTRIBUTES_COUNT:
				setDroppedAttributesCount(DROPPED_ATTRIBUTES_COUNT_EDEFAULT);
				return;
			case ModelPackage.SPAN__EVENTS:
				getEvents().clear();
				return;
			case ModelPackage.SPAN__DROPPED_EVENTS_COUNT:
				setDroppedEventsCount(DROPPED_EVENTS_COUNT_EDEFAULT);
				return;
			case ModelPackage.SPAN__LINKS:
				getLinks().clear();
				return;
			case ModelPackage.SPAN__DROPPED_LINKS_COUNT:
				setDroppedLinksCount(DROPPED_LINKS_COUNT_EDEFAULT);
				return;
			case ModelPackage.SPAN__STATUS:
				setStatus((SpanStatus)null);
				return;
			case ModelPackage.SPAN__FLAGS:
				setFlags(FLAGS_EDEFAULT);
				return;
			case ModelPackage.SPAN__CHANGE_DESCRIPTION:
				setChangeDescription((ChangeDescription)null);
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
			case ModelPackage.SPAN__TRACE_ID:
				return TRACE_ID_EDEFAULT == null ? getTraceId() != null : !TRACE_ID_EDEFAULT.equals(getTraceId());
			case ModelPackage.SPAN__SPAN_ID:
				return SPAN_ID_EDEFAULT == null ? getSpanId() != null : !SPAN_ID_EDEFAULT.equals(getSpanId());
			case ModelPackage.SPAN__TRACE_STATE:
				return TRACE_STATE_EDEFAULT == null ? getTraceState() != null : !TRACE_STATE_EDEFAULT.equals(getTraceState());
			case ModelPackage.SPAN__PARENT_SPAN_ID:
				return PARENT_SPAN_ID_EDEFAULT == null ? getParentSpanId() != null : !PARENT_SPAN_ID_EDEFAULT.equals(getParentSpanId());
			case ModelPackage.SPAN__NAME:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
			case ModelPackage.SPAN__KIND:
				return getKind() != KIND_EDEFAULT;
			case ModelPackage.SPAN__START_TIME_UNIX_NANO:
				return getStartTimeUnixNano() != START_TIME_UNIX_NANO_EDEFAULT;
			case ModelPackage.SPAN__END_TIME_UNIX_NANO:
				return getEndTimeUnixNano() != END_TIME_UNIX_NANO_EDEFAULT;
			case ModelPackage.SPAN__ATTRIBUTES:
				return !getAttributes().isEmpty();
			case ModelPackage.SPAN__DROPPED_ATTRIBUTES_COUNT:
				return getDroppedAttributesCount() != DROPPED_ATTRIBUTES_COUNT_EDEFAULT;
			case ModelPackage.SPAN__EVENTS:
				return !getEvents().isEmpty();
			case ModelPackage.SPAN__DROPPED_EVENTS_COUNT:
				return getDroppedEventsCount() != DROPPED_EVENTS_COUNT_EDEFAULT;
			case ModelPackage.SPAN__LINKS:
				return !getLinks().isEmpty();
			case ModelPackage.SPAN__DROPPED_LINKS_COUNT:
				return getDroppedLinksCount() != DROPPED_LINKS_COUNT_EDEFAULT;
			case ModelPackage.SPAN__STATUS:
				return getStatus() != null;
			case ModelPackage.SPAN__FLAGS:
				return getFlags() != FLAGS_EDEFAULT;
			case ModelPackage.SPAN__CHANGE_DESCRIPTION:
				return getChangeDescription() != null;
		}
		return super.eIsSet(featureID);
	}

} //SpanImpl
