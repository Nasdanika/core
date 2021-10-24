/**
 */
package org.nasdanika.flow.impl;

import java.util.Collection;
import java.util.Map;

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
import org.nasdanika.flow.Transition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Call</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.impl.CallImpl#getResponse <em>Response</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.CallImpl#getResponseKeys <em>Response Keys</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CallImpl extends TransitionImpl implements Call {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CallImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FlowPackage.Literals.CALL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Artifact> getResponse() {
		return (EList<Artifact>) getCachedFeature(FlowPackage.Literals.CALL__RESPONSE);
	}
	
	@Override
	protected Object computeCachedFeature(EStructuralFeature feature) {
		if (feature == FlowPackage.Literals.CALL__RESPONSE) {
			return resolveArtifacts(getResponseKeys());
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
	public EList<String> getResponseKeys() {
		return (EList<String>)eDynamicGet(FlowPackage.CALL__RESPONSE_KEYS, FlowPackage.Literals.CALL__RESPONSE_KEYS, true, true);
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
			case FlowPackage.CALL__RESPONSE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getResponse()).basicAdd(otherEnd, msgs);
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
			case FlowPackage.CALL__RESPONSE:
				return ((InternalEList<?>)getResponse()).basicRemove(otherEnd, msgs);
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
			case FlowPackage.CALL__RESPONSE:
				return getResponse();
			case FlowPackage.CALL__RESPONSE_KEYS:
				return getResponseKeys();
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
			case FlowPackage.CALL__RESPONSE_KEYS:
				getResponseKeys().clear();
				getResponseKeys().addAll((Collection<? extends String>)newValue);
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
			case FlowPackage.CALL__RESPONSE_KEYS:
				getResponseKeys().clear();
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
			case FlowPackage.CALL__RESPONSE:
				return !getResponse().isEmpty();
			case FlowPackage.CALL__RESPONSE_KEYS:
				return !getResponseKeys().isEmpty();
		}
		return super.eIsSet(featureID);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public EList<Transition> getExtends() {
		EList<Transition> ret = ECollections.newBasicEList();
		if (eContainmentFeature() == FlowPackage.Literals.CALL_ENTRY__VALUE) {
			String key = ((Map.Entry<String, ?>) eContainer()).getKey();
			FlowElement<?> container = (FlowElement<?>) eContainer().eContainer();
			for (FlowElement<?> cExtends: container.getExtends()) {
				Call ext = cExtends.getCalls().get(key);
				if (ext != null) {
					ret.add(ext);
				}
			}
		}
		
		return ret;
	}

} //CallImpl
