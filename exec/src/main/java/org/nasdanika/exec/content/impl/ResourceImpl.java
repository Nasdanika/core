/**
 */
package org.nasdanika.exec.content.impl;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.exec.content.ContentPackage;
import org.nasdanika.exec.content.Resource;
import org.nasdanika.ncore.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.exec.content.impl.ResourceImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link org.nasdanika.exec.content.impl.ResourceImpl#isInterpolate <em>Interpolate</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ResourceImpl extends ModelElementImpl implements Resource {
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
	 * The default value of the '{@link #isInterpolate() <em>Interpolate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInterpolate()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INTERPOLATE_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ContentPackage.Literals.RESOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLocation() {
		return (String)eDynamicGet(ContentPackage.RESOURCE__LOCATION, ContentPackage.Literals.RESOURCE__LOCATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLocation(String newLocation) {
		eDynamicSet(ContentPackage.RESOURCE__LOCATION, ContentPackage.Literals.RESOURCE__LOCATION, newLocation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isInterpolate() {
		return (Boolean)eDynamicGet(ContentPackage.RESOURCE__INTERPOLATE, ContentPackage.Literals.RESOURCE__INTERPOLATE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInterpolate(boolean newInterpolate) {
		eDynamicSet(ContentPackage.RESOURCE__INTERPOLATE, ContentPackage.Literals.RESOURCE__INTERPOLATE, newInterpolate);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ContentPackage.RESOURCE__LOCATION:
				return getLocation();
			case ContentPackage.RESOURCE__INTERPOLATE:
				return isInterpolate();
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
			case ContentPackage.RESOURCE__LOCATION:
				setLocation((String)newValue);
				return;
			case ContentPackage.RESOURCE__INTERPOLATE:
				setInterpolate((Boolean)newValue);
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
			case ContentPackage.RESOURCE__LOCATION:
				setLocation(LOCATION_EDEFAULT);
				return;
			case ContentPackage.RESOURCE__INTERPOLATE:
				setInterpolate(INTERPOLATE_EDEFAULT);
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
			case ContentPackage.RESOURCE__LOCATION:
				return LOCATION_EDEFAULT == null ? getLocation() != null : !LOCATION_EDEFAULT.equals(getLocation());
			case ContentPackage.RESOURCE__INTERPOLATE:
				return isInterpolate() != INTERPOLATE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //ResourceImpl
