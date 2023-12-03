/**
 */
package org.nasdanika.drawio.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Semantic Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * EClasses mapped/created from diagram elements may implement this interface to trace the elements they were mapped from
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.SemanticElement#getSemanticMappings <em>Semantic Mappings</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.drawio.model.ModelPackage#getSemanticElement()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface SemanticElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Semantic Mappings</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.drawio.model.SemanticMapping}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A list of semantic mappings
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Semantic Mappings</em>' containment reference list.
	 * @see org.nasdanika.drawio.model.ModelPackage#getSemanticElement_SemanticMappings()
	 * @model containment="true"
	 * @generated
	 */
	EList<SemanticMapping> getSemanticMappings();

} // SemanticElement
