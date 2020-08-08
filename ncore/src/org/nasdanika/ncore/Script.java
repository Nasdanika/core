/**
 */
package org.nasdanika.ncore;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Script</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Evaluates JavaScript code with provided bindings. Context is available via ``context`` binding.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.Script#getBindings <em>Bindings</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getScript()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='bindings'"
 * @generated
 */
public interface Script extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Bindings</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.ncore.AbstractEntry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Script bindings. ``context`` binding is reserved for execution context.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bindings</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getScript_Bindings()
	 * @model containment="true"
	 * @generated
	 */
	EList<AbstractEntry> getBindings();

} // Script
