/**
 */
package org.nasdanika.engineering.impl;

import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.engineering.EngineeringPackage;
import org.nasdanika.engineering.Increment;

import org.nasdanika.ncore.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Increment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.impl.IncrementImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.IncrementImpl#getStart <em>Start</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.IncrementImpl#getEnd <em>End</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IncrementImpl extends ModelElementImpl implements Increment {
	/**
	 * The default value of the '{@link #getStart() <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStart()
	 * @generated
	 * @ordered
	 */
	protected static final Date START_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getEnd() <em>End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnd()
	 * @generated
	 * @ordered
	 */
	protected static final Date END_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IncrementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EngineeringPackage.Literals.INCREMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Increment> getChildren() {
		return (EList<Increment>)eDynamicGet(EngineeringPackage.INCREMENT__CHILDREN, EngineeringPackage.Literals.INCREMENT__CHILDREN, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getStart() {
		return (Date)eDynamicGet(EngineeringPackage.INCREMENT__START, EngineeringPackage.Literals.INCREMENT__START, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStart(Date newStart) {
		eDynamicSet(EngineeringPackage.INCREMENT__START, EngineeringPackage.Literals.INCREMENT__START, newStart);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getEnd() {
		return (Date)eDynamicGet(EngineeringPackage.INCREMENT__END, EngineeringPackage.Literals.INCREMENT__END, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEnd(Date newEnd) {
		eDynamicSet(EngineeringPackage.INCREMENT__END, EngineeringPackage.Literals.INCREMENT__END, newEnd);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EngineeringPackage.INCREMENT__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
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
			case EngineeringPackage.INCREMENT__CHILDREN:
				return getChildren();
			case EngineeringPackage.INCREMENT__START:
				return getStart();
			case EngineeringPackage.INCREMENT__END:
				return getEnd();
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
			case EngineeringPackage.INCREMENT__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends Increment>)newValue);
				return;
			case EngineeringPackage.INCREMENT__START:
				setStart((Date)newValue);
				return;
			case EngineeringPackage.INCREMENT__END:
				setEnd((Date)newValue);
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
			case EngineeringPackage.INCREMENT__CHILDREN:
				getChildren().clear();
				return;
			case EngineeringPackage.INCREMENT__START:
				setStart(START_EDEFAULT);
				return;
			case EngineeringPackage.INCREMENT__END:
				setEnd(END_EDEFAULT);
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
			case EngineeringPackage.INCREMENT__CHILDREN:
				return !getChildren().isEmpty();
			case EngineeringPackage.INCREMENT__START:
				return START_EDEFAULT == null ? getStart() != null : !START_EDEFAULT.equals(getStart());
			case EngineeringPackage.INCREMENT__END:
				return END_EDEFAULT == null ? getEnd() != null : !END_EDEFAULT.equals(getEnd());
		}
		return super.eIsSet(featureID);
	}

} //IncrementImpl
