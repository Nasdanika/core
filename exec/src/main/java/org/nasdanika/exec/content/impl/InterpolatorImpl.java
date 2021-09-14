/**
 */
package org.nasdanika.exec.content.impl;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.exec.content.ContentPackage;
import org.nasdanika.exec.content.Interpolator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Interpolator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.exec.content.impl.InterpolatorImpl#isProcessIncludes <em>Process Includes</em>}</li>
 *   <li>{@link org.nasdanika.exec.content.impl.InterpolatorImpl#getBase <em>Base</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InterpolatorImpl extends FilterImpl implements Interpolator {
	/**
	 * The default value of the '{@link #isProcessIncludes() <em>Process Includes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isProcessIncludes()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PROCESS_INCLUDES_EDEFAULT = false;
	/**
	 * The default value of the '{@link #getBase() <em>Base</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBase()
	 * @generated
	 * @ordered
	 */
	protected static final String BASE_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InterpolatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ContentPackage.Literals.INTERPOLATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isProcessIncludes() {
		return (Boolean)eDynamicGet(ContentPackage.INTERPOLATOR__PROCESS_INCLUDES, ContentPackage.Literals.INTERPOLATOR__PROCESS_INCLUDES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProcessIncludes(boolean newProcessIncludes) {
		eDynamicSet(ContentPackage.INTERPOLATOR__PROCESS_INCLUDES, ContentPackage.Literals.INTERPOLATOR__PROCESS_INCLUDES, newProcessIncludes);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBase() {
		return (String)eDynamicGet(ContentPackage.INTERPOLATOR__BASE, ContentPackage.Literals.INTERPOLATOR__BASE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBase(String newBase) {
		eDynamicSet(ContentPackage.INTERPOLATOR__BASE, ContentPackage.Literals.INTERPOLATOR__BASE, newBase);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ContentPackage.INTERPOLATOR__PROCESS_INCLUDES:
				return isProcessIncludes();
			case ContentPackage.INTERPOLATOR__BASE:
				return getBase();
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
			case ContentPackage.INTERPOLATOR__PROCESS_INCLUDES:
				setProcessIncludes((Boolean)newValue);
				return;
			case ContentPackage.INTERPOLATOR__BASE:
				setBase((String)newValue);
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
			case ContentPackage.INTERPOLATOR__PROCESS_INCLUDES:
				setProcessIncludes(PROCESS_INCLUDES_EDEFAULT);
				return;
			case ContentPackage.INTERPOLATOR__BASE:
				setBase(BASE_EDEFAULT);
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
			case ContentPackage.INTERPOLATOR__PROCESS_INCLUDES:
				return isProcessIncludes() != PROCESS_INCLUDES_EDEFAULT;
			case ContentPackage.INTERPOLATOR__BASE:
				return BASE_EDEFAULT == null ? getBase() != null : !BASE_EDEFAULT.equals(getBase());
		}
		return super.eIsSet(featureID);
	}

} //InterpolatorImpl
