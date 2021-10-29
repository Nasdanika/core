/**
 */
package org.nasdanika.flow.impl;

import java.util.Map;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.Util;
import org.nasdanika.diagram.Connection;
import org.nasdanika.flow.Artifact;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Relationship;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.util.NcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Relationship</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.impl.RelationshipImpl#getTargetKey <em>Target Key</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.RelationshipImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.RelationshipImpl#getStyle <em>Style</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RelationshipImpl extends PackageElementImpl<Relationship> implements Relationship {
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
	protected RelationshipImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FlowPackage.Literals.RELATIONSHIP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific type known in this context.
	 * @generated
	 */
	@Override
	public void setPrototype(Relationship newPrototype) {
		super.setPrototype(newPrototype);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTargetKey() {
		return (String)eDynamicGet(FlowPackage.RELATIONSHIP__TARGET_KEY, FlowPackage.Literals.RELATIONSHIP__TARGET_KEY, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTargetKey(String newTargetKey) {
		eDynamicSet(FlowPackage.RELATIONSHIP__TARGET_KEY, FlowPackage.Literals.RELATIONSHIP__TARGET_KEY, newTargetKey);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Artifact getTarget() {
		return (Artifact) getCachedFeature(FlowPackage.Literals.RELATIONSHIP__TARGET);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Artifact basicGetTarget() {
		return (Artifact)eDynamicGet(FlowPackage.RELATIONSHIP__TARGET, FlowPackage.Literals.RELATIONSHIP__TARGET, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTarget(Artifact newTarget, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newTarget, FlowPackage.RELATIONSHIP__TARGET, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Connection getStyle() {
		return (Connection)eDynamicGet(FlowPackage.RELATIONSHIP__STYLE, FlowPackage.Literals.RELATIONSHIP__STYLE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStyle(Connection newStyle, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newStyle, FlowPackage.RELATIONSHIP__STYLE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStyle(Connection newStyle) {
		eDynamicSet(FlowPackage.RELATIONSHIP__STYLE, FlowPackage.Literals.RELATIONSHIP__STYLE, newStyle);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FlowPackage.RELATIONSHIP__TARGET:
				Artifact target = basicGetTarget();
				if (target != null)
					msgs = ((InternalEObject)target).eInverseRemove(this, FlowPackage.ARTIFACT__INBOUND_RELATIONSHIPS, Artifact.class, msgs);
				return basicSetTarget((Artifact)otherEnd, msgs);
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
			case FlowPackage.RELATIONSHIP__TARGET:
				return basicSetTarget(null, msgs);
			case FlowPackage.RELATIONSHIP__STYLE:
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
			case FlowPackage.RELATIONSHIP__TARGET_KEY:
				return getTargetKey();
			case FlowPackage.RELATIONSHIP__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case FlowPackage.RELATIONSHIP__STYLE:
				return getStyle();
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
			case FlowPackage.RELATIONSHIP__TARGET_KEY:
				setTargetKey((String)newValue);
				return;
			case FlowPackage.RELATIONSHIP__STYLE:
				setStyle((Connection)newValue);
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
			case FlowPackage.RELATIONSHIP__TARGET_KEY:
				setTargetKey(TARGET_KEY_EDEFAULT);
				return;
			case FlowPackage.RELATIONSHIP__STYLE:
				setStyle((Connection)null);
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
			case FlowPackage.RELATIONSHIP__TARGET_KEY:
				return TARGET_KEY_EDEFAULT == null ? getTargetKey() != null : !TARGET_KEY_EDEFAULT.equals(getTargetKey());
			case FlowPackage.RELATIONSHIP__TARGET:
				return basicGetTarget() != null;
			case FlowPackage.RELATIONSHIP__STYLE:
				return getStyle() != null;
		}
		return super.eIsSet(featureID);
	}
		
	@Override
	protected Object computeCachedFeature(EStructuralFeature feature) {
		if (feature == FlowPackage.Literals.RELATIONSHIP__TARGET) {
			URI targetURI = URI.createURI(getTargetKey());
			if (eContainmentFeature() == FlowPackage.Literals.RELATIONSHIP_ENTRY__VALUE) {
				URI ccURI = NcoreUtil.getUri(eContainer().eContainer());
				if (ccURI != null) {
					targetURI = targetURI.resolve(ccURI);
				}
			}
			return resolveArtifact(targetURI);
		}
		return super.computeCachedFeature(feature);
	}	
	
	@Override
	public void apply(Relationship instance) {
		super.apply(instance);
		
		// Style
		Connection style = getStyle();
		if (style != null) {
			instance.setStyle(EcoreUtil.copy(style));
		}		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public EList<Relationship> getExtends() {
		EList<Relationship> ret = ECollections.newBasicEList();
		if (eContainmentFeature() == FlowPackage.Literals.RELATIONSHIP_ENTRY__VALUE) {
			String key = ((Map.Entry<String, ?>) eContainer()).getKey();
			Artifact container = (Artifact) eContainer().eContainer();
			for (Artifact cExtends: container.getExtends()) {
				Relationship ext = cExtends.getOutboundRelationships().get(key);
				if (ext != null) {
					ret.add(ext);
				}
			}
		}
		
		return ret;
	}	

} //RelationshipImpl
