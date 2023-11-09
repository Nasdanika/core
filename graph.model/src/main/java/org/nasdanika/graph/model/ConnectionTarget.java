/**
 */
package org.nasdanika.graph.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connection Target</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A graph element which can have incoming connections
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.graph.model.ConnectionTarget#getIncomingConnections <em>Incoming Connections</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.graph.model.ModelPackage#getConnectionTarget()
 * @model
 * @generated
 */
public interface ConnectionTarget<C extends Connection<?>> extends GraphElement {
	/**
	 * Returns the value of the '<em><b>Incoming Connections</b></em>' reference list.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.graph.model.Connection#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Incoming connections
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Incoming Connections</em>' reference list.
	 * @see org.nasdanika.graph.model.ModelPackage#getConnectionTarget_IncomingConnections()
	 * @see org.nasdanika.graph.model.Connection#getTarget
	 * @model opposite="target"
	 * @generated
	 */
	EList<C> getIncomingConnections();

} // ConnectionTarget
