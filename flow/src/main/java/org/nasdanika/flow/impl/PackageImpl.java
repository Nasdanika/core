/**
 */
package org.nasdanika.flow.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

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
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Package;
import org.nasdanika.flow.Participant;
import org.nasdanika.flow.Resource;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.impl.PackageImpl#getSuperPackages <em>Super Packages</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.PackageImpl#getSubPackages <em>Sub Packages</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.PackageImpl#getParticipants <em>Participants</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.PackageImpl#getResources <em>Resources</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.PackageImpl#getArtifacts <em>Artifacts</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.PackageImpl#getActivities <em>Activities</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PackageImpl extends PackageElementImpl<org.nasdanika.flow.Package> implements org.nasdanika.flow.Package {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FlowPackage.Literals.PACKAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific type known in this context.
	 * @generated
	 */
	@Override
	public void setPrototype(org.nasdanika.flow.Package newPrototype) {
		super.setPrototype(newPrototype);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<org.nasdanika.flow.Package> getSuperPackages() {
		return (EList<org.nasdanika.flow.Package>)eDynamicGet(FlowPackage.PACKAGE__SUPER_PACKAGES, FlowPackage.Literals.PACKAGE__SUPER_PACKAGES, true, true);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public EList<Package> getExtends() {
		EList<Package> ret = ECollections.newBasicEList();
		if (eContainmentFeature() == FlowPackage.Literals.PACKAGE_ENTRY__VALUE) {
			String key = ((Map.Entry<String, ?>) eContainer()).getKey();
			Package container = (Package) eContainer().eContainer();
			for (Package cExtends: container.getExtends()) {
				Package ext = cExtends.getSubPackages().get(key);
				if (ext != null) {
					ret.add(ext);
				}
			}
		}
		
		ret.addAll(getSuperPackages());
		return ret;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, org.nasdanika.flow.Package> getSubPackages() {
		return (EMap<String, org.nasdanika.flow.Package>)eDynamicGet(FlowPackage.PACKAGE__SUB_PACKAGES, FlowPackage.Literals.PACKAGE__SUB_PACKAGES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, Participant> getParticipants() {
		return (EMap<String, Participant>)eDynamicGet(FlowPackage.PACKAGE__PARTICIPANTS, FlowPackage.Literals.PACKAGE__PARTICIPANTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, Resource> getResources() {
		return (EMap<String, Resource>)eDynamicGet(FlowPackage.PACKAGE__RESOURCES, FlowPackage.Literals.PACKAGE__RESOURCES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, Artifact> getArtifacts() {
		return (EMap<String, Artifact>)eDynamicGet(FlowPackage.PACKAGE__ARTIFACTS, FlowPackage.Literals.PACKAGE__ARTIFACTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, Activity<?>> getActivities() {
		return (EMap<String, Activity<?>>)eDynamicGet(FlowPackage.PACKAGE__ACTIVITIES, FlowPackage.Literals.PACKAGE__ACTIVITIES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FlowPackage.PACKAGE__SUB_PACKAGES:
				return ((InternalEList<?>)getSubPackages()).basicRemove(otherEnd, msgs);
			case FlowPackage.PACKAGE__PARTICIPANTS:
				return ((InternalEList<?>)getParticipants()).basicRemove(otherEnd, msgs);
			case FlowPackage.PACKAGE__RESOURCES:
				return ((InternalEList<?>)getResources()).basicRemove(otherEnd, msgs);
			case FlowPackage.PACKAGE__ARTIFACTS:
				return ((InternalEList<?>)getArtifacts()).basicRemove(otherEnd, msgs);
			case FlowPackage.PACKAGE__ACTIVITIES:
				return ((InternalEList<?>)getActivities()).basicRemove(otherEnd, msgs);
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
			case FlowPackage.PACKAGE__SUPER_PACKAGES:
				return getSuperPackages();
			case FlowPackage.PACKAGE__SUB_PACKAGES:
				if (coreType) return getSubPackages();
				else return getSubPackages().map();
			case FlowPackage.PACKAGE__PARTICIPANTS:
				if (coreType) return getParticipants();
				else return getParticipants().map();
			case FlowPackage.PACKAGE__RESOURCES:
				if (coreType) return getResources();
				else return getResources().map();
			case FlowPackage.PACKAGE__ARTIFACTS:
				if (coreType) return getArtifacts();
				else return getArtifacts().map();
			case FlowPackage.PACKAGE__ACTIVITIES:
				if (coreType) return getActivities();
				else return getActivities().map();
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
			case FlowPackage.PACKAGE__SUPER_PACKAGES:
				getSuperPackages().clear();
				getSuperPackages().addAll((Collection<? extends org.nasdanika.flow.Package>)newValue);
				return;
			case FlowPackage.PACKAGE__SUB_PACKAGES:
				((EStructuralFeature.Setting)getSubPackages()).set(newValue);
				return;
			case FlowPackage.PACKAGE__PARTICIPANTS:
				((EStructuralFeature.Setting)getParticipants()).set(newValue);
				return;
			case FlowPackage.PACKAGE__RESOURCES:
				((EStructuralFeature.Setting)getResources()).set(newValue);
				return;
			case FlowPackage.PACKAGE__ARTIFACTS:
				((EStructuralFeature.Setting)getArtifacts()).set(newValue);
				return;
			case FlowPackage.PACKAGE__ACTIVITIES:
				((EStructuralFeature.Setting)getActivities()).set(newValue);
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
			case FlowPackage.PACKAGE__SUPER_PACKAGES:
				getSuperPackages().clear();
				return;
			case FlowPackage.PACKAGE__SUB_PACKAGES:
				getSubPackages().clear();
				return;
			case FlowPackage.PACKAGE__PARTICIPANTS:
				getParticipants().clear();
				return;
			case FlowPackage.PACKAGE__RESOURCES:
				getResources().clear();
				return;
			case FlowPackage.PACKAGE__ARTIFACTS:
				getArtifacts().clear();
				return;
			case FlowPackage.PACKAGE__ACTIVITIES:
				getActivities().clear();
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
			case FlowPackage.PACKAGE__SUPER_PACKAGES:
				return !getSuperPackages().isEmpty();
			case FlowPackage.PACKAGE__SUB_PACKAGES:
				return !getSubPackages().isEmpty();
			case FlowPackage.PACKAGE__PARTICIPANTS:
				return !getParticipants().isEmpty();
			case FlowPackage.PACKAGE__RESOURCES:
				return !getResources().isEmpty();
			case FlowPackage.PACKAGE__ARTIFACTS:
				return !getArtifacts().isEmpty();
			case FlowPackage.PACKAGE__ACTIVITIES:
				return !getActivities().isEmpty();
		}
		return super.eIsSet(featureID);
	}
	
	@Override
	public Package create() {
		Package ret = super.create();
		if (eContainmentFeature() != FlowPackage.Literals.PACKAGE_ENTRY__VALUE) {
			apply(ret);
			resolve(ret);
		}
		return ret;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void apply(Package instance) {
		super.apply(instance);
		
		// --- Creating contained elements ---
		
		// Sub-packages
		for (Entry<String, Package> pe: getSubPackages().entrySet()) {
			Package sp = pe.getValue();
			EMap<String, Package> instanceSubPackages = instance.getSubPackages();
			String spKey = pe.getKey();
			if (sp == null) {
				instanceSubPackages.removeKey(spKey);
			} else {
				Package spi = sp.create();
				instanceSubPackages.put(spKey, spi);
				sp.apply(spi);
			}
		}
		
		// Participants
		for (Entry<String, Participant> pe: getParticipants().entrySet()) {
			Participant participant = pe.getValue();
			EMap<String, Participant> instanceParticipants = instance.getParticipants();
			String participantKey = pe.getKey();
			if (participant == null) {
				instanceParticipants.removeKey(participantKey);
			} else {
				Participant instanceParticipant = participant.create();
				instanceParticipants.put(participantKey, instanceParticipant);
				participant.apply(instanceParticipant);
			}
		}
		
		// Resources
		for (Entry<String, Resource> re: getResources().entrySet()) {
			Resource resource = re.getValue();
			EMap<String, Resource> instanceResources = instance.getResources();
			String resourceKey = re.getKey();
			if (resource == null) {
				instanceResources.removeKey(resourceKey);
			} else {
				Resource instanceResource = resource.create();
				instanceResources.put(resourceKey, instanceResource);
				resource.apply(instanceResource);
			}
		}
		
		// Artifacts
		for (Entry<String, Artifact> ae: getArtifacts().entrySet()) {
			Artifact artifact = ae.getValue();
			EMap<String, Artifact> instanceArtifacts = instance.getArtifacts();
			String artifactKey = ae.getKey();
			if (artifact == null) {
				instanceArtifacts.removeKey(artifactKey);
			} else {
				Artifact instanceArtifact = artifact.create();
				instanceArtifacts.put(artifactKey, instanceArtifact);
				artifact.apply(instanceArtifact);
			}
		}
		
		// Activities
		for (Entry<String, Activity<?>> ae: getActivities().entrySet()) {
			Activity activity = ae.getValue();
			EMap<String, Activity<?>> instanceActivities = instance.getActivities();
			String activityKey = ae.getKey();
			if (activity == null) {
				instanceActivities.removeKey(activityKey);
			} else {
				Activity instanceService = (Activity) activity.create();
				instanceActivities.put(activityKey, instanceService);
				activity.apply(instanceService);
			}
		}
		
	}
	
	@Override
	public void resolve(Package instance) {
		// NOP
	}

} //PackageImpl
