/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.Resource#getArtifacts <em>Artifacts</em>}</li>
 *   <li>{@link org.nasdanika.flow.Resource#getUsedIn <em>Used In</em>}</li>
 *   <li>{@link org.nasdanika.flow.Resource#getUsedBy <em>Used By</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getResource()
 * @model
 * @generated
 */
public interface Resource extends ServiceProvider<Resource> {

	/**
	 * Returns the value of the '<em><b>Artifacts</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Artifact}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Artifact#getRepositories <em>Repositories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Derived opposite to Artifact.repositories.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Artifacts</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getResource_Artifacts()
	 * @see org.nasdanika.flow.Artifact#getRepositories
	 * @model opposite="repositories" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Artifact> getArtifacts();

	/**
	 * Returns the value of the '<em><b>Used In</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.FlowElement}<code>&lt;?&gt;</code>.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.FlowElement#getResources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Flow elements which use this resource.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Used In</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getResource_UsedIn()
	 * @see org.nasdanika.flow.FlowElement#getResources
	 * @model opposite="resources" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<FlowElement<?>> getUsedIn();

	/**
	 * Returns the value of the '<em><b>Used By</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Participant}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Participant#getResources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Participants use this resource in their activities.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Used By</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getResource_UsedBy()
	 * @see org.nasdanika.flow.Participant#getResources
	 * @model opposite="resources" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Participant> getUsedBy();
} // Resource
