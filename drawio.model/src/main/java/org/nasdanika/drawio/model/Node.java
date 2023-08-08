/**
 */
package org.nasdanika.drawio.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.Node#getIncoming <em>Incoming</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.Node#getOutgoing <em>Outgoing</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.drawio.model.ModelPackage#getNode()
 * @model
 * @generated
 */
public interface Node extends Layer, LayerElement {

	/**
	 * Returns the value of the '<em><b>Incoming</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.drawio.model.Connection}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.drawio.model.Connection#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incoming</em>' reference list.
	 * @see org.nasdanika.drawio.model.ModelPackage#getNode_Incoming()
	 * @see org.nasdanika.drawio.model.Connection#getTarget
	 * @model opposite="target"
	 * @generated
	 */
	EList<Connection> getIncoming();

	/**
	 * Returns the value of the '<em><b>Outgoing</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.drawio.model.Connection}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.drawio.model.Connection#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing</em>' reference list.
	 * @see org.nasdanika.drawio.model.ModelPackage#getNode_Outgoing()
	 * @see org.nasdanika.drawio.model.Connection#getSource
	 * @model opposite="source"
	 * @generated
	 */
	EList<Connection> getOutgoing();
} // Node
