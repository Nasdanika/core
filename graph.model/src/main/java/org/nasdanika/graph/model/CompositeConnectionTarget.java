/**
 */
package org.nasdanika.graph.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Composite Connection Target</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Connection target which is also as sub-graph, i.e. it may contain other graph elements
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.graph.model.ModelPackage#getCompositeConnectionTarget()
 * @model
 * @generated
 */
public interface CompositeConnectionTarget<E extends GraphElement, C extends Connection<?>> extends SubGraph<E>, ConnectionTarget<C> {
} // CompositeConnectionTarget
