/**
 */
package org.nasdanika.graph.model;

import org.nasdanika.ncore.StringIdentity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Connections are contained by their source and uniquiely identified in the source by a string id. This allows to implement connection inheritance behaviors such as overriding (replacing), adding, or removing.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.graph.model.Connection#getTarget <em>Target</em>}</li>
 *   <li>{@link org.nasdanika.graph.model.Connection#isBidirectional <em>Bidirectional</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.graph.model.ModelPackage#getConnection()
 * @model
 * @generated
 */
public interface Connection<T extends ConnectionTarget<?>> extends StringIdentity {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.graph.model.ConnectionTarget#getIncomingConnections <em>Incoming Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Connection target
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(ConnectionTarget)
	 * @see org.nasdanika.graph.model.ModelPackage#getConnection_Target()
	 * @see org.nasdanika.graph.model.ConnectionTarget#getIncomingConnections
	 * @model opposite="incomingConnections" required="true"
	 * @generated
	 */
	T getTarget();

	/**
	 * Sets the value of the '{@link org.nasdanika.graph.model.Connection#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(T value);

	/**
	 * Returns the value of the '<em><b>Bidirectional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates that the connection is bidirectional
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bidirectional</em>' attribute.
	 * @see #setBidirectional(boolean)
	 * @see org.nasdanika.graph.model.ModelPackage#getConnection_Bidirectional()
	 * @model
	 * @generated
	 */
	boolean isBidirectional();

	/**
	 * Sets the value of the '{@link org.nasdanika.graph.model.Connection#isBidirectional <em>Bidirectional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bidirectional</em>' attribute.
	 * @see #isBidirectional()
	 * @generated
	 */
	void setBidirectional(boolean value);

} // Connection
