/**
 */
package org.nasdanika.exec;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Map</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.exec.Map#getEntries <em>Entries</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.exec.ExecPackage#getMap()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/map.md'"
 * @generated
 */
public interface Map extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Entries</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.eclipse.emf.ecore.EObject},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Map entries.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Entries</em>' map.
	 * @see org.nasdanika.exec.ExecPackage#getMap_Entries()
	 * @model mapType="org.nasdanika.exec.Property&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EObject&gt;"
	 * @generated
	 */
	EMap<String, EObject> getEntries();

} // Map
