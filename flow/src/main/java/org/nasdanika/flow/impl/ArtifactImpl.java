/**
 */
package org.nasdanika.flow.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
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
 * An implementation of the model object '<em><b>Artifact</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.impl.ArtifactImpl#getRepositories <em>Repositories</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ArtifactImpl#getRepositoryKeys <em>Repository Keys</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ArtifactImpl#getInputFor <em>Input For</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ArtifactImpl#getOutputFor <em>Output For</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ArtifactImpl#getPayloadFor <em>Payload For</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ArtifactImpl#getResponseFor <em>Response For</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ArtifactImpl#getUsedBy <em>Used By</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ArtifactImpl extends PackageElementImpl<Artifact> implements Artifact {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArtifactImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FlowPackage.Literals.ARTIFACT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific type known in this context.
	 * @generated
	 */
	@Override
	public void setPrototype(Artifact newPrototype) {
		super.setPrototype(newPrototype);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Resource> getRepositories() {
		return (EList<Resource>) getCachedFeature(FlowPackage.Literals.ARTIFACT__REPOSITORIES);
	}
	
	@Override
	protected Object computeCachedFeature(EStructuralFeature feature) {
		if (feature == FlowPackage.Literals.ARTIFACT__REPOSITORIES) {
			return resolveResources(getRepositoryKeys());
		}
		return super.computeCachedFeature(feature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<String> getRepositoryKeys() {
		return (EList<String>)eDynamicGet(FlowPackage.ARTIFACT__REPOSITORY_KEYS, FlowPackage.Literals.ARTIFACT__REPOSITORY_KEYS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<FlowElement<?>> getInputFor() {
		return getOppositeReferrers(FlowPackage.Literals.ARTIFACT__INPUT_FOR);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<FlowElement<?>> getOutputFor() {
		return getOppositeReferrers(FlowPackage.Literals.ARTIFACT__OUTPUT_FOR);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Transition> getPayloadFor() {
		return getOppositeReferrers(FlowPackage.Literals.ARTIFACT__PAYLOAD_FOR);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Call> getResponseFor() {
		return getOppositeReferrers(FlowPackage.Literals.ARTIFACT__RESPONSE_FOR);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Participant> getUsedBy() {
		Set<Participant> collector = new HashSet<>();		
		for (FlowElement<?> fe: getInputFor()) {
			collector.addAll(fe.getParticipants());
		}
		for (FlowElement<?> fe: getOutputFor()) {
			collector.addAll(fe.getParticipants());
		}
		for (Transition transition: getPayloadFor()) {
			collector.addAll(transition.getTarget().getParticipants());
			collector.addAll(((FlowElement<?>) transition.eContainer().eContainer()).getParticipants());
		}
		for (Call call: getResponseFor()) {
			collector.addAll(call.getTarget().getParticipants());
			collector.addAll(((FlowElement<?>) call.eContainer().eContainer()).getParticipants());
		}
		
		EList<Participant> ret = ECollections.newBasicEList(collector);
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
			case FlowPackage.ARTIFACT__REPOSITORIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRepositories()).basicAdd(otherEnd, msgs);
			case FlowPackage.ARTIFACT__INPUT_FOR:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInputFor()).basicAdd(otherEnd, msgs);
			case FlowPackage.ARTIFACT__OUTPUT_FOR:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutputFor()).basicAdd(otherEnd, msgs);
			case FlowPackage.ARTIFACT__PAYLOAD_FOR:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPayloadFor()).basicAdd(otherEnd, msgs);
			case FlowPackage.ARTIFACT__RESPONSE_FOR:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getResponseFor()).basicAdd(otherEnd, msgs);
			case FlowPackage.ARTIFACT__USED_BY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getUsedBy()).basicAdd(otherEnd, msgs);
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
			case FlowPackage.ARTIFACT__REPOSITORIES:
				return ((InternalEList<?>)getRepositories()).basicRemove(otherEnd, msgs);
			case FlowPackage.ARTIFACT__INPUT_FOR:
				return ((InternalEList<?>)getInputFor()).basicRemove(otherEnd, msgs);
			case FlowPackage.ARTIFACT__OUTPUT_FOR:
				return ((InternalEList<?>)getOutputFor()).basicRemove(otherEnd, msgs);
			case FlowPackage.ARTIFACT__PAYLOAD_FOR:
				return ((InternalEList<?>)getPayloadFor()).basicRemove(otherEnd, msgs);
			case FlowPackage.ARTIFACT__RESPONSE_FOR:
				return ((InternalEList<?>)getResponseFor()).basicRemove(otherEnd, msgs);
			case FlowPackage.ARTIFACT__USED_BY:
				return ((InternalEList<?>)getUsedBy()).basicRemove(otherEnd, msgs);
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
			case FlowPackage.ARTIFACT__REPOSITORIES:
				return getRepositories();
			case FlowPackage.ARTIFACT__REPOSITORY_KEYS:
				return getRepositoryKeys();
			case FlowPackage.ARTIFACT__INPUT_FOR:
				return getInputFor();
			case FlowPackage.ARTIFACT__OUTPUT_FOR:
				return getOutputFor();
			case FlowPackage.ARTIFACT__PAYLOAD_FOR:
				return getPayloadFor();
			case FlowPackage.ARTIFACT__RESPONSE_FOR:
				return getResponseFor();
			case FlowPackage.ARTIFACT__USED_BY:
				return getUsedBy();
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
			case FlowPackage.ARTIFACT__REPOSITORY_KEYS:
				getRepositoryKeys().clear();
				getRepositoryKeys().addAll((Collection<? extends String>)newValue);
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
			case FlowPackage.ARTIFACT__REPOSITORY_KEYS:
				getRepositoryKeys().clear();
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
			case FlowPackage.ARTIFACT__REPOSITORIES:
				return !getRepositories().isEmpty();
			case FlowPackage.ARTIFACT__REPOSITORY_KEYS:
				return !getRepositoryKeys().isEmpty();
			case FlowPackage.ARTIFACT__INPUT_FOR:
				return !getInputFor().isEmpty();
			case FlowPackage.ARTIFACT__OUTPUT_FOR:
				return !getOutputFor().isEmpty();
			case FlowPackage.ARTIFACT__PAYLOAD_FOR:
				return !getPayloadFor().isEmpty();
			case FlowPackage.ARTIFACT__RESPONSE_FOR:
				return !getResponseFor().isEmpty();
			case FlowPackage.ARTIFACT__USED_BY:
				return !getUsedBy().isEmpty();
		}
		return super.eIsSet(featureID);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public EList<Artifact> getExtends() {
		EList<Artifact> ret = ECollections.newBasicEList();
		if (eContainmentFeature() == FlowPackage.Literals.ARTIFACT_ENTRY__VALUE) {
			String key = ((Map.Entry<String, ?>) eContainer()).getKey();
			Package container = (Package) eContainer().eContainer();
			for (Package cExtends: container.getExtends()) {
				Artifact ext = cExtends.getArtifacts().get(key);
				if (ext != null) {
					ret.add(ext);
				}
			}
		}
		
		return ret;
	}	

} //ArtifactImpl
