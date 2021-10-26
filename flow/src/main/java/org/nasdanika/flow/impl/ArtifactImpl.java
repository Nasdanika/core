/**
 */
package org.nasdanika.flow.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.diagram.DiagramElement;
import org.nasdanika.flow.Activity;
import org.nasdanika.flow.Artifact;
import org.nasdanika.flow.ArtifactParticipantResponsibility;
import org.nasdanika.flow.Call;
import org.nasdanika.flow.FlowElement;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Package;
import org.nasdanika.flow.PackageElement;
import org.nasdanika.flow.Participant;
import org.nasdanika.flow.Relationship;
import org.nasdanika.flow.Resource;
import org.nasdanika.flow.ServiceProvider;
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
 *   <li>{@link org.nasdanika.flow.impl.ArtifactImpl#getServices <em>Services</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ArtifactImpl#getRepositories <em>Repositories</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ArtifactImpl#getRepositoryKeys <em>Repository Keys</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ArtifactImpl#getInputFor <em>Input For</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ArtifactImpl#getOutputFor <em>Output For</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ArtifactImpl#getPayloadFor <em>Payload For</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ArtifactImpl#getResponseFor <em>Response For</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ArtifactImpl#getUsedBy <em>Used By</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ArtifactImpl#getResponsibilities <em>Responsibilities</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ArtifactImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ArtifactImpl#getOutboundRelationships <em>Outbound Relationships</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ArtifactImpl#getInboundRelationships <em>Inbound Relationships</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ArtifactImpl#isPartition <em>Partition</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ArtifactImpl#getStyle <em>Style</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ArtifactImpl extends ParticipantResponsibilityImpl<Artifact> implements Artifact {
	/**
	 * The default value of the '{@link #isPartition() <em>Partition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPartition()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PARTITION_EDEFAULT = false;

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
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, Activity<?>> getServices() {
		return (EMap<String, Activity<?>>)eDynamicGet(FlowPackage.ARTIFACT__SERVICES, FlowPackage.Literals.SERVICE_PROVIDER__SERVICES, true, true);
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
	 * @generated NOT
	 */
	@Override
	public EList<ArtifactParticipantResponsibility> getResponsibilities() {
		return getOppositeReferrers(FlowPackage.Literals.ARTIFACT__RESPONSIBILITIES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, Artifact> getChildren() {
		return (EMap<String, Artifact>)eDynamicGet(FlowPackage.ARTIFACT__CHILDREN, FlowPackage.Literals.ARTIFACT__CHILDREN, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, Relationship> getOutboundRelationships() {
		return (EMap<String, Relationship>)eDynamicGet(FlowPackage.ARTIFACT__OUTBOUND_RELATIONSHIPS, FlowPackage.Literals.ARTIFACT__OUTBOUND_RELATIONSHIPS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Relationship> getInboundRelationships() {
		return getOppositeReferrers(FlowPackage.Literals.ARTIFACT__INBOUND_RELATIONSHIPS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isPartition() {
		return (Boolean)eDynamicGet(FlowPackage.ARTIFACT__PARTITION, FlowPackage.Literals.ARTIFACT__PARTITION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPartition(boolean newPartition) {
		eDynamicSet(FlowPackage.ARTIFACT__PARTITION, FlowPackage.Literals.ARTIFACT__PARTITION, newPartition);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DiagramElement getStyle() {
		return (DiagramElement)eDynamicGet(FlowPackage.ARTIFACT__STYLE, FlowPackage.Literals.ARTIFACT__STYLE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStyle(DiagramElement newStyle, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newStyle, FlowPackage.ARTIFACT__STYLE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStyle(DiagramElement newStyle) {
		eDynamicSet(FlowPackage.ARTIFACT__STYLE, FlowPackage.Literals.ARTIFACT__STYLE, newStyle);
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
			case FlowPackage.ARTIFACT__RESPONSIBILITIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getResponsibilities()).basicAdd(otherEnd, msgs);
			case FlowPackage.ARTIFACT__INBOUND_RELATIONSHIPS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInboundRelationships()).basicAdd(otherEnd, msgs);
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
			case FlowPackage.ARTIFACT__SERVICES:
				return ((InternalEList<?>)getServices()).basicRemove(otherEnd, msgs);
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
			case FlowPackage.ARTIFACT__RESPONSIBILITIES:
				return ((InternalEList<?>)getResponsibilities()).basicRemove(otherEnd, msgs);
			case FlowPackage.ARTIFACT__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case FlowPackage.ARTIFACT__OUTBOUND_RELATIONSHIPS:
				return ((InternalEList<?>)getOutboundRelationships()).basicRemove(otherEnd, msgs);
			case FlowPackage.ARTIFACT__INBOUND_RELATIONSHIPS:
				return ((InternalEList<?>)getInboundRelationships()).basicRemove(otherEnd, msgs);
			case FlowPackage.ARTIFACT__STYLE:
				return basicSetStyle(null, msgs);
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
			case FlowPackage.ARTIFACT__SERVICES:
				if (coreType) return getServices();
				else return getServices().map();
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
			case FlowPackage.ARTIFACT__RESPONSIBILITIES:
				return getResponsibilities();
			case FlowPackage.ARTIFACT__CHILDREN:
				if (coreType) return getChildren();
				else return getChildren().map();
			case FlowPackage.ARTIFACT__OUTBOUND_RELATIONSHIPS:
				if (coreType) return getOutboundRelationships();
				else return getOutboundRelationships().map();
			case FlowPackage.ARTIFACT__INBOUND_RELATIONSHIPS:
				return getInboundRelationships();
			case FlowPackage.ARTIFACT__PARTITION:
				return isPartition();
			case FlowPackage.ARTIFACT__STYLE:
				return getStyle();
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
			case FlowPackage.ARTIFACT__SERVICES:
				((EStructuralFeature.Setting)getServices()).set(newValue);
				return;
			case FlowPackage.ARTIFACT__REPOSITORY_KEYS:
				getRepositoryKeys().clear();
				getRepositoryKeys().addAll((Collection<? extends String>)newValue);
				return;
			case FlowPackage.ARTIFACT__CHILDREN:
				((EStructuralFeature.Setting)getChildren()).set(newValue);
				return;
			case FlowPackage.ARTIFACT__OUTBOUND_RELATIONSHIPS:
				((EStructuralFeature.Setting)getOutboundRelationships()).set(newValue);
				return;
			case FlowPackage.ARTIFACT__PARTITION:
				setPartition((Boolean)newValue);
				return;
			case FlowPackage.ARTIFACT__STYLE:
				setStyle((DiagramElement)newValue);
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
			case FlowPackage.ARTIFACT__SERVICES:
				getServices().clear();
				return;
			case FlowPackage.ARTIFACT__REPOSITORY_KEYS:
				getRepositoryKeys().clear();
				return;
			case FlowPackage.ARTIFACT__CHILDREN:
				getChildren().clear();
				return;
			case FlowPackage.ARTIFACT__OUTBOUND_RELATIONSHIPS:
				getOutboundRelationships().clear();
				return;
			case FlowPackage.ARTIFACT__PARTITION:
				setPartition(PARTITION_EDEFAULT);
				return;
			case FlowPackage.ARTIFACT__STYLE:
				setStyle((DiagramElement)null);
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
			case FlowPackage.ARTIFACT__SERVICES:
				return !getServices().isEmpty();
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
			case FlowPackage.ARTIFACT__RESPONSIBILITIES:
				return !getResponsibilities().isEmpty();
			case FlowPackage.ARTIFACT__CHILDREN:
				return !getChildren().isEmpty();
			case FlowPackage.ARTIFACT__OUTBOUND_RELATIONSHIPS:
				return !getOutboundRelationships().isEmpty();
			case FlowPackage.ARTIFACT__INBOUND_RELATIONSHIPS:
				return !getInboundRelationships().isEmpty();
			case FlowPackage.ARTIFACT__PARTITION:
				return isPartition() != PARTITION_EDEFAULT;
			case FlowPackage.ARTIFACT__STYLE:
				return getStyle() != null;
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
		if (baseClass == ServiceProvider.class) {
			switch (derivedFeatureID) {
				case FlowPackage.ARTIFACT__SERVICES: return FlowPackage.SERVICE_PROVIDER__SERVICES;
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
		if (baseClass == ServiceProvider.class) {
			switch (baseFeatureID) {
				case FlowPackage.SERVICE_PROVIDER__SERVICES: return FlowPackage.ARTIFACT__SERVICES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	@SuppressWarnings("unchecked")
	@Override
	public EList<Artifact> getExtends() {
		EList<Artifact> ret = ECollections.newBasicEList();
		if (eContainmentFeature() == FlowPackage.Literals.ARTIFACT_ENTRY__VALUE) {
			String key = ((Map.Entry<String, ?>) eContainer()).getKey();
			PackageElement<?> container = (PackageElement<?>) eContainer().eContainer();
			for (PackageElement<?> cExtends: container.getExtends()) {
				if (cExtends instanceof Package) {
					Artifact ext = ((Package) cExtends).getArtifacts().get(key);
					if (ext != null) {
						ret.add(ext);
					}
				} else if (cExtends instanceof Artifact) {
					Artifact ext = ((Artifact) cExtends).getChildren().get(key);
					if (ext != null) {
						ret.add(ext);
					}
				}
			}
		}
		
		return ret;
	}
	
	@Override
	public void apply(Artifact instance) {
		super.apply(instance);
		
		// Artifacts
		for (Entry<String, Artifact> ae: getChildren().entrySet()) {
			Artifact artifact = ae.getValue();
			EMap<String, Artifact> instanceArtifacts = instance.getChildren();
			String artifactKey = ae.getKey();
			if (artifact == null) {
				instanceArtifacts.removeKey(artifactKey);
			} else {
				Artifact instanceArtifact = artifact.create();
				instanceArtifacts.put(artifactKey, instanceArtifact);
				artifact.apply(instanceArtifact);
			}
		}
		
		// Style
		DiagramElement style = getStyle();
		if (style != null) {
			instance.setStyle(EcoreUtil.copy(style));
		}

		// Relationships
		for (Entry<String, Relationship> outboundRelationship: getOutboundRelationships()) {
			Relationship relationship = outboundRelationship.getValue();
			EMap<String, Relationship> instanceOutboundRelationships = instance.getOutboundRelationships();
			String relKey = outboundRelationship.getKey();
			if (relationship == null) {
				instanceOutboundRelationships.removeKey(relKey);
			} else {
				Relationship instanceOutboundRelationship = relationship.create();
				instanceOutboundRelationships.put(relKey, instanceOutboundRelationship);
				relationship.apply(instanceOutboundRelationship);
			}
		}
		
	}
	
} //ArtifactImpl
