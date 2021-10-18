/**
 */
package org.nasdanika.flow.impl;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.flow.Activity;
import org.nasdanika.flow.Artifact;
import org.nasdanika.flow.Call;
import org.nasdanika.flow.FlowElement;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Package;
import org.nasdanika.flow.Participant;
import org.nasdanika.flow.Resource;
import org.nasdanika.flow.Transition;
import org.nasdanika.ncore.util.NamedElementComparator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Participant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.impl.ParticipantImpl#getServices <em>Services</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ParticipantImpl#getParticipates <em>Participates</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ParticipantImpl#getResources <em>Resources</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ParticipantImpl#getArtifacts <em>Artifacts</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ParticipantImpl extends PackageElementImpl<Participant> implements Participant {
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
	 * This is specialized for the more specific type known in this context.
	 * @generated
	 */
	@Override
	public void setPrototype(Participant newPrototype) {
		super.setPrototype(newPrototype);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, Activity<?>> getServices() {
		return (EMap<String, Activity<?>>)eDynamicGet(FlowPackage.PARTICIPANT__SERVICES, FlowPackage.Literals.PARTICIPANT__SERVICES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<FlowElement<?>> getParticipates() {
		return getOppositeReferrers(FlowPackage.Literals.PARTICIPANT__PARTICIPATES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Resource> getResources() {
		Set<Resource> collector = new HashSet<>();
		for (FlowElement<?> fe: getParticipates()) {
			collector.addAll(fe.getResources());
		}
		
		EList<Resource> ret = ECollections.newBasicEList(collector);
		ret.sort(NamedElementComparator.INSTANCE);
		return ret;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Artifact> getArtifacts() {
		Set<Artifact> collector = new HashSet<>();
		for (FlowElement<?> fe: getParticipates()) {
			collector.addAll(fe.getInputArtifacts());
			collector.addAll(fe.getOutputArtifacts());
			for (Transition input: fe.getInputs()) {
				collector.addAll(input.getPayload());
			}
			for (Transition output: fe.getOutputs().values()) {
				collector.addAll(output.getPayload());
			}
			for (Call invocation: fe.getInvocations()) {
				collector.addAll(invocation.getPayload());
				collector.addAll(invocation.getResponse());
			}
			for (Call call: fe.getCalls().values()) {
				collector.addAll(call.getPayload());
				collector.addAll(call.getResponse());
			}
		}
		
		EList<Artifact> ret = ECollections.newBasicEList(collector);
		ret.sort(NamedElementComparator.INSTANCE);
		return ret;
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
			case FlowPackage.PARTICIPANT__PARTICIPATES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getParticipates()).basicAdd(otherEnd, msgs);
			case FlowPackage.PARTICIPANT__RESOURCES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getResources()).basicAdd(otherEnd, msgs);
			case FlowPackage.PARTICIPANT__ARTIFACTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getArtifacts()).basicAdd(otherEnd, msgs);
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
			case FlowPackage.PARTICIPANT__SERVICES:
				return ((InternalEList<?>)getServices()).basicRemove(otherEnd, msgs);
			case FlowPackage.PARTICIPANT__PARTICIPATES:
				return ((InternalEList<?>)getParticipates()).basicRemove(otherEnd, msgs);
			case FlowPackage.PARTICIPANT__RESOURCES:
				return ((InternalEList<?>)getResources()).basicRemove(otherEnd, msgs);
			case FlowPackage.PARTICIPANT__ARTIFACTS:
				return ((InternalEList<?>)getArtifacts()).basicRemove(otherEnd, msgs);
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
			case FlowPackage.PARTICIPANT__SERVICES:
				if (coreType) return getServices();
				else return getServices().map();
			case FlowPackage.PARTICIPANT__PARTICIPATES:
				return getParticipates();
			case FlowPackage.PARTICIPANT__RESOURCES:
				return getResources();
			case FlowPackage.PARTICIPANT__ARTIFACTS:
				return getArtifacts();
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
			case FlowPackage.PARTICIPANT__SERVICES:
				return !getServices().isEmpty();
			case FlowPackage.PARTICIPANT__PARTICIPATES:
				return !getParticipates().isEmpty();
			case FlowPackage.PARTICIPANT__RESOURCES:
				return !getResources().isEmpty();
			case FlowPackage.PARTICIPANT__ARTIFACTS:
				return !getArtifacts().isEmpty();
		}
		return super.eIsSet(featureID);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void apply(Participant instance) {
		super.apply(instance);
		
		// Services
		for (Entry<String, Activity<?>> se: getServices().entrySet()) {
			Activity service = se.getValue();
			EMap<String, Activity<?>> instanceServices = instance.getServices();
			String serviceKey = se.getKey();
			if (service == null) {
				instanceServices.removeKey(serviceKey);
			} else {
				Activity instanceService = (Activity) service.create();
				instanceServices.put(serviceKey, instanceService);
				service.apply(instanceService);
			}
		}
	}
	
	@Override
	public void resolve(Participant instance) {
		// NOP
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public EList<Participant> getExtends() {
		EList<Participant> ret = ECollections.newBasicEList();
		if (eContainmentFeature() == FlowPackage.Literals.PARTICIPANT_ENTRY__VALUE) {
			String key = ((Map.Entry<String, ?>) eContainer()).getKey();
			Package container = (Package) eContainer().eContainer();
			for (Package cExtends: container.getExtends()) {
				Participant ext = cExtends.getParticipants().get(key);
				if (ext != null) {
					ret.add(ext);
				}
			}
		}
		
		return ret;
	}	

} //ParticipantImpl
