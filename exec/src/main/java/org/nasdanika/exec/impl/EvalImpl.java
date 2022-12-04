/**
 */
package org.nasdanika.exec.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.exec.Eval;
import org.nasdanika.exec.ExecPackage;
import org.nasdanika.ncore.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Eval</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.exec.impl.EvalImpl#getScript <em>Script</em>}</li>
 *   <li>{@link org.nasdanika.exec.impl.EvalImpl#getBindings <em>Bindings</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EvalImpl extends ModelElementImpl implements Eval {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EvalImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExecPackage.Literals.EVAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject getScript() {
		return (EObject)eDynamicGet(ExecPackage.EVAL__SCRIPT, ExecPackage.Literals.EVAL__SCRIPT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScript(EObject newScript, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newScript, ExecPackage.EVAL__SCRIPT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setScript(EObject newScript) {
		eDynamicSet(ExecPackage.EVAL__SCRIPT, ExecPackage.Literals.EVAL__SCRIPT, newScript);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, EObject> getBindings() {
		return (EMap<String, EObject>)eDynamicGet(ExecPackage.EVAL__BINDINGS, ExecPackage.Literals.EVAL__BINDINGS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ExecPackage.EVAL__SCRIPT:
				return basicSetScript(null, msgs);
			case ExecPackage.EVAL__BINDINGS:
				return ((InternalEList<?>)getBindings()).basicRemove(otherEnd, msgs);
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
			case ExecPackage.EVAL__SCRIPT:
				return getScript();
			case ExecPackage.EVAL__BINDINGS:
				if (coreType) return getBindings();
				else return getBindings().map();
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
			case ExecPackage.EVAL__SCRIPT:
				setScript((EObject)newValue);
				return;
			case ExecPackage.EVAL__BINDINGS:
				((EStructuralFeature.Setting)getBindings()).set(newValue);
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
			case ExecPackage.EVAL__SCRIPT:
				setScript((EObject)null);
				return;
			case ExecPackage.EVAL__BINDINGS:
				getBindings().clear();
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
			case ExecPackage.EVAL__SCRIPT:
				return getScript() != null;
			case ExecPackage.EVAL__BINDINGS:
				return !getBindings().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //EvalImpl
