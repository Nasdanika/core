/**
 */
package org.nasdanika.graph.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Composite Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Node which is also a sub-graph. I.e. it may contain other graph elements.
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.graph.model.ModelPackage#getCompositeNode()
 * @model
 * @generated
 */
public interface CompositeNode<E extends GraphElement, C extends Connection<?>> extends SubGraph<E>, Node<C> {
} // CompositeNode
