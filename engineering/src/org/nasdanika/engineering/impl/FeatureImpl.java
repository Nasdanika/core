/**
 */
package org.nasdanika.engineering.impl;

import java.util.Collection;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.nasdanika.engineering.EngineeringPackage;
import org.nasdanika.engineering.Feature;
import org.nasdanika.engineering.FeatureType;
import org.nasdanika.engineering.Release;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.impl.FeatureImpl#getPlannedFor <em>Planned For</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.FeatureImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.FeatureImpl#getRequires <em>Requires</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FeatureImpl extends MinimalEObjectImpl.Container implements Feature {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EngineeringPackage.Literals.FEATURE;
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
	public Release getPlannedFor() {
		return (Release)eDynamicGet(EngineeringPackage.FEATURE__PLANNED_FOR, EngineeringPackage.Literals.FEATURE__PLANNED_FOR, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Release basicGetPlannedFor() {
		return (Release)eDynamicGet(EngineeringPackage.FEATURE__PLANNED_FOR, EngineeringPackage.Literals.FEATURE__PLANNED_FOR, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPlannedFor(Release newPlannedFor) {
		eDynamicSet(EngineeringPackage.FEATURE__PLANNED_FOR, EngineeringPackage.Literals.FEATURE__PLANNED_FOR, newPlannedFor);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FeatureType getType() {
		return (FeatureType)eDynamicGet(EngineeringPackage.FEATURE__TYPE, EngineeringPackage.Literals.FEATURE__TYPE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureType basicGetType() {
		return (FeatureType)eDynamicGet(EngineeringPackage.FEATURE__TYPE, EngineeringPackage.Literals.FEATURE__TYPE, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(FeatureType newType) {
		eDynamicSet(EngineeringPackage.FEATURE__TYPE, EngineeringPackage.Literals.FEATURE__TYPE, newType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Feature> getRequires() {
		return (EList<Feature>)eDynamicGet(EngineeringPackage.FEATURE__REQUIRES, EngineeringPackage.Literals.FEATURE__REQUIRES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EngineeringPackage.FEATURE__PLANNED_FOR:
				if (resolve) return getPlannedFor();
				return basicGetPlannedFor();
			case EngineeringPackage.FEATURE__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case EngineeringPackage.FEATURE__REQUIRES:
				return getRequires();
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
			case EngineeringPackage.FEATURE__PLANNED_FOR:
				setPlannedFor((Release)newValue);
				return;
			case EngineeringPackage.FEATURE__TYPE:
				setType((FeatureType)newValue);
				return;
			case EngineeringPackage.FEATURE__REQUIRES:
				getRequires().clear();
				getRequires().addAll((Collection<? extends Feature>)newValue);
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
			case EngineeringPackage.FEATURE__PLANNED_FOR:
				setPlannedFor((Release)null);
				return;
			case EngineeringPackage.FEATURE__TYPE:
				setType((FeatureType)null);
				return;
			case EngineeringPackage.FEATURE__REQUIRES:
				getRequires().clear();
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
			case EngineeringPackage.FEATURE__PLANNED_FOR:
				return basicGetPlannedFor() != null;
			case EngineeringPackage.FEATURE__TYPE:
				return basicGetType() != null;
			case EngineeringPackage.FEATURE__REQUIRES:
				return !getRequires().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //FeatureImpl
