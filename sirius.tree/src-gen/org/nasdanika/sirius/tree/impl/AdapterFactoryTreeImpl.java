/**
 */
package org.nasdanika.sirius.tree.impl;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.impl.DRepresentationImpl;

import org.nasdanika.sirius.tree.AdapterFactoryTree;
import org.nasdanika.sirius.tree.AdapterFactoryTreeDescription;
import org.nasdanika.sirius.tree.TreePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Adapter Factory Tree</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.sirius.tree.impl.AdapterFactoryTreeImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.nasdanika.sirius.tree.impl.AdapterFactoryTreeImpl#getDescription <em>Description</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AdapterFactoryTreeImpl extends DRepresentationImpl implements AdapterFactoryTree {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AdapterFactoryTreeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TreePackage.Literals.ADAPTER_FACTORY_TREE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int ESTATIC_FEATURE_COUNT = 8;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return ESTATIC_FEATURE_COUNT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getTarget() {
		return (EObject) eDynamicGet(TreePackage.ADAPTER_FACTORY_TREE__TARGET - ESTATIC_FEATURE_COUNT,
				ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject basicGetTarget() {
		return (EObject) eDynamicGet(TreePackage.ADAPTER_FACTORY_TREE__TARGET - ESTATIC_FEATURE_COUNT,
				ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(EObject newTarget) {
		eDynamicSet(TreePackage.ADAPTER_FACTORY_TREE__TARGET - ESTATIC_FEATURE_COUNT,
				ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET, newTarget);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdapterFactoryTreeDescription getDescription() {
		return (AdapterFactoryTreeDescription) eDynamicGet(
				TreePackage.ADAPTER_FACTORY_TREE__DESCRIPTION - ESTATIC_FEATURE_COUNT,
				TreePackage.Literals.ADAPTER_FACTORY_TREE__DESCRIPTION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdapterFactoryTreeDescription basicGetDescription() {
		return (AdapterFactoryTreeDescription) eDynamicGet(
				TreePackage.ADAPTER_FACTORY_TREE__DESCRIPTION - ESTATIC_FEATURE_COUNT,
				TreePackage.Literals.ADAPTER_FACTORY_TREE__DESCRIPTION, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(AdapterFactoryTreeDescription newDescription) {
		eDynamicSet(TreePackage.ADAPTER_FACTORY_TREE__DESCRIPTION - ESTATIC_FEATURE_COUNT,
				TreePackage.Literals.ADAPTER_FACTORY_TREE__DESCRIPTION, newDescription);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case TreePackage.ADAPTER_FACTORY_TREE__TARGET:
			if (resolve)
				return getTarget();
			return basicGetTarget();
		case TreePackage.ADAPTER_FACTORY_TREE__DESCRIPTION:
			if (resolve)
				return getDescription();
			return basicGetDescription();
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
		case TreePackage.ADAPTER_FACTORY_TREE__TARGET:
			setTarget((EObject) newValue);
			return;
		case TreePackage.ADAPTER_FACTORY_TREE__DESCRIPTION:
			setDescription((AdapterFactoryTreeDescription) newValue);
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
		case TreePackage.ADAPTER_FACTORY_TREE__TARGET:
			setTarget((EObject) null);
			return;
		case TreePackage.ADAPTER_FACTORY_TREE__DESCRIPTION:
			setDescription((AdapterFactoryTreeDescription) null);
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
		case TreePackage.ADAPTER_FACTORY_TREE__TARGET:
			return basicGetTarget() != null;
		case TreePackage.ADAPTER_FACTORY_TREE__DESCRIPTION:
			return basicGetDescription() != null;
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
		if (baseClass == DSemanticDecorator.class) {
			switch (derivedFeatureID) {
			case TreePackage.ADAPTER_FACTORY_TREE__TARGET:
				return ViewpointPackage.DSEMANTIC_DECORATOR__TARGET;
			default:
				return -1;
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
		if (baseClass == DSemanticDecorator.class) {
			switch (baseFeatureID) {
			case ViewpointPackage.DSEMANTIC_DECORATOR__TARGET:
				return TreePackage.ADAPTER_FACTORY_TREE__TARGET;
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	@Override
	public EList<DRepresentationElement> getRepresentationElements() {
		return ECollections.emptyEList();
	}

} //AdapterFactoryTreeImpl
