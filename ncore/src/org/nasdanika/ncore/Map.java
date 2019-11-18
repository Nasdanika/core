/**
 */
package org.nasdanika.ncore;

import java.lang.Object;

import org.eclipse.emf.common.util.EList;
import org.nasdanika.common.SupplierFactory;

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
 * @model superTypes="org.nasdanika.ncore.ModelElement org.nasdanika.ncore.ISupplierFactory&lt;org.eclipse.emf.ecore.EMap&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EJavaObject&gt;&gt;"
 * @generated
 */
public interface Map extends ModelElement, SupplierFactory<java.util.Map<String, Object>> {
	/**
	 * Returns the value of the '<em><b>Entries</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.ncore.Entry}<code>&lt;java.lang.Object&gt;</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Map entries.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Entries</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getMap_Entries()
	 * @model type="org.nasdanika.ncore.Entry&lt;org.eclipse.emf.ecore.EJavaObject&gt;" containment="true"
	 * @generated
	 */
	EList<Entry<Object>> getEntries();

} // Map
