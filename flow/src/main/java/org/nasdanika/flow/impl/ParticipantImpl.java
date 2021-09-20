/**
 */
package org.nasdanika.flow.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.flow.Activity;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Participant;

import org.nasdanika.ncore.impl.NamedElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Participant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.impl.ParticipantImpl#getExtends <em>Extends</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ParticipantImpl#getExtensions <em>Extensions</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ParticipantImpl#getServices <em>Services</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ParticipantImpl extends NamedElementImpl implements Participant {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ParticipantImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FlowPackage.Literals.PARTICIPANT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Participant> getExtends() {
		return (EList<Participant>)eDynamicGet(FlowPackage.PARTICIPANT__EXTENDS, FlowPackage.Literals.PARTICIPANT__EXTENDS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Participant> getExtensions() {
		return (EList<Participant>)eDynamicGet(FlowPackage.PARTICIPANT__EXTENSIONS, FlowPackage.Literals.PARTICIPANT__EXTENSIONS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, Activity> getServices() {
		return (EMap<String, Activity>)eDynamicGet(FlowPackage.PARTICIPANT__SERVICES, FlowPackage.Literals.PARTICIPANT__SERVICES, true, true);
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
			case FlowPackage.PARTICIPANT__EXTENDS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExtends()).basicAdd(otherEnd, msgs);
			case FlowPackage.PARTICIPANT__EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExtensions()).basicAdd(otherEnd, msgs);
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
			case FlowPackage.PARTICIPANT__EXTENDS:
				return ((InternalEList<?>)getExtends()).basicRemove(otherEnd, msgs);
			case FlowPackage.PARTICIPANT__EXTENSIONS:
				return ((InternalEList<?>)getExtensions()).basicRemove(otherEnd, msgs);
			case FlowPackage.PARTICIPANT__SERVICES:
				return ((InternalEList<?>)getServices()).basicRemove(otherEnd, msgs);
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
			case FlowPackage.PARTICIPANT__EXTENDS:
				return getExtends();
			case FlowPackage.PARTICIPANT__EXTENSIONS:
				return getExtensions();
			case FlowPackage.PARTICIPANT__SERVICES:
				if (coreType) return getServices();
				else return getServices().map();
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
			case FlowPackage.PARTICIPANT__EXTENDS:
				getExtends().clear();
				getExtends().addAll((Collection<? extends Participant>)newValue);
				return;
			case FlowPackage.PARTICIPANT__SERVICES:
				((EStructuralFeature.Setting)getServices()).set(newValue);
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
			case FlowPackage.PARTICIPANT__EXTENDS:
				getExtends().clear();
				return;
			case FlowPackage.PARTICIPANT__SERVICES:
				getServices().clear();
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
			case FlowPackage.PARTICIPANT__EXTENDS:
				return !getExtends().isEmpty();
			case FlowPackage.PARTICIPANT__EXTENSIONS:
				return !getExtensions().isEmpty();
			case FlowPackage.PARTICIPANT__SERVICES:
				return !getServices().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ParticipantImpl
