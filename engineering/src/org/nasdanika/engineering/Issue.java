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
 *   <li>{@link org.nasdanika.engineering.Issue#getImportance <em>Importance</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Issue#getStatus <em>Status</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Issue#getAssignedTo <em>Assigned To</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Issue#getSize <em>Size</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Issue#getBenefit <em>Benefit</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Issue#getChildren <em>Children</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Issue#getPlannedFor <em>Planned For</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getIssue()
 * @model
 * @generated
 */
public interface Issue extends Entity {
	/**
	 * Returns the value of the '<em><b>Importance</b></em>' attribute.
	 * The default value is <code>"Medium"</code>.
	 * The literals are from the enumeration {@link org.nasdanika.engineering.IssueImportance}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Issue importance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Importance</em>' attribute.
	 * @see org.nasdanika.engineering.IssueImportance
	 * @see #setImportance(IssueImportance)
	 * @see org.nasdanika.engineering.EngineeringPackage#getIssue_Importance()
	 * @model default="Medium"
	 * @generated
	 */
	IssueImportance getImportance();

	/**
	 * Sets the value of the '{@link org.nasdanika.engineering.Issue#getImportance <em>Importance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Importance</em>' attribute.
	 * @see org.nasdanika.engineering.IssueImportance
	 * @see #getImportance()
	 * @generated
	 */
	void setImportance(IssueImportance value);

	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * The default value is <code>"Open"</code>.
	 * The literals are from the enumeration {@link org.nasdanika.engineering.IssueStatus}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Issue status.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see org.nasdanika.engineering.IssueStatus
	 * @see #setStatus(IssueStatus)
	 * @see org.nasdanika.engineering.EngineeringPackage#getIssue_Status()
	 * @model default="Open"
	 * @generated
	 */
	IssueStatus getStatus();

	/**
	 * Sets the value of the '{@link org.nasdanika.engineering.Issue#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see org.nasdanika.engineering.IssueStatus
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(IssueStatus value);

	/**
	 * Returns the value of the '<em><b>Assigned To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Engineer this issue is assigned to. If this attribute is null the issue is assumed to be assigned to the owner of the containing element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Assigned To</em>' reference.
	 * @see #setAssignedTo(Engineer)
	 * @see org.nasdanika.engineering.EngineeringPackage#getIssue_AssignedTo()
	 * @model
	 * @generated
	 */
	Engineer getAssignedTo();

	/**
	 * Sets the value of the '{@link org.nasdanika.engineering.Issue#getAssignedTo <em>Assigned To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Assigned To</em>' reference.
	 * @see #getAssignedTo()
	 * @generated
	 */
	void setAssignedTo(Engineer value);

	/**
	 * Returns the value of the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An estimation of effort required to complete this issue in some units used consistently throughout the model - points, person hours, dollars.
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
	 * Returns the value of the '<em><b>Benefit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An estimation of reduction of the containing activity effort caused by completion of this issue. It can be used for cost/benefit analysis in order to prioritize issues.
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
	 * Issues may be organized into a hierarchy.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getIssue_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<Issue> getChildren();

	/**
	 * Returns the value of the '<em><b>Planned For</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
