/**
 */
package org.nasdanika.engineering.impl;

import org.eclipse.emf.ecore.EClass;
import org.nasdanika.engineering.EngineeringPackage;
import org.nasdanika.engineering.Issue;
import org.nasdanika.engineering.IssueRelationship;
import org.nasdanika.engineering.IssueRelationshipType;
import org.nasdanika.ncore.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Issue Relationship</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.impl.IssueRelationshipImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.IssueRelationshipImpl#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IssueRelationshipImpl extends ModelElementImpl implements IssueRelationship {
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
	public Issue getSource() {
		return (Issue)eDynamicGet(EngineeringPackage.ISSUE_RELATIONSHIP__SOURCE, EngineeringPackage.Literals.ISSUE_RELATIONSHIP__SOURCE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Issue basicGetSource() {
		return (Issue)eDynamicGet(EngineeringPackage.ISSUE_RELATIONSHIP__SOURCE, EngineeringPackage.Literals.ISSUE_RELATIONSHIP__SOURCE, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSource(Issue newSource) {
		eDynamicSet(EngineeringPackage.ISSUE_RELATIONSHIP__SOURCE, EngineeringPackage.Literals.ISSUE_RELATIONSHIP__SOURCE, newSource);
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
			case EngineeringPackage.ISSUE_RELATIONSHIP__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
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
			case EngineeringPackage.ISSUE_RELATIONSHIP__SOURCE:
				setSource((Issue)newValue);
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
			case EngineeringPackage.ISSUE_RELATIONSHIP__SOURCE:
				setSource((Issue)null);
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
			case EngineeringPackage.ISSUE_RELATIONSHIP__SOURCE:
				return basicGetSource() != null;
		}
		return super.eIsSet(featureID);
	}

} //IssueRelationshipImpl
