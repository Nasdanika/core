/**
 */
package org.nasdanika.flow.impl;

import java.util.Map;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.flow.Activity;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Package;
import org.nasdanika.flow.Participant;
import org.nasdanika.flow.Resource;
import org.nasdanika.flow.Service;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.impl.ActivityImpl#getServices <em>Services</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ActivityImpl<T extends Activity<T>> extends FlowElementImpl<T> implements Activity<T> {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActivityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FlowPackage.Literals.ACTIVITY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Service> getServices() {
		return getOppositeReferrers(FlowPackage.Literals.ACTIVITY__SERVICES);
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
			case FlowPackage.ACTIVITY__SERVICES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getServices()).basicAdd(otherEnd, msgs);
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
			case FlowPackage.ACTIVITY__SERVICES:
				return ((InternalEList<?>)getServices()).basicRemove(otherEnd, msgs);
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
			case FlowPackage.ACTIVITY__SERVICES:
				return getServices();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case FlowPackage.ACTIVITY__SERVICES:
				return !getServices().isEmpty();
		}
		return super.eIsSet(featureID);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public EList<T> getExtends() {
		if (eContainmentFeature() == FlowPackage.Literals.ACTIVITY_ENTRY__VALUE) {
			EList<T> ret = ECollections.newBasicEList();
			EObject entry = eContainer();
			String key = ((Map.Entry<String, ?>) entry).getKey();
			if (entry.eContainmentFeature() == FlowPackage.Literals.PACKAGE__ACTIVITIES) {
				Package container = (Package) entry.eContainer();
				for (Package cExtends: container.getExtends()) {
					Activity<?> ext = cExtends.getActivities().get(key);
					if (ext != null) {
						ret.add((T) ext);
					}
				}
			} else if (entry.eContainmentFeature() == FlowPackage.Literals.PARTICIPANT__SERVICES) {
				Participant container = (Participant) entry.eContainer();
				for (Participant cExtends: container.getExtends()) {
					Activity<?> ext = cExtends.getServices().get(key);
					if (ext != null) {
						ret.add((T) ext);
					}
				}
			} else if (entry.eContainmentFeature() == FlowPackage.Literals.RESOURCE__SERVICES) {
				Resource container = (Resource) entry.eContainer();
				for (Resource cExtends: container.getExtends()) {
					Activity<?> ext = cExtends.getServices().get(key);
					if (ext != null) {
						ret.add((T) ext);
					}
				}
			} 
			
			return ret;
		}
		
		return super.getExtends();
	}	

} //ActivityImpl
