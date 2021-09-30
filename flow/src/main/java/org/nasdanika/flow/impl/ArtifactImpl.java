/**
 */
package org.nasdanika.flow.impl;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.flow.Artifact;
import org.nasdanika.flow.FlowElement;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Package;
import org.nasdanika.flow.Resource;

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
	@Override
	public EList<Resource> getRepositories() {
		EList<Resource> ret = ECollections.newBasicEList();
		org.eclipse.emf.ecore.resource.Resource res = eResource();
		if (res == null) {
			throw new IllegalStateException("Not in a resource");
		}
		ResourceSet resourceSet = res.getResourceSet();
		if (resourceSet == null) {
			throw new IllegalStateException("Not in a resourceset");
		}
		for (EObject ancestor = eContainer(); ancestor != null; ancestor = ancestor.eContainer()) {
			if (ancestor instanceof org.nasdanika.flow.Package) {
				URI resourcesURI = URI.createURI(((org.nasdanika.flow.Package) ancestor).getUri() + "/resources/");
				for (String key: getRepositoryKeys()) {
					URI rURI = URI.createURI(key).resolve(resourcesURI);
					EObject target = resourceSet.getEObject(rURI, false);
					if (target == null) {
						throw new ConfigurationException("Invalid resource reference: " + key + " (" + rURI + ")", EObjectAdaptable.adaptTo(this, Marked.class));
					}
					
					if (target instanceof Resource) {
						ret.add((Resource) target);
					} else {
						throw new ConfigurationException("Expected resource at: " + key + " (" + rURI + "), got " + target, EObjectAdaptable.adaptTo(this, Marked.class));
					}
				}
				break;
			}
		}
		return ret;
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
