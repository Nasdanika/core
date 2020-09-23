/**
 */
package org.nasdanika.engineering.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.engineering.Edition;
import org.nasdanika.engineering.EngineeringPackage;
import org.nasdanika.engineering.FeatureCategoryElement;
import org.nasdanika.engineering.Offering;
import org.nasdanika.engineering.Persona;
import org.nasdanika.engineering.Product;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Product</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.impl.ProductImpl#getTargetAudiences <em>Target Audiences</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.ProductImpl#getEditions <em>Editions</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.ProductImpl#getFeatures <em>Features</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProductImpl extends ComponentImpl implements Product {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProductImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EngineeringPackage.Literals.PRODUCT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Persona> getTargetAudiences() {
		return (EList<Persona>)eDynamicGet(EngineeringPackage.PRODUCT__TARGET_AUDIENCES, EngineeringPackage.Literals.OFFERING__TARGET_AUDIENCES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Edition> getEditions() {
		return (EList<Edition>)eDynamicGet(EngineeringPackage.PRODUCT__EDITIONS, EngineeringPackage.Literals.PRODUCT__EDITIONS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FeatureCategoryElement getFeatures() {
		return (FeatureCategoryElement)eDynamicGet(EngineeringPackage.PRODUCT__FEATURES, EngineeringPackage.Literals.PRODUCT__FEATURES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureCategoryElement basicGetFeatures() {
		return (FeatureCategoryElement)eDynamicGet(EngineeringPackage.PRODUCT__FEATURES, EngineeringPackage.Literals.PRODUCT__FEATURES, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFeatures(FeatureCategoryElement newFeatures) {
		eDynamicSet(EngineeringPackage.PRODUCT__FEATURES, EngineeringPackage.Literals.PRODUCT__FEATURES, newFeatures);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EngineeringPackage.PRODUCT__EDITIONS:
				return ((InternalEList<?>)getEditions()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EngineeringPackage.PRODUCT__TARGET_AUDIENCES:
				return getTargetAudiences();
			case EngineeringPackage.PRODUCT__EDITIONS:
				return getEditions();
			case EngineeringPackage.PRODUCT__FEATURES:
				if (resolve) return getFeatures();
				return basicGetFeatures();
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
			case EngineeringPackage.PRODUCT__TARGET_AUDIENCES:
				getTargetAudiences().clear();
				getTargetAudiences().addAll((Collection<? extends Persona>)newValue);
				return;
			case EngineeringPackage.PRODUCT__EDITIONS:
				getEditions().clear();
				getEditions().addAll((Collection<? extends Edition>)newValue);
				return;
			case EngineeringPackage.PRODUCT__FEATURES:
				setFeatures((FeatureCategoryElement)newValue);
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
			case EngineeringPackage.PRODUCT__TARGET_AUDIENCES:
				getTargetAudiences().clear();
				return;
			case EngineeringPackage.PRODUCT__EDITIONS:
				getEditions().clear();
				return;
			case EngineeringPackage.PRODUCT__FEATURES:
				setFeatures((FeatureCategoryElement)null);
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
			case EngineeringPackage.PRODUCT__TARGET_AUDIENCES:
				return !getTargetAudiences().isEmpty();
			case EngineeringPackage.PRODUCT__EDITIONS:
				return !getEditions().isEmpty();
			case EngineeringPackage.PRODUCT__FEATURES:
				return basicGetFeatures() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Offering.class) {
			switch (derivedFeatureID) {
				case EngineeringPackage.PRODUCT__TARGET_AUDIENCES: return EngineeringPackage.OFFERING__TARGET_AUDIENCES;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == Offering.class) {
			switch (baseFeatureID) {
				case EngineeringPackage.OFFERING__TARGET_AUDIENCES: return EngineeringPackage.PRODUCT__TARGET_AUDIENCES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //ProductImpl
