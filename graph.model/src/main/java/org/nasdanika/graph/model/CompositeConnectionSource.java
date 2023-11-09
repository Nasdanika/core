/**
 */
package org.nasdanika.graph.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Composite Connection Source</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Connection source which is also a sub-graph. I.e. it may contain other graph elements.
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.graph.model.ModelPackage#getCompositeConnectionSource()
 * @model
 * @generated
 */
public interface CompositeConnectionSource<E extends GraphElement, C extends Connection<?>> extends SubGraph<E>, ConnectionSource<C> {
} // CompositeConnectionSource
