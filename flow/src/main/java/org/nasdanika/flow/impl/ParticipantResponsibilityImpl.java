/**
 */
package org.nasdanika.flow.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.PackageElement;
import org.nasdanika.flow.Participant;
import org.nasdanika.flow.ParticipantResponsibility;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Participant Responsibility</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.impl.ParticipantResponsibilityImpl#getResponsible <em>Responsible</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ParticipantResponsibilityImpl#getResponsibleKeys <em>Responsible Keys</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ParticipantResponsibilityImpl#getAccountable <em>Accountable</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ParticipantResponsibilityImpl#getAccountableKeys <em>Accountable Keys</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ParticipantResponsibilityImpl#getConsulted <em>Consulted</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ParticipantResponsibilityImpl#getConsultedKeys <em>Consulted Keys</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ParticipantResponsibilityImpl#getInformed <em>Informed</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ParticipantResponsibilityImpl#getInformedKeys <em>Informed Keys</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ParticipantResponsibilityImpl<T extends PackageElement<T>> extends PackageElementImpl<T> implements ParticipantResponsibility<T> {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ParticipantResponsibilityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FlowPackage.Literals.PARTICIPANT_RESPONSIBILITY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Participant> getResponsible() {
		return resolveParticipants(getResponsibleKeys());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<String> getResponsibleKeys() {
		return (EList<String>)eDynamicGet(FlowPackage.PARTICIPANT_RESPONSIBILITY__RESPONSIBLE_KEYS, FlowPackage.Literals.PARTICIPANT_RESPONSIBILITY__RESPONSIBLE_KEYS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Participant> getAccountable() {
		return resolveParticipants(getAccountableKeys());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<String> getAccountableKeys() {
		return (EList<String>)eDynamicGet(FlowPackage.PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE_KEYS, FlowPackage.Literals.PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE_KEYS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Participant> getConsulted() {
		return resolveParticipants(getConsultedKeys());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<String> getConsultedKeys() {
		return (EList<String>)eDynamicGet(FlowPackage.PARTICIPANT_RESPONSIBILITY__CONSULTED_KEYS, FlowPackage.Literals.PARTICIPANT_RESPONSIBILITY__CONSULTED_KEYS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Participant> getInformed() {
		return resolveParticipants(getInformedKeys());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<String> getInformedKeys() {
		return (EList<String>)eDynamicGet(FlowPackage.PARTICIPANT_RESPONSIBILITY__INFORMED_KEYS, FlowPackage.Literals.PARTICIPANT_RESPONSIBILITY__INFORMED_KEYS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__RESPONSIBLE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getResponsible()).basicAdd(otherEnd, msgs);
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAccountable()).basicAdd(otherEnd, msgs);
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__CONSULTED:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getConsulted()).basicAdd(otherEnd, msgs);
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__INFORMED:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInformed()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__RESPONSIBLE:
				return ((InternalEList<?>)getResponsible()).basicRemove(otherEnd, msgs);
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE:
				return ((InternalEList<?>)getAccountable()).basicRemove(otherEnd, msgs);
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__CONSULTED:
				return ((InternalEList<?>)getConsulted()).basicRemove(otherEnd, msgs);
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__INFORMED:
				return ((InternalEList<?>)getInformed()).basicRemove(otherEnd, msgs);
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
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__RESPONSIBLE:
				return getResponsible();
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__RESPONSIBLE_KEYS:
				return getResponsibleKeys();
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE:
				return getAccountable();
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE_KEYS:
				return getAccountableKeys();
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__CONSULTED:
				return getConsulted();
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__CONSULTED_KEYS:
				return getConsultedKeys();
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__INFORMED:
				return getInformed();
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__INFORMED_KEYS:
				return getInformedKeys();
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
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__RESPONSIBLE_KEYS:
				getResponsibleKeys().clear();
				getResponsibleKeys().addAll((Collection<? extends String>)newValue);
				return;
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE_KEYS:
				getAccountableKeys().clear();
				getAccountableKeys().addAll((Collection<? extends String>)newValue);
				return;
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__CONSULTED_KEYS:
				getConsultedKeys().clear();
				getConsultedKeys().addAll((Collection<? extends String>)newValue);
				return;
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__INFORMED_KEYS:
				getInformedKeys().clear();
				getInformedKeys().addAll((Collection<? extends String>)newValue);
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
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__RESPONSIBLE_KEYS:
				getResponsibleKeys().clear();
				return;
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE_KEYS:
				getAccountableKeys().clear();
				return;
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__CONSULTED_KEYS:
				getConsultedKeys().clear();
				return;
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__INFORMED_KEYS:
				getInformedKeys().clear();
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
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__RESPONSIBLE:
				return !getResponsible().isEmpty();
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__RESPONSIBLE_KEYS:
				return !getResponsibleKeys().isEmpty();
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE:
				return !getAccountable().isEmpty();
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE_KEYS:
				return !getAccountableKeys().isEmpty();
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__CONSULTED:
				return !getConsulted().isEmpty();
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__CONSULTED_KEYS:
				return !getConsultedKeys().isEmpty();
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__INFORMED:
				return !getInformed().isEmpty();
			case FlowPackage.PARTICIPANT_RESPONSIBILITY__INFORMED_KEYS:
				return !getInformedKeys().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ParticipantResponsibilityImpl
