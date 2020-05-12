/**
 */
package org.nasdanika.rigel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.nasdanika.rigel.Artifact;
import org.nasdanika.rigel.RigelPackage;
import org.nasdanika.rigel.Target;
import org.nasdanika.rigel.Transition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.rigel.impl.TransitionImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.nasdanika.rigel.impl.TransitionImpl#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.nasdanika.rigel.impl.TransitionImpl#getResults <em>Results</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TransitionImpl extends ModelElementImpl implements Transition {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RigelPackage.Literals.TRANSITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Target getTarget() {
		return (Target)eDynamicGet(RigelPackage.TRANSITION__TARGET, RigelPackage.Literals.TRANSITION__TARGET, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Target basicGetTarget() {
		return (Target)eDynamicGet(RigelPackage.TRANSITION__TARGET, RigelPackage.Literals.TRANSITION__TARGET, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTarget(Target newTarget, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newTarget, RigelPackage.TRANSITION__TARGET, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTarget(Target newTarget) {
		eDynamicSet(RigelPackage.TRANSITION__TARGET, RigelPackage.Literals.TRANSITION__TARGET, newTarget);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Artifact> getInputs() {
		return (EList<Artifact>)eDynamicGet(RigelPackage.TRANSITION__INPUTS, RigelPackage.Literals.TRANSITION__INPUTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Artifact> getResults() {
		return (EList<Artifact>)eDynamicGet(RigelPackage.TRANSITION__RESULTS, RigelPackage.Literals.TRANSITION__RESULTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RigelPackage.TRANSITION__TARGET:
				Target target = basicGetTarget();
				if (target != null)
					msgs = ((InternalEObject)target).eInverseRemove(this, RigelPackage.TARGET__INBOUND_TRANSITIONS, Target.class, msgs);
				return basicSetTarget((Target)otherEnd, msgs);
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
			case RigelPackage.TRANSITION__TARGET:
				return basicSetTarget(null, msgs);
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
			case RigelPackage.TRANSITION__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case RigelPackage.TRANSITION__INPUTS:
				return getInputs();
			case RigelPackage.TRANSITION__RESULTS:
				return getResults();
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
			case RigelPackage.TRANSITION__TARGET:
				setTarget((Target)newValue);
				return;
			case RigelPackage.TRANSITION__INPUTS:
				getInputs().clear();
				getInputs().addAll((Collection<? extends Artifact>)newValue);
				return;
			case RigelPackage.TRANSITION__RESULTS:
				getResults().clear();
				getResults().addAll((Collection<? extends Artifact>)newValue);
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
			case RigelPackage.TRANSITION__TARGET:
				setTarget((Target)null);
				return;
			case RigelPackage.TRANSITION__INPUTS:
				getInputs().clear();
				return;
			case RigelPackage.TRANSITION__RESULTS:
				getResults().clear();
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
			case RigelPackage.TRANSITION__TARGET:
				return basicGetTarget() != null;
			case RigelPackage.TRANSITION__INPUTS:
				return !getInputs().isEmpty();
			case RigelPackage.TRANSITION__RESULTS:
				return !getResults().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TransitionImpl
