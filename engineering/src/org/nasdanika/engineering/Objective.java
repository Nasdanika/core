/**
 */
package org.nasdanika.engineering;

import org.eclipse.emf.common.util.EList;
import org.nasdanika.ncore.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Objective</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Strategic objective owned by an engineer or engineering org unit. Hierarchical, may reference a non-containing parent (only for top level?). References an increment. Parent shall be for the same or larger increment. Inherits increment from the parent if not set. Issue references objectives. Weight in the parent objective. Effective weight. Completion status - manually entered number? Effective completion - manually entered number plus computed from associated issues and key results. Status - open/closed? Or auto-closes when increment closes?
 * 
 * TODO - risk to objectives as well as to issues. Affects effective importance and may be used for filtering based on org risk appetite/tolerance.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.Objective#getIncrement <em>Increment</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Objective#getChildren <em>Children</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Objective#getKeyResults <em>Key Results</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Objective#getParent <em>Parent</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getObjective()
 * @model
 * @generated
 */
public interface Objective extends ModelElement {

	/**
	 * Returns the value of the '<em><b>Increment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Interment in which release is scheduled to be published.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Increment</em>' reference.
	 * @see #setIncrement(Increment)
	 * @see org.nasdanika.engineering.EngineeringPackage#getObjective_Increment()
	 * @model
	 * @generated
	 */
	Increment getIncrement();

	/**
	 * Sets the value of the '{@link org.nasdanika.engineering.Objective#getIncrement <em>Increment</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Increment</em>' reference.
	 * @see #getIncrement()
	 * @generated
	 */
	void setIncrement(Increment value);

	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.Objective}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getObjective_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<Objective> getChildren();

	/**
	 * Returns the value of the '<em><b>Key Results</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.KeyResult}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key Results</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getObjective_KeyResults()
	 * @model containment="true"
	 * @generated
	 */
	EList<KeyResult> getKeyResults();

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' reference.
	 * @see #setParent(Objective)
	 * @see org.nasdanika.engineering.EngineeringPackage#getObjective_Parent()
	 * @model
	 * @generated
	 */
	Objective getParent();

	/**
	 * Sets the value of the '{@link org.nasdanika.engineering.Objective#getParent <em>Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(Objective value);
} // Objective
