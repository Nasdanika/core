/**
 */
package org.nasdanika.ncore.impl;

import java.lang.Long;

import java.util.Date;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.ncore.File;
import org.nasdanika.ncore.NcorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>File</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.impl.FileImpl#getLength <em>Length</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.FileImpl#getLastModified <em>Last Modified</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FileImpl extends TreeItemImpl implements File {
	/**
	 * The default value of the '{@link #getLength() <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLength()
	 * @generated
	 * @ordered
	 */
	protected static final long LENGTH_EDEFAULT = 0L;

	/**
	 * The default value of the '{@link #getLastModified() <em>Last Modified</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastModified()
	 * @generated
	 * @ordered
	 */
	protected static final Date LAST_MODIFIED_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FileImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NcorePackage.Literals.FILE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public long getLength() {
		return (Long)eDynamicGet(NcorePackage.FILE__LENGTH, NcorePackage.Literals.FILE__LENGTH, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLength(long newLength) {
		eDynamicSet(NcorePackage.FILE__LENGTH, NcorePackage.Literals.FILE__LENGTH, newLength);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getLastModified() {
		return (Date)eDynamicGet(NcorePackage.FILE__LAST_MODIFIED, NcorePackage.Literals.FILE__LAST_MODIFIED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLastModified(Date newLastModified) {
		eDynamicSet(NcorePackage.FILE__LAST_MODIFIED, NcorePackage.Literals.FILE__LAST_MODIFIED, newLastModified);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NcorePackage.FILE__LENGTH:
				return getLength();
			case NcorePackage.FILE__LAST_MODIFIED:
				return getLastModified();
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
			case NcorePackage.FILE__LENGTH:
				setLength((Long)newValue);
				return;
			case NcorePackage.FILE__LAST_MODIFIED:
				setLastModified((Date)newValue);
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
			case NcorePackage.FILE__LENGTH:
				setLength(LENGTH_EDEFAULT);
				return;
			case NcorePackage.FILE__LAST_MODIFIED:
				setLastModified(LAST_MODIFIED_EDEFAULT);
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
			case NcorePackage.FILE__LENGTH:
				return getLength() != LENGTH_EDEFAULT;
			case NcorePackage.FILE__LAST_MODIFIED:
				return LAST_MODIFIED_EDEFAULT == null ? getLastModified() != null : !LAST_MODIFIED_EDEFAULT.equals(getLastModified());
		}
		return super.eIsSet(featureID);
	}

} //FileImpl
