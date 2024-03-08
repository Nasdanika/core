/**
 */
package org.nasdanika.ncore.impl;

import java.lang.String;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.ncore.NcorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Throwable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.impl.ThrowableImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.ThrowableImpl#getMessage <em>Message</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.ThrowableImpl#getStackTrace <em>Stack Trace</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.ThrowableImpl#getSupressed <em>Supressed</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.ThrowableImpl#getCause <em>Cause</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ThrowableImpl extends MinimalEObjectImpl.Container implements org.nasdanika.ncore.Throwable {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getMessage() <em>Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessage()
	 * @generated
	 * @ordered
	 */
	protected static final String MESSAGE_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ThrowableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NcorePackage.Literals.THROWABLE;
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
	public String getType() {
		return (String)eDynamicGet(NcorePackage.THROWABLE__TYPE, NcorePackage.Literals.THROWABLE__TYPE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(String newType) {
		eDynamicSet(NcorePackage.THROWABLE__TYPE, NcorePackage.Literals.THROWABLE__TYPE, newType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMessage() {
		return (String)eDynamicGet(NcorePackage.THROWABLE__MESSAGE, NcorePackage.Literals.THROWABLE__MESSAGE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMessage(String newMessage) {
		eDynamicSet(NcorePackage.THROWABLE__MESSAGE, NcorePackage.Literals.THROWABLE__MESSAGE, newMessage);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<org.nasdanika.ncore.StackTraceElement> getStackTrace() {
		return (EList<org.nasdanika.ncore.StackTraceElement>)eDynamicGet(NcorePackage.THROWABLE__STACK_TRACE, NcorePackage.Literals.THROWABLE__STACK_TRACE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<org.nasdanika.ncore.Throwable> getSupressed() {
		return (EList<org.nasdanika.ncore.Throwable>)eDynamicGet(NcorePackage.THROWABLE__SUPRESSED, NcorePackage.Literals.THROWABLE__SUPRESSED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.nasdanika.ncore.Throwable getCause() {
		return (org.nasdanika.ncore.Throwable)eDynamicGet(NcorePackage.THROWABLE__CAUSE, NcorePackage.Literals.THROWABLE__CAUSE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCause(org.nasdanika.ncore.Throwable newCause, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newCause, NcorePackage.THROWABLE__CAUSE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCause(org.nasdanika.ncore.Throwable newCause) {
		eDynamicSet(NcorePackage.THROWABLE__CAUSE, NcorePackage.Literals.THROWABLE__CAUSE, newCause);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NcorePackage.THROWABLE__STACK_TRACE:
				return ((InternalEList<?>)getStackTrace()).basicRemove(otherEnd, msgs);
			case NcorePackage.THROWABLE__SUPRESSED:
				return ((InternalEList<?>)getSupressed()).basicRemove(otherEnd, msgs);
			case NcorePackage.THROWABLE__CAUSE:
				return basicSetCause(null, msgs);
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
			case NcorePackage.THROWABLE__TYPE:
				return getType();
			case NcorePackage.THROWABLE__MESSAGE:
				return getMessage();
			case NcorePackage.THROWABLE__STACK_TRACE:
				return getStackTrace();
			case NcorePackage.THROWABLE__SUPRESSED:
				return getSupressed();
			case NcorePackage.THROWABLE__CAUSE:
				return getCause();
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
			case NcorePackage.THROWABLE__TYPE:
				setType((String)newValue);
				return;
			case NcorePackage.THROWABLE__MESSAGE:
				setMessage((String)newValue);
				return;
			case NcorePackage.THROWABLE__STACK_TRACE:
				getStackTrace().clear();
				getStackTrace().addAll((Collection<? extends org.nasdanika.ncore.StackTraceElement>)newValue);
				return;
			case NcorePackage.THROWABLE__SUPRESSED:
				getSupressed().clear();
				getSupressed().addAll((Collection<? extends org.nasdanika.ncore.Throwable>)newValue);
				return;
			case NcorePackage.THROWABLE__CAUSE:
				setCause((org.nasdanika.ncore.Throwable)newValue);
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
			case NcorePackage.THROWABLE__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case NcorePackage.THROWABLE__MESSAGE:
				setMessage(MESSAGE_EDEFAULT);
				return;
			case NcorePackage.THROWABLE__STACK_TRACE:
				getStackTrace().clear();
				return;
			case NcorePackage.THROWABLE__SUPRESSED:
				getSupressed().clear();
				return;
			case NcorePackage.THROWABLE__CAUSE:
				setCause((org.nasdanika.ncore.Throwable)null);
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
			case NcorePackage.THROWABLE__TYPE:
				return TYPE_EDEFAULT == null ? getType() != null : !TYPE_EDEFAULT.equals(getType());
			case NcorePackage.THROWABLE__MESSAGE:
				return MESSAGE_EDEFAULT == null ? getMessage() != null : !MESSAGE_EDEFAULT.equals(getMessage());
			case NcorePackage.THROWABLE__STACK_TRACE:
				return !getStackTrace().isEmpty();
			case NcorePackage.THROWABLE__SUPRESSED:
				return !getSupressed().isEmpty();
			case NcorePackage.THROWABLE__CAUSE:
				return getCause() != null;
		}
		return super.eIsSet(featureID);
	}

} //ThrowableImpl
