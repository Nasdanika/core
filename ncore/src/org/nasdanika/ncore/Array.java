/**
 */
package org.nasdanika.ncore;

import java.lang.Object;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.CommandFactory;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A sequence of other elements.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.Array#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getArray()
 * @model superTypes="org.nasdanika.ncore.CommandFactory&lt;org.eclipse.emf.ecore.EEList&lt;org.eclipse.emf.ecore.EJavaObject&gt;&gt;"
 * @generated
 */
public interface Array extends EObject, CommandFactory<EList<Object>> {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.common.CommandFactory}<code>&lt;java.lang.Object&gt;</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array elements.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getArray_Elements()
	 * @model type="org.nasdanika.ncore.CommandFactory&lt;org.eclipse.emf.ecore.EJavaObject&gt;" containment="true"
	 * @generated
	 */
	EList<CommandFactory<Object>> getElements();

} // Array
