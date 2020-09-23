/**
 */
package org.nasdanika.engineering;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Offering</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Offering is something which satisfies persona needs - product, edition, feature. Offerings may reference personas as their intended target audiences. Also offering benefit may be computed from needs and scenarios weights.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.Offering#getTargetAudiences <em>Target Audiences</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getOffering()
 * @model
 * @generated
 */
public interface Offering extends EObject {

	/**
	 * Returns the value of the '<em><b>Target Audiences</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.Persona}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Audiences</em>' reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getOffering_TargetAudiences()
	 * @model
	 * @generated
	 */
	EList<Persona> getTargetAudiences();
} // Offering
