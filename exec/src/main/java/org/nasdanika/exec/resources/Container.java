/**
 */
package org.nasdanika.exec.resources;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.nasdanika.exec.resources.ResourcesPackage#getContainer()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/resources/container.md'"
 * @generated
 */
public interface Container extends Resource {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	Container getContainer(String path);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	File getFile(String path);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	Resource find(String path);
} // Container
