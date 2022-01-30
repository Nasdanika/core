/**
 */
package org.nasdanika.flow.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.flow.Artifact;
import org.nasdanika.flow.ArtifactParticipantResponsibility;
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
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getCalls <em>Calls</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getInvocations <em>Invocations</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getInputArtifacts <em>Input Artifacts</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getInputArtifactKeys <em>Input Artifact Keys</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getOutputArtifacts <em>Output Artifacts</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getOutputArtifactKeys <em>Output Artifact Keys</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getParticipants <em>Participants</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getParticipantKeys <em>Participant Keys</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getResources <em>Resources</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getResourceKeys <em>Resource Keys</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getArtifactResponsibilities <em>Artifact Responsibilities</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getSortGroup <em>Sort Group</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FlowElementImpl<T extends FlowElement<T>> extends ParticipantResponsibilityImpl<T> implements FlowElement<T> {
	/**
	 * The default value of the '{@link #getSortGroup() <em>Sort Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSortGroup()
	 * @generated
	 * @ordered
	 */
	protected static final String SORT_GROUP_EDEFAULT = null;

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
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, Transition> getOutputs() {
		return (EMap<String, Transition>)eDynamicGet(FlowPackage.FLOW_ELEMENT__OUTPUTS, FlowPackage.Literals.FLOW_ELEMENT__OUTPUTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Transition> getInputs() {
		List<Transition> inputs = getReferrers(FlowPackage.Literals.TRANSITION__TARGET);
		return ECollections.newBasicEList(inputs.stream().filter(e -> e.eContainmentFeature() == FlowPackage.Literals.TRANSITION_ENTRY__VALUE).collect(Collectors.toList()));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, Call> getCalls() {
		return (EMap<String, Call>)eDynamicGet(FlowPackage.FLOW_ELEMENT__CALLS, FlowPackage.Literals.FLOW_ELEMENT__CALLS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Call> getInvocations() {
		List<Call> invocations = getReferrers(FlowPackage.Literals.TRANSITION__TARGET).stream().filter(e -> e instanceof Call).map(e -> (Call) e).collect(Collectors.toList());
		return ECollections.newBasicEList(invocations.stream().filter(e -> e.eContainmentFeature() == FlowPackage.Literals.CALL_ENTRY__VALUE).collect(Collectors.toList()));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<String> getInputArtifactKeys() {
		return (EList<String>)eDynamicGet(FlowPackage.FLOW_ELEMENT__INPUT_ARTIFACT_KEYS, FlowPackage.Literals.FLOW_ELEMENT__INPUT_ARTIFACT_KEYS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Artifact> getInputArtifacts() {
		return (EList<Artifact>) getCachedFeature(FlowPackage.Literals.FLOW_ELEMENT__INPUT_ARTIFACTS);
	}
	
	@Override
	protected Object computeCachedFeature(EStructuralFeature feature) {
		if (feature == FlowPackage.Literals.FLOW_ELEMENT__INPUT_ARTIFACTS) {
			return resolveArtifacts(getInputArtifactKeys());
		}
		if (feature == FlowPackage.Literals.FLOW_ELEMENT__OUTPUT_ARTIFACTS) {
			return resolveArtifacts(getOutputArtifactKeys());
		}
		if (feature == FlowPackage.Literals.FLOW_ELEMENT__PARTICIPANTS) {
			return resolveParticipants(getParticipantKeys());
		}
		if (feature == FlowPackage.Literals.FLOW_ELEMENT__RESOURCES) {
			return resolveResources(getResourceKeys());
		}
		return super.computeCachedFeature(feature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Artifact> getOutputArtifacts() {
		return (EList<Artifact>) getCachedFeature(FlowPackage.Literals.FLOW_ELEMENT__OUTPUT_ARTIFACTS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<String> getOutputArtifactKeys() {
		return (EList<String>)eDynamicGet(FlowPackage.FLOW_ELEMENT__OUTPUT_ARTIFACT_KEYS, FlowPackage.Literals.FLOW_ELEMENT__OUTPUT_ARTIFACT_KEYS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Participant> getParticipants() {
		return (EList<Participant>) getCachedFeature(FlowPackage.Literals.FLOW_ELEMENT__PARTICIPANTS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<String> getParticipantKeys() {
		return (EList<String>)eDynamicGet(FlowPackage.FLOW_ELEMENT__PARTICIPANT_KEYS, FlowPackage.Literals.FLOW_ELEMENT__PARTICIPANT_KEYS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Resource> getResources() {
		return (EList<Resource>) getCachedFeature(FlowPackage.Literals.FLOW_ELEMENT__RESOURCES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<String> getResourceKeys() {
		return (EList<String>)eDynamicGet(FlowPackage.FLOW_ELEMENT__RESOURCE_KEYS, FlowPackage.Literals.FLOW_ELEMENT__RESOURCE_KEYS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<ArtifactParticipantResponsibility> getArtifactResponsibilities() {
		return (EList<ArtifactParticipantResponsibility>)eDynamicGet(FlowPackage.FLOW_ELEMENT__ARTIFACT_RESPONSIBILITIES, FlowPackage.Literals.FLOW_ELEMENT__ARTIFACT_RESPONSIBILITIES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSortGroup() {
		return (String)eDynamicGet(FlowPackage.FLOW_ELEMENT__SORT_GROUP, FlowPackage.Literals.FLOW_ELEMENT__SORT_GROUP, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSortGroup(String newSortGroup) {
		eDynamicSet(FlowPackage.FLOW_ELEMENT__SORT_GROUP, FlowPackage.Literals.FLOW_ELEMENT__SORT_GROUP, newSortGroup);
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
			case FlowPackage.FLOW_ELEMENT__INPUT_ARTIFACTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInputArtifacts()).basicAdd(otherEnd, msgs);
			case FlowPackage.FLOW_ELEMENT__OUTPUT_ARTIFACTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutputArtifacts()).basicAdd(otherEnd, msgs);
			case FlowPackage.FLOW_ELEMENT__PARTICIPANTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getParticipants()).basicAdd(otherEnd, msgs);
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
			case FlowPackage.FLOW_ELEMENT__INPUT_ARTIFACTS:
				return ((InternalEList<?>)getInputArtifacts()).basicRemove(otherEnd, msgs);
			case FlowPackage.FLOW_ELEMENT__OUTPUT_ARTIFACTS:
				return ((InternalEList<?>)getOutputArtifacts()).basicRemove(otherEnd, msgs);
			case FlowPackage.FLOW_ELEMENT__PARTICIPANTS:
				return ((InternalEList<?>)getParticipants()).basicRemove(otherEnd, msgs);
			case FlowPackage.FLOW_ELEMENT__ARTIFACT_RESPONSIBILITIES:
				return ((InternalEList<?>)getArtifactResponsibilities()).basicRemove(otherEnd, msgs);
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
				if (coreType) return getOutputs();
				else return getOutputs().map();
			case FlowPackage.FLOW_ELEMENT__INPUTS:
				return getInputs();
			case FlowPackage.FLOW_ELEMENT__CALLS:
				if (coreType) return getCalls();
				else return getCalls().map();
			case FlowPackage.FLOW_ELEMENT__INVOCATIONS:
				return getInvocations();
			case FlowPackage.FLOW_ELEMENT__INPUT_ARTIFACTS:
				return getInputArtifacts();
			case FlowPackage.FLOW_ELEMENT__INPUT_ARTIFACT_KEYS:
				return getInputArtifactKeys();
			case FlowPackage.FLOW_ELEMENT__OUTPUT_ARTIFACTS:
				return getOutputArtifacts();
			case FlowPackage.FLOW_ELEMENT__OUTPUT_ARTIFACT_KEYS:
				return getOutputArtifactKeys();
			case FlowPackage.FLOW_ELEMENT__PARTICIPANTS:
				return getParticipants();
			case FlowPackage.FLOW_ELEMENT__PARTICIPANT_KEYS:
				return getParticipantKeys();
			case FlowPackage.FLOW_ELEMENT__RESOURCES:
				return getResources();
			case FlowPackage.FLOW_ELEMENT__RESOURCE_KEYS:
				return getResourceKeys();
			case FlowPackage.FLOW_ELEMENT__ARTIFACT_RESPONSIBILITIES:
				return getArtifactResponsibilities();
			case FlowPackage.FLOW_ELEMENT__SORT_GROUP:
				return getSortGroup();
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
				((EStructuralFeature.Setting)getOutputs()).set(newValue);
				return;
			case FlowPackage.FLOW_ELEMENT__CALLS:
				((EStructuralFeature.Setting)getCalls()).set(newValue);
				return;
			case FlowPackage.FLOW_ELEMENT__INPUT_ARTIFACT_KEYS:
				getInputArtifactKeys().clear();
				getInputArtifactKeys().addAll((Collection<? extends String>)newValue);
				return;
			case FlowPackage.FLOW_ELEMENT__OUTPUT_ARTIFACT_KEYS:
				getOutputArtifactKeys().clear();
				getOutputArtifactKeys().addAll((Collection<? extends String>)newValue);
				return;
			case FlowPackage.FLOW_ELEMENT__PARTICIPANT_KEYS:
				getParticipantKeys().clear();
				getParticipantKeys().addAll((Collection<? extends String>)newValue);
				return;
			case FlowPackage.FLOW_ELEMENT__RESOURCE_KEYS:
				getResourceKeys().clear();
				getResourceKeys().addAll((Collection<? extends String>)newValue);
				return;
			case FlowPackage.FLOW_ELEMENT__ARTIFACT_RESPONSIBILITIES:
				getArtifactResponsibilities().clear();
				getArtifactResponsibilities().addAll((Collection<? extends ArtifactParticipantResponsibility>)newValue);
				return;
			case FlowPackage.FLOW_ELEMENT__SORT_GROUP:
				setSortGroup((String)newValue);
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
			case FlowPackage.FLOW_ELEMENT__INPUT_ARTIFACT_KEYS:
				getInputArtifactKeys().clear();
				return;
			case FlowPackage.FLOW_ELEMENT__OUTPUT_ARTIFACT_KEYS:
				getOutputArtifactKeys().clear();
				return;
			case FlowPackage.FLOW_ELEMENT__PARTICIPANT_KEYS:
				getParticipantKeys().clear();
				return;
			case FlowPackage.FLOW_ELEMENT__RESOURCE_KEYS:
				getResourceKeys().clear();
				return;
			case FlowPackage.FLOW_ELEMENT__ARTIFACT_RESPONSIBILITIES:
				getArtifactResponsibilities().clear();
				return;
			case FlowPackage.FLOW_ELEMENT__SORT_GROUP:
				setSortGroup(SORT_GROUP_EDEFAULT);
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
			case FlowPackage.FLOW_ELEMENT__INPUTS:
				return !getInputs().isEmpty();
			case FlowPackage.FLOW_ELEMENT__CALLS:
				return !getCalls().isEmpty();
			case FlowPackage.FLOW_ELEMENT__INVOCATIONS:
				return !getInvocations().isEmpty();
			case FlowPackage.FLOW_ELEMENT__INPUT_ARTIFACTS:
				return !getInputArtifacts().isEmpty();
			case FlowPackage.FLOW_ELEMENT__INPUT_ARTIFACT_KEYS:
				return !getInputArtifactKeys().isEmpty();
			case FlowPackage.FLOW_ELEMENT__OUTPUT_ARTIFACTS:
				return !getOutputArtifacts().isEmpty();
			case FlowPackage.FLOW_ELEMENT__OUTPUT_ARTIFACT_KEYS:
				return !getOutputArtifactKeys().isEmpty();
			case FlowPackage.FLOW_ELEMENT__PARTICIPANTS:
				return !getParticipants().isEmpty();
			case FlowPackage.FLOW_ELEMENT__PARTICIPANT_KEYS:
				return !getParticipantKeys().isEmpty();
			case FlowPackage.FLOW_ELEMENT__RESOURCES:
				return !getResources().isEmpty();
			case FlowPackage.FLOW_ELEMENT__RESOURCE_KEYS:
				return !getResourceKeys().isEmpty();
			case FlowPackage.FLOW_ELEMENT__ARTIFACT_RESPONSIBILITIES:
				return !getArtifactResponsibilities().isEmpty();
			case FlowPackage.FLOW_ELEMENT__SORT_GROUP:
				return SORT_GROUP_EDEFAULT == null ? getSortGroup() != null : !SORT_GROUP_EDEFAULT.equals(getSortGroup());
		}
		return super.eIsSet(featureID);
	}
	
	@Override
	public void apply(T instance) {
		super.apply(instance);
		
		// Outputs
		for (Entry<String, Transition> outputEntry: getOutputs().entrySet()) {
			Transition output = outputEntry.getValue();
			EMap<String, Transition> instanceOutputs = instance.getOutputs();
			String outputKey = outputEntry.getKey();
			if (output == null) {
				instanceOutputs.removeKey(outputKey);
			} else {
				Transition instanceOutput = output.create();
				instanceOutputs.put(outputKey, instanceOutput);
				output.apply(instanceOutput);
			}
		}
		
		// Calls
		for (Entry<String, Call> callEntry: getCalls().entrySet()) {
			Call call = callEntry.getValue();
			EMap<String, Call> instanceCalls = instance.getCalls();
			String callKey = callEntry.getKey();
			if (call == null) {
				instanceCalls.removeKey(callKey);
			} else {
				Call instanceCall = (Call) call.create();
				instanceCalls.put(callKey, instanceCall);
				call.apply(instanceCall);
			}
		}
		
		// Artifact participant responsibilities
		for (ArtifactParticipantResponsibility apr: getArtifactResponsibilities()) {
			EList<ArtifactParticipantResponsibility> instanceArtifactResponsibilities = instance.getArtifactResponsibilities();
			instanceArtifactResponsibilities.removeIf(e -> apr.getArtifactKey().equals(e.getArtifactKey()));
			if (!apr.isSuppress()) {
				ArtifactParticipantResponsibility instanceApr = apr.create();
				instanceArtifactResponsibilities.add(instanceApr);
				apr.apply(instanceApr);
			}
		}		
	}
	
	/**
	 * Flow containment
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<T> getExtends() {
		EList<T> ret = ECollections.newBasicEList();
		if (eContainmentFeature() == FlowPackage.Literals.FLOW_ELEMENT_ENTRY__VALUE) {
			String key = ((Map.Entry<String, ?>) eContainer()).getKey();
			Flow container = (Flow) eContainer().eContainer();
			for (Flow cExtends: container.getExtends()) {
				FlowElement<?> ext = cExtends.getElements().get(key);
				if (ext != null) {
					ret.add((T) ext);
				}
			}
		}
		
		return ret;
	}	
	

} //FlowElementImpl
