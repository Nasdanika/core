/**
 */
package org.nasdanika.ncore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Supplier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Supplier computes it result using an adapter obtained from a factory. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.Supplier#getFactory <em>Factory</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getSupplier()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='factory'"
 * @generated
 */
public interface Supplier extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Factory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Supplier named factory id in the form of ``<bundle symbolic name>/<factory id>``. 
	 * The factory shall be for ${javadoc/org.nasdanika.common.SupplierFactory} and ${javadoc/org.nasdanika.common.FunctionFactory} for [Operation](Operation.html) and [Function](Function.html).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Factory</em>' attribute.
	 * @see #setFactory(String)
	 * @see org.nasdanika.ncore.NcorePackage#getSupplier_Factory()
	 * @model
	 * @generated
	 */
	String getFactory();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.Supplier#getFactory <em>Factory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Factory</em>' attribute.
	 * @see #getFactory()
	 * @generated
	 */
	void setFactory(String value);

} // Supplier
