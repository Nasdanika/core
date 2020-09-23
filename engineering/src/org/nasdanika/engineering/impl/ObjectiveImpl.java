/**
 */
package org.nasdanika.engineering.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.engineering.EngineeringPackage;
import org.nasdanika.engineering.Increment;
import org.nasdanika.engineering.KeyResult;
import org.nasdanika.engineering.Objective;
import org.nasdanika.ncore.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Objective</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.impl.ObjectiveImpl#getIncrement <em>Increment</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.ObjectiveImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.ObjectiveImpl#getKeyResults <em>Key Results</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.ObjectiveImpl#getParent <em>Parent</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ObjectiveImpl extends ModelElementImpl implements Objective {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ObjectiveImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EngineeringPackage.Literals.OBJECTIVE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Increment getIncrement() {
		return (Increment)eDynamicGet(EngineeringPackage.OBJECTIVE__INCREMENT, EngineeringPackage.Literals.OBJECTIVE__INCREMENT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Increment basicGetIncrement() {
		return (Increment)eDynamicGet(EngineeringPackage.OBJECTIVE__INCREMENT, EngineeringPackage.Literals.OBJECTIVE__INCREMENT, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIncrement(Increment newIncrement) {
		eDynamicSet(EngineeringPackage.OBJECTIVE__INCREMENT, EngineeringPackage.Literals.OBJECTIVE__INCREMENT, newIncrement);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Objective> getChildren() {
		return (EList<Objective>)eDynamicGet(EngineeringPackage.OBJECTIVE__CHILDREN, EngineeringPackage.Literals.OBJECTIVE__CHILDREN, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<KeyResult> getKeyResults() {
		return (EList<KeyResult>)eDynamicGet(EngineeringPackage.OBJECTIVE__KEY_RESULTS, EngineeringPackage.Literals.OBJECTIVE__KEY_RESULTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Objective getParent() {
		return (Objective)eDynamicGet(EngineeringPackage.OBJECTIVE__PARENT, EngineeringPackage.Literals.OBJECTIVE__PARENT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Objective basicGetParent() {
		return (Objective)eDynamicGet(EngineeringPackage.OBJECTIVE__PARENT, EngineeringPackage.Literals.OBJECTIVE__PARENT, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setParent(Objective newParent) {
		eDynamicSet(EngineeringPackage.OBJECTIVE__PARENT, EngineeringPackage.Literals.OBJECTIVE__PARENT, newParent);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EngineeringPackage.OBJECTIVE__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case EngineeringPackage.OBJECTIVE__KEY_RESULTS:
				return ((InternalEList<?>)getKeyResults()).basicRemove(otherEnd, msgs);
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
			case EngineeringPackage.OBJECTIVE__INCREMENT:
				if (resolve) return getIncrement();
				return basicGetIncrement();
			case EngineeringPackage.OBJECTIVE__CHILDREN:
				return getChildren();
			case EngineeringPackage.OBJECTIVE__KEY_RESULTS:
				return getKeyResults();
			case EngineeringPackage.OBJECTIVE__PARENT:
				if (resolve) return getParent();
				return basicGetParent();
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
			case EngineeringPackage.OBJECTIVE__INCREMENT:
				setIncrement((Increment)newValue);
				return;
			case EngineeringPackage.OBJECTIVE__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends Objective>)newValue);
				return;
			case EngineeringPackage.OBJECTIVE__KEY_RESULTS:
				getKeyResults().clear();
				getKeyResults().addAll((Collection<? extends KeyResult>)newValue);
				return;
			case EngineeringPackage.OBJECTIVE__PARENT:
				setParent((Objective)newValue);
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
			case EngineeringPackage.OBJECTIVE__INCREMENT:
				setIncrement((Increment)null);
				return;
			case EngineeringPackage.OBJECTIVE__CHILDREN:
				getChildren().clear();
				return;
			case EngineeringPackage.OBJECTIVE__KEY_RESULTS:
				getKeyResults().clear();
				return;
			case EngineeringPackage.OBJECTIVE__PARENT:
				setParent((Objective)null);
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
			case EngineeringPackage.OBJECTIVE__INCREMENT:
				return basicGetIncrement() != null;
			case EngineeringPackage.OBJECTIVE__CHILDREN:
				return !getChildren().isEmpty();
			case EngineeringPackage.OBJECTIVE__KEY_RESULTS:
				return !getKeyResults().isEmpty();
			case EngineeringPackage.OBJECTIVE__PARENT:
				return basicGetParent() != null;
		}
		return super.eIsSet(featureID);
	}

} //ObjectiveImpl
