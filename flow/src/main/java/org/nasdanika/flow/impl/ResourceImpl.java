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
import org.nasdanika.flow.FlowElement;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Package;
import org.nasdanika.flow.Participant;
import org.nasdanika.flow.Resource;
import org.nasdanika.ncore.util.NamedElementComparator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.impl.ResourceImpl#getArtifacts <em>Artifacts</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ResourceImpl#getUsedIn <em>Used In</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ResourceImpl#getUsedBy <em>Used By</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ResourceImpl#getChildren <em>Children</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ResourceImpl extends ServiceProviderImpl<Resource> implements Resource {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FlowPackage.Literals.RESOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Artifact> getArtifacts() {
		return getOppositeReferrers(FlowPackage.Literals.RESOURCE__ARTIFACTS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<FlowElement<?>> getUsedIn() {
		return getOppositeReferrers(FlowPackage.Literals.RESOURCE__USED_IN);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Participant> getUsedBy() {
		Set<Participant> collector = new HashSet<>();
		for (FlowElement<?> fe: getUsedIn()) {
			collector.addAll(fe.getParticipants());
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
	public EMap<String, Resource> getChildren() {
		return (EMap<String, Resource>)eDynamicGet(FlowPackage.RESOURCE__CHILDREN, FlowPackage.Literals.RESOURCE__CHILDREN, true, true);
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
			case FlowPackage.RESOURCE__ARTIFACTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getArtifacts()).basicAdd(otherEnd, msgs);
			case FlowPackage.RESOURCE__USED_IN:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getUsedIn()).basicAdd(otherEnd, msgs);
			case FlowPackage.RESOURCE__USED_BY:
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
			case FlowPackage.RESOURCE__ARTIFACTS:
				return ((InternalEList<?>)getArtifacts()).basicRemove(otherEnd, msgs);
			case FlowPackage.RESOURCE__USED_IN:
				return ((InternalEList<?>)getUsedIn()).basicRemove(otherEnd, msgs);
			case FlowPackage.RESOURCE__USED_BY:
				return ((InternalEList<?>)getUsedBy()).basicRemove(otherEnd, msgs);
			case FlowPackage.RESOURCE__CHILDREN:
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
			case FlowPackage.RESOURCE__ARTIFACTS:
				return getArtifacts();
			case FlowPackage.RESOURCE__USED_IN:
				return getUsedIn();
			case FlowPackage.RESOURCE__USED_BY:
				return getUsedBy();
			case FlowPackage.RESOURCE__CHILDREN:
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
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case FlowPackage.RESOURCE__CHILDREN:
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
			case FlowPackage.RESOURCE__CHILDREN:
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
			case FlowPackage.RESOURCE__ARTIFACTS:
				return !getArtifacts().isEmpty();
			case FlowPackage.RESOURCE__USED_IN:
				return !getUsedIn().isEmpty();
			case FlowPackage.RESOURCE__USED_BY:
				return !getUsedBy().isEmpty();
			case FlowPackage.RESOURCE__CHILDREN:
				return !getChildren().isEmpty();
		}
		return super.eIsSet(featureID);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void apply(Resource instance) {
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
	public void resolve(Resource instance) {
		// NOP
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public EList<Resource> getExtends() {
		EList<Resource> ret = ECollections.newBasicEList();
		if (eContainmentFeature() == FlowPackage.Literals.RESOURCE_ENTRY__VALUE) {
			String key = ((Map.Entry<String, ?>) eContainer()).getKey();
			Package container = (Package) eContainer().eContainer();
			for (Package cExtends: container.getExtends()) {
				Resource ext = cExtends.getResources().get(key);
				if (ext != null) {
					ret.add(ext);
				}
			}
		}
		
		return ret;
	}	
	
} //ResourceImpl
