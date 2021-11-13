/**
 */
package org.nasdanika.ncore;

import org.eclipse.emf.common.util.EList;

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
 *   <li>{@link org.nasdanika.ncore.Map#getValues <em>Values</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getMap()
 * @model
 * @generated
 */
public interface Map extends EObject {
	/**
	 * Returns the value of the '<em><b>Values</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.ncore.Property}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values</em>' reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getMap_Values()
	 * @model keys="name"
	 *        annotation="urn:org.nasdanika reference-type='map: MapProperty\nlist: ListProperty\nstring: StringProperty' value-feature='true'"
	 * @generated
	 */
	EList<Property> getValues();

} // Map
