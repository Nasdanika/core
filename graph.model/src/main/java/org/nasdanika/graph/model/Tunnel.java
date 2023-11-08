/**
 */
package org.nasdanika.graph.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tunnel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.graph.model.Tunnel#getConnections <em>Connections</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.graph.model.ModelPackage#getTunnel()
 * @model
 * @generated
 */
public interface Tunnel<T extends ConnectionTarget<?>, C extends Connection<?>> extends Connection<T> {
	/**
	 * Returns the value of the '<em><b>Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connections</em>' reference list.
	 * @see org.nasdanika.graph.model.ModelPackage#getTunnel_Connections()
	 * @model
	 * @generated
	 */
	EList<C> getConnections();

} // Tunnel
