/**
 */
package org.nasdanika.party.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.party.DirectoryElement;
import org.nasdanika.party.MemberDirectoryElement;
import org.nasdanika.party.Organization;
import org.nasdanika.party.PartyPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Organization</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.party.impl.OrganizationImpl#getMembers <em>Members</em>}</li>
 *   <li>{@link org.nasdanika.party.impl.OrganizationImpl#getDirectory <em>Directory</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OrganizationImpl extends OrganizationalUnitImpl implements Organization {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated 
	 */
	protected OrganizationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PartyPackage.Literals.ORGANIZATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<MemberDirectoryElement> getMembers() {
		return (EList<MemberDirectoryElement>)eDynamicGet(PartyPackage.ORGANIZATION__MEMBERS, PartyPackage.Literals.ORGANIZATION__MEMBERS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<DirectoryElement> getDirectory() {
		return (EList<DirectoryElement>)eDynamicGet(PartyPackage.ORGANIZATION__DIRECTORY, PartyPackage.Literals.ORGANIZATION__DIRECTORY, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PartyPackage.ORGANIZATION__MEMBERS:
				return ((InternalEList<?>)getMembers()).basicRemove(otherEnd, msgs);
			case PartyPackage.ORGANIZATION__DIRECTORY:
				return ((InternalEList<?>)getDirectory()).basicRemove(otherEnd, msgs);
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
			case PartyPackage.ORGANIZATION__MEMBERS:
				return getMembers();
			case PartyPackage.ORGANIZATION__DIRECTORY:
				return getDirectory();
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
			case PartyPackage.ORGANIZATION__MEMBERS:
				getMembers().clear();
				getMembers().addAll((Collection<? extends MemberDirectoryElement>)newValue);
				return;
			case PartyPackage.ORGANIZATION__DIRECTORY:
				getDirectory().clear();
				getDirectory().addAll((Collection<? extends DirectoryElement>)newValue);
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
			case PartyPackage.ORGANIZATION__MEMBERS:
				getMembers().clear();
				return;
			case PartyPackage.ORGANIZATION__DIRECTORY:
				getDirectory().clear();
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
			case PartyPackage.ORGANIZATION__MEMBERS:
				return !getMembers().isEmpty();
			case PartyPackage.ORGANIZATION__DIRECTORY:
				return !getDirectory().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //OrganizationImpl
