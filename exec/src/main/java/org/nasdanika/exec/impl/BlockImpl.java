/**
 */
package org.nasdanika.exec.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.exec.Block;
import org.nasdanika.exec.ExecPackage;
import org.nasdanika.ncore.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Block</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.exec.impl.BlockImpl#getTry <em>Try</em>}</li>
 *   <li>{@link org.nasdanika.exec.impl.BlockImpl#getCatch <em>Catch</em>}</li>
 *   <li>{@link org.nasdanika.exec.impl.BlockImpl#getFinally <em>Finally</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BlockImpl extends ModelElementImpl implements Block {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BlockImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExecPackage.Literals.BLOCK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getTry() {
		return (EList<EObject>)eDynamicGet(ExecPackage.BLOCK__TRY, ExecPackage.Literals.BLOCK__TRY, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getCatch() {
		return (EList<EObject>)eDynamicGet(ExecPackage.BLOCK__CATCH, ExecPackage.Literals.BLOCK__CATCH, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getFinally() {
		return (EList<EObject>)eDynamicGet(ExecPackage.BLOCK__FINALLY, ExecPackage.Literals.BLOCK__FINALLY, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ExecPackage.BLOCK__TRY:
				return ((InternalEList<?>)getTry()).basicRemove(otherEnd, msgs);
			case ExecPackage.BLOCK__CATCH:
				return ((InternalEList<?>)getCatch()).basicRemove(otherEnd, msgs);
			case ExecPackage.BLOCK__FINALLY:
				return ((InternalEList<?>)getFinally()).basicRemove(otherEnd, msgs);
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
			case ExecPackage.BLOCK__TRY:
				return getTry();
			case ExecPackage.BLOCK__CATCH:
				return getCatch();
			case ExecPackage.BLOCK__FINALLY:
				return getFinally();
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
			case ExecPackage.BLOCK__TRY:
				getTry().clear();
				getTry().addAll((Collection<? extends EObject>)newValue);
				return;
			case ExecPackage.BLOCK__CATCH:
				getCatch().clear();
				getCatch().addAll((Collection<? extends EObject>)newValue);
				return;
			case ExecPackage.BLOCK__FINALLY:
				getFinally().clear();
				getFinally().addAll((Collection<? extends EObject>)newValue);
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
			case ExecPackage.BLOCK__TRY:
				getTry().clear();
				return;
			case ExecPackage.BLOCK__CATCH:
				getCatch().clear();
				return;
			case ExecPackage.BLOCK__FINALLY:
				getFinally().clear();
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
			case ExecPackage.BLOCK__TRY:
				return !getTry().isEmpty();
			case ExecPackage.BLOCK__CATCH:
				return !getCatch().isEmpty();
			case ExecPackage.BLOCK__FINALLY:
				return !getFinally().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //BlockImpl
