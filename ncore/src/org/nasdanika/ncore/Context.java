/**
 */
package org.nasdanika.ncore;

import java.lang.Object;

import org.eclipse.emf.common.util.EList;
import org.nasdanika.common.SupplierFactory;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Context</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Produces ``org.nasdanika.common.Context``. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.Context#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getContext()
 * @model superTypes="org.nasdanika.ncore.ModelElement org.nasdanika.ncore.WorkFactory&lt;org.nasdanika.ncore.IContext&gt;"
 * @generated
 */
public interface Context extends ModelElement, SupplierFactory<org.nasdanika.common.Context> {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.common.SupplierFactory}<code>&lt;java.lang.Object&gt;</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Context elements. Elements which extend Entry are treated as context properties and unnamed typed elments are treated as services.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getContext_Elements()
	 * @model type="org.nasdanika.ncore.WorkFactory&lt;org.eclipse.emf.ecore.EJavaObject&gt;" containment="true"
	 * @generated
	 */
	EList<SupplierFactory<Object>> getElements();

} // Context
