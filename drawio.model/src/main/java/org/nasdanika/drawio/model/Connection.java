/**
 */
package org.nasdanika.drawio.model;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.Connection#getSource <em>Source</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.Connection#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.drawio.model.ModelPackage#getConnection()
 * @model
 * @generated
 */
public interface Connection extends LayerElement {

	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.drawio.model.Node#getOutgoing <em>Outgoing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(Node)
	 * @see org.nasdanika.drawio.model.ModelPackage#getConnection_Source()
	 * @see org.nasdanika.drawio.model.Node#getOutgoing
	 * @model opposite="outgoing"
	 * @generated
	 */
	Node getSource();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.Connection#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Node value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.drawio.model.Node#getIncoming <em>Incoming</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Node)
	 * @see org.nasdanika.drawio.model.ModelPackage#getConnection_Target()
	 * @see org.nasdanika.drawio.model.Node#getIncoming
	 * @model opposite="incoming"
	 * @generated
	 */
	Node getTarget();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.Connection#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Node value);
} // Connection
