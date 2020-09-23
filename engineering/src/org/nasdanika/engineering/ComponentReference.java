/**
 */
package org.nasdanika.engineering;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference to a component. Can be used to federate multiple model resources into a single logical model.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.ComponentReference#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getComponentReference()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface ComponentReference extends ComponentCategoryElement {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.AbstractComponent}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Reference target.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target</em>' reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getComponentReference_Target()
	 * @model
	 * @generated
	 */
	EList<AbstractComponent> getTarget();

} // ComponentReference
