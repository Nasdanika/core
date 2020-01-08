/**
 */
package org.nasdanika.ncore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Supplier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Supplier is a typed element which computes it result using an implementation operation - constructor or method. Supplier is equivalent to an operation without arguments.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.Supplier#getImplementation <em>Implementation</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getSupplier()
 * @model
 * @generated
 */
public interface Supplier extends TypedElement {
	/**
	 * Returns the value of the '<em><b>Implementation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Supplier implementation. If empty, type's constructor is used as a provider.
	 * 
	 * Supplier implementation can be defined as follows:
	 * 
	 * * Fully qualified class name, e.g. ``java.lang.Integer``. If both type and implementation as defined then the implementation must be a subclass/implementation of type or there should be a Converter service in the context which converts implementation into type. An instance of the implementation class is constructed using a contructor which takes ``org.nasdanika.common.Context`` or the default contructor if there is no context constructor.
	 * * Method reference using ``::`` as a separator between the fully qualified class name and the method name. This definition can be used if the type is a functional interface with a single method. If the method is not static then an instance of the implementation class is constructed using a contructor which takes ``org.nasdanika.common.Context`` or the default contructor if there is no context constructor.
	 * * Provider reference using ``->`` as a separator between the fully qualified class name and the provider method. If the method is static then a method with a given name which taks a single Context argument or not arguments is used. Otherwise an instance of the implementation class is constructed using a contructor which takes ``org.nasdanika.common.Context`` or the default contructor if there is no context constructor and a no-argument method with a given name is used.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Implementation</em>' attribute.
	 * @see #setImplementation(String)
	 * @see org.nasdanika.ncore.NcorePackage#getSupplier_Implementation()
	 * @model
	 * @generated
	 */
	String getImplementation();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.Supplier#getImplementation <em>Implementation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implementation</em>' attribute.
	 * @see #getImplementation()
	 * @generated
	 */
	void setImplementation(String value);

} // Supplier
