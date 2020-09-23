/**
 */
package org.nasdanika.engineering.impl;

import java.util.Collection;
import java.util.Date;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.nasdanika.engineering.EngineeringPackage;
import org.nasdanika.engineering.Increment;
import org.nasdanika.engineering.Release;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Release</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.impl.ReleaseImpl#getRequires <em>Requires</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.ReleaseImpl#getIncludes <em>Includes</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.ReleaseImpl#getDate <em>Date</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.ReleaseImpl#getPlannedFor <em>Planned For</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReleaseImpl extends MinimalEObjectImpl.Container implements Release {
	/**
	 * The default value of the '{@link #getDate() <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDate()
	 * @generated
	 * @ordered
	 */
	protected static final Date DATE_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReleaseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EngineeringPackage.Literals.RELEASE;
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
	public EList<Release> getRequires() {
		return (EList<Release>)eDynamicGet(EngineeringPackage.RELEASE__REQUIRES, EngineeringPackage.Literals.RELEASE__REQUIRES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Release> getIncludes() {
		return (EList<Release>)eDynamicGet(EngineeringPackage.RELEASE__INCLUDES, EngineeringPackage.Literals.RELEASE__INCLUDES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getDate() {
		return (Date)eDynamicGet(EngineeringPackage.RELEASE__DATE, EngineeringPackage.Literals.RELEASE__DATE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDate(Date newDate) {
		eDynamicSet(EngineeringPackage.RELEASE__DATE, EngineeringPackage.Literals.RELEASE__DATE, newDate);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Increment getPlannedFor() {
		return (Increment)eDynamicGet(EngineeringPackage.RELEASE__PLANNED_FOR, EngineeringPackage.Literals.RELEASE__PLANNED_FOR, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Increment basicGetPlannedFor() {
		return (Increment)eDynamicGet(EngineeringPackage.RELEASE__PLANNED_FOR, EngineeringPackage.Literals.RELEASE__PLANNED_FOR, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPlannedFor(Increment newPlannedFor) {
		eDynamicSet(EngineeringPackage.RELEASE__PLANNED_FOR, EngineeringPackage.Literals.RELEASE__PLANNED_FOR, newPlannedFor);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EngineeringPackage.RELEASE__REQUIRES:
				return getRequires();
			case EngineeringPackage.RELEASE__INCLUDES:
				return getIncludes();
			case EngineeringPackage.RELEASE__DATE:
				return getDate();
			case EngineeringPackage.RELEASE__PLANNED_FOR:
				if (resolve) return getPlannedFor();
				return basicGetPlannedFor();
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
			case EngineeringPackage.RELEASE__REQUIRES:
				getRequires().clear();
				getRequires().addAll((Collection<? extends Release>)newValue);
				return;
			case EngineeringPackage.RELEASE__INCLUDES:
				getIncludes().clear();
				getIncludes().addAll((Collection<? extends Release>)newValue);
				return;
			case EngineeringPackage.RELEASE__DATE:
				setDate((Date)newValue);
				return;
			case EngineeringPackage.RELEASE__PLANNED_FOR:
				setPlannedFor((Increment)newValue);
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
			case EngineeringPackage.RELEASE__REQUIRES:
				getRequires().clear();
				return;
			case EngineeringPackage.RELEASE__INCLUDES:
				getIncludes().clear();
				return;
			case EngineeringPackage.RELEASE__DATE:
				setDate(DATE_EDEFAULT);
				return;
			case EngineeringPackage.RELEASE__PLANNED_FOR:
				setPlannedFor((Increment)null);
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
			case EngineeringPackage.RELEASE__REQUIRES:
				return !getRequires().isEmpty();
			case EngineeringPackage.RELEASE__INCLUDES:
				return !getIncludes().isEmpty();
			case EngineeringPackage.RELEASE__DATE:
				return DATE_EDEFAULT == null ? getDate() != null : !DATE_EDEFAULT.equals(getDate());
			case EngineeringPackage.RELEASE__PLANNED_FOR:
				return basicGetPlannedFor() != null;
		}
		return super.eIsSet(featureID);
	}

} //ReleaseImpl
