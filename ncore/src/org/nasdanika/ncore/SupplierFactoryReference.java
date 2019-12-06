/**
 */
package org.nasdanika.ncore;

import java.lang.Object;

import org.nasdanika.common.SupplierFactory;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Supplier Factory Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * SupplierFactoryReference delegates its functionality to its target. It is primarily used to build multi-resource (file) models.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.SupplierFactoryReference#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getSupplierFactoryReference()
 * @model superTypes="org.nasdanika.ncore.ModelElement org.nasdanika.ncore.ISupplierFactory&lt;org.eclipse.emf.ecore.EJavaObject&gt;"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='target'"
 * @generated
 */
public interface SupplierFactoryReference extends ModelElement, SupplierFactory<Object> {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Reference to the target supplier factory.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(SupplierFactory)
	 * @see org.nasdanika.ncore.NcorePackage#getSupplierFactoryReference_Target()
	 * @model type="org.nasdanika.ncore.ISupplierFactory&lt;org.eclipse.emf.ecore.EJavaObject&gt;" required="true"
	 * @generated
	 */
	SupplierFactory<Object> getTarget();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.SupplierFactoryReference#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(SupplierFactory<Object> value);

} // SupplierFactoryReference
