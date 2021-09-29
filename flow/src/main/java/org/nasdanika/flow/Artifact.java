/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Artifact</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.Artifact#getRepositories <em>Repositories</em>}</li>
 *   <li>{@link org.nasdanika.flow.Artifact#getRepositoryKeys <em>Repository Keys</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getArtifact()
 * @model
 * @generated
 */
public interface Artifact extends PackageElement<Artifact> {
	/**
	 * Returns the value of the '<em><b>Repositories</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Resource}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Repositories</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getArtifact_Repositories()
	 * @model changeable="false" derived="true"
	 * @generated
	 */
	EList<Resource> getRepositories();

	/**
	 * Returns the value of the '<em><b>Repository Keys</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Keys of artifact's repositories relative to the containing package ``resources/`` reference.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Repository Keys</em>' attribute list.
	 * @see org.nasdanika.flow.FlowPackage#getArtifact_RepositoryKeys()
	 * @model annotation="urn:org.nasdanika load-key='repositories'"
	 * @generated
	 */
	EList<String> getRepositoryKeys();

} // Artifact
