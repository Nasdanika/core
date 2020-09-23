/**
 */
package org.nasdanika.engineering.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.nasdanika.engineering.EngineeringPackage;
import org.nasdanika.engineering.IssueRelationshipType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Issue Relationship Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.impl.IssueRelationshipTypeImpl#isBlocks <em>Blocks</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IssueRelationshipTypeImpl extends MinimalEObjectImpl.Container implements IssueRelationshipType {
	/**
	 * The default value of the '{@link #isBlocks() <em>Blocks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBlocks()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BLOCKS_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IssueRelationshipTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EngineeringPackage.Literals.ISSUE_RELATIONSHIP_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isBlocks() {
		return (Boolean)eDynamicGet(EngineeringPackage.ISSUE_RELATIONSHIP_TYPE__BLOCKS, EngineeringPackage.Literals.ISSUE_RELATIONSHIP_TYPE__BLOCKS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBlocks(boolean newBlocks) {
		eDynamicSet(EngineeringPackage.ISSUE_RELATIONSHIP_TYPE__BLOCKS, EngineeringPackage.Literals.ISSUE_RELATIONSHIP_TYPE__BLOCKS, newBlocks);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EngineeringPackage.ISSUE_RELATIONSHIP_TYPE__BLOCKS:
				return isBlocks();
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
			case EngineeringPackage.ISSUE_RELATIONSHIP_TYPE__BLOCKS:
				setBlocks((Boolean)newValue);
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
			case EngineeringPackage.ISSUE_RELATIONSHIP_TYPE__BLOCKS:
				setBlocks(BLOCKS_EDEFAULT);
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
			case EngineeringPackage.ISSUE_RELATIONSHIP_TYPE__BLOCKS:
				return isBlocks() != BLOCKS_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //IssueRelationshipTypeImpl
