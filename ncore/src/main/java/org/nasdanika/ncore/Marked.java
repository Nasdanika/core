/**
 */
package org.nasdanika.ncore;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Marked</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.Marked#getMarkers <em>Markers</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getMarked()
 * @model interface="true" abstract="true" superTypes="org.nasdanika.ncore.IMarked"
 *        annotation="urn:org.nasdanika documentation-reference='doc/marked.md'"
 * @generated
 */
public interface Marked extends EObject, org.nasdanika.persistence.Marked {
	/**
	 * Returns the value of the '<em><b>Markers</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.ncore.Marker}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Markers pointing to where this model element was loaded from - location, line and column numbers. 
	 * Multiple markers are supported for situations when a single model element is loaded from multiple locations, e.g. a row in an Excel document or a database and then pom.xml and readme.md is a source repository.
	 * Another possiblity is an element being created from a prototype and then loaded - in this case the object will inherit markers from its prototype and will have a marker point to the location where it was loaded from.
	 * Markers are listed in the reverse order, i.e. the prototype marker would be after the load marker in the list.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Markers</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getMarked_Markers()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika loadable='false'"
	 * @generated
	 */
	EList<Marker> getMarkers();

} // Marked
