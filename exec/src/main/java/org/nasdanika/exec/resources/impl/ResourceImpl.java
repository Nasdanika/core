/**
 */
package org.nasdanika.exec.resources.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.exec.resources.ReconcileAction;
import org.nasdanika.exec.resources.Resource;
import org.nasdanika.exec.resources.ResourcesPackage;
import org.nasdanika.ncore.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.exec.resources.impl.ResourceImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.nasdanika.exec.resources.impl.ResourceImpl#getContents <em>Contents</em>}</li>
 *   <li>{@link org.nasdanika.exec.resources.impl.ResourceImpl#getReconcileAction <em>Reconcile Action</em>}</li>
 *   <li>{@link org.nasdanika.exec.resources.impl.ResourceImpl#getMerger <em>Merger</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ResourceImpl extends ModelElementImpl implements Resource {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getReconcileAction() <em>Reconcile Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReconcileAction()
	 * @generated
	 * @ordered
	 */
	protected static final ReconcileAction RECONCILE_ACTION_EDEFAULT = ReconcileAction.OVERWRITE;

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
		return ResourcesPackage.Literals.RESOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return (String)eDynamicGet(ResourcesPackage.RESOURCE__NAME, ResourcesPackage.Literals.RESOURCE__NAME, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		eDynamicSet(ResourcesPackage.RESOURCE__NAME, ResourcesPackage.Literals.RESOURCE__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getContents() {
		return (EList<EObject>)eDynamicGet(ResourcesPackage.RESOURCE__CONTENTS, ResourcesPackage.Literals.RESOURCE__CONTENTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ReconcileAction getReconcileAction() {
		return (ReconcileAction)eDynamicGet(ResourcesPackage.RESOURCE__RECONCILE_ACTION, ResourcesPackage.Literals.RESOURCE__RECONCILE_ACTION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReconcileAction(ReconcileAction newReconcileAction) {
		eDynamicSet(ResourcesPackage.RESOURCE__RECONCILE_ACTION, ResourcesPackage.Literals.RESOURCE__RECONCILE_ACTION, newReconcileAction);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject getMerger() {
		return (EObject)eDynamicGet(ResourcesPackage.RESOURCE__MERGER, ResourcesPackage.Literals.RESOURCE__MERGER, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMerger(EObject newMerger, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newMerger, ResourcesPackage.RESOURCE__MERGER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMerger(EObject newMerger) {
		eDynamicSet(ResourcesPackage.RESOURCE__MERGER, ResourcesPackage.Literals.RESOURCE__MERGER, newMerger);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ResourcesPackage.RESOURCE__CONTENTS:
				return ((InternalEList<?>)getContents()).basicRemove(otherEnd, msgs);
			case ResourcesPackage.RESOURCE__MERGER:
				return basicSetMerger(null, msgs);
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
			case ResourcesPackage.RESOURCE__NAME:
				return getName();
			case ResourcesPackage.RESOURCE__CONTENTS:
				return getContents();
			case ResourcesPackage.RESOURCE__RECONCILE_ACTION:
				return getReconcileAction();
			case ResourcesPackage.RESOURCE__MERGER:
				return getMerger();
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
			case ResourcesPackage.RESOURCE__NAME:
				setName((String)newValue);
				return;
			case ResourcesPackage.RESOURCE__CONTENTS:
				getContents().clear();
				getContents().addAll((Collection<? extends EObject>)newValue);
				return;
			case ResourcesPackage.RESOURCE__RECONCILE_ACTION:
				setReconcileAction((ReconcileAction)newValue);
				return;
			case ResourcesPackage.RESOURCE__MERGER:
				setMerger((EObject)newValue);
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
			case ResourcesPackage.RESOURCE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ResourcesPackage.RESOURCE__CONTENTS:
				getContents().clear();
				return;
			case ResourcesPackage.RESOURCE__RECONCILE_ACTION:
				setReconcileAction(RECONCILE_ACTION_EDEFAULT);
				return;
			case ResourcesPackage.RESOURCE__MERGER:
				setMerger((EObject)null);
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
			case ResourcesPackage.RESOURCE__NAME:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
			case ResourcesPackage.RESOURCE__CONTENTS:
				return !getContents().isEmpty();
			case ResourcesPackage.RESOURCE__RECONCILE_ACTION:
				return getReconcileAction() != RECONCILE_ACTION_EDEFAULT;
			case ResourcesPackage.RESOURCE__MERGER:
				return getMerger() != null;
		}
		return super.eIsSet(featureID);
	}

} //ResourceImpl
