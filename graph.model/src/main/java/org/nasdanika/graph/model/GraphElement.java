/**
 */
package org.nasdanika.graph.model;

import org.nasdanika.drawio.model.SemanticElement;
import org.nasdanika.ncore.StringIdentity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Graph Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Graph element uniquely identified in the containing graph by a string identifier. This allows to implement graph inheritance - deriving a graph from another graph by removing, adding and replacing graph elements similar to how inheritance works in object-oriented languages.
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.graph.model.ModelPackage#getGraphElement()
 * @model abstract="true"
 * @generated
 */
public interface GraphElement extends StringIdentity, SemanticElement {
} // GraphElement
