/**
 */
package org.nasdanika.engineering.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.nasdanika.engineering.EngineeringPackage;
import org.nasdanika.engineering.Issue;
import org.nasdanika.engineering.IssueRelationship;
import org.nasdanika.engineering.IssueRelationshipType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Issue Relationship</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.impl.IssueRelationshipImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.IssueRelationshipImpl#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IssueRelationshipImpl extends MinimalEObjectImpl.Container implements IssueRelationship {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IssueRelationshipImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EngineeringPackage.Literals.ISSUE_RELATIONSHIP;
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
	public IssueRelationshipType getType() {
		return (IssueRelationshipType)eDynamicGet(EngineeringPackage.ISSUE_RELATIONSHIP__TYPE, EngineeringPackage.Literals.ISSUE_RELATIONSHIP__TYPE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IssueRelationshipType basicGetType() {
		return (IssueRelationshipType)eDynamicGet(EngineeringPackage.ISSUE_RELATIONSHIP__TYPE, EngineeringPackage.Literals.ISSUE_RELATIONSHIP__TYPE, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(IssueRelationshipType newType) {
		eDynamicSet(EngineeringPackage.ISSUE_RELATIONSHIP__TYPE, EngineeringPackage.Literals.ISSUE_RELATIONSHIP__TYPE, newType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Issue getTarget() {
		return (Issue)eDynamicGet(EngineeringPackage.ISSUE_RELATIONSHIP__TARGET, EngineeringPackage.Literals.ISSUE_RELATIONSHIP__TARGET, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Issue basicGetTarget() {
		return (Issue)eDynamicGet(EngineeringPackage.ISSUE_RELATIONSHIP__TARGET, EngineeringPackage.Literals.ISSUE_RELATIONSHIP__TARGET, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTarget(Issue newTarget) {
		eDynamicSet(EngineeringPackage.ISSUE_RELATIONSHIP__TARGET, EngineeringPackage.Literals.ISSUE_RELATIONSHIP__TARGET, newTarget);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EngineeringPackage.ISSUE_RELATIONSHIP__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case EngineeringPackage.ISSUE_RELATIONSHIP__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
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
			case EngineeringPackage.ISSUE_RELATIONSHIP__TYPE:
				setType((IssueRelationshipType)newValue);
				return;
			case EngineeringPackage.ISSUE_RELATIONSHIP__TARGET:
				setTarget((Issue)newValue);
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
			case EngineeringPackage.ISSUE_RELATIONSHIP__TYPE:
				setType((IssueRelationshipType)null);
				return;
			case EngineeringPackage.ISSUE_RELATIONSHIP__TARGET:
				setTarget((Issue)null);
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
			case EngineeringPackage.ISSUE_RELATIONSHIP__TYPE:
				return basicGetType() != null;
			case EngineeringPackage.ISSUE_RELATIONSHIP__TARGET:
				return basicGetTarget() != null;
		}
		return super.eIsSet(featureID);
	}

} //IssueRelationshipImpl
