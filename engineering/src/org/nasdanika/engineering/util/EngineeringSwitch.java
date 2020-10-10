/**
 */
package org.nasdanika.engineering.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.nasdanika.engineering.*;
import org.nasdanika.ncore.Entity;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.party.AbstractOrganizationalUnit;
import org.nasdanika.party.DirectoryElement;
import org.nasdanika.party.Organization;
import org.nasdanika.party.OrganizationalUnit;
import org.nasdanika.party.Party;
import org.nasdanika.party.Role;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.nasdanika.engineering.EngineeringPackage
 * @generated
 */
public class EngineeringSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static EngineeringPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EngineeringSwitch() {
		if (modelPackage == null) {
			modelPackage = EngineeringPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case EngineeringPackage.COMPONENT_CATEGORY_ELEMENT: {
				ComponentCategoryElement componentCategoryElement = (ComponentCategoryElement)theEObject;
				T result = caseComponentCategoryElement(componentCategoryElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.ABSTRACT_COMPONENT: {
				AbstractComponent abstractComponent = (AbstractComponent)theEObject;
				T result = caseAbstractComponent(abstractComponent);
				if (result == null) result = caseComponentCategoryElement(abstractComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.COMPONENT_CATEGORY: {
				ComponentCategory componentCategory = (ComponentCategory)theEObject;
				T result = caseComponentCategory(componentCategory);
				if (result == null) result = caseModelElement(componentCategory);
				if (result == null) result = caseComponentCategoryElement(componentCategory);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.COMPONENT_REFERENCE: {
				ComponentReference componentReference = (ComponentReference)theEObject;
				T result = caseComponentReference(componentReference);
				if (result == null) result = caseComponentCategoryElement(componentReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.ABSTRACT_ENGINEER: {
				AbstractEngineer abstractEngineer = (AbstractEngineer)theEObject;
				T result = caseAbstractEngineer(abstractEngineer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.ENGINEERING_ORGANIZATIONAL_UNIT: {
				EngineeringOrganizationalUnit engineeringOrganizationalUnit = (EngineeringOrganizationalUnit)theEObject;
				T result = caseEngineeringOrganizationalUnit(engineeringOrganizationalUnit);
				if (result == null) result = caseOrganizationalUnit(engineeringOrganizationalUnit);
				if (result == null) result = caseAbstractEngineer(engineeringOrganizationalUnit);
				if (result == null) result = caseParty(engineeringOrganizationalUnit);
				if (result == null) result = caseAbstractOrganizationalUnit(engineeringOrganizationalUnit);
				if (result == null) result = caseDirectoryElement(engineeringOrganizationalUnit);
				if (result == null) result = caseEntity(engineeringOrganizationalUnit);
				if (result == null) result = caseModelElement(engineeringOrganizationalUnit);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.ENGINEERING_ORGANIZATION: {
				EngineeringOrganization engineeringOrganization = (EngineeringOrganization)theEObject;
				T result = caseEngineeringOrganization(engineeringOrganization);
				if (result == null) result = caseOrganization(engineeringOrganization);
				if (result == null) result = caseEngineeringOrganizationalUnit(engineeringOrganization);
				if (result == null) result = caseOrganizationalUnit(engineeringOrganization);
				if (result == null) result = caseAbstractEngineer(engineeringOrganization);
				if (result == null) result = caseParty(engineeringOrganization);
				if (result == null) result = caseAbstractOrganizationalUnit(engineeringOrganization);
				if (result == null) result = caseDirectoryElement(engineeringOrganization);
				if (result == null) result = caseEntity(engineeringOrganization);
				if (result == null) result = caseModelElement(engineeringOrganization);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.ENGINEER: {
				Engineer engineer = (Engineer)theEObject;
				T result = caseEngineer(engineer);
				if (result == null) result = caseRole(engineer);
				if (result == null) result = caseAbstractEngineer(engineer);
				if (result == null) result = caseEntity(engineer);
				if (result == null) result = caseModelElement(engineer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.ISSUE_TYPE: {
				IssueType issueType = (IssueType)theEObject;
				T result = caseIssueType(issueType);
				if (result == null) result = caseModelElement(issueType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.ISSUE_RESOLUTION: {
				IssueResolution issueResolution = (IssueResolution)theEObject;
				T result = caseIssueResolution(issueResolution);
				if (result == null) result = caseModelElement(issueResolution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.ISSUE_CATEGORY: {
				IssueCategory issueCategory = (IssueCategory)theEObject;
				T result = caseIssueCategory(issueCategory);
				if (result == null) result = caseModelElement(issueCategory);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.ISSUE_STATUS: {
				IssueStatus issueStatus = (IssueStatus)theEObject;
				T result = caseIssueStatus(issueStatus);
				if (result == null) result = caseModelElement(issueStatus);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.ISSUE_NOTE: {
				IssueNote issueNote = (IssueNote)theEObject;
				T result = caseIssueNote(issueNote);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.ISSUE_RELATIONSHIP_TYPE: {
				IssueRelationshipType issueRelationshipType = (IssueRelationshipType)theEObject;
				T result = caseIssueRelationshipType(issueRelationshipType);
				if (result == null) result = caseModelElement(issueRelationshipType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.ISSUE_RELATIONSHIP: {
				IssueRelationship issueRelationship = (IssueRelationship)theEObject;
				T result = caseIssueRelationship(issueRelationship);
				if (result == null) result = caseModelElement(issueRelationship);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.ISSUE: {
				Issue issue = (Issue)theEObject;
				T result = caseIssue(issue);
				if (result == null) result = caseEntity(issue);
				if (result == null) result = caseModelElement(issue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.INCREMENT: {
				Increment increment = (Increment)theEObject;
				T result = caseIncrement(increment);
				if (result == null) result = caseModelElement(increment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.RELEASE: {
				Release release = (Release)theEObject;
				T result = caseRelease(release);
				if (result == null) result = caseModelElement(release);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.OBJECTIVE: {
				Objective objective = (Objective)theEObject;
				T result = caseObjective(objective);
				if (result == null) result = caseModelElement(objective);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.KEY_RESULT: {
				KeyResult keyResult = (KeyResult)theEObject;
				T result = caseKeyResult(keyResult);
				if (result == null) result = caseModelElement(keyResult);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.COMPONENT: {
				Component component = (Component)theEObject;
				T result = caseComponent(component);
				if (result == null) result = caseModelElement(component);
				if (result == null) result = caseAbstractComponent(component);
				if (result == null) result = caseComponentCategoryElement(component);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.OFFERING: {
				Offering offering = (Offering)theEObject;
				T result = caseOffering(offering);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.PRODUCT: {
				Product product = (Product)theEObject;
				T result = caseProduct(product);
				if (result == null) result = caseComponent(product);
				if (result == null) result = caseOffering(product);
				if (result == null) result = caseModelElement(product);
				if (result == null) result = caseAbstractComponent(product);
				if (result == null) result = caseComponentCategoryElement(product);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.EDITION: {
				Edition edition = (Edition)theEObject;
				T result = caseEdition(edition);
				if (result == null) result = caseModelElement(edition);
				if (result == null) result = caseOffering(edition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.FEATURE_TYPE: {
				FeatureType featureType = (FeatureType)theEObject;
				T result = caseFeatureType(featureType);
				if (result == null) result = caseModelElement(featureType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.FEATURE_CATEGORY_ELEMENT: {
				FeatureCategoryElement featureCategoryElement = (FeatureCategoryElement)theEObject;
				T result = caseFeatureCategoryElement(featureCategoryElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.FEATURE_CATEGORY: {
				FeatureCategory featureCategory = (FeatureCategory)theEObject;
				T result = caseFeatureCategory(featureCategory);
				if (result == null) result = caseModelElement(featureCategory);
				if (result == null) result = caseFeatureCategoryElement(featureCategory);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.FEATURE: {
				Feature feature = (Feature)theEObject;
				T result = caseFeature(feature);
				if (result == null) result = caseFeatureCategoryElement(feature);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.PERSONA: {
				Persona persona = (Persona)theEObject;
				T result = casePersona(persona);
				if (result == null) result = caseModelElement(persona);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.NEED_CATEGORY_ELEMENT: {
				NeedCategoryElement needCategoryElement = (NeedCategoryElement)theEObject;
				T result = caseNeedCategoryElement(needCategoryElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.NEED_CATEGORY: {
				NeedCategory needCategory = (NeedCategory)theEObject;
				T result = caseNeedCategory(needCategory);
				if (result == null) result = caseModelElement(needCategory);
				if (result == null) result = caseNeedCategoryElement(needCategory);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.NEED: {
				Need need = (Need)theEObject;
				T result = caseNeed(need);
				if (result == null) result = caseNeedCategoryElement(need);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.SCENARIO: {
				Scenario scenario = (Scenario)theEObject;
				T result = caseScenario(scenario);
				if (result == null) result = caseModelElement(scenario);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.CRITERION: {
				Criterion criterion = (Criterion)theEObject;
				T result = caseCriterion(criterion);
				if (result == null) result = caseModelElement(criterion);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.COMPARISON: {
				Comparison comparison = (Comparison)theEObject;
				T result = caseComparison(comparison);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EngineeringPackage.RISK: {
				Risk risk = (Risk)theEObject;
				T result = caseRisk(risk);
				if (result == null) result = caseModelElement(risk);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component Category Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component Category Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponentCategoryElement(ComponentCategoryElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractComponent(AbstractComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component Category</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component Category</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponentCategory(ComponentCategory object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponentReference(ComponentReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Engineer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Engineer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractEngineer(AbstractEngineer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Engineer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Engineer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEngineer(Engineer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Issue Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Issue Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIssueType(IssueType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Issue Resolution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Issue Resolution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIssueResolution(IssueResolution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Issue Category</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Issue Category</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIssueCategory(IssueCategory object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Issue Status</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Issue Status</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIssueStatus(IssueStatus object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Issue Note</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Issue Note</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIssueNote(IssueNote object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Issue Relationship Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Issue Relationship Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIssueRelationshipType(IssueRelationshipType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Issue Relationship</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Issue Relationship</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIssueRelationship(IssueRelationship object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Issue</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Issue</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIssue(Issue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Increment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Increment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIncrement(Increment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Organizational Unit</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Organizational Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEngineeringOrganizationalUnit(EngineeringOrganizationalUnit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Organization</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Organization</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEngineeringOrganization(EngineeringOrganization object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Release</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Release</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelease(Release object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Objective</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Objective</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseObjective(Objective object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Key Result</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Key Result</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseKeyResult(KeyResult object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Product</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Product</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProduct(Product object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Offering</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Offering</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOffering(Offering object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Edition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Edition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEdition(Edition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Feature Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Feature Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFeatureType(FeatureType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Feature Category Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Feature Category Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFeatureCategoryElement(FeatureCategoryElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Feature Category</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Feature Category</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFeatureCategory(FeatureCategory object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Feature</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Feature</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFeature(Feature object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponent(Component object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Persona</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Persona</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePersona(Persona object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Need Category Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Need Category Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNeedCategoryElement(NeedCategoryElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Need Category</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Need Category</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNeedCategory(NeedCategory object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Need</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Need</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNeed(Need object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Scenario</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Scenario</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScenario(Scenario object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Criterion</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Criterion</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCriterion(Criterion object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Comparison</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Comparison</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComparison(Comparison object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Risk</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Risk</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRisk(Risk object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelElement(ModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Role</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Role</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRole(Role object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEntity(Entity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Directory Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Directory Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDirectoryElement(DirectoryElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Party</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Party</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParty(Party object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Organizational Unit</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Organizational Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractOrganizationalUnit(AbstractOrganizationalUnit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Organizational Unit</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Organizational Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOrganizationalUnit(OrganizationalUnit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Organization</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Organization</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOrganization(Organization object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //EngineeringSwitch
