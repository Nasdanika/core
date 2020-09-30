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
import org.nasdanika.engineering.NeedCategoryElement;
import org.nasdanika.engineering.Persona;
import org.nasdanika.ncore.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Persona</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.impl.PersonaImpl#getNeeds <em>Needs</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.PersonaImpl#getExtends <em>Extends</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PersonaImpl extends ModelElementImpl implements Persona {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PersonaImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EngineeringPackage.Literals.PERSONA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<NeedCategoryElement> getNeeds() {
		return (EList<NeedCategoryElement>)eDynamicGet(EngineeringPackage.PERSONA__NEEDS, EngineeringPackage.Literals.PERSONA__NEEDS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Persona> getExtends() {
		return (EList<Persona>)eDynamicGet(EngineeringPackage.PERSONA__EXTENDS, EngineeringPackage.Literals.PERSONA__EXTENDS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EngineeringPackage.PERSONA__NEEDS:
				return ((InternalEList<?>)getNeeds()).basicRemove(otherEnd, msgs);
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
			case EngineeringPackage.PERSONA__NEEDS:
				return getNeeds();
			case EngineeringPackage.PERSONA__EXTENDS:
				return getExtends();
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
			case EngineeringPackage.PERSONA__NEEDS:
				getNeeds().clear();
				getNeeds().addAll((Collection<? extends NeedCategoryElement>)newValue);
				return;
			case EngineeringPackage.PERSONA__EXTENDS:
				getExtends().clear();
				getExtends().addAll((Collection<? extends Persona>)newValue);
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
			case EngineeringPackage.PERSONA__NEEDS:
				getNeeds().clear();
				return;
			case EngineeringPackage.PERSONA__EXTENDS:
				getExtends().clear();
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
			case EngineeringPackage.PERSONA__NEEDS:
				return !getNeeds().isEmpty();
			case EngineeringPackage.PERSONA__EXTENDS:
				return !getExtends().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //PersonaImpl
