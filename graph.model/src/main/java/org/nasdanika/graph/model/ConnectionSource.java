/**
 */
package org.nasdanika.graph.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connection Source</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.graph.model.ConnectionSource#getOutgoingConnections <em>Outgoing Connections</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.graph.model.ModelPackage#getConnectionSource()
 * @model
 * @generated
 */
public interface ConnectionSource<C extends Connection<?>> extends GraphElement {
	/**
	 * Returns the value of the '<em><b>Outgoing Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing Connections</em>' containment reference list.
	 * @see org.nasdanika.graph.model.ModelPackage#getConnectionSource_OutgoingConnections()
	 * @model containment="true" keys="id"
	 * @generated
	 */
	EList<C> getOutgoingConnections();

} // ConnectionSource
