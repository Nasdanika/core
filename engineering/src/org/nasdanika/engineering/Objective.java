/**
 */
package org.nasdanika.engineering;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Objective</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Strategic objective owned by an engineer or engineering org unit. Hierarchical, may reference a non-containing parent (only for top level?). References an increment. Parent shall be for the same or larger increment. Inherits increment from the parent if not set. Issue references objectives. Weight in the parent objective. Effective weight. Completion status - manually entered number? Effective completion - manually entered number plus computed from associated issues and key results. Status - open/closed? Or auto-closes when increment closes?
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getObjective()
 * @model
 * @generated
 */
public interface Objective extends EObject {
} // Objective
