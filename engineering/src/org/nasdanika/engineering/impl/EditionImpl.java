/**
 */
package org.nasdanika.engineering.impl;

import java.util.Collection;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.nasdanika.engineering.Edition;
import org.nasdanika.engineering.EngineeringPackage;
import org.nasdanika.engineering.Feature;
import org.nasdanika.engineering.Offering;
import org.nasdanika.engineering.Persona;
import org.nasdanika.ncore.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Edition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.impl.EditionImpl#getTargetAudiences <em>Target Audiences</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.EditionImpl#getBases <em>Bases</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.EditionImpl#getFeatures <em>Features</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EditionImpl extends ModelElementImpl implements Edition {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EditionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EngineeringPackage.Literals.EDITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Persona> getTargetAudiences() {
		return (EList<Persona>)eDynamicGet(EngineeringPackage.EDITION__TARGET_AUDIENCES, EngineeringPackage.Literals.OFFERING__TARGET_AUDIENCES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Edition> getBases() {
		return (EList<Edition>)eDynamicGet(EngineeringPackage.EDITION__BASES, EngineeringPackage.Literals.EDITION__BASES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Feature> getFeatures() {
		return (EList<Feature>)eDynamicGet(EngineeringPackage.EDITION__FEATURES, EngineeringPackage.Literals.EDITION__FEATURES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EngineeringPackage.EDITION__TARGET_AUDIENCES:
				return getTargetAudiences();
			case EngineeringPackage.EDITION__BASES:
				return getBases();
			case EngineeringPackage.EDITION__FEATURES:
				return getFeatures();
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
			case EngineeringPackage.EDITION__TARGET_AUDIENCES:
				getTargetAudiences().clear();
				getTargetAudiences().addAll((Collection<? extends Persona>)newValue);
				return;
			case EngineeringPackage.EDITION__BASES:
				getBases().clear();
				getBases().addAll((Collection<? extends Edition>)newValue);
				return;
			case EngineeringPackage.EDITION__FEATURES:
				getFeatures().clear();
				getFeatures().addAll((Collection<? extends Feature>)newValue);
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
			case EngineeringPackage.EDITION__TARGET_AUDIENCES:
				getTargetAudiences().clear();
				return;
			case EngineeringPackage.EDITION__BASES:
				getBases().clear();
				return;
			case EngineeringPackage.EDITION__FEATURES:
				getFeatures().clear();
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
			case EngineeringPackage.EDITION__TARGET_AUDIENCES:
				return !getTargetAudiences().isEmpty();
			case EngineeringPackage.EDITION__BASES:
				return !getBases().isEmpty();
			case EngineeringPackage.EDITION__FEATURES:
				return !getFeatures().isEmpty();
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
				case EngineeringPackage.EDITION__TARGET_AUDIENCES: return EngineeringPackage.OFFERING__TARGET_AUDIENCES;
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
				case EngineeringPackage.OFFERING__TARGET_AUDIENCES: return EngineeringPackage.EDITION__TARGET_AUDIENCES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //EditionImpl
