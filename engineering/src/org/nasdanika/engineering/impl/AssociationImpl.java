/**
 */
package org.nasdanika.rigel.impl;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.rigel.Association;
import org.nasdanika.rigel.PackageElement;
import org.nasdanika.rigel.RigelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Association</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.rigel.impl.AssociationImpl#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AssociationImpl extends ModelElementImpl implements Association {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssociationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RigelPackage.Literals.ASSOCIATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PackageElement getTarget() {
		return (PackageElement)eDynamicGet(RigelPackage.ASSOCIATION__TARGET, RigelPackage.Literals.ASSOCIATION__TARGET, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageElement basicGetTarget() {
		return (PackageElement)eDynamicGet(RigelPackage.ASSOCIATION__TARGET, RigelPackage.Literals.ASSOCIATION__TARGET, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTarget(PackageElement newTarget) {
		eDynamicSet(RigelPackage.ASSOCIATION__TARGET, RigelPackage.Literals.ASSOCIATION__TARGET, newTarget);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RigelPackage.ASSOCIATION__TARGET:
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
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RigelPackage.ASSOCIATION__TARGET:
				setTarget((PackageElement)newValue);
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
			case RigelPackage.ASSOCIATION__TARGET:
				setTarget((PackageElement)null);
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
			case RigelPackage.ASSOCIATION__TARGET:
				return basicGetTarget() != null;
		}
		return super.eIsSet(featureID);
	}

} //AssociationImpl
