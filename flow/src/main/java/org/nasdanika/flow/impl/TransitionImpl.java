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
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.flow.Artifact;
import org.nasdanika.flow.FlowElement;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Transition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.impl.TransitionImpl#getPayload <em>Payload</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.TransitionImpl#getPayloadKeys <em>Payload Keys</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.TransitionImpl#isSuppress <em>Suppress</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.TransitionImpl#getTargetKey <em>Target Key</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.TransitionImpl#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TransitionImpl extends PackageElementImpl<Transition> implements Transition {
	/**
	 * The default value of the '{@link #isSuppress() <em>Suppress</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSuppress()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SUPPRESS_EDEFAULT = false;

	/**
	 * The default value of the '{@link #getTargetKey() <em>Target Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetKey()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_KEY_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FlowPackage.Literals.TRANSITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific type known in this context.
	 * @generated
	 */
	@Override
	public void setPrototype(Transition newPrototype) {
		super.setPrototype(newPrototype);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Artifact> getPayload() {
		EList<Artifact> ret = ECollections.newBasicEList();
		org.eclipse.emf.ecore.resource.Resource res = eResource();
		if (res != null) {
			ResourceSet resourceSet = res.getResourceSet();
			if (resourceSet != null) {
				for (EObject ancestor = eContainer(); ancestor != null; ancestor = ancestor.eContainer()) {
					if (ancestor instanceof org.nasdanika.flow.Package) {
						URI artifactsURI = URI.createURI(((org.nasdanika.flow.Package) ancestor).getUri() + "/artifacts/");
						for (String key: getPayloadKeys()) {
							URI aURI = URI.createURI(key).resolve(artifactsURI);
							EObject target = resourceSet.getEObject(aURI, false);
							if (target == null) {
								throw new ConfigurationException("Invalid artifact reference: " + key + " (" + aURI + ")", EObjectAdaptable.adaptTo(this, Marked.class));
							}
							
							if (target instanceof Artifact) {
								ret.add((Artifact) target);
							} else {
								throw new ConfigurationException("Expected artifact at: " + key + " (" + aURI + "), got " + target, EObjectAdaptable.adaptTo(this, Marked.class));
							}
						}
						break;
					}
				}
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
	public EList<String> getPayloadKeys() {
		return (EList<String>)eDynamicGet(FlowPackage.TRANSITION__PAYLOAD_KEYS, FlowPackage.Literals.TRANSITION__PAYLOAD_KEYS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSuppress() {
		return (Boolean)eDynamicGet(FlowPackage.TRANSITION__SUPPRESS, FlowPackage.Literals.TRANSITION__SUPPRESS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSuppress(boolean newSuppress) {
		eDynamicSet(FlowPackage.TRANSITION__SUPPRESS, FlowPackage.Literals.TRANSITION__SUPPRESS, newSuppress);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTargetKey() {
		return (String)eDynamicGet(FlowPackage.TRANSITION__TARGET_KEY, FlowPackage.Literals.TRANSITION__TARGET_KEY, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTargetKey(String newTargetKey) {
		eDynamicSet(FlowPackage.TRANSITION__TARGET_KEY, FlowPackage.Literals.TRANSITION__TARGET_KEY, newTargetKey);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public FlowElement<?> getTarget() {
		String targetKey = getTargetKey();
		if (Util.isBlank(targetKey)) {
			return null;
		}
		org.eclipse.emf.ecore.resource.Resource res = eResource();
		if (res == null) {
			throw new IllegalStateException("Not in a resource");
		}
		ResourceSet resourceSet = res.getResourceSet();
		if (resourceSet == null) {
			throw new IllegalStateException("Not in a resourceset");
		}
		for (EObject ancestor = eContainer(); ancestor != null; ancestor = ancestor.eContainer()) {
			if (ancestor instanceof org.nasdanika.flow.Flow) {
				URI flowElementsURI = URI.createURI(((org.nasdanika.flow.Flow) ancestor).getUri() + "/elements/");
				URI feURI = URI.createURI(targetKey).resolve(flowElementsURI);
				EObject target = resourceSet.getEObject(feURI, false);
				if (target == null) {
					throw new ConfigurationException("Invalid flow element reference: " + targetKey + " (" + feURI + ")", EObjectAdaptable.adaptTo(this, Marked.class));
				}
				
				if (target instanceof FlowElement) {
					return (FlowElement<?>) target;
				}
				
				throw new ConfigurationException("Expected flow element at: " + targetKey + " (" + feURI + "), got " + target, EObjectAdaptable.adaptTo(this, Marked.class));
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlowElement<?> basicGetTarget() {
		return (FlowElement<?>)eDynamicGet(FlowPackage.TRANSITION__TARGET, FlowPackage.Literals.TRANSITION__TARGET, false, true);
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
			case FlowPackage.TRANSITION__PAYLOAD:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPayload()).basicAdd(otherEnd, msgs);
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
			case FlowPackage.TRANSITION__PAYLOAD:
				return ((InternalEList<?>)getPayload()).basicRemove(otherEnd, msgs);
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
			case FlowPackage.TRANSITION__PAYLOAD:
				return getPayload();
			case FlowPackage.TRANSITION__PAYLOAD_KEYS:
				return getPayloadKeys();
			case FlowPackage.TRANSITION__SUPPRESS:
				return isSuppress();
			case FlowPackage.TRANSITION__TARGET_KEY:
				return getTargetKey();
			case FlowPackage.TRANSITION__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
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
			case FlowPackage.TRANSITION__PAYLOAD_KEYS:
				getPayloadKeys().clear();
				getPayloadKeys().addAll((Collection<? extends String>)newValue);
				return;
			case FlowPackage.TRANSITION__SUPPRESS:
				setSuppress((Boolean)newValue);
				return;
			case FlowPackage.TRANSITION__TARGET_KEY:
				setTargetKey((String)newValue);
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
			case FlowPackage.TRANSITION__PAYLOAD_KEYS:
				getPayloadKeys().clear();
				return;
			case FlowPackage.TRANSITION__SUPPRESS:
				setSuppress(SUPPRESS_EDEFAULT);
				return;
			case FlowPackage.TRANSITION__TARGET_KEY:
				setTargetKey(TARGET_KEY_EDEFAULT);
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
			case FlowPackage.TRANSITION__PAYLOAD:
				return !getPayload().isEmpty();
			case FlowPackage.TRANSITION__PAYLOAD_KEYS:
				return !getPayloadKeys().isEmpty();
			case FlowPackage.TRANSITION__SUPPRESS:
				return isSuppress() != SUPPRESS_EDEFAULT;
			case FlowPackage.TRANSITION__TARGET_KEY:
				return TARGET_KEY_EDEFAULT == null ? getTargetKey() != null : !TARGET_KEY_EDEFAULT.equals(getTargetKey());
			case FlowPackage.TRANSITION__TARGET:
				return basicGetTarget() != null;
		}
		return super.eIsSet(featureID);
	}

	@SuppressWarnings("unchecked")
	@Override
	public EList<Transition> getExtends() {
		EList<Transition> ret = ECollections.newBasicEList();
		if (eContainmentFeature() == FlowPackage.Literals.TRANSITION_ENTRY__VALUE) {
			String key = ((Map.Entry<String, ?>) eContainer()).getKey();
			FlowElement<?> container = (FlowElement<?>) eContainer().eContainer();
			for (FlowElement<?> cExtends: container.getExtends()) {
				Transition ext = cExtends.getOutputs().get(key);
				if (ext != null) {
					ret.add(ext);
				}
			}
		}
		
		return ret;
	}

} //TransitionImpl
