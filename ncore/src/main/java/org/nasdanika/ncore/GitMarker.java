/**
 */
package org.nasdanika.ncore;

import java.lang.String;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Git Marker</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Marker with Git information such as a map of remotes.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.GitMarker#getPath <em>Path</em>}</li>
 *   <li>{@link org.nasdanika.ncore.GitMarker#getRemotes <em>Remotes</em>}</li>
 *   <li>{@link org.nasdanika.ncore.GitMarker#getBranch <em>Branch</em>}</li>
 *   <li>{@link org.nasdanika.ncore.GitMarker#getHead <em>Head</em>}</li>
 *   <li>{@link org.nasdanika.ncore.GitMarker#getHeadRefs <em>Head Refs</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getGitMarker()
 * @model
 * @generated
 */
public interface GitMarker extends Marker {
	/**
	 * Returns the value of the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Path in the repository
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Path</em>' attribute.
	 * @see #setPath(String)
	 * @see org.nasdanika.ncore.NcorePackage#getGitMarker_Path()
	 * @model
	 * @generated
	 */
	String getPath();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.GitMarker#getPath <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(String value);

	/**
	 * Returns the value of the '<em><b>Remotes</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A map of remote names to url's.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Remotes</em>' map.
	 * @see org.nasdanika.ncore.NcorePackage#getGitMarker_Remotes()
	 * @model mapType="org.nasdanika.ncore.StringEntry&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getRemotes();

	/**
	 * Returns the value of the '<em><b>Branch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Short branch name
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Branch</em>' attribute.
	 * @see #setBranch(String)
	 * @see org.nasdanika.ncore.NcorePackage#getGitMarker_Branch()
	 * @model
	 * @generated
	 */
	String getBranch();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.GitMarker#getBranch <em>Branch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Branch</em>' attribute.
	 * @see #getBranch()
	 * @generated
	 */
	void setBranch(String value);

	/**
	 * Returns the value of the '<em><b>Head</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Commit ID of the head
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Head</em>' attribute.
	 * @see #setHead(String)
	 * @see org.nasdanika.ncore.NcorePackage#getGitMarker_Head()
	 * @model
	 * @generated
	 */
	String getHead();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.GitMarker#getHead <em>Head</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Head</em>' attribute.
	 * @see #getHead()
	 * @generated
	 */
	void setHead(String value);

	/**
	 * Returns the value of the '<em><b>Head Refs</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Refs on the head commit excluding the head itself. E.g. branch, tags.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Head Refs</em>' attribute list.
	 * @see org.nasdanika.ncore.NcorePackage#getGitMarker_HeadRefs()
	 * @model
	 * @generated
	 */
	EList<String> getHeadRefs();

} // GitMarker
