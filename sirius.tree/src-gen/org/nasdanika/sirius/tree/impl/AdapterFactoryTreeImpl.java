/**
 */
package org.nasdanika.sirius.tree.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
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
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected EObject target;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected AdapterFactoryTreeDescription description;

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
	public EObject getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject) target;
			target = eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TreePackage.ADAPTER_FACTORY_TREE__TARGET,
							oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject basicGetTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(EObject newTarget) {
		EObject oldTarget = target;
		target = newTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TreePackage.ADAPTER_FACTORY_TREE__TARGET, oldTarget,
					target));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdapterFactoryTreeDescription getDescription() {
		if (description != null && description.eIsProxy()) {
			InternalEObject oldDescription = (InternalEObject) description;
			description = (AdapterFactoryTreeDescription) eResolveProxy(oldDescription);
			if (description != oldDescription) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							TreePackage.ADAPTER_FACTORY_TREE__DESCRIPTION, oldDescription, description));
			}
		}
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdapterFactoryTreeDescription basicGetDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(AdapterFactoryTreeDescription newDescription) {
		AdapterFactoryTreeDescription oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TreePackage.ADAPTER_FACTORY_TREE__DESCRIPTION,
					oldDescription, description));
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
			return target != null;
		case TreePackage.ADAPTER_FACTORY_TREE__DESCRIPTION:
			return description != null;
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
