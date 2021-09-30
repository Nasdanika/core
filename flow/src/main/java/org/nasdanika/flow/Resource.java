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
 *   <li>{@link org.nasdanika.flow.Resource#getServices <em>Services</em>}</li>
 *   <li>{@link org.nasdanika.flow.Resource#getArtifacts <em>Artifacts</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getResource()
 * @model
 * @generated
 */
public interface Resource extends PackageElement<Resource> {

	/**
	 * Returns the value of the '<em><b>Services</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.nasdanika.flow.Activity<?>},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Services provided by a resource. Resource service activities imply the containing resource. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Services</em>' map.
	 * @see org.nasdanika.flow.FlowPackage#getResource_Services()
	 * @model mapType="org.nasdanika.flow.ActivityEntry&lt;org.eclipse.emf.ecore.EString, org.nasdanika.flow.Activity&lt;?&gt;&gt;"
	 * @generated
	 */
	EMap<String, Activity<?>> getServices();

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
} // Resource
