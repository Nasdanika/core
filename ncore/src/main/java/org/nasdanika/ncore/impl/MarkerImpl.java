/**
 */
package org.nasdanika.ncore.impl;

import java.lang.Integer;
import java.lang.String;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.nasdanika.ncore.Marker;
import org.nasdanika.ncore.NcorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Marker</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.impl.MarkerImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.MarkerImpl#getLine <em>Line</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.MarkerImpl#getColumn <em>Column</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.MarkerImpl#getOrigin <em>Origin</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MarkerImpl extends MinimalEObjectImpl.Container implements Marker {
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
	 * The default value of the '{@link #getLine() <em>Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLine()
	 * @generated
	 * @ordered
	 */
	protected static final int LINE_EDEFAULT = 0;

	/**
	 * The default value of the '{@link #getColumn() <em>Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumn()
	 * @generated
	 * @ordered
	 */
	protected static final int COLUMN_EDEFAULT = 0;

	/**
	 * The default value of the '{@link #getOrigin() <em>Origin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrigin()
	 * @generated
	 * @ordered
	 */
	protected static final String ORIGIN_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MarkerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NcorePackage.Literals.MARKER;
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
	public String getLocation() {
		return (String)eDynamicGet(NcorePackage.MARKER__LOCATION, NcorePackage.Literals.MARKER__LOCATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLocation(String newLocation) {
		eDynamicSet(NcorePackage.MARKER__LOCATION, NcorePackage.Literals.MARKER__LOCATION, newLocation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getLine() {
		return (Integer)eDynamicGet(NcorePackage.MARKER__LINE, NcorePackage.Literals.MARKER__LINE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLine(int newLine) {
		eDynamicSet(NcorePackage.MARKER__LINE, NcorePackage.Literals.MARKER__LINE, newLine);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getColumn() {
		return (Integer)eDynamicGet(NcorePackage.MARKER__COLUMN, NcorePackage.Literals.MARKER__COLUMN, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setColumn(int newColumn) {
		eDynamicSet(NcorePackage.MARKER__COLUMN, NcorePackage.Literals.MARKER__COLUMN, newColumn);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getOrigin() {
		return (String)eDynamicGet(NcorePackage.MARKER__ORIGIN, NcorePackage.Literals.MARKER__ORIGIN, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOrigin(String newOrigin) {
		eDynamicSet(NcorePackage.MARKER__ORIGIN, NcorePackage.Literals.MARKER__ORIGIN, newOrigin);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NcorePackage.MARKER__LOCATION:
				return getLocation();
			case NcorePackage.MARKER__LINE:
				return getLine();
			case NcorePackage.MARKER__COLUMN:
				return getColumn();
			case NcorePackage.MARKER__ORIGIN:
				return getOrigin();
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
			case NcorePackage.MARKER__LOCATION:
				setLocation((String)newValue);
				return;
			case NcorePackage.MARKER__LINE:
				setLine((Integer)newValue);
				return;
			case NcorePackage.MARKER__COLUMN:
				setColumn((Integer)newValue);
				return;
			case NcorePackage.MARKER__ORIGIN:
				setOrigin((String)newValue);
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
			case NcorePackage.MARKER__LOCATION:
				setLocation(LOCATION_EDEFAULT);
				return;
			case NcorePackage.MARKER__LINE:
				setLine(LINE_EDEFAULT);
				return;
			case NcorePackage.MARKER__COLUMN:
				setColumn(COLUMN_EDEFAULT);
				return;
			case NcorePackage.MARKER__ORIGIN:
				setOrigin(ORIGIN_EDEFAULT);
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
			case NcorePackage.MARKER__LOCATION:
				return LOCATION_EDEFAULT == null ? getLocation() != null : !LOCATION_EDEFAULT.equals(getLocation());
			case NcorePackage.MARKER__LINE:
				return getLine() != LINE_EDEFAULT;
			case NcorePackage.MARKER__COLUMN:
				return getColumn() != COLUMN_EDEFAULT;
			case NcorePackage.MARKER__ORIGIN:
				return ORIGIN_EDEFAULT == null ? getOrigin() != null : !ORIGIN_EDEFAULT.equals(getOrigin());
		}
		return super.eIsSet(featureID);
	}

} //MarkerImpl
