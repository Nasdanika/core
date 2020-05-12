/**
 */
package org.nasdanika.rigel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.rigel.Activity;
import org.nasdanika.rigel.FlowElement;
import org.nasdanika.rigel.ActivityReference;
import org.nasdanika.rigel.Artifact;
import org.nasdanika.rigel.RigelPackage;
import org.nasdanika.rigel.Source;
import org.nasdanika.rigel.Target;
import org.nasdanika.rigel.Transition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.rigel.impl.ActivityReferenceImpl#getOutboundTransitions <em>Outbound Transitions</em>}</li>
 *   <li>{@link org.nasdanika.rigel.impl.ActivityReferenceImpl#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link org.nasdanika.rigel.impl.ActivityReferenceImpl#getInboundTransitions <em>Inbound Transitions</em>}</li>
 *   <li>{@link org.nasdanika.rigel.impl.ActivityReferenceImpl#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.nasdanika.rigel.impl.ActivityReferenceImpl#getActivity <em>Activity</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ActivityReferenceImpl extends PackageElementImpl implements ActivityReference {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActivityReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RigelPackage.Literals.ACTIVITY_REFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Transition> getOutboundTransitions() {
		return (EList<Transition>)eDynamicGet(RigelPackage.ACTIVITY_REFERENCE__OUTBOUND_TRANSITIONS, RigelPackage.Literals.SOURCE__OUTBOUND_TRANSITIONS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Artifact> getOutputs() {
		return (EList<Artifact>)eDynamicGet(RigelPackage.ACTIVITY_REFERENCE__OUTPUTS, RigelPackage.Literals.SOURCE__OUTPUTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Transition> getInboundTransitions() {
		return (EList<Transition>)eDynamicGet(RigelPackage.ACTIVITY_REFERENCE__INBOUND_TRANSITIONS, RigelPackage.Literals.TARGET__INBOUND_TRANSITIONS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Artifact> getInputs() {
		return (EList<Artifact>)eDynamicGet(RigelPackage.ACTIVITY_REFERENCE__INPUTS, RigelPackage.Literals.TARGET__INPUTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Activity getActivity() {
		return (Activity)eDynamicGet(RigelPackage.ACTIVITY_REFERENCE__ACTIVITY, RigelPackage.Literals.ACTIVITY_REFERENCE__ACTIVITY, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Activity basicGetActivity() {
		return (Activity)eDynamicGet(RigelPackage.ACTIVITY_REFERENCE__ACTIVITY, RigelPackage.Literals.ACTIVITY_REFERENCE__ACTIVITY, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setActivity(Activity newActivity) {
		eDynamicSet(RigelPackage.ACTIVITY_REFERENCE__ACTIVITY, RigelPackage.Literals.ACTIVITY_REFERENCE__ACTIVITY, newActivity);
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
			case RigelPackage.ACTIVITY_REFERENCE__OUTPUTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutputs()).basicAdd(otherEnd, msgs);
			case RigelPackage.ACTIVITY_REFERENCE__INBOUND_TRANSITIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInboundTransitions()).basicAdd(otherEnd, msgs);
			case RigelPackage.ACTIVITY_REFERENCE__INPUTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInputs()).basicAdd(otherEnd, msgs);
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
			case RigelPackage.ACTIVITY_REFERENCE__OUTBOUND_TRANSITIONS:
				return ((InternalEList<?>)getOutboundTransitions()).basicRemove(otherEnd, msgs);
			case RigelPackage.ACTIVITY_REFERENCE__OUTPUTS:
				return ((InternalEList<?>)getOutputs()).basicRemove(otherEnd, msgs);
			case RigelPackage.ACTIVITY_REFERENCE__INBOUND_TRANSITIONS:
				return ((InternalEList<?>)getInboundTransitions()).basicRemove(otherEnd, msgs);
			case RigelPackage.ACTIVITY_REFERENCE__INPUTS:
				return ((InternalEList<?>)getInputs()).basicRemove(otherEnd, msgs);
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
			case RigelPackage.ACTIVITY_REFERENCE__OUTBOUND_TRANSITIONS:
				return getOutboundTransitions();
			case RigelPackage.ACTIVITY_REFERENCE__OUTPUTS:
				return getOutputs();
			case RigelPackage.ACTIVITY_REFERENCE__INBOUND_TRANSITIONS:
				return getInboundTransitions();
			case RigelPackage.ACTIVITY_REFERENCE__INPUTS:
				return getInputs();
			case RigelPackage.ACTIVITY_REFERENCE__ACTIVITY:
				if (resolve) return getActivity();
				return basicGetActivity();
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
			case RigelPackage.ACTIVITY_REFERENCE__OUTBOUND_TRANSITIONS:
				getOutboundTransitions().clear();
				getOutboundTransitions().addAll((Collection<? extends Transition>)newValue);
				return;
			case RigelPackage.ACTIVITY_REFERENCE__OUTPUTS:
				getOutputs().clear();
				getOutputs().addAll((Collection<? extends Artifact>)newValue);
				return;
			case RigelPackage.ACTIVITY_REFERENCE__INBOUND_TRANSITIONS:
				getInboundTransitions().clear();
				getInboundTransitions().addAll((Collection<? extends Transition>)newValue);
				return;
			case RigelPackage.ACTIVITY_REFERENCE__INPUTS:
				getInputs().clear();
				getInputs().addAll((Collection<? extends Artifact>)newValue);
				return;
			case RigelPackage.ACTIVITY_REFERENCE__ACTIVITY:
				setActivity((Activity)newValue);
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
			case RigelPackage.ACTIVITY_REFERENCE__OUTBOUND_TRANSITIONS:
				getOutboundTransitions().clear();
				return;
			case RigelPackage.ACTIVITY_REFERENCE__OUTPUTS:
				getOutputs().clear();
				return;
			case RigelPackage.ACTIVITY_REFERENCE__INBOUND_TRANSITIONS:
				getInboundTransitions().clear();
				return;
			case RigelPackage.ACTIVITY_REFERENCE__INPUTS:
				getInputs().clear();
				return;
			case RigelPackage.ACTIVITY_REFERENCE__ACTIVITY:
				setActivity((Activity)null);
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
			case RigelPackage.ACTIVITY_REFERENCE__OUTBOUND_TRANSITIONS:
				return !getOutboundTransitions().isEmpty();
			case RigelPackage.ACTIVITY_REFERENCE__OUTPUTS:
				return !getOutputs().isEmpty();
			case RigelPackage.ACTIVITY_REFERENCE__INBOUND_TRANSITIONS:
				return !getInboundTransitions().isEmpty();
			case RigelPackage.ACTIVITY_REFERENCE__INPUTS:
				return !getInputs().isEmpty();
			case RigelPackage.ACTIVITY_REFERENCE__ACTIVITY:
				return basicGetActivity() != null;
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
		if (baseClass == FlowElement.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == Source.class) {
			switch (derivedFeatureID) {
				case RigelPackage.ACTIVITY_REFERENCE__OUTBOUND_TRANSITIONS: return RigelPackage.SOURCE__OUTBOUND_TRANSITIONS;
				case RigelPackage.ACTIVITY_REFERENCE__OUTPUTS: return RigelPackage.SOURCE__OUTPUTS;
				default: return -1;
			}
		}
		if (baseClass == Target.class) {
			switch (derivedFeatureID) {
				case RigelPackage.ACTIVITY_REFERENCE__INBOUND_TRANSITIONS: return RigelPackage.TARGET__INBOUND_TRANSITIONS;
				case RigelPackage.ACTIVITY_REFERENCE__INPUTS: return RigelPackage.TARGET__INPUTS;
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
		if (baseClass == FlowElement.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == Source.class) {
			switch (baseFeatureID) {
				case RigelPackage.SOURCE__OUTBOUND_TRANSITIONS: return RigelPackage.ACTIVITY_REFERENCE__OUTBOUND_TRANSITIONS;
				case RigelPackage.SOURCE__OUTPUTS: return RigelPackage.ACTIVITY_REFERENCE__OUTPUTS;
				default: return -1;
			}
		}
		if (baseClass == Target.class) {
			switch (baseFeatureID) {
				case RigelPackage.TARGET__INBOUND_TRANSITIONS: return RigelPackage.ACTIVITY_REFERENCE__INBOUND_TRANSITIONS;
				case RigelPackage.TARGET__INPUTS: return RigelPackage.ACTIVITY_REFERENCE__INPUTS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //ActivityReferenceImpl
