/**
 */
package org.nasdanika.ncore.impl;

import java.lang.String;
import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.ncore.Map;
import org.nasdanika.ncore.MapProperty;
import org.nasdanika.ncore.Marker;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.Property;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Map Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.impl.MapPropertyImpl#getMarkers <em>Markers</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.MapPropertyImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.MapPropertyImpl#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MapPropertyImpl extends MinimalEObjectImpl.Container implements MapProperty {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MapPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NcorePackage.Literals.MAP_PROPERTY;
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
	@Override
	public String getName() {
		return (String)eDynamicGet(NcorePackage.MAP_PROPERTY__NAME, NcorePackage.Literals.PROPERTY__NAME, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		eDynamicSet(NcorePackage.MAP_PROPERTY__NAME, NcorePackage.Literals.PROPERTY__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Marker> getMarkers() {
		return (EList<Marker>)eDynamicGet(NcorePackage.MAP_PROPERTY__MARKERS, NcorePackage.Literals.MARKED__MARKERS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Property> getValue() {
		return (EList<Property>)eDynamicGet(NcorePackage.MAP_PROPERTY__VALUE, NcorePackage.Literals.MAP__VALUE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NcorePackage.MAP_PROPERTY__MARKERS:
				return ((InternalEList<?>)getMarkers()).basicRemove(otherEnd, msgs);
			case NcorePackage.MAP_PROPERTY__VALUE:
				return ((InternalEList<?>)getValue()).basicRemove(otherEnd, msgs);
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
			case NcorePackage.MAP_PROPERTY__MARKERS:
				return getMarkers();
			case NcorePackage.MAP_PROPERTY__NAME:
				return getName();
			case NcorePackage.MAP_PROPERTY__VALUE:
				return getValue();
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
			case NcorePackage.MAP_PROPERTY__MARKERS:
				getMarkers().clear();
				getMarkers().addAll((Collection<? extends Marker>)newValue);
				return;
			case NcorePackage.MAP_PROPERTY__NAME:
				setName((String)newValue);
				return;
			case NcorePackage.MAP_PROPERTY__VALUE:
				getValue().clear();
				getValue().addAll((Collection<? extends Property>)newValue);
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
			case NcorePackage.MAP_PROPERTY__MARKERS:
				getMarkers().clear();
				return;
			case NcorePackage.MAP_PROPERTY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case NcorePackage.MAP_PROPERTY__VALUE:
				getValue().clear();
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
			case NcorePackage.MAP_PROPERTY__MARKERS:
				return !getMarkers().isEmpty();
			case NcorePackage.MAP_PROPERTY__NAME:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
			case NcorePackage.MAP_PROPERTY__VALUE:
				return !getValue().isEmpty();
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
		if (baseClass == Map.class) {
			switch (derivedFeatureID) {
				case NcorePackage.MAP_PROPERTY__VALUE: return NcorePackage.MAP__VALUE;
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
		if (baseClass == Map.class) {
			switch (baseFeatureID) {
				case NcorePackage.MAP__VALUE: return NcorePackage.MAP_PROPERTY__VALUE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //MapPropertyImpl
