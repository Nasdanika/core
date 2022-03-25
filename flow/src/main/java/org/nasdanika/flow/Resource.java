/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

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
 *   <li>{@link org.nasdanika.flow.Resource#getChildren <em>Children</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getResource()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/resource.md' load-doc-reference='doc/participant-load-doc.md'"
 * @generated
 */
public interface Resource extends ServiceProvider<Resource> {

	/**
	 * Returns the value of the '<em><b>Artifacts</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Artifact}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Artifacts which can be stored in this resource. E.g. source code can be stored in a source control system. Derived opposite to Artifact.repositories.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Artifacts</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getResource_Artifacts()
	 * @model transient="true" changeable="false" derived="true"
	 *        annotation="urn:org.nasdanika opposite='repositories'"
	 * @generated
	 */
	EList<Artifact> getArtifacts();

	/**
	 * Returns the value of the '<em><b>Used In</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.FlowElement}<code>&lt;?&gt;</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Flow elements which use this resource.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Used In</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getResource_UsedIn()
	 * @model transient="true" changeable="false" derived="true"
	 *        annotation="urn:org.nasdanika opposite='resources'"
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
	 * Participants which use this resource in their activities.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Used By</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getResource_UsedBy()
	 * @see org.nasdanika.flow.Participant#getResources
	 * @model opposite="resources" transient="true" changeable="false" derived="true"
	 *        annotation="urn:org.nasdanika opposite='resources'"
	 * @generated
	 */
	EList<Participant> getUsedBy();

	/**
	 * Returns the value of the '<em><b>Children</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.nasdanika.flow.Resource},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resources can be organized into a hierarchy (Domains).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Children</em>' map.
	 * @see org.nasdanika.flow.FlowPackage#getResource_Children()
	 * @model mapType="org.nasdanika.flow.ResourceEntry&lt;org.eclipse.emf.ecore.EString, org.nasdanika.flow.Resource&gt;"
	 * @generated
	 */
	EMap<String, Resource> getChildren();
} // Resource
