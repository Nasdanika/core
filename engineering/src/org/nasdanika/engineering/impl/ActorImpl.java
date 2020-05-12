/**
 */
package org.nasdanika.rigel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.rigel.Actor;
import org.nasdanika.rigel.Engineer;
import org.nasdanika.rigel.EngineeredElement;
import org.nasdanika.rigel.Flow;
import org.nasdanika.rigel.Issue;
import org.nasdanika.rigel.Participant;
import org.nasdanika.rigel.RigelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Actor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.rigel.impl.ActorImpl#getOwners <em>Owners</em>}</li>
 *   <li>{@link org.nasdanika.rigel.impl.ActorImpl#getIssues <em>Issues</em>}</li>
 *   <li>{@link org.nasdanika.rigel.impl.ActorImpl#getFlows <em>Flows</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ActorImpl extends PackageElementImpl implements Actor {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RigelPackage.Literals.ACTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Engineer> getOwners() {
		return (EList<Engineer>)eDynamicGet(RigelPackage.ACTOR__OWNERS, RigelPackage.Literals.ENGINEERED_ELEMENT__OWNERS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Issue> getIssues() {
		return (EList<Issue>)eDynamicGet(RigelPackage.ACTOR__ISSUES, RigelPackage.Literals.ENGINEERED_ELEMENT__ISSUES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Flow> getFlows() {
		return (EList<Flow>)eDynamicGet(RigelPackage.ACTOR__FLOWS, RigelPackage.Literals.PARTICIPANT__FLOWS, true, true);
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
			case RigelPackage.ACTOR__OWNERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwners()).basicAdd(otherEnd, msgs);
			case RigelPackage.ACTOR__FLOWS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFlows()).basicAdd(otherEnd, msgs);
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
			case RigelPackage.ACTOR__OWNERS:
				return ((InternalEList<?>)getOwners()).basicRemove(otherEnd, msgs);
			case RigelPackage.ACTOR__ISSUES:
				return ((InternalEList<?>)getIssues()).basicRemove(otherEnd, msgs);
			case RigelPackage.ACTOR__FLOWS:
				return ((InternalEList<?>)getFlows()).basicRemove(otherEnd, msgs);
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
			case RigelPackage.ACTOR__OWNERS:
				return getOwners();
			case RigelPackage.ACTOR__ISSUES:
				return getIssues();
			case RigelPackage.ACTOR__FLOWS:
				return getFlows();
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
			case RigelPackage.ACTOR__OWNERS:
				getOwners().clear();
				getOwners().addAll((Collection<? extends Engineer>)newValue);
				return;
			case RigelPackage.ACTOR__ISSUES:
				getIssues().clear();
				getIssues().addAll((Collection<? extends Issue>)newValue);
				return;
			case RigelPackage.ACTOR__FLOWS:
				getFlows().clear();
				getFlows().addAll((Collection<? extends Flow>)newValue);
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
			case RigelPackage.ACTOR__OWNERS:
				getOwners().clear();
				return;
			case RigelPackage.ACTOR__ISSUES:
				getIssues().clear();
				return;
			case RigelPackage.ACTOR__FLOWS:
				getFlows().clear();
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
			case RigelPackage.ACTOR__OWNERS:
				return !getOwners().isEmpty();
			case RigelPackage.ACTOR__ISSUES:
				return !getIssues().isEmpty();
			case RigelPackage.ACTOR__FLOWS:
				return !getFlows().isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == EngineeredElement.class) {
			switch (derivedFeatureID) {
				case RigelPackage.ACTOR__OWNERS: return RigelPackage.ENGINEERED_ELEMENT__OWNERS;
				case RigelPackage.ACTOR__ISSUES: return RigelPackage.ENGINEERED_ELEMENT__ISSUES;
				default: return -1;
			}
		}
		if (baseClass == Participant.class) {
			switch (derivedFeatureID) {
				case RigelPackage.ACTOR__FLOWS: return RigelPackage.PARTICIPANT__FLOWS;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == EngineeredElement.class) {
			switch (baseFeatureID) {
				case RigelPackage.ENGINEERED_ELEMENT__OWNERS: return RigelPackage.ACTOR__OWNERS;
				case RigelPackage.ENGINEERED_ELEMENT__ISSUES: return RigelPackage.ACTOR__ISSUES;
				default: return -1;
			}
		}
		if (baseClass == Participant.class) {
			switch (baseFeatureID) {
				case RigelPackage.PARTICIPANT__FLOWS: return RigelPackage.ACTOR__FLOWS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //ActorImpl
