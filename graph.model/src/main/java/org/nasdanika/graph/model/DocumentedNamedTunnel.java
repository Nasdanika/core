/**
 */
package org.nasdanika.graph.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Documented Named Tunnel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Tunnel with a name and documentation
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.graph.model.ModelPackage#getDocumentedNamedTunnel()
 * @model
 * @generated
 */
public interface DocumentedNamedTunnel<T extends ConnectionTarget<?>, C extends Connection<?>> extends Tunnel<T, C>, DocumentedNamedConnection<T> {
} // DocumentedNamedTunnel
