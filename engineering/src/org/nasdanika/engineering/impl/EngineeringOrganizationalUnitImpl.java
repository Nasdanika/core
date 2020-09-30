/**
 */
package org.nasdanika.engineering.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.engineering.AbstractEngineer;
import org.nasdanika.engineering.ComponentCategoryElement;
import org.nasdanika.engineering.EngineeringOrganizationalUnit;
import org.nasdanika.engineering.EngineeringPackage;

import org.nasdanika.engineering.FeatureType;
import org.nasdanika.engineering.Increment;
import org.nasdanika.engineering.Issue;
import org.nasdanika.engineering.IssueCategory;
import org.nasdanika.engineering.IssueRelationshipType;
import org.nasdanika.engineering.IssueResolution;
import org.nasdanika.engineering.IssueStatus;
import org.nasdanika.engineering.IssueType;
import org.nasdanika.engineering.Objective;
import org.nasdanika.engineering.Persona;
import org.nasdanika.party.impl.OrganizationalUnitImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Organizational Unit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.impl.EngineeringOrganizationalUnitImpl#getIssues <em>Issues</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.EngineeringOrganizationalUnitImpl#getObjectives <em>Objectives</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.EngineeringOrganizationalUnitImpl#getPortfolio <em>Portfolio</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.EngineeringOrganizationalUnitImpl#getTargetAudiences <em>Target Audiences</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.EngineeringOrganizationalUnitImpl#getIssueTypes <em>Issue Types</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.EngineeringOrganizationalUnitImpl#getIssueResolutions <em>Issue Resolutions</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.EngineeringOrganizationalUnitImpl#getIssueCategories <em>Issue Categories</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.EngineeringOrganizationalUnitImpl#getIssueStatuses <em>Issue Statuses</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.EngineeringOrganizationalUnitImpl#getIssueRelationshipTypes <em>Issue Relationship Types</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.EngineeringOrganizationalUnitImpl#getIncrements <em>Increments</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.EngineeringOrganizationalUnitImpl#getFeatureTypes <em>Feature Types</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EngineeringOrganizationalUnitImpl extends OrganizationalUnitImpl implements EngineeringOrganizationalUnit {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EngineeringOrganizationalUnitImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Issue> getIssues() {
		return (EList<Issue>)eDynamicGet(EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUES, EngineeringPackage.Literals.ABSTRACT_ENGINEER__ISSUES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Objective> getObjectives() {
		return (EList<Objective>)eDynamicGet(EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__OBJECTIVES, EngineeringPackage.Literals.ABSTRACT_ENGINEER__OBJECTIVES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<ComponentCategoryElement> getPortfolio() {
		return (EList<ComponentCategoryElement>)eDynamicGet(EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__PORTFOLIO, EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__PORTFOLIO, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Persona> getTargetAudiences() {
		return (EList<Persona>)eDynamicGet(EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__TARGET_AUDIENCES, EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__TARGET_AUDIENCES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<IssueType> getIssueTypes() {
		return (EList<IssueType>)eDynamicGet(EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_TYPES, EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_TYPES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<IssueResolution> getIssueResolutions() {
		return (EList<IssueResolution>)eDynamicGet(EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RESOLUTIONS, EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RESOLUTIONS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<IssueCategory> getIssueCategories() {
		return (EList<IssueCategory>)eDynamicGet(EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_CATEGORIES, EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_CATEGORIES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<IssueStatus> getIssueStatuses() {
		return (EList<IssueStatus>)eDynamicGet(EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_STATUSES, EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_STATUSES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<IssueRelationshipType> getIssueRelationshipTypes() {
		return (EList<IssueRelationshipType>)eDynamicGet(EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RELATIONSHIP_TYPES, EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RELATIONSHIP_TYPES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Increment> getIncrements() {
		return (EList<Increment>)eDynamicGet(EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__INCREMENTS, EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__INCREMENTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<FeatureType> getFeatureTypes() {
		return (EList<FeatureType>)eDynamicGet(EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__FEATURE_TYPES, EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__FEATURE_TYPES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUES:
				return ((InternalEList<?>)getIssues()).basicRemove(otherEnd, msgs);
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__OBJECTIVES:
				return ((InternalEList<?>)getObjectives()).basicRemove(otherEnd, msgs);
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__PORTFOLIO:
				return ((InternalEList<?>)getPortfolio()).basicRemove(otherEnd, msgs);
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__TARGET_AUDIENCES:
				return ((InternalEList<?>)getTargetAudiences()).basicRemove(otherEnd, msgs);
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_TYPES:
				return ((InternalEList<?>)getIssueTypes()).basicRemove(otherEnd, msgs);
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RESOLUTIONS:
				return ((InternalEList<?>)getIssueResolutions()).basicRemove(otherEnd, msgs);
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_CATEGORIES:
				return ((InternalEList<?>)getIssueCategories()).basicRemove(otherEnd, msgs);
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_STATUSES:
				return ((InternalEList<?>)getIssueStatuses()).basicRemove(otherEnd, msgs);
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RELATIONSHIP_TYPES:
				return ((InternalEList<?>)getIssueRelationshipTypes()).basicRemove(otherEnd, msgs);
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__INCREMENTS:
				return ((InternalEList<?>)getIncrements()).basicRemove(otherEnd, msgs);
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__FEATURE_TYPES:
				return ((InternalEList<?>)getFeatureTypes()).basicRemove(otherEnd, msgs);
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
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUES:
				return getIssues();
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__OBJECTIVES:
				return getObjectives();
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__PORTFOLIO:
				return getPortfolio();
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__TARGET_AUDIENCES:
				return getTargetAudiences();
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_TYPES:
				return getIssueTypes();
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RESOLUTIONS:
				return getIssueResolutions();
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_CATEGORIES:
				return getIssueCategories();
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_STATUSES:
				return getIssueStatuses();
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RELATIONSHIP_TYPES:
				return getIssueRelationshipTypes();
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__INCREMENTS:
				return getIncrements();
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__FEATURE_TYPES:
				return getFeatureTypes();
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
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUES:
				getIssues().clear();
				getIssues().addAll((Collection<? extends Issue>)newValue);
				return;
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__OBJECTIVES:
				getObjectives().clear();
				getObjectives().addAll((Collection<? extends Objective>)newValue);
				return;
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__PORTFOLIO:
				getPortfolio().clear();
				getPortfolio().addAll((Collection<? extends ComponentCategoryElement>)newValue);
				return;
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__TARGET_AUDIENCES:
				getTargetAudiences().clear();
				getTargetAudiences().addAll((Collection<? extends Persona>)newValue);
				return;
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_TYPES:
				getIssueTypes().clear();
				getIssueTypes().addAll((Collection<? extends IssueType>)newValue);
				return;
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RESOLUTIONS:
				getIssueResolutions().clear();
				getIssueResolutions().addAll((Collection<? extends IssueResolution>)newValue);
				return;
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_CATEGORIES:
				getIssueCategories().clear();
				getIssueCategories().addAll((Collection<? extends IssueCategory>)newValue);
				return;
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_STATUSES:
				getIssueStatuses().clear();
				getIssueStatuses().addAll((Collection<? extends IssueStatus>)newValue);
				return;
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RELATIONSHIP_TYPES:
				getIssueRelationshipTypes().clear();
				getIssueRelationshipTypes().addAll((Collection<? extends IssueRelationshipType>)newValue);
				return;
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__INCREMENTS:
				getIncrements().clear();
				getIncrements().addAll((Collection<? extends Increment>)newValue);
				return;
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__FEATURE_TYPES:
				getFeatureTypes().clear();
				getFeatureTypes().addAll((Collection<? extends FeatureType>)newValue);
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
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUES:
				getIssues().clear();
				return;
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__OBJECTIVES:
				getObjectives().clear();
				return;
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__PORTFOLIO:
				getPortfolio().clear();
				return;
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__TARGET_AUDIENCES:
				getTargetAudiences().clear();
				return;
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_TYPES:
				getIssueTypes().clear();
				return;
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RESOLUTIONS:
				getIssueResolutions().clear();
				return;
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_CATEGORIES:
				getIssueCategories().clear();
				return;
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_STATUSES:
				getIssueStatuses().clear();
				return;
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RELATIONSHIP_TYPES:
				getIssueRelationshipTypes().clear();
				return;
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__INCREMENTS:
				getIncrements().clear();
				return;
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__FEATURE_TYPES:
				getFeatureTypes().clear();
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
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUES:
				return !getIssues().isEmpty();
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__OBJECTIVES:
				return !getObjectives().isEmpty();
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__PORTFOLIO:
				return !getPortfolio().isEmpty();
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__TARGET_AUDIENCES:
				return !getTargetAudiences().isEmpty();
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_TYPES:
				return !getIssueTypes().isEmpty();
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RESOLUTIONS:
				return !getIssueResolutions().isEmpty();
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_CATEGORIES:
				return !getIssueCategories().isEmpty();
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_STATUSES:
				return !getIssueStatuses().isEmpty();
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RELATIONSHIP_TYPES:
				return !getIssueRelationshipTypes().isEmpty();
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__INCREMENTS:
				return !getIncrements().isEmpty();
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__FEATURE_TYPES:
				return !getFeatureTypes().isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == AbstractEngineer.class) {
			switch (derivedFeatureID) {
				case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUES: return EngineeringPackage.ABSTRACT_ENGINEER__ISSUES;
				case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__OBJECTIVES: return EngineeringPackage.ABSTRACT_ENGINEER__OBJECTIVES;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == AbstractEngineer.class) {
			switch (baseFeatureID) {
				case EngineeringPackage.ABSTRACT_ENGINEER__ISSUES: return EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUES;
				case EngineeringPackage.ABSTRACT_ENGINEER__OBJECTIVES: return EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT__OBJECTIVES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //EngineeringOrganizationalUnitImpl
