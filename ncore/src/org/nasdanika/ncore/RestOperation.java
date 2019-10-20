/**
 */
package org.nasdanika.ncore;

import java.lang.Object;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rest Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * HTTP Call with JSON payload constructed from arguments.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.RestOperation#getArguments <em>Arguments</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getRestOperation()
 * @model
 * @generated
 */
public interface RestOperation extends HttpCall {
	/**
	 * Returns the value of the '<em><b>Arguments</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.ncore.Entry}<code>&lt;java.lang.Object&gt;</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Operation arguments.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Arguments</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getRestOperation_Arguments()
	 * @model type="org.nasdanika.ncore.Entry&lt;org.eclipse.emf.ecore.EJavaObject&gt;" containment="true"
	 * @generated
	 */
	EList<Entry<Object>> getArguments();

} // RestOperation
