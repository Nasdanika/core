/**
 */
package org.nasdanika.party.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.ncore.impl.EntityImpl;
import org.nasdanika.party.Member;
import org.nasdanika.party.Party;
import org.nasdanika.party.PartyPackage;
import org.nasdanika.party.ResourceCategoryElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Member</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.party.impl.MemberImpl#getParty <em>Party</em>}</li>
 *   <li>{@link org.nasdanika.party.impl.MemberImpl#getResources <em>Resources</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MemberImpl extends EntityImpl implements Member {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MemberImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PartyPackage.Literals.MEMBER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Party getParty() {
		return (Party)eDynamicGet(PartyPackage.MEMBER__PARTY, PartyPackage.Literals.MEMBER__PARTY, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Party basicGetParty() {
		return (Party)eDynamicGet(PartyPackage.MEMBER__PARTY, PartyPackage.Literals.MEMBER__PARTY, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setParty(Party newParty) {
		eDynamicSet(PartyPackage.MEMBER__PARTY, PartyPackage.Literals.MEMBER__PARTY, newParty);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<ResourceCategoryElement> getResources() {
		return (EList<ResourceCategoryElement>)eDynamicGet(PartyPackage.MEMBER__RESOURCES, PartyPackage.Literals.MEMBER__RESOURCES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PartyPackage.MEMBER__RESOURCES:
				return ((InternalEList<?>)getResources()).basicRemove(otherEnd, msgs);
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
			case PartyPackage.MEMBER__PARTY:
				if (resolve) return getParty();
				return basicGetParty();
			case PartyPackage.MEMBER__RESOURCES:
				return getResources();
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
			case PartyPackage.MEMBER__PARTY:
				setParty((Party)newValue);
				return;
			case PartyPackage.MEMBER__RESOURCES:
				getResources().clear();
				getResources().addAll((Collection<? extends ResourceCategoryElement>)newValue);
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
			case PartyPackage.MEMBER__PARTY:
				setParty((Party)null);
				return;
			case PartyPackage.MEMBER__RESOURCES:
				getResources().clear();
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
			case PartyPackage.MEMBER__PARTY:
				return basicGetParty() != null;
			case PartyPackage.MEMBER__RESOURCES:
				return !getResources().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //MemberImpl
