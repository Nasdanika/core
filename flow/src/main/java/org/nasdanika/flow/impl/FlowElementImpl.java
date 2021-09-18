/**
 */
package org.nasdanika.flow.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.flow.Artifact;
import org.nasdanika.flow.Call;
import org.nasdanika.flow.Flow;
import org.nasdanika.flow.FlowElement;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Participant;
import org.nasdanika.flow.Resource;
import org.nasdanika.flow.Transition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getCalls <em>Calls</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getInputArtifacts <em>Input Artifacts</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getDeliverables <em>Deliverables</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getParticipants <em>Participants</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getOverrides <em>Overrides</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getOverridenBy <em>Overriden By</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getModifiers <em>Modifiers</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getResources <em>Resources</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FlowElementImpl extends MinimalEObjectImpl.Container implements FlowElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FlowElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FlowPackage.Literals.FLOW_ELEMENT;
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
	@SuppressWarnings("unchecked")
	@Override
	public EList<Transition> getOutputs() {
		return (EList<Transition>)eDynamicGet(FlowPackage.FLOW_ELEMENT__OUTPUTS, FlowPackage.Literals.FLOW_ELEMENT__OUTPUTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Call> getCalls() {
		return (EList<Call>)eDynamicGet(FlowPackage.FLOW_ELEMENT__CALLS, FlowPackage.Literals.FLOW_ELEMENT__CALLS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Artifact> getInputArtifacts() {
		return (EList<Artifact>)eDynamicGet(FlowPackage.FLOW_ELEMENT__INPUT_ARTIFACTS, FlowPackage.Literals.FLOW_ELEMENT__INPUT_ARTIFACTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Artifact> getDeliverables() {
		return (EList<Artifact>)eDynamicGet(FlowPackage.FLOW_ELEMENT__DELIVERABLES, FlowPackage.Literals.FLOW_ELEMENT__DELIVERABLES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Participant> getParticipants() {
		return (EList<Participant>)eDynamicGet(FlowPackage.FLOW_ELEMENT__PARTICIPANTS, FlowPackage.Literals.FLOW_ELEMENT__PARTICIPANTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FlowElement getOverrides() {
		return (FlowElement)eDynamicGet(FlowPackage.FLOW_ELEMENT__OVERRIDES, FlowPackage.Literals.FLOW_ELEMENT__OVERRIDES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlowElement basicGetOverrides() {
		return (FlowElement)eDynamicGet(FlowPackage.FLOW_ELEMENT__OVERRIDES, FlowPackage.Literals.FLOW_ELEMENT__OVERRIDES, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOverrides(FlowElement newOverrides, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newOverrides, FlowPackage.FLOW_ELEMENT__OVERRIDES, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<FlowElement> getOverridenBy() {
		return (EList<FlowElement>)eDynamicGet(FlowPackage.FLOW_ELEMENT__OVERRIDEN_BY, FlowPackage.Literals.FLOW_ELEMENT__OVERRIDEN_BY, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<String> getModifiers() {
		return (EList<String>)eDynamicGet(FlowPackage.FLOW_ELEMENT__MODIFIERS, FlowPackage.Literals.FLOW_ELEMENT__MODIFIERS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Resource> getResources() {
		return (EList<Resource>)eDynamicGet(FlowPackage.FLOW_ELEMENT__RESOURCES, FlowPackage.Literals.FLOW_ELEMENT__RESOURCES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Transition> getInputs(EList<Flow> journeyPath) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Call> getInvocations(EList<Flow> journeyPath) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Transition> getAllInputs(EList<Flow> journeyPath) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Call> getAllInvocations(EList<Flow> journeyPath) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Transition> getAllOutputs(EList<Flow> journeyPath) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Call> getAllCalls(EList<Flow> journeyPath) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean overrides(FlowElement journeyElement) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
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
			case FlowPackage.FLOW_ELEMENT__OVERRIDES:
				FlowElement overrides = basicGetOverrides();
				if (overrides != null)
					msgs = ((InternalEObject)overrides).eInverseRemove(this, FlowPackage.FLOW_ELEMENT__OVERRIDEN_BY, FlowElement.class, msgs);
				return basicSetOverrides((FlowElement)otherEnd, msgs);
			case FlowPackage.FLOW_ELEMENT__OVERRIDEN_BY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOverridenBy()).basicAdd(otherEnd, msgs);
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
			case FlowPackage.FLOW_ELEMENT__OUTPUTS:
				return ((InternalEList<?>)getOutputs()).basicRemove(otherEnd, msgs);
			case FlowPackage.FLOW_ELEMENT__CALLS:
				return ((InternalEList<?>)getCalls()).basicRemove(otherEnd, msgs);
			case FlowPackage.FLOW_ELEMENT__OVERRIDES:
				return basicSetOverrides(null, msgs);
			case FlowPackage.FLOW_ELEMENT__OVERRIDEN_BY:
				return ((InternalEList<?>)getOverridenBy()).basicRemove(otherEnd, msgs);
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
			case FlowPackage.FLOW_ELEMENT__OUTPUTS:
				return getOutputs();
			case FlowPackage.FLOW_ELEMENT__CALLS:
				return getCalls();
			case FlowPackage.FLOW_ELEMENT__INPUT_ARTIFACTS:
				return getInputArtifacts();
			case FlowPackage.FLOW_ELEMENT__DELIVERABLES:
				return getDeliverables();
			case FlowPackage.FLOW_ELEMENT__PARTICIPANTS:
				return getParticipants();
			case FlowPackage.FLOW_ELEMENT__OVERRIDES:
				if (resolve) return getOverrides();
				return basicGetOverrides();
			case FlowPackage.FLOW_ELEMENT__OVERRIDEN_BY:
				return getOverridenBy();
			case FlowPackage.FLOW_ELEMENT__MODIFIERS:
				return getModifiers();
			case FlowPackage.FLOW_ELEMENT__RESOURCES:
				return getResources();
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
			case FlowPackage.FLOW_ELEMENT__OUTPUTS:
				getOutputs().clear();
				getOutputs().addAll((Collection<? extends Transition>)newValue);
				return;
			case FlowPackage.FLOW_ELEMENT__CALLS:
				getCalls().clear();
				getCalls().addAll((Collection<? extends Call>)newValue);
				return;
			case FlowPackage.FLOW_ELEMENT__INPUT_ARTIFACTS:
				getInputArtifacts().clear();
				getInputArtifacts().addAll((Collection<? extends Artifact>)newValue);
				return;
			case FlowPackage.FLOW_ELEMENT__DELIVERABLES:
				getDeliverables().clear();
				getDeliverables().addAll((Collection<? extends Artifact>)newValue);
				return;
			case FlowPackage.FLOW_ELEMENT__PARTICIPANTS:
				getParticipants().clear();
				getParticipants().addAll((Collection<? extends Participant>)newValue);
				return;
			case FlowPackage.FLOW_ELEMENT__MODIFIERS:
				getModifiers().clear();
				getModifiers().addAll((Collection<? extends String>)newValue);
				return;
			case FlowPackage.FLOW_ELEMENT__RESOURCES:
				getResources().clear();
				getResources().addAll((Collection<? extends Resource>)newValue);
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
			case FlowPackage.FLOW_ELEMENT__OUTPUTS:
				getOutputs().clear();
				return;
			case FlowPackage.FLOW_ELEMENT__CALLS:
				getCalls().clear();
				return;
			case FlowPackage.FLOW_ELEMENT__INPUT_ARTIFACTS:
				getInputArtifacts().clear();
				return;
			case FlowPackage.FLOW_ELEMENT__DELIVERABLES:
				getDeliverables().clear();
				return;
			case FlowPackage.FLOW_ELEMENT__PARTICIPANTS:
				getParticipants().clear();
				return;
			case FlowPackage.FLOW_ELEMENT__MODIFIERS:
				getModifiers().clear();
				return;
			case FlowPackage.FLOW_ELEMENT__RESOURCES:
				getResources().clear();
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
			case FlowPackage.FLOW_ELEMENT__OUTPUTS:
				return !getOutputs().isEmpty();
			case FlowPackage.FLOW_ELEMENT__CALLS:
				return !getCalls().isEmpty();
			case FlowPackage.FLOW_ELEMENT__INPUT_ARTIFACTS:
				return !getInputArtifacts().isEmpty();
			case FlowPackage.FLOW_ELEMENT__DELIVERABLES:
				return !getDeliverables().isEmpty();
			case FlowPackage.FLOW_ELEMENT__PARTICIPANTS:
				return !getParticipants().isEmpty();
			case FlowPackage.FLOW_ELEMENT__OVERRIDES:
				return basicGetOverrides() != null;
			case FlowPackage.FLOW_ELEMENT__OVERRIDEN_BY:
				return !getOverridenBy().isEmpty();
			case FlowPackage.FLOW_ELEMENT__MODIFIERS:
				return !getModifiers().isEmpty();
			case FlowPackage.FLOW_ELEMENT__RESOURCES:
				return !getResources().isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case FlowPackage.FLOW_ELEMENT___GET_INPUTS__ELIST:
				return getInputs((EList<Flow>)arguments.get(0));
			case FlowPackage.FLOW_ELEMENT___GET_INVOCATIONS__ELIST:
				return getInvocations((EList<Flow>)arguments.get(0));
			case FlowPackage.FLOW_ELEMENT___GET_ALL_INPUTS__ELIST:
				return getAllInputs((EList<Flow>)arguments.get(0));
			case FlowPackage.FLOW_ELEMENT___GET_ALL_INVOCATIONS__ELIST:
				return getAllInvocations((EList<Flow>)arguments.get(0));
			case FlowPackage.FLOW_ELEMENT___GET_ALL_OUTPUTS__ELIST:
				return getAllOutputs((EList<Flow>)arguments.get(0));
			case FlowPackage.FLOW_ELEMENT___GET_ALL_CALLS__ELIST:
				return getAllCalls((EList<Flow>)arguments.get(0));
			case FlowPackage.FLOW_ELEMENT___OVERRIDES__FLOWELEMENT:
				return overrides((FlowElement)arguments.get(0));
		}
		return super.eInvoke(operationID, arguments);
	}

} //FlowElementImpl
