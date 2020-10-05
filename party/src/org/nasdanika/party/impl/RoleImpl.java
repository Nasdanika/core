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
import org.nasdanika.party.PartyPackage;
import org.nasdanika.party.ResourceCategoryElement;
import org.nasdanika.party.Role;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Role</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.party.impl.RoleImpl#getMembers <em>Members</em>}</li>
 *   <li>{@link org.nasdanika.party.impl.RoleImpl#getExtends <em>Extends</em>}</li>
 *   <li>{@link org.nasdanika.party.impl.RoleImpl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link org.nasdanika.party.impl.RoleImpl#getResources <em>Resources</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RoleImpl extends EntityImpl implements Role {
	/**
	 * The default value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ABSTRACT_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RoleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PartyPackage.Literals.ROLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Member> getMembers() {
		return (EList<Member>)eDynamicGet(PartyPackage.ROLE__MEMBERS, PartyPackage.Literals.ROLE__MEMBERS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Role> getExtends() {
		return (EList<Role>)eDynamicGet(PartyPackage.ROLE__EXTENDS, PartyPackage.Literals.ROLE__EXTENDS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isAbstract() {
		return (Boolean)eDynamicGet(PartyPackage.ROLE__ABSTRACT, PartyPackage.Literals.ROLE__ABSTRACT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAbstract(boolean newAbstract) {
		eDynamicSet(PartyPackage.ROLE__ABSTRACT, PartyPackage.Literals.ROLE__ABSTRACT, newAbstract);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<ResourceCategoryElement> getResources() {
		return (EList<ResourceCategoryElement>)eDynamicGet(PartyPackage.ROLE__RESOURCES, PartyPackage.Literals.ROLE__RESOURCES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PartyPackage.ROLE__RESOURCES:
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
			case PartyPackage.ROLE__MEMBERS:
				return getMembers();
			case PartyPackage.ROLE__EXTENDS:
				return getExtends();
			case PartyPackage.ROLE__ABSTRACT:
				return isAbstract();
			case PartyPackage.ROLE__RESOURCES:
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
			case PartyPackage.ROLE__MEMBERS:
				getMembers().clear();
				getMembers().addAll((Collection<? extends Member>)newValue);
				return;
			case PartyPackage.ROLE__EXTENDS:
				getExtends().clear();
				getExtends().addAll((Collection<? extends Role>)newValue);
				return;
			case PartyPackage.ROLE__ABSTRACT:
				setAbstract((Boolean)newValue);
				return;
			case PartyPackage.ROLE__RESOURCES:
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
			case PartyPackage.ROLE__MEMBERS:
				getMembers().clear();
				return;
			case PartyPackage.ROLE__EXTENDS:
				getExtends().clear();
				return;
			case PartyPackage.ROLE__ABSTRACT:
				setAbstract(ABSTRACT_EDEFAULT);
				return;
			case PartyPackage.ROLE__RESOURCES:
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
			case PartyPackage.ROLE__MEMBERS:
				return !getMembers().isEmpty();
			case PartyPackage.ROLE__EXTENDS:
				return !getExtends().isEmpty();
			case PartyPackage.ROLE__ABSTRACT:
				return isAbstract() != ABSTRACT_EDEFAULT;
			case PartyPackage.ROLE__RESOURCES:
				return !getResources().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //RoleImpl
