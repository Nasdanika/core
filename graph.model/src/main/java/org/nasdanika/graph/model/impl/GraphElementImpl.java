/**
 */
package org.nasdanika.graph.model.impl;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.graph.model.GraphElement;
import org.nasdanika.graph.model.ModelPackage;

import org.nasdanika.ncore.impl.StringIdentityImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Graph Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class GraphElementImpl extends StringIdentityImpl implements GraphElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GraphElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.GRAPH_ELEMENT;
	}

} //GraphElementImpl
