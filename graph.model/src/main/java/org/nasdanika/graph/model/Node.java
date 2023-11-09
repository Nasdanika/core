/**
 */
package org.nasdanika.graph.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Node is both connection source and target. I.e. it may have both incoming and outgoing connections.
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.graph.model.ModelPackage#getNode()
 * @model
 * @generated
 */
public interface Node<C extends Connection<?>> extends ConnectionSource<C>, ConnectionTarget<C> {
} // Node
