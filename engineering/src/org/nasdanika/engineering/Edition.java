/**
 */
package org.nasdanika.engineering;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A subset of product features offered to different personas at different prices to maximize value for a particular persona. For example, a beginner user may not get benefit from advanced product features and therefore does not need a professional edition. Editions may have bases, e.g. professional edition is based on community edition.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.Edition#getBases <em>Bases</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Edition#getFeatures <em>Features</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getEdition()
 * @model
 * @generated
 */
public interface Edition extends Offering {

	/**
	 * Returns the value of the '<em><b>Bases</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.Edition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bases</em>' reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getEdition_Bases()
	 * @model
	 * @generated
	 */
	EList<Edition> getBases();

	/**
	 * Returns the value of the '<em><b>Features</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.Feature}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Features</em>' reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getEdition_Features()
	 * @model
	 * @generated
	 */
	EList<Feature> getFeatures();
} // Edition
