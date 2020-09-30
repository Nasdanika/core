/**
 */
package org.nasdanika.party.impl;

import org.eclipse.emf.ecore.EClass;
import org.nasdanika.party.PartyPackage;
import org.nasdanika.party.ResourceReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.party.impl.ResourceReferenceImpl#getLocation <em>Location</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ResourceReferenceImpl extends ResourceImpl implements ResourceReference {
	/**
	 * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCATION_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PartyPackage.Literals.RESOURCE_REFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLocation() {
		return (String)eDynamicGet(PartyPackage.RESOURCE_REFERENCE__LOCATION, PartyPackage.Literals.RESOURCE_REFERENCE__LOCATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLocation(String newLocation) {
		eDynamicSet(PartyPackage.RESOURCE_REFERENCE__LOCATION, PartyPackage.Literals.RESOURCE_REFERENCE__LOCATION, newLocation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PartyPackage.RESOURCE_REFERENCE__LOCATION:
				return getLocation();
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
			case PartyPackage.RESOURCE_REFERENCE__LOCATION:
				setLocation((String)newValue);
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
			case PartyPackage.RESOURCE_REFERENCE__LOCATION:
				setLocation(LOCATION_EDEFAULT);
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
			case PartyPackage.RESOURCE_REFERENCE__LOCATION:
				return LOCATION_EDEFAULT == null ? getLocation() != null : !LOCATION_EDEFAULT.equals(getLocation());
		}
		return super.eIsSet(featureID);
	}

} //ResourceReferenceImpl
