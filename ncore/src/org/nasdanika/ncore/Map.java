/**
 */
package org.nasdanika.ncore;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Map</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A collection of entries mapping names to results.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.Map#getEntries <em>Entries</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getMap()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='entries'"
 * @generated
 */
public interface Map extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Entries</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.ncore.AbstractEntry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Map entries.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Entries</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getMap_Entries()
	 * @model containment="true"
	 * @generated
	 */
	EList<AbstractEntry> getEntries();

} // Map
