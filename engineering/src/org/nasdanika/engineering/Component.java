/**
 */
package org.nasdanika.engineering;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Component is a concrete generic component, can be a part of a product. Components can be nested. They can also depend on other components. More precisely, component releases may depend on other releases.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.Component#getComponents <em>Components</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getComponent()
 * @model
 * @generated
 */
public interface Component extends AbstractComponent {

	/**
	 * Returns the value of the '<em><b>Components</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.ComponentCategoryElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Components</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getComponent_Components()
	 * @model containment="true"
	 * @generated
	 */
	EList<ComponentCategoryElement> getComponents();
} // Component
