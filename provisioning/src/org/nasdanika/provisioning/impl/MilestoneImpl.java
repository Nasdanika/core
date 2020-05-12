/**
 */
package org.nasdanika.rigel.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.rigel.ActivityReference;
import org.nasdanika.rigel.Artifact;
import org.nasdanika.rigel.Flow;
import org.nasdanika.rigel.FlowElement;
import org.nasdanika.rigel.Milestone;
import org.nasdanika.rigel.RigelPackage;
import org.nasdanika.rigel.Source;
import org.nasdanika.rigel.Target;
import org.nasdanika.rigel.Transition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Milestone</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.rigel.impl.MilestoneImpl#getOutboundTransitions <em>Outbound Transitions</em>}</li>
 *   <li>{@link org.nasdanika.rigel.impl.MilestoneImpl#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link org.nasdanika.rigel.impl.MilestoneImpl#getInboundTransitions <em>Inbound Transitions</em>}</li>
 *   <li>{@link org.nasdanika.rigel.impl.MilestoneImpl#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.nasdanika.rigel.impl.MilestoneImpl#getSize <em>Size</em>}</li>
 *   <li>{@link org.nasdanika.rigel.impl.MilestoneImpl#getProgress <em>Progress</em>}</li>
 *   <li>{@link org.nasdanika.rigel.impl.MilestoneImpl#getTargetDate <em>Target Date</em>}</li>
 *   <li>{@link org.nasdanika.rigel.impl.MilestoneImpl#isMissed <em>Missed</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MilestoneImpl extends PackageElementImpl implements Milestone {
	/**
	 * The default value of the '{@link #getSize() <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSize()
	 * @generated
	 * @ordered
	 */
	protected static final double SIZE_EDEFAULT = 0.0;
	/**
	 * The default value of the '{@link #getProgress() <em>Progress</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProgress()
	 * @generated
	 * @ordered
	 */
	protected static final int PROGRESS_EDEFAULT = 0;

	/**
	 * The default value of the '{@link #getTargetDate() <em>Target Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetDate()
	 * @generated
	 * @ordered
	 */
	protected static final Date TARGET_DATE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isMissed() <em>Missed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMissed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MISSED_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MilestoneImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RigelPackage.Literals.MILESTONE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Transition> getOutboundTransitions() {
		return (EList<Transition>)eDynamicGet(RigelPackage.MILESTONE__OUTBOUND_TRANSITIONS, RigelPackage.Literals.SOURCE__OUTBOUND_TRANSITIONS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Artifact> getOutputs() {
		return (EList<Artifact>)eDynamicGet(RigelPackage.MILESTONE__OUTPUTS, RigelPackage.Literals.SOURCE__OUTPUTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Transition> getInboundTransitions() {
		return (EList<Transition>)eDynamicGet(RigelPackage.MILESTONE__INBOUND_TRANSITIONS, RigelPackage.Literals.TARGET__INBOUND_TRANSITIONS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Artifact> getInputs() {
		return (EList<Artifact>)eDynamicGet(RigelPackage.MILESTONE__INPUTS, RigelPackage.Literals.TARGET__INPUTS, true, true);
	}
	
	/**
	 * Traverses 
	 * @param target
	 * @param flows
	 */
	protected Set<Flow> collectIboundFlows(Target target, Set<Flow> flows) {
		for (Transition it: target.getInboundTransitions()) {
			EObject source = it.eContainer();
			if (source instanceof Flow && flows.add((Flow) source) && source instanceof Target) {
				collectIboundFlows((Target) source, flows);
			}
			if (source instanceof ActivityReference && flows.add(((ActivityReference) source).getActivity())) {
				collectIboundFlows((ActivityReference) source, flows);
			}
		}
		return flows;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public double getSize() {
		return collectIboundFlows(this, new HashSet<Flow>()).stream().mapToDouble(Flow::getTotalSize).sum();
	}
		
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int getProgress() {
		Set<Flow> inboundFlows = collectIboundFlows(this, new HashSet<Flow>());
		double size = inboundFlows.stream().mapToDouble(Flow::getTotalSize).sum();
		double worked = inboundFlows.stream().mapToDouble(e -> e.getTotalSize() * e.getTotalProgress()).sum();
		return size == 0 ? 0 : (int) Math.round(worked/size);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getTargetDate() {
		return (Date)eDynamicGet(RigelPackage.MILESTONE__TARGET_DATE, RigelPackage.Literals.MILESTONE__TARGET_DATE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTargetDate(Date newTargetDate) {
		eDynamicSet(RigelPackage.MILESTONE__TARGET_DATE, RigelPackage.Literals.MILESTONE__TARGET_DATE, newTargetDate);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isMissed() {
    	return getTargetDate() != null && new Date().after(getTargetDate()) && getSize() > 0 && getProgress() < 100;
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
			case RigelPackage.MILESTONE__OUTPUTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutputs()).basicAdd(otherEnd, msgs);
			case RigelPackage.MILESTONE__INBOUND_TRANSITIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInboundTransitions()).basicAdd(otherEnd, msgs);
			case RigelPackage.MILESTONE__INPUTS:
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
			case RigelPackage.MILESTONE__OUTBOUND_TRANSITIONS:
				return ((InternalEList<?>)getOutboundTransitions()).basicRemove(otherEnd, msgs);
			case RigelPackage.MILESTONE__OUTPUTS:
				return ((InternalEList<?>)getOutputs()).basicRemove(otherEnd, msgs);
			case RigelPackage.MILESTONE__INBOUND_TRANSITIONS:
				return ((InternalEList<?>)getInboundTransitions()).basicRemove(otherEnd, msgs);
			case RigelPackage.MILESTONE__INPUTS:
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
			case RigelPackage.MILESTONE__OUTBOUND_TRANSITIONS:
				return getOutboundTransitions();
			case RigelPackage.MILESTONE__OUTPUTS:
				return getOutputs();
			case RigelPackage.MILESTONE__INBOUND_TRANSITIONS:
				return getInboundTransitions();
			case RigelPackage.MILESTONE__INPUTS:
				return getInputs();
			case RigelPackage.MILESTONE__SIZE:
				return getSize();
			case RigelPackage.MILESTONE__PROGRESS:
				return getProgress();
			case RigelPackage.MILESTONE__TARGET_DATE:
				return getTargetDate();
			case RigelPackage.MILESTONE__MISSED:
				return isMissed();
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
			case RigelPackage.MILESTONE__OUTBOUND_TRANSITIONS:
				getOutboundTransitions().clear();
				getOutboundTransitions().addAll((Collection<? extends Transition>)newValue);
				return;
			case RigelPackage.MILESTONE__OUTPUTS:
				getOutputs().clear();
				getOutputs().addAll((Collection<? extends Artifact>)newValue);
				return;
			case RigelPackage.MILESTONE__INBOUND_TRANSITIONS:
				getInboundTransitions().clear();
				getInboundTransitions().addAll((Collection<? extends Transition>)newValue);
				return;
			case RigelPackage.MILESTONE__INPUTS:
				getInputs().clear();
				getInputs().addAll((Collection<? extends Artifact>)newValue);
				return;
			case RigelPackage.MILESTONE__TARGET_DATE:
				setTargetDate((Date)newValue);
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
			case RigelPackage.MILESTONE__OUTBOUND_TRANSITIONS:
				getOutboundTransitions().clear();
				return;
			case RigelPackage.MILESTONE__OUTPUTS:
				getOutputs().clear();
				return;
			case RigelPackage.MILESTONE__INBOUND_TRANSITIONS:
				getInboundTransitions().clear();
				return;
			case RigelPackage.MILESTONE__INPUTS:
				getInputs().clear();
				return;
			case RigelPackage.MILESTONE__TARGET_DATE:
				setTargetDate(TARGET_DATE_EDEFAULT);
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
			case RigelPackage.MILESTONE__OUTBOUND_TRANSITIONS:
				return !getOutboundTransitions().isEmpty();
			case RigelPackage.MILESTONE__OUTPUTS:
				return !getOutputs().isEmpty();
			case RigelPackage.MILESTONE__INBOUND_TRANSITIONS:
				return !getInboundTransitions().isEmpty();
			case RigelPackage.MILESTONE__INPUTS:
				return !getInputs().isEmpty();
			case RigelPackage.MILESTONE__SIZE:
				return getSize() != SIZE_EDEFAULT;
			case RigelPackage.MILESTONE__PROGRESS:
				return getProgress() != PROGRESS_EDEFAULT;
			case RigelPackage.MILESTONE__TARGET_DATE:
				return TARGET_DATE_EDEFAULT == null ? getTargetDate() != null : !TARGET_DATE_EDEFAULT.equals(getTargetDate());
			case RigelPackage.MILESTONE__MISSED:
				return isMissed() != MISSED_EDEFAULT;
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
				case RigelPackage.MILESTONE__OUTBOUND_TRANSITIONS: return RigelPackage.SOURCE__OUTBOUND_TRANSITIONS;
				case RigelPackage.MILESTONE__OUTPUTS: return RigelPackage.SOURCE__OUTPUTS;
				default: return -1;
			}
		}
		if (baseClass == Target.class) {
			switch (derivedFeatureID) {
				case RigelPackage.MILESTONE__INBOUND_TRANSITIONS: return RigelPackage.TARGET__INBOUND_TRANSITIONS;
				case RigelPackage.MILESTONE__INPUTS: return RigelPackage.TARGET__INPUTS;
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
				case RigelPackage.SOURCE__OUTBOUND_TRANSITIONS: return RigelPackage.MILESTONE__OUTBOUND_TRANSITIONS;
				case RigelPackage.SOURCE__OUTPUTS: return RigelPackage.MILESTONE__OUTPUTS;
				default: return -1;
			}
		}
		if (baseClass == Target.class) {
			switch (baseFeatureID) {
				case RigelPackage.TARGET__INBOUND_TRANSITIONS: return RigelPackage.MILESTONE__INBOUND_TRANSITIONS;
				case RigelPackage.TARGET__INPUTS: return RigelPackage.MILESTONE__INPUTS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //MilestoneImpl
