/**
 */
package org.nasdanika.ncore.impl;

import java.lang.Object;

import org.eclipse.emf.ecore.EClass;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.Value;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.impl.ValueImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.ValueImpl#isInterpolate <em>Interpolate</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ValueImpl extends SupplierImpl implements Value {
	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isInterpolate() <em>Interpolate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInterpolate()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INTERPOLATE_EDEFAULT = true;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NcorePackage.Literals.VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getValue() {
		return (String)eDynamicGet(NcorePackage.VALUE__VALUE, NcorePackage.Literals.VALUE__VALUE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setValue(String newValue) {
		eDynamicSet(NcorePackage.VALUE__VALUE, NcorePackage.Literals.VALUE__VALUE, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isInterpolate() {
		return (Boolean)eDynamicGet(NcorePackage.VALUE__INTERPOLATE, NcorePackage.Literals.VALUE__INTERPOLATE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInterpolate(boolean newInterpolate) {
		eDynamicSet(NcorePackage.VALUE__INTERPOLATE, NcorePackage.Literals.VALUE__INTERPOLATE, newInterpolate);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NcorePackage.VALUE__VALUE:
				return getValue();
			case NcorePackage.VALUE__INTERPOLATE:
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
			case NcorePackage.VALUE__VALUE:
				setValue((String)newValue);
				return;
			case NcorePackage.VALUE__INTERPOLATE:
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
			case NcorePackage.VALUE__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case NcorePackage.VALUE__INTERPOLATE:
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
			case NcorePackage.VALUE__VALUE:
				return VALUE_EDEFAULT == null ? getValue() != null : !VALUE_EDEFAULT.equals(getValue());
			case NcorePackage.VALUE__INTERPOLATE:
				return isInterpolate() != INTERPOLATE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}
	
	// TODO - proper implementation.
	@Override
	public Supplier<Object> create(Context context) throws Exception {
		return new Supplier<Object>() {

			@Override
			public Object execute(ProgressMonitor progressMonitor) throws Exception {
				// TODO Auto-generated method stub
				return getValue();
			}

			@Override
			public double size() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String name() {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
	}

} //ValueImpl
