/**
 */
package org.nasdanika.engineering.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.nasdanika.engineering.EngineeringPackage;
import org.nasdanika.engineering.IssueResolution;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Issue Resolution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.impl.IssueResolutionImpl#isCompleted <em>Completed</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IssueResolutionImpl extends MinimalEObjectImpl.Container implements IssueResolution {
	/**
	 * The default value of the '{@link #isCompleted() <em>Completed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCompleted()
	 * @generated
	 * @ordered
	 */
	protected static final boolean COMPLETED_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IssueResolutionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EngineeringPackage.Literals.ISSUE_RESOLUTION;
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
	public boolean isCompleted() {
		return (Boolean)eDynamicGet(EngineeringPackage.ISSUE_RESOLUTION__COMPLETED, EngineeringPackage.Literals.ISSUE_RESOLUTION__COMPLETED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCompleted(boolean newCompleted) {
		eDynamicSet(EngineeringPackage.ISSUE_RESOLUTION__COMPLETED, EngineeringPackage.Literals.ISSUE_RESOLUTION__COMPLETED, newCompleted);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EngineeringPackage.ISSUE_RESOLUTION__COMPLETED:
				return isCompleted();
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
			case EngineeringPackage.ISSUE_RESOLUTION__COMPLETED:
				setCompleted((Boolean)newValue);
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
			case EngineeringPackage.ISSUE_RESOLUTION__COMPLETED:
				setCompleted(COMPLETED_EDEFAULT);
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
			case EngineeringPackage.ISSUE_RESOLUTION__COMPLETED:
				return isCompleted() != COMPLETED_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //IssueResolutionImpl
