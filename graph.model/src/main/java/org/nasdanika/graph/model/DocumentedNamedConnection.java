/**
 */
package org.nasdanika.graph.model;

import org.nasdanika.ncore.DocumentedNamedStringIdentity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Documented Named Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Connection with a name and documentation
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.graph.model.ModelPackage#getDocumentedNamedConnection()
 * @model
 * @generated
 */
public interface DocumentedNamedConnection<T extends ConnectionTarget<?>> extends Connection<T>, DocumentedNamedStringIdentity {
} // DocumentedNamedConnection
