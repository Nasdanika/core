/**
 */
package org.nasdanika.graph.model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Graph</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.graph.model.Graph#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.graph.model.ModelPackage#getGraph()
 * @model
 * @generated
 */
public interface Graph<E extends GraphElement> extends EObject {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see org.nasdanika.graph.model.ModelPackage#getGraph_Elements()
	 * @model containment="true" keys="id"
	 * @generated
	 */
	EList<E> getElements();
	
} // Graph
