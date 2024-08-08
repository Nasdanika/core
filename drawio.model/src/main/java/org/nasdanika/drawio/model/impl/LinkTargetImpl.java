/**
 */
package org.nasdanika.drawio.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.drawio.model.LinkTarget;
import org.nasdanika.drawio.model.ModelElement;
import org.nasdanika.drawio.model.ModelPackage;

import org.nasdanika.ncore.Marker;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.StringIdentity;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Link Target</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.impl.LinkTargetImpl#getMarkers <em>Markers</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.LinkTargetImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.LinkTargetImpl#getLinks <em>Links</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LinkTargetImpl extends MinimalEObjectImpl.Container implements LinkTarget {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LinkTargetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.LINK_TARGET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Marker> getMarkers() {
		return (EList<Marker>)eDynamicGet(ModelPackage.LINK_TARGET__MARKERS, NcorePackage.Literals.MARKED__MARKERS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return (String)eDynamicGet(ModelPackage.LINK_TARGET__ID, NcorePackage.Literals.STRING_IDENTITY__ID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		eDynamicSet(ModelPackage.LINK_TARGET__ID, NcorePackage.Literals.STRING_IDENTITY__ID, newId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<ModelElement> getLinks() {
		return (EList<ModelElement>)eDynamicGet(ModelPackage.LINK_TARGET__LINKS, ModelPackage.Literals.LINK_TARGET__LINKS, true, true);
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
			case ModelPackage.LINK_TARGET__LINKS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLinks()).basicAdd(otherEnd, msgs);
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
			case ModelPackage.LINK_TARGET__MARKERS:
				return ((InternalEList<?>)getMarkers()).basicRemove(otherEnd, msgs);
			case ModelPackage.LINK_TARGET__LINKS:
				return ((InternalEList<?>)getLinks()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.LINK_TARGET__MARKERS:
				return getMarkers();
			case ModelPackage.LINK_TARGET__ID:
				return getId();
			case ModelPackage.LINK_TARGET__LINKS:
				return getLinks();
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
			case ModelPackage.LINK_TARGET__MARKERS:
				getMarkers().clear();
				getMarkers().addAll((Collection<? extends Marker>)newValue);
				return;
			case ModelPackage.LINK_TARGET__ID:
				setId((String)newValue);
				return;
			case ModelPackage.LINK_TARGET__LINKS:
				getLinks().clear();
				getLinks().addAll((Collection<? extends ModelElement>)newValue);
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
			case ModelPackage.LINK_TARGET__MARKERS:
				getMarkers().clear();
				return;
			case ModelPackage.LINK_TARGET__ID:
				setId(ID_EDEFAULT);
				return;
			case ModelPackage.LINK_TARGET__LINKS:
				getLinks().clear();
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
			case ModelPackage.LINK_TARGET__MARKERS:
				return !getMarkers().isEmpty();
			case ModelPackage.LINK_TARGET__ID:
				return ID_EDEFAULT == null ? getId() != null : !ID_EDEFAULT.equals(getId());
			case ModelPackage.LINK_TARGET__LINKS:
				return !getLinks().isEmpty();
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
		if (baseClass == StringIdentity.class) {
			switch (derivedFeatureID) {
				case ModelPackage.LINK_TARGET__ID: return NcorePackage.STRING_IDENTITY__ID;
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
		if (baseClass == StringIdentity.class) {
			switch (baseFeatureID) {
				case NcorePackage.STRING_IDENTITY__ID: return ModelPackage.LINK_TARGET__ID;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //LinkTargetImpl
