/**
 */
package org.nasdanika.engineering;

import java.util.Date;

import org.eclipse.emf.common.util.EList;

import org.nasdanika.ncore.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Increment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A time period in which engineers are working on issues. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.Increment#getChildren <em>Children</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Increment#getStart <em>Start</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Increment#getEnd <em>End</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getIncrement()
 * @model
 * @generated
 */
public interface Increment extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.Increment}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Increments can be organized into a hierarchy. E.g. Year - Quarter - Month - Week.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getIncrement_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<Increment> getChildren();

	/**
	 * Returns the value of the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start</em>' attribute.
	 * @see #setStart(Date)
	 * @see org.nasdanika.engineering.EngineeringPackage#getIncrement_Start()
	 * @model
	 * @generated
	 */
	Date getStart();

	/**
	 * Sets the value of the '{@link org.nasdanika.engineering.Increment#getStart <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start</em>' attribute.
	 * @see #getStart()
	 * @generated
	 */
	void setStart(Date value);

	/**
	 * Returns the value of the '<em><b>End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End</em>' attribute.
	 * @see #setEnd(Date)
	 * @see org.nasdanika.engineering.EngineeringPackage#getIncrement_End()
	 * @model
	 * @generated
	 */
	Date getEnd();

	/**
	 * Sets the value of the '{@link org.nasdanika.engineering.Increment#getEnd <em>End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End</em>' attribute.
	 * @see #getEnd()
	 * @generated
	 */
	void setEnd(Date value);

} // Increment
