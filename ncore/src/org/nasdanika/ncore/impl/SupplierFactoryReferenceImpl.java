/**
 */
package org.nasdanika.ncore.impl;

import java.lang.Object;

import org.eclipse.emf.ecore.EClass;
import org.nasdanika.common.Context;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;

import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.SupplierFactoryReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Supplier Factory Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.impl.SupplierFactoryReferenceImpl#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SupplierFactoryReferenceImpl extends ModelElementImpl implements SupplierFactoryReference {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SupplierFactoryReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NcorePackage.Literals.SUPPLIER_FACTORY_REFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public SupplierFactory<Object> getTarget() {
		return (SupplierFactory<Object>)eDynamicGet(NcorePackage.SUPPLIER_FACTORY_REFERENCE__TARGET, NcorePackage.Literals.SUPPLIER_FACTORY_REFERENCE__TARGET, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SupplierFactory<Object> basicGetTarget() {
		return (SupplierFactory<Object>)eDynamicGet(NcorePackage.SUPPLIER_FACTORY_REFERENCE__TARGET, NcorePackage.Literals.SUPPLIER_FACTORY_REFERENCE__TARGET, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTarget(SupplierFactory<Object> newTarget) {
		eDynamicSet(NcorePackage.SUPPLIER_FACTORY_REFERENCE__TARGET, NcorePackage.Literals.SUPPLIER_FACTORY_REFERENCE__TARGET, newTarget);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NcorePackage.SUPPLIER_FACTORY_REFERENCE__TARGET:
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
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case NcorePackage.SUPPLIER_FACTORY_REFERENCE__TARGET:
				setTarget((SupplierFactory<Object>)newValue);
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
			case NcorePackage.SUPPLIER_FACTORY_REFERENCE__TARGET:
				setTarget((SupplierFactory<Object>)null);
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
			case NcorePackage.SUPPLIER_FACTORY_REFERENCE__TARGET:
				return basicGetTarget() != null;
		}
		return super.eIsSet(featureID);
	}

	@Override
	public Supplier<Object> create(Context context) throws Exception {
		return getTarget().create(context);
	}

} //SupplierFactoryReferenceImpl
