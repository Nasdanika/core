/**
 */
package org.nasdanika.rigel;

import org.eclipse.emf.common.util.EList;


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
 *   <li>{@link org.nasdanika.rigel.Issue#getImportance <em>Importance</em>}</li>
 *   <li>{@link org.nasdanika.rigel.Issue#getStatus <em>Status</em>}</li>
 *   <li>{@link org.nasdanika.rigel.Issue#getAssignedTo <em>Assigned To</em>}</li>
 *   <li>{@link org.nasdanika.rigel.Issue#getSize <em>Size</em>}</li>
 *   <li>{@link org.nasdanika.rigel.Issue#getBenefit <em>Benefit</em>}</li>
 *   <li>{@link org.nasdanika.rigel.Issue#getChildren <em>Children</em>}</li>
 *   <li>{@link org.nasdanika.rigel.Issue#getImplementation <em>Implementation</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.rigel.RigelPackage#getIssue()
 * @model annotation="urn:org.nasdanika label_ru='\u0417\u0430\u0434\u0430\u0447\u0430' documentation_ru='\u0427\u0442\u043e-\u0442\u043e, \u0442\u0440\u0435\u0431\u0443\u044e\u0449\u0435\u0435 \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u044f \u043a\u0430\u043a\u043e\u0439-\u043b\u0438\u0431\u043e \u0440\u0430\u0431\u043e\u0442\u044b \u0432 \u043e\u0442\u043d\u043e\u0448\u0435\u043d\u0438\u0438 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430-\u0432\u043b\u0430\u0434\u0435\u043b\u044c\u0446\u0430 - \u043e\u0448\u0438\u0431\u043a\u0430, \u043f\u0440\u043e\u0431\u043b\u0435\u043c\u0430, \u0438\u043d\u0446\u0435\u0434\u0435\u043d\u0442, \u0432\u043e\u0437\u043c\u043e\u0436\u043d\u043e\u0441\u0442\u044c \u0443\u0441\u043e\u0432\u0435\u0440\u0448\u0435\u043d\u0441\u0442\u0432\u043e\u0432\u0430\u043d\u0438\u044f.'"
 * @generated
 */
public interface Issue extends ModelElement, Requirement {
	/**
	 * Returns the value of the '<em><b>Importance</b></em>' attribute.
	 * The default value is <code>"Medium"</code>.
	 * The literals are from the enumeration {@link org.nasdanika.rigel.IssueImportance}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Issue importance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Importance</em>' attribute.
	 * @see org.nasdanika.rigel.IssueImportance
	 * @see #setImportance(IssueImportance)
	 * @see org.nasdanika.rigel.RigelPackage#getIssue_Importance()
	 * @model default="Medium"
	 * @generated
	 */
	IssueImportance getImportance();

	/**
	 * Sets the value of the '{@link org.nasdanika.rigel.Issue#getImportance <em>Importance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Importance</em>' attribute.
	 * @see org.nasdanika.rigel.IssueImportance
	 * @see #getImportance()
	 * @generated
	 */
	void setImportance(IssueImportance value);

	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * The default value is <code>"Open"</code>.
	 * The literals are from the enumeration {@link org.nasdanika.rigel.IssueStatus}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Issue status.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see org.nasdanika.rigel.IssueStatus
	 * @see #setStatus(IssueStatus)
	 * @see org.nasdanika.rigel.RigelPackage#getIssue_Status()
	 * @model default="Open"
	 * @generated
	 */
	IssueStatus getStatus();

	/**
	 * Sets the value of the '{@link org.nasdanika.rigel.Issue#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see org.nasdanika.rigel.IssueStatus
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(IssueStatus value);

	/**
	 * Returns the value of the '<em><b>Assigned To</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.rigel.Engineer#getAssignments <em>Assignments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Engineer this issue is assigned to. If this attribute is null the issue is assumed to be assigned to the owner of the containing element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Assigned To</em>' reference.
	 * @see #setAssignedTo(Engineer)
	 * @see org.nasdanika.rigel.RigelPackage#getIssue_AssignedTo()
	 * @see org.nasdanika.rigel.Engineer#getAssignments
	 * @model opposite="assignments"
	 * @generated
	 */
	Engineer getAssignedTo();

	/**
	 * Sets the value of the '{@link org.nasdanika.rigel.Issue#getAssignedTo <em>Assigned To</em>}' reference.
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
	 * @see org.nasdanika.rigel.RigelPackage#getIssue_Size()
	 * @model
	 * @generated
	 */
	double getSize();

	/**
	 * Sets the value of the '{@link org.nasdanika.rigel.Issue#getSize <em>Size</em>}' attribute.
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
	 * @see org.nasdanika.rigel.RigelPackage#getIssue_Benefit()
	 * @model
	 * @generated
	 */
	double getBenefit();

	/**
	 * Sets the value of the '{@link org.nasdanika.rigel.Issue#getBenefit <em>Benefit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Benefit</em>' attribute.
	 * @see #getBenefit()
	 * @generated
	 */
	void setBenefit(double value);

	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.rigel.Issue}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Issues may be organized into a hierarchy.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.nasdanika.rigel.RigelPackage#getIssue_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<Issue> getChildren();

	/**
	 * Returns the value of the '<em><b>Implementation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Activity providing details about how to implement this issue.
	 * 
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Implementation</em>' reference.
	 * @see #setImplementation(Activity)
	 * @see org.nasdanika.rigel.RigelPackage#getIssue_Implementation()
	 * @model
	 * @generated
	 */
	Activity getImplementation();

	/**
	 * Sets the value of the '{@link org.nasdanika.rigel.Issue#getImplementation <em>Implementation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implementation</em>' reference.
	 * @see #getImplementation()
	 * @generated
	 */
	void setImplementation(Activity value);

} // Issue
