/**
 */
package org.nasdanika.ncore;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Operation computes its result the operation arguments using a ${javadoc/org.nasdanika.common.FunctionFactory} adapter created by the factory.
 * Arguments are passed as a list of objects to the function.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.Operation#getArguments <em>Arguments</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getOperation()
 * @model
 * @generated
 */
public interface Operation extends Supplier {
	/**
	 * Returns the value of the '<em><b>Arguments</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Operation arguments.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Arguments</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getOperation_Arguments()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getArguments();

} // Operation
