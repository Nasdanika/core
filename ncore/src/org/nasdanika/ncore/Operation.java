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
 * Operation computes its result the operation arguments. 
 * 
 * Operation implementation can be defined as follows:
 * 
 * * Fully qualified class name, e.g. ``java.lang.Integer``. An instance of the implementation class is constructed using a contructor which takes ``org.nasdanika.common.Context`` and the results of the arguments, or just the results of the arguments.
 * * Method reference using ``::`` as a separator between the fully qualified class name and the method name. This definition can be used if the type is a functional interface with a single method. If the method is not static then an instance of the implementation class is constructed using a contructor which takes ``org.nasdanika.common.Context`` and the results of the arguments, or just the results of the arguments.
 * * Provider reference using ``->`` as a separator between the fully qualified class name and the provider method. If the method not static then its parameters shall be compatible with Context and as the first argument and then operation arguments, or just with operation arguments without context. Otherwise an instance of the implementation class is constructed using a contructor which takes ``org.nasdanika.common.Context`` and the results of the arguments, or just the results of the arguments and the method shall not take any arguments.
 * 
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
