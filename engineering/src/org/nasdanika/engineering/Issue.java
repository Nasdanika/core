/**
 */
package org.nasdanika.engineering;

import org.eclipse.emf.common.util.EList;
import org.nasdanika.ncore.Entity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Issue</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Something to possibly act on regarding the owning element - a problem/pain point, an improvement opportunity/enhancement.
 * 
 * Example:
 * 
 * * Containing activity - "Initial setup of a software project", 
 * * Size - 4.0 (hours) - copy an existing project and modify its sources.
 * * Issue - "Create a code generator" (enhancement),
 *     * Size - 40.0 (hours).
 *     * Benefit - 3.5 (hours).
 *     * Implementation - an activity providing a detailed explanation how code generator shall be implemented.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.Issue#getAssignedTo <em>Assigned To</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Issue#getSize <em>Size</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Issue#getCost <em>Cost</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Issue#getBenefit <em>Benefit</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Issue#getPlannedFor <em>Planned For</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Issue#getChildren <em>Children</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Issue#getType <em>Type</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Issue#getStatus <em>Status</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Issue#getResolution <em>Resolution</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Issue#getCategories <em>Categories</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Issue#getNotes <em>Notes</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Issue#getReleases <em>Releases</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Issue#getRelationships <em>Relationships</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getIssue()
 * @model
 * @generated
 */
public interface Issue extends Entity {
	/**
	 * Returns the value of the '<em><b>Assigned To</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.AbstractEngineer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Engineer this issue is assigned to. If this attribute is null the issue is assumed to be assigned to the owner of the containing element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Assigned To</em>' reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getIssue_AssignedTo()
	 * @model
	 * @generated
	 */
	EList<AbstractEngineer> getAssignedTo();

	/**
	 * Returns the value of the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An estimation of effort required to complete this issue in some units used consistently throughout the model - e.g. points or person hours.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Size</em>' attribute.
	 * @see #setSize(double)
	 * @see org.nasdanika.engineering.EngineeringPackage#getIssue_Size()
	 * @model
	 * @generated
	 */
	double getSize();

	/**
	 * Sets the value of the '{@link org.nasdanika.engineering.Issue#getSize <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size</em>' attribute.
	 * @see #getSize()
	 * @generated
	 */
	void setSize(double value);

	/**
	 * Returns the value of the '<em><b>Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An estimation of monetary expenditure required to complete this issue in some units used consistently throughout the model - e.g. dollars. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cost</em>' attribute.
	 * @see #setCost(double)
	 * @see org.nasdanika.engineering.EngineeringPackage#getIssue_Cost()
	 * @model
	 * @generated
	 */
	double getCost();

	/**
	 * Sets the value of the '{@link org.nasdanika.engineering.Issue#getCost <em>Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cost</em>' attribute.
	 * @see #getCost()
	 * @generated
	 */
	void setCost(double value);

	/**
	 * Returns the value of the '<em><b>Benefit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Explicitly assigned issue benefit value. Used for computing effective benefit which in turn is used for prioritization.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Benefit</em>' attribute.
	 * @see #setBenefit(double)
	 * @see org.nasdanika.engineering.EngineeringPackage#getIssue_Benefit()
	 * @model
	 * @generated
	 */
	double getBenefit();

	/**
	 * Sets the value of the '{@link org.nasdanika.engineering.Issue#getBenefit <em>Benefit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Benefit</em>' attribute.
	 * @see #getBenefit()
	 * @generated
	 */
	void setBenefit(double value);

	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.Issue}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Issues may be organized into a hierarchy, a [Work Breakdown Structure](https://en.wikipedia.org/wiki/Work_breakdown_structure). The parent issue is implicitly blocked by its children, i.e. it can be worked on and closed only after all of its children are closed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getIssue_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<Issue> getChildren();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(IssueType)
	 * @see org.nasdanika.engineering.EngineeringPackage#getIssue_Type()
	 * @model
	 * @generated
	 */
	IssueType getType();

	/**
	 * Sets the value of the '{@link org.nasdanika.engineering.Issue#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(IssueType value);

	/**
	 * Returns the value of the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' reference.
	 * @see #setStatus(IssueStatus)
	 * @see org.nasdanika.engineering.EngineeringPackage#getIssue_Status()
	 * @model
	 * @generated
	 */
	IssueStatus getStatus();

	/**
	 * Sets the value of the '{@link org.nasdanika.engineering.Issue#getStatus <em>Status</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' reference.
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(IssueStatus value);

	/**
	 * Returns the value of the '<em><b>Resolution</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resolution</em>' reference.
	 * @see #setResolution(IssueResolution)
	 * @see org.nasdanika.engineering.EngineeringPackage#getIssue_Resolution()
	 * @model
	 * @generated
	 */
	IssueResolution getResolution();

	/**
	 * Sets the value of the '{@link org.nasdanika.engineering.Issue#getResolution <em>Resolution</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resolution</em>' reference.
	 * @see #getResolution()
	 * @generated
	 */
	void setResolution(IssueResolution value);

	/**
	 * Returns the value of the '<em><b>Categories</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.IssueCategory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An issue can be assigned zero or more categories.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Categories</em>' reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getIssue_Categories()
	 * @model
	 * @generated
	 */
	EList<IssueCategory> getCategories();

	/**
	 * Returns the value of the '<em><b>Notes</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.IssueNote}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Issue notes can be used for tracking work progress including time and expenditure reporting.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Notes</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getIssue_Notes()
	 * @model containment="true"
	 * @generated
	 */
	EList<IssueNote> getNotes();

	/**
	 * Returns the value of the '<em><b>Releases</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.Release}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Modifications performed as part of the issue work may appear in zero or more component releases.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Releases</em>' reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getIssue_Releases()
	 * @model
	 * @generated
	 */
	EList<Release> getReleases();

	/**
	 * Returns the value of the '<em><b>Relationships</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.IssueRelationship}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relationships</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getIssue_Relationships()
	 * @model containment="true"
	 * @generated
	 */
	EList<IssueRelationship> getRelationships();

	/**
	 * Returns the value of the '<em><b>Planned For</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Interment in which issue is scheduled to be worked on.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Planned For</em>' reference.
	 * @see #setPlannedFor(Increment)
	 * @see org.nasdanika.engineering.EngineeringPackage#getIssue_PlannedFor()
	 * @model
	 * @generated
	 */
	Increment getPlannedFor();

	/**
	 * Sets the value of the '{@link org.nasdanika.engineering.Issue#getPlannedFor <em>Planned For</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Planned For</em>' reference.
	 * @see #getPlannedFor()
	 * @generated
	 */
	void setPlannedFor(Increment value);

} // Issue
