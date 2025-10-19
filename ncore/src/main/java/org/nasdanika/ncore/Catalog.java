/**
 */
package org.nasdanika.ncore;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Catalog</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.Catalog#getItems <em>Items</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getCatalog()
 * @model
 * @generated
 */
public interface Catalog extends DocumentedNamedStringIdentity {
	/**
	 * Returns the value of the '<em><b>Items</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.ncore.DocumentedNamedStringIdentity}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Items</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getCatalog_Items()
	 * @model containment="true" keys="id"
	 * @generated
	 */
	EList<DocumentedNamedStringIdentity> getItems();

} // Catalog
