/**
 */
package org.nasdanika.flow.impl;

import java.util.Collection;
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
import org.nasdanika.flow.PackageElement;
import org.nasdanika.flow.Participant;
import org.nasdanika.flow.ParticipantResponsibility;
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
 *   <li>{@link org.nasdanika.flow.impl.ParticipantImpl#getParticipates <em>Participates</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ParticipantImpl#getResources <em>Resources</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ParticipantImpl#getArtifacts <em>Artifacts</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ParticipantImpl#getSpecializations <em>Specializations</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ParticipantImpl#getBaseKeys <em>Base Keys</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ParticipantImpl#getBases <em>Bases</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ParticipantImpl#getResponsible <em>Responsible</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ParticipantImpl#getAccountable <em>Accountable</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ParticipantImpl#getConsulted <em>Consulted</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ParticipantImpl#getInformed <em>Informed</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ParticipantImpl#getChildren <em>Children</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ParticipantImpl extends ServiceProviderImpl<Participant> implements Participant {
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
	 * @generated NOT
	 */
	@Override
	public EList<Participant> getSpecializations() {
		return getOppositeReferrers(FlowPackage.Literals.PARTICIPANT__SPECIALIZATIONS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<String> getBaseKeys() {
		return (EList<String>)eDynamicGet(FlowPackage.PARTICIPANT__BASE_KEYS, FlowPackage.Literals.PARTICIPANT__BASE_KEYS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Participant> getBases() {
		return resolveParticipants(getBaseKeys());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<ParticipantResponsibility<?>> getResponsible() {
		return getOppositeReferrers(FlowPackage.Literals.PARTICIPANT__RESPONSIBLE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<ParticipantResponsibility<?>> getAccountable() {
		return getOppositeReferrers(FlowPackage.Literals.PARTICIPANT__ACCOUNTABLE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<ParticipantResponsibility<?>> getConsulted() {
		return getOppositeReferrers(FlowPackage.Literals.PARTICIPANT__CONSULTED);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<ParticipantResponsibility<?>> getInformed() {
		return getOppositeReferrers(FlowPackage.Literals.PARTICIPANT__INFORMED);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, Participant> getChildren() {
		return (EMap<String, Participant>)eDynamicGet(FlowPackage.PARTICIPANT__CHILDREN, FlowPackage.Literals.PARTICIPANT__CHILDREN, true, true);
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
			case FlowPackage.PARTICIPANT__RESPONSIBLE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getResponsible()).basicAdd(otherEnd, msgs);
			case FlowPackage.PARTICIPANT__ACCOUNTABLE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAccountable()).basicAdd(otherEnd, msgs);
			case FlowPackage.PARTICIPANT__CONSULTED:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getConsulted()).basicAdd(otherEnd, msgs);
			case FlowPackage.PARTICIPANT__INFORMED:
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
			case FlowPackage.PARTICIPANT__PARTICIPATES:
				return ((InternalEList<?>)getParticipates()).basicRemove(otherEnd, msgs);
			case FlowPackage.PARTICIPANT__RESOURCES:
				return ((InternalEList<?>)getResources()).basicRemove(otherEnd, msgs);
			case FlowPackage.PARTICIPANT__ARTIFACTS:
				return ((InternalEList<?>)getArtifacts()).basicRemove(otherEnd, msgs);
			case FlowPackage.PARTICIPANT__RESPONSIBLE:
				return ((InternalEList<?>)getResponsible()).basicRemove(otherEnd, msgs);
			case FlowPackage.PARTICIPANT__ACCOUNTABLE:
				return ((InternalEList<?>)getAccountable()).basicRemove(otherEnd, msgs);
			case FlowPackage.PARTICIPANT__CONSULTED:
				return ((InternalEList<?>)getConsulted()).basicRemove(otherEnd, msgs);
			case FlowPackage.PARTICIPANT__INFORMED:
				return ((InternalEList<?>)getInformed()).basicRemove(otherEnd, msgs);
			case FlowPackage.PARTICIPANT__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
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
			case FlowPackage.PARTICIPANT__PARTICIPATES:
				return getParticipates();
			case FlowPackage.PARTICIPANT__RESOURCES:
				return getResources();
			case FlowPackage.PARTICIPANT__ARTIFACTS:
				return getArtifacts();
			case FlowPackage.PARTICIPANT__SPECIALIZATIONS:
				return getSpecializations();
			case FlowPackage.PARTICIPANT__BASE_KEYS:
				return getBaseKeys();
			case FlowPackage.PARTICIPANT__BASES:
				return getBases();
			case FlowPackage.PARTICIPANT__RESPONSIBLE:
				return getResponsible();
			case FlowPackage.PARTICIPANT__ACCOUNTABLE:
				return getAccountable();
			case FlowPackage.PARTICIPANT__CONSULTED:
				return getConsulted();
			case FlowPackage.PARTICIPANT__INFORMED:
				return getInformed();
			case FlowPackage.PARTICIPANT__CHILDREN:
				if (coreType) return getChildren();
				else return getChildren().map();
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
			case FlowPackage.PARTICIPANT__BASE_KEYS:
				getBaseKeys().clear();
				getBaseKeys().addAll((Collection<? extends String>)newValue);
				return;
			case FlowPackage.PARTICIPANT__CHILDREN:
				((EStructuralFeature.Setting)getChildren()).set(newValue);
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
			case FlowPackage.PARTICIPANT__BASE_KEYS:
				getBaseKeys().clear();
				return;
			case FlowPackage.PARTICIPANT__CHILDREN:
				getChildren().clear();
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
			case FlowPackage.PARTICIPANT__PARTICIPATES:
				return !getParticipates().isEmpty();
			case FlowPackage.PARTICIPANT__RESOURCES:
				return !getResources().isEmpty();
			case FlowPackage.PARTICIPANT__ARTIFACTS:
				return !getArtifacts().isEmpty();
			case FlowPackage.PARTICIPANT__SPECIALIZATIONS:
				return !getSpecializations().isEmpty();
			case FlowPackage.PARTICIPANT__BASE_KEYS:
				return !getBaseKeys().isEmpty();
			case FlowPackage.PARTICIPANT__BASES:
				return !getBases().isEmpty();
			case FlowPackage.PARTICIPANT__RESPONSIBLE:
				return !getResponsible().isEmpty();
			case FlowPackage.PARTICIPANT__ACCOUNTABLE:
				return !getAccountable().isEmpty();
			case FlowPackage.PARTICIPANT__CONSULTED:
				return !getConsulted().isEmpty();
			case FlowPackage.PARTICIPANT__INFORMED:
				return !getInformed().isEmpty();
			case FlowPackage.PARTICIPANT__CHILDREN:
				return !getChildren().isEmpty();
		}
		return super.eIsSet(featureID);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void apply(Participant instance) {
		super.apply(instance);
		
		// Children
		for (Entry<String, Participant> participantEntry: getChildren().entrySet()) {
			Participant participant = participantEntry.getValue();
			EMap<String, Participant> instanceParticipants = instance.getChildren();
			String participantKey = participantEntry.getKey();
			if (participant == null) {
				instanceParticipants.removeKey(participantKey);
			} else {
				Participant	instanceParicipant = participant.create();
				instanceParticipants.put(participantKey, instanceParicipant);
				participant.apply(instanceParicipant);
			}
		}		
		
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
			PackageElement<?> container = (PackageElement<?>) eContainer().eContainer();
			for (PackageElement<?> cExtends: container.getExtends()) { 
				if (cExtends instanceof Package) {
					Participant ext = ((Package) cExtends). getParticipants().get(key); 
					if (ext != null) { 
						ret.add(ext);
					}
				} else if (cExtends instanceof Participant) {
					Participant ext = ((Participant) cExtends).getChildren().get(key); 
					if (ext != null) { 
						ret.add(ext);
					}
				}
			}
		}
		
		return ret;
	}	

} //ParticipantImpl
